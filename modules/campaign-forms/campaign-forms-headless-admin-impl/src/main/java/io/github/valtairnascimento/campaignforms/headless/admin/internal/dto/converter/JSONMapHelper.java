package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = JSONMapHelper.class)
public class JSONMapHelper {
	public Map<String, Object> toMap(String json) {
		if (Validator.isNull(json)) {
			return Collections.emptyMap();
		}

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(json);

			return jsonObject.toMap();
		}
		catch (JSONException jsonException) {
			return Collections.emptyMap();
		}
	}

	public String toJSON(Map<String, ?> map) {
		if (map == null) {
			return null;
		}

		return _jsonFactory.createJSONObject(
			map
		).toString();
	}

	@Reference
	private JSONFactory _jsonFactory;
}
