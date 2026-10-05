package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DataFeedConfigurationException extends PortalException {
	public DataFeedConfigurationException() {
	}

	public DataFeedConfigurationException(String msg) {
		super(msg);
	}

	public DataFeedConfigurationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DataFeedConfigurationException(Throwable throwable) {
		super(throwable);
	}

}
