package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Order;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PageBlock;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Position;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.PageDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.PageBlockResource;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationService;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/page-block.properties",
	scope = ServiceScope.PROTOTYPE, service = PageBlockResource.class
)
public class PageBlockResourceImpl extends BasePageBlockResourceImpl {
	@Override
	public Response deletePageBlock(Long pageBlockId) throws Exception {
		_pageConfigurationService.deletePageBlock(pageBlockId);

		return Response.noContent(
		).build();
	}

	@Override
	public PageBlock getPageBlock(Long pageBlockId) throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.getPageBlock(pageBlockId));
	}

	@Override
	public PageBlock postCampaignPageBlock(Long campaignId, PageBlock pageBlock)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.addPageBlock(
				campaignId, pageBlock.getBlockType(),
				_pageDTOConverter.toConfigurationJSON(pageBlock),
				GetterUtil.getBoolean(pageBlock.getEnabled(), true)));
	}

	@Override
	public PageBlock postPageBlockDuplicate(Long pageBlockId)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.duplicatePageBlock(pageBlockId));
	}

	@Override
	public Page<PageBlock> putCampaignPageBlocksOrder(
			Long campaignId, Order order)
		throws Exception {
		return Page.of(
			Arrays.asList(
				_pageDTOConverter.toDTOs(
					_pageConfigurationService.reorderPageBlocks(
						campaignId, ArrayUtil.toArray(order.getIds())))));
	}

	@Override
	public PageBlock putPageBlock(Long pageBlockId, PageBlock pageBlock)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.updatePageBlock(
				pageBlockId, _pageDTOConverter.toConfigurationJSON(pageBlock),
				GetterUtil.getBoolean(pageBlock.getEnabled(), true)));
	}

	@Override
	public Page<PageBlock> putPageBlockPosition(
			Long pageBlockId, Position position)
		throws Exception {
		return Page.of(
			Arrays.asList(
				_pageDTOConverter.toDTOs(
					_pageConfigurationService.movePageBlock(
						pageBlockId,
						GetterUtil.getInteger(position.getPosition())))));
	}

	@Reference
	private PageConfigurationService _pageConfigurationService;

	@Reference
	private PageDTOConverter _pageDTOConverter;
}
