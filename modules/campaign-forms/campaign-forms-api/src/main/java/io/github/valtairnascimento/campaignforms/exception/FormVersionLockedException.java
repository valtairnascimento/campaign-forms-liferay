package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class FormVersionLockedException extends PortalException {
	public FormVersionLockedException() {
	}

	public FormVersionLockedException(String msg) {
		super(msg);
	}

	public FormVersionLockedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormVersionLockedException(Throwable throwable) {
		super(throwable);
	}

}
