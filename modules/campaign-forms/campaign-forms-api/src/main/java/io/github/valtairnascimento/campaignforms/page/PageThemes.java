package io.github.valtairnascimento.campaignforms.page;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PageThemes {
	public static final String DEFAULT = "default";

	public static final List<String> KEYS = Collections.unmodifiableList(
		Arrays.asList(DEFAULT, "light", "dark", "brand", "accent", "neutral"));

	public static boolean isValid(String themeKey) {
		return (themeKey == null) || themeKey.isEmpty() ||
			   KEYS.contains(themeKey);
	}

}
