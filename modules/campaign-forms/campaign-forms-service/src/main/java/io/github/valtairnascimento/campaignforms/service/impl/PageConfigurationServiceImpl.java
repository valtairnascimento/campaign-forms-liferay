package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.input.PageSettingsInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.base.PageConfigurationServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=PageConfiguration"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class PageConfigurationServiceImpl
	extends PageConfigurationServiceBaseImpl {
	@Override
	public PageBlock addPageBlock(
			long campaignId, String blockType, String configuration,
			boolean enabled)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.addPageBlock(
			_getDraftPageConfigurationId(campaignId), blockType, configuration,
			enabled);
	}

	@Override
	public PageConfiguration applyPageTemplate(
			long campaignId, String templateKey)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		return pageConfigurationLocalService.applyPageTemplate(
			_getDraftPageConfigurationId(campaignId), templateKey);
	}

	@Override
	public PageBlock deletePageBlock(long pageBlockId) throws PortalException {
		_checkPageBlock(pageBlockId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.deletePageBlock(pageBlockId);
	}

	@Override
	public PageBlock duplicatePageBlock(long pageBlockId)
		throws PortalException {
		_checkPageBlock(pageBlockId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.duplicatePageBlock(pageBlockId);
	}

	@Override
	public PageConfiguration getDraftPageConfiguration(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW);

		return pageConfigurationLocalService.getPageConfiguration(
			campaignId, PageStage.DRAFT);
	}

	@Override
	public PageBlock getPageBlock(long pageBlockId) throws PortalException {
		_checkPageBlock(pageBlockId, CampaignActionKeys.VIEW);

		return _pageBlockLocalService.getPageBlock(pageBlockId);
	}

	@Override
	public List<PageBlock> getPageBlocks(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW);

		return _pageBlockLocalService.getPageBlocks(
			_getDraftPageConfigurationId(campaignId));
	}

	@Override
	public List<PageBlock> movePageBlock(long pageBlockId, int position)
		throws PortalException {
		_checkPageBlock(pageBlockId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.movePageBlock(pageBlockId, position);
	}

	@Override
	public List<PageBlock> reorderPageBlocks(
			long campaignId, long[] pageBlockIds)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.reorderPageBlocks(
			_getDraftPageConfigurationId(campaignId), pageBlockIds);
	}

	@Override
	public PageBlock updatePageBlock(
			long pageBlockId, String configuration, boolean enabled)
		throws PortalException {
		_checkPageBlock(pageBlockId, CampaignActionKeys.UPDATE);

		return _pageBlockLocalService.updatePageBlock(
			pageBlockId, configuration, enabled);
	}

	@Override
	public PageConfiguration updatePageSettings(
			long campaignId, PageSettingsInput pageSettingsInput)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.UPDATE);

		return pageConfigurationLocalService.updatePageSettings(
			_getDraftPageConfigurationId(campaignId), pageSettingsInput);
	}

	private void _check(long campaignId, String actionId)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, actionId);
	}

	private void _checkPageBlock(long pageBlockId, String actionId)
		throws PortalException {
		PageBlock pageBlock = _pageBlockLocalService.getPageBlock(pageBlockId);

		PageConfiguration pageConfiguration =
			pageConfigurationLocalService.getPageConfiguration(
				pageBlock.getPageConfigurationId());

		_check(pageConfiguration.getCampaignId(), actionId);
	}

	private long _getDraftPageConfigurationId(long campaignId)
		throws PortalException {
		PageConfiguration pageConfiguration =
			pageConfigurationLocalService.getPageConfiguration(
				campaignId, PageStage.DRAFT);

		return pageConfiguration.getPageConfigurationId();
	}

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;
}
