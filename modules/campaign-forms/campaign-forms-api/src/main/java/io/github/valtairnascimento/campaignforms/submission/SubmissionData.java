package io.github.valtairnascimento.campaignforms.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SubmissionData {
	public SubmissionData addSubmittedField(SubmittedField submittedField) {
		_submittedFields.add(submittedField);

		return this;
	}

	public Date getConsentDate() {
		return _consentDate;
	}

	public String getConsentVersion() {
		return _consentVersion;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getIdempotencyKey() {
		return _idempotencyKey;
	}

	public String getIpHash() {
		return _ipHash;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getMetadata() {
		return _metadata;
	}

	public String getPayloadSnapshot() {
		return _payloadSnapshot;
	}

	public String getReferrer() {
		return _referrer;
	}

	public String getSourceUrl() {
		return _sourceUrl;
	}

	public List<SubmittedField> getSubmittedFields() {
		return Collections.unmodifiableList(_submittedFields);
	}

	public String getUserAgent() {
		return _userAgent;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUserName() {
		return _userName;
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

	public SubmissionData setConsent(String consentVersion, Date consentDate) {
		_consentVersion = consentVersion;
		_consentDate = consentDate;

		return this;
	}

	public SubmissionData setCreateDate(Date createDate) {
		_createDate = createDate;

		return this;
	}

	public SubmissionData setIdempotencyKey(String idempotencyKey) {
		_idempotencyKey = idempotencyKey;

		return this;
	}

	public SubmissionData setIpHash(String ipHash) {
		_ipHash = ipHash;

		return this;
	}

	public SubmissionData setLanguageId(String languageId) {
		_languageId = languageId;

		return this;
	}

	public SubmissionData setMetadata(String metadata) {
		_metadata = metadata;

		return this;
	}

	public SubmissionData setPayloadSnapshot(String payloadSnapshot) {
		_payloadSnapshot = payloadSnapshot;

		return this;
	}

	public SubmissionData setReferrer(String referrer) {
		_referrer = referrer;

		return this;
	}

	public SubmissionData setSourceUrl(String sourceUrl) {
		_sourceUrl = sourceUrl;

		return this;
	}

	public SubmissionData setUser(long userId, String userName) {
		_userId = userId;
		_userName = userName;

		return this;
	}

	public SubmissionData setUserAgent(String userAgent) {
		_userAgent = userAgent;

		return this;
	}

	public SubmissionData setUtm(
		String utmSource, String utmMedium, String utmCampaign,
		String utmContent, String utmTerm) {
		_utmSource = utmSource;
		_utmMedium = utmMedium;
		_utmCampaign = utmCampaign;
		_utmContent = utmContent;
		_utmTerm = utmTerm;

		return this;
	}

	private Date _consentDate;
	private String _consentVersion;
	private Date _createDate;
	private String _idempotencyKey;
	private String _ipHash;
	private String _languageId;
	private String _metadata;
	private String _payloadSnapshot;
	private String _referrer;
	private String _sourceUrl;
	private final List<SubmittedField> _submittedFields = new ArrayList<>();
	private String _userAgent;
	private long _userId;
	private String _userName;
	private String _utmCampaign;
	private String _utmContent;
	private String _utmMedium;
	private String _utmSource;
	private String _utmTerm;
}
