package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public abstract class BaseSingleValueFieldType extends BaseFieldType {
	@Override
	public List<FieldValue> toValues(
			FieldDefinition fieldDefinition, List<String> rawValues,
			TimeZone timeZone)
		throws FieldValueException {
		if (rawValues.size() != 1) {
			throw new FieldValueException(
				FieldError.INVALID_VALUE, "field-error-invalid-value");
		}

		return Collections.singletonList(
			toValue(fieldDefinition, rawValues.get(0), timeZone));
	}

	protected abstract FieldValue toValue(
			FieldDefinition fieldDefinition, String rawValue,
			TimeZone timeZone)
		throws FieldValueException;
}
