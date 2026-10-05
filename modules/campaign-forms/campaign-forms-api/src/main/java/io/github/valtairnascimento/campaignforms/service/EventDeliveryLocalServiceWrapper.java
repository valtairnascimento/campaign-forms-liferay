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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EventDeliveryLocalService}.
 *
 * @author Valtair Nascimento
 * @see EventDeliveryLocalService
 * @generated
 */
public class EventDeliveryLocalServiceWrapper
	implements EventDeliveryLocalService,
			   ServiceWrapper<EventDeliveryLocalService> {

	public EventDeliveryLocalServiceWrapper() {
		this(null);
	}

	public EventDeliveryLocalServiceWrapper(
		EventDeliveryLocalService eventDeliveryLocalService) {

		_eventDeliveryLocalService = eventDeliveryLocalService;
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
			addEventDeliveries(
				io.github.valtairnascimento.campaignforms.model.Submission
					submission,
				io.github.valtairnascimento.campaignforms.constants.EventType
					eventType) {

		return _eventDeliveryLocalService.addEventDeliveries(
			submission, eventType);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		addEventDelivery(
			io.github.valtairnascimento.campaignforms.model.EventDelivery
				eventDelivery) {

		return _eventDeliveryLocalService.addEventDelivery(eventDelivery);
	}

	/**
	 * Creates a new event delivery with the primary key. Does not add the event delivery to the database.
	 *
	 * @param eventDeliveryId the primary key for the new event delivery
	 * @return the new event delivery
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		createEventDelivery(long eventDeliveryId) {

		return _eventDeliveryLocalService.createEventDelivery(eventDeliveryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventDeliveryLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		deleteEventDelivery(
			io.github.valtairnascimento.campaignforms.model.EventDelivery
				eventDelivery) {

		return _eventDeliveryLocalService.deleteEventDelivery(eventDelivery);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
			deleteEventDelivery(long eventDeliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventDeliveryLocalService.deleteEventDelivery(eventDeliveryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventDeliveryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _eventDeliveryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _eventDeliveryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventDeliveryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _eventDeliveryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _eventDeliveryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _eventDeliveryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _eventDeliveryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _eventDeliveryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		fetchEventDelivery(long eventDeliveryId) {

		return _eventDeliveryLocalService.fetchEventDelivery(eventDeliveryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _eventDeliveryLocalService.getActionableDynamicQuery();
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
	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
			getEventDeliveries(int start, int end) {

		return _eventDeliveryLocalService.getEventDeliveries(start, end);
	}

	/**
	 * Returns the number of event deliveries.
	 *
	 * @return the number of event deliveries
	 */
	@Override
	public int getEventDeliveriesCount() {
		return _eventDeliveryLocalService.getEventDeliveriesCount();
	}

	/**
	 * Returns the event delivery with the primary key.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery
	 * @throws PortalException if a event delivery with the primary key could not be found
	 */
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
			getEventDelivery(long eventDeliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventDeliveryLocalService.getEventDelivery(eventDeliveryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _eventDeliveryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _eventDeliveryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _eventDeliveryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
			getSubmissionEventDeliveries(long submissionId) {

		return _eventDeliveryLocalService.getSubmissionEventDeliveries(
			submissionId);
	}

	@Override
	public int processDueEventDeliveries() {
		return _eventDeliveryLocalService.processDueEventDeliveries();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		processEventDelivery(long eventDeliveryId) {

		return _eventDeliveryLocalService.processEventDelivery(eventDeliveryId);
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
	@Override
	public io.github.valtairnascimento.campaignforms.model.EventDelivery
		updateEventDelivery(
			io.github.valtairnascimento.campaignforms.model.EventDelivery
				eventDelivery) {

		return _eventDeliveryLocalService.updateEventDelivery(eventDelivery);
	}

	@Override
	public EventDeliveryLocalService getWrappedService() {
		return _eventDeliveryLocalService;
	}

	@Override
	public void setWrappedService(
		EventDeliveryLocalService eventDeliveryLocalService) {

		_eventDeliveryLocalService = eventDeliveryLocalService;
	}

	private EventDeliveryLocalService _eventDeliveryLocalService;

}