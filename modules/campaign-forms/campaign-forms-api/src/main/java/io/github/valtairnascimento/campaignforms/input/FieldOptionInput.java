package io.github.valtairnascimento.campaignforms.input;

public class FieldOptionInput {
	public FieldOptionInput(String label, String value) {
		_label = label;
		_value = value;
	}

	public String getLabel() {
		return _label;
	}

	public String getValue() {
		return _value;
	}

	private final String _label;
	private final String _value;
}
