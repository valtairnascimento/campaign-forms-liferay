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

import io.github.valtairnascimento.campaignforms.model.Campaign;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Campaign in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class CampaignCacheModel
	implements CacheModel<Campaign>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CampaignCacheModel)) {
			return false;
		}

		CampaignCacheModel campaignCacheModel = (CampaignCacheModel)object;

		if ((campaignId == campaignCacheModel.campaignId) &&
			(mvccVersion == campaignCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, campaignId);

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
		StringBundler sb = new StringBundler(53);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", campaignId=");
		sb.append(campaignId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", slug=");
		sb.append(slug);
		sb.append(", mode=");
		sb.append(mode);
		sb.append(", status=");
		sb.append(status);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", submissionLimit=");
		sb.append(submissionLimit);
		sb.append(", seriesKey=");
		sb.append(seriesKey);
		sb.append(", instanceKey=");
		sb.append(instanceKey);
		sb.append(", sourceCampaignId=");
		sb.append(sourceCampaignId);
		sb.append(", retentionDays=");
		sb.append(retentionDays);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append(", publishedDate=");
		sb.append(publishedDate);
		sb.append(", publishedByUserId=");
		sb.append(publishedByUserId);
		sb.append(", settings=");
		sb.append(settings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Campaign toEntityModel() {
		CampaignImpl campaignImpl = new CampaignImpl();

		campaignImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			campaignImpl.setUuid("");
		}
		else {
			campaignImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			campaignImpl.setExternalReferenceCode("");
		}
		else {
			campaignImpl.setExternalReferenceCode(externalReferenceCode);
		}

		campaignImpl.setCampaignId(campaignId);
		campaignImpl.setGroupId(groupId);
		campaignImpl.setCompanyId(companyId);
		campaignImpl.setUserId(userId);

		if (userName == null) {
			campaignImpl.setUserName("");
		}
		else {
			campaignImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			campaignImpl.setCreateDate(null);
		}
		else {
			campaignImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			campaignImpl.setModifiedDate(null);
		}
		else {
			campaignImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			campaignImpl.setName("");
		}
		else {
			campaignImpl.setName(name);
		}

		if (description == null) {
			campaignImpl.setDescription("");
		}
		else {
			campaignImpl.setDescription(description);
		}

		if (slug == null) {
			campaignImpl.setSlug("");
		}
		else {
			campaignImpl.setSlug(slug);
		}

		if (mode == null) {
			campaignImpl.setMode("");
		}
		else {
			campaignImpl.setMode(mode);
		}

		if (status == null) {
			campaignImpl.setStatus("");
		}
		else {
			campaignImpl.setStatus(status);
		}

		if (startDate == Long.MIN_VALUE) {
			campaignImpl.setStartDate(null);
		}
		else {
			campaignImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			campaignImpl.setEndDate(null);
		}
		else {
			campaignImpl.setEndDate(new Date(endDate));
		}

		campaignImpl.setSubmissionLimit(submissionLimit);

		if (seriesKey == null) {
			campaignImpl.setSeriesKey("");
		}
		else {
			campaignImpl.setSeriesKey(seriesKey);
		}

		if (instanceKey == null) {
			campaignImpl.setInstanceKey("");
		}
		else {
			campaignImpl.setInstanceKey(instanceKey);
		}

		campaignImpl.setSourceCampaignId(sourceCampaignId);
		campaignImpl.setRetentionDays(retentionDays);

		if (defaultLanguageId == null) {
			campaignImpl.setDefaultLanguageId("");
		}
		else {
			campaignImpl.setDefaultLanguageId(defaultLanguageId);
		}

		if (publishedDate == Long.MIN_VALUE) {
			campaignImpl.setPublishedDate(null);
		}
		else {
			campaignImpl.setPublishedDate(new Date(publishedDate));
		}

		campaignImpl.setPublishedByUserId(publishedByUserId);

		if (settings == null) {
			campaignImpl.setSettings("");
		}
		else {
			campaignImpl.setSettings(settings);
		}

		campaignImpl.resetOriginalValues();

		return campaignImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		campaignId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		slug = objectInput.readUTF();
		mode = objectInput.readUTF();
		status = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		submissionLimit = objectInput.readInt();
		seriesKey = objectInput.readUTF();
		instanceKey = objectInput.readUTF();

		sourceCampaignId = objectInput.readLong();

		retentionDays = objectInput.readInt();
		defaultLanguageId = objectInput.readUTF();
		publishedDate = objectInput.readLong();

		publishedByUserId = objectInput.readLong();
		settings = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(campaignId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (slug == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(slug);
		}

		if (mode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mode);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(submissionLimit);

		if (seriesKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(seriesKey);
		}

		if (instanceKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(instanceKey);
		}

		objectOutput.writeLong(sourceCampaignId);

		objectOutput.writeInt(retentionDays);

		if (defaultLanguageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(defaultLanguageId);
		}

		objectOutput.writeLong(publishedDate);

		objectOutput.writeLong(publishedByUserId);

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}
	}

	public long mvccVersion;
	public String uuid;
	public String externalReferenceCode;
	public long campaignId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String slug;
	public String mode;
	public String status;
	public long startDate;
	public long endDate;
	public int submissionLimit;
	public String seriesKey;
	public String instanceKey;
	public long sourceCampaignId;
	public int retentionDays;
	public String defaultLanguageId;
	public long publishedDate;
	public long publishedByUserId;
	public String settings;

}