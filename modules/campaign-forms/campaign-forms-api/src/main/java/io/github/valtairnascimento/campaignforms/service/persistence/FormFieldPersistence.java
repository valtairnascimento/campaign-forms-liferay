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

package io.github.valtairnascimento.campaignforms.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormFieldException;
import io.github.valtairnascimento.campaignforms.model.FormField;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the form field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormFieldUtil
 * @generated
 */
@ProviderType
public interface FormFieldPersistence extends BasePersistence<FormField> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormFieldUtil} to access the form field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the form fields where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the matching form fields
	 */
	public java.util.List<FormField> findByFormVersionId(long formVersionId);

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
	public java.util.List<FormField> findByFormVersionId(
		long formVersionId, int start, int end);

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
	public java.util.List<FormField> findByFormVersionId(
		long formVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator);

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
	public java.util.List<FormField> findByFormVersionId(
		long formVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	public FormField findByFormVersionId_First(
			long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<FormField>
				orderByComparator)
		throws NoSuchFormFieldException;

	/**
	 * Returns the first form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field, or <code>null</code> if a matching form field could not be found
	 */
	public FormField fetchByFormVersionId_First(
		long formVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator);

	/**
	 * Returns the last form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	public FormField findByFormVersionId_Last(
			long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<FormField>
				orderByComparator)
		throws NoSuchFormFieldException;

	/**
	 * Returns the last form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field, or <code>null</code> if a matching form field could not be found
	 */
	public FormField fetchByFormVersionId_Last(
		long formVersionId,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator);

	/**
	 * Returns the form fields before and after the current form field in the ordered set where formVersionId = &#63;.
	 *
	 * @param formFieldId the primary key of the current form field
	 * @param formVersionId the form version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form field
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	public FormField[] findByFormVersionId_PrevAndNext(
			long formFieldId, long formVersionId,
			com.liferay.portal.kernel.util.OrderByComparator<FormField>
				orderByComparator)
		throws NoSuchFormFieldException;

	/**
	 * Removes all the form fields where formVersionId = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 */
	public void removeByFormVersionId(long formVersionId);

	/**
	 * Returns the number of form fields where formVersionId = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @return the number of matching form fields
	 */
	public int countByFormVersionId(long formVersionId);

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or throws a <code>NoSuchFormFieldException</code> if it could not be found.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the matching form field
	 * @throws NoSuchFormFieldException if a matching form field could not be found
	 */
	public FormField findByFV_FK(long formVersionId, String fieldKey)
		throws NoSuchFormFieldException;

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the matching form field, or <code>null</code> if a matching form field could not be found
	 */
	public FormField fetchByFV_FK(long formVersionId, String fieldKey);

	/**
	 * Returns the form field where formVersionId = &#63; and fieldKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form field, or <code>null</code> if a matching form field could not be found
	 */
	public FormField fetchByFV_FK(
		long formVersionId, String fieldKey, boolean useFinderCache);

	/**
	 * Removes the form field where formVersionId = &#63; and fieldKey = &#63; from the database.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the form field that was removed
	 */
	public FormField removeByFV_FK(long formVersionId, String fieldKey)
		throws NoSuchFormFieldException;

	/**
	 * Returns the number of form fields where formVersionId = &#63; and fieldKey = &#63;.
	 *
	 * @param formVersionId the form version ID
	 * @param fieldKey the field key
	 * @return the number of matching form fields
	 */
	public int countByFV_FK(long formVersionId, String fieldKey);

	/**
	 * Caches the form field in the entity cache if it is enabled.
	 *
	 * @param formField the form field
	 */
	public void cacheResult(FormField formField);

	/**
	 * Caches the form fields in the entity cache if it is enabled.
	 *
	 * @param formFields the form fields
	 */
	public void cacheResult(java.util.List<FormField> formFields);

	/**
	 * Creates a new form field with the primary key. Does not add the form field to the database.
	 *
	 * @param formFieldId the primary key for the new form field
	 * @return the new form field
	 */
	public FormField create(long formFieldId);

	/**
	 * Removes the form field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field that was removed
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	public FormField remove(long formFieldId) throws NoSuchFormFieldException;

	public FormField updateImpl(FormField formField);

	/**
	 * Returns the form field with the primary key or throws a <code>NoSuchFormFieldException</code> if it could not be found.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field
	 * @throws NoSuchFormFieldException if a form field with the primary key could not be found
	 */
	public FormField findByPrimaryKey(long formFieldId)
		throws NoSuchFormFieldException;

	/**
	 * Returns the form field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formFieldId the primary key of the form field
	 * @return the form field, or <code>null</code> if a form field with the primary key could not be found
	 */
	public FormField fetchByPrimaryKey(long formFieldId);

	/**
	 * Returns all the form fields.
	 *
	 * @return the form fields
	 */
	public java.util.List<FormField> findAll();

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
	public java.util.List<FormField> findAll(int start, int end);

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
	public java.util.List<FormField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator);

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
	public java.util.List<FormField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the form fields from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of form fields.
	 *
	 * @return the number of form fields
	 */
	public int countAll();

}