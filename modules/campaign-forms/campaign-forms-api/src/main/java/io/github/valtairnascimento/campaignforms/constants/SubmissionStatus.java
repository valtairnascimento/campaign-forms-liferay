package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum SubmissionStatus {
	RECEIVED,
	PARTIAL,
	ANONYMIZED;

	public static SubmissionStatus parse(String value) {
		for (SubmissionStatus item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid SubmissionStatus: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
