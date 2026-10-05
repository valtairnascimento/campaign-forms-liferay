package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = BlockRendererRegistry.class)
public class BlockRendererRegistry {
	public BlockRenderer getBlockRenderer(String blockType) {
		if (blockType == null) {
			return null;
		}

		return _serviceTrackerMap.getService(blockType);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, BlockRenderer.class, BlockRenderer.PROPERTY_KEY);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, BlockRenderer> _serviceTrackerMap;
}
