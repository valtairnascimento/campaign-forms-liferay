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
 * Provides a wrapper for {@link PageTemplateService}.
 *
 * @author Valtair Nascimento
 * @see PageTemplateService
 * @generated
 */
public class PageTemplateServiceWrapper
	implements PageTemplateService, ServiceWrapper<PageTemplateService> {

	public PageTemplateServiceWrapper() {
		this(null);
	}

	public PageTemplateServiceWrapper(PageTemplateService pageTemplateService) {
		_pageTemplateService = pageTemplateService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pageTemplateService.getOSGiServiceIdentifier();
	}

	@Override
	public PageTemplateService getWrappedService() {
		return _pageTemplateService;
	}

	@Override
	public void setWrappedService(PageTemplateService pageTemplateService) {
		_pageTemplateService = pageTemplateService;
	}

	private PageTemplateService _pageTemplateService;

}