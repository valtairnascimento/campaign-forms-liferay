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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import io.github.valtairnascimento.campaignforms.exception.NoSuchSubmissionValueException;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.model.SubmissionValueTable;
import io.github.valtairnascimento.campaignforms.model.impl.SubmissionValueImpl;
import io.github.valtairnascimento.campaignforms.model.impl.SubmissionValueModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.SubmissionValuePersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.SubmissionValueUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the submission value service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = SubmissionValuePersistence.class)
public class SubmissionValuePersistenceImpl
	extends BasePersistenceImpl<SubmissionValue>
	implements SubmissionValuePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubmissionValueUtil</code> to access the submission value persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubmissionValueImpl.class.getName();

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
	 * Returns all the submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the matching submission values
	 */
	@Override
	public List<SubmissionValue> findBySubmissionId(long submissionId) {
		return findBySubmissionId(
			submissionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end) {

		return findBySubmissionId(submissionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return findBySubmissionId(
			submissionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the submission values where submissionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param submissionId the submission ID
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findBySubmissionId(
		long submissionId, int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator,
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

		List<SubmissionValue> list = null;

		if (useFinderCache) {
			list = (List<SubmissionValue>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubmissionValue submissionValue : list) {
					if (submissionId != submissionValue.getSubmissionId()) {
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

			sb.append(_SQL_SELECT_SUBMISSIONVALUE_WHERE);

			sb.append(_FINDER_COLUMN_SUBMISSIONID_SUBMISSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubmissionValueModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(submissionId);

				list = (List<SubmissionValue>)QueryUtil.list(
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
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue findBySubmissionId_First(
			long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = fetchBySubmissionId_First(
			submissionId, orderByComparator);

		if (submissionValue != null) {
			return submissionValue;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("submissionId=");
		sb.append(submissionId);

		sb.append("}");

		throw new NoSuchSubmissionValueException(sb.toString());
	}

	/**
	 * Returns the first submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue fetchBySubmissionId_First(
		long submissionId,
		OrderByComparator<SubmissionValue> orderByComparator) {

		List<SubmissionValue> list = findBySubmissionId(
			submissionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue findBySubmissionId_Last(
			long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = fetchBySubmissionId_Last(
			submissionId, orderByComparator);

		if (submissionValue != null) {
			return submissionValue;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("submissionId=");
		sb.append(submissionId);

		sb.append("}");

		throw new NoSuchSubmissionValueException(sb.toString());
	}

	/**
	 * Returns the last submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue fetchBySubmissionId_Last(
		long submissionId,
		OrderByComparator<SubmissionValue> orderByComparator) {

		int count = countBySubmissionId(submissionId);

		if (count == 0) {
			return null;
		}

		List<SubmissionValue> list = findBySubmissionId(
			submissionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the submission values before and after the current submission value in the ordered set where submissionId = &#63;.
	 *
	 * @param submissionValueId the primary key of the current submission value
	 * @param submissionId the submission ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue[] findBySubmissionId_PrevAndNext(
			long submissionValueId, long submissionId,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = findByPrimaryKey(submissionValueId);

		Session session = null;

		try {
			session = openSession();

			SubmissionValue[] array = new SubmissionValueImpl[3];

			array[0] = getBySubmissionId_PrevAndNext(
				session, submissionValue, submissionId, orderByComparator,
				true);

			array[1] = submissionValue;

			array[2] = getBySubmissionId_PrevAndNext(
				session, submissionValue, submissionId, orderByComparator,
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

	protected SubmissionValue getBySubmissionId_PrevAndNext(
		Session session, SubmissionValue submissionValue, long submissionId,
		OrderByComparator<SubmissionValue> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBMISSIONVALUE_WHERE);

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
			sb.append(SubmissionValueModelImpl.ORDER_BY_JPQL);
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
						submissionValue)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubmissionValue> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the submission values where submissionId = &#63; from the database.
	 *
	 * @param submissionId the submission ID
	 */
	@Override
	public void removeBySubmissionId(long submissionId) {
		for (SubmissionValue submissionValue :
				findBySubmissionId(
					submissionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(submissionValue);
		}
	}

	/**
	 * Returns the number of submission values where submissionId = &#63;.
	 *
	 * @param submissionId the submission ID
	 * @return the number of matching submission values
	 */
	@Override
	public int countBySubmissionId(long submissionId) {
		FinderPath finderPath = _finderPathCountBySubmissionId;

		Object[] finderArgs = new Object[] {submissionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBMISSIONVALUE_WHERE);

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
		"submissionValue.submissionId = ?";

	private FinderPath _finderPathWithPaginationFindByC_FK_SV;
	private FinderPath _finderPathWithoutPaginationFindByC_FK_SV;
	private FinderPath _finderPathCountByC_FK_SV;

	/**
	 * Returns all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the matching submission values
	 */
	@Override
	public List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		return findByC_FK_SV(
			campaignId, fieldKey, stringValue, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end) {

		return findByC_FK_SV(
			campaignId, fieldKey, stringValue, start, end, null);
	}

	/**
	 * Returns an ordered range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end, OrderByComparator<SubmissionValue> orderByComparator) {

		return findByC_FK_SV(
			campaignId, fieldKey, stringValue, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching submission values
	 */
	@Override
	public List<SubmissionValue> findByC_FK_SV(
		long campaignId, String fieldKey, String stringValue, int start,
		int end, OrderByComparator<SubmissionValue> orderByComparator,
		boolean useFinderCache) {

		fieldKey = Objects.toString(fieldKey, "");
		stringValue = Objects.toString(stringValue, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_FK_SV;
				finderArgs = new Object[] {campaignId, fieldKey, stringValue};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_FK_SV;
			finderArgs = new Object[] {
				campaignId, fieldKey, stringValue, start, end, orderByComparator
			};
		}

		List<SubmissionValue> list = null;

		if (useFinderCache) {
			list = (List<SubmissionValue>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubmissionValue submissionValue : list) {
					if ((campaignId != submissionValue.getCampaignId()) ||
						!fieldKey.equals(submissionValue.getFieldKey()) ||
						!stringValue.equals(submissionValue.getStringValue())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_SUBMISSIONVALUE_WHERE);

			sb.append(_FINDER_COLUMN_C_FK_SV_CAMPAIGNID_2);

			boolean bindFieldKey = false;

			if (fieldKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_3);
			}
			else {
				bindFieldKey = true;

				sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_2);
			}

			boolean bindStringValue = false;

			if (stringValue.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_3);
			}
			else {
				bindStringValue = true;

				sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubmissionValueModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindFieldKey) {
					queryPos.add(fieldKey);
				}

				if (bindStringValue) {
					queryPos.add(stringValue);
				}

				list = (List<SubmissionValue>)QueryUtil.list(
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
	 * Returns the first submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue findByC_FK_SV_First(
			long campaignId, String fieldKey, String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = fetchByC_FK_SV_First(
			campaignId, fieldKey, stringValue, orderByComparator);

		if (submissionValue != null) {
			return submissionValue;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append(", fieldKey=");
		sb.append(fieldKey);

		sb.append(", stringValue=");
		sb.append(stringValue);

		sb.append("}");

		throw new NoSuchSubmissionValueException(sb.toString());
	}

	/**
	 * Returns the first submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue fetchByC_FK_SV_First(
		long campaignId, String fieldKey, String stringValue,
		OrderByComparator<SubmissionValue> orderByComparator) {

		List<SubmissionValue> list = findByC_FK_SV(
			campaignId, fieldKey, stringValue, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value
	 * @throws NoSuchSubmissionValueException if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue findByC_FK_SV_Last(
			long campaignId, String fieldKey, String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = fetchByC_FK_SV_Last(
			campaignId, fieldKey, stringValue, orderByComparator);

		if (submissionValue != null) {
			return submissionValue;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append(", fieldKey=");
		sb.append(fieldKey);

		sb.append(", stringValue=");
		sb.append(stringValue);

		sb.append("}");

		throw new NoSuchSubmissionValueException(sb.toString());
	}

	/**
	 * Returns the last submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching submission value, or <code>null</code> if a matching submission value could not be found
	 */
	@Override
	public SubmissionValue fetchByC_FK_SV_Last(
		long campaignId, String fieldKey, String stringValue,
		OrderByComparator<SubmissionValue> orderByComparator) {

		int count = countByC_FK_SV(campaignId, fieldKey, stringValue);

		if (count == 0) {
			return null;
		}

		List<SubmissionValue> list = findByC_FK_SV(
			campaignId, fieldKey, stringValue, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the submission values before and after the current submission value in the ordered set where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param submissionValueId the primary key of the current submission value
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue[] findByC_FK_SV_PrevAndNext(
			long submissionValueId, long campaignId, String fieldKey,
			String stringValue,
			OrderByComparator<SubmissionValue> orderByComparator)
		throws NoSuchSubmissionValueException {

		fieldKey = Objects.toString(fieldKey, "");
		stringValue = Objects.toString(stringValue, "");

		SubmissionValue submissionValue = findByPrimaryKey(submissionValueId);

		Session session = null;

		try {
			session = openSession();

			SubmissionValue[] array = new SubmissionValueImpl[3];

			array[0] = getByC_FK_SV_PrevAndNext(
				session, submissionValue, campaignId, fieldKey, stringValue,
				orderByComparator, true);

			array[1] = submissionValue;

			array[2] = getByC_FK_SV_PrevAndNext(
				session, submissionValue, campaignId, fieldKey, stringValue,
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

	protected SubmissionValue getByC_FK_SV_PrevAndNext(
		Session session, SubmissionValue submissionValue, long campaignId,
		String fieldKey, String stringValue,
		OrderByComparator<SubmissionValue> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_SUBMISSIONVALUE_WHERE);

		sb.append(_FINDER_COLUMN_C_FK_SV_CAMPAIGNID_2);

		boolean bindFieldKey = false;

		if (fieldKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_3);
		}
		else {
			bindFieldKey = true;

			sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_2);
		}

		boolean bindStringValue = false;

		if (stringValue.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_3);
		}
		else {
			bindStringValue = true;

			sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_2);
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
			sb.append(SubmissionValueModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(campaignId);

		if (bindFieldKey) {
			queryPos.add(fieldKey);
		}

		if (bindStringValue) {
			queryPos.add(stringValue);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						submissionValue)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubmissionValue> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 */
	@Override
	public void removeByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		for (SubmissionValue submissionValue :
				findByC_FK_SV(
					campaignId, fieldKey, stringValue, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(submissionValue);
		}
	}

	/**
	 * Returns the number of submission values where campaignId = &#63; and fieldKey = &#63; and stringValue = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param fieldKey the field key
	 * @param stringValue the string value
	 * @return the number of matching submission values
	 */
	@Override
	public int countByC_FK_SV(
		long campaignId, String fieldKey, String stringValue) {

		fieldKey = Objects.toString(fieldKey, "");
		stringValue = Objects.toString(stringValue, "");

		FinderPath finderPath = _finderPathCountByC_FK_SV;

		Object[] finderArgs = new Object[] {campaignId, fieldKey, stringValue};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_SUBMISSIONVALUE_WHERE);

			sb.append(_FINDER_COLUMN_C_FK_SV_CAMPAIGNID_2);

			boolean bindFieldKey = false;

			if (fieldKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_3);
			}
			else {
				bindFieldKey = true;

				sb.append(_FINDER_COLUMN_C_FK_SV_FIELDKEY_2);
			}

			boolean bindStringValue = false;

			if (stringValue.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_3);
			}
			else {
				bindStringValue = true;

				sb.append(_FINDER_COLUMN_C_FK_SV_STRINGVALUE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindFieldKey) {
					queryPos.add(fieldKey);
				}

				if (bindStringValue) {
					queryPos.add(stringValue);
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

	private static final String _FINDER_COLUMN_C_FK_SV_CAMPAIGNID_2 =
		"submissionValue.campaignId = ? AND ";

	private static final String _FINDER_COLUMN_C_FK_SV_FIELDKEY_2 =
		"submissionValue.fieldKey = ? AND ";

	private static final String _FINDER_COLUMN_C_FK_SV_FIELDKEY_3 =
		"(submissionValue.fieldKey IS NULL OR submissionValue.fieldKey = '') AND ";

	private static final String _FINDER_COLUMN_C_FK_SV_STRINGVALUE_2 =
		"submissionValue.stringValue = ?";

	private static final String _FINDER_COLUMN_C_FK_SV_STRINGVALUE_3 =
		"(submissionValue.stringValue IS NULL OR submissionValue.stringValue = '')";

	public SubmissionValuePersistenceImpl() {
		setModelClass(SubmissionValue.class);

		setModelImplClass(SubmissionValueImpl.class);
		setModelPKClass(long.class);

		setTable(SubmissionValueTable.INSTANCE);
	}

	/**
	 * Caches the submission value in the entity cache if it is enabled.
	 *
	 * @param submissionValue the submission value
	 */
	@Override
	public void cacheResult(SubmissionValue submissionValue) {
		entityCache.putResult(
			SubmissionValueImpl.class, submissionValue.getPrimaryKey(),
			submissionValue);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the submission values in the entity cache if it is enabled.
	 *
	 * @param submissionValues the submission values
	 */
	@Override
	public void cacheResult(List<SubmissionValue> submissionValues) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (submissionValues.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SubmissionValue submissionValue : submissionValues) {
			if (entityCache.getResult(
					SubmissionValueImpl.class,
					submissionValue.getPrimaryKey()) == null) {

				cacheResult(submissionValue);
			}
		}
	}

	/**
	 * Clears the cache for all submission values.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubmissionValueImpl.class);

		finderCache.clearCache(SubmissionValueImpl.class);
	}

	/**
	 * Clears the cache for the submission value.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubmissionValue submissionValue) {
		entityCache.removeResult(SubmissionValueImpl.class, submissionValue);
	}

	@Override
	public void clearCache(List<SubmissionValue> submissionValues) {
		for (SubmissionValue submissionValue : submissionValues) {
			entityCache.removeResult(
				SubmissionValueImpl.class, submissionValue);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SubmissionValueImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(SubmissionValueImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new submission value with the primary key. Does not add the submission value to the database.
	 *
	 * @param submissionValueId the primary key for the new submission value
	 * @return the new submission value
	 */
	@Override
	public SubmissionValue create(long submissionValueId) {
		SubmissionValue submissionValue = new SubmissionValueImpl();

		submissionValue.setNew(true);
		submissionValue.setPrimaryKey(submissionValueId);

		submissionValue.setCompanyId(CompanyThreadLocal.getCompanyId());

		return submissionValue;
	}

	/**
	 * Removes the submission value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value that was removed
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue remove(long submissionValueId)
		throws NoSuchSubmissionValueException {

		return remove((Serializable)submissionValueId);
	}

	/**
	 * Removes the submission value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the submission value
	 * @return the submission value that was removed
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue remove(Serializable primaryKey)
		throws NoSuchSubmissionValueException {

		Session session = null;

		try {
			session = openSession();

			SubmissionValue submissionValue = (SubmissionValue)session.get(
				SubmissionValueImpl.class, primaryKey);

			if (submissionValue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubmissionValueException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(submissionValue);
		}
		catch (NoSuchSubmissionValueException noSuchEntityException) {
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
	protected SubmissionValue removeImpl(SubmissionValue submissionValue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(submissionValue)) {
				submissionValue = (SubmissionValue)session.get(
					SubmissionValueImpl.class,
					submissionValue.getPrimaryKeyObj());
			}

			if (submissionValue != null) {
				session.delete(submissionValue);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (submissionValue != null) {
			clearCache(submissionValue);
		}

		return submissionValue;
	}

	@Override
	public SubmissionValue updateImpl(SubmissionValue submissionValue) {
		boolean isNew = submissionValue.isNew();

		if (!(submissionValue instanceof SubmissionValueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(submissionValue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					submissionValue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in submissionValue proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SubmissionValue implementation " +
					submissionValue.getClass());
		}

		SubmissionValueModelImpl submissionValueModelImpl =
			(SubmissionValueModelImpl)submissionValue;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(submissionValue);
			}
			else {
				submissionValue = (SubmissionValue)session.merge(
					submissionValue);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SubmissionValueImpl.class, submissionValueModelImpl, false, true);

		if (isNew) {
			submissionValue.setNew(false);
		}

		submissionValue.resetOriginalValues();

		return submissionValue;
	}

	/**
	 * Returns the submission value with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the submission value
	 * @return the submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubmissionValueException {

		SubmissionValue submissionValue = fetchByPrimaryKey(primaryKey);

		if (submissionValue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubmissionValueException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return submissionValue;
	}

	/**
	 * Returns the submission value with the primary key or throws a <code>NoSuchSubmissionValueException</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value
	 * @throws NoSuchSubmissionValueException if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue findByPrimaryKey(long submissionValueId)
		throws NoSuchSubmissionValueException {

		return findByPrimaryKey((Serializable)submissionValueId);
	}

	/**
	 * Returns the submission value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param submissionValueId the primary key of the submission value
	 * @return the submission value, or <code>null</code> if a submission value with the primary key could not be found
	 */
	@Override
	public SubmissionValue fetchByPrimaryKey(long submissionValueId) {
		return fetchByPrimaryKey((Serializable)submissionValueId);
	}

	/**
	 * Returns all the submission values.
	 *
	 * @return the submission values
	 */
	@Override
	public List<SubmissionValue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @return the range of submission values
	 */
	@Override
	public List<SubmissionValue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of submission values
	 */
	@Override
	public List<SubmissionValue> findAll(
		int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the submission values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubmissionValueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of submission values
	 * @param end the upper bound of the range of submission values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of submission values
	 */
	@Override
	public List<SubmissionValue> findAll(
		int start, int end,
		OrderByComparator<SubmissionValue> orderByComparator,
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

		List<SubmissionValue> list = null;

		if (useFinderCache) {
			list = (List<SubmissionValue>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBMISSIONVALUE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBMISSIONVALUE;

				sql = sql.concat(SubmissionValueModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SubmissionValue>)QueryUtil.list(
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
	 * Removes all the submission values from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SubmissionValue submissionValue : findAll()) {
			remove(submissionValue);
		}
	}

	/**
	 * Returns the number of submission values.
	 *
	 * @return the number of submission values
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBMISSIONVALUE);

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
		return "submissionValueId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUBMISSIONVALUE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SubmissionValueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the submission value persistence.
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

		_finderPathWithPaginationFindByC_FK_SV = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_FK_SV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"campaignId", "fieldKey", "stringValue"}, true);

		_finderPathWithoutPaginationFindByC_FK_SV = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_FK_SV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			new String[] {"campaignId", "fieldKey", "stringValue"}, true);

		_finderPathCountByC_FK_SV = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_FK_SV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			new String[] {"campaignId", "fieldKey", "stringValue"}, false);

		_setSubmissionValueUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setSubmissionValueUtilPersistence(null);

		entityCache.removeCache(SubmissionValueImpl.class.getName());
	}

	private void _setSubmissionValueUtilPersistence(
		SubmissionValuePersistence submissionValuePersistence) {

		try {
			Field field = SubmissionValueUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, submissionValuePersistence);
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

	private static final String _SQL_SELECT_SUBMISSIONVALUE =
		"SELECT submissionValue FROM SubmissionValue submissionValue";

	private static final String _SQL_SELECT_SUBMISSIONVALUE_WHERE =
		"SELECT submissionValue FROM SubmissionValue submissionValue WHERE ";

	private static final String _SQL_COUNT_SUBMISSIONVALUE =
		"SELECT COUNT(submissionValue) FROM SubmissionValue submissionValue";

	private static final String _SQL_COUNT_SUBMISSIONVALUE_WHERE =
		"SELECT COUNT(submissionValue) FROM SubmissionValue submissionValue WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "submissionValue.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SubmissionValue exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SubmissionValue exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubmissionValuePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}