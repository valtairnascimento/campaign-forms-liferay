package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchPageConfigurationException extends NoSuchModelException {
	public NoSuchPageConfigurationException() {
	}

	public NoSuchPageConfigurationException(String msg) {
		super(msg);
	}

	public NoSuchPageConfigurationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchPageConfigurationException(Throwable throwable) {
		super(throwable);
	}

}
