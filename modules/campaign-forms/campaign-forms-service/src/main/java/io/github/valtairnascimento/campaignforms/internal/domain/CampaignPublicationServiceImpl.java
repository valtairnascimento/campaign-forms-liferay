package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.exception.CampaignPublicationException;
import io.github.valtairnascimento.campaignforms.exception.CampaignStatusException;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignTransactionUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignQuotaLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignPublicationService.class)
public class CampaignPublicationServiceImpl
	implements CampaignPublicationService {
	@Override
	public Campaign archive(long userId, long campaignId)
		throws PortalException {
		Campaign campaign = _campaignLocalService.getCampaign(campaignId);

		if (!CampaignStatus.PUBLISHED.matches(campaign.getStatus())) {
			throw new CampaignStatusException(
				"only-published-campaigns-can-be-archived");
		}

		campaign = _campaignLocalService.updateStatus(
			userId, campaignId, CampaignStatus.ARCHIVED);

		_logStatusChange(campaign);

		return campaign;
	}

	@Override
	public List<PublicationIssue> getPublicationIssues(long campaignId)
		throws PortalException {
		return _publicationValidator.validate(
			_campaignLocalService.getCampaign(campaignId));
	}

	@Override
	public Campaign publish(long userId, long campaignId)
		throws PortalException {
		Campaign campaign = _campaignLocalService.getCampaign(campaignId);

		if (CampaignStatus.ARCHIVED.matches(campaign.getStatus())) {
			throw new CampaignStatusException(
				"archived-campaign-must-be-reopened");
		}

		List<PublicationIssue> publicationIssues =
			_publicationValidator.validate(campaign);

		if (!publicationIssues.isEmpty()) {
			throw new CampaignPublicationException(publicationIssues);
		}

		campaign = CampaignTransactionUtil.invoke(
			() -> {
				Form form = _formLocalService.getCampaignForm(campaignId);

				_formLocalService.publishDraftFormVersion(
					userId, form.getFormId());

				PageConfiguration draftPageConfiguration =
					_pageConfigurationLocalService.getPageConfiguration(
						campaignId, PageStage.DRAFT);

				_pageConfigurationLocalService.copyPageConfiguration(
					userId, draftPageConfiguration.getPageConfigurationId(),
					campaignId, PageStage.PUBLISHED);

				Campaign publishedCampaign =
					_campaignLocalService.getCampaign(campaignId);

				if (publishedCampaign.getSubmissionLimit() > 0) {
					_campaignQuotaLocalService.addCampaignQuotaIfAbsent(
						publishedCampaign);
				}

				return _campaignLocalService.updateStatus(
					userId, campaignId, CampaignStatus.PUBLISHED);
			});

		_logStatusChange(campaign);

		return campaign;
	}

	@Override
	public Campaign reopen(long userId, long campaignId)
		throws PortalException {
		Campaign campaign = _campaignLocalService.getCampaign(campaignId);

		if (!CampaignStatus.ARCHIVED.matches(campaign.getStatus())) {
			throw new CampaignStatusException(
				"only-archived-campaigns-can-be-reopened");
		}

		campaign = _campaignLocalService.updateStatus(
			userId, campaignId, CampaignStatus.PUBLISHED);

		_logStatusChange(campaign);

		return campaign;
	}

	private void _logStatusChange(Campaign campaign) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Campaign " + campaign.getCampaignId() + " is now " +
					campaign.getStatus());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CampaignPublicationServiceImpl.class);

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference
	private CampaignQuotaLocalService _campaignQuotaLocalService;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;

	@Reference
	private PublicationValidator _publicationValidator;
}
