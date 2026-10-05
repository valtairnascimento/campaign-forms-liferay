package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchEventDeliveryException extends NoSuchModelException {
	public NoSuchEventDeliveryException() {
	}

	public NoSuchEventDeliveryException(String msg) {
		super(msg);
	}

	public NoSuchEventDeliveryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchEventDeliveryException(Throwable throwable) {
		super(throwable);
	}

}
