package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum DeliveryTargetType {
	NOTIFICATION,
	INTEGRATION;

	public static DeliveryTargetType parse(String value) {
		for (DeliveryTargetType item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid DeliveryTargetType: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
