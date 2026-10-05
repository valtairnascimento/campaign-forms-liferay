package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class FormTokenException extends PortalException {
	public FormTokenException() {
	}

	public FormTokenException(String msg) {
		super(msg);
	}

	public FormTokenException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormTokenException(Throwable throwable) {
		super(throwable);
	}

}
