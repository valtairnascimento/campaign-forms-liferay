package io.github.valtairnascimento.campaignforms.field;

public class FieldValueException extends Exception {
	public FieldValueException(
		String code, String messageKey, Object... messageArguments) {
		super(code, null, false, false);

		_code = code;
		_messageKey = messageKey;
		_messageArguments = messageArguments;
	}

	public String getCode() {
		return _code;
	}

	public Object[] getMessageArguments() {
		return _messageArguments.clone();
	}

	public String getMessageKey() {
		return _messageKey;
	}

	public FieldError toFieldError(String field) {
		return new FieldError(field, _code, _messageKey, _messageArguments);
	}

	private final String _code;
	private final Object[] _messageArguments;
	private final String _messageKey;
}
