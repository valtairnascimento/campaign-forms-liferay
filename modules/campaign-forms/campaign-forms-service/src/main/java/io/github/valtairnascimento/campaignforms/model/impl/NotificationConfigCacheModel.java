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

import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NotificationConfig in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class NotificationConfigCacheModel
	implements CacheModel<NotificationConfig>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NotificationConfigCacheModel)) {
			return false;
		}

		NotificationConfigCacheModel notificationConfigCacheModel =
			(NotificationConfigCacheModel)object;

		if ((notificationConfigId ==
				notificationConfigCacheModel.notificationConfigId) &&
			(mvccVersion == notificationConfigCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, notificationConfigId);

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
		StringBundler sb = new StringBundler(43);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", notificationConfigId=");
		sb.append(notificationConfigId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", channel=");
		sb.append(channel);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", recipients=");
		sb.append(recipients);
		sb.append(", cc=");
		sb.append(cc);
		sb.append(", bcc=");
		sb.append(bcc);
		sb.append(", fromName=");
		sb.append(fromName);
		sb.append(", replyTo=");
		sb.append(replyTo);
		sb.append(", subjectTemplate=");
		sb.append(subjectTemplate);
		sb.append(", bodyTemplate=");
		sb.append(bodyTemplate);
		sb.append(", settings=");
		sb.append(settings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NotificationConfig toEntityModel() {
		NotificationConfigImpl notificationConfigImpl =
			new NotificationConfigImpl();

		notificationConfigImpl.setMvccVersion(mvccVersion);
		notificationConfigImpl.setNotificationConfigId(notificationConfigId);
		notificationConfigImpl.setGroupId(groupId);
		notificationConfigImpl.setCompanyId(companyId);
		notificationConfigImpl.setUserId(userId);

		if (userName == null) {
			notificationConfigImpl.setUserName("");
		}
		else {
			notificationConfigImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notificationConfigImpl.setCreateDate(null);
		}
		else {
			notificationConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notificationConfigImpl.setModifiedDate(null);
		}
		else {
			notificationConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		notificationConfigImpl.setCampaignId(campaignId);

		if (name == null) {
			notificationConfigImpl.setName("");
		}
		else {
			notificationConfigImpl.setName(name);
		}

		if (eventType == null) {
			notificationConfigImpl.setEventType("");
		}
		else {
			notificationConfigImpl.setEventType(eventType);
		}

		if (channel == null) {
			notificationConfigImpl.setChannel("");
		}
		else {
			notificationConfigImpl.setChannel(channel);
		}

		notificationConfigImpl.setEnabled(enabled);

		if (recipients == null) {
			notificationConfigImpl.setRecipients("");
		}
		else {
			notificationConfigImpl.setRecipients(recipients);
		}

		if (cc == null) {
			notificationConfigImpl.setCc("");
		}
		else {
			notificationConfigImpl.setCc(cc);
		}

		if (bcc == null) {
			notificationConfigImpl.setBcc("");
		}
		else {
			notificationConfigImpl.setBcc(bcc);
		}

		if (fromName == null) {
			notificationConfigImpl.setFromName("");
		}
		else {
			notificationConfigImpl.setFromName(fromName);
		}

		if (replyTo == null) {
			notificationConfigImpl.setReplyTo("");
		}
		else {
			notificationConfigImpl.setReplyTo(replyTo);
		}

		if (subjectTemplate == null) {
			notificationConfigImpl.setSubjectTemplate("");
		}
		else {
			notificationConfigImpl.setSubjectTemplate(subjectTemplate);
		}

		if (bodyTemplate == null) {
			notificationConfigImpl.setBodyTemplate("");
		}
		else {
			notificationConfigImpl.setBodyTemplate(bodyTemplate);
		}

		if (settings == null) {
			notificationConfigImpl.setSettings("");
		}
		else {
			notificationConfigImpl.setSettings(settings);
		}

		notificationConfigImpl.resetOriginalValues();

		return notificationConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		notificationConfigId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();
		name = objectInput.readUTF();
		eventType = objectInput.readUTF();
		channel = objectInput.readUTF();

		enabled = objectInput.readBoolean();
		recipients = objectInput.readUTF();
		cc = objectInput.readUTF();
		bcc = objectInput.readUTF();
		fromName = objectInput.readUTF();
		replyTo = objectInput.readUTF();
		subjectTemplate = objectInput.readUTF();
		bodyTemplate = (String)objectInput.readObject();
		settings = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(notificationConfigId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(campaignId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (eventType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eventType);
		}

		if (channel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(channel);
		}

		objectOutput.writeBoolean(enabled);

		if (recipients == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(recipients);
		}

		if (cc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cc);
		}

		if (bcc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(bcc);
		}

		if (fromName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fromName);
		}

		if (replyTo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replyTo);
		}

		if (subjectTemplate == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subjectTemplate);
		}

		if (bodyTemplate == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(bodyTemplate);
		}

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}
	}

	public long mvccVersion;
	public long notificationConfigId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public String name;
	public String eventType;
	public String channel;
	public boolean enabled;
	public String recipients;
	public String cc;
	public String bcc;
	public String fromName;
	public String replyTo;
	public String subjectTemplate;
	public String bodyTemplate;
	public String settings;

}