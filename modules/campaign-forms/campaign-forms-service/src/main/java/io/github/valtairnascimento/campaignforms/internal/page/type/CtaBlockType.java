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
	property = BlockType.PROPERTY_KEY + "=" + CtaBlockType.KEY,
	service = BlockType.class
)
public class CtaBlockType extends BaseBlockType {
	public static final String KEY = "CTA";

	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		return _jsonFactory.createJSONObject(
		).put(
			"buttonLabel",
			CampaignLanguageUtil.get(
				blockContext.getLocale(), "block-default-button-label")
		).put(
			"buttonUrl", "#form"
		).put(
			"style", "brand"
		).put(
			"title",
			CampaignLanguageUtil.get(
				blockContext.getLocale(), "block-default-cta-title")
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

		readText(configuration, sanitized, "title", 150, true);
		readMarkup(configuration, sanitized, "text", 2000, false, "text");
		readText(configuration, sanitized, "buttonLabel", 60, true);
		readURL(configuration, sanitized, "buttonUrl", true);
		readOption(configuration, sanitized, "style", STYLES, "brand");

		return sanitized;
	}

	@Reference
	private JSONFactory _jsonFactory;
}
