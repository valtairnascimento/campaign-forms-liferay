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
 * Provides a wrapper for {@link IntegrationConfigService}.
 *
 * @author Valtair Nascimento
 * @see IntegrationConfigService
 * @generated
 */
public class IntegrationConfigServiceWrapper
	implements IntegrationConfigService,
			   ServiceWrapper<IntegrationConfigService> {

	public IntegrationConfigServiceWrapper() {
		this(null);
	}

	public IntegrationConfigServiceWrapper(
		IntegrationConfigService integrationConfigService) {

		_integrationConfigService = integrationConfigService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			addIntegrationConfig(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.
					IntegrationConfigInput integrationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigService.addIntegrationConfig(
			campaignId, integrationConfigInput);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			deleteIntegrationConfig(long integrationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigService.deleteIntegrationConfig(
			integrationConfigId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.IntegrationConfig>
				getCampaignIntegrationConfigs(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigService.getCampaignIntegrationConfigs(
			campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			getIntegrationConfig(long integrationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigService.getIntegrationConfig(
			integrationConfigId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _integrationConfigService.getOSGiServiceIdentifier();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.IntegrationConfig
			updateIntegrationConfig(
				long integrationConfigId,
				io.github.valtairnascimento.campaignforms.input.
					IntegrationConfigInput integrationConfigInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _integrationConfigService.updateIntegrationConfig(
			integrationConfigId, integrationConfigInput);
	}

	@Override
	public IntegrationConfigService getWrappedService() {
		return _integrationConfigService;
	}

	@Override
	public void setWrappedService(
		IntegrationConfigService integrationConfigService) {

		_integrationConfigService = integrationConfigService;
	}

	private IntegrationConfigService _integrationConfigService;

}