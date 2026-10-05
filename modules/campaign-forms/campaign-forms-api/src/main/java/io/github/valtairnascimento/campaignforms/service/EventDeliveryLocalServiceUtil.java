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

import io.github.valtairnascimento.campaignforms.model.EventDelivery;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for EventDelivery. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.EventDeliveryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Valtair Nascimento
 * @see EventDeliveryLocalService
 * @generated
 */
public class EventDeliveryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.EventDeliveryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static List<EventDelivery> addEventDeliveries(
		io.github.valtairnascimento.campaignforms.model.Submission submission,
		io.github.valtairnascimento.campaignforms.constants.EventType
			eventType) {

		return getService().addEventDeliveries(submission, eventType);
	}

	/**
	 * Adds the event delivery to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventDelivery the event delivery
	 * @return the event delivery that was added
	 */
	public static EventDelivery addEventDelivery(EventDelivery eventDelivery) {
		return getService().addEventDelivery(eventDelivery);
	}

	/**
	 * Creates a new event delivery with the primary key. Does not add the event delivery to the database.
	 *
	 * @param eventDeliveryId the primary key for the new event delivery
	 * @return the new event delivery
	 */
	public static EventDelivery createEventDelivery(long eventDeliveryId) {
		return getService().createEventDelivery(eventDeliveryId);
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
	 * Deletes the event delivery from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventDelivery the event delivery
	 * @return the event delivery that was removed
	 */
	public static EventDelivery deleteEventDelivery(
		EventDelivery eventDelivery) {

		return getService().deleteEventDelivery(eventDelivery);
	}

	/**
	 * Deletes the event delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery that was removed
	 * @throws PortalException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery deleteEventDelivery(long eventDeliveryId)
		throws PortalException {

		return getService().deleteEventDelivery(eventDeliveryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.EventDeliveryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.EventDeliveryModelImpl</code>.
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

	public static EventDelivery fetchEventDelivery(long eventDeliveryId) {
		return getService().fetchEventDelivery(eventDeliveryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the event deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>io.github.valtairnascimento.campaignforms.model.impl.EventDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event deliveries
	 * @param end the upper bound of the range of event deliveries (not inclusive)
	 * @return the range of event deliveries
	 */
	public static List<EventDelivery> getEventDeliveries(int start, int end) {
		return getService().getEventDeliveries(start, end);
	}

	/**
	 * Returns the number of event deliveries.
	 *
	 * @return the number of event deliveries
	 */
	public static int getEventDeliveriesCount() {
		return getService().getEventDeliveriesCount();
	}

	/**
	 * Returns the event delivery with the primary key.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery
	 * @throws PortalException if a event delivery with the primary key could not be found
	 */
	public static EventDelivery getEventDelivery(long eventDeliveryId)
		throws PortalException {

		return getService().getEventDelivery(eventDeliveryId);
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

	public static List<EventDelivery> getSubmissionEventDeliveries(
		long submissionId) {

		return getService().getSubmissionEventDeliveries(submissionId);
	}

	public static int processDueEventDeliveries() {
		return getService().processDueEventDeliveries();
	}

	public static EventDelivery processEventDelivery(long eventDeliveryId) {
		return getService().processEventDelivery(eventDeliveryId);
	}

	/**
	 * Updates the event delivery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EventDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventDelivery the event delivery
	 * @return the event delivery that was updated
	 */
	public static EventDelivery updateEventDelivery(
		EventDelivery eventDelivery) {

		return getService().updateEventDelivery(eventDelivery);
	}

	public static EventDeliveryLocalService getService() {
		return _service;
	}

	private static volatile EventDeliveryLocalService _service;

}