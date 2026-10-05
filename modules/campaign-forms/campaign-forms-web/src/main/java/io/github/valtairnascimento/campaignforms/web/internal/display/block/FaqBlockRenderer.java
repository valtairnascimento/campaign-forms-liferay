package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.page.SimpleMarkup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=FAQ",
	service = BlockRenderer.class
)
public class FaqBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		List<Map<String, Object>> items = new ArrayList<>();

		Object itemsObject = configuration.get("items");

		if (itemsObject instanceof List) {
			for (Object itemObject : (List<?>)itemsObject) {
				if (!(itemObject instanceof Map)) {
					continue;
				}

				@SuppressWarnings("unchecked")
				Map<String, Object> item = (Map<String, Object>)itemObject;

				Map<String, Object> itemViewModel = new HashMap<>();

				itemViewModel.put(
					"answerHtml",
					SimpleMarkup.toHtml(getString(item, "answer")));
				itemViewModel.put("question", getString(item, "question"));

				items.add(itemViewModel);
			}
		}

		if (items.isEmpty()) {
			return null;
		}

		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put("items", items);
		viewModel.put("title", getString(configuration, "title"));

		return viewModel;
	}

}
