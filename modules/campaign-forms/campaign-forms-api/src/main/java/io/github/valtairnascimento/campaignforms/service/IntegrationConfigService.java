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

import io.github.valtairnascimento.campaignforms.input.IntegrationConfigInput;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for IntegrationConfig. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Valtair Nascimento
 * @see IntegrationConfigServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface IntegrationConfigService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.IntegrationConfigServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the integration config remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link IntegrationConfigServiceUtil} if injection and service tracking are not available.
	 */
	public IntegrationConfig addIntegrationConfig(
			long campaignId, IntegrationConfigInput integrationConfigInput)
		throws PortalException;

	public IntegrationConfig deleteIntegrationConfig(long integrationConfigId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<IntegrationConfig> getCampaignIntegrationConfigs(
			long campaignId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IntegrationConfig getIntegrationConfig(long integrationConfigId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public IntegrationConfig updateIntegrationConfig(
			long integrationConfigId,
			IntegrationConfigInput integrationConfigInput)
		throws PortalException;

}