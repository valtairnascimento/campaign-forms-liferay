package io.github.valtairnascimento.campaignforms.internal.field.preset;

import io.github.valtairnascimento.campaignforms.field.FieldPreset;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BuiltInFieldPreset implements FieldPreset {
	public BuiltInFieldPreset(
		String key, String type, boolean required, boolean personalData,
		String validation, String properties, List<FieldOptionInput> options) {
		_key = key;
		_type = type;
		_required = required;
		_personalData = personalData;
		_validation = validation;
		_properties = properties;
		_options = Collections.unmodifiableList(options);
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getLabel(Locale locale) {
		return CampaignLanguageUtil.get(locale, "field-preset-" + _key);
	}

	@Override
	public FormFieldInput toFormFieldInput(Locale locale) {
		return new FormFieldInput(
		).setFieldKey(
			_key
		).setLabel(
			getLabel(locale)
		).setType(
			_type
		).setRequired(
			_required
		).setPersonalData(
			_personalData
		).setPresetKey(
			_key
		).setValidation(
			_validation
		).setProperties(
			_properties
		).setOptions(
			new ArrayList<>(_options)
		);
	}

	private final String _key;
	private final List<FieldOptionInput> _options;
	private final boolean _personalData;
	private final String _properties;
	private final boolean _required;
	private final String _type;
	private final String _validation;
}
