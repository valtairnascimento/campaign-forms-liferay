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

import io.github.valtairnascimento.campaignforms.model.Form;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Form in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class FormCacheModel
	implements CacheModel<Form>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FormCacheModel)) {
			return false;
		}

		FormCacheModel formCacheModel = (FormCacheModel)object;

		if ((formId == formCacheModel.formId) &&
			(mvccVersion == formCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, formId);

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
		StringBundler sb = new StringBundler(27);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", formId=");
		sb.append(formId);
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
		sb.append(", formKey=");
		sb.append(formKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", draftVersionId=");
		sb.append(draftVersionId);
		sb.append(", publishedVersionId=");
		sb.append(publishedVersionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Form toEntityModel() {
		FormImpl formImpl = new FormImpl();

		formImpl.setMvccVersion(mvccVersion);
		formImpl.setFormId(formId);
		formImpl.setGroupId(groupId);
		formImpl.setCompanyId(companyId);
		formImpl.setUserId(userId);

		if (userName == null) {
			formImpl.setUserName("");
		}
		else {
			formImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			formImpl.setCreateDate(null);
		}
		else {
			formImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			formImpl.setModifiedDate(null);
		}
		else {
			formImpl.setModifiedDate(new Date(modifiedDate));
		}

		formImpl.setCampaignId(campaignId);

		if (formKey == null) {
			formImpl.setFormKey("");
		}
		else {
			formImpl.setFormKey(formKey);
		}

		if (name == null) {
			formImpl.setName("");
		}
		else {
			formImpl.setName(name);
		}

		formImpl.setDraftVersionId(draftVersionId);
		formImpl.setPublishedVersionId(publishedVersionId);

		formImpl.resetOriginalValues();

		return formImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		formId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();
		formKey = objectInput.readUTF();
		name = objectInput.readUTF();

		draftVersionId = objectInput.readLong();

		publishedVersionId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(formId);

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

		if (formKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(draftVersionId);

		objectOutput.writeLong(publishedVersionId);
	}

	public long mvccVersion;
	public long formId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public String formKey;
	public String name;
	public long draftVersionId;
	public long publishedVersionId;

}