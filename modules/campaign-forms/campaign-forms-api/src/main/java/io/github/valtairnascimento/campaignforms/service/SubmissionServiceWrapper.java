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
 * Provides a wrapper for {@link SubmissionService}.
 *
 * @author Valtair Nascimento
 * @see SubmissionService
 * @generated
 */
public class SubmissionServiceWrapper
	implements ServiceWrapper<SubmissionService>, SubmissionService {

	public SubmissionServiceWrapper() {
		this(null);
	}

	public SubmissionServiceWrapper(SubmissionService submissionService) {
		_submissionService = submissionService;
	}

	@Override
	public void checkSubmissionsExport(long campaignId, String format)
		throws com.liferay.portal.kernel.exception.PortalException {

		_submissionService.checkSubmissionsExport(campaignId, format);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Submission
			deleteSubmission(long submissionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.deleteSubmission(submissionId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Submission>
				getCampaignSubmissions(long campaignId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.getCampaignSubmissions(
			campaignId, start, end);
	}

	@Override
	public int getCampaignSubmissionsCount(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.getCampaignSubmissionsCount(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _submissionService.getOSGiServiceIdentifier();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Submission
			getSubmission(long submissionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.getSubmission(submissionId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
				getSubmissionEventDeliveries(long submissionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionService.getSubmissionEventDeliveries(submissionId);
	}

	@Override
	public SubmissionService getWrappedService() {
		return _submissionService;
	}

	@Override
	public void setWrappedService(SubmissionService submissionService) {
		_submissionService = submissionService;
	}

	private SubmissionService _submissionService;

}