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

import io.github.valtairnascimento.campaignforms.exception.NoSuchNotificationConfigException;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the notification config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see NotificationConfigUtil
 * @generated
 */
@ProviderType
public interface NotificationConfigPersistence
	extends BasePersistence<NotificationConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationConfigUtil} to access the notification config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching notification configs
	 */
	public java.util.List<NotificationConfig> findByCampaignId(long campaignId);

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
	public java.util.List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end);

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
	public java.util.List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

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
	public java.util.List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public NotificationConfig findByCampaignId_First(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public NotificationConfig fetchByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public NotificationConfig findByCampaignId_Last(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public NotificationConfig fetchByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

	/**
	 * Returns the notification configs before and after the current notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param notificationConfigId the primary key of the current notification config
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public NotificationConfig[] findByCampaignId_PrevAndNext(
			long notificationConfigId, long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Removes all the notification configs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(long campaignId);

	/**
	 * Returns the number of notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching notification configs
	 */
	public int countByCampaignId(long campaignId);

	/**
	 * Returns all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the matching notification configs
	 */
	public java.util.List<NotificationConfig> findByC_ET(
		long campaignId, String eventType);

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
	public java.util.List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end);

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
	public java.util.List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

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
	public java.util.List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public NotificationConfig findByC_ET_First(
			long campaignId, String eventType,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public NotificationConfig fetchByC_ET_First(
		long campaignId, String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	public NotificationConfig findByC_ET_Last(
			long campaignId, String eventType,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	public NotificationConfig fetchByC_ET_Last(
		long campaignId, String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

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
	public NotificationConfig[] findByC_ET_PrevAndNext(
			long notificationConfigId, long campaignId, String eventType,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
				orderByComparator)
		throws NoSuchNotificationConfigException;

	/**
	 * Removes all the notification configs where campaignId = &#63; and eventType = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 */
	public void removeByC_ET(long campaignId, String eventType);

	/**
	 * Returns the number of notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the number of matching notification configs
	 */
	public int countByC_ET(long campaignId, String eventType);

	/**
	 * Caches the notification config in the entity cache if it is enabled.
	 *
	 * @param notificationConfig the notification config
	 */
	public void cacheResult(NotificationConfig notificationConfig);

	/**
	 * Caches the notification configs in the entity cache if it is enabled.
	 *
	 * @param notificationConfigs the notification configs
	 */
	public void cacheResult(
		java.util.List<NotificationConfig> notificationConfigs);

	/**
	 * Creates a new notification config with the primary key. Does not add the notification config to the database.
	 *
	 * @param notificationConfigId the primary key for the new notification config
	 * @return the new notification config
	 */
	public NotificationConfig create(long notificationConfigId);

	/**
	 * Removes the notification config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config that was removed
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public NotificationConfig remove(long notificationConfigId)
		throws NoSuchNotificationConfigException;

	public NotificationConfig updateImpl(NotificationConfig notificationConfig);

	/**
	 * Returns the notification config with the primary key or throws a <code>NoSuchNotificationConfigException</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	public NotificationConfig findByPrimaryKey(long notificationConfigId)
		throws NoSuchNotificationConfigException;

	/**
	 * Returns the notification config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config, or <code>null</code> if a notification config with the primary key could not be found
	 */
	public NotificationConfig fetchByPrimaryKey(long notificationConfigId);

	/**
	 * Returns all the notification configs.
	 *
	 * @return the notification configs
	 */
	public java.util.List<NotificationConfig> findAll();

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
	public java.util.List<NotificationConfig> findAll(int start, int end);

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
	public java.util.List<NotificationConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator);

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
	public java.util.List<NotificationConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the notification configs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of notification configs.
	 *
	 * @return the number of notification configs
	 */
	public int countAll();

}