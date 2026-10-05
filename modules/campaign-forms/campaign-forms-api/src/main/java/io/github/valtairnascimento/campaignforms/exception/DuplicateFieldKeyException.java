package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DuplicateFieldKeyException extends PortalException {
	public DuplicateFieldKeyException() {
	}

	public DuplicateFieldKeyException(String msg) {
		super(msg);
	}

	public DuplicateFieldKeyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DuplicateFieldKeyException(Throwable throwable) {
		super(throwable);
	}

}
