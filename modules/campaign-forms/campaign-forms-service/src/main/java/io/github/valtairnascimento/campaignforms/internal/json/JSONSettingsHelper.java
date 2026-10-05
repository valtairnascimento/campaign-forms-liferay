package io.github.valtairnascimento.campaignforms.internal.json;

import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = JSONSettingsHelper.class)
public class JSONSettingsHelper {
	public String normalizeArray(String json) throws JSONException {
		if (CampaignValidator.isBlank(json)) {
			return "[]";
		}

		return _jsonFactory.createJSONArray(
			json
		).toString();
	}

	public String normalizeObject(String json) throws JSONException {
		if (CampaignValidator.isBlank(json)) {
			return "{}";
		}

		return _jsonFactory.createJSONObject(
			json
		).toString();
	}

	@Reference
	private JSONFactory _jsonFactory;
}
