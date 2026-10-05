package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum EventType {
	SUBMISSION_CREATED,
	SUBMISSION_UPDATED,
	CAMPAIGN_LIMIT_REACHED;

	public static EventType parse(String value) {
		for (EventType item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid EventType: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
