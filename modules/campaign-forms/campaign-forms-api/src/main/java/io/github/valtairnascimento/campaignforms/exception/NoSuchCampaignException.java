package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

public class NoSuchCampaignException extends NoSuchModelException {
	public NoSuchCampaignException() {
	}

	public NoSuchCampaignException(String msg) {
		super(msg);
	}

	public NoSuchCampaignException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchCampaignException(Throwable throwable) {
		super(throwable);
	}

}
