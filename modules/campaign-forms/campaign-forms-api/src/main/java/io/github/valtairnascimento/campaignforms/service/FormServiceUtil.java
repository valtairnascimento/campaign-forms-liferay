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

import java.util.List;

/**
 * Provides the remote service utility for Form. This utility wraps
 * <code>io.github.valtairnascimento.campaignforms.service.impl.FormServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Valtair Nascimento
 * @see FormService
 * @generated
 */
public class FormServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>io.github.valtairnascimento.campaignforms.service.impl.FormServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static io.github.valtairnascimento.campaignforms.model.FormField
			addFormField(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws PortalException {

		return getService().addFormField(campaignId, formFieldInput);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			deleteFormField(long formFieldId)
		throws PortalException {

		return getService().deleteFormField(formFieldId);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getCampaignFormVersion(long campaignId)
		throws PortalException {

		return getService().getCampaignFormVersion(campaignId);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.FormVersion>
				getCampaignFormVersions(long campaignId)
			throws PortalException {

		return getService().getCampaignFormVersions(campaignId);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			getFormField(long formFieldId)
		throws PortalException {

		return getService().getFormField(formFieldId);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				getFormFields(long formVersionId)
			throws PortalException {

		return getService().getFormFields(formVersionId);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getFormVersion(long formVersionId)
		throws PortalException {

		return getService().getFormVersion(formVersionId);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getOrCreateDraftFormVersion(long campaignId)
		throws PortalException {

		return getService().getOrCreateDraftFormVersion(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				moveFormField(long formFieldId, int position)
			throws PortalException {

		return getService().moveFormField(formFieldId, position);
	}

	public static List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				reorderFormFields(long campaignId, long[] formFieldIds)
			throws PortalException {

		return getService().reorderFormFields(campaignId, formFieldIds);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			updateDraftFormVersion(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormVersionInput
					formVersionInput)
		throws PortalException {

		return getService().updateDraftFormVersion(
			campaignId, formVersionInput);
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			updateFormField(
				long formFieldId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws PortalException {

		return getService().updateFormField(formFieldId, formFieldInput);
	}

	public static FormService getService() {
		return _service;
	}

	private static volatile FormService _service;

}