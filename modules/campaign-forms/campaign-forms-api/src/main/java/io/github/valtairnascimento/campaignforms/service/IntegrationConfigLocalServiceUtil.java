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

import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for IntegrationConfig. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.IntegrationConfigLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see IntegrationConfigLocalService
 * @generated
 */
public class IntegrationConfigLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.IntegrationConfigLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the integration config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect IntegrationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param integrationConfig the integration config
	 * @return the integration config that was added
	 */
	public static IntegrationConfig addIntegrationConfig(
		IntegrationConfig integrationConfig) {

		return getService().addIntegrationConfig(integrationConfig);
	}

	public static IntegrationConfig addIntegrationConfig(
			long userId, long campaignId,
			io.github.valtairnascimento.campaignforms.input.
				IntegrationConfigInput integrationConfigInput)
		throws PortalException {

		return getService().addIntegrationConfig(
			userId, campaignId, integrationConfigInput);
	}

	/**
	 * Creates a new integration config with the primary key. Does not add the integration config to the database.
	 *
	 * @param integrationConfigId the primary key for the new integration config
	 * @return the new integration config
	 */
	public static IntegrationConfig createIntegrationConfig(
		long integrationConfigId) {

		return getService().createIntegrationConfig(integrationConfigId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteCampaignIntegrationConfigs(long campaignId) {
		getService().deleteCampaignIntegrationConfigs(campaignId);
	}

	/**
	 * Deletes the integration config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect IntegrationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param integrationConfig the integration config
	 * @return the integration config that was removed
	 */
	public static IntegrationConfig deleteIntegrationConfig(
		IntegrationConfig integrationConfig) {

		return getService().deleteIntegrationConfig(integrationConfig);
	}

	/**
	 * Deletes the integration config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect IntegrationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config that was removed
	 * @throws PortalException if a integration config with the primary key could not be found
	 */
	public static IntegrationConfig deleteIntegrationConfig(
			long integrationConfigId)
		throws PortalException {

		return getService().deleteIntegrationConfig(integrationConfigId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.IntegrationConfigModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.IntegrationConfigModelImpl</code>.
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

	public static IntegrationConfig fetchIntegrationConfig(
		long integrationConfigId) {

		return getService().fetchIntegrationConfig(integrationConfigId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<IntegrationConfig> getCampaignIntegrationConfigs(
		long campaignId) {

		return getService().getCampaignIntegrationConfigs(campaignId);
	}

	public static List<IntegrationConfig> getEnabledIntegrationConfigs(
		long campaignId,
		io.github.valtairnascimento.campaignforms.constants.EventType
			eventType) {

		return getService().getEnabledIntegrationConfigs(campaignId, eventType);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the integration config with the primary key.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config
	 * @throws PortalException if a integration config with the primary key could not be found
	 */
	public static IntegrationConfig getIntegrationConfig(
			long integrationConfigId)
		throws PortalException {

		return getService().getIntegrationConfig(integrationConfigId);
	}

	/**
	 * Returns a range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @return the range of integration configs
	 */
	public static List<IntegrationConfig> getIntegrationConfigs(
		int start, int end) {

		return getService().getIntegrationConfigs(start, end);
	}

	/**
	 * Returns the number of integration configs.
	 *
	 * @return the number of integration configs
	 */
	public static int getIntegrationConfigsCount() {
		return getService().getIntegrationConfigsCount();
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
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the integration config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect IntegrationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param integrationConfig the integration config
	 * @return the integration config that was updated
	 */
	public static IntegrationConfig updateIntegrationConfig(
		IntegrationConfig integrationConfig) {

		return getService().updateIntegrationConfig(integrationConfig);
	}

	public static IntegrationConfig updateIntegrationConfig(
			long integrationConfigId,
			io.github.valtairnascimento.campaignforms.input.
				IntegrationConfigInput integrationConfigInput)
		throws PortalException {

		return getService().updateIntegrationConfig(
			integrationConfigId, integrationConfigInput);
	}

	public static IntegrationConfigLocalService getService() {
		return _service;
	}

	private static volatile IntegrationConfigLocalService _service;

}