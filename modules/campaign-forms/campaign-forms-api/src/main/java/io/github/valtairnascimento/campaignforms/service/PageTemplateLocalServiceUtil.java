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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import io.github.valtairnascimento.campaignforms.model.PageTemplate;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for PageTemplate. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.PageTemplateLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see PageTemplateLocalService
 * @generated
 */
public class PageTemplateLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.PageTemplateLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static PageTemplate addPageTemplate(
			long userId, long groupId, String templateKey, String name,
			String description, String blocks, boolean systemTemplate,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addPageTemplate(
			userId, groupId, templateKey, name, description, blocks,
			systemTemplate, serviceContext);
	}

	/**
	 * Adds the page template to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageTemplate the page template
	 * @return the page template that was added
	 */
	public static PageTemplate addPageTemplate(PageTemplate pageTemplate) {
		return getService().addPageTemplate(pageTemplate);
	}

	/**
	 * Creates a new page template with the primary key. Does not add the page template to the database.
	 *
	 * @param pageTemplateId the primary key for the new page template
	 * @return the new page template
	 */
	public static PageTemplate createPageTemplate(long pageTemplateId) {
		return getService().createPageTemplate(pageTemplateId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template that was removed
	 * @throws PortalException if a page template with the primary key could not be found
	 */
	public static PageTemplate deletePageTemplate(long pageTemplateId)
		throws PortalException {

		return getService().deletePageTemplate(pageTemplateId);
	}

	/**
	 * Deletes the page template from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageTemplate the page template
	 * @return the page template that was removed
	 */
	public static PageTemplate deletePageTemplate(PageTemplate pageTemplate) {
		return getService().deletePageTemplate(pageTemplate);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static PageTemplate fetchPageTemplate(long pageTemplateId) {
		return getService().fetchPageTemplate(pageTemplateId);
	}

	public static PageTemplate fetchPageTemplate(
		long groupId, String templateKey) {

		return getService().fetchPageTemplate(groupId, templateKey);
	}

	/**
	 * Returns the page template matching the UUID and group.
	 *
	 * @param uuid the page template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	public static PageTemplate fetchPageTemplateByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchPageTemplateByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static List<PageTemplate> getGroupPageTemplates(long groupId) {
		return getService().getGroupPageTemplates(groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Returns the page template with the primary key.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template
	 * @throws PortalException if a page template with the primary key could not be found
	 */
	public static PageTemplate getPageTemplate(long pageTemplateId)
		throws PortalException {

		return getService().getPageTemplate(pageTemplateId);
	}

	/**
	 * Returns the page template matching the UUID and group.
	 *
	 * @param uuid the page template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching page template
	 * @throws PortalException if a matching page template could not be found
	 */
	public static PageTemplate getPageTemplateByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getPageTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the page templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.PageTemplateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @return the range of page templates
	 */
	public static List<PageTemplate> getPageTemplates(int start, int end) {
		return getService().getPageTemplates(start, end);
	}

	/**
	 * Returns all the page templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the page templates
	 * @param companyId the primary key of the company
	 * @return the matching page templates, or an empty list if no matches were found
	 */
	public static List<PageTemplate> getPageTemplatesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getPageTemplatesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of page templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the page templates
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of page templates
	 * @param end the upper bound of the range of page templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching page templates, or an empty list if no matches were found
	 */
	public static List<PageTemplate> getPageTemplatesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<PageTemplate> orderByComparator) {

		return getService().getPageTemplatesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of page templates.
	 *
	 * @return the number of page templates
	 */
	public static int getPageTemplatesCount() {
		return getService().getPageTemplatesCount();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static PageTemplate updatePageTemplate(
			long pageTemplateId, String name, String description, String blocks)
		throws PortalException {

		return getService().updatePageTemplate(
			pageTemplateId, name, description, blocks);
	}

	/**
	 * Updates the page template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PageTemplateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pageTemplate the page template
	 * @return the page template that was updated
	 */
	public static PageTemplate updatePageTemplate(PageTemplate pageTemplate) {
		return getService().updatePageTemplate(pageTemplate);
	}

	public static PageTemplateLocalService getService() {
		return _service;
	}

	private static volatile PageTemplateLocalService _service;

}