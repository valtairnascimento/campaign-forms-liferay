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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import io.github.valtairnascimento.campaignforms.exception.NoSuchPageTemplateException;
import io.github.valtairnascimento.campaignforms.model.PageTemplate;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the page template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see PageTemplateUtil
 * @generated
 */
@ProviderType
public interface PageTemplatePersistence extends BasePersistence<PageTemplate> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PageTemplateUtil} to access the page template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching page templates
	 */
	public java.util.List<PageTemplate> findByUuid(String uuid);

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
	public java.util.List<PageTemplate> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<PageTemplate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

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
	public java.util.List<PageTemplate> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the first page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

	/**
	 * Returns the last page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the last page template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

	/**
	 * Returns the page templates before and after the current page template in the ordered set where uuid = &#63;.
	 *
	 * @param pageTemplateId the primary key of the current page template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public PageTemplate[] findByUuid_PrevAndNext(
			long pageTemplateId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Removes all the page templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of page templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching page templates
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the page template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the page template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the page template that was removed
	 */
	public PageTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the number of page templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching page templates
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching page templates
	 */
	public java.util.List<PageTemplate> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

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
	public java.util.List<PageTemplate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the first page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

	/**
	 * Returns the last page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the last page template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

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
	public PageTemplate[] findByUuid_C_PrevAndNext(
			long pageTemplateId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Removes all the page templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of page templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching page templates
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the page templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching page templates
	 */
	public java.util.List<PageTemplate> findByGroupId(long groupId);

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
	public java.util.List<PageTemplate> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<PageTemplate> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

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
	public java.util.List<PageTemplate> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the first page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

	/**
	 * Returns the last page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the last page template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

	/**
	 * Returns the page templates before and after the current page template in the ordered set where groupId = &#63;.
	 *
	 * @param pageTemplateId the primary key of the current page template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public PageTemplate[] findByGroupId_PrevAndNext(
			long pageTemplateId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
				orderByComparator)
		throws NoSuchPageTemplateException;

	/**
	 * Removes all the page templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of page templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching page templates
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the matching page template
	 * @throws NoSuchPageTemplateException if a matching page template could not be found
	 */
	public PageTemplate findByG_TK(long groupId, String templateKey)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByG_TK(long groupId, String templateKey);

	/**
	 * Returns the page template where groupId = &#63; and templateKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public PageTemplate fetchByG_TK(
		long groupId, String templateKey, boolean useFinderCache);

	/**
	 * Removes the page template where groupId = &#63; and templateKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the page template that was removed
	 */
	public PageTemplate removeByG_TK(long groupId, String templateKey)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the number of page templates where groupId = &#63; and templateKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param templateKey the template key
	 * @return the number of matching page templates
	 */
	public int countByG_TK(long groupId, String templateKey);

	/**
	 * Caches the page template in the entity cache if it is enabled.
	 *
	 * @param pageTemplate the page template
	 */
	public void cacheResult(PageTemplate pageTemplate);

	/**
	 * Caches the page templates in the entity cache if it is enabled.
	 *
	 * @param pageTemplates the page templates
	 */
	public void cacheResult(java.util.List<PageTemplate> pageTemplates);

	/**
	 * Creates a new page template with the primary key. Does not add the page template to the database.
	 *
	 * @param pageTemplateId the primary key for the new page template
	 * @return the new page template
	 */
	public PageTemplate create(long pageTemplateId);

	/**
	 * Removes the page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template that was removed
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public PageTemplate remove(long pageTemplateId)
		throws NoSuchPageTemplateException;

	public PageTemplate updateImpl(PageTemplate pageTemplate);

	/**
	 * Returns the page template with the primary key or throws a <code>NoSuchPageTemplateException</code> if it could not be found.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template
	 * @throws NoSuchPageTemplateException if a page template with the primary key could not be found
	 */
	public PageTemplate findByPrimaryKey(long pageTemplateId)
		throws NoSuchPageTemplateException;

	/**
	 * Returns the page template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template, or <code>null</code> if a page template with the primary key could not be found
	 */
	public PageTemplate fetchByPrimaryKey(long pageTemplateId);

	/**
	 * Returns all the page templates.
	 *
	 * @return the page templates
	 */
	public java.util.List<PageTemplate> findAll();

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
	public java.util.List<PageTemplate> findAll(int start, int end);

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
	public java.util.List<PageTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator);

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
	public java.util.List<PageTemplate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PageTemplate>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the page templates from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of page templates.
	 *
	 * @return the number of page templates
	 */
	public int countAll();

}