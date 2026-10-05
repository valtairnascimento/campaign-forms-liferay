package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PageBlock;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PageConfiguration;
import io.github.valtairnascimento.campaignforms.input.PageSettingsInput;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PageDTOConverter.class)
public class PageDTOConverter {
	public PageBlock toDTO(
		io.github.valtairnascimento.campaignforms.model.PageBlock pageBlock) {
		PageBlock dto = new PageBlock();

		dto.setBlockKey(pageBlock.getBlockKey());
		dto.setBlockType(pageBlock.getBlockType());
		dto.setConfiguration(
			_jsonMapHelper.toMap(pageBlock.getConfiguration()));
		dto.setEnabled(pageBlock.isEnabled());
		dto.setId(pageBlock.getPageBlockId());
		dto.setPosition(pageBlock.getPosition());
		dto.setSchemaVersion(pageBlock.getSchemaVersion());

		return dto;
	}

	public PageConfiguration toDTO(
		io.github.valtairnascimento.campaignforms.model.PageConfiguration
			pageConfiguration) {
		PageConfiguration dto = new PageConfiguration();

		dto.setCanonicalUrl(_nullIfEmpty(pageConfiguration.getCanonicalUrl()));
		dto.setCssClass(_nullIfEmpty(pageConfiguration.getCssClass()));
		dto.setId(pageConfiguration.getPageConfigurationId());
		dto.setNoIndex(pageConfiguration.isNoIndex());
		dto.setOgDescription(
			_nullIfEmpty(pageConfiguration.getOgDescription()));
		dto.setOgImageFileEntryId(pageConfiguration.getOgImageFileEntryId());
		dto.setOgTitle(_nullIfEmpty(pageConfiguration.getOgTitle()));
		dto.setPageBlocks(
			toDTOs(
				_pageBlockLocalService.getPageBlocks(
					pageConfiguration.getPageConfigurationId())));
		dto.setSeoDescription(
			_nullIfEmpty(pageConfiguration.getSeoDescription()));
		dto.setSeoTitle(_nullIfEmpty(pageConfiguration.getSeoTitle()));
		dto.setSettings(_jsonMapHelper.toMap(pageConfiguration.getSettings()));
		dto.setTemplateKey(_nullIfEmpty(pageConfiguration.getTemplateKey()));
		dto.setThemeKey(_nullIfEmpty(pageConfiguration.getThemeKey()));

		return dto;
	}

	public PageBlock[] toDTOs(
		List<io.github.valtairnascimento.campaignforms.model.PageBlock> pageBlocks) {
		return TransformUtil.transformToArray(
			pageBlocks, this::toDTO, PageBlock.class);
	}

	public String toConfigurationJSON(PageBlock pageBlock) {
		return _jsonMapHelper.toJSON(pageBlock.getConfiguration());
	}

	public PageSettingsInput toPageSettingsInput(
		PageConfiguration pageConfiguration) {
		return new PageSettingsInput(
		).setCanonicalUrl(
			pageConfiguration.getCanonicalUrl()
		).setCssClass(
			pageConfiguration.getCssClass()
		).setNoIndex(
			GetterUtil.getBoolean(pageConfiguration.getNoIndex())
		).setOgDescription(
			pageConfiguration.getOgDescription()
		).setOgImageFileEntryId(
			GetterUtil.getLong(pageConfiguration.getOgImageFileEntryId())
		).setOgTitle(
			pageConfiguration.getOgTitle()
		).setSeoDescription(
			pageConfiguration.getSeoDescription()
		).setSeoTitle(
			pageConfiguration.getSeoTitle()
		).setSettings(
			_jsonMapHelper.toJSON(pageConfiguration.getSettings())
		).setTemplateKey(
			pageConfiguration.getTemplateKey()
		).setThemeKey(
			pageConfiguration.getThemeKey()
		);
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	@Reference
	private JSONMapHelper _jsonMapHelper;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;
}
