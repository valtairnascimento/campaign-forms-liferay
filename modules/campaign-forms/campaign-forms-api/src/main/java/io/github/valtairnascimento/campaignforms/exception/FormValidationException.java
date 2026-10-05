package io.github.valtairnascimento.campaignforms.exception;

import io.github.valtairnascimento.campaignforms.field.FieldError;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FormValidationException extends PortalException {
	public FormValidationException() {
	}

	public FormValidationException(List<FieldError> fieldErrors) {
		super("form-has-invalid-fields");

		_fieldErrors.addAll(fieldErrors);
	}

	public FormValidationException(String msg) {
		super(msg);
	}

	public FormValidationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public FormValidationException(Throwable throwable) {
		super(throwable);
	}

	public List<FieldError> getFieldErrors() {
		return Collections.unmodifiableList(_fieldErrors);
	}

	private final List<FieldError> _fieldErrors = new ArrayList<>();
}
