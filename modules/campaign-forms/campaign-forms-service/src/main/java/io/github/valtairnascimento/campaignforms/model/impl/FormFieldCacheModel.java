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

import io.github.valtairnascimento.campaignforms.model.FormField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FormField in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class FormFieldCacheModel
	implements CacheModel<FormField>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FormFieldCacheModel)) {
			return false;
		}

		FormFieldCacheModel formFieldCacheModel = (FormFieldCacheModel)object;

		if ((formFieldId == formFieldCacheModel.formFieldId) &&
			(mvccVersion == formFieldCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, formFieldId);

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
		sb.append(", formFieldId=");
		sb.append(formFieldId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", formVersionId=");
		sb.append(formVersionId);
		sb.append(", fieldKey=");
		sb.append(fieldKey);
		sb.append(", type=");
		sb.append(type);
		sb.append(", label=");
		sb.append(label);
		sb.append(", placeholder=");
		sb.append(placeholder);
		sb.append(", helpText=");
		sb.append(helpText);
		sb.append(", required=");
		sb.append(required);
		sb.append(", position=");
		sb.append(position);
		sb.append(", defaultValue=");
		sb.append(defaultValue);
		sb.append(", width=");
		sb.append(width);
		sb.append(", validation=");
		sb.append(validation);
		sb.append(", properties=");
		sb.append(properties);
		sb.append(", conditionalRule=");
		sb.append(conditionalRule);
		sb.append(", presetKey=");
		sb.append(presetKey);
		sb.append(", personalData=");
		sb.append(personalData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormField toEntityModel() {
		FormFieldImpl formFieldImpl = new FormFieldImpl();

		formFieldImpl.setMvccVersion(mvccVersion);
		formFieldImpl.setFormFieldId(formFieldId);
		formFieldImpl.setGroupId(groupId);
		formFieldImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			formFieldImpl.setCreateDate(null);
		}
		else {
			formFieldImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			formFieldImpl.setModifiedDate(null);
		}
		else {
			formFieldImpl.setModifiedDate(new Date(modifiedDate));
		}

		formFieldImpl.setFormVersionId(formVersionId);

		if (fieldKey == null) {
			formFieldImpl.setFieldKey("");
		}
		else {
			formFieldImpl.setFieldKey(fieldKey);
		}

		if (type == null) {
			formFieldImpl.setType("");
		}
		else {
			formFieldImpl.setType(type);
		}

		if (label == null) {
			formFieldImpl.setLabel("");
		}
		else {
			formFieldImpl.setLabel(label);
		}

		if (placeholder == null) {
			formFieldImpl.setPlaceholder("");
		}
		else {
			formFieldImpl.setPlaceholder(placeholder);
		}

		if (helpText == null) {
			formFieldImpl.setHelpText("");
		}
		else {
			formFieldImpl.setHelpText(helpText);
		}

		formFieldImpl.setRequired(required);
		formFieldImpl.setPosition(position);

		if (defaultValue == null) {
			formFieldImpl.setDefaultValue("");
		}
		else {
			formFieldImpl.setDefaultValue(defaultValue);
		}

		if (width == null) {
			formFieldImpl.setWidth("");
		}
		else {
			formFieldImpl.setWidth(width);
		}

		if (validation == null) {
			formFieldImpl.setValidation("");
		}
		else {
			formFieldImpl.setValidation(validation);
		}

		if (properties == null) {
			formFieldImpl.setProperties("");
		}
		else {
			formFieldImpl.setProperties(properties);
		}

		if (conditionalRule == null) {
			formFieldImpl.setConditionalRule("");
		}
		else {
			formFieldImpl.setConditionalRule(conditionalRule);
		}

		if (presetKey == null) {
			formFieldImpl.setPresetKey("");
		}
		else {
			formFieldImpl.setPresetKey(presetKey);
		}

		formFieldImpl.setPersonalData(personalData);

		formFieldImpl.resetOriginalValues();

		return formFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		formFieldId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		formVersionId = objectInput.readLong();
		fieldKey = objectInput.readUTF();
		type = objectInput.readUTF();
		label = objectInput.readUTF();
		placeholder = objectInput.readUTF();
		helpText = objectInput.readUTF();

		required = objectInput.readBoolean();

		position = objectInput.readInt();
		defaultValue = objectInput.readUTF();
		width = objectInput.readUTF();
		validation = (String)objectInput.readObject();
		properties = (String)objectInput.readObject();
		conditionalRule = (String)objectInput.readObject();
		presetKey = objectInput.readUTF();

		personalData = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(formFieldId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(formVersionId);

		if (fieldKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fieldKey);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (label == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(label);
		}

		if (placeholder == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(placeholder);
		}

		if (helpText == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(helpText);
		}

		objectOutput.writeBoolean(required);

		objectOutput.writeInt(position);

		if (defaultValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(defaultValue);
		}

		if (width == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(width);
		}

		if (validation == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(validation);
		}

		if (properties == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(properties);
		}

		if (conditionalRule == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(conditionalRule);
		}

		if (presetKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(presetKey);
		}

		objectOutput.writeBoolean(personalData);
	}

	public long mvccVersion;
	public long formFieldId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long formVersionId;
	public String fieldKey;
	public String type;
	public String label;
	public String placeholder;
	public String helpText;
	public boolean required;
	public int position;
	public String defaultValue;
	public String width;
	public String validation;
	public String properties;
	public String conditionalRule;
	public String presetKey;
	public boolean personalData;

}