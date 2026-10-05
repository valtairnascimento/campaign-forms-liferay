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
@GraphQLName("FieldType")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "FieldType")
public class FieldType implements Serializable {

	public static FieldType toDTO(String json) {
		return ObjectMapperUtil.readValue(FieldType.class, json);
	}

	public static FieldType unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(FieldType.class, json);
	}

	@Schema
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
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
	protected String key;

	@Schema
	public Boolean getMultiValued() {
		return multiValued;
	}

	public void setMultiValued(Boolean multiValued) {
		this.multiValued = multiValued;
	}

	@JsonIgnore
	public void setMultiValued(
		UnsafeSupplier<Boolean, Exception> multiValuedUnsafeSupplier) {

		try {
			multiValued = multiValuedUnsafeSupplier.get();
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
	protected Boolean multiValued;

	@Schema
	public Boolean getOptionBased() {
		return optionBased;
	}

	public void setOptionBased(Boolean optionBased) {
		this.optionBased = optionBased;
	}

	@JsonIgnore
	public void setOptionBased(
		UnsafeSupplier<Boolean, Exception> optionBasedUnsafeSupplier) {

		try {
			optionBased = optionBasedUnsafeSupplier.get();
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
	protected Boolean optionBased;

	@Schema
	@Valid
	public ValueType getValueType() {
		return valueType;
	}

	@JsonIgnore
	public String getValueTypeAsString() {
		if (valueType == null) {
			return null;
		}

		return valueType.toString();
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	@JsonIgnore
	public void setValueType(
		UnsafeSupplier<ValueType, Exception> valueTypeUnsafeSupplier) {

		try {
			valueType = valueTypeUnsafeSupplier.get();
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
	protected ValueType valueType;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FieldType)) {
			return false;
		}

		FieldType fieldType = (FieldType)object;

		return Objects.equals(toString(), fieldType.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		if (multiValued != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"multiValued\": ");

			sb.append(multiValued);
		}

		if (optionBased != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"optionBased\": ");

			sb.append(optionBased);
		}

		if (valueType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"valueType\": ");

			sb.append("\"");

			sb.append(valueType);

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldType",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("ValueType")
	public static enum ValueType {

		STRING("STRING"), TEXT("TEXT"), NUMBER("NUMBER"), DATE("DATE"),
		DATETIME("DATETIME"), BOOLEAN("BOOLEAN");

		@JsonCreator
		public static ValueType create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (ValueType valueType : values()) {
				if (Objects.equals(valueType.getValue(), value)) {
					return valueType;
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

		private ValueType(String value) {
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