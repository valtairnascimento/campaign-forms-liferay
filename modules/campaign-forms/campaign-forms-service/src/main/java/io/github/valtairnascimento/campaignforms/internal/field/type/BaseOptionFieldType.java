package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public abstract class BaseOptionFieldType extends BaseFieldType {
	@Override
	public FieldValueType getValueType() {
		return FieldValueType.STRING;
	}

	@Override
	public boolean isOptionBased() {
		return true;
	}

	@Override
	public List<FieldValue> toValues(
			FieldDefinition fieldDefinition, List<String> rawValues,
			TimeZone timeZone)
		throws FieldValueException {
		Set<String> selectedValues = new LinkedHashSet<>(rawValues);

		if (!isMultiValued() && (selectedValues.size() > 1)) {
			throw new FieldValueException(
				FieldError.INVALID_VALUE, "field-error-invalid-value");
		}

		List<String> optionValues = fieldDefinition.getOptionValues();

		for (String selectedValue : selectedValues) {
			if (!optionValues.contains(selectedValue)) {
				throw new FieldValueException(
					FieldError.INVALID_OPTION, "field-error-invalid-option");
			}
		}

		if (isMultiValued()) {
			JSONObject validationJSONObject = fieldDefinition.getValidation();

			int minSelected = validationJSONObject.getInt("minSelected", 0);

			if (selectedValues.size() < minSelected) {
				throw new FieldValueException(
					FieldError.TOO_FEW_SELECTED,
					"field-error-too-few-selected", minSelected);
			}

			int maxSelected = validationJSONObject.getInt(
				"maxSelected", optionValues.size());

			if (selectedValues.size() > maxSelected) {
				throw new FieldValueException(
					FieldError.TOO_MANY_SELECTED,
					"field-error-too-many-selected", maxSelected);
			}
		}

		List<FieldValue> fieldValues = new ArrayList<>(selectedValues.size());

		for (String selectedValue : selectedValues) {
			fieldValues.add(
				FieldValue.ofString(FieldValueType.STRING, selectedValue));
		}

		return fieldValues;
	}

	@Override
	protected void doValidateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		if (!isMultiValued()) {
			return;
		}

		JSONObject validationJSONObject = fieldDefinition.getValidation();

		int minSelected = getNonNegativeInt(validationJSONObject, "minSelected");

		if (!validationJSONObject.has("maxSelected")) {
			return;
		}

		int maxSelected = getNonNegativeInt(validationJSONObject, "maxSelected");

		if ((maxSelected < 1) || (minSelected > maxSelected)) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}
	}

	@Override
	protected Set<String> getSupportedRules() {
		if (isMultiValued()) {
			return _MULTI_VALUED_RULES;
		}

		return Collections.emptySet();
	}

	private static final Set<String> _MULTI_VALUED_RULES = rules(
		"maxSelected", "minSelected");
}
