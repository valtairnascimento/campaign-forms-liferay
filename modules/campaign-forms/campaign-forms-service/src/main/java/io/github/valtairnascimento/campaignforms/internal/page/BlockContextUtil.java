package io.github.valtairnascimento.campaignforms.internal.page;

import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.page.BlockContext;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

public class BlockContextUtil {
	public static BlockContext create(Campaign campaign) {
		String languageId = campaign.getDefaultLanguageId();

		return new BlockContext(
			campaign.getCompanyId(), campaign.getGroupId(),
			Validator.isNull(languageId) ? LocaleUtil.getSiteDefault() :
				LocaleUtil.fromLanguageId(languageId),
			campaign.getName());
	}

	private BlockContextUtil() {
	}

}
