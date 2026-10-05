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

import io.github.valtairnascimento.campaignforms.model.CampaignQuota;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CampaignQuota in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class CampaignQuotaCacheModel
	implements CacheModel<CampaignQuota>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CampaignQuotaCacheModel)) {
			return false;
		}

		CampaignQuotaCacheModel campaignQuotaCacheModel =
			(CampaignQuotaCacheModel)object;

		if ((campaignId == campaignQuotaCacheModel.campaignId) &&
			(mvccVersion == campaignQuotaCacheModel.mvccVersion)) {

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
		StringBundler sb = new StringBundler(9);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", used=");
		sb.append(used);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CampaignQuota toEntityModel() {
		CampaignQuotaImpl campaignQuotaImpl = new CampaignQuotaImpl();

		campaignQuotaImpl.setMvccVersion(mvccVersion);
		campaignQuotaImpl.setCampaignId(campaignId);
		campaignQuotaImpl.setCompanyId(companyId);
		campaignQuotaImpl.setUsed(used);

		campaignQuotaImpl.resetOriginalValues();

		return campaignQuotaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		campaignId = objectInput.readLong();

		companyId = objectInput.readLong();

		used = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(campaignId);

		objectOutput.writeLong(companyId);

		objectOutput.writeInt(used);
	}

	public long mvccVersion;
	public long campaignId;
	public long companyId;
	public int used;

}