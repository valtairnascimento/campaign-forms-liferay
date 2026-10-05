package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignNameException extends PortalException {
	public CampaignNameException() {
	}

	public CampaignNameException(String msg) {
		super(msg);
	}

	public CampaignNameException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignNameException(Throwable throwable) {
		super(throwable);
	}

}
