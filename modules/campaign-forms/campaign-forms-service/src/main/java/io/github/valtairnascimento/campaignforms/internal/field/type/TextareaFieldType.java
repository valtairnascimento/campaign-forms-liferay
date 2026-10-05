package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + TextareaFieldType.KEY,
	service = FieldType.class
)
public class TextareaFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "textarea";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.TEXT;
	}

	@Override
	protected void doValidateDefinition(FieldDefinition fieldDefinition)
		throws FormFieldConfigurationException {
		validateLengthRules(fieldDefinition, _HARD_MAX_LENGTH);
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
		checkLength(fieldDefinition, rawValue, _DEFAULT_MAX_LENGTH);

		return FieldValue.ofString(FieldValueType.TEXT, rawValue);
	}

	private static final int _DEFAULT_MAX_LENGTH = 4000;

	private static final int _HARD_MAX_LENGTH = 10000;

	private static final Set<String> _SUPPORTED_RULES = rules(
		"maxLength", "minLength");
}
