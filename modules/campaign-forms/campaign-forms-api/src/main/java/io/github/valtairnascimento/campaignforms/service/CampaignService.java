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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Campaign. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Valtair Nascimento
 * @see CampaignServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CampaignService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.CampaignServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the campaign remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CampaignServiceUtil} if injection and service tracking are not available.
	 */
	public Campaign addCampaign(
			long groupId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException;

	public Campaign archiveCampaign(long campaignId) throws PortalException;

	public Campaign convertToCustomPage(long campaignId) throws PortalException;

	public Campaign deleteCampaign(long campaignId) throws PortalException;

	public Campaign duplicateCampaign(
			long sourceCampaignId, DuplicationRequest duplicationRequest,
			ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Campaign getCampaign(long campaignId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Campaign getCampaignByExternalReferenceCode(
			long groupId, String externalReferenceCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Campaign getCampaignBySlug(long groupId, String slug)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Campaign> getGroupCampaigns(
		long groupId, int start, int end,
		OrderByComparator<Campaign> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupCampaignsCount(long groupId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublicationIssue> getPublicationIssues(long campaignId)
		throws PortalException;

	public Campaign publishCampaign(long campaignId) throws PortalException;

	public Campaign reopenCampaign(long campaignId) throws PortalException;

	public Campaign updateCampaign(
			long campaignId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException;

}