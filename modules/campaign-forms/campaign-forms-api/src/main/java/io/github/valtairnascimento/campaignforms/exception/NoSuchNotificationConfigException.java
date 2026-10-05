package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchNotificationConfigException extends NoSuchModelException {
	public NoSuchNotificationConfigException() {
	}

	public NoSuchNotificationConfigException(String msg) {
		super(msg);
	}

	public NoSuchNotificationConfigException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchNotificationConfigException(Throwable throwable) {
		super(throwable);
	}

}
