package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.Campaign;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public interface CampaignPublicationService {
	public Campaign archive(long userId, long campaignId)
		throws PortalException;

	public List<PublicationIssue> getPublicationIssues(long campaignId)
		throws PortalException;

	public Campaign publish(long userId, long campaignId)
		throws PortalException;

	public Campaign reopen(long userId, long campaignId)
		throws PortalException;
}
