package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.json.JSONObject;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public abstract class BaseFieldType implements FieldType {
	public static final Map<String, Pattern> PATTERNS;

	@Override
	public void validateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		Set<String> supportedRules = getSupportedRules();

		Iterator<String> iterator = validationJSONObject.keys();

		while (iterator.hasNext()) {
			if (!supportedRules.contains(iterator.next())) {
				throw new FormFieldConfigurationException(
					"field-validation-is-invalid");
			}
		}

		if (isOptionBased() && fieldDefinition.getOptionValues().isEmpty()) {
			throw new FormFieldConfigurationException(
				"field-options-are-required");
		}

		doValidateDefinition(fieldDefinition);
	}

	protected static Set<String> rules(String... rules) {
		return Collections.unmodifiableSet(new TreeSet<>(Set.of(rules)));
	}

	protected void checkLength(
			FieldDefinition fieldDefinition, String value, int defaultMaxLength)
		throws FieldValueException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		int minLength = validationJSONObject.getInt("minLength", 0);

		if (value.length() < minLength) {
			throw new FieldValueException(
				FieldError.TOO_SHORT, "field-error-too-short", minLength);
		}

		int maxLength = validationJSONObject.getInt(
			"maxLength", defaultMaxLength);

		if (value.length() > maxLength) {
			throw new FieldValueException(
				FieldError.TOO_LONG, "field-error-too-long", maxLength);
		}
	}

	protected void checkPattern(FieldDefinition fieldDefinition, String value)
		throws FieldValueException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		if (!validationJSONObject.has("pattern")) {
			return;
		}

		Pattern pattern = PATTERNS.get(
			validationJSONObject.getString("pattern"));

		if (!pattern.matcher(
				value
			).matches()) {
			throw new FieldValueException(
				FieldError.PATTERN_MISMATCH, "field-error-pattern-mismatch");
		}
	}

	protected void doValidateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
	}

	protected abstract Set<String> getSupportedRules();

	protected void validateLengthRules(
			FieldDefinition fieldDefinition, int hardMaxLength)
		throws FormFieldConfigurationException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		int minLength = getNonNegativeInt(validationJSONObject, "minLength");
		int maxLength = getNonNegativeInt(validationJSONObject, "maxLength");

		if ((maxLength > hardMaxLength) ||
			(validationJSONObject.has("maxLength") && (maxLength < 1)) ||
			(validationJSONObject.has("maxLength") && (minLength > maxLength))) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}

		if (validationJSONObject.has("pattern") &&
			!PATTERNS.containsKey(validationJSONObject.getString("pattern"))) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}
	}

	protected void validateNumberRangeRules(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		BigDecimal min = toBigDecimal(validationJSONObject, "min");
		BigDecimal max = toBigDecimal(validationJSONObject, "max");

		if ((min != null) && (max != null) && (min.compareTo(max) > 0)) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}
	}

	protected BigDecimal toBigDecimal(JSONObject jsonObject, String key)
		throws FormFieldConfigurationException {
		if (!jsonObject.has(key)) {
			return null;
		}

		try {
			return new BigDecimal(jsonObject.getString(key));
		}
		catch (NumberFormatException numberFormatException) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid", numberFormatException);
		}
	}

	protected int getNonNegativeInt(JSONObject jsonObject, String key)
		throws FormFieldConfigurationException {
		if (!jsonObject.has(key)) {
			return 0;
		}

		Object value = jsonObject.get(key);

		if (!(value instanceof Number)) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}

		Number number = (Number)value;

		if ((number.doubleValue() < 0) ||
			(number.doubleValue() != number.intValue())) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}

		return number.intValue();
	}

	static {
		Map<String, Pattern> patterns = new HashMap<>();

		patterns.put("alphanumeric", Pattern.compile("^[\\p{L}\\p{N} ]+$"));
		patterns.put("cep", Pattern.compile("^\\d{5}-?\\d{3}$"));
		patterns.put("digits", Pattern.compile("^\\d+$"));
		patterns.put("letters", Pattern.compile("^[\\p{L} '-]+$"));

		PATTERNS = Collections.unmodifiableMap(patterns);
	}

}
