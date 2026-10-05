package io.github.valtairnascimento.campaignforms.internal.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationData {
	public void addFieldValue(String fieldKey, String rawValue) {
		_fieldValues.computeIfAbsent(
			fieldKey, key -> new ArrayList<>()
		).add(
			rawValue
		);
	}

	public void addTableRow(String label, String value) {
		_tableRows.add(new String[] {label, value});
	}

	public List<String> getFieldValues(String fieldKey) {
		return _fieldValues.getOrDefault(fieldKey, Collections.emptyList());
	}

	public List<String[]> getTableRows() {
		return _tableRows;
	}

	public String getValue(String key) {
		return _values.get(key);
	}

	public NotificationData put(String key, String value) {
		if (value != null) {
			_values.put(key, value);
		}

		return this;
	}

	private final Map<String, List<String>> _fieldValues = new HashMap<>();
	private final List<String[]> _tableRows = new ArrayList<>();
	private final Map<String, String> _values = new HashMap<>();
}
