package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class NotificationConfigException extends PortalException {
	public NotificationConfigException() {
	}

	public NotificationConfigException(String msg) {
		super(msg);
	}

	public NotificationConfigException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NotificationConfigException(Throwable throwable) {
		super(throwable);
	}

}
