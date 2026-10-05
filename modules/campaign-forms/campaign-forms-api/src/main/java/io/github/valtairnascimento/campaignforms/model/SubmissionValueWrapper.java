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

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SubmissionValue}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see SubmissionValue
 * @generated
 */
public class SubmissionValueWrapper
	extends BaseModelWrapper<SubmissionValue>
	implements ModelWrapper<SubmissionValue>, SubmissionValue {

	public SubmissionValueWrapper(SubmissionValue submissionValue) {
		super(submissionValue);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("submissionValueId", getSubmissionValueId());
		attributes.put("companyId", getCompanyId());
		attributes.put("campaignId", getCampaignId());
		attributes.put("submissionId", getSubmissionId());
		attributes.put("formFieldId", getFormFieldId());
		attributes.put("fieldKey", getFieldKey());
		attributes.put("valueType", getValueType());
		attributes.put("valueIndex", getValueIndex());
		attributes.put("stringValue", getStringValue());
		attributes.put("textValue", getTextValue());
		attributes.put("numberValue", getNumberValue());
		attributes.put("dateValue", getDateValue());
		attributes.put("booleanValue", isBooleanValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long submissionValueId = (Long)attributes.get("submissionValueId");

		if (submissionValueId != null) {
			setSubmissionValueId(submissionValueId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long submissionId = (Long)attributes.get("submissionId");

		if (submissionId != null) {
			setSubmissionId(submissionId);
		}

		Long formFieldId = (Long)attributes.get("formFieldId");

		if (formFieldId != null) {
			setFormFieldId(formFieldId);
		}

		String fieldKey = (String)attributes.get("fieldKey");

		if (fieldKey != null) {
			setFieldKey(fieldKey);
		}

		String valueType = (String)attributes.get("valueType");

		if (valueType != null) {
			setValueType(valueType);
		}

		Integer valueIndex = (Integer)attributes.get("valueIndex");

		if (valueIndex != null) {
			setValueIndex(valueIndex);
		}

		String stringValue = (String)attributes.get("stringValue");

		if (stringValue != null) {
			setStringValue(stringValue);
		}

		String textValue = (String)attributes.get("textValue");

		if (textValue != null) {
			setTextValue(textValue);
		}

		BigDecimal numberValue = (BigDecimal)attributes.get("numberValue");

		if (numberValue != null) {
			setNumberValue(numberValue);
		}

		Date dateValue = (Date)attributes.get("dateValue");

		if (dateValue != null) {
			setDateValue(dateValue);
		}

		Boolean booleanValue = (Boolean)attributes.get("booleanValue");

		if (booleanValue != null) {
			setBooleanValue(booleanValue);
		}
	}

	@Override
	public SubmissionValue cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the boolean value of this submission value.
	 *
	 * @return the boolean value of this submission value
	 */
	@Override
	public boolean getBooleanValue() {
		return model.getBooleanValue();
	}

	/**
	 * Returns the campaign ID of this submission value.
	 *
	 * @return the campaign ID of this submission value
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this submission value.
	 *
	 * @return the company ID of this submission value
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the date value of this submission value.
	 *
	 * @return the date value of this submission value
	 */
	@Override
	public Date getDateValue() {
		return model.getDateValue();
	}

	/**
	 * Returns the field key of this submission value.
	 *
	 * @return the field key of this submission value
	 */
	@Override
	public String getFieldKey() {
		return model.getFieldKey();
	}

	/**
	 * Returns the form field ID of this submission value.
	 *
	 * @return the form field ID of this submission value
	 */
	@Override
	public long getFormFieldId() {
		return model.getFormFieldId();
	}

	/**
	 * Returns the mvcc version of this submission value.
	 *
	 * @return the mvcc version of this submission value
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the number value of this submission value.
	 *
	 * @return the number value of this submission value
	 */
	@Override
	public BigDecimal getNumberValue() {
		return model.getNumberValue();
	}

	/**
	 * Returns the primary key of this submission value.
	 *
	 * @return the primary key of this submission value
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the string value of this submission value.
	 *
	 * @return the string value of this submission value
	 */
	@Override
	public String getStringValue() {
		return model.getStringValue();
	}

	/**
	 * Returns the submission ID of this submission value.
	 *
	 * @return the submission ID of this submission value
	 */
	@Override
	public long getSubmissionId() {
		return model.getSubmissionId();
	}

	/**
	 * Returns the submission value ID of this submission value.
	 *
	 * @return the submission value ID of this submission value
	 */
	@Override
	public long getSubmissionValueId() {
		return model.getSubmissionValueId();
	}

	/**
	 * Returns the text value of this submission value.
	 *
	 * @return the text value of this submission value
	 */
	@Override
	public String getTextValue() {
		return model.getTextValue();
	}

	/**
	 * Returns the value index of this submission value.
	 *
	 * @return the value index of this submission value
	 */
	@Override
	public int getValueIndex() {
		return model.getValueIndex();
	}

	/**
	 * Returns the value type of this submission value.
	 *
	 * @return the value type of this submission value
	 */
	@Override
	public String getValueType() {
		return model.getValueType();
	}

	/**
	 * Returns <code>true</code> if this submission value is boolean value.
	 *
	 * @return <code>true</code> if this submission value is boolean value; <code>false</code> otherwise
	 */
	@Override
	public boolean isBooleanValue() {
		return model.isBooleanValue();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this submission value is boolean value.
	 *
	 * @param booleanValue the boolean value of this submission value
	 */
	@Override
	public void setBooleanValue(boolean booleanValue) {
		model.setBooleanValue(booleanValue);
	}

	/**
	 * Sets the campaign ID of this submission value.
	 *
	 * @param campaignId the campaign ID of this submission value
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this submission value.
	 *
	 * @param companyId the company ID of this submission value
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the date value of this submission value.
	 *
	 * @param dateValue the date value of this submission value
	 */
	@Override
	public void setDateValue(Date dateValue) {
		model.setDateValue(dateValue);
	}

	/**
	 * Sets the field key of this submission value.
	 *
	 * @param fieldKey the field key of this submission value
	 */
	@Override
	public void setFieldKey(String fieldKey) {
		model.setFieldKey(fieldKey);
	}

	/**
	 * Sets the form field ID of this submission value.
	 *
	 * @param formFieldId the form field ID of this submission value
	 */
	@Override
	public void setFormFieldId(long formFieldId) {
		model.setFormFieldId(formFieldId);
	}

	/**
	 * Sets the mvcc version of this submission value.
	 *
	 * @param mvccVersion the mvcc version of this submission value
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the number value of this submission value.
	 *
	 * @param numberValue the number value of this submission value
	 */
	@Override
	public void setNumberValue(BigDecimal numberValue) {
		model.setNumberValue(numberValue);
	}

	/**
	 * Sets the primary key of this submission value.
	 *
	 * @param primaryKey the primary key of this submission value
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the string value of this submission value.
	 *
	 * @param stringValue the string value of this submission value
	 */
	@Override
	public void setStringValue(String stringValue) {
		model.setStringValue(stringValue);
	}

	/**
	 * Sets the submission ID of this submission value.
	 *
	 * @param submissionId the submission ID of this submission value
	 */
	@Override
	public void setSubmissionId(long submissionId) {
		model.setSubmissionId(submissionId);
	}

	/**
	 * Sets the submission value ID of this submission value.
	 *
	 * @param submissionValueId the submission value ID of this submission value
	 */
	@Override
	public void setSubmissionValueId(long submissionValueId) {
		model.setSubmissionValueId(submissionValueId);
	}

	/**
	 * Sets the text value of this submission value.
	 *
	 * @param textValue the text value of this submission value
	 */
	@Override
	public void setTextValue(String textValue) {
		model.setTextValue(textValue);
	}

	/**
	 * Sets the value index of this submission value.
	 *
	 * @param valueIndex the value index of this submission value
	 */
	@Override
	public void setValueIndex(int valueIndex) {
		model.setValueIndex(valueIndex);
	}

	/**
	 * Sets the value type of this submission value.
	 *
	 * @param valueType the value type of this submission value
	 */
	@Override
	public void setValueType(String valueType) {
		model.setValueType(valueType);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SubmissionValueWrapper wrap(SubmissionValue submissionValue) {
		return new SubmissionValueWrapper(submissionValue);
	}

}