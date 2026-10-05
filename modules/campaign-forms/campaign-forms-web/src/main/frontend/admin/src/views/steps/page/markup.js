export function wrapSelection(text, start, end, marker, placeholder) {
	const selected = text.slice(start, end) || placeholder;

	const next =
		text.slice(0, start) + marker + selected + marker + text.slice(end);

	return {
		selectionEnd: start + marker.length + selected.length,
		selectionStart: start + marker.length,
		text: next,
	};
}

export function insertLink(text, start, end, placeholder) {
	const label = text.slice(start, end) || placeholder;

	const url = 'https://';

	const inserted = `[${label}](${url})`;

	const urlStart = start + label.length + 3;

	return {
		selectionEnd: urlStart + url.length,
		selectionStart: urlStart,
		text: text.slice(0, start) + inserted + text.slice(end),
	};
}

export function prefixLines(text, start, end, prefix) {
	const lineStart = text.lastIndexOf('\n', start - 1) + 1;

	let lineEnd = text.indexOf('\n', end);

	if (lineEnd < 0) {
		lineEnd = text.length;
	}

	const lines = text.slice(lineStart, lineEnd).split('\n');

	const prefixed = lines
		.map((line, index) => {
			const linePrefix = prefix === '1. ' ? `${index + 1}. ` : prefix;

			return line.startsWith(linePrefix) ? line : linePrefix + line;
		})
		.join('\n');

	return {
		selectionEnd: lineStart + prefixed.length,
		selectionStart: lineStart,
		text: text.slice(0, lineStart) + prefixed + text.slice(lineEnd),
	};
}
