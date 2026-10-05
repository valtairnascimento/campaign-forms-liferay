package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.DataFeed;

import com.liferay.portal.kernel.exception.PortalException;

public interface DataFeedAccessService {
	public DataFeed authenticate(String feedKey, String token)
		throws PortalException;

	public long[] getCampaignIds(DataFeed dataFeed) throws PortalException;
}
