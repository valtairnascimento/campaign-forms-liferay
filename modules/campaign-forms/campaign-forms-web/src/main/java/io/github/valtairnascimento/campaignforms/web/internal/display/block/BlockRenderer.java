package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import java.util.Map;

public interface BlockRenderer {
	public static final String PROPERTY_KEY = "campaignforms.block.type";

	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext);
}
