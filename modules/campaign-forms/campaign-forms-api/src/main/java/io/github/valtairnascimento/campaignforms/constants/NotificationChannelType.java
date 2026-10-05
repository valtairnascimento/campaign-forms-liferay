package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum NotificationChannelType {
	EMAIL;

	public static NotificationChannelType parse(String value) {
		for (NotificationChannelType item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid NotificationChannelType: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
