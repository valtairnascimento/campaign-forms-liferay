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
	description = "Published form version. `formToken` and `honeypotFieldName` must be sent with the submission.",
	value = "PublicForm"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PublicForm")
public class PublicForm implements Serializable {

	public static PublicForm toDTO(String json) {
		return ObjectMapperUtil.readValue(PublicForm.class, json);
	}

	public static PublicForm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(PublicForm.class, json);
	}

	@Schema
	public Boolean getCaptchaEnabled() {
		return captchaEnabled;
	}

	public void setCaptchaEnabled(Boolean captchaEnabled) {
		this.captchaEnabled = captchaEnabled;
	}

	@JsonIgnore
	public void setCaptchaEnabled(
		UnsafeSupplier<Boolean, Exception> captchaEnabledUnsafeSupplier) {

		try {
			captchaEnabled = captchaEnabledUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean captchaEnabled;

	@Schema
	public Boolean getConsentRequired() {
		return consentRequired;
	}

	public void setConsentRequired(Boolean consentRequired) {
		this.consentRequired = consentRequired;
	}

	@JsonIgnore
	public void setConsentRequired(
		UnsafeSupplier<Boolean, Exception> consentRequiredUnsafeSupplier) {

		try {
			consentRequired = consentRequiredUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean consentRequired;

	@Schema
	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
	}

	@JsonIgnore
	public void setConsentText(
		UnsafeSupplier<String, Exception> consentTextUnsafeSupplier) {

		try {
			consentText = consentTextUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String consentText;

	@Schema(description = "Link to the privacy policy.")
	public String getConsentURL() {
		return consentURL;
	}

	public void setConsentURL(String consentURL) {
		this.consentURL = consentURL;
	}

	@JsonIgnore
	public void setConsentURL(
		UnsafeSupplier<String, Exception> consentURLUnsafeSupplier) {

		try {
			consentURL = consentURLUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Link to the privacy policy.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String consentURL;

	@Schema(description = "Consent version to send in `consent.version`.")
	public String getConsentVersion() {
		return consentVersion;
	}

	public void setConsentVersion(String consentVersion) {
		this.consentVersion = consentVersion;
	}

	@JsonIgnore
	public void setConsentVersion(
		UnsafeSupplier<String, Exception> consentVersionUnsafeSupplier) {

		try {
			consentVersion = consentVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Consent version to send in `consent.version`.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String consentVersion;

	@Schema
	@Valid
	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	@JsonIgnore
	public void setFields(
		UnsafeSupplier<Field[], Exception> fieldsUnsafeSupplier) {

		try {
			fields = fieldsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Field[] fields;

	@Schema
	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	@JsonIgnore
	public void setFormId(
		UnsafeSupplier<Long, Exception> formIdUnsafeSupplier) {

		try {
			formId = formIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long formId;

	@Schema(
		description = "Signed token, valid from 2 seconds to 24 hours after it is issued."
	)
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

	@GraphQLField(
		description = "Signed token, valid from 2 seconds to 24 hours after it is issued."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String formToken;

	@Schema(
		description = "Name of the hidden anti-spam field. Its value goes in `honeypot` in the submission and must be empty."
	)
	public String getHoneypotFieldName() {
		return honeypotFieldName;
	}

	public void setHoneypotFieldName(String honeypotFieldName) {
		this.honeypotFieldName = honeypotFieldName;
	}

	@JsonIgnore
	public void setHoneypotFieldName(
		UnsafeSupplier<String, Exception> honeypotFieldNameUnsafeSupplier) {

		try {
			honeypotFieldName = honeypotFieldNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Name of the hidden anti-spam field. Its value goes in `honeypot` in the submission and must be empty."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String honeypotFieldName;

	@Schema
	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@JsonIgnore
	public void setRedirectUrl(
		UnsafeSupplier<String, Exception> redirectUrlUnsafeSupplier) {

		try {
			redirectUrl = redirectUrlUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String redirectUrl;

	@Schema
	public Boolean getRequireAuthentication() {
		return requireAuthentication;
	}

	public void setRequireAuthentication(Boolean requireAuthentication) {
		this.requireAuthentication = requireAuthentication;
	}

	@JsonIgnore
	public void setRequireAuthentication(
		UnsafeSupplier<Boolean, Exception>
			requireAuthenticationUnsafeSupplier) {

		try {
			requireAuthentication = requireAuthenticationUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean requireAuthentication;

	@Schema
	public String getSubmitLabel() {
		return submitLabel;
	}

	public void setSubmitLabel(String submitLabel) {
		this.submitLabel = submitLabel;
	}

	@JsonIgnore
	public void setSubmitLabel(
		UnsafeSupplier<String, Exception> submitLabelUnsafeSupplier) {

		try {
			submitLabel = submitLabelUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String submitLabel;

	@Schema
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@JsonIgnore
	public void setSuccessMessage(
		UnsafeSupplier<String, Exception> successMessageUnsafeSupplier) {

		try {
			successMessage = successMessageUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String successMessage;

	@Schema
	public String getSuccessTitle() {
		return successTitle;
	}

	public void setSuccessTitle(String successTitle) {
		this.successTitle = successTitle;
	}

	@JsonIgnore
	public void setSuccessTitle(
		UnsafeSupplier<String, Exception> successTitleUnsafeSupplier) {

		try {
			successTitle = successTitleUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String successTitle;

	@Schema
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@JsonIgnore
	public void setVersion(
		UnsafeSupplier<Integer, Exception> versionUnsafeSupplier) {

		try {
			version = versionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer version;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PublicForm)) {
			return false;
		}

		PublicForm publicForm = (PublicForm)object;

		return Objects.equals(toString(), publicForm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (captchaEnabled != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"captchaEnabled\": ");

			sb.append(captchaEnabled);
		}

		if (consentRequired != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consentRequired\": ");

			sb.append(consentRequired);
		}

		if (consentText != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consentText\": ");

			sb.append("\"");

			sb.append(_escape(consentText));

			sb.append("\"");
		}

		if (consentURL != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consentURL\": ");

			sb.append("\"");

			sb.append(_escape(consentURL));

			sb.append("\"");
		}

		if (consentVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consentVersion\": ");

			sb.append("\"");

			sb.append(_escape(consentVersion));

			sb.append("\"");
		}

		if (fields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fields\": ");

			sb.append("[");

			for (int i = 0; i < fields.length; i++) {
				sb.append(String.valueOf(fields[i]));

				if ((i + 1) < fields.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (formId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formId\": ");

			sb.append(formId);
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

		if (honeypotFieldName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"honeypotFieldName\": ");

			sb.append("\"");

			sb.append(_escape(honeypotFieldName));

			sb.append("\"");
		}

		if (redirectUrl != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"redirectUrl\": ");

			sb.append("\"");

			sb.append(_escape(redirectUrl));

			sb.append("\"");
		}

		if (requireAuthentication != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requireAuthentication\": ");

			sb.append(requireAuthentication);
		}

		if (submitLabel != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"submitLabel\": ");

			sb.append("\"");

			sb.append(_escape(submitLabel));

			sb.append("\"");
		}

		if (successMessage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"successMessage\": ");

			sb.append("\"");

			sb.append(_escape(successMessage));

			sb.append("\"");
		}

		if (successTitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"successTitle\": ");

			sb.append("\"");

			sb.append(_escape(successTitle));

			sb.append("\"");
		}

		if (version != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"version\": ");

			sb.append(version);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicForm",
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