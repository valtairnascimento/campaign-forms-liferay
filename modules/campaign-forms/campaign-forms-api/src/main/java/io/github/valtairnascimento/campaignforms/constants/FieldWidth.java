package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum FieldWidth {
	FULL,
	HALF,
	THIRD;

	public static FieldWidth parse(String value) {
		for (FieldWidth item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid FieldWidth: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
