package io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0;

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
@GraphQLName(description = "Received submission.", value = "Submission")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Submission")
public class Submission implements Serializable {

	public static Submission toDTO(String json) {
		return ObjectMapperUtil.readValue(Submission.class, json);
	}

	public static Submission unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Submission.class, json);
	}

	@Schema
	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	@JsonIgnore
	public void setCampaignId(
		UnsafeSupplier<Long, Exception> campaignIdUnsafeSupplier) {

		try {
			campaignId = campaignIdUnsafeSupplier.get();
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
	protected Long campaignId;

	@Schema
	public Date getConsentDate() {
		return consentDate;
	}

	public void setConsentDate(Date consentDate) {
		this.consentDate = consentDate;
	}

	@JsonIgnore
	public void setConsentDate(
		UnsafeSupplier<Date, Exception> consentDateUnsafeSupplier) {

		try {
			consentDate = consentDateUnsafeSupplier.get();
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
	protected Date consentDate;

	@Schema
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

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String consentVersion;

	@Schema
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
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
	protected Date dateCreated;

	@Schema
	public Integer getFormVersion() {
		return formVersion;
	}

	public void setFormVersion(Integer formVersion) {
		this.formVersion = formVersion;
	}

	@JsonIgnore
	public void setFormVersion(
		UnsafeSupplier<Integer, Exception> formVersionUnsafeSupplier) {

		try {
			formVersion = formVersionUnsafeSupplier.get();
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
	protected Integer formVersion;

	@Schema
	public Long getFormVersionId() {
		return formVersionId;
	}

	public void setFormVersionId(Long formVersionId) {
		this.formVersionId = formVersionId;
	}

	@JsonIgnore
	public void setFormVersionId(
		UnsafeSupplier<Long, Exception> formVersionIdUnsafeSupplier) {

		try {
			formVersionId = formVersionIdUnsafeSupplier.get();
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
	protected Long formVersionId;

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
	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	@JsonIgnore
	public void setLanguageId(
		UnsafeSupplier<String, Exception> languageIdUnsafeSupplier) {

		try {
			languageId = languageIdUnsafeSupplier.get();
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
	protected String languageId;

	@Schema
	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	@JsonIgnore
	public void setReferrer(
		UnsafeSupplier<String, Exception> referrerUnsafeSupplier) {

		try {
			referrer = referrerUnsafeSupplier.get();
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
	protected String referrer;

	@Schema
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	@JsonIgnore
	public void setSourceUrl(
		UnsafeSupplier<String, Exception> sourceUrlUnsafeSupplier) {

		try {
			sourceUrl = sourceUrlUnsafeSupplier.get();
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
	protected String sourceUrl;

	@Schema
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<String, Exception> statusUnsafeSupplier) {

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
	protected String status;

	@Schema
	public String getUtmCampaign() {
		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	@JsonIgnore
	public void setUtmCampaign(
		UnsafeSupplier<String, Exception> utmCampaignUnsafeSupplier) {

		try {
			utmCampaign = utmCampaignUnsafeSupplier.get();
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
	protected String utmCampaign;

	@Schema
	public String getUtmContent() {
		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	@JsonIgnore
	public void setUtmContent(
		UnsafeSupplier<String, Exception> utmContentUnsafeSupplier) {

		try {
			utmContent = utmContentUnsafeSupplier.get();
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
	protected String utmContent;

	@Schema
	public String getUtmMedium() {
		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	@JsonIgnore
	public void setUtmMedium(
		UnsafeSupplier<String, Exception> utmMediumUnsafeSupplier) {

		try {
			utmMedium = utmMediumUnsafeSupplier.get();
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
	protected String utmMedium;

	@Schema
	public String getUtmSource() {
		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	@JsonIgnore
	public void setUtmSource(
		UnsafeSupplier<String, Exception> utmSourceUnsafeSupplier) {

		try {
			utmSource = utmSourceUnsafeSupplier.get();
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
	protected String utmSource;

	@Schema
	public String getUtmTerm() {
		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	@JsonIgnore
	public void setUtmTerm(
		UnsafeSupplier<String, Exception> utmTermUnsafeSupplier) {

		try {
			utmTerm = utmTermUnsafeSupplier.get();
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
	protected String utmTerm;

	@Schema(
		description = "Typed values by fieldKey: text, number, boolean, ISO-8601 date, or a list for multiple-choice fields."
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
		description = "Typed values by fieldKey: text, number, boolean, ISO-8601 date, or a list for multiple-choice fields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, Object> values;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Submission)) {
			return false;
		}

		Submission submission = (Submission)object;

		return Objects.equals(toString(), submission.toString());
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

		if (campaignId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaignId\": ");

			sb.append(campaignId);
		}

		if (consentDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consentDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(consentDate));

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

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		if (formVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formVersion\": ");

			sb.append(formVersion);
		}

		if (formVersionId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"formVersionId\": ");

			sb.append(formVersionId);
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (languageId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"languageId\": ");

			sb.append("\"");

			sb.append(_escape(languageId));

			sb.append("\"");
		}

		if (referrer != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"referrer\": ");

			sb.append("\"");

			sb.append(_escape(referrer));

			sb.append("\"");
		}

		if (sourceUrl != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sourceUrl\": ");

			sb.append("\"");

			sb.append(_escape(sourceUrl));

			sb.append("\"");
		}

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(_escape(status));

			sb.append("\"");
		}

		if (utmCampaign != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmCampaign\": ");

			sb.append("\"");

			sb.append(_escape(utmCampaign));

			sb.append("\"");
		}

		if (utmContent != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmContent\": ");

			sb.append("\"");

			sb.append(_escape(utmContent));

			sb.append("\"");
		}

		if (utmMedium != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmMedium\": ");

			sb.append("\"");

			sb.append(_escape(utmMedium));

			sb.append("\"");
		}

		if (utmSource != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmSource\": ");

			sb.append("\"");

			sb.append(_escape(utmSource));

			sb.append("\"");
		}

		if (utmTerm != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmTerm\": ");

			sb.append("\"");

			sb.append(_escape(utmTerm));

			sb.append("\"");
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
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Submission",
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