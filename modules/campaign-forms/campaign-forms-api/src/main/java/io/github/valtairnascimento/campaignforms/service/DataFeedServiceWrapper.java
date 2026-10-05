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
 * Provides a wrapper for {@link DataFeedService}.
 *
 * @author Valtair Nascimento
 * @see DataFeedService
 * @generated
 */
public class DataFeedServiceWrapper
	implements DataFeedService, ServiceWrapper<DataFeedService> {

	public DataFeedServiceWrapper() {
		this(null);
	}

	public DataFeedServiceWrapper(DataFeedService dataFeedService) {
		_dataFeedService = dataFeedService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
			addDataFeed(
				long campaignId, String name, boolean seriesScope,
				String format, boolean includeMetadata,
				java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedService.addDataFeed(
			campaignId, name, seriesScope, format, includeMetadata,
			expirationDate);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.DataFeed>
				getCampaignDataFeeds(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedService.getCampaignDataFeeds(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dataFeedService.getOSGiServiceIdentifier();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
			regenerateDataFeedToken(long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedService.regenerateDataFeedToken(dataFeedId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
			revokeDataFeed(long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedService.revokeDataFeed(dataFeedId);
	}

	@Override
	public DataFeedService getWrappedService() {
		return _dataFeedService;
	}

	@Override
	public void setWrappedService(DataFeedService dataFeedService) {
		_dataFeedService = dataFeedService;
	}

	private DataFeedService _dataFeedService;

}