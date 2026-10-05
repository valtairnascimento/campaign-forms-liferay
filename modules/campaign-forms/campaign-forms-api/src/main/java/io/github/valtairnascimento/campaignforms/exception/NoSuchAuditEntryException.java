package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchAuditEntryException extends NoSuchModelException {
	public NoSuchAuditEntryException() {
	}

	public NoSuchAuditEntryException(String msg) {
		super(msg);
	}

	public NoSuchAuditEntryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchAuditEntryException(Throwable throwable) {
		super(throwable);
	}

}
