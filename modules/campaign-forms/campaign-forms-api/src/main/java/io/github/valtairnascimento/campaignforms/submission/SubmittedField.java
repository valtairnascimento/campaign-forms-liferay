package io.github.valtairnascimento.campaignforms.submission;

import io.github.valtairnascimento.campaignforms.field.FieldValue;

import java.util.Collections;
import java.util.List;

public class SubmittedField {
	public SubmittedField(
		long formFieldId, String fieldKey, List<FieldValue> fieldValues) {
		_formFieldId = formFieldId;
		_fieldKey = fieldKey;
		_fieldValues = Collections.unmodifiableList(fieldValues);
	}

	public String getFieldKey() {
		return _fieldKey;
	}

	public List<FieldValue> getFieldValues() {
		return _fieldValues;
	}

	public long getFormFieldId() {
		return _formFieldId;
	}

	private final String _fieldKey;
	private final List<FieldValue> _fieldValues;
	private final long _formFieldId;
}
