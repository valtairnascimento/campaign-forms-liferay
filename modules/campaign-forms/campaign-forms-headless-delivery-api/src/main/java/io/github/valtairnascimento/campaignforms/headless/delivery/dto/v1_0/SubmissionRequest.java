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
	description = "A form submission. Keys in `values` that are not fields of the form are rejected (400).",
	value = "SubmissionRequest"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SubmissionRequest")
public class SubmissionRequest implements Serializable {

	public static SubmissionRequest toDTO(String json) {
		return ObjectMapperUtil.readValue(SubmissionRequest.class, json);
	}

	public static SubmissionRequest unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(SubmissionRequest.class, json);
	}

	@Schema
	public String getCaptchaToken() {
		return captchaToken;
	}

	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;
	}

	@JsonIgnore
	public void setCaptchaToken(
		UnsafeSupplier<String, Exception> captchaTokenUnsafeSupplier) {

		try {
			captchaToken = captchaTokenUnsafeSupplier.get();
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
	protected String captchaToken;

	@Schema
	@Valid
	public SubmissionConsent getConsent() {
		return consent;
	}

	public void setConsent(SubmissionConsent consent) {
		this.consent = consent;
	}

	@JsonIgnore
	public void setConsent(
		UnsafeSupplier<SubmissionConsent, Exception> consentUnsafeSupplier) {

		try {
			consent = consentUnsafeSupplier.get();
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
	protected SubmissionConsent consent;

	@Schema(description = "The `formToken` received with the form.")
	public String getFormToken() {
		return formToken;
	}

	public void setFormToken(String formToken) {
		this.formToken = formToken;
	}

	@JsonIgnore
	public void setFormToken(
		UnsafeSupplier<String, Exception> formTokenUnsafeSupplier) {

		try {
			formToken = formTokenUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The `formToken` received with the form.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String formToken;

	@Schema(description = "Value of the `honeypotFieldName` field.")
	public String getHoneypot() {
		return honeypot;
	}

	public void setHoneypot(String honeypot) {
		this.honeypot = honeypot;
	}

	@JsonIgnore
	public void setHoneypot(
		UnsafeSupplier<String, Exception> honeypotUnsafeSupplier) {

		try {
			honeypot = honeypotUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Value of the `honeypotFieldName` field.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String honeypot;

	@Schema
	@Valid
	public SubmissionSource getSource() {
		return source;
	}

	public void setSource(SubmissionSource source) {
		this.source = source;
	}

	@JsonIgnore
	public void setSource(
		UnsafeSupplier<SubmissionSource, Exception> sourceUnsafeSupplier) {

		try {
			source = sourceUnsafeSupplier.get();
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
	protected SubmissionSource source;

	@Schema(
		description = "Values by `fieldKey`: text, or a list of texts for `multiValued` fields.",
		example = "{email=maria@empresa.com.br, interesses=[inovacao, gestao], nome=Maria Silva}"
	)
	@Valid
	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	@JsonIgnore
	public void setValues(
		UnsafeSupplier<Map<String, Object>, Exception> valuesUnsafeSupplier) {

		try {
			values = valuesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Values by `fieldKey`: text, or a list of texts for `multiValued` fields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> values;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubmissionRequest)) {
			return false;
		}

		SubmissionRequest submissionRequest = (SubmissionRequest)object;

		return Objects.equals(toString(), submissionRequest.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (captchaToken != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"captchaToken\": ");

			sb.append("\"");

			sb.append(_escape(captchaToken));

			sb.append("\"");
		}

		if (consent != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consent\": ");

			sb.append(String.valueOf(consent));
		}

		if (formToken != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formToken\": ");

			sb.append("\"");

			sb.append(_escape(formToken));

			sb.append("\"");
		}

		if (honeypot != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"honeypot\": ");

			sb.append("\"");

			sb.append(_escape(honeypot));

			sb.append("\"");
		}

		if (source != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"source\": ");

			sb.append(String.valueOf(source));
		}

		if (values != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"values\": ");

			sb.append(_toJSON(values));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionRequest",
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