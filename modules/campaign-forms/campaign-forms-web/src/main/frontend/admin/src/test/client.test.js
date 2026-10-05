import {describe, expect, it, vi} from 'vitest';

import {ApiError, createClient, getErrorMessage, toApiError} from '../api/client.js';

function response(status, body) {
	return Promise.resolve({
		ok: status >= 200 && status < 300,
		status,
		text: () => Promise.resolve(body === undefined ? '' : JSON.stringify(body)),
	});
}

describe('client', () => {
	it('sends the session CSRF token, the locale and JSON', async () => {
		const fetchImpl = vi.fn(() => response(200, {id: 1}));

		const client = createClient({
			apiURL: '/o/api',
			fetchImpl,
			getAuthToken: () => 'token123',
			locale: 'pt-BR',
		});

		await expect(client.post('/campaigns', {name: 'A'})).resolves.toEqual({id: 1});

		const [url, options] = fetchImpl.mock.calls[0];

		expect(url).toBe('/o/api/campaigns');
		expect(options.method).toBe('POST');
		expect(options.credentials).toBe('include');
		expect(options.body).toBe('{"name":"A"}');
		expect(options.headers).toMatchObject({
			'Accept-Language': 'pt-BR',
			'Content-Type': 'application/json',
			'x-csrf-token': 'token123',
		});
	});

	it('builds the query string without empty values', async () => {
		const fetchImpl = vi.fn(() => response(204));

		const client = createClient({
			apiURL: '',
			fetchImpl,
			getAuthToken: () => null,
			locale: 'en-US',
		});

		await expect(
			client.get('/x', {empty: '', page: 2, pageSize: 20, skip: null})
		).resolves.toBeNull();

		expect(fetchImpl.mock.calls[0][0]).toBe('/x?page=2&pageSize=20');
		expect(fetchImpl.mock.calls[0][1].headers['x-csrf-token']).toBeUndefined();
	});

	it('maps the API error format', async () => {
		const fetchImpl = vi.fn(() =>
			response(422, {
				code: 'FORM_VALIDATION_ERROR',
				errors: [
					{
						code: 'INVALID_EMAIL',
						field: 'email',
						message: 'Informe um e-mail válido.',
					},
					{code: 'X', field: 'email', message: 'second'},
				],
				message: 'Alguns campos precisam ser corrigidos.',
			})
		);

		const client = createClient({
			apiURL: '',
			fetchImpl,
			getAuthToken: () => null,
			locale: 'pt-BR',
		});

		const error = await client.put('/x', {}).catch((caught) => caught);

		expect(error).toBeInstanceOf(ApiError);
		expect(error.status).toBe(422);
		expect(error.code).toBe('FORM_VALIDATION_ERROR');
		expect(error.fieldErrors).toEqual({email: 'Informe um e-mail válido.'});
		expect(getErrorMessage((key) => key, error)).toBe(
			'Alguns campos precisam ser corrigidos.'
		);
	});

	it('reports network failures and bodies that are not JSON', async () => {
		const client = createClient({
			apiURL: '',
			fetchImpl: () => Promise.reject(new TypeError('Failed to fetch')),
			getAuthToken: () => null,
			locale: 'pt-BR',
		});

		const error = await client.get('/x').catch((caught) => caught);

		expect(error.code).toBe('NETWORK_ERROR');
		expect(getErrorMessage((key) => key, error)).toBe('error-network');

		const forbidden = toApiError(403, null);

		expect(forbidden.code).toBe('HTTP_403');
		expect(getErrorMessage((key) => key, forbidden)).toBe('error-forbidden');
	});
});
