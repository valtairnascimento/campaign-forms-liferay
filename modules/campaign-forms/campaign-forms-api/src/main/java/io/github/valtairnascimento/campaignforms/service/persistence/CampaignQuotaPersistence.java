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

import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignQuotaException;
import io.github.valtairnascimento.campaignforms.model.CampaignQuota;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the campaign quota service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see CampaignQuotaUtil
 * @generated
 */
@ProviderType
public interface CampaignQuotaPersistence
	extends BasePersistence<CampaignQuota> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CampaignQuotaUtil} to access the campaign quota persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the campaign quota in the entity cache if it is enabled.
	 *
	 * @param campaignQuota the campaign quota
	 */
	public void cacheResult(CampaignQuota campaignQuota);

	/**
	 * Caches the campaign quotas in the entity cache if it is enabled.
	 *
	 * @param campaignQuotas the campaign quotas
	 */
	public void cacheResult(java.util.List<CampaignQuota> campaignQuotas);

	/**
	 * Creates a new campaign quota with the primary key. Does not add the campaign quota to the database.
	 *
	 * @param campaignId the primary key for the new campaign quota
	 * @return the new campaign quota
	 */
	public CampaignQuota create(long campaignId);

	/**
	 * Removes the campaign quota with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota that was removed
	 * @throws NoSuchCampaignQuotaException if a campaign quota with the primary key could not be found
	 */
	public CampaignQuota remove(long campaignId)
		throws NoSuchCampaignQuotaException;

	public CampaignQuota updateImpl(CampaignQuota campaignQuota);

	/**
	 * Returns the campaign quota with the primary key or throws a <code>NoSuchCampaignQuotaException</code> if it could not be found.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota
	 * @throws NoSuchCampaignQuotaException if a campaign quota with the primary key could not be found
	 */
	public CampaignQuota findByPrimaryKey(long campaignId)
		throws NoSuchCampaignQuotaException;

	/**
	 * Returns the campaign quota with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param campaignId the primary key of the campaign quota
	 * @return the campaign quota, or <code>null</code> if a campaign quota with the primary key could not be found
	 */
	public CampaignQuota fetchByPrimaryKey(long campaignId);

	/**
	 * Returns all the campaign quotas.
	 *
	 * @return the campaign quotas
	 */
	public java.util.List<CampaignQuota> findAll();

	/**
	 * Returns a range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @return the range of campaign quotas
	 */
	public java.util.List<CampaignQuota> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of campaign quotas
	 */
	public java.util.List<CampaignQuota> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignQuota>
			orderByComparator);

	/**
	 * Returns an ordered range of all the campaign quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignQuotaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign quotas
	 * @param end the upper bound of the range of campaign quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of campaign quotas
	 */
	public java.util.List<CampaignQuota> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignQuota>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the campaign quotas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of campaign quotas.
	 *
	 * @return the number of campaign quotas
	 */
	public int countAll();

}