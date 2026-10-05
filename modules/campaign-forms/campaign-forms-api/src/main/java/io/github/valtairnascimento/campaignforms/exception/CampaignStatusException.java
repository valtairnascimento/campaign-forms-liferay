package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignStatusException extends PortalException {
	public CampaignStatusException() {
	}

	public CampaignStatusException(String msg) {
		super(msg);
	}

	public CampaignStatusException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignStatusException(Throwable throwable) {
		super(throwable);
	}

}
