package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum FieldValueType {
	STRING,
	TEXT,
	NUMBER,
	DATE,
	DATETIME,
	BOOLEAN;

	public static FieldValueType parse(String value) {
		for (FieldValueType item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid FieldValueType: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
