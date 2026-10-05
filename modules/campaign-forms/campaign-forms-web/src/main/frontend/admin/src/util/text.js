function stripAccents(value) {
	return value.normalize('NFD').replace(/[̀-ͯ]/g, '');
}

export function slugify(value, maxLength = 100) {
	return stripAccents(String(value || ''))
		.toLowerCase()
		.replace(/[^a-z0-9]+/g, '-')
		.replace(/^-+|-+$/g, '')
		.slice(0, maxLength)
		.replace(/-+$/g, '');
}

export const SLUG_PATTERN = /^[a-z0-9]+(-[a-z0-9]+)*$/;

export function toFieldKey(label) {
	const words = stripAccents(String(label || ''))
		.replace(/[^A-Za-z0-9]+/g, ' ')
		.trim()
		.split(' ')
		.filter(Boolean);

	let key = words
		.map((word, index) => {
			const lower = word.toLowerCase();

			return index === 0
				? lower
				: lower.charAt(0).toUpperCase() + lower.slice(1);
		})
		.join('');

	if (key && !/^[a-z]/.test(key)) {
		key = `campo${key.charAt(0).toUpperCase()}${key.slice(1)}`;
	}

	return key.slice(0, 50);
}

export const FIELD_KEY_PATTERN = /^[a-z][a-zA-Z0-9_]*$/;

export function toOptionValue(label) {
	return slugify(label, 100);
}
