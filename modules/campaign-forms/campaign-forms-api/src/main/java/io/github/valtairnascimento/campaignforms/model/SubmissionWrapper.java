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

package io.github.valtairnascimento.campaignforms.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Submission}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see Submission
 * @generated
 */
public class SubmissionWrapper
	extends BaseModelWrapper<Submission>
	implements ModelWrapper<Submission>, Submission {

	public SubmissionWrapper(Submission submission) {
		super(submission);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("submissionId", getSubmissionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("campaignId", getCampaignId());
		attributes.put("formId", getFormId());
		attributes.put("formVersionId", getFormVersionId());
		attributes.put("status", getStatus());
		attributes.put("sourceUrl", getSourceUrl());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("languageId", getLanguageId());
		attributes.put("ipHash", getIpHash());
		attributes.put("utmSource", getUtmSource());
		attributes.put("utmMedium", getUtmMedium());
		attributes.put("utmCampaign", getUtmCampaign());
		attributes.put("utmContent", getUtmContent());
		attributes.put("utmTerm", getUtmTerm());
		attributes.put("consentVersion", getConsentVersion());
		attributes.put("consentDate", getConsentDate());
		attributes.put("idempotencyKey", getIdempotencyKey());
		attributes.put("metadata", getMetadata());
		attributes.put("payloadSnapshot", getPayloadSnapshot());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long submissionId = (Long)attributes.get("submissionId");

		if (submissionId != null) {
			setSubmissionId(submissionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long formId = (Long)attributes.get("formId");

		if (formId != null) {
			setFormId(formId);
		}

		Long formVersionId = (Long)attributes.get("formVersionId");

		if (formVersionId != null) {
			setFormVersionId(formVersionId);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String sourceUrl = (String)attributes.get("sourceUrl");

		if (sourceUrl != null) {
			setSourceUrl(sourceUrl);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		String ipHash = (String)attributes.get("ipHash");

		if (ipHash != null) {
			setIpHash(ipHash);
		}

		String utmSource = (String)attributes.get("utmSource");

		if (utmSource != null) {
			setUtmSource(utmSource);
		}

		String utmMedium = (String)attributes.get("utmMedium");

		if (utmMedium != null) {
			setUtmMedium(utmMedium);
		}

		String utmCampaign = (String)attributes.get("utmCampaign");

		if (utmCampaign != null) {
			setUtmCampaign(utmCampaign);
		}

		String utmContent = (String)attributes.get("utmContent");

		if (utmContent != null) {
			setUtmContent(utmContent);
		}

		String utmTerm = (String)attributes.get("utmTerm");

		if (utmTerm != null) {
			setUtmTerm(utmTerm);
		}

		String consentVersion = (String)attributes.get("consentVersion");

		if (consentVersion != null) {
			setConsentVersion(consentVersion);
		}

		Date consentDate = (Date)attributes.get("consentDate");

		if (consentDate != null) {
			setConsentDate(consentDate);
		}

		String idempotencyKey = (String)attributes.get("idempotencyKey");

		if (idempotencyKey != null) {
			setIdempotencyKey(idempotencyKey);
		}

		String metadata = (String)attributes.get("metadata");

		if (metadata != null) {
			setMetadata(metadata);
		}

		String payloadSnapshot = (String)attributes.get("payloadSnapshot");

		if (payloadSnapshot != null) {
			setPayloadSnapshot(payloadSnapshot);
		}
	}

	@Override
	public Submission cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this submission.
	 *
	 * @return the campaign ID of this submission
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this submission.
	 *
	 * @return the company ID of this submission
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the consent date of this submission.
	 *
	 * @return the consent date of this submission
	 */
	@Override
	public Date getConsentDate() {
		return model.getConsentDate();
	}

	/**
	 * Returns the consent version of this submission.
	 *
	 * @return the consent version of this submission
	 */
	@Override
	public String getConsentVersion() {
		return model.getConsentVersion();
	}

	/**
	 * Returns the create date of this submission.
	 *
	 * @return the create date of this submission
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the form ID of this submission.
	 *
	 * @return the form ID of this submission
	 */
	@Override
	public long getFormId() {
		return model.getFormId();
	}

	/**
	 * Returns the form version ID of this submission.
	 *
	 * @return the form version ID of this submission
	 */
	@Override
	public long getFormVersionId() {
		return model.getFormVersionId();
	}

	/**
	 * Returns the group ID of this submission.
	 *
	 * @return the group ID of this submission
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the idempotency key of this submission.
	 *
	 * @return the idempotency key of this submission
	 */
	@Override
	public String getIdempotencyKey() {
		return model.getIdempotencyKey();
	}

	/**
	 * Returns the ip hash of this submission.
	 *
	 * @return the ip hash of this submission
	 */
	@Override
	public String getIpHash() {
		return model.getIpHash();
	}

	/**
	 * Returns the language ID of this submission.
	 *
	 * @return the language ID of this submission
	 */
	@Override
	public String getLanguageId() {
		return model.getLanguageId();
	}

	/**
	 * Returns the metadata of this submission.
	 *
	 * @return the metadata of this submission
	 */
	@Override
	public String getMetadata() {
		return model.getMetadata();
	}

	/**
	 * Returns the modified date of this submission.
	 *
	 * @return the modified date of this submission
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this submission.
	 *
	 * @return the mvcc version of this submission
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the payload snapshot of this submission.
	 *
	 * @return the payload snapshot of this submission
	 */
	@Override
	public String getPayloadSnapshot() {
		return model.getPayloadSnapshot();
	}

	/**
	 * Returns the primary key of this submission.
	 *
	 * @return the primary key of this submission
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the referrer of this submission.
	 *
	 * @return the referrer of this submission
	 */
	@Override
	public String getReferrer() {
		return model.getReferrer();
	}

	/**
	 * Returns the source url of this submission.
	 *
	 * @return the source url of this submission
	 */
	@Override
	public String getSourceUrl() {
		return model.getSourceUrl();
	}

	/**
	 * Returns the status of this submission.
	 *
	 * @return the status of this submission
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the submission ID of this submission.
	 *
	 * @return the submission ID of this submission
	 */
	@Override
	public long getSubmissionId() {
		return model.getSubmissionId();
	}

	/**
	 * Returns the user agent of this submission.
	 *
	 * @return the user agent of this submission
	 */
	@Override
	public String getUserAgent() {
		return model.getUserAgent();
	}

	/**
	 * Returns the user ID of this submission.
	 *
	 * @return the user ID of this submission
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this submission.
	 *
	 * @return the user name of this submission
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this submission.
	 *
	 * @return the user uuid of this submission
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the utm campaign of this submission.
	 *
	 * @return the utm campaign of this submission
	 */
	@Override
	public String getUtmCampaign() {
		return model.getUtmCampaign();
	}

	/**
	 * Returns the utm content of this submission.
	 *
	 * @return the utm content of this submission
	 */
	@Override
	public String getUtmContent() {
		return model.getUtmContent();
	}

	/**
	 * Returns the utm medium of this submission.
	 *
	 * @return the utm medium of this submission
	 */
	@Override
	public String getUtmMedium() {
		return model.getUtmMedium();
	}

	/**
	 * Returns the utm source of this submission.
	 *
	 * @return the utm source of this submission
	 */
	@Override
	public String getUtmSource() {
		return model.getUtmSource();
	}

	/**
	 * Returns the utm term of this submission.
	 *
	 * @return the utm term of this submission
	 */
	@Override
	public String getUtmTerm() {
		return model.getUtmTerm();
	}

	/**
	 * Returns the uuid of this submission.
	 *
	 * @return the uuid of this submission
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this submission.
	 *
	 * @param campaignId the campaign ID of this submission
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this submission.
	 *
	 * @param companyId the company ID of this submission
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the consent date of this submission.
	 *
	 * @param consentDate the consent date of this submission
	 */
	@Override
	public void setConsentDate(Date consentDate) {
		model.setConsentDate(consentDate);
	}

	/**
	 * Sets the consent version of this submission.
	 *
	 * @param consentVersion the consent version of this submission
	 */
	@Override
	public void setConsentVersion(String consentVersion) {
		model.setConsentVersion(consentVersion);
	}

	/**
	 * Sets the create date of this submission.
	 *
	 * @param createDate the create date of this submission
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the form ID of this submission.
	 *
	 * @param formId the form ID of this submission
	 */
	@Override
	public void setFormId(long formId) {
		model.setFormId(formId);
	}

	/**
	 * Sets the form version ID of this submission.
	 *
	 * @param formVersionId the form version ID of this submission
	 */
	@Override
	public void setFormVersionId(long formVersionId) {
		model.setFormVersionId(formVersionId);
	}

	/**
	 * Sets the group ID of this submission.
	 *
	 * @param groupId the group ID of this submission
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the idempotency key of this submission.
	 *
	 * @param idempotencyKey the idempotency key of this submission
	 */
	@Override
	public void setIdempotencyKey(String idempotencyKey) {
		model.setIdempotencyKey(idempotencyKey);
	}

	/**
	 * Sets the ip hash of this submission.
	 *
	 * @param ipHash the ip hash of this submission
	 */
	@Override
	public void setIpHash(String ipHash) {
		model.setIpHash(ipHash);
	}

	/**
	 * Sets the language ID of this submission.
	 *
	 * @param languageId the language ID of this submission
	 */
	@Override
	public void setLanguageId(String languageId) {
		model.setLanguageId(languageId);
	}

	/**
	 * Sets the metadata of this submission.
	 *
	 * @param metadata the metadata of this submission
	 */
	@Override
	public void setMetadata(String metadata) {
		model.setMetadata(metadata);
	}

	/**
	 * Sets the modified date of this submission.
	 *
	 * @param modifiedDate the modified date of this submission
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this submission.
	 *
	 * @param mvccVersion the mvcc version of this submission
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the payload snapshot of this submission.
	 *
	 * @param payloadSnapshot the payload snapshot of this submission
	 */
	@Override
	public void setPayloadSnapshot(String payloadSnapshot) {
		model.setPayloadSnapshot(payloadSnapshot);
	}

	/**
	 * Sets the primary key of this submission.
	 *
	 * @param primaryKey the primary key of this submission
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the referrer of this submission.
	 *
	 * @param referrer the referrer of this submission
	 */
	@Override
	public void setReferrer(String referrer) {
		model.setReferrer(referrer);
	}

	/**
	 * Sets the source url of this submission.
	 *
	 * @param sourceUrl the source url of this submission
	 */
	@Override
	public void setSourceUrl(String sourceUrl) {
		model.setSourceUrl(sourceUrl);
	}

	/**
	 * Sets the status of this submission.
	 *
	 * @param status the status of this submission
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the submission ID of this submission.
	 *
	 * @param submissionId the submission ID of this submission
	 */
	@Override
	public void setSubmissionId(long submissionId) {
		model.setSubmissionId(submissionId);
	}

	/**
	 * Sets the user agent of this submission.
	 *
	 * @param userAgent the user agent of this submission
	 */
	@Override
	public void setUserAgent(String userAgent) {
		model.setUserAgent(userAgent);
	}

	/**
	 * Sets the user ID of this submission.
	 *
	 * @param userId the user ID of this submission
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this submission.
	 *
	 * @param userName the user name of this submission
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this submission.
	 *
	 * @param userUuid the user uuid of this submission
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the utm campaign of this submission.
	 *
	 * @param utmCampaign the utm campaign of this submission
	 */
	@Override
	public void setUtmCampaign(String utmCampaign) {
		model.setUtmCampaign(utmCampaign);
	}

	/**
	 * Sets the utm content of this submission.
	 *
	 * @param utmContent the utm content of this submission
	 */
	@Override
	public void setUtmContent(String utmContent) {
		model.setUtmContent(utmContent);
	}

	/**
	 * Sets the utm medium of this submission.
	 *
	 * @param utmMedium the utm medium of this submission
	 */
	@Override
	public void setUtmMedium(String utmMedium) {
		model.setUtmMedium(utmMedium);
	}

	/**
	 * Sets the utm source of this submission.
	 *
	 * @param utmSource the utm source of this submission
	 */
	@Override
	public void setUtmSource(String utmSource) {
		model.setUtmSource(utmSource);
	}

	/**
	 * Sets the utm term of this submission.
	 *
	 * @param utmTerm the utm term of this submission
	 */
	@Override
	public void setUtmTerm(String utmTerm) {
		model.setUtmTerm(utmTerm);
	}

	/**
	 * Sets the uuid of this submission.
	 *
	 * @param uuid the uuid of this submission
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected SubmissionWrapper wrap(Submission submission) {
		return new SubmissionWrapper(submission);
	}

}