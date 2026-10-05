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

import io.github.valtairnascimento.campaignforms.model.Submission;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the submission service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.SubmissionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see SubmissionPersistence
 * @generated
 */
public class SubmissionUtil {

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
	public static void clearCache(Submission submission) {
		getPersistence().clearCache(submission);
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
	public static Map<Serializable, Submission> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Submission> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Submission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Submission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Submission update(Submission submission) {
		return getPersistence().update(submission);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Submission update(
		Submission submission, ServiceContext serviceContext) {

		return getPersistence().update(submission, serviceContext);
	}

	/**
	 * Returns all the submissions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching submissions
	 */
	public static List<Submission> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the submissions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByUuid_First(
			String uuid, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUuid_First(
		String uuid, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByUuid_Last(
			String uuid, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUuid_Last(
		String uuid, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where uuid = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByUuid_PrevAndNext(
			long submissionId, String uuid,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_PrevAndNext(
			submissionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the submissions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of submissions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching submissions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByUUID_G(String uuid, long groupId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the submission where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the submission that was removed
	 */
	public static Submission removeByUUID_G(String uuid, long groupId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of submissions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching submissions
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching submissions
	 */
	public static List<Submission> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByUuid_C_PrevAndNext(
			long submissionId, String uuid, long companyId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			submissionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the submissions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching submissions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the submissions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching submissions
	 */
	public static List<Submission> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the submissions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByGroupId_First(
			long groupId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByGroupId_First(
		long groupId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByGroupId_Last(
			long groupId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByGroupId_Last(
		long groupId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where groupId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByGroupId_PrevAndNext(
			long submissionId, long groupId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByGroupId_PrevAndNext(
			submissionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the submissions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of submissions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching submissions
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the submissions where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching submissions
	 */
	public static List<Submission> findByCampaignId(long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

	/**
	 * Returns a range of all the submissions where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByCampaignId(
		long campaignId, int start, int end) {

		return getPersistence().findByCampaignId(campaignId, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByCampaignId_First(
			long campaignId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByCampaignId_First(
		long campaignId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByCampaignId_Last(
			long campaignId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByCampaignId_Last(
		long campaignId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where campaignId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByCampaignId_PrevAndNext(
			long submissionId, long campaignId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByCampaignId_PrevAndNext(
			submissionId, campaignId, orderByComparator);
	}

	/**
	 * Removes all the submissions where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public static void removeByCampaignId(long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	 * Returns the number of submissions where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching submissions
	 */
	public static int countByCampaignId(long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	 * Returns all the submissions where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @return the matching submissions
	 */
	public static List<Submission> findByC_S(long campaignId, String status) {
		return getPersistence().findByC_S(campaignId, status);
	}

	/**
	 * Returns a range of all the submissions where campaignId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByC_S(
		long campaignId, String status, int start, int end) {

		return getPersistence().findByC_S(campaignId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where campaignId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByC_S(
		long campaignId, String status, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByC_S(
			campaignId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where campaignId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByC_S(
		long campaignId, String status, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_S(
			campaignId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByC_S_First(
			long campaignId, String status,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByC_S_First(
			campaignId, status, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByC_S_First(
		long campaignId, String status,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByC_S_First(
			campaignId, status, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByC_S_Last(
			long campaignId, String status,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByC_S_Last(
			campaignId, status, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByC_S_Last(
		long campaignId, String status,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByC_S_Last(
			campaignId, status, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByC_S_PrevAndNext(
			long submissionId, long campaignId, String status,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByC_S_PrevAndNext(
			submissionId, campaignId, status, orderByComparator);
	}

	/**
	 * Removes all the submissions where campaignId = &#63; and status = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 */
	public static void removeByC_S(long campaignId, String status) {
		getPersistence().removeByC_S(campaignId, status);
	}

	/**
	 * Returns the number of submissions where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @return the number of matching submissions
	 */
	public static int countByC_S(long campaignId, String status) {
		return getPersistence().countByC_S(campaignId, status);
	}

	/**
	 * Returns all the submissions where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the matching submissions
	 */
	public static List<Submission> findByFormVersionId(long formVersionId) {
		return getPersistence().findByFormVersionId(formVersionId);
	}

	/**
	 * Returns a range of all the submissions where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of matching submissions
	 */
	public static List<Submission> findByFormVersionId(
		long formVersionId, int start, int end) {

		return getPersistence().findByFormVersionId(formVersionId, start, end);
	}

	/**
	 * Returns an ordered range of all the submissions where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByFormVersionId(
		long formVersionId, int start, int end,
		OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findByFormVersionId(
			formVersionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submissions
	 */
	public static List<Submission> findByFormVersionId(
		long formVersionId, int start, int end,
		OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFormVersionId(
			formVersionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByFormVersionId_First(
			long formVersionId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByFormVersionId_First(
			formVersionId, orderByComparator);
	}

	/**
	 * Returns the first submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByFormVersionId_First(
		long formVersionId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByFormVersionId_First(
			formVersionId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByFormVersionId_Last(
			long formVersionId, OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByFormVersionId_Last(
			formVersionId, orderByComparator);
	}

	/**
	 * Returns the last submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByFormVersionId_Last(
		long formVersionId, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().fetchByFormVersionId_Last(
			formVersionId, orderByComparator);
	}

	/**
	 * Returns the submissions before and after the current submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission[] findByFormVersionId_PrevAndNext(
			long submissionId, long formVersionId,
			OrderByComparator<Submission> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByFormVersionId_PrevAndNext(
			submissionId, formVersionId, orderByComparator);
	}

	/**
	 * Removes all the submissions where formVersionId = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 */
	public static void removeByFormVersionId(long formVersionId) {
		getPersistence().removeByFormVersionId(formVersionId);
	}

	/**
	 * Returns the number of submissions where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the number of matching submissions
	 */
	public static int countByFormVersionId(long formVersionId) {
		return getPersistence().countByFormVersionId(formVersionId);
	}

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public static Submission findByC_IK(long campaignId, String idempotencyKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByC_IK(campaignId, idempotencyKey);
	}

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByC_IK(
		long campaignId, String idempotencyKey) {

		return getPersistence().fetchByC_IK(campaignId, idempotencyKey);
	}

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public static Submission fetchByC_IK(
		long campaignId, String idempotencyKey, boolean useFinderCache) {

		return getPersistence().fetchByC_IK(
			campaignId, idempotencyKey, useFinderCache);
	}

	/**
	 * Removes the submission where campaignId = &#63; and idempotencyKey = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the submission that was removed
	 */
	public static Submission removeByC_IK(
			long campaignId, String idempotencyKey)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().removeByC_IK(campaignId, idempotencyKey);
	}

	/**
	 * Returns the number of submissions where campaignId = &#63; and idempotencyKey = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the number of matching submissions
	 */
	public static int countByC_IK(long campaignId, String idempotencyKey) {
		return getPersistence().countByC_IK(campaignId, idempotencyKey);
	}

	/**
	 * Caches the submission in the entity cache if it is enabled.
	 *
	 * @param submission the submission
	 */
	public static void cacheResult(Submission submission) {
		getPersistence().cacheResult(submission);
	}

	/**
	 * Caches the submissions in the entity cache if it is enabled.
	 *
	 * @param submissions the submissions
	 */
	public static void cacheResult(List<Submission> submissions) {
		getPersistence().cacheResult(submissions);
	}

	/**
	 * Creates a new submission with the primary key. Does not add the submission to the database.
	 *
	 * @param submissionId the primary key for the new submission
	 * @return the new submission
	 */
	public static Submission create(long submissionId) {
		return getPersistence().create(submissionId);
	}

	/**
	 * Removes the submission with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission that was removed
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission remove(long submissionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().remove(submissionId);
	}

	public static Submission updateImpl(Submission submission) {
		return getPersistence().updateImpl(submission);
	}

	/**
	 * Returns the submission with the primary key or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public static Submission findByPrimaryKey(long submissionId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchSubmissionException {

		return getPersistence().findByPrimaryKey(submissionId);
	}

	/**
	 * Returns the submission with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission, or <code>null</code> if a submission with the primary key could not be found
	 */
	public static Submission fetchByPrimaryKey(long submissionId) {
		return getPersistence().fetchByPrimaryKey(submissionId);
	}

	/**
	 * Returns all the submissions.
	 *
	 * @return the submissions
	 */
	public static List<Submission> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the submissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @return the range of submissions
	 */
	public static List<Submission> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the submissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of submissions
	 */
	public static List<Submission> findAll(
		int start, int end, OrderByComparator<Submission> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the submissions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submissions
	 * @param end the upper bound of the range of submissions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of submissions
	 */
	public static List<Submission> findAll(
		int start, int end, OrderByComparator<Submission> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the submissions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of submissions.
	 *
	 * @return the number of submissions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SubmissionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SubmissionPersistence _persistence;

}