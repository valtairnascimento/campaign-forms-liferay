package io.github.valtairnascimento.campaignforms.submission;

public interface CaptchaVerifier {
	public static final String PROPERTY_KEY = "campaignforms.captcha.provider";

	public boolean verify(long companyId, String token, String ipAddress);
}
