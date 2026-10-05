package io.github.valtairnascimento.campaignforms.input;

import io.github.valtairnascimento.campaignforms.constants.FieldWidth;

import java.util.ArrayList;
import java.util.List;

public class FormFieldInput {
	public String getConditionalRule() {
		return _conditionalRule;
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public String getFieldKey() {
		return _fieldKey;
	}

	public String getHelpText() {
		return _helpText;
	}

	public String getLabel() {
		return _label;
	}

	public List<FieldOptionInput> getOptions() {
		return _options;
	}

	public String getPlaceholder() {
		return _placeholder;
	}

	public String getPresetKey() {
		return _presetKey;
	}

	public String getProperties() {
		return _properties;
	}

	public String getType() {
		return _type;
	}

	public String getValidation() {
		return _validation;
	}

	public FieldWidth getWidth() {
		return _width;
	}

	public boolean isPersonalData() {
		return _personalData;
	}

	public boolean isRequired() {
		return _required;
	}

	public FormFieldInput setConditionalRule(String conditionalRule) {
		_conditionalRule = conditionalRule;

		return this;
	}

	public FormFieldInput setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;

		return this;
	}

	public FormFieldInput setFieldKey(String fieldKey) {
		_fieldKey = fieldKey;

		return this;
	}

	public FormFieldInput setHelpText(String helpText) {
		_helpText = helpText;

		return this;
	}

	public FormFieldInput setLabel(String label) {
		_label = label;

		return this;
	}

	public FormFieldInput setOptions(List<FieldOptionInput> options) {
		_options = (options == null) ? new ArrayList<>() : options;

		return this;
	}

	public FormFieldInput setPersonalData(boolean personalData) {
		_personalData = personalData;

		return this;
	}

	public FormFieldInput setPlaceholder(String placeholder) {
		_placeholder = placeholder;

		return this;
	}

	public FormFieldInput setPresetKey(String presetKey) {
		_presetKey = presetKey;

		return this;
	}

	public FormFieldInput setProperties(String properties) {
		_properties = properties;

		return this;
	}

	public FormFieldInput setRequired(boolean required) {
		_required = required;

		return this;
	}

	public FormFieldInput setType(String type) {
		_type = type;

		return this;
	}

	public FormFieldInput setValidation(String validation) {
		_validation = validation;

		return this;
	}

	public FormFieldInput setWidth(FieldWidth width) {
		_width = width;

		return this;
	}

	private String _conditionalRule;
	private String _defaultValue;
	private String _fieldKey;
	private String _helpText;
	private String _label;
	private List<FieldOptionInput> _options = new ArrayList<>();
	private boolean _personalData;
	private String _placeholder;
	private String _presetKey;
	private String _properties;
	private boolean _required;
	private String _type;
	private String _validation;
	private FieldWidth _width = FieldWidth.FULL;
}
