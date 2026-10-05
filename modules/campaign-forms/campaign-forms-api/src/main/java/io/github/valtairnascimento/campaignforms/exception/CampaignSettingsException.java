package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class CampaignSettingsException extends PortalException {
	public CampaignSettingsException() {
	}

	public CampaignSettingsException(String msg) {
		super(msg);
	}

	public CampaignSettingsException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CampaignSettingsException(Throwable throwable) {
		super(throwable);
	}

}
