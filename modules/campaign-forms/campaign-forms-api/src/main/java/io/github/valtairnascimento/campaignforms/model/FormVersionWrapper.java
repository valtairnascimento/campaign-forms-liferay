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
 * This class is a wrapper for {@link FormVersion}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormVersion
 * @generated
 */
public class FormVersionWrapper
	extends BaseModelWrapper<FormVersion>
	implements FormVersion, ModelWrapper<FormVersion> {

	public FormVersionWrapper(FormVersion formVersion) {
		super(formVersion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("formVersionId", getFormVersionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formId", getFormId());
		attributes.put("version", getVersion());
		attributes.put("status", getStatus());
		attributes.put("submitLabel", getSubmitLabel());
		attributes.put("successTitle", getSuccessTitle());
		attributes.put("successMessage", getSuccessMessage());
		attributes.put("redirectUrl", getRedirectUrl());
		attributes.put("captchaEnabled", isCaptchaEnabled());
		attributes.put("requireAuthentication", isRequireAuthentication());
		attributes.put("consentRequired", isConsentRequired());
		attributes.put("consentText", getConsentText());
		attributes.put("consentVersion", getConsentVersion());
		attributes.put("consentURL", getConsentURL());
		attributes.put(
			"saveIncompleteSubmission", isSaveIncompleteSubmission());
		attributes.put("settings", getSettings());
		attributes.put("publishedDate", getPublishedDate());
		attributes.put("publishedByUserId", getPublishedByUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formVersionId = (Long)attributes.get("formVersionId");

		if (formVersionId != null) {
			setFormVersionId(formVersionId);
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

		Long formId = (Long)attributes.get("formId");

		if (formId != null) {
			setFormId(formId);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String submitLabel = (String)attributes.get("submitLabel");

		if (submitLabel != null) {
			setSubmitLabel(submitLabel);
		}

		String successTitle = (String)attributes.get("successTitle");

		if (successTitle != null) {
			setSuccessTitle(successTitle);
		}

		String successMessage = (String)attributes.get("successMessage");

		if (successMessage != null) {
			setSuccessMessage(successMessage);
		}

		String redirectUrl = (String)attributes.get("redirectUrl");

		if (redirectUrl != null) {
			setRedirectUrl(redirectUrl);
		}

		Boolean captchaEnabled = (Boolean)attributes.get("captchaEnabled");

		if (captchaEnabled != null) {
			setCaptchaEnabled(captchaEnabled);
		}

		Boolean requireAuthentication = (Boolean)attributes.get(
			"requireAuthentication");

		if (requireAuthentication != null) {
			setRequireAuthentication(requireAuthentication);
		}

		Boolean consentRequired = (Boolean)attributes.get("consentRequired");

		if (consentRequired != null) {
			setConsentRequired(consentRequired);
		}

		String consentText = (String)attributes.get("consentText");

		if (consentText != null) {
			setConsentText(consentText);
		}

		String consentVersion = (String)attributes.get("consentVersion");

		if (consentVersion != null) {
			setConsentVersion(consentVersion);
		}

		String consentURL = (String)attributes.get("consentURL");

		if (consentURL != null) {
			setConsentURL(consentURL);
		}

		Boolean saveIncompleteSubmission = (Boolean)attributes.get(
			"saveIncompleteSubmission");

		if (saveIncompleteSubmission != null) {
			setSaveIncompleteSubmission(saveIncompleteSubmission);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}

		Date publishedDate = (Date)attributes.get("publishedDate");

		if (publishedDate != null) {
			setPublishedDate(publishedDate);
		}

		Long publishedByUserId = (Long)attributes.get("publishedByUserId");

		if (publishedByUserId != null) {
			setPublishedByUserId(publishedByUserId);
		}
	}

	@Override
	public FormVersion cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the captcha enabled of this form version.
	 *
	 * @return the captcha enabled of this form version
	 */
	@Override
	public boolean getCaptchaEnabled() {
		return model.getCaptchaEnabled();
	}

	/**
	 * Returns the company ID of this form version.
	 *
	 * @return the company ID of this form version
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the consent required of this form version.
	 *
	 * @return the consent required of this form version
	 */
	@Override
	public boolean getConsentRequired() {
		return model.getConsentRequired();
	}

	/**
	 * Returns the consent text of this form version.
	 *
	 * @return the consent text of this form version
	 */
	@Override
	public String getConsentText() {
		return model.getConsentText();
	}

	/**
	 * Returns the consent url of this form version.
	 *
	 * @return the consent url of this form version
	 */
	@Override
	public String getConsentURL() {
		return model.getConsentURL();
	}

	/**
	 * Returns the consent version of this form version.
	 *
	 * @return the consent version of this form version
	 */
	@Override
	public String getConsentVersion() {
		return model.getConsentVersion();
	}

	/**
	 * Returns the create date of this form version.
	 *
	 * @return the create date of this form version
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the form ID of this form version.
	 *
	 * @return the form ID of this form version
	 */
	@Override
	public long getFormId() {
		return model.getFormId();
	}

	/**
	 * Returns the form version ID of this form version.
	 *
	 * @return the form version ID of this form version
	 */
	@Override
	public long getFormVersionId() {
		return model.getFormVersionId();
	}

	/**
	 * Returns the group ID of this form version.
	 *
	 * @return the group ID of this form version
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this form version.
	 *
	 * @return the modified date of this form version
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this form version.
	 *
	 * @return the mvcc version of this form version
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this form version.
	 *
	 * @return the primary key of this form version
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the published by user ID of this form version.
	 *
	 * @return the published by user ID of this form version
	 */
	@Override
	public long getPublishedByUserId() {
		return model.getPublishedByUserId();
	}

	/**
	 * Returns the published by user uuid of this form version.
	 *
	 * @return the published by user uuid of this form version
	 */
	@Override
	public String getPublishedByUserUuid() {
		return model.getPublishedByUserUuid();
	}

	/**
	 * Returns the published date of this form version.
	 *
	 * @return the published date of this form version
	 */
	@Override
	public Date getPublishedDate() {
		return model.getPublishedDate();
	}

	/**
	 * Returns the redirect url of this form version.
	 *
	 * @return the redirect url of this form version
	 */
	@Override
	public String getRedirectUrl() {
		return model.getRedirectUrl();
	}

	/**
	 * Returns the require authentication of this form version.
	 *
	 * @return the require authentication of this form version
	 */
	@Override
	public boolean getRequireAuthentication() {
		return model.getRequireAuthentication();
	}

	/**
	 * Returns the save incomplete submission of this form version.
	 *
	 * @return the save incomplete submission of this form version
	 */
	@Override
	public boolean getSaveIncompleteSubmission() {
		return model.getSaveIncompleteSubmission();
	}

	/**
	 * Returns the settings of this form version.
	 *
	 * @return the settings of this form version
	 */
	@Override
	public String getSettings() {
		return model.getSettings();
	}

	/**
	 * Returns the status of this form version.
	 *
	 * @return the status of this form version
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the submit label of this form version.
	 *
	 * @return the submit label of this form version
	 */
	@Override
	public String getSubmitLabel() {
		return model.getSubmitLabel();
	}

	/**
	 * Returns the success message of this form version.
	 *
	 * @return the success message of this form version
	 */
	@Override
	public String getSuccessMessage() {
		return model.getSuccessMessage();
	}

	/**
	 * Returns the success title of this form version.
	 *
	 * @return the success title of this form version
	 */
	@Override
	public String getSuccessTitle() {
		return model.getSuccessTitle();
	}

	/**
	 * Returns the user ID of this form version.
	 *
	 * @return the user ID of this form version
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this form version.
	 *
	 * @return the user name of this form version
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this form version.
	 *
	 * @return the user uuid of this form version
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version of this form version.
	 *
	 * @return the version of this form version
	 */
	@Override
	public int getVersion() {
		return model.getVersion();
	}

	/**
	 * Returns <code>true</code> if this form version is captcha enabled.
	 *
	 * @return <code>true</code> if this form version is captcha enabled; <code>false</code> otherwise
	 */
	@Override
	public boolean isCaptchaEnabled() {
		return model.isCaptchaEnabled();
	}

	/**
	 * Returns <code>true</code> if this form version is consent required.
	 *
	 * @return <code>true</code> if this form version is consent required; <code>false</code> otherwise
	 */
	@Override
	public boolean isConsentRequired() {
		return model.isConsentRequired();
	}

	/**
	 * Returns <code>true</code> if this form version is require authentication.
	 *
	 * @return <code>true</code> if this form version is require authentication; <code>false</code> otherwise
	 */
	@Override
	public boolean isRequireAuthentication() {
		return model.isRequireAuthentication();
	}

	/**
	 * Returns <code>true</code> if this form version is save incomplete submission.
	 *
	 * @return <code>true</code> if this form version is save incomplete submission; <code>false</code> otherwise
	 */
	@Override
	public boolean isSaveIncompleteSubmission() {
		return model.isSaveIncompleteSubmission();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this form version is captcha enabled.
	 *
	 * @param captchaEnabled the captcha enabled of this form version
	 */
	@Override
	public void setCaptchaEnabled(boolean captchaEnabled) {
		model.setCaptchaEnabled(captchaEnabled);
	}

	/**
	 * Sets the company ID of this form version.
	 *
	 * @param companyId the company ID of this form version
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets whether this form version is consent required.
	 *
	 * @param consentRequired the consent required of this form version
	 */
	@Override
	public void setConsentRequired(boolean consentRequired) {
		model.setConsentRequired(consentRequired);
	}

	/**
	 * Sets the consent text of this form version.
	 *
	 * @param consentText the consent text of this form version
	 */
	@Override
	public void setConsentText(String consentText) {
		model.setConsentText(consentText);
	}

	/**
	 * Sets the consent url of this form version.
	 *
	 * @param consentURL the consent url of this form version
	 */
	@Override
	public void setConsentURL(String consentURL) {
		model.setConsentURL(consentURL);
	}

	/**
	 * Sets the consent version of this form version.
	 *
	 * @param consentVersion the consent version of this form version
	 */
	@Override
	public void setConsentVersion(String consentVersion) {
		model.setConsentVersion(consentVersion);
	}

	/**
	 * Sets the create date of this form version.
	 *
	 * @param createDate the create date of this form version
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the form ID of this form version.
	 *
	 * @param formId the form ID of this form version
	 */
	@Override
	public void setFormId(long formId) {
		model.setFormId(formId);
	}

	/**
	 * Sets the form version ID of this form version.
	 *
	 * @param formVersionId the form version ID of this form version
	 */
	@Override
	public void setFormVersionId(long formVersionId) {
		model.setFormVersionId(formVersionId);
	}

	/**
	 * Sets the group ID of this form version.
	 *
	 * @param groupId the group ID of this form version
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this form version.
	 *
	 * @param modifiedDate the modified date of this form version
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this form version.
	 *
	 * @param mvccVersion the mvcc version of this form version
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this form version.
	 *
	 * @param primaryKey the primary key of this form version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the published by user ID of this form version.
	 *
	 * @param publishedByUserId the published by user ID of this form version
	 */
	@Override
	public void setPublishedByUserId(long publishedByUserId) {
		model.setPublishedByUserId(publishedByUserId);
	}

	/**
	 * Sets the published by user uuid of this form version.
	 *
	 * @param publishedByUserUuid the published by user uuid of this form version
	 */
	@Override
	public void setPublishedByUserUuid(String publishedByUserUuid) {
		model.setPublishedByUserUuid(publishedByUserUuid);
	}

	/**
	 * Sets the published date of this form version.
	 *
	 * @param publishedDate the published date of this form version
	 */
	@Override
	public void setPublishedDate(Date publishedDate) {
		model.setPublishedDate(publishedDate);
	}

	/**
	 * Sets the redirect url of this form version.
	 *
	 * @param redirectUrl the redirect url of this form version
	 */
	@Override
	public void setRedirectUrl(String redirectUrl) {
		model.setRedirectUrl(redirectUrl);
	}

	/**
	 * Sets whether this form version is require authentication.
	 *
	 * @param requireAuthentication the require authentication of this form version
	 */
	@Override
	public void setRequireAuthentication(boolean requireAuthentication) {
		model.setRequireAuthentication(requireAuthentication);
	}

	/**
	 * Sets whether this form version is save incomplete submission.
	 *
	 * @param saveIncompleteSubmission the save incomplete submission of this form version
	 */
	@Override
	public void setSaveIncompleteSubmission(boolean saveIncompleteSubmission) {
		model.setSaveIncompleteSubmission(saveIncompleteSubmission);
	}

	/**
	 * Sets the settings of this form version.
	 *
	 * @param settings the settings of this form version
	 */
	@Override
	public void setSettings(String settings) {
		model.setSettings(settings);
	}

	/**
	 * Sets the status of this form version.
	 *
	 * @param status the status of this form version
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the submit label of this form version.
	 *
	 * @param submitLabel the submit label of this form version
	 */
	@Override
	public void setSubmitLabel(String submitLabel) {
		model.setSubmitLabel(submitLabel);
	}

	/**
	 * Sets the success message of this form version.
	 *
	 * @param successMessage the success message of this form version
	 */
	@Override
	public void setSuccessMessage(String successMessage) {
		model.setSuccessMessage(successMessage);
	}

	/**
	 * Sets the success title of this form version.
	 *
	 * @param successTitle the success title of this form version
	 */
	@Override
	public void setSuccessTitle(String successTitle) {
		model.setSuccessTitle(successTitle);
	}

	/**
	 * Sets the user ID of this form version.
	 *
	 * @param userId the user ID of this form version
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this form version.
	 *
	 * @param userName the user name of this form version
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this form version.
	 *
	 * @param userUuid the user uuid of this form version
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version of this form version.
	 *
	 * @param version the version of this form version
	 */
	@Override
	public void setVersion(int version) {
		model.setVersion(version);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected FormVersionWrapper wrap(FormVersion formVersion) {
		return new FormVersionWrapper(formVersion);
	}

}