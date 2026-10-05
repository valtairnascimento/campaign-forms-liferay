package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignDuplicationService;
import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.exception.CampaignSettingsException;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.input.IntegrationConfigInput;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignTransactionUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.IntegrationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignDuplicationService.class)
public class CampaignDuplicationServiceImpl
	implements CampaignDuplicationService {
	@Override
	public Campaign duplicate(
			long userId, long sourceCampaignId,
			DuplicationRequest duplicationRequest,
			ServiceContext serviceContext)
		throws PortalException {
		Campaign sourceCampaign = _campaignLocalService.getCampaign(
			sourceCampaignId);

		long targetGroupId = duplicationRequest.getTargetGroupId();

		if (targetGroupId <= 0) {
			targetGroupId = sourceCampaign.getGroupId();
		}

		Group targetGroup = _groupLocalService.getGroup(targetGroupId);

		if (targetGroup.getCompanyId() != sourceCampaign.getCompanyId()) {
			throw new CampaignSettingsException(
				"campaign-cannot-be-duplicated-to-another-instance");
		}

		CampaignInput campaignInput = _toCampaignInput(
			sourceCampaign, duplicationRequest);

		long finalTargetGroupId = targetGroupId;

		Campaign campaign = CampaignTransactionUtil.invoke(
			() -> {
				Campaign targetCampaign = _campaignLocalService.addCampaign(
					userId, finalTargetGroupId, campaignInput, serviceContext);

				if (duplicationRequest.isCopyFields()) {
					_copyForm(userId, sourceCampaign, targetCampaign);
				}

				_copyPage(
					userId, sourceCampaign, targetCampaign, duplicationRequest);

				if (duplicationRequest.isCopyNotifications()) {
					_copyNotifications(
						userId, sourceCampaign, targetCampaign,
						duplicationRequest);
				}

				if (duplicationRequest.isCopyIntegrations()) {
					_copyIntegrations(
						userId, sourceCampaign, targetCampaign,
						duplicationRequest);
				}

				targetCampaign.setSourceCampaignId(sourceCampaignId);

				targetCampaign = _campaignLocalService.updateCampaign(
					targetCampaign);

				_auditEntryLocalService.addAuditEntry(
					targetCampaign.getCompanyId(), targetCampaign.getGroupId(),
					userId, targetCampaign.getCampaignId(),
					AuditAction.CAMPAIGN_DUPLICATED, Campaign.class.getName(),
					targetCampaign.getCampaignId(),
					_getAuditDetails(sourceCampaignId, duplicationRequest));

				return targetCampaign;
			});

		if (_log.isInfoEnabled()) {
			_log.info(
				"Campaign " + sourceCampaignId + " duplicated as campaign " +
					campaign.getCampaignId());
		}

		return campaign;
	}

	private void _copyForm(
			long userId, Campaign sourceCampaign, Campaign targetCampaign)
		throws PortalException {
		Form sourceForm = _formLocalService.getCampaignForm(
			sourceCampaign.getCampaignId());

		long sourceFormVersionId = sourceForm.getDraftVersionId();

		if (sourceFormVersionId <= 0) {
			sourceFormVersionId = sourceForm.getPublishedVersionId();
		}

		if (sourceFormVersionId <= 0) {
			return;
		}

		Form targetForm = _formLocalService.getCampaignForm(
			targetCampaign.getCampaignId());

		_formLocalService.replaceDraftFormVersion(
			userId, targetForm.getFormId(), sourceFormVersionId);
	}

	private void _copyIntegrations(
			long userId, Campaign sourceCampaign, Campaign targetCampaign,
			DuplicationRequest duplicationRequest)
		throws PortalException {
		for (IntegrationConfig sourceIntegrationConfig :
				_integrationConfigLocalService.getCampaignIntegrationConfigs(
					sourceCampaign.getCampaignId())) {
			boolean hasSecrets = !CampaignValidator.isBlank(
				sourceIntegrationConfig.getEncryptedSecrets());

			boolean copySecrets =
				hasSecrets && duplicationRequest.isCopyIntegrationSecrets();

			IntegrationConfig integrationConfig =
				_integrationConfigLocalService.addIntegrationConfig(
					userId, targetCampaign.getCampaignId(),
					new IntegrationConfigInput(
					).setProviderKey(
						sourceIntegrationConfig.getProviderKey()
					).setName(
						sourceIntegrationConfig.getName()
					).setEnabled(
						sourceIntegrationConfig.isEnabled() &&
						(!hasSecrets || copySecrets)
					).setEventTypes(
						_toEventTypes(sourceIntegrationConfig.getEventTypes())
					).setSettings(
						sourceIntegrationConfig.getSettings()
					));

			if (copySecrets) {
				integrationConfig.setEncryptedSecrets(
					sourceIntegrationConfig.getEncryptedSecrets());

				_integrationConfigLocalService.updateIntegrationConfig(
					integrationConfig);
			}
		}
	}

	private void _copyNotifications(
			long userId, Campaign sourceCampaign, Campaign targetCampaign,
			DuplicationRequest duplicationRequest)
		throws PortalException {
		_notificationConfigLocalService.deleteCampaignNotificationConfigs(
			targetCampaign.getCampaignId());

		for (NotificationConfig sourceNotificationConfig :
				_notificationConfigLocalService.getCampaignNotificationConfigs(
					sourceCampaign.getCampaignId())) {
			String recipients = sourceNotificationConfig.getRecipients();
			String subjectTemplate =
				sourceNotificationConfig.getSubjectTemplate();

			if (!recipients.contains("${submission.")) {
				if (duplicationRequest.getRecipients() != null) {
					recipients = duplicationRequest.getRecipients();
				}

				if (duplicationRequest.getSubjectTemplate() != null) {
					subjectTemplate = duplicationRequest.getSubjectTemplate();
				}
			}

			_notificationConfigLocalService.addNotificationConfig(
				userId, targetCampaign.getCampaignId(),
				new NotificationConfigInput(
				).setName(
					sourceNotificationConfig.getName()
				).setEventType(
					EventType.parse(sourceNotificationConfig.getEventType())
				).setChannel(
					NotificationChannelType.parse(
						sourceNotificationConfig.getChannel())
				).setEnabled(
					sourceNotificationConfig.isEnabled()
				).setRecipients(
					recipients
				).setCc(
					sourceNotificationConfig.getCc()
				).setBcc(
					sourceNotificationConfig.getBcc()
				).setFromName(
					sourceNotificationConfig.getFromName()
				).setReplyTo(
					sourceNotificationConfig.getReplyTo()
				).setSubjectTemplate(
					subjectTemplate
				).setBodyTemplate(
					sourceNotificationConfig.getBodyTemplate()
				).setSettings(
					sourceNotificationConfig.getSettings()
				));
		}
	}

	private void _copyPage(
			long userId, Campaign sourceCampaign, Campaign targetCampaign,
			DuplicationRequest duplicationRequest)
		throws PortalException {
		PageConfiguration sourcePageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				sourceCampaign.getCampaignId(), PageStage.DRAFT);

		PageConfiguration targetPageConfiguration;

		if (duplicationRequest.isCopyPage()) {
			targetPageConfiguration =
				_pageConfigurationLocalService.copyPageConfiguration(
					userId, sourcePageConfiguration.getPageConfigurationId(),
					targetCampaign.getCampaignId(), PageStage.DRAFT);

			if (!duplicationRequest.isCopySeo()) {
				_clearSeo(targetPageConfiguration);
			}
		}
		else {
			targetPageConfiguration =
				_pageConfigurationLocalService.getPageConfiguration(
					targetCampaign.getCampaignId(), PageStage.DRAFT);

			if (duplicationRequest.isCopySeo()) {
				_copySeo(sourcePageConfiguration, targetPageConfiguration);
			}
		}

		if (sourceCampaign.getGroupId() != targetCampaign.getGroupId()) {
			targetPageConfiguration.setOgImageFileEntryId(0);
		}

		_pageConfigurationLocalService.updatePageConfiguration(
			targetPageConfiguration);
	}

	private void _clearSeo(PageConfiguration pageConfiguration) {
		pageConfiguration.setSeoTitle(null);
		pageConfiguration.setSeoDescription(null);
		pageConfiguration.setCanonicalUrl(null);
		pageConfiguration.setNoIndex(false);
		pageConfiguration.setOgTitle(null);
		pageConfiguration.setOgDescription(null);
		pageConfiguration.setOgImageFileEntryId(0);
	}

	private void _copySeo(
		PageConfiguration sourcePageConfiguration,
		PageConfiguration targetPageConfiguration) {
		targetPageConfiguration.setSeoTitle(
			sourcePageConfiguration.getSeoTitle());
		targetPageConfiguration.setSeoDescription(
			sourcePageConfiguration.getSeoDescription());
		targetPageConfiguration.setCanonicalUrl(
			sourcePageConfiguration.getCanonicalUrl());
		targetPageConfiguration.setNoIndex(sourcePageConfiguration.isNoIndex());
		targetPageConfiguration.setOgTitle(
			sourcePageConfiguration.getOgTitle());
		targetPageConfiguration.setOgDescription(
			sourcePageConfiguration.getOgDescription());
		targetPageConfiguration.setOgImageFileEntryId(
			sourcePageConfiguration.getOgImageFileEntryId());
	}

	private JSONObject _getAuditDetails(
		long sourceCampaignId, DuplicationRequest duplicationRequest) {
		return _jsonFactory.createJSONObject(
		).put(
			"copyFields", duplicationRequest.isCopyFields()
		).put(
			"copyIntegrations", duplicationRequest.isCopyIntegrations()
		).put(
			"copyIntegrationSecrets",
			duplicationRequest.isCopyIntegrationSecrets()
		).put(
			"copyNotifications", duplicationRequest.isCopyNotifications()
		).put(
			"copyPage", duplicationRequest.isCopyPage()
		).put(
			"copySeo", duplicationRequest.isCopySeo()
		).put(
			"copySettings", duplicationRequest.isCopySettings()
		).put(
			"sourceCampaignId", sourceCampaignId
		);
	}

	private String _getSettings(
			Campaign sourceCampaign, DuplicationRequest duplicationRequest)
		throws CampaignSettingsException {
		JSONObject settingsJSONObject = _jsonFactory.createJSONObject();

		if (duplicationRequest.isCopySettings()) {
			try {
				settingsJSONObject = _jsonFactory.createJSONObject(
					sourceCampaign.getSettings());
			}
			catch (JSONException jsonException) {
				throw new CampaignSettingsException(
					"campaign-settings-are-invalid", jsonException);
			}
		}

		Map<String, String> attributes = duplicationRequest.getAttributes();

		if (!attributes.isEmpty()) {
			JSONObject attributesJSONObject =
				settingsJSONObject.getJSONObject("attributes");

			if (attributesJSONObject == null) {
				attributesJSONObject = _jsonFactory.createJSONObject();

				settingsJSONObject.put("attributes", attributesJSONObject);
			}

			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				attributesJSONObject.put(entry.getKey(), entry.getValue());
			}
		}

		return settingsJSONObject.toString();
	}

	private CampaignInput _toCampaignInput(
			Campaign sourceCampaign, DuplicationRequest duplicationRequest)
		throws CampaignSettingsException {
		String name = duplicationRequest.getName();

		if (CampaignValidator.isBlank(name)) {
			name = sourceCampaign.getName();
		}

		CampaignInput campaignInput = new CampaignInput(
		).setMode(
			CampaignMode.parse(sourceCampaign.getMode())
		).setName(
			name
		).setSlug(
			duplicationRequest.getSlug()
		).setSeriesKey(
			sourceCampaign.getSeriesKey()
		).setInstanceKey(
			duplicationRequest.getInstanceKey()
		).setStartDate(
			duplicationRequest.getStartDate()
		).setEndDate(
			duplicationRequest.getEndDate()
		).setDefaultLanguageId(
			sourceCampaign.getDefaultLanguageId()
		).setSettings(
			_getSettings(sourceCampaign, duplicationRequest)
		);

		if (duplicationRequest.isCopySettings()) {
			campaignInput.setDescription(
				sourceCampaign.getDescription()
			).setSubmissionLimit(
				sourceCampaign.getSubmissionLimit()
			).setRetentionDays(
				sourceCampaign.getRetentionDays()
			);
		}

		return campaignInput;
	}

	private List<EventType> _toEventTypes(String eventTypeNames) {
		List<EventType> eventTypes = new ArrayList<>();

		for (String eventTypeName : StringUtil.split(eventTypeNames)) {
			try {
				eventTypes.add(EventType.parse(eventTypeName));
			}
			catch (IllegalArgumentException illegalArgumentException) {
				if (_log.isDebugEnabled()) {
					_log.debug(illegalArgumentException);
				}
			}
		}

		return eventTypes;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CampaignDuplicationServiceImpl.class);

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private IntegrationConfigLocalService _integrationConfigLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
