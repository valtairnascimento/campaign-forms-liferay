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

import io.github.valtairnascimento.campaignforms.model.PageConfiguration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PageConfiguration in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class PageConfigurationCacheModel
	implements CacheModel<PageConfiguration>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PageConfigurationCacheModel)) {
			return false;
		}

		PageConfigurationCacheModel pageConfigurationCacheModel =
			(PageConfigurationCacheModel)object;

		if ((pageConfigurationId ==
				pageConfigurationCacheModel.pageConfigurationId) &&
			(mvccVersion == pageConfigurationCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, pageConfigurationId);

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
		sb.append(", pageConfigurationId=");
		sb.append(pageConfigurationId);
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
		sb.append(", stage=");
		sb.append(stage);
		sb.append(", templateKey=");
		sb.append(templateKey);
		sb.append(", themeKey=");
		sb.append(themeKey);
		sb.append(", cssClass=");
		sb.append(cssClass);
		sb.append(", seoTitle=");
		sb.append(seoTitle);
		sb.append(", seoDescription=");
		sb.append(seoDescription);
		sb.append(", canonicalUrl=");
		sb.append(canonicalUrl);
		sb.append(", noIndex=");
		sb.append(noIndex);
		sb.append(", ogTitle=");
		sb.append(ogTitle);
		sb.append(", ogDescription=");
		sb.append(ogDescription);
		sb.append(", ogImageFileEntryId=");
		sb.append(ogImageFileEntryId);
		sb.append(", settings=");
		sb.append(settings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PageConfiguration toEntityModel() {
		PageConfigurationImpl pageConfigurationImpl =
			new PageConfigurationImpl();

		pageConfigurationImpl.setMvccVersion(mvccVersion);
		pageConfigurationImpl.setPageConfigurationId(pageConfigurationId);
		pageConfigurationImpl.setGroupId(groupId);
		pageConfigurationImpl.setCompanyId(companyId);
		pageConfigurationImpl.setUserId(userId);

		if (userName == null) {
			pageConfigurationImpl.setUserName("");
		}
		else {
			pageConfigurationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pageConfigurationImpl.setCreateDate(null);
		}
		else {
			pageConfigurationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pageConfigurationImpl.setModifiedDate(null);
		}
		else {
			pageConfigurationImpl.setModifiedDate(new Date(modifiedDate));
		}

		pageConfigurationImpl.setCampaignId(campaignId);

		if (stage == null) {
			pageConfigurationImpl.setStage("");
		}
		else {
			pageConfigurationImpl.setStage(stage);
		}

		if (templateKey == null) {
			pageConfigurationImpl.setTemplateKey("");
		}
		else {
			pageConfigurationImpl.setTemplateKey(templateKey);
		}

		if (themeKey == null) {
			pageConfigurationImpl.setThemeKey("");
		}
		else {
			pageConfigurationImpl.setThemeKey(themeKey);
		}

		if (cssClass == null) {
			pageConfigurationImpl.setCssClass("");
		}
		else {
			pageConfigurationImpl.setCssClass(cssClass);
		}

		if (seoTitle == null) {
			pageConfigurationImpl.setSeoTitle("");
		}
		else {
			pageConfigurationImpl.setSeoTitle(seoTitle);
		}

		if (seoDescription == null) {
			pageConfigurationImpl.setSeoDescription("");
		}
		else {
			pageConfigurationImpl.setSeoDescription(seoDescription);
		}

		if (canonicalUrl == null) {
			pageConfigurationImpl.setCanonicalUrl("");
		}
		else {
			pageConfigurationImpl.setCanonicalUrl(canonicalUrl);
		}

		pageConfigurationImpl.setNoIndex(noIndex);

		if (ogTitle == null) {
			pageConfigurationImpl.setOgTitle("");
		}
		else {
			pageConfigurationImpl.setOgTitle(ogTitle);
		}

		if (ogDescription == null) {
			pageConfigurationImpl.setOgDescription("");
		}
		else {
			pageConfigurationImpl.setOgDescription(ogDescription);
		}

		pageConfigurationImpl.setOgImageFileEntryId(ogImageFileEntryId);

		if (settings == null) {
			pageConfigurationImpl.setSettings("");
		}
		else {
			pageConfigurationImpl.setSettings(settings);
		}

		pageConfigurationImpl.resetOriginalValues();

		return pageConfigurationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		pageConfigurationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();
		stage = objectInput.readUTF();
		templateKey = objectInput.readUTF();
		themeKey = objectInput.readUTF();
		cssClass = objectInput.readUTF();
		seoTitle = objectInput.readUTF();
		seoDescription = objectInput.readUTF();
		canonicalUrl = objectInput.readUTF();

		noIndex = objectInput.readBoolean();
		ogTitle = objectInput.readUTF();
		ogDescription = objectInput.readUTF();

		ogImageFileEntryId = objectInput.readLong();
		settings = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(pageConfigurationId);

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

		if (stage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stage);
		}

		if (templateKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(templateKey);
		}

		if (themeKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(themeKey);
		}

		if (cssClass == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cssClass);
		}

		if (seoTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(seoTitle);
		}

		if (seoDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(seoDescription);
		}

		if (canonicalUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(canonicalUrl);
		}

		objectOutput.writeBoolean(noIndex);

		if (ogTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ogTitle);
		}

		if (ogDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ogDescription);
		}

		objectOutput.writeLong(ogImageFileEntryId);

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}
	}

	public long mvccVersion;
	public long pageConfigurationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public String stage;
	public String templateKey;
	public String themeKey;
	public String cssClass;
	public String seoTitle;
	public String seoDescription;
	public String canonicalUrl;
	public boolean noIndex;
	public String ogTitle;
	public String ogDescription;
	public long ogImageFileEntryId;
	public String settings;

}