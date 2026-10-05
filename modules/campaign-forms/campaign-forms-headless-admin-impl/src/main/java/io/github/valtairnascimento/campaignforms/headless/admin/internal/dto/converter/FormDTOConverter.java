package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.constants.FieldWidth;
import io.github.valtairnascimento.campaignforms.field.FieldPreset;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldOption;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FormField;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FormVersion;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldOptionLocalService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = FormDTOConverter.class)
public class FormDTOConverter {
	public io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldPreset
		toDTO(FieldPreset fieldPreset, Locale locale) {
		io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldPreset dto =
			new io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.
				FieldPreset();

		dto.setFormField(
			_toFormField(fieldPreset.toFormFieldInput(locale)));
		dto.setKey(fieldPreset.getKey());
		dto.setLabel(fieldPreset.getLabel(locale));

		return dto;
	}

	public io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldType toDTO(
		FieldType fieldType) {
		io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldType dto =
			new io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.
				FieldType();

		dto.setKey(fieldType.getKey());
		dto.setMultiValued(fieldType.isMultiValued());
		dto.setOptionBased(fieldType.isOptionBased());
		dto.setValueType(
			io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldType.
				ValueType.create(
					fieldType.getValueType(
					).name()));

		return dto;
	}

	public FormField toDTO(
		io.github.valtairnascimento.campaignforms.model.FormField formField) {
		return _toFormField(
			formField,
			_formFieldOptionLocalService.getFormFieldOptions(
				formField.getFormFieldId()));
	}

	public FormVersion toDTO(
		io.github.valtairnascimento.campaignforms.model.FormVersion formVersion) {
		FormVersion dto = new FormVersion();

		dto.setCaptchaEnabled(formVersion.isCaptchaEnabled());
		dto.setConsentRequired(formVersion.isConsentRequired());
		dto.setConsentText(_nullIfEmpty(formVersion.getConsentText()));
		dto.setConsentURL(_nullIfEmpty(formVersion.getConsentURL()));
		dto.setConsentVersion(_nullIfEmpty(formVersion.getConsentVersion()));
		dto.setDatePublished(formVersion.getPublishedDate());
		dto.setFormFields(
			toDTOs(
				_formFieldLocalService.getFormFields(
					formVersion.getFormVersionId())
			).toArray(
				new FormField[0]
			));
		dto.setFormId(formVersion.getFormId());
		dto.setId(formVersion.getFormVersionId());
		dto.setRedirectUrl(_nullIfEmpty(formVersion.getRedirectUrl()));
		dto.setRequireAuthentication(formVersion.isRequireAuthentication());
		dto.setSettings(_jsonMapHelper.toMap(formVersion.getSettings()));
		dto.setStatus(FormVersion.Status.create(formVersion.getStatus()));
		dto.setSubmitLabel(_nullIfEmpty(formVersion.getSubmitLabel()));
		dto.setSuccessMessage(_nullIfEmpty(formVersion.getSuccessMessage()));
		dto.setSuccessTitle(_nullIfEmpty(formVersion.getSuccessTitle()));
		dto.setVersion(formVersion.getVersion());

		return dto;
	}

	public List<FormField> toDTOs(
		List<io.github.valtairnascimento.campaignforms.model.FormField> formFields) {
		long[] formFieldIds = new long[formFields.size()];

		for (int i = 0; i < formFields.size(); i++) {
			io.github.valtairnascimento.campaignforms.model.FormField formField =
				formFields.get(i);

			formFieldIds[i] = formField.getFormFieldId();
		}

		Map<Long, List<FormFieldOption>> formFieldOptionsMap =
			_formFieldOptionLocalService.getFormFieldOptionsMap(formFieldIds);

		List<FormField> dtos = new ArrayList<>(formFields.size());

		for (io.github.valtairnascimento.campaignforms.model.FormField formField :
				formFields) {
			dtos.add(
				_toFormField(
					formField,
					formFieldOptionsMap.getOrDefault(
						formField.getFormFieldId(),
						Collections.emptyList())));
		}

		return dtos;
	}

	public FormFieldInput toFormFieldInput(FormField formField) {
		List<FieldOptionInput> fieldOptionInputs = new ArrayList<>();

		FieldOption[] fieldOptions = formField.getFieldOptions();

		if (fieldOptions != null) {
			for (FieldOption fieldOption : fieldOptions) {
				fieldOptionInputs.add(
					new FieldOptionInput(
						fieldOption.getLabel(), fieldOption.getValue()));
			}
		}

		FieldWidth fieldWidth = FieldWidth.FULL;

		if (formField.getWidth() != null) {
			fieldWidth = FieldWidth.parse(
				formField.getWidth(
				).getValue());
		}

		return new FormFieldInput(
		).setConditionalRule(
			_jsonMapHelper.toJSON(formField.getConditionalRule())
		).setDefaultValue(
			formField.getDefaultValue()
		).setFieldKey(
			formField.getFieldKey()
		).setHelpText(
			formField.getHelpText()
		).setLabel(
			formField.getLabel()
		).setOptions(
			fieldOptionInputs
		).setPersonalData(
			GetterUtil.getBoolean(formField.getPersonalData())
		).setPlaceholder(
			formField.getPlaceholder()
		).setPresetKey(
			formField.getPresetKey()
		).setProperties(
			_jsonMapHelper.toJSON(formField.getProperties())
		).setRequired(
			GetterUtil.getBoolean(formField.getRequired())
		).setType(
			formField.getType()
		).setValidation(
			_jsonMapHelper.toJSON(formField.getValidation())
		).setWidth(
			fieldWidth
		);
	}

	public FormVersionInput toFormVersionInput(FormVersion formVersion) {
		return new FormVersionInput(
		).setCaptchaEnabled(
			GetterUtil.getBoolean(formVersion.getCaptchaEnabled())
		).setConsentRequired(
			GetterUtil.getBoolean(formVersion.getConsentRequired())
		).setConsentText(
			formVersion.getConsentText()
		).setConsentURL(
			formVersion.getConsentURL()
		).setConsentVersion(
			formVersion.getConsentVersion()
		).setRedirectUrl(
			formVersion.getRedirectUrl()
		).setRequireAuthentication(
			GetterUtil.getBoolean(formVersion.getRequireAuthentication())
		).setSettings(
			_jsonMapHelper.toJSON(formVersion.getSettings())
		).setSubmitLabel(
			formVersion.getSubmitLabel()
		).setSuccessMessage(
			formVersion.getSuccessMessage()
		).setSuccessTitle(
			formVersion.getSuccessTitle()
		);
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	private FormField _toFormField(
		io.github.valtairnascimento.campaignforms.model.FormField formField,
		List<FormFieldOption> formFieldOptions) {
		FormField dto = new FormField();

		dto.setConditionalRule(
			_jsonMapHelper.toMap(formField.getConditionalRule()));
		dto.setDefaultValue(_nullIfEmpty(formField.getDefaultValue()));
		dto.setFieldKey(formField.getFieldKey());
		dto.setFieldOptions(
			TransformUtil.transformToArray(
				formFieldOptions,
				formFieldOption -> {
					FieldOption fieldOption = new FieldOption();

					fieldOption.setLabel(formFieldOption.getLabel());
					fieldOption.setValue(formFieldOption.getValue());

					return fieldOption;
				},
				FieldOption.class));
		dto.setHelpText(_nullIfEmpty(formField.getHelpText()));
		dto.setId(formField.getFormFieldId());
		dto.setLabel(formField.getLabel());
		dto.setPersonalData(formField.isPersonalData());
		dto.setPlaceholder(_nullIfEmpty(formField.getPlaceholder()));
		dto.setPosition(formField.getPosition());
		dto.setPresetKey(_nullIfEmpty(formField.getPresetKey()));
		dto.setProperties(_jsonMapHelper.toMap(formField.getProperties()));
		dto.setRequired(formField.isRequired());
		dto.setType(formField.getType());
		dto.setValidation(_jsonMapHelper.toMap(formField.getValidation()));
		dto.setWidth(FormField.Width.create(formField.getWidth()));

		return dto;
	}

	private FormField _toFormField(FormFieldInput formFieldInput) {
		FormField dto = new FormField();

		dto.setDefaultValue(formFieldInput.getDefaultValue());
		dto.setFieldKey(formFieldInput.getFieldKey());
		dto.setFieldOptions(
			TransformUtil.transformToArray(
				formFieldInput.getOptions(),
				fieldOptionInput -> {
					FieldOption fieldOption = new FieldOption();

					fieldOption.setLabel(fieldOptionInput.getLabel());
					fieldOption.setValue(fieldOptionInput.getValue());

					return fieldOption;
				},
				FieldOption.class));
		dto.setLabel(formFieldInput.getLabel());
		dto.setPersonalData(formFieldInput.isPersonalData());
		dto.setPresetKey(formFieldInput.getPresetKey());
		dto.setProperties(_jsonMapHelper.toMap(formFieldInput.getProperties()));
		dto.setRequired(formFieldInput.isRequired());
		dto.setType(formFieldInput.getType());
		dto.setValidation(_jsonMapHelper.toMap(formFieldInput.getValidation()));
		dto.setWidth(
			FormField.Width.create(
				formFieldInput.getWidth(
				).name()));

		return dto;
	}

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormFieldOptionLocalService _formFieldOptionLocalService;

	@Reference
	private JSONMapHelper _jsonMapHelper;
}
