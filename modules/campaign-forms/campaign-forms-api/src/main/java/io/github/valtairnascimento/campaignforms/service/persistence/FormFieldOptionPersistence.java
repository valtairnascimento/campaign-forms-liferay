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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormFieldOptionException;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the form field option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormFieldOptionUtil
 * @generated
 */
@ProviderType
public interface FormFieldOptionPersistence
	extends BasePersistence<FormFieldOption> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormFieldOptionUtil} to access the form field option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the matching form field options
	 */
	public java.util.List<FormFieldOption> findByFormFieldId(long formFieldId);

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
	public java.util.List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end);

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
	public java.util.List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator);

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
	public java.util.List<FormFieldOption> findByFormFieldId(
		long formFieldId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	public FormFieldOption findByFormFieldId_First(
			long formFieldId,
			com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
				orderByComparator)
		throws NoSuchFormFieldOptionException;

	/**
	 * Returns the first form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	public FormFieldOption fetchByFormFieldId_First(
		long formFieldId,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator);

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option
	 * @throws NoSuchFormFieldOptionException if a matching form field option could not be found
	 */
	public FormFieldOption findByFormFieldId_Last(
			long formFieldId,
			com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
				orderByComparator)
		throws NoSuchFormFieldOptionException;

	/**
	 * Returns the last form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form field option, or <code>null</code> if a matching form field option could not be found
	 */
	public FormFieldOption fetchByFormFieldId_Last(
		long formFieldId,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator);

	/**
	 * Returns the form field options before and after the current form field option in the ordered set where formFieldId = &#63;.
	 *
	 * @param formFieldOptionId the primary key of the current form field option
	 * @param formFieldId the form field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public FormFieldOption[] findByFormFieldId_PrevAndNext(
			long formFieldOptionId, long formFieldId,
			com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
				orderByComparator)
		throws NoSuchFormFieldOptionException;

	/**
	 * Removes all the form field options where formFieldId = &#63; from the database.
	 *
	 * @param formFieldId the form field ID
	 */
	public void removeByFormFieldId(long formFieldId);

	/**
	 * Returns the number of form field options where formFieldId = &#63;.
	 *
	 * @param formFieldId the form field ID
	 * @return the number of matching form field options
	 */
	public int countByFormFieldId(long formFieldId);

	/**
	 * Caches the form field option in the entity cache if it is enabled.
	 *
	 * @param formFieldOption the form field option
	 */
	public void cacheResult(FormFieldOption formFieldOption);

	/**
	 * Caches the form field options in the entity cache if it is enabled.
	 *
	 * @param formFieldOptions the form field options
	 */
	public void cacheResult(java.util.List<FormFieldOption> formFieldOptions);

	/**
	 * Creates a new form field option with the primary key. Does not add the form field option to the database.
	 *
	 * @param formFieldOptionId the primary key for the new form field option
	 * @return the new form field option
	 */
	public FormFieldOption create(long formFieldOptionId);

	/**
	 * Removes the form field option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option that was removed
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public FormFieldOption remove(long formFieldOptionId)
		throws NoSuchFormFieldOptionException;

	public FormFieldOption updateImpl(FormFieldOption formFieldOption);

	/**
	 * Returns the form field option with the primary key or throws a <code>NoSuchFormFieldOptionException</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option
	 * @throws NoSuchFormFieldOptionException if a form field option with the primary key could not be found
	 */
	public FormFieldOption findByPrimaryKey(long formFieldOptionId)
		throws NoSuchFormFieldOptionException;

	/**
	 * Returns the form field option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formFieldOptionId the primary key of the form field option
	 * @return the form field option, or <code>null</code> if a form field option with the primary key could not be found
	 */
	public FormFieldOption fetchByPrimaryKey(long formFieldOptionId);

	/**
	 * Returns all the form field options.
	 *
	 * @return the form field options
	 */
	public java.util.List<FormFieldOption> findAll();

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
	public java.util.List<FormFieldOption> findAll(int start, int end);

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
	public java.util.List<FormFieldOption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator);

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
	public java.util.List<FormFieldOption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormFieldOption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the form field options from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of form field options.
	 *
	 * @return the number of form field options
	 */
	public int countAll();

}