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

import io.github.valtairnascimento.campaignforms.exception.NoSuchFormVersionException;
import io.github.valtairnascimento.campaignforms.model.FormVersion;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the form version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see FormVersionUtil
 * @generated
 */
@ProviderType
public interface FormVersionPersistence extends BasePersistence<FormVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormVersionUtil} to access the form version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the matching form versions
	 */
	public java.util.List<FormVersion> findByFormId(long formId);

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
	public java.util.List<FormVersion> findByFormId(
		long formId, int start, int end);

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
	public java.util.List<FormVersion> findByFormId(
		long formId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

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
	public java.util.List<FormVersion> findByFormId(
		long formId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public FormVersion findByFormId_First(
			long formId,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Returns the first form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByFormId_First(
		long formId,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public FormVersion findByFormId_Last(
			long formId,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Returns the last form version in the ordered set where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByFormId_Last(
		long formId,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

	/**
	 * Returns the form versions before and after the current form version in the ordered set where formId = &#63;.
	 *
	 * @param formVersionId the primary key of the current form version
	 * @param formId the form ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public FormVersion[] findByFormId_PrevAndNext(
			long formVersionId, long formId,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Removes all the form versions where formId = &#63; from the database.
	 *
	 * @param formId the form ID
	 */
	public void removeByFormId(long formId);

	/**
	 * Returns the number of form versions where formId = &#63;.
	 *
	 * @param formId the form ID
	 * @return the number of matching form versions
	 */
	public int countByFormId(long formId);

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public FormVersion findByF_V(long formId, int version)
		throws NoSuchFormVersionException;

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByF_V(long formId, int version);

	/**
	 * Returns the form version where formId = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByF_V(
		long formId, int version, boolean useFinderCache);

	/**
	 * Removes the form version where formId = &#63; and version = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the form version that was removed
	 */
	public FormVersion removeByF_V(long formId, int version)
		throws NoSuchFormVersionException;

	/**
	 * Returns the number of form versions where formId = &#63; and version = &#63;.
	 *
	 * @param formId the form ID
	 * @param version the version
	 * @return the number of matching form versions
	 */
	public int countByF_V(long formId, int version);

	/**
	 * Returns all the form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the matching form versions
	 */
	public java.util.List<FormVersion> findByF_S(long formId, String status);

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
	public java.util.List<FormVersion> findByF_S(
		long formId, String status, int start, int end);

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
	public java.util.List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

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
	public java.util.List<FormVersion> findByF_S(
		long formId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public FormVersion findByF_S_First(
			long formId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Returns the first form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByF_S_First(
		long formId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version
	 * @throws NoSuchFormVersionException if a matching form version could not be found
	 */
	public FormVersion findByF_S_Last(
			long formId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Returns the last form version in the ordered set where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form version, or <code>null</code> if a matching form version could not be found
	 */
	public FormVersion fetchByF_S_Last(
		long formId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

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
	public FormVersion[] findByF_S_PrevAndNext(
			long formVersionId, long formId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
				orderByComparator)
		throws NoSuchFormVersionException;

	/**
	 * Removes all the form versions where formId = &#63; and status = &#63; from the database.
	 *
	 * @param formId the form ID
	 * @param status the status
	 */
	public void removeByF_S(long formId, String status);

	/**
	 * Returns the number of form versions where formId = &#63; and status = &#63;.
	 *
	 * @param formId the form ID
	 * @param status the status
	 * @return the number of matching form versions
	 */
	public int countByF_S(long formId, String status);

	/**
	 * Caches the form version in the entity cache if it is enabled.
	 *
	 * @param formVersion the form version
	 */
	public void cacheResult(FormVersion formVersion);

	/**
	 * Caches the form versions in the entity cache if it is enabled.
	 *
	 * @param formVersions the form versions
	 */
	public void cacheResult(java.util.List<FormVersion> formVersions);

	/**
	 * Creates a new form version with the primary key. Does not add the form version to the database.
	 *
	 * @param formVersionId the primary key for the new form version
	 * @return the new form version
	 */
	public FormVersion create(long formVersionId);

	/**
	 * Removes the form version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version that was removed
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public FormVersion remove(long formVersionId)
		throws NoSuchFormVersionException;

	public FormVersion updateImpl(FormVersion formVersion);

	/**
	 * Returns the form version with the primary key or throws a <code>NoSuchFormVersionException</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version
	 * @throws NoSuchFormVersionException if a form version with the primary key could not be found
	 */
	public FormVersion findByPrimaryKey(long formVersionId)
		throws NoSuchFormVersionException;

	/**
	 * Returns the form version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formVersionId the primary key of the form version
	 * @return the form version, or <code>null</code> if a form version with the primary key could not be found
	 */
	public FormVersion fetchByPrimaryKey(long formVersionId);

	/**
	 * Returns all the form versions.
	 *
	 * @return the form versions
	 */
	public java.util.List<FormVersion> findAll();

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
	public java.util.List<FormVersion> findAll(int start, int end);

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
	public java.util.List<FormVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator);

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
	public java.util.List<FormVersion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormVersion>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the form versions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of form versions.
	 *
	 * @return the number of form versions
	 */
	public int countAll();

}