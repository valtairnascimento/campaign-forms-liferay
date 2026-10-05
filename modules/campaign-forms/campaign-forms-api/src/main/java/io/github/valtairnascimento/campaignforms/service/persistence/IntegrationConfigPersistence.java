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

import io.github.valtairnascimento.campaignforms.exception.NoSuchIntegrationConfigException;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the integration config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Valtair Nascimento
 * @see IntegrationConfigUtil
 * @generated
 */
@ProviderType
public interface IntegrationConfigPersistence
	extends BasePersistence<IntegrationConfig> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IntegrationConfigUtil} to access the integration config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the integration configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching integration configs
	 */
	public java.util.List<IntegrationConfig> findByCampaignId(long campaignId);

	/**
	 * Returns a range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @return the range of matching integration configs
	 */
	public java.util.List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end);

	/**
	 * Returns an ordered range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching integration configs
	 */
	public java.util.List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator);

	/**
	 * Returns an ordered range of all the integration configs where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching integration configs
	 */
	public java.util.List<IntegrationConfig> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching integration config
	 * @throws NoSuchIntegrationConfigException if a matching integration config could not be found
	 */
	public IntegrationConfig findByCampaignId_First(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
				orderByComparator)
		throws NoSuchIntegrationConfigException;

	/**
	 * Returns the first integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching integration config, or <code>null</code> if a matching integration config could not be found
	 */
	public IntegrationConfig fetchByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator);

	/**
	 * Returns the last integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching integration config
	 * @throws NoSuchIntegrationConfigException if a matching integration config could not be found
	 */
	public IntegrationConfig findByCampaignId_Last(
			long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
				orderByComparator)
		throws NoSuchIntegrationConfigException;

	/**
	 * Returns the last integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching integration config, or <code>null</code> if a matching integration config could not be found
	 */
	public IntegrationConfig fetchByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator);

	/**
	 * Returns the integration configs before and after the current integration config in the ordered set where campaignId = &#63;.
	 *
	 * @param integrationConfigId the primary key of the current integration config
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next integration config
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	public IntegrationConfig[] findByCampaignId_PrevAndNext(
			long integrationConfigId, long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
				orderByComparator)
		throws NoSuchIntegrationConfigException;

	/**
	 * Removes all the integration configs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(long campaignId);

	/**
	 * Returns the number of integration configs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching integration configs
	 */
	public int countByCampaignId(long campaignId);

	/**
	 * Caches the integration config in the entity cache if it is enabled.
	 *
	 * @param integrationConfig the integration config
	 */
	public void cacheResult(IntegrationConfig integrationConfig);

	/**
	 * Caches the integration configs in the entity cache if it is enabled.
	 *
	 * @param integrationConfigs the integration configs
	 */
	public void cacheResult(
		java.util.List<IntegrationConfig> integrationConfigs);

	/**
	 * Creates a new integration config with the primary key. Does not add the integration config to the database.
	 *
	 * @param integrationConfigId the primary key for the new integration config
	 * @return the new integration config
	 */
	public IntegrationConfig create(long integrationConfigId);

	/**
	 * Removes the integration config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config that was removed
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	public IntegrationConfig remove(long integrationConfigId)
		throws NoSuchIntegrationConfigException;

	public IntegrationConfig updateImpl(IntegrationConfig integrationConfig);

	/**
	 * Returns the integration config with the primary key or throws a <code>NoSuchIntegrationConfigException</code> if it could not be found.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config
	 * @throws NoSuchIntegrationConfigException if a integration config with the primary key could not be found
	 */
	public IntegrationConfig findByPrimaryKey(long integrationConfigId)
		throws NoSuchIntegrationConfigException;

	/**
	 * Returns the integration config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param integrationConfigId the primary key of the integration config
	 * @return the integration config, or <code>null</code> if a integration config with the primary key could not be found
	 */
	public IntegrationConfig fetchByPrimaryKey(long integrationConfigId);

	/**
	 * Returns all the integration configs.
	 *
	 * @return the integration configs
	 */
	public java.util.List<IntegrationConfig> findAll();

	/**
	 * Returns a range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @return the range of integration configs
	 */
	public java.util.List<IntegrationConfig> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of integration configs
	 */
	public java.util.List<IntegrationConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator);

	/**
	 * Returns an ordered range of all the integration configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>IntegrationConfigModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of integration configs
	 * @param end the upper bound of the range of integration configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of integration configs
	 */
	public java.util.List<IntegrationConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IntegrationConfig>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the integration configs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of integration configs.
	 *
	 * @return the number of integration configs
	 */
	public int countAll();

}