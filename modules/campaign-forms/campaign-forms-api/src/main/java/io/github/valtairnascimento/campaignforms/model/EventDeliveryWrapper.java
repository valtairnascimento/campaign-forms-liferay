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
 * This class is a wrapper for {@link EventDelivery}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see EventDelivery
 * @generated
 */
public class EventDeliveryWrapper
	extends BaseModelWrapper<EventDelivery>
	implements EventDelivery, ModelWrapper<EventDelivery> {

	public EventDeliveryWrapper(EventDelivery eventDelivery) {
		super(eventDelivery);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("eventDeliveryId", getEventDeliveryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("submissionId", getSubmissionId());
		attributes.put("eventType", getEventType());
		attributes.put("targetType", getTargetType());
		attributes.put("targetId", getTargetId());
		attributes.put("status", getStatus());
		attributes.put("attempts", getAttempts());
		attributes.put("nextAttemptDate", getNextAttemptDate());
		attributes.put("lastErrorCode", getLastErrorCode());
		attributes.put("lastErrorMessage", getLastErrorMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long eventDeliveryId = (Long)attributes.get("eventDeliveryId");

		if (eventDeliveryId != null) {
			setEventDeliveryId(eventDeliveryId);
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

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long submissionId = (Long)attributes.get("submissionId");

		if (submissionId != null) {
			setSubmissionId(submissionId);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String targetType = (String)attributes.get("targetType");

		if (targetType != null) {
			setTargetType(targetType);
		}

		Long targetId = (Long)attributes.get("targetId");

		if (targetId != null) {
			setTargetId(targetId);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer attempts = (Integer)attributes.get("attempts");

		if (attempts != null) {
			setAttempts(attempts);
		}

		Date nextAttemptDate = (Date)attributes.get("nextAttemptDate");

		if (nextAttemptDate != null) {
			setNextAttemptDate(nextAttemptDate);
		}

		String lastErrorCode = (String)attributes.get("lastErrorCode");

		if (lastErrorCode != null) {
			setLastErrorCode(lastErrorCode);
		}

		String lastErrorMessage = (String)attributes.get("lastErrorMessage");

		if (lastErrorMessage != null) {
			setLastErrorMessage(lastErrorMessage);
		}
	}

	@Override
	public EventDelivery cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the attempts of this event delivery.
	 *
	 * @return the attempts of this event delivery
	 */
	@Override
	public int getAttempts() {
		return model.getAttempts();
	}

	/**
	 * Returns the campaign ID of this event delivery.
	 *
	 * @return the campaign ID of this event delivery
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this event delivery.
	 *
	 * @return the company ID of this event delivery
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this event delivery.
	 *
	 * @return the create date of this event delivery
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the event delivery ID of this event delivery.
	 *
	 * @return the event delivery ID of this event delivery
	 */
	@Override
	public long getEventDeliveryId() {
		return model.getEventDeliveryId();
	}

	/**
	 * Returns the event type of this event delivery.
	 *
	 * @return the event type of this event delivery
	 */
	@Override
	public String getEventType() {
		return model.getEventType();
	}

	/**
	 * Returns the group ID of this event delivery.
	 *
	 * @return the group ID of this event delivery
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last error code of this event delivery.
	 *
	 * @return the last error code of this event delivery
	 */
	@Override
	public String getLastErrorCode() {
		return model.getLastErrorCode();
	}

	/**
	 * Returns the last error message of this event delivery.
	 *
	 * @return the last error message of this event delivery
	 */
	@Override
	public String getLastErrorMessage() {
		return model.getLastErrorMessage();
	}

	/**
	 * Returns the modified date of this event delivery.
	 *
	 * @return the modified date of this event delivery
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this event delivery.
	 *
	 * @return the mvcc version of this event delivery
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the next attempt date of this event delivery.
	 *
	 * @return the next attempt date of this event delivery
	 */
	@Override
	public Date getNextAttemptDate() {
		return model.getNextAttemptDate();
	}

	/**
	 * Returns the primary key of this event delivery.
	 *
	 * @return the primary key of this event delivery
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this event delivery.
	 *
	 * @return the status of this event delivery
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the submission ID of this event delivery.
	 *
	 * @return the submission ID of this event delivery
	 */
	@Override
	public long getSubmissionId() {
		return model.getSubmissionId();
	}

	/**
	 * Returns the target ID of this event delivery.
	 *
	 * @return the target ID of this event delivery
	 */
	@Override
	public long getTargetId() {
		return model.getTargetId();
	}

	/**
	 * Returns the target type of this event delivery.
	 *
	 * @return the target type of this event delivery
	 */
	@Override
	public String getTargetType() {
		return model.getTargetType();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the attempts of this event delivery.
	 *
	 * @param attempts the attempts of this event delivery
	 */
	@Override
	public void setAttempts(int attempts) {
		model.setAttempts(attempts);
	}

	/**
	 * Sets the campaign ID of this event delivery.
	 *
	 * @param campaignId the campaign ID of this event delivery
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this event delivery.
	 *
	 * @param companyId the company ID of this event delivery
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this event delivery.
	 *
	 * @param createDate the create date of this event delivery
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the event delivery ID of this event delivery.
	 *
	 * @param eventDeliveryId the event delivery ID of this event delivery
	 */
	@Override
	public void setEventDeliveryId(long eventDeliveryId) {
		model.setEventDeliveryId(eventDeliveryId);
	}

	/**
	 * Sets the event type of this event delivery.
	 *
	 * @param eventType the event type of this event delivery
	 */
	@Override
	public void setEventType(String eventType) {
		model.setEventType(eventType);
	}

	/**
	 * Sets the group ID of this event delivery.
	 *
	 * @param groupId the group ID of this event delivery
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last error code of this event delivery.
	 *
	 * @param lastErrorCode the last error code of this event delivery
	 */
	@Override
	public void setLastErrorCode(String lastErrorCode) {
		model.setLastErrorCode(lastErrorCode);
	}

	/**
	 * Sets the last error message of this event delivery.
	 *
	 * @param lastErrorMessage the last error message of this event delivery
	 */
	@Override
	public void setLastErrorMessage(String lastErrorMessage) {
		model.setLastErrorMessage(lastErrorMessage);
	}

	/**
	 * Sets the modified date of this event delivery.
	 *
	 * @param modifiedDate the modified date of this event delivery
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this event delivery.
	 *
	 * @param mvccVersion the mvcc version of this event delivery
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the next attempt date of this event delivery.
	 *
	 * @param nextAttemptDate the next attempt date of this event delivery
	 */
	@Override
	public void setNextAttemptDate(Date nextAttemptDate) {
		model.setNextAttemptDate(nextAttemptDate);
	}

	/**
	 * Sets the primary key of this event delivery.
	 *
	 * @param primaryKey the primary key of this event delivery
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this event delivery.
	 *
	 * @param status the status of this event delivery
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the submission ID of this event delivery.
	 *
	 * @param submissionId the submission ID of this event delivery
	 */
	@Override
	public void setSubmissionId(long submissionId) {
		model.setSubmissionId(submissionId);
	}

	/**
	 * Sets the target ID of this event delivery.
	 *
	 * @param targetId the target ID of this event delivery
	 */
	@Override
	public void setTargetId(long targetId) {
		model.setTargetId(targetId);
	}

	/**
	 * Sets the target type of this event delivery.
	 *
	 * @param targetType the target type of this event delivery
	 */
	@Override
	public void setTargetType(String targetType) {
		model.setTargetType(targetType);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected EventDeliveryWrapper wrap(EventDelivery eventDelivery) {
		return new EventDeliveryWrapper(eventDelivery);
	}

}