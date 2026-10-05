package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class FormFieldConfigurationException extends PortalException {
	public FormFieldConfigurationException() {
	}

	public FormFieldConfigurationException(String msg) {
		super(msg);
	}

	public FormFieldConfigurationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormFieldConfigurationException(Throwable throwable) {
		super(throwable);
	}

}
