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

import io.github.valtairnascimento.campaignforms.model.PageTemplate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PageTemplate in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class PageTemplateCacheModel
	implements CacheModel<PageTemplate>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PageTemplateCacheModel)) {
			return false;
		}

		PageTemplateCacheModel pageTemplateCacheModel =
			(PageTemplateCacheModel)object;

		if ((pageTemplateId == pageTemplateCacheModel.pageTemplateId) &&
			(mvccVersion == pageTemplateCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, pageTemplateId);

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
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", pageTemplateId=");
		sb.append(pageTemplateId);
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
		sb.append(", templateKey=");
		sb.append(templateKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", systemTemplate=");
		sb.append(systemTemplate);
		sb.append(", blocks=");
		sb.append(blocks);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PageTemplate toEntityModel() {
		PageTemplateImpl pageTemplateImpl = new PageTemplateImpl();

		pageTemplateImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			pageTemplateImpl.setUuid("");
		}
		else {
			pageTemplateImpl.setUuid(uuid);
		}

		pageTemplateImpl.setPageTemplateId(pageTemplateId);
		pageTemplateImpl.setGroupId(groupId);
		pageTemplateImpl.setCompanyId(companyId);
		pageTemplateImpl.setUserId(userId);

		if (userName == null) {
			pageTemplateImpl.setUserName("");
		}
		else {
			pageTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pageTemplateImpl.setCreateDate(null);
		}
		else {
			pageTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pageTemplateImpl.setModifiedDate(null);
		}
		else {
			pageTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (templateKey == null) {
			pageTemplateImpl.setTemplateKey("");
		}
		else {
			pageTemplateImpl.setTemplateKey(templateKey);
		}

		if (name == null) {
			pageTemplateImpl.setName("");
		}
		else {
			pageTemplateImpl.setName(name);
		}

		if (description == null) {
			pageTemplateImpl.setDescription("");
		}
		else {
			pageTemplateImpl.setDescription(description);
		}

		pageTemplateImpl.setSystemTemplate(systemTemplate);

		if (blocks == null) {
			pageTemplateImpl.setBlocks("");
		}
		else {
			pageTemplateImpl.setBlocks(blocks);
		}

		pageTemplateImpl.resetOriginalValues();

		return pageTemplateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		pageTemplateId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		templateKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		systemTemplate = objectInput.readBoolean();
		blocks = (String)objectInput.readObject();
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

		objectOutput.writeLong(pageTemplateId);

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

		if (templateKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(templateKey);
		}

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

		objectOutput.writeBoolean(systemTemplate);

		if (blocks == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(blocks);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long pageTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String templateKey;
	public String name;
	public String description;
	public boolean systemTemplate;
	public String blocks;

}