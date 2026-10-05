'use strict';

const assert = require('node:assert/strict');
const path = require('node:path');
const test = require('node:test');

const form = require(
	path.join(
		__dirname,
		'../../main/resources/META-INF/resources/js/campaign-form.js'
	)
);

function code(field, values) {
	const error = form.validateValues(field, values);

	return error ? error.code : null;
}

test('required fields', () => {
	assert.equal(code({required: true, type: 'text'}, []), 'REQUIRED');
	assert.equal(code({required: false, type: 'text'}, []), null);
	assert.equal(code({required: true, type: 'boolean'}, ['false']), 'REQUIRED');
	assert.equal(code({required: true, type: 'boolean'}, ['true']), null);
});

test('text length and pattern', () => {
	const field = {
		type: 'text',
		validation: {maxLength: 9, minLength: 8, pattern: 'cep'},
	};

	assert.equal(code(field, ['80000-000']), null);
	assert.equal(code(field, ['800']), 'TOO_SHORT');
	assert.equal(code(field, ['80000-0000']), 'TOO_LONG');
	assert.equal(code(field, ['8000a-000']), 'PATTERN_MISMATCH');
	assert.equal(code({type: 'text'}, ['a'.repeat(256)]), 'TOO_LONG');

	const letters = {type: 'text', validation: {pattern: 'letters'}};

	assert.equal(code(letters, ['José da Conceição']), null);
	assert.equal(code(letters, ['abc1']), 'PATTERN_MISMATCH');
	assert.equal(code({type: 'textarea'}, ['a'.repeat(4000)]), null);
});

test('email and phone', () => {
	assert.equal(code({type: 'email'}, ['maria@example.org']), null);
	assert.equal(code({type: 'email'}, ['maria@']), 'INVALID_EMAIL');
	assert.equal(code({type: 'tel'}, ['(41) 99999-0000']), null);
	assert.equal(code({type: 'tel'}, ['+55 41 99999-0000']), null);
	assert.equal(code({type: 'tel'}, ['1234']), 'INVALID_PHONE');
	assert.equal(code({type: 'tel'}, ['ligue já']), 'INVALID_PHONE');
});

test('numbers', () => {
	const field = {type: 'number', validation: {integer: true, max: 10, min: 1}};

	assert.equal(code(field, ['3']), null);
	assert.equal(code(field, ['3,0']), null);
	assert.equal(code(field, ['2.5']), 'INVALID_NUMBER');
	assert.equal(code(field, ['1.000,5']), 'INVALID_NUMBER');
	assert.equal(code(field, ['11']), 'TOO_LARGE');
	assert.equal(code(field, ['0']), 'TOO_SMALL');

	assert.equal(
		form.validateValues(field, ['2.5']).messageCode,
		'INVALID_INTEGER'
	);
});

test('dates', () => {
	const field = {
		type: 'date',
		validation: {max: '2030-12-31', min: '1990-01-01'},
	};

	assert.equal(code(field, ['2026-10-01']), null);
	assert.equal(code(field, ['2026-02-30']), 'INVALID_DATE');
	assert.equal(code(field, ['01/10/2026']), 'INVALID_DATE');
	assert.equal(code(field, ['1980-05-05']), 'TOO_SMALL');
	assert.equal(form.validateValues(field, ['2031-01-01']).messageCode, 'DATE_TOO_LATE');
});

test('options', () => {
	const select = {options: ['curitiba', 'londrina'], type: 'select'};

	assert.equal(code(select, ['londrina']), null);
	assert.equal(code(select, ['maringa']), 'INVALID_OPTION');
	assert.equal(code(select, ['curitiba', 'londrina']), 'INVALID_VALUE');

	const checkbox = {
		multiValued: true,
		options: ['a', 'b', 'c'],
		type: 'checkbox',
		validation: {maxSelected: 2, minSelected: 1},
	};

	assert.equal(code(checkbox, ['a', 'b']), null);
	assert.equal(code(checkbox, ['a', 'b', 'c']), 'TOO_MANY_SELECTED');
});

test('message formatting', () => {
	const messages = {
		errors: {TOO_LONG: 'Use no máximo {0} caracteres.'},
		genericError: 'Erro.',
	};

	assert.equal(
		form.formatMessage(messages, {args: [255], code: 'TOO_LONG', messageCode: 'TOO_LONG'}),
		'Use no máximo 255 caracteres.'
	);
	assert.equal(
		form.formatMessage(messages, {args: [], code: 'X', messageCode: 'X'}),
		'Erro.'
	);
});

test('utm first touch', () => {
	const storage = {
		data: {},
		getItem(key) {
			return this.data[key] || null;
		},
		setItem(key, value) {
			this.data[key] = value;
		},
	};

	assert.deepEqual(
		form.readUtm('?utm_source=newsletter&utm_campaign=encontro-rh', storage),
		{utmCampaign: 'encontro-rh', utmSource: 'newsletter'}
	);

	assert.deepEqual(form.readUtm('?utm_source=google', storage), {
		utmCampaign: 'encontro-rh',
		utmSource: 'newsletter',
	});
});
