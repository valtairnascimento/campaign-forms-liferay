package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=HERO",
	service = BlockRenderer.class
)
public class HeroBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put(
			"align",
			getStyle(configuration, "align", "left", "left", "center"));
		viewModel.put("buttonLabel", getString(configuration, "buttonLabel"));
		viewModel.put("buttonUrl", getString(configuration, "buttonUrl"));
		viewModel.put(
			"imageUrl",
			GetterUtil.getString(
				_blockImageResolver.getURL(
					configuration.get("imageFileEntryId"),
					blockRenderContext.getGroupId(),
					blockRenderContext.getThemeDisplay())));
		viewModel.put(
			"style",
			getStyle(
				configuration, "style", "brand", "brand", "light", "dark"));
		viewModel.put("subtitle", getString(configuration, "subtitle"));
		viewModel.put("title", getString(configuration, "title"));

		return viewModel;
	}

	@Reference
	private BlockImageResolver _blockImageResolver;
}
