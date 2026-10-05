package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum CampaignStatus {
	DRAFT,
	PUBLISHED,
	ARCHIVED;

	public static CampaignStatus parse(String value) {
		for (CampaignStatus item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid CampaignStatus: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
