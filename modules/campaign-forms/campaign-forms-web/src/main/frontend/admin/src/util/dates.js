function pad(value) {
	return String(value).padStart(2, '0');
}

export function toDateTimeInputValue(iso) {
	if (!iso) {
		return '';
	}

	const date = new Date(iso);

	if (Number.isNaN(date.getTime())) {
		return '';
	}

	return (
		`${date.getFullYear()}-${pad(date.getMonth() + 1)}-` +
		`${pad(date.getDate())}T${pad(date.getHours())}:` +
		`${pad(date.getMinutes())}`
	);
}

export function fromDateTimeInputValue(value) {
	if (!value) {
		return null;
	}

	const date = new Date(value);

	return Number.isNaN(date.getTime()) ? null : date.toISOString();
}

export function formatDateTime(iso, locale, timeZone) {
	if (!iso) {
		return '';
	}

	const date = new Date(iso);

	if (Number.isNaN(date.getTime())) {
		return '';
	}

	const options = {dateStyle: 'short', timeStyle: 'short'};

	try {
		return new Intl.DateTimeFormat(locale, {...options, timeZone}).format(
			date
		);
	}
	catch (error) {
		return new Intl.DateTimeFormat(locale, options).format(date);
	}
}
