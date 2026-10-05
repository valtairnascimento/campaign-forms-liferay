package io.github.valtairnascimento.campaignforms.internal.field;

import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = FieldTypeRegistry.class)
public class FieldTypeRegistryImpl implements FieldTypeRegistry {
	@Override
	public FieldType getFieldType(String key) {
		if (key == null) {
			return null;
		}

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public List<FieldType> getFieldTypes() {
		List<FieldType> fieldTypes = new ArrayList<>();

		for (String key : new TreeSet<>(_serviceTrackerMap.keySet())) {
			FieldType fieldType = _serviceTrackerMap.getService(key);

			if (fieldType != null) {
				fieldTypes.add(fieldType);
			}
		}

		return fieldTypes;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, FieldType.class, FieldType.PROPERTY_KEY);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, FieldType> _serviceTrackerMap;
}
