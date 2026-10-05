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

import io.github.valtairnascimento.campaignforms.exception.NoSuchIntegrationConfigException;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfigTable;
import io.github.valtairnascimento.campaignforms.model.impl.IntegrationConfigImpl;
import io.github.valtairnascimento.campaignforms.model.impl.IntegrationConfigModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.IntegrationConfigPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.IntegrationConfigUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the integration config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = IntegrationConfigPersistence.class)
public class IntegrationConfigPersistenceImpl
	extends BasePersistenceImpl<IntegrationConfig>
	implements IntegrationConfigPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>IntegrationConfigUtil</code> to access the integration config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		IntegrationConfigImpl.class.getName();

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
	 * Returns all the integration configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching integration configs
	 */
	@Override
	public List<IntegrationConfig> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @return the range of matching integration configs
	 */
	@Override
	public List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end) {

		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching integration configs
	 */
	@Override
	public List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<IntegrationConfig> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching integration configs
	 */
	@Override
	public List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<IntegrationConfig> orderByComparator,
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

		List<IntegrationConfig> list = null;

		if (useFinderCache) {
			list = (List<IntegrationConfig>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IntegrationConfig integrationConfig : list) {
					if (campaignId != integrationConfig.getCampaignId()) {
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

			sb.append(_SQL_SELECT_INTEGRATIONCONFIG_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(IntegrationConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<IntegrationConfig>)QueryUtil.list(
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
	 * Returns the first integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching integration config
	 * @throws NoSuchIntegrationConfigException if a matching integration config could not be found
	 */
	@Override
	public IntegrationConfig findByCampaignId_First(
			long campaignId,
			OrderByComparator<IntegrationConfig> orderByComparator)
		throws NoSuchIntegrationConfigException {

		IntegrationConfig integrationConfig = fetchByCampaignId_First(
			campaignId, orderByComparator);

		if (integrationConfig != null) {
			return integrationConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchIntegrationConfigException(sb.toString());
	}

	/**
	 * Returns the first integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching integration config, or <code>null</code> if a matching integration config could not be found
	 */
	@Override
	public IntegrationConfig fetchByCampaignId_First(
		long campaignId,
		OrderByComparator<IntegrationConfig> orderByComparator) {

		List<IntegrationConfig> list = findByCampaignId(
			campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching integration config
	 * @throws NoSuchIntegrationConfigException if a matching integration config could not be found
	 */
	@Override
	public IntegrationConfig findByCampaignId_Last(
			long campaignId,
			OrderByComparator<IntegrationConfig> orderByComparator)
		throws NoSuchIntegrationConfigException {

		IntegrationConfig integrationConfig = fetchByCampaignId_Last(
			campaignId, orderByComparator);

		if (integrationConfig != null) {
			return integrationConfig;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchIntegrationConfigException(sb.toString());
	}

	/**
	 * Returns the last integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching integration config, or <code>null</code> if a matching integration config could not be found
	 */
	@Override
	public IntegrationConfig fetchByCampaignId_Last(
		long campaignId,
		OrderByComparator<IntegrationConfig> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<IntegrationConfig> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the integration configs before and after the current integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param integrationConfigId the primary key of the current integration config
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next integration config
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig[] findByCampaignId_PrevAndNext(
			long integrationConfigId, long campaignId,
			OrderByComparator<IntegrationConfig> orderByComparator)
		throws NoSuchIntegrationConfigException {

		IntegrationConfig integrationConfig = findByPrimaryKey(
			integrationConfigId);

		Session session = null;

		try {
			session = openSession();

			IntegrationConfig[] array = new IntegrationConfigImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, integrationConfig, campaignId, orderByComparator,
				true);

			array[1] = integrationConfig;

			array[2] = getByCampaignId_PrevAndNext(
				session, integrationConfig, campaignId, orderByComparator,
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

	protected IntegrationConfig getByCampaignId_PrevAndNext(
		Session session, IntegrationConfig integrationConfig, long campaignId,
		OrderByComparator<IntegrationConfig> orderByComparator,
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

		sb.append(_SQL_SELECT_INTEGRATIONCONFIG_WHERE);

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
			sb.append(IntegrationConfigModelImpl.ORDER_BY_JPQL);
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
						integrationConfig)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<IntegrationConfig> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the integration configs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (IntegrationConfig integrationConfig :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(integrationConfig);
		}
	}

	/**
	 * Returns the number of integration configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching integration configs
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_INTEGRATIONCONFIG_WHERE);

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
		"integrationConfig.campaignId = ?";

	public IntegrationConfigPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);

		setModelClass(IntegrationConfig.class);

		setModelImplClass(IntegrationConfigImpl.class);
		setModelPKClass(long.class);

		setTable(IntegrationConfigTable.INSTANCE);
	}

	/**
	 * Caches the integration config in the entity cache if it is enabled.
	 *
	 * @param integrationConfig the integration config
	 */
	@Override
	public void cacheResult(IntegrationConfig integrationConfig) {
		entityCache.putResult(
			IntegrationConfigImpl.class, integrationConfig.getPrimaryKey(),
			integrationConfig);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the integration configs in the entity cache if it is enabled.
	 *
	 * @param integrationConfigs the integration configs
	 */
	@Override
	public void cacheResult(List<IntegrationConfig> integrationConfigs) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (integrationConfigs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (IntegrationConfig integrationConfig : integrationConfigs) {
			if (entityCache.getResult(
					IntegrationConfigImpl.class,
					integrationConfig.getPrimaryKey()) == null) {

				cacheResult(integrationConfig);
			}
		}
	}

	/**
	 * Clears the cache for all integration configs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IntegrationConfigImpl.class);

		finderCache.clearCache(IntegrationConfigImpl.class);
	}

	/**
	 * Clears the cache for the integration config.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IntegrationConfig integrationConfig) {
		entityCache.removeResult(
			IntegrationConfigImpl.class, integrationConfig);
	}

	@Override
	public void clearCache(List<IntegrationConfig> integrationConfigs) {
		for (IntegrationConfig integrationConfig : integrationConfigs) {
			entityCache.removeResult(
				IntegrationConfigImpl.class, integrationConfig);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(IntegrationConfigImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(IntegrationConfigImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new integration config with the primary key. Does not add the integration config to the database.
	 *
	 * @param integrationConfigId the primary key for the new integration config
	 * @return the new integration config
	 */
	@Override
	public IntegrationConfig create(long integrationConfigId) {
		IntegrationConfig integrationConfig = new IntegrationConfigImpl();

		integrationConfig.setNew(true);
		integrationConfig.setPrimaryKey(integrationConfigId);

		integrationConfig.setCompanyId(CompanyThreadLocal.getCompanyId());

		return integrationConfig;
	}

	/**
	 * Removes the integration config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config that was removed
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig remove(long integrationConfigId)
		throws NoSuchIntegrationConfigException {

		return remove((Serializable)integrationConfigId);
	}

	/**
	 * Removes the integration config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the integration config
	 * @return the integration config that was removed
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig remove(Serializable primaryKey)
		throws NoSuchIntegrationConfigException {

		Session session = null;

		try {
			session = openSession();

			IntegrationConfig integrationConfig =
				(IntegrationConfig)session.get(
					IntegrationConfigImpl.class, primaryKey);

			if (integrationConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIntegrationConfigException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(integrationConfig);
		}
		catch (NoSuchIntegrationConfigException noSuchEntityException) {
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
	protected IntegrationConfig removeImpl(
		IntegrationConfig integrationConfig) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(integrationConfig)) {
				integrationConfig = (IntegrationConfig)session.get(
					IntegrationConfigImpl.class,
					integrationConfig.getPrimaryKeyObj());
			}

			if (integrationConfig != null) {
				session.delete(integrationConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (integrationConfig != null) {
			clearCache(integrationConfig);
		}

		return integrationConfig;
	}

	@Override
	public IntegrationConfig updateImpl(IntegrationConfig integrationConfig) {
		boolean isNew = integrationConfig.isNew();

		if (!(integrationConfig instanceof IntegrationConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(integrationConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					integrationConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in integrationConfig proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom IntegrationConfig implementation " +
					integrationConfig.getClass());
		}

		IntegrationConfigModelImpl integrationConfigModelImpl =
			(IntegrationConfigModelImpl)integrationConfig;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (integrationConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				integrationConfig.setCreateDate(date);
			}
			else {
				integrationConfig.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!integrationConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				integrationConfig.setModifiedDate(date);
			}
			else {
				integrationConfig.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(integrationConfig);
			}
			else {
				integrationConfig = (IntegrationConfig)session.merge(
					integrationConfig);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			IntegrationConfigImpl.class, integrationConfigModelImpl, false,
			true);

		if (isNew) {
			integrationConfig.setNew(false);
		}

		integrationConfig.resetOriginalValues();

		return integrationConfig;
	}

	/**
	 * Returns the integration config with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the integration config
	 * @return the integration config
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIntegrationConfigException {

		IntegrationConfig integrationConfig = fetchByPrimaryKey(primaryKey);

		if (integrationConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIntegrationConfigException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return integrationConfig;
	}

	/**
	 * Returns the integration config with the primary key or throws a <code>NoSuchIntegrationConfigException</code> if it could not be found.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig findByPrimaryKey(long integrationConfigId)
		throws NoSuchIntegrationConfigException {

		return findByPrimaryKey((Serializable)integrationConfigId);
	}

	/**
	 * Returns the integration config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config, or <code>null</code> if a integration config with the primary key could not be found
	 */
	@Override
	public IntegrationConfig fetchByPrimaryKey(long integrationConfigId) {
		return fetchByPrimaryKey((Serializable)integrationConfigId);
	}

	/**
	 * Returns all the integration configs.
	 *
	 * @return the integration configs
	 */
	@Override
	public List<IntegrationConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @return the range of integration configs
	 */
	@Override
	public List<IntegrationConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of integration configs
	 */
	@Override
	public List<IntegrationConfig> findAll(
		int start, int end,
		OrderByComparator<IntegrationConfig> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of integration configs
	 */
	@Override
	public List<IntegrationConfig> findAll(
		int start, int end,
		OrderByComparator<IntegrationConfig> orderByComparator,
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

		List<IntegrationConfig> list = null;

		if (useFinderCache) {
			list = (List<IntegrationConfig>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_INTEGRATIONCONFIG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_INTEGRATIONCONFIG;

				sql = sql.concat(IntegrationConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<IntegrationConfig>)QueryUtil.list(
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
	 * Removes all the integration configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IntegrationConfig integrationConfig : findAll()) {
			remove(integrationConfig);
		}
	}

	/**
	 * Returns the number of integration configs.
	 *
	 * @return the number of integration configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_INTEGRATIONCONFIG);

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
		return "integrationConfigId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_INTEGRATIONCONFIG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return IntegrationConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the integration config persistence.
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

		_setIntegrationConfigUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setIntegrationConfigUtilPersistence(null);

		entityCache.removeCache(IntegrationConfigImpl.class.getName());
	}

	private void _setIntegrationConfigUtilPersistence(
		IntegrationConfigPersistence integrationConfigPersistence) {

		try {
			Field field = IntegrationConfigUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, integrationConfigPersistence);
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

	private static final String _SQL_SELECT_INTEGRATIONCONFIG =
		"SELECT integrationConfig FROM IntegrationConfig integrationConfig";

	private static final String _SQL_SELECT_INTEGRATIONCONFIG_WHERE =
		"SELECT integrationConfig FROM IntegrationConfig integrationConfig WHERE ";

	private static final String _SQL_COUNT_INTEGRATIONCONFIG =
		"SELECT COUNT(integrationConfig) FROM IntegrationConfig integrationConfig";

	private static final String _SQL_COUNT_INTEGRATIONCONFIG_WHERE =
		"SELECT COUNT(integrationConfig) FROM IntegrationConfig integrationConfig WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "integrationConfig.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No IntegrationConfig exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No IntegrationConfig exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		IntegrationConfigPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}