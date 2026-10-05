package io.github.valtairnascimento.campaignforms.internal.page.type;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.page.BaseBlockType;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockType.PROPERTY_KEY + "=" + TextBlockType.KEY,
	service = BlockType.class
)
public class TextBlockType extends BaseBlockType {
	public static final String KEY = "TEXT";

	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		return _jsonFactory.createJSONObject(
		).put(
			"body",
			CampaignLanguageUtil.get(
				blockContext.getLocale(), "block-default-text-body")
		).put(
			"title",
			CampaignLanguageUtil.get(
				blockContext.getLocale(), "block-default-text-title")
		);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public JSONObject sanitize(
			JSONObject configuration, BlockContext blockContext)
		throws PageBlockConfigurationException {
		JSONObject sanitized = _jsonFactory.createJSONObject();

		readText(configuration, sanitized, "title", 150, false);
		readMarkup(configuration, sanitized, "body", 10000, true, "body");

		return sanitized;
	}

	@Reference
	private JSONFactory _jsonFactory;
}
