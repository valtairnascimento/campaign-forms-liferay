package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.field.FieldType;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + MultiSelectFieldType.KEY,
	service = FieldType.class
)
public class MultiSelectFieldType extends BaseOptionFieldType {
	public static final String KEY = "multiSelect";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public boolean isMultiValued() {
		return true;
	}

}
