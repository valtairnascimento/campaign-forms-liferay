package io.github.valtairnascimento.campaignforms.headless.common.internal.jaxrs.exception.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {
	public ErrorResponse(String code, String message, String errorId) {
		_code = code;
		_message = message;
		_errorId = errorId;
	}

	public ErrorResponse addError(String field, String code, String message) {
		_errors.add(new Error(field, code, message));

		return this;
	}

	public String getCode() {
		return _code;
	}

	public String getErrorId() {
		return _errorId;
	}

	public List<Error> getErrors() {
		return Collections.unmodifiableList(_errors);
	}

	public String getMessage() {
		return _message;
	}

	public static class Error {
		public Error(String field, String code, String message) {
			_field = field;
			_code = code;
			_message = message;
		}

		public String getCode() {
			return _code;
		}

		public String getField() {
			return _field;
		}

		public String getMessage() {
			return _message;
		}

		private final String _code;
		private final String _field;
		private final String _message;
	}

	private final String _code;
	private final String _errorId;
	private final List<Error> _errors = new ArrayList<>();
	private final String _message;
}
