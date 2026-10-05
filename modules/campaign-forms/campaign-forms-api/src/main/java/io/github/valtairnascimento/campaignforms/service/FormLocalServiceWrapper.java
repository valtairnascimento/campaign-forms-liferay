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
 * Provides a wrapper for {@link FormLocalService}.
 *
 * @author Valtair Nascimento
 * @see FormLocalService
 * @generated
 */
public class FormLocalServiceWrapper
	implements FormLocalService, ServiceWrapper<FormLocalService> {

	public FormLocalServiceWrapper() {
		this(null);
	}

	public FormLocalServiceWrapper(FormLocalService formLocalService) {
		_formLocalService = formLocalService;
	}

	/**
	 * Adds the form to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param form the form
	 * @return the form that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form addForm(
		io.github.valtairnascimento.campaignforms.model.Form form) {

		return _formLocalService.addForm(form);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Form addForm(
			long userId, long campaignId, String formKey, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.addForm(userId, campaignId, formKey, name);
	}

	/**
	 * Creates a new form with the primary key. Does not add the form to the database.
	 *
	 * @param formId the primary key for the new form
	 * @return the new form
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form createForm(
		long formId) {

		return _formLocalService.createForm(formId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.createPersistedModel(primaryKeyObj);
	}

	@Override
	public void deleteCampaignForms(long campaignId) {
		_formLocalService.deleteCampaignForms(campaignId);
	}

	/**
	 * Deletes the form from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param form the form
	 * @return the form that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form deleteForm(
		io.github.valtairnascimento.campaignforms.model.Form form) {

		return _formLocalService.deleteForm(form);
	}

	/**
	 * Deletes the form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formId the primary key of the form
	 * @return the form that was removed
	 * @throws PortalException if a form with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form deleteForm(
			long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.deleteForm(formId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _formLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _formLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formLocalService.dynamicQuery();
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

		return _formLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormModelImpl</code>.
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

		return _formLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormModelImpl</code>.
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

		return _formLocalService.dynamicQuery(
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

		return _formLocalService.dynamicQueryCount(dynamicQuery);
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

		return _formLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Form
		fetchCampaignForm(long campaignId) {

		return _formLocalService.fetchCampaignForm(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Form fetchForm(
		long formId) {

		return _formLocalService.fetchForm(formId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _formLocalService.getActionableDynamicQuery();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Form getCampaignForm(
			long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.getCampaignForm(campaignId);
	}

	@Override
	public java.util.List<io.github.valtairnascimento.campaignforms.model.Form>
		getCampaignForms(long campaignId) {

		return _formLocalService.getCampaignForms(campaignId);
	}

	/**
	 * Returns the form with the primary key.
	 *
	 * @param formId the primary key of the form
	 * @return the form
	 * @throws PortalException if a form with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form getForm(
			long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.getForm(formId);
	}

	/**
	 * Returns a range of all the forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @return the range of forms
	 */
	@Override
	public java.util.List<io.github.valtairnascimento.campaignforms.model.Form>
		getForms(int start, int end) {

		return _formLocalService.getForms(start, end);
	}

	/**
	 * Returns the number of forms.
	 *
	 * @return the number of forms
	 */
	@Override
	public int getFormsCount() {
		return _formLocalService.getFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _formLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			getOrCreateDraftFormVersion(long userId, long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.getOrCreateDraftFormVersion(userId, formId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _formLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.Form
			publishDraftFormVersion(long userId, long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.publishDraftFormVersion(userId, formId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			replaceDraftFormVersion(
				long userId, long formId, long sourceFormVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formLocalService.replaceDraftFormVersion(
			userId, formId, sourceFormVersionId);
	}

	/**
	 * Updates the form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param form the form
	 * @return the form that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.Form updateForm(
		io.github.valtairnascimento.campaignforms.model.Form form) {

		return _formLocalService.updateForm(form);
	}

	@Override
	public FormLocalService getWrappedService() {
		return _formLocalService;
	}

	@Override
	public void setWrappedService(FormLocalService formLocalService) {
		_formLocalService = formLocalService;
	}

	private FormLocalService _formLocalService;

}