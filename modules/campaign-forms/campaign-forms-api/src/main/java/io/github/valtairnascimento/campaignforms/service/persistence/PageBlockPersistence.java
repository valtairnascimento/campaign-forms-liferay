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

import io.github.valtairnascimento.campaignforms.exception.NoSuchPageBlockException;
import io.github.valtairnascimento.campaignforms.model.PageBlock;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the page block service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageBlockUtil
 * @generated
 */
@ProviderType
public interface PageBlockPersistence extends BasePersistence<PageBlock> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PageBlockUtil} to access the page block persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the matching page blocks
	 */
	public java.util.List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId);

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
	public java.util.List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end);

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
	public java.util.List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator);

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
	public java.util.List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public PageBlock findByPageConfigurationId_First(
			long pageConfigurationId,
			com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
				orderByComparator)
		throws NoSuchPageBlockException;

	/**
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public PageBlock fetchByPageConfigurationId_First(
		long pageConfigurationId,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator);

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public PageBlock findByPageConfigurationId_Last(
			long pageConfigurationId,
			com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
				orderByComparator)
		throws NoSuchPageBlockException;

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public PageBlock fetchByPageConfigurationId_Last(
		long pageConfigurationId,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator);

	/**
	 * Returns the page blocks before and after the current page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageBlockId the primary key of the current page block
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public PageBlock[] findByPageConfigurationId_PrevAndNext(
			long pageBlockId, long pageConfigurationId,
			com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
				orderByComparator)
		throws NoSuchPageBlockException;

	/**
	 * Removes all the page blocks where pageConfigurationId = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 */
	public void removeByPageConfigurationId(long pageConfigurationId);

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the number of matching page blocks
	 */
	public int countByPageConfigurationId(long pageConfigurationId);

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	public PageBlock findByPC_BK(long pageConfigurationId, String blockKey)
		throws NoSuchPageBlockException;

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public PageBlock fetchByPC_BK(long pageConfigurationId, String blockKey);

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	public PageBlock fetchByPC_BK(
		long pageConfigurationId, String blockKey, boolean useFinderCache);

	/**
	 * Removes the page block where pageConfigurationId = &#63; and blockKey = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the page block that was removed
	 */
	public PageBlock removeByPC_BK(long pageConfigurationId, String blockKey)
		throws NoSuchPageBlockException;

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63; and blockKey = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the number of matching page blocks
	 */
	public int countByPC_BK(long pageConfigurationId, String blockKey);

	/**
	 * Caches the page block in the entity cache if it is enabled.
	 *
	 * @param pageBlock the page block
	 */
	public void cacheResult(PageBlock pageBlock);

	/**
	 * Caches the page blocks in the entity cache if it is enabled.
	 *
	 * @param pageBlocks the page blocks
	 */
	public void cacheResult(java.util.List<PageBlock> pageBlocks);

	/**
	 * Creates a new page block with the primary key. Does not add the page block to the database.
	 *
	 * @param pageBlockId the primary key for the new page block
	 * @return the new page block
	 */
	public PageBlock create(long pageBlockId);

	/**
	 * Removes the page block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block that was removed
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public PageBlock remove(long pageBlockId) throws NoSuchPageBlockException;

	public PageBlock updateImpl(PageBlock pageBlock);

	/**
	 * Returns the page block with the primary key or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	public PageBlock findByPrimaryKey(long pageBlockId)
		throws NoSuchPageBlockException;

	/**
	 * Returns the page block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block, or <code>null</code> if a page block with the primary key could not be found
	 */
	public PageBlock fetchByPrimaryKey(long pageBlockId);

	/**
	 * Returns all the page blocks.
	 *
	 * @return the page blocks
	 */
	public java.util.List<PageBlock> findAll();

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
	public java.util.List<PageBlock> findAll(int start, int end);

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
	public java.util.List<PageBlock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator);

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
	public java.util.List<PageBlock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageBlock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the page blocks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of page blocks.
	 *
	 * @return the number of page blocks
	 */
	public int countAll();

}