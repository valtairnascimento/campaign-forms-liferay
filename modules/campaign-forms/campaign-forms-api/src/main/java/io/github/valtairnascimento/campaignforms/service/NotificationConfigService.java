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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for NotificationConfig. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Valtair Nascimento
 * @see NotificationConfigServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface NotificationConfigService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.NotificationConfigServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the notification config remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link NotificationConfigServiceUtil} if injection and service tracking are not available.
	 */
	public NotificationConfig addNotificationConfig(
			long campaignId, NotificationConfigInput notificationConfigInput)
		throws PortalException;

	public NotificationConfig deleteNotificationConfig(
			long notificationConfigId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NotificationConfig> getCampaignNotificationConfigs(
			long campaignId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationConfig getNotificationConfig(long notificationConfigId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public String sendTestNotification(long notificationConfigId)
		throws PortalException;

	public NotificationConfig updateNotificationConfig(
			long notificationConfigId,
			NotificationConfigInput notificationConfigInput)
		throws PortalException;

}