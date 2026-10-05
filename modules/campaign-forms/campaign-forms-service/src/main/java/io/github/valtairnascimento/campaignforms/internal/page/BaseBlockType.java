package io.github.valtairnascimento.campaignforms.internal.page;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.SimpleMarkup;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Locale;

public abstract class BaseBlockType implements BlockType {
	@Override
	public String getDescription(Locale locale) {
		return CampaignLanguageUtil.get(
			locale, "block-type-" + _getLowerCaseKey() + "-description");
	}

	@Override
	public String getLabel(Locale locale) {
		return CampaignLanguageUtil.get(
			locale, "block-type-" + _getLowerCaseKey());
	}

	@Override
	public int getSchemaVersion() {
		return 1;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	protected static String getString(JSONObject configuration, String key) {
		if ((configuration == null) || !configuration.has(key) ||
			configuration.isNull(key)) {
			return null;
		}

		Object value = configuration.get(key);

		if ((value instanceof JSONObject) ||
			(value instanceof JSONArray)) {
			return null;
		}

		String string = String.valueOf(
			value
		).trim();

		return string.isEmpty() ? null : string;
	}

	protected static boolean readBoolean(
		JSONObject configuration, JSONObject sanitized, String key) {
		boolean value = (configuration != null) &&
			configuration.getBoolean(key, false);

		if (value) {
			sanitized.put(key, true);
		}

		return value;
	}

	protected static String readMarkup(
			JSONObject configuration, JSONObject sanitized, String key,
			int maxLength, boolean required, String field)
		throws PageBlockConfigurationException {
		String value = readText(
			configuration, sanitized, key, maxLength, required, field);

		if ((value != null) &&
			!SimpleMarkup.getInvalidLinks(
				value
			).isEmpty()) {
			throw new PageBlockConfigurationException(
				"block-link-is-invalid", field);
		}

		return value;
	}

	protected static String readOption(
			JSONObject configuration, JSONObject sanitized, String key,
			List<String> options, String defaultValue)
		throws PageBlockConfigurationException {
		String value = getString(configuration, key);

		if (value == null) {
			value = defaultValue;
		}
		else if (!options.contains(value)) {
			throw new PageBlockConfigurationException(
				"block-option-is-invalid", key);
		}

		sanitized.put(key, value);

		return value;
	}

	protected static String readText(
			JSONObject configuration, JSONObject sanitized, String key,
			int maxLength, boolean required)
		throws PageBlockConfigurationException {
		return readText(
			configuration, sanitized, key, maxLength, required, key);
	}

	protected static String readText(
			JSONObject configuration, JSONObject sanitized, String key,
			int maxLength, boolean required, String field)
		throws PageBlockConfigurationException {
		String value = getString(configuration, key);

		if (value == null) {
			if (required) {
				throw new PageBlockConfigurationException(
					"block-field-is-required", field);
			}

			return null;
		}

		if (value.length() > maxLength) {
			throw new PageBlockConfigurationException(
				"block-field-is-too-long", field);
		}

		sanitized.put(key, value);

		return value;
	}

	protected static String readURL(
			JSONObject configuration, JSONObject sanitized, String key,
			boolean required)
		throws PageBlockConfigurationException {
		String value = getString(configuration, key);

		if (value == null) {
			if (required) {
				throw new PageBlockConfigurationException(
					"block-field-is-required", key);
			}

			return null;
		}

		if (!SimpleMarkup.isAllowedURL(value)) {
			throw new PageBlockConfigurationException(
				"block-url-is-invalid", key);
		}

		sanitized.put(key, value);

		return value;
	}

	protected static final List<String> STYLES = List.of(
		"brand", "light", "dark");

	private String _getLowerCaseKey() {
		return StringUtil.toLowerCase(getKey());
	}

}
