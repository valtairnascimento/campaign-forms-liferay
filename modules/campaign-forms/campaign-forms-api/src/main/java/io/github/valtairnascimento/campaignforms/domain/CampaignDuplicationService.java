package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.Campaign;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface CampaignDuplicationService {
	public Campaign duplicate(
			long userId, long sourceCampaignId,
			DuplicationRequest duplicationRequest,
			ServiceContext serviceContext)
		throws PortalException;
}
