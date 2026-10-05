package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchIntegrationConfigException extends NoSuchModelException {
	public NoSuchIntegrationConfigException() {
	}

	public NoSuchIntegrationConfigException(String msg) {
		super(msg);
	}

	public NoSuchIntegrationConfigException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchIntegrationConfigException(Throwable throwable) {
		super(throwable);
	}

}
