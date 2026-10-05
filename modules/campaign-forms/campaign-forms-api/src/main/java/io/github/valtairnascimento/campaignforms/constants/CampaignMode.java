package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum CampaignMode {
	QUICK,
	CUSTOM_PAGE;

	public static CampaignMode parse(String value) {
		for (CampaignMode item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid CampaignMode: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
