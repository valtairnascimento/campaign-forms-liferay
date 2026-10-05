package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=TEXT",
	service = BlockRenderer.class
)
public class TextBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put("bodyHtml", getMarkupHTML(configuration, "body"));
		viewModel.put("title", getString(configuration, "title"));

		return viewModel;
	}

}
