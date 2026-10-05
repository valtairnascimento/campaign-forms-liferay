import {describe, expect, it} from 'vitest';

import {fromDateTimeInputValue, toDateTimeInputValue} from '../util/dates.js';
import {SLUG_PATTERN, slugify, toFieldKey, toOptionValue} from '../util/text.js';

describe('text', () => {
	it('slugify follows the server slug format', () => {
		expect(slugify('Encontro de RH — Londrina 2026')).toBe('encontro-de-rh-londrina-2026');
		expect(slugify('  Ação & Inovação!  ')).toBe('acao-inovacao');
		expect(slugify('')).toBe('');
		expect(slugify('a'.repeat(120))).toHaveLength(100);
		expect(SLUG_PATTERN.test(slugify('São José dos Pinhais'))).toBe(true);
	});

	it('toFieldKey builds camelCase identifiers', () => {
		expect(toFieldKey('Nome completo')).toBe('nomeCompleto');
		expect(toFieldKey('E-mail corporativo')).toBe('eMailCorporativo');
		expect(toFieldKey('2º telefone')).toBe('campo2Telefone');
		expect(toFieldKey('Município de atuação')).toBe('municipioDeAtuacao');
		expect(toFieldKey('')).toBe('');
	});

	it('toOptionValue is stable and ASCII', () => {
		expect(toOptionValue('São José dos Pinhais')).toBe('sao-jose-dos-pinhais');
	});
});

describe('dates', () => {
	it('round-trips datetime-local values', () => {
		const iso = fromDateTimeInputValue('2026-10-15T09:30');

		expect(toDateTimeInputValue(iso)).toBe('2026-10-15T09:30');
		expect(fromDateTimeInputValue('')).toBeNull();
		expect(toDateTimeInputValue(null)).toBe('');
		expect(toDateTimeInputValue('not a date')).toBe('');
	});
});
