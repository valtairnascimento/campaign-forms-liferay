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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Generated("")
@GraphQLName(description = "Campaign.", value = "Campaign")
@JsonFilter("Liferay.Vulcan")
@Schema(description = "Campaign.", requiredProperties = {"name", "slug"})
@XmlRootElement(name = "Campaign")
public class Campaign implements Serializable {

	public static Campaign toDTO(String json) {
		return ObjectMapperUtil.readValue(Campaign.class, json);
	}

	public static Campaign unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Campaign.class, json);
	}

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
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@JsonIgnore
	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		try {
			dateModified = dateModifiedUnsafeSupplier.get();
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
	protected Date dateModified;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String defaultLanguageId;

	@Schema
	@Size(max = 4000)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "End of the submission period.")
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

	@GraphQLField(description = "End of the submission period.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date endDate;

	@Schema
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonIgnore
	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
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
	protected String externalReferenceCode;

	@Schema(
		description = "True when the form or the page has unpublished changes."
	)
	public Boolean getHasUnpublishedChanges() {
		return hasUnpublishedChanges;
	}

	public void setHasUnpublishedChanges(Boolean hasUnpublishedChanges) {
		this.hasUnpublishedChanges = hasUnpublishedChanges;
	}

	@JsonIgnore
	public void setHasUnpublishedChanges(
		UnsafeSupplier<Boolean, Exception>
			hasUnpublishedChangesUnsafeSupplier) {

		try {
			hasUnpublishedChanges = hasUnpublishedChangesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "True when the form or the page has unpublished changes."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean hasUnpublishedChanges;

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

	@Schema(description = "Instance within the series (e.g. `londrina`).")
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

	@GraphQLField(description = "Instance within the series (e.g. `londrina`).")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String instanceKey;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Mode mode;

	@Schema
	@Size(max = 150)
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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String name;

	@Schema(
		description = "What the current user can do with the campaign, per action: `UPDATE`, `DELETE`, `PUBLISH`, `VIEW_SUBMISSIONS`, `DELETE_SUBMISSIONS`, `EXPORT_SUBMISSIONS`, `MANAGE_NOTIFICATIONS`, `MANAGE_INTEGRATIONS`. Only a hint for the UI: the server always checks again."
	)
	@Valid
	public Map<String, Object> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Object> permissions) {
		this.permissions = permissions;
	}

	@JsonIgnore
	public void setPermissions(
		UnsafeSupplier<Map<String, Object>, Exception>
			permissionsUnsafeSupplier) {

		try {
			permissions = permissionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "What the current user can do with the campaign, per action: `UPDATE`, `DELETE`, `PUBLISH`, `VIEW_SUBMISSIONS`, `DELETE_SUBMISSIONS`, `EXPORT_SUBMISSIONS`, `MANAGE_NOTIFICATIONS`, `MANAGE_INTEGRATIONS`. Only a hint for the UI: the server always checks again."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Map<String, Object> permissions;

	@Schema
	public Integer getRetentionDays() {
		return retentionDays;
	}

	public void setRetentionDays(Integer retentionDays) {
		this.retentionDays = retentionDays;
	}

	@JsonIgnore
	public void setRetentionDays(
		UnsafeSupplier<Integer, Exception> retentionDaysUnsafeSupplier) {

		try {
			retentionDays = retentionDaysUnsafeSupplier.get();
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
	protected Integer retentionDays;

	@Schema(
		description = "Groups campaigns of the same series in BI reports (e.g. `hr-summit`)."
	)
	public String getSeriesKey() {
		return seriesKey;
	}

	public void setSeriesKey(String seriesKey) {
		this.seriesKey = seriesKey;
	}

	@JsonIgnore
	public void setSeriesKey(
		UnsafeSupplier<String, Exception> seriesKeyUnsafeSupplier) {

		try {
			seriesKey = seriesKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Groups campaigns of the same series in BI reports (e.g. `hr-summit`)."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String seriesKey;

	@Schema(
		description = "Free-form settings: `attributes` (e.g. `city`, used in `${campaign.city}`), `closedMessage`, `limitReachedMessage`."
	)
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

	@GraphQLField(
		description = "Free-form settings: `attributes` (e.g. `city`, used in `${campaign.city}`), `closedMessage`, `limitReachedMessage`."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, Object> settings;

	@Schema
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	@JsonIgnore
	public void setSiteId(
		UnsafeSupplier<Long, Exception> siteIdUnsafeSupplier) {

		try {
			siteId = siteIdUnsafeSupplier.get();
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
	protected Long siteId;

	@Schema(description = "Unique within the site; `^[a-z0-9]+(-[a-z0-9]+)*$`.")
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

	@GraphQLField(
		description = "Unique within the site; `^[a-z0-9]+(-[a-z0-9]+)*$`."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String slug;

	@Schema(description = "Source campaign, when this one was duplicated.")
	public Long getSourceCampaignId() {
		return sourceCampaignId;
	}

	public void setSourceCampaignId(Long sourceCampaignId) {
		this.sourceCampaignId = sourceCampaignId;
	}

	@JsonIgnore
	public void setSourceCampaignId(
		UnsafeSupplier<Long, Exception> sourceCampaignIdUnsafeSupplier) {

		try {
			sourceCampaignId = sourceCampaignIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Source campaign, when this one was duplicated."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long sourceCampaignId;

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

	@Schema(description = "0 = no limit.")
	public Integer getSubmissionLimit() {
		return submissionLimit;
	}

	public void setSubmissionLimit(Integer submissionLimit) {
		this.submissionLimit = submissionLimit;
	}

	@JsonIgnore
	public void setSubmissionLimit(
		UnsafeSupplier<Integer, Exception> submissionLimitUnsafeSupplier) {

		try {
			submissionLimit = submissionLimitUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "0 = no limit.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer submissionLimit;

	@Schema
	public Long getSubmissionsCount() {
		return submissionsCount;
	}

	public void setSubmissionsCount(Long submissionsCount) {
		this.submissionsCount = submissionsCount;
	}

	@JsonIgnore
	public void setSubmissionsCount(
		UnsafeSupplier<Long, Exception> submissionsCountUnsafeSupplier) {

		try {
			submissionsCount = submissionsCountUnsafeSupplier.get();
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
	protected Long submissionsCount;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Campaign)) {
			return false;
		}

		Campaign campaign = (Campaign)object;

		return Objects.equals(toString(), campaign.toString());
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

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		if (dateModified != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateModified));

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

		if (endDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(endDate));

			sb.append("\"");
		}

		if (externalReferenceCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(externalReferenceCode));

			sb.append("\"");
		}

		if (hasUnpublishedChanges != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hasUnpublishedChanges\": ");

			sb.append(hasUnpublishedChanges);
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
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

		if (permissions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(_toJSON(permissions));
		}

		if (retentionDays != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"retentionDays\": ");

			sb.append(retentionDays);
		}

		if (seriesKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"seriesKey\": ");

			sb.append("\"");

			sb.append(_escape(seriesKey));

			sb.append("\"");
		}

		if (settings != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"settings\": ");

			sb.append(_toJSON(settings));
		}

		if (siteId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(siteId);
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

		if (sourceCampaignId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sourceCampaignId\": ");

			sb.append(sourceCampaignId);
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

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(status);

			sb.append("\"");
		}

		if (submissionLimit != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"submissionLimit\": ");

			sb.append(submissionLimit);
		}

		if (submissionsCount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"submissionsCount\": ");

			sb.append(submissionsCount);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Campaign",
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

	@GraphQLName("Status")
	public static enum Status {

		DRAFT("DRAFT"), PUBLISHED("PUBLISHED"), ARCHIVED("ARCHIVED");

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