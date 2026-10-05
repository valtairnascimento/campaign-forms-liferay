package io.github.valtairnascimento.campaignforms.submission;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubmissionCommand {
	public String getCaptchaToken() {
		return _captchaToken;
	}

	public String getConsentVersion() {
		return _consentVersion;
	}

	public String getFormToken() {
		return _formToken;
	}

	public String getHoneypotValue() {
		return _honeypotValue;
	}

	public String getIdempotencyKey() {
		return _idempotencyKey;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getReferrer() {
		return _referrer;
	}

	public String getSourceUrl() {
		return _sourceUrl;
	}

	public String getUserAgent() {
		return _userAgent;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUtmCampaign() {
		return _utmCampaign;
	}

	public String getUtmContent() {
		return _utmContent;
	}

	public String getUtmMedium() {
		return _utmMedium;
	}

	public String getUtmSource() {
		return _utmSource;
	}

	public String getUtmTerm() {
		return _utmTerm;
	}

	public Map<String, List<String>> getValues() {
		return Collections.unmodifiableMap(_values);
	}

	public boolean isConsentAccepted() {
		return _consentAccepted;
	}

	public SubmissionCommand putValue(String fieldKey, String value) {
		_values.put(fieldKey, Collections.singletonList(value));

		return this;
	}

	public SubmissionCommand putValues(String fieldKey, List<String> values) {
		_values.put(fieldKey, values);

		return this;
	}

	public SubmissionCommand setCaptchaToken(String captchaToken) {
		_captchaToken = captchaToken;

		return this;
	}

	public SubmissionCommand setConsent(
		boolean consentAccepted, String consentVersion) {
		_consentAccepted = consentAccepted;
		_consentVersion = consentVersion;

		return this;
	}

	public SubmissionCommand setFormToken(String formToken) {
		_formToken = formToken;

		return this;
	}

	public SubmissionCommand setHoneypotValue(String honeypotValue) {
		_honeypotValue = honeypotValue;

		return this;
	}

	public SubmissionCommand setIdempotencyKey(String idempotencyKey) {
		_idempotencyKey = idempotencyKey;

		return this;
	}

	public SubmissionCommand setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;

		return this;
	}

	public SubmissionCommand setLanguageId(String languageId) {
		_languageId = languageId;

		return this;
	}

	public SubmissionCommand setReferrer(String referrer) {
		_referrer = referrer;

		return this;
	}

	public SubmissionCommand setSourceUrl(String sourceUrl) {
		_sourceUrl = sourceUrl;

		return this;
	}

	public SubmissionCommand setUserAgent(String userAgent) {
		_userAgent = userAgent;

		return this;
	}

	public SubmissionCommand setUserId(long userId) {
		_userId = userId;

		return this;
	}

	public SubmissionCommand setUtm(
		String utmSource, String utmMedium, String utmCampaign,
		String utmContent, String utmTerm) {
		_utmSource = utmSource;
		_utmMedium = utmMedium;
		_utmCampaign = utmCampaign;
		_utmContent = utmContent;
		_utmTerm = utmTerm;

		return this;
	}

	private String _captchaToken;
	private boolean _consentAccepted;
	private String _consentVersion;
	private String _formToken;
	private String _honeypotValue;
	private String _idempotencyKey;
	private String _ipAddress;
	private String _languageId;
	private String _referrer;
	private String _sourceUrl;
	private String _userAgent;
	private long _userId;
	private String _utmCampaign;
	private String _utmContent;
	private String _utmMedium;
	private String _utmSource;
	private String _utmTerm;
	private final Map<String, List<String>> _values = new LinkedHashMap<>();
}
