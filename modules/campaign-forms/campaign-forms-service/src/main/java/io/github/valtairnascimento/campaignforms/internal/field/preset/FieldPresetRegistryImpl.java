package io.github.valtairnascimento.campaignforms.internal.field.preset;

import io.github.valtairnascimento.campaignforms.field.FieldPreset;
import io.github.valtairnascimento.campaignforms.field.FieldPresetRegistry;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = FieldPresetRegistry.class)
public class FieldPresetRegistryImpl implements FieldPresetRegistry {
	@Override
	public FieldPreset getFieldPreset(String key) {
		if (key == null) {
			return null;
		}

		FieldPreset fieldPreset = _BUILT_IN_FIELD_PRESETS.get(key);

		if (fieldPreset != null) {
			return fieldPreset;
		}

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public List<FieldPreset> getFieldPresets() {
		List<FieldPreset> fieldPresets = new ArrayList<>(
			_BUILT_IN_FIELD_PRESETS.values());

		for (String key : new TreeSet<>(_serviceTrackerMap.keySet())) {
			FieldPreset fieldPreset = _serviceTrackerMap.getService(key);

			if ((fieldPreset != null) &&
				!_BUILT_IN_FIELD_PRESETS.containsKey(key)) {
				fieldPresets.add(fieldPreset);
			}
		}

		return fieldPresets;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, FieldPreset.class, FieldPreset.PROPERTY_KEY);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static void _add(
		Map<String, FieldPreset> fieldPresets, String key, String type,
		boolean required, boolean personalData, String validation,
		String autocomplete, List<FieldOptionInput> options) {
		fieldPresets.put(
			key,
			new BuiltInFieldPreset(
				key, type, required, personalData, validation,
				"{\"autocomplete\":\"" + autocomplete + "\"}", options));
	}

	private static List<FieldOptionInput> _getBrazilianStates() {
		String[][] states = {
			{"AC", "Acre"}, {"AL", "Alagoas"}, {"AP", "Amapá"},
			{"AM", "Amazonas"}, {"BA", "Bahia"}, {"CE", "Ceará"},
			{"DF", "Distrito Federal"}, {"ES", "Espírito Santo"},
			{"GO", "Goiás"}, {"MA", "Maranhão"}, {"MT", "Mato Grosso"},
			{"MS", "Mato Grosso do Sul"}, {"MG", "Minas Gerais"},
			{"PA", "Pará"}, {"PB", "Paraíba"}, {"PR", "Paraná"},
			{"PE", "Pernambuco"}, {"PI", "Piauí"}, {"RJ", "Rio de Janeiro"},
			{"RN", "Rio Grande do Norte"}, {"RS", "Rio Grande do Sul"},
			{"RO", "Rondônia"}, {"RR", "Roraima"}, {"SC", "Santa Catarina"},
			{"SP", "São Paulo"}, {"SE", "Sergipe"}, {"TO", "Tocantins"}
		};

		List<FieldOptionInput> options = new ArrayList<>(states.length);

		for (String[] state : states) {
			options.add(new FieldOptionInput(state[1], state[0]));
		}

		return options;
	}

	private static final Map<String, FieldPreset> _BUILT_IN_FIELD_PRESETS;

	static {
		Map<String, FieldPreset> fieldPresets = new LinkedHashMap<>();

		List<FieldOptionInput> noOptions = Collections.emptyList();

		_add(
			fieldPresets, "nome", "text", true, true, "{\"maxLength\":150}",
			"name", noOptions);
		_add(
			fieldPresets, "email", "email", true, true, null, "email",
			noOptions);
		_add(
			fieldPresets, "telefone", "tel", false, true, null, "tel",
			noOptions);
		_add(
			fieldPresets, "empresa", "text", false, false,
			"{\"maxLength\":150}", "organization", noOptions);
		_add(
			fieldPresets, "cargo", "text", false, false, "{\"maxLength\":150}",
			"organization-title", noOptions);
		_add(
			fieldPresets, "cidade", "text", false, false,
			"{\"maxLength\":100}", "address-level2", noOptions);
		_add(
			fieldPresets, "estado", "select", false, false, null,
			"address-level1", _getBrazilianStates());

		_BUILT_IN_FIELD_PRESETS = Collections.unmodifiableMap(fieldPresets);
	}

	private ServiceTrackerMap<String, FieldPreset> _serviceTrackerMap;
}
