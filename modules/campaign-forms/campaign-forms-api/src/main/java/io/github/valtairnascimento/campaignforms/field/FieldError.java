package io.github.valtairnascimento.campaignforms.field;

import java.util.Arrays;
import java.util.Objects;

public class FieldError {
	public static final String CAPTCHA_INVALID = "CAPTCHA_INVALID";

	public static final String CONSENT_REQUIRED = "CONSENT_REQUIRED";

	public static final String INVALID_DATE = "INVALID_DATE";

	public static final String INVALID_EMAIL = "INVALID_EMAIL";

	public static final String INVALID_NUMBER = "INVALID_NUMBER";

	public static final String INVALID_OPTION = "INVALID_OPTION";

	public static final String INVALID_PHONE = "INVALID_PHONE";

	public static final String INVALID_VALUE = "INVALID_VALUE";

	public static final String PATTERN_MISMATCH = "PATTERN_MISMATCH";

	public static final String REQUIRED = "REQUIRED";

	public static final String TOO_FEW_SELECTED = "TOO_FEW_SELECTED";

	public static final String TOO_LARGE = "TOO_LARGE";

	public static final String TOO_LONG = "TOO_LONG";

	public static final String TOO_MANY_SELECTED = "TOO_MANY_SELECTED";

	public static final String TOO_SHORT = "TOO_SHORT";

	public static final String TOO_SMALL = "TOO_SMALL";

	public FieldError(
		String field, String code, String messageKey,
		Object... messageArguments) {
		_field = field;
		_code = code;
		_messageKey = messageKey;
		_messageArguments = messageArguments;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FieldError)) {
			return false;
		}

		FieldError fieldError = (FieldError)object;

		return Objects.equals(_field, fieldError._field) &&
			   Objects.equals(_code, fieldError._code);
	}

	public String getCode() {
		return _code;
	}

	public String getField() {
		return _field;
	}

	public Object[] getMessageArguments() {
		return _messageArguments.clone();
	}

	public String getMessageKey() {
		return _messageKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_field, _code);
	}

	@Override
	public String toString() {
		return _field + ":" + _code + Arrays.toString(_messageArguments);
	}

	private final String _code;
	private final String _field;
	private final Object[] _messageArguments;
	private final String _messageKey;
}
