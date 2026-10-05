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
import javax.validation.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "What changes in the copy and what gets copied. Submissions are never copied.",
	value = "DuplicationRequest"
)
@JsonFilter("Liferay.Vulcan")
@Schema(
	description = "What changes in the copy and what gets copied. Submissions are never copied.",
	requiredProperties = {"slug"}
)
@XmlRootElement(name = "DuplicationRequest")
public class DuplicationRequest implements Serializable {

	public static DuplicationRequest toDTO(String json) {
		return ObjectMapperUtil.readValue(DuplicationRequest.class, json);
	}

	public static DuplicationRequest unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(DuplicationRequest.class, json);
	}

	@Schema(description = "Attributes to override (e.g. `city`).")
	@Valid
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@JsonIgnore
	public void setAttributes(
		UnsafeSupplier<Map<String, String>, Exception>
			attributesUnsafeSupplier) {

		try {
			attributes = attributesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Attributes to override (e.g. `city`).")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, String> attributes;

	@Schema
	public Boolean getCopyFields() {
		return copyFields;
	}

	public void setCopyFields(Boolean copyFields) {
		this.copyFields = copyFields;
	}

	@JsonIgnore
	public void setCopyFields(
		UnsafeSupplier<Boolean, Exception> copyFieldsUnsafeSupplier) {

		try {
			copyFields = copyFieldsUnsafeSupplier.get();
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
	protected Boolean copyFields;

	@Schema
	public Boolean getCopyIntegrationSecrets() {
		return copyIntegrationSecrets;
	}

	public void setCopyIntegrationSecrets(Boolean copyIntegrationSecrets) {
		this.copyIntegrationSecrets = copyIntegrationSecrets;
	}

	@JsonIgnore
	public void setCopyIntegrationSecrets(
		UnsafeSupplier<Boolean, Exception>
			copyIntegrationSecretsUnsafeSupplier) {

		try {
			copyIntegrationSecrets = copyIntegrationSecretsUnsafeSupplier.get();
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
	protected Boolean copyIntegrationSecrets;

	@Schema
	public Boolean getCopyIntegrations() {
		return copyIntegrations;
	}

	public void setCopyIntegrations(Boolean copyIntegrations) {
		this.copyIntegrations = copyIntegrations;
	}

	@JsonIgnore
	public void setCopyIntegrations(
		UnsafeSupplier<Boolean, Exception> copyIntegrationsUnsafeSupplier) {

		try {
			copyIntegrations = copyIntegrationsUnsafeSupplier.get();
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
	protected Boolean copyIntegrations;

	@Schema
	public Boolean getCopyNotifications() {
		return copyNotifications;
	}

	public void setCopyNotifications(Boolean copyNotifications) {
		this.copyNotifications = copyNotifications;
	}

	@JsonIgnore
	public void setCopyNotifications(
		UnsafeSupplier<Boolean, Exception> copyNotificationsUnsafeSupplier) {

		try {
			copyNotifications = copyNotificationsUnsafeSupplier.get();
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
	protected Boolean copyNotifications;

	@Schema
	public Boolean getCopyPage() {
		return copyPage;
	}

	public void setCopyPage(Boolean copyPage) {
		this.copyPage = copyPage;
	}

	@JsonIgnore
	public void setCopyPage(
		UnsafeSupplier<Boolean, Exception> copyPageUnsafeSupplier) {

		try {
			copyPage = copyPageUnsafeSupplier.get();
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
	protected Boolean copyPage;

	@Schema
	public Boolean getCopySeo() {
		return copySeo;
	}

	public void setCopySeo(Boolean copySeo) {
		this.copySeo = copySeo;
	}

	@JsonIgnore
	public void setCopySeo(
		UnsafeSupplier<Boolean, Exception> copySeoUnsafeSupplier) {

		try {
			copySeo = copySeoUnsafeSupplier.get();
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
	protected Boolean copySeo;

	@Schema
	public Boolean getCopySettings() {
		return copySettings;
	}

	public void setCopySettings(Boolean copySettings) {
		this.copySettings = copySettings;
	}

	@JsonIgnore
	public void setCopySettings(
		UnsafeSupplier<Boolean, Exception> copySettingsUnsafeSupplier) {

		try {
			copySettings = copySettingsUnsafeSupplier.get();
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
	protected Boolean copySettings;

	@Schema
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	public void setEndDate(
		UnsafeSupplier<Date, Exception> endDateUnsafeSupplier) {

		try {
			endDate = endDateUnsafeSupplier.get();
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
	protected Date endDate;

	@Schema
	public String getInstanceKey() {
		return instanceKey;
	}

	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}

	@JsonIgnore
	public void setInstanceKey(
		UnsafeSupplier<String, Exception> instanceKeyUnsafeSupplier) {

		try {
			instanceKey = instanceKeyUnsafeSupplier.get();
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
	protected String instanceKey;

	@Schema(description = "Empty = same name as the source.")
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

	@GraphQLField(description = "Empty = same name as the source.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	@Schema(
		description = "New recipients for the notifications that have fixed recipients."
	)
	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	@JsonIgnore
	public void setRecipients(
		UnsafeSupplier<String, Exception> recipientsUnsafeSupplier) {

		try {
			recipients = recipientsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "New recipients for the notifications that have fixed recipients."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String recipients;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String slug;

	@Schema
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonIgnore
	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
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
	protected Date startDate;

	@Schema(description = "New subject for the same notifications.")
	public String getSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(String subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	@JsonIgnore
	public void setSubjectTemplate(
		UnsafeSupplier<String, Exception> subjectTemplateUnsafeSupplier) {

		try {
			subjectTemplate = subjectTemplateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "New subject for the same notifications.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String subjectTemplate;

	@Schema(description = "Target site; empty = same as the source.")
	public Long getTargetSiteId() {
		return targetSiteId;
	}

	public void setTargetSiteId(Long targetSiteId) {
		this.targetSiteId = targetSiteId;
	}

	@JsonIgnore
	public void setTargetSiteId(
		UnsafeSupplier<Long, Exception> targetSiteIdUnsafeSupplier) {

		try {
			targetSiteId = targetSiteIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Target site; empty = same as the source.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long targetSiteId;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DuplicationRequest)) {
			return false;
		}

		DuplicationRequest duplicationRequest = (DuplicationRequest)object;

		return Objects.equals(toString(), duplicationRequest.toString());
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

		if (attributes != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"attributes\": ");

			sb.append(_toJSON(attributes));
		}

		if (copyFields != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copyFields\": ");

			sb.append(copyFields);
		}

		if (copyIntegrationSecrets != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copyIntegrationSecrets\": ");

			sb.append(copyIntegrationSecrets);
		}

		if (copyIntegrations != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copyIntegrations\": ");

			sb.append(copyIntegrations);
		}

		if (copyNotifications != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copyNotifications\": ");

			sb.append(copyNotifications);
		}

		if (copyPage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copyPage\": ");

			sb.append(copyPage);
		}

		if (copySeo != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copySeo\": ");

			sb.append(copySeo);
		}

		if (copySettings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"copySettings\": ");

			sb.append(copySettings);
		}

		if (endDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(endDate));

			sb.append("\"");
		}

		if (instanceKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"instanceKey\": ");

			sb.append("\"");

			sb.append(_escape(instanceKey));

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

		if (recipients != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recipients\": ");

			sb.append("\"");

			sb.append(_escape(recipients));

			sb.append("\"");
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

		if (startDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(startDate));

			sb.append("\"");
		}

		if (subjectTemplate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subjectTemplate\": ");

			sb.append("\"");

			sb.append(_escape(subjectTemplate));

			sb.append("\"");
		}

		if (targetSiteId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"targetSiteId\": ");

			sb.append(targetSiteId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DuplicationRequest",
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