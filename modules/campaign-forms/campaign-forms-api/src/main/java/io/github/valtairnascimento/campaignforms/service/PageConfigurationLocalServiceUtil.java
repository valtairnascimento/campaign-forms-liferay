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

import io.github.valtairnascimento.campaignforms.model.PageConfiguration;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for PageConfiguration. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.PageConfigurationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see PageConfigurationLocalService
 * @generated
 */
public class PageConfigurationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.PageConfigurationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PageConfiguration addPageConfiguration(
			long userId, long campaignId,
			io.github.valtairnascimento.campaignforms.constants.PageStage stage,
			String templateKey)
		throws PortalException {

		return getService().addPageConfiguration(
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
	public static PageConfiguration addPageConfiguration(
		PageConfiguration pageConfiguration) {

		return getService().addPageConfiguration(pageConfiguration);
	}

	public static PageConfiguration applyPageTemplate(
			long pageConfigurationId, String templateKey)
		throws PortalException {

		return getService().applyPageTemplate(pageConfigurationId, templateKey);
	}

	public static PageConfiguration copyPageConfiguration(
			long userId, long sourcePageConfigurationId, long targetCampaignId,
			io.github.valtairnascimento.campaignforms.constants.PageStage
				targetStage)
		throws PortalException {

		return getService().copyPageConfiguration(
			userId, sourcePageConfigurationId, targetCampaignId, targetStage);
	}

	/**
	 * Creates a new page configuration with the primary key. Does not add the page configuration to the database.
	 *
	 * @param pageConfigurationId the primary key for the new page configuration
	 * @return the new page configuration
	 */
	public static PageConfiguration createPageConfiguration(
		long pageConfigurationId) {

		return getService().createPageConfiguration(pageConfigurationId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteCampaignPageConfigurations(long campaignId) {
		getService().deleteCampaignPageConfigurations(campaignId);
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
	public static PageConfiguration deletePageConfiguration(
			long pageConfigurationId)
		throws PortalException {

		return getService().deletePageConfiguration(pageConfigurationId);
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
	public static PageConfiguration deletePageConfiguration(
		PageConfiguration pageConfiguration) {

		return getService().deletePageConfiguration(pageConfiguration);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl</code>.
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

	public static PageConfiguration fetchPageConfiguration(
		long pageConfigurationId) {

		return getService().fetchPageConfiguration(pageConfigurationId);
	}

	public static PageConfiguration fetchPageConfiguration(
		long campaignId,
		io.github.valtairnascimento.campaignforms.constants.PageStage stage) {

		return getService().fetchPageConfiguration(campaignId, stage);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<PageConfiguration> getCampaignPageConfigurations(
		long campaignId) {

		return getService().getCampaignPageConfigurations(campaignId);
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
	 * Returns the page configuration with the primary key.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration
	 * @throws PortalException if a page configuration with the primary key could not be found
	 */
	public static PageConfiguration getPageConfiguration(
			long pageConfigurationId)
		throws PortalException {

		return getService().getPageConfiguration(pageConfigurationId);
	}

	public static PageConfiguration getPageConfiguration(
			long campaignId,
			io.github.valtairnascimento.campaignforms.constants.PageStage stage)
		throws PortalException {

		return getService().getPageConfiguration(campaignId, stage);
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
	public static List<PageConfiguration> getPageConfigurations(
		int start, int end) {

		return getService().getPageConfigurations(start, end);
	}

	/**
	 * Returns the number of page configurations.
	 *
	 * @return the number of page configurations
	 */
	public static int getPageConfigurationsCount() {
		return getService().getPageConfigurationsCount();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static PageConfiguration updatePageConfiguration(
		PageConfiguration pageConfiguration) {

		return getService().updatePageConfiguration(pageConfiguration);
	}

	public static PageConfiguration updatePageSettings(
			long pageConfigurationId,
			io.github.valtairnascimento.campaignforms.input.PageSettingsInput
				pageSettingsInput)
		throws PortalException {

		return getService().updatePageSettings(
			pageConfigurationId, pageSettingsInput);
	}

	public static PageConfigurationLocalService getService() {
		return _service;
	}

	private static volatile PageConfigurationLocalService _service;

}