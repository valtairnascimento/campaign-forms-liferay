package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class SubmissionLimitReachedException extends PortalException {
	public SubmissionLimitReachedException() {
	}

	public SubmissionLimitReachedException(String msg) {
		super(msg);
	}

	public SubmissionLimitReachedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public SubmissionLimitReachedException(Throwable throwable) {
		super(throwable);
	}

}
