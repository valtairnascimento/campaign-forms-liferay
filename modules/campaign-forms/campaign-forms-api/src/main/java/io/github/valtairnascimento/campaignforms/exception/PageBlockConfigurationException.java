package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class PageBlockConfigurationException extends PortalException {
	public PageBlockConfigurationException() {
	}

	public PageBlockConfigurationException(String msg) {
		super(msg);
	}

	public PageBlockConfigurationException(String msg, String field) {
		super(msg);

		_field = field;
	}

	public PageBlockConfigurationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public PageBlockConfigurationException(Throwable throwable) {
		super(throwable);
	}

	public String getField() {
		return _field;
	}

	private String _field;
}
