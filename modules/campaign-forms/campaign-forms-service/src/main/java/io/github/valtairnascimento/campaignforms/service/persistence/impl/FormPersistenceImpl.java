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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormException;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormTable;
import io.github.valtairnascimento.campaignforms.model.impl.FormImpl;
import io.github.valtairnascimento.campaignforms.model.impl.FormModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.FormPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormUtil;
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
 * The persistence implementation for the form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = FormPersistence.class)
public class FormPersistenceImpl
	extends BasePersistenceImpl<Form> implements FormPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FormUtil</code> to access the form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FormImpl.class.getName();

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
	 * Returns all the forms where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching forms
	 */
	@Override
	public List<Form> findByCampaignId(long campaignId) {
		return findByCampaignId(
			campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the forms where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @return the range of matching forms
	 */
	@Override
	public List<Form> findByCampaignId(long campaignId, int start, int end) {
		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the forms where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching forms
	 */
	@Override
	public List<Form> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<Form> orderByComparator) {

		return findByCampaignId(
			campaignId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the forms where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching forms
	 */
	@Override
	public List<Form> findByCampaignId(
		long campaignId, int start, int end,
		OrderByComparator<Form> orderByComparator, boolean useFinderCache) {

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

		List<Form> list = null;

		if (useFinderCache) {
			list = (List<Form>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Form form : list) {
					if (campaignId != form.getCampaignId()) {
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

			sb.append(_SQL_SELECT_FORM_WHERE);

			sb.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FormModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				list = (List<Form>)QueryUtil.list(
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
	 * Returns the first form in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form
	 * @throws NoSuchFormException if a matching form could not be found
	 */
	@Override
	public Form findByCampaignId_First(
			long campaignId, OrderByComparator<Form> orderByComparator)
		throws NoSuchFormException {

		Form form = fetchByCampaignId_First(campaignId, orderByComparator);

		if (form != null) {
			return form;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchFormException(sb.toString());
	}

	/**
	 * Returns the first form in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form, or <code>null</code> if a matching form could not be found
	 */
	@Override
	public Form fetchByCampaignId_First(
		long campaignId, OrderByComparator<Form> orderByComparator) {

		List<Form> list = findByCampaignId(campaignId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form
	 * @throws NoSuchFormException if a matching form could not be found
	 */
	@Override
	public Form findByCampaignId_Last(
			long campaignId, OrderByComparator<Form> orderByComparator)
		throws NoSuchFormException {

		Form form = fetchByCampaignId_Last(campaignId, orderByComparator);

		if (form != null) {
			return form;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("campaignId=");
		sb.append(campaignId);

		sb.append("}");

		throw new NoSuchFormException(sb.toString());
	}

	/**
	 * Returns the last form in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form, or <code>null</code> if a matching form could not be found
	 */
	@Override
	public Form fetchByCampaignId_Last(
		long campaignId, OrderByComparator<Form> orderByComparator) {

		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<Form> list = findByCampaignId(
			campaignId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the forms before and after the current form in the ordered set where campaignId = &#63;.
	 *
	 * @param formId the primary key of the current form
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form
	 * @throws NoSuchFormException if a form with the primary key could not be found
	 */
	@Override
	public Form[] findByCampaignId_PrevAndNext(
			long formId, long campaignId,
			OrderByComparator<Form> orderByComparator)
		throws NoSuchFormException {

		Form form = findByPrimaryKey(formId);

		Session session = null;

		try {
			session = openSession();

			Form[] array = new FormImpl[3];

			array[0] = getByCampaignId_PrevAndNext(
				session, form, campaignId, orderByComparator, true);

			array[1] = form;

			array[2] = getByCampaignId_PrevAndNext(
				session, form, campaignId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Form getByCampaignId_PrevAndNext(
		Session session, Form form, long campaignId,
		OrderByComparator<Form> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FORM_WHERE);

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
			sb.append(FormModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(campaignId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(form)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Form> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the forms where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	@Override
	public void removeByCampaignId(long campaignId) {
		for (Form form :
				findByCampaignId(
					campaignId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(form);
		}
	}

	/**
	 * Returns the number of forms where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching forms
	 */
	@Override
	public int countByCampaignId(long campaignId) {
		FinderPath finderPath = _finderPathCountByCampaignId;

		Object[] finderArgs = new Object[] {campaignId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FORM_WHERE);

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
		"form.campaignId = ?";

	private FinderPath _finderPathFetchByC_FK;
	private FinderPath _finderPathCountByC_FK;

	/**
	 * Returns the form where campaignId = &#63; and formKey = &#63; or throws a <code>NoSuchFormException</code> if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param formKey the form key
	 * @return the matching form
	 * @throws NoSuchFormException if a matching form could not be found
	 */
	@Override
	public Form findByC_FK(long campaignId, String formKey)
		throws NoSuchFormException {

		Form form = fetchByC_FK(campaignId, formKey);

		if (form == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("campaignId=");
			sb.append(campaignId);

			sb.append(", formKey=");
			sb.append(formKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormException(sb.toString());
		}

		return form;
	}

	/**
	 * Returns the form where campaignId = &#63; and formKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param formKey the form key
	 * @return the matching form, or <code>null</code> if a matching form could not be found
	 */
	@Override
	public Form fetchByC_FK(long campaignId, String formKey) {
		return fetchByC_FK(campaignId, formKey, true);
	}

	/**
	 * Returns the form where campaignId = &#63; and formKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param formKey the form key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form, or <code>null</code> if a matching form could not be found
	 */
	@Override
	public Form fetchByC_FK(
		long campaignId, String formKey, boolean useFinderCache) {

		formKey = Objects.toString(formKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {campaignId, formKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_FK, finderArgs, this);
		}

		if (result instanceof Form) {
			Form form = (Form)result;

			if ((campaignId != form.getCampaignId()) ||
				!Objects.equals(formKey, form.getFormKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FORM_WHERE);

			sb.append(_FINDER_COLUMN_C_FK_CAMPAIGNID_2);

			boolean bindFormKey = false;

			if (formKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_FORMKEY_3);
			}
			else {
				bindFormKey = true;

				sb.append(_FINDER_COLUMN_C_FK_FORMKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindFormKey) {
					queryPos.add(formKey);
				}

				List<Form> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_FK, finderArgs, list);
					}
				}
				else {
					Form form = list.get(0);

					result = form;

					cacheResult(form);
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
			return (Form)result;
		}
	}

	/**
	 * Removes the form where campaignId = &#63; and formKey = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param formKey the form key
	 * @return the form that was removed
	 */
	@Override
	public Form removeByC_FK(long campaignId, String formKey)
		throws NoSuchFormException {

		Form form = findByC_FK(campaignId, formKey);

		return remove(form);
	}

	/**
	 * Returns the number of forms where campaignId = &#63; and formKey = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param formKey the form key
	 * @return the number of matching forms
	 */
	@Override
	public int countByC_FK(long campaignId, String formKey) {
		formKey = Objects.toString(formKey, "");

		FinderPath finderPath = _finderPathCountByC_FK;

		Object[] finderArgs = new Object[] {campaignId, formKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FORM_WHERE);

			sb.append(_FINDER_COLUMN_C_FK_CAMPAIGNID_2);

			boolean bindFormKey = false;

			if (formKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_FK_FORMKEY_3);
			}
			else {
				bindFormKey = true;

				sb.append(_FINDER_COLUMN_C_FK_FORMKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(campaignId);

				if (bindFormKey) {
					queryPos.add(formKey);
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

	private static final String _FINDER_COLUMN_C_FK_CAMPAIGNID_2 =
		"form.campaignId = ? AND ";

	private static final String _FINDER_COLUMN_C_FK_FORMKEY_2 =
		"form.formKey = ?";

	private static final String _FINDER_COLUMN_C_FK_FORMKEY_3 =
		"(form.formKey IS NULL OR form.formKey = '')";

	public FormPersistenceImpl() {
		setModelClass(Form.class);

		setModelImplClass(FormImpl.class);
		setModelPKClass(long.class);

		setTable(FormTable.INSTANCE);
	}

	/**
	 * Caches the form in the entity cache if it is enabled.
	 *
	 * @param form the form
	 */
	@Override
	public void cacheResult(Form form) {
		entityCache.putResult(FormImpl.class, form.getPrimaryKey(), form);

		finderCache.putResult(
			_finderPathFetchByC_FK,
			new Object[] {form.getCampaignId(), form.getFormKey()}, form);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the forms in the entity cache if it is enabled.
	 *
	 * @param forms the forms
	 */
	@Override
	public void cacheResult(List<Form> forms) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (forms.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Form form : forms) {
			if (entityCache.getResult(FormImpl.class, form.getPrimaryKey()) ==
					null) {

				cacheResult(form);
			}
		}
	}

	/**
	 * Clears the cache for all forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormImpl.class);

		finderCache.clearCache(FormImpl.class);
	}

	/**
	 * Clears the cache for the form.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Form form) {
		entityCache.removeResult(FormImpl.class, form);
	}

	@Override
	public void clearCache(List<Form> forms) {
		for (Form form : forms) {
			entityCache.removeResult(FormImpl.class, form);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FormImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FormImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(FormModelImpl formModelImpl) {
		Object[] args = new Object[] {
			formModelImpl.getCampaignId(), formModelImpl.getFormKey()
		};

		finderCache.putResult(_finderPathCountByC_FK, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByC_FK, args, formModelImpl);
	}

	/**
	 * Creates a new form with the primary key. Does not add the form to the database.
	 *
	 * @param formId the primary key for the new form
	 * @return the new form
	 */
	@Override
	public Form create(long formId) {
		Form form = new FormImpl();

		form.setNew(true);
		form.setPrimaryKey(formId);

		form.setCompanyId(CompanyThreadLocal.getCompanyId());

		return form;
	}

	/**
	 * Removes the form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formId the primary key of the form
	 * @return the form that was removed
	 * @throws NoSuchFormException if a form with the primary key could not be found
	 */
	@Override
	public Form remove(long formId) throws NoSuchFormException {
		return remove((Serializable)formId);
	}

	/**
	 * Removes the form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form
	 * @return the form that was removed
	 * @throws NoSuchFormException if a form with the primary key could not be found
	 */
	@Override
	public Form remove(Serializable primaryKey) throws NoSuchFormException {
		Session session = null;

		try {
			session = openSession();

			Form form = (Form)session.get(FormImpl.class, primaryKey);

			if (form == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(form);
		}
		catch (NoSuchFormException noSuchEntityException) {
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
	protected Form removeImpl(Form form) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(form)) {
				form = (Form)session.get(
					FormImpl.class, form.getPrimaryKeyObj());
			}

			if (form != null) {
				session.delete(form);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (form != null) {
			clearCache(form);
		}

		return form;
	}

	@Override
	public Form updateImpl(Form form) {
		boolean isNew = form.isNew();

		if (!(form instanceof FormModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(form.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(form);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in form proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Form implementation " +
					form.getClass());
		}

		FormModelImpl formModelImpl = (FormModelImpl)form;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (form.getCreateDate() == null)) {
			if (serviceContext == null) {
				form.setCreateDate(date);
			}
			else {
				form.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!formModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				form.setModifiedDate(date);
			}
			else {
				form.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(form);
			}
			else {
				form = (Form)session.merge(form);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(FormImpl.class, formModelImpl, false, true);

		cacheUniqueFindersCache(formModelImpl);

		if (isNew) {
			form.setNew(false);
		}

		form.resetOriginalValues();

		return form;
	}

	/**
	 * Returns the form with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form
	 * @return the form
	 * @throws NoSuchFormException if a form with the primary key could not be found
	 */
	@Override
	public Form findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormException {

		Form form = fetchByPrimaryKey(primaryKey);

		if (form == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return form;
	}

	/**
	 * Returns the form with the primary key or throws a <code>NoSuchFormException</code> if it could not be found.
	 *
	 * @param formId the primary key of the form
	 * @return the form
	 * @throws NoSuchFormException if a form with the primary key could not be found
	 */
	@Override
	public Form findByPrimaryKey(long formId) throws NoSuchFormException {
		return findByPrimaryKey((Serializable)formId);
	}

	/**
	 * Returns the form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formId the primary key of the form
	 * @return the form, or <code>null</code> if a form with the primary key could not be found
	 */
	@Override
	public Form fetchByPrimaryKey(long formId) {
		return fetchByPrimaryKey((Serializable)formId);
	}

	/**
	 * Returns all the forms.
	 *
	 * @return the forms
	 */
	@Override
	public List<Form> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @return the range of forms
	 */
	@Override
	public List<Form> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of forms
	 */
	@Override
	public List<Form> findAll(
		int start, int end, OrderByComparator<Form> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the forms.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of forms
	 * @param end the upper bound of the range of forms (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of forms
	 */
	@Override
	public List<Form> findAll(
		int start, int end, OrderByComparator<Form> orderByComparator,
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

		List<Form> list = null;

		if (useFinderCache) {
			list = (List<Form>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FORM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FORM;

				sql = sql.concat(FormModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Form>)QueryUtil.list(
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
	 * Removes all the forms from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Form form : findAll()) {
			remove(form);
		}
	}

	/**
	 * Returns the number of forms.
	 *
	 * @return the number of forms
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FORM);

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
		return "formId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FORM;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FormModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form persistence.
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

		_finderPathFetchByC_FK = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_FK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "formKey"}, true);

		_finderPathCountByC_FK = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_FK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"campaignId", "formKey"}, false);

		_setFormUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFormUtilPersistence(null);

		entityCache.removeCache(FormImpl.class.getName());
	}

	private void _setFormUtilPersistence(FormPersistence formPersistence) {
		try {
			Field field = FormUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, formPersistence);
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

	private static final String _SQL_SELECT_FORM = "SELECT form FROM Form form";

	private static final String _SQL_SELECT_FORM_WHERE =
		"SELECT form FROM Form form WHERE ";

	private static final String _SQL_COUNT_FORM =
		"SELECT COUNT(form) FROM Form form";

	private static final String _SQL_COUNT_FORM_WHERE =
		"SELECT COUNT(form) FROM Form form WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "form.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Form exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Form exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FormPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}