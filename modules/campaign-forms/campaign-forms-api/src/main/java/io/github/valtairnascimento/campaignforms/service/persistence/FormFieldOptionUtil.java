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

import io.github.valtairnascimento.campaignforms.model.FormFieldOption;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the form field option service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.FormFieldOptionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormFieldOptionPersistence
 * @generated
 */
public class FormFieldOptionUtil {

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
	public static void clearCache(FormFieldOption formFieldOption) {
		getPersistence().clearCache(formFieldOption);
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
	public static Map<Serializable, FormFieldOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FormFieldOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FormFieldOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FormFieldOption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FormFieldOption update(FormFieldOption formFieldOption) {
		return getPersistence().update(formFieldOption);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FormFieldOption update(
		FormFieldOption formFieldOption, ServiceContext serviceContext) {

		return getPersistence().update(formFieldOption, serviceContext);
	}

	/**
	 * Returns all the form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the matching form field options
	 */
	public static List<FormFieldOption> findByFormFieldId(long formFieldId) {
		return getPersistence().findByFormFieldId(formFieldId);
	}

	/**
	 * Returns a range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @return the range of matching form field options
	 */
	public static List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end) {

		return getPersistence().findByFormFieldId(formFieldId, start, end);
	}

	/**
	 * Returns an ordered range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form field options
	 */
	public static List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return getPersistence().findByFormFieldId(
			formFieldId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form field options
	 */
	public static List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFormFieldId(
			formFieldId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	public static FormFieldOption findByFormFieldId_First(
			long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormFieldOptionException {

		return getPersistence().findByFormFieldId_First(
			formFieldId, orderByComparator);
	}

	/**
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	public static FormFieldOption fetchByFormFieldId_First(
		long formFieldId,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return getPersistence().fetchByFormFieldId_First(
			formFieldId, orderByComparator);
	}

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	public static FormFieldOption findByFormFieldId_Last(
			long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormFieldOptionException {

		return getPersistence().findByFormFieldId_Last(
			formFieldId, orderByComparator);
	}

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	public static FormFieldOption fetchByFormFieldId_Last(
		long formFieldId,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return getPersistence().fetchByFormFieldId_Last(
			formFieldId, orderByComparator);
	}

	/**
	 * Returns the form field options before and after the current form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldOptionId the primary key of the current form field option
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public static FormFieldOption[] findByFormFieldId_PrevAndNext(
			long formFieldOptionId, long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormFieldOptionException {

		return getPersistence().findByFormFieldId_PrevAndNext(
			formFieldOptionId, formFieldId, orderByComparator);
	}

	/**
	 * Removes all the form field options where formFieldId = &#63; from the database.
	 *
	 * @param formFieldId the form field ID
	 */
	public static void removeByFormFieldId(long formFieldId) {
		getPersistence().removeByFormFieldId(formFieldId);
	}

	/**
	 * Returns the number of form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the number of matching form field options
	 */
	public static int countByFormFieldId(long formFieldId) {
		return getPersistence().countByFormFieldId(formFieldId);
	}

	/**
	 * Caches the form field option in the entity cache if it is enabled.
	 *
	 * @param formFieldOption the form field option
	 */
	public static void cacheResult(FormFieldOption formFieldOption) {
		getPersistence().cacheResult(formFieldOption);
	}

	/**
	 * Caches the form field options in the entity cache if it is enabled.
	 *
	 * @param formFieldOptions the form field options
	 */
	public static void cacheResult(List<FormFieldOption> formFieldOptions) {
		getPersistence().cacheResult(formFieldOptions);
	}

	/**
	 * Creates a new form field option with the primary key. Does not add the form field option to the database.
	 *
	 * @param formFieldOptionId the primary key for the new form field option
	 * @return the new form field option
	 */
	public static FormFieldOption create(long formFieldOptionId) {
		return getPersistence().create(formFieldOptionId);
	}

	/**
	 * Removes the form field option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option that was removed
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public static FormFieldOption remove(long formFieldOptionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormFieldOptionException {

		return getPersistence().remove(formFieldOptionId);
	}

	public static FormFieldOption updateImpl(FormFieldOption formFieldOption) {
		return getPersistence().updateImpl(formFieldOption);
	}

	/**
	 * Returns the form field option with the primary key or throws a <code>NoSuchFormFieldOptionException</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public static FormFieldOption findByPrimaryKey(long formFieldOptionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormFieldOptionException {

		return getPersistence().findByPrimaryKey(formFieldOptionId);
	}

	/**
	 * Returns the form field option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option, or <code>null</code> if a form field option with the primary key could not be found
	 */
	public static FormFieldOption fetchByPrimaryKey(long formFieldOptionId) {
		return getPersistence().fetchByPrimaryKey(formFieldOptionId);
	}

	/**
	 * Returns all the form field options.
	 *
	 * @return the form field options
	 */
	public static List<FormFieldOption> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @return the range of form field options
	 */
	public static List<FormFieldOption> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form field options
	 */
	public static List<FormFieldOption> findAll(
		int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of form field options
	 */
	public static List<FormFieldOption> findAll(
		int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the form field options from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of form field options.
	 *
	 * @return the number of form field options
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FormFieldOptionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FormFieldOptionPersistence _persistence;

}