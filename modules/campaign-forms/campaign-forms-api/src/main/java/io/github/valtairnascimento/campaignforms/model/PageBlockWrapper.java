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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PageBlock}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageBlock
 * @generated
 */
public class PageBlockWrapper
	extends BaseModelWrapper<PageBlock>
	implements ModelWrapper<PageBlock>, PageBlock {

	public PageBlockWrapper(PageBlock pageBlock) {
		super(pageBlock);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("pageBlockId", getPageBlockId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("pageConfigurationId", getPageConfigurationId());
		attributes.put("blockType", getBlockType());
		attributes.put("blockKey", getBlockKey());
		attributes.put("position", getPosition());
		attributes.put("enabled", isEnabled());
		attributes.put("schemaVersion", getSchemaVersion());
		attributes.put("configuration", getConfiguration());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long pageBlockId = (Long)attributes.get("pageBlockId");

		if (pageBlockId != null) {
			setPageBlockId(pageBlockId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long pageConfigurationId = (Long)attributes.get("pageConfigurationId");

		if (pageConfigurationId != null) {
			setPageConfigurationId(pageConfigurationId);
		}

		String blockType = (String)attributes.get("blockType");

		if (blockType != null) {
			setBlockType(blockType);
		}

		String blockKey = (String)attributes.get("blockKey");

		if (blockKey != null) {
			setBlockKey(blockKey);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		Integer schemaVersion = (Integer)attributes.get("schemaVersion");

		if (schemaVersion != null) {
			setSchemaVersion(schemaVersion);
		}

		String configuration = (String)attributes.get("configuration");

		if (configuration != null) {
			setConfiguration(configuration);
		}
	}

	@Override
	public PageBlock cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the block key of this page block.
	 *
	 * @return the block key of this page block
	 */
	@Override
	public String getBlockKey() {
		return model.getBlockKey();
	}

	/**
	 * Returns the block type of this page block.
	 *
	 * @return the block type of this page block
	 */
	@Override
	public String getBlockType() {
		return model.getBlockType();
	}

	/**
	 * Returns the company ID of this page block.
	 *
	 * @return the company ID of this page block
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the configuration of this page block.
	 *
	 * @return the configuration of this page block
	 */
	@Override
	public String getConfiguration() {
		return model.getConfiguration();
	}

	/**
	 * Returns the create date of this page block.
	 *
	 * @return the create date of this page block
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the enabled of this page block.
	 *
	 * @return the enabled of this page block
	 */
	@Override
	public boolean getEnabled() {
		return model.getEnabled();
	}

	/**
	 * Returns the modified date of this page block.
	 *
	 * @return the modified date of this page block
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this page block.
	 *
	 * @return the mvcc version of this page block
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the page block ID of this page block.
	 *
	 * @return the page block ID of this page block
	 */
	@Override
	public long getPageBlockId() {
		return model.getPageBlockId();
	}

	/**
	 * Returns the page configuration ID of this page block.
	 *
	 * @return the page configuration ID of this page block
	 */
	@Override
	public long getPageConfigurationId() {
		return model.getPageConfigurationId();
	}

	/**
	 * Returns the position of this page block.
	 *
	 * @return the position of this page block
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this page block.
	 *
	 * @return the primary key of this page block
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the schema version of this page block.
	 *
	 * @return the schema version of this page block
	 */
	@Override
	public int getSchemaVersion() {
		return model.getSchemaVersion();
	}

	/**
	 * Returns <code>true</code> if this page block is enabled.
	 *
	 * @return <code>true</code> if this page block is enabled; <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return model.isEnabled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the block key of this page block.
	 *
	 * @param blockKey the block key of this page block
	 */
	@Override
	public void setBlockKey(String blockKey) {
		model.setBlockKey(blockKey);
	}

	/**
	 * Sets the block type of this page block.
	 *
	 * @param blockType the block type of this page block
	 */
	@Override
	public void setBlockType(String blockType) {
		model.setBlockType(blockType);
	}

	/**
	 * Sets the company ID of this page block.
	 *
	 * @param companyId the company ID of this page block
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the configuration of this page block.
	 *
	 * @param configuration the configuration of this page block
	 */
	@Override
	public void setConfiguration(String configuration) {
		model.setConfiguration(configuration);
	}

	/**
	 * Sets the create date of this page block.
	 *
	 * @param createDate the create date of this page block
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets whether this page block is enabled.
	 *
	 * @param enabled the enabled of this page block
	 */
	@Override
	public void setEnabled(boolean enabled) {
		model.setEnabled(enabled);
	}

	/**
	 * Sets the modified date of this page block.
	 *
	 * @param modifiedDate the modified date of this page block
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this page block.
	 *
	 * @param mvccVersion the mvcc version of this page block
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the page block ID of this page block.
	 *
	 * @param pageBlockId the page block ID of this page block
	 */
	@Override
	public void setPageBlockId(long pageBlockId) {
		model.setPageBlockId(pageBlockId);
	}

	/**
	 * Sets the page configuration ID of this page block.
	 *
	 * @param pageConfigurationId the page configuration ID of this page block
	 */
	@Override
	public void setPageConfigurationId(long pageConfigurationId) {
		model.setPageConfigurationId(pageConfigurationId);
	}

	/**
	 * Sets the position of this page block.
	 *
	 * @param position the position of this page block
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this page block.
	 *
	 * @param primaryKey the primary key of this page block
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the schema version of this page block.
	 *
	 * @param schemaVersion the schema version of this page block
	 */
	@Override
	public void setSchemaVersion(int schemaVersion) {
		model.setSchemaVersion(schemaVersion);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected PageBlockWrapper wrap(PageBlock pageBlock) {
		return new PageBlockWrapper(pageBlock);
	}

}