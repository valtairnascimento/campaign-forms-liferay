package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.exception.PageTemplateConfigurationException;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.PageTemplate;
import io.github.valtairnascimento.campaignforms.service.base.PageTemplateLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.PageTemplate",
	service = AopService.class
)
public class PageTemplateLocalServiceImpl
	extends PageTemplateLocalServiceBaseImpl {
	@Override
	public PageTemplate addPageTemplate(
			long userId, long groupId, String templateKey, String name,
			String description, String blocks, boolean systemTemplate,
			ServiceContext serviceContext)
		throws PortalException {
		Group group = _groupLocalService.getGroup(groupId);

		if (!CampaignValidator.isValidKey(templateKey)) {
			throw new PageTemplateConfigurationException(
				"template-key-is-invalid");
		}

		if (pageTemplatePersistence.fetchByG_TK(groupId, templateKey) !=
				null) {
			throw new PageTemplateConfigurationException(
				"template-key-must-be-unique");
		}

		_validateName(name);

		User user = userLocalService.getUser(userId);

		PageTemplate pageTemplate = pageTemplatePersistence.create(
			counterLocalService.increment());

		pageTemplate.setUuid(serviceContext.getUuid());
		pageTemplate.setGroupId(groupId);
		pageTemplate.setCompanyId(group.getCompanyId());
		pageTemplate.setUserId(userId);
		pageTemplate.setUserName(user.getFullName());

		Date now = new Date();

		pageTemplate.setCreateDate(now);
		pageTemplate.setModifiedDate(now);

		pageTemplate.setTemplateKey(templateKey);
		pageTemplate.setName(name);
		pageTemplate.setDescription(description);
		pageTemplate.setSystemTemplate(systemTemplate);
		pageTemplate.setBlocks(_normalizeBlocks(blocks));

		return pageTemplatePersistence.update(pageTemplate);
	}

	@Override
	public PageTemplate deletePageTemplate(long pageTemplateId)
		throws PortalException {
		PageTemplate pageTemplate = pageTemplatePersistence.findByPrimaryKey(
			pageTemplateId);

		_checkNotSystem(pageTemplate);

		return pageTemplatePersistence.remove(pageTemplate);
	}

	@Override
	public PageTemplate fetchPageTemplate(long groupId, String templateKey) {
		return pageTemplatePersistence.fetchByG_TK(groupId, templateKey);
	}

	@Override
	public List<PageTemplate> getGroupPageTemplates(long groupId) {
		return pageTemplatePersistence.findByGroupId(groupId);
	}

	@Override
	public PageTemplate updatePageTemplate(
			long pageTemplateId, String name, String description, String blocks)
		throws PortalException {
		PageTemplate pageTemplate = pageTemplatePersistence.findByPrimaryKey(
			pageTemplateId);

		_checkNotSystem(pageTemplate);

		_validateName(name);

		pageTemplate.setModifiedDate(new Date());
		pageTemplate.setName(name);
		pageTemplate.setDescription(description);
		pageTemplate.setBlocks(_normalizeBlocks(blocks));

		return pageTemplatePersistence.update(pageTemplate);
	}

	private void _checkNotSystem(PageTemplate pageTemplate)
		throws PageTemplateConfigurationException {
		if (pageTemplate.isSystemTemplate()) {
			throw new PageTemplateConfigurationException(
				"system-template-cannot-be-changed");
		}
	}

	private String _normalizeBlocks(String blocks)
		throws PageTemplateConfigurationException {
		try {
			return _jsonSettingsHelper.normalizeArray(blocks);
		}
		catch (JSONException jsonException) {
			throw new PageTemplateConfigurationException(
				"template-blocks-are-invalid", jsonException);
		}
	}

	private void _validateName(String name)
		throws PageTemplateConfigurationException {
		if (CampaignValidator.isBlank(name)) {
			throw new PageTemplateConfigurationException(
				"template-name-is-required");
		}

		if (name.length() > CampaignValidator.MAX_NAME_LENGTH) {
			throw new PageTemplateConfigurationException(
				"template-name-is-too-long");
		}
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
