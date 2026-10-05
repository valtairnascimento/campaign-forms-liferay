package io.github.valtairnascimento.campaignforms.exception;

import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampaignPublicationException extends PortalException {
	public CampaignPublicationException() {
	}

	public CampaignPublicationException(
		List<PublicationIssue> publicationIssues) {
		super("campaign-cannot-be-published");

		_publicationIssues.addAll(publicationIssues);
	}

	public CampaignPublicationException(String msg) {
		super(msg);
	}

	public CampaignPublicationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignPublicationException(Throwable throwable) {
		super(throwable);
	}

	public List<PublicationIssue> getPublicationIssues() {
		return Collections.unmodifiableList(_publicationIssues);
	}

	private final List<PublicationIssue> _publicationIssues =
		new ArrayList<>();
}
