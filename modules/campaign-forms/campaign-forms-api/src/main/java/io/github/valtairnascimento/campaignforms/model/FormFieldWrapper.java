/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package io.github.valtairnascimento.campaignforms.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FormField}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormField
 * @generated
 */
public class FormFieldWrapper
	extends BaseModelWrapper<FormField>
	implements FormField, ModelWrapper<FormField> {

	public FormFieldWrapper(FormField formField) {
		super(formField);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("formFieldId", getFormFieldId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formVersionId", getFormVersionId());
		attributes.put("fieldKey", getFieldKey());
		attributes.put("type", getType());
		attributes.put("label", getLabel());
		attributes.put("placeholder", getPlaceholder());
		attributes.put("helpText", getHelpText());
		attributes.put("required", isRequired());
		attributes.put("position", getPosition());
		attributes.put("defaultValue", getDefaultValue());
		attributes.put("width", getWidth());
		attributes.put("validation", getValidation());
		attributes.put("properties", getProperties());
		attributes.put("conditionalRule", getConditionalRule());
		attributes.put("presetKey", getPresetKey());
		attributes.put("personalData", isPersonalData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formFieldId = (Long)attributes.get("formFieldId");

		if (formFieldId != null) {
			setFormFieldId(formFieldId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long formVersionId = (Long)attributes.get("formVersionId");

		if (formVersionId != null) {
			setFormVersionId(formVersionId);
		}

		String fieldKey = (String)attributes.get("fieldKey");

		if (fieldKey != null) {
			setFieldKey(fieldKey);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String label = (String)attributes.get("label");

		if (label != null) {
			setLabel(label);
		}

		String placeholder = (String)attributes.get("placeholder");

		if (placeholder != null) {
			setPlaceholder(placeholder);
		}

		String helpText = (String)attributes.get("helpText");

		if (helpText != null) {
			setHelpText(helpText);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}

		String defaultValue = (String)attributes.get("defaultValue");

		if (defaultValue != null) {
			setDefaultValue(defaultValue);
		}

		String width = (String)attributes.get("width");

		if (width != null) {
			setWidth(width);
		}

		String validation = (String)attributes.get("validation");

		if (validation != null) {
			setValidation(validation);
		}

		String properties = (String)attributes.get("properties");

		if (properties != null) {
			setProperties(properties);
		}

		String conditionalRule = (String)attributes.get("conditionalRule");

		if (conditionalRule != null) {
			setConditionalRule(conditionalRule);
		}

		String presetKey = (String)attributes.get("presetKey");

		if (presetKey != null) {
			setPresetKey(presetKey);
		}

		Boolean personalData = (Boolean)attributes.get("personalData");

		if (personalData != null) {
			setPersonalData(personalData);
		}
	}

	@Override
	public FormField cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this form field.
	 *
	 * @return the company ID of this form field
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the conditional rule of this form field.
	 *
	 * @return the conditional rule of this form field
	 */
	@Override
	public String getConditionalRule() {
		return model.getConditionalRule();
	}

	/**
	 * Returns the create date of this form field.
	 *
	 * @return the create date of this form field
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the default value of this form field.
	 *
	 * @return the default value of this form field
	 */
	@Override
	public String getDefaultValue() {
		return model.getDefaultValue();
	}

	/**
	 * Returns the field key of this form field.
	 *
	 * @return the field key of this form field
	 */
	@Override
	public String getFieldKey() {
		return model.getFieldKey();
	}

	/**
	 * Returns the form field ID of this form field.
	 *
	 * @return the form field ID of this form field
	 */
	@Override
	public long getFormFieldId() {
		return model.getFormFieldId();
	}

	/**
	 * Returns the form version ID of this form field.
	 *
	 * @return the form version ID of this form field
	 */
	@Override
	public long getFormVersionId() {
		return model.getFormVersionId();
	}

	/**
	 * Returns the group ID of this form field.
	 *
	 * @return the group ID of this form field
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the help text of this form field.
	 *
	 * @return the help text of this form field
	 */
	@Override
	public String getHelpText() {
		return model.getHelpText();
	}

	/**
	 * Returns the label of this form field.
	 *
	 * @return the label of this form field
	 */
	@Override
	public String getLabel() {
		return model.getLabel();
	}

	/**
	 * Returns the modified date of this form field.
	 *
	 * @return the modified date of this form field
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this form field.
	 *
	 * @return the mvcc version of this form field
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the personal data of this form field.
	 *
	 * @return the personal data of this form field
	 */
	@Override
	public boolean getPersonalData() {
		return model.getPersonalData();
	}

	/**
	 * Returns the placeholder of this form field.
	 *
	 * @return the placeholder of this form field
	 */
	@Override
	public String getPlaceholder() {
		return model.getPlaceholder();
	}

	/**
	 * Returns the position of this form field.
	 *
	 * @return the position of this form field
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the preset key of this form field.
	 *
	 * @return the preset key of this form field
	 */
	@Override
	public String getPresetKey() {
		return model.getPresetKey();
	}

	/**
	 * Returns the primary key of this form field.
	 *
	 * @return the primary key of this form field
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the properties of this form field.
	 *
	 * @return the properties of this form field
	 */
	@Override
	public String getProperties() {
		return model.getProperties();
	}

	/**
	 * Returns the required of this form field.
	 *
	 * @return the required of this form field
	 */
	@Override
	public boolean getRequired() {
		return model.getRequired();
	}

	/**
	 * Returns the type of this form field.
	 *
	 * @return the type of this form field
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the validation of this form field.
	 *
	 * @return the validation of this form field
	 */
	@Override
	public String getValidation() {
		return model.getValidation();
	}

	/**
	 * Returns the width of this form field.
	 *
	 * @return the width of this form field
	 */
	@Override
	public String getWidth() {
		return model.getWidth();
	}

	/**
	 * Returns <code>true</code> if this form field is personal data.
	 *
	 * @return <code>true</code> if this form field is personal data; <code>false</code> otherwise
	 */
	@Override
	public boolean isPersonalData() {
		return model.isPersonalData();
	}

	/**
	 * Returns <code>true</code> if this form field is required.
	 *
	 * @return <code>true</code> if this form field is required; <code>false</code> otherwise
	 */
	@Override
	public boolean isRequired() {
		return model.isRequired();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this form field.
	 *
	 * @param companyId the company ID of this form field
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the conditional rule of this form field.
	 *
	 * @param conditionalRule the conditional rule of this form field
	 */
	@Override
	public void setConditionalRule(String conditionalRule) {
		model.setConditionalRule(conditionalRule);
	}

	/**
	 * Sets the create date of this form field.
	 *
	 * @param createDate the create date of this form field
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the default value of this form field.
	 *
	 * @param defaultValue the default value of this form field
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		model.setDefaultValue(defaultValue);
	}

	/**
	 * Sets the field key of this form field.
	 *
	 * @param fieldKey the field key of this form field
	 */
	@Override
	public void setFieldKey(String fieldKey) {
		model.setFieldKey(fieldKey);
	}

	/**
	 * Sets the form field ID of this form field.
	 *
	 * @param formFieldId the form field ID of this form field
	 */
	@Override
	public void setFormFieldId(long formFieldId) {
		model.setFormFieldId(formFieldId);
	}

	/**
	 * Sets the form version ID of this form field.
	 *
	 * @param formVersionId the form version ID of this form field
	 */
	@Override
	public void setFormVersionId(long formVersionId) {
		model.setFormVersionId(formVersionId);
	}

	/**
	 * Sets the group ID of this form field.
	 *
	 * @param groupId the group ID of this form field
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the help text of this form field.
	 *
	 * @param helpText the help text of this form field
	 */
	@Override
	public void setHelpText(String helpText) {
		model.setHelpText(helpText);
	}

	/**
	 * Sets the label of this form field.
	 *
	 * @param label the label of this form field
	 */
	@Override
	public void setLabel(String label) {
		model.setLabel(label);
	}

	/**
	 * Sets the modified date of this form field.
	 *
	 * @param modifiedDate the modified date of this form field
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this form field.
	 *
	 * @param mvccVersion the mvcc version of this form field
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets whether this form field is personal data.
	 *
	 * @param personalData the personal data of this form field
	 */
	@Override
	public void setPersonalData(boolean personalData) {
		model.setPersonalData(personalData);
	}

	/**
	 * Sets the placeholder of this form field.
	 *
	 * @param placeholder the placeholder of this form field
	 */
	@Override
	public void setPlaceholder(String placeholder) {
		model.setPlaceholder(placeholder);
	}

	/**
	 * Sets the position of this form field.
	 *
	 * @param position the position of this form field
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the preset key of this form field.
	 *
	 * @param presetKey the preset key of this form field
	 */
	@Override
	public void setPresetKey(String presetKey) {
		model.setPresetKey(presetKey);
	}

	/**
	 * Sets the primary key of this form field.
	 *
	 * @param primaryKey the primary key of this form field
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the properties of this form field.
	 *
	 * @param properties the properties of this form field
	 */
	@Override
	public void setProperties(String properties) {
		model.setProperties(properties);
	}

	/**
	 * Sets whether this form field is required.
	 *
	 * @param required the required of this form field
	 */
	@Override
	public void setRequired(boolean required) {
		model.setRequired(required);
	}

	/**
	 * Sets the type of this form field.
	 *
	 * @param type the type of this form field
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the validation of this form field.
	 *
	 * @param validation the validation of this form field
	 */
	@Override
	public void setValidation(String validation) {
		model.setValidation(validation);
	}

	/**
	 * Sets the width of this form field.
	 *
	 * @param width the width of this form field
	 */
	@Override
	public void setWidth(String width) {
		model.setWidth(width);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected FormFieldWrapper wrap(FormField formField) {
		return new FormFieldWrapper(formField);
	}

}