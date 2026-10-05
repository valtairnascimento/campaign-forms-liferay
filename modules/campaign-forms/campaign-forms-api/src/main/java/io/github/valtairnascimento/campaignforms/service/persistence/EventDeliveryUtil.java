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

import io.github.valtairnascimento.campaignforms.model.EventDelivery;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the event delivery service. This utility wraps <code>io.github.valtairnascimento.campaignforms.service.persistence.impl.EventDeliveryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see EventDeliveryPersistence
 * @generated
 */
public class EventDeliveryUtil {

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
	public static void clearCache(EventDelivery eventDelivery) {
		getPersistence().clearCache(eventDelivery);
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
	public static Map<Serializable, EventDelivery> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EventDelivery> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventDelivery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventDelivery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EventDelivery update(EventDelivery eventDelivery) {
		return getPersistence().update(eventDelivery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EventDelivery update(
		EventDelivery eventDelivery, ServiceContext serviceContext) {

		return getPersistence().update(eventDelivery, serviceContext);
	}

	/**
	 * Returns all the event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching event deliveries
	 */
	public static List<EventDelivery> findBySubmissionId(long submissionId) {
		return getPersistence().findBySubmissionId(submissionId);
	}

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
	public static List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end) {

		return getPersistence().findBySubmissionId(submissionId, start, end);
	}

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
	public static List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().findBySubmissionId(
			submissionId, start, end, orderByComparator);
	}

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
	public static List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBySubmissionId(
			submissionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findBySubmissionId_First(
			long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findBySubmissionId_First(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchBySubmissionId_First(
		long submissionId, OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchBySubmissionId_First(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findBySubmissionId_Last(
			long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findBySubmissionId_Last(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchBySubmissionId_Last(
		long submissionId, OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchBySubmissionId_Last(
			submissionId, orderByComparator);
	}

	/**
	 * Returns the event deliveries before and after the current event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param eventDeliveryId the primary key of the current event delivery
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery[] findBySubmissionId_PrevAndNext(
			long eventDeliveryId, long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findBySubmissionId_PrevAndNext(
			eventDeliveryId, submissionId, orderByComparator);
	}

	/**
	 * Removes all the event deliveries where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	public static void removeBySubmissionId(long submissionId) {
		getPersistence().removeBySubmissionId(submissionId);
	}

	/**
	 * Returns the number of event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching event deliveries
	 */
	public static int countBySubmissionId(long submissionId) {
		return getPersistence().countBySubmissionId(submissionId);
	}

	/**
	 * Returns all the event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching event deliveries
	 */
	public static List<EventDelivery> findByCampaignId(long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

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
	public static List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end) {

		return getPersistence().findByCampaignId(campaignId, start, end);
	}

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
	public static List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator);
	}

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
	public static List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCampaignId(
			campaignId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findByCampaignId_First(
			long campaignId, OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchByCampaignId_First(
		long campaignId, OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchByCampaignId_First(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findByCampaignId_Last(
			long campaignId, OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchByCampaignId_Last(
		long campaignId, OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchByCampaignId_Last(
			campaignId, orderByComparator);
	}

	/**
	 * Returns the event deliveries before and after the current event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param eventDeliveryId the primary key of the current event delivery
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery[] findByCampaignId_PrevAndNext(
			long eventDeliveryId, long campaignId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByCampaignId_PrevAndNext(
			eventDeliveryId, campaignId, orderByComparator);
	}

	/**
	 * Removes all the event deliveries where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public static void removeByCampaignId(long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	 * Returns the number of event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching event deliveries
	 */
	public static int countByCampaignId(long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	 * Returns all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the matching event deliveries
	 */
	public static List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate) {

		return getPersistence().findByS_LtNAD(status, nextAttemptDate);
	}

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
	public static List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end) {

		return getPersistence().findByS_LtNAD(
			status, nextAttemptDate, start, end);
	}

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
	public static List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().findByS_LtNAD(
			status, nextAttemptDate, start, end, orderByComparator);
	}

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
	public static List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByS_LtNAD(
			status, nextAttemptDate, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findByS_LtNAD_First(
			String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByS_LtNAD_First(
			status, nextAttemptDate, orderByComparator);
	}

	/**
	 * Returns the first event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchByS_LtNAD_First(
		String status, Date nextAttemptDate,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchByS_LtNAD_First(
			status, nextAttemptDate, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	public static EventDelivery findByS_LtNAD_Last(
			String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByS_LtNAD_Last(
			status, nextAttemptDate, orderByComparator);
	}

	/**
	 * Returns the last event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	public static EventDelivery fetchByS_LtNAD_Last(
		String status, Date nextAttemptDate,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().fetchByS_LtNAD_Last(
			status, nextAttemptDate, orderByComparator);
	}

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
	public static EventDelivery[] findByS_LtNAD_PrevAndNext(
			long eventDeliveryId, String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByS_LtNAD_PrevAndNext(
			eventDeliveryId, status, nextAttemptDate, orderByComparator);
	}

	/**
	 * Removes all the event deliveries where status = &#63; and nextAttemptDate &le; &#63; from the database.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 */
	public static void removeByS_LtNAD(String status, Date nextAttemptDate) {
		getPersistence().removeByS_LtNAD(status, nextAttemptDate);
	}

	/**
	 * Returns the number of event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the number of matching event deliveries
	 */
	public static int countByS_LtNAD(String status, Date nextAttemptDate) {
		return getPersistence().countByS_LtNAD(status, nextAttemptDate);
	}

	/**
	 * Caches the event delivery in the entity cache if it is enabled.
	 *
	 * @param eventDelivery the event delivery
	 */
	public static void cacheResult(EventDelivery eventDelivery) {
		getPersistence().cacheResult(eventDelivery);
	}

	/**
	 * Caches the event deliveries in the entity cache if it is enabled.
	 *
	 * @param eventDeliveries the event deliveries
	 */
	public static void cacheResult(List<EventDelivery> eventDeliveries) {
		getPersistence().cacheResult(eventDeliveries);
	}

	/**
	 * Creates a new event delivery with the primary key. Does not add the event delivery to the database.
	 *
	 * @param eventDeliveryId the primary key for the new event delivery
	 * @return the new event delivery
	 */
	public static EventDelivery create(long eventDeliveryId) {
		return getPersistence().create(eventDeliveryId);
	}

	/**
	 * Removes the event delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery that was removed
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery remove(long eventDeliveryId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().remove(eventDeliveryId);
	}

	public static EventDelivery updateImpl(EventDelivery eventDelivery) {
		return getPersistence().updateImpl(eventDelivery);
	}

	/**
	 * Returns the event delivery with the primary key or throws a <code>NoSuchEventDeliveryException</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery findByPrimaryKey(long eventDeliveryId)
		throws io.github.valtairnascimento.campaignforms.exception.
			NoSuchEventDeliveryException {

		return getPersistence().findByPrimaryKey(eventDeliveryId);
	}

	/**
	 * Returns the event delivery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery, or <code>null</code> if a event delivery with the primary key could not be found
	 */
	public static EventDelivery fetchByPrimaryKey(long eventDeliveryId) {
		return getPersistence().fetchByPrimaryKey(eventDeliveryId);
	}

	/**
	 * Returns all the event deliveries.
	 *
	 * @return the event deliveries
	 */
	public static List<EventDelivery> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EventDelivery> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EventDelivery> findAll(
		int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EventDelivery> findAll(
		int start, int end, OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the event deliveries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of event deliveries.
	 *
	 * @return the number of event deliveries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EventDeliveryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EventDeliveryPersistence _persistence;

}