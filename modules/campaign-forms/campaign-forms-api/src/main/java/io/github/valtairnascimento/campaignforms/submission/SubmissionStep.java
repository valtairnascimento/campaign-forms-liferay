package io.github.valtairnascimento.campaignforms.submission;

import com.liferay.portal.kernel.exception.PortalException;

public interface SubmissionStep {
	public void process(SubmissionContext submissionContext)
		throws PortalException;
}
