package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchPageBlockException extends NoSuchModelException {
	public NoSuchPageBlockException() {
	}

	public NoSuchPageBlockException(String msg) {
		super(msg);
	}

	public NoSuchPageBlockException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchPageBlockException(Throwable throwable) {
		super(throwable);
	}

}
