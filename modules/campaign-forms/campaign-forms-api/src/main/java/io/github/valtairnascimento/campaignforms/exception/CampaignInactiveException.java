package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignInactiveException extends PortalException {
	public CampaignInactiveException() {
	}

	public CampaignInactiveException(String msg) {
		super(msg);
	}

	public CampaignInactiveException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignInactiveException(Throwable throwable) {
		super(throwable);
	}

}
