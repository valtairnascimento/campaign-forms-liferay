package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignHasSubmissionsException extends PortalException {
	public CampaignHasSubmissionsException() {
	}

	public CampaignHasSubmissionsException(String msg) {
		super(msg);
	}

	public CampaignHasSubmissionsException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignHasSubmissionsException(Throwable throwable) {
		super(throwable);
	}

}
