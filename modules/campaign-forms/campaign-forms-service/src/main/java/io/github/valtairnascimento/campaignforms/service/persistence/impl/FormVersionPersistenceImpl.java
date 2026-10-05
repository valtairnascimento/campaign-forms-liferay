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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormVersionException;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.FormVersionTable;
import io.github.valtairnascimento.campaignforms.model.impl.FormVersionImpl;
import io.github.valtairnascimento.campaignforms.model.impl.FormVersionModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.FormVersionPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormVersionUtil;
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
 * The persistence implementation for the form version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = FormVersionPersistence.class)
public class FormVersionPersistenceImpl
	extends BasePersistenceImpl<FormVersion> implements FormVersionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FormVersionUtil</code> to access the form version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FormVersionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByFormId;
	private FinderPath _finderPathWithoutPaginationFindByFormId;
	private FinderPath _finderPathCountByFormId;

	/**
	 * Returns all the form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the matching form versions
	 */
	@Override
	public List<FormVersion> findByFormId(long formId) {
		return findByFormId(formId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of matching form versions
	 */
	@Override
	public List<FormVersion> findByFormId(long formId, int start, int end) {
		return findByFormId(formId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form versions
	 */
	@Override
	public List<FormVersion> findByFormId(
		long formId, int start, int end,
		OrderByComparator<FormVersion> orderByComparator) {

		return findByFormId(formId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form versions
	 */
	@Override
	public List<FormVersion> findByFormId(
		long formId, int start, int end,
		OrderByComparator<FormVersion> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFormId;
				finderArgs = new Object[] {formId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFormId;
			finderArgs = new Object[] {formId, start, end, orderByComparator};
		}

		List<FormVersion> list = null;

		if (useFinderCache) {
			list = (List<FormVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormVersion formVersion : list) {
					if (formId != formVersion.getFormId()) {
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

			sb.append(_SQL_SELECT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_FORMID_FORMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FormVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

				list = (List<FormVersion>)QueryUtil.list(
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
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	@Override
	public FormVersion findByFormId_First(
			long formId, OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByFormId_First(
			formId, orderByComparator);

		if (formVersion != null) {
			return formVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formId=");
		sb.append(formId);

		sb.append("}");

		throw new NoSuchFormVersionException(sb.toString());
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByFormId_First(
		long formId, OrderByComparator<FormVersion> orderByComparator) {

		List<FormVersion> list = findByFormId(formId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	@Override
	public FormVersion findByFormId_Last(
			long formId, OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByFormId_Last(formId, orderByComparator);

		if (formVersion != null) {
			return formVersion;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formId=");
		sb.append(formId);

		sb.append("}");

		throw new NoSuchFormVersionException(sb.toString());
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByFormId_Last(
		long formId, OrderByComparator<FormVersion> orderByComparator) {

		int count = countByFormId(formId);

		if (count == 0) {
			return null;
		}

		List<FormVersion> list = findByFormId(
			formId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form versions before and after the current form version in the ordered set where formId = &#63;.
	 *
	 * @param formVersionId the primary key of the current form version
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion[] findByFormId_PrevAndNext(
			long formVersionId, long formId,
			OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		FormVersion formVersion = findByPrimaryKey(formVersionId);

		Session session = null;

		try {
			session = openSession();

			FormVersion[] array = new FormVersionImpl[3];

			array[0] = getByFormId_PrevAndNext(
				session, formVersion, formId, orderByComparator, true);

			array[1] = formVersion;

			array[2] = getByFormId_PrevAndNext(
				session, formVersion, formId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormVersion getByFormId_PrevAndNext(
		Session session, FormVersion formVersion, long formId,
		OrderByComparator<FormVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FORMVERSION_WHERE);

		sb.append(_FINDER_COLUMN_FORMID_FORMID_2);

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
			sb.append(FormVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(formVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FormVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form versions where formId = &#63; from the database.
	 *
	 * @param formId the form ID
	 */
	@Override
	public void removeByFormId(long formId) {
		for (FormVersion formVersion :
				findByFormId(
					formId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(formVersion);
		}
	}

	/**
	 * Returns the number of form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the number of matching form versions
	 */
	@Override
	public int countByFormId(long formId) {
		FinderPath finderPath = _finderPathCountByFormId;

		Object[] finderArgs = new Object[] {formId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_FORMID_FORMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

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

	private static final String _FINDER_COLUMN_FORMID_FORMID_2 =
		"formVersion.formId = ?";

	private FinderPath _finderPathFetchByF_V;
	private FinderPath _finderPathCountByF_V;

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	@Override
	public FormVersion findByF_V(long formId, int version)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByF_V(formId, version);

		if (formVersion == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formId=");
			sb.append(formId);

			sb.append(", version=");
			sb.append(version);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormVersionException(sb.toString());
		}

		return formVersion;
	}

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByF_V(long formId, int version) {
		return fetchByF_V(formId, version, true);
	}

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByF_V(
		long formId, int version, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formId, version};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByF_V, finderArgs, this);
		}

		if (result instanceof FormVersion) {
			FormVersion formVersion = (FormVersion)result;

			if ((formId != formVersion.getFormId()) ||
				(version != formVersion.getVersion())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_V_FORMID_2);

			sb.append(_FINDER_COLUMN_F_V_VERSION_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

				queryPos.add(version);

				List<FormVersion> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByF_V, finderArgs, list);
					}
				}
				else {
					FormVersion formVersion = list.get(0);

					result = formVersion;

					cacheResult(formVersion);
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
			return (FormVersion)result;
		}
	}

	/**
	 * Removes the form version where formId = &#63; and version = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the form version that was removed
	 */
	@Override
	public FormVersion removeByF_V(long formId, int version)
		throws NoSuchFormVersionException {

		FormVersion formVersion = findByF_V(formId, version);

		return remove(formVersion);
	}

	/**
	 * Returns the number of form versions where formId = &#63; and version = &#63;.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the number of matching form versions
	 */
	@Override
	public int countByF_V(long formId, int version) {
		FinderPath finderPath = _finderPathCountByF_V;

		Object[] finderArgs = new Object[] {formId, version};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_V_FORMID_2);

			sb.append(_FINDER_COLUMN_F_V_VERSION_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

				queryPos.add(version);

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

	private static final String _FINDER_COLUMN_F_V_FORMID_2 =
		"formVersion.formId = ? AND ";

	private static final String _FINDER_COLUMN_F_V_VERSION_2 =
		"formVersion.version = ?";

	private FinderPath _finderPathWithPaginationFindByF_S;
	private FinderPath _finderPathWithoutPaginationFindByF_S;
	private FinderPath _finderPathCountByF_S;

	/**
	 * Returns all the form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the matching form versions
	 */
	@Override
	public List<FormVersion> findByF_S(long formId, String status) {
		return findByF_S(
			formId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of matching form versions
	 */
	@Override
	public List<FormVersion> findByF_S(
		long formId, String status, int start, int end) {

		return findByF_S(formId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form versions
	 */
	@Override
	public List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		OrderByComparator<FormVersion> orderByComparator) {

		return findByF_S(formId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form versions where formId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form versions
	 */
	@Override
	public List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		OrderByComparator<FormVersion> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByF_S;
				finderArgs = new Object[] {formId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByF_S;
			finderArgs = new Object[] {
				formId, status, start, end, orderByComparator
			};
		}

		List<FormVersion> list = null;

		if (useFinderCache) {
			list = (List<FormVersion>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormVersion formVersion : list) {
					if ((formId != formVersion.getFormId()) ||
						!status.equals(formVersion.getStatus())) {

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

			sb.append(_SQL_SELECT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FORMID_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_S_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_F_S_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FormVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<FormVersion>)QueryUtil.list(
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
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	@Override
	public FormVersion findByF_S_First(
			long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByF_S_First(
			formId, status, orderByComparator);

		if (formVersion != null) {
			return formVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formId=");
		sb.append(formId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormVersionException(sb.toString());
	}

	/**
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByF_S_First(
		long formId, String status,
		OrderByComparator<FormVersion> orderByComparator) {

		List<FormVersion> list = findByF_S(
			formId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	@Override
	public FormVersion findByF_S_Last(
			long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByF_S_Last(
			formId, status, orderByComparator);

		if (formVersion != null) {
			return formVersion;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formId=");
		sb.append(formId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchFormVersionException(sb.toString());
	}

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	@Override
	public FormVersion fetchByF_S_Last(
		long formId, String status,
		OrderByComparator<FormVersion> orderByComparator) {

		int count = countByF_S(formId, status);

		if (count == 0) {
			return null;
		}

		List<FormVersion> list = findByF_S(
			formId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form versions before and after the current form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formVersionId the primary key of the current form version
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion[] findByF_S_PrevAndNext(
			long formVersionId, long formId, String status,
			OrderByComparator<FormVersion> orderByComparator)
		throws NoSuchFormVersionException {

		status = Objects.toString(status, "");

		FormVersion formVersion = findByPrimaryKey(formVersionId);

		Session session = null;

		try {
			session = openSession();

			FormVersion[] array = new FormVersionImpl[3];

			array[0] = getByF_S_PrevAndNext(
				session, formVersion, formId, status, orderByComparator, true);

			array[1] = formVersion;

			array[2] = getByF_S_PrevAndNext(
				session, formVersion, formId, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormVersion getByF_S_PrevAndNext(
		Session session, FormVersion formVersion, long formId, String status,
		OrderByComparator<FormVersion> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FORMVERSION_WHERE);

		sb.append(_FINDER_COLUMN_F_S_FORMID_2);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_F_S_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_F_S_STATUS_2);
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
			sb.append(FormVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formId);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(formVersion)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FormVersion> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form versions where formId = &#63; and status = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param status the status
	 */
	@Override
	public void removeByF_S(long formId, String status) {
		for (FormVersion formVersion :
				findByF_S(
					formId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(formVersion);
		}
	}

	/**
	 * Returns the number of form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the number of matching form versions
	 */
	@Override
	public int countByF_S(long formId, String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByF_S;

		Object[] finderArgs = new Object[] {formId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FORMVERSION_WHERE);

			sb.append(_FINDER_COLUMN_F_S_FORMID_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_S_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_F_S_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formId);

				if (bindStatus) {
					queryPos.add(status);
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

	private static final String _FINDER_COLUMN_F_S_FORMID_2 =
		"formVersion.formId = ? AND ";

	private static final String _FINDER_COLUMN_F_S_STATUS_2 =
		"formVersion.status = ?";

	private static final String _FINDER_COLUMN_F_S_STATUS_3 =
		"(formVersion.status IS NULL OR formVersion.status = '')";

	public FormVersionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FormVersion.class);

		setModelImplClass(FormVersionImpl.class);
		setModelPKClass(long.class);

		setTable(FormVersionTable.INSTANCE);
	}

	/**
	 * Caches the form version in the entity cache if it is enabled.
	 *
	 * @param formVersion the form version
	 */
	@Override
	public void cacheResult(FormVersion formVersion) {
		entityCache.putResult(
			FormVersionImpl.class, formVersion.getPrimaryKey(), formVersion);

		finderCache.putResult(
			_finderPathFetchByF_V,
			new Object[] {formVersion.getFormId(), formVersion.getVersion()},
			formVersion);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the form versions in the entity cache if it is enabled.
	 *
	 * @param formVersions the form versions
	 */
	@Override
	public void cacheResult(List<FormVersion> formVersions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (formVersions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FormVersion formVersion : formVersions) {
			if (entityCache.getResult(
					FormVersionImpl.class, formVersion.getPrimaryKey()) ==
						null) {

				cacheResult(formVersion);
			}
		}
	}

	/**
	 * Clears the cache for all form versions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormVersionImpl.class);

		finderCache.clearCache(FormVersionImpl.class);
	}

	/**
	 * Clears the cache for the form version.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormVersion formVersion) {
		entityCache.removeResult(FormVersionImpl.class, formVersion);
	}

	@Override
	public void clearCache(List<FormVersion> formVersions) {
		for (FormVersion formVersion : formVersions) {
			entityCache.removeResult(FormVersionImpl.class, formVersion);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FormVersionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FormVersionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FormVersionModelImpl formVersionModelImpl) {

		Object[] args = new Object[] {
			formVersionModelImpl.getFormId(), formVersionModelImpl.getVersion()
		};

		finderCache.putResult(_finderPathCountByF_V, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByF_V, args, formVersionModelImpl);
	}

	/**
	 * Creates a new form version with the primary key. Does not add the form version to the database.
	 *
	 * @param formVersionId the primary key for the new form version
	 * @return the new form version
	 */
	@Override
	public FormVersion create(long formVersionId) {
		FormVersion formVersion = new FormVersionImpl();

		formVersion.setNew(true);
		formVersion.setPrimaryKey(formVersionId);

		formVersion.setCompanyId(CompanyThreadLocal.getCompanyId());

		return formVersion;
	}

	/**
	 * Removes the form version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version that was removed
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion remove(long formVersionId)
		throws NoSuchFormVersionException {

		return remove((Serializable)formVersionId);
	}

	/**
	 * Removes the form version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form version
	 * @return the form version that was removed
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion remove(Serializable primaryKey)
		throws NoSuchFormVersionException {

		Session session = null;

		try {
			session = openSession();

			FormVersion formVersion = (FormVersion)session.get(
				FormVersionImpl.class, primaryKey);

			if (formVersion == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormVersionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(formVersion);
		}
		catch (NoSuchFormVersionException noSuchEntityException) {
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
	protected FormVersion removeImpl(FormVersion formVersion) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formVersion)) {
				formVersion = (FormVersion)session.get(
					FormVersionImpl.class, formVersion.getPrimaryKeyObj());
			}

			if (formVersion != null) {
				session.delete(formVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (formVersion != null) {
			clearCache(formVersion);
		}

		return formVersion;
	}

	@Override
	public FormVersion updateImpl(FormVersion formVersion) {
		boolean isNew = formVersion.isNew();

		if (!(formVersion instanceof FormVersionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(formVersion.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(formVersion);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in formVersion proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FormVersion implementation " +
					formVersion.getClass());
		}

		FormVersionModelImpl formVersionModelImpl =
			(FormVersionModelImpl)formVersion;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (formVersion.getCreateDate() == null)) {
			if (serviceContext == null) {
				formVersion.setCreateDate(date);
			}
			else {
				formVersion.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!formVersionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				formVersion.setModifiedDate(date);
			}
			else {
				formVersion.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(formVersion);
			}
			else {
				formVersion = (FormVersion)session.merge(formVersion);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FormVersionImpl.class, formVersionModelImpl, false, true);

		cacheUniqueFindersCache(formVersionModelImpl);

		if (isNew) {
			formVersion.setNew(false);
		}

		formVersion.resetOriginalValues();

		return formVersion;
	}

	/**
	 * Returns the form version with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form version
	 * @return the form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormVersionException {

		FormVersion formVersion = fetchByPrimaryKey(primaryKey);

		if (formVersion == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormVersionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return formVersion;
	}

	/**
	 * Returns the form version with the primary key or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion findByPrimaryKey(long formVersionId)
		throws NoSuchFormVersionException {

		return findByPrimaryKey((Serializable)formVersionId);
	}

	/**
	 * Returns the form version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version, or <code>null</code> if a form version with the primary key could not be found
	 */
	@Override
	public FormVersion fetchByPrimaryKey(long formVersionId) {
		return fetchByPrimaryKey((Serializable)formVersionId);
	}

	/**
	 * Returns all the form versions.
	 *
	 * @return the form versions
	 */
	@Override
	public List<FormVersion> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @return the range of form versions
	 */
	@Override
	public List<FormVersion> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form versions
	 */
	@Override
	public List<FormVersion> findAll(
		int start, int end, OrderByComparator<FormVersion> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form versions
	 * @param end the upper bound of the range of form versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of form versions
	 */
	@Override
	public List<FormVersion> findAll(
		int start, int end, OrderByComparator<FormVersion> orderByComparator,
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

		List<FormVersion> list = null;

		if (useFinderCache) {
			list = (List<FormVersion>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FORMVERSION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FORMVERSION;

				sql = sql.concat(FormVersionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FormVersion>)QueryUtil.list(
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
	 * Removes all the form versions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormVersion formVersion : findAll()) {
			remove(formVersion);
		}
	}

	/**
	 * Returns the number of form versions.
	 *
	 * @return the number of form versions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FORMVERSION);

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
		return "formVersionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FORMVERSION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FormVersionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form version persistence.
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

		_finderPathWithPaginationFindByFormId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFormId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"formId"}, true);

		_finderPathWithoutPaginationFindByFormId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFormId",
			new String[] {Long.class.getName()}, new String[] {"formId"}, true);

		_finderPathCountByFormId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormId",
			new String[] {Long.class.getName()}, new String[] {"formId"},
			false);

		_finderPathFetchByF_V = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByF_V",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"formId", "version"}, true);

		_finderPathCountByF_V = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_V",
			new String[] {Long.class.getName(), Integer.class.getName()},
			new String[] {"formId", "version"}, false);

		_finderPathWithPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"formId", "status"}, true);

		_finderPathWithoutPaginationFindByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_S",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formId", "status"}, true);

		_finderPathCountByF_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_S",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formId", "status"}, false);

		_setFormVersionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFormVersionUtilPersistence(null);

		entityCache.removeCache(FormVersionImpl.class.getName());
	}

	private void _setFormVersionUtilPersistence(
		FormVersionPersistence formVersionPersistence) {

		try {
			Field field = FormVersionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, formVersionPersistence);
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

	private static final String _SQL_SELECT_FORMVERSION =
		"SELECT formVersion FROM FormVersion formVersion";

	private static final String _SQL_SELECT_FORMVERSION_WHERE =
		"SELECT formVersion FROM FormVersion formVersion WHERE ";

	private static final String _SQL_COUNT_FORMVERSION =
		"SELECT COUNT(formVersion) FROM FormVersion formVersion";

	private static final String _SQL_COUNT_FORMVERSION_WHERE =
		"SELECT COUNT(formVersion) FROM FormVersion formVersion WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "formVersion.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FormVersion exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FormVersion exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FormVersionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}