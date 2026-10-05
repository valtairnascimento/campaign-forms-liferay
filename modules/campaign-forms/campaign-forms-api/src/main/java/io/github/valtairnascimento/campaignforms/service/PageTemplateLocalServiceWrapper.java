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
 * Provides a wrapper for {@link PageTemplateLocalService}.
 *
 * @author Valtair Nascimento
 * @see PageTemplateLocalService
 * @generated
 */
public class PageTemplateLocalServiceWrapper
	implements PageTemplateLocalService,
			   ServiceWrapper<PageTemplateLocalService> {

	public PageTemplateLocalServiceWrapper() {
		this(null);
	}

	public PageTemplateLocalServiceWrapper(
		PageTemplateLocalService pageTemplateLocalService) {

		_pageTemplateLocalService = pageTemplateLocalService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
			addPageTemplate(
				long userId, long groupId, String templateKey, String name,
				String description, String blocks, boolean systemTemplate,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.addPageTemplate(
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		addPageTemplate(
			io.github.valtairnascimento.campaignforms.model.PageTemplate
				pageTemplate) {

		return _pageTemplateLocalService.addPageTemplate(pageTemplate);
	}

	/**
	 * Creates a new page template with the primary key. Does not add the page template to the database.
	 *
	 * @param pageTemplateId the primary key for the new page template
	 * @return the new page template
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		createPageTemplate(long pageTemplateId) {

		return _pageTemplateLocalService.createPageTemplate(pageTemplateId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
			deletePageTemplate(long pageTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.deletePageTemplate(pageTemplateId);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		deletePageTemplate(
			io.github.valtairnascimento.campaignforms.model.PageTemplate
				pageTemplate) {

		return _pageTemplateLocalService.deletePageTemplate(pageTemplate);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _pageTemplateLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _pageTemplateLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pageTemplateLocalService.dynamicQuery();
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

		return _pageTemplateLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _pageTemplateLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _pageTemplateLocalService.dynamicQuery(
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

		return _pageTemplateLocalService.dynamicQueryCount(dynamicQuery);
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

		return _pageTemplateLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		fetchPageTemplate(long pageTemplateId) {

		return _pageTemplateLocalService.fetchPageTemplate(pageTemplateId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		fetchPageTemplate(long groupId, String templateKey) {

		return _pageTemplateLocalService.fetchPageTemplate(
			groupId, templateKey);
	}

	/**
	 * Returns the page template matching the UUID and group.
	 *
	 * @param uuid the page template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching page template, or <code>null</code> if a matching page template could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		fetchPageTemplateByUuidAndGroupId(String uuid, long groupId) {

		return _pageTemplateLocalService.fetchPageTemplateByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _pageTemplateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _pageTemplateLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageTemplate>
			getGroupPageTemplates(long groupId) {

		return _pageTemplateLocalService.getGroupPageTemplates(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _pageTemplateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageTemplateLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the page template with the primary key.
	 *
	 * @param pageTemplateId the primary key of the page template
	 * @return the page template
	 * @throws PortalException if a page template with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
			getPageTemplate(long pageTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.getPageTemplate(pageTemplateId);
	}

	/**
	 * Returns the page template matching the UUID and group.
	 *
	 * @param uuid the page template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching page template
	 * @throws PortalException if a matching page template could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
			getPageTemplateByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.getPageTemplateByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageTemplate>
			getPageTemplates(int start, int end) {

		return _pageTemplateLocalService.getPageTemplates(start, end);
	}

	/**
	 * Returns all the page templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the page templates
	 * @param companyId the primary key of the company
	 * @return the matching page templates, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageTemplate>
			getPageTemplatesByUuidAndCompanyId(String uuid, long companyId) {

		return _pageTemplateLocalService.getPageTemplatesByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageTemplate>
			getPageTemplatesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<io.github.valtairnascimento.campaignforms.model.
						PageTemplate> orderByComparator) {

		return _pageTemplateLocalService.getPageTemplatesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of page templates.
	 *
	 * @return the number of page templates
	 */
	@Override
	public int getPageTemplatesCount() {
		return _pageTemplateLocalService.getPageTemplatesCount();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
			updatePageTemplate(
				long pageTemplateId, String name, String description,
				String blocks)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageTemplateLocalService.updatePageTemplate(
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.PageTemplate
		updatePageTemplate(
			io.github.valtairnascimento.campaignforms.model.PageTemplate
				pageTemplate) {

		return _pageTemplateLocalService.updatePageTemplate(pageTemplate);
	}

	@Override
	public PageTemplateLocalService getWrappedService() {
		return _pageTemplateLocalService;
	}

	@Override
	public void setWrappedService(
		PageTemplateLocalService pageTemplateLocalService) {

		_pageTemplateLocalService = pageTemplateLocalService;
	}

	private PageTemplateLocalService _pageTemplateLocalService;

}