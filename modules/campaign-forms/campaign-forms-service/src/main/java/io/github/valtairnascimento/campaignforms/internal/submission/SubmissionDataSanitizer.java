package io.github.valtairnascimento.campaignforms.internal.submission;

import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;

import java.util.Locale;
import java.util.regex.Pattern;

public class SubmissionDataSanitizer {
	public static final int MAX_LANGUAGE_ID_LENGTH = 10;

	public static final int MAX_URL_LENGTH = 2048;

	public static final int MAX_USER_AGENT_LENGTH = 500;

	public static final int MAX_UTM_LENGTH = 255;

	public static String normalizeIdempotencyKey(String idempotencyKey)
		throws InvalidFieldException {
		if ((idempotencyKey == null) || idempotencyKey.trim(
			).isEmpty()) {
			return null;
		}

		idempotencyKey = idempotencyKey.trim();

		if (!_idempotencyKeyPattern.matcher(
				idempotencyKey
			).matches()) {
			throw new InvalidFieldException("idempotency-key-is-invalid");
		}

		return idempotencyKey;
	}

	public static String normalizeLanguageId(
		String languageId, String defaultLanguageId) {
		if ((languageId != null) &&
			(languageId.length() <= MAX_LANGUAGE_ID_LENGTH) &&
			_languageIdPattern.matcher(
				languageId
			).matches()) {
			return languageId;
		}

		return defaultLanguageId;
	}

	public static String sanitizeText(String value, int maxLength) {
		if (value == null) {
			return "";
		}

		value = _controlCharactersPattern.matcher(
			value
		).replaceAll(
			""
		).trim();

		if (value.length() > maxLength) {
			value = value.substring(0, maxLength);
		}

		return value;
	}

	public static String sanitizeUrl(String url) {
		String sanitizedURL = sanitizeText(url, Integer.MAX_VALUE);

		String lowerCaseURL = sanitizedURL.toLowerCase(Locale.ROOT);

		if (!lowerCaseURL.startsWith("https://") &&
			!lowerCaseURL.startsWith("http://")) {
			return "";
		}

		int index = _indexOfAny(sanitizedURL, '?', '#');

		if (index >= 0) {
			sanitizedURL = sanitizedURL.substring(0, index);
		}

		if (sanitizedURL.length() > MAX_URL_LENGTH) {
			sanitizedURL = sanitizedURL.substring(0, MAX_URL_LENGTH);
		}

		return sanitizedURL;
	}

	private static int _indexOfAny(String value, char... chars) {
		int index = -1;

		for (char c : chars) {
			int charIndex = value.indexOf(c);

			if ((charIndex >= 0) && ((index < 0) || (charIndex < index))) {
				index = charIndex;
			}
		}

		return index;
	}

	private SubmissionDataSanitizer() {
	}

	private static final Pattern _controlCharactersPattern = Pattern.compile(
		"\\p{Cntrl}");
	private static final Pattern _idempotencyKeyPattern = Pattern.compile(
		"^[A-Za-z0-9_-]{8,64}$");
	private static final Pattern _languageIdPattern = Pattern.compile(
		"^[a-z]{2,3}(_[A-Z]{2})?$");
}
