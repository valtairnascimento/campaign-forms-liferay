package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum PageStage {
	DRAFT,
	PUBLISHED;

	public static PageStage parse(String value) {
		for (PageStage item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid PageStage: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
