package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.exception.FormVersionLockedException;
import io.github.valtairnascimento.campaignforms.exception.FormVersionSettingsException;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.util.DraftLockUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.base.FormVersionLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.FormVersion",
	service = AopService.class
)
public class FormVersionLocalServiceImpl
	extends FormVersionLocalServiceBaseImpl {
	@Override
	public FormVersion addFormVersion(long userId, Form form, int version)
		throws PortalException {
		FormVersion formVersion = _createFormVersion(userId, form, version);

		formVersion.setConsentURL(_getLatestConsentURL(form.getGroupId()));
		formVersion.setSettings("{}");

		return formVersionPersistence.update(formVersion);
	}

	@Override
	public FormVersion copyFormVersion(
			long userId, long sourceFormVersionId, Form targetForm,
			int version)
		throws PortalException {
		FormVersion sourceFormVersion = formVersionPersistence.findByPrimaryKey(
			sourceFormVersionId);

		FormVersion formVersion = _createFormVersion(
			userId, targetForm, version);

		formVersion.setSubmitLabel(sourceFormVersion.getSubmitLabel());
		formVersion.setSuccessTitle(sourceFormVersion.getSuccessTitle());
		formVersion.setSuccessMessage(sourceFormVersion.getSuccessMessage());
		formVersion.setRedirectUrl(sourceFormVersion.getRedirectUrl());
		formVersion.setCaptchaEnabled(sourceFormVersion.isCaptchaEnabled());
		formVersion.setRequireAuthentication(
			sourceFormVersion.isRequireAuthentication());
		formVersion.setConsentRequired(sourceFormVersion.isConsentRequired());
		formVersion.setConsentText(sourceFormVersion.getConsentText());
		formVersion.setConsentURL(sourceFormVersion.getConsentURL());
		formVersion.setConsentVersion(sourceFormVersion.getConsentVersion());
		formVersion.setSaveIncompleteSubmission(
			sourceFormVersion.isSaveIncompleteSubmission());
		formVersion.setSettings(sourceFormVersion.getSettings());

		formVersion = formVersionPersistence.update(formVersion);

		_formFieldLocalService.copyFormFields(
			sourceFormVersionId, formVersion);

		return formVersion;
	}

	@Override
	public FormVersion deleteFormVersion(long formVersionId)
		throws PortalException {
		FormVersion formVersion = formVersionPersistence.findByPrimaryKey(
			formVersionId);

		DraftLockUtil.checkDraft(formVersion);

		if (submissionPersistence.countByFormVersionId(formVersionId) > 0) {
			throw new FormVersionLockedException(
				"form-version-with-submissions-cannot-be-deleted");
		}

		Form form = formPersistence.fetchByPrimaryKey(formVersion.getFormId());

		if ((form != null) && (form.getDraftVersionId() == formVersionId)) {
			form.setDraftVersionId(0);

			formPersistence.update(form);
		}

		_formFieldLocalService.deleteFormVersionFields(formVersionId);

		return formVersionPersistence.remove(formVersion);
	}

	@Override
	public void deleteFormVersions(long formId) {
		for (FormVersion formVersion :
				formVersionPersistence.findByFormId(formId)) {
			_formFieldLocalService.deleteFormVersionFields(
				formVersion.getFormVersionId());

			formVersionPersistence.remove(formVersion);
		}
	}

	@Override
	public List<FormVersion> getFormVersions(long formId) {
		return formVersionPersistence.findByFormId(formId);
	}

	@Override
	public int getNextVersion(long formId) {
		List<FormVersion> formVersions = formVersionPersistence.findByFormId(
			formId, 0, 1);

		if (formVersions.isEmpty()) {
			return 1;
		}

		FormVersion latestFormVersion = formVersions.get(0);

		return latestFormVersion.getVersion() + 1;
	}

	@Override
	public FormVersion updateFormVersion(
			long formVersionId, FormVersionInput formVersionInput)
		throws PortalException {
		FormVersion formVersion = formVersionPersistence.findByPrimaryKey(
			formVersionId);

		DraftLockUtil.checkDraft(formVersion);

		_validate(formVersionInput);

		formVersion.setModifiedDate(new Date());
		formVersion.setSubmitLabel(formVersionInput.getSubmitLabel());
		formVersion.setSuccessTitle(formVersionInput.getSuccessTitle());
		formVersion.setSuccessMessage(formVersionInput.getSuccessMessage());
		formVersion.setRedirectUrl(formVersionInput.getRedirectUrl());
		formVersion.setCaptchaEnabled(formVersionInput.isCaptchaEnabled());
		formVersion.setRequireAuthentication(
			formVersionInput.isRequireAuthentication());
		formVersion.setConsentRequired(formVersionInput.isConsentRequired());
		formVersion.setConsentText(
			_trimToNull(formVersionInput.getConsentText()));
		formVersion.setConsentURL(_trimToNull(formVersionInput.getConsentURL()));
		formVersion.setConsentVersion(
			_trimToNull(formVersionInput.getConsentVersion()));
		formVersion.setSaveIncompleteSubmission(
			formVersionInput.isSaveIncompleteSubmission());

		try {
			formVersion.setSettings(
				_jsonSettingsHelper.normalizeObject(
					formVersionInput.getSettings()));
		}
		catch (JSONException jsonException) {
			throw new FormVersionSettingsException(
				"form-settings-are-invalid", jsonException);
		}

		return formVersionPersistence.update(formVersion);
	}

	@Override
	public FormVersion updateStatus(
			long userId, long formVersionId, FormVersionStatus status)
		throws PortalException {
		FormVersion formVersion = formVersionPersistence.findByPrimaryKey(
			formVersionId);

		FormVersionStatus currentStatus = FormVersionStatus.parse(
			formVersion.getStatus());

		boolean validTransition = false;

		if ((currentStatus == FormVersionStatus.DRAFT) &&
			(status == FormVersionStatus.PUBLISHED)) {
			formVersion.setPublishedDate(new Date());
			formVersion.setPublishedByUserId(userId);

			validTransition = true;
		}
		else if ((currentStatus == FormVersionStatus.PUBLISHED) &&
				 (status == FormVersionStatus.SUPERSEDED)) {
			validTransition = true;
		}

		if (!validTransition) {
			throw new FormVersionSettingsException(
				"form-version-status-transition-is-invalid");
		}

		formVersion.setStatus(status.name());
		formVersion.setModifiedDate(new Date());

		return formVersionPersistence.update(formVersion);
	}

	private FormVersion _createFormVersion(long userId, Form form, int version)
		throws PortalException {
		User user = userLocalService.getUser(userId);

		FormVersion formVersion = formVersionPersistence.create(
			counterLocalService.increment());

		formVersion.setGroupId(form.getGroupId());
		formVersion.setCompanyId(form.getCompanyId());
		formVersion.setUserId(userId);
		formVersion.setUserName(user.getFullName());

		Date now = new Date();

		formVersion.setCreateDate(now);
		formVersion.setModifiedDate(now);

		formVersion.setFormId(form.getFormId());
		formVersion.setVersion(version);
		formVersion.setStatus(FormVersionStatus.DRAFT.name());

		return formVersion;
	}

	private void _validate(FormVersionInput formVersionInput)
		throws PortalException {
		if (!CampaignValidator.isValidRedirectUrl(
				formVersionInput.getRedirectUrl())) {
			throw new FormVersionSettingsException("redirect-url-is-invalid");
		}

		String consentURL = _trimToNull(formVersionInput.getConsentURL());

		if ((consentURL != null) &&
			(!CampaignValidator.isValidRedirectUrl(consentURL) ||
			 (consentURL.length() > 2048))) {
			throw new FormVersionSettingsException("consent-url-is-invalid");
		}

		if ((formVersionInput.getSubmitLabel() != null) &&
			(formVersionInput.getSubmitLabel(
			).length() > 150)) {
			throw new FormVersionSettingsException("submit-label-is-too-long");
		}

		if ((formVersionInput.getSuccessTitle() != null) &&
			(formVersionInput.getSuccessTitle(
			).length() > 255)) {
			throw new FormVersionSettingsException(
				"success-title-is-too-long");
		}

		if ((formVersionInput.getConsentVersion() != null) &&
			(formVersionInput.getConsentVersion(
			).length() > 75)) {
			throw new FormVersionSettingsException(
				"consent-version-is-too-long");
		}
	}

	private String _getLatestConsentURL(long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));

		dynamicQuery.add(RestrictionsFactoryUtil.like("consentURL", "_%"));
		dynamicQuery.addOrder(OrderFactoryUtil.desc("modifiedDate"));
		dynamicQuery.setProjection(ProjectionFactoryUtil.property("consentURL"));

		List<String> consentURLs = dynamicQuery(dynamicQuery, 0, 1);

		if (consentURLs.isEmpty() ||
			CampaignValidator.isBlank(consentURLs.get(0))) {
			return null;
		}

		return consentURLs.get(0);
	}

	private String _trimToNull(String value) {
		if (value == null) {
			return null;
		}

		String trimmed = value.trim();

		return trimmed.isEmpty() ? null : trimmed;
	}

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
