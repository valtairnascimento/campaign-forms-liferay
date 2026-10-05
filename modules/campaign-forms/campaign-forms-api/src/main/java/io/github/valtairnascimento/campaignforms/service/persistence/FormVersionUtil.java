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

import io.github.valtairnascimento.campaignforms.model.FormVersion;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the form version service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.FormVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormVersionPersistence
 * @generated
 */
public class FormVersionUtil {

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
	public static void clearCache(FormVersion formVersion) {
		getPersistence().clearCache(formVersion);
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
	public static Map<Serializable, FormVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FormVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FormVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FormVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FormVersion update(FormVersion formVersion) {
		return getPersistence().update(formVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FormVersion update(
		FormVersion formVersion, ServiceContext serviceContext) {

		return getPersistence().update(formVersion, serviceContext);
	}

	/**
	 * Returns all the form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the matching form versions
	 */
	public static List<FormVersion> findByFormId(long formId) {
		return getPersistence().findByFormId(formId);
	}

	/**
	 * Returns a range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of matching form versions
	 */
	public static List<FormVersion> findByFormId(
		long formId, int start, int end) {

		return getPersistence().findByFormId(formId, start, end);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form versions
	 */
	public static List<FormVersion> findByFormId(
		long formId, int start, int end,
		OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().findByFormId(
			formId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form versions
	 */
	public static List<FormVersion> findByFormId(
		long formId, int start, int end,
		OrderByComparator<FormVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFormId(
			formId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public static FormVersion findByFormId_First(
			long formId, OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByFormId_First(formId, orderByComparator);
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByFormId_First(
		long formId, OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().fetchByFormId_First(formId, orderByComparator);
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public static FormVersion findByFormId_Last(
			long formId, OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByFormId_Last(formId, orderByComparator);
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByFormId_Last(
		long formId, OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().fetchByFormId_Last(formId, orderByComparator);
	}

	/**
	 * Returns the form versions before and after the current form version in the ordered set where formId = &#63;.
	 *
	 * @param formVersionId the primary key of the current form version
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public static FormVersion[] findByFormId_PrevAndNext(
			long formVersionId, long formId,
			OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByFormId_PrevAndNext(
			formVersionId, formId, orderByComparator);
	}

	/**
	 * Removes all the form versions where formId = &#63; from the database.
	 *
	 * @param formId the form ID
	 */
	public static void removeByFormId(long formId) {
		getPersistence().removeByFormId(formId);
	}

	/**
	 * Returns the number of form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the number of matching form versions
	 */
	public static int countByFormId(long formId) {
		return getPersistence().countByFormId(formId);
	}

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public static FormVersion findByF_V(long formId, int version)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByF_V(formId, version);
	}

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByF_V(long formId, int version) {
		return getPersistence().fetchByF_V(formId, version);
	}

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByF_V(
		long formId, int version, boolean useFinderCache) {

		return getPersistence().fetchByF_V(formId, version, useFinderCache);
	}

	/**
	 * Removes the form version where formId = &#63; and version = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the form version that was removed
	 */
	public static FormVersion removeByF_V(long formId, int version)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().removeByF_V(formId, version);
	}

	/**
	 * Returns the number of form versions where formId = &#63; and version = &#63;.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the number of matching form versions
	 */
	public static int countByF_V(long formId, int version) {
		return getPersistence().countByF_V(formId, version);
	}

	/**
	 * Returns all the form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the matching form versions
	 */
	public static List<FormVersion> findByF_S(long formId, String status) {
		return getPersistence().findByF_S(formId, status);
	}

	/**
	 * Returns a range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of matching form versions
	 */
	public static List<FormVersion> findByF_S(
		long formId, String status, int start, int end) {

		return getPersistence().findByF_S(formId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form versions
	 */
	public static List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().findByF_S(
			formId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form versions
	 */
	public static List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		OrderByComparator<FormVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByF_S(
			formId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public static FormVersion findByF_S_First(
			long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByF_S_First(
			formId, status, orderByComparator);
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByF_S_First(
		long formId, String status,
		OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().fetchByF_S_First(
			formId, status, orderByComparator);
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public static FormVersion findByF_S_Last(
			long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByF_S_Last(
			formId, status, orderByComparator);
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public static FormVersion fetchByF_S_Last(
		long formId, String status,
		OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().fetchByF_S_Last(
			formId, status, orderByComparator);
	}

	/**
	 * Returns the form versions before and after the current form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formVersionId the primary key of the current form version
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public static FormVersion[] findByF_S_PrevAndNext(
			long formVersionId, long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByF_S_PrevAndNext(
			formVersionId, formId, status, orderByComparator);
	}

	/**
	 * Removes all the form versions where formId = &#63; and status = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param status the status
	 */
	public static void removeByF_S(long formId, String status) {
		getPersistence().removeByF_S(formId, status);
	}

	/**
	 * Returns the number of form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the number of matching form versions
	 */
	public static int countByF_S(long formId, String status) {
		return getPersistence().countByF_S(formId, status);
	}

	/**
	 * Caches the form version in the entity cache if it is enabled.
	 *
	 * @param formVersion the form version
	 */
	public static void cacheResult(FormVersion formVersion) {
		getPersistence().cacheResult(formVersion);
	}

	/**
	 * Caches the form versions in the entity cache if it is enabled.
	 *
	 * @param formVersions the form versions
	 */
	public static void cacheResult(List<FormVersion> formVersions) {
		getPersistence().cacheResult(formVersions);
	}

	/**
	 * Creates a new form version with the primary key. Does not add the form version to the database.
	 *
	 * @param formVersionId the primary key for the new form version
	 * @return the new form version
	 */
	public static FormVersion create(long formVersionId) {
		return getPersistence().create(formVersionId);
	}

	/**
	 * Removes the form version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version that was removed
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public static FormVersion remove(long formVersionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().remove(formVersionId);
	}

	public static FormVersion updateImpl(FormVersion formVersion) {
		return getPersistence().updateImpl(formVersion);
	}

	/**
	 * Returns the form version with the primary key or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public static FormVersion findByPrimaryKey(long formVersionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchFormVersionException {

		return getPersistence().findByPrimaryKey(formVersionId);
	}

	/**
	 * Returns the form version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version, or <code>null</code> if a form version with the primary key could not be found
	 */
	public static FormVersion fetchByPrimaryKey(long formVersionId) {
		return getPersistence().fetchByPrimaryKey(formVersionId);
	}

	/**
	 * Returns all the form versions.
	 *
	 * @return the form versions
	 */
	public static List<FormVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of form versions
	 */
	public static List<FormVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form versions
	 */
	public static List<FormVersion> findAll(
		int start, int end, OrderByComparator<FormVersion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of form versions
	 */
	public static List<FormVersion> findAll(
		int start, int end, OrderByComparator<FormVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the form versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of form versions.
	 *
	 * @return the number of form versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FormVersionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FormVersionPersistence _persistence;

}