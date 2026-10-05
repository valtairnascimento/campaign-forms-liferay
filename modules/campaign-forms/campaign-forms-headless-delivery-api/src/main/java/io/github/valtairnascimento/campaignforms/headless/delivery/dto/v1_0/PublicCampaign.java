package io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0;

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
	description = "Published campaign with everything needed to render it in a single request. Does not include recipients, integrations, internal settings or user IDs.",
	value = "PublicCampaign"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PublicCampaign")
public class PublicCampaign implements Serializable {

	public static PublicCampaign toDTO(String json) {
		return ObjectMapperUtil.readValue(PublicCampaign.class, json);
	}

	public static PublicCampaign unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(PublicCampaign.class, json);
	}

	@Schema(
		description = "False when the campaign is archived, outside its submission period or full."
	)
	public Boolean getAcceptingSubmissions() {
		return acceptingSubmissions;
	}

	public void setAcceptingSubmissions(Boolean acceptingSubmissions) {
		this.acceptingSubmissions = acceptingSubmissions;
	}

	@JsonIgnore
	public void setAcceptingSubmissions(
		UnsafeSupplier<Boolean, Exception> acceptingSubmissionsUnsafeSupplier) {

		try {
			acceptingSubmissions = acceptingSubmissionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "False when the campaign is archived, outside its submission period or full."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean acceptingSubmissions;

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

	@Schema(
		description = "Message to display when `acceptingSubmissions` is false."
	)
	public String getClosedMessage() {
		return closedMessage;
	}

	public void setClosedMessage(String closedMessage) {
		this.closedMessage = closedMessage;
	}

	@JsonIgnore
	public void setClosedMessage(
		UnsafeSupplier<String, Exception> closedMessageUnsafeSupplier) {

		try {
			closedMessage = closedMessageUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Message to display when `acceptingSubmissions` is false."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String closedMessage;

	@Schema
	public String getDefaultLanguageId() {
		return defaultLanguageId;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}

	@JsonIgnore
	public void setDefaultLanguageId(
		UnsafeSupplier<String, Exception> defaultLanguageIdUnsafeSupplier) {

		try {
			defaultLanguageId = defaultLanguageIdUnsafeSupplier.get();
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
	protected String defaultLanguageId;

	@Schema
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
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
	protected String description;

	@Schema
	@Valid
	public PublicForm getForm() {
		return form;
	}

	public void setForm(PublicForm form) {
		this.form = form;
	}

	@JsonIgnore
	public void setForm(
		UnsafeSupplier<PublicForm, Exception> formUnsafeSupplier) {

		try {
			form = formUnsafeSupplier.get();
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
	protected PublicForm form;

	@Schema
	@Valid
	public Mode getMode() {
		return mode;
	}

	@JsonIgnore
	public String getModeAsString() {
		if (mode == null) {
			return null;
		}

		return mode.toString();
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@JsonIgnore
	public void setMode(UnsafeSupplier<Mode, Exception> modeUnsafeSupplier) {
		try {
			mode = modeUnsafeSupplier.get();
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
	protected Mode mode;

	@Schema
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
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
	protected String name;

	@Schema
	@Valid
	public PublicPage getPage() {
		return page;
	}

	public void setPage(PublicPage page) {
		this.page = page;
	}

	@JsonIgnore
	public void setPage(
		UnsafeSupplier<PublicPage, Exception> pageUnsafeSupplier) {

		try {
			page = pageUnsafeSupplier.get();
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
	protected PublicPage page;

	@Schema
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@JsonIgnore
	public void setSlug(UnsafeSupplier<String, Exception> slugUnsafeSupplier) {
		try {
			slug = slugUnsafeSupplier.get();
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
	protected String slug;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PublicCampaign)) {
			return false;
		}

		PublicCampaign publicCampaign = (PublicCampaign)object;

		return Objects.equals(toString(), publicCampaign.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (acceptingSubmissions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"acceptingSubmissions\": ");

			sb.append(acceptingSubmissions);
		}

		if (campaignId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaignId\": ");

			sb.append(campaignId);
		}

		if (closedMessage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"closedMessage\": ");

			sb.append("\"");

			sb.append(_escape(closedMessage));

			sb.append("\"");
		}

		if (defaultLanguageId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"defaultLanguageId\": ");

			sb.append("\"");

			sb.append(_escape(defaultLanguageId));

			sb.append("\"");
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (form != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"form\": ");

			sb.append(String.valueOf(form));
		}

		if (mode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mode\": ");

			sb.append("\"");

			sb.append(mode);

			sb.append("\"");
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (page != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"page\": ");

			sb.append(String.valueOf(page));
		}

		if (slug != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"slug\": ");

			sb.append("\"");

			sb.append(_escape(slug));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicCampaign",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("Mode")
	public static enum Mode {

		QUICK("QUICK"), CUSTOM_PAGE("CUSTOM_PAGE");

		@JsonCreator
		public static Mode create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Mode mode : values()) {
				if (Objects.equals(mode.getValue(), value)) {
					return mode;
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

		private Mode(String value) {
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