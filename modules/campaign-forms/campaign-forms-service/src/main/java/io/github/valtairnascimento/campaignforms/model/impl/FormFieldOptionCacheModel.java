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

import io.github.valtairnascimento.campaignforms.model.FormFieldOption;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FormFieldOption in entity cache.
 *
 * @author Valtair Nascimento
 * @generated
 */
public class FormFieldOptionCacheModel
	implements CacheModel<FormFieldOption>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FormFieldOptionCacheModel)) {
			return false;
		}

		FormFieldOptionCacheModel formFieldOptionCacheModel =
			(FormFieldOptionCacheModel)object;

		if ((formFieldOptionId ==
				formFieldOptionCacheModel.formFieldOptionId) &&
			(mvccVersion == formFieldOptionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, formFieldOptionId);

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
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", formFieldOptionId=");
		sb.append(formFieldOptionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", formFieldId=");
		sb.append(formFieldId);
		sb.append(", label=");
		sb.append(label);
		sb.append(", value=");
		sb.append(value);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormFieldOption toEntityModel() {
		FormFieldOptionImpl formFieldOptionImpl = new FormFieldOptionImpl();

		formFieldOptionImpl.setMvccVersion(mvccVersion);
		formFieldOptionImpl.setFormFieldOptionId(formFieldOptionId);
		formFieldOptionImpl.setCompanyId(companyId);
		formFieldOptionImpl.setFormFieldId(formFieldId);

		if (label == null) {
			formFieldOptionImpl.setLabel("");
		}
		else {
			formFieldOptionImpl.setLabel(label);
		}

		if (value == null) {
			formFieldOptionImpl.setValue("");
		}
		else {
			formFieldOptionImpl.setValue(value);
		}

		formFieldOptionImpl.setPosition(position);

		formFieldOptionImpl.resetOriginalValues();

		return formFieldOptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		formFieldOptionId = objectInput.readLong();

		companyId = objectInput.readLong();

		formFieldId = objectInput.readLong();
		label = objectInput.readUTF();
		value = objectInput.readUTF();

		position = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(formFieldOptionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(formFieldId);

		if (label == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(label);
		}

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}

		objectOutput.writeInt(position);
	}

	public long mvccVersion;
	public long formFieldOptionId;
	public long companyId;
	public long formFieldId;
	public String label;
	public String value;
	public int position;

}