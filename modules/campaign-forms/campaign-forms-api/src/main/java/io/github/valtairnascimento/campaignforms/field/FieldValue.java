package io.github.valtairnascimento.campaignforms.field;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;

import java.math.BigDecimal;

import java.util.Date;

public class FieldValue {
	public static final int MAX_STRING_VALUE_LENGTH = 500;

	public static FieldValue ofBoolean(boolean value) {
		return new FieldValue(
			FieldValueType.BOOLEAN, String.valueOf(value), null, null, null,
			value);
	}

	public static FieldValue ofDate(
		FieldValueType valueType, Date value, String isoValue) {
		return new FieldValue(valueType, isoValue, null, null, value, false);
	}

	public static FieldValue ofNumber(BigDecimal value) {
		return new FieldValue(
			FieldValueType.NUMBER, value.toPlainString(), null, value, null,
			false);
	}

	public static FieldValue ofString(FieldValueType valueType, String value) {
		if (value.length() <= MAX_STRING_VALUE_LENGTH) {
			return new FieldValue(valueType, value, null, null, null, false);
		}

		return new FieldValue(
			valueType, value.substring(0, MAX_STRING_VALUE_LENGTH), value, null,
			null, false);
	}

	public Date getDateValue() {
		return _dateValue;
	}

	public String getFullStringValue() {
		if (_textValue != null) {
			return _textValue;
		}

		return _stringValue;
	}

	public BigDecimal getNumberValue() {
		return _numberValue;
	}

	public String getStringValue() {
		return _stringValue;
	}

	public String getTextValue() {
		return _textValue;
	}

	public FieldValueType getValueType() {
		return _valueType;
	}

	public boolean isBooleanValue() {
		return _booleanValue;
	}

	private FieldValue(
		FieldValueType valueType, String stringValue, String textValue,
		BigDecimal numberValue, Date dateValue, boolean booleanValue) {
		_valueType = valueType;
		_stringValue = stringValue;
		_textValue = textValue;
		_numberValue = numberValue;
		_dateValue = dateValue;
		_booleanValue = booleanValue;
	}

	private final boolean _booleanValue;
	private final Date _dateValue;
	private final BigDecimal _numberValue;
	private final String _stringValue;
	private final String _textValue;
	private final FieldValueType _valueType;
}
