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
 * This class is a wrapper for {@link IntegrationConfig}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see IntegrationConfig
 * @generated
 */
public class IntegrationConfigWrapper
	extends BaseModelWrapper<IntegrationConfig>
	implements IntegrationConfig, ModelWrapper<IntegrationConfig> {

	public IntegrationConfigWrapper(IntegrationConfig integrationConfig) {
		super(integrationConfig);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("integrationConfigId", getIntegrationConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("providerKey", getProviderKey());
		attributes.put("name", getName());
		attributes.put("enabled", isEnabled());
		attributes.put("eventTypes", getEventTypes());
		attributes.put("settings", getSettings());
		attributes.put("encryptedSecrets", getEncryptedSecrets());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long integrationConfigId = (Long)attributes.get("integrationConfigId");

		if (integrationConfigId != null) {
			setIntegrationConfigId(integrationConfigId);
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

		String providerKey = (String)attributes.get("providerKey");

		if (providerKey != null) {
			setProviderKey(providerKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String eventTypes = (String)attributes.get("eventTypes");

		if (eventTypes != null) {
			setEventTypes(eventTypes);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}

		String encryptedSecrets = (String)attributes.get("encryptedSecrets");

		if (encryptedSecrets != null) {
			setEncryptedSecrets(encryptedSecrets);
		}
	}

	@Override
	public IntegrationConfig cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this integration config.
	 *
	 * @return the campaign ID of this integration config
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this integration config.
	 *
	 * @return the company ID of this integration config
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this integration config.
	 *
	 * @return the create date of this integration config
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the enabled of this integration config.
	 *
	 * @return the enabled of this integration config
	 */
	@Override
	public boolean getEnabled() {
		return model.getEnabled();
	}

	/**
	 * Returns the encrypted secrets of this integration config.
	 *
	 * @return the encrypted secrets of this integration config
	 */
	@Override
	public String getEncryptedSecrets() {
		return model.getEncryptedSecrets();
	}

	/**
	 * Returns the event types of this integration config.
	 *
	 * @return the event types of this integration config
	 */
	@Override
	public String getEventTypes() {
		return model.getEventTypes();
	}

	/**
	 * Returns the group ID of this integration config.
	 *
	 * @return the group ID of this integration config
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the integration config ID of this integration config.
	 *
	 * @return the integration config ID of this integration config
	 */
	@Override
	public long getIntegrationConfigId() {
		return model.getIntegrationConfigId();
	}

	/**
	 * Returns the modified date of this integration config.
	 *
	 * @return the modified date of this integration config
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this integration config.
	 *
	 * @return the mvcc version of this integration config
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this integration config.
	 *
	 * @return the name of this integration config
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this integration config.
	 *
	 * @return the primary key of this integration config
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the provider key of this integration config.
	 *
	 * @return the provider key of this integration config
	 */
	@Override
	public String getProviderKey() {
		return model.getProviderKey();
	}

	/**
	 * Returns the settings of this integration config.
	 *
	 * @return the settings of this integration config
	 */
	@Override
	public String getSettings() {
		return model.getSettings();
	}

	/**
	 * Returns the user ID of this integration config.
	 *
	 * @return the user ID of this integration config
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this integration config.
	 *
	 * @return the user name of this integration config
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this integration config.
	 *
	 * @return the user uuid of this integration config
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this integration config is enabled.
	 *
	 * @return <code>true</code> if this integration config is enabled; <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return model.isEnabled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this integration config.
	 *
	 * @param campaignId the campaign ID of this integration config
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this integration config.
	 *
	 * @param companyId the company ID of this integration config
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this integration config.
	 *
	 * @param createDate the create date of this integration config
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets whether this integration config is enabled.
	 *
	 * @param enabled the enabled of this integration config
	 */
	@Override
	public void setEnabled(boolean enabled) {
		model.setEnabled(enabled);
	}

	/**
	 * Sets the encrypted secrets of this integration config.
	 *
	 * @param encryptedSecrets the encrypted secrets of this integration config
	 */
	@Override
	public void setEncryptedSecrets(String encryptedSecrets) {
		model.setEncryptedSecrets(encryptedSecrets);
	}

	/**
	 * Sets the event types of this integration config.
	 *
	 * @param eventTypes the event types of this integration config
	 */
	@Override
	public void setEventTypes(String eventTypes) {
		model.setEventTypes(eventTypes);
	}

	/**
	 * Sets the group ID of this integration config.
	 *
	 * @param groupId the group ID of this integration config
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the integration config ID of this integration config.
	 *
	 * @param integrationConfigId the integration config ID of this integration config
	 */
	@Override
	public void setIntegrationConfigId(long integrationConfigId) {
		model.setIntegrationConfigId(integrationConfigId);
	}

	/**
	 * Sets the modified date of this integration config.
	 *
	 * @param modifiedDate the modified date of this integration config
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this integration config.
	 *
	 * @param mvccVersion the mvcc version of this integration config
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this integration config.
	 *
	 * @param name the name of this integration config
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this integration config.
	 *
	 * @param primaryKey the primary key of this integration config
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the provider key of this integration config.
	 *
	 * @param providerKey the provider key of this integration config
	 */
	@Override
	public void setProviderKey(String providerKey) {
		model.setProviderKey(providerKey);
	}

	/**
	 * Sets the settings of this integration config.
	 *
	 * @param settings the settings of this integration config
	 */
	@Override
	public void setSettings(String settings) {
		model.setSettings(settings);
	}

	/**
	 * Sets the user ID of this integration config.
	 *
	 * @param userId the user ID of this integration config
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this integration config.
	 *
	 * @param userName the user name of this integration config
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this integration config.
	 *
	 * @param userUuid the user uuid of this integration config
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
	protected IntegrationConfigWrapper wrap(
		IntegrationConfig integrationConfig) {

		return new IntegrationConfigWrapper(integrationConfig);
	}

}