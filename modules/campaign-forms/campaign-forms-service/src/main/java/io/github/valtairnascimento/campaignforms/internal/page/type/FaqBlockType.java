package io.github.valtairnascimento.campaignforms.internal.page.type;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.page.BaseBlockType;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockType.PROPERTY_KEY + "=" + FaqBlockType.KEY,
	service = BlockType.class
)
public class FaqBlockType extends BaseBlockType {
	public static final String KEY = "FAQ";

	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		Locale locale = blockContext.getLocale();

		return _jsonFactory.createJSONObject(
		).put(
			"items",
			_jsonFactory.createJSONArray(
			).put(
				_jsonFactory.createJSONObject(
				).put(
					"answer",
					CampaignLanguageUtil.get(locale, "block-default-faq-answer")
				).put(
					"question",
					CampaignLanguageUtil.get(
						locale, "block-default-faq-question")
				)
			)
		).put(
			"title", CampaignLanguageUtil.get(locale, "block-default-faq-title")
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

		JSONArray itemsJSONArray = null;

		if (configuration != null) {
			itemsJSONArray = configuration.getJSONArray("items");
		}

		if ((itemsJSONArray == null) || (itemsJSONArray.length() == 0)) {
			throw new PageBlockConfigurationException(
				"block-items-are-required", "items");
		}

		if (itemsJSONArray.length() > _MAX_ITEMS) {
			throw new PageBlockConfigurationException(
				"block-items-are-too-many", "items");
		}

		JSONArray sanitizedItemsJSONArray = _jsonFactory.createJSONArray();

		for (int i = 0; i < itemsJSONArray.length(); i++) {
			JSONObject itemJSONObject = itemsJSONArray.getJSONObject(i);

			String field = "items[" + i + "]";

			if (itemJSONObject == null) {
				throw new PageBlockConfigurationException(
					"block-configuration-is-invalid", field);
			}

			JSONObject sanitizedItemJSONObject =
				_jsonFactory.createJSONObject();

			readText(
				itemJSONObject, sanitizedItemJSONObject, "question", 300, true,
				field + ".question");
			readMarkup(
				itemJSONObject, sanitizedItemJSONObject, "answer", 4000, true,
				field + ".answer");

			sanitizedItemsJSONArray.put(sanitizedItemJSONObject);
		}

		sanitized.put("items", sanitizedItemsJSONArray);

		return sanitized;
	}

	private static final int _MAX_ITEMS = 30;

	@Reference
	private JSONFactory _jsonFactory;
}
