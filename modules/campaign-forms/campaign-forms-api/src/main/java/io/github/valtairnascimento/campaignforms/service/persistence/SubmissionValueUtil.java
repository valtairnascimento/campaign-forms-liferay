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

import io.github.valtairnascimento.campaignforms.model.SubmissionValue;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the submission value service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.SubmissionValuePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see SubmissionValuePersistence
 * @generated
 */
public class SubmissionValueUtil {

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
	public static void clearCache(SubmissionValue submissionValue) {
		getPersistence().clearCache(submissionValue);
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
	public static Map<Serializable, SubmissionValue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SubmissionValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SubmissionValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SubmissionValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SubmissionValue update(SubmissionValue submissionValue) {
		return getPersistence().update(submissionValue);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SubmissionValue update(
		SubmissionValue submissionValue, ServiceContext serviceContext) {

		return getPersistence().update(submissionValue, serviceContext);
	}

	/**
	 * Returns all the submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching submission values
	 */
	public static List<SubmissionValue> findBySubmissionId(long submissionId) {
		return getPersistence().findBySubmissionId(submissionId);
	}

	/**
	 * Returns a range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of matching submission values
	 */
	public static List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end) {

		return getPersistence().findBySubmissionId(submissionId, start, end);
	}

	/**
	 * Returns an ordered range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submission values
	 */
	public static List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().findBySubmissionId(
			submissionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submission values
	 */
	public static List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBySubmissionId(
			submissionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public static SubmissionValue findBySubmissionId_First(
			long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findBySubmissionId_First(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public static SubmissionValue fetchBySubmissionId_First(
		long submissionId,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().fetchBySubmissionId_First(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public static SubmissionValue findBySubmissionId_Last(
			long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findBySubmissionId_Last(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public static SubmissionValue fetchBySubmissionId_Last(
		long submissionId,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().fetchBySubmissionId_Last(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the submission values before and after the current submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionValueId the primary key of the current submission value
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public static SubmissionValue[] findBySubmissionId_PrevAndNext(
			long submissionValueId, long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findBySubmissionId_PrevAndNext(
			submissionValueId, submissionId, orderByComparator);
	}

	/**
	 * Removes all the submission values where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	public static void removeBySubmissionId(long submissionId) {
		getPersistence().removeBySubmissionId(submissionId);
	}

	/**
	 * Returns the number of submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching submission values
	 */
	public static int countBySubmissionId(long submissionId) {
		return getPersistence().countBySubmissionId(submissionId);
	}

	/**
	 * Returns all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the matching submission values
	 */
	public static List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		return getPersistence().findByC_FK_SV(
			campaignId, fieldKey, stringValue);
	}

	/**
	 * Returns a range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of matching submission values
	 */
	public static List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end) {

		return getPersistence().findByC_FK_SV(
			campaignId, fieldKey, stringValue, start, end);
	}

	/**
	 * Returns an ordered range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submission values
	 */
	public static List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end, OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().findByC_FK_SV(
			campaignId, fieldKey, stringValue, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submission values
	 */
	public static List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end, OrderByComparator<SubmissionValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_FK_SV(
			campaignId, fieldKey, stringValue, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public static SubmissionValue findByC_FK_SV_First(
			long campaignId, String fieldKey, String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findByC_FK_SV_First(
			campaignId, fieldKey, stringValue, orderByComparator);
	}

	/**
	 * Returns the first submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public static SubmissionValue fetchByC_FK_SV_First(
		long campaignId, String fieldKey, String stringValue,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().fetchByC_FK_SV_First(
			campaignId, fieldKey, stringValue, orderByComparator);
	}

	/**
	 * Returns the last submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public static SubmissionValue findByC_FK_SV_Last(
			long campaignId, String fieldKey, String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findByC_FK_SV_Last(
			campaignId, fieldKey, stringValue, orderByComparator);
	}

	/**
	 * Returns the last submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public static SubmissionValue fetchByC_FK_SV_Last(
		long campaignId, String fieldKey, String stringValue,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().fetchByC_FK_SV_Last(
			campaignId, fieldKey, stringValue, orderByComparator);
	}

	/**
	 * Returns the submission values before and after the current submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param submissionValueId the primary key of the current submission value
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public static SubmissionValue[] findByC_FK_SV_PrevAndNext(
			long submissionValueId, long campaignId, String fieldKey,
			String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findByC_FK_SV_PrevAndNext(
			submissionValueId, campaignId, fieldKey, stringValue,
			orderByComparator);
	}

	/**
	 * Removes all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 */
	public static void removeByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		getPersistence().removeByC_FK_SV(campaignId, fieldKey, stringValue);
	}

	/**
	 * Returns the number of submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the number of matching submission values
	 */
	public static int countByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		return getPersistence().countByC_FK_SV(
			campaignId, fieldKey, stringValue);
	}

	/**
	 * Caches the submission value in the entity cache if it is enabled.
	 *
	 * @param submissionValue the submission value
	 */
	public static void cacheResult(SubmissionValue submissionValue) {
		getPersistence().cacheResult(submissionValue);
	}

	/**
	 * Caches the submission values in the entity cache if it is enabled.
	 *
	 * @param submissionValues the submission values
	 */
	public static void cacheResult(List<SubmissionValue> submissionValues) {
		getPersistence().cacheResult(submissionValues);
	}

	/**
	 * Creates a new submission value with the primary key. Does not add the submission value to the database.
	 *
	 * @param submissionValueId the primary key for the new submission value
	 * @return the new submission value
	 */
	public static SubmissionValue create(long submissionValueId) {
		return getPersistence().create(submissionValueId);
	}

	/**
	 * Removes the submission value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value that was removed
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public static SubmissionValue remove(long submissionValueId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().remove(submissionValueId);
	}

	public static SubmissionValue updateImpl(SubmissionValue submissionValue) {
		return getPersistence().updateImpl(submissionValue);
	}

	/**
	 * Returns the submission value with the primary key or throws a <code>NoSuchSubmissionValueException</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public static SubmissionValue findByPrimaryKey(long submissionValueId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionValueException {

		return getPersistence().findByPrimaryKey(submissionValueId);
	}

	/**
	 * Returns the submission value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value, or <code>null</code> if a submission value with the primary key could not be found
	 */
	public static SubmissionValue fetchByPrimaryKey(long submissionValueId) {
		return getPersistence().fetchByPrimaryKey(submissionValueId);
	}

	/**
	 * Returns all the submission values.
	 *
	 * @return the submission values
	 */
	public static List<SubmissionValue> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of submission values
	 */
	public static List<SubmissionValue> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of submission values
	 */
	public static List<SubmissionValue> findAll(
		int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of submission values
	 */
	public static List<SubmissionValue> findAll(
		int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the submission values from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of submission values.
	 *
	 * @return the number of submission values
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SubmissionValuePersistence getPersistence() {
		return _persistence;
	}

	private static volatile SubmissionValuePersistence _persistence;

}