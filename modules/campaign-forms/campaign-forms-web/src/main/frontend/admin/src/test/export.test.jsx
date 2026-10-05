import {screen, waitFor, within} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {afterEach, beforeEach, describe, expect, it, vi} from 'vitest';

import {ApiError, createClient, getFileName} from '../api/client.js';
import {toExpirationDate} from '../views/DataExport.jsx';
import Results from '../views/Results.jsx';
import NotificationStep from '../views/steps/NotificationStep.jsx';
import {renderWithApp, t} from './renderWithApp.jsx';

const FIELDS = [
	{fieldKey: 'nome', label: 'Nome', type: 'text'},
	{
		fieldKey: 'interesses',
		fieldOptions: [{label: 'Inovação', value: 'inovacao'}],
		label: 'Interesses',
		type: 'checkbox',
	},
];

const SUBMISSION = {
	dateCreated: '2026-10-02T13:00:00Z',
	id: 501,
	values: {interesses: ['inovacao'], nome: 'Maria'},
};

function resultsApi(permissions, overrides = {}) {
	return {
		getCampaign: vi.fn(() =>
			Promise.resolve({
				id: 7,
				name: 'Feira 2026',
				permissions,
				seriesKey: 'feira',
			})
		),
		getForm: vi.fn(() => Promise.resolve({formFields: FIELDS})),
		getSubmissions: vi.fn(() =>
			Promise.resolve({items: [SUBMISSION], totalCount: 1})
		),
		...overrides,
	};
}

describe('client.download', () => {
	it('reads the file name from Content-Disposition', () => {
		expect(getFileName('attachment; filename="submissions-7.csv"')).toBe('submissions-7.csv');
		expect(getFileName("attachment; filename*=UTF-8''inscri%C3%A7%C3%B5es.csv")).toBe(
			'inscrições.csv'
		);
		expect(getFileName(null)).toBe(null);
	});

	it('accepts any type and turns errors into ApiError', async () => {
		const fetchImpl = vi.fn(() =>
			Promise.resolve({
				ok: false,
				status: 403,
				text: () => Promise.resolve('{"code":"FORBIDDEN","message":"Sem permissão"}'),
			})
		);

		const client = createClient({
			apiURL: '/o/api',
			fetchImpl,
			getAuthToken: () => 'token123',
			locale: 'pt-BR',
		});

		const error = await client
			.download('/campaigns/7/submissions/export', {format: 'csv', labels: true})
			.catch((downloadError) => downloadError);

		expect(error).toBeInstanceOf(ApiError);
		expect(error.serverMessage).toBe('Sem permissão');

		const [url, options] = fetchImpl.mock.calls[0];

		expect(url).toBe('/o/api/campaigns/7/submissions/export?format=csv&labels=true');
		expect(options.headers).toMatchObject({Accept: '*/*', 'x-csrf-token': 'token123'});
	});
});

describe('toExpirationDate', () => {
	it('uses the end of the chosen day', () => {
		expect(toExpirationDate('')).toBe(null);
		expect(new Date(toExpirationDate('2026-12-31')).getDate()).toBe(31);
		expect(new Date(toExpirationDate('2026-12-31')).getHours()).toBe(23);
	});
});

describe('Results', () => {
	beforeEach(() => {
		URL.createObjectURL = vi.fn(() => 'blob:x');
		URL.revokeObjectURL = vi.fn();

		vi.spyOn(HTMLAnchorElement.prototype, 'click').mockImplementation(() => {});
	});

	afterEach(() => {
		vi.restoreAllMocks();
	});

	it('hides export and Power BI without EXPORT_SUBMISSIONS', async () => {
		renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({VIEW_SUBMISSIONS: true}),
		});

		await screen.findByText('Maria');

		expect(screen.queryByRole('button', {name: t('export-csv')})).toBeNull();
		expect(screen.queryByRole('button', {name: t('power-bi')})).toBeNull();
	});

	it('downloads the CSV with the chosen options', async () => {
		const user = userEvent.setup();

		const exportSubmissions = vi.fn(() =>
			Promise.resolve({blob: new Blob(['a;b']), fileName: 'submissions-7.csv'})
		);

		const {context} = renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({EXPORT_SUBMISSIONS: true}, {exportSubmissions}),
		});

		await user.click(await screen.findByRole('button', {name: t('export-csv')}));

		const dialog = await screen.findByRole('dialog');

		await user.selectOptions(within(dialog).getByLabelText(t('separator')), 'comma');
		await user.click(within(dialog).getByLabelText(t('export-labels')));
		await user.click(within(dialog).getByRole('button', {name: t('download-csv')}));

		await waitFor(() => expect(context.notify).toHaveBeenCalledWith(t('export-ready')));

		expect(exportSubmissions).toHaveBeenCalledWith(7, {
			includeMetadata: true,
			labels: false,
			separator: 'comma',
		});
		expect(URL.createObjectURL).toHaveBeenCalled();
		expect(HTMLAnchorElement.prototype.click).toHaveBeenCalled();
		expect(screen.queryByRole('dialog')).toBeNull();
	});

	it('downloads the clean Excel spreadsheet in one click', async () => {
		const user = userEvent.setup();

		const exportSubmissions = vi.fn(() =>
			Promise.resolve({blob: new Blob(['xlsx']), fileName: 'submissions-feira-20261002-1500.xlsx'})
		);

		const {context} = renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({EXPORT_SUBMISSIONS: true}, {exportSubmissions}),
		});

		await user.click(await screen.findByRole('button', {name: t('download-excel')}));

		await waitFor(() => expect(context.notify).toHaveBeenCalledWith(t('excel-ready')));

		expect(exportSubmissions).toHaveBeenCalledWith(7, {format: 'xlsx'});
		expect(HTMLAnchorElement.prototype.click).toHaveBeenCalled();
		expect(screen.queryByRole('dialog')).toBeNull();
	});

	it('shows whether the notifications of a response were sent', async () => {
		const user = userEvent.setup();

		const getSubmissionDeliveries = vi.fn(() =>
			Promise.resolve([
				{attempts: 1, id: 1, status: 'SUCCESS', targetName: 'Aviso à equipe'},
				{
					attempts: 5,
					id: 2,
					lastErrorMessage: 'O endereço é inválido.',
					status: 'DEAD',
					targetName: 'Confirmação',
				},
			])
		);

		renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({VIEW_SUBMISSIONS: true}, {getSubmissionDeliveries}),
		});

		await user.click(await screen.findByRole('button', {name: 'formatted:2026-10-02T13:00:00Z'}));

		const dialog = await screen.findByRole('dialog');

		await within(dialog).findByText('Aviso à equipe');

		expect(getSubmissionDeliveries).toHaveBeenCalledWith(501);
		expect(within(dialog).getByText(t('delivery-success'))).toBeTruthy();
		expect(within(dialog).getByText(t('delivery-dead'))).toBeTruthy();
		expect(within(dialog).getByText('O endereço é inválido.')).toBeTruthy();
		expect(within(dialog).getByText(t('attempts-x', 5), {exact: false})).toBeTruthy();
	});
});

describe('Power BI', () => {
	const FEED = {
		active: true,
		format: 'CSV',
		id: 31,
		name: 'Painel da diretoria',
		scope: 'SERIES',
		seriesKey: 'feira',
		tokenHint: 'wEjw',
	};

	it('creates a URL and shows it only once', async () => {
		const user = userEvent.setup();

		const addDataFeed = vi.fn(() =>
			Promise.resolve({...FEED, url: 'http://x/o/campaign-forms/v1.0/data-feeds/k/submissions?format=csv&token=SECRET'})
		);

		const getDataFeeds = vi
			.fn()
			.mockResolvedValueOnce([])
			.mockResolvedValue([{...FEED, url: 'http://x/o/campaign-forms/v1.0/data-feeds/k/submissions?format=csv'}]);

		renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({EXPORT_SUBMISSIONS: true}, {addDataFeed, getDataFeeds}),
		});

		await user.click(await screen.findByRole('button', {name: t('power-bi')}));

		const dialog = await screen.findByRole('dialog');

		await within(dialog).findByText(t('no-data-feeds'));

		await user.click(within(dialog).getByRole('button', {name: t('new-data-feed')}));

		await user.click(within(dialog).getByRole('button', {name: t('create-url')}));

		expect(within(dialog).getByText(t('field-required'))).toBeTruthy();
		expect(addDataFeed).not.toHaveBeenCalled();

		await user.type(within(dialog).getByLabelText(t('data-feed-name'), {exact: false}), 'Painel da diretoria');
		await user.click(within(dialog).getByRole('button', {name: t('create-url')}));

		const url = await within(dialog).findByLabelText(t('data-feed-url-x', FEED.name));

		expect(url.value).toContain('token=SECRET');
		expect(addDataFeed).toHaveBeenCalledWith(7, {
			expirationDate: null,
			format: 'CSV',
			includeMetadata: true,
			name: 'Painel da diretoria',
			scope: 'SERIES',
		});

		await user.click(within(dialog).getByRole('button', {name: t('done')}));

		await within(dialog).findByText(FEED.name);

		expect(within(dialog).queryByDisplayValue(/SECRET/)).toBeNull();
		expect(within(dialog).getByText(t('token-ending-x', 'wEjw'), {exact: false})).toBeTruthy();
	});

	it('revokes after confirmation', async () => {
		const user = userEvent.setup();

		const deleteDataFeed = vi.fn(() => Promise.resolve(null));

		const getDataFeeds = vi
			.fn()
			.mockResolvedValueOnce([FEED])
			.mockResolvedValue([{...FEED, active: false}]);

		const {context} = renderWithApp(<Results campaignId={7} />, {
			api: resultsApi({EXPORT_SUBMISSIONS: true}, {deleteDataFeed, getDataFeeds}),
		});

		await user.click(await screen.findByRole('button', {name: t('power-bi')}));

		const [dialog] = await screen.findAllByRole('dialog');

		await user.click(await within(dialog).findByRole('button', {name: t('revoke')}));

		const confirm = screen.getAllByRole('dialog').pop();

		expect(within(confirm).getByText(t('revoke-data-feed-confirmation', FEED.name))).toBeTruthy();

		await user.click(within(confirm).getByRole('button', {name: t('revoke')}));

		await waitFor(() => expect(context.notify).toHaveBeenCalledWith(t('data-feed-revoked')));

		expect(deleteDataFeed).toHaveBeenCalledWith(31);

		await within(dialog).findByText(t('no-data-feeds'));
	});
});

describe('NotificationStep', () => {
	it('sends a test email to the current user', async () => {
		const user = userEvent.setup();

		const sendTestNotification = vi.fn(() =>
			Promise.resolve({recipient: 'ana@example.com'})
		);

		const {context} = renderWithApp(
			<NotificationStep
				campaign={{id: 7, permissions: {MANAGE_NOTIFICATIONS: true}}}
				onBack={() => {}}
				onNext={() => {}}
			/>,
			{
				api: {
					getForm: vi.fn(() => Promise.resolve({formFields: FIELDS})),
					getNotifications: vi.fn(() =>
						Promise.resolve([
							{
								enabled: true,
								eventType: 'SUBMISSION_CREATED',
								id: 12,
								name: 'Aviso à equipe',
								recipients: 'rh@example.com',
							},
						])
					),
					sendTestNotification,
				},
			}
		);

		await user.click(
			await screen.findByRole('button', {name: t('actions-for-x', 'Aviso à equipe')})
		);
		await user.click(screen.getByRole('menuitem', {name: t('send-test-email')}));

		await waitFor(() =>
			expect(context.notify).toHaveBeenCalledWith(
				t('test-email-sent-x', 'ana@example.com')
			)
		);

		expect(sendTestNotification).toHaveBeenCalledWith(12);
	});
});
