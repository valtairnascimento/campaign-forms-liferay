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
 * Provides a wrapper for {@link CampaignQuotaLocalService}.
 *
 * @author Valtair Nascimento
 * @see CampaignQuotaLocalService
 * @generated
 */
public class CampaignQuotaLocalServiceWrapper
	implements CampaignQuotaLocalService,
			   ServiceWrapper<CampaignQuotaLocalService> {

	public CampaignQuotaLocalServiceWrapper() {
		this(null);
	}

	public CampaignQuotaLocalServiceWrapper(
		CampaignQuotaLocalService campaignQuotaLocalService) {

		_campaignQuotaLocalService = campaignQuotaLocalService;
	}

	/**
	 * Adds the campaign quota to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignQuotaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaignQuota the campaign quota
	 * @return the campaign quota that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		addCampaignQuota(
			io.github.valtairnascimento.campaignforms.model.CampaignQuota
				campaignQuota) {

		return _campaignQuotaLocalService.addCampaignQuota(campaignQuota);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		addCampaignQuotaIfAbsent(
			io.github.valtairnascimento.campaignforms.model.Campaign campaign) {

		return _campaignQuotaLocalService.addCampaignQuotaIfAbsent(campaign);
	}

	/**
	 * Creates a new campaign quota with the primary key. Does not add the campaign quota to the database.
	 *
	 * @param campaignId the primary key for the new campaign quota
	 * @return the new campaign quota
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		createCampaignQuota(long campaignId) {

		return _campaignQuotaLocalService.createCampaignQuota(campaignId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the campaign quota from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignQuotaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaignQuota the campaign quota
	 * @return the campaign quota that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		deleteCampaignQuota(
			io.github.valtairnascimento.campaignforms.model.CampaignQuota
				campaignQuota) {

		return _campaignQuotaLocalService.deleteCampaignQuota(campaignQuota);
	}

	/**
	 * Deletes the campaign quota with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignQuotaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota that was removed
	 * @throws PortalException if a campaign quota with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
			deleteCampaignQuota(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.deleteCampaignQuota(campaignId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _campaignQuotaLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _campaignQuotaLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _campaignQuotaLocalService.dynamicQuery();
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

		return _campaignQuotaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignQuotaModelImpl</code>.
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

		return _campaignQuotaLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignQuotaModelImpl</code>.
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

		return _campaignQuotaLocalService.dynamicQuery(
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

		return _campaignQuotaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _campaignQuotaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		fetchCampaignQuota(long campaignId) {

		return _campaignQuotaLocalService.fetchCampaignQuota(campaignId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _campaignQuotaLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the campaign quota with the primary key.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota
	 * @throws PortalException if a campaign quota with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
			getCampaignQuota(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.getCampaignQuota(campaignId);
	}

	/**
	 * Returns a range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @return the range of campaign quotas
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.CampaignQuota>
			getCampaignQuotas(int start, int end) {

		return _campaignQuotaLocalService.getCampaignQuotas(start, end);
	}

	/**
	 * Returns the number of campaign quotas.
	 *
	 * @return the number of campaign quotas
	 */
	@Override
	public int getCampaignQuotasCount() {
		return _campaignQuotaLocalService.getCampaignQuotasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _campaignQuotaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _campaignQuotaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getUsedSlots(long campaignId) {
		return _campaignQuotaLocalService.getUsedSlots(campaignId);
	}

	@Override
	public void releaseSlot(long campaignId) {
		_campaignQuotaLocalService.releaseSlot(campaignId);
	}

	@Override
	public int reserveSlot(
			io.github.valtairnascimento.campaignforms.model.Campaign campaign)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignQuotaLocalService.reserveSlot(campaign);
	}

	/**
	 * Updates the campaign quota in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignQuotaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaignQuota the campaign quota
	 * @return the campaign quota that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.CampaignQuota
		updateCampaignQuota(
			io.github.valtairnascimento.campaignforms.model.CampaignQuota
				campaignQuota) {

		return _campaignQuotaLocalService.updateCampaignQuota(campaignQuota);
	}

	@Override
	public CampaignQuotaLocalService getWrappedService() {
		return _campaignQuotaLocalService;
	}

	@Override
	public void setWrappedService(
		CampaignQuotaLocalService campaignQuotaLocalService) {

		_campaignQuotaLocalService = campaignQuotaLocalService;
	}

	private CampaignQuotaLocalService _campaignQuotaLocalService;

}