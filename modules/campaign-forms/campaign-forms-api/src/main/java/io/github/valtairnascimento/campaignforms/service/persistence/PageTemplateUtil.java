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

import io.github.valtairnascimento.campaignforms.model.PageTemplate;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the page template service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.PageTemplatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageTemplatePersistence
 * @generated
 */
public class PageTemplateUtil {

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
	public static void clearCache(PageTemplate pageTemplate) {
		getPersistence().clearCache(pageTemplate);
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
	public static Map<Serializable, PageTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PageTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PageTemplate update(PageTemplate pageTemplate) {
		return getPersistence().update(pageTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PageTemplate update(
		PageTemplate pageTemplate, ServiceContext serviceContext) {

		return getPersistence().update(pageTemplate, serviceContext);
	}

	/**
	 * Returns all the page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching page templates
	 */
	public static List<PageTemplate> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the page templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @return the range of matching page templates
	 */
	public static List<PageTemplate> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the page templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByUuid_First(
			String uuid, OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUuid_First(
		String uuid, OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByUuid_Last(
			String uuid, OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUuid_Last(
		String uuid, OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the page templates before and after the current page template in the ordered set where uuid = &#63;.
	 *
	 * @param pageTemplateId the primary key of the current page template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public static PageTemplate[] findByUuid_PrevAndNext(
			long pageTemplateId, String uuid,
			OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_PrevAndNext(
			pageTemplateId, uuid, orderByComparator);
	}

	/**
	 * Removes all the page templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching page templates
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByUUID_G(String uuid, long groupId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the page template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the page template that was removed
	 */
	public static PageTemplate removeByUUID_G(String uuid, long groupId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of page templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching page templates
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching page templates
	 */
	public static List<PageTemplate> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @return the range of matching page templates
	 */
	public static List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the page templates before and after the current page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param pageTemplateId the primary key of the current page template
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public static PageTemplate[] findByUuid_C_PrevAndNext(
			long pageTemplateId, String uuid, long companyId,
			OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByUuid_C_PrevAndNext(
			pageTemplateId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the page templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching page templates
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the page templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching page templates
	 */
	public static List<PageTemplate> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the page templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @return the range of matching page templates
	 */
	public static List<PageTemplate> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the page templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page templates
	 */
	public static List<PageTemplate> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByGroupId_First(
			long groupId, OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByGroupId_First(
		long groupId, OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByGroupId_Last(
			long groupId, OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByGroupId_Last(
		long groupId, OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the page templates before and after the current page template in the ordered set where groupId = &#63;.
	 *
	 * @param pageTemplateId the primary key of the current page template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public static PageTemplate[] findByGroupId_PrevAndNext(
			long pageTemplateId, long groupId,
			OrderByComparator<PageTemplate> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByGroupId_PrevAndNext(
			pageTemplateId, groupId, orderByComparator);
	}

	/**
	 * Removes all the page templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of page templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching page templates
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public static PageTemplate findByG_TK(long groupId, String templateKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByG_TK(groupId, templateKey);
	}

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByG_TK(long groupId, String templateKey) {
		return getPersistence().fetchByG_TK(groupId, templateKey);
	}

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchByG_TK(
		long groupId, String templateKey, boolean useFinderCache) {

		return getPersistence().fetchByG_TK(
			groupId, templateKey, useFinderCache);
	}

	/**
	 * Removes the page template where groupId = &#63; and templateKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the page template that was removed
	 */
	public static PageTemplate removeByG_TK(long groupId, String templateKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().removeByG_TK(groupId, templateKey);
	}

	/**
	 * Returns the number of page templates where groupId = &#63; and templateKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the number of matching page templates
	 */
	public static int countByG_TK(long groupId, String templateKey) {
		return getPersistence().countByG_TK(groupId, templateKey);
	}

	/**
	 * Caches the page template in the entity cache if it is enabled.
	 *
	 * @param pageTemplate the page template
	 */
	public static void cacheResult(PageTemplate pageTemplate) {
		getPersistence().cacheResult(pageTemplate);
	}

	/**
	 * Caches the page templates in the entity cache if it is enabled.
	 *
	 * @param pageTemplates the page templates
	 */
	public static void cacheResult(List<PageTemplate> pageTemplates) {
		getPersistence().cacheResult(pageTemplates);
	}

	/**
	 * Creates a new page template with the primary key. Does not add the page template to the database.
	 *
	 * @param pageTemplateId the primary key for the new page template
	 * @return the new page template
	 */
	public static PageTemplate create(long pageTemplateId) {
		return getPersistence().create(pageTemplateId);
	}

	/**
	 * Removes the page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template that was removed
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public static PageTemplate remove(long pageTemplateId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().remove(pageTemplateId);
	}

	public static PageTemplate updateImpl(PageTemplate pageTemplate) {
		return getPersistence().updateImpl(pageTemplate);
	}

	/**
	 * Returns the page template with the primary key or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public static PageTemplate findByPrimaryKey(long pageTemplateId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchPageTemplateException {

		return getPersistence().findByPrimaryKey(pageTemplateId);
	}

	/**
	 * Returns the page template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template, or <code>null</code> if a page template with the primary key could not be found
	 */
	public static PageTemplate fetchByPrimaryKey(long pageTemplateId) {
		return getPersistence().fetchByPrimaryKey(pageTemplateId);
	}

	/**
	 * Returns all the page templates.
	 *
	 * @return the page templates
	 */
	public static List<PageTemplate> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the page templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @return the range of page templates
	 */
	public static List<PageTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the page templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of page templates
	 */
	public static List<PageTemplate> findAll(
		int start, int end, OrderByComparator<PageTemplate> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the page templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of page templates
	 */
	public static List<PageTemplate> findAll(
		int start, int end, OrderByComparator<PageTemplate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the page templates from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of page templates.
	 *
	 * @return the number of page templates
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PageTemplatePersistence getPersistence() {
		return _persistence;
	}

	private static volatile PageTemplatePersistence _persistence;

}