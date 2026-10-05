export const SUPPORTED_RULES = {
	checkbox: ['minSelected', 'maxSelected'],
	date: ['min', 'max'],
	hidden: ['maxLength'],
	multiSelect: ['minSelected', 'maxSelected'],
	number: ['min', 'max', 'integer'],
	text: ['minLength', 'maxLength', 'pattern'],
	textarea: ['minLength', 'maxLength'],
};

export const TEXT_PATTERNS = ['letters', 'alphanumeric', 'digits', 'cep'];

export const FIELD_TYPE_ORDER = [
	'text',
	'textarea',
	'email',
	'tel',
	'number',
	'date',
	'datetime',
	'select',
	'radio',
	'checkbox',
	'multiSelect',
	'boolean',
	'hidden',
];

export const PLACEHOLDER_TYPES = ['email', 'number', 'tel', 'text', 'textarea'];

export const WIDTHS = ['FULL', 'HALF', 'THIRD'];

const NUMERIC_RULES = ['maxLength', 'maxSelected', 'minLength', 'minSelected'];

export function toValidation(type, rules) {
	const validation = {};

	(SUPPORTED_RULES[type] || []).forEach((rule) => {
		const value = rules[rule];

		if (rule === 'integer') {
			if (value) {
				validation.integer = true;
			}

			return;
		}

		if (value === undefined || value === null || value === '') {
			return;
		}

		if (NUMERIC_RULES.includes(rule) || type === 'number') {
			validation[rule] = Number(value);
		}
		else {
			validation[rule] = value;
		}
	});

	return validation;
}

const FIELD_KEYS = [
	'conditionalRule',
	'defaultValue',
	'fieldKey',
	'fieldOptions',
	'helpText',
	'label',
	'personalData',
	'placeholder',
	'presetKey',
	'properties',
	'required',
	'type',
	'validation',
	'width',
];

export function toFieldPayload(field, changes = {}) {
	const merged = {...field, ...changes};

	const payload = {};

	FIELD_KEYS.forEach((key) => {
		if (merged[key] !== undefined && merged[key] !== null) {
			payload[key] = merged[key];
		}
	});

	return payload;
}

const FORM_VERSION_KEYS = [
	'captchaEnabled',
	'consentRequired',
	'consentText',
	'consentURL',
	'consentVersion',
	'redirectUrl',
	'requireAuthentication',
	'settings',
	'submitLabel',
	'successMessage',
	'successTitle',
];

export function toFormVersionPayload(formVersion, changes = {}) {
	const merged = {...formVersion, ...changes};

	const payload = {};

	FORM_VERSION_KEYS.forEach((key) => {
		if (merged[key] !== undefined && merged[key] !== null) {
			payload[key] = merged[key];
		}
	});

	return payload;
}

export function isValidConsentURL(url) {
	if (!url) {
		return false;
	}

	if (url.startsWith('//') || url.startsWith('/\\')) {
		return false;
	}

	if (url.startsWith('/')) {
		return !/\s/.test(url);
	}

	return /^https?:\/\/[^\s/?#:]+(:\d+)?([/?#]\S*)?$/i.test(url);
}

export function findField(formVersion, fieldKey) {
	return (formVersion.formFields || []).find((field) => field.fieldKey === fieldKey) || null;
}

export function moveItem(list, index, offset) {
	const target = index + offset;

	if (target < 0 || target >= list.length) {
		return list;
	}

	const copy = list.slice();

	const [item] = copy.splice(index, 1);

	copy.splice(target, 0, item);

	return copy;
}
