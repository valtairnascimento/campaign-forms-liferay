package io.github.valtairnascimento.campaignforms.field;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;

import java.util.List;
import java.util.TimeZone;

public interface FieldType {
	public static final String PROPERTY_KEY = "campaignforms.field.type";

	public String getKey();

	public FieldValueType getValueType();

	public default boolean isMultiValued() {
		return false;
	}

	public default boolean isOptionBased() {
		return false;
	}

	public default boolean usesDefaultValueWhenEmpty() {
		return false;
	}

	public List<FieldValue> toValues(
			FieldDefinition fieldDefinition, List<String> rawValues,
			TimeZone timeZone)
		throws FieldValueException;

	public void validateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException;
}
