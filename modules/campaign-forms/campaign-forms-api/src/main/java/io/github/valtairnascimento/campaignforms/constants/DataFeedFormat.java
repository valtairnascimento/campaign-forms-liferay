package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum DataFeedFormat {
	CSV,
	JSON;

	public static DataFeedFormat parse(String value) {
		for (DataFeedFormat item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid DataFeedFormat: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
