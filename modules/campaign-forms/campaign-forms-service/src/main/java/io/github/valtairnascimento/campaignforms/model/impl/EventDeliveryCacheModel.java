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

package io.github.valtairnascimento.campaignforms.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import io.github.valtairnascimento.campaignforms.model.EventDelivery;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EventDelivery in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class EventDeliveryCacheModel
	implements CacheModel<EventDelivery>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventDeliveryCacheModel)) {
			return false;
		}

		EventDeliveryCacheModel eventDeliveryCacheModel =
			(EventDeliveryCacheModel)object;

		if ((eventDeliveryId == eventDeliveryCacheModel.eventDeliveryId) &&
			(mvccVersion == eventDeliveryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, eventDeliveryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", eventDeliveryId=");
		sb.append(eventDeliveryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", submissionId=");
		sb.append(submissionId);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", targetType=");
		sb.append(targetType);
		sb.append(", targetId=");
		sb.append(targetId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", attempts=");
		sb.append(attempts);
		sb.append(", nextAttemptDate=");
		sb.append(nextAttemptDate);
		sb.append(", lastErrorCode=");
		sb.append(lastErrorCode);
		sb.append(", lastErrorMessage=");
		sb.append(lastErrorMessage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EventDelivery toEntityModel() {
		EventDeliveryImpl eventDeliveryImpl = new EventDeliveryImpl();

		eventDeliveryImpl.setMvccVersion(mvccVersion);
		eventDeliveryImpl.setEventDeliveryId(eventDeliveryId);
		eventDeliveryImpl.setGroupId(groupId);
		eventDeliveryImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			eventDeliveryImpl.setCreateDate(null);
		}
		else {
			eventDeliveryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			eventDeliveryImpl.setModifiedDate(null);
		}
		else {
			eventDeliveryImpl.setModifiedDate(new Date(modifiedDate));
		}

		eventDeliveryImpl.setCampaignId(campaignId);
		eventDeliveryImpl.setSubmissionId(submissionId);

		if (eventType == null) {
			eventDeliveryImpl.setEventType("");
		}
		else {
			eventDeliveryImpl.setEventType(eventType);
		}

		if (targetType == null) {
			eventDeliveryImpl.setTargetType("");
		}
		else {
			eventDeliveryImpl.setTargetType(targetType);
		}

		eventDeliveryImpl.setTargetId(targetId);

		if (status == null) {
			eventDeliveryImpl.setStatus("");
		}
		else {
			eventDeliveryImpl.setStatus(status);
		}

		eventDeliveryImpl.setAttempts(attempts);

		if (nextAttemptDate == Long.MIN_VALUE) {
			eventDeliveryImpl.setNextAttemptDate(null);
		}
		else {
			eventDeliveryImpl.setNextAttemptDate(new Date(nextAttemptDate));
		}

		if (lastErrorCode == null) {
			eventDeliveryImpl.setLastErrorCode("");
		}
		else {
			eventDeliveryImpl.setLastErrorCode(lastErrorCode);
		}

		if (lastErrorMessage == null) {
			eventDeliveryImpl.setLastErrorMessage("");
		}
		else {
			eventDeliveryImpl.setLastErrorMessage(lastErrorMessage);
		}

		eventDeliveryImpl.resetOriginalValues();

		return eventDeliveryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		eventDeliveryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();

		submissionId = objectInput.readLong();
		eventType = objectInput.readUTF();
		targetType = objectInput.readUTF();

		targetId = objectInput.readLong();
		status = objectInput.readUTF();

		attempts = objectInput.readInt();
		nextAttemptDate = objectInput.readLong();
		lastErrorCode = objectInput.readUTF();
		lastErrorMessage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(eventDeliveryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(campaignId);

		objectOutput.writeLong(submissionId);

		if (eventType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eventType);
		}

		if (targetType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(targetType);
		}

		objectOutput.writeLong(targetId);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeInt(attempts);
		objectOutput.writeLong(nextAttemptDate);

		if (lastErrorCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastErrorCode);
		}

		if (lastErrorMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastErrorMessage);
		}
	}

	public long mvccVersion;
	public long eventDeliveryId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public long submissionId;
	public String eventType;
	public String targetType;
	public long targetId;
	public String status;
	public int attempts;
	public long nextAttemptDate;
	public String lastErrorCode;
	public String lastErrorMessage;

}