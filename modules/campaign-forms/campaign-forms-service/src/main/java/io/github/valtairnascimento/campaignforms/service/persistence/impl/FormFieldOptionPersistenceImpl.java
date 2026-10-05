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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormFieldOptionException;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.model.FormFieldOptionTable;
import io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionImpl;
import io.github.valtairnascimento.campaignforms.model.impl.FormFieldOptionModelImpl;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldOptionPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldOptionUtil;
import io.github.valtairnascimento.campaignforms.service.persistence.impl.constants.CFPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the form field option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
@Component(service = FormFieldOptionPersistence.class)
public class FormFieldOptionPersistenceImpl
	extends BasePersistenceImpl<FormFieldOption>
	implements FormFieldOptionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FormFieldOptionUtil</code> to access the form field option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FormFieldOptionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByFormFieldId;
	private FinderPath _finderPathWithoutPaginationFindByFormFieldId;
	private FinderPath _finderPathCountByFormFieldId;

	/**
	 * Returns all the form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the matching form field options
	 */
	@Override
	public List<FormFieldOption> findByFormFieldId(long formFieldId) {
		return findByFormFieldId(
			formFieldId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @return the range of matching form field options
	 */
	@Override
	public List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end) {

		return findByFormFieldId(formFieldId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form field options
	 */
	@Override
	public List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return findByFormFieldId(
			formFieldId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form field options where formFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param formFieldId the form field ID
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching form field options
	 */
	@Override
	public List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFormFieldId;
				finderArgs = new Object[] {formFieldId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFormFieldId;
			finderArgs = new Object[] {
				formFieldId, start, end, orderByComparator
			};
		}

		List<FormFieldOption> list = null;

		if (useFinderCache) {
			list = (List<FormFieldOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormFieldOption formFieldOption : list) {
					if (formFieldId != formFieldOption.getFormFieldId()) {
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

			sb.append(_SQL_SELECT_FORMFIELDOPTION_WHERE);

			sb.append(_FINDER_COLUMN_FORMFIELDID_FORMFIELDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FormFieldOptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formFieldId);

				list = (List<FormFieldOption>)QueryUtil.list(
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
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	@Override
	public FormFieldOption findByFormFieldId_First(
			long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws NoSuchFormFieldOptionException {

		FormFieldOption formFieldOption = fetchByFormFieldId_First(
			formFieldId, orderByComparator);

		if (formFieldOption != null) {
			return formFieldOption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formFieldId=");
		sb.append(formFieldId);

		sb.append("}");

		throw new NoSuchFormFieldOptionException(sb.toString());
	}

	/**
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	@Override
	public FormFieldOption fetchByFormFieldId_First(
		long formFieldId,
		OrderByComparator<FormFieldOption> orderByComparator) {

		List<FormFieldOption> list = findByFormFieldId(
			formFieldId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	@Override
	public FormFieldOption findByFormFieldId_Last(
			long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws NoSuchFormFieldOptionException {

		FormFieldOption formFieldOption = fetchByFormFieldId_Last(
			formFieldId, orderByComparator);

		if (formFieldOption != null) {
			return formFieldOption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formFieldId=");
		sb.append(formFieldId);

		sb.append("}");

		throw new NoSuchFormFieldOptionException(sb.toString());
	}

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	@Override
	public FormFieldOption fetchByFormFieldId_Last(
		long formFieldId,
		OrderByComparator<FormFieldOption> orderByComparator) {

		int count = countByFormFieldId(formFieldId);

		if (count == 0) {
			return null;
		}

		List<FormFieldOption> list = findByFormFieldId(
			formFieldId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form field options before and after the current form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldOptionId the primary key of the current form field option
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption[] findByFormFieldId_PrevAndNext(
			long formFieldOptionId, long formFieldId,
			OrderByComparator<FormFieldOption> orderByComparator)
		throws NoSuchFormFieldOptionException {

		FormFieldOption formFieldOption = findByPrimaryKey(formFieldOptionId);

		Session session = null;

		try {
			session = openSession();

			FormFieldOption[] array = new FormFieldOptionImpl[3];

			array[0] = getByFormFieldId_PrevAndNext(
				session, formFieldOption, formFieldId, orderByComparator, true);

			array[1] = formFieldOption;

			array[2] = getByFormFieldId_PrevAndNext(
				session, formFieldOption, formFieldId, orderByComparator,
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

	protected FormFieldOption getByFormFieldId_PrevAndNext(
		Session session, FormFieldOption formFieldOption, long formFieldId,
		OrderByComparator<FormFieldOption> orderByComparator,
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

		sb.append(_SQL_SELECT_FORMFIELDOPTION_WHERE);

		sb.append(_FINDER_COLUMN_FORMFIELDID_FORMFIELDID_2);

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
			sb.append(FormFieldOptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formFieldId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						formFieldOption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FormFieldOption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form field options where formFieldId = &#63; from the database.
	 *
	 * @param formFieldId the form field ID
	 */
	@Override
	public void removeByFormFieldId(long formFieldId) {
		for (FormFieldOption formFieldOption :
				findByFormFieldId(
					formFieldId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(formFieldOption);
		}
	}

	/**
	 * Returns the number of form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the number of matching form field options
	 */
	@Override
	public int countByFormFieldId(long formFieldId) {
		FinderPath finderPath = _finderPathCountByFormFieldId;

		Object[] finderArgs = new Object[] {formFieldId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FORMFIELDOPTION_WHERE);

			sb.append(_FINDER_COLUMN_FORMFIELDID_FORMFIELDID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formFieldId);

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

	private static final String _FINDER_COLUMN_FORMFIELDID_FORMFIELDID_2 =
		"formFieldOption.formFieldId = ?";

	public FormFieldOptionPersistenceImpl() {
		setModelClass(FormFieldOption.class);

		setModelImplClass(FormFieldOptionImpl.class);
		setModelPKClass(long.class);

		setTable(FormFieldOptionTable.INSTANCE);
	}

	/**
	 * Caches the form field option in the entity cache if it is enabled.
	 *
	 * @param formFieldOption the form field option
	 */
	@Override
	public void cacheResult(FormFieldOption formFieldOption) {
		entityCache.putResult(
			FormFieldOptionImpl.class, formFieldOption.getPrimaryKey(),
			formFieldOption);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the form field options in the entity cache if it is enabled.
	 *
	 * @param formFieldOptions the form field options
	 */
	@Override
	public void cacheResult(List<FormFieldOption> formFieldOptions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (formFieldOptions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FormFieldOption formFieldOption : formFieldOptions) {
			if (entityCache.getResult(
					FormFieldOptionImpl.class,
					formFieldOption.getPrimaryKey()) == null) {

				cacheResult(formFieldOption);
			}
		}
	}

	/**
	 * Clears the cache for all form field options.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormFieldOptionImpl.class);

		finderCache.clearCache(FormFieldOptionImpl.class);
	}

	/**
	 * Clears the cache for the form field option.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormFieldOption formFieldOption) {
		entityCache.removeResult(FormFieldOptionImpl.class, formFieldOption);
	}

	@Override
	public void clearCache(List<FormFieldOption> formFieldOptions) {
		for (FormFieldOption formFieldOption : formFieldOptions) {
			entityCache.removeResult(
				FormFieldOptionImpl.class, formFieldOption);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FormFieldOptionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FormFieldOptionImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new form field option with the primary key. Does not add the form field option to the database.
	 *
	 * @param formFieldOptionId the primary key for the new form field option
	 * @return the new form field option
	 */
	@Override
	public FormFieldOption create(long formFieldOptionId) {
		FormFieldOption formFieldOption = new FormFieldOptionImpl();

		formFieldOption.setNew(true);
		formFieldOption.setPrimaryKey(formFieldOptionId);

		formFieldOption.setCompanyId(CompanyThreadLocal.getCompanyId());

		return formFieldOption;
	}

	/**
	 * Removes the form field option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option that was removed
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption remove(long formFieldOptionId)
		throws NoSuchFormFieldOptionException {

		return remove((Serializable)formFieldOptionId);
	}

	/**
	 * Removes the form field option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form field option
	 * @return the form field option that was removed
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption remove(Serializable primaryKey)
		throws NoSuchFormFieldOptionException {

		Session session = null;

		try {
			session = openSession();

			FormFieldOption formFieldOption = (FormFieldOption)session.get(
				FormFieldOptionImpl.class, primaryKey);

			if (formFieldOption == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormFieldOptionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(formFieldOption);
		}
		catch (NoSuchFormFieldOptionException noSuchEntityException) {
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
	protected FormFieldOption removeImpl(FormFieldOption formFieldOption) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formFieldOption)) {
				formFieldOption = (FormFieldOption)session.get(
					FormFieldOptionImpl.class,
					formFieldOption.getPrimaryKeyObj());
			}

			if (formFieldOption != null) {
				session.delete(formFieldOption);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (formFieldOption != null) {
			clearCache(formFieldOption);
		}

		return formFieldOption;
	}

	@Override
	public FormFieldOption updateImpl(FormFieldOption formFieldOption) {
		boolean isNew = formFieldOption.isNew();

		if (!(formFieldOption instanceof FormFieldOptionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(formFieldOption.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					formFieldOption);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in formFieldOption proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FormFieldOption implementation " +
					formFieldOption.getClass());
		}

		FormFieldOptionModelImpl formFieldOptionModelImpl =
			(FormFieldOptionModelImpl)formFieldOption;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(formFieldOption);
			}
			else {
				formFieldOption = (FormFieldOption)session.merge(
					formFieldOption);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FormFieldOptionImpl.class, formFieldOptionModelImpl, false, true);

		if (isNew) {
			formFieldOption.setNew(false);
		}

		formFieldOption.resetOriginalValues();

		return formFieldOption;
	}

	/**
	 * Returns the form field option with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form field option
	 * @return the form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormFieldOptionException {

		FormFieldOption formFieldOption = fetchByPrimaryKey(primaryKey);

		if (formFieldOption == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormFieldOptionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return formFieldOption;
	}

	/**
	 * Returns the form field option with the primary key or throws a <code>NoSuchFormFieldOptionException</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption findByPrimaryKey(long formFieldOptionId)
		throws NoSuchFormFieldOptionException {

		return findByPrimaryKey((Serializable)formFieldOptionId);
	}

	/**
	 * Returns the form field option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option, or <code>null</code> if a form field option with the primary key could not be found
	 */
	@Override
	public FormFieldOption fetchByPrimaryKey(long formFieldOptionId) {
		return fetchByPrimaryKey((Serializable)formFieldOptionId);
	}

	/**
	 * Returns all the form field options.
	 *
	 * @return the form field options
	 */
	@Override
	public List<FormFieldOption> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @return the range of form field options
	 */
	@Override
	public List<FormFieldOption> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form field options
	 */
	@Override
	public List<FormFieldOption> findAll(
		int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form field options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FormFieldOptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of form field options
	 * @param end the upper bound of the range of form field options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of form field options
	 */
	@Override
	public List<FormFieldOption> findAll(
		int start, int end,
		OrderByComparator<FormFieldOption> orderByComparator,
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

		List<FormFieldOption> list = null;

		if (useFinderCache) {
			list = (List<FormFieldOption>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FORMFIELDOPTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FORMFIELDOPTION;

				sql = sql.concat(FormFieldOptionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FormFieldOption>)QueryUtil.list(
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
	 * Removes all the form field options from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormFieldOption formFieldOption : findAll()) {
			remove(formFieldOption);
		}
	}

	/**
	 * Returns the number of form field options.
	 *
	 * @return the number of form field options
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FORMFIELDOPTION);

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
		return "formFieldOptionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FORMFIELDOPTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FormFieldOptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form field option persistence.
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

		_finderPathWithPaginationFindByFormFieldId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFormFieldId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"formFieldId"}, true);

		_finderPathWithoutPaginationFindByFormFieldId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFormFieldId",
			new String[] {Long.class.getName()}, new String[] {"formFieldId"},
			true);

		_finderPathCountByFormFieldId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormFieldId",
			new String[] {Long.class.getName()}, new String[] {"formFieldId"},
			false);

		_setFormFieldOptionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFormFieldOptionUtilPersistence(null);

		entityCache.removeCache(FormFieldOptionImpl.class.getName());
	}

	private void _setFormFieldOptionUtilPersistence(
		FormFieldOptionPersistence formFieldOptionPersistence) {

		try {
			Field field = FormFieldOptionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, formFieldOptionPersistence);
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

	private static final String _SQL_SELECT_FORMFIELDOPTION =
		"SELECT formFieldOption FROM FormFieldOption formFieldOption";

	private static final String _SQL_SELECT_FORMFIELDOPTION_WHERE =
		"SELECT formFieldOption FROM FormFieldOption formFieldOption WHERE ";

	private static final String _SQL_COUNT_FORMFIELDOPTION =
		"SELECT COUNT(formFieldOption) FROM FormFieldOption formFieldOption";

	private static final String _SQL_COUNT_FORMFIELDOPTION_WHERE =
		"SELECT COUNT(formFieldOption) FROM FormFieldOption formFieldOption WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "formFieldOption.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FormFieldOption exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FormFieldOption exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FormFieldOptionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}