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
 * This class is a wrapper for {@link DataFeed}.
 * </p>
 *
 * @author Valtair Nascimento
 * @see DataFeed
 * @generated
 */
public class DataFeedWrapper
	extends BaseModelWrapper<DataFeed>
	implements DataFeed, ModelWrapper<DataFeed> {

	public DataFeedWrapper(DataFeed dataFeed) {
		super(dataFeed);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("dataFeedId", getDataFeedId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("feedKey", getFeedKey());
		attributes.put("tokenHash", getTokenHash());
		attributes.put("tokenHint", getTokenHint());
		attributes.put("name", getName());
		attributes.put("campaignId", getCampaignId());
		attributes.put("seriesKey", getSeriesKey());
		attributes.put("format", getFormat());
		attributes.put("includeMetadata", isIncludeMetadata());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("lastAccessDate", getLastAccessDate());
		attributes.put("active", isActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long dataFeedId = (Long)attributes.get("dataFeedId");

		if (dataFeedId != null) {
			setDataFeedId(dataFeedId);
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

		String feedKey = (String)attributes.get("feedKey");

		if (feedKey != null) {
			setFeedKey(feedKey);
		}

		String tokenHash = (String)attributes.get("tokenHash");

		if (tokenHash != null) {
			setTokenHash(tokenHash);
		}

		String tokenHint = (String)attributes.get("tokenHint");

		if (tokenHint != null) {
			setTokenHint(tokenHint);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		String seriesKey = (String)attributes.get("seriesKey");

		if (seriesKey != null) {
			setSeriesKey(seriesKey);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		Boolean includeMetadata = (Boolean)attributes.get("includeMetadata");

		if (includeMetadata != null) {
			setIncludeMetadata(includeMetadata);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Date lastAccessDate = (Date)attributes.get("lastAccessDate");

		if (lastAccessDate != null) {
			setLastAccessDate(lastAccessDate);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public DataFeed cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the active of this data feed.
	 *
	 * @return the active of this data feed
	 */
	@Override
	public boolean getActive() {
		return model.getActive();
	}

	/**
	 * Returns the campaign ID of this data feed.
	 *
	 * @return the campaign ID of this data feed
	 */
	@Override
	public long getCampaignId() {
		return model.getCampaignId();
	}

	/**
	 * Returns the company ID of this data feed.
	 *
	 * @return the company ID of this data feed
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this data feed.
	 *
	 * @return the create date of this data feed
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the data feed ID of this data feed.
	 *
	 * @return the data feed ID of this data feed
	 */
	@Override
	public long getDataFeedId() {
		return model.getDataFeedId();
	}

	/**
	 * Returns the expiration date of this data feed.
	 *
	 * @return the expiration date of this data feed
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the feed key of this data feed.
	 *
	 * @return the feed key of this data feed
	 */
	@Override
	public String getFeedKey() {
		return model.getFeedKey();
	}

	/**
	 * Returns the format of this data feed.
	 *
	 * @return the format of this data feed
	 */
	@Override
	public String getFormat() {
		return model.getFormat();
	}

	/**
	 * Returns the group ID of this data feed.
	 *
	 * @return the group ID of this data feed
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the include metadata of this data feed.
	 *
	 * @return the include metadata of this data feed
	 */
	@Override
	public boolean getIncludeMetadata() {
		return model.getIncludeMetadata();
	}

	/**
	 * Returns the last access date of this data feed.
	 *
	 * @return the last access date of this data feed
	 */
	@Override
	public Date getLastAccessDate() {
		return model.getLastAccessDate();
	}

	/**
	 * Returns the modified date of this data feed.
	 *
	 * @return the modified date of this data feed
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this data feed.
	 *
	 * @return the mvcc version of this data feed
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this data feed.
	 *
	 * @return the name of this data feed
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this data feed.
	 *
	 * @return the primary key of this data feed
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the series key of this data feed.
	 *
	 * @return the series key of this data feed
	 */
	@Override
	public String getSeriesKey() {
		return model.getSeriesKey();
	}

	/**
	 * Returns the token hash of this data feed.
	 *
	 * @return the token hash of this data feed
	 */
	@Override
	public String getTokenHash() {
		return model.getTokenHash();
	}

	/**
	 * Returns the token hint of this data feed.
	 *
	 * @return the token hint of this data feed
	 */
	@Override
	public String getTokenHint() {
		return model.getTokenHint();
	}

	/**
	 * Returns the user ID of this data feed.
	 *
	 * @return the user ID of this data feed
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this data feed.
	 *
	 * @return the user name of this data feed
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this data feed.
	 *
	 * @return the user uuid of this data feed
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this data feed is active.
	 *
	 * @return <code>true</code> if this data feed is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return model.isActive();
	}

	/**
	 * Returns <code>true</code> if this data feed is include metadata.
	 *
	 * @return <code>true</code> if this data feed is include metadata; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncludeMetadata() {
		return model.isIncludeMetadata();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this data feed is active.
	 *
	 * @param active the active of this data feed
	 */
	@Override
	public void setActive(boolean active) {
		model.setActive(active);
	}

	/**
	 * Sets the campaign ID of this data feed.
	 *
	 * @param campaignId the campaign ID of this data feed
	 */
	@Override
	public void setCampaignId(long campaignId) {
		model.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this data feed.
	 *
	 * @param companyId the company ID of this data feed
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this data feed.
	 *
	 * @param createDate the create date of this data feed
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the data feed ID of this data feed.
	 *
	 * @param dataFeedId the data feed ID of this data feed
	 */
	@Override
	public void setDataFeedId(long dataFeedId) {
		model.setDataFeedId(dataFeedId);
	}

	/**
	 * Sets the expiration date of this data feed.
	 *
	 * @param expirationDate the expiration date of this data feed
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the feed key of this data feed.
	 *
	 * @param feedKey the feed key of this data feed
	 */
	@Override
	public void setFeedKey(String feedKey) {
		model.setFeedKey(feedKey);
	}

	/**
	 * Sets the format of this data feed.
	 *
	 * @param format the format of this data feed
	 */
	@Override
	public void setFormat(String format) {
		model.setFormat(format);
	}

	/**
	 * Sets the group ID of this data feed.
	 *
	 * @param groupId the group ID of this data feed
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets whether this data feed is include metadata.
	 *
	 * @param includeMetadata the include metadata of this data feed
	 */
	@Override
	public void setIncludeMetadata(boolean includeMetadata) {
		model.setIncludeMetadata(includeMetadata);
	}

	/**
	 * Sets the last access date of this data feed.
	 *
	 * @param lastAccessDate the last access date of this data feed
	 */
	@Override
	public void setLastAccessDate(Date lastAccessDate) {
		model.setLastAccessDate(lastAccessDate);
	}

	/**
	 * Sets the modified date of this data feed.
	 *
	 * @param modifiedDate the modified date of this data feed
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this data feed.
	 *
	 * @param mvccVersion the mvcc version of this data feed
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this data feed.
	 *
	 * @param name the name of this data feed
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this data feed.
	 *
	 * @param primaryKey the primary key of this data feed
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the series key of this data feed.
	 *
	 * @param seriesKey the series key of this data feed
	 */
	@Override
	public void setSeriesKey(String seriesKey) {
		model.setSeriesKey(seriesKey);
	}

	/**
	 * Sets the token hash of this data feed.
	 *
	 * @param tokenHash the token hash of this data feed
	 */
	@Override
	public void setTokenHash(String tokenHash) {
		model.setTokenHash(tokenHash);
	}

	/**
	 * Sets the token hint of this data feed.
	 *
	 * @param tokenHint the token hint of this data feed
	 */
	@Override
	public void setTokenHint(String tokenHint) {
		model.setTokenHint(tokenHint);
	}

	/**
	 * Sets the user ID of this data feed.
	 *
	 * @param userId the user ID of this data feed
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this data feed.
	 *
	 * @param userName the user name of this data feed
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this data feed.
	 *
	 * @param userUuid the user uuid of this data feed
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DataFeedWrapper wrap(DataFeed dataFeed) {
		return new DataFeedWrapper(dataFeed);
	}

}