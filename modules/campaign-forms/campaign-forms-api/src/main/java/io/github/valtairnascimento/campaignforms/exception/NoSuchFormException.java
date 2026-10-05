package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchFormException extends NoSuchModelException {
	public NoSuchFormException() {
	}

	public NoSuchFormException(String msg) {
		super(msg);
	}

	public NoSuchFormException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFormException(Throwable throwable) {
		super(throwable);
	}

}
