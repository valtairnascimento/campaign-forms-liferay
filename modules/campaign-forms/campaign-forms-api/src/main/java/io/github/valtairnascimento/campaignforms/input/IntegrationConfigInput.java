package io.github.valtairnascimento.campaignforms.input;

import io.github.valtairnascimento.campaignforms.constants.EventType;

import java.util.ArrayList;
import java.util.List;

public class IntegrationConfigInput {
	public List<EventType> getEventTypes() {
		return _eventTypes;
	}

	public String getName() {
		return _name;
	}

	public String getProviderKey() {
		return _providerKey;
	}

	public String getSettings() {
		return _settings;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public IntegrationConfigInput setEnabled(boolean enabled) {
		_enabled = enabled;

		return this;
	}

	public IntegrationConfigInput setEventTypes(List<EventType> eventTypes) {
		_eventTypes = (eventTypes == null) ? new ArrayList<>() : eventTypes;

		return this;
	}

	public IntegrationConfigInput setName(String name) {
		_name = name;

		return this;
	}

	public IntegrationConfigInput setProviderKey(String providerKey) {
		_providerKey = providerKey;

		return this;
	}

	public IntegrationConfigInput setSettings(String settings) {
		_settings = settings;

		return this;
	}

	private boolean _enabled;
	private List<EventType> _eventTypes = new ArrayList<>();
	private String _name;
	private String _providerKey;
	private String _settings;
}
