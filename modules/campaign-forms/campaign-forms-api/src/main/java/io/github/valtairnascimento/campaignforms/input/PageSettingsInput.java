package io.github.valtairnascimento.campaignforms.input;

public class PageSettingsInput {
	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public String getOgDescription() {
		return _ogDescription;
	}

	public long getOgImageFileEntryId() {
		return _ogImageFileEntryId;
	}

	public String getOgTitle() {
		return _ogTitle;
	}

	public String getSeoDescription() {
		return _seoDescription;
	}

	public String getSeoTitle() {
		return _seoTitle;
	}

	public String getSettings() {
		return _settings;
	}

	public String getTemplateKey() {
		return _templateKey;
	}

	public String getThemeKey() {
		return _themeKey;
	}

	public boolean isNoIndex() {
		return _noIndex;
	}

	public PageSettingsInput setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;

		return this;
	}

	public PageSettingsInput setCssClass(String cssClass) {
		_cssClass = cssClass;

		return this;
	}

	public PageSettingsInput setNoIndex(boolean noIndex) {
		_noIndex = noIndex;

		return this;
	}

	public PageSettingsInput setOgDescription(String ogDescription) {
		_ogDescription = ogDescription;

		return this;
	}

	public PageSettingsInput setOgImageFileEntryId(long ogImageFileEntryId) {
		_ogImageFileEntryId = ogImageFileEntryId;

		return this;
	}

	public PageSettingsInput setOgTitle(String ogTitle) {
		_ogTitle = ogTitle;

		return this;
	}

	public PageSettingsInput setSeoDescription(String seoDescription) {
		_seoDescription = seoDescription;

		return this;
	}

	public PageSettingsInput setSeoTitle(String seoTitle) {
		_seoTitle = seoTitle;

		return this;
	}

	public PageSettingsInput setSettings(String settings) {
		_settings = settings;

		return this;
	}

	public PageSettingsInput setTemplateKey(String templateKey) {
		_templateKey = templateKey;

		return this;
	}

	public PageSettingsInput setThemeKey(String themeKey) {
		_themeKey = themeKey;

		return this;
	}

	private String _canonicalUrl;
	private String _cssClass;
	private boolean _noIndex;
	private String _ogDescription;
	private long _ogImageFileEntryId;
	private String _ogTitle;
	private String _seoDescription;
	private String _seoTitle;
	private String _settings;
	private String _templateKey;
	private String _themeKey;
}
