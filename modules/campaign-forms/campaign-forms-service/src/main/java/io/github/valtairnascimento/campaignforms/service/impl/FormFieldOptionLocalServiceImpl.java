package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.service.base.FormFieldOptionLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.FormFieldOption",
	service = AopService.class
)
public class FormFieldOptionLocalServiceImpl
	extends FormFieldOptionLocalServiceBaseImpl {
	@Override
	public List<FormFieldOption> copyFormFieldOptions(
		long sourceFormFieldId, FormField targetFormField) {
		List<FormFieldOption> copies = new ArrayList<>();

		for (FormFieldOption formFieldOption :
				formFieldOptionPersistence.findByFormFieldId(
					sourceFormFieldId)) {
			copies.add(
				_addFormFieldOption(
					targetFormField, formFieldOption.getLabel(),
					formFieldOption.getValue(),
					formFieldOption.getPosition()));
		}

		return copies;
	}

	@Override
	public void deleteFormFieldOptions(long formFieldId) {
		formFieldOptionPersistence.removeByFormFieldId(formFieldId);
	}

	@Override
	public List<FormFieldOption> getFormFieldOptions(long formFieldId) {
		return formFieldOptionPersistence.findByFormFieldId(formFieldId);
	}

	@Override
	public Map<Long, List<FormFieldOption>> getFormFieldOptionsMap(
		long[] formFieldIds) {
		Map<Long, List<FormFieldOption>> formFieldOptionsMap = new HashMap<>();

		if (ArrayUtil.isEmpty(formFieldIds)) {
			return formFieldOptionsMap;
		}

		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"formFieldId", Arrays.asList(ArrayUtil.toArray(formFieldIds))));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("position"));

		List<FormFieldOption> formFieldOptions = dynamicQuery(dynamicQuery);

		for (FormFieldOption formFieldOption : formFieldOptions) {
			List<FormFieldOption> options =
				formFieldOptionsMap.computeIfAbsent(
					formFieldOption.getFormFieldId(), key -> new ArrayList<>());

			options.add(formFieldOption);
		}

		return formFieldOptionsMap;
	}

	@Override
	public List<FormFieldOption> setFormFieldOptions(
			FormField formField, List<FieldOptionInput> fieldOptionInputs)
		throws PortalException {
		_validate(fieldOptionInputs);

		formFieldOptionPersistence.removeByFormFieldId(
			formField.getFormFieldId());

		List<FormFieldOption> formFieldOptions = new ArrayList<>();

		for (int i = 0; i < fieldOptionInputs.size(); i++) {
			FieldOptionInput fieldOptionInput = fieldOptionInputs.get(i);

			formFieldOptions.add(
				_addFormFieldOption(
					formField, fieldOptionInput.getLabel(),
					fieldOptionInput.getValue(), i));
		}

		return formFieldOptions;
	}

	private FormFieldOption _addFormFieldOption(
		FormField formField, String label, String value, int position) {
		FormFieldOption formFieldOption = formFieldOptionPersistence.create(
			counterLocalService.increment());

		formFieldOption.setCompanyId(formField.getCompanyId());
		formFieldOption.setFormFieldId(formField.getFormFieldId());
		formFieldOption.setLabel(label);
		formFieldOption.setValue(value);
		formFieldOption.setPosition(position);

		return formFieldOptionPersistence.update(formFieldOption);
	}

	private void _validate(List<FieldOptionInput> fieldOptionInputs)
		throws PortalException {
		Set<String> values = new HashSet<>();

		for (FieldOptionInput fieldOptionInput : fieldOptionInputs) {
			if (CampaignValidator.isBlank(fieldOptionInput.getLabel())) {
				throw new FormFieldConfigurationException(
					"option-label-is-required");
			}

			if (CampaignValidator.isBlank(fieldOptionInput.getValue())) {
				throw new FormFieldConfigurationException(
					"option-value-is-required");
			}

			if ((fieldOptionInput.getLabel(
				).length() > _MAX_LABEL_LENGTH) ||
				(fieldOptionInput.getValue(
				).length() > _MAX_VALUE_LENGTH)) {
				throw new FormFieldConfigurationException(
					"option-is-too-long");
			}

			if (!values.add(fieldOptionInput.getValue())) {
				throw new FormFieldConfigurationException(
					"option-value-must-be-unique");
			}
		}
	}

	private static final int _MAX_LABEL_LENGTH = 500;

	private static final int _MAX_VALUE_LENGTH = 255;
}
