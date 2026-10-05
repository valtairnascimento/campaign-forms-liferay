(function (root, factory) {
	var api = factory();

	if (typeof module === 'object' && module.exports) {
		module.exports = api;

		return;
	}

	root.CampaignFormsPublic = api;

	var start = function () {
		api.initAll(document);
	};

	if (document.readyState === 'loading') {
		document.addEventListener('DOMContentLoaded', start);
	}
	else {
		start();
	}

	if (root.Liferay && typeof root.Liferay.on === 'function') {
		root.Liferay.on('endNavigate', start);
	}
})(typeof window !== 'undefined' ? window : this, function () {
	'use strict';

	var DEFAULT_MAX_LENGTH = {hidden: 500, text: 255, textarea: 4000};

	var PATTERNS = {
		alphanumeric: /^[0-9A-Za-zÀ-ÖØ-öø-ſ ]+$/,
		cep: /^\d{5}-?\d{3}$/,
		digits: /^\d+$/,
		letters: /^[A-Za-zÀ-ÖØ-öø-ſ '-]+$/,
	};

	var UTM_KEYS = ['utmSource', 'utmMedium', 'utmCampaign', 'utmContent', 'utmTerm'];

	var UTM_STORAGE_KEY = 'campaign-forms-utm';

	function error(code, messageCode, args) {
		return {args: args || [], code: code, messageCode: messageCode || code};
	}

	function isValidDate(value) {
		if (!/^\d{4}-\d{2}-\d{2}$/.test(value)) {
			return false;
		}

		var date = new Date(value + 'T00:00:00Z');

		return !isNaN(date.getTime()) && date.toISOString().slice(0, 10) === value;
	}

	function validateText(field, value) {
		var validation = field.validation || {};

		if (validation.minLength && value.length < validation.minLength) {
			return error('TOO_SHORT', null, [validation.minLength]);
		}

		var maxLength = validation.maxLength || DEFAULT_MAX_LENGTH[field.type];

		if (maxLength && value.length > maxLength) {
			return error('TOO_LONG', null, [maxLength]);
		}

		var pattern = PATTERNS[validation.pattern];

		if (pattern && !pattern.test(value)) {
			return error('PATTERN_MISMATCH');
		}

		return null;
	}

	function validateNumber(field, value) {
		if (!/^[-+]?\d{1,18}([.,]\d{1,10})?$/.test(value)) {
			return error('INVALID_NUMBER');
		}

		var validation = field.validation || {};
		var number = Number(value.replace(',', '.'));

		if (validation.integer && !Number.isInteger(number)) {
			return error('INVALID_NUMBER', 'INVALID_INTEGER');
		}

		if (validation.min !== undefined && number < Number(validation.min)) {
			return error('TOO_SMALL', null, [validation.min]);
		}

		if (validation.max !== undefined && number > Number(validation.max)) {
			return error('TOO_LARGE', null, [validation.max]);
		}

		return null;
	}

	function validateDate(field, value) {
		if (!isValidDate(value)) {
			return error('INVALID_DATE');
		}

		var validation = field.validation || {};

		if (validation.min && value < validation.min) {
			return error('TOO_SMALL', 'DATE_TOO_EARLY', [validation.min]);
		}

		if (validation.max && value > validation.max) {
			return error('TOO_LARGE', 'DATE_TOO_LATE', [validation.max]);
		}

		return null;
	}

	function validateOptions(field, values) {
		var options = field.options || [];

		for (var i = 0; i < values.length; i++) {
			if (options.indexOf(values[i]) < 0) {
				return error('INVALID_OPTION');
			}
		}

		if (!field.multiValued) {
			return null;
		}

		var validation = field.validation || {};

		if (validation.minSelected && values.length < validation.minSelected) {
			return error('TOO_FEW_SELECTED', null, [validation.minSelected]);
		}

		if (validation.maxSelected && values.length > validation.maxSelected) {
			return error('TOO_MANY_SELECTED', null, [validation.maxSelected]);
		}

		return null;
	}

	function validateValues(field, values) {
		if (!values.length) {
			return field.required ? error('REQUIRED') : null;
		}

		if (!field.multiValued && values.length > 1) {
			return error('INVALID_VALUE');
		}

		var value = values[0];

		switch (field.type) {
			case 'boolean':
				if (value !== 'true' && value !== 'false') {
					return error('INVALID_VALUE');
				}

				return field.required && value !== 'true' ? error('REQUIRED') : null;

			case 'checkbox':
			case 'multiSelect':
			case 'radio':
			case 'select':
				return validateOptions(field, values);

			case 'date':
				return validateDate(field, value);

			case 'email':
				return value.length > 254 ||
					!/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(value)
					? error('INVALID_EMAIL')
					: null;

			case 'number':
				return validateNumber(field, value);

			case 'tel':
				var digits = value.replace(/\D/g, '');

				return !/^\+?[0-9 ().-]{8,25}$/.test(value) ||
					digits.length < 8 ||
					digits.length > 15
					? error('INVALID_PHONE')
					: null;

			default:
				return validateText(field, value);
		}
	}

	function formatMessage(messages, validationError) {
		var template =
			(messages.errors || {})[validationError.messageCode] ||
			(messages.errors || {})[validationError.code] ||
			messages.genericError;

		return template.replace(/\{(\d+)\}/g, function (match, index) {
			var arg = validationError.args[Number(index)];

			return arg === undefined ? match : String(arg);
		});
	}

	function readUtm(search, storage) {
		var stored = null;

		try {
			stored = JSON.parse(storage.getItem(UTM_STORAGE_KEY) || 'null');
		}
		catch (exception) {
			stored = null;
		}

		if (stored) {
			return stored;
		}

		var params = new URLSearchParams(search || '');
		var utm = {};
		var found = false;

		UTM_KEYS.forEach(function (key) {
			var param = 'utm_' + key.slice(3).toLowerCase();
			var value = params.get(param);

			if (value) {
				utm[key] = value.slice(0, 255);
				found = true;
			}
		});

		if (found) {
			try {
				storage.setItem(UTM_STORAGE_KEY, JSON.stringify(utm));
			}
			catch (exception) {
			}
		}

		return utm;
	}

	function uuid() {
		var crypto = typeof window !== 'undefined' && window.crypto;

		if (crypto && typeof crypto.randomUUID === 'function') {
			return crypto.randomUUID();
		}

		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
			var r = (Math.random() * 16) | 0;

			return (c === 'x' ? r : (r & 0x3) | 0x8).toString(16);
		});
	}

	function getElements(form, name) {
		return Array.prototype.filter.call(form.elements, function (element) {
			return element.name === name;
		});
	}

	function collectValues(form, field) {
		var elements = getElements(form, field.fieldKey);
		var values = [];

		elements.forEach(function (element) {
			if (element.tagName === 'SELECT') {
				Array.prototype.forEach.call(element.options, function (option) {
					if (option.selected && option.value) {
						values.push(option.value);
					}
				});
			}
			else if (element.type === 'checkbox' || element.type === 'radio') {
				if (element.checked) {
					values.push(element.value);
				}
			}
			else if (element.value && element.value.trim()) {
				values.push(element.value.trim());
			}
		});

		return values;
	}

	function emit(config, name, extra) {
		if (config.preview) {
			return;
		}

		var detail = {
			action: name,
			campaignId: config.campaignId,
			campaignSlug: config.campaignSlug,
			formId: config.formId,
			formVersion: config.formVersion,
		};

		Object.keys(extra || {}).forEach(function (key) {
			detail[key] = extra[key];
		});

		window.dataLayer = window.dataLayer || [];
		window.dataLayer.push(Object.assign({event: 'campaign_forms'}, detail));

		try {
			window.dispatchEvent(new CustomEvent('campaign-forms', {detail: detail}));
		}
		catch (exception) {
		}
	}

	function setFieldError(form, fieldKey, message) {
		var container = form.querySelector('[data-cf-field="' + fieldKey + '"]');
		var errorElement = form.querySelector('[data-cf-error-for="' + fieldKey + '"]');

		if (!container || !errorElement) {
			return null;
		}

		var controls = container.querySelectorAll('input, select, textarea');

		Array.prototype.forEach.call(controls, function (control) {
			if (message) {
				control.setAttribute('aria-invalid', 'true');
			}
			else {
				control.removeAttribute('aria-invalid');
			}
		});

		container.classList.toggle('campaign-forms-form__field--invalid', !!message);
		container.classList.toggle('has-error', !!message);

		errorElement.textContent = message || '';
		errorElement.hidden = !message;

		return controls[0] || null;
	}

	function showSummary(form, title, items) {
		var summary = form.querySelector('.campaign-forms-form__summary');

		summary.textContent = '';

		var paragraph = document.createElement('p');

		paragraph.textContent = title;
		summary.appendChild(paragraph);

		if (items && items.length) {
			var list = document.createElement('ul');

			items.forEach(function (item) {
				var listItem = document.createElement('li');

				if (item.target) {
					var link = document.createElement('a');

					link.href = '#' + item.target;
					link.textContent = item.text;
					listItem.appendChild(link);
				}
				else {
					listItem.textContent = item.text;
				}

				list.appendChild(listItem);
			});

			summary.appendChild(list);
		}

		summary.hidden = false;
	}

	function hideSummary(form) {
		var summary = form.querySelector('.campaign-forms-form__summary');

		summary.hidden = true;
		summary.textContent = '';
	}

	function getLabel(form, fieldKey) {
		var container = form.querySelector('[data-cf-field="' + fieldKey + '"]');
		var label = container && container.querySelector('label, legend');

		return label ? label.textContent.replace(/\*/g, '').replace(/\s+/g, ' ').trim() : fieldKey;
	}

	function applyErrors(form, config, errors) {
		var firstControl = null;
		var items = [];

		errors.forEach(function (fieldError) {
			var control = setFieldError(form, fieldError.field, fieldError.message);

			if (control && !firstControl) {
				firstControl = control;
			}

			items.push({
				target: control ? control.id : null,
				text: getLabel(form, fieldError.field) + ': ' + fieldError.message,
			});
		});

		showSummary(form, config.messages.summary, items);

		emit(config, 'form_error', {
			errors: errors.map(function (fieldError) {
				return fieldError.code;
			}),
		});

		(firstControl || form.querySelector('.campaign-forms-form__summary')).focus();
	}

	function clearErrors(form, config) {
		config.fields.forEach(function (field) {
			setFieldError(form, field.fieldKey, null);
		});

		setFieldError(form, 'consent', null);
		hideSummary(form);
	}

	function validateForm(form, config) {
		var errors = [];

		config.fields.forEach(function (field) {
			var validationError = validateValues(field, collectValues(form, field));

			if (validationError) {
				errors.push({
					code: validationError.code,
					field: field.fieldKey,
					message: formatMessage(config.messages, validationError),
				});
			}
		});

		var consent = getElements(form, 'consent')[0];

		if (consent && !consent.checked) {
			errors.push({
				code: 'CONSENT_REQUIRED',
				field: 'consent',
				message: formatMessage(config.messages, error('CONSENT_REQUIRED')),
			});
		}

		return errors;
	}

	function buildBody(form, config) {
		var values = {};

		config.fields.forEach(function (field) {
			var fieldValues = collectValues(form, field);

			if (!fieldValues.length) {
				return;
			}

			values[field.fieldKey] = field.multiValued ? fieldValues : fieldValues[0];
		});

		var honeypot = form.querySelector('[data-cf-honeypot]');

		var source = {
			referrer: document.referrer || undefined,
			sourceUrl: window.location.href,
		};

		var utm = readUtm(window.location.search, window.sessionStorage);

		UTM_KEYS.forEach(function (key) {
			if (utm[key]) {
				source[key] = utm[key];
			}
		});

		var body = {
			formToken: config.formToken,
			honeypot: honeypot ? honeypot.value : '',
			source: source,
			values: values,
		};

		if (getElements(form, 'consent').length) {
			body.consent = {
				accepted: getElements(form, 'consent')[0].checked,
				version: config.consentVersion,
			};
		}

		return body;
	}

	function showSuccess(form, config, response) {
		var status = form.parentNode.querySelector('.campaign-forms-form__status');

		status.querySelector('.campaign-forms-form__status-title').textContent =
			response.title || config.successTitle || config.messages.success;
		status.querySelector('.campaign-forms-form__status-message').textContent =
			response.message || config.successMessage || '';

		form.hidden = true;
		status.hidden = false;
		status.focus();

		emit(config, 'form_submit_success');

		var redirectUrl = response.redirectUrl || config.redirectUrl;

		if (redirectUrl) {
			window.setTimeout(function () {
				window.location.assign(redirectUrl);
			}, 1500);
		}
	}

	function setBusy(form, config, busy) {
		var button = form.querySelector('.campaign-forms-form__submit');

		if (!button.dataset.label) {
			button.dataset.label = button.textContent;
		}

		button.disabled = busy;
		button.textContent = busy ? config.messages.sending : button.dataset.label;
		form.setAttribute('aria-busy', busy ? 'true' : 'false');
	}

	function handleFailure(form, config, httpStatus, json) {
		var code = (json && json.code) || 'NETWORK_ERROR';

		if (httpStatus === 422 && json && json.errors && json.errors.length) {
			applyErrors(form, config, json.errors);

			return;
		}

		var message = config.messages.genericError;

		if (code === 'INVALID_FORM_TOKEN') {
			message = config.messages.invalidFormToken;
		}
		else if (httpStatus === 409 && json && json.message) {
			message = json.message;
		}

		showSummary(form, message, []);
		form.querySelector('.campaign-forms-form__summary').focus();

		emit(config, 'form_submit_error', {errorCode: code});
	}

	function submit(form, config, idempotencyKey) {
		clearErrors(form, config);

		var errors = validateForm(form, config);

		if (errors.length) {
			applyErrors(form, config, errors);

			return;
		}

		if (config.preview) {
			showSummary(form, config.messages.previewSubmit, []);
			form.querySelector('.campaign-forms-form__summary').focus();

			return;
		}

		setBusy(form, config, true);

		var headers = {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Idempotency-Key': idempotencyKey,
		};

		if (window.Liferay && window.Liferay.authToken) {
			headers['x-csrf-token'] = window.Liferay.authToken;
		}

		fetch(config.submissionUrl, {
			body: JSON.stringify(buildBody(form, config)),
			credentials: 'same-origin',
			headers: headers,
			method: 'POST',
		})
			.then(function (response) {
				return response.json().then(
					function (json) {
						return {json: json, status: response.status};
					},
					function () {
						return {json: null, status: response.status};
					}
				);
			})
			.then(function (result) {
				setBusy(form, config, false);

				if (result.status >= 200 && result.status < 300) {
					showSuccess(form, config, result.json || {});
				}
				else {
					handleFailure(form, config, result.status, result.json);
				}
			})
			.catch(function () {
				setBusy(form, config, false);
				handleFailure(form, config, 0, null);
			});
	}

	function init(form) {
		if (form.dataset.cfInitialized) {
			return;
		}

		form.dataset.cfInitialized = 'true';

		var config = JSON.parse(form.dataset.campaignFormsConfig);
		var idempotencyKey = uuid();
		var started = false;

		readUtm(window.location.search, window.sessionStorage);

		form.addEventListener('submit', function (event) {
			event.preventDefault();

			submit(form, config, idempotencyKey);
		});

		var onStart = function () {
			if (!started) {
				started = true;

				emit(config, 'form_start');
			}
		};

		form.addEventListener('input', onStart);
		form.addEventListener('change', onStart);

		emit(config, 'form_view');
	}

	function initAll(scope) {
		Array.prototype.forEach.call(
			scope.querySelectorAll('form.campaign-forms-form[data-campaign-forms-config]'),
			init
		);
	}

	return {
		formatMessage: formatMessage,
		init: init,
		initAll: initAll,
		readUtm: readUtm,
		validateValues: validateValues,
	};
});
