package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + EmailFieldType.KEY,
	service = FieldType.class
)
public class EmailFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "email";

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
		String email = StringUtil.toLowerCase(rawValue);

		if ((email.length() > _MAX_LENGTH) || !Validator.isEmailAddress(email)) {
			throw new FieldValueException(
				FieldError.INVALID_EMAIL, "field-error-invalid-email");
		}

		return FieldValue.ofString(FieldValueType.STRING, email);
	}

	private static final int _MAX_LENGTH = 254;
}
