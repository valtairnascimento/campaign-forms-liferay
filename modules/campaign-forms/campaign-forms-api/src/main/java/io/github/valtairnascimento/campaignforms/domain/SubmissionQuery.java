package io.github.valtairnascimento.campaignforms.domain;

import java.util.Date;

public class SubmissionQuery {
	public SubmissionQuery(long... campaignIds) {
		_campaignIds = campaignIds;
	}

	public long getAfterId() {
		return _afterId;
	}

	public long[] getCampaignIds() {
		return _campaignIds;
	}

	public int getLimit() {
		return _limit;
	}

	public Date getModifiedSince() {
		return _modifiedSince;
	}

	public char getSeparator() {
		return _separator;
	}

	public boolean isIncludeMetadata() {
		return _includeMetadata;
	}

	public boolean isUseLabels() {
		return _useLabels;
	}

	public SubmissionQuery setAfterId(long afterId) {
		_afterId = afterId;

		return this;
	}

	public SubmissionQuery setIncludeMetadata(boolean includeMetadata) {
		_includeMetadata = includeMetadata;

		return this;
	}

	public SubmissionQuery setLimit(int limit) {
		_limit = limit;

		return this;
	}

	public SubmissionQuery setModifiedSince(Date modifiedSince) {
		_modifiedSince = modifiedSince;

		return this;
	}

	public SubmissionQuery setSeparator(char separator) {
		_separator = separator;

		return this;
	}

	public SubmissionQuery setUseLabels(boolean useLabels) {
		_useLabels = useLabels;

		return this;
	}

	private long _afterId;
	private final long[] _campaignIds;
	private boolean _includeMetadata = true;
	private int _limit;
	private Date _modifiedSince;
	private char _separator = ';';
	private boolean _useLabels;
}
