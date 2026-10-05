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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PageBlockLocalService}.
 *
 * @author Valtair Nascimento
 * @see PageBlockLocalService
 * @generated
 */
public class PageBlockLocalServiceWrapper
	implements PageBlockLocalService, ServiceWrapper<PageBlockLocalService> {

	public PageBlockLocalServiceWrapper() {
		this(null);
	}

	public PageBlockLocalServiceWrapper(
		PageBlockLocalService pageBlockLocalService) {

		_pageBlockLocalService = pageBlockLocalService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			addPageBlock(
				long pageConfigurationId, String blockType,
				String configuration, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.addPageBlock(
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
		addPageBlock(
			io.github.valtairnascimento.campaignforms.model.PageBlock
				pageBlock) {

		return _pageBlockLocalService.addPageBlock(pageBlock);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
			copyPageBlocks(
				long sourcePageConfigurationId,
				io.github.valtairnascimento.campaignforms.model.
					PageConfiguration targetPageConfiguration) {

		return _pageBlockLocalService.copyPageBlocks(
			sourcePageConfigurationId, targetPageConfiguration);
	}

	/**
	 * Creates a new page block with the primary key. Does not add the page block to the database.
	 *
	 * @param pageBlockId the primary key for the new page block
	 * @return the new page block
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
		createPageBlock(long pageBlockId) {

		return _pageBlockLocalService.createPageBlock(pageBlockId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			deletePageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.deletePageBlock(pageBlockId);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
		deletePageBlock(
			io.github.valtairnascimento.campaignforms.model.PageBlock
				pageBlock) {

		return _pageBlockLocalService.deletePageBlock(pageBlock);
	}

	@Override
	public void deletePageBlocks(long pageConfigurationId) {
		_pageBlockLocalService.deletePageBlocks(pageConfigurationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _pageBlockLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _pageBlockLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			duplicatePageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.duplicatePageBlock(pageBlockId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pageBlockLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _pageBlockLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _pageBlockLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _pageBlockLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _pageBlockLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _pageBlockLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
		fetchPageBlock(long pageBlockId) {

		return _pageBlockLocalService.fetchPageBlock(pageBlockId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _pageBlockLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _pageBlockLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageBlockLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the page block with the primary key.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block
	 * @throws PortalException if a page block with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			getPageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.getPageBlock(pageBlockId);
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
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
			getPageBlocks(int start, int end) {

		return _pageBlockLocalService.getPageBlocks(start, end);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
			getPageBlocks(long pageConfigurationId) {

		return _pageBlockLocalService.getPageBlocks(pageConfigurationId);
	}

	/**
	 * Returns the number of page blocks.
	 *
	 * @return the number of page blocks
	 */
	@Override
	public int getPageBlocksCount() {
		return _pageBlockLocalService.getPageBlocksCount();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				movePageBlock(long pageBlockId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.movePageBlock(pageBlockId, position);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				reorderPageBlocks(
					long pageConfigurationId, long[] orderedPageBlockIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.reorderPageBlocks(
			pageConfigurationId, orderedPageBlockIds);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			updatePageBlock(
				long pageBlockId, String configuration, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageBlockLocalService.updatePageBlock(
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
		updatePageBlock(
			io.github.valtairnascimento.campaignforms.model.PageBlock
				pageBlock) {

		return _pageBlockLocalService.updatePageBlock(pageBlock);
	}

	@Override
	public PageBlockLocalService getWrappedService() {
		return _pageBlockLocalService;
	}

	@Override
	public void setWrappedService(PageBlockLocalService pageBlockLocalService) {
		_pageBlockLocalService = pageBlockLocalService;
	}

	private PageBlockLocalService _pageBlockLocalService;

}