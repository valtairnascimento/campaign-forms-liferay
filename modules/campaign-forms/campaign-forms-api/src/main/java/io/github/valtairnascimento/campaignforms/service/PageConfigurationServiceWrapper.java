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

package io.github.valtairnascimento.campaignforms.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PageConfigurationService}.
 *
 * @author Valtair Nascimento
 * @see PageConfigurationService
 * @generated
 */
public class PageConfigurationServiceWrapper
	implements PageConfigurationService,
			   ServiceWrapper<PageConfigurationService> {

	public PageConfigurationServiceWrapper() {
		this(null);
	}

	public PageConfigurationServiceWrapper(
		PageConfigurationService pageConfigurationService) {

		_pageConfigurationService = pageConfigurationService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			addPageBlock(
				long campaignId, String blockType, String configuration,
				boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.addPageBlock(
			campaignId, blockType, configuration, enabled);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			applyPageTemplate(long campaignId, String templateKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.applyPageTemplate(
			campaignId, templateKey);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			deletePageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.deletePageBlock(pageBlockId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			duplicatePageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.duplicatePageBlock(pageBlockId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			getDraftPageConfiguration(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.getDraftPageConfiguration(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageConfigurationService.getOSGiServiceIdentifier();
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			getPageBlock(long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.getPageBlock(pageBlockId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				getPageBlocks(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.getPageBlocks(campaignId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				movePageBlock(long pageBlockId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.movePageBlock(pageBlockId, position);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				reorderPageBlocks(long campaignId, long[] pageBlockIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.reorderPageBlocks(
			campaignId, pageBlockIds);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageBlock
			updatePageBlock(
				long pageBlockId, String configuration, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.updatePageBlock(
			pageBlockId, configuration, enabled);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.PageConfiguration
			updatePageSettings(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.
					PageSettingsInput pageSettingsInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pageConfigurationService.updatePageSettings(
			campaignId, pageSettingsInput);
	}

	@Override
	public PageConfigurationService getWrappedService() {
		return _pageConfigurationService;
	}

	@Override
	public void setWrappedService(
		PageConfigurationService pageConfigurationService) {

		_pageConfigurationService = pageConfigurationService;
	}

	private PageConfigurationService _pageConfigurationService;

}