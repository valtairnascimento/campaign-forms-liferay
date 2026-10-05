package io.github.valtairnascimento.campaignforms.headless.common.internal.jaxrs.exception.mapper;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.exception.CampaignHasSubmissionsException;
import io.github.valtairnascimento.campaignforms.exception.CampaignInactiveException;
import io.github.valtairnascimento.campaignforms.exception.CampaignPublicationException;
import io.github.valtairnascimento.campaignforms.exception.CampaignStatusException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateCampaignExternalReferenceCodeException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateFieldKeyException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateSlugException;
import io.github.valtairnascimento.campaignforms.exception.FormTokenException;
import io.github.valtairnascimento.campaignforms.exception.FormValidationException;
import io.github.valtairnascimento.campaignforms.exception.FormVersionLockedException;
import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.exception.SubmissionLimitReachedException;
import io.github.valtairnascimento.campaignforms.field.FieldError;

import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.validation.ValidationException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;

@Component(service = ErrorResponseFactory.class)
public class ErrorResponseFactory {
	public static final String AUTHENTICATION_REQUIRED =
		"AUTHENTICATION_REQUIRED";

	public static final String FORBIDDEN = "FORBIDDEN";

	public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

	public static final String INVALID_REQUEST = "INVALID_REQUEST";

	public static final String NOT_FOUND = "NOT_FOUND";

	public Response toResponse(Throwable throwable, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, CampaignConstants.class);

		if (throwable instanceof FormValidationException) {
			return _toFormValidationResponse(
				(FormValidationException)throwable, resourceBundle);
		}

		if (throwable instanceof CampaignPublicationException) {
			return _toPublicationResponse(
				(CampaignPublicationException)throwable, resourceBundle);
		}

		if (throwable instanceof InvalidFieldException) {
			InvalidFieldException invalidFieldException =
				(InvalidFieldException)throwable;

			ErrorResponse errorResponse = _errorResponse(
				INVALID_REQUEST, throwable.getMessage(), resourceBundle);

			if (invalidFieldException.getFieldKey() != null) {
				errorResponse = _errorResponse(
					"UNKNOWN_FIELD", throwable.getMessage(), resourceBundle);

				errorResponse.addError(
					invalidFieldException.getFieldKey(), "UNKNOWN_FIELD",
					_get(resourceBundle, throwable.getMessage()));
			}

			return _response(Response.Status.BAD_REQUEST, errorResponse);
		}

		if (throwable instanceof PageBlockConfigurationException) {
			PageBlockConfigurationException pageBlockConfigurationException =
				(PageBlockConfigurationException)throwable;

			ErrorResponse errorResponse = _errorResponse(
				"VALIDATION_ERROR", throwable.getMessage(), resourceBundle);

			if (pageBlockConfigurationException.getField() != null) {
				errorResponse.addError(
					pageBlockConfigurationException.getField(), "INVALID_VALUE",
					_get(resourceBundle, throwable.getMessage()));
			}

			return _response(Response.Status.BAD_REQUEST, errorResponse);
		}

		if (throwable instanceof NoSuchCampaignException) {
			return _response(
				Response.Status.NOT_FOUND,
				_errorResponse(
					"CAMPAIGN_NOT_FOUND", "campaign-not-found",
					resourceBundle));
		}

		if (throwable instanceof NoSuchModelException) {
			return _response(
				Response.Status.NOT_FOUND,
				_errorResponse(
					NOT_FOUND, "resource-not-found", resourceBundle));
		}

		if (throwable instanceof PrincipalException.MustBeAuthenticated) {
			return _response(
				Response.Status.UNAUTHORIZED,
				_errorResponse(
					AUTHENTICATION_REQUIRED, "authentication-is-required",
					resourceBundle));
		}

		if (throwable instanceof PrincipalException) {
			return _response(
				Response.Status.FORBIDDEN,
				_errorResponse(
					FORBIDDEN, "you-do-not-have-permission-for-this-action",
					resourceBundle));
		}

		String conflictCode = _conflictCodes.get(throwable.getClass());

		if (conflictCode != null) {
			return _response(
				Response.Status.CONFLICT,
				_errorResponse(
					conflictCode, throwable.getMessage(), resourceBundle));
		}

		if (throwable instanceof FormTokenException) {
			return _response(
				Response.Status.BAD_REQUEST,
				_errorResponse(
					"INVALID_FORM_TOKEN", throwable.getMessage(),
					resourceBundle));
		}

		if (_isCampaignException(throwable)) {
			return _response(
				Response.Status.BAD_REQUEST,
				_errorResponse(
					"VALIDATION_ERROR", throwable.getMessage(),
					resourceBundle));
		}

		if (throwable instanceof ValidationException) {
			return _response(
				Response.Status.BAD_REQUEST,
				_errorResponse(
					INVALID_REQUEST, "request-is-invalid", resourceBundle));
		}

		if (throwable instanceof WebApplicationException) {
			return _toWebApplicationResponse(
				(WebApplicationException)throwable, resourceBundle);
		}

		if (_isJsonProcessingException(throwable)) {
			return _response(
				Response.Status.BAD_REQUEST,
				_errorResponse(
					INVALID_REQUEST, "request-is-invalid", resourceBundle));
		}

		String errorId = UUID.randomUUID(
		).toString();

		_log.error("Unexpected error " + errorId, throwable);

		return _response(
			Response.Status.INTERNAL_SERVER_ERROR,
			new ErrorResponse(
				INTERNAL_ERROR,
				_get(resourceBundle, "an-unexpected-error-occurred"), errorId));
	}

	private ErrorResponse _errorResponse(
		String code, String messageKey, ResourceBundle resourceBundle) {
		return new ErrorResponse(code, _get(resourceBundle, messageKey), null);
	}

	private String _get(
		ResourceBundle resourceBundle, String key, Object... arguments) {
		if (key == null) {
			return null;
		}

		if (arguments.length == 0) {
			return LanguageUtil.get(resourceBundle, key);
		}

		return LanguageUtil.format(resourceBundle, key, arguments, false);
	}

	private boolean _isCampaignException(Throwable throwable) {
		if (!(throwable instanceof PortalException)) {
			return false;
		}

		Package exceptionPackage = throwable.getClass(
		).getPackage();

		return (exceptionPackage != null) &&
			   _CAMPAIGN_EXCEPTION_PACKAGE.equals(exceptionPackage.getName());
	}

	private boolean _isJsonProcessingException(Throwable throwable) {
		String className = throwable.getClass(
		).getName();

		return className.startsWith("com.fasterxml.jackson.");
	}

	private Response _response(
		Response.Status status, ErrorResponse errorResponse) {
		return Response.status(
			status
		).entity(
			errorResponse
		).type(
			MediaType.APPLICATION_JSON_TYPE
		).build();
	}

	private Response _toFormValidationResponse(
		FormValidationException formValidationException,
		ResourceBundle resourceBundle) {
		ErrorResponse errorResponse = _errorResponse(
			"FORM_VALIDATION_ERROR", "form-has-invalid-fields",
			resourceBundle);

		for (FieldError fieldError : formValidationException.getFieldErrors()) {
			errorResponse.addError(
				fieldError.getField(), fieldError.getCode(),
				_get(
					resourceBundle, fieldError.getMessageKey(),
					fieldError.getMessageArguments()));
		}

		return Response.status(
			_UNPROCESSABLE_ENTITY
		).entity(
			errorResponse
		).type(
			MediaType.APPLICATION_JSON_TYPE
		).build();
	}

	private Response _toPublicationResponse(
		CampaignPublicationException campaignPublicationException,
		ResourceBundle resourceBundle) {
		ErrorResponse errorResponse = _errorResponse(
			"PUBLICATION_BLOCKED", "campaign-cannot-be-published",
			resourceBundle);

		for (PublicationIssue publicationIssue :
				campaignPublicationException.getPublicationIssues()) {
			String target = publicationIssue.getTarget();

			errorResponse.addError(
				target.isEmpty() ? null : target, publicationIssue.getCode(),
				_get(resourceBundle, publicationIssue.getCode()));
		}

		return _response(Response.Status.CONFLICT, errorResponse);
	}

	private Response _toWebApplicationResponse(
		WebApplicationException webApplicationException,
		ResourceBundle resourceBundle) {
		Response response = webApplicationException.getResponse();

		int status = response.getStatus();

		String code = INVALID_REQUEST;
		String messageKey = "request-is-invalid";

		if (status == Response.Status.NOT_FOUND.getStatusCode()) {
			code = NOT_FOUND;
			messageKey = "resource-not-found";
		}
		else if (status ==
					Response.Status.METHOD_NOT_ALLOWED.getStatusCode()) {
			code = "METHOD_NOT_ALLOWED";
		}
		else if (status >= 500) {
			code = INTERNAL_ERROR;
			messageKey = "an-unexpected-error-occurred";
		}

		return Response.status(
			status
		).entity(
			_errorResponse(code, messageKey, resourceBundle)
		).type(
			MediaType.APPLICATION_JSON_TYPE
		).build();
	}

	private static final String _CAMPAIGN_EXCEPTION_PACKAGE =
		"io.github.valtairnascimento.campaignforms.exception";

	private static final int _UNPROCESSABLE_ENTITY = 422;

	private static final Log _log = LogFactoryUtil.getLog(
		ErrorResponseFactory.class);

	private static final Map<Class<?>, String> _conflictCodes =
		new HashMap<>();

	static {
		_conflictCodes.put(
			CampaignHasSubmissionsException.class,
			"CAMPAIGN_HAS_SUBMISSIONS");
		_conflictCodes.put(CampaignInactiveException.class, "CAMPAIGN_CLOSED");
		_conflictCodes.put(CampaignStatusException.class, "INVALID_STATUS");
		_conflictCodes.put(
			DuplicateCampaignExternalReferenceCodeException.class,
			"DUPLICATE_EXTERNAL_REFERENCE_CODE");
		_conflictCodes.put(
			DuplicateFieldKeyException.class, "DUPLICATE_FIELD_KEY");
		_conflictCodes.put(DuplicateSlugException.class, "DUPLICATE_SLUG");
		_conflictCodes.put(FormVersionLockedException.class, "VERSION_LOCKED");
		_conflictCodes.put(
			SubmissionLimitReachedException.class, "LIMIT_REACHED");
	}

}
