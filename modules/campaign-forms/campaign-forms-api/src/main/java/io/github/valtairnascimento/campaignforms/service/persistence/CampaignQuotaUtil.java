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

package io.github.valtairnascimento.campaignforms.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.model.CampaignQuota;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the campaign quota service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.CampaignQuotaPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see CampaignQuotaPersistence
 * @generated
 */
public class CampaignQuotaUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CampaignQuota campaignQuota) {
		getPersistence().clearCache(campaignQuota);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CampaignQuota> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CampaignQuota> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CampaignQuota> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CampaignQuota> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CampaignQuota> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CampaignQuota update(CampaignQuota campaignQuota) {
		return getPersistence().update(campaignQuota);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CampaignQuota update(
		CampaignQuota campaignQuota, ServiceContext serviceContext) {

		return getPersistence().update(campaignQuota, serviceContext);
	}

	/**
	 * Caches the campaign quota in the entity cache if it is enabled.
	 *
	 * @param campaignQuota the campaign quota
	 */
	public static void cacheResult(CampaignQuota campaignQuota) {
		getPersistence().cacheResult(campaignQuota);
	}

	/**
	 * Caches the campaign quotas in the entity cache if it is enabled.
	 *
	 * @param campaignQuotas the campaign quotas
	 */
	public static void cacheResult(List<CampaignQuota> campaignQuotas) {
		getPersistence().cacheResult(campaignQuotas);
	}

	/**
	 * Creates a new campaign quota with the primary key. Does not add the campaign quota to the database.
	 *
	 * @param campaignId the primary key for the new campaign quota
	 * @return the new campaign quota
	 */
	public static CampaignQuota create(long campaignId) {
		return getPersistence().create(campaignId);
	}

	/**
	 * Removes the campaign quota with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota that was removed
	 * @throws NoSuchCampaignQuotaException if a campaign quota with the primary key could not be found
	 */
	public static CampaignQuota remove(long campaignId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchCampaignQuotaException {

		return getPersistence().remove(campaignId);
	}

	public static CampaignQuota updateImpl(CampaignQuota campaignQuota) {
		return getPersistence().updateImpl(campaignQuota);
	}

	/**
	 * Returns the campaign quota with the primary key or throws a <code>NoSuchCampaignQuotaException</code> if it could not be found.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota
	 * @throws NoSuchCampaignQuotaException if a campaign quota with the primary key could not be found
	 */
	public static CampaignQuota findByPrimaryKey(long campaignId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchCampaignQuotaException {

		return getPersistence().findByPrimaryKey(campaignId);
	}

	/**
	 * Returns the campaign quota with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota, or <code>null</code> if a campaign quota with the primary key could not be found
	 */
	public static CampaignQuota fetchByPrimaryKey(long campaignId) {
		return getPersistence().fetchByPrimaryKey(campaignId);
	}

	/**
	 * Returns all the campaign quotas.
	 *
	 * @return the campaign quotas
	 */
	public static List<CampaignQuota> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @return the range of campaign quotas
	 */
	public static List<CampaignQuota> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of campaign quotas
	 */
	public static List<CampaignQuota> findAll(
		int start, int end,
		OrderByComparator<CampaignQuota> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of campaign quotas
	 */
	public static List<CampaignQuota> findAll(
		int start, int end, OrderByComparator<CampaignQuota> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the campaign quotas from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of campaign quotas.
	 *
	 * @return the number of campaign quotas
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CampaignQuotaPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CampaignQuotaPersistence _persistence;

}