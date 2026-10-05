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
 * This class is a wrapper for {@link PageTemplate}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageTemplate
 * @generated
 */
public class PageTemplateWrapper
	extends BaseModelWrapper<PageTemplate>
	implements ModelWrapper<PageTemplate>, PageTemplate {

	public PageTemplateWrapper(PageTemplate pageTemplate) {
		super(pageTemplate);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("pageTemplateId", getPageTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("templateKey", getTemplateKey());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("systemTemplate", isSystemTemplate());
		attributes.put("blocks", getBlocks());

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

		Long pageTemplateId = (Long)attributes.get("pageTemplateId");

		if (pageTemplateId != null) {
			setPageTemplateId(pageTemplateId);
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

		String templateKey = (String)attributes.get("templateKey");

		if (templateKey != null) {
			setTemplateKey(templateKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean systemTemplate = (Boolean)attributes.get("systemTemplate");

		if (systemTemplate != null) {
			setSystemTemplate(systemTemplate);
		}

		String blocks = (String)attributes.get("blocks");

		if (blocks != null) {
			setBlocks(blocks);
		}
	}

	@Override
	public PageTemplate cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the blocks of this page template.
	 *
	 * @return the blocks of this page template
	 */
	@Override
	public String getBlocks() {
		return model.getBlocks();
	}

	/**
	 * Returns the company ID of this page template.
	 *
	 * @return the company ID of this page template
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this page template.
	 *
	 * @return the create date of this page template
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this page template.
	 *
	 * @return the description of this page template
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the group ID of this page template.
	 *
	 * @return the group ID of this page template
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this page template.
	 *
	 * @return the modified date of this page template
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this page template.
	 *
	 * @return the mvcc version of this page template
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this page template.
	 *
	 * @return the name of this page template
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the page template ID of this page template.
	 *
	 * @return the page template ID of this page template
	 */
	@Override
	public long getPageTemplateId() {
		return model.getPageTemplateId();
	}

	/**
	 * Returns the primary key of this page template.
	 *
	 * @return the primary key of this page template
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the system template of this page template.
	 *
	 * @return the system template of this page template
	 */
	@Override
	public boolean getSystemTemplate() {
		return model.getSystemTemplate();
	}

	/**
	 * Returns the template key of this page template.
	 *
	 * @return the template key of this page template
	 */
	@Override
	public String getTemplateKey() {
		return model.getTemplateKey();
	}

	/**
	 * Returns the user ID of this page template.
	 *
	 * @return the user ID of this page template
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this page template.
	 *
	 * @return the user name of this page template
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this page template.
	 *
	 * @return the user uuid of this page template
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this page template.
	 *
	 * @return the uuid of this page template
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this page template is system template.
	 *
	 * @return <code>true</code> if this page template is system template; <code>false</code> otherwise
	 */
	@Override
	public boolean isSystemTemplate() {
		return model.isSystemTemplate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the blocks of this page template.
	 *
	 * @param blocks the blocks of this page template
	 */
	@Override
	public void setBlocks(String blocks) {
		model.setBlocks(blocks);
	}

	/**
	 * Sets the company ID of this page template.
	 *
	 * @param companyId the company ID of this page template
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this page template.
	 *
	 * @param createDate the create date of this page template
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this page template.
	 *
	 * @param description the description of this page template
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this page template.
	 *
	 * @param groupId the group ID of this page template
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this page template.
	 *
	 * @param modifiedDate the modified date of this page template
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this page template.
	 *
	 * @param mvccVersion the mvcc version of this page template
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this page template.
	 *
	 * @param name the name of this page template
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the page template ID of this page template.
	 *
	 * @param pageTemplateId the page template ID of this page template
	 */
	@Override
	public void setPageTemplateId(long pageTemplateId) {
		model.setPageTemplateId(pageTemplateId);
	}

	/**
	 * Sets the primary key of this page template.
	 *
	 * @param primaryKey the primary key of this page template
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this page template is system template.
	 *
	 * @param systemTemplate the system template of this page template
	 */
	@Override
	public void setSystemTemplate(boolean systemTemplate) {
		model.setSystemTemplate(systemTemplate);
	}

	/**
	 * Sets the template key of this page template.
	 *
	 * @param templateKey the template key of this page template
	 */
	@Override
	public void setTemplateKey(String templateKey) {
		model.setTemplateKey(templateKey);
	}

	/**
	 * Sets the user ID of this page template.
	 *
	 * @param userId the user ID of this page template
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this page template.
	 *
	 * @param userName the user name of this page template
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this page template.
	 *
	 * @param userUuid the user uuid of this page template
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this page template.
	 *
	 * @param uuid the uuid of this page template
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
	protected PageTemplateWrapper wrap(PageTemplate pageTemplate) {
		return new PageTemplateWrapper(pageTemplate);
	}

}