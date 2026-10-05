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
	description = "Read-only URL for Power BI (Get Data › Web). The token is shown only on creation and when a new one is generated; after that, only its last characters (tokenHint).",
	value = "DataFeed"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "DataFeed")
public class DataFeed implements Serializable {

	public static DataFeed toDTO(String json) {
		return ObjectMapperUtil.readValue(DataFeed.class, json);
	}

	public static DataFeed unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(DataFeed.class, json);
	}

	@Schema
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonIgnore
	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
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
	protected Boolean active;

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
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonIgnore
	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
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
	protected Date expirationDate;

	@Schema
	@Valid
	public Format getFormat() {
		return format;
	}

	@JsonIgnore
	public String getFormatAsString() {
		if (format == null) {
			return null;
		}

		return format.toString();
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	@JsonIgnore
	public void setFormat(
		UnsafeSupplier<Format, Exception> formatUnsafeSupplier) {

		try {
			format = formatUnsafeSupplier.get();
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
	protected Format format;

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
	public Boolean getIncludeMetadata() {
		return includeMetadata;
	}

	public void setIncludeMetadata(Boolean includeMetadata) {
		this.includeMetadata = includeMetadata;
	}

	@JsonIgnore
	public void setIncludeMetadata(
		UnsafeSupplier<Boolean, Exception> includeMetadataUnsafeSupplier) {

		try {
			includeMetadata = includeMetadataUnsafeSupplier.get();
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
	protected Boolean includeMetadata;

	@Schema
	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	@JsonIgnore
	public void setLastAccessDate(
		UnsafeSupplier<Date, Exception> lastAccessDateUnsafeSupplier) {

		try {
			lastAccessDate = lastAccessDateUnsafeSupplier.get();
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
	protected Date lastAccessDate;

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
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	@Schema(
		description = "CAMPAIGN: this campaign only. SERIES: all campaigns in the series (seriesKey) on the same site."
	)
	@Valid
	public Scope getScope() {
		return scope;
	}

	@JsonIgnore
	public String getScopeAsString() {
		if (scope == null) {
			return null;
		}

		return scope.toString();
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@JsonIgnore
	public void setScope(UnsafeSupplier<Scope, Exception> scopeUnsafeSupplier) {
		try {
			scope = scopeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "CAMPAIGN: this campaign only. SERIES: all campaigns in the series (seriesKey) on the same site."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Scope scope;

	@Schema
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

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String seriesKey;

	@Schema(description = "Only on creation and when a new token is generated.")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonIgnore
	public void setToken(
		UnsafeSupplier<String, Exception> tokenUnsafeSupplier) {

		try {
			token = tokenUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Only on creation and when a new token is generated."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String token;

	@Schema
	public String getTokenHint() {
		return tokenHint;
	}

	public void setTokenHint(String tokenHint) {
		this.tokenHint = tokenHint;
	}

	@JsonIgnore
	public void setTokenHint(
		UnsafeSupplier<String, Exception> tokenHintUnsafeSupplier) {

		try {
			tokenHint = tokenHintUnsafeSupplier.get();
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
	protected String tokenHint;

	@Schema(
		description = "URL for Power BI; includes the token only on creation and when a new one is generated."
	)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonIgnore
	public void setUrl(UnsafeSupplier<String, Exception> urlUnsafeSupplier) {
		try {
			url = urlUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "URL for Power BI; includes the token only on creation and when a new one is generated."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String url;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataFeed)) {
			return false;
		}

		DataFeed dataFeed = (DataFeed)object;

		return Objects.equals(toString(), dataFeed.toString());
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

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
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

		if (expirationDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(expirationDate));

			sb.append("\"");
		}

		if (format != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"format\": ");

			sb.append("\"");

			sb.append(format);

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (includeMetadata != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"includeMetadata\": ");

			sb.append(includeMetadata);
		}

		if (lastAccessDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastAccessDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(lastAccessDate));

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

		if (scope != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"scope\": ");

			sb.append("\"");

			sb.append(scope);

			sb.append("\"");
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

		if (token != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"token\": ");

			sb.append("\"");

			sb.append(_escape(token));

			sb.append("\"");
		}

		if (tokenHint != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tokenHint\": ");

			sb.append("\"");

			sb.append(_escape(tokenHint));

			sb.append("\"");
		}

		if (url != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"url\": ");

			sb.append("\"");

			sb.append(_escape(url));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DataFeed",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("Format")
	public static enum Format {

		CSV("CSV"), JSON("JSON");

		@JsonCreator
		public static Format create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Format format : values()) {
				if (Objects.equals(format.getValue(), value)) {
					return format;
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

		private Format(String value) {
			_value = value;
		}

		private final String _value;

	}

	@GraphQLName("Scope")
	public static enum Scope {

		CAMPAIGN("CAMPAIGN"), SERIES("SERIES");

		@JsonCreator
		public static Scope create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Scope scope : values()) {
				if (Objects.equals(scope.getValue(), value)) {
					return scope;
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

		private Scope(String value) {
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