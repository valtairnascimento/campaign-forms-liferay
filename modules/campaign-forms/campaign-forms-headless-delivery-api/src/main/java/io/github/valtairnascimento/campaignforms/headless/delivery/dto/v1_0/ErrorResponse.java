package io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Error format for all operations. `errorId` appears only on unexpected errors (500) and matches the ID written to the log.",
	value = "ErrorResponse"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ErrorResponse")
public class ErrorResponse implements Serializable {

	public static ErrorResponse toDTO(String json) {
		return ObjectMapperUtil.readValue(ErrorResponse.class, json);
	}

	public static ErrorResponse unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(ErrorResponse.class, json);
	}

	@Schema(
		description = "Stable code: CAMPAIGN_NOT_FOUND, CAMPAIGN_CLOSED, LIMIT_REACHED, FORM_VALIDATION_ERROR, UNKNOWN_FIELD, INVALID_FORM_TOKEN, INVALID_REQUEST, AUTHENTICATION_REQUIRED, INTERNAL_ERROR...",
		example = "FORM_VALIDATION_ERROR"
	)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public void setCode(UnsafeSupplier<String, Exception> codeUnsafeSupplier) {
		try {
			code = codeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Stable code: CAMPAIGN_NOT_FOUND, CAMPAIGN_CLOSED, LIMIT_REACHED, FORM_VALIDATION_ERROR, UNKNOWN_FIELD, INVALID_FORM_TOKEN, INVALID_REQUEST, AUTHENTICATION_REQUIRED, INTERNAL_ERROR..."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String code;

	@Schema
	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	@JsonIgnore
	public void setErrorId(
		UnsafeSupplier<String, Exception> errorIdUnsafeSupplier) {

		try {
			errorId = errorIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String errorId;

	@Schema(
		description = "One item per problem: `field` (fieldKey, `consent` or `captcha`), `code` (REQUIRED, INVALID_EMAIL, INVALID_OPTION, TOO_LONG...) and `message`."
	)
	@Valid
	public Map[] getErrors() {
		return errors;
	}

	public void setErrors(Map[] errors) {
		this.errors = errors;
	}

	@JsonIgnore
	public void setErrors(
		UnsafeSupplier<Map[], Exception> errorsUnsafeSupplier) {

		try {
			errors = errorsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "One item per problem: `field` (fieldKey, `consent` or `captcha`), `code` (REQUIRED, INVALID_EMAIL, INVALID_OPTION, TOO_LONG...) and `message`."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map[] errors;

	@Schema(description = "Message in the language of the request.")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public void setMessage(
		UnsafeSupplier<String, Exception> messageUnsafeSupplier) {

		try {
			message = messageUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Message in the language of the request.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String message;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ErrorResponse)) {
			return false;
		}

		ErrorResponse errorResponse = (ErrorResponse)object;

		return Objects.equals(toString(), errorResponse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (code != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"code\": ");

			sb.append("\"");

			sb.append(_escape(code));

			sb.append("\"");
		}

		if (errorId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"errorId\": ");

			sb.append("\"");

			sb.append(_escape(errorId));

			sb.append("\"");
		}

		if (errors != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"errors\": ");

			sb.append("[");

			for (int i = 0; i < errors.length; i++) {
				sb.append(errors[i]);

				if ((i + 1) < errors.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (message != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"message\": ");

			sb.append("\"");

			sb.append(_escape(message));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.ErrorResponse",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}