package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;

import com.liferay.portal.kernel.exception.PortalException;

public interface FormSubmissionService {
	public SubmissionResult submit(
			long groupId, String slug, SubmissionCommand submissionCommand)
		throws PortalException;
}
