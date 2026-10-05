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

import io.github.valtairnascimento.campaignforms.exception.NoSuchNotificationConfigException;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.NotificationConfigTable;
import io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigImpl;
import io.github.valtairnascimento.campaignforms.model.impl.NotificationConfigModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.NotificationConfigPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.NotificationConfigUtil;
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
 * The persistence implementation for the notification config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = NotificationConfigPersistence.class)
public class NotificationConfigPersistenceImpl
	extends BasePersistenceImpl<NotificationConfig>
	implements NotificationConfigPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NotificationConfigUtil</code> to access the notification config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NotificationConfigImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCampaignId;
	private FinderPath _finderPathWithoutPaginationFindByCampaignId;
	private FinderPath _finderPathCountByCampaignId;

	/**
	 * Returns all the notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end) {

		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
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

		List<NotificationConfig> list = null;

		if (useFinderCache) {
			list = (List<NotificationConfig>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationConfig notificationConfig : list) {
					if (campaignId != notificationConfig.getCampaignId()) {
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

			sb.append(_SQL_SELECT_NOTIFICATIONCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<NotificationConfig>)QueryUtil.list(
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
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig findByCampaignId_First(
			long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = fetchByCampaignId_First(
			campaignId, orderByComparator);

		if (notificationConfig != null) {
			return notificationConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchNotificationConfigException(sb.toString());
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig fetchByCampaignId_First(
		long campaignId,
		OrderByComparator<NotificationConfig> orderByComparator) {

		List<NotificationConfig> list = findByCampaignId(
			campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig findByCampaignId_Last(
			long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = fetchByCampaignId_Last(
			campaignId, orderByComparator);

		if (notificationConfig != null) {
			return notificationConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchNotificationConfigException(sb.toString());
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig fetchByCampaignId_Last(
		long campaignId,
		OrderByComparator<NotificationConfig> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<NotificationConfig> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification configs before and after the current notification config in the ordered set where campaignId = &#63;.
	 *
	 * @param notificationConfigId the primary key of the current notification config
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig[] findByCampaignId_PrevAndNext(
			long notificationConfigId, long campaignId,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = findByPrimaryKey(
			notificationConfigId);

		Session session = null;

		try {
			session = openSession();

			NotificationConfig[] array = new NotificationConfigImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, notificationConfig, campaignId, orderByComparator,
				true);

			array[1] = notificationConfig;

			array[2] = getByCampaignId_PrevAndNext(
				session, notificationConfig, campaignId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationConfig getByCampaignId_PrevAndNext(
		Session session, NotificationConfig notificationConfig, long campaignId,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONCONFIG_WHERE);

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
			sb.append(NotificationConfigModelImpl.ORDER_BY_JPQL);
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
						notificationConfig)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationConfig> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification configs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (NotificationConfig notificationConfig :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(notificationConfig);
		}
	}

	/**
	 * Returns the number of notification configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching notification configs
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NOTIFICATIONCONFIG_WHERE);

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
		"notificationConfig.campaignId = ?";

	private FinderPath _finderPathWithPaginationFindByC_ET;
	private FinderPath _finderPathWithoutPaginationFindByC_ET;
	private FinderPath _finderPathCountByC_ET;

	/**
	 * Returns all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByC_ET(
		long campaignId, String eventType) {

		return findByC_ET(
			campaignId, eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end) {

		return findByC_ET(campaignId, eventType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return findByC_ET(
			campaignId, eventType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification configs
	 */
	@Override
	public List<NotificationConfig> findByC_ET(
		long campaignId, String eventType, int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean useFinderCache) {

		eventType = Objects.toString(eventType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_ET;
				finderArgs = new Object[] {campaignId, eventType};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_ET;
			finderArgs = new Object[] {
				campaignId, eventType, start, end, orderByComparator
			};
		}

		List<NotificationConfig> list = null;

		if (useFinderCache) {
			list = (List<NotificationConfig>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationConfig notificationConfig : list) {
					if ((campaignId != notificationConfig.getCampaignId()) ||
						!eventType.equals(notificationConfig.getEventType())) {

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

			sb.append(_SQL_SELECT_NOTIFICATIONCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_C_ET_CAMPAIGNID_2);

			boolean bindEventType = false;

			if (eventType.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindEventType) {
					queryPos.add(eventType);
				}

				list = (List<NotificationConfig>)QueryUtil.list(
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
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig findByC_ET_First(
			long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = fetchByC_ET_First(
			campaignId, eventType, orderByComparator);

		if (notificationConfig != null) {
			return notificationConfig;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append(", eventType=");
		sb.append(eventType);

		sb.append("}");

		throw new NoSuchNotificationConfigException(sb.toString());
	}

	/**
	 * Returns the first notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig fetchByC_ET_First(
		long campaignId, String eventType,
		OrderByComparator<NotificationConfig> orderByComparator) {

		List<NotificationConfig> list = findByC_ET(
			campaignId, eventType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config
	 * @throws NoSuchNotificationConfigException if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig findByC_ET_Last(
			long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = fetchByC_ET_Last(
			campaignId, eventType, orderByComparator);

		if (notificationConfig != null) {
			return notificationConfig;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append(", eventType=");
		sb.append(eventType);

		sb.append("}");

		throw new NoSuchNotificationConfigException(sb.toString());
	}

	/**
	 * Returns the last notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification config, or <code>null</code> if a matching notification config could not be found
	 */
	@Override
	public NotificationConfig fetchByC_ET_Last(
		long campaignId, String eventType,
		OrderByComparator<NotificationConfig> orderByComparator) {

		int count = countByC_ET(campaignId, eventType);

		if (count == 0) {
			return null;
		}

		List<NotificationConfig> list = findByC_ET(
			campaignId, eventType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification configs before and after the current notification config in the ordered set where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param notificationConfigId the primary key of the current notification config
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig[] findByC_ET_PrevAndNext(
			long notificationConfigId, long campaignId, String eventType,
			OrderByComparator<NotificationConfig> orderByComparator)
		throws NoSuchNotificationConfigException {

		eventType = Objects.toString(eventType, "");

		NotificationConfig notificationConfig = findByPrimaryKey(
			notificationConfigId);

		Session session = null;

		try {
			session = openSession();

			NotificationConfig[] array = new NotificationConfigImpl[3];

			array[0] = getByC_ET_PrevAndNext(
				session, notificationConfig, campaignId, eventType,
				orderByComparator, true);

			array[1] = notificationConfig;

			array[2] = getByC_ET_PrevAndNext(
				session, notificationConfig, campaignId, eventType,
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

	protected NotificationConfig getByC_ET_PrevAndNext(
		Session session, NotificationConfig notificationConfig, long campaignId,
		String eventType,
		OrderByComparator<NotificationConfig> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONCONFIG_WHERE);

		sb.append(_FINDER_COLUMN_C_ET_CAMPAIGNID_2);

		boolean bindEventType = false;

		if (eventType.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_3);
		}
		else {
			bindEventType = true;

			sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_2);
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
			sb.append(NotificationConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(campaignId);

		if (bindEventType) {
			queryPos.add(eventType);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationConfig)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationConfig> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification configs where campaignId = &#63; and eventType = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 */
	@Override
	public void removeByC_ET(long campaignId, String eventType) {
		for (NotificationConfig notificationConfig :
				findByC_ET(
					campaignId, eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(notificationConfig);
		}
	}

	/**
	 * Returns the number of notification configs where campaignId = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param eventType the event type
	 * @return the number of matching notification configs
	 */
	@Override
	public int countByC_ET(long campaignId, String eventType) {
		eventType = Objects.toString(eventType, "");

		FinderPath finderPath = _finderPathCountByC_ET;

		Object[] finderArgs = new Object[] {campaignId, eventType};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NOTIFICATIONCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_C_ET_CAMPAIGNID_2);

			boolean bindEventType = false;

			if (eventType.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				sb.append(_FINDER_COLUMN_C_ET_EVENTTYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindEventType) {
					queryPos.add(eventType);
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

	private static final String _FINDER_COLUMN_C_ET_CAMPAIGNID_2 =
		"notificationConfig.campaignId = ? AND ";

	private static final String _FINDER_COLUMN_C_ET_EVENTTYPE_2 =
		"notificationConfig.eventType = ?";

	private static final String _FINDER_COLUMN_C_ET_EVENTTYPE_3 =
		"(notificationConfig.eventType IS NULL OR notificationConfig.eventType = '')";

	public NotificationConfigPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);

		setModelClass(NotificationConfig.class);

		setModelImplClass(NotificationConfigImpl.class);
		setModelPKClass(long.class);

		setTable(NotificationConfigTable.INSTANCE);
	}

	/**
	 * Caches the notification config in the entity cache if it is enabled.
	 *
	 * @param notificationConfig the notification config
	 */
	@Override
	public void cacheResult(NotificationConfig notificationConfig) {
		entityCache.putResult(
			NotificationConfigImpl.class, notificationConfig.getPrimaryKey(),
			notificationConfig);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the notification configs in the entity cache if it is enabled.
	 *
	 * @param notificationConfigs the notification configs
	 */
	@Override
	public void cacheResult(List<NotificationConfig> notificationConfigs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (notificationConfigs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (NotificationConfig notificationConfig : notificationConfigs) {
			if (entityCache.getResult(
					NotificationConfigImpl.class,
					notificationConfig.getPrimaryKey()) == null) {

				cacheResult(notificationConfig);
			}
		}
	}

	/**
	 * Clears the cache for all notification configs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotificationConfigImpl.class);

		finderCache.clearCache(NotificationConfigImpl.class);
	}

	/**
	 * Clears the cache for the notification config.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotificationConfig notificationConfig) {
		entityCache.removeResult(
			NotificationConfigImpl.class, notificationConfig);
	}

	@Override
	public void clearCache(List<NotificationConfig> notificationConfigs) {
		for (NotificationConfig notificationConfig : notificationConfigs) {
			entityCache.removeResult(
				NotificationConfigImpl.class, notificationConfig);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(NotificationConfigImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NotificationConfigImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new notification config with the primary key. Does not add the notification config to the database.
	 *
	 * @param notificationConfigId the primary key for the new notification config
	 * @return the new notification config
	 */
	@Override
	public NotificationConfig create(long notificationConfigId) {
		NotificationConfig notificationConfig = new NotificationConfigImpl();

		notificationConfig.setNew(true);
		notificationConfig.setPrimaryKey(notificationConfigId);

		notificationConfig.setCompanyId(CompanyThreadLocal.getCompanyId());

		return notificationConfig;
	}

	/**
	 * Removes the notification config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config that was removed
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig remove(long notificationConfigId)
		throws NoSuchNotificationConfigException {

		return remove((Serializable)notificationConfigId);
	}

	/**
	 * Removes the notification config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notification config
	 * @return the notification config that was removed
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig remove(Serializable primaryKey)
		throws NoSuchNotificationConfigException {

		Session session = null;

		try {
			session = openSession();

			NotificationConfig notificationConfig =
				(NotificationConfig)session.get(
					NotificationConfigImpl.class, primaryKey);

			if (notificationConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationConfigException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(notificationConfig);
		}
		catch (NoSuchNotificationConfigException noSuchEntityException) {
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
	protected NotificationConfig removeImpl(
		NotificationConfig notificationConfig) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notificationConfig)) {
				notificationConfig = (NotificationConfig)session.get(
					NotificationConfigImpl.class,
					notificationConfig.getPrimaryKeyObj());
			}

			if (notificationConfig != null) {
				session.delete(notificationConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (notificationConfig != null) {
			clearCache(notificationConfig);
		}

		return notificationConfig;
	}

	@Override
	public NotificationConfig updateImpl(
		NotificationConfig notificationConfig) {

		boolean isNew = notificationConfig.isNew();

		if (!(notificationConfig instanceof NotificationConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notificationConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					notificationConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notificationConfig proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NotificationConfig implementation " +
					notificationConfig.getClass());
		}

		NotificationConfigModelImpl notificationConfigModelImpl =
			(NotificationConfigModelImpl)notificationConfig;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (notificationConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				notificationConfig.setCreateDate(date);
			}
			else {
				notificationConfig.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!notificationConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				notificationConfig.setModifiedDate(date);
			}
			else {
				notificationConfig.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(notificationConfig);
			}
			else {
				notificationConfig = (NotificationConfig)session.merge(
					notificationConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NotificationConfigImpl.class, notificationConfigModelImpl, false,
			true);

		if (isNew) {
			notificationConfig.setNew(false);
		}

		notificationConfig.resetOriginalValues();

		return notificationConfig;
	}

	/**
	 * Returns the notification config with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification config
	 * @return the notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationConfigException {

		NotificationConfig notificationConfig = fetchByPrimaryKey(primaryKey);

		if (notificationConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationConfigException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return notificationConfig;
	}

	/**
	 * Returns the notification config with the primary key or throws a <code>NoSuchNotificationConfigException</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config
	 * @throws NoSuchNotificationConfigException if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig findByPrimaryKey(long notificationConfigId)
		throws NoSuchNotificationConfigException {

		return findByPrimaryKey((Serializable)notificationConfigId);
	}

	/**
	 * Returns the notification config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationConfigId the primary key of the notification config
	 * @return the notification config, or <code>null</code> if a notification config with the primary key could not be found
	 */
	@Override
	public NotificationConfig fetchByPrimaryKey(long notificationConfigId) {
		return fetchByPrimaryKey((Serializable)notificationConfigId);
	}

	/**
	 * Returns all the notification configs.
	 *
	 * @return the notification configs
	 */
	@Override
	public List<NotificationConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @return the range of notification configs
	 */
	@Override
	public List<NotificationConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification configs
	 */
	@Override
	public List<NotificationConfig> findAll(
		int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification configs
	 * @param end the upper bound of the range of notification configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notification configs
	 */
	@Override
	public List<NotificationConfig> findAll(
		int start, int end,
		OrderByComparator<NotificationConfig> orderByComparator,
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

		List<NotificationConfig> list = null;

		if (useFinderCache) {
			list = (List<NotificationConfig>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NOTIFICATIONCONFIG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFICATIONCONFIG;

				sql = sql.concat(NotificationConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NotificationConfig>)QueryUtil.list(
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
	 * Removes all the notification configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NotificationConfig notificationConfig : findAll()) {
			remove(notificationConfig);
		}
	}

	/**
	 * Returns the number of notification configs.
	 *
	 * @return the number of notification configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_NOTIFICATIONCONFIG);

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
		return "notificationConfigId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NOTIFICATIONCONFIG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NotificationConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notification config persistence.
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

		_finderPathWithPaginationFindByC_ET = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_ET",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"campaignId", "eventType"}, true);

		_finderPathWithoutPaginationFindByC_ET = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_ET",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "eventType"}, true);

		_finderPathCountByC_ET = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ET",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "eventType"}, false);

		_setNotificationConfigUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setNotificationConfigUtilPersistence(null);

		entityCache.removeCache(NotificationConfigImpl.class.getName());
	}

	private void _setNotificationConfigUtilPersistence(
		NotificationConfigPersistence notificationConfigPersistence) {

		try {
			Field field = NotificationConfigUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, notificationConfigPersistence);
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

	private static final String _SQL_SELECT_NOTIFICATIONCONFIG =
		"SELECT notificationConfig FROM NotificationConfig notificationConfig";

	private static final String _SQL_SELECT_NOTIFICATIONCONFIG_WHERE =
		"SELECT notificationConfig FROM NotificationConfig notificationConfig WHERE ";

	private static final String _SQL_COUNT_NOTIFICATIONCONFIG =
		"SELECT COUNT(notificationConfig) FROM NotificationConfig notificationConfig";

	private static final String _SQL_COUNT_NOTIFICATIONCONFIG_WHERE =
		"SELECT COUNT(notificationConfig) FROM NotificationConfig notificationConfig WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "notificationConfig.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NotificationConfig exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NotificationConfig exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NotificationConfigPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}