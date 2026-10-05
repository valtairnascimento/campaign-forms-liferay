package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchSubmissionException extends NoSuchModelException {
	public NoSuchSubmissionException() {
	}

	public NoSuchSubmissionException(String msg) {
		super(msg);
	}

	public NoSuchSubmissionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchSubmissionException(Throwable throwable) {
		super(throwable);
	}

}
