package io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
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
	description = "Form version. Only the draft (`DRAFT`) can be modified.",
	value = "FormVersion"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "FormVersion")
public class FormVersion implements Serializable {

	public static FormVersion toDTO(String json) {
		return ObjectMapperUtil.readValue(FormVersion.class, json);
	}

	public static FormVersion unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(FormVersion.class, json);
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean captchaEnabled;

	@Schema(
		description = "Whether the form asks for privacy consent before submitting."
	)
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

	@GraphQLField(
		description = "Whether the form asks for privacy consent before submitting."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean consentRequired;

	@Schema(description = "Consent text; when empty, the default text is used.")
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

	@GraphQLField(
		description = "Consent text; when empty, the default text is used."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String consentText;

	@Schema(
		description = "Link to the privacy policy shown next to the consent checkbox (https://... or a portal path). Optional."
	)
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

	@GraphQLField(
		description = "Link to the privacy policy shown next to the consent checkbox (https://... or a portal path). Optional."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String consentURL;

	@Schema(
		description = "Consent version; when empty, one is derived from the text and the link."
	)
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

	@GraphQLField(
		description = "Consent version; when empty, one is derived from the text and the link."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String consentVersion;

	@Schema
	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	@JsonIgnore
	public void setDatePublished(
		UnsafeSupplier<Date, Exception> datePublishedUnsafeSupplier) {

		try {
			datePublished = datePublishedUnsafeSupplier.get();
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
	protected Date datePublished;

	@Schema
	@Valid
	public FormField[] getFormFields() {
		return formFields;
	}

	public void setFormFields(FormField[] formFields) {
		this.formFields = formFields;
	}

	@JsonIgnore
	public void setFormFields(
		UnsafeSupplier<FormField[], Exception> formFieldsUnsafeSupplier) {

		try {
			formFields = formFieldsUnsafeSupplier.get();
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
	protected FormField[] formFields;

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

	@Schema
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
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
	protected Long id;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean requireAuthentication;

	@Schema
	@Valid
	public Map<String, Object> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, Object> settings) {
		this.settings = settings;
	}

	@JsonIgnore
	public void setSettings(
		UnsafeSupplier<Map<String, Object>, Exception> settingsUnsafeSupplier) {

		try {
			settings = settingsUnsafeSupplier.get();
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
	protected Map<String, Object> settings;

	@Schema
	@Valid
	public Status getStatus() {
		return status;
	}

	@JsonIgnore
	public String getStatusAsString() {
		if (status == null) {
			return null;
		}

		return status.toString();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<Status, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
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
	protected Status status;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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

		if (!(object instanceof FormVersion)) {
			return false;
		}

		FormVersion formVersion = (FormVersion)object;

		return Objects.equals(toString(), formVersion.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

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

		if (datePublished != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"datePublished\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(datePublished));

			sb.append("\"");
		}

		if (formFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formFields\": ");

			sb.append("[");

			for (int i = 0; i < formFields.length; i++) {
				sb.append(String.valueOf(formFields[i]));

				if ((i + 1) < formFields.length) {
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

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
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

		if (settings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"settings\": ");

			sb.append(_toJSON(settings));
		}

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(status);

			sb.append("\"");
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
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FormVersion",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("Status")
	public static enum Status {

		DRAFT("DRAFT"), PUBLISHED("PUBLISHED"), SUPERSEDED("SUPERSEDED");

		@JsonCreator
		public static Status create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Status status : values()) {
				if (Objects.equals(status.getValue(), value)) {
					return status;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Status(String value) {
			_value = value;
		}

		private final String _value;

	}

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