package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.exception.FormTokenException;
import io.github.valtairnascimento.campaignforms.internal.submission.security.SubmissionSecurityHelper;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "service.ranking:Integer=800", service = SubmissionStep.class
)
public class SpamProtectionSubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext)
		throws PortalException {
		SubmissionCommand submissionCommand = submissionContext.getCommand();

		if (!CampaignValidator.isBlank(submissionCommand.getHoneypotValue())) {
			_discard(submissionContext, "honeypot");

			return;
		}

		SubmissionSecurityHelper.FormTokenStatus formTokenStatus =
			_submissionSecurityHelper.verifyFormToken(
				submissionContext.getFormVersion(),
				submissionCommand.getFormToken(), submissionContext.getNow());

		if (formTokenStatus == SubmissionSecurityHelper.FormTokenStatus.VALID) {
			return;
		}

		if (formTokenStatus ==
				SubmissionSecurityHelper.FormTokenStatus.TOO_FAST) {
			_discard(submissionContext, "minimum fill time");

			return;
		}

		if (formTokenStatus ==
				SubmissionSecurityHelper.FormTokenStatus.EXPIRED) {
			throw new FormTokenException("form-token-has-expired");
		}

		throw new FormTokenException("form-token-is-invalid");
	}

	private void _discard(SubmissionContext submissionContext, String reason) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Submission discarded by " + reason + " for campaign " +
					submissionContext.getCampaign(
					).getCampaignId());
		}

		submissionContext.discard();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpamProtectionSubmissionStep.class);

	@Reference
	private SubmissionSecurityHelper _submissionSecurityHelper;
}
