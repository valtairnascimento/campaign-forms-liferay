package io.github.valtairnascimento.campaignforms.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class ReservedKeys {
	public static final Set<String> METADATA_KEYS = _toLowerCaseSet(
		"campaign", "campaignId", "consentDate", "consentVersion", "createdAt",
		"formId", "formVersion", "instance", "languageId", "modifiedAt",
		"referrer", "site", "sourceUrl", "status", "submissionId", "utmCampaign",
		"utmContent", "utmMedium", "utmSource", "utmTerm");

	public static final Set<String> SLUGS = _toLowerCaseSet(
		"admin", "api", "edit", "new", "preview");

	public static boolean isReservedFieldKey(String fieldKey) {
		if (fieldKey == null) {
			return false;
		}

		return METADATA_KEYS.contains(fieldKey.toLowerCase(Locale.ROOT));
	}

	public static boolean isReservedSlug(String slug) {
		if (slug == null) {
			return false;
		}

		return SLUGS.contains(slug.toLowerCase(Locale.ROOT));
	}

	private static Set<String> _toLowerCaseSet(String... values) {
		Set<String> set = new TreeSet<>();

		for (String value : values) {
			set.add(value.toLowerCase(Locale.ROOT));
		}

		return Collections.unmodifiableSet(set);
	}

	private ReservedKeys() {
	}

}
