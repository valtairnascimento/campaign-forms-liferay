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

import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the notification config service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.NotificationConfigPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see NotificationConfigPersistence
 * @generated
 */
public class NotificationConfigUtil {

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
	public static void clearCache(NotificationConfig notificationConfig) {
		getPersistence().clearCache(notificationConfig);
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
	public static Map<Serializable, NotificationConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NotificationConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NotificationConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NotificationConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NotificationConfig update(
		NotificationConfig notificationConfig) {

		return getPersistence().update(notificationConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NotificationConfig update(
		NotificationConfig notificationConfig, ServiceContext serviceContext) {

		return getPersistence().update(notificationConfig, serviceContext);
	}

	/**
	 * Returns all the notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching notification configs
	 */
	public static List<NotificationConfig> findByCampaignId(long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

	/**
	 * Returns a range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of matching notification configs
	 */
	public static List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end) {

		return getPersistence().findByCampaignId(campaignId, start, end);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification configs
	 */
	public static List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification configs
	 */
	public static List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public static NotificationConfig findByCampaignId_First(
			long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public static NotificationConfig fetchByCampaignId_First(
		long campaignId,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().fetchByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public static NotificationConfig findByCampaignId_Last(
			long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public static NotificationConfig fetchByCampaignId_Last(
		long campaignId,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().fetchByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the notification configs before and after the current notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param notificationConfigId the primary key of the current notification config
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public static NotificationConfig[] findByCampaignId_PrevAndNext(
			long notificationConfigId, long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByCampaignId_PrevAndNext(
			notificationConfigId, campaignId, orderByComparator);
	}

	/**
	 * Removes all the notification configs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public static void removeByCampaignId(long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	 * Returns the number of notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching notification configs
	 */
	public static int countByCampaignId(long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	 * Returns all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the matching notification configs
	 */
	public static List<NotificationConfig> findByC_ET(
		long campaignId, String eventType) {

		return getPersistence().findByC_ET(campaignId, eventType);
	}

	/**
	 * Returns a range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of matching notification configs
	 */
	public static List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end) {

		return getPersistence().findByC_ET(campaignId, eventType, start, end);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification configs
	 */
	public static List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().findByC_ET(
			campaignId, eventType, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification configs
	 */
	public static List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_ET(
			campaignId, eventType, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public static NotificationConfig findByC_ET_First(
			long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByC_ET_First(
			campaignId, eventType, orderByComparator);
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public static NotificationConfig fetchByC_ET_First(
		long campaignId, String eventType,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().fetchByC_ET_First(
			campaignId, eventType, orderByComparator);
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public static NotificationConfig findByC_ET_Last(
			long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByC_ET_Last(
			campaignId, eventType, orderByComparator);
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public static NotificationConfig fetchByC_ET_Last(
		long campaignId, String eventType,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().fetchByC_ET_Last(
			campaignId, eventType, orderByComparator);
	}

	/**
	 * Returns the notification configs before and after the current notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param notificationConfigId the primary key of the current notification config
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public static NotificationConfig[] findByC_ET_PrevAndNext(
			long notificationConfigId, long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByC_ET_PrevAndNext(
			notificationConfigId, campaignId, eventType, orderByComparator);
	}

	/**
	 * Removes all the notification configs where campaignId = &#63; and eventType = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 */
	public static void removeByC_ET(long campaignId, String eventType) {
		getPersistence().removeByC_ET(campaignId, eventType);
	}

	/**
	 * Returns the number of notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the number of matching notification configs
	 */
	public static int countByC_ET(long campaignId, String eventType) {
		return getPersistence().countByC_ET(campaignId, eventType);
	}

	/**
	 * Caches the notification config in the entity cache if it is enabled.
	 *
	 * @param notificationConfig the notification config
	 */
	public static void cacheResult(NotificationConfig notificationConfig) {
		getPersistence().cacheResult(notificationConfig);
	}

	/**
	 * Caches the notification configs in the entity cache if it is enabled.
	 *
	 * @param notificationConfigs the notification configs
	 */
	public static void cacheResult(
		List<NotificationConfig> notificationConfigs) {

		getPersistence().cacheResult(notificationConfigs);
	}

	/**
	 * Creates a new notification config with the primary key. Does not add the notification config to the database.
	 *
	 * @param notificationConfigId the primary key for the new notification config
	 * @return the new notification config
	 */
	public static NotificationConfig create(long notificationConfigId) {
		return getPersistence().create(notificationConfigId);
	}

	/**
	 * Removes the notification config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config that was removed
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public static NotificationConfig remove(long notificationConfigId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().remove(notificationConfigId);
	}

	public static NotificationConfig updateImpl(
		NotificationConfig notificationConfig) {

		return getPersistence().updateImpl(notificationConfig);
	}

	/**
	 * Returns the notification config with the primary key or throws a <code>NoSuchNotificationConfigException</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public static NotificationConfig findByPrimaryKey(long notificationConfigId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchNotificationConfigException {

		return getPersistence().findByPrimaryKey(notificationConfigId);
	}

	/**
	 * Returns the notification config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config, or <code>null</code> if a notification config with the primary key could not be found
	 */
	public static NotificationConfig fetchByPrimaryKey(
		long notificationConfigId) {

		return getPersistence().fetchByPrimaryKey(notificationConfigId);
	}

	/**
	 * Returns all the notification configs.
	 *
	 * @return the notification configs
	 */
	public static List<NotificationConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of notification configs
	 */
	public static List<NotificationConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification configs
	 */
	public static List<NotificationConfig> findAll(
		int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notification configs
	 */
	public static List<NotificationConfig> findAll(
		int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the notification configs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of notification configs.
	 *
	 * @return the number of notification configs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotificationConfigPersistence getPersistence() {
		return _persistence;
	}

	private static volatile NotificationConfigPersistence _persistence;

}