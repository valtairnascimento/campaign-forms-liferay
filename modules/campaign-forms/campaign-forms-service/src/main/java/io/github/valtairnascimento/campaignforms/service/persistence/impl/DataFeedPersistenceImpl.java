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
import com.liferay.portal.kernel.util.SetUtil;

import io.github.valtairnascimento.campaignforms.exception.NoSuchDataFeedException;
import io.github.valtairnascimento.campaignforms.model.DataFeed;
import io.github.valtairnascimento.campaignforms.model.DataFeedTable;
import io.github.valtairnascimento.campaignforms.model.impl.DataFeedImpl;
import io.github.valtairnascimento.campaignforms.model.impl.DataFeedModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.DataFeedPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.DataFeedUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
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
 * The persistence implementation for the data feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = DataFeedPersistence.class)
public class DataFeedPersistenceImpl
	extends BasePersistenceImpl<DataFeed> implements DataFeedPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DataFeedUtil</code> to access the data feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DataFeedImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching data feeds
	 */
	@Override
	public List<DataFeed> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the data feeds where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<DataFeed> list = null;

		if (useFinderCache) {
			list = (List<DataFeed>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DataFeed dataFeed : list) {
					if (groupId != dataFeed.getGroupId()) {
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

			sb.append(_SQL_SELECT_DATAFEED_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DataFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<DataFeed>)QueryUtil.list(
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
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	@Override
	public DataFeed findByGroupId_First(
			long groupId, OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByGroupId_First(groupId, orderByComparator);

		if (dataFeed != null) {
			return dataFeed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchDataFeedException(sb.toString());
	}

	/**
	 * Returns the first data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByGroupId_First(
		long groupId, OrderByComparator<DataFeed> orderByComparator) {

		List<DataFeed> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	@Override
	public DataFeed findByGroupId_Last(
			long groupId, OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByGroupId_Last(groupId, orderByComparator);

		if (dataFeed != null) {
			return dataFeed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchDataFeedException(sb.toString());
	}

	/**
	 * Returns the last data feed in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByGroupId_Last(
		long groupId, OrderByComparator<DataFeed> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DataFeed> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where groupId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed[] findByGroupId_PrevAndNext(
			long dataFeedId, long groupId,
			OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = findByPrimaryKey(dataFeedId);

		Session session = null;

		try {
			session = openSession();

			DataFeed[] array = new DataFeedImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, dataFeed, groupId, orderByComparator, true);

			array[1] = dataFeed;

			array[2] = getByGroupId_PrevAndNext(
				session, dataFeed, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataFeed getByGroupId_PrevAndNext(
		Session session, DataFeed dataFeed, long groupId,
		OrderByComparator<DataFeed> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DATAFEED_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(DataFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(dataFeed)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DataFeed> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data feeds where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (DataFeed dataFeed :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dataFeed);
		}
	}

	/**
	 * Returns the number of data feeds where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching data feeds
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DATAFEED_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"dataFeed.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByCampaignId;
	private FinderPath _finderPathWithoutPaginationFindByCampaignId;
	private FinderPath _finderPathCountByCampaignId;

	/**
	 * Returns all the data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching data feeds
	 */
	@Override
	public List<DataFeed> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByCampaignId(
		long campaignId, int start, int end) {

		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the data feeds where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching data feeds
	 */
	@Override
	public List<DataFeed> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<DataFeed> orderByComparator, boolean useFinderCache) {

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

		List<DataFeed> list = null;

		if (useFinderCache) {
			list = (List<DataFeed>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DataFeed dataFeed : list) {
					if (campaignId != dataFeed.getCampaignId()) {
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

			sb.append(_SQL_SELECT_DATAFEED_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DataFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<DataFeed>)QueryUtil.list(
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
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	@Override
	public DataFeed findByCampaignId_First(
			long campaignId, OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByCampaignId_First(
			campaignId, orderByComparator);

		if (dataFeed != null) {
			return dataFeed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchDataFeedException(sb.toString());
	}

	/**
	 * Returns the first data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByCampaignId_First(
		long campaignId, OrderByComparator<DataFeed> orderByComparator) {

		List<DataFeed> list = findByCampaignId(
			campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	@Override
	public DataFeed findByCampaignId_Last(
			long campaignId, OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByCampaignId_Last(
			campaignId, orderByComparator);

		if (dataFeed != null) {
			return dataFeed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchDataFeedException(sb.toString());
	}

	/**
	 * Returns the last data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByCampaignId_Last(
		long campaignId, OrderByComparator<DataFeed> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<DataFeed> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the data feeds before and after the current data feed in the ordered set where campaignId = &#63;.
	 *
	 * @param dataFeedId the primary key of the current data feed
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed[] findByCampaignId_PrevAndNext(
			long dataFeedId, long campaignId,
			OrderByComparator<DataFeed> orderByComparator)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = findByPrimaryKey(dataFeedId);

		Session session = null;

		try {
			session = openSession();

			DataFeed[] array = new DataFeedImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, dataFeed, campaignId, orderByComparator, true);

			array[1] = dataFeed;

			array[2] = getByCampaignId_PrevAndNext(
				session, dataFeed, campaignId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataFeed getByCampaignId_PrevAndNext(
		Session session, DataFeed dataFeed, long campaignId,
		OrderByComparator<DataFeed> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DATAFEED_WHERE);

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
			sb.append(DataFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(campaignId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(dataFeed)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DataFeed> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data feeds where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (DataFeed dataFeed :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dataFeed);
		}
	}

	/**
	 * Returns the number of data feeds where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching data feeds
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DATAFEED_WHERE);

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
		"dataFeed.campaignId = ?";

	private FinderPath _finderPathFetchByFeedKey;
	private FinderPath _finderPathCountByFeedKey;

	/**
	 * Returns the data feed where feedKey = &#63; or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed
	 * @throws NoSuchDataFeedException if a matching data feed could not be found
	 */
	@Override
	public DataFeed findByFeedKey(String feedKey)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByFeedKey(feedKey);

		if (dataFeed == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("feedKey=");
			sb.append(feedKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchDataFeedException(sb.toString());
		}

		return dataFeed;
	}

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param feedKey the feed key
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByFeedKey(String feedKey) {
		return fetchByFeedKey(feedKey, true);
	}

	/**
	 * Returns the data feed where feedKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param feedKey the feed key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching data feed, or <code>null</code> if a matching data feed could not be found
	 */
	@Override
	public DataFeed fetchByFeedKey(String feedKey, boolean useFinderCache) {
		feedKey = Objects.toString(feedKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {feedKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFeedKey, finderArgs, this);
		}

		if (result instanceof DataFeed) {
			DataFeed dataFeed = (DataFeed)result;

			if (!Objects.equals(feedKey, dataFeed.getFeedKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DATAFEED_WHERE);

			boolean bindFeedKey = false;

			if (feedKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_FEEDKEY_FEEDKEY_3);
			}
			else {
				bindFeedKey = true;

				sb.append(_FINDER_COLUMN_FEEDKEY_FEEDKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFeedKey) {
					queryPos.add(feedKey);
				}

				List<DataFeed> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFeedKey, finderArgs, list);
					}
				}
				else {
					DataFeed dataFeed = list.get(0);

					result = dataFeed;

					cacheResult(dataFeed);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DataFeed)result;
		}
	}

	/**
	 * Removes the data feed where feedKey = &#63; from the database.
	 *
	 * @param feedKey the feed key
	 * @return the data feed that was removed
	 */
	@Override
	public DataFeed removeByFeedKey(String feedKey)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = findByFeedKey(feedKey);

		return remove(dataFeed);
	}

	/**
	 * Returns the number of data feeds where feedKey = &#63;.
	 *
	 * @param feedKey the feed key
	 * @return the number of matching data feeds
	 */
	@Override
	public int countByFeedKey(String feedKey) {
		feedKey = Objects.toString(feedKey, "");

		FinderPath finderPath = _finderPathCountByFeedKey;

		Object[] finderArgs = new Object[] {feedKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DATAFEED_WHERE);

			boolean bindFeedKey = false;

			if (feedKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_FEEDKEY_FEEDKEY_3);
			}
			else {
				bindFeedKey = true;

				sb.append(_FINDER_COLUMN_FEEDKEY_FEEDKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFeedKey) {
					queryPos.add(feedKey);
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

	private static final String _FINDER_COLUMN_FEEDKEY_FEEDKEY_2 =
		"dataFeed.feedKey = ?";

	private static final String _FINDER_COLUMN_FEEDKEY_FEEDKEY_3 =
		"(dataFeed.feedKey IS NULL OR dataFeed.feedKey = '')";

	public DataFeedPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("active", "active_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DataFeed.class);

		setModelImplClass(DataFeedImpl.class);
		setModelPKClass(long.class);

		setTable(DataFeedTable.INSTANCE);
	}

	/**
	 * Caches the data feed in the entity cache if it is enabled.
	 *
	 * @param dataFeed the data feed
	 */
	@Override
	public void cacheResult(DataFeed dataFeed) {
		entityCache.putResult(
			DataFeedImpl.class, dataFeed.getPrimaryKey(), dataFeed);

		finderCache.putResult(
			_finderPathFetchByFeedKey, new Object[] {dataFeed.getFeedKey()},
			dataFeed);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the data feeds in the entity cache if it is enabled.
	 *
	 * @param dataFeeds the data feeds
	 */
	@Override
	public void cacheResult(List<DataFeed> dataFeeds) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dataFeeds.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DataFeed dataFeed : dataFeeds) {
			if (entityCache.getResult(
					DataFeedImpl.class, dataFeed.getPrimaryKey()) == null) {

				cacheResult(dataFeed);
			}
		}
	}

	/**
	 * Clears the cache for all data feeds.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DataFeedImpl.class);

		finderCache.clearCache(DataFeedImpl.class);
	}

	/**
	 * Clears the cache for the data feed.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataFeed dataFeed) {
		entityCache.removeResult(DataFeedImpl.class, dataFeed);
	}

	@Override
	public void clearCache(List<DataFeed> dataFeeds) {
		for (DataFeed dataFeed : dataFeeds) {
			entityCache.removeResult(DataFeedImpl.class, dataFeed);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DataFeedImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DataFeedImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DataFeedModelImpl dataFeedModelImpl) {

		Object[] args = new Object[] {dataFeedModelImpl.getFeedKey()};

		finderCache.putResult(_finderPathCountByFeedKey, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByFeedKey, args, dataFeedModelImpl);
	}

	/**
	 * Creates a new data feed with the primary key. Does not add the data feed to the database.
	 *
	 * @param dataFeedId the primary key for the new data feed
	 * @return the new data feed
	 */
	@Override
	public DataFeed create(long dataFeedId) {
		DataFeed dataFeed = new DataFeedImpl();

		dataFeed.setNew(true);
		dataFeed.setPrimaryKey(dataFeedId);

		dataFeed.setCompanyId(CompanyThreadLocal.getCompanyId());

		return dataFeed;
	}

	/**
	 * Removes the data feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed that was removed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed remove(long dataFeedId) throws NoSuchDataFeedException {
		return remove((Serializable)dataFeedId);
	}

	/**
	 * Removes the data feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data feed
	 * @return the data feed that was removed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed remove(Serializable primaryKey)
		throws NoSuchDataFeedException {

		Session session = null;

		try {
			session = openSession();

			DataFeed dataFeed = (DataFeed)session.get(
				DataFeedImpl.class, primaryKey);

			if (dataFeed == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataFeedException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dataFeed);
		}
		catch (NoSuchDataFeedException noSuchEntityException) {
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
	protected DataFeed removeImpl(DataFeed dataFeed) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataFeed)) {
				dataFeed = (DataFeed)session.get(
					DataFeedImpl.class, dataFeed.getPrimaryKeyObj());
			}

			if (dataFeed != null) {
				session.delete(dataFeed);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dataFeed != null) {
			clearCache(dataFeed);
		}

		return dataFeed;
	}

	@Override
	public DataFeed updateImpl(DataFeed dataFeed) {
		boolean isNew = dataFeed.isNew();

		if (!(dataFeed instanceof DataFeedModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dataFeed.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(dataFeed);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dataFeed proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DataFeed implementation " +
					dataFeed.getClass());
		}

		DataFeedModelImpl dataFeedModelImpl = (DataFeedModelImpl)dataFeed;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (dataFeed.getCreateDate() == null)) {
			if (serviceContext == null) {
				dataFeed.setCreateDate(date);
			}
			else {
				dataFeed.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!dataFeedModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dataFeed.setModifiedDate(date);
			}
			else {
				dataFeed.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dataFeed);
			}
			else {
				dataFeed = (DataFeed)session.merge(dataFeed);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DataFeedImpl.class, dataFeedModelImpl, false, true);

		cacheUniqueFindersCache(dataFeedModelImpl);

		if (isNew) {
			dataFeed.setNew(false);
		}

		dataFeed.resetOriginalValues();

		return dataFeed;
	}

	/**
	 * Returns the data feed with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data feed
	 * @return the data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataFeedException {

		DataFeed dataFeed = fetchByPrimaryKey(primaryKey);

		if (dataFeed == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataFeedException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dataFeed;
	}

	/**
	 * Returns the data feed with the primary key or throws a <code>NoSuchDataFeedException</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed
	 * @throws NoSuchDataFeedException if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed findByPrimaryKey(long dataFeedId)
		throws NoSuchDataFeedException {

		return findByPrimaryKey((Serializable)dataFeedId);
	}

	/**
	 * Returns the data feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataFeedId the primary key of the data feed
	 * @return the data feed, or <code>null</code> if a data feed with the primary key could not be found
	 */
	@Override
	public DataFeed fetchByPrimaryKey(long dataFeedId) {
		return fetchByPrimaryKey((Serializable)dataFeedId);
	}

	/**
	 * Returns all the data feeds.
	 *
	 * @return the data feeds
	 */
	@Override
	public List<DataFeed> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @return the range of data feeds
	 */
	@Override
	public List<DataFeed> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data feeds
	 */
	@Override
	public List<DataFeed> findAll(
		int start, int end, OrderByComparator<DataFeed> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the data feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataFeedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data feeds
	 * @param end the upper bound of the range of data feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of data feeds
	 */
	@Override
	public List<DataFeed> findAll(
		int start, int end, OrderByComparator<DataFeed> orderByComparator,
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

		List<DataFeed> list = null;

		if (useFinderCache) {
			list = (List<DataFeed>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DATAFEED);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DATAFEED;

				sql = sql.concat(DataFeedModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DataFeed>)QueryUtil.list(
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
	 * Removes all the data feeds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DataFeed dataFeed : findAll()) {
			remove(dataFeed);
		}
	}

	/**
	 * Returns the number of data feeds.
	 *
	 * @return the number of data feeds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DATAFEED);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "dataFeedId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DATAFEED;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DataFeedModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the data feed persistence.
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

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
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

		_finderPathFetchByFeedKey = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFeedKey",
			new String[] {String.class.getName()}, new String[] {"feedKey"},
			true);

		_finderPathCountByFeedKey = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFeedKey",
			new String[] {String.class.getName()}, new String[] {"feedKey"},
			false);

		_setDataFeedUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setDataFeedUtilPersistence(null);

		entityCache.removeCache(DataFeedImpl.class.getName());
	}

	private void _setDataFeedUtilPersistence(
		DataFeedPersistence dataFeedPersistence) {

		try {
			Field field = DataFeedUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, dataFeedPersistence);
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

	private static final String _SQL_SELECT_DATAFEED =
		"SELECT dataFeed FROM DataFeed dataFeed";

	private static final String _SQL_SELECT_DATAFEED_WHERE =
		"SELECT dataFeed FROM DataFeed dataFeed WHERE ";

	private static final String _SQL_COUNT_DATAFEED =
		"SELECT COUNT(dataFeed) FROM DataFeed dataFeed";

	private static final String _SQL_COUNT_DATAFEED_WHERE =
		"SELECT COUNT(dataFeed) FROM DataFeed dataFeed WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dataFeed.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DataFeed exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DataFeed exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DataFeedPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"active"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}