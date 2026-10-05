package io.github.valtairnascimento.campaignforms.page;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Locale;

public interface BlockType {
	public static final String PROPERTY_KEY = "campaignforms.block.type";

	public JSONObject getDefaultConfiguration(BlockContext blockContext);

	public String getDescription(Locale locale);

	public String getKey();

	public String getLabel(Locale locale);

	public int getSchemaVersion();

	public boolean isSingleton();

	public default JSONObject migrate(
		JSONObject configuration, int fromSchemaVersion) {
		return configuration;
	}

	public JSONObject sanitize(
			JSONObject configuration, BlockContext blockContext)
		throws PageBlockConfigurationException;
}
