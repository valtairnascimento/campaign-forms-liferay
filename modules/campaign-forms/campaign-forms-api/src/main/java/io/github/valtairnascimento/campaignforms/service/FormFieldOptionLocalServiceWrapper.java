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
 * Provides a wrapper for {@link FormFieldOptionLocalService}.
 *
 * @author Valtair Nascimento
 * @see FormFieldOptionLocalService
 * @generated
 */
public class FormFieldOptionLocalServiceWrapper
	implements FormFieldOptionLocalService,
			   ServiceWrapper<FormFieldOptionLocalService> {

	public FormFieldOptionLocalServiceWrapper() {
		this(null);
	}

	public FormFieldOptionLocalServiceWrapper(
		FormFieldOptionLocalService formFieldOptionLocalService) {

		_formFieldOptionLocalService = formFieldOptionLocalService;
	}

	/**
	 * Adds the form field option to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldOptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldOption the form field option
	 * @return the form field option that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
		addFormFieldOption(
			io.github.valtairnascimento.campaignforms.model.FormFieldOption
				formFieldOption) {

		return _formFieldOptionLocalService.addFormFieldOption(formFieldOption);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormFieldOption>
			copyFormFieldOptions(
				long sourceFormFieldId,
				io.github.valtairnascimento.campaignforms.model.FormField
					targetFormField) {

		return _formFieldOptionLocalService.copyFormFieldOptions(
			sourceFormFieldId, targetFormField);
	}

	/**
	 * Creates a new form field option with the primary key. Does not add the form field option to the database.
	 *
	 * @param formFieldOptionId the primary key for the new form field option
	 * @return the new form field option
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
		createFormFieldOption(long formFieldOptionId) {

		return _formFieldOptionLocalService.createFormFieldOption(
			formFieldOptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the form field option from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldOptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldOption the form field option
	 * @return the form field option that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
		deleteFormFieldOption(
			io.github.valtairnascimento.campaignforms.model.FormFieldOption
				formFieldOption) {

		return _formFieldOptionLocalService.deleteFormFieldOption(
			formFieldOption);
	}

	/**
	 * Deletes the form field option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldOptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option that was removed
	 * @throws PortalException if a form field option with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
			deleteFormFieldOption(long formFieldOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.deleteFormFieldOption(
			formFieldOptionId);
	}

	@Override
	public void deleteFormFieldOptions(long formFieldId) {
		_formFieldOptionLocalService.deleteFormFieldOptions(formFieldId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _formFieldOptionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _formFieldOptionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formFieldOptionLocalService.dynamicQuery();
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

		return _formFieldOptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl</code>.
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

		return _formFieldOptionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl</code>.
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

		return _formFieldOptionLocalService.dynamicQuery(
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

		return _formFieldOptionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _formFieldOptionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
		fetchFormFieldOption(long formFieldOptionId) {

		return _formFieldOptionLocalService.fetchFormFieldOption(
			formFieldOptionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _formFieldOptionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the form field option with the primary key.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option
	 * @throws PortalException if a form field option with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
			getFormFieldOption(long formFieldOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.getFormFieldOption(
			formFieldOptionId);
	}

	/**
	 * Returns a range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @return the range of form field options
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormFieldOption>
			getFormFieldOptions(int start, int end) {

		return _formFieldOptionLocalService.getFormFieldOptions(start, end);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormFieldOption>
			getFormFieldOptions(long formFieldId) {

		return _formFieldOptionLocalService.getFormFieldOptions(formFieldId);
	}

	/**
	 * Returns the number of form field options.
	 *
	 * @return the number of form field options
	 */
	@Override
	public int getFormFieldOptionsCount() {
		return _formFieldOptionLocalService.getFormFieldOptionsCount();
	}

	@Override
	public java.util.Map
		<Long,
		 java.util.List
			 <io.github.valtairnascimento.campaignforms.model.FormFieldOption>>
				getFormFieldOptionsMap(long[] formFieldIds) {

		return _formFieldOptionLocalService.getFormFieldOptionsMap(
			formFieldIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _formFieldOptionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _formFieldOptionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormFieldOption>
				setFormFieldOptions(
					io.github.valtairnascimento.campaignforms.model.FormField
						formField,
					java.util.List
						<io.github.valtairnascimento.campaignforms.input.
							FieldOptionInput> fieldOptionInputs)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formFieldOptionLocalService.setFormFieldOptions(
			formField, fieldOptionInputs);
	}

	/**
	 * Updates the form field option in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldOptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldOption the form field option
	 * @return the form field option that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormFieldOption
		updateFormFieldOption(
			io.github.valtairnascimento.campaignforms.model.FormFieldOption
				formFieldOption) {

		return _formFieldOptionLocalService.updateFormFieldOption(
			formFieldOption);
	}

	@Override
	public FormFieldOptionLocalService getWrappedService() {
		return _formFieldOptionLocalService;
	}

	@Override
	public void setWrappedService(
		FormFieldOptionLocalService formFieldOptionLocalService) {

		_formFieldOptionLocalService = formFieldOptionLocalService;
	}

	private FormFieldOptionLocalService _formFieldOptionLocalService;

}