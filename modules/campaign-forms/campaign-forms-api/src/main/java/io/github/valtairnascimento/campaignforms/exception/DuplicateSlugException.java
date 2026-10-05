package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DuplicateSlugException extends PortalException {
	public DuplicateSlugException() {
	}

	public DuplicateSlugException(String msg) {
		super(msg);
	}

	public DuplicateSlugException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DuplicateSlugException(Throwable throwable) {
		super(throwable);
	}

}
