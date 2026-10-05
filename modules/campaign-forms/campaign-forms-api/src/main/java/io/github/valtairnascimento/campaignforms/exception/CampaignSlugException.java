package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignSlugException extends PortalException {
	public CampaignSlugException() {
	}

	public CampaignSlugException(String msg) {
		super(msg);
	}

	public CampaignSlugException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignSlugException(Throwable throwable) {
		super(throwable);
	}

}
