package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.Campaign;

import com.liferay.portal.kernel.exception.PortalException;

public interface CampaignModeService {
	public Campaign convertToCustomPage(long userId, long campaignId)
		throws PortalException;
}
