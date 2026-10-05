package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.exception.CampaignHasSubmissionsException;
import io.github.valtairnascimento.campaignforms.exception.CampaignSettingsException;
import io.github.valtairnascimento.campaignforms.exception.CampaignSlugException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateCampaignExternalReferenceCodeException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateSlugException;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.CampaignQuota;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.IntegrationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
import io.github.valtairnascimento.campaignforms.service.base.CampaignLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign",
	service = AopService.class
)
public class CampaignLocalServiceImpl extends CampaignLocalServiceBaseImpl {
	@Override
	public Campaign addCampaign(
			long userId, long groupId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException {
		User user = userLocalService.getUser(userId);
		Group group = _groupLocalService.getGroup(groupId);

		_validate(0, groupId, campaignInput);

		Campaign campaign = campaignPersistence.create(
			counterLocalService.increment());

		campaign.setUuid(serviceContext.getUuid());
		campaign.setGroupId(groupId);
		campaign.setCompanyId(group.getCompanyId());
		campaign.setUserId(userId);
		campaign.setUserName(user.getFullName());

		Date now = new Date();

		campaign.setCreateDate(serviceContext.getCreateDate(now));
		campaign.setModifiedDate(serviceContext.getModifiedDate(now));

		campaign.setMode(
			campaignInput.getMode(
			).name());
		campaign.setStatus(CampaignStatus.DRAFT.name());

		String defaultLanguageId = campaignInput.getDefaultLanguageId();

		if (Validator.isNull(defaultLanguageId)) {
			defaultLanguageId = group.getDefaultLanguageId();
		}

		campaign.setDefaultLanguageId(defaultLanguageId);

		_setFields(campaign, campaignInput);

		campaign = campaignPersistence.update(campaign);

		if (Validator.isNull(campaign.getExternalReferenceCode())) {
			campaign.setExternalReferenceCode(campaign.getUuid());

			campaign = campaignPersistence.update(campaign);
		}

		resourceLocalService.addModelResources(campaign, serviceContext);

		long campaignId = campaign.getCampaignId();

		_formLocalService.addForm(
			userId, campaignId, CampaignConstants.DEFAULT_FORM_KEY,
			campaign.getName());

		String templateKey = null;

		if (CampaignMode.QUICK.matches(campaign.getMode())) {
			templateKey = CampaignConstants.TEMPLATE_KEY_QUICK_DEFAULT;
		}

		_pageConfigurationLocalService.addPageConfiguration(
			userId, campaignId, PageStage.DRAFT, templateKey);

		_notificationConfigLocalService.addNotificationConfig(
			userId, campaignId,
			new NotificationConfigInput(
			).setName(
				CampaignLanguageUtil.get(
					LocaleUtil.fromLanguageId(defaultLanguageId),
					"default-notification-name")
			).setEventType(
				EventType.SUBMISSION_CREATED
			).setChannel(
				NotificationChannelType.EMAIL
			).setEnabled(
				false
			));

		_audit(
			userId, campaign, AuditAction.CAMPAIGN_ADDED,
			_jsonFactory.createJSONObject(
			).put(
				"mode", campaign.getMode()
			).put(
				"slug", campaign.getSlug()
			));

		return campaign;
	}

	@Override
	public Campaign deleteCampaign(long campaignId) throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		if (submissionPersistence.countByCampaignId(campaignId) > 0) {
			throw new CampaignHasSubmissionsException(
				"campaign-with-submissions-cannot-be-deleted");
		}

		_formLocalService.deleteCampaignForms(campaignId);
		_pageConfigurationLocalService.deleteCampaignPageConfigurations(
			campaignId);
		_notificationConfigLocalService.deleteCampaignNotificationConfigs(
			campaignId);
		_integrationConfigLocalService.deleteCampaignIntegrationConfigs(
			campaignId);

		dataFeedPersistence.removeByCampaignId(campaignId);
		eventDeliveryPersistence.removeByCampaignId(campaignId);

		CampaignQuota campaignQuota = campaignQuotaPersistence.fetchByPrimaryKey(
			campaignId);

		if (campaignQuota != null) {
			campaignQuotaPersistence.remove(campaignQuota);
		}

		resourceLocalService.deleteResource(
			campaign, ResourceConstants.SCOPE_INDIVIDUAL);

		campaign = campaignPersistence.remove(campaign);

		_audit(
			PrincipalThreadLocal.getUserId(), campaign,
			AuditAction.CAMPAIGN_DELETED,
			_jsonFactory.createJSONObject(
			).put(
				"slug", campaign.getSlug()
			));

		return campaign;
	}

	@Override
	public Campaign fetchCampaignBySlug(long groupId, String slug) {
		return campaignPersistence.fetchByG_S(groupId, slug);
	}

	@Override
	public Campaign getCampaignBySlug(long groupId, String slug)
		throws PortalException {
		return campaignPersistence.findByG_S(groupId, slug);
	}

	@Override
	public List<Campaign> getGroupCampaigns(
		long groupId, int start, int end,
		OrderByComparator<Campaign> orderByComparator) {
		return campaignPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupCampaignsCount(long groupId) {
		return campaignPersistence.countByGroupId(groupId);
	}

	@Override
	public List<Campaign> getSeriesCampaigns(long companyId, String seriesKey) {
		return campaignPersistence.findByC_SK(
			companyId, seriesKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public Campaign updateCampaign(
			long userId, long campaignId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		_validate(campaignId, campaign.getGroupId(), campaignInput);

		JSONObject changedFieldsJSONObject = _getChangedFields(
			campaign, campaignInput);

		campaign.setModifiedDate(serviceContext.getModifiedDate(new Date()));

		if (Validator.isNotNull(campaignInput.getDefaultLanguageId())) {
			campaign.setDefaultLanguageId(campaignInput.getDefaultLanguageId());
		}

		_setFields(campaign, campaignInput);

		campaign = campaignPersistence.update(campaign);

		_audit(
			userId, campaign, AuditAction.CAMPAIGN_UPDATED,
			_jsonFactory.createJSONObject(
			).put(
				"changedFields", changedFieldsJSONObject
			));

		return campaign;
	}

	@Override
	public Campaign updateMode(long userId, long campaignId, CampaignMode mode)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		String previousMode = campaign.getMode();

		if (mode.matches(previousMode)) {
			return campaign;
		}

		campaign.setMode(mode.name());
		campaign.setModifiedDate(new Date());

		campaign = campaignPersistence.update(campaign);

		_audit(
			userId, campaign, AuditAction.CAMPAIGN_MODE_CONVERTED,
			_jsonFactory.createJSONObject(
			).put(
				"from", previousMode
			).put(
				"to", mode.name()
			));

		return campaign;
	}

	@Override
	public Campaign updateStatus(
			long userId, long campaignId, CampaignStatus status)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		CampaignStatus previousStatus = CampaignStatus.parse(
			campaign.getStatus());

		Date now = new Date();

		campaign.setStatus(status.name());
		campaign.setModifiedDate(now);

		if (status == CampaignStatus.PUBLISHED) {
			campaign.setPublishedDate(now);
			campaign.setPublishedByUserId(userId);
		}

		campaign = campaignPersistence.update(campaign);

		_audit(
			userId, campaign, _getAuditAction(previousStatus, status),
			_jsonFactory.createJSONObject(
			).put(
				"from", previousStatus.name()
			).put(
				"to", status.name()
			));

		return campaign;
	}

	private void _audit(
		long userId, Campaign campaign, AuditAction auditAction,
		JSONObject detailsJSONObject) {
		_auditEntryLocalService.addAuditEntry(
			campaign.getCompanyId(), campaign.getGroupId(), userId,
			campaign.getCampaignId(), auditAction, Campaign.class.getName(),
			campaign.getCampaignId(), detailsJSONObject);
	}

	private AuditAction _getAuditAction(
		CampaignStatus previousStatus, CampaignStatus status) {
		if (status == CampaignStatus.ARCHIVED) {
			return AuditAction.CAMPAIGN_ARCHIVED;
		}

		if (previousStatus == CampaignStatus.ARCHIVED) {
			return AuditAction.CAMPAIGN_REOPENED;
		}

		if (status == CampaignStatus.PUBLISHED) {
			return AuditAction.CAMPAIGN_PUBLISHED;
		}

		return AuditAction.CAMPAIGN_UPDATED;
	}

	private JSONObject _getChangedFields(
		Campaign campaign, CampaignInput campaignInput) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		_putIfChanged(
			jsonObject, "name", campaign.getName(), campaignInput.getName());
		_putIfChanged(
			jsonObject, "slug", campaign.getSlug(), campaignInput.getSlug());
		_putIfChanged(
			jsonObject, "description", campaign.getDescription(),
			campaignInput.getDescription());
		_putIfChanged(
			jsonObject, "startDate", campaign.getStartDate(),
			campaignInput.getStartDate());
		_putIfChanged(
			jsonObject, "endDate", campaign.getEndDate(),
			campaignInput.getEndDate());
		_putIfChanged(
			jsonObject, "submissionLimit", campaign.getSubmissionLimit(),
			campaignInput.getSubmissionLimit());
		_putIfChanged(
			jsonObject, "seriesKey", campaign.getSeriesKey(),
			campaignInput.getSeriesKey());
		_putIfChanged(
			jsonObject, "instanceKey", campaign.getInstanceKey(),
			campaignInput.getInstanceKey());
		_putIfChanged(
			jsonObject, "retentionDays", campaign.getRetentionDays(),
			campaignInput.getRetentionDays());

		return jsonObject;
	}

	private void _putIfChanged(
		JSONObject jsonObject, String fieldName, Object oldValue,
		Object newValue) {
		if (!Objects.equals(oldValue, newValue)) {
			jsonObject.put(fieldName, true);
		}
	}

	private void _setFields(Campaign campaign, CampaignInput campaignInput)
		throws PortalException {
		if (Validator.isNotNull(campaignInput.getExternalReferenceCode())) {
			campaign.setExternalReferenceCode(
				campaignInput.getExternalReferenceCode());
		}

		campaign.setName(campaignInput.getName());
		campaign.setDescription(campaignInput.getDescription());
		campaign.setSlug(campaignInput.getSlug());
		campaign.setStartDate(campaignInput.getStartDate());
		campaign.setEndDate(campaignInput.getEndDate());
		campaign.setSubmissionLimit(campaignInput.getSubmissionLimit());
		campaign.setSeriesKey(campaignInput.getSeriesKey());
		campaign.setInstanceKey(campaignInput.getInstanceKey());
		campaign.setRetentionDays(campaignInput.getRetentionDays());

		try {
			campaign.setSettings(
				_jsonSettingsHelper.normalizeObject(
					campaignInput.getSettings()));
		}
		catch (JSONException jsonException) {
			throw new CampaignSettingsException(
				"campaign-settings-are-invalid", jsonException);
		}
	}

	private void _validate(
			long campaignId, long groupId, CampaignInput campaignInput)
		throws PortalException {
		CampaignValidator.validateName(campaignInput.getName());
		CampaignValidator.validateSlug(campaignInput.getSlug());
		CampaignValidator.validateDates(
			campaignInput.getStartDate(), campaignInput.getEndDate());

		if (campaignInput.getMode() == null) {
			throw new CampaignSettingsException("campaign-mode-is-required");
		}

		Campaign existingCampaign = campaignPersistence.fetchByG_S(
			groupId, campaignInput.getSlug());

		if ((existingCampaign != null) &&
			(existingCampaign.getCampaignId() != campaignId)) {
			throw new DuplicateSlugException("slug-is-already-in-use");
		}

		String externalReferenceCode =
			campaignInput.getExternalReferenceCode();

		if (Validator.isNotNull(externalReferenceCode)) {
			if (externalReferenceCode.length() > 75) {
				throw new CampaignSettingsException(
					"external-reference-code-is-too-long");
			}

			existingCampaign = campaignPersistence.fetchByERC_G(
				externalReferenceCode, groupId);

			if ((existingCampaign != null) &&
				(existingCampaign.getCampaignId() != campaignId)) {
				throw new DuplicateCampaignExternalReferenceCodeException(
					"external-reference-code-is-already-in-use");
			}
		}

		if ((campaignInput.getDescription() != null) &&
			(campaignInput.getDescription(
			).length() > 4000)) {
			throw new CampaignSettingsException(
				"campaign-description-is-too-long");
		}

		if ((campaignInput.getSubmissionLimit() < 0) ||
			(campaignInput.getRetentionDays() < 0)) {
			throw new CampaignSettingsException(
				"campaign-limits-must-not-be-negative");
		}

		if ((Validator.isNotNull(campaignInput.getSeriesKey()) &&
			 !CampaignValidator.isValidKey(campaignInput.getSeriesKey())) ||
			(Validator.isNotNull(campaignInput.getInstanceKey()) &&
			 !CampaignValidator.isValidKey(campaignInput.getInstanceKey()))) {
			throw new CampaignSlugException("series-or-instance-key-is-invalid");
		}
	}

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private IntegrationConfigLocalService _integrationConfigLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;

	@Reference
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
