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

import io.github.valtairnascimento.campaignforms.model.DataFeed;

import java.util.List;

/**
 * Provides the remote service utility for DataFeed. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.DataFeedServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see DataFeedService
 * @generated
 */
public class DataFeedServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.DataFeedServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static
		io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
				addDataFeed(
					long campaignId, String name, boolean seriesScope,
					String format, boolean includeMetadata,
					java.util.Date expirationDate)
			throws PortalException {

		return getService().addDataFeed(
			campaignId, name, seriesScope, format, includeMetadata,
			expirationDate);
	}

	public static List<DataFeed> getCampaignDataFeeds(long campaignId)
		throws PortalException {

		return getService().getCampaignDataFeeds(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static
		io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
				regenerateDataFeedToken(long dataFeedId)
			throws PortalException {

		return getService().regenerateDataFeedToken(dataFeedId);
	}

	public static DataFeed revokeDataFeed(long dataFeedId)
		throws PortalException {

		return getService().revokeDataFeed(dataFeedId);
	}

	public static DataFeedService getService() {
		return _service;
	}

	private static volatile DataFeedService _service;

}