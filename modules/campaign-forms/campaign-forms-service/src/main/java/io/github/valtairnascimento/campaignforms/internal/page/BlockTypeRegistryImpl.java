package io.github.valtairnascimento.campaignforms.internal.page;

import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = BlockTypeRegistry.class)
public class BlockTypeRegistryImpl implements BlockTypeRegistry {
	@Override
	public BlockType getBlockType(String key) {
		if (key == null) {
			return null;
		}

		return _serviceTrackerMap.getService(key);
	}

	@Override
	public List<BlockType> getBlockTypes() {
		List<BlockType> blockTypes = new ArrayList<>();

		for (String key : _serviceTrackerMap.keySet()) {
			BlockType blockType = _serviceTrackerMap.getService(key);

			if (blockType != null) {
				blockTypes.add(blockType);
			}
		}

		blockTypes.sort(
			Comparator.comparingInt(
				(BlockType blockType) -> _getOrder(blockType.getKey())
			).thenComparing(
				BlockType::getKey
			));

		return blockTypes;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, BlockType.class, BlockType.PROPERTY_KEY);
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
		"HERO", "TEXT", "IMAGE", "CTA", "FAQ", "FORM");

	private ServiceTrackerMap<String, BlockType> _serviceTrackerMap;
}
