import {describe, expect, it} from 'vitest';

import {parseRoute} from '../router.js';
import {formatValue} from '../views/Results.jsx';
import {isValidRedirectUrl} from '../views/steps/ConfirmationStep.jsx';
import {validateField} from '../views/steps/FieldDialog.jsx';
import {
	findField,
	moveItem,
	toFieldPayload,
	toFormVersionPayload,
	toValidation,
} from '../views/steps/fieldModel.js';
import {toCampaignPayload, validateInfo} from '../views/steps/InfoStep.jsx';
import {
	getPlaceholders,
	toNotificationPayload,
} from '../views/steps/NotificationStep.jsx';
import {getIssueStep} from '../views/steps/PublishStep.jsx';

const t = (key) => key;

describe('routes', () => {
	it.each([
		['', {name: 'list'}],
		['#/', {name: 'list'}],
		['#/new', {name: 'new'}],
		['#/new/quick', {mode: 'QUICK', name: 'create'}],
		['#/campaigns/42', {campaignId: 42, name: 'edit', step: 'info'}],
		['#/campaigns/42/fields', {campaignId: 42, name: 'edit', step: 'fields'}],
		['#/campaigns/42/unknown', {campaignId: 42, name: 'edit', step: 'info'}],
		['#/campaigns/42/results', {campaignId: 42, name: 'results'}],
		['#/campaigns/abc', {name: 'list'}],
	])('%s', (hash, route) => {
		expect(parseRoute(hash)).toEqual(route);
	});
});

describe('field model', () => {
	it('keeps only the rules each type supports, with the right types', () => {
		expect(
			toValidation('text', {maxLength: '9', min: '3', minLength: '', pattern: 'cep'})
		).toEqual({maxLength: 9, pattern: 'cep'});
		expect(toValidation('number', {integer: true, max: '10', min: '1.5'})).toEqual({
			integer: true,
			max: 10,
			min: 1.5,
		});
		expect(toValidation('date', {max: '2026-12-31', min: ''})).toEqual({
			max: '2026-12-31',
		});
		expect(toValidation('checkbox', {maxSelected: '2'})).toEqual({maxSelected: 2});
		expect(toValidation('email', {maxLength: '10'})).toEqual({});
		expect(toValidation('number', {integer: false})).toEqual({});
	});

	it('builds full replacement payloads without read-only data', () => {
		expect(
			toFieldPayload(
				{fieldKey: 'nome', id: 7, label: 'Nome', position: 0, required: false, type: 'text'},
				{required: true}
			)
		).toEqual({fieldKey: 'nome', label: 'Nome', required: true, type: 'text'});

		expect(
			toFormVersionPayload(
				{formFields: [{}], id: 3, status: 'DRAFT', submitLabel: 'Enviar', successTitle: null},
				{successTitle: 'Obrigado'}
			)
		).toEqual({submitLabel: 'Enviar', successTitle: 'Obrigado'});

		expect(
			toCampaignPayload(
				{
					id: 1,
					name: 'A',
					permissions: {},
					settings: {x: 1},
					slug: 'a',
					status: 'DRAFT',
					submissionsCount: 3,
				},
				{name: 'B'}
			)
		).toEqual({name: 'B', settings: {x: 1}, slug: 'a'});
	});

	it('finds fields by key and moves items', () => {
		const formVersion = {
			formFields: [
				{fieldKey: 'nome', id: 10},
				{fieldKey: 'email', id: 11},
			],
		};

		expect(findField(formVersion, 'email').id).toBe(11);
		expect(findField(formVersion, 'x')).toBeNull();
		expect(moveItem(['a', 'b', 'c'], 2, -1)).toEqual(['a', 'c', 'b']);
		expect(moveItem(['a', 'b'], 0, -1)).toEqual(['a', 'b']);
	});

	it('validates fields like the server', () => {
		const base = {
			defaultValue: '',
			fieldKey: 'cidade',
			label: 'Cidade',
			options: [],
			type: 'select',
		};

		expect(validateField(base, {existingKeys: [], optionBased: true, t})).toEqual({
			options: 'options-required',
		});
		expect(
			validateField(
				{
					...base,
					options: [
						{label: 'Curitiba', value: ''},
						{label: 'curitiba!', value: ''},
					],
				},
				{existingKeys: [], optionBased: true, t}
			)
		).toEqual({options: 'options-duplicated'});
		expect(
			validateField(
				{...base, fieldKey: 'Cidade', type: 'text'},
				{existingKeys: [], optionBased: false, t}
			)
		).toEqual({fieldKey: 'field-key-invalid'});
		expect(
			validateField(
				{...base, type: 'text'},
				{existingKeys: ['cidade'], optionBased: false, t}
			)
		).toEqual({fieldKey: 'field-key-in-use'});
		expect(
			validateField(
				{...base, type: 'hidden'},
				{existingKeys: [], optionBased: false, t}
			)
		).toEqual({defaultValue: 'field-required'});
	});
});

describe('campaign info', () => {
	it('validates name, slug, period and limit', () => {
		expect(
			validateInfo(
				{
					endDate: '2026-01-01T00:00',
					name: ' ',
					slug: 'Com Espaço',
					startDate: '2026-02-01T00:00',
					submissionLimit: '1.5',
				},
				t
			)
		).toEqual({
			endDate: 'end-date-before-start-date',
			name: 'field-required',
			slug: 'slug-invalid',
			submissionLimit: 'submission-limit-invalid',
		});
		expect(
			validateInfo(
				{endDate: '', name: 'A', slug: 'a-1', startDate: '', submissionLimit: ''},
				t
			)
		).toEqual({});
	});
});

describe('publication and results', () => {
	it('maps publication issues to the step that fixes them', () => {
		expect(getIssueStep({code: 'notification-placeholder-is-unknown'})).toBe(
			'notification'
		);
		expect(getIssueStep({code: 'campaign-end-date-is-in-the-past'})).toBe('info');
		expect(getIssueStep({code: 'form-has-no-fields'})).toBe('fields');
		expect(getIssueStep({code: 'field-type-is-not-available', target: 'cpf'})).toBe(
			'fields'
		);
	});

	it('accepts the same redirect URLs as the server', () => {
		expect(isValidRedirectUrl('')).toBe(true);
		expect(isValidRedirectUrl('/web/acme/obrigado')).toBe(true);
		expect(isValidRedirectUrl('https://www.example.com/')).toBe(true);
		expect(isValidRedirectUrl('//evil.example')).toBe(false);
		expect(isValidRedirectUrl('/\\evil.example')).toBe(false);
		expect(isValidRedirectUrl('javascript:alert(1)')).toBe(false);
	});

	it('formats answers with option labels', () => {
		const field = {fieldOptions: [{label: 'Gestão', value: 'gestao'}]};

		expect(formatValue(['gestao', 'outra'], field, t)).toBe('Gestão, outra');
		expect(formatValue(true, null, t)).toBe('yes');
		expect(formatValue(null, null, t)).toBe('');
		expect(formatValue(42, null, t)).toBe('42');
	});

	it('offers the placeholders the server accepts', () => {
		const placeholders = getPlaceholders([
			{fieldKey: 'nome', type: 'text'},
			{fieldKey: 'origem', type: 'hidden'},
		]);

		expect(placeholders).toContain('${submission.nome}');
		expect(placeholders).toContain('${submission.table}');
		expect(placeholders).not.toContain('${submission.origem}');
	});

	it('notification payload always has the channel', () => {
		expect(toNotificationPayload({id: 5, name: 'Aviso'}, {enabled: true})).toEqual({
			channel: 'EMAIL',
			enabled: true,
			name: 'Aviso',
		});
	});
});
