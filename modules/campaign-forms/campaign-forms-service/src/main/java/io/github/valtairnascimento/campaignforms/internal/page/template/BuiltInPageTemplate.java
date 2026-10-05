package io.github.valtairnascimento.campaignforms.internal.page.template;

import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;
import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Reference;

public abstract class BuiltInPageTemplate implements PageTemplateDefinition {
	@Override
	public List<TemplateBlock> getBlocks(BlockContext blockContext) {
		List<TemplateBlock> templateBlocks = new ArrayList<>();

		for (String blockTypeKey : getBlockTypes()) {
			BlockType blockType = blockTypeRegistry.getBlockType(blockTypeKey);

			if (blockType == null) {
				continue;
			}

			JSONObject configuration = blockType.getDefaultConfiguration(
				blockContext);

			_applyTexts(
				blockContext.getLocale(), blockTypeKey, configuration);

			templateBlocks.add(new TemplateBlock(blockTypeKey, configuration));
		}

		return templateBlocks;
	}

	@Override
	public String getDescription(Locale locale) {
		return CampaignLanguageUtil.get(
			locale, "page-template-" + getKey() + "-description");
	}

	@Override
	public String getLabel(Locale locale) {
		return CampaignLanguageUtil.get(locale, "page-template-" + getKey());
	}

	protected abstract String[] getBlockTypes();

	@Reference
	protected BlockTypeRegistry blockTypeRegistry;

	private void _applyTexts(
		Locale locale, String blockTypeKey, JSONObject configuration) {
		String prefix =
			"page-template-" + getKey() + "-" +
				StringUtil.toLowerCase(blockTypeKey) + "-";

		Iterator<String> iterator = configuration.keys();

		List<String> keys = new ArrayList<>();

		while (iterator.hasNext()) {
			keys.add(iterator.next());
		}

		keys.add("title");
		keys.add("body");
		keys.add("subtitle");

		for (String key : keys) {
			String text = CampaignLanguageUtil.fetch(locale, prefix + key);

			if (text != null) {
				configuration.put(key, text);
			}
		}
	}

}
