import {screen, waitFor} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {describe, expect, it, vi} from 'vitest';

import FieldsStep from '../views/steps/FieldsStep.jsx';
import {isValidConsentURL} from '../views/steps/fieldModel.js';
import {renderWithApp, t} from './renderWithApp.jsx';

const CAMPAIGN = {
	id: 7,
	mode: 'QUICK',
	name: 'Annual meeting',
	permissions: {UPDATE: true},
	status: 'DRAFT',
};

function fieldsApi(formVersion) {
	return {
		getFieldPresets: vi.fn(() => Promise.resolve([])),
		getFieldTypes: vi.fn(() => Promise.resolve([{key: 'text', optionBased: false}])),
		getForm: vi.fn(() => Promise.resolve(formVersion)),
		updateForm: vi.fn((campaignId, payload) => Promise.resolve({...formVersion, ...payload})),
	};
}

function renderStep(api, onNext) {
	return renderWithApp(
		<FieldsStep campaign={CAMPAIGN} onBack={vi.fn()} onNext={onNext} onReloadCampaign={vi.fn()} />,
		{api}
	);
}

describe('isValidConsentURL', () => {
	it('accepts http(s) links and portal paths, like the server', () => {
		expect(isValidConsentURL('https://www.example.com/privacy')).toBe(true);
		expect(isValidConsentURL('http://localhost:8080/web/guest/privacy')).toBe(true);
		expect(isValidConsentURL('/web/guest/privacy')).toBe(true);

		expect(isValidConsentURL('')).toBe(false);
		expect(isValidConsentURL('www.example.com')).toBe(false);
		expect(isValidConsentURL('javascript:alert(1)')).toBe(false);
		expect(isValidConsentURL('//evil.example')).toBe(false);
		expect(isValidConsentURL('https://example .com')).toBe(false);
	});
});

describe('FieldsStep consent', () => {
	it('is optional: off by default and saving needs no privacy link', async () => {
		const user = userEvent.setup();

		const api = fieldsApi({
			formFields: [{fieldKey: 'name', id: 1, label: 'Name', type: 'text'}],
			status: 'DRAFT',
		});

		const onNext = vi.fn();

		renderStep(api, onNext);

		const checkbox = await screen.findByLabelText(t('consent-required'));

		expect(checkbox.checked).toBe(false);
		expect(screen.queryByLabelText(t('consent-url'), {exact: false})).toBeNull();

		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		await waitFor(() => expect(onNext).toHaveBeenCalled());

		expect(api.updateForm).not.toHaveBeenCalled();
	});

	it('asks for consent with an optional, validated privacy link', async () => {
		const user = userEvent.setup();

		const api = fieldsApi({formFields: [], status: 'DRAFT'});

		const onNext = vi.fn();

		renderStep(api, onNext);

		await user.click(await screen.findByLabelText(t('consent-required')));

		const url = screen.getByLabelText(t('consent-url'), {exact: false});

		expect(screen.getByLabelText(t('consent-text'), {exact: false}).placeholder).toBe(
			t('consent-default-text')
		);

		await user.type(url, 'www.example.com');
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		expect(await screen.findByText(t('consent-url-invalid'))).toBeTruthy();
		expect(onNext).not.toHaveBeenCalled();

		await user.clear(url);
		await user.type(url, 'https://www.example.com/privacy');
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		await waitFor(() => expect(onNext).toHaveBeenCalled());

		const payload = api.updateForm.mock.calls[0][1];

		expect(payload.consentRequired).toBe(true);
		expect(payload.consentURL).toBe('https://www.example.com/privacy');
	});

	it('saves consent without a privacy link', async () => {
		const user = userEvent.setup();

		const api = fieldsApi({formFields: [], status: 'DRAFT'});

		const onNext = vi.fn();

		renderStep(api, onNext);

		await user.click(await screen.findByLabelText(t('consent-required')));
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		await waitFor(() => expect(onNext).toHaveBeenCalled());

		const payload = api.updateForm.mock.calls[0][1];

		expect(payload.consentRequired).toBe(true);
		expect('consentURL' in payload).toBe(false);
	});
});
