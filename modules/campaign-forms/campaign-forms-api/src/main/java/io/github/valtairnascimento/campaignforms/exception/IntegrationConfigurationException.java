package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class IntegrationConfigurationException extends PortalException {
	public IntegrationConfigurationException() {
	}

	public IntegrationConfigurationException(String msg) {
		super(msg);
	}

	public IntegrationConfigurationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public IntegrationConfigurationException(Throwable throwable) {
		super(throwable);
	}

}
