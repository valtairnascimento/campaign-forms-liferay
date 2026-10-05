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
 * Provides a wrapper for {@link CampaignService}.
 *
 * @author Valtair Nascimento
 * @see CampaignService
 * @generated
 */
public class CampaignServiceWrapper
	implements CampaignService, ServiceWrapper<CampaignService> {

	public CampaignServiceWrapper() {
		this(null);
	}

	public CampaignServiceWrapper(CampaignService campaignService) {
		_campaignService = campaignService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign addCampaign(
			long groupId,
			io.github.valtairnascimento.campaignforms.input.CampaignInput
				campaignInput,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.addCampaign(
			groupId, campaignInput, serviceContext);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			archiveCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.archiveCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			convertToCustomPage(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.convertToCustomPage(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			deleteCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.deleteCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			duplicateCampaign(
				long sourceCampaignId,
				io.github.valtairnascimento.campaignforms.domain.
					DuplicationRequest duplicationRequest,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.duplicateCampaign(
			sourceCampaignId, duplicationRequest, serviceContext);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign getCampaign(
			long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.getCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignByExternalReferenceCode(
				long groupId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.getCampaignByExternalReferenceCode(
			groupId, externalReferenceCode);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignBySlug(long groupId, String slug)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.getCampaignBySlug(groupId, slug);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getGroupCampaigns(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<io.github.valtairnascimento.campaignforms.model.Campaign>
						orderByComparator) {

		return _campaignService.getGroupCampaigns(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupCampaignsCount(long groupId) {
		return _campaignService.getGroupCampaignsCount(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _campaignService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.domain.PublicationIssue>
				getPublicationIssues(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.getPublicationIssues(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			publishCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.publishCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			reopenCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.reopenCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			updateCampaign(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.CampaignInput
					campaignInput,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignService.updateCampaign(
			campaignId, campaignInput, serviceContext);
	}

	@Override
	public CampaignService getWrappedService() {
		return _campaignService;
	}

	@Override
	public void setWrappedService(CampaignService campaignService) {
		_campaignService = campaignService;
	}

	private CampaignService _campaignService;

}