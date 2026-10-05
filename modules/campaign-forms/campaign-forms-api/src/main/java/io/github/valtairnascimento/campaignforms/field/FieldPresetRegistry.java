package io.github.valtairnascimento.campaignforms.field;

import java.util.List;

public interface FieldPresetRegistry {
	public FieldPreset getFieldPreset(String key);

	public List<FieldPreset> getFieldPresets();
}
