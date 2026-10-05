package io.github.valtairnascimento.campaignforms.internal.notification;

public class NotificationException extends Exception {
	public NotificationException(String code, boolean permanent) {
		super(code);

		_code = code;
		_permanent = permanent;
	}

	public String getCode() {
		return _code;
	}

	public boolean isPermanent() {
		return _permanent;
	}

	private static final long serialVersionUID = 1L;

	private final String _code;
	private final boolean _permanent;
}
