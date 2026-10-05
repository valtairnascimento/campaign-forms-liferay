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
 * Provides a wrapper for {@link SubmissionValueLocalService}.
 *
 * @author Valtair Nascimento
 * @see SubmissionValueLocalService
 * @generated
 */
public class SubmissionValueLocalServiceWrapper
	implements ServiceWrapper<SubmissionValueLocalService>,
			   SubmissionValueLocalService {

	public SubmissionValueLocalServiceWrapper() {
		this(null);
	}

	public SubmissionValueLocalServiceWrapper(
		SubmissionValueLocalService submissionValueLocalService) {

		_submissionValueLocalService = submissionValueLocalService;
	}

	/**
	 * Adds the submission value to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubmissionValueLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param submissionValue the submission value
	 * @return the submission value that was added
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
		addSubmissionValue(
			io.github.valtairnascimento.campaignforms.model.SubmissionValue
				submissionValue) {

		return _submissionValueLocalService.addSubmissionValue(submissionValue);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionValueLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new submission value with the primary key. Does not add the submission value to the database.
	 *
	 * @param submissionValueId the primary key for the new submission value
	 * @return the new submission value
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
		createSubmissionValue(long submissionValueId) {

		return _submissionValueLocalService.createSubmissionValue(
			submissionValueId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionValueLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the submission value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubmissionValueLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value that was removed
	 * @throws PortalException if a submission value with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
			deleteSubmissionValue(long submissionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionValueLocalService.deleteSubmissionValue(
			submissionValueId);
	}

	/**
	 * Deletes the submission value from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubmissionValueLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param submissionValue the submission value
	 * @return the submission value that was removed
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
		deleteSubmissionValue(
			io.github.valtairnascimento.campaignforms.model.SubmissionValue
				submissionValue) {

		return _submissionValueLocalService.deleteSubmissionValue(
			submissionValue);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _submissionValueLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _submissionValueLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _submissionValueLocalService.dynamicQuery();
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

		return _submissionValueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.SubmissionValueModelImpl</code>.
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

		return _submissionValueLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.SubmissionValueModelImpl</code>.
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

		return _submissionValueLocalService.dynamicQuery(
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

		return _submissionValueLocalService.dynamicQueryCount(dynamicQuery);
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

		return _submissionValueLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
		fetchSubmissionValue(long submissionValueId) {

		return _submissionValueLocalService.fetchSubmissionValue(
			submissionValueId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _submissionValueLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.SubmissionValue>
			getCampaignSubmissionValues(
				long campaignId, String fieldKey, String stringValue) {

		return _submissionValueLocalService.getCampaignSubmissionValues(
			campaignId, fieldKey, stringValue);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _submissionValueLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _submissionValueLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionValueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the submission value with the primary key.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value
	 * @throws PortalException if a submission value with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
			getSubmissionValue(long submissionValueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _submissionValueLocalService.getSubmissionValue(
			submissionValueId);
	}

	/**
	 * Returns a range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of submission values
	 */
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.SubmissionValue>
			getSubmissionValues(int start, int end) {

		return _submissionValueLocalService.getSubmissionValues(start, end);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.SubmissionValue>
			getSubmissionValues(long submissionId) {

		return _submissionValueLocalService.getSubmissionValues(submissionId);
	}

	/**
	 * Returns the number of submission values.
	 *
	 * @return the number of submission values
	 */
	@Override
	public int getSubmissionValuesCount() {
		return _submissionValueLocalService.getSubmissionValuesCount();
	}

	/**
	 * Updates the submission value in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SubmissionValueLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param submissionValue the submission value
	 * @return the submission value that was updated
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.SubmissionValue
		updateSubmissionValue(
			io.github.valtairnascimento.campaignforms.model.SubmissionValue
				submissionValue) {

		return _submissionValueLocalService.updateSubmissionValue(
			submissionValue);
	}

	@Override
	public SubmissionValueLocalService getWrappedService() {
		return _submissionValueLocalService;
	}

	@Override
	public void setWrappedService(
		SubmissionValueLocalService submissionValueLocalService) {

		_submissionValueLocalService = submissionValueLocalService;
	}

	private SubmissionValueLocalService _submissionValueLocalService;

}