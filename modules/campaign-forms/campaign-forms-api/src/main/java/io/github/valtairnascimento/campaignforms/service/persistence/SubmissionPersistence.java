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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import io.github.valtairnascimento.campaignforms.exception.NoSuchSubmissionException;
import io.github.valtairnascimento.campaignforms.model.Submission;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the submission service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see SubmissionUtil
 * @generated
 */
@ProviderType
public interface SubmissionPersistence extends BasePersistence<Submission> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubmissionUtil} to access the submission persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the submissions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByUuid(String uuid);

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
	public java.util.List<Submission> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<Submission> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the submissions before and after the current submission in the ordered set where uuid = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission[] findByUuid_PrevAndNext(
			long submissionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of submissions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching submissions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByUUID_G(String uuid, long groupId)
		throws NoSuchSubmissionException;

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the submission where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the submission where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the submission that was removed
	 */
	public Submission removeByUUID_G(String uuid, long groupId)
		throws NoSuchSubmissionException;

	/**
	 * Returns the number of submissions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching submissions
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public Submission[] findByUuid_C_PrevAndNext(
			long submissionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of submissions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching submissions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the submissions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByGroupId(long groupId);

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
	public java.util.List<Submission> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<Submission> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the submissions before and after the current submission in the ordered set where groupId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission[] findByGroupId_PrevAndNext(
			long submissionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of submissions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching submissions
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the submissions where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByCampaignId(long campaignId);

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
	public java.util.List<Submission> findByCampaignId(
		long campaignId, int start, int end);

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
	public java.util.List<Submission> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByCampaignId_First(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByCampaignId_Last(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the submissions before and after the current submission in the ordered set where campaignId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission[] findByCampaignId_PrevAndNext(
			long submissionId, long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(long campaignId);

	/**
	 * Returns the number of submissions where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching submissions
	 */
	public int countByCampaignId(long campaignId);

	/**
	 * Returns all the submissions where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByC_S(long campaignId, String status);

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
	public java.util.List<Submission> findByC_S(
		long campaignId, String status, int start, int end);

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
	public java.util.List<Submission> findByC_S(
		long campaignId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByC_S(
		long campaignId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByC_S_First(
			long campaignId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByC_S_First(
		long campaignId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByC_S_Last(
			long campaignId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByC_S_Last(
		long campaignId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public Submission[] findByC_S_PrevAndNext(
			long submissionId, long campaignId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where campaignId = &#63; and status = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 */
	public void removeByC_S(long campaignId, String status);

	/**
	 * Returns the number of submissions where campaignId = &#63; and status = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param status the status
	 * @return the number of matching submissions
	 */
	public int countByC_S(long campaignId, String status);

	/**
	 * Returns all the submissions where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the matching submissions
	 */
	public java.util.List<Submission> findByFormVersionId(long formVersionId);

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
	public java.util.List<Submission> findByFormVersionId(
		long formVersionId, int start, int end);

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
	public java.util.List<Submission> findByFormVersionId(
		long formVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findByFormVersionId(
		long formVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByFormVersionId_First(
			long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the first submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByFormVersionId_First(
		long formVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the last submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByFormVersionId_Last(
			long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Returns the last submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByFormVersionId_Last(
		long formVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

	/**
	 * Returns the submissions before and after the current submission in the ordered set where formVersionId = &#63;.
	 *
	 * @param submissionId the primary key of the current submission
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission[] findByFormVersionId_PrevAndNext(
			long submissionId, long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<Submission>
				orderByComparator)
		throws NoSuchSubmissionException;

	/**
	 * Removes all the submissions where formVersionId = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 */
	public void removeByFormVersionId(long formVersionId);

	/**
	 * Returns the number of submissions where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the number of matching submissions
	 */
	public int countByFormVersionId(long formVersionId);

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the matching submission
	 * @throws NoSuchSubmissionException if a matching submission could not be found
	 */
	public Submission findByC_IK(long campaignId, String idempotencyKey)
		throws NoSuchSubmissionException;

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByC_IK(long campaignId, String idempotencyKey);

	/**
	 * Returns the submission where campaignId = &#63; and idempotencyKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching submission, or <code>null</code> if a matching submission could not be found
	 */
	public Submission fetchByC_IK(
		long campaignId, String idempotencyKey, boolean useFinderCache);

	/**
	 * Removes the submission where campaignId = &#63; and idempotencyKey = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the submission that was removed
	 */
	public Submission removeByC_IK(long campaignId, String idempotencyKey)
		throws NoSuchSubmissionException;

	/**
	 * Returns the number of submissions where campaignId = &#63; and idempotencyKey = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param idempotencyKey the idempotency key
	 * @return the number of matching submissions
	 */
	public int countByC_IK(long campaignId, String idempotencyKey);

	/**
	 * Caches the submission in the entity cache if it is enabled.
	 *
	 * @param submission the submission
	 */
	public void cacheResult(Submission submission);

	/**
	 * Caches the submissions in the entity cache if it is enabled.
	 *
	 * @param submissions the submissions
	 */
	public void cacheResult(java.util.List<Submission> submissions);

	/**
	 * Creates a new submission with the primary key. Does not add the submission to the database.
	 *
	 * @param submissionId the primary key for the new submission
	 * @return the new submission
	 */
	public Submission create(long submissionId);

	/**
	 * Removes the submission with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission that was removed
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission remove(long submissionId)
		throws NoSuchSubmissionException;

	public Submission updateImpl(Submission submission);

	/**
	 * Returns the submission with the primary key or throws a <code>NoSuchSubmissionException</code> if it could not be found.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission
	 * @throws NoSuchSubmissionException if a submission with the primary key could not be found
	 */
	public Submission findByPrimaryKey(long submissionId)
		throws NoSuchSubmissionException;

	/**
	 * Returns the submission with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param submissionId the primary key of the submission
	 * @return the submission, or <code>null</code> if a submission with the primary key could not be found
	 */
	public Submission fetchByPrimaryKey(long submissionId);

	/**
	 * Returns all the submissions.
	 *
	 * @return the submissions
	 */
	public java.util.List<Submission> findAll();

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
	public java.util.List<Submission> findAll(int start, int end);

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
	public java.util.List<Submission> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator);

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
	public java.util.List<Submission> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Submission>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the submissions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of submissions.
	 *
	 * @return the number of submissions
	 */
	public int countAll();

}