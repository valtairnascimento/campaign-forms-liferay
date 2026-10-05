package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class FieldKeyException extends PortalException {
	public FieldKeyException() {
	}

	public FieldKeyException(String msg) {
		super(msg);
	}

	public FieldKeyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FieldKeyException(Throwable throwable) {
		super(throwable);
	}

}
