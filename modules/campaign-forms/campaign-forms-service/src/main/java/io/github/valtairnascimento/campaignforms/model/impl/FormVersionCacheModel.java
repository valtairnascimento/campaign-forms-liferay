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

import io.github.valtairnascimento.campaignforms.model.FormVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FormVersion in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class FormVersionCacheModel
	implements CacheModel<FormVersion>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FormVersionCacheModel)) {
			return false;
		}

		FormVersionCacheModel formVersionCacheModel =
			(FormVersionCacheModel)object;

		if ((formVersionId == formVersionCacheModel.formVersionId) &&
			(mvccVersion == formVersionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, formVersionId);

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
		StringBundler sb = new StringBundler(51);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", formVersionId=");
		sb.append(formVersionId);
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
		sb.append(", formId=");
		sb.append(formId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", status=");
		sb.append(status);
		sb.append(", submitLabel=");
		sb.append(submitLabel);
		sb.append(", successTitle=");
		sb.append(successTitle);
		sb.append(", successMessage=");
		sb.append(successMessage);
		sb.append(", redirectUrl=");
		sb.append(redirectUrl);
		sb.append(", captchaEnabled=");
		sb.append(captchaEnabled);
		sb.append(", requireAuthentication=");
		sb.append(requireAuthentication);
		sb.append(", consentRequired=");
		sb.append(consentRequired);
		sb.append(", consentText=");
		sb.append(consentText);
		sb.append(", consentVersion=");
		sb.append(consentVersion);
		sb.append(", consentURL=");
		sb.append(consentURL);
		sb.append(", saveIncompleteSubmission=");
		sb.append(saveIncompleteSubmission);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", publishedDate=");
		sb.append(publishedDate);
		sb.append(", publishedByUserId=");
		sb.append(publishedByUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormVersion toEntityModel() {
		FormVersionImpl formVersionImpl = new FormVersionImpl();

		formVersionImpl.setMvccVersion(mvccVersion);
		formVersionImpl.setFormVersionId(formVersionId);
		formVersionImpl.setGroupId(groupId);
		formVersionImpl.setCompanyId(companyId);
		formVersionImpl.setUserId(userId);

		if (userName == null) {
			formVersionImpl.setUserName("");
		}
		else {
			formVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			formVersionImpl.setCreateDate(null);
		}
		else {
			formVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			formVersionImpl.setModifiedDate(null);
		}
		else {
			formVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		formVersionImpl.setFormId(formId);
		formVersionImpl.setVersion(version);

		if (status == null) {
			formVersionImpl.setStatus("");
		}
		else {
			formVersionImpl.setStatus(status);
		}

		if (submitLabel == null) {
			formVersionImpl.setSubmitLabel("");
		}
		else {
			formVersionImpl.setSubmitLabel(submitLabel);
		}

		if (successTitle == null) {
			formVersionImpl.setSuccessTitle("");
		}
		else {
			formVersionImpl.setSuccessTitle(successTitle);
		}

		if (successMessage == null) {
			formVersionImpl.setSuccessMessage("");
		}
		else {
			formVersionImpl.setSuccessMessage(successMessage);
		}

		if (redirectUrl == null) {
			formVersionImpl.setRedirectUrl("");
		}
		else {
			formVersionImpl.setRedirectUrl(redirectUrl);
		}

		formVersionImpl.setCaptchaEnabled(captchaEnabled);
		formVersionImpl.setRequireAuthentication(requireAuthentication);
		formVersionImpl.setConsentRequired(consentRequired);

		if (consentText == null) {
			formVersionImpl.setConsentText("");
		}
		else {
			formVersionImpl.setConsentText(consentText);
		}

		if (consentVersion == null) {
			formVersionImpl.setConsentVersion("");
		}
		else {
			formVersionImpl.setConsentVersion(consentVersion);
		}

		if (consentURL == null) {
			formVersionImpl.setConsentURL("");
		}
		else {
			formVersionImpl.setConsentURL(consentURL);
		}

		formVersionImpl.setSaveIncompleteSubmission(saveIncompleteSubmission);

		if (settings == null) {
			formVersionImpl.setSettings("");
		}
		else {
			formVersionImpl.setSettings(settings);
		}

		if (publishedDate == Long.MIN_VALUE) {
			formVersionImpl.setPublishedDate(null);
		}
		else {
			formVersionImpl.setPublishedDate(new Date(publishedDate));
		}

		formVersionImpl.setPublishedByUserId(publishedByUserId);

		formVersionImpl.resetOriginalValues();

		return formVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		formVersionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		formId = objectInput.readLong();

		version = objectInput.readInt();
		status = objectInput.readUTF();
		submitLabel = objectInput.readUTF();
		successTitle = objectInput.readUTF();
		successMessage = (String)objectInput.readObject();
		redirectUrl = objectInput.readUTF();

		captchaEnabled = objectInput.readBoolean();

		requireAuthentication = objectInput.readBoolean();

		consentRequired = objectInput.readBoolean();
		consentText = (String)objectInput.readObject();
		consentVersion = objectInput.readUTF();
		consentURL = objectInput.readUTF();

		saveIncompleteSubmission = objectInput.readBoolean();
		settings = (String)objectInput.readObject();
		publishedDate = objectInput.readLong();

		publishedByUserId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(formVersionId);

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

		objectOutput.writeLong(formId);

		objectOutput.writeInt(version);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (submitLabel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(submitLabel);
		}

		if (successTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(successTitle);
		}

		if (successMessage == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(successMessage);
		}

		if (redirectUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(redirectUrl);
		}

		objectOutput.writeBoolean(captchaEnabled);

		objectOutput.writeBoolean(requireAuthentication);

		objectOutput.writeBoolean(consentRequired);

		if (consentText == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(consentText);
		}

		if (consentVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(consentVersion);
		}

		if (consentURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(consentURL);
		}

		objectOutput.writeBoolean(saveIncompleteSubmission);

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}

		objectOutput.writeLong(publishedDate);

		objectOutput.writeLong(publishedByUserId);
	}

	public long mvccVersion;
	public long formVersionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long formId;
	public int version;
	public String status;
	public String submitLabel;
	public String successTitle;
	public String successMessage;
	public String redirectUrl;
	public boolean captchaEnabled;
	public boolean requireAuthentication;
	public boolean consentRequired;
	public String consentText;
	public String consentVersion;
	public String consentURL;
	public boolean saveIncompleteSubmission;
	public String settings;
	public long publishedDate;
	public long publishedByUserId;

}