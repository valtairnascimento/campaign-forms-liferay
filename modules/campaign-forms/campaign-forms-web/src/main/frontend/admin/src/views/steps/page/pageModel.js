export const THEMES = ['default', 'light', 'dark', 'brand', 'accent', 'neutral'];

export const STYLES = ['brand', 'light', 'dark'];

const PAGE_KEYS = [
	'canonicalUrl',
	'cssClass',
	'noIndex',
	'ogDescription',
	'ogImageFileEntryId',
	'ogTitle',
	'seoDescription',
	'seoTitle',
	'settings',
	'templateKey',
	'themeKey',
];

export function toPagePayload(page, changes = {}) {
	const merged = {...page, ...changes};

	const payload = {};

	PAGE_KEYS.forEach((key) => {
		if (merged[key] !== undefined && merged[key] !== null) {
			payload[key] = merged[key];
		}
	});

	return payload;
}

export function getPreviewURL(publicURL, slug) {
	return publicURL ? `${publicURL}${slug}/preview?p_l_mode=preview` : null;
}

export function getAnchors(blocks, t) {
	return blocks
		.filter((block) => block.blockType !== 'HERO')
		.map((block) => ({
			label: t('anchor-x', blockSummary(block, t)),
			value: `#${block.blockKey}`,
		}));
}

export function blockSummary(block, t) {
	const configuration = block.configuration || {};

	const text =
		configuration.title ||
		configuration.question ||
		configuration.caption ||
		configuration.alt ||
		configuration.body ||
		'';

	const label = t(`block-type-${String(block.blockType).toLowerCase()}`);

	if (!text) {
		return label;
	}

	const firstLine = String(text).split('\n')[0];

	return firstLine.length > 60 ? `${firstLine.slice(0, 57)}…` : firstLine;
}

export function cleanConfiguration(configuration) {
	const clean = {};

	Object.keys(configuration).forEach((key) => {
		const value = configuration[key];

		if (value === '' || value === null || value === undefined) {
			return;
		}

		if (value === false) {
			return;
		}

		clean[key] = typeof value === 'string' ? value.trim() : value;
	});

	return clean;
}

export function validateBlock(blockType, configuration, t) {
	const errors = {};

	const required = (key) => {
		const value = configuration[key];

		if (value === undefined || value === null || String(value).trim() === '') {
			errors[key] = t('field-required');
		}
	};

	if (blockType === 'HERO') {
		required('title');

		if (configuration.buttonLabel && !configuration.buttonUrl) {
			errors.buttonUrl = t('field-required');
		}
	}
	else if (blockType === 'TEXT') {
		required('body');
	}
	else if (blockType === 'IMAGE') {
		required('imageFileEntryId');

		if (!configuration.decorative) {
			required('alt');
		}
	}
	else if (blockType === 'CTA') {
		required('title');
		required('buttonLabel');
		required('buttonUrl');
	}
	else if (blockType === 'FAQ') {
		const items = configuration.items || [];

		if (!items.length) {
			errors.items = t('faq-items-required');
		}

		items.forEach((item, index) => {
			if (!String(item.question || '').trim()) {
				errors[`items[${index}].question`] = t('field-required');
			}

			if (!String(item.answer || '').trim()) {
				errors[`items[${index}].answer`] = t('field-required');
			}
		});
	}

	return errors;
}

export function moveBlock(blocks, fromIndex, toIndex) {
	if (
		fromIndex === toIndex ||
		toIndex < 0 ||
		toIndex >= blocks.length ||
		fromIndex < 0
	) {
		return blocks;
	}

	const copy = blocks.slice();

	const [block] = copy.splice(fromIndex, 1);

	copy.splice(toIndex, 0, block);

	return copy;
}
