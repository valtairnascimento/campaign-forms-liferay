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

import io.github.valtairnascimento.campaignforms.model.DataFeed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DataFeed in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class DataFeedCacheModel
	implements CacheModel<DataFeed>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DataFeedCacheModel)) {
			return false;
		}

		DataFeedCacheModel dataFeedCacheModel = (DataFeedCacheModel)object;

		if ((dataFeedId == dataFeedCacheModel.dataFeedId) &&
			(mvccVersion == dataFeedCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, dataFeedId);

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
		StringBundler sb = new StringBundler(39);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", dataFeedId=");
		sb.append(dataFeedId);
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
		sb.append(", feedKey=");
		sb.append(feedKey);
		sb.append(", tokenHash=");
		sb.append(tokenHash);
		sb.append(", tokenHint=");
		sb.append(tokenHint);
		sb.append(", name=");
		sb.append(name);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", seriesKey=");
		sb.append(seriesKey);
		sb.append(", format=");
		sb.append(format);
		sb.append(", includeMetadata=");
		sb.append(includeMetadata);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastAccessDate=");
		sb.append(lastAccessDate);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataFeed toEntityModel() {
		DataFeedImpl dataFeedImpl = new DataFeedImpl();

		dataFeedImpl.setMvccVersion(mvccVersion);
		dataFeedImpl.setDataFeedId(dataFeedId);
		dataFeedImpl.setGroupId(groupId);
		dataFeedImpl.setCompanyId(companyId);
		dataFeedImpl.setUserId(userId);

		if (userName == null) {
			dataFeedImpl.setUserName("");
		}
		else {
			dataFeedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dataFeedImpl.setCreateDate(null);
		}
		else {
			dataFeedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dataFeedImpl.setModifiedDate(null);
		}
		else {
			dataFeedImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (feedKey == null) {
			dataFeedImpl.setFeedKey("");
		}
		else {
			dataFeedImpl.setFeedKey(feedKey);
		}

		if (tokenHash == null) {
			dataFeedImpl.setTokenHash("");
		}
		else {
			dataFeedImpl.setTokenHash(tokenHash);
		}

		if (tokenHint == null) {
			dataFeedImpl.setTokenHint("");
		}
		else {
			dataFeedImpl.setTokenHint(tokenHint);
		}

		if (name == null) {
			dataFeedImpl.setName("");
		}
		else {
			dataFeedImpl.setName(name);
		}

		dataFeedImpl.setCampaignId(campaignId);

		if (seriesKey == null) {
			dataFeedImpl.setSeriesKey("");
		}
		else {
			dataFeedImpl.setSeriesKey(seriesKey);
		}

		if (format == null) {
			dataFeedImpl.setFormat("");
		}
		else {
			dataFeedImpl.setFormat(format);
		}

		dataFeedImpl.setIncludeMetadata(includeMetadata);

		if (expirationDate == Long.MIN_VALUE) {
			dataFeedImpl.setExpirationDate(null);
		}
		else {
			dataFeedImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastAccessDate == Long.MIN_VALUE) {
			dataFeedImpl.setLastAccessDate(null);
		}
		else {
			dataFeedImpl.setLastAccessDate(new Date(lastAccessDate));
		}

		dataFeedImpl.setActive(active);

		dataFeedImpl.resetOriginalValues();

		return dataFeedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		dataFeedId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		feedKey = objectInput.readUTF();
		tokenHash = objectInput.readUTF();
		tokenHint = objectInput.readUTF();
		name = objectInput.readUTF();

		campaignId = objectInput.readLong();
		seriesKey = objectInput.readUTF();
		format = objectInput.readUTF();

		includeMetadata = objectInput.readBoolean();
		expirationDate = objectInput.readLong();
		lastAccessDate = objectInput.readLong();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(dataFeedId);

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

		if (feedKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedKey);
		}

		if (tokenHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tokenHash);
		}

		if (tokenHint == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tokenHint);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(campaignId);

		if (seriesKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(seriesKey);
		}

		if (format == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(format);
		}

		objectOutput.writeBoolean(includeMetadata);
		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(lastAccessDate);

		objectOutput.writeBoolean(active);
	}

	public long mvccVersion;
	public long dataFeedId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String feedKey;
	public String tokenHash;
	public String tokenHint;
	public String name;
	public long campaignId;
	public String seriesKey;
	public String format;
	public boolean includeMetadata;
	public long expirationDate;
	public long lastAccessDate;
	public boolean active;

}