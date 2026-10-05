package io.github.valtairnascimento.campaignforms.internal.field;

import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldOptionLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = FieldDefinitionLoader.class)
public class FieldDefinitionLoader {
	public List<FieldDefinition> getFieldDefinitions(long formVersionId) {
		List<FormField> formFields = _formFieldLocalService.getFormFields(
			formVersionId);

		long[] formFieldIds = new long[formFields.size()];

		for (int i = 0; i < formFields.size(); i++) {
			FormField formField = formFields.get(i);

			formFieldIds[i] = formField.getFormFieldId();
		}

		Map<Long, List<FormFieldOption>> formFieldOptionsMap =
			_formFieldOptionLocalService.getFormFieldOptionsMap(formFieldIds);

		List<FieldDefinition> fieldDefinitions = new ArrayList<>(
			formFields.size());

		for (FormField formField : formFields) {
			fieldDefinitions.add(
				_fieldDefinitionFactory.create(
					formField,
					formFieldOptionsMap.getOrDefault(
						formField.getFormFieldId(),
						Collections.emptyList())));
		}

		return fieldDefinitions;
	}

	@Reference
	private FieldDefinitionFactory _fieldDefinitionFactory;

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormFieldOptionLocalService _formFieldOptionLocalService;
}
