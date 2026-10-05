package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.base.FormServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=Form"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class FormServiceImpl extends FormServiceBaseImpl {
	@Override
	public FormField addFormField(
			long campaignId, FormFieldInput formFieldInput)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		FormVersion formVersion = _getOrCreateDraftFormVersion(campaignId);

		return _formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), formFieldInput);
	}

	@Override
	public FormField deleteFormField(long formFieldId) throws PortalException {
		_checkFormField(formFieldId, CampaignActionKeys.UPDATE);

		return _formFieldLocalService.deleteFormField(formFieldId);
	}

	@Override
	public FormVersion getCampaignFormVersion(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW);

		Form form = formLocalService.getCampaignForm(campaignId);

		long formVersionId = form.getDraftVersionId();

		if (formVersionId <= 0) {
			formVersionId = form.getPublishedVersionId();
		}

		return _formVersionLocalService.getFormVersion(formVersionId);
	}

	@Override
	public List<FormVersion> getCampaignFormVersions(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW);

		Form form = formLocalService.getCampaignForm(campaignId);

		return _formVersionLocalService.getFormVersions(form.getFormId());
	}

	@Override
	public FormField getFormField(long formFieldId) throws PortalException {
		_checkFormField(formFieldId, CampaignActionKeys.VIEW);

		return _formFieldLocalService.getFormField(formFieldId);
	}

	@Override
	public List<FormField> getFormFields(long formVersionId)
		throws PortalException {
		_checkFormVersion(formVersionId, CampaignActionKeys.VIEW);

		return _formFieldLocalService.getFormFields(formVersionId);
	}

	@Override
	public FormVersion getFormVersion(long formVersionId)
		throws PortalException {
		_checkFormVersion(formVersionId, CampaignActionKeys.VIEW);

		return _formVersionLocalService.getFormVersion(formVersionId);
	}

	@Override
	public FormVersion getOrCreateDraftFormVersion(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		return _getOrCreateDraftFormVersion(campaignId);
	}

	@Override
	public List<FormField> moveFormField(long formFieldId, int position)
		throws PortalException {
		_checkFormField(formFieldId, CampaignActionKeys.UPDATE);

		return _formFieldLocalService.moveFormField(formFieldId, position);
	}

	@Override
	public List<FormField> reorderFormFields(
			long campaignId, long[] formFieldIds)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		Form form = formLocalService.getCampaignForm(campaignId);

		if (form.getDraftVersionId() <= 0) {
			throw new FormFieldConfigurationException("field-order-is-invalid");
		}

		return _formFieldLocalService.reorderFormFields(
			form.getDraftVersionId(), formFieldIds);
	}

	@Override
	public FormVersion updateDraftFormVersion(
			long campaignId, FormVersionInput formVersionInput)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		FormVersion formVersion = _getOrCreateDraftFormVersion(campaignId);

		return _formVersionLocalService.updateFormVersion(
			formVersion.getFormVersionId(), formVersionInput);
	}

	@Override
	public FormField updateFormField(
			long formFieldId, FormFieldInput formFieldInput)
		throws PortalException {
		_checkFormField(formFieldId, CampaignActionKeys.UPDATE);

		return _formFieldLocalService.updateFormField(
			formFieldId, formFieldInput);
	}

	private void _check(long campaignId, String actionId)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, actionId);
	}

	private void _checkFormField(long formFieldId, String actionId)
		throws PortalException {
		FormField formField = _formFieldLocalService.getFormField(formFieldId);

		_checkFormVersion(formField.getFormVersionId(), actionId);
	}

	private void _checkFormVersion(long formVersionId, String actionId)
		throws PortalException {
		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			formVersionId);

		Form form = formLocalService.getForm(formVersion.getFormId());

		_check(form.getCampaignId(), actionId);
	}

	private FormVersion _getOrCreateDraftFormVersion(long campaignId)
		throws PortalException {
		Form form = formLocalService.getCampaignForm(campaignId);

		return formLocalService.getOrCreateDraftFormVersion(
			getUserId(), form.getFormId());
	}

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;
}
