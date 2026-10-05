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
 * Provides a wrapper for {@link NotificationConfigService}.
 *
 * @author Valtair Nascimento
 * @see NotificationConfigService
 * @generated
 */
public class NotificationConfigServiceWrapper
	implements NotificationConfigService,
			   ServiceWrapper<NotificationConfigService> {

	public NotificationConfigServiceWrapper() {
		this(null);
	}

	public NotificationConfigServiceWrapper(
		NotificationConfigService notificationConfigService) {

		_notificationConfigService = notificationConfigService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			addNotificationConfig(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.
					NotificationConfigInput notificationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.addNotificationConfig(
			campaignId, notificationConfigInput);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			deleteNotificationConfig(long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.deleteNotificationConfig(
			notificationConfigId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.NotificationConfig>
				getCampaignNotificationConfigs(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.getCampaignNotificationConfigs(
			campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			getNotificationConfig(long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.getNotificationConfig(
			notificationConfigId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationConfigService.getOSGiServiceIdentifier();
	}

	@Override
	public String sendTestNotification(long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.sendTestNotification(
			notificationConfigId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.NotificationConfig
			updateNotificationConfig(
				long notificationConfigId,
				io.github.valtairnascimento.campaignforms.input.
					NotificationConfigInput notificationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationConfigService.updateNotificationConfig(
			notificationConfigId, notificationConfigInput);
	}

	@Override
	public NotificationConfigService getWrappedService() {
		return _notificationConfigService;
	}

	@Override
	public void setWrappedService(
		NotificationConfigService notificationConfigService) {

		_notificationConfigService = notificationConfigService;
	}

	private NotificationConfigService _notificationConfigService;

}