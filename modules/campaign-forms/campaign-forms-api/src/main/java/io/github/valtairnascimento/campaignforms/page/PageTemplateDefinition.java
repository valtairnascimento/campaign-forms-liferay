package io.github.valtairnascimento.campaignforms.page;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;
import java.util.Locale;

public interface PageTemplateDefinition {
	public static final String PROPERTY_KEY = "campaignforms.page.template";

	public List<TemplateBlock> getBlocks(BlockContext blockContext);

	public String getDescription(Locale locale);

	public String getKey();

	public String getLabel(Locale locale);

	public static class TemplateBlock {
		public TemplateBlock(String blockType, JSONObject configuration) {
			_blockType = blockType;
			_configuration = configuration;
		}

		public String getBlockType() {
			return _blockType;
		}

		public JSONObject getConfiguration() {
			return _configuration;
		}

		private final String _blockType;
		private final JSONObject _configuration;
	}

}
