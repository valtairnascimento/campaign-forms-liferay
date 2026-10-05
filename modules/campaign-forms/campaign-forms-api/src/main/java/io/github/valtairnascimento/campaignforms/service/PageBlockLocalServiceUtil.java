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

package io.github.valtairnascimento.campaignforms.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.model.PageBlock;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for PageBlock. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.PageBlockLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see PageBlockLocalService
 * @generated
 */
public class PageBlockLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.PageBlockLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PageBlock addPageBlock(
			long pageConfigurationId, String blockType, String configuration,
			boolean enabled)
		throws PortalException {

		return getService().addPageBlock(
			pageConfigurationId, blockType, configuration, enabled);
	}

	/**
	 * Adds the page block to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageBlock the page block
	 * @return the page block that was added
	 */
	public static PageBlock addPageBlock(PageBlock pageBlock) {
		return getService().addPageBlock(pageBlock);
	}

	public static List<PageBlock> copyPageBlocks(
		long sourcePageConfigurationId,
		io.github.valtairnascimento.campaignforms.model.PageConfiguration
			targetPageConfiguration) {

		return getService().copyPageBlocks(
			sourcePageConfigurationId, targetPageConfiguration);
	}

	/**
	 * Creates a new page block with the primary key. Does not add the page block to the database.
	 *
	 * @param pageBlockId the primary key for the new page block
	 * @return the new page block
	 */
	public static PageBlock createPageBlock(long pageBlockId) {
		return getService().createPageBlock(pageBlockId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the page block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block that was removed
	 * @throws PortalException if a page block with the primary key could not be found
	 */
	public static PageBlock deletePageBlock(long pageBlockId)
		throws PortalException {

		return getService().deletePageBlock(pageBlockId);
	}

	/**
	 * Deletes the page block from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageBlock the page block
	 * @return the page block that was removed
	 */
	public static PageBlock deletePageBlock(PageBlock pageBlock) {
		return getService().deletePageBlock(pageBlock);
	}

	public static void deletePageBlocks(long pageConfigurationId) {
		getService().deletePageBlocks(pageConfigurationId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static PageBlock duplicatePageBlock(long pageBlockId)
		throws PortalException {

		return getService().duplicatePageBlock(pageBlockId);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static PageBlock fetchPageBlock(long pageBlockId) {
		return getService().fetchPageBlock(pageBlockId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Returns the page block with the primary key.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block
	 * @throws PortalException if a page block with the primary key could not be found
	 */
	public static PageBlock getPageBlock(long pageBlockId)
		throws PortalException {

		return getService().getPageBlock(pageBlockId);
	}

	/**
	 * Returns a range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @return the range of page blocks
	 */
	public static List<PageBlock> getPageBlocks(int start, int end) {
		return getService().getPageBlocks(start, end);
	}

	public static List<PageBlock> getPageBlocks(long pageConfigurationId) {
		return getService().getPageBlocks(pageConfigurationId);
	}

	/**
	 * Returns the number of page blocks.
	 *
	 * @return the number of page blocks
	 */
	public static int getPageBlocksCount() {
		return getService().getPageBlocksCount();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<PageBlock> movePageBlock(long pageBlockId, int position)
		throws PortalException {

		return getService().movePageBlock(pageBlockId, position);
	}

	public static List<PageBlock> reorderPageBlocks(
			long pageConfigurationId, long[] orderedPageBlockIds)
		throws PortalException {

		return getService().reorderPageBlocks(
			pageConfigurationId, orderedPageBlockIds);
	}

	public static PageBlock updatePageBlock(
			long pageBlockId, String configuration, boolean enabled)
		throws PortalException {

		return getService().updatePageBlock(
			pageBlockId, configuration, enabled);
	}

	/**
	 * Updates the page block in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageBlockLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageBlock the page block
	 * @return the page block that was updated
	 */
	public static PageBlock updatePageBlock(PageBlock pageBlock) {
		return getService().updatePageBlock(pageBlock);
	}

	public static PageBlockLocalService getService() {
		return _service;
	}

	private static volatile PageBlockLocalService _service;

}