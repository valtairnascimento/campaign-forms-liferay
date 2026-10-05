package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class PageSettingsException extends PortalException {
	public PageSettingsException() {
	}

	public PageSettingsException(String msg) {
		super(msg);
	}

	public PageSettingsException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public PageSettingsException(Throwable throwable) {
		super(throwable);
	}

}
