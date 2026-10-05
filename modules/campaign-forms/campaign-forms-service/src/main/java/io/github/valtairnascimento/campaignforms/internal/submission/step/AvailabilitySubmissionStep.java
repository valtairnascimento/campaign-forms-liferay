package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.exception.CampaignInactiveException;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.exception.SubmissionLimitReachedException;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.CampaignQuotaLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "service.ranking:Integer=1000", service = SubmissionStep.class
)
public class AvailabilitySubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext)
		throws PortalException {
		Campaign campaign = submissionContext.getCampaign();

		if (CampaignStatus.ARCHIVED.matches(campaign.getStatus())) {
			throw new CampaignInactiveException("campaign-is-closed");
		}

		if (!CampaignStatus.PUBLISHED.matches(campaign.getStatus())) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		Date now = submissionContext.getNow();

		if ((campaign.getStartDate() != null) &&
			now.before(campaign.getStartDate())) {
			throw new CampaignInactiveException("campaign-has-not-started");
		}

		if ((campaign.getEndDate() != null) &&
			!now.before(campaign.getEndDate())) {
			throw new CampaignInactiveException("campaign-has-ended");
		}

		int submissionLimit = campaign.getSubmissionLimit();

		if ((submissionLimit > 0) &&
			(_campaignQuotaLocalService.getUsedSlots(campaign.getCampaignId()) >=
				submissionLimit)) {
			throw new SubmissionLimitReachedException(
				"submission-limit-reached");
		}
	}

	@Reference
	private CampaignQuotaLocalService _campaignQuotaLocalService;
}
