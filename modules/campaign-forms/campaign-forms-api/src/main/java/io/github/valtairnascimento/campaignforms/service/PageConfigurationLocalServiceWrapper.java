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
 * Provides a wrapper for {@link PageConfigurationLocalService}.
 *
 * @author Valtair Nascimento
 * @see PageConfigurationLocalService
 * @generated
 */
public class PageConfigurationLocalServiceWrapper
	implements PageConfigurationLocalService,
			   ServiceWrapper<PageConfigurationLocalService> {

	public PageConfigurationLocalServiceWrapper() {
		this(null);
	}

	public PageConfigurationLocalServiceWrapper(
		PageConfigurationLocalService pageConfigurationLocalService) {

		_pageConfigurationLocalService = pageConfigurationLocalService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			addPageConfiguration(
				long userId, long campaignId,
				io.github.valtairnascimento.campaignforms.constants.PageStage
					stage,
				String templateKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.addPageConfiguration(
			userId, campaignId, stage, templateKey);
	}

	/**
	 * Adds the page configuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageConfiguration the page configuration
	 * @return the page configuration that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		addPageConfiguration(
			io.github.valtairnascimento.campaignforms.model.PageConfiguration
				pageConfiguration) {

		return _pageConfigurationLocalService.addPageConfiguration(
			pageConfiguration);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			applyPageTemplate(long pageConfigurationId, String templateKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.applyPageTemplate(
			pageConfigurationId, templateKey);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			copyPageConfiguration(
				long userId, long sourcePageConfigurationId,
				long targetCampaignId,
				io.github.valtairnascimento.campaignforms.constants.PageStage
					targetStage)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.copyPageConfiguration(
			userId, sourcePageConfigurationId, targetCampaignId, targetStage);
	}

	/**
	 * Creates a new page configuration with the primary key. Does not add the page configuration to the database.
	 *
	 * @param pageConfigurationId the primary key for the new page configuration
	 * @return the new page configuration
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		createPageConfiguration(long pageConfigurationId) {

		return _pageConfigurationLocalService.createPageConfiguration(
			pageConfigurationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteCampaignPageConfigurations(long campaignId) {
		_pageConfigurationLocalService.deleteCampaignPageConfigurations(
			campaignId);
	}

	/**
	 * Deletes the page configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration that was removed
	 * @throws PortalException if a page configuration with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			deletePageConfiguration(long pageConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.deletePageConfiguration(
			pageConfigurationId);
	}

	/**
	 * Deletes the page configuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageConfiguration the page configuration
	 * @return the page configuration that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		deletePageConfiguration(
			io.github.valtairnascimento.campaignforms.model.PageConfiguration
				pageConfiguration) {

		return _pageConfigurationLocalService.deletePageConfiguration(
			pageConfiguration);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _pageConfigurationLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _pageConfigurationLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pageConfigurationLocalService.dynamicQuery();
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

		return _pageConfigurationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl</code>.
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

		return _pageConfigurationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl</code>.
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

		return _pageConfigurationLocalService.dynamicQuery(
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

		return _pageConfigurationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _pageConfigurationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		fetchPageConfiguration(long pageConfigurationId) {

		return _pageConfigurationLocalService.fetchPageConfiguration(
			pageConfigurationId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		fetchPageConfiguration(
			long campaignId,
			io.github.valtairnascimento.campaignforms.constants.PageStage
				stage) {

		return _pageConfigurationLocalService.fetchPageConfiguration(
			campaignId, stage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _pageConfigurationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageConfiguration>
			getCampaignPageConfigurations(long campaignId) {

		return _pageConfigurationLocalService.getCampaignPageConfigurations(
			campaignId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _pageConfigurationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageConfigurationLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the page configuration with the primary key.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration
	 * @throws PortalException if a page configuration with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			getPageConfiguration(long pageConfigurationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.getPageConfiguration(
			pageConfigurationId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			getPageConfiguration(
				long campaignId,
				io.github.valtairnascimento.campaignforms.constants.PageStage
					stage)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.getPageConfiguration(
			campaignId, stage);
	}

	/**
	 * Returns a range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @return the range of page configurations
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageConfiguration>
			getPageConfigurations(int start, int end) {

		return _pageConfigurationLocalService.getPageConfigurations(start, end);
	}

	/**
	 * Returns the number of page configurations.
	 *
	 * @return the number of page configurations
	 */
	@Override
	public int getPageConfigurationsCount() {
		return _pageConfigurationLocalService.getPageConfigurationsCount();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the page configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageConfigurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageConfiguration the page configuration
	 * @return the page configuration that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
		updatePageConfiguration(
			io.github.valtairnascimento.campaignforms.model.PageConfiguration
				pageConfiguration) {

		return _pageConfigurationLocalService.updatePageConfiguration(
			pageConfiguration);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			updatePageSettings(
				long pageConfigurationId,
				io.github.valtairnascimento.campaignforms.input.
					PageSettingsInput pageSettingsInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationLocalService.updatePageSettings(
			pageConfigurationId, pageSettingsInput);
	}

	@Override
	public PageConfigurationLocalService getWrappedService() {
		return _pageConfigurationLocalService;
	}

	@Override
	public void setWrappedService(
		PageConfigurationLocalService pageConfigurationLocalService) {

		_pageConfigurationLocalService = pageConfigurationLocalService;
	}

	private PageConfigurationLocalService _pageConfigurationLocalService;

}