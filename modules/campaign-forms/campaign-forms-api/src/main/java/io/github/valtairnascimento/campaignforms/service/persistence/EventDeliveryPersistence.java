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

import io.github.valtairnascimento.campaignforms.exception.NoSuchEventDeliveryException;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the event delivery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see EventDeliveryUtil
 * @generated
 */
@ProviderType
public interface EventDeliveryPersistence
	extends BasePersistence<EventDelivery> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventDeliveryUtil} to access the event delivery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching event deliveries
	 */
	public java.util.List<EventDelivery> findBySubmissionId(long submissionId);

	/**
	 * Returns a range of all the event deliveries where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @return the range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end);

	/**
	 * Returns an ordered range of all the event deliveries where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns an ordered range of all the event deliveries where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findBySubmissionId_First(
			long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchBySubmissionId_First(
		long submissionId,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findBySubmissionId_Last(
			long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchBySubmissionId_Last(
		long submissionId,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the event deliveries before and after the current event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param eventDeliveryId the primary key of the current event delivery
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public EventDelivery[] findBySubmissionId_PrevAndNext(
			long eventDeliveryId, long submissionId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Removes all the event deliveries where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	public void removeBySubmissionId(long submissionId);

	/**
	 * Returns the number of event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching event deliveries
	 */
	public int countBySubmissionId(long submissionId);

	/**
	 * Returns all the event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching event deliveries
	 */
	public java.util.List<EventDelivery> findByCampaignId(long campaignId);

	/**
	 * Returns a range of all the event deliveries where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @return the range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end);

	/**
	 * Returns an ordered range of all the event deliveries where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns an ordered range of all the event deliveries where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findByCampaignId_First(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findByCampaignId_Last(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the event deliveries before and after the current event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param eventDeliveryId the primary key of the current event delivery
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public EventDelivery[] findByCampaignId_PrevAndNext(
			long eventDeliveryId, long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Removes all the event deliveries where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(long campaignId);

	/**
	 * Returns the number of event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching event deliveries
	 */
	public int countByCampaignId(long campaignId);

	/**
	 * Returns all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the matching event deliveries
	 */
	public java.util.List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate);

	/**
	 * Returns a range of all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @return the range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end);

	/**
	 * Returns an ordered range of all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns an ordered range of all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event deliveries
	 */
	public java.util.List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findByS_LtNAD_First(
			String status, Date nextAttemptDate,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the first event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchByS_LtNAD_First(
		String status, Date nextAttemptDate,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the last event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public EventDelivery findByS_LtNAD_Last(
			String status, Date nextAttemptDate,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the last event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public EventDelivery fetchByS_LtNAD_Last(
		String status, Date nextAttemptDate,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns the event deliveries before and after the current event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param eventDeliveryId the primary key of the current event delivery
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public EventDelivery[] findByS_LtNAD_PrevAndNext(
			long eventDeliveryId, String status, Date nextAttemptDate,
			com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
				orderByComparator)
		throws NoSuchEventDeliveryException;

	/**
	 * Removes all the event deliveries where status = &#63; and nextAttemptDate &le; &#63; from the database.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 */
	public void removeByS_LtNAD(String status, Date nextAttemptDate);

	/**
	 * Returns the number of event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the number of matching event deliveries
	 */
	public int countByS_LtNAD(String status, Date nextAttemptDate);

	/**
	 * Caches the event delivery in the entity cache if it is enabled.
	 *
	 * @param eventDelivery the event delivery
	 */
	public void cacheResult(EventDelivery eventDelivery);

	/**
	 * Caches the event deliveries in the entity cache if it is enabled.
	 *
	 * @param eventDeliveries the event deliveries
	 */
	public void cacheResult(java.util.List<EventDelivery> eventDeliveries);

	/**
	 * Creates a new event delivery with the primary key. Does not add the event delivery to the database.
	 *
	 * @param eventDeliveryId the primary key for the new event delivery
	 * @return the new event delivery
	 */
	public EventDelivery create(long eventDeliveryId);

	/**
	 * Removes the event delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery that was removed
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public EventDelivery remove(long eventDeliveryId)
		throws NoSuchEventDeliveryException;

	public EventDelivery updateImpl(EventDelivery eventDelivery);

	/**
	 * Returns the event delivery with the primary key or throws a <code>NoSuchEventDeliveryException</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public EventDelivery findByPrimaryKey(long eventDeliveryId)
		throws NoSuchEventDeliveryException;

	/**
	 * Returns the event delivery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery, or <code>null</code> if a event delivery with the primary key could not be found
	 */
	public EventDelivery fetchByPrimaryKey(long eventDeliveryId);

	/**
	 * Returns all the event deliveries.
	 *
	 * @return the event deliveries
	 */
	public java.util.List<EventDelivery> findAll();

	/**
	 * Returns a range of all the event deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @return the range of event deliveries
	 */
	public java.util.List<EventDelivery> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the event deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event deliveries
	 */
	public java.util.List<EventDelivery> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator);

	/**
	 * Returns an ordered range of all the event deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event deliveries
	 */
	public java.util.List<EventDelivery> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventDelivery>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the event deliveries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of event deliveries.
	 *
	 * @return the number of event deliveries
	 */
	public int countAll();

}