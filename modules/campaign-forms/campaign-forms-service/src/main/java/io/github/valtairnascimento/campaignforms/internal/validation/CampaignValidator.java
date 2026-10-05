package io.github.valtairnascimento.campaignforms.internal.validation;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.ReservedKeys;
import io.github.valtairnascimento.campaignforms.exception.CampaignDatesException;
import io.github.valtairnascimento.campaignforms.exception.CampaignNameException;
import io.github.valtairnascimento.campaignforms.exception.CampaignSlugException;
import io.github.valtairnascimento.campaignforms.exception.FieldKeyException;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.regex.Pattern;

public class CampaignValidator {
	public static final int MAX_KEY_LENGTH = 75;

	public static final int MAX_NAME_LENGTH = 150;

	public static boolean isBlank(String value) {
		if (value == null) {
			return true;
		}

		String trimmedValue = value.trim();

		return trimmedValue.isEmpty();
	}

	public static boolean isEmailOrSubmissionPlaceholder(String value) {
		if (Validator.isEmailAddress(value)) {
			return true;
		}

		return _submissionPlaceholderPattern.matcher(
			value
		).matches();
	}

	public static boolean isValidKey(String key) {
		if (Validator.isNull(key)) {
			return false;
		}

		if (key.length() > MAX_KEY_LENGTH) {
			return false;
		}

		return _keyPattern.matcher(
			key
		).matches();
	}

	public static boolean isValidRedirectUrl(String url) {
		if (Validator.isNull(url)) {
			return true;
		}

		if (url.startsWith(StringPool.DOUBLE_SLASH) ||
			url.startsWith(StringPool.SLASH + CharPool.BACK_SLASH)) {
			return false;
		}

		if (url.startsWith(StringPool.SLASH)) {
			return true;
		}

		String lowerCaseURL = StringUtil.toLowerCase(url);

		if (!lowerCaseURL.startsWith("https://") &&
			!lowerCaseURL.startsWith("http://")) {
			return false;
		}

		return Validator.isUrl(url);
	}

	public static void validateDates(Date startDate, Date endDate)
		throws CampaignDatesException {
		if ((startDate != null) && (endDate != null) &&
			!endDate.after(startDate)) {
			throw new CampaignDatesException(
				"end-date-must-be-after-start-date");
		}
	}

	public static void validateFieldKey(String fieldKey)
		throws FieldKeyException {
		if (Validator.isNull(fieldKey)) {
			throw new FieldKeyException("field-key-is-required");
		}

		if ((fieldKey.length() > CampaignConstants.MAX_FIELD_KEY_LENGTH) ||
			!_fieldKeyPattern.matcher(
				fieldKey
			).matches()) {
			throw new FieldKeyException("field-key-is-invalid");
		}

		if (ReservedKeys.isReservedFieldKey(fieldKey)) {
			throw new FieldKeyException("field-key-is-reserved");
		}
	}

	public static void validateName(String name) throws CampaignNameException {
		if (isBlank(name)) {
			throw new CampaignNameException("campaign-name-is-required");
		}

		if (name.length() > MAX_NAME_LENGTH) {
			throw new CampaignNameException("campaign-name-is-too-long");
		}
	}

	public static void validateSlug(String slug) throws CampaignSlugException {
		if (Validator.isNull(slug)) {
			throw new CampaignSlugException("slug-is-required");
		}

		if ((slug.length() > CampaignConstants.MAX_SLUG_LENGTH) ||
			!_slugPattern.matcher(
				slug
			).matches()) {
			throw new CampaignSlugException("slug-is-invalid");
		}

		if (ReservedKeys.isReservedSlug(slug)) {
			throw new CampaignSlugException("slug-is-reserved");
		}
	}

	private CampaignValidator() {
	}

	private static final Pattern _fieldKeyPattern = Pattern.compile(
		"^[a-z][a-zA-Z0-9_]*$");
	private static final Pattern _keyPattern = Pattern.compile(
		"^[a-z0-9]+(?:[-_][a-z0-9]+)*$");
	private static final Pattern _slugPattern = Pattern.compile(
		"^[a-z0-9]+(?:-[a-z0-9]+)*$");
	private static final Pattern _submissionPlaceholderPattern =
		Pattern.compile("^\\$\\{submission\\.[a-z][a-zA-Z0-9_]*\\}$");
}
