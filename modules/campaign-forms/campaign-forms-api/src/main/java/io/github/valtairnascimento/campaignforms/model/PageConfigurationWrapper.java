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
 * This class is a wrapper for {@link PageConfiguration}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageConfiguration
 * @generated
 */
public class PageConfigurationWrapper
	extends BaseModelWrapper<PageConfiguration>
	implements ModelWrapper<PageConfiguration>, PageConfiguration {

	public PageConfigurationWrapper(PageConfiguration pageConfiguration) {
		super(pageConfiguration);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("pageConfigurationId", getPageConfigurationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("stage", getStage());
		attributes.put("templateKey", getTemplateKey());
		attributes.put("themeKey", getThemeKey());
		attributes.put("cssClass", getCssClass());
		attributes.put("seoTitle", getSeoTitle());
		attributes.put("seoDescription", getSeoDescription());
		attributes.put("canonicalUrl", getCanonicalUrl());
		attributes.put("noIndex", isNoIndex());
		attributes.put("ogTitle", getOgTitle());
		attributes.put("ogDescription", getOgDescription());
		attributes.put("ogImageFileEntryId", getOgImageFileEntryId());
		attributes.put("settings", getSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long pageConfigurationId = (Long)attributes.get("pageConfigurationId");

		if (pageConfigurationId != null) {
			setPageConfigurationId(pageConfigurationId);
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

		String stage = (String)attributes.get("stage");

		if (stage != null) {
			setStage(stage);
		}

		String templateKey = (String)attributes.get("templateKey");

		if (templateKey != null) {
			setTemplateKey(templateKey);
		}

		String themeKey = (String)attributes.get("themeKey");

		if (themeKey != null) {
			setThemeKey(themeKey);
		}

		String cssClass = (String)attributes.get("cssClass");

		if (cssClass != null) {
			setCssClass(cssClass);
		}

		String seoTitle = (String)attributes.get("seoTitle");

		if (seoTitle != null) {
			setSeoTitle(seoTitle);
		}

		String seoDescription = (String)attributes.get("seoDescription");

		if (seoDescription != null) {
			setSeoDescription(seoDescription);
		}

		String canonicalUrl = (String)attributes.get("canonicalUrl");

		if (canonicalUrl != null) {
			setCanonicalUrl(canonicalUrl);
		}

		Boolean noIndex = (Boolean)attributes.get("noIndex");

		if (noIndex != null) {
			setNoIndex(noIndex);
		}

		String ogTitle = (String)attributes.get("ogTitle");

		if (ogTitle != null) {
			setOgTitle(ogTitle);
		}

		String ogDescription = (String)attributes.get("ogDescription");

		if (ogDescription != null) {
			setOgDescription(ogDescription);
		}

		Long ogImageFileEntryId = (Long)attributes.get("ogImageFileEntryId");

		if (ogImageFileEntryId != null) {
			setOgImageFileEntryId(ogImageFileEntryId);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}
	}

	@Override
	public PageConfiguration cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this page configuration.
	 *
	 * @return the campaign ID of this page configuration
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the canonical url of this page configuration.
	 *
	 * @return the canonical url of this page configuration
	 */
	@Override
	public String getCanonicalUrl() {
		return model.getCanonicalUrl();
	}

	/**
	 * Returns the company ID of this page configuration.
	 *
	 * @return the company ID of this page configuration
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this page configuration.
	 *
	 * @return the create date of this page configuration
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the css class of this page configuration.
	 *
	 * @return the css class of this page configuration
	 */
	@Override
	public String getCssClass() {
		return model.getCssClass();
	}

	/**
	 * Returns the group ID of this page configuration.
	 *
	 * @return the group ID of this page configuration
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this page configuration.
	 *
	 * @return the modified date of this page configuration
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this page configuration.
	 *
	 * @return the mvcc version of this page configuration
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the no index of this page configuration.
	 *
	 * @return the no index of this page configuration
	 */
	@Override
	public boolean getNoIndex() {
		return model.getNoIndex();
	}

	/**
	 * Returns the og description of this page configuration.
	 *
	 * @return the og description of this page configuration
	 */
	@Override
	public String getOgDescription() {
		return model.getOgDescription();
	}

	/**
	 * Returns the og image file entry ID of this page configuration.
	 *
	 * @return the og image file entry ID of this page configuration
	 */
	@Override
	public long getOgImageFileEntryId() {
		return model.getOgImageFileEntryId();
	}

	/**
	 * Returns the og title of this page configuration.
	 *
	 * @return the og title of this page configuration
	 */
	@Override
	public String getOgTitle() {
		return model.getOgTitle();
	}

	/**
	 * Returns the page configuration ID of this page configuration.
	 *
	 * @return the page configuration ID of this page configuration
	 */
	@Override
	public long getPageConfigurationId() {
		return model.getPageConfigurationId();
	}

	/**
	 * Returns the primary key of this page configuration.
	 *
	 * @return the primary key of this page configuration
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the seo description of this page configuration.
	 *
	 * @return the seo description of this page configuration
	 */
	@Override
	public String getSeoDescription() {
		return model.getSeoDescription();
	}

	/**
	 * Returns the seo title of this page configuration.
	 *
	 * @return the seo title of this page configuration
	 */
	@Override
	public String getSeoTitle() {
		return model.getSeoTitle();
	}

	/**
	 * Returns the settings of this page configuration.
	 *
	 * @return the settings of this page configuration
	 */
	@Override
	public String getSettings() {
		return model.getSettings();
	}

	/**
	 * Returns the stage of this page configuration.
	 *
	 * @return the stage of this page configuration
	 */
	@Override
	public String getStage() {
		return model.getStage();
	}

	/**
	 * Returns the template key of this page configuration.
	 *
	 * @return the template key of this page configuration
	 */
	@Override
	public String getTemplateKey() {
		return model.getTemplateKey();
	}

	/**
	 * Returns the theme key of this page configuration.
	 *
	 * @return the theme key of this page configuration
	 */
	@Override
	public String getThemeKey() {
		return model.getThemeKey();
	}

	/**
	 * Returns the user ID of this page configuration.
	 *
	 * @return the user ID of this page configuration
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this page configuration.
	 *
	 * @return the user name of this page configuration
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this page configuration.
	 *
	 * @return the user uuid of this page configuration
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this page configuration is no index.
	 *
	 * @return <code>true</code> if this page configuration is no index; <code>false</code> otherwise
	 */
	@Override
	public boolean isNoIndex() {
		return model.isNoIndex();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this page configuration.
	 *
	 * @param campaignId the campaign ID of this page configuration
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the canonical url of this page configuration.
	 *
	 * @param canonicalUrl the canonical url of this page configuration
	 */
	@Override
	public void setCanonicalUrl(String canonicalUrl) {
		model.setCanonicalUrl(canonicalUrl);
	}

	/**
	 * Sets the company ID of this page configuration.
	 *
	 * @param companyId the company ID of this page configuration
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this page configuration.
	 *
	 * @param createDate the create date of this page configuration
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the css class of this page configuration.
	 *
	 * @param cssClass the css class of this page configuration
	 */
	@Override
	public void setCssClass(String cssClass) {
		model.setCssClass(cssClass);
	}

	/**
	 * Sets the group ID of this page configuration.
	 *
	 * @param groupId the group ID of this page configuration
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this page configuration.
	 *
	 * @param modifiedDate the modified date of this page configuration
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this page configuration.
	 *
	 * @param mvccVersion the mvcc version of this page configuration
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets whether this page configuration is no index.
	 *
	 * @param noIndex the no index of this page configuration
	 */
	@Override
	public void setNoIndex(boolean noIndex) {
		model.setNoIndex(noIndex);
	}

	/**
	 * Sets the og description of this page configuration.
	 *
	 * @param ogDescription the og description of this page configuration
	 */
	@Override
	public void setOgDescription(String ogDescription) {
		model.setOgDescription(ogDescription);
	}

	/**
	 * Sets the og image file entry ID of this page configuration.
	 *
	 * @param ogImageFileEntryId the og image file entry ID of this page configuration
	 */
	@Override
	public void setOgImageFileEntryId(long ogImageFileEntryId) {
		model.setOgImageFileEntryId(ogImageFileEntryId);
	}

	/**
	 * Sets the og title of this page configuration.
	 *
	 * @param ogTitle the og title of this page configuration
	 */
	@Override
	public void setOgTitle(String ogTitle) {
		model.setOgTitle(ogTitle);
	}

	/**
	 * Sets the page configuration ID of this page configuration.
	 *
	 * @param pageConfigurationId the page configuration ID of this page configuration
	 */
	@Override
	public void setPageConfigurationId(long pageConfigurationId) {
		model.setPageConfigurationId(pageConfigurationId);
	}

	/**
	 * Sets the primary key of this page configuration.
	 *
	 * @param primaryKey the primary key of this page configuration
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the seo description of this page configuration.
	 *
	 * @param seoDescription the seo description of this page configuration
	 */
	@Override
	public void setSeoDescription(String seoDescription) {
		model.setSeoDescription(seoDescription);
	}

	/**
	 * Sets the seo title of this page configuration.
	 *
	 * @param seoTitle the seo title of this page configuration
	 */
	@Override
	public void setSeoTitle(String seoTitle) {
		model.setSeoTitle(seoTitle);
	}

	/**
	 * Sets the settings of this page configuration.
	 *
	 * @param settings the settings of this page configuration
	 */
	@Override
	public void setSettings(String settings) {
		model.setSettings(settings);
	}

	/**
	 * Sets the stage of this page configuration.
	 *
	 * @param stage the stage of this page configuration
	 */
	@Override
	public void setStage(String stage) {
		model.setStage(stage);
	}

	/**
	 * Sets the template key of this page configuration.
	 *
	 * @param templateKey the template key of this page configuration
	 */
	@Override
	public void setTemplateKey(String templateKey) {
		model.setTemplateKey(templateKey);
	}

	/**
	 * Sets the theme key of this page configuration.
	 *
	 * @param themeKey the theme key of this page configuration
	 */
	@Override
	public void setThemeKey(String themeKey) {
		model.setThemeKey(themeKey);
	}

	/**
	 * Sets the user ID of this page configuration.
	 *
	 * @param userId the user ID of this page configuration
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this page configuration.
	 *
	 * @param userName the user name of this page configuration
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this page configuration.
	 *
	 * @param userUuid the user uuid of this page configuration
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
	protected PageConfigurationWrapper wrap(
		PageConfiguration pageConfiguration) {

		return new PageConfigurationWrapper(pageConfiguration);
	}

}