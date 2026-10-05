package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchDataFeedException extends NoSuchModelException {
	public NoSuchDataFeedException() {
	}

	public NoSuchDataFeedException(String msg) {
		super(msg);
	}

	public NoSuchDataFeedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchDataFeedException(Throwable throwable) {
		super(throwable);
	}

}
