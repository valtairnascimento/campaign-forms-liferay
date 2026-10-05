import {screen, waitFor, within} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {describe, expect, it, vi} from 'vitest';

import {ApiError} from '../api/client.js';
import CampaignList from '../views/CampaignList.jsx';
import FieldsStep from '../views/steps/FieldsStep.jsx';
import InfoStep from '../views/steps/InfoStep.jsx';
import PublishStep from '../views/steps/PublishStep.jsx';
import {renderWithApp, t} from './renderWithApp.jsx';

const ALL_PERMISSIONS = {
	DELETE: true,
	DELETE_SUBMISSIONS: true,
	MANAGE_NOTIFICATIONS: true,
	PUBLISH: true,
	UPDATE: true,
	VIEW_SUBMISSIONS: true,
};

function campaign(overrides = {}) {
	return {
		hasUnpublishedChanges: false,
		id: 7,
		mode: 'QUICK',
		name: 'Encontro de RH',
		permissions: ALL_PERMISSIONS,
		settings: {},
		slug: 'encontro-de-rh',
		status: 'DRAFT',
		submissionsCount: 0,
		...overrides,
	};
}

describe('InfoStep', () => {
	it('suggests the slug from the name until the user edits it', async () => {
		const user = userEvent.setup();

		renderWithApp(<InfoStep campaign={null} mode="QUICK" onSaved={vi.fn()} />);

		const name = screen.getByLabelText(/^Nome/);
		const slug = screen.getByLabelText(/^Endereço \(URL\)/);

		await user.type(name, 'Encontro RH Londrina');

		expect(slug.value).toBe('encontro-rh-londrina');
		expect(
			screen.getByText(/web\/acme\/campaign\/-\/campaign\/encontro-rh-londrina/)
		).toBeTruthy();

		await user.clear(slug);
		await user.type(slug, 'rh-ldn');
		await user.type(name, ' 2026');

		expect(slug.value).toBe('rh-ldn');
	});

	it('creates the campaign in the chosen mode', async () => {
		const user = userEvent.setup();

		const addCampaign = vi.fn(() => Promise.resolve(campaign({id: 99})));
		const onSaved = vi.fn();

		renderWithApp(<InfoStep campaign={null} mode="QUICK" onSaved={onSaved} />, {
			api: {addCampaign},
		});

		await user.type(screen.getByLabelText(/^Nome/), 'Feira de Inovação');
		await user.click(screen.getByRole('button', {name: t('create-and-continue')}));

		await waitFor(() => expect(onSaved).toHaveBeenCalled());

		expect(addCampaign.mock.calls[0][0]).toMatchObject({
			defaultLanguageId: 'pt_BR',
			endDate: null,
			mode: 'QUICK',
			name: 'Feira de Inovação',
			slug: 'feira-de-inovacao',
			submissionLimit: 0,
		});
		expect(onSaved.mock.calls[0][0].id).toBe(99);
	});

	it('validates locally and shows server errors on the field', async () => {
		const user = userEvent.setup();

		const updateCampaign = vi.fn(() =>
			Promise.reject(
				new ApiError({
					code: 'DUPLICATE_SLUG',
					message: 'Já existe uma captação com este endereço.',
					status: 409,
				})
			)
		);

		renderWithApp(<InfoStep campaign={campaign()} onSaved={vi.fn()} />, {
			api: {updateCampaign},
		});

		const name = screen.getByLabelText(/^Nome/);

		await user.clear(name);
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		expect(name.getAttribute('aria-invalid')).toBe('true');
		expect(screen.getByText(t('field-required'))).toBeTruthy();
		expect(updateCampaign).not.toHaveBeenCalled();

		await user.type(name, 'Outro nome');
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		const slug = screen.getByLabelText(/^Endereço \(URL\)/);

		await waitFor(() => expect(slug.getAttribute('aria-invalid')).toBe('true'));

		expect(screen.getByText('Já existe uma captação com este endereço.')).toBeTruthy();

		expect(updateCampaign.mock.calls[0][1]).toMatchObject({
			name: 'Outro nome',
			settings: {},
			slug: 'encontro-de-rh',
		});
		expect(updateCampaign.mock.calls[0][1].permissions).toBeUndefined();
	});
});

describe('FieldsStep', () => {
	const fieldTypes = [
		{key: 'text', optionBased: false},
		{key: 'email', optionBased: false},
		{key: 'select', optionBased: true},
	];

	const presets = [
		{formField: {fieldKey: 'nome', label: 'Nome', type: 'text'}, key: 'nome', label: 'Nome'},
		{formField: {fieldKey: 'telefone', label: 'Telefone', type: 'tel'}, key: 'telefone', label: 'Telefone'},
	];

	it('edits a published form through a draft, matching fields by key', async () => {
		const user = userEvent.setup();

		const published = {
			formFields: [
				{fieldKey: 'nome', id: 1, label: 'Nome', presetKey: 'nome', required: true, type: 'text'},
				{fieldKey: 'email', id: 2, label: 'E-mail', required: false, type: 'email'},
			],
			status: 'PUBLISHED',
		};

		const draft = {
			formFields: [
				{fieldKey: 'nome', id: 11, label: 'Nome', presetKey: 'nome', required: true, type: 'text'},
				{fieldKey: 'email', id: 12, label: 'E-mail', required: false, type: 'email'},
			],
			status: 'DRAFT',
		};

		const api = {
			createFormDraft: vi.fn(() => Promise.resolve(draft)),
			getFieldPresets: vi.fn(() => Promise.resolve(presets)),
			getFieldTypes: vi.fn(() => Promise.resolve(fieldTypes)),
			getForm: vi
				.fn()
				.mockResolvedValueOnce(published)
				.mockResolvedValue({
					...draft,
					formFields: [draft.formFields[0], {...draft.formFields[1], required: true}],
				}),
			updateField: vi.fn(() => Promise.resolve({})),
		};

		const onReloadCampaign = vi.fn();

		renderWithApp(
			<FieldsStep
				campaign={campaign({status: 'PUBLISHED'})}
				onBack={vi.fn()}
				onNext={vi.fn()}
				onReloadCampaign={onReloadCampaign}
			/>,
			{api}
		);

		expect(await screen.findByText(t('published-form-edit-info'))).toBeTruthy();

		expect(screen.queryByRole('button', {name: 'Nome'})).toBeNull();
		expect(screen.getByRole('button', {name: 'Telefone'})).toBeTruthy();

		await user.click(screen.getByRole('button', {name: t('actions-for-x').replace('{0}', 'E-mail')}));
		await user.click(screen.getByRole('menuitem', {name: t('make-required')}));

		await waitFor(() => expect(api.updateField).toHaveBeenCalled());

		expect(api.createFormDraft).toHaveBeenCalledWith(7);
		expect(api.updateField.mock.calls[0][0]).toBe(12);
		expect(api.updateField.mock.calls[0][1]).toEqual({
			fieldKey: 'email',
			label: 'E-mail',
			required: true,
			type: 'email',
		});
		expect(onReloadCampaign).toHaveBeenCalled();
	});

	it('adds a custom field with options', async () => {
		const user = userEvent.setup();

		const empty = {formFields: [], status: 'DRAFT'};

		const api = {
			addField: vi.fn(() => Promise.resolve({})),
			getFieldPresets: vi.fn(() => Promise.resolve([])),
			getFieldTypes: vi.fn(() => Promise.resolve(fieldTypes)),
			getForm: vi.fn(() => Promise.resolve(empty)),
		};

		renderWithApp(
			<FieldsStep
				campaign={campaign()}
				onBack={vi.fn()}
				onNext={vi.fn()}
				onReloadCampaign={vi.fn()}
			/>,
			{api}
		);

		await user.click(await screen.findByRole('button', {name: t('custom-field')}));

		const dialog = screen.getByRole('dialog');

		await user.type(within(dialog).getByLabelText(/^Pergunta ou rótulo/), 'Cidade de atuação');
		await user.selectOptions(within(dialog).getByLabelText(/^Tipo/), 'select');

		expect(within(dialog).getByLabelText(/^Identificador/).value).toBe('cidadeDeAtuacao');

		await user.click(within(dialog).getByRole('button', {name: t('add-field')}));

		expect(within(dialog).getByText(t('options-required'))).toBeTruthy();
		expect(api.addField).not.toHaveBeenCalled();

		await user.click(within(dialog).getByRole('button', {name: t('add-option')}));
		await user.type(within(dialog).getByLabelText('Opção 1'), 'São José dos Pinhais');
		await user.click(within(dialog).getByRole('button', {name: t('add-field')}));

		await waitFor(() => expect(api.addField).toHaveBeenCalled());

		expect(api.addField.mock.calls[0][1]).toMatchObject({
			fieldKey: 'cidadeDeAtuacao',
			fieldOptions: [{label: 'São José dos Pinhais', value: 'sao-jose-dos-pinhais'}],
			label: 'Cidade de atuação',
			required: false,
			type: 'select',
			validation: {},
			width: 'FULL',
		});

		await waitFor(() => expect(screen.queryByRole('dialog')).toBeNull());
	});
});

describe('CampaignList', () => {
	it('shows only the actions the user may perform', async () => {
		const user = userEvent.setup();

		const api = {
			getCampaigns: vi.fn(() =>
				Promise.resolve({
					items: [
						campaign({status: 'PUBLISHED', submissionsCount: 3}),
						campaign({
							id: 8,
							name: 'Somente leitura',
							permissions: {VIEW_SUBMISSIONS: true},
							slug: 'leitura',
						}),
					],
					totalCount: 2,
				})
			),
		};

		renderWithApp(<CampaignList />, {api});

		expect(await screen.findByRole('link', {name: 'Encontro de RH'})).toBeTruthy();

		expect(screen.queryByRole('link', {name: 'Somente leitura'})).toBeNull();

		await user.click(
			screen.getByRole('button', {name: t('actions-for-x').replace('{0}', 'Encontro de RH')})
		);

		const items = screen.getAllByRole('menuitem').map((item) => item.textContent);

		expect(items).toEqual([
			t('edit'),
			t('view'),
			t('convert-to-custom-page'),
			t('duplicate'),
			t('results'),
			t('archive'),
		]);

		await user.keyboard('{Escape}');

		await user.click(
			screen.getByRole('button', {name: t('actions-for-x').replace('{0}', 'Somente leitura')})
		);

		expect(screen.getAllByRole('menuitem').map((item) => item.textContent)).toEqual([
			t('duplicate'),
			t('results'),
		]);
	});

	it('shows the empty state and hides creation without permission', async () => {
		renderWithApp(<CampaignList />, {
			api: {getCampaigns: vi.fn(() => Promise.resolve({items: [], totalCount: 0}))},
			config: {permissions: {ADD_CAMPAIGN: false}},
		});

		expect(await screen.findByText(t('no-campaigns'))).toBeTruthy();
		expect(screen.queryByRole('button', {name: t('new-campaign')})).toBeNull();
	});
});

describe('PublishStep', () => {
	it('blocks publication while there are issues and links to the fix', async () => {
		const api = {
			getPublicationIssues: vi.fn(() =>
				Promise.resolve([
					{code: 'form-has-no-fields', message: 'O formulário precisa ter pelo menos um campo.'},
				])
			),
		};

		renderWithApp(
			<PublishStep
				campaign={campaign()}
				onBack={vi.fn()}
				onCampaignChange={vi.fn()}
				onReloadCampaign={vi.fn()}
			/>,
			{api}
		);

		expect(
			await screen.findByText(/O formulário precisa ter pelo menos um campo./)
		).toBeTruthy();
		expect(screen.getByRole('link', {name: t('fix')}).getAttribute('href')).toBe(
			'#/campaigns/7/fields'
		);
		expect(screen.getByRole('button', {name: t('publish')}).disabled).toBe(true);
	});

	it('publishes and shows the public address', async () => {
		const user = userEvent.setup();

		const published = campaign({status: 'PUBLISHED'});

		const api = {
			getPublicationIssues: vi.fn(() => Promise.resolve([])),
			publishCampaign: vi.fn(() => Promise.resolve(published)),
		};

		const onCampaignChange = vi.fn();

		const {context} = renderWithApp(
			<PublishStep
				campaign={campaign()}
				onBack={vi.fn()}
				onCampaignChange={onCampaignChange}
				onReloadCampaign={vi.fn()}
			/>,
			{api}
		);

		expect(await screen.findByText(t('ready-to-publish'))).toBeTruthy();
		expect(screen.getByLabelText(t('public-address')).value).toBe(
			'http://localhost:8080/web/acme/campaign/-/campaign/encontro-de-rh'
		);

		await user.click(screen.getByRole('button', {name: t('publish')}));

		await waitFor(() => expect(onCampaignChange).toHaveBeenCalledWith(published));

		expect(context.notify).toHaveBeenCalledWith(t('campaign-published'));
	});

	it('warns when the site has no page with the renderer widget', async () => {
		renderWithApp(
			<PublishStep
				campaign={campaign()}
				onBack={vi.fn()}
				onCampaignChange={vi.fn()}
				onReloadCampaign={vi.fn()}
			/>,
			{api: {getPublicationIssues: vi.fn(() => Promise.resolve([]))}, config: {publicURL: null}}
		);

		expect(await screen.findByText(/ainda não tem uma página com o widget/)).toBeTruthy();
	});
});
