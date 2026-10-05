package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.field.FieldType;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + RadioFieldType.KEY,
	service = FieldType.class
)
public class RadioFieldType extends BaseOptionFieldType {
	public static final String KEY = "radio";

	@Override
	public String getKey() {
		return KEY;
	}

}
