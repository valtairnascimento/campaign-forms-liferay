package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.BlockType;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.BlockTypeResource;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;
import io.github.valtairnascimento.campaignforms.service.CampaignService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/block-type.properties",
	scope = ServiceScope.PROTOTYPE, service = BlockTypeResource.class
)
public class BlockTypeResourceImpl extends BaseBlockTypeResourceImpl {
	@Override
	public Page<BlockType> getCampaignBlockTypesPage(Long campaignId)
		throws Exception {
		Campaign campaign = _campaignService.getCampaign(campaignId);

		String languageId = campaign.getDefaultLanguageId();

		BlockContext blockContext = new BlockContext(
			campaign.getCompanyId(), campaign.getGroupId(),
			Validator.isNull(languageId) ? LocaleUtil.getSiteDefault() :
				LocaleUtil.fromLanguageId(languageId),
			campaign.getName());

		Locale locale = contextAcceptLanguage.getPreferredLocale();

		return Page.of(
			TransformUtil.transform(
				_blockTypeRegistry.getBlockTypes(),
				blockType -> {
					BlockType dto = new BlockType();

					dto.setDefaultConfiguration(
						blockType.getDefaultConfiguration(
							blockContext
						).toMap());
					dto.setDescription(blockType.getDescription(locale));
					dto.setKey(blockType.getKey());
					dto.setLabel(blockType.getLabel(locale));
					dto.setSchemaVersion(blockType.getSchemaVersion());
					dto.setSingleton(blockType.isSingleton());

					return dto;
				}));
	}

	@Reference
	private BlockTypeRegistry _blockTypeRegistry;

	@Reference
	private CampaignService _campaignService;
}
