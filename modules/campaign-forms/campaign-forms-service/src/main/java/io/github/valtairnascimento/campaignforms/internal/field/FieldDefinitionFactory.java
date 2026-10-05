package io.github.valtairnascimento.campaignforms.internal.field;

import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = FieldDefinitionFactory.class)
public class FieldDefinitionFactory {
	public FieldDefinition create(
		FormField formField, List<FormFieldOption> formFieldOptions) {
		List<String> optionValues = new ArrayList<>(formFieldOptions.size());

		for (FormFieldOption formFieldOption : formFieldOptions) {
			optionValues.add(formFieldOption.getValue());
		}

		return new FieldDefinition(
			formField.getFormFieldId(), formField.getFieldKey(),
			formField.getType(), formField.getLabel(), formField.isRequired(),
			formField.getDefaultValue(),
			_toJSONObject(formField.getValidation()),
			_toJSONObject(formField.getProperties()), optionValues,
			formField.isPersonalData());
	}

	public FieldDefinition create(FormFieldInput formFieldInput)
		throws FormFieldConfigurationException {
		List<String> optionValues = new ArrayList<>();

		for (FieldOptionInput fieldOptionInput : formFieldInput.getOptions()) {
			optionValues.add(fieldOptionInput.getValue());
		}

		return new FieldDefinition(
			0, formFieldInput.getFieldKey(), formFieldInput.getType(),
			formFieldInput.getLabel(), formFieldInput.isRequired(),
			formFieldInput.getDefaultValue(),
			_parseJSONObject(
				formFieldInput.getValidation(), "field-validation-is-invalid"),
			_parseJSONObject(
				formFieldInput.getProperties(), "field-properties-are-invalid"),
			optionValues, formFieldInput.isPersonalData());
	}

	private JSONObject _parseJSONObject(String json, String messageKey)
		throws FormFieldConfigurationException {
		if (CampaignValidator.isBlank(json)) {
			return _jsonFactory.createJSONObject();
		}

		try {
			return _jsonFactory.createJSONObject(json);
		}
		catch (JSONException jsonException) {
			throw new FormFieldConfigurationException(
				messageKey, jsonException);
		}
	}

	private JSONObject _toJSONObject(String json) {
		try {
			return _parseJSONObject(json, "field-validation-is-invalid");
		}
		catch (FormFieldConfigurationException
					formFieldConfigurationException) {
			return _jsonFactory.createJSONObject();
		}
	}

	@Reference
	private JSONFactory _jsonFactory;
}
