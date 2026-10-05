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
 * This class is a wrapper for {@link Form}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see Form
 * @generated
 */
public class FormWrapper
	extends BaseModelWrapper<Form> implements Form, ModelWrapper<Form> {

	public FormWrapper(Form form) {
		super(form);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("formId", getFormId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("formKey", getFormKey());
		attributes.put("name", getName());
		attributes.put("draftVersionId", getDraftVersionId());
		attributes.put("publishedVersionId", getPublishedVersionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formId = (Long)attributes.get("formId");

		if (formId != null) {
			setFormId(formId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		String formKey = (String)attributes.get("formKey");

		if (formKey != null) {
			setFormKey(formKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long draftVersionId = (Long)attributes.get("draftVersionId");

		if (draftVersionId != null) {
			setDraftVersionId(draftVersionId);
		}

		Long publishedVersionId = (Long)attributes.get("publishedVersionId");

		if (publishedVersionId != null) {
			setPublishedVersionId(publishedVersionId);
		}
	}

	@Override
	public Form cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this form.
	 *
	 * @return the campaign ID of this form
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this form.
	 *
	 * @return the company ID of this form
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this form.
	 *
	 * @return the create date of this form
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the draft version ID of this form.
	 *
	 * @return the draft version ID of this form
	 */
	@Override
	public long getDraftVersionId() {
		return model.getDraftVersionId();
	}

	/**
	 * Returns the form ID of this form.
	 *
	 * @return the form ID of this form
	 */
	@Override
	public long getFormId() {
		return model.getFormId();
	}

	/**
	 * Returns the form key of this form.
	 *
	 * @return the form key of this form
	 */
	@Override
	public String getFormKey() {
		return model.getFormKey();
	}

	/**
	 * Returns the group ID of this form.
	 *
	 * @return the group ID of this form
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this form.
	 *
	 * @return the modified date of this form
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this form.
	 *
	 * @return the mvcc version of this form
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this form.
	 *
	 * @return the name of this form
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this form.
	 *
	 * @return the primary key of this form
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the published version ID of this form.
	 *
	 * @return the published version ID of this form
	 */
	@Override
	public long getPublishedVersionId() {
		return model.getPublishedVersionId();
	}

	/**
	 * Returns the user ID of this form.
	 *
	 * @return the user ID of this form
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this form.
	 *
	 * @return the user name of this form
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this form.
	 *
	 * @return the user uuid of this form
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this form.
	 *
	 * @param campaignId the campaign ID of this form
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this form.
	 *
	 * @param companyId the company ID of this form
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this form.
	 *
	 * @param createDate the create date of this form
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the draft version ID of this form.
	 *
	 * @param draftVersionId the draft version ID of this form
	 */
	@Override
	public void setDraftVersionId(long draftVersionId) {
		model.setDraftVersionId(draftVersionId);
	}

	/**
	 * Sets the form ID of this form.
	 *
	 * @param formId the form ID of this form
	 */
	@Override
	public void setFormId(long formId) {
		model.setFormId(formId);
	}

	/**
	 * Sets the form key of this form.
	 *
	 * @param formKey the form key of this form
	 */
	@Override
	public void setFormKey(String formKey) {
		model.setFormKey(formKey);
	}

	/**
	 * Sets the group ID of this form.
	 *
	 * @param groupId the group ID of this form
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this form.
	 *
	 * @param modifiedDate the modified date of this form
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this form.
	 *
	 * @param mvccVersion the mvcc version of this form
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this form.
	 *
	 * @param name the name of this form
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this form.
	 *
	 * @param primaryKey the primary key of this form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the published version ID of this form.
	 *
	 * @param publishedVersionId the published version ID of this form
	 */
	@Override
	public void setPublishedVersionId(long publishedVersionId) {
		model.setPublishedVersionId(publishedVersionId);
	}

	/**
	 * Sets the user ID of this form.
	 *
	 * @param userId the user ID of this form
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this form.
	 *
	 * @param userName the user name of this form
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this form.
	 *
	 * @param userUuid the user uuid of this form
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected FormWrapper wrap(Form form) {
		return new FormWrapper(form);
	}

}