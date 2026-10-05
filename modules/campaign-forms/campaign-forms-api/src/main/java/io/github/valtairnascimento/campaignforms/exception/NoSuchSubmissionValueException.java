package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchSubmissionValueException extends NoSuchModelException {
	public NoSuchSubmissionValueException() {
	}

	public NoSuchSubmissionValueException(String msg) {
		super(msg);
	}

	public NoSuchSubmissionValueException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchSubmissionValueException(Throwable throwable) {
		super(throwable);
	}

}
