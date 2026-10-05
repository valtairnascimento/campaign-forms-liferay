package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PageConfiguration;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.TemplateSelection;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.PageDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.PageConfigurationResource;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/page-configuration.properties",
	scope = ServiceScope.PROTOTYPE, service = PageConfigurationResource.class
)
public class PageConfigurationResourceImpl
	extends BasePageConfigurationResourceImpl {
	@Override
	public PageConfiguration getCampaignPage(Long campaignId)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.getDraftPageConfiguration(campaignId));
	}

	@Override
	public PageConfiguration postCampaignPageApplyTemplate(
			Long campaignId, TemplateSelection templateSelection)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.applyPageTemplate(
				campaignId, templateSelection.getTemplateKey()));
	}

	@Override
	public PageConfiguration putCampaignPage(
			Long campaignId, PageConfiguration pageConfiguration)
		throws Exception {
		return _pageDTOConverter.toDTO(
			_pageConfigurationService.updatePageSettings(
				campaignId,
				_pageDTOConverter.toPageSettingsInput(pageConfiguration)));
	}

	@Reference
	private PageConfigurationService _pageConfigurationService;

	@Reference
	private PageDTOConverter _pageDTOConverter;
}
