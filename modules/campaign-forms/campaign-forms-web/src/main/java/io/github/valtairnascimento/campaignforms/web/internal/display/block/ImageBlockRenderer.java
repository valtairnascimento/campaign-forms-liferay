package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockRenderer.PROPERTY_KEY + "=IMAGE",
	service = BlockRenderer.class
)
public class ImageBlockRenderer extends BaseBlockRenderer {
	@Override
	public Map<String, Object> getViewModel(
		PublicCampaignSnapshot.Block block,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> configuration = block.getConfiguration();

		String imageUrl = _blockImageResolver.getURL(
			configuration.get("imageFileEntryId"),
			blockRenderContext.getGroupId(),
			blockRenderContext.getThemeDisplay());

		if (Validator.isNull(imageUrl)) {
			return null;
		}

		Map<String, Object> viewModel = new HashMap<>();

		boolean decorative = GetterUtil.getBoolean(
			configuration.get("decorative"));

		viewModel.put("alt", decorative ? "" : getString(configuration, "alt"));
		viewModel.put("caption", getString(configuration, "caption"));
		viewModel.put("imageUrl", imageUrl);
		viewModel.put(
			"size",
			getStyle(configuration, "size", "content", "content", "wide"));

		return viewModel;
	}

	@Reference
	private BlockImageResolver _blockImageResolver;
}
