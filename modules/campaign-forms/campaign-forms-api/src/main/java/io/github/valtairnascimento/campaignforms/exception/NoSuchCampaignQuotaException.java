package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchCampaignQuotaException extends NoSuchModelException {
	public NoSuchCampaignQuotaException() {
	}

	public NoSuchCampaignQuotaException(String msg) {
		super(msg);
	}

	public NoSuchCampaignQuotaException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchCampaignQuotaException(Throwable throwable) {
		super(throwable);
	}

}
