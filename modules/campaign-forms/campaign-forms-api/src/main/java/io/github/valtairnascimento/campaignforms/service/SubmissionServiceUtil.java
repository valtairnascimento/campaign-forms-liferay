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

import io.github.valtairnascimento.campaignforms.model.Submission;

import java.util.List;

/**
 * Provides the remote service utility for Submission. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.SubmissionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see SubmissionService
 * @generated
 */
public class SubmissionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.SubmissionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void checkSubmissionsExport(long campaignId, String format)
		throws PortalException {

		getService().checkSubmissionsExport(campaignId, format);
	}

	public static Submission deleteSubmission(long submissionId)
		throws PortalException {

		return getService().deleteSubmission(submissionId);
	}

	public static List<Submission> getCampaignSubmissions(
			long campaignId, int start, int end)
		throws PortalException {

		return getService().getCampaignSubmissions(campaignId, start, end);
	}

	public static int getCampaignSubmissionsCount(long campaignId)
		throws PortalException {

		return getService().getCampaignSubmissionsCount(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Submission getSubmission(long submissionId)
		throws PortalException {

		return getService().getSubmission(submissionId);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
				getSubmissionEventDeliveries(long submissionId)
			throws PortalException {

		return getService().getSubmissionEventDeliveries(submissionId);
	}

	public static SubmissionService getService() {
		return _service;
	}

	private static volatile SubmissionService _service;

}