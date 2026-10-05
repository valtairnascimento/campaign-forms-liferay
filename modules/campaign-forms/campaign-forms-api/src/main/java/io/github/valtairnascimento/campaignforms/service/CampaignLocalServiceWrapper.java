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
 * Provides a wrapper for {@link CampaignLocalService}.
 *
 * @author Valtair Nascimento
 * @see CampaignLocalService
 * @generated
 */
public class CampaignLocalServiceWrapper
	implements CampaignLocalService, ServiceWrapper<CampaignLocalService> {

	public CampaignLocalServiceWrapper() {
		this(null);
	}

	public CampaignLocalServiceWrapper(
		CampaignLocalService campaignLocalService) {

		_campaignLocalService = campaignLocalService;
	}

	/**
	 * Adds the campaign to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaign the campaign
	 * @return the campaign that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign addCampaign(
		io.github.valtairnascimento.campaignforms.model.Campaign campaign) {

		return _campaignLocalService.addCampaign(campaign);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign addCampaign(
			long userId, long groupId,
			io.github.valtairnascimento.campaignforms.input.CampaignInput
				campaignInput,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.addCampaign(
			userId, groupId, campaignInput, serviceContext);
	}

	/**
	 * Creates a new campaign with the primary key. Does not add the campaign to the database.
	 *
	 * @param campaignId the primary key for the new campaign
	 * @return the new campaign
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		createCampaign(long campaignId) {

		return _campaignLocalService.createCampaign(campaignId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the campaign from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaign the campaign
	 * @return the campaign that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		deleteCampaign(
			io.github.valtairnascimento.campaignforms.model.Campaign campaign) {

		return _campaignLocalService.deleteCampaign(campaign);
	}

	/**
	 * Deletes the campaign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaignId the primary key of the campaign
	 * @return the campaign that was removed
	 * @throws PortalException if a campaign with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			deleteCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.deleteCampaign(campaignId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _campaignLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _campaignLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _campaignLocalService.dynamicQuery();
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

		return _campaignLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignModelImpl</code>.
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

		return _campaignLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignModelImpl</code>.
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

		return _campaignLocalService.dynamicQuery(
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

		return _campaignLocalService.dynamicQueryCount(dynamicQuery);
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

		return _campaignLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		fetchCampaign(long campaignId) {

		return _campaignLocalService.fetchCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		fetchCampaignByExternalReferenceCode(
			String externalReferenceCode, long groupId) {

		return _campaignLocalService.fetchCampaignByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		fetchCampaignBySlug(long groupId, String slug) {

		return _campaignLocalService.fetchCampaignBySlug(groupId, slug);
	}

	/**
	 * Returns the campaign matching the UUID and group.
	 *
	 * @param uuid the campaign's UUID
	 * @param groupId the primary key of the group
	 * @return the matching campaign, or <code>null</code> if a matching campaign could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		fetchCampaignByUuidAndGroupId(String uuid, long groupId) {

		return _campaignLocalService.fetchCampaignByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _campaignLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the campaign with the primary key.
	 *
	 * @param campaignId the primary key of the campaign
	 * @return the campaign
	 * @throws PortalException if a campaign with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign getCampaign(
			long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.getCampaign(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignByExternalReferenceCode(
				String externalReferenceCode, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.getCampaignByExternalReferenceCode(
			externalReferenceCode, groupId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignBySlug(long groupId, String slug)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.getCampaignBySlug(groupId, slug);
	}

	/**
	 * Returns the campaign matching the UUID and group.
	 *
	 * @param uuid the campaign's UUID
	 * @param groupId the primary key of the group
	 * @return the matching campaign
	 * @throws PortalException if a matching campaign could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.getCampaignByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the campaigns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.CampaignModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaigns
	 * @param end the upper bound of the range of campaigns (not inclusive)
	 * @return the range of campaigns
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign> getCampaigns(
			int start, int end) {

		return _campaignLocalService.getCampaigns(start, end);
	}

	/**
	 * Returns all the campaigns matching the UUID and company.
	 *
	 * @param uuid the UUID of the campaigns
	 * @param companyId the primary key of the company
	 * @return the matching campaigns, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getCampaignsByUuidAndCompanyId(String uuid, long companyId) {

		return _campaignLocalService.getCampaignsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of campaigns matching the UUID and company.
	 *
	 * @param uuid the UUID of the campaigns
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of campaigns
	 * @param end the upper bound of the range of campaigns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching campaigns, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getCampaignsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<io.github.valtairnascimento.campaignforms.model.Campaign>
						orderByComparator) {

		return _campaignLocalService.getCampaignsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of campaigns.
	 *
	 * @return the number of campaigns
	 */
	@Override
	public int getCampaignsCount() {
		return _campaignLocalService.getCampaignsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _campaignLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getGroupCampaigns(
				long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<io.github.valtairnascimento.campaignforms.model.Campaign>
						orderByComparator) {

		return _campaignLocalService.getGroupCampaigns(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupCampaignsCount(long groupId) {
		return _campaignLocalService.getGroupCampaignsCount(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _campaignLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _campaignLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getSeriesCampaigns(long companyId, String seriesKey) {

		return _campaignLocalService.getSeriesCampaigns(companyId, seriesKey);
	}

	/**
	 * Updates the campaign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CampaignLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param campaign the campaign
	 * @return the campaign that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
		updateCampaign(
			io.github.valtairnascimento.campaignforms.model.Campaign campaign) {

		return _campaignLocalService.updateCampaign(campaign);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			updateCampaign(
				long userId, long campaignId,
				io.github.valtairnascimento.campaignforms.input.CampaignInput
					campaignInput,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.updateCampaign(
			userId, campaignId, campaignInput, serviceContext);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign updateMode(
			long userId, long campaignId,
			io.github.valtairnascimento.campaignforms.constants.CampaignMode
				mode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.updateMode(userId, campaignId, mode);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Campaign
			updateStatus(
				long userId, long campaignId,
				io.github.valtairnascimento.campaignforms.constants.
					CampaignStatus status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignLocalService.updateStatus(userId, campaignId, status);
	}

	@Override
	public CampaignLocalService getWrappedService() {
		return _campaignLocalService;
	}

	@Override
	public void setWrappedService(CampaignLocalService campaignLocalService) {
		_campaignLocalService = campaignLocalService;
	}

	private CampaignLocalService _campaignLocalService;

}