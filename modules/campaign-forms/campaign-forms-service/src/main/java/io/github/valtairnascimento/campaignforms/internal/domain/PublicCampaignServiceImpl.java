package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.internal.submission.ConsentTerms;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignQuotaLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldOptionLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PublicCampaignService.class)
public class PublicCampaignServiceImpl implements PublicCampaignService {
	@Override
	public String getClosedMessage(
		PublicCampaignSnapshot publicCampaignSnapshot, Locale locale) {
		String messageKey = _getClosedMessageKey(publicCampaignSnapshot);

		if (messageKey == null) {
			return null;
		}

		String message = publicCampaignSnapshot.getClosedMessage();

		if (messageKey.equals(_LIMIT_REACHED)) {
			message = publicCampaignSnapshot.getLimitReachedMessage();
		}

		if (Validator.isNotNull(message)) {
			return message;
		}

		return CampaignLanguageUtil.get(locale, messageKey);
	}

	@Override
	public PublicCampaignSnapshot getPreviewSnapshot(
			long groupId, String slug, PermissionChecker permissionChecker)
		throws PortalException {
		Campaign campaign = _campaignLocalService.fetchCampaignBySlug(
			groupId, slug);

		if (campaign == null) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		_campaignModelResourcePermission.check(
			permissionChecker, campaign, CampaignActionKeys.UPDATE);

		Form form = _formLocalService.fetchCampaignForm(
			campaign.getCampaignId());

		if (form == null) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		long formVersionId = form.getDraftVersionId();

		if (formVersionId <= 0) {
			formVersionId = form.getPublishedVersionId();
		}

		return _toSnapshot(
			campaign, form, _formVersionLocalService.getFormVersion(formVersionId),
			PageStage.DRAFT, true);
	}

	@Override
	public PublicCampaignSnapshot getPublishedSnapshot(
			long groupId, String slug)
		throws PortalException {
		Campaign campaign = _campaignLocalService.fetchCampaignBySlug(
			groupId, slug);

		if ((campaign == null) ||
			CampaignStatus.DRAFT.matches(campaign.getStatus())) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		Form form = _formLocalService.fetchCampaignForm(
			campaign.getCampaignId());

		if ((form == null) || (form.getPublishedVersionId() <= 0)) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		return _toSnapshot(
			campaign, form,
			_formVersionLocalService.getFormVersion(form.getPublishedVersionId()),
			PageStage.PUBLISHED, false);
	}

	private String _getClosedMessageKey(
		PublicCampaignSnapshot publicCampaignSnapshot) {
		if (!CampaignStatus.PUBLISHED.matches(
				publicCampaignSnapshot.getStatus())) {
			return "campaign-is-closed";
		}

		Date now = new Date();

		Date startDate = publicCampaignSnapshot.getStartDate();

		if ((startDate != null) && now.before(startDate)) {
			return "campaign-has-not-started";
		}

		Date endDate = publicCampaignSnapshot.getEndDate();

		if ((endDate != null) && !now.before(endDate)) {
			return "campaign-has-ended";
		}

		int submissionLimit = publicCampaignSnapshot.getSubmissionLimit();

		if ((submissionLimit > 0) &&
			(_campaignQuotaLocalService.getUsedSlots(
				publicCampaignSnapshot.getCampaignId()) >= submissionLimit)) {
			return _LIMIT_REACHED;
		}

		return null;
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	private PublicCampaignSnapshot.Field _toField(
		FormField formField, List<FormFieldOption> formFieldOptions) {
		List<PublicCampaignSnapshot.Option> options = new ArrayList<>(
			formFieldOptions.size());

		for (FormFieldOption formFieldOption : formFieldOptions) {
			options.add(
				new PublicCampaignSnapshot.Option(
					formFieldOption.getLabel(), formFieldOption.getValue()));
		}

		FieldType fieldType = _fieldTypeRegistry.getFieldType(
			formField.getType());

		return new PublicCampaignSnapshot.Field(
			formField.getFieldKey(), formField.getType(), formField.getLabel(),
			_nullIfEmpty(formField.getPlaceholder()),
			_nullIfEmpty(formField.getHelpText()), formField.isRequired(),
			_nullIfEmpty(formField.getDefaultValue()), formField.getWidth(),
			(fieldType != null) && fieldType.isMultiValued(),
			_toMap(formField.getValidation()),
			_toMap(formField.getProperties()), options);
	}

	private PublicCampaignSnapshot.Form _toForm(
		Form form, FormVersion formVersion, Locale locale) {
		List<FormField> formFields = _formFieldLocalService.getFormFields(
			formVersion.getFormVersionId());

		long[] formFieldIds = new long[formFields.size()];

		for (int i = 0; i < formFields.size(); i++) {
			FormField formField = formFields.get(i);

			formFieldIds[i] = formField.getFormFieldId();
		}

		Map<Long, List<FormFieldOption>> formFieldOptionsMap =
			_formFieldOptionLocalService.getFormFieldOptionsMap(formFieldIds);

		List<PublicCampaignSnapshot.Field> fields = new ArrayList<>(
			formFields.size());

		for (FormField formField : formFields) {
			fields.add(
				_toField(
					formField,
					formFieldOptionsMap.getOrDefault(
						formField.getFormFieldId(),
						Collections.emptyList())));
		}

		return new PublicCampaignSnapshot.Form(
			form.getFormId(), formVersion.getFormVersionId(),
			formVersion.getVersion(),
			_nullIfEmpty(formVersion.getSubmitLabel()),
			_nullIfEmpty(formVersion.getSuccessTitle()),
			_nullIfEmpty(formVersion.getSuccessMessage()),
			_nullIfEmpty(formVersion.getRedirectUrl()),
			formVersion.isCaptchaEnabled(),
			formVersion.isRequireAuthentication(),
			formVersion.isConsentRequired(),
			formVersion.isConsentRequired() ?
				ConsentTerms.getText(formVersion, locale) : null,
			formVersion.isConsentRequired() ? ConsentTerms.getURL(formVersion) :
				null,
			formVersion.isConsentRequired() ?
				ConsentTerms.getVersion(formVersion) : null,
			fields);
	}

	private JSONObject _toJSONObject(String json) {
		if (Validator.isNull(json)) {
			return _jsonFactory.createJSONObject();
		}

		try {
			return _jsonFactory.createJSONObject(json);
		}
		catch (JSONException jsonException) {
			return _jsonFactory.createJSONObject();
		}
	}

	private Map<String, Object> _toMap(String json) {
		JSONObject jsonObject = _toJSONObject(json);

		if (jsonObject.length() == 0) {
			return Collections.emptyMap();
		}

		return jsonObject.toMap();
	}

	private PublicCampaignSnapshot.Page _toPage(
		Campaign campaign, PageStage pageStage) {
		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.fetchPageConfiguration(
				campaign.getCampaignId(), pageStage);

		if (pageConfiguration == null) {
			return new PublicCampaignSnapshot.Page(
				null, null, null, null, null, null, false, null, null, 0,
				Collections.emptyList());
		}

		List<PublicCampaignSnapshot.Block> blocks = new ArrayList<>();

		for (PageBlock pageBlock :
				_pageBlockLocalService.getPageBlocks(
					pageConfiguration.getPageConfigurationId())) {
			if (!pageBlock.isEnabled()) {
				continue;
			}

			BlockType blockType = _blockTypeRegistry.getBlockType(
				pageBlock.getBlockType());

			if (blockType == null) {
				continue;
			}

			JSONObject configurationJSONObject = _toJSONObject(
				pageBlock.getConfiguration());

			if (pageBlock.getSchemaVersion() < blockType.getSchemaVersion()) {
				configurationJSONObject = blockType.migrate(
					configurationJSONObject, pageBlock.getSchemaVersion());
			}

			blocks.add(
				new PublicCampaignSnapshot.Block(
					pageBlock.getBlockKey(), pageBlock.getBlockType(),
					blockType.getSchemaVersion(),
					configurationJSONObject.toMap()));
		}

		return new PublicCampaignSnapshot.Page(
			_nullIfEmpty(pageConfiguration.getTemplateKey()),
			_nullIfEmpty(pageConfiguration.getThemeKey()),
			_nullIfEmpty(pageConfiguration.getCssClass()),
			_nullIfEmpty(pageConfiguration.getSeoTitle()),
			_nullIfEmpty(pageConfiguration.getSeoDescription()),
			_nullIfEmpty(pageConfiguration.getCanonicalUrl()),
			pageConfiguration.isNoIndex(),
			_nullIfEmpty(pageConfiguration.getOgTitle()),
			_nullIfEmpty(pageConfiguration.getOgDescription()),
			pageConfiguration.getOgImageFileEntryId(), blocks);
	}

	private Locale _getLocale(Campaign campaign) {
		if (Validator.isNull(campaign.getDefaultLanguageId())) {
			return LocaleUtil.getSiteDefault();
		}

		return LocaleUtil.fromLanguageId(campaign.getDefaultLanguageId());
	}

	private PublicCampaignSnapshot _toSnapshot(
		Campaign campaign, Form form, FormVersion formVersion,
		PageStage pageStage, boolean preview) {
		JSONObject settingsJSONObject = _toJSONObject(campaign.getSettings());

		return new PublicCampaignSnapshot(
			campaign.getCampaignId(), campaign.getCompanyId(),
			campaign.getGroupId(), campaign.getSlug(), campaign.getName(),
			_nullIfEmpty(campaign.getDescription()), campaign.getMode(),
			campaign.getStatus(), campaign.getDefaultLanguageId(),
			campaign.getStartDate(), campaign.getEndDate(),
			campaign.getSubmissionLimit(),
			_nullIfEmpty(settingsJSONObject.getString("closedMessage")),
			_nullIfEmpty(settingsJSONObject.getString("limitReachedMessage")),
			_toForm(form, formVersion, _getLocale(campaign)),
			_toPage(campaign, pageStage), preview);
	}

	private static final String _LIMIT_REACHED = "submission-limit-reached";

	@Reference
	private BlockTypeRegistry _blockTypeRegistry;

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private CampaignQuotaLocalService _campaignQuotaLocalService;

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormFieldOptionLocalService _formFieldOptionLocalService;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
