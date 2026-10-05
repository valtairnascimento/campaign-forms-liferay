package io.github.valtairnascimento.campaignforms.internal.page.type;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.page.BaseBlockType;
import io.github.valtairnascimento.campaignforms.internal.page.BlockImageValidator;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = BlockType.PROPERTY_KEY + "=" + ImageBlockType.KEY,
	service = BlockType.class
)
public class ImageBlockType extends BaseBlockType {
	public static final String KEY = "IMAGE";

	@Override
	public JSONObject getDefaultConfiguration(BlockContext blockContext) {
		return _jsonFactory.createJSONObject(
		).put(
			"size", "content"
		);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public JSONObject sanitize(
			JSONObject configuration, BlockContext blockContext)
		throws PageBlockConfigurationException {
		JSONObject sanitized = _jsonFactory.createJSONObject();

		_blockImageValidator.readImage(
			configuration, sanitized, "imageFileEntryId",
			blockContext.getGroupId(), true);

		boolean decorative = readBoolean(
			configuration, sanitized, "decorative");

		if (!decorative) {
			readText(configuration, sanitized, "alt", 250, true);
		}

		readText(configuration, sanitized, "caption", 300, false);
		readOption(configuration, sanitized, "size", _SIZES, "content");

		return sanitized;
	}

	private static final List<String> _SIZES = List.of("content", "wide");

	@Reference
	private BlockImageValidator _blockImageValidator;

	@Reference
	private JSONFactory _jsonFactory;
}
