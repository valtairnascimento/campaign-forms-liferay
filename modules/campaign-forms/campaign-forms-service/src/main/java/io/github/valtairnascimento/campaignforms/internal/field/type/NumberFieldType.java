package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.json.JSONObject;

import java.math.BigDecimal;

import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + NumberFieldType.KEY,
	service = FieldType.class
)
public class NumberFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "number";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.NUMBER;
	}

	@Override
	protected void doValidateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		validateNumberRangeRules(fieldDefinition);

		JSONObject validationJSONObject = fieldDefinition.getValidation();

		if (validationJSONObject.has("integer") &&
			!(validationJSONObject.get("integer") instanceof Boolean)) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid");
		}
	}

	@Override
	protected Set<String> getSupportedRules() {
		return _SUPPORTED_RULES;
	}

	@Override
	protected FieldValue toValue(
			FieldDefinition fieldDefinition, String rawValue,
			TimeZone timeZone)
		throws FieldValueException {
		if (!_numberPattern.matcher(
				rawValue
			).matches()) {
			throw new FieldValueException(
				FieldError.INVALID_NUMBER, "field-error-invalid-number");
		}

		BigDecimal number = new BigDecimal(rawValue.replace(',', '.'));

		JSONObject validationJSONObject = fieldDefinition.getValidation();

		if (validationJSONObject.getBoolean("integer") &&
			(number.stripTrailingZeros(
			).scale() > 0)) {
			throw new FieldValueException(
				FieldError.INVALID_NUMBER, "field-error-invalid-integer");
		}

		try {
			BigDecimal min = toBigDecimal(validationJSONObject, "min");

			if ((min != null) && (number.compareTo(min) < 0)) {
				throw new FieldValueException(
					FieldError.TOO_SMALL, "field-error-too-small",
					min.toPlainString());
			}

			BigDecimal max = toBigDecimal(validationJSONObject, "max");

			if ((max != null) && (number.compareTo(max) > 0)) {
				throw new FieldValueException(
					FieldError.TOO_LARGE, "field-error-too-large",
					max.toPlainString());
			}
		}
		catch (FormFieldConfigurationException
					formFieldConfigurationException) {
			throw new FieldValueException(
				FieldError.INVALID_NUMBER, "field-error-invalid-number");
		}

		number = number.stripTrailingZeros();

		if (number.scale() < 0) {
			number = number.setScale(0);
		}

		return FieldValue.ofNumber(number);
	}

	private static final Set<String> _SUPPORTED_RULES = rules(
		"integer", "max", "min");

	private static final Pattern _numberPattern = Pattern.compile(
		"^[-+]?\\d{1,18}([.,]\\d{1,10})?$");
}
