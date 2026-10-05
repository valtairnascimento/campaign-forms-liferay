package io.github.valtairnascimento.campaignforms.internal.page.type;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.page.BaseBlockType;
import io.github.valtairnascimento.campaignforms.internal.page.BlockImageValidator;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockType.PROPERTY_KEY + "=" + HeroBlockType.KEY,
	service = BlockType.class
)
public class HeroBlockType extends BaseBlockType {
	public static final String KEY = "HERO";

	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		return _jsonFactory.createJSONObject(
		).put(
			"align", "left"
		).put(
			"buttonLabel",
			CampaignLanguageUtil.get(
				blockContext.getLocale(), "block-default-button-label")
		).put(
			"buttonUrl", "#form"
		).put(
			"style", "brand"
		).put(
			"title", blockContext.getCampaignName()
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
		readText(configuration, sanitized, "subtitle", 500, false);

		_blockImageValidator.readImage(
			configuration, sanitized, "imageFileEntryId",
			blockContext.getGroupId(), false);

		String buttonLabel = readText(
			configuration, sanitized, "buttonLabel", 60, false);

		readURL(configuration, sanitized, "buttonUrl", buttonLabel != null);

		readOption(configuration, sanitized, "style", STYLES, "brand");
		readOption(configuration, sanitized, "align", _ALIGNS, "left");

		return sanitized;
	}

	private static final List<String> _ALIGNS = List.of("left", "center");

	@Reference
	private BlockImageValidator _blockImageValidator;

	@Reference
	private JSONFactory _jsonFactory;
}
