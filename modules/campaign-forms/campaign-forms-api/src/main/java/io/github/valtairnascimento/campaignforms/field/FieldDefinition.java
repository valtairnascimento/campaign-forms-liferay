package io.github.valtairnascimento.campaignforms.field;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class FieldDefinition {
	public FieldDefinition(
		long formFieldId, String fieldKey, String type, String label,
		boolean required, String defaultValue, JSONObject validation,
		JSONObject properties, List<String> optionValues,
		boolean personalData) {
		_formFieldId = formFieldId;
		_fieldKey = fieldKey;
		_type = type;
		_label = label;
		_required = required;
		_defaultValue = defaultValue;
		_validation = validation;
		_properties = properties;
		_optionValues = Collections.unmodifiableList(optionValues);
		_personalData = personalData;
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public String getFieldKey() {
		return _fieldKey;
	}

	public long getFormFieldId() {
		return _formFieldId;
	}

	public String getLabel() {
		return _label;
	}

	public List<String> getOptionValues() {
		return _optionValues;
	}

	public JSONObject getProperties() {
		return _properties;
	}

	public String getType() {
		return _type;
	}

	public JSONObject getValidation() {
		return _validation;
	}

	public boolean isPersonalData() {
		return _personalData;
	}

	public boolean isRequired() {
		return _required;
	}

	private final String _defaultValue;
	private final String _fieldKey;
	private final long _formFieldId;
	private final String _label;
	private final List<String> _optionValues;
	private final boolean _personalData;
	private final JSONObject _properties;
	private final boolean _required;
	private final String _type;
	private final JSONObject _validation;
}
