package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.page.SimpleMarkup;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

public abstract class BaseBlockRenderer implements BlockRenderer {
	protected static String getMarkupHTML(
		Map<String, Object> configuration, String key) {
		return SimpleMarkup.toHtml(getString(configuration, key));
	}

	protected static String getString(
		Map<String, Object> configuration, String key) {
		Object value = configuration.get(key);

		if (value == null) {
			return "";
		}

		return GetterUtil.getString(String.valueOf(value));
	}

	protected static String getStyle(
		Map<String, Object> configuration, String key, String defaultValue,
		String... options) {
		String value = getString(configuration, key);

		for (String option : options) {
			if (option.equals(value)) {
				return value;
			}
		}

		return defaultValue;
	}

}
