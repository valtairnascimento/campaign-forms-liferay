package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum FormVersionStatus {
	DRAFT,
	PUBLISHED,
	SUPERSEDED;

	public static FormVersionStatus parse(String value) {
		for (FormVersionStatus item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid FormVersionStatus: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
