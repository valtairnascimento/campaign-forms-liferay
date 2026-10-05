package io.github.valtairnascimento.campaignforms.constants;

import java.util.Objects;

public enum AuditAction {
	CAMPAIGN_ADDED,
	CAMPAIGN_UPDATED,
	CAMPAIGN_PUBLISHED,
	CAMPAIGN_ARCHIVED,
	CAMPAIGN_REOPENED,
	CAMPAIGN_DELETED,
	CAMPAIGN_DUPLICATED,
	CAMPAIGN_MODE_CONVERTED,
	DATA_FEED_ADDED,
	DATA_FEED_REVOKED,
	DATA_FEED_TOKEN_ROTATED,
	INTEGRATION_ADDED,
	INTEGRATION_UPDATED,
	INTEGRATION_DELETED,
	NOTIFICATION_ADDED,
	NOTIFICATION_UPDATED,
	NOTIFICATION_DELETED,
	SUBMISSION_ANONYMIZED,
	SUBMISSION_DELETED,
	SUBMISSIONS_EXPORTED;

	public static AuditAction parse(String value) {
		for (AuditAction item : values()) {
			if (Objects.equals(item.name(), value)) {
				return item;
			}
		}

		throw new IllegalArgumentException("Invalid AuditAction: " + value);
	}

	public boolean matches(String value) {
		return name().equals(value);
	}

}
