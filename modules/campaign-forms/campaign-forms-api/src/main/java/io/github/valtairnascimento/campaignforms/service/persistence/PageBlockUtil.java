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

import io.github.valtairnascimento.campaignforms.model.PageBlock;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the page block service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.PageBlockPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageBlockPersistence
 * @generated
 */
public class PageBlockUtil {

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
	public static void clearCache(PageBlock pageBlock) {
		getPersistence().clearCache(pageBlock);
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
	public static Map<Serializable, PageBlock> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PageBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PageBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PageBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PageBlock> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PageBlock update(PageBlock pageBlock) {
		return getPersistence().update(pageBlock);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PageBlock update(
		PageBlock pageBlock, ServiceContext serviceContext) {

		return getPersistence().update(pageBlock, serviceContext);
	}

	/**
	 * Returns all the page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the matching page blocks
	 */
	public static List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId) {

		return getPersistence().findByPageConfigurationId(pageConfigurationId);
	}

	/**
	 * Returns a range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @return the range of matching page blocks
	 */
	public static List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end) {

		return getPersistence().findByPageConfigurationId(
			pageConfigurationId, start, end);
	}

	/**
	 * Returns an ordered range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page blocks
	 */
	public static List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		OrderByComparator<PageBlock> orderByComparator) {

		return getPersistence().findByPageConfigurationId(
			pageConfigurationId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page blocks
	 */
	public static List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		OrderByComparator<PageBlock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPageConfigurationId(
			pageConfigurationId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public static PageBlock findByPageConfigurationId_First(
			long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().findByPageConfigurationId_First(
			pageConfigurationId, orderByComparator);
	}

	/**
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public static PageBlock fetchByPageConfigurationId_First(
		long pageConfigurationId,
		OrderByComparator<PageBlock> orderByComparator) {

		return getPersistence().fetchByPageConfigurationId_First(
			pageConfigurationId, orderByComparator);
	}

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public static PageBlock findByPageConfigurationId_Last(
			long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().findByPageConfigurationId_Last(
			pageConfigurationId, orderByComparator);
	}

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public static PageBlock fetchByPageConfigurationId_Last(
		long pageConfigurationId,
		OrderByComparator<PageBlock> orderByComparator) {

		return getPersistence().fetchByPageConfigurationId_Last(
			pageConfigurationId, orderByComparator);
	}

	/**
	 * Returns the page blocks before and after the current page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageBlockId the primary key of the current page block
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public static PageBlock[] findByPageConfigurationId_PrevAndNext(
			long pageBlockId, long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().findByPageConfigurationId_PrevAndNext(
			pageBlockId, pageConfigurationId, orderByComparator);
	}

	/**
	 * Removes all the page blocks where pageConfigurationId = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 */
	public static void removeByPageConfigurationId(long pageConfigurationId) {
		getPersistence().removeByPageConfigurationId(pageConfigurationId);
	}

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the number of matching page blocks
	 */
	public static int countByPageConfigurationId(long pageConfigurationId) {
		return getPersistence().countByPageConfigurationId(pageConfigurationId);
	}

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public static PageBlock findByPC_BK(
			long pageConfigurationId, String blockKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().findByPC_BK(pageConfigurationId, blockKey);
	}

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public static PageBlock fetchByPC_BK(
		long pageConfigurationId, String blockKey) {

		return getPersistence().fetchByPC_BK(pageConfigurationId, blockKey);
	}

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public static PageBlock fetchByPC_BK(
		long pageConfigurationId, String blockKey, boolean useFinderCache) {

		return getPersistence().fetchByPC_BK(
			pageConfigurationId, blockKey, useFinderCache);
	}

	/**
	 * Removes the page block where pageConfigurationId = &#63; and blockKey = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the page block that was removed
	 */
	public static PageBlock removeByPC_BK(
			long pageConfigurationId, String blockKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().removeByPC_BK(pageConfigurationId, blockKey);
	}

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63; and blockKey = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the number of matching page blocks
	 */
	public static int countByPC_BK(long pageConfigurationId, String blockKey) {
		return getPersistence().countByPC_BK(pageConfigurationId, blockKey);
	}

	/**
	 * Caches the page block in the entity cache if it is enabled.
	 *
	 * @param pageBlock the page block
	 */
	public static void cacheResult(PageBlock pageBlock) {
		getPersistence().cacheResult(pageBlock);
	}

	/**
	 * Caches the page blocks in the entity cache if it is enabled.
	 *
	 * @param pageBlocks the page blocks
	 */
	public static void cacheResult(List<PageBlock> pageBlocks) {
		getPersistence().cacheResult(pageBlocks);
	}

	/**
	 * Creates a new page block with the primary key. Does not add the page block to the database.
	 *
	 * @param pageBlockId the primary key for the new page block
	 * @return the new page block
	 */
	public static PageBlock create(long pageBlockId) {
		return getPersistence().create(pageBlockId);
	}

	/**
	 * Removes the page block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block that was removed
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public static PageBlock remove(long pageBlockId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().remove(pageBlockId);
	}

	public static PageBlock updateImpl(PageBlock pageBlock) {
		return getPersistence().updateImpl(pageBlock);
	}

	/**
	 * Returns the page block with the primary key or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public static PageBlock findByPrimaryKey(long pageBlockId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageBlockException {

		return getPersistence().findByPrimaryKey(pageBlockId);
	}

	/**
	 * Returns the page block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block, or <code>null</code> if a page block with the primary key could not be found
	 */
	public static PageBlock fetchByPrimaryKey(long pageBlockId) {
		return getPersistence().fetchByPrimaryKey(pageBlockId);
	}

	/**
	 * Returns all the page blocks.
	 *
	 * @return the page blocks
	 */
	public static List<PageBlock> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @return the range of page blocks
	 */
	public static List<PageBlock> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of page blocks
	 */
	public static List<PageBlock> findAll(
		int start, int end, OrderByComparator<PageBlock> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of page blocks
	 */
	public static List<PageBlock> findAll(
		int start, int end, OrderByComparator<PageBlock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the page blocks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of page blocks.
	 *
	 * @return the number of page blocks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PageBlockPersistence getPersistence() {
		return _persistence;
	}

	private static volatile PageBlockPersistence _persistence;

}