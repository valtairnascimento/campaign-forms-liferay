package io.github.valtairnascimento.campaignforms.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

public class DuplicateCampaignExternalReferenceCodeException extends DuplicateExternalReferenceCodeException {
	public DuplicateCampaignExternalReferenceCodeException() {
	}

	public DuplicateCampaignExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateCampaignExternalReferenceCodeException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DuplicateCampaignExternalReferenceCodeException(Throwable throwable) {
		super(throwable);
	}

}
