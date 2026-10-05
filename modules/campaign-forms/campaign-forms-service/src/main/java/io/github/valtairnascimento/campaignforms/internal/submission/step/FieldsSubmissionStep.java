package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "service.ranking:Integer=500", service = SubmissionStep.class
)
public class FieldsSubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext)
		throws PortalException {
		Map<String, List<String>> submittedValues =
			submissionContext.getCommand(
			).getValues();

		_checkRequest(submissionContext, submittedValues);

		for (FieldDefinition fieldDefinition :
				submissionContext.getFieldDefinitions()) {
			FieldType fieldType = _fieldTypeRegistry.getFieldType(
				fieldDefinition.getType());

			if (fieldType == null) {
				throw new FormFieldConfigurationException(
					"field-type-is-invalid");
			}

			String fieldKey = fieldDefinition.getFieldKey();

			List<String> rawValues = _clean(submittedValues.get(fieldKey));

			if (rawValues.isEmpty() && fieldType.usesDefaultValueWhenEmpty() &&
				!CampaignValidator.isBlank(fieldDefinition.getDefaultValue())) {
				rawValues = Collections.singletonList(
					fieldDefinition.getDefaultValue(
					).trim());
			}

			if (rawValues.isEmpty()) {
				if (fieldDefinition.isRequired()) {
					submissionContext.addError(
						new FieldError(
							fieldKey, FieldError.REQUIRED,
							"field-error-required"));
				}

				continue;
			}

			if (rawValues.size() > _MAX_VALUES_PER_FIELD) {
				submissionContext.addError(
					new FieldError(
						fieldKey, FieldError.INVALID_VALUE,
						"field-error-invalid-value"));

				continue;
			}

			try {
				submissionContext.setValues(
					fieldKey,
					fieldType.toValues(
						fieldDefinition, rawValues,
						submissionContext.getTimeZone()));
			}
			catch (FieldValueException fieldValueException) {
				submissionContext.addError(
					fieldValueException.toFieldError(fieldKey));
			}
		}
	}

	private void _checkRequest(
			SubmissionContext submissionContext,
			Map<String, List<String>> submittedValues)
		throws InvalidFieldException {
		Set<String> fieldKeys = new HashSet<>();

		for (FieldDefinition fieldDefinition :
				submissionContext.getFieldDefinitions()) {
			fieldKeys.add(fieldDefinition.getFieldKey());
		}

		long length = 0;

		for (Map.Entry<String, List<String>> entry :
				submittedValues.entrySet()) {
			if (!fieldKeys.contains(entry.getKey())) {
				throw new InvalidFieldException(
					"unknown-field", entry.getKey());
			}

			if (entry.getValue() == null) {
				continue;
			}

			for (String value : entry.getValue()) {
				if (value != null) {
					length += value.length();
				}
			}
		}

		if (length > CampaignConstants.MAX_SUBMISSION_LENGTH) {
			throw new InvalidFieldException("submission-is-too-large");
		}
	}

	private List<String> _clean(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<String> cleanValues = new ArrayList<>(values.size());

		for (String value : values) {
			if (!CampaignValidator.isBlank(value)) {
				cleanValues.add(value.trim());
			}
		}

		return cleanValues;
	}

	private static final int _MAX_VALUES_PER_FIELD = 100;

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;
}
