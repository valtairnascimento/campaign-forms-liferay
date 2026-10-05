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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CampaignQuota}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see CampaignQuota
 * @generated
 */
public class CampaignQuotaWrapper
	extends BaseModelWrapper<CampaignQuota>
	implements CampaignQuota, ModelWrapper<CampaignQuota> {

	public CampaignQuotaWrapper(CampaignQuota campaignQuota) {
		super(campaignQuota);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("campaignId", getCampaignId());
		attributes.put("companyId", getCompanyId());
		attributes.put("used", getUsed());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Integer used = (Integer)attributes.get("used");

		if (used != null) {
			setUsed(used);
		}
	}

	@Override
	public CampaignQuota cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the campaign ID of this campaign quota.
	 *
	 * @return the campaign ID of this campaign quota
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this campaign quota.
	 *
	 * @return the company ID of this campaign quota
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the mvcc version of this campaign quota.
	 *
	 * @return the mvcc version of this campaign quota
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this campaign quota.
	 *
	 * @return the primary key of this campaign quota
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the used of this campaign quota.
	 *
	 * @return the used of this campaign quota
	 */
	@Override
	public int getUsed() {
		return model.getUsed();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the campaign ID of this campaign quota.
	 *
	 * @param campaignId the campaign ID of this campaign quota
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this campaign quota.
	 *
	 * @param companyId the company ID of this campaign quota
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the mvcc version of this campaign quota.
	 *
	 * @param mvccVersion the mvcc version of this campaign quota
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this campaign quota.
	 *
	 * @param primaryKey the primary key of this campaign quota
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the used of this campaign quota.
	 *
	 * @param used the used of this campaign quota
	 */
	@Override
	public void setUsed(int used) {
		model.setUsed(used);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected CampaignQuotaWrapper wrap(CampaignQuota campaignQuota) {
		return new CampaignQuotaWrapper(campaignQuota);
	}

}