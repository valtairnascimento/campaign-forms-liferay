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

import io.github.valtairnascimento.campaignforms.exception.NoSuchPageConfigurationException;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.model.PageConfigurationTable;
import io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationImpl;
import io.github.valtairnascimento.campaignforms.model.impl.PageConfigurationModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.PageConfigurationPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.PageConfigurationUtil;
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
 * The persistence implementation for the page configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = PageConfigurationPersistence.class)
public class PageConfigurationPersistenceImpl
	extends BasePersistenceImpl<PageConfiguration>
	implements PageConfigurationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PageConfigurationUtil</code> to access the page configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PageConfigurationImpl.class.getName();

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
	 * Returns all the page configurations where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching page configurations
	 */
	@Override
	public List<PageConfiguration> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @return the range of matching page configurations
	 */
	@Override
	public List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end) {

		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page configurations
	 */
	@Override
	public List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the page configurations where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page configurations
	 */
	@Override
	public List<PageConfiguration> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator,
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

		List<PageConfiguration> list = null;

		if (useFinderCache) {
			list = (List<PageConfiguration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PageConfiguration pageConfiguration : list) {
					if (campaignId != pageConfiguration.getCampaignId()) {
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

			sb.append(_SQL_SELECT_PAGECONFIGURATION_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PageConfigurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<PageConfiguration>)QueryUtil.list(
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
	 * Returns the first page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration findByCampaignId_First(
			long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = fetchByCampaignId_First(
			campaignId, orderByComparator);

		if (pageConfiguration != null) {
			return pageConfiguration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchPageConfigurationException(sb.toString());
	}

	/**
	 * Returns the first page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration fetchByCampaignId_First(
		long campaignId,
		OrderByComparator<PageConfiguration> orderByComparator) {

		List<PageConfiguration> list = findByCampaignId(
			campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration findByCampaignId_Last(
			long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = fetchByCampaignId_Last(
			campaignId, orderByComparator);

		if (pageConfiguration != null) {
			return pageConfiguration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchPageConfigurationException(sb.toString());
	}

	/**
	 * Returns the last page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration fetchByCampaignId_Last(
		long campaignId,
		OrderByComparator<PageConfiguration> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<PageConfiguration> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the page configurations before and after the current page configuration in the ordered set where campaignId = &#63;.
	 *
	 * @param pageConfigurationId the primary key of the current page configuration
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page configuration
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration[] findByCampaignId_PrevAndNext(
			long pageConfigurationId, long campaignId,
			OrderByComparator<PageConfiguration> orderByComparator)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = findByPrimaryKey(
			pageConfigurationId);

		Session session = null;

		try {
			session = openSession();

			PageConfiguration[] array = new PageConfigurationImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, pageConfiguration, campaignId, orderByComparator,
				true);

			array[1] = pageConfiguration;

			array[2] = getByCampaignId_PrevAndNext(
				session, pageConfiguration, campaignId, orderByComparator,
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

	protected PageConfiguration getByCampaignId_PrevAndNext(
		Session session, PageConfiguration pageConfiguration, long campaignId,
		OrderByComparator<PageConfiguration> orderByComparator,
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

		sb.append(_SQL_SELECT_PAGECONFIGURATION_WHERE);

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
			sb.append(PageConfigurationModelImpl.ORDER_BY_JPQL);
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
						pageConfiguration)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PageConfiguration> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the page configurations where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (PageConfiguration pageConfiguration :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(pageConfiguration);
		}
	}

	/**
	 * Returns the number of page configurations where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching page configurations
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PAGECONFIGURATION_WHERE);

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
		"pageConfiguration.campaignId = ?";

	private FinderPath _finderPathFetchByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or throws a <code>NoSuchPageConfigurationException</code> if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the matching page configuration
	 * @throws NoSuchPageConfigurationException if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration findByC_S(long campaignId, String stage)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = fetchByC_S(campaignId, stage);

		if (pageConfiguration == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("campaignId=");
			sb.append(campaignId);

			sb.append(", stage=");
			sb.append(stage);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPageConfigurationException(sb.toString());
		}

		return pageConfiguration;
	}

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration fetchByC_S(long campaignId, String stage) {
		return fetchByC_S(campaignId, stage, true);
	}

	/**
	 * Returns the page configuration where campaignId = &#63; and stage = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page configuration, or <code>null</code> if a matching page configuration could not be found
	 */
	@Override
	public PageConfiguration fetchByC_S(
		long campaignId, String stage, boolean useFinderCache) {

		stage = Objects.toString(stage, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {campaignId, stage};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_S, finderArgs, this);
		}

		if (result instanceof PageConfiguration) {
			PageConfiguration pageConfiguration = (PageConfiguration)result;

			if ((campaignId != pageConfiguration.getCampaignId()) ||
				!Objects.equals(stage, pageConfiguration.getStage())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_PAGECONFIGURATION_WHERE);

			sb.append(_FINDER_COLUMN_C_S_CAMPAIGNID_2);

			boolean bindStage = false;

			if (stage.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_S_STAGE_3);
			}
			else {
				bindStage = true;

				sb.append(_FINDER_COLUMN_C_S_STAGE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindStage) {
					queryPos.add(stage);
				}

				List<PageConfiguration> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_S, finderArgs, list);
					}
				}
				else {
					PageConfiguration pageConfiguration = list.get(0);

					result = pageConfiguration;

					cacheResult(pageConfiguration);
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
			return (PageConfiguration)result;
		}
	}

	/**
	 * Removes the page configuration where campaignId = &#63; and stage = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the page configuration that was removed
	 */
	@Override
	public PageConfiguration removeByC_S(long campaignId, String stage)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = findByC_S(campaignId, stage);

		return remove(pageConfiguration);
	}

	/**
	 * Returns the number of page configurations where campaignId = &#63; and stage = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param stage the stage
	 * @return the number of matching page configurations
	 */
	@Override
	public int countByC_S(long campaignId, String stage) {
		stage = Objects.toString(stage, "");

		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {campaignId, stage};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PAGECONFIGURATION_WHERE);

			sb.append(_FINDER_COLUMN_C_S_CAMPAIGNID_2);

			boolean bindStage = false;

			if (stage.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_S_STAGE_3);
			}
			else {
				bindStage = true;

				sb.append(_FINDER_COLUMN_C_S_STAGE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindStage) {
					queryPos.add(stage);
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

	private static final String _FINDER_COLUMN_C_S_CAMPAIGNID_2 =
		"pageConfiguration.campaignId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_STAGE_2 =
		"pageConfiguration.stage = ?";

	private static final String _FINDER_COLUMN_C_S_STAGE_3 =
		"(pageConfiguration.stage IS NULL OR pageConfiguration.stage = '')";

	public PageConfigurationPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);

		setModelClass(PageConfiguration.class);

		setModelImplClass(PageConfigurationImpl.class);
		setModelPKClass(long.class);

		setTable(PageConfigurationTable.INSTANCE);
	}

	/**
	 * Caches the page configuration in the entity cache if it is enabled.
	 *
	 * @param pageConfiguration the page configuration
	 */
	@Override
	public void cacheResult(PageConfiguration pageConfiguration) {
		entityCache.putResult(
			PageConfigurationImpl.class, pageConfiguration.getPrimaryKey(),
			pageConfiguration);

		finderCache.putResult(
			_finderPathFetchByC_S,
			new Object[] {
				pageConfiguration.getCampaignId(), pageConfiguration.getStage()
			},
			pageConfiguration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the page configurations in the entity cache if it is enabled.
	 *
	 * @param pageConfigurations the page configurations
	 */
	@Override
	public void cacheResult(List<PageConfiguration> pageConfigurations) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (pageConfigurations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PageConfiguration pageConfiguration : pageConfigurations) {
			if (entityCache.getResult(
					PageConfigurationImpl.class,
					pageConfiguration.getPrimaryKey()) == null) {

				cacheResult(pageConfiguration);
			}
		}
	}

	/**
	 * Clears the cache for all page configurations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PageConfigurationImpl.class);

		finderCache.clearCache(PageConfigurationImpl.class);
	}

	/**
	 * Clears the cache for the page configuration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PageConfiguration pageConfiguration) {
		entityCache.removeResult(
			PageConfigurationImpl.class, pageConfiguration);
	}

	@Override
	public void clearCache(List<PageConfiguration> pageConfigurations) {
		for (PageConfiguration pageConfiguration : pageConfigurations) {
			entityCache.removeResult(
				PageConfigurationImpl.class, pageConfiguration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PageConfigurationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PageConfigurationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		PageConfigurationModelImpl pageConfigurationModelImpl) {

		Object[] args = new Object[] {
			pageConfigurationModelImpl.getCampaignId(),
			pageConfigurationModelImpl.getStage()
		};

		finderCache.putResult(_finderPathCountByC_S, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByC_S, args, pageConfigurationModelImpl);
	}

	/**
	 * Creates a new page configuration with the primary key. Does not add the page configuration to the database.
	 *
	 * @param pageConfigurationId the primary key for the new page configuration
	 * @return the new page configuration
	 */
	@Override
	public PageConfiguration create(long pageConfigurationId) {
		PageConfiguration pageConfiguration = new PageConfigurationImpl();

		pageConfiguration.setNew(true);
		pageConfiguration.setPrimaryKey(pageConfigurationId);

		pageConfiguration.setCompanyId(CompanyThreadLocal.getCompanyId());

		return pageConfiguration;
	}

	/**
	 * Removes the page configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration that was removed
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration remove(long pageConfigurationId)
		throws NoSuchPageConfigurationException {

		return remove((Serializable)pageConfigurationId);
	}

	/**
	 * Removes the page configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the page configuration
	 * @return the page configuration that was removed
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration remove(Serializable primaryKey)
		throws NoSuchPageConfigurationException {

		Session session = null;

		try {
			session = openSession();

			PageConfiguration pageConfiguration =
				(PageConfiguration)session.get(
					PageConfigurationImpl.class, primaryKey);

			if (pageConfiguration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageConfigurationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(pageConfiguration);
		}
		catch (NoSuchPageConfigurationException noSuchEntityException) {
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
	protected PageConfiguration removeImpl(
		PageConfiguration pageConfiguration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pageConfiguration)) {
				pageConfiguration = (PageConfiguration)session.get(
					PageConfigurationImpl.class,
					pageConfiguration.getPrimaryKeyObj());
			}

			if (pageConfiguration != null) {
				session.delete(pageConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (pageConfiguration != null) {
			clearCache(pageConfiguration);
		}

		return pageConfiguration;
	}

	@Override
	public PageConfiguration updateImpl(PageConfiguration pageConfiguration) {
		boolean isNew = pageConfiguration.isNew();

		if (!(pageConfiguration instanceof PageConfigurationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(pageConfiguration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					pageConfiguration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in pageConfiguration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PageConfiguration implementation " +
					pageConfiguration.getClass());
		}

		PageConfigurationModelImpl pageConfigurationModelImpl =
			(PageConfigurationModelImpl)pageConfiguration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (pageConfiguration.getCreateDate() == null)) {
			if (serviceContext == null) {
				pageConfiguration.setCreateDate(date);
			}
			else {
				pageConfiguration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!pageConfigurationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pageConfiguration.setModifiedDate(date);
			}
			else {
				pageConfiguration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(pageConfiguration);
			}
			else {
				pageConfiguration = (PageConfiguration)session.merge(
					pageConfiguration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PageConfigurationImpl.class, pageConfigurationModelImpl, false,
			true);

		cacheUniqueFindersCache(pageConfigurationModelImpl);

		if (isNew) {
			pageConfiguration.setNew(false);
		}

		pageConfiguration.resetOriginalValues();

		return pageConfiguration;
	}

	/**
	 * Returns the page configuration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the page configuration
	 * @return the page configuration
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageConfigurationException {

		PageConfiguration pageConfiguration = fetchByPrimaryKey(primaryKey);

		if (pageConfiguration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageConfigurationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return pageConfiguration;
	}

	/**
	 * Returns the page configuration with the primary key or throws a <code>NoSuchPageConfigurationException</code> if it could not be found.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration
	 * @throws NoSuchPageConfigurationException if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration findByPrimaryKey(long pageConfigurationId)
		throws NoSuchPageConfigurationException {

		return findByPrimaryKey((Serializable)pageConfigurationId);
	}

	/**
	 * Returns the page configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageConfigurationId the primary key of the page configuration
	 * @return the page configuration, or <code>null</code> if a page configuration with the primary key could not be found
	 */
	@Override
	public PageConfiguration fetchByPrimaryKey(long pageConfigurationId) {
		return fetchByPrimaryKey((Serializable)pageConfigurationId);
	}

	/**
	 * Returns all the page configurations.
	 *
	 * @return the page configurations
	 */
	@Override
	public List<PageConfiguration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @return the range of page configurations
	 */
	@Override
	public List<PageConfiguration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of page configurations
	 */
	@Override
	public List<PageConfiguration> findAll(
		int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the page configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page configurations
	 * @param end the upper bound of the range of page configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of page configurations
	 */
	@Override
	public List<PageConfiguration> findAll(
		int start, int end,
		OrderByComparator<PageConfiguration> orderByComparator,
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

		List<PageConfiguration> list = null;

		if (useFinderCache) {
			list = (List<PageConfiguration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PAGECONFIGURATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PAGECONFIGURATION;

				sql = sql.concat(PageConfigurationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PageConfiguration>)QueryUtil.list(
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
	 * Removes all the page configurations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PageConfiguration pageConfiguration : findAll()) {
			remove(pageConfiguration);
		}
	}

	/**
	 * Returns the number of page configurations.
	 *
	 * @return the number of page configurations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PAGECONFIGURATION);

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
		return "pageConfigurationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PAGECONFIGURATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PageConfigurationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the page configuration persistence.
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

		_finderPathFetchByC_S = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_S",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "stage"}, true);

		_finderPathCountByC_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "stage"}, false);

		_setPageConfigurationUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setPageConfigurationUtilPersistence(null);

		entityCache.removeCache(PageConfigurationImpl.class.getName());
	}

	private void _setPageConfigurationUtilPersistence(
		PageConfigurationPersistence pageConfigurationPersistence) {

		try {
			Field field = PageConfigurationUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, pageConfigurationPersistence);
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

	private static final String _SQL_SELECT_PAGECONFIGURATION =
		"SELECT pageConfiguration FROM PageConfiguration pageConfiguration";

	private static final String _SQL_SELECT_PAGECONFIGURATION_WHERE =
		"SELECT pageConfiguration FROM PageConfiguration pageConfiguration WHERE ";

	private static final String _SQL_COUNT_PAGECONFIGURATION =
		"SELECT COUNT(pageConfiguration) FROM PageConfiguration pageConfiguration";

	private static final String _SQL_COUNT_PAGECONFIGURATION_WHERE =
		"SELECT COUNT(pageConfiguration) FROM PageConfiguration pageConfiguration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "pageConfiguration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PageConfiguration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PageConfiguration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PageConfigurationPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}