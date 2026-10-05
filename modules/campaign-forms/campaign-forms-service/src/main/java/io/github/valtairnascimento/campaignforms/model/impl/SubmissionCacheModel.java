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

import io.github.valtairnascimento.campaignforms.model.Submission;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Submission in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class SubmissionCacheModel
	implements CacheModel<Submission>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubmissionCacheModel)) {
			return false;
		}

		SubmissionCacheModel submissionCacheModel =
			(SubmissionCacheModel)object;

		if ((submissionId == submissionCacheModel.submissionId) &&
			(mvccVersion == submissionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, submissionId);

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
		StringBundler sb = new StringBundler(57);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", submissionId=");
		sb.append(submissionId);
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
		sb.append(", formId=");
		sb.append(formId);
		sb.append(", formVersionId=");
		sb.append(formVersionId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", sourceUrl=");
		sb.append(sourceUrl);
		sb.append(", referrer=");
		sb.append(referrer);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", ipHash=");
		sb.append(ipHash);
		sb.append(", utmSource=");
		sb.append(utmSource);
		sb.append(", utmMedium=");
		sb.append(utmMedium);
		sb.append(", utmCampaign=");
		sb.append(utmCampaign);
		sb.append(", utmContent=");
		sb.append(utmContent);
		sb.append(", utmTerm=");
		sb.append(utmTerm);
		sb.append(", consentVersion=");
		sb.append(consentVersion);
		sb.append(", consentDate=");
		sb.append(consentDate);
		sb.append(", idempotencyKey=");
		sb.append(idempotencyKey);
		sb.append(", metadata=");
		sb.append(metadata);
		sb.append(", payloadSnapshot=");
		sb.append(payloadSnapshot);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Submission toEntityModel() {
		SubmissionImpl submissionImpl = new SubmissionImpl();

		submissionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			submissionImpl.setUuid("");
		}
		else {
			submissionImpl.setUuid(uuid);
		}

		submissionImpl.setSubmissionId(submissionId);
		submissionImpl.setGroupId(groupId);
		submissionImpl.setCompanyId(companyId);
		submissionImpl.setUserId(userId);

		if (userName == null) {
			submissionImpl.setUserName("");
		}
		else {
			submissionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			submissionImpl.setCreateDate(null);
		}
		else {
			submissionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			submissionImpl.setModifiedDate(null);
		}
		else {
			submissionImpl.setModifiedDate(new Date(modifiedDate));
		}

		submissionImpl.setCampaignId(campaignId);
		submissionImpl.setFormId(formId);
		submissionImpl.setFormVersionId(formVersionId);

		if (status == null) {
			submissionImpl.setStatus("");
		}
		else {
			submissionImpl.setStatus(status);
		}

		if (sourceUrl == null) {
			submissionImpl.setSourceUrl("");
		}
		else {
			submissionImpl.setSourceUrl(sourceUrl);
		}

		if (referrer == null) {
			submissionImpl.setReferrer("");
		}
		else {
			submissionImpl.setReferrer(referrer);
		}

		if (userAgent == null) {
			submissionImpl.setUserAgent("");
		}
		else {
			submissionImpl.setUserAgent(userAgent);
		}

		if (languageId == null) {
			submissionImpl.setLanguageId("");
		}
		else {
			submissionImpl.setLanguageId(languageId);
		}

		if (ipHash == null) {
			submissionImpl.setIpHash("");
		}
		else {
			submissionImpl.setIpHash(ipHash);
		}

		if (utmSource == null) {
			submissionImpl.setUtmSource("");
		}
		else {
			submissionImpl.setUtmSource(utmSource);
		}

		if (utmMedium == null) {
			submissionImpl.setUtmMedium("");
		}
		else {
			submissionImpl.setUtmMedium(utmMedium);
		}

		if (utmCampaign == null) {
			submissionImpl.setUtmCampaign("");
		}
		else {
			submissionImpl.setUtmCampaign(utmCampaign);
		}

		if (utmContent == null) {
			submissionImpl.setUtmContent("");
		}
		else {
			submissionImpl.setUtmContent(utmContent);
		}

		if (utmTerm == null) {
			submissionImpl.setUtmTerm("");
		}
		else {
			submissionImpl.setUtmTerm(utmTerm);
		}

		if (consentVersion == null) {
			submissionImpl.setConsentVersion("");
		}
		else {
			submissionImpl.setConsentVersion(consentVersion);
		}

		if (consentDate == Long.MIN_VALUE) {
			submissionImpl.setConsentDate(null);
		}
		else {
			submissionImpl.setConsentDate(new Date(consentDate));
		}

		if (idempotencyKey == null) {
			submissionImpl.setIdempotencyKey("");
		}
		else {
			submissionImpl.setIdempotencyKey(idempotencyKey);
		}

		if (metadata == null) {
			submissionImpl.setMetadata("");
		}
		else {
			submissionImpl.setMetadata(metadata);
		}

		if (payloadSnapshot == null) {
			submissionImpl.setPayloadSnapshot("");
		}
		else {
			submissionImpl.setPayloadSnapshot(payloadSnapshot);
		}

		submissionImpl.resetOriginalValues();

		return submissionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		submissionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		campaignId = objectInput.readLong();

		formId = objectInput.readLong();

		formVersionId = objectInput.readLong();
		status = objectInput.readUTF();
		sourceUrl = objectInput.readUTF();
		referrer = objectInput.readUTF();
		userAgent = objectInput.readUTF();
		languageId = objectInput.readUTF();
		ipHash = objectInput.readUTF();
		utmSource = objectInput.readUTF();
		utmMedium = objectInput.readUTF();
		utmCampaign = objectInput.readUTF();
		utmContent = objectInput.readUTF();
		utmTerm = objectInput.readUTF();
		consentVersion = objectInput.readUTF();
		consentDate = objectInput.readLong();
		idempotencyKey = objectInput.readUTF();
		metadata = (String)objectInput.readObject();
		payloadSnapshot = (String)objectInput.readObject();
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

		objectOutput.writeLong(submissionId);

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

		objectOutput.writeLong(formId);

		objectOutput.writeLong(formVersionId);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (sourceUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sourceUrl);
		}

		if (referrer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referrer);
		}

		if (userAgent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userAgent);
		}

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		if (ipHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipHash);
		}

		if (utmSource == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(utmSource);
		}

		if (utmMedium == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(utmMedium);
		}

		if (utmCampaign == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(utmCampaign);
		}

		if (utmContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(utmContent);
		}

		if (utmTerm == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(utmTerm);
		}

		if (consentVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(consentVersion);
		}

		objectOutput.writeLong(consentDate);

		if (idempotencyKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(idempotencyKey);
		}

		if (metadata == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(metadata);
		}

		if (payloadSnapshot == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(payloadSnapshot);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long submissionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long campaignId;
	public long formId;
	public long formVersionId;
	public String status;
	public String sourceUrl;
	public String referrer;
	public String userAgent;
	public String languageId;
	public String ipHash;
	public String utmSource;
	public String utmMedium;
	public String utmCampaign;
	public String utmContent;
	public String utmTerm;
	public String consentVersion;
	public long consentDate;
	public String idempotencyKey;
	public String metadata;
	public String payloadSnapshot;

}