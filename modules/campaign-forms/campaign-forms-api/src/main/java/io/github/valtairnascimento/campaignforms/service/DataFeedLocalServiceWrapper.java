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
 * Provides a wrapper for {@link DataFeedLocalService}.
 *
 * @author Valtair Nascimento
 * @see DataFeedLocalService
 * @generated
 */
public class DataFeedLocalServiceWrapper
	implements DataFeedLocalService, ServiceWrapper<DataFeedLocalService> {

	public DataFeedLocalServiceWrapper() {
		this(null);
	}

	public DataFeedLocalServiceWrapper(
		DataFeedLocalService dataFeedLocalService) {

		_dataFeedLocalService = dataFeedLocalService;
	}

	/**
	 * Adds the data feed to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DataFeedLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dataFeed the data feed
	 * @return the data feed that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed addDataFeed(
		io.github.valtairnascimento.campaignforms.model.DataFeed dataFeed) {

		return _dataFeedLocalService.addDataFeed(dataFeed);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
			addDataFeed(
				long userId, long campaignId, String name, boolean seriesScope,
				String format, boolean includeMetadata,
				java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.addDataFeed(
			userId, campaignId, name, seriesScope, format, includeMetadata,
			expirationDate);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
			authenticateDataFeed(String feedKey, String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.authenticateDataFeed(feedKey, token);
	}

	/**
	 * Creates a new data feed with the primary key. Does not add the data feed to the database.
	 *
	 * @param dataFeedId the primary key for the new data feed
	 * @return the new data feed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
		createDataFeed(long dataFeedId) {

		return _dataFeedLocalService.createDataFeed(dataFeedId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the data feed from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DataFeedLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dataFeed the data feed
	 * @return the data feed that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
		deleteDataFeed(
			io.github.valtairnascimento.campaignforms.model.DataFeed dataFeed) {

		return _dataFeedLocalService.deleteDataFeed(dataFeed);
	}

	/**
	 * Deletes the data feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DataFeedLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed that was removed
	 * @throws PortalException if a data feed with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
			deleteDataFeed(long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.deleteDataFeed(dataFeedId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _dataFeedLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _dataFeedLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dataFeedLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _dataFeedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _dataFeedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _dataFeedLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _dataFeedLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _dataFeedLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
		fetchDataFeed(long dataFeedId) {

		return _dataFeedLocalService.fetchDataFeed(dataFeedId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _dataFeedLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.DataFeed>
			getCampaignDataFeeds(long campaignId) {

		return _dataFeedLocalService.getCampaignDataFeeds(campaignId);
	}

	/**
	 * Returns the data feed with the primary key.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed
	 * @throws PortalException if a data feed with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed getDataFeed(
			long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.getDataFeed(dataFeedId);
	}

	/**
	 * Returns a range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of data feeds
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.DataFeed> getDataFeeds(
			int start, int end) {

		return _dataFeedLocalService.getDataFeeds(start, end);
	}

	/**
	 * Returns the number of data feeds.
	 *
	 * @return the number of data feeds
	 */
	@Override
	public int getDataFeedsCount() {
		return _dataFeedLocalService.getDataFeedsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _dataFeedLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dataFeedLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials
			regenerateDataFeedToken(long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.regenerateDataFeedToken(dataFeedId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
			revokeDataFeed(long dataFeedId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataFeedLocalService.revokeDataFeed(dataFeedId);
	}

	/**
	 * Updates the data feed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DataFeedLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dataFeed the data feed
	 * @return the data feed that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.DataFeed
		updateDataFeed(
			io.github.valtairnascimento.campaignforms.model.DataFeed dataFeed) {

		return _dataFeedLocalService.updateDataFeed(dataFeed);
	}

	@Override
	public DataFeedLocalService getWrappedService() {
		return _dataFeedLocalService;
	}

	@Override
	public void setWrappedService(DataFeedLocalService dataFeedLocalService) {
		_dataFeedLocalService = dataFeedLocalService;
	}

	private DataFeedLocalService _dataFeedLocalService;

}