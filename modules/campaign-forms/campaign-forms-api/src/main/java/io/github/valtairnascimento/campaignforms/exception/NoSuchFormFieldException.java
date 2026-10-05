package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchFormFieldException extends NoSuchModelException {
	public NoSuchFormFieldException() {
	}

	public NoSuchFormFieldException(String msg) {
		super(msg);
	}

	public NoSuchFormFieldException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFormFieldException(Throwable throwable) {
		super(throwable);
	}

}
