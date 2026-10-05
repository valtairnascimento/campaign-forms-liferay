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
 * This class is a wrapper for {@link NotificationConfig}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see NotificationConfig
 * @generated
 */
public class NotificationConfigWrapper
	extends BaseModelWrapper<NotificationConfig>
	implements ModelWrapper<NotificationConfig>, NotificationConfig {

	public NotificationConfigWrapper(NotificationConfig notificationConfig) {
		super(notificationConfig);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("notificationConfigId", getNotificationConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("name", getName());
		attributes.put("eventType", getEventType());
		attributes.put("channel", getChannel());
		attributes.put("enabled", isEnabled());
		attributes.put("recipients", getRecipients());
		attributes.put("cc", getCc());
		attributes.put("bcc", getBcc());
		attributes.put("fromName", getFromName());
		attributes.put("replyTo", getReplyTo());
		attributes.put("subjectTemplate", getSubjectTemplate());
		attributes.put("bodyTemplate", getBodyTemplate());
		attributes.put("settings", getSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long notificationConfigId = (Long)attributes.get(
			"notificationConfigId");

		if (notificationConfigId != null) {
			setNotificationConfigId(notificationConfigId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String channel = (String)attributes.get("channel");

		if (channel != null) {
			setChannel(channel);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String recipients = (String)attributes.get("recipients");

		if (recipients != null) {
			setRecipients(recipients);
		}

		String cc = (String)attributes.get("cc");

		if (cc != null) {
			setCc(cc);
		}

		String bcc = (String)attributes.get("bcc");

		if (bcc != null) {
			setBcc(bcc);
		}

		String fromName = (String)attributes.get("fromName");

		if (fromName != null) {
			setFromName(fromName);
		}

		String replyTo = (String)attributes.get("replyTo");

		if (replyTo != null) {
			setReplyTo(replyTo);
		}

		String subjectTemplate = (String)attributes.get("subjectTemplate");

		if (subjectTemplate != null) {
			setSubjectTemplate(subjectTemplate);
		}

		String bodyTemplate = (String)attributes.get("bodyTemplate");

		if (bodyTemplate != null) {
			setBodyTemplate(bodyTemplate);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}
	}

	@Override
	public NotificationConfig cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the bcc of this notification config.
	 *
	 * @return the bcc of this notification config
	 */
	@Override
	public String getBcc() {
		return model.getBcc();
	}

	/**
	 * Returns the body template of this notification config.
	 *
	 * @return the body template of this notification config
	 */
	@Override
	public String getBodyTemplate() {
		return model.getBodyTemplate();
	}

	/**
	 * Returns the campaign ID of this notification config.
	 *
	 * @return the campaign ID of this notification config
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the cc of this notification config.
	 *
	 * @return the cc of this notification config
	 */
	@Override
	public String getCc() {
		return model.getCc();
	}

	/**
	 * Returns the channel of this notification config.
	 *
	 * @return the channel of this notification config
	 */
	@Override
	public String getChannel() {
		return model.getChannel();
	}

	/**
	 * Returns the company ID of this notification config.
	 *
	 * @return the company ID of this notification config
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this notification config.
	 *
	 * @return the create date of this notification config
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the enabled of this notification config.
	 *
	 * @return the enabled of this notification config
	 */
	@Override
	public boolean getEnabled() {
		return model.getEnabled();
	}

	/**
	 * Returns the event type of this notification config.
	 *
	 * @return the event type of this notification config
	 */
	@Override
	public String getEventType() {
		return model.getEventType();
	}

	/**
	 * Returns the from name of this notification config.
	 *
	 * @return the from name of this notification config
	 */
	@Override
	public String getFromName() {
		return model.getFromName();
	}

	/**
	 * Returns the group ID of this notification config.
	 *
	 * @return the group ID of this notification config
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this notification config.
	 *
	 * @return the modified date of this notification config
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this notification config.
	 *
	 * @return the mvcc version of this notification config
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this notification config.
	 *
	 * @return the name of this notification config
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the notification config ID of this notification config.
	 *
	 * @return the notification config ID of this notification config
	 */
	@Override
	public long getNotificationConfigId() {
		return model.getNotificationConfigId();
	}

	/**
	 * Returns the primary key of this notification config.
	 *
	 * @return the primary key of this notification config
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the recipients of this notification config.
	 *
	 * @return the recipients of this notification config
	 */
	@Override
	public String getRecipients() {
		return model.getRecipients();
	}

	/**
	 * Returns the reply to of this notification config.
	 *
	 * @return the reply to of this notification config
	 */
	@Override
	public String getReplyTo() {
		return model.getReplyTo();
	}

	/**
	 * Returns the settings of this notification config.
	 *
	 * @return the settings of this notification config
	 */
	@Override
	public String getSettings() {
		return model.getSettings();
	}

	/**
	 * Returns the subject template of this notification config.
	 *
	 * @return the subject template of this notification config
	 */
	@Override
	public String getSubjectTemplate() {
		return model.getSubjectTemplate();
	}

	/**
	 * Returns the user ID of this notification config.
	 *
	 * @return the user ID of this notification config
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this notification config.
	 *
	 * @return the user name of this notification config
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this notification config.
	 *
	 * @return the user uuid of this notification config
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this notification config is enabled.
	 *
	 * @return <code>true</code> if this notification config is enabled; <code>false</code> otherwise
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
	 * Sets the bcc of this notification config.
	 *
	 * @param bcc the bcc of this notification config
	 */
	@Override
	public void setBcc(String bcc) {
		model.setBcc(bcc);
	}

	/**
	 * Sets the body template of this notification config.
	 *
	 * @param bodyTemplate the body template of this notification config
	 */
	@Override
	public void setBodyTemplate(String bodyTemplate) {
		model.setBodyTemplate(bodyTemplate);
	}

	/**
	 * Sets the campaign ID of this notification config.
	 *
	 * @param campaignId the campaign ID of this notification config
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the cc of this notification config.
	 *
	 * @param cc the cc of this notification config
	 */
	@Override
	public void setCc(String cc) {
		model.setCc(cc);
	}

	/**
	 * Sets the channel of this notification config.
	 *
	 * @param channel the channel of this notification config
	 */
	@Override
	public void setChannel(String channel) {
		model.setChannel(channel);
	}

	/**
	 * Sets the company ID of this notification config.
	 *
	 * @param companyId the company ID of this notification config
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this notification config.
	 *
	 * @param createDate the create date of this notification config
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets whether this notification config is enabled.
	 *
	 * @param enabled the enabled of this notification config
	 */
	@Override
	public void setEnabled(boolean enabled) {
		model.setEnabled(enabled);
	}

	/**
	 * Sets the event type of this notification config.
	 *
	 * @param eventType the event type of this notification config
	 */
	@Override
	public void setEventType(String eventType) {
		model.setEventType(eventType);
	}

	/**
	 * Sets the from name of this notification config.
	 *
	 * @param fromName the from name of this notification config
	 */
	@Override
	public void setFromName(String fromName) {
		model.setFromName(fromName);
	}

	/**
	 * Sets the group ID of this notification config.
	 *
	 * @param groupId the group ID of this notification config
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this notification config.
	 *
	 * @param modifiedDate the modified date of this notification config
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this notification config.
	 *
	 * @param mvccVersion the mvcc version of this notification config
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this notification config.
	 *
	 * @param name the name of this notification config
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the notification config ID of this notification config.
	 *
	 * @param notificationConfigId the notification config ID of this notification config
	 */
	@Override
	public void setNotificationConfigId(long notificationConfigId) {
		model.setNotificationConfigId(notificationConfigId);
	}

	/**
	 * Sets the primary key of this notification config.
	 *
	 * @param primaryKey the primary key of this notification config
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the recipients of this notification config.
	 *
	 * @param recipients the recipients of this notification config
	 */
	@Override
	public void setRecipients(String recipients) {
		model.setRecipients(recipients);
	}

	/**
	 * Sets the reply to of this notification config.
	 *
	 * @param replyTo the reply to of this notification config
	 */
	@Override
	public void setReplyTo(String replyTo) {
		model.setReplyTo(replyTo);
	}

	/**
	 * Sets the settings of this notification config.
	 *
	 * @param settings the settings of this notification config
	 */
	@Override
	public void setSettings(String settings) {
		model.setSettings(settings);
	}

	/**
	 * Sets the subject template of this notification config.
	 *
	 * @param subjectTemplate the subject template of this notification config
	 */
	@Override
	public void setSubjectTemplate(String subjectTemplate) {
		model.setSubjectTemplate(subjectTemplate);
	}

	/**
	 * Sets the user ID of this notification config.
	 *
	 * @param userId the user ID of this notification config
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this notification config.
	 *
	 * @param userName the user name of this notification config
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this notification config.
	 *
	 * @param userUuid the user uuid of this notification config
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
	protected NotificationConfigWrapper wrap(
		NotificationConfig notificationConfig) {

		return new NotificationConfigWrapper(notificationConfig);
	}

}