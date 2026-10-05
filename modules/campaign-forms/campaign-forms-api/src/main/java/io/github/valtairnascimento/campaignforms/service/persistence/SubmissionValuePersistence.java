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

import io.github.valtairnascimento.campaignforms.exception.NoSuchSubmissionValueException;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the submission value service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see SubmissionValueUtil
 * @generated
 */
@ProviderType
public interface SubmissionValuePersistence
	extends BasePersistence<SubmissionValue> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubmissionValueUtil} to access the submission value persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching submission values
	 */
	public java.util.List<SubmissionValue> findBySubmissionId(
		long submissionId);

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
	public java.util.List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end);

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
	public java.util.List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

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
	public java.util.List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public SubmissionValue findBySubmissionId_First(
			long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public SubmissionValue fetchBySubmissionId_First(
		long submissionId,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	public SubmissionValue findBySubmissionId_Last(
			long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public SubmissionValue fetchBySubmissionId_Last(
		long submissionId,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

	/**
	 * Returns the submission values before and after the current submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionValueId the primary key of the current submission value
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public SubmissionValue[] findBySubmissionId_PrevAndNext(
			long submissionValueId, long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Removes all the submission values where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	public void removeBySubmissionId(long submissionId);

	/**
	 * Returns the number of submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching submission values
	 */
	public int countBySubmissionId(long submissionId);

	/**
	 * Returns all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the matching submission values
	 */
	public java.util.List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue);

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
	public java.util.List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end);

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
	public java.util.List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

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
	public java.util.List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator,
		boolean useFinderCache);

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
	public SubmissionValue findByC_FK_SV_First(
			long campaignId, String fieldKey, String stringValue,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Returns the first submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public SubmissionValue fetchByC_FK_SV_First(
		long campaignId, String fieldKey, String stringValue,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

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
	public SubmissionValue findByC_FK_SV_Last(
			long campaignId, String fieldKey, String stringValue,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Returns the last submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	public SubmissionValue fetchByC_FK_SV_Last(
		long campaignId, String fieldKey, String stringValue,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

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
	public SubmissionValue[] findByC_FK_SV_PrevAndNext(
			long submissionValueId, long campaignId, String fieldKey,
			String stringValue,
			com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
				orderByComparator)
		throws NoSuchSubmissionValueException;

	/**
	 * Removes all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 */
	public void removeByC_FK_SV(
		long campaignId, String fieldKey, String stringValue);

	/**
	 * Returns the number of submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the number of matching submission values
	 */
	public int countByC_FK_SV(
		long campaignId, String fieldKey, String stringValue);

	/**
	 * Caches the submission value in the entity cache if it is enabled.
	 *
	 * @param submissionValue the submission value
	 */
	public void cacheResult(SubmissionValue submissionValue);

	/**
	 * Caches the submission values in the entity cache if it is enabled.
	 *
	 * @param submissionValues the submission values
	 */
	public void cacheResult(java.util.List<SubmissionValue> submissionValues);

	/**
	 * Creates a new submission value with the primary key. Does not add the submission value to the database.
	 *
	 * @param submissionValueId the primary key for the new submission value
	 * @return the new submission value
	 */
	public SubmissionValue create(long submissionValueId);

	/**
	 * Removes the submission value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value that was removed
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public SubmissionValue remove(long submissionValueId)
		throws NoSuchSubmissionValueException;

	public SubmissionValue updateImpl(SubmissionValue submissionValue);

	/**
	 * Returns the submission value with the primary key or throws a <code>NoSuchSubmissionValueException</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	public SubmissionValue findByPrimaryKey(long submissionValueId)
		throws NoSuchSubmissionValueException;

	/**
	 * Returns the submission value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value, or <code>null</code> if a submission value with the primary key could not be found
	 */
	public SubmissionValue fetchByPrimaryKey(long submissionValueId);

	/**
	 * Returns all the submission values.
	 *
	 * @return the submission values
	 */
	public java.util.List<SubmissionValue> findAll();

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
	public java.util.List<SubmissionValue> findAll(int start, int end);

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
	public java.util.List<SubmissionValue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator);

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
	public java.util.List<SubmissionValue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubmissionValue>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the submission values from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of submission values.
	 *
	 * @return the number of submission values
	 */
	public int countAll();

}