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

import io.github.valtairnascimento.campaignforms.model.PageBlock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PageBlock in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class PageBlockCacheModel
	implements CacheModel<PageBlock>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PageBlockCacheModel)) {
			return false;
		}

		PageBlockCacheModel pageBlockCacheModel = (PageBlockCacheModel)object;

		if ((pageBlockId == pageBlockCacheModel.pageBlockId) &&
			(mvccVersion == pageBlockCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, pageBlockId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", pageBlockId=");
		sb.append(pageBlockId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", pageConfigurationId=");
		sb.append(pageConfigurationId);
		sb.append(", blockType=");
		sb.append(blockType);
		sb.append(", blockKey=");
		sb.append(blockKey);
		sb.append(", position=");
		sb.append(position);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", schemaVersion=");
		sb.append(schemaVersion);
		sb.append(", configuration=");
		sb.append(configuration);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PageBlock toEntityModel() {
		PageBlockImpl pageBlockImpl = new PageBlockImpl();

		pageBlockImpl.setMvccVersion(mvccVersion);
		pageBlockImpl.setPageBlockId(pageBlockId);
		pageBlockImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			pageBlockImpl.setCreateDate(null);
		}
		else {
			pageBlockImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pageBlockImpl.setModifiedDate(null);
		}
		else {
			pageBlockImpl.setModifiedDate(new Date(modifiedDate));
		}

		pageBlockImpl.setPageConfigurationId(pageConfigurationId);

		if (blockType == null) {
			pageBlockImpl.setBlockType("");
		}
		else {
			pageBlockImpl.setBlockType(blockType);
		}

		if (blockKey == null) {
			pageBlockImpl.setBlockKey("");
		}
		else {
			pageBlockImpl.setBlockKey(blockKey);
		}

		pageBlockImpl.setPosition(position);
		pageBlockImpl.setEnabled(enabled);
		pageBlockImpl.setSchemaVersion(schemaVersion);

		if (configuration == null) {
			pageBlockImpl.setConfiguration("");
		}
		else {
			pageBlockImpl.setConfiguration(configuration);
		}

		pageBlockImpl.resetOriginalValues();

		return pageBlockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		pageBlockId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		pageConfigurationId = objectInput.readLong();
		blockType = objectInput.readUTF();
		blockKey = objectInput.readUTF();

		position = objectInput.readInt();

		enabled = objectInput.readBoolean();

		schemaVersion = objectInput.readInt();
		configuration = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(pageBlockId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(pageConfigurationId);

		if (blockType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(blockType);
		}

		if (blockKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(blockKey);
		}

		objectOutput.writeInt(position);

		objectOutput.writeBoolean(enabled);

		objectOutput.writeInt(schemaVersion);

		if (configuration == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(configuration);
		}
	}

	public long mvccVersion;
	public long pageBlockId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long pageConfigurationId;
	public String blockType;
	public String blockKey;
	public int position;
	public boolean enabled;
	public int schemaVersion;
	public String configuration;

}