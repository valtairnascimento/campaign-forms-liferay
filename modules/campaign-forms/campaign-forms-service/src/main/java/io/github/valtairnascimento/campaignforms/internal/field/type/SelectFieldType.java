package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.field.FieldType;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + SelectFieldType.KEY,
	service = FieldType.class
)
public class SelectFieldType extends BaseOptionFieldType {
	public static final String KEY = "select";

	@Override
	public String getKey() {
		return KEY;
	}

}
