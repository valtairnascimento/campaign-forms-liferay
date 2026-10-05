package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.page.BlockContextUtil;
import io.github.valtairnascimento.campaignforms.internal.util.DraftLockUtil;
import io.github.valtairnascimento.campaignforms.internal.util.PositionUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;
import io.github.valtairnascimento.campaignforms.service.base.PageBlockLocalServiceBaseImpl;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.PageBlock",
	service = AopService.class
)
public class PageBlockLocalServiceImpl extends PageBlockLocalServiceBaseImpl {
	@Override
	public PageBlock addPageBlock(
			long pageConfigurationId, String blockType, String configuration,
			boolean enabled)
		throws PortalException {
		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageConfigurationId);

		BlockType type = _getBlockType(blockType);

		_checkSingleton(type, pageConfigurationId);

		PageBlock pageBlock = _createPageBlock(
			pageConfiguration, blockType,
			_generateBlockKey(pageConfigurationId, blockType),
			_sanitize(type, configuration, pageConfiguration), enabled,
			type.getSchemaVersion(),
			pageBlockPersistence.countByPageConfigurationId(
				pageConfigurationId));

		_touch(pageConfiguration);

		return pageBlock;
	}

	@Override
	public List<PageBlock> copyPageBlocks(
		long sourcePageConfigurationId,
		PageConfiguration targetPageConfiguration) {
		List<PageBlock> copies = new ArrayList<>();

		for (PageBlock pageBlock :
				pageBlockPersistence.findByPageConfigurationId(
					sourcePageConfigurationId)) {
			copies.add(
				_createPageBlock(
					targetPageConfiguration, pageBlock.getBlockType(),
					pageBlock.getBlockKey(), pageBlock.getConfiguration(),
					pageBlock.isEnabled(), pageBlock.getSchemaVersion(),
					pageBlock.getPosition()));
		}

		return copies;
	}

	@Override
	public PageBlock deletePageBlock(long pageBlockId) throws PortalException {
		PageBlock pageBlock = pageBlockPersistence.findByPrimaryKey(
			pageBlockId);

		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageBlock.getPageConfigurationId());

		pageBlock = pageBlockPersistence.remove(pageBlock);

		_compactPositions(pageConfiguration.getPageConfigurationId());

		_touch(pageConfiguration);

		return pageBlock;
	}

	@Override
	public void deletePageBlocks(long pageConfigurationId) {
		pageBlockPersistence.removeByPageConfigurationId(pageConfigurationId);
	}

	@Override
	public PageBlock duplicatePageBlock(long pageBlockId)
		throws PortalException {
		PageBlock pageBlock = pageBlockPersistence.findByPrimaryKey(
			pageBlockId);

		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageBlock.getPageConfigurationId());

		_checkSingleton(
			_getBlockType(pageBlock.getBlockType()),
			pageConfiguration.getPageConfigurationId());

		long pageConfigurationId = pageConfiguration.getPageConfigurationId();

		PageBlock copy = _createPageBlock(
			pageConfiguration, pageBlock.getBlockType(),
			_generateBlockKey(pageConfigurationId, pageBlock.getBlockType()),
			pageBlock.getConfiguration(), pageBlock.isEnabled(),
			pageBlock.getSchemaVersion(),
			pageBlockPersistence.countByPageConfigurationId(
				pageConfigurationId));

		_updatePositions(
			PositionUtil.move(
				pageBlockPersistence.findByPageConfigurationId(
					pageConfigurationId),
				copy.getPageBlockId(), pageBlock.getPosition() + 1,
				PageBlock::getPageBlockId, PageBlock::getPosition,
				PageBlock::setPosition));

		_touch(pageConfiguration);

		return pageBlockPersistence.findByPrimaryKey(copy.getPageBlockId());
	}

	@Override
	public List<PageBlock> getPageBlocks(long pageConfigurationId) {
		return pageBlockPersistence.findByPageConfigurationId(
			pageConfigurationId);
	}

	@Override
	public List<PageBlock> movePageBlock(long pageBlockId, int position)
		throws PortalException {
		PageBlock pageBlock = pageBlockPersistence.findByPrimaryKey(
			pageBlockId);

		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageBlock.getPageConfigurationId());

		long pageConfigurationId = pageConfiguration.getPageConfigurationId();

		_updatePositions(
			PositionUtil.move(
				pageBlockPersistence.findByPageConfigurationId(
					pageConfigurationId),
				pageBlockId, position, PageBlock::getPageBlockId,
				PageBlock::getPosition, PageBlock::setPosition));

		_touch(pageConfiguration);

		return pageBlockPersistence.findByPageConfigurationId(
			pageConfigurationId);
	}

	@Override
	public List<PageBlock> reorderPageBlocks(
			long pageConfigurationId, long[] orderedPageBlockIds)
		throws PortalException {
		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageConfigurationId);

		try {
			_updatePositions(
				PositionUtil.reorder(
					pageBlockPersistence.findByPageConfigurationId(
						pageConfigurationId),
					orderedPageBlockIds, PageBlock::getPageBlockId,
					PageBlock::getPosition, PageBlock::setPosition));
		}
		catch (IllegalArgumentException illegalArgumentException) {
			throw new PageBlockConfigurationException(
				"block-order-is-invalid", illegalArgumentException);
		}

		_touch(pageConfiguration);

		return pageBlockPersistence.findByPageConfigurationId(
			pageConfigurationId);
	}

	@Override
	public PageBlock updatePageBlock(
			long pageBlockId, String configuration, boolean enabled)
		throws PortalException {
		PageBlock pageBlock = pageBlockPersistence.findByPrimaryKey(
			pageBlockId);

		PageConfiguration pageConfiguration = _getDraftPageConfiguration(
			pageBlock.getPageConfigurationId());

		BlockType type = _getBlockType(pageBlock.getBlockType());

		pageBlock.setModifiedDate(new Date());
		pageBlock.setConfiguration(
			_sanitize(type, configuration, pageConfiguration));
		pageBlock.setSchemaVersion(type.getSchemaVersion());
		pageBlock.setEnabled(enabled);

		pageBlock = pageBlockPersistence.update(pageBlock);

		_touch(pageConfiguration);

		return pageBlock;
	}

	private void _compactPositions(long pageConfigurationId) {
		List<PageBlock> pageBlocks =
			pageBlockPersistence.findByPageConfigurationId(pageConfigurationId);

		for (int i = 0; i < pageBlocks.size(); i++) {
			PageBlock pageBlock = pageBlocks.get(i);

			if (pageBlock.getPosition() != i) {
				pageBlock.setPosition(i);

				pageBlockPersistence.update(pageBlock);
			}
		}
	}

	private PageBlock _createPageBlock(
		PageConfiguration pageConfiguration, String blockType, String blockKey,
		String configuration, boolean enabled, int schemaVersion,
		int position) {
		PageBlock pageBlock = pageBlockPersistence.create(
			counterLocalService.increment());

		pageBlock.setCompanyId(pageConfiguration.getCompanyId());

		Date now = new Date();

		pageBlock.setCreateDate(now);
		pageBlock.setModifiedDate(now);

		pageBlock.setPageConfigurationId(
			pageConfiguration.getPageConfigurationId());
		pageBlock.setBlockType(blockType);
		pageBlock.setBlockKey(blockKey);
		pageBlock.setPosition(position);
		pageBlock.setEnabled(enabled);
		pageBlock.setSchemaVersion(schemaVersion);
		pageBlock.setConfiguration(configuration);

		return pageBlockPersistence.update(pageBlock);
	}

	private String _generateBlockKey(
		long pageConfigurationId, String blockType) {
		String baseKey = StringUtil.replace(
			StringUtil.toLowerCase(blockType), CharPool.UNDERLINE,
			CharPool.DASH);

		String blockKey = baseKey;

		for (int i = 2;
			 pageBlockPersistence.fetchByPC_BK(pageConfigurationId, blockKey) !=
				 null;
			 i++) {
			blockKey = baseKey + StringPool.DASH + i;
		}

		return blockKey;
	}

	private PageConfiguration _getDraftPageConfiguration(
			long pageConfigurationId)
		throws PortalException {
		pageConfigurationPersistence.clearCache(
			Collections.<Serializable>singleton(pageConfigurationId));

		PageConfiguration pageConfiguration =
			pageConfigurationPersistence.findByPrimaryKey(pageConfigurationId);

		DraftLockUtil.checkDraft(pageConfiguration);

		return pageConfiguration;
	}

	private BlockType _getBlockType(String blockType)
		throws PageBlockConfigurationException {
		BlockType type = _blockTypeRegistry.getBlockType(blockType);

		if (type == null) {
			throw new PageBlockConfigurationException(
				"block-type-is-invalid", "blockType");
		}

		return type;
	}

	private void _checkSingleton(BlockType blockType, long pageConfigurationId)
		throws PageBlockConfigurationException {
		if (!blockType.isSingleton()) {
			return;
		}

		for (PageBlock pageBlock :
				pageBlockPersistence.findByPageConfigurationId(
					pageConfigurationId)) {
			if (blockType.getKey().equals(pageBlock.getBlockType())) {
				throw new PageBlockConfigurationException(
					"block-type-is-singleton", "blockType");
			}
		}
	}

	private String _sanitize(
			BlockType blockType, String configuration,
			PageConfiguration pageConfiguration)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(
			pageConfiguration.getCampaignId());

		BlockContext blockContext = BlockContextUtil.create(campaign);

		JSONObject configurationJSONObject;

		if ((configuration == null) || configuration.trim(
			).isEmpty()) {
			configurationJSONObject = blockType.getDefaultConfiguration(
				blockContext);
		}
		else {
			try {
				configurationJSONObject = _jsonFactory.createJSONObject(
					_jsonSettingsHelper.normalizeObject(configuration));
			}
			catch (JSONException jsonException) {
				throw new PageBlockConfigurationException(
					"block-configuration-is-invalid", jsonException);
			}
		}

		JSONObject sanitizedJSONObject = blockType.sanitize(
			configurationJSONObject, blockContext);

		return sanitizedJSONObject.toString();
	}

	private void _touch(PageConfiguration pageConfiguration) {
		pageConfiguration.setModifiedDate(new Date());

		pageConfigurationPersistence.update(pageConfiguration);
	}

	private void _updatePositions(List<PageBlock> changedPageBlocks) {
		for (PageBlock pageBlock : changedPageBlocks) {
			pageBlockPersistence.update(pageBlock);
		}
	}

	@Reference
	private BlockTypeRegistry _blockTypeRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
