package io.github.valtairnascimento.campaignforms.field;

import io.github.valtairnascimento.campaignforms.input.FormFieldInput;

import java.util.Locale;

public interface FieldPreset {
	public static final String PROPERTY_KEY = "campaignforms.field.preset";

	public String getKey();

	public String getLabel(Locale locale);

	public FormFieldInput toFormFieldInput(Locale locale);
}
