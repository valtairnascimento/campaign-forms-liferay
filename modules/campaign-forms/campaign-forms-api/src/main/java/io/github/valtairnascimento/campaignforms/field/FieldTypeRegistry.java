package io.github.valtairnascimento.campaignforms.field;

import java.util.List;

public interface FieldTypeRegistry {
	public FieldType getFieldType(String key);

	public List<FieldType> getFieldTypes();
}
