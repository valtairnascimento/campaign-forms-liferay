package io.github.valtairnascimento.campaignforms.internal.page.type;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.page.BaseBlockType;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockType.PROPERTY_KEY + "=" + CampaignConstants.BLOCK_TYPE_FORM,
	service = BlockType.class
)
public class FormBlockType extends BaseBlockType {
	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		return _jsonFactory.createJSONObject();
	}

	@Override
	public String getKey() {
		return CampaignConstants.BLOCK_TYPE_FORM;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public JSONObject sanitize(
			JSONObject configuration, BlockContext blockContext)
		throws PageBlockConfigurationException {
		JSONObject sanitized = _jsonFactory.createJSONObject();

		readText(configuration, sanitized, "title", 150, false);
		readMarkup(
			configuration, sanitized, "description", 2000, false,
			"description");

		return sanitized;
	}

	@Reference
	private JSONFactory _jsonFactory;
}
