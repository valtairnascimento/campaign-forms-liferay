package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignModeService;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignTransactionUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignModeService.class)
public class CampaignModeServiceImpl implements CampaignModeService {
	@Override
	public Campaign convertToCustomPage(long userId, long campaignId)
		throws PortalException {
		Campaign campaign = _campaignLocalService.getCampaign(campaignId);

		if (CampaignMode.CUSTOM_PAGE.matches(campaign.getMode())) {
			return campaign;
		}

		return CampaignTransactionUtil.invoke(
			() -> {
				PageConfiguration pageConfiguration =
					_pageConfigurationLocalService.getPageConfiguration(
						campaignId, PageStage.DRAFT);

				long pageConfigurationId =
					pageConfiguration.getPageConfigurationId();

				List<PageBlock> pageBlocks =
					_pageBlockLocalService.getPageBlocks(pageConfigurationId);

				if (pageBlocks.isEmpty()) {
					_pageBlockLocalService.addPageBlock(
						pageConfigurationId, CampaignConstants.BLOCK_TYPE_HERO,
						_jsonFactory.createJSONObject(
						).put(
							"subtitle", campaign.getDescription()
						).put(
							"title", campaign.getName()
						).toString(),
						true);
				}

				if (!_hasFormBlock(pageBlocks)) {
					_pageBlockLocalService.addPageBlock(
						pageConfigurationId, CampaignConstants.BLOCK_TYPE_FORM,
						"{}", true);
				}

				return _campaignLocalService.updateMode(
					userId, campaignId, CampaignMode.CUSTOM_PAGE);
			});
	}

	private boolean _hasFormBlock(List<PageBlock> pageBlocks) {
		for (PageBlock pageBlock : pageBlocks) {
			if (CampaignConstants.BLOCK_TYPE_FORM.equals(
					pageBlock.getBlockType())) {
				return true;
			}
		}

		return false;
	}

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
