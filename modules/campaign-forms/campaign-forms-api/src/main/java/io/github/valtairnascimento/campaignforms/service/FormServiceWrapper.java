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
 * Provides a wrapper for {@link FormService}.
 *
 * @author Valtair Nascimento
 * @see FormService
 * @generated
 */
public class FormServiceWrapper
	implements FormService, ServiceWrapper<FormService> {

	public FormServiceWrapper() {
		this(null);
	}

	public FormServiceWrapper(FormService formService) {
		_formService = formService;
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			addFormField(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.addFormField(campaignId, formFieldInput);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			deleteFormField(long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.deleteFormField(formFieldId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			getCampaignFormVersion(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getCampaignFormVersion(campaignId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormVersion>
				getCampaignFormVersions(long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getCampaignFormVersions(campaignId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			getFormField(long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getFormField(formFieldId);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				getFormFields(long formVersionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getFormFields(formVersionId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			getFormVersion(long formVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getFormVersion(formVersionId);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			getOrCreateDraftFormVersion(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.getOrCreateDraftFormVersion(campaignId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _formService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				moveFormField(long formFieldId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.moveFormField(formFieldId, position);
	}

	@Override
	public java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				reorderFormFields(long campaignId, long[] formFieldIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.reorderFormFields(campaignId, formFieldIds);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormVersion
			updateDraftFormVersion(
				long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormVersionInput
					formVersionInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.updateDraftFormVersion(
			campaignId, formVersionInput);
	}

	@Override
	public io.github.valtairnascimento.campaignforms.model.FormField
			updateFormField(
				long formFieldId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _formService.updateFormField(formFieldId, formFieldInput);
	}

	@Override
	public FormService getWrappedService() {
		return _formService;
	}

	@Override
	public void setWrappedService(FormService formService) {
		_formService = formService;
	}

	private FormService _formService;

}