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
 * Provides a wrapper for {@link FormVersionLocalService}.
 *
 * @author Valtair Nascimento
 * @see FormVersionLocalService
 * @generated
 */
public class FormVersionLocalServiceWrapper
	implements FormVersionLocalService,
			   ServiceWrapper<FormVersionLocalService> {

	public FormVersionLocalServiceWrapper() {
		this(null);
	}

	public FormVersionLocalServiceWrapper(
		FormVersionLocalService formVersionLocalService) {

		_formVersionLocalService = formVersionLocalService;
	}

	/**
	 * Adds the form version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formVersion the form version
	 * @return the form version that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
		addFormVersion(
			io.github.valtairnascimento.campaignforms.model.FormVersion
				formVersion) {

		return _formVersionLocalService.addFormVersion(formVersion);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			addFormVersion(
				long userId,
				io.github.valtairnascimento.campaignforms.model.Form form,
				int version)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.addFormVersion(userId, form, version);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			copyFormVersion(
				long userId, long sourceFormVersionId,
				io.github.valtairnascimento.campaignforms.model.Form targetForm,
				int version)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.copyFormVersion(
			userId, sourceFormVersionId, targetForm, version);
	}

	/**
	 * Creates a new form version with the primary key. Does not add the form version to the database.
	 *
	 * @param formVersionId the primary key for the new form version
	 * @return the new form version
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
		createFormVersion(long formVersionId) {

		return _formVersionLocalService.createFormVersion(formVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the form version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formVersion the form version
	 * @return the form version that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
		deleteFormVersion(
			io.github.valtairnascimento.campaignforms.model.FormVersion
				formVersion) {

		return _formVersionLocalService.deleteFormVersion(formVersion);
	}

	/**
	 * Deletes the form version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version that was removed
	 * @throws PortalException if a form version with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			deleteFormVersion(long formVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.deleteFormVersion(formVersionId);
	}

	@Override
	public void deleteFormVersions(long formId) {
		_formVersionLocalService.deleteFormVersions(formId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _formVersionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _formVersionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formVersionLocalService.dynamicQuery();
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

		return _formVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormVersionModelImpl</code>.
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

		return _formVersionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormVersionModelImpl</code>.
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

		return _formVersionLocalService.dynamicQuery(
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

		return _formVersionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _formVersionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
		fetchFormVersion(long formVersionId) {

		return _formVersionLocalService.fetchFormVersion(formVersionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _formVersionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the form version with the primary key.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version
	 * @throws PortalException if a form version with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			getFormVersion(long formVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.getFormVersion(formVersionId);
	}

	/**
	 * Returns a range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of form versions
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormVersion>
			getFormVersions(int start, int end) {

		return _formVersionLocalService.getFormVersions(start, end);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormVersion>
			getFormVersions(long formId) {

		return _formVersionLocalService.getFormVersions(formId);
	}

	/**
	 * Returns the number of form versions.
	 *
	 * @return the number of form versions
	 */
	@Override
	public int getFormVersionsCount() {
		return _formVersionLocalService.getFormVersionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _formVersionLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public int getNextVersion(long formId) {
		return _formVersionLocalService.getNextVersion(formId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _formVersionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the form version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formVersion the form version
	 * @return the form version that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
		updateFormVersion(
			io.github.valtairnascimento.campaignforms.model.FormVersion
				formVersion) {

		return _formVersionLocalService.updateFormVersion(formVersion);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			updateFormVersion(
				long formVersionId,
				io.github.valtairnascimento.campaignforms.input.FormVersionInput
					formVersionInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.updateFormVersion(
			formVersionId, formVersionInput);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			updateStatus(
				long userId, long formVersionId,
				io.github.valtairnascimento.campaignforms.constants.
					FormVersionStatus status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formVersionLocalService.updateStatus(
			userId, formVersionId, status);
	}

	@Override
	public FormVersionLocalService getWrappedService() {
		return _formVersionLocalService;
	}

	@Override
	public void setWrappedService(
		FormVersionLocalService formVersionLocalService) {

		_formVersionLocalService = formVersionLocalService;
	}

	private FormVersionLocalService _formVersionLocalService;

}