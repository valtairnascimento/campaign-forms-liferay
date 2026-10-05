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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FormFieldOption}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormFieldOption
 * @generated
 */
public class FormFieldOptionWrapper
	extends BaseModelWrapper<FormFieldOption>
	implements FormFieldOption, ModelWrapper<FormFieldOption> {

	public FormFieldOptionWrapper(FormFieldOption formFieldOption) {
		super(formFieldOption);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("formFieldOptionId", getFormFieldOptionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("formFieldId", getFormFieldId());
		attributes.put("label", getLabel());
		attributes.put("value", getValue());
		attributes.put("position", getPosition());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formFieldOptionId = (Long)attributes.get("formFieldOptionId");

		if (formFieldOptionId != null) {
			setFormFieldOptionId(formFieldOptionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long formFieldId = (Long)attributes.get("formFieldId");

		if (formFieldId != null) {
			setFormFieldId(formFieldId);
		}

		String label = (String)attributes.get("label");

		if (label != null) {
			setLabel(label);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}
	}

	@Override
	public FormFieldOption cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this form field option.
	 *
	 * @return the company ID of this form field option
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the form field ID of this form field option.
	 *
	 * @return the form field ID of this form field option
	 */
	@Override
	public long getFormFieldId() {
		return model.getFormFieldId();
	}

	/**
	 * Returns the form field option ID of this form field option.
	 *
	 * @return the form field option ID of this form field option
	 */
	@Override
	public long getFormFieldOptionId() {
		return model.getFormFieldOptionId();
	}

	/**
	 * Returns the label of this form field option.
	 *
	 * @return the label of this form field option
	 */
	@Override
	public String getLabel() {
		return model.getLabel();
	}

	/**
	 * Returns the mvcc version of this form field option.
	 *
	 * @return the mvcc version of this form field option
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the position of this form field option.
	 *
	 * @return the position of this form field option
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this form field option.
	 *
	 * @return the primary key of this form field option
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the value of this form field option.
	 *
	 * @return the value of this form field option
	 */
	@Override
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this form field option.
	 *
	 * @param companyId the company ID of this form field option
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the form field ID of this form field option.
	 *
	 * @param formFieldId the form field ID of this form field option
	 */
	@Override
	public void setFormFieldId(long formFieldId) {
		model.setFormFieldId(formFieldId);
	}

	/**
	 * Sets the form field option ID of this form field option.
	 *
	 * @param formFieldOptionId the form field option ID of this form field option
	 */
	@Override
	public void setFormFieldOptionId(long formFieldOptionId) {
		model.setFormFieldOptionId(formFieldOptionId);
	}

	/**
	 * Sets the label of this form field option.
	 *
	 * @param label the label of this form field option
	 */
	@Override
	public void setLabel(String label) {
		model.setLabel(label);
	}

	/**
	 * Sets the mvcc version of this form field option.
	 *
	 * @param mvccVersion the mvcc version of this form field option
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the position of this form field option.
	 *
	 * @param position the position of this form field option
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this form field option.
	 *
	 * @param primaryKey the primary key of this form field option
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the value of this form field option.
	 *
	 * @param value the value of this form field option
	 */
	@Override
	public void setValue(String value) {
		model.setValue(value);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected FormFieldOptionWrapper wrap(FormFieldOption formFieldOption) {
		return new FormFieldOptionWrapper(formFieldOption);
	}

}