package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.exception.FormKeyException;
import io.github.valtairnascimento.campaignforms.internal.util.DraftLockUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.base.FormLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.Form",
	service = AopService.class
)
public class FormLocalServiceImpl extends FormLocalServiceBaseImpl {
	@Override
	public Form addForm(
			long userId, long campaignId, String formKey, String name)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		if (!CampaignValidator.isValidKey(formKey)) {
			throw new FormKeyException("form-key-is-invalid");
		}

		if (formPersistence.fetchByC_FK(campaignId, formKey) != null) {
			throw new FormKeyException("form-key-must-be-unique");
		}

		User user = userLocalService.getUser(userId);

		Form form = formPersistence.create(counterLocalService.increment());

		form.setGroupId(campaign.getGroupId());
		form.setCompanyId(campaign.getCompanyId());
		form.setUserId(userId);
		form.setUserName(user.getFullName());

		Date now = new Date();

		form.setCreateDate(now);
		form.setModifiedDate(now);

		form.setCampaignId(campaignId);
		form.setFormKey(formKey);
		form.setName(name);

		form = formPersistence.update(form);

		FormVersion formVersion = _formVersionLocalService.addFormVersion(
			userId, form, 1);

		form.setDraftVersionId(formVersion.getFormVersionId());

		return formPersistence.update(form);
	}

	@Override
	public void deleteCampaignForms(long campaignId) {
		for (Form form : formPersistence.findByCampaignId(campaignId)) {
			_formVersionLocalService.deleteFormVersions(form.getFormId());

			formPersistence.remove(form);
		}
	}

	@Override
	public Form fetchCampaignForm(long campaignId) {
		return formPersistence.fetchByC_FK(
			campaignId, CampaignConstants.DEFAULT_FORM_KEY);
	}

	@Override
	public Form getCampaignForm(long campaignId) throws PortalException {
		return formPersistence.findByC_FK(
			campaignId, CampaignConstants.DEFAULT_FORM_KEY);
	}

	@Override
	public List<Form> getCampaignForms(long campaignId) {
		return formPersistence.findByCampaignId(campaignId);
	}

	@Override
	public FormVersion getOrCreateDraftFormVersion(long userId, long formId)
		throws PortalException {
		Form form = formPersistence.findByPrimaryKey(formId);

		if (form.getDraftVersionId() > 0) {
			return formVersionPersistence.findByPrimaryKey(
				form.getDraftVersionId());
		}

		FormVersion draftFormVersion = _formVersionLocalService.copyFormVersion(
			userId, form.getPublishedVersionId(), form,
			_formVersionLocalService.getNextVersion(formId));

		form.setDraftVersionId(draftFormVersion.getFormVersionId());
		form.setModifiedDate(new Date());

		formPersistence.update(form);

		return draftFormVersion;
	}

	@Override
	public Form publishDraftFormVersion(long userId, long formId)
		throws PortalException {
		Form form = formPersistence.findByPrimaryKey(formId);

		if (form.getDraftVersionId() <= 0) {
			return form;
		}

		if (form.getPublishedVersionId() > 0) {
			_formVersionLocalService.updateStatus(
				userId, form.getPublishedVersionId(),
				FormVersionStatus.SUPERSEDED);
		}

		_formVersionLocalService.updateStatus(
			userId, form.getDraftVersionId(), FormVersionStatus.PUBLISHED);

		form.setPublishedVersionId(form.getDraftVersionId());
		form.setDraftVersionId(0);
		form.setModifiedDate(new Date());

		return formPersistence.update(form);
	}

	@Override
	public FormVersion replaceDraftFormVersion(
			long userId, long formId, long sourceFormVersionId)
		throws PortalException {
		formPersistence.clearCache(Collections.<Serializable>singleton(formId));

		Form form = formPersistence.findByPrimaryKey(formId);

		if (form.getDraftVersionId() > 0) {
			FormVersion currentDraftFormVersion =
				formVersionPersistence.findByPrimaryKey(
					form.getDraftVersionId());

			DraftLockUtil.checkDraft(currentDraftFormVersion);

			_formFieldLocalService.deleteFormVersionFields(
				currentDraftFormVersion.getFormVersionId());

			formVersionPersistence.remove(currentDraftFormVersion);
		}

		FormVersion draftFormVersion = _formVersionLocalService.copyFormVersion(
			userId, sourceFormVersionId, form,
			_formVersionLocalService.getNextVersion(formId));

		form.setDraftVersionId(draftFormVersion.getFormVersionId());
		form.setModifiedDate(new Date());

		formPersistence.update(form);

		return draftFormVersion;
	}

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;
}
