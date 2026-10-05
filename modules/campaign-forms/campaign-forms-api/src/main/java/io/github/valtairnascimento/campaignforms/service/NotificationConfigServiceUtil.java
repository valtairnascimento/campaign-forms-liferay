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

import com.liferay.portal.kernel.exception.PortalException;

import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import java.util.List;

/**
 * Provides the remote service utility for NotificationConfig. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.NotificationConfigServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see NotificationConfigService
 * @generated
 */
public class NotificationConfigServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.NotificationConfigServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static NotificationConfig addNotificationConfig(
			long campaignId,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput notificationConfigInput)
		throws PortalException {

		return getService().addNotificationConfig(
			campaignId, notificationConfigInput);
	}

	public static NotificationConfig deleteNotificationConfig(
			long notificationConfigId)
		throws PortalException {

		return getService().deleteNotificationConfig(notificationConfigId);
	}

	public static List<NotificationConfig> getCampaignNotificationConfigs(
			long campaignId)
		throws PortalException {

		return getService().getCampaignNotificationConfigs(campaignId);
	}

	public static NotificationConfig getNotificationConfig(
			long notificationConfigId)
		throws PortalException {

		return getService().getNotificationConfig(notificationConfigId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String sendTestNotification(long notificationConfigId)
		throws PortalException {

		return getService().sendTestNotification(notificationConfigId);
	}

	public static NotificationConfig updateNotificationConfig(
			long notificationConfigId,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput notificationConfigInput)
		throws PortalException {

		return getService().updateNotificationConfig(
			notificationConfigId, notificationConfigInput);
	}

	public static NotificationConfigService getService() {
		return _service;
	}

	private static volatile NotificationConfigService _service;

}