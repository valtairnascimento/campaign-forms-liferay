package io.github.valtairnascimento.campaignforms.internal.page;

import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;
import io.github.valtairnascimento.campaignforms.page.PageTemplateRegistry;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = PageTemplateRegistry.class)
public class PageTemplateRegistryImpl implements PageTemplateRegistry {
	@Override
	public PageTemplateDefinition getPageTemplateDefinition(String key) {
		if (key == null) {
			return null;
		}

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public List<PageTemplateDefinition> getPageTemplateDefinitions() {
		List<PageTemplateDefinition> pageTemplateDefinitions =
			new ArrayList<>();

		for (String key : _serviceTrackerMap.keySet()) {
			PageTemplateDefinition pageTemplateDefinition =
				_serviceTrackerMap.getService(key);

			if (pageTemplateDefinition != null) {
				pageTemplateDefinitions.add(pageTemplateDefinition);
			}
		}

		pageTemplateDefinitions.sort(
			Comparator.comparingInt(
				(PageTemplateDefinition pageTemplateDefinition) -> _getOrder(
					pageTemplateDefinition.getKey())
			).thenComparing(
				PageTemplateDefinition::getKey
			));

		return pageTemplateDefinitions;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, PageTemplateDefinition.class,
			PageTemplateDefinition.PROPERTY_KEY);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private int _getOrder(String key) {
		int index = _ORDER.indexOf(key);

		return (index < 0) ? _ORDER.size() : index;
	}

	private static final List<String> _ORDER = List.of(
		"event", "interest", "blank");

	private ServiceTrackerMap<String, PageTemplateDefinition>
		_serviceTrackerMap;
}
