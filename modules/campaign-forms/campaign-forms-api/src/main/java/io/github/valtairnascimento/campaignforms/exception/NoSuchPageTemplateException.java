package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchPageTemplateException extends NoSuchModelException {
	public NoSuchPageTemplateException() {
	}

	public NoSuchPageTemplateException(String msg) {
		super(msg);
	}

	public NoSuchPageTemplateException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchPageTemplateException(Throwable throwable) {
		super(throwable);
	}

}
