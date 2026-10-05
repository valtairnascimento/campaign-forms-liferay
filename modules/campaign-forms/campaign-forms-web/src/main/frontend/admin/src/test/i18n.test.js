import {describe, expect, it} from 'vitest';

import {createTranslator} from '../i18n.js';
import {FIELD_TYPE_ORDER, TEXT_PATTERNS, WIDTHS} from '../views/steps/fieldModel.js';
import {ALL_STEPS} from '../router.js';
import {STYLES, THEMES} from '../views/steps/page/pageModel.js';
import {readAdminMessages} from './languages.js';

const SOURCES = import.meta.glob(['../**/*.{js,jsx}', '!../test/**', '!../**/*.test.*'], {
	eager: true,
	import: 'default',
	query: '?raw',
});

function usedKeys() {
	const keys = new Set();

	Object.values(SOURCES).forEach((source) => {
		for (const match of source.matchAll(/\bt\(\s*'([^']+)'/g)) {
			keys.add(match[1]);
		}
	});

	FIELD_TYPE_ORDER.forEach((type) => keys.add(`field-type-${type}`));
	TEXT_PATTERNS.forEach((pattern) => keys.add(`pattern-${pattern}`));
	WIDTHS.forEach((width) => keys.add(`width-${width.toLowerCase()}`));
	ALL_STEPS.forEach((step) => keys.add(`step-${step}`));
	['cta', 'faq', 'form', 'hero', 'image', 'text'].forEach((type) =>
		keys.add(`block-type-${type}`)
	);
	STYLES.forEach((style) => keys.add(`style-${style}`));
	THEMES.forEach((theme) => keys.add(`theme-${theme}`));
	['center', 'left'].forEach((align) => keys.add(`align-${align}`));
	['content', 'wide'].forEach((size) => keys.add(`image-size-${size}`));
	['desktop', 'mobile', 'tablet'].forEach((device) =>
		keys.add(`device-${device}`)
	);
	['archived', 'draft', 'published'].forEach((status) => keys.add(`status-${status}`));
	['custom_page', 'quick'].forEach((mode) => keys.add(`mode-${mode}`));
	['campaign_limit_reached', 'submission_created'].forEach((event) =>
		keys.add(`event-${event}`)
	);
	['dead', 'failed', 'pending', 'success'].forEach((status) =>
		keys.add(`delivery-${status}`)
	);
	['integration', 'notification'].forEach((type) => keys.add(`target-${type}`));
	['comma', 'semicolon', 'tab'].forEach((separator) =>
		keys.add(`separator-${separator}`)
	);
	[
		'consent-date',
		'consent-version',
		'regenerate-token-confirmation',
		'revoke-data-feed-confirmation',
		'form-version',
		'language',
		'referrer',
		'source-url',
		'submission-date',
		'utm-campaign',
		'utm-content',
		'utm-medium',
		'utm-source',
		'utm-term',
	].forEach((key) => keys.add(key));

	return [...keys].sort();
}

describe('i18n', () => {
	it('replaces Liferay-style arguments', () => {
		const t = createTranslator({greeting: 'Olá, {0} e {1}!', raw: '${campaign.name}'});

		expect(t('greeting', 'Ana', 'Rui')).toBe('Olá, Ana e Rui!');
		expect(t('raw')).toBe('${campaign.name}');
		expect(t('missing-key')).toBe('missing-key');
	});

	it.each(['Language.properties', 'Language_pt_BR.properties', 'Language_en_US.properties'])(
		'%s has every key the app uses',
		(fileName) => {
			const messages = readAdminMessages(fileName);

			expect(Object.keys(SOURCES).length).toBeGreaterThan(10);
			expect(usedKeys().length).toBeGreaterThan(200);

			expect(usedKeys().filter((key) => !(key in messages))).toEqual([]);
		}
	);

	it('Portuguese and English have the same keys', () => {
		expect(Object.keys(readAdminMessages('Language_en_US.properties')).sort()).toEqual(
			Object.keys(readAdminMessages('Language_pt_BR.properties')).sort()
		);
	});
});
