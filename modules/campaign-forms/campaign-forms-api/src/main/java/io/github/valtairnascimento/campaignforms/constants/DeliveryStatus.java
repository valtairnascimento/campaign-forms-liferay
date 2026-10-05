package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum DeliveryStatus {
	PENDING,
	PROCESSING,
	SUCCESS,
	FAILED,
	DEAD;

	public static DeliveryStatus parse(String value) {
		for (DeliveryStatus item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid DeliveryStatus: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
