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

import io.github.valtairnascimento.campaignforms.model.PageConfiguration;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the page configuration service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.PageConfigurationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageConfigurationPersistence
 * @generated
 */
public class PageConfigurationUtil {

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
	public static void clearCache(PageConfiguration pageConfiguration) {
		getPersistence().clearCache(pageConfiguration);
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
	public static Map<Serializable, PageConfiguration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PageConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PageConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PageConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PageConfiguration update(
		PageConfiguration pageConfiguration) {

		return getPersistence().update(pageConfiguration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PageConfiguration update(
		PageConfiguration pageConfiguration, ServiceContext serviceContext) {

		return getPersistence().update(pageConfiguration, serviceContext);
	}

	/**
	 * Returns all the page configurations where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching page configurations
	 */
	public static List<PageConfiguration> findByCampaignId(long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

	/**
	 * Returns a range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @return the range of matching page configurations
	 */
	public static List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end) {

		return getPersistence().findByCampaignId(campaignId, start, end);
	}

	/**
	 * Returns an ordered range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page configurations
	 */
	public static List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page configurations
	 */
	public static List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	public static PageConfiguration findByCampaignId_First(
			long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().findByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the first page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	public static PageConfiguration fetchByCampaignId_First(
		long campaignId,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return getPersistence().fetchByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	public static PageConfiguration findByCampaignId_Last(
			long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().findByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	public static PageConfiguration fetchByCampaignId_Last(
		long campaignId,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return getPersistence().fetchByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the page configurations before and after the current page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param pageConfigurationId the primary key of the current page configuration
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page configuration
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	public static PageConfiguration[] findByCampaignId_PrevAndNext(
			long pageConfigurationId, long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().findByCampaignId_PrevAndNext(
			pageConfigurationId, campaignId, orderByComparator);
	}

	/**
	 * Removes all the page configurations where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public static void removeByCampaignId(long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	 * Returns the number of page configurations where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching page configurations
	 */
	public static int countByCampaignId(long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or throws a <code>NoSuchPageConfigurationException</code> if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	public static PageConfiguration findByC_S(long campaignId, String stage)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().findByC_S(campaignId, stage);
	}

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	public static PageConfiguration fetchByC_S(long campaignId, String stage) {
		return getPersistence().fetchByC_S(campaignId, stage);
	}

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	public static PageConfiguration fetchByC_S(
		long campaignId, String stage, boolean useFinderCache) {

		return getPersistence().fetchByC_S(campaignId, stage, useFinderCache);
	}

	/**
	 * Removes the page configuration where campaignId = &#63; and stage = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the page configuration that was removed
	 */
	public static PageConfiguration removeByC_S(long campaignId, String stage)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().removeByC_S(campaignId, stage);
	}

	/**
	 * Returns the number of page configurations where campaignId = &#63; and stage = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the number of matching page configurations
	 */
	public static int countByC_S(long campaignId, String stage) {
		return getPersistence().countByC_S(campaignId, stage);
	}

	/**
	 * Caches the page configuration in the entity cache if it is enabled.
	 *
	 * @param pageConfiguration the page configuration
	 */
	public static void cacheResult(PageConfiguration pageConfiguration) {
		getPersistence().cacheResult(pageConfiguration);
	}

	/**
	 * Caches the page configurations in the entity cache if it is enabled.
	 *
	 * @param pageConfigurations the page configurations
	 */
	public static void cacheResult(List<PageConfiguration> pageConfigurations) {
		getPersistence().cacheResult(pageConfigurations);
	}

	/**
	 * Creates a new page configuration with the primary key. Does not add the page configuration to the database.
	 *
	 * @param pageConfigurationId the primary key for the new page configuration
	 * @return the new page configuration
	 */
	public static PageConfiguration create(long pageConfigurationId) {
		return getPersistence().create(pageConfigurationId);
	}

	/**
	 * Removes the page configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration that was removed
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	public static PageConfiguration remove(long pageConfigurationId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().remove(pageConfigurationId);
	}

	public static PageConfiguration updateImpl(
		PageConfiguration pageConfiguration) {

		return getPersistence().updateImpl(pageConfiguration);
	}

	/**
	 * Returns the page configuration with the primary key or throws a <code>NoSuchPageConfigurationException</code> if it could not be found.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	public static PageConfiguration findByPrimaryKey(long pageConfigurationId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageConfigurationException {

		return getPersistence().findByPrimaryKey(pageConfigurationId);
	}

	/**
	 * Returns the page configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration, or <code>null</code> if a page configuration with the primary key could not be found
	 */
	public static PageConfiguration fetchByPrimaryKey(
		long pageConfigurationId) {

		return getPersistence().fetchByPrimaryKey(pageConfigurationId);
	}

	/**
	 * Returns all the page configurations.
	 *
	 * @return the page configurations
	 */
	public static List<PageConfiguration> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @return the range of page configurations
	 */
	public static List<PageConfiguration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of page configurations
	 */
	public static List<PageConfiguration> findAll(
		int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of page configurations
	 */
	public static List<PageConfiguration> findAll(
		int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the page configurations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of page configurations.
	 *
	 * @return the number of page configurations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PageConfigurationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile PageConfigurationPersistence _persistence;

}