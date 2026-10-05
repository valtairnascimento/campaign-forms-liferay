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

import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IntegrationConfig in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class IntegrationConfigCacheModel
	implements CacheModel<IntegrationConfig>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof IntegrationConfigCacheModel)) {
			return false;
		}

		IntegrationConfigCacheModel integrationConfigCacheModel =
			(IntegrationConfigCacheModel)object;

		if ((integrationConfigId ==
				integrationConfigCacheModel.integrationConfigId) &&
			(mvccVersion == integrationConfigCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, integrationConfigId);

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
		StringBundler sb = new StringBundler(31);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", integrationConfigId=");
		sb.append(integrationConfigId);
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
		sb.append(", providerKey=");
		sb.append(providerKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", eventTypes=");
		sb.append(eventTypes);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", encryptedSecrets=");
		sb.append(encryptedSecrets);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IntegrationConfig toEntityModel() {
		IntegrationConfigImpl integrationConfigImpl =
			new IntegrationConfigImpl();

		integrationConfigImpl.setMvccVersion(mvccVersion);
		integrationConfigImpl.setIntegrationConfigId(integrationConfigId);
		integrationConfigImpl.setGroupId(groupId);
		integrationConfigImpl.setCompanyId(companyId);
		integrationConfigImpl.setUserId(userId);

		if (userName == null) {
			integrationConfigImpl.setUserName("");
		}
		else {
			integrationConfigImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			integrationConfigImpl.setCreateDate(null);
		}
		else {
			integrationConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			integrationConfigImpl.setModifiedDate(null);
		}
		else {
			integrationConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		integrationConfigImpl.setCampaignId(campaignId);

		if (providerKey == null) {
			integrationConfigImpl.setProviderKey("");
		}
		else {
			integrationConfigImpl.setProviderKey(providerKey);
		}

		if (name == null) {
			integrationConfigImpl.setName("");
		}
		else {
			integrationConfigImpl.setName(name);
		}

		integrationConfigImpl.setEnabled(enabled);

		if (eventTypes == null) {
			integrationConfigImpl.setEventTypes("");
		}
		else {
			integrationConfigImpl.setEventTypes(eventTypes);
		}

		if (settings == null) {
			integrationConfigImpl.setSettings("");
		}
		else {
			integrationConfigImpl.setSettings(settings);
		}

		if (encryptedSecrets == null) {
			integrationConfigImpl.setEncryptedSecrets("");
		}
		else {
			integrationConfigImpl.setEncryptedSecrets(encryptedSecrets);
		}

		integrationConfigImpl.resetOriginalValues();

		return integrationConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		integrationConfigId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();
		providerKey = objectInput.readUTF();
		name = objectInput.readUTF();

		enabled = objectInput.readBoolean();
		eventTypes = objectInput.readUTF();
		settings = (String)objectInput.readObject();
		encryptedSecrets = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(integrationConfigId);

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

		if (providerKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(providerKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(enabled);

		if (eventTypes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eventTypes);
		}

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}

		if (encryptedSecrets == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(encryptedSecrets);
		}
	}

	public long mvccVersion;
	public long integrationConfigId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public String providerKey;
	public String name;
	public boolean enabled;
	public String eventTypes;
	public String settings;
	public String encryptedSecrets;

}