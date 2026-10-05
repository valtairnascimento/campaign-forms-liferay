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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import io.github.valtairnascimento.campaignforms.exception.NoSuchDataFeedException;
import io.github.valtairnascimento.campaignforms.model.DataFeed;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the data feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see DataFeedUtil
 * @generated
 */
@ProviderType
public interface DataFeedPersistence extends BasePersistence<DataFeed> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataFeedUtil} to access the data feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching data feeds
	 */
	public java.util.List<DataFeed> findByGroupId(long groupId);

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
	public java.util.List<DataFeed> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

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
	public java.util.List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public DataFeed findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public DataFeed findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where groupId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public DataFeed[] findByGroupId_PrevAndNext(
			long dataFeedId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Removes all the data feeds where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching data feeds
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching data feeds
	 */
	public java.util.List<DataFeed> findByCampaignId(long campaignId);

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
	public java.util.List<DataFeed> findByCampaignId(
		long campaignId, int start, int end);

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
	public java.util.List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

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
	public java.util.List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public DataFeed findByCampaignId_First(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public DataFeed findByCampaignId_Last(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public DataFeed[] findByCampaignId_PrevAndNext(
			long dataFeedId, long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
				orderByComparator)
		throws NoSuchDataFeedException;

	/**
	 * Removes all the data feeds where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(long campaignId);

	/**
	 * Returns the number of data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching data feeds
	 */
	public int countByCampaignId(long campaignId);

	/**
	 * Returns the data feed where feedKey = &#63; or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	public DataFeed findByFeedKey(String feedKey)
		throws NoSuchDataFeedException;

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByFeedKey(String feedKey);

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param feedKey the feed key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	public DataFeed fetchByFeedKey(String feedKey, boolean useFinderCache);

	/**
	 * Removes the data feed where feedKey = &#63; from the database.
	 *
	 * @param feedKey the feed key
	 * @return the data feed that was removed
	 */
	public DataFeed removeByFeedKey(String feedKey)
		throws NoSuchDataFeedException;

	/**
	 * Returns the number of data feeds where feedKey = &#63;.
	 *
	 * @param feedKey the feed key
	 * @return the number of matching data feeds
	 */
	public int countByFeedKey(String feedKey);

	/**
	 * Caches the data feed in the entity cache if it is enabled.
	 *
	 * @param dataFeed the data feed
	 */
	public void cacheResult(DataFeed dataFeed);

	/**
	 * Caches the data feeds in the entity cache if it is enabled.
	 *
	 * @param dataFeeds the data feeds
	 */
	public void cacheResult(java.util.List<DataFeed> dataFeeds);

	/**
	 * Creates a new data feed with the primary key. Does not add the data feed to the database.
	 *
	 * @param dataFeedId the primary key for the new data feed
	 * @return the new data feed
	 */
	public DataFeed create(long dataFeedId);

	/**
	 * Removes the data feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed that was removed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public DataFeed remove(long dataFeedId) throws NoSuchDataFeedException;

	public DataFeed updateImpl(DataFeed dataFeed);

	/**
	 * Returns the data feed with the primary key or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	public DataFeed findByPrimaryKey(long dataFeedId)
		throws NoSuchDataFeedException;

	/**
	 * Returns the data feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed, or <code>null</code> if a data feed with the primary key could not be found
	 */
	public DataFeed fetchByPrimaryKey(long dataFeedId);

	/**
	 * Returns all the data feeds.
	 *
	 * @return the data feeds
	 */
	public java.util.List<DataFeed> findAll();

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
	public java.util.List<DataFeed> findAll(int start, int end);

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
	public java.util.List<DataFeed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator);

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
	public java.util.List<DataFeed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DataFeed>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the data feeds from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of data feeds.
	 *
	 * @return the number of data feeds
	 */
	public int countAll();

}