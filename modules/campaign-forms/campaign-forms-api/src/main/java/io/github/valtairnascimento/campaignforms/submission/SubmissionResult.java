package io.github.valtairnascimento.campaignforms.submission;

public class SubmissionResult {
	public SubmissionResult(
		Outcome outcome, long submissionId, String successTitle,
		String successMessage, String redirectUrl) {
		_outcome = outcome;
		_submissionId = submissionId;
		_successTitle = successTitle;
		_successMessage = successMessage;
		_redirectUrl = redirectUrl;
	}

	public Outcome getOutcome() {
		return _outcome;
	}

	public String getRedirectUrl() {
		return _redirectUrl;
	}

	public long getSubmissionId() {
		return _submissionId;
	}

	public String getSuccessMessage() {
		return _successMessage;
	}

	public String getSuccessTitle() {
		return _successTitle;
	}

	public enum Outcome {
		CREATED,

		DISCARDED,

		DUPLICATE
	}

	private final Outcome _outcome;
	private final String _redirectUrl;
	private final long _submissionId;
	private final String _successMessage;
	private final String _successTitle;
}
