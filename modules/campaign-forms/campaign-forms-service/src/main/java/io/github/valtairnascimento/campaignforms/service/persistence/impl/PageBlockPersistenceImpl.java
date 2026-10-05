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

import io.github.valtairnascimento.campaignforms.exception.NoSuchPageBlockException;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageBlockTable;
import io.github.valtairnascimento.campaignforms.model.impl.PageBlockImpl;
import io.github.valtairnascimento.campaignforms.model.impl.PageBlockModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.PageBlockPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.PageBlockUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the page block service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = PageBlockPersistence.class)
public class PageBlockPersistenceImpl
	extends BasePersistenceImpl<PageBlock> implements PageBlockPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PageBlockUtil</code> to access the page block persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PageBlockImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByPageConfigurationId;
	private FinderPath _finderPathWithoutPaginationFindByPageConfigurationId;
	private FinderPath _finderPathCountByPageConfigurationId;

	/**
	 * Returns all the page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the matching page blocks
	 */
	@Override
	public List<PageBlock> findByPageConfigurationId(long pageConfigurationId) {
		return findByPageConfigurationId(
			pageConfigurationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @return the range of matching page blocks
	 */
	@Override
	public List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end) {

		return findByPageConfigurationId(pageConfigurationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching page blocks
	 */
	@Override
	public List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		OrderByComparator<PageBlock> orderByComparator) {

		return findByPageConfigurationId(
			pageConfigurationId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the page blocks where pageConfigurationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching page blocks
	 */
	@Override
	public List<PageBlock> findByPageConfigurationId(
		long pageConfigurationId, int start, int end,
		OrderByComparator<PageBlock> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByPageConfigurationId;
				finderArgs = new Object[] {pageConfigurationId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPageConfigurationId;
			finderArgs = new Object[] {
				pageConfigurationId, start, end, orderByComparator
			};
		}

		List<PageBlock> list = null;

		if (useFinderCache) {
			list = (List<PageBlock>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PageBlock pageBlock : list) {
					if (pageConfigurationId !=
							pageBlock.getPageConfigurationId()) {

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

			sb.append(_SQL_SELECT_PAGEBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_PAGECONFIGURATIONID_PAGECONFIGURATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PageBlockModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(pageConfigurationId);

				list = (List<PageBlock>)QueryUtil.list(
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
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	@Override
	public PageBlock findByPageConfigurationId_First(
			long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = fetchByPageConfigurationId_First(
			pageConfigurationId, orderByComparator);

		if (pageBlock != null) {
			return pageBlock;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("pageConfigurationId=");
		sb.append(pageConfigurationId);

		sb.append("}");

		throw new NoSuchPageBlockException(sb.toString());
	}

	/**
	 * Returns the first page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching page block, or <code>null</code> if a matching page block could not be found
	 */
	@Override
	public PageBlock fetchByPageConfigurationId_First(
		long pageConfigurationId,
		OrderByComparator<PageBlock> orderByComparator) {

		List<PageBlock> list = findByPageConfigurationId(
			pageConfigurationId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	@Override
	public PageBlock findByPageConfigurationId_Last(
			long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = fetchByPageConfigurationId_Last(
			pageConfigurationId, orderByComparator);

		if (pageBlock != null) {
			return pageBlock;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("pageConfigurationId=");
		sb.append(pageConfigurationId);

		sb.append("}");

		throw new NoSuchPageBlockException(sb.toString());
	}

	/**
	 * Returns the last page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching page block, or <code>null</code> if a matching page block could not be found
	 */
	@Override
	public PageBlock fetchByPageConfigurationId_Last(
		long pageConfigurationId,
		OrderByComparator<PageBlock> orderByComparator) {

		int count = countByPageConfigurationId(pageConfigurationId);

		if (count == 0) {
			return null;
		}

		List<PageBlock> list = findByPageConfigurationId(
			pageConfigurationId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the page blocks before and after the current page block in the ordered set where pageConfigurationId = &#63;.
	 *
	 * @param pageBlockId the primary key of the current page block
	 * @param pageConfigurationId the page configuration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock[] findByPageConfigurationId_PrevAndNext(
			long pageBlockId, long pageConfigurationId,
			OrderByComparator<PageBlock> orderByComparator)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = findByPrimaryKey(pageBlockId);

		Session session = null;

		try {
			session = openSession();

			PageBlock[] array = new PageBlockImpl[3];

			array[0] = getByPageConfigurationId_PrevAndNext(
				session, pageBlock, pageConfigurationId, orderByComparator,
				true);

			array[1] = pageBlock;

			array[2] = getByPageConfigurationId_PrevAndNext(
				session, pageBlock, pageConfigurationId, orderByComparator,
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

	protected PageBlock getByPageConfigurationId_PrevAndNext(
		Session session, PageBlock pageBlock, long pageConfigurationId,
		OrderByComparator<PageBlock> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_PAGEBLOCK_WHERE);

		sb.append(_FINDER_COLUMN_PAGECONFIGURATIONID_PAGECONFIGURATIONID_2);

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
			sb.append(PageBlockModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(pageConfigurationId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(pageBlock)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PageBlock> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the page blocks where pageConfigurationId = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 */
	@Override
	public void removeByPageConfigurationId(long pageConfigurationId) {
		for (PageBlock pageBlock :
				findByPageConfigurationId(
					pageConfigurationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(pageBlock);
		}
	}

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @return the number of matching page blocks
	 */
	@Override
	public int countByPageConfigurationId(long pageConfigurationId) {
		FinderPath finderPath = _finderPathCountByPageConfigurationId;

		Object[] finderArgs = new Object[] {pageConfigurationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PAGEBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_PAGECONFIGURATIONID_PAGECONFIGURATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(pageConfigurationId);

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

	private static final String
		_FINDER_COLUMN_PAGECONFIGURATIONID_PAGECONFIGURATIONID_2 =
			"pageBlock.pageConfigurationId = ?";

	private FinderPath _finderPathFetchByPC_BK;
	private FinderPath _finderPathCountByPC_BK;

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block
	 * @throws NoSuchPageBlockException if a matching page block could not be found
	 */
	@Override
	public PageBlock findByPC_BK(long pageConfigurationId, String blockKey)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = fetchByPC_BK(pageConfigurationId, blockKey);

		if (pageBlock == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("pageConfigurationId=");
			sb.append(pageConfigurationId);

			sb.append(", blockKey=");
			sb.append(blockKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPageBlockException(sb.toString());
		}

		return pageBlock;
	}

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	@Override
	public PageBlock fetchByPC_BK(long pageConfigurationId, String blockKey) {
		return fetchByPC_BK(pageConfigurationId, blockKey, true);
	}

	/**
	 * Returns the page block where pageConfigurationId = &#63; and blockKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching page block, or <code>null</code> if a matching page block could not be found
	 */
	@Override
	public PageBlock fetchByPC_BK(
		long pageConfigurationId, String blockKey, boolean useFinderCache) {

		blockKey = Objects.toString(blockKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {pageConfigurationId, blockKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByPC_BK, finderArgs, this);
		}

		if (result instanceof PageBlock) {
			PageBlock pageBlock = (PageBlock)result;

			if ((pageConfigurationId != pageBlock.getPageConfigurationId()) ||
				!Objects.equals(blockKey, pageBlock.getBlockKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_PAGEBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_PC_BK_PAGECONFIGURATIONID_2);

			boolean bindBlockKey = false;

			if (blockKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PC_BK_BLOCKKEY_3);
			}
			else {
				bindBlockKey = true;

				sb.append(_FINDER_COLUMN_PC_BK_BLOCKKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(pageConfigurationId);

				if (bindBlockKey) {
					queryPos.add(blockKey);
				}

				List<PageBlock> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByPC_BK, finderArgs, list);
					}
				}
				else {
					PageBlock pageBlock = list.get(0);

					result = pageBlock;

					cacheResult(pageBlock);
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
			return (PageBlock)result;
		}
	}

	/**
	 * Removes the page block where pageConfigurationId = &#63; and blockKey = &#63; from the database.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the page block that was removed
	 */
	@Override
	public PageBlock removeByPC_BK(long pageConfigurationId, String blockKey)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = findByPC_BK(pageConfigurationId, blockKey);

		return remove(pageBlock);
	}

	/**
	 * Returns the number of page blocks where pageConfigurationId = &#63; and blockKey = &#63;.
	 *
	 * @param pageConfigurationId the page configuration ID
	 * @param blockKey the block key
	 * @return the number of matching page blocks
	 */
	@Override
	public int countByPC_BK(long pageConfigurationId, String blockKey) {
		blockKey = Objects.toString(blockKey, "");

		FinderPath finderPath = _finderPathCountByPC_BK;

		Object[] finderArgs = new Object[] {pageConfigurationId, blockKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PAGEBLOCK_WHERE);

			sb.append(_FINDER_COLUMN_PC_BK_PAGECONFIGURATIONID_2);

			boolean bindBlockKey = false;

			if (blockKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PC_BK_BLOCKKEY_3);
			}
			else {
				bindBlockKey = true;

				sb.append(_FINDER_COLUMN_PC_BK_BLOCKKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(pageConfigurationId);

				if (bindBlockKey) {
					queryPos.add(blockKey);
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

	private static final String _FINDER_COLUMN_PC_BK_PAGECONFIGURATIONID_2 =
		"pageBlock.pageConfigurationId = ? AND ";

	private static final String _FINDER_COLUMN_PC_BK_BLOCKKEY_2 =
		"pageBlock.blockKey = ?";

	private static final String _FINDER_COLUMN_PC_BK_BLOCKKEY_3 =
		"(pageBlock.blockKey IS NULL OR pageBlock.blockKey = '')";

	public PageBlockPersistenceImpl() {
		setModelClass(PageBlock.class);

		setModelImplClass(PageBlockImpl.class);
		setModelPKClass(long.class);

		setTable(PageBlockTable.INSTANCE);
	}

	/**
	 * Caches the page block in the entity cache if it is enabled.
	 *
	 * @param pageBlock the page block
	 */
	@Override
	public void cacheResult(PageBlock pageBlock) {
		entityCache.putResult(
			PageBlockImpl.class, pageBlock.getPrimaryKey(), pageBlock);

		finderCache.putResult(
			_finderPathFetchByPC_BK,
			new Object[] {
				pageBlock.getPageConfigurationId(), pageBlock.getBlockKey()
			},
			pageBlock);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the page blocks in the entity cache if it is enabled.
	 *
	 * @param pageBlocks the page blocks
	 */
	@Override
	public void cacheResult(List<PageBlock> pageBlocks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (pageBlocks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PageBlock pageBlock : pageBlocks) {
			if (entityCache.getResult(
					PageBlockImpl.class, pageBlock.getPrimaryKey()) == null) {

				cacheResult(pageBlock);
			}
		}
	}

	/**
	 * Clears the cache for all page blocks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PageBlockImpl.class);

		finderCache.clearCache(PageBlockImpl.class);
	}

	/**
	 * Clears the cache for the page block.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PageBlock pageBlock) {
		entityCache.removeResult(PageBlockImpl.class, pageBlock);
	}

	@Override
	public void clearCache(List<PageBlock> pageBlocks) {
		for (PageBlock pageBlock : pageBlocks) {
			entityCache.removeResult(PageBlockImpl.class, pageBlock);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PageBlockImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PageBlockImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		PageBlockModelImpl pageBlockModelImpl) {

		Object[] args = new Object[] {
			pageBlockModelImpl.getPageConfigurationId(),
			pageBlockModelImpl.getBlockKey()
		};

		finderCache.putResult(_finderPathCountByPC_BK, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByPC_BK, args, pageBlockModelImpl);
	}

	/**
	 * Creates a new page block with the primary key. Does not add the page block to the database.
	 *
	 * @param pageBlockId the primary key for the new page block
	 * @return the new page block
	 */
	@Override
	public PageBlock create(long pageBlockId) {
		PageBlock pageBlock = new PageBlockImpl();

		pageBlock.setNew(true);
		pageBlock.setPrimaryKey(pageBlockId);

		pageBlock.setCompanyId(CompanyThreadLocal.getCompanyId());

		return pageBlock;
	}

	/**
	 * Removes the page block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block that was removed
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock remove(long pageBlockId) throws NoSuchPageBlockException {
		return remove((Serializable)pageBlockId);
	}

	/**
	 * Removes the page block with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the page block
	 * @return the page block that was removed
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock remove(Serializable primaryKey)
		throws NoSuchPageBlockException {

		Session session = null;

		try {
			session = openSession();

			PageBlock pageBlock = (PageBlock)session.get(
				PageBlockImpl.class, primaryKey);

			if (pageBlock == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPageBlockException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(pageBlock);
		}
		catch (NoSuchPageBlockException noSuchEntityException) {
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
	protected PageBlock removeImpl(PageBlock pageBlock) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pageBlock)) {
				pageBlock = (PageBlock)session.get(
					PageBlockImpl.class, pageBlock.getPrimaryKeyObj());
			}

			if (pageBlock != null) {
				session.delete(pageBlock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (pageBlock != null) {
			clearCache(pageBlock);
		}

		return pageBlock;
	}

	@Override
	public PageBlock updateImpl(PageBlock pageBlock) {
		boolean isNew = pageBlock.isNew();

		if (!(pageBlock instanceof PageBlockModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(pageBlock.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(pageBlock);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in pageBlock proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PageBlock implementation " +
					pageBlock.getClass());
		}

		PageBlockModelImpl pageBlockModelImpl = (PageBlockModelImpl)pageBlock;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (pageBlock.getCreateDate() == null)) {
			if (serviceContext == null) {
				pageBlock.setCreateDate(date);
			}
			else {
				pageBlock.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!pageBlockModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				pageBlock.setModifiedDate(date);
			}
			else {
				pageBlock.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(pageBlock);
			}
			else {
				pageBlock = (PageBlock)session.merge(pageBlock);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PageBlockImpl.class, pageBlockModelImpl, false, true);

		cacheUniqueFindersCache(pageBlockModelImpl);

		if (isNew) {
			pageBlock.setNew(false);
		}

		pageBlock.resetOriginalValues();

		return pageBlock;
	}

	/**
	 * Returns the page block with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the page block
	 * @return the page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPageBlockException {

		PageBlock pageBlock = fetchByPrimaryKey(primaryKey);

		if (pageBlock == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPageBlockException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return pageBlock;
	}

	/**
	 * Returns the page block with the primary key or throws a <code>NoSuchPageBlockException</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block
	 * @throws NoSuchPageBlockException if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock findByPrimaryKey(long pageBlockId)
		throws NoSuchPageBlockException {

		return findByPrimaryKey((Serializable)pageBlockId);
	}

	/**
	 * Returns the page block with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pageBlockId the primary key of the page block
	 * @return the page block, or <code>null</code> if a page block with the primary key could not be found
	 */
	@Override
	public PageBlock fetchByPrimaryKey(long pageBlockId) {
		return fetchByPrimaryKey((Serializable)pageBlockId);
	}

	/**
	 * Returns all the page blocks.
	 *
	 * @return the page blocks
	 */
	@Override
	public List<PageBlock> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @return the range of page blocks
	 */
	@Override
	public List<PageBlock> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of page blocks
	 */
	@Override
	public List<PageBlock> findAll(
		int start, int end, OrderByComparator<PageBlock> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the page blocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PageBlockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of page blocks
	 * @param end the upper bound of the range of page blocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of page blocks
	 */
	@Override
	public List<PageBlock> findAll(
		int start, int end, OrderByComparator<PageBlock> orderByComparator,
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

		List<PageBlock> list = null;

		if (useFinderCache) {
			list = (List<PageBlock>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PAGEBLOCK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PAGEBLOCK;

				sql = sql.concat(PageBlockModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PageBlock>)QueryUtil.list(
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
	 * Removes all the page blocks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PageBlock pageBlock : findAll()) {
			remove(pageBlock);
		}
	}

	/**
	 * Returns the number of page blocks.
	 *
	 * @return the number of page blocks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PAGEBLOCK);

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
		return "pageBlockId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PAGEBLOCK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PageBlockModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the page block persistence.
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

		_finderPathWithPaginationFindByPageConfigurationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPageConfigurationId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"pageConfigurationId"}, true);

		_finderPathWithoutPaginationFindByPageConfigurationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPageConfigurationId", new String[] {Long.class.getName()},
			new String[] {"pageConfigurationId"}, true);

		_finderPathCountByPageConfigurationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPageConfigurationId", new String[] {Long.class.getName()},
			new String[] {"pageConfigurationId"}, false);

		_finderPathFetchByPC_BK = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByPC_BK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"pageConfigurationId", "blockKey"}, true);

		_finderPathCountByPC_BK = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPC_BK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"pageConfigurationId", "blockKey"}, false);

		_setPageBlockUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setPageBlockUtilPersistence(null);

		entityCache.removeCache(PageBlockImpl.class.getName());
	}

	private void _setPageBlockUtilPersistence(
		PageBlockPersistence pageBlockPersistence) {

		try {
			Field field = PageBlockUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, pageBlockPersistence);
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

	private static final String _SQL_SELECT_PAGEBLOCK =
		"SELECT pageBlock FROM PageBlock pageBlock";

	private static final String _SQL_SELECT_PAGEBLOCK_WHERE =
		"SELECT pageBlock FROM PageBlock pageBlock WHERE ";

	private static final String _SQL_COUNT_PAGEBLOCK =
		"SELECT COUNT(pageBlock) FROM PageBlock pageBlock";

	private static final String _SQL_COUNT_PAGEBLOCK_WHERE =
		"SELECT COUNT(pageBlock) FROM PageBlock pageBlock WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "pageBlock.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PageBlock exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PageBlock exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PageBlockPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}