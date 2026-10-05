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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormFieldException;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldTable;
import io.github.valtairnascimento.campaignforms.model.impl.FormFieldImpl;
import io.github.valtairnascimento.campaignforms.model.impl.FormFieldModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldUtil;
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
 * The persistence implementation for the form field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = FormFieldPersistence.class)
public class FormFieldPersistenceImpl
	extends BasePersistenceImpl<FormField> implements FormFieldPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FormFieldUtil</code> to access the form field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FormFieldImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByFormVersionId;
	private FinderPath _finderPathWithoutPaginationFindByFormVersionId;
	private FinderPath _finderPathCountByFormVersionId;

	/**
	 * Returns all the form fields where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the matching form fields
	 */
	@Override
	public List<FormField> findByFormVersionId(long formVersionId) {
		return findByFormVersionId(
			formVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form fields where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @return the range of matching form fields
	 */
	@Override
	public List<FormField> findByFormVersionId(
		long formVersionId, int start, int end) {

		return findByFormVersionId(formVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form fields where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form fields
	 */
	@Override
	public List<FormField> findByFormVersionId(
		long formVersionId, int start, int end,
		OrderByComparator<FormField> orderByComparator) {

		return findByFormVersionId(
			formVersionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form fields where formVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param formVersionId the form version ID
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form fields
	 */
	@Override
	public List<FormField> findByFormVersionId(
		long formVersionId, int start, int end,
		OrderByComparator<FormField> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFormVersionId;
				finderArgs = new Object[] {formVersionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFormVersionId;
			finderArgs = new Object[] {
				formVersionId, start, end, orderByComparator
			};
		}

		List<FormField> list = null;

		if (useFinderCache) {
			list = (List<FormField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormField formField : list) {
					if (formVersionId != formField.getFormVersionId()) {
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

			sb.append(_SQL_SELECT_FORMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_FORMVERSIONID_FORMVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FormFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formVersionId);

				list = (List<FormField>)QueryUtil.list(
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
	 * Returns the first form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	@Override
	public FormField findByFormVersionId_First(
			long formVersionId, OrderByComparator<FormField> orderByComparator)
		throws NoSuchFormFieldException {

		FormField formField = fetchByFormVersionId_First(
			formVersionId, orderByComparator);

		if (formField != null) {
			return formField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formVersionId=");
		sb.append(formVersionId);

		sb.append("}");

		throw new NoSuchFormFieldException(sb.toString());
	}

	/**
	 * Returns the first form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field, or <code>null</code> if a matching form field could not be found
	 */
	@Override
	public FormField fetchByFormVersionId_First(
		long formVersionId, OrderByComparator<FormField> orderByComparator) {

		List<FormField> list = findByFormVersionId(
			formVersionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	@Override
	public FormField findByFormVersionId_Last(
			long formVersionId, OrderByComparator<FormField> orderByComparator)
		throws NoSuchFormFieldException {

		FormField formField = fetchByFormVersionId_Last(
			formVersionId, orderByComparator);

		if (formField != null) {
			return formField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formVersionId=");
		sb.append(formVersionId);

		sb.append("}");

		throw new NoSuchFormFieldException(sb.toString());
	}

	/**
	 * Returns the last form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field, or <code>null</code> if a matching form field could not be found
	 */
	@Override
	public FormField fetchByFormVersionId_Last(
		long formVersionId, OrderByComparator<FormField> orderByComparator) {

		int count = countByFormVersionId(formVersionId);

		if (count == 0) {
			return null;
		}

		List<FormField> list = findByFormVersionId(
			formVersionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form fields before and after the current form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formFieldId the primary key of the current form field
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form field
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	@Override
	public FormField[] findByFormVersionId_PrevAndNext(
			long formFieldId, long formVersionId,
			OrderByComparator<FormField> orderByComparator)
		throws NoSuchFormFieldException {

		FormField formField = findByPrimaryKey(formFieldId);

		Session session = null;

		try {
			session = openSession();

			FormField[] array = new FormFieldImpl[3];

			array[0] = getByFormVersionId_PrevAndNext(
				session, formField, formVersionId, orderByComparator, true);

			array[1] = formField;

			array[2] = getByFormVersionId_PrevAndNext(
				session, formField, formVersionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormField getByFormVersionId_PrevAndNext(
		Session session, FormField formField, long formVersionId,
		OrderByComparator<FormField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FORMFIELD_WHERE);

		sb.append(_FINDER_COLUMN_FORMVERSIONID_FORMVERSIONID_2);

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
			sb.append(FormFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formVersionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(formField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FormField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form fields where formVersionId = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 */
	@Override
	public void removeByFormVersionId(long formVersionId) {
		for (FormField formField :
				findByFormVersionId(
					formVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(formField);
		}
	}

	/**
	 * Returns the number of form fields where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the number of matching form fields
	 */
	@Override
	public int countByFormVersionId(long formVersionId) {
		FinderPath finderPath = _finderPathCountByFormVersionId;

		Object[] finderArgs = new Object[] {formVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FORMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_FORMVERSIONID_FORMVERSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formVersionId);

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

	private static final String _FINDER_COLUMN_FORMVERSIONID_FORMVERSIONID_2 =
		"formField.formVersionId = ?";

	private FinderPath _finderPathFetchByFV_FK;
	private FinderPath _finderPathCountByFV_FK;

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or throws a <code>NoSuchFormFieldException</code> if it could not be found.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	@Override
	public FormField findByFV_FK(long formVersionId, String fieldKey)
		throws NoSuchFormFieldException {

		FormField formField = fetchByFV_FK(formVersionId, fieldKey);

		if (formField == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formVersionId=");
			sb.append(formVersionId);

			sb.append(", fieldKey=");
			sb.append(fieldKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormFieldException(sb.toString());
		}

		return formField;
	}

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the matching form field, or <code>null</code> if a matching form field could not be found
	 */
	@Override
	public FormField fetchByFV_FK(long formVersionId, String fieldKey) {
		return fetchByFV_FK(formVersionId, fieldKey, true);
	}

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form field, or <code>null</code> if a matching form field could not be found
	 */
	@Override
	public FormField fetchByFV_FK(
		long formVersionId, String fieldKey, boolean useFinderCache) {

		fieldKey = Objects.toString(fieldKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formVersionId, fieldKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFV_FK, finderArgs, this);
		}

		if (result instanceof FormField) {
			FormField formField = (FormField)result;

			if ((formVersionId != formField.getFormVersionId()) ||
				!Objects.equals(fieldKey, formField.getFieldKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FORMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_FV_FK_FORMVERSIONID_2);

			boolean bindFieldKey = false;

			if (fieldKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_FV_FK_FIELDKEY_3);
			}
			else {
				bindFieldKey = true;

				sb.append(_FINDER_COLUMN_FV_FK_FIELDKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formVersionId);

				if (bindFieldKey) {
					queryPos.add(fieldKey);
				}

				List<FormField> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFV_FK, finderArgs, list);
					}
				}
				else {
					FormField formField = list.get(0);

					result = formField;

					cacheResult(formField);
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
			return (FormField)result;
		}
	}

	/**
	 * Removes the form field where formVersionId = &#63; and fieldKey = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the form field that was removed
	 */
	@Override
	public FormField removeByFV_FK(long formVersionId, String fieldKey)
		throws NoSuchFormFieldException {

		FormField formField = findByFV_FK(formVersionId, fieldKey);

		return remove(formField);
	}

	/**
	 * Returns the number of form fields where formVersionId = &#63; and fieldKey = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the number of matching form fields
	 */
	@Override
	public int countByFV_FK(long formVersionId, String fieldKey) {
		fieldKey = Objects.toString(fieldKey, "");

		FinderPath finderPath = _finderPathCountByFV_FK;

		Object[] finderArgs = new Object[] {formVersionId, fieldKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FORMFIELD_WHERE);

			sb.append(_FINDER_COLUMN_FV_FK_FORMVERSIONID_2);

			boolean bindFieldKey = false;

			if (fieldKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_FV_FK_FIELDKEY_3);
			}
			else {
				bindFieldKey = true;

				sb.append(_FINDER_COLUMN_FV_FK_FIELDKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formVersionId);

				if (bindFieldKey) {
					queryPos.add(fieldKey);
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

	private static final String _FINDER_COLUMN_FV_FK_FORMVERSIONID_2 =
		"formField.formVersionId = ? AND ";

	private static final String _FINDER_COLUMN_FV_FK_FIELDKEY_2 =
		"formField.fieldKey = ?";

	private static final String _FINDER_COLUMN_FV_FK_FIELDKEY_3 =
		"(formField.fieldKey IS NULL OR formField.fieldKey = '')";

	public FormFieldPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FormField.class);

		setModelImplClass(FormFieldImpl.class);
		setModelPKClass(long.class);

		setTable(FormFieldTable.INSTANCE);
	}

	/**
	 * Caches the form field in the entity cache if it is enabled.
	 *
	 * @param formField the form field
	 */
	@Override
	public void cacheResult(FormField formField) {
		entityCache.putResult(
			FormFieldImpl.class, formField.getPrimaryKey(), formField);

		finderCache.putResult(
			_finderPathFetchByFV_FK,
			new Object[] {
				formField.getFormVersionId(), formField.getFieldKey()
			},
			formField);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the form fields in the entity cache if it is enabled.
	 *
	 * @param formFields the form fields
	 */
	@Override
	public void cacheResult(List<FormField> formFields) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (formFields.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FormField formField : formFields) {
			if (entityCache.getResult(
					FormFieldImpl.class, formField.getPrimaryKey()) == null) {

				cacheResult(formField);
			}
		}
	}

	/**
	 * Clears the cache for all form fields.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormFieldImpl.class);

		finderCache.clearCache(FormFieldImpl.class);
	}

	/**
	 * Clears the cache for the form field.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormField formField) {
		entityCache.removeResult(FormFieldImpl.class, formField);
	}

	@Override
	public void clearCache(List<FormField> formFields) {
		for (FormField formField : formFields) {
			entityCache.removeResult(FormFieldImpl.class, formField);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FormFieldImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FormFieldImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FormFieldModelImpl formFieldModelImpl) {

		Object[] args = new Object[] {
			formFieldModelImpl.getFormVersionId(),
			formFieldModelImpl.getFieldKey()
		};

		finderCache.putResult(_finderPathCountByFV_FK, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByFV_FK, args, formFieldModelImpl);
	}

	/**
	 * Creates a new form field with the primary key. Does not add the form field to the database.
	 *
	 * @param formFieldId the primary key for the new form field
	 * @return the new form field
	 */
	@Override
	public FormField create(long formFieldId) {
		FormField formField = new FormFieldImpl();

		formField.setNew(true);
		formField.setPrimaryKey(formFieldId);

		formField.setCompanyId(CompanyThreadLocal.getCompanyId());

		return formField;
	}

	/**
	 * Removes the form field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field that was removed
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	@Override
	public FormField remove(long formFieldId) throws NoSuchFormFieldException {
		return remove((Serializable)formFieldId);
	}

	/**
	 * Removes the form field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form field
	 * @return the form field that was removed
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	@Override
	public FormField remove(Serializable primaryKey)
		throws NoSuchFormFieldException {

		Session session = null;

		try {
			session = openSession();

			FormField formField = (FormField)session.get(
				FormFieldImpl.class, primaryKey);

			if (formField == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormFieldException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(formField);
		}
		catch (NoSuchFormFieldException noSuchEntityException) {
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
	protected FormField removeImpl(FormField formField) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formField)) {
				formField = (FormField)session.get(
					FormFieldImpl.class, formField.getPrimaryKeyObj());
			}

			if (formField != null) {
				session.delete(formField);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (formField != null) {
			clearCache(formField);
		}

		return formField;
	}

	@Override
	public FormField updateImpl(FormField formField) {
		boolean isNew = formField.isNew();

		if (!(formField instanceof FormFieldModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(formField.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(formField);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in formField proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FormField implementation " +
					formField.getClass());
		}

		FormFieldModelImpl formFieldModelImpl = (FormFieldModelImpl)formField;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (formField.getCreateDate() == null)) {
			if (serviceContext == null) {
				formField.setCreateDate(date);
			}
			else {
				formField.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!formFieldModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				formField.setModifiedDate(date);
			}
			else {
				formField.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(formField);
			}
			else {
				formField = (FormField)session.merge(formField);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FormFieldImpl.class, formFieldModelImpl, false, true);

		cacheUniqueFindersCache(formFieldModelImpl);

		if (isNew) {
			formField.setNew(false);
		}

		formField.resetOriginalValues();

		return formField;
	}

	/**
	 * Returns the form field with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form field
	 * @return the form field
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	@Override
	public FormField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormFieldException {

		FormField formField = fetchByPrimaryKey(primaryKey);

		if (formField == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormFieldException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return formField;
	}

	/**
	 * Returns the form field with the primary key or throws a <code>NoSuchFormFieldException</code> if it could not be found.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	@Override
	public FormField findByPrimaryKey(long formFieldId)
		throws NoSuchFormFieldException {

		return findByPrimaryKey((Serializable)formFieldId);
	}

	/**
	 * Returns the form field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field, or <code>null</code> if a form field with the primary key could not be found
	 */
	@Override
	public FormField fetchByPrimaryKey(long formFieldId) {
		return fetchByPrimaryKey((Serializable)formFieldId);
	}

	/**
	 * Returns all the form fields.
	 *
	 * @return the form fields
	 */
	@Override
	public List<FormField> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @return the range of form fields
	 */
	@Override
	public List<FormField> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the form fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form fields
	 */
	@Override
	public List<FormField> findAll(
		int start, int end, OrderByComparator<FormField> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form fields
	 * @param end the upper bound of the range of form fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of form fields
	 */
	@Override
	public List<FormField> findAll(
		int start, int end, OrderByComparator<FormField> orderByComparator,
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

		List<FormField> list = null;

		if (useFinderCache) {
			list = (List<FormField>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FORMFIELD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FORMFIELD;

				sql = sql.concat(FormFieldModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FormField>)QueryUtil.list(
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
	 * Removes all the form fields from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormField formField : findAll()) {
			remove(formField);
		}
	}

	/**
	 * Returns the number of form fields.
	 *
	 * @return the number of form fields
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FORMFIELD);

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
		return "formFieldId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FORMFIELD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FormFieldModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form field persistence.
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

		_finderPathWithPaginationFindByFormVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFormVersionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"formVersionId"}, true);

		_finderPathWithoutPaginationFindByFormVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFormVersionId",
			new String[] {Long.class.getName()}, new String[] {"formVersionId"},
			true);

		_finderPathCountByFormVersionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormVersionId",
			new String[] {Long.class.getName()}, new String[] {"formVersionId"},
			false);

		_finderPathFetchByFV_FK = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFV_FK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formVersionId", "fieldKey"}, true);

		_finderPathCountByFV_FK = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFV_FK",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"formVersionId", "fieldKey"}, false);

		_setFormFieldUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFormFieldUtilPersistence(null);

		entityCache.removeCache(FormFieldImpl.class.getName());
	}

	private void _setFormFieldUtilPersistence(
		FormFieldPersistence formFieldPersistence) {

		try {
			Field field = FormFieldUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, formFieldPersistence);
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

	private static final String _SQL_SELECT_FORMFIELD =
		"SELECT formField FROM FormField formField";

	private static final String _SQL_SELECT_FORMFIELD_WHERE =
		"SELECT formField FROM FormField formField WHERE ";

	private static final String _SQL_COUNT_FORMFIELD =
		"SELECT COUNT(formField) FROM FormField formField";

	private static final String _SQL_COUNT_FORMFIELD_WHERE =
		"SELECT COUNT(formField) FROM FormField formField WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "formField.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FormField exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FormField exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FormFieldPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}