package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchFormFieldOptionException extends NoSuchModelException {
	public NoSuchFormFieldOptionException() {
	}

	public NoSuchFormFieldOptionException(String msg) {
		super(msg);
	}

	public NoSuchFormFieldOptionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFormFieldOptionException(Throwable throwable) {
		super(throwable);
	}

}
