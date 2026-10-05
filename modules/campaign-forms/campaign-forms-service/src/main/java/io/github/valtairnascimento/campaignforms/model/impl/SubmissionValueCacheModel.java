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

import io.github.valtairnascimento.campaignforms.model.SubmissionValue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.math.BigDecimal;

import java.util.Date;

/**
 * The cache model class for representing SubmissionValue in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class SubmissionValueCacheModel
	implements CacheModel<SubmissionValue>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubmissionValueCacheModel)) {
			return false;
		}

		SubmissionValueCacheModel submissionValueCacheModel =
			(SubmissionValueCacheModel)object;

		if ((submissionValueId ==
				submissionValueCacheModel.submissionValueId) &&
			(mvccVersion == submissionValueCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, submissionValueId);

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
		sb.append(", submissionValueId=");
		sb.append(submissionValueId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", submissionId=");
		sb.append(submissionId);
		sb.append(", formFieldId=");
		sb.append(formFieldId);
		sb.append(", fieldKey=");
		sb.append(fieldKey);
		sb.append(", valueType=");
		sb.append(valueType);
		sb.append(", valueIndex=");
		sb.append(valueIndex);
		sb.append(", stringValue=");
		sb.append(stringValue);
		sb.append(", textValue=");
		sb.append(textValue);
		sb.append(", numberValue=");
		sb.append(numberValue);
		sb.append(", dateValue=");
		sb.append(dateValue);
		sb.append(", booleanValue=");
		sb.append(booleanValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SubmissionValue toEntityModel() {
		SubmissionValueImpl submissionValueImpl = new SubmissionValueImpl();

		submissionValueImpl.setMvccVersion(mvccVersion);
		submissionValueImpl.setSubmissionValueId(submissionValueId);
		submissionValueImpl.setCompanyId(companyId);
		submissionValueImpl.setCampaignId(campaignId);
		submissionValueImpl.setSubmissionId(submissionId);
		submissionValueImpl.setFormFieldId(formFieldId);

		if (fieldKey == null) {
			submissionValueImpl.setFieldKey("");
		}
		else {
			submissionValueImpl.setFieldKey(fieldKey);
		}

		if (valueType == null) {
			submissionValueImpl.setValueType("");
		}
		else {
			submissionValueImpl.setValueType(valueType);
		}

		submissionValueImpl.setValueIndex(valueIndex);

		if (stringValue == null) {
			submissionValueImpl.setStringValue("");
		}
		else {
			submissionValueImpl.setStringValue(stringValue);
		}

		if (textValue == null) {
			submissionValueImpl.setTextValue("");
		}
		else {
			submissionValueImpl.setTextValue(textValue);
		}

		submissionValueImpl.setNumberValue(numberValue);

		if (dateValue == Long.MIN_VALUE) {
			submissionValueImpl.setDateValue(null);
		}
		else {
			submissionValueImpl.setDateValue(new Date(dateValue));
		}

		submissionValueImpl.setBooleanValue(booleanValue);

		submissionValueImpl.resetOriginalValues();

		return submissionValueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		submissionValueId = objectInput.readLong();

		companyId = objectInput.readLong();

		campaignId = objectInput.readLong();

		submissionId = objectInput.readLong();

		formFieldId = objectInput.readLong();
		fieldKey = objectInput.readUTF();
		valueType = objectInput.readUTF();

		valueIndex = objectInput.readInt();
		stringValue = objectInput.readUTF();
		textValue = (String)objectInput.readObject();
		numberValue = (BigDecimal)objectInput.readObject();
		dateValue = objectInput.readLong();

		booleanValue = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(submissionValueId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(campaignId);

		objectOutput.writeLong(submissionId);

		objectOutput.writeLong(formFieldId);

		if (fieldKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fieldKey);
		}

		if (valueType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(valueType);
		}

		objectOutput.writeInt(valueIndex);

		if (stringValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stringValue);
		}

		if (textValue == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(textValue);
		}

		objectOutput.writeObject(numberValue);
		objectOutput.writeLong(dateValue);

		objectOutput.writeBoolean(booleanValue);
	}

	public long mvccVersion;
	public long submissionValueId;
	public long companyId;
	public long campaignId;
	public long submissionId;
	public long formFieldId;
	public String fieldKey;
	public String valueType;
	public int valueIndex;
	public String stringValue;
	public String textValue;
	public BigDecimal numberValue;
	public long dateValue;
	public boolean booleanValue;

}