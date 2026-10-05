package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class IntegrationExecutionException extends PortalException {
	public IntegrationExecutionException() {
	}

	public IntegrationExecutionException(String msg) {
		super(msg);
	}

	public IntegrationExecutionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public IntegrationExecutionException(Throwable throwable) {
		super(throwable);
	}

}
