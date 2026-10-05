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
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.model.Campaign;

import java.util.List;

/**
 * Provides the remote service utility for Campaign. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.CampaignServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see CampaignService
 * @generated
 */
public class CampaignServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.CampaignServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Campaign addCampaign(
			long groupId,
			io.github.valtairnascimento.campaignforms.input.CampaignInput
				campaignInput,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCampaign(groupId, campaignInput, serviceContext);
	}

	public static Campaign archiveCampaign(long campaignId)
		throws PortalException {

		return getService().archiveCampaign(campaignId);
	}

	public static Campaign convertToCustomPage(long campaignId)
		throws PortalException {

		return getService().convertToCustomPage(campaignId);
	}

	public static Campaign deleteCampaign(long campaignId)
		throws PortalException {

		return getService().deleteCampaign(campaignId);
	}

	public static Campaign duplicateCampaign(
			long sourceCampaignId,
			io.github.valtairnascimento.campaignforms.domain.DuplicationRequest
				duplicationRequest,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().duplicateCampaign(
			sourceCampaignId, duplicationRequest, serviceContext);
	}

	public static Campaign getCampaign(long campaignId) throws PortalException {
		return getService().getCampaign(campaignId);
	}

	public static Campaign getCampaignByExternalReferenceCode(
			long groupId, String externalReferenceCode)
		throws PortalException {

		return getService().getCampaignByExternalReferenceCode(
			groupId, externalReferenceCode);
	}

	public static Campaign getCampaignBySlug(long groupId, String slug)
		throws PortalException {

		return getService().getCampaignBySlug(groupId, slug);
	}

	public static List<Campaign> getGroupCampaigns(
		long groupId, int start, int end,
		OrderByComparator<Campaign> orderByComparator) {

		return getService().getGroupCampaigns(
			groupId, start, end, orderByComparator);
	}

	public static int getGroupCampaignsCount(long groupId) {
		return getService().getGroupCampaignsCount(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List
		<io.github.valtairnascimento.campaignforms.domain.PublicationIssue>
				getPublicationIssues(long campaignId)
			throws PortalException {

		return getService().getPublicationIssues(campaignId);
	}

	public static Campaign publishCampaign(long campaignId)
		throws PortalException {

		return getService().publishCampaign(campaignId);
	}

	public static Campaign reopenCampaign(long campaignId)
		throws PortalException {

		return getService().reopenCampaign(campaignId);
	}

	public static Campaign updateCampaign(
			long campaignId,
			io.github.valtairnascimento.campaignforms.input.CampaignInput
				campaignInput,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCampaign(
			campaignId, campaignInput, serviceContext);
	}

	public static CampaignService getService() {
		return _service;
	}

	private static volatile CampaignService _service;

}