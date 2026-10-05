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

package io.github.valtairnascimento.campaignforms.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.model.DataFeed;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the data feed service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.DataFeedPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see DataFeedPersistence
 * @generated
 */
public class DataFeedUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DataFeed dataFeed) {
		getPersistence().clearCache(dataFeed);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, DataFeed> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DataFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DataFeed update(DataFeed dataFeed) {
		return getPersistence().update(dataFeed);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DataFeed update(
		DataFeed dataFeed, ServiceContext serviceContext) {

		return getPersistence().update(dataFeed, serviceContext);
	}

	/**
	 * Returns all the data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching data feeds
	 */
	public static List<DataFeed> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of matching data feeds
	 */
	public static List<DataFeed> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data feeds
	 */
	public static List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching data feeds
	 */
	public static List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public static DataFeed findByGroupId_First(
			long groupId, OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByGroupId_First(
		long groupId, OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public static DataFeed findByGroupId_Last(
			long groupId, OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByGroupId_Last(
		long groupId, OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where groupId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public static DataFeed[] findByGroupId_PrevAndNext(
			long dataFeedId, long groupId,
			OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByGroupId_PrevAndNext(
			dataFeedId, groupId, orderByComparator);
	}

	/**
	 * Removes all the data feeds where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching data feeds
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching data feeds
	 */
	public static List<DataFeed> findByCampaignId(long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

	/**
	 * Returns a range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of matching data feeds
	 */
	public static List<DataFeed> findByCampaignId(
		long campaignId, int start, int end) {

		return getPersistence().findByCampaignId(campaignId, start, end);
	}

	/**
	 * Returns an ordered range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data feeds
	 */
	public static List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching data feeds
	 */
	public static List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public static DataFeed findByCampaignId_First(
			long campaignId, OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByCampaignId_First(
		long campaignId, OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().fetchByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public static DataFeed findByCampaignId_Last(
			long campaignId, OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByCampaignId_Last(
		long campaignId, OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().fetchByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public static DataFeed[] findByCampaignId_PrevAndNext(
			long dataFeedId, long campaignId,
			OrderByComparator<DataFeed> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByCampaignId_PrevAndNext(
			dataFeedId, campaignId, orderByComparator);
	}

	/**
	 * Removes all the data feeds where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public static void removeByCampaignId(long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	 * Returns the number of data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching data feeds
	 */
	public static int countByCampaignId(long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	 * Returns the data feed where feedKey = &#63; or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public static DataFeed findByFeedKey(String feedKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByFeedKey(feedKey);
	}

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByFeedKey(String feedKey) {
		return getPersistence().fetchByFeedKey(feedKey);
	}

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param feedKey the feed key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public static DataFeed fetchByFeedKey(
		String feedKey, boolean useFinderCache) {

		return getPersistence().fetchByFeedKey(feedKey, useFinderCache);
	}

	/**
	 * Removes the data feed where feedKey = &#63; from the database.
	 *
	 * @param feedKey the feed key
	 * @return the data feed that was removed
	 */
	public static DataFeed removeByFeedKey(String feedKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().removeByFeedKey(feedKey);
	}

	/**
	 * Returns the number of data feeds where feedKey = &#63;.
	 *
	 * @param feedKey the feed key
	 * @return the number of matching data feeds
	 */
	public static int countByFeedKey(String feedKey) {
		return getPersistence().countByFeedKey(feedKey);
	}

	/**
	 * Caches the data feed in the entity cache if it is enabled.
	 *
	 * @param dataFeed the data feed
	 */
	public static void cacheResult(DataFeed dataFeed) {
		getPersistence().cacheResult(dataFeed);
	}

	/**
	 * Caches the data feeds in the entity cache if it is enabled.
	 *
	 * @param dataFeeds the data feeds
	 */
	public static void cacheResult(List<DataFeed> dataFeeds) {
		getPersistence().cacheResult(dataFeeds);
	}

	/**
	 * Creates a new data feed with the primary key. Does not add the data feed to the database.
	 *
	 * @param dataFeedId the primary key for the new data feed
	 * @return the new data feed
	 */
	public static DataFeed create(long dataFeedId) {
		return getPersistence().create(dataFeedId);
	}

	/**
	 * Removes the data feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed that was removed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public static DataFeed remove(long dataFeedId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().remove(dataFeedId);
	}

	public static DataFeed updateImpl(DataFeed dataFeed) {
		return getPersistence().updateImpl(dataFeed);
	}

	/**
	 * Returns the data feed with the primary key or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public static DataFeed findByPrimaryKey(long dataFeedId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchDataFeedException {

		return getPersistence().findByPrimaryKey(dataFeedId);
	}

	/**
	 * Returns the data feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed, or <code>null</code> if a data feed with the primary key could not be found
	 */
	public static DataFeed fetchByPrimaryKey(long dataFeedId) {
		return getPersistence().fetchByPrimaryKey(dataFeedId);
	}

	/**
	 * Returns all the data feeds.
	 *
	 * @return the data feeds
	 */
	public static List<DataFeed> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of data feeds
	 */
	public static List<DataFeed> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data feeds
	 */
	public static List<DataFeed> findAll(
		int start, int end, OrderByComparator<DataFeed> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of data feeds
	 */
	public static List<DataFeed> findAll(
		int start, int end, OrderByComparator<DataFeed> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the data feeds from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of data feeds.
	 *
	 * @return the number of data feeds
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DataFeedPersistence getPersistence() {
		return _persistence;
	}

	private static volatile DataFeedPersistence _persistence;

}