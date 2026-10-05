package io.github.valtairnascimento.campaignforms.input;

public class FormVersionInput {
	public String getConsentText() {
		return _consentText;
	}

	public String getConsentURL() {
		return _consentURL;
	}

	public String getConsentVersion() {
		return _consentVersion;
	}

	public String getRedirectUrl() {
		return _redirectUrl;
	}

	public String getSettings() {
		return _settings;
	}

	public String getSubmitLabel() {
		return _submitLabel;
	}

	public String getSuccessMessage() {
		return _successMessage;
	}

	public String getSuccessTitle() {
		return _successTitle;
	}

	public boolean isCaptchaEnabled() {
		return _captchaEnabled;
	}

	public boolean isConsentRequired() {
		return _consentRequired;
	}

	public boolean isRequireAuthentication() {
		return _requireAuthentication;
	}

	public boolean isSaveIncompleteSubmission() {
		return _saveIncompleteSubmission;
	}

	public FormVersionInput setCaptchaEnabled(boolean captchaEnabled) {
		_captchaEnabled = captchaEnabled;

		return this;
	}

	public FormVersionInput setConsentRequired(boolean consentRequired) {
		_consentRequired = consentRequired;

		return this;
	}

	public FormVersionInput setConsentText(String consentText) {
		_consentText = consentText;

		return this;
	}

	public FormVersionInput setConsentURL(String consentURL) {
		_consentURL = consentURL;

		return this;
	}

	public FormVersionInput setConsentVersion(String consentVersion) {
		_consentVersion = consentVersion;

		return this;
	}

	public FormVersionInput setRedirectUrl(String redirectUrl) {
		_redirectUrl = redirectUrl;

		return this;
	}

	public FormVersionInput setRequireAuthentication(
		boolean requireAuthentication) {
		_requireAuthentication = requireAuthentication;

		return this;
	}

	public FormVersionInput setSaveIncompleteSubmission(
		boolean saveIncompleteSubmission) {
		_saveIncompleteSubmission = saveIncompleteSubmission;

		return this;
	}

	public FormVersionInput setSettings(String settings) {
		_settings = settings;

		return this;
	}

	public FormVersionInput setSubmitLabel(String submitLabel) {
		_submitLabel = submitLabel;

		return this;
	}

	public FormVersionInput setSuccessMessage(String successMessage) {
		_successMessage = successMessage;

		return this;
	}

	public FormVersionInput setSuccessTitle(String successTitle) {
		_successTitle = successTitle;

		return this;
	}

	private boolean _captchaEnabled;
	private boolean _consentRequired;
	private String _consentText;
	private String _consentURL;
	private String _consentVersion;
	private String _redirectUrl;
	private boolean _requireAuthentication;
	private boolean _saveIncompleteSubmission;
	private String _settings;
	private String _submitLabel;
	private String _successMessage;
	private String _successTitle;
}
