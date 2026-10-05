package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.exception.PageSettingsException;
import io.github.valtairnascimento.campaignforms.input.PageSettingsInput;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.page.BlockContextUtil;
import io.github.valtairnascimento.campaignforms.internal.page.BlockImageValidator;
import io.github.valtairnascimento.campaignforms.internal.util.DraftLockUtil;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;
import io.github.valtairnascimento.campaignforms.page.PageTemplateRegistry;
import io.github.valtairnascimento.campaignforms.page.PageThemes;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.base.PageConfigurationLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.PageConfiguration",
	service = AopService.class
)
public class PageConfigurationLocalServiceImpl
	extends PageConfigurationLocalServiceBaseImpl {
	@Override
	public PageConfiguration addPageConfiguration(
			long userId, long campaignId, PageStage stage, String templateKey)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		if (pageConfigurationPersistence.fetchByC_S(campaignId, stage.name()) !=
				null) {
			throw new PageSettingsException("page-stage-already-exists");
		}

		PageConfiguration pageConfiguration = _createPageConfiguration(
			userId, campaign, stage);

		pageConfiguration.setTemplateKey(templateKey);
		pageConfiguration.setSettings("{}");

		return pageConfigurationPersistence.update(pageConfiguration);
	}

	@Override
	public PageConfiguration applyPageTemplate(
			long pageConfigurationId, String templateKey)
		throws PortalException {
		PageConfiguration pageConfiguration =
			pageConfigurationPersistence.findByPrimaryKey(pageConfigurationId);

		DraftLockUtil.checkDraft(pageConfiguration);

		PageTemplateDefinition pageTemplateDefinition =
			_pageTemplateRegistry.getPageTemplateDefinition(templateKey);

		if (pageTemplateDefinition == null) {
			throw new PageSettingsException("page-template-is-invalid");
		}

		Campaign campaign = campaignPersistence.findByPrimaryKey(
			pageConfiguration.getCampaignId());

		_pageBlockLocalService.deletePageBlocks(pageConfigurationId);

		for (PageTemplateDefinition.TemplateBlock templateBlock :
				pageTemplateDefinition.getBlocks(
					BlockContextUtil.create(campaign))) {
			_pageBlockLocalService.addPageBlock(
				pageConfigurationId, templateBlock.getBlockType(),
				templateBlock.getConfiguration(
				).toString(),
				true);
		}

		pageConfigurationPersistence.clearCache(
			Collections.<Serializable>singleton(pageConfigurationId));

		pageConfiguration = pageConfigurationPersistence.findByPrimaryKey(
			pageConfigurationId);

		pageConfiguration.setModifiedDate(new Date());
		pageConfiguration.setTemplateKey(templateKey);

		return pageConfigurationPersistence.update(pageConfiguration);
	}

	@Override
	public PageConfiguration copyPageConfiguration(
			long userId, long sourcePageConfigurationId, long targetCampaignId,
			PageStage targetStage)
		throws PortalException {
		PageConfiguration sourcePageConfiguration =
			pageConfigurationPersistence.findByPrimaryKey(
				sourcePageConfigurationId);

		PageConfiguration targetPageConfiguration =
			pageConfigurationPersistence.fetchByC_S(
				targetCampaignId, targetStage.name());

		if (targetPageConfiguration == null) {
			targetPageConfiguration = _createPageConfiguration(
				userId, campaignPersistence.findByPrimaryKey(targetCampaignId),
				targetStage);
		}
		else {
			_pageBlockLocalService.deletePageBlocks(
				targetPageConfiguration.getPageConfigurationId());

			targetPageConfiguration.setModifiedDate(new Date());
		}

		targetPageConfiguration.setTemplateKey(
			sourcePageConfiguration.getTemplateKey());
		targetPageConfiguration.setThemeKey(
			sourcePageConfiguration.getThemeKey());
		targetPageConfiguration.setCssClass(
			sourcePageConfiguration.getCssClass());
		targetPageConfiguration.setSeoTitle(
			sourcePageConfiguration.getSeoTitle());
		targetPageConfiguration.setSeoDescription(
			sourcePageConfiguration.getSeoDescription());
		targetPageConfiguration.setCanonicalUrl(
			sourcePageConfiguration.getCanonicalUrl());
		targetPageConfiguration.setNoIndex(
			sourcePageConfiguration.isNoIndex());
		targetPageConfiguration.setOgTitle(
			sourcePageConfiguration.getOgTitle());
		targetPageConfiguration.setOgDescription(
			sourcePageConfiguration.getOgDescription());
		targetPageConfiguration.setOgImageFileEntryId(
			sourcePageConfiguration.getOgImageFileEntryId());
		targetPageConfiguration.setSettings(
			sourcePageConfiguration.getSettings());

		targetPageConfiguration = pageConfigurationPersistence.update(
			targetPageConfiguration);

		_pageBlockLocalService.copyPageBlocks(
			sourcePageConfigurationId, targetPageConfiguration);

		return targetPageConfiguration;
	}

	@Override
	public void deleteCampaignPageConfigurations(long campaignId) {
		for (PageConfiguration pageConfiguration :
				pageConfigurationPersistence.findByCampaignId(campaignId)) {
			_pageBlockLocalService.deletePageBlocks(
				pageConfiguration.getPageConfigurationId());

			pageConfigurationPersistence.remove(pageConfiguration);
		}
	}

	@Override
	public PageConfiguration fetchPageConfiguration(
		long campaignId, PageStage stage) {
		return pageConfigurationPersistence.fetchByC_S(
			campaignId, stage.name());
	}

	@Override
	public List<PageConfiguration> getCampaignPageConfigurations(
		long campaignId) {
		return pageConfigurationPersistence.findByCampaignId(campaignId);
	}

	@Override
	public PageConfiguration getPageConfiguration(
			long campaignId, PageStage stage)
		throws PortalException {
		return pageConfigurationPersistence.findByC_S(
			campaignId, stage.name());
	}

	@Override
	public PageConfiguration updatePageSettings(
			long pageConfigurationId, PageSettingsInput pageSettingsInput)
		throws PortalException {
		PageConfiguration pageConfiguration =
			pageConfigurationPersistence.findByPrimaryKey(pageConfigurationId);

		DraftLockUtil.checkDraft(pageConfiguration);

		_validate(pageSettingsInput);

		long ogImageFileEntryId = pageSettingsInput.getOgImageFileEntryId();

		if (ogImageFileEntryId > 0) {
			String problem = _blockImageValidator.getProblem(
				ogImageFileEntryId, pageConfiguration.getGroupId());

			if (problem != null) {
				throw new PageSettingsException(
					problem.endsWith("-not-public") ?
						"page-og-image-is-not-public" : "page-og-image-is-invalid");
			}
		}

		pageConfiguration.setModifiedDate(new Date());
		pageConfiguration.setTemplateKey(pageSettingsInput.getTemplateKey());
		pageConfiguration.setThemeKey(pageSettingsInput.getThemeKey());
		pageConfiguration.setCssClass(pageSettingsInput.getCssClass());
		pageConfiguration.setSeoTitle(pageSettingsInput.getSeoTitle());
		pageConfiguration.setSeoDescription(
			pageSettingsInput.getSeoDescription());
		pageConfiguration.setCanonicalUrl(pageSettingsInput.getCanonicalUrl());
		pageConfiguration.setNoIndex(pageSettingsInput.isNoIndex());
		pageConfiguration.setOgTitle(pageSettingsInput.getOgTitle());
		pageConfiguration.setOgDescription(
			pageSettingsInput.getOgDescription());
		pageConfiguration.setOgImageFileEntryId(
			pageSettingsInput.getOgImageFileEntryId());

		try {
			pageConfiguration.setSettings(
				_jsonSettingsHelper.normalizeObject(
					pageSettingsInput.getSettings()));
		}
		catch (JSONException jsonException) {
			throw new PageSettingsException(
				"page-settings-are-invalid", jsonException);
		}

		return pageConfigurationPersistence.update(pageConfiguration);
	}

	private void _checkKey(String key, String messageKey)
		throws PageSettingsException {
		if (Validator.isNotNull(key) && !CampaignValidator.isValidKey(key)) {
			throw new PageSettingsException(messageKey);
		}
	}

	private void _checkLength(String value, int maxLength, String messageKey)
		throws PageSettingsException {
		if ((value != null) && (value.length() > maxLength)) {
			throw new PageSettingsException(messageKey);
		}
	}

	private PageConfiguration _createPageConfiguration(
			long userId, Campaign campaign, PageStage stage)
		throws PortalException {
		User user = userLocalService.getUser(userId);

		PageConfiguration pageConfiguration =
			pageConfigurationPersistence.create(
				counterLocalService.increment());

		pageConfiguration.setGroupId(campaign.getGroupId());
		pageConfiguration.setCompanyId(campaign.getCompanyId());
		pageConfiguration.setUserId(userId);
		pageConfiguration.setUserName(user.getFullName());

		Date now = new Date();

		pageConfiguration.setCreateDate(now);
		pageConfiguration.setModifiedDate(now);

		pageConfiguration.setCampaignId(campaign.getCampaignId());
		pageConfiguration.setStage(stage.name());

		return pageConfiguration;
	}

	private void _validate(PageSettingsInput pageSettingsInput)
		throws PageSettingsException {
		_checkKey(
			pageSettingsInput.getTemplateKey(), "page-template-is-invalid");
		if (!PageThemes.isValid(pageSettingsInput.getThemeKey())) {
			throw new PageSettingsException("page-theme-is-invalid");
		}

		_checkKey(
			pageSettingsInput.getCssClass(), "page-css-class-is-invalid");

		_checkLength(
			pageSettingsInput.getSeoTitle(), 255, "seo-title-is-too-long");
		_checkLength(
			pageSettingsInput.getSeoDescription(), 1000,
			"seo-description-is-too-long");
		_checkLength(
			pageSettingsInput.getOgTitle(), 255, "og-title-is-too-long");
		_checkLength(
			pageSettingsInput.getOgDescription(), 1000,
			"og-description-is-too-long");

		String canonicalUrl = pageSettingsInput.getCanonicalUrl();

		if (Validator.isNotNull(canonicalUrl) &&
			(!StringUtil.startsWith(canonicalUrl, "https://") ||
			 !Validator.isUrl(canonicalUrl) ||
			 (canonicalUrl.length() > 2048))) {
			throw new PageSettingsException("canonical-url-is-invalid");
		}
	}

	@Reference
	private BlockImageValidator _blockImageValidator;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;

	@Reference
	private PageTemplateRegistry _pageTemplateRegistry;
}
