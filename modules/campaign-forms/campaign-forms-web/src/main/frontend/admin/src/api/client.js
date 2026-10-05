export class ApiError extends Error {
	constructor({code, errorId = null, fieldErrors = {}, message, status}) {
		super(message || code);

		this.code = code;
		this.errorId = errorId;
		this.fieldErrors = fieldErrors;
		this.serverMessage = message || null;
		this.status = status;
	}
}

export function toApiError(status, data) {
	const fieldErrors = {};

	const errors = data && Array.isArray(data.errors) ? data.errors : [];

	errors.forEach((error) => {
		if (error && error.field && !fieldErrors[error.field]) {
			fieldErrors[error.field] = error.message || error.code;
		}
	});

	return new ApiError({
		code: (data && data.code) || `HTTP_${status}`,
		errorId: (data && data.errorId) || null,
		fieldErrors,
		message: (data && data.message) || null,
		status,
	});
}

function buildURL(baseURL, path, query) {
	let url = baseURL + path;

	if (query) {
		const searchParams = new URLSearchParams();

		Object.keys(query).forEach((key) => {
			const value = query[key];

			if (value !== null && value !== undefined && value !== '') {
				searchParams.append(key, String(value));
			}
		});

		const queryString = searchParams.toString();

		if (queryString) {
			url += `?${queryString}`;
		}
	}

	return url;
}

export function createClient({
	apiURL,
	fetchImpl = (...args) => window.fetch(...args),
	getAuthToken = () => window.Liferay && window.Liferay.authToken,
	locale,
}) {
	function getHeaders(accept) {
		const headers = {
			'Accept': accept,
			'Accept-Language': locale,
		};

		const authToken = getAuthToken();

		if (authToken) {
			headers['x-csrf-token'] = authToken;
		}

		return headers;
	}

	function parseJSON(text) {
		if (!text) {
			return null;
		}

		try {
			return JSON.parse(text);
		}
		catch (error) {
			return null;
		}
	}

	async function request(method, path, {body, formData, query} = {}) {
		const headers = getHeaders('application/json');

		if (body !== undefined && !formData) {
			headers['Content-Type'] = 'application/json';
		}

		let response;

		try {
			response = await fetchImpl(buildURL(apiURL, path, query), {
				body: formData || (body === undefined ? undefined : JSON.stringify(body)),
				credentials: 'include',
				headers,
				method,
			});
		}
		catch (error) {
			throw new ApiError({code: 'NETWORK_ERROR', status: 0});
		}

		if (response.status === 204) {
			return null;
		}

		const data = parseJSON(await response.text());

		if (!response.ok) {
			throw toApiError(response.status, data);
		}

		return data;
	}

	async function download(path, query) {
		let response;

		try {
			response = await fetchImpl(buildURL(apiURL, path, query), {
				credentials: 'include',
				headers: getHeaders('*/*'),
				method: 'GET',
			});
		}
		catch (error) {
			throw new ApiError({code: 'NETWORK_ERROR', status: 0});
		}

		if (!response.ok) {
			throw toApiError(response.status, parseJSON(await response.text()));
		}

		return {
			blob: await response.blob(),
			fileName: getFileName(response.headers.get('Content-Disposition')),
		};
	}

	return {
		delete: (path) => request('DELETE', path),
		download,
		get: (path, query) => request('GET', path, {query}),
		post: (path, body = {}) => request('POST', path, {body}),
		put: (path, body) => request('PUT', path, {body}),
		upload: (path, formData) => request('POST', path, {formData}),
	};
}

export function getFileName(contentDisposition) {
	if (!contentDisposition) {
		return null;
	}

	const encoded = /filename\*=UTF-8''([^;]+)/i.exec(contentDisposition);

	if (encoded) {
		try {
			return decodeURIComponent(encoded[1].trim());
		}
		catch (error) {
			return null;
		}
	}

	const plain = /filename="?([^";]+)"?/i.exec(contentDisposition);

	return plain ? plain[1].trim() : null;
}

export function getErrorMessage(t, error) {
	if (!error) {
		return null;
	}

	if (error.status === 0) {
		return t('error-network');
	}

	if (error.serverMessage) {
		return error.serverMessage;
	}

	if (error.status === 401 || error.status === 403) {
		return t('error-forbidden');
	}

	if (error.status === 404) {
		return t('error-not-found');
	}

	return t('error-generic');
}
