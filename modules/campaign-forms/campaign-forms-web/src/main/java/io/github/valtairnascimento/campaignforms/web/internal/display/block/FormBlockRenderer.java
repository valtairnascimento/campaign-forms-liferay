package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=FORM",
	service = BlockRenderer.class
)
public class FormBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put(
			"descriptionHtml", getMarkupHTML(configuration, "description"));
		viewModel.put("title", getString(configuration, "title"));

		return viewModel;
	}

}
