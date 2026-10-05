package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + BooleanFieldType.KEY,
	service = FieldType.class
)
public class BooleanFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "boolean";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.BOOLEAN;
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
		String value = StringUtil.toLowerCase(rawValue);

		boolean booleanValue;

		if (_TRUE_VALUES.contains(value)) {
			booleanValue = true;
		}
		else if (_FALSE_VALUES.contains(value)) {
			booleanValue = false;
		}
		else {
			throw new FieldValueException(
				FieldError.INVALID_VALUE, "field-error-invalid-value");
		}

		if (fieldDefinition.isRequired() && !booleanValue) {
			throw new FieldValueException(
				FieldError.REQUIRED, "field-error-required");
		}

		return FieldValue.ofBoolean(booleanValue);
	}

	private static final Set<String> _FALSE_VALUES = Set.of(
		"0", "false", "off");

	private static final Set<String> _TRUE_VALUES = Set.of("1", "on", "true");
}
