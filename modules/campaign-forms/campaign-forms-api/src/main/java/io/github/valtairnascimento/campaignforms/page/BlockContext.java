package io.github.valtairnascimento.campaignforms.page;

import java.util.Locale;

public class BlockContext {
	public BlockContext(
		long companyId, long groupId, Locale locale, String campaignName) {
		_companyId = companyId;
		_groupId = groupId;
		_locale = locale;
		_campaignName = campaignName;
	}

	public String getCampaignName() {
		return _campaignName;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public Locale getLocale() {
		return _locale;
	}

	private final String _campaignName;
	private final long _companyId;
	private final long _groupId;
	private final Locale _locale;
}
