package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.FormVersion;

import java.util.Date;

public interface FormTokenManager {
	public static final long MAX_AGE_MILLIS = 24L * 60 * 60 * 1000;

	public static final long MIN_FILL_TIME_MILLIS = 2000;

	public String createFormToken(FormVersion formVersion);

	public String createFormToken(long companyId, long formVersionId);

	public String createFormToken(FormVersion formVersion, Date issueDate);

	public String getHoneypotFieldName(FormVersion formVersion);

	public String getHoneypotFieldName(long companyId, long formVersionId);
}
