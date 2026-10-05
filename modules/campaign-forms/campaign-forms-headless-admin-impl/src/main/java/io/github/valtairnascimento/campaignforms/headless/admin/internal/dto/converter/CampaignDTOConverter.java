package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Campaign;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignDTOConverter.class)
public class CampaignDTOConverter {
	public Campaign toDTO(io.github.valtairnascimento.campaignforms.model.Campaign campaign) {
		Map<Long, Long> submissionsCounts =
			_submissionLocalService.getSubmissionsCounts(
				new long[] {campaign.getCampaignId()});

		return _toDTO(
			campaign, submissionsCounts.get(campaign.getCampaignId()));
	}

	public List<Campaign> toDTOs(
		List<io.github.valtairnascimento.campaignforms.model.Campaign> campaigns) {
		if (campaigns.isEmpty()) {
			return Collections.emptyList();
		}

		long[] campaignIds = new long[campaigns.size()];

		for (int i = 0; i < campaigns.size(); i++) {
			io.github.valtairnascimento.campaignforms.model.Campaign campaign = campaigns.get(
				i);

			campaignIds[i] = campaign.getCampaignId();
		}

		Map<Long, Long> submissionsCounts =
			_submissionLocalService.getSubmissionsCounts(campaignIds);

		List<Campaign> dtos = new ArrayList<>(campaigns.size());

		for (io.github.valtairnascimento.campaignforms.model.Campaign campaign : campaigns) {
			dtos.add(
				_toDTO(
					campaign,
					submissionsCounts.get(campaign.getCampaignId())));
		}

		return dtos;
	}

	public CampaignInput toCampaignInput(Campaign campaign, CampaignMode mode) {
		CampaignMode campaignMode = mode;

		if ((campaignMode == null) && (campaign.getMode() != null)) {
			campaignMode = CampaignMode.parse(
				campaign.getMode(
				).getValue());
		}

		return new CampaignInput(
		).setDefaultLanguageId(
			campaign.getDefaultLanguageId()
		).setDescription(
			campaign.getDescription()
		).setEndDate(
			campaign.getEndDate()
		).setExternalReferenceCode(
			campaign.getExternalReferenceCode()
		).setInstanceKey(
			campaign.getInstanceKey()
		).setMode(
			campaignMode
		).setName(
			campaign.getName()
		).setRetentionDays(
			GetterUtil.getInteger(campaign.getRetentionDays())
		).setSeriesKey(
			campaign.getSeriesKey()
		).setSettings(
			_jsonMapHelper.toJSON(campaign.getSettings())
		).setSlug(
			campaign.getSlug()
		).setStartDate(
			campaign.getStartDate()
		).setSubmissionLimit(
			GetterUtil.getInteger(campaign.getSubmissionLimit())
		);
	}

	public DuplicationRequest toDuplicationRequest(
		io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DuplicationRequest
			duplicationRequest) {
		DuplicationRequest domainDuplicationRequest = new DuplicationRequest(
		).setCopyFields(
			GetterUtil.getBoolean(duplicationRequest.getCopyFields(), true)
		).setCopyIntegrations(
			GetterUtil.getBoolean(duplicationRequest.getCopyIntegrations())
		).setCopyIntegrationSecrets(
			GetterUtil.getBoolean(
				duplicationRequest.getCopyIntegrationSecrets())
		).setCopyNotifications(
			GetterUtil.getBoolean(
				duplicationRequest.getCopyNotifications(), true)
		).setCopyPage(
			GetterUtil.getBoolean(duplicationRequest.getCopyPage(), true)
		).setCopySeo(
			GetterUtil.getBoolean(duplicationRequest.getCopySeo(), true)
		).setCopySettings(
			GetterUtil.getBoolean(duplicationRequest.getCopySettings(), true)
		).setDates(
			duplicationRequest.getStartDate(), duplicationRequest.getEndDate()
		).setInstanceKey(
			duplicationRequest.getInstanceKey()
		).setName(
			duplicationRequest.getName()
		).setNotificationOverrides(
			duplicationRequest.getRecipients(),
			duplicationRequest.getSubjectTemplate()
		).setSlug(
			duplicationRequest.getSlug()
		).setTargetGroupId(
			GetterUtil.getLong(duplicationRequest.getTargetSiteId())
		);

		Map<String, String> attributes = duplicationRequest.getAttributes();

		if (attributes != null) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				domainDuplicationRequest.putAttribute(
					entry.getKey(), entry.getValue());
			}
		}

		return domainDuplicationRequest;
	}

	private boolean _hasUnpublishedChanges(
		io.github.valtairnascimento.campaignforms.model.Campaign campaign) {
		if (!CampaignStatus.PUBLISHED.matches(campaign.getStatus()) &&
			!CampaignStatus.ARCHIVED.matches(campaign.getStatus())) {
			return false;
		}

		Form form = _formLocalService.fetchCampaignForm(
			campaign.getCampaignId());

		if ((form != null) && (form.getDraftVersionId() > 0)) {
			return true;
		}

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.fetchPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		Date publishedDate = campaign.getPublishedDate();

		return (pageConfiguration != null) && (publishedDate != null) &&
			   pageConfiguration.getModifiedDate(
			   ).after(
				   publishedDate
			   );
	}

	private Map<String, Object> _getPermissions(
		io.github.valtairnascimento.campaignforms.model.Campaign campaign) {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		Map<String, Object> permissions = new LinkedHashMap<>();

		if (permissionChecker == null) {
			return permissions;
		}

		for (String actionId : _ACTION_IDS) {
			try {
				permissions.put(
					actionId,
					_campaignModelResourcePermission.contains(
						permissionChecker, campaign, actionId));
			}
			catch (PortalException portalException) {
				throw new IllegalStateException(portalException);
			}
		}

		return permissions;
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	private Campaign _toDTO(
		io.github.valtairnascimento.campaignforms.model.Campaign campaign,
		Long submissionsCount) {
		Campaign dto = new Campaign();

		dto.setDateCreated(campaign.getCreateDate());
		dto.setDateModified(campaign.getModifiedDate());
		dto.setDatePublished(campaign.getPublishedDate());
		dto.setDefaultLanguageId(campaign.getDefaultLanguageId());
		dto.setDescription(_nullIfEmpty(campaign.getDescription()));
		dto.setEndDate(campaign.getEndDate());
		dto.setExternalReferenceCode(campaign.getExternalReferenceCode());
		dto.setHasUnpublishedChanges(_hasUnpublishedChanges(campaign));
		dto.setId(campaign.getCampaignId());
		dto.setInstanceKey(_nullIfEmpty(campaign.getInstanceKey()));
		dto.setMode(Campaign.Mode.create(campaign.getMode()));
		dto.setName(campaign.getName());
		dto.setPermissions(_getPermissions(campaign));
		dto.setRetentionDays(campaign.getRetentionDays());
		dto.setSeriesKey(_nullIfEmpty(campaign.getSeriesKey()));
		dto.setSettings(_jsonMapHelper.toMap(campaign.getSettings()));
		dto.setSiteId(campaign.getGroupId());
		dto.setSlug(campaign.getSlug());

		if (campaign.getSourceCampaignId() > 0) {
			dto.setSourceCampaignId(campaign.getSourceCampaignId());
		}

		dto.setStartDate(campaign.getStartDate());
		dto.setStatus(Campaign.Status.create(campaign.getStatus()));
		dto.setSubmissionLimit(campaign.getSubmissionLimit());
		dto.setSubmissionsCount(GetterUtil.getLong(submissionsCount));

		return dto;
	}

	private static final String[] _ACTION_IDS = {
		CampaignActionKeys.DELETE, CampaignActionKeys.DELETE_SUBMISSIONS,
		CampaignActionKeys.EXPORT_SUBMISSIONS,
		CampaignActionKeys.MANAGE_INTEGRATIONS,
		CampaignActionKeys.MANAGE_NOTIFICATIONS, CampaignActionKeys.PUBLISH,
		CampaignActionKeys.UPDATE, CampaignActionKeys.VIEW_SUBMISSIONS
	};

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<io.github.valtairnascimento.campaignforms.model.Campaign>
		_campaignModelResourcePermission;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private JSONMapHelper _jsonMapHelper;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;

	@Reference
	private SubmissionLocalService _submissionLocalService;
}
