package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + TelFieldType.KEY,
	service = FieldType.class
)
public class TelFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "tel";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.STRING;
	}

	@Override
	protected Set<String> getSupportedRules() {
		return Collections.emptySet();
	}

	@Override
	protected FieldValue toValue(
			FieldDefinition fieldDefinition, String rawValue,
			TimeZone timeZone)
		throws FieldValueException {
		if (!_allowedCharactersPattern.matcher(
				rawValue
			).matches()) {
			throw new FieldValueException(
				FieldError.INVALID_PHONE, "field-error-invalid-phone");
		}

		String digits = rawValue.replaceAll("\\D", "");

		if ((digits.length() < 8) || (digits.length() > 15)) {
			throw new FieldValueException(
				FieldError.INVALID_PHONE, "field-error-invalid-phone");
		}

		if (rawValue.startsWith("+")) {
			digits = "+" + digits;
		}

		return FieldValue.ofString(FieldValueType.STRING, digits);
	}

	private static final Pattern _allowedCharactersPattern = Pattern.compile(
		"^\\+?[0-9 ().\\-]{8,25}$");
}
