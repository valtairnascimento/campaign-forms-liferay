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

import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for NotificationConfig. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.NotificationConfigLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see NotificationConfigLocalService
 * @generated
 */
public class NotificationConfigLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.NotificationConfigLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static NotificationConfig addNotificationConfig(
			long userId, long campaignId,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput notificationConfigInput)
		throws PortalException {

		return getService().addNotificationConfig(
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
	public static NotificationConfig addNotificationConfig(
		NotificationConfig notificationConfig) {

		return getService().addNotificationConfig(notificationConfig);
	}

	/**
	 * Creates a new notification config with the primary key. Does not add the notification config to the database.
	 *
	 * @param notificationConfigId the primary key for the new notification config
	 * @return the new notification config
	 */
	public static NotificationConfig createNotificationConfig(
		long notificationConfigId) {

		return getService().createNotificationConfig(notificationConfigId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteCampaignNotificationConfigs(long campaignId) {
		getService().deleteCampaignNotificationConfigs(campaignId);
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
	public static NotificationConfig deleteNotificationConfig(
			long notificationConfigId)
		throws PortalException {

		return getService().deleteNotificationConfig(notificationConfigId);
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
	public static NotificationConfig deleteNotificationConfig(
		NotificationConfig notificationConfig) {

		return getService().deleteNotificationConfig(notificationConfig);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl</code>.
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

	public static NotificationConfig fetchNotificationConfig(
		long notificationConfigId) {

		return getService().fetchNotificationConfig(notificationConfigId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<NotificationConfig> getCampaignNotificationConfigs(
		long campaignId) {

		return getService().getCampaignNotificationConfigs(campaignId);
	}

	public static List<NotificationConfig> getEnabledNotificationConfigs(
		long campaignId,
		io.github.valtairnascimento.campaignforms.constants.EventType
			eventType) {

		return getService().getEnabledNotificationConfigs(
			campaignId, eventType);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notification config with the primary key.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config
	 * @throws PortalException if a notification config with the primary key could not be found
	 */
	public static NotificationConfig getNotificationConfig(
			long notificationConfigId)
		throws PortalException {

		return getService().getNotificationConfig(notificationConfigId);
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
	public static List<NotificationConfig> getNotificationConfigs(
		int start, int end) {

		return getService().getNotificationConfigs(start, end);
	}

	/**
	 * Returns the number of notification configs.
	 *
	 * @return the number of notification configs
	 */
	public static int getNotificationConfigsCount() {
		return getService().getNotificationConfigsCount();
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

	public static String sendTestNotification(
			long notificationConfigId, long userId)
		throws PortalException {

		return getService().sendTestNotification(notificationConfigId, userId);
	}

	public static NotificationConfig updateNotificationConfig(
			long notificationConfigId,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput notificationConfigInput)
		throws PortalException {

		return getService().updateNotificationConfig(
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
	public static NotificationConfig updateNotificationConfig(
		NotificationConfig notificationConfig) {

		return getService().updateNotificationConfig(notificationConfig);
	}

	public static NotificationConfigLocalService getService() {
		return _service;
	}

	private static volatile NotificationConfigLocalService _service;

}