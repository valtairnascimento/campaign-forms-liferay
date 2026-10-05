package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.json.JSONObject;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + DateFieldType.KEY,
	service = FieldType.class
)
public class DateFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "date";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.DATE;
	}

	@Override
	protected void doValidateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		LocalDate min = _getRuleDate(fieldDefinition, "min");
		LocalDate max = _getRuleDate(fieldDefinition, "max");

		if ((min != null) && (max != null) && min.isAfter(max)) {
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
		LocalDate localDate;

		try {
			localDate = LocalDate.parse(rawValue);
		}
		catch (DateTimeParseException dateTimeParseException) {
			throw new FieldValueException(
				FieldError.INVALID_DATE, "field-error-invalid-date");
		}

		if ((localDate.getYear() < _MIN_YEAR) ||
			(localDate.getYear() > _MAX_YEAR)) {
			throw new FieldValueException(
				FieldError.INVALID_DATE, "field-error-invalid-date");
		}

		try {
			LocalDate min = _getRuleDate(fieldDefinition, "min");

			if ((min != null) && localDate.isBefore(min)) {
				throw new FieldValueException(
					FieldError.TOO_SMALL, "field-error-date-too-early",
					min.toString());
			}

			LocalDate max = _getRuleDate(fieldDefinition, "max");

			if ((max != null) && localDate.isAfter(max)) {
				throw new FieldValueException(
					FieldError.TOO_LARGE, "field-error-date-too-late",
					max.toString());
			}
		}
		catch (FormFieldConfigurationException
					formFieldConfigurationException) {
			throw new FieldValueException(
				FieldError.INVALID_DATE, "field-error-invalid-date");
		}

		return FieldValue.ofDate(
			FieldValueType.DATE,
			Date.from(
				localDate.atStartOfDay(
					ZoneOffset.UTC
				).toInstant()),
			localDate.toString());
	}

	private LocalDate _getRuleDate(FieldDefinition fieldDefinition, String key)
		throws FormFieldConfigurationException {
		JSONObject validationJSONObject = fieldDefinition.getValidation();

		if (!validationJSONObject.has(key)) {
			return null;
		}

		try {
			return LocalDate.parse(validationJSONObject.getString(key));
		}
		catch (DateTimeParseException dateTimeParseException) {
			throw new FormFieldConfigurationException(
				"field-validation-is-invalid", dateTimeParseException);
		}
	}

	private static final int _MAX_YEAR = 2200;

	private static final int _MIN_YEAR = 1900;

	private static final Set<String> _SUPPORTED_RULES = rules("max", "min");
}
