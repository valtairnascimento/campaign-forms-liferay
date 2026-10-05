package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class FormKeyException extends PortalException {
	public FormKeyException() {
	}

	public FormKeyException(String msg) {
		super(msg);
	}

	public FormKeyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormKeyException(Throwable throwable) {
		super(throwable);
	}

}
