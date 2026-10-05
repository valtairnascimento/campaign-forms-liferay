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

import io.github.valtairnascimento.campaignforms.model.FormFieldOption;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for FormFieldOption. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.FormFieldOptionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see FormFieldOptionLocalService
 * @generated
 */
public class FormFieldOptionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.FormFieldOptionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static FormFieldOption addFormFieldOption(
		FormFieldOption formFieldOption) {

		return getService().addFormFieldOption(formFieldOption);
	}

	public static List<FormFieldOption> copyFormFieldOptions(
		long sourceFormFieldId,
		io.github.valtairnascimento.campaignforms.model.FormField
			targetFormField) {

		return getService().copyFormFieldOptions(
			sourceFormFieldId, targetFormField);
	}

	/**
	 * Creates a new form field option with the primary key. Does not add the form field option to the database.
	 *
	 * @param formFieldOptionId the primary key for the new form field option
	 * @return the new form field option
	 */
	public static FormFieldOption createFormFieldOption(
		long formFieldOptionId) {

		return getService().createFormFieldOption(formFieldOptionId);
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
	 * Deletes the form field option from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FormFieldOptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formFieldOption the form field option
	 * @return the form field option that was removed
	 */
	public static FormFieldOption deleteFormFieldOption(
		FormFieldOption formFieldOption) {

		return getService().deleteFormFieldOption(formFieldOption);
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
	public static FormFieldOption deleteFormFieldOption(long formFieldOptionId)
		throws PortalException {

		return getService().deleteFormFieldOption(formFieldOptionId);
	}

	public static void deleteFormFieldOptions(long formFieldId) {
		getService().deleteFormFieldOptions(formFieldId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl</code>.
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

	public static FormFieldOption fetchFormFieldOption(long formFieldOptionId) {
		return getService().fetchFormFieldOption(formFieldOptionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the form field option with the primary key.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option
	 * @throws PortalException if a form field option with the primary key could not be found
	 */
	public static FormFieldOption getFormFieldOption(long formFieldOptionId)
		throws PortalException {

		return getService().getFormFieldOption(formFieldOptionId);
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
	public static List<FormFieldOption> getFormFieldOptions(
		int start, int end) {

		return getService().getFormFieldOptions(start, end);
	}

	public static List<FormFieldOption> getFormFieldOptions(long formFieldId) {
		return getService().getFormFieldOptions(formFieldId);
	}

	/**
	 * Returns the number of form field options.
	 *
	 * @return the number of form field options
	 */
	public static int getFormFieldOptionsCount() {
		return getService().getFormFieldOptionsCount();
	}

	public static Map<Long, List<FormFieldOption>> getFormFieldOptionsMap(
		long[] formFieldIds) {

		return getService().getFormFieldOptionsMap(formFieldIds);
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
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<FormFieldOption> setFormFieldOptions(
			io.github.valtairnascimento.campaignforms.model.FormField formField,
			List
				<io.github.valtairnascimento.campaignforms.input.
					FieldOptionInput> fieldOptionInputs)
		throws PortalException {

		return getService().setFormFieldOptions(formField, fieldOptionInputs);
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
	public static FormFieldOption updateFormFieldOption(
		FormFieldOption formFieldOption) {

		return getService().updateFormFieldOption(formFieldOption);
	}

	public static FormFieldOptionLocalService getService() {
		return _service;
	}

	private static volatile FormFieldOptionLocalService _service;

}