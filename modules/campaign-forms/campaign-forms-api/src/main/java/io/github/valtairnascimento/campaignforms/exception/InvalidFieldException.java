package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class InvalidFieldException extends PortalException {
	public InvalidFieldException() {
	}

	public InvalidFieldException(String msg) {
		super(msg);
	}

	public InvalidFieldException(String msg, String fieldKey) {
		super(msg);

		_fieldKey = fieldKey;
	}

	public InvalidFieldException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public InvalidFieldException(Throwable throwable) {
		super(throwable);
	}

	public String getFieldKey() {
		return _fieldKey;
	}

	private String _fieldKey;
}
