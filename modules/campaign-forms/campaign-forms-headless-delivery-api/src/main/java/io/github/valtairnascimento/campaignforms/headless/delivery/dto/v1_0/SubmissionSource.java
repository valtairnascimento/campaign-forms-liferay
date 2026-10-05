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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Submission source. URL query strings are not stored.",
	value = "SubmissionSource"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SubmissionSource")
public class SubmissionSource implements Serializable {

	public static SubmissionSource toDTO(String json) {
		return ObjectMapperUtil.readValue(SubmissionSource.class, json);
	}

	public static SubmissionSource unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(SubmissionSource.class, json);
	}

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String sourceUrl;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String utmTerm;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubmissionSource)) {
			return false;
		}

		SubmissionSource submissionSource = (SubmissionSource)object;

		return Objects.equals(toString(), submissionSource.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

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

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionSource",
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