package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class UnauthorizedCampaignAccessException extends PortalException {
	public UnauthorizedCampaignAccessException() {
	}

	public UnauthorizedCampaignAccessException(String msg) {
		super(msg);
	}

	public UnauthorizedCampaignAccessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public UnauthorizedCampaignAccessException(Throwable throwable) {
		super(throwable);
	}

}
