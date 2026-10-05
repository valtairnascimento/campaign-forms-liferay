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
 * Provides a wrapper for {@link NotificationConfigLocalService}.
 *
 * @author Valtair Nascimento
 * @see NotificationConfigLocalService
 * @generated
 */
public class NotificationConfigLocalServiceWrapper
	implements NotificationConfigLocalService,
			   ServiceWrapper<NotificationConfigLocalService> {

	public NotificationConfigLocalServiceWrapper() {
		this(null);
	}

	public NotificationConfigLocalServiceWrapper(
		NotificationConfigLocalService notificationConfigLocalService) {

		_notificationConfigLocalService = notificationConfigLocalService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			addNotificationConfig(
				long userId, long campaignId,
				io.github.valtairnascimento.campaignforms.input.
					NotificationConfigInput notificationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.addNotificationConfig(
			userId, campaignId, notificationConfigInput);
	}

	/**
	 * Adds the notification config to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationConfig the notification config
	 * @return the notification config that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
		addNotificationConfig(
			io.github.valtairnascimento.campaignforms.model.NotificationConfig
				notificationConfig) {

		return _notificationConfigLocalService.addNotificationConfig(
			notificationConfig);
	}

	/**
	 * Creates a new notification config with the primary key. Does not add the notification config to the database.
	 *
	 * @param notificationConfigId the primary key for the new notification config
	 * @return the new notification config
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
		createNotificationConfig(long notificationConfigId) {

		return _notificationConfigLocalService.createNotificationConfig(
			notificationConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.createPersistedModel(
			primaryKeyObj);
	}

	@Override
	public void deleteCampaignNotificationConfigs(long campaignId) {
		_notificationConfigLocalService.deleteCampaignNotificationConfigs(
			campaignId);
	}

	/**
	 * Deletes the notification config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config that was removed
	 * @throws PortalException if a notification config with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			deleteNotificationConfig(long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.deleteNotificationConfig(
			notificationConfigId);
	}

	/**
	 * Deletes the notification config from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationConfig the notification config
	 * @return the notification config that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
		deleteNotificationConfig(
			io.github.valtairnascimento.campaignforms.model.NotificationConfig
				notificationConfig) {

		return _notificationConfigLocalService.deleteNotificationConfig(
			notificationConfig);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _notificationConfigLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _notificationConfigLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notificationConfigLocalService.dynamicQuery();
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

		return _notificationConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl</code>.
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

		return _notificationConfigLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl</code>.
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

		return _notificationConfigLocalService.dynamicQuery(
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

		return _notificationConfigLocalService.dynamicQueryCount(dynamicQuery);
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

		return _notificationConfigLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
		fetchNotificationConfig(long notificationConfigId) {

		return _notificationConfigLocalService.fetchNotificationConfig(
			notificationConfigId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _notificationConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.NotificationConfig>
			getCampaignNotificationConfigs(long campaignId) {

		return _notificationConfigLocalService.getCampaignNotificationConfigs(
			campaignId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.NotificationConfig>
			getEnabledNotificationConfigs(
				long campaignId,
				io.github.valtairnascimento.campaignforms.constants.EventType
					eventType) {

		return _notificationConfigLocalService.getEnabledNotificationConfigs(
			campaignId, eventType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _notificationConfigLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notification config with the primary key.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config
	 * @throws PortalException if a notification config with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			getNotificationConfig(long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.getNotificationConfig(
			notificationConfigId);
	}

	/**
	 * Returns a range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of notification configs
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.NotificationConfig>
			getNotificationConfigs(int start, int end) {

		return _notificationConfigLocalService.getNotificationConfigs(
			start, end);
	}

	/**
	 * Returns the number of notification configs.
	 *
	 * @return the number of notification configs
	 */
	@Override
	public int getNotificationConfigsCount() {
		return _notificationConfigLocalService.getNotificationConfigsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationConfigLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public String sendTestNotification(long notificationConfigId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.sendTestNotification(
			notificationConfigId, userId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			updateNotificationConfig(
				long notificationConfigId,
				io.github.valtairnascimento.campaignforms.input.
					NotificationConfigInput notificationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigLocalService.updateNotificationConfig(
			notificationConfigId, notificationConfigInput);
	}

	/**
	 * Updates the notification config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationConfigLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationConfig the notification config
	 * @return the notification config that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
		updateNotificationConfig(
			io.github.valtairnascimento.campaignforms.model.NotificationConfig
				notificationConfig) {

		return _notificationConfigLocalService.updateNotificationConfig(
			notificationConfig);
	}

	@Override
	public NotificationConfigLocalService getWrappedService() {
		return _notificationConfigLocalService;
	}

	@Override
	public void setWrappedService(
		NotificationConfigLocalService notificationConfigLocalService) {

		_notificationConfigLocalService = notificationConfigLocalService;
	}

	private NotificationConfigLocalService _notificationConfigLocalService;

}