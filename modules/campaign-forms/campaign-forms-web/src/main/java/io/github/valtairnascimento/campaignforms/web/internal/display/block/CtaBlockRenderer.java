package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=CTA",
	service = BlockRenderer.class
)
public class CtaBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put("buttonLabel", getString(configuration, "buttonLabel"));
		viewModel.put("buttonUrl", getString(configuration, "buttonUrl"));
		viewModel.put(
			"style",
			getStyle(
				configuration, "style", "brand", "brand", "light", "dark"));
		viewModel.put("textHtml", getMarkupHTML(configuration, "text"));
		viewModel.put("title", getString(configuration, "title"));

		return viewModel;
	}

}
