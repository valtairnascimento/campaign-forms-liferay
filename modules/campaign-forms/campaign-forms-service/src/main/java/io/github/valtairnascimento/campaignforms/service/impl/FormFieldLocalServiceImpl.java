package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.exception.DuplicateFieldKeyException;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.internal.field.FieldDefinitionFactory;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.util.DraftLockUtil;
import io.github.valtairnascimento.campaignforms.internal.util.PositionUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.FormFieldOptionLocalService;
import io.github.valtairnascimento.campaignforms.service.base.FormFieldLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.FormField",
	service = AopService.class
)
public class FormFieldLocalServiceImpl extends FormFieldLocalServiceBaseImpl {
	@Override
	public FormField addFormField(
			long formVersionId, FormFieldInput formFieldInput)
		throws PortalException {
		FormVersion formVersion = _getDraftFormVersion(formVersionId);

		if (formFieldPersistence.countByFormVersionId(formVersionId) >=
				CampaignConstants.MAX_FIELDS_PER_FORM) {
			throw new FormFieldConfigurationException(
				"form-has-too-many-fields");
		}

		_validate(0, formVersionId, formFieldInput);

		FormField formField = formFieldPersistence.create(
			counterLocalService.increment());

		formField.setGroupId(formVersion.getGroupId());
		formField.setCompanyId(formVersion.getCompanyId());

		Date now = new Date();

		formField.setCreateDate(now);
		formField.setModifiedDate(now);

		formField.setFormVersionId(formVersionId);
		formField.setPosition(
			formFieldPersistence.countByFormVersionId(formVersionId));

		_setFields(formField, formFieldInput);

		formField = formFieldPersistence.update(formField);

		_formFieldOptionLocalService.setFormFieldOptions(
			formField, formFieldInput.getOptions());

		_touch(formVersion);

		return formField;
	}

	@Override
	public void copyFormFields(
			long sourceFormVersionId, FormVersion targetFormVersion)
		throws PortalException {
		DraftLockUtil.checkDraft(targetFormVersion);

		Date now = new Date();

		for (FormField sourceFormField :
				formFieldPersistence.findByFormVersionId(sourceFormVersionId)) {
			FormField formField = formFieldPersistence.create(
				counterLocalService.increment());

			formField.setGroupId(targetFormVersion.getGroupId());
			formField.setCompanyId(targetFormVersion.getCompanyId());
			formField.setCreateDate(now);
			formField.setModifiedDate(now);
			formField.setFormVersionId(targetFormVersion.getFormVersionId());
			formField.setFieldKey(sourceFormField.getFieldKey());
			formField.setType(sourceFormField.getType());
			formField.setLabel(sourceFormField.getLabel());
			formField.setPlaceholder(sourceFormField.getPlaceholder());
			formField.setHelpText(sourceFormField.getHelpText());
			formField.setRequired(sourceFormField.isRequired());
			formField.setPosition(sourceFormField.getPosition());
			formField.setDefaultValue(sourceFormField.getDefaultValue());
			formField.setWidth(sourceFormField.getWidth());
			formField.setValidation(sourceFormField.getValidation());
			formField.setProperties(sourceFormField.getProperties());
			formField.setConditionalRule(sourceFormField.getConditionalRule());
			formField.setPresetKey(sourceFormField.getPresetKey());
			formField.setPersonalData(sourceFormField.isPersonalData());

			formField = formFieldPersistence.update(formField);

			_formFieldOptionLocalService.copyFormFieldOptions(
				sourceFormField.getFormFieldId(), formField);
		}
	}

	@Override
	public FormField deleteFormField(long formFieldId) throws PortalException {
		FormField formField = formFieldPersistence.findByPrimaryKey(
			formFieldId);

		FormVersion formVersion = _getDraftFormVersion(
			formField.getFormVersionId());

		_formFieldOptionLocalService.deleteFormFieldOptions(formFieldId);

		formField = formFieldPersistence.remove(formField);

		_compactPositions(formVersion.getFormVersionId());

		_touch(formVersion);

		return formField;
	}

	@Override
	public void deleteFormVersionFields(long formVersionId) {
		for (FormField formField :
				formFieldPersistence.findByFormVersionId(formVersionId)) {
			_formFieldOptionLocalService.deleteFormFieldOptions(
				formField.getFormFieldId());

			formFieldPersistence.remove(formField);
		}
	}

	@Override
	public List<FormField> getFormFields(long formVersionId) {
		return formFieldPersistence.findByFormVersionId(formVersionId);
	}

	@Override
	public List<FormField> moveFormField(long formFieldId, int position)
		throws PortalException {
		FormField formField = formFieldPersistence.findByPrimaryKey(
			formFieldId);

		FormVersion formVersion = _getDraftFormVersion(
			formField.getFormVersionId());

		List<FormField> formFields = formFieldPersistence.findByFormVersionId(
			formVersion.getFormVersionId());

		_updatePositions(
			PositionUtil.move(
				formFields, formFieldId, position, FormField::getFormFieldId,
				FormField::getPosition, FormField::setPosition));

		_touch(formVersion);

		return formFieldPersistence.findByFormVersionId(
			formVersion.getFormVersionId());
	}

	@Override
	public List<FormField> reorderFormFields(
			long formVersionId, long[] orderedFormFieldIds)
		throws PortalException {
		FormVersion formVersion = _getDraftFormVersion(formVersionId);

		List<FormField> formFields = formFieldPersistence.findByFormVersionId(
			formVersionId);

		try {
			_updatePositions(
				PositionUtil.reorder(
					formFields, orderedFormFieldIds, FormField::getFormFieldId,
					FormField::getPosition, FormField::setPosition));
		}
		catch (IllegalArgumentException illegalArgumentException) {
			throw new FormFieldConfigurationException(
				"field-order-is-invalid", illegalArgumentException);
		}

		_touch(formVersion);

		return formFieldPersistence.findByFormVersionId(formVersionId);
	}

	@Override
	public FormField updateFormField(
			long formFieldId, FormFieldInput formFieldInput)
		throws PortalException {
		FormField formField = formFieldPersistence.findByPrimaryKey(
			formFieldId);

		FormVersion formVersion = _getDraftFormVersion(
			formField.getFormVersionId());

		_validate(formFieldId, formField.getFormVersionId(), formFieldInput);

		formField.setModifiedDate(new Date());

		_setFields(formField, formFieldInput);

		formField = formFieldPersistence.update(formField);

		_formFieldOptionLocalService.setFormFieldOptions(
			formField, formFieldInput.getOptions());

		_touch(formVersion);

		return formField;
	}

	private void _checkLength(String value, int maxLength, String messageKey)
		throws FormFieldConfigurationException {
		if ((value != null) && (value.length() > maxLength)) {
			throw new FormFieldConfigurationException(messageKey);
		}
	}

	private void _compactPositions(long formVersionId) {
		List<FormField> formFields = formFieldPersistence.findByFormVersionId(
			formVersionId);

		for (int i = 0; i < formFields.size(); i++) {
			FormField formField = formFields.get(i);

			if (formField.getPosition() != i) {
				formField.setPosition(i);

				formFieldPersistence.update(formField);
			}
		}
	}

	private FormVersion _getDraftFormVersion(long formVersionId)
		throws PortalException {
		FormVersion formVersion = formVersionPersistence.findByPrimaryKey(
			formVersionId);

		DraftLockUtil.checkDraft(formVersion);

		return formVersion;
	}

	private String _normalizeJSON(String json, String messageKey)
		throws FormFieldConfigurationException {
		try {
			return _jsonSettingsHelper.normalizeObject(json);
		}
		catch (JSONException jsonException) {
			throw new FormFieldConfigurationException(
				messageKey, jsonException);
		}
	}

	private void _setFields(FormField formField, FormFieldInput formFieldInput)
		throws FormFieldConfigurationException {
		formField.setFieldKey(formFieldInput.getFieldKey());
		formField.setType(formFieldInput.getType());
		formField.setLabel(formFieldInput.getLabel());
		formField.setPlaceholder(formFieldInput.getPlaceholder());
		formField.setHelpText(formFieldInput.getHelpText());
		formField.setRequired(formFieldInput.isRequired());
		formField.setDefaultValue(formFieldInput.getDefaultValue());
		formField.setWidth(
			formFieldInput.getWidth(
			).name());
		formField.setValidation(
			_normalizeJSON(
				formFieldInput.getValidation(), "field-validation-is-invalid"));
		formField.setProperties(
			_normalizeJSON(
				formFieldInput.getProperties(), "field-properties-are-invalid"));
		formField.setConditionalRule(
			_normalizeJSON(
				formFieldInput.getConditionalRule(),
				"field-conditional-rule-is-invalid"));
		formField.setPresetKey(formFieldInput.getPresetKey());
		formField.setPersonalData(formFieldInput.isPersonalData());
	}

	private void _touch(FormVersion formVersion) {
		formVersion.setModifiedDate(new Date());

		formVersionPersistence.update(formVersion);
	}

	private void _updatePositions(List<FormField> changedFormFields) {
		for (FormField formField : changedFormFields) {
			formFieldPersistence.update(formField);
		}
	}

	private void _validate(
			long formFieldId, long formVersionId, FormFieldInput formFieldInput)
		throws PortalException {
		CampaignValidator.validateFieldKey(formFieldInput.getFieldKey());

		FormField existingFormField = formFieldPersistence.fetchByFV_FK(
			formVersionId, formFieldInput.getFieldKey());

		if ((existingFormField != null) &&
			(existingFormField.getFormFieldId() != formFieldId)) {
			throw new DuplicateFieldKeyException("field-key-must-be-unique");
		}

		if (Validator.isNull(formFieldInput.getType()) ||
			!_typePattern.matcher(
				formFieldInput.getType()
			).matches()) {
			throw new FormFieldConfigurationException("field-type-is-invalid");
		}

		FieldType fieldType = _fieldTypeRegistry.getFieldType(
			formFieldInput.getType());

		if (fieldType == null) {
			throw new FormFieldConfigurationException("field-type-is-invalid");
		}

		if (!fieldType.isOptionBased() &&
			!formFieldInput.getOptions(
			).isEmpty()) {
			throw new FormFieldConfigurationException(
				"field-type-does-not-support-options");
		}

		fieldType.validateDefinition(
			_fieldDefinitionFactory.create(formFieldInput));

		if (CampaignValidator.isBlank(formFieldInput.getLabel())) {
			throw new FormFieldConfigurationException(
				"field-label-is-required");
		}

		if (formFieldInput.getWidth() == null) {
			throw new FormFieldConfigurationException("field-width-is-invalid");
		}

		_checkLength(
			formFieldInput.getLabel(), 500, "field-label-is-too-long");
		_checkLength(
			formFieldInput.getPlaceholder(), 255,
			"field-placeholder-is-too-long");
		_checkLength(
			formFieldInput.getHelpText(), 1000, "field-help-text-is-too-long");
		_checkLength(
			formFieldInput.getDefaultValue(), 1000,
			"field-default-value-is-too-long");

		if (Validator.isNotNull(formFieldInput.getPresetKey()) &&
			!CampaignValidator.isValidKey(formFieldInput.getPresetKey())) {
			throw new FormFieldConfigurationException(
				"field-preset-is-invalid");
		}
	}

	private static final Pattern _typePattern = Pattern.compile(
		"^[a-zA-Z][a-zA-Z0-9]{0,29}$");

	@Reference
	private FieldDefinitionFactory _fieldDefinitionFactory;

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormFieldOptionLocalService _formFieldOptionLocalService;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
