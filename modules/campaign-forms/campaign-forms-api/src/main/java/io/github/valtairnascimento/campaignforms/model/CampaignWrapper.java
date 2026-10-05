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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Campaign}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see Campaign
 * @generated
 */
public class CampaignWrapper
	extends BaseModelWrapper<Campaign>
	implements Campaign, ModelWrapper<Campaign> {

	public CampaignWrapper(Campaign campaign) {
		super(campaign);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("campaignId", getCampaignId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("slug", getSlug());
		attributes.put("mode", getMode());
		attributes.put("status", getStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("submissionLimit", getSubmissionLimit());
		attributes.put("seriesKey", getSeriesKey());
		attributes.put("instanceKey", getInstanceKey());
		attributes.put("sourceCampaignId", getSourceCampaignId());
		attributes.put("retentionDays", getRetentionDays());
		attributes.put("defaultLanguageId", getDefaultLanguageId());
		attributes.put("publishedDate", getPublishedDate());
		attributes.put("publishedByUserId", getPublishedByUserId());
		attributes.put("settings", getSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String externalReferenceCode = (String)attributes.get(
			"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String slug = (String)attributes.get("slug");

		if (slug != null) {
			setSlug(slug);
		}

		String mode = (String)attributes.get("mode");

		if (mode != null) {
			setMode(mode);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer submissionLimit = (Integer)attributes.get("submissionLimit");

		if (submissionLimit != null) {
			setSubmissionLimit(submissionLimit);
		}

		String seriesKey = (String)attributes.get("seriesKey");

		if (seriesKey != null) {
			setSeriesKey(seriesKey);
		}

		String instanceKey = (String)attributes.get("instanceKey");

		if (instanceKey != null) {
			setInstanceKey(instanceKey);
		}

		Long sourceCampaignId = (Long)attributes.get("sourceCampaignId");

		if (sourceCampaignId != null) {
			setSourceCampaignId(sourceCampaignId);
		}

		Integer retentionDays = (Integer)attributes.get("retentionDays");

		if (retentionDays != null) {
			setRetentionDays(retentionDays);
		}

		String defaultLanguageId = (String)attributes.get("defaultLanguageId");

		if (defaultLanguageId != null) {
			setDefaultLanguageId(defaultLanguageId);
		}

		Date publishedDate = (Date)attributes.get("publishedDate");

		if (publishedDate != null) {
			setPublishedDate(publishedDate);
		}

		Long publishedByUserId = (Long)attributes.get("publishedByUserId");

		if (publishedByUserId != null) {
			setPublishedByUserId(publishedByUserId);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}
	}

	@Override
	public Campaign cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this campaign.
	 *
	 * @return the campaign ID of this campaign
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this campaign.
	 *
	 * @return the company ID of this campaign
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this campaign.
	 *
	 * @return the create date of this campaign
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the default language ID of this campaign.
	 *
	 * @return the default language ID of this campaign
	 */
	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this campaign.
	 *
	 * @return the description of this campaign
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the end date of this campaign.
	 *
	 * @return the end date of this campaign
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the external reference code of this campaign.
	 *
	 * @return the external reference code of this campaign
	 */
	@Override
	public String getExternalReferenceCode() {
		return model.getExternalReferenceCode();
	}

	/**
	 * Returns the group ID of this campaign.
	 *
	 * @return the group ID of this campaign
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the instance key of this campaign.
	 *
	 * @return the instance key of this campaign
	 */
	@Override
	public String getInstanceKey() {
		return model.getInstanceKey();
	}

	/**
	 * Returns the mode of this campaign.
	 *
	 * @return the mode of this campaign
	 */
	@Override
	public String getMode() {
		return model.getMode();
	}

	/**
	 * Returns the modified date of this campaign.
	 *
	 * @return the modified date of this campaign
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this campaign.
	 *
	 * @return the mvcc version of this campaign
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this campaign.
	 *
	 * @return the name of this campaign
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this campaign.
	 *
	 * @return the primary key of this campaign
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the published by user ID of this campaign.
	 *
	 * @return the published by user ID of this campaign
	 */
	@Override
	public long getPublishedByUserId() {
		return model.getPublishedByUserId();
	}

	/**
	 * Returns the published by user uuid of this campaign.
	 *
	 * @return the published by user uuid of this campaign
	 */
	@Override
	public String getPublishedByUserUuid() {
		return model.getPublishedByUserUuid();
	}

	/**
	 * Returns the published date of this campaign.
	 *
	 * @return the published date of this campaign
	 */
	@Override
	public Date getPublishedDate() {
		return model.getPublishedDate();
	}

	/**
	 * Returns the retention days of this campaign.
	 *
	 * @return the retention days of this campaign
	 */
	@Override
	public int getRetentionDays() {
		return model.getRetentionDays();
	}

	/**
	 * Returns the series key of this campaign.
	 *
	 * @return the series key of this campaign
	 */
	@Override
	public String getSeriesKey() {
		return model.getSeriesKey();
	}

	/**
	 * Returns the settings of this campaign.
	 *
	 * @return the settings of this campaign
	 */
	@Override
	public String getSettings() {
		return model.getSettings();
	}

	/**
	 * Returns the slug of this campaign.
	 *
	 * @return the slug of this campaign
	 */
	@Override
	public String getSlug() {
		return model.getSlug();
	}

	/**
	 * Returns the source campaign ID of this campaign.
	 *
	 * @return the source campaign ID of this campaign
	 */
	@Override
	public long getSourceCampaignId() {
		return model.getSourceCampaignId();
	}

	/**
	 * Returns the start date of this campaign.
	 *
	 * @return the start date of this campaign
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the status of this campaign.
	 *
	 * @return the status of this campaign
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the submission limit of this campaign.
	 *
	 * @return the submission limit of this campaign
	 */
	@Override
	public int getSubmissionLimit() {
		return model.getSubmissionLimit();
	}

	/**
	 * Returns the user ID of this campaign.
	 *
	 * @return the user ID of this campaign
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this campaign.
	 *
	 * @return the user name of this campaign
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this campaign.
	 *
	 * @return the user uuid of this campaign
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this campaign.
	 *
	 * @return the uuid of this campaign
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this campaign.
	 *
	 * @param campaignId the campaign ID of this campaign
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this campaign.
	 *
	 * @param companyId the company ID of this campaign
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this campaign.
	 *
	 * @param createDate the create date of this campaign
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the default language ID of this campaign.
	 *
	 * @param defaultLanguageId the default language ID of this campaign
	 */
	@Override
	public void setDefaultLanguageId(String defaultLanguageId) {
		model.setDefaultLanguageId(defaultLanguageId);
	}

	/**
	 * Sets the description of this campaign.
	 *
	 * @param description the description of this campaign
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the end date of this campaign.
	 *
	 * @param endDate the end date of this campaign
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the external reference code of this campaign.
	 *
	 * @param externalReferenceCode the external reference code of this campaign
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		model.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the group ID of this campaign.
	 *
	 * @param groupId the group ID of this campaign
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the instance key of this campaign.
	 *
	 * @param instanceKey the instance key of this campaign
	 */
	@Override
	public void setInstanceKey(String instanceKey) {
		model.setInstanceKey(instanceKey);
	}

	/**
	 * Sets the mode of this campaign.
	 *
	 * @param mode the mode of this campaign
	 */
	@Override
	public void setMode(String mode) {
		model.setMode(mode);
	}

	/**
	 * Sets the modified date of this campaign.
	 *
	 * @param modifiedDate the modified date of this campaign
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this campaign.
	 *
	 * @param mvccVersion the mvcc version of this campaign
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this campaign.
	 *
	 * @param name the name of this campaign
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this campaign.
	 *
	 * @param primaryKey the primary key of this campaign
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the published by user ID of this campaign.
	 *
	 * @param publishedByUserId the published by user ID of this campaign
	 */
	@Override
	public void setPublishedByUserId(long publishedByUserId) {
		model.setPublishedByUserId(publishedByUserId);
	}

	/**
	 * Sets the published by user uuid of this campaign.
	 *
	 * @param publishedByUserUuid the published by user uuid of this campaign
	 */
	@Override
	public void setPublishedByUserUuid(String publishedByUserUuid) {
		model.setPublishedByUserUuid(publishedByUserUuid);
	}

	/**
	 * Sets the published date of this campaign.
	 *
	 * @param publishedDate the published date of this campaign
	 */
	@Override
	public void setPublishedDate(Date publishedDate) {
		model.setPublishedDate(publishedDate);
	}

	/**
	 * Sets the retention days of this campaign.
	 *
	 * @param retentionDays the retention days of this campaign
	 */
	@Override
	public void setRetentionDays(int retentionDays) {
		model.setRetentionDays(retentionDays);
	}

	/**
	 * Sets the series key of this campaign.
	 *
	 * @param seriesKey the series key of this campaign
	 */
	@Override
	public void setSeriesKey(String seriesKey) {
		model.setSeriesKey(seriesKey);
	}

	/**
	 * Sets the settings of this campaign.
	 *
	 * @param settings the settings of this campaign
	 */
	@Override
	public void setSettings(String settings) {
		model.setSettings(settings);
	}

	/**
	 * Sets the slug of this campaign.
	 *
	 * @param slug the slug of this campaign
	 */
	@Override
	public void setSlug(String slug) {
		model.setSlug(slug);
	}

	/**
	 * Sets the source campaign ID of this campaign.
	 *
	 * @param sourceCampaignId the source campaign ID of this campaign
	 */
	@Override
	public void setSourceCampaignId(long sourceCampaignId) {
		model.setSourceCampaignId(sourceCampaignId);
	}

	/**
	 * Sets the start date of this campaign.
	 *
	 * @param startDate the start date of this campaign
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this campaign.
	 *
	 * @param status the status of this campaign
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the submission limit of this campaign.
	 *
	 * @param submissionLimit the submission limit of this campaign
	 */
	@Override
	public void setSubmissionLimit(int submissionLimit) {
		model.setSubmissionLimit(submissionLimit);
	}

	/**
	 * Sets the user ID of this campaign.
	 *
	 * @param userId the user ID of this campaign
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this campaign.
	 *
	 * @param userName the user name of this campaign
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this campaign.
	 *
	 * @param userUuid the user uuid of this campaign
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this campaign.
	 *
	 * @param uuid the uuid of this campaign
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected CampaignWrapper wrap(Campaign campaign) {
		return new CampaignWrapper(campaign);
	}

}