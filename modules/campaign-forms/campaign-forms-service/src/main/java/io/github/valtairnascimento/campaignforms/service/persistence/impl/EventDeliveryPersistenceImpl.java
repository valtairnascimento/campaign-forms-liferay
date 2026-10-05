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

package io.github.valtairnascimento.campaignforms.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import io.github.valtairnascimento.campaignforms.exception.NoSuchEventDeliveryException;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.EventDeliveryTable;
import io.github.valtairnascimento.campaignforms.model.impl.EventDeliveryImpl;
import io.github.valtairnascimento.campaignforms.model.impl.EventDeliveryModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.EventDeliveryPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.EventDeliveryUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the event delivery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = EventDeliveryPersistence.class)
public class EventDeliveryPersistenceImpl
	extends BasePersistenceImpl<EventDelivery>
	implements EventDeliveryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EventDeliveryUtil</code> to access the event delivery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EventDeliveryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBySubmissionId;
	private FinderPath _finderPathWithoutPaginationFindBySubmissionId;
	private FinderPath _finderPathCountBySubmissionId;

	/**
	 * Returns all the event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching event deliveries
	 */
	@Override
	public List<EventDelivery> findBySubmissionId(long submissionId) {
		return findBySubmissionId(
			submissionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end) {

		return findBySubmissionId(submissionId, start, end, null);
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
	@Override
	public List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return findBySubmissionId(
			submissionId, start, end, orderByComparator, true);
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
	@Override
	public List<EventDelivery> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBySubmissionId;
				finderArgs = new Object[] {submissionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySubmissionId;
			finderArgs = new Object[] {
				submissionId, start, end, orderByComparator
			};
		}

		List<EventDelivery> list = null;

		if (useFinderCache) {
			list = (List<EventDelivery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventDelivery eventDelivery : list) {
					if (submissionId != eventDelivery.getSubmissionId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

			sb.append(_FINDER_COLUMN_SUBMISSIONID_SUBMISSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(submissionId);

				list = (List<EventDelivery>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery findBySubmissionId_First(
			long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchBySubmissionId_First(
			submissionId, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("submissionId=");
		sb.append(submissionId);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the first event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchBySubmissionId_First(
		long submissionId, OrderByComparator<EventDelivery> orderByComparator) {

		List<EventDelivery> list = findBySubmissionId(
			submissionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery findBySubmissionId_Last(
			long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchBySubmissionId_Last(
			submissionId, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("submissionId=");
		sb.append(submissionId);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the last event delivery in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchBySubmissionId_Last(
		long submissionId, OrderByComparator<EventDelivery> orderByComparator) {

		int count = countBySubmissionId(submissionId);

		if (count == 0) {
			return null;
		}

		List<EventDelivery> list = findBySubmissionId(
			submissionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EventDelivery[] findBySubmissionId_PrevAndNext(
			long eventDeliveryId, long submissionId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = findByPrimaryKey(eventDeliveryId);

		Session session = null;

		try {
			session = openSession();

			EventDelivery[] array = new EventDeliveryImpl[3];

			array[0] = getBySubmissionId_PrevAndNext(
				session, eventDelivery, submissionId, orderByComparator, true);

			array[1] = eventDelivery;

			array[2] = getBySubmissionId_PrevAndNext(
				session, eventDelivery, submissionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventDelivery getBySubmissionId_PrevAndNext(
		Session session, EventDelivery eventDelivery, long submissionId,
		OrderByComparator<EventDelivery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

		sb.append(_FINDER_COLUMN_SUBMISSIONID_SUBMISSIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(submissionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						eventDelivery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventDelivery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event deliveries where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	@Override
	public void removeBySubmissionId(long submissionId) {
		for (EventDelivery eventDelivery :
				findBySubmissionId(
					submissionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(eventDelivery);
		}
	}

	/**
	 * Returns the number of event deliveries where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching event deliveries
	 */
	@Override
	public int countBySubmissionId(long submissionId) {
		FinderPath finderPath = _finderPathCountBySubmissionId;

		Object[] finderArgs = new Object[] {submissionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EVENTDELIVERY_WHERE);

			sb.append(_FINDER_COLUMN_SUBMISSIONID_SUBMISSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(submissionId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SUBMISSIONID_SUBMISSIONID_2 =
		"eventDelivery.submissionId = ?";

	private FinderPath _finderPathWithPaginationFindByCampaignId;
	private FinderPath _finderPathWithoutPaginationFindByCampaignId;
	private FinderPath _finderPathCountByCampaignId;

	/**
	 * Returns all the event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching event deliveries
	 */
	@Override
	public List<EventDelivery> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end) {

		return findByCampaignId(campaignId, start, end, null);
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
	@Override
	public List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
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
	@Override
	public List<EventDelivery> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCampaignId;
				finderArgs = new Object[] {campaignId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCampaignId;
			finderArgs = new Object[] {
				campaignId, start, end, orderByComparator
			};
		}

		List<EventDelivery> list = null;

		if (useFinderCache) {
			list = (List<EventDelivery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventDelivery eventDelivery : list) {
					if (campaignId != eventDelivery.getCampaignId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<EventDelivery>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery findByCampaignId_First(
			long campaignId, OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchByCampaignId_First(
			campaignId, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the first event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchByCampaignId_First(
		long campaignId, OrderByComparator<EventDelivery> orderByComparator) {

		List<EventDelivery> list = findByCampaignId(
			campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery
	 * @throws NoSuchEventDeliveryException if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery findByCampaignId_Last(
			long campaignId, OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchByCampaignId_Last(
			campaignId, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the last event delivery in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchByCampaignId_Last(
		long campaignId, OrderByComparator<EventDelivery> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<EventDelivery> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EventDelivery[] findByCampaignId_PrevAndNext(
			long eventDeliveryId, long campaignId,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = findByPrimaryKey(eventDeliveryId);

		Session session = null;

		try {
			session = openSession();

			EventDelivery[] array = new EventDeliveryImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, eventDelivery, campaignId, orderByComparator, true);

			array[1] = eventDelivery;

			array[2] = getByCampaignId_PrevAndNext(
				session, eventDelivery, campaignId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventDelivery getByCampaignId_PrevAndNext(
		Session session, EventDelivery eventDelivery, long campaignId,
		OrderByComparator<EventDelivery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

		sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(campaignId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						eventDelivery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventDelivery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event deliveries where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (EventDelivery eventDelivery :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(eventDelivery);
		}
	}

	/**
	 * Returns the number of event deliveries where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching event deliveries
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_EVENTDELIVERY_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2 =
		"eventDelivery.campaignId = ?";

	private FinderPath _finderPathWithPaginationFindByS_LtNAD;
	private FinderPath _finderPathWithPaginationCountByS_LtNAD;

	/**
	 * Returns all the event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the matching event deliveries
	 */
	@Override
	public List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate) {

		return findByS_LtNAD(
			status, nextAttemptDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end) {

		return findByS_LtNAD(status, nextAttemptDate, start, end, null);
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
	@Override
	public List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return findByS_LtNAD(
			status, nextAttemptDate, start, end, orderByComparator, true);
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
	@Override
	public List<EventDelivery> findByS_LtNAD(
		String status, Date nextAttemptDate, int start, int end,
		OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByS_LtNAD;
		finderArgs = new Object[] {
			status, _getTime(nextAttemptDate), start, end, orderByComparator
		};

		List<EventDelivery> list = null;

		if (useFinderCache) {
			list = (List<EventDelivery>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EventDelivery eventDelivery : list) {
					if (!status.equals(eventDelivery.getStatus()) ||
						(nextAttemptDate.getTime() <
							eventDelivery.getNextAttemptDate(
							).getTime())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_2);
			}

			boolean bindNextAttemptDate = false;

			if (nextAttemptDate == null) {
				sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_1);
			}
			else {
				bindNextAttemptDate = true;

				sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				if (bindNextAttemptDate) {
					queryPos.add(new Timestamp(nextAttemptDate.getTime()));
				}

				list = (List<EventDelivery>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public EventDelivery findByS_LtNAD_First(
			String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchByS_LtNAD_First(
			status, nextAttemptDate, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append(", nextAttemptDate<=");
		sb.append(nextAttemptDate);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the first event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchByS_LtNAD_First(
		String status, Date nextAttemptDate,
		OrderByComparator<EventDelivery> orderByComparator) {

		List<EventDelivery> list = findByS_LtNAD(
			status, nextAttemptDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EventDelivery findByS_LtNAD_Last(
			String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchByS_LtNAD_Last(
			status, nextAttemptDate, orderByComparator);

		if (eventDelivery != null) {
			return eventDelivery;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append(", nextAttemptDate<=");
		sb.append(nextAttemptDate);

		sb.append("}");

		throw new NoSuchEventDeliveryException(sb.toString());
	}

	/**
	 * Returns the last event delivery in the ordered set where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event delivery, or <code>null</code> if a matching event delivery could not be found
	 */
	@Override
	public EventDelivery fetchByS_LtNAD_Last(
		String status, Date nextAttemptDate,
		OrderByComparator<EventDelivery> orderByComparator) {

		int count = countByS_LtNAD(status, nextAttemptDate);

		if (count == 0) {
			return null;
		}

		List<EventDelivery> list = findByS_LtNAD(
			status, nextAttemptDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EventDelivery[] findByS_LtNAD_PrevAndNext(
			long eventDeliveryId, String status, Date nextAttemptDate,
			OrderByComparator<EventDelivery> orderByComparator)
		throws NoSuchEventDeliveryException {

		status = Objects.toString(status, "");

		EventDelivery eventDelivery = findByPrimaryKey(eventDeliveryId);

		Session session = null;

		try {
			session = openSession();

			EventDelivery[] array = new EventDeliveryImpl[3];

			array[0] = getByS_LtNAD_PrevAndNext(
				session, eventDelivery, status, nextAttemptDate,
				orderByComparator, true);

			array[1] = eventDelivery;

			array[2] = getByS_LtNAD_PrevAndNext(
				session, eventDelivery, status, nextAttemptDate,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EventDelivery getByS_LtNAD_PrevAndNext(
		Session session, EventDelivery eventDelivery, String status,
		Date nextAttemptDate,
		OrderByComparator<EventDelivery> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_EVENTDELIVERY_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_2);
		}

		boolean bindNextAttemptDate = false;

		if (nextAttemptDate == null) {
			sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_1);
		}
		else {
			bindNextAttemptDate = true;

			sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(EventDeliveryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (bindNextAttemptDate) {
			queryPos.add(new Timestamp(nextAttemptDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						eventDelivery)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EventDelivery> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the event deliveries where status = &#63; and nextAttemptDate &le; &#63; from the database.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 */
	@Override
	public void removeByS_LtNAD(String status, Date nextAttemptDate) {
		for (EventDelivery eventDelivery :
				findByS_LtNAD(
					status, nextAttemptDate, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(eventDelivery);
		}
	}

	/**
	 * Returns the number of event deliveries where status = &#63; and nextAttemptDate &le; &#63;.
	 *
	 * @param status the status
	 * @param nextAttemptDate the next attempt date
	 * @return the number of matching event deliveries
	 */
	@Override
	public int countByS_LtNAD(String status, Date nextAttemptDate) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathWithPaginationCountByS_LtNAD;

		Object[] finderArgs = new Object[] {status, _getTime(nextAttemptDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_EVENTDELIVERY_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_S_LTNAD_STATUS_2);
			}

			boolean bindNextAttemptDate = false;

			if (nextAttemptDate == null) {
				sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_1);
			}
			else {
				bindNextAttemptDate = true;

				sb.append(_FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				if (bindNextAttemptDate) {
					queryPos.add(new Timestamp(nextAttemptDate.getTime()));
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_S_LTNAD_STATUS_2 =
		"eventDelivery.status = ? AND ";

	private static final String _FINDER_COLUMN_S_LTNAD_STATUS_3 =
		"(eventDelivery.status IS NULL OR eventDelivery.status = '') AND ";

	private static final String _FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_1 =
		"eventDelivery.nextAttemptDate IS NULL";

	private static final String _FINDER_COLUMN_S_LTNAD_NEXTATTEMPTDATE_2 =
		"eventDelivery.nextAttemptDate <= ?";

	public EventDeliveryPersistenceImpl() {
		setModelClass(EventDelivery.class);

		setModelImplClass(EventDeliveryImpl.class);
		setModelPKClass(long.class);

		setTable(EventDeliveryTable.INSTANCE);
	}

	/**
	 * Caches the event delivery in the entity cache if it is enabled.
	 *
	 * @param eventDelivery the event delivery
	 */
	@Override
	public void cacheResult(EventDelivery eventDelivery) {
		entityCache.putResult(
			EventDeliveryImpl.class, eventDelivery.getPrimaryKey(),
			eventDelivery);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the event deliveries in the entity cache if it is enabled.
	 *
	 * @param eventDeliveries the event deliveries
	 */
	@Override
	public void cacheResult(List<EventDelivery> eventDeliveries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (eventDeliveries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EventDelivery eventDelivery : eventDeliveries) {
			if (entityCache.getResult(
					EventDeliveryImpl.class, eventDelivery.getPrimaryKey()) ==
						null) {

				cacheResult(eventDelivery);
			}
		}
	}

	/**
	 * Clears the cache for all event deliveries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventDeliveryImpl.class);

		finderCache.clearCache(EventDeliveryImpl.class);
	}

	/**
	 * Clears the cache for the event delivery.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EventDelivery eventDelivery) {
		entityCache.removeResult(EventDeliveryImpl.class, eventDelivery);
	}

	@Override
	public void clearCache(List<EventDelivery> eventDeliveries) {
		for (EventDelivery eventDelivery : eventDeliveries) {
			entityCache.removeResult(EventDeliveryImpl.class, eventDelivery);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(EventDeliveryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(EventDeliveryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new event delivery with the primary key. Does not add the event delivery to the database.
	 *
	 * @param eventDeliveryId the primary key for the new event delivery
	 * @return the new event delivery
	 */
	@Override
	public EventDelivery create(long eventDeliveryId) {
		EventDelivery eventDelivery = new EventDeliveryImpl();

		eventDelivery.setNew(true);
		eventDelivery.setPrimaryKey(eventDeliveryId);

		eventDelivery.setCompanyId(CompanyThreadLocal.getCompanyId());

		return eventDelivery;
	}

	/**
	 * Removes the event delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery that was removed
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	@Override
	public EventDelivery remove(long eventDeliveryId)
		throws NoSuchEventDeliveryException {

		return remove((Serializable)eventDeliveryId);
	}

	/**
	 * Removes the event delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event delivery
	 * @return the event delivery that was removed
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	@Override
	public EventDelivery remove(Serializable primaryKey)
		throws NoSuchEventDeliveryException {

		Session session = null;

		try {
			session = openSession();

			EventDelivery eventDelivery = (EventDelivery)session.get(
				EventDeliveryImpl.class, primaryKey);

			if (eventDelivery == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventDeliveryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(eventDelivery);
		}
		catch (NoSuchEventDeliveryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected EventDelivery removeImpl(EventDelivery eventDelivery) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(eventDelivery)) {
				eventDelivery = (EventDelivery)session.get(
					EventDeliveryImpl.class, eventDelivery.getPrimaryKeyObj());
			}

			if (eventDelivery != null) {
				session.delete(eventDelivery);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (eventDelivery != null) {
			clearCache(eventDelivery);
		}

		return eventDelivery;
	}

	@Override
	public EventDelivery updateImpl(EventDelivery eventDelivery) {
		boolean isNew = eventDelivery.isNew();

		if (!(eventDelivery instanceof EventDeliveryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(eventDelivery.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					eventDelivery);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in eventDelivery proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EventDelivery implementation " +
					eventDelivery.getClass());
		}

		EventDeliveryModelImpl eventDeliveryModelImpl =
			(EventDeliveryModelImpl)eventDelivery;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (eventDelivery.getCreateDate() == null)) {
			if (serviceContext == null) {
				eventDelivery.setCreateDate(date);
			}
			else {
				eventDelivery.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!eventDeliveryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				eventDelivery.setModifiedDate(date);
			}
			else {
				eventDelivery.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(eventDelivery);
			}
			else {
				eventDelivery = (EventDelivery)session.merge(eventDelivery);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			EventDeliveryImpl.class, eventDeliveryModelImpl, false, true);

		if (isNew) {
			eventDelivery.setNew(false);
		}

		eventDelivery.resetOriginalValues();

		return eventDelivery;
	}

	/**
	 * Returns the event delivery with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event delivery
	 * @return the event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	@Override
	public EventDelivery findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventDeliveryException {

		EventDelivery eventDelivery = fetchByPrimaryKey(primaryKey);

		if (eventDelivery == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventDeliveryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return eventDelivery;
	}

	/**
	 * Returns the event delivery with the primary key or throws a <code>NoSuchEventDeliveryException</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery
	 * @throws NoSuchEventDeliveryException if a event delivery with the primary key could not be found
	 */
	@Override
	public EventDelivery findByPrimaryKey(long eventDeliveryId)
		throws NoSuchEventDeliveryException {

		return findByPrimaryKey((Serializable)eventDeliveryId);
	}

	/**
	 * Returns the event delivery with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventDeliveryId the primary key of the event delivery
	 * @return the event delivery, or <code>null</code> if a event delivery with the primary key could not be found
	 */
	@Override
	public EventDelivery fetchByPrimaryKey(long eventDeliveryId) {
		return fetchByPrimaryKey((Serializable)eventDeliveryId);
	}

	/**
	 * Returns all the event deliveries.
	 *
	 * @return the event deliveries
	 */
	@Override
	public List<EventDelivery> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EventDelivery> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<EventDelivery> findAll(
		int start, int end,
		OrderByComparator<EventDelivery> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<EventDelivery> findAll(
		int start, int end, OrderByComparator<EventDelivery> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<EventDelivery> list = null;

		if (useFinderCache) {
			list = (List<EventDelivery>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_EVENTDELIVERY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_EVENTDELIVERY;

				sql = sql.concat(EventDeliveryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EventDelivery>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the event deliveries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EventDelivery eventDelivery : findAll()) {
			remove(eventDelivery);
		}
	}

	/**
	 * Returns the number of event deliveries.
	 *
	 * @return the number of event deliveries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_EVENTDELIVERY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "eventDeliveryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EVENTDELIVERY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventDeliveryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event delivery persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindBySubmissionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubmissionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"submissionId"}, true);

		_finderPathWithoutPaginationFindBySubmissionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubmissionId",
			new String[] {Long.class.getName()}, new String[] {"submissionId"},
			true);

		_finderPathCountBySubmissionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubmissionId",
			new String[] {Long.class.getName()}, new String[] {"submissionId"},
			false);

		_finderPathWithPaginationFindByCampaignId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCampaignId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"campaignId"}, true);

		_finderPathWithoutPaginationFindByCampaignId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCampaignId",
			new String[] {Long.class.getName()}, new String[] {"campaignId"},
			true);

		_finderPathCountByCampaignId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCampaignId",
			new String[] {Long.class.getName()}, new String[] {"campaignId"},
			false);

		_finderPathWithPaginationFindByS_LtNAD = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_LtNAD",
			new String[] {
				String.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"status", "nextAttemptDate"}, true);

		_finderPathWithPaginationCountByS_LtNAD = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByS_LtNAD",
			new String[] {String.class.getName(), Date.class.getName()},
			new String[] {"status", "nextAttemptDate"}, false);

		_setEventDeliveryUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setEventDeliveryUtilPersistence(null);

		entityCache.removeCache(EventDeliveryImpl.class.getName());
	}

	private void _setEventDeliveryUtilPersistence(
		EventDeliveryPersistence eventDeliveryPersistence) {

		try {
			Field field = EventDeliveryUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, eventDeliveryPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CFPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_EVENTDELIVERY =
		"SELECT eventDelivery FROM EventDelivery eventDelivery";

	private static final String _SQL_SELECT_EVENTDELIVERY_WHERE =
		"SELECT eventDelivery FROM EventDelivery eventDelivery WHERE ";

	private static final String _SQL_COUNT_EVENTDELIVERY =
		"SELECT COUNT(eventDelivery) FROM EventDelivery eventDelivery";

	private static final String _SQL_COUNT_EVENTDELIVERY_WHERE =
		"SELECT COUNT(eventDelivery) FROM EventDelivery eventDelivery WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "eventDelivery.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EventDelivery exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EventDelivery exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EventDeliveryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}