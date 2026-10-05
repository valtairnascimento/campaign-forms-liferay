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
	description = "Published page: SEO and enabled blocks, in order.",
	value = "PublicPage"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "PublicPage")
public class PublicPage implements Serializable {

	public static PublicPage toDTO(String json) {
		return ObjectMapperUtil.readValue(PublicPage.class, json);
	}

	public static PublicPage unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(PublicPage.class, json);
	}

	@Schema
	@Valid
	public Block[] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[] blocks) {
		this.blocks = blocks;
	}

	@JsonIgnore
	public void setBlocks(
		UnsafeSupplier<Block[], Exception> blocksUnsafeSupplier) {

		try {
			blocks = blocksUnsafeSupplier.get();
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
	protected Block[] blocks;

	@Schema
	public String getCanonicalUrl() {
		return canonicalUrl;
	}

	public void setCanonicalUrl(String canonicalUrl) {
		this.canonicalUrl = canonicalUrl;
	}

	@JsonIgnore
	public void setCanonicalUrl(
		UnsafeSupplier<String, Exception> canonicalUrlUnsafeSupplier) {

		try {
			canonicalUrl = canonicalUrlUnsafeSupplier.get();
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
	protected String canonicalUrl;

	@Schema
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	@JsonIgnore
	public void setCssClass(
		UnsafeSupplier<String, Exception> cssClassUnsafeSupplier) {

		try {
			cssClass = cssClassUnsafeSupplier.get();
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
	protected String cssClass;

	@Schema
	public Boolean getNoIndex() {
		return noIndex;
	}

	public void setNoIndex(Boolean noIndex) {
		this.noIndex = noIndex;
	}

	@JsonIgnore
	public void setNoIndex(
		UnsafeSupplier<Boolean, Exception> noIndexUnsafeSupplier) {

		try {
			noIndex = noIndexUnsafeSupplier.get();
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
	protected Boolean noIndex;

	@Schema
	public String getOgDescription() {
		return ogDescription;
	}

	public void setOgDescription(String ogDescription) {
		this.ogDescription = ogDescription;
	}

	@JsonIgnore
	public void setOgDescription(
		UnsafeSupplier<String, Exception> ogDescriptionUnsafeSupplier) {

		try {
			ogDescription = ogDescriptionUnsafeSupplier.get();
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
	protected String ogDescription;

	@Schema
	public String getOgTitle() {
		return ogTitle;
	}

	public void setOgTitle(String ogTitle) {
		this.ogTitle = ogTitle;
	}

	@JsonIgnore
	public void setOgTitle(
		UnsafeSupplier<String, Exception> ogTitleUnsafeSupplier) {

		try {
			ogTitle = ogTitleUnsafeSupplier.get();
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
	protected String ogTitle;

	@Schema
	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	@JsonIgnore
	public void setSeoDescription(
		UnsafeSupplier<String, Exception> seoDescriptionUnsafeSupplier) {

		try {
			seoDescription = seoDescriptionUnsafeSupplier.get();
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
	protected String seoDescription;

	@Schema
	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	@JsonIgnore
	public void setSeoTitle(
		UnsafeSupplier<String, Exception> seoTitleUnsafeSupplier) {

		try {
			seoTitle = seoTitleUnsafeSupplier.get();
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
	protected String seoTitle;

	@Schema
	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	@JsonIgnore
	public void setTemplateKey(
		UnsafeSupplier<String, Exception> templateKeyUnsafeSupplier) {

		try {
			templateKey = templateKeyUnsafeSupplier.get();
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
	protected String templateKey;

	@Schema
	public String getThemeKey() {
		return themeKey;
	}

	public void setThemeKey(String themeKey) {
		this.themeKey = themeKey;
	}

	@JsonIgnore
	public void setThemeKey(
		UnsafeSupplier<String, Exception> themeKeyUnsafeSupplier) {

		try {
			themeKey = themeKeyUnsafeSupplier.get();
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
	protected String themeKey;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PublicPage)) {
			return false;
		}

		PublicPage publicPage = (PublicPage)object;

		return Objects.equals(toString(), publicPage.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (blocks != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"blocks\": ");

			sb.append("[");

			for (int i = 0; i < blocks.length; i++) {
				sb.append(String.valueOf(blocks[i]));

				if ((i + 1) < blocks.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (canonicalUrl != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"canonicalUrl\": ");

			sb.append("\"");

			sb.append(_escape(canonicalUrl));

			sb.append("\"");
		}

		if (cssClass != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cssClass\": ");

			sb.append("\"");

			sb.append(_escape(cssClass));

			sb.append("\"");
		}

		if (noIndex != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"noIndex\": ");

			sb.append(noIndex);
		}

		if (ogDescription != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ogDescription\": ");

			sb.append("\"");

			sb.append(_escape(ogDescription));

			sb.append("\"");
		}

		if (ogTitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ogTitle\": ");

			sb.append("\"");

			sb.append(_escape(ogTitle));

			sb.append("\"");
		}

		if (seoDescription != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"seoDescription\": ");

			sb.append("\"");

			sb.append(_escape(seoDescription));

			sb.append("\"");
		}

		if (seoTitle != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"seoTitle\": ");

			sb.append("\"");

			sb.append(_escape(seoTitle));

			sb.append("\"");
		}

		if (templateKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"templateKey\": ");

			sb.append("\"");

			sb.append(_escape(templateKey));

			sb.append("\"");
		}

		if (themeKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"themeKey\": ");

			sb.append("\"");

			sb.append(_escape(themeKey));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicPage",
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