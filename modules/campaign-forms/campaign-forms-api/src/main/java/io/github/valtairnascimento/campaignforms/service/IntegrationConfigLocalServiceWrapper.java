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
 * Provides a wrapper for {@link IntegrationConfigLocalService}.
 *
 * @author Valtair Nascimento
 * @see IntegrationConfigLocalService
 * @generated
 */
public class IntegrationConfigLocalServiceWrapper
	implements IntegrationConfigLocalService,
			   ServiceWrapper<IntegrationConfigLocalService> {

	public IntegrationConfigLocalServiceWrapper() {
		this(null);
	}

	public IntegrationConfigLocalServiceWrapper(
		IntegrationConfigLocalService integrationConfigLocalService) {

		_integrationConfigLocalService = integrationConfigLocalService;
	}

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
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
		addIntegrationConfig(
			io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				integrationConfig) {

		return _integrationConfigLocalService.addIntegrationConfig(
			integrationConfig);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			addIntegrationConfig(
				long userId, long campaignId,
				io.github.valtairnascimento.campaignforms.input.
					IntegrationConfigInput integrationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.addIntegrationConfig(
			userId, campaignId, integrationConfigInput);
	}

	/**
	 * Creates a new integration config with the primary key. Does not add the integration config to the database.
	 *
	 * @param integrationConfigId the primary key for the new integration config
	 * @return the new integration config
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
		createIntegrationConfig(long integrationConfigId) {

		return _integrationConfigLocalService.createIntegrationConfig(
			integrationConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteCampaignIntegrationConfigs(long campaignId) {
		_integrationConfigLocalService.deleteCampaignIntegrationConfigs(
			campaignId);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
		deleteIntegrationConfig(
			io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				integrationConfig) {

		return _integrationConfigLocalService.deleteIntegrationConfig(
			integrationConfig);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			deleteIntegrationConfig(long integrationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.deleteIntegrationConfig(
			integrationConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _integrationConfigLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _integrationConfigLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _integrationConfigLocalService.dynamicQuery();
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

		return _integrationConfigLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _integrationConfigLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _integrationConfigLocalService.dynamicQuery(
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

		return _integrationConfigLocalService.dynamicQueryCount(dynamicQuery);
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

		return _integrationConfigLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
		fetchIntegrationConfig(long integrationConfigId) {

		return _integrationConfigLocalService.fetchIntegrationConfig(
			integrationConfigId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _integrationConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.IntegrationConfig>
			getCampaignIntegrationConfigs(long campaignId) {

		return _integrationConfigLocalService.getCampaignIntegrationConfigs(
			campaignId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.IntegrationConfig>
			getEnabledIntegrationConfigs(
				long campaignId,
				io.github.valtairnascimento.campaignforms.constants.EventType
					eventType) {

		return _integrationConfigLocalService.getEnabledIntegrationConfigs(
			campaignId, eventType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _integrationConfigLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the integration config with the primary key.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config
	 * @throws PortalException if a integration config with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			getIntegrationConfig(long integrationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.getIntegrationConfig(
			integrationConfigId);
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
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.IntegrationConfig>
			getIntegrationConfigs(int start, int end) {

		return _integrationConfigLocalService.getIntegrationConfigs(start, end);
	}

	/**
	 * Returns the number of integration configs.
	 *
	 * @return the number of integration configs
	 */
	@Override
	public int getIntegrationConfigsCount() {
		return _integrationConfigLocalService.getIntegrationConfigsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _integrationConfigLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
		updateIntegrationConfig(
			io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				integrationConfig) {

		return _integrationConfigLocalService.updateIntegrationConfig(
			integrationConfig);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			updateIntegrationConfig(
				long integrationConfigId,
				io.github.valtairnascimento.campaignforms.input.
					IntegrationConfigInput integrationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigLocalService.updateIntegrationConfig(
			integrationConfigId, integrationConfigInput);
	}

	@Override
	public IntegrationConfigLocalService getWrappedService() {
		return _integrationConfigLocalService;
	}

	@Override
	public void setWrappedService(
		IntegrationConfigLocalService integrationConfigLocalService) {

		_integrationConfigLocalService = integrationConfigLocalService;
	}

	private IntegrationConfigLocalService _integrationConfigLocalService;

}