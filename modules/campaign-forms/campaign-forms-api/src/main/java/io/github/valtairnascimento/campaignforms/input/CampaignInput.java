package io.github.valtairnascimento.campaignforms.input;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;

import java.util.Date;

public class CampaignInput {
	public String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public String getDescription() {
		return _description;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getInstanceKey() {
		return _instanceKey;
	}

	public CampaignMode getMode() {
		return _mode;
	}

	public String getName() {
		return _name;
	}

	public int getRetentionDays() {
		return _retentionDays;
	}

	public String getSeriesKey() {
		return _seriesKey;
	}

	public String getSettings() {
		return _settings;
	}

	public String getSlug() {
		return _slug;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public int getSubmissionLimit() {
		return _submissionLimit;
	}

	public CampaignInput setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;

		return this;
	}

	public CampaignInput setDescription(String description) {
		_description = description;

		return this;
	}

	public CampaignInput setEndDate(Date endDate) {
		_endDate = endDate;

		return this;
	}

	public CampaignInput setExternalReferenceCode(
		String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;

		return this;
	}

	public CampaignInput setInstanceKey(String instanceKey) {
		_instanceKey = instanceKey;

		return this;
	}

	public CampaignInput setMode(CampaignMode mode) {
		_mode = mode;

		return this;
	}

	public CampaignInput setName(String name) {
		_name = name;

		return this;
	}

	public CampaignInput setRetentionDays(int retentionDays) {
		_retentionDays = retentionDays;

		return this;
	}

	public CampaignInput setSeriesKey(String seriesKey) {
		_seriesKey = seriesKey;

		return this;
	}

	public CampaignInput setSettings(String settings) {
		_settings = settings;

		return this;
	}

	public CampaignInput setSlug(String slug) {
		_slug = slug;

		return this;
	}

	public CampaignInput setStartDate(Date startDate) {
		_startDate = startDate;

		return this;
	}

	public CampaignInput setSubmissionLimit(int submissionLimit) {
		_submissionLimit = submissionLimit;

		return this;
	}

	private String _defaultLanguageId;
	private String _description;
	private Date _endDate;
	private String _externalReferenceCode;
	private String _instanceKey;
	private CampaignMode _mode = CampaignMode.QUICK;
	private String _name;
	private int _retentionDays;
	private String _seriesKey;
	private String _settings;
	private String _slug;
	private Date _startDate;
	private int _submissionLimit;
}
