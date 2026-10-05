package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchFormVersionException extends NoSuchModelException {
	public NoSuchFormVersionException() {
	}

	public NoSuchFormVersionException(String msg) {
		super(msg);
	}

	public NoSuchFormVersionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFormVersionException(Throwable throwable) {
		super(throwable);
	}

}
