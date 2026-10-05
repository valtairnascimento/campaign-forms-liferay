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
 * Provides a wrapper for {@link FormFieldLocalService}.
 *
 * @author Valtair Nascimento
 * @see FormFieldLocalService
 * @generated
 */
public class FormFieldLocalServiceWrapper
	implements FormFieldLocalService, ServiceWrapper<FormFieldLocalService> {

	public FormFieldLocalServiceWrapper() {
		this(null);
	}

	public FormFieldLocalServiceWrapper(
		FormFieldLocalService formFieldLocalService) {

		_formFieldLocalService = formFieldLocalService;
	}

	/**
	 * Adds the form field to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formField the form field
	 * @return the form field that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
		addFormField(
			io.github.valtairnascimento.campaignforms.model.FormField
				formField) {

		return _formFieldLocalService.addFormField(formField);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			addFormField(
				long formVersionId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.addFormField(
			formVersionId, formFieldInput);
	}

	@Override
	public void copyFormFields(
			long sourceFormVersionId,
			io.github.valtairnascimento.campaignforms.model.FormVersion
				targetFormVersion)
		throws com.liferay.portal.kernel.exception.PortalException {

		_formFieldLocalService.copyFormFields(
			sourceFormVersionId, targetFormVersion);
	}

	/**
	 * Creates a new form field with the primary key. Does not add the form field to the database.
	 *
	 * @param formFieldId the primary key for the new form field
	 * @return the new form field
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
		createFormField(long formFieldId) {

		return _formFieldLocalService.createFormField(formFieldId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the form field from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formField the form field
	 * @return the form field that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
		deleteFormField(
			io.github.valtairnascimento.campaignforms.model.FormField
				formField) {

		return _formFieldLocalService.deleteFormField(formField);
	}

	/**
	 * Deletes the form field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field that was removed
	 * @throws PortalException if a form field with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			deleteFormField(long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.deleteFormField(formFieldId);
	}

	@Override
	public void deleteFormVersionFields(long formVersionId) {
		_formFieldLocalService.deleteFormVersionFields(formVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _formFieldLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _formFieldLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formFieldLocalService.dynamicQuery();
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

		return _formFieldLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldModelImpl</code>.
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

		return _formFieldLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldModelImpl</code>.
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

		return _formFieldLocalService.dynamicQuery(
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

		return _formFieldLocalService.dynamicQueryCount(dynamicQuery);
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

		return _formFieldLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
		fetchFormField(long formFieldId) {

		return _formFieldLocalService.fetchFormField(formFieldId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _formFieldLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the form field with the primary key.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field
	 * @throws PortalException if a form field with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			getFormField(long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.getFormField(formFieldId);
	}

	/**
	 * Returns a range of all the form fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @return the range of form fields
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
			getFormFields(int start, int end) {

		return _formFieldLocalService.getFormFields(start, end);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
			getFormFields(long formVersionId) {

		return _formFieldLocalService.getFormFields(formVersionId);
	}

	/**
	 * Returns the number of form fields.
	 *
	 * @return the number of form fields
	 */
	@Override
	public int getFormFieldsCount() {
		return _formFieldLocalService.getFormFieldsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _formFieldLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _formFieldLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				moveFormField(long formFieldId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.moveFormField(formFieldId, position);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				reorderFormFields(
					long formVersionId, long[] orderedFormFieldIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.reorderFormFields(
			formVersionId, orderedFormFieldIds);
	}

	/**
	 * Updates the form field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formField the form field
	 * @return the form field that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
		updateFormField(
			io.github.valtairnascimento.campaignforms.model.FormField
				formField) {

		return _formFieldLocalService.updateFormField(formField);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			updateFormField(
				long formFieldId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldLocalService.updateFormField(
			formFieldId, formFieldInput);
	}

	@Override
	public FormFieldLocalService getWrappedService() {
		return _formFieldLocalService;
	}

	@Override
	public void setWrappedService(FormFieldLocalService formFieldLocalService) {
		_formFieldLocalService = formFieldLocalService;
	}

	private FormFieldLocalService _formFieldLocalService;

}