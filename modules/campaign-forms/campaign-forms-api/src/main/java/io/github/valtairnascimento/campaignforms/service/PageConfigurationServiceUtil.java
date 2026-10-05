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

import com.liferay.portal.kernel.exception.PortalException;

import io.github.valtairnascimento.campaignforms.model.PageConfiguration;

import java.util.List;

/**
 * Provides the remote service utility for PageConfiguration. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.PageConfigurationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see PageConfigurationService
 * @generated
 */
public class PageConfigurationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.PageConfigurationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			addPageBlock(
				long campaignId, String blockType, String configuration,
				boolean enabled)
		throws PortalException {

		return getService().addPageBlock(
			campaignId, blockType, configuration, enabled);
	}

	public static PageConfiguration applyPageTemplate(
			long campaignId, String templateKey)
		throws PortalException {

		return getService().applyPageTemplate(campaignId, templateKey);
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			deletePageBlock(long pageBlockId)
		throws PortalException {

		return getService().deletePageBlock(pageBlockId);
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			duplicatePageBlock(long pageBlockId)
		throws PortalException {

		return getService().duplicatePageBlock(pageBlockId);
	}

	public static PageConfiguration getDraftPageConfiguration(long campaignId)
		throws PortalException {

		return getService().getDraftPageConfiguration(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			getPageBlock(long pageBlockId)
		throws PortalException {

		return getService().getPageBlock(pageBlockId);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				getPageBlocks(long campaignId)
			throws PortalException {

		return getService().getPageBlocks(campaignId);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				movePageBlock(long pageBlockId, int position)
			throws PortalException {

		return getService().movePageBlock(pageBlockId, position);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				reorderPageBlocks(long campaignId, long[] pageBlockIds)
			throws PortalException {

		return getService().reorderPageBlocks(campaignId, pageBlockIds);
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			updatePageBlock(
				long pageBlockId, String configuration, boolean enabled)
		throws PortalException {

		return getService().updatePageBlock(
			pageBlockId, configuration, enabled);
	}

	public static PageConfiguration updatePageSettings(
			long campaignId,
			io.github.valtairnascimento.campaignforms.input.PageSettingsInput
				pageSettingsInput)
		throws PortalException {

		return getService().updatePageSettings(campaignId, pageSettingsInput);
	}

	public static PageConfigurationService getService() {
		return _service;
	}

	private static volatile PageConfigurationService _service;

}