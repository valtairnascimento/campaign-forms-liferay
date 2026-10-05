package io.github.valtairnascimento.campaignforms.internal.submission;

import io.github.valtairnascimento.campaignforms.domain.ConsentVersionUtil;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.model.FormVersion;

import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

public class ConsentTerms {
	public static String getText(FormVersion formVersion, Locale locale) {
		if (Validator.isNotNull(formVersion.getConsentText())) {
			return formVersion.getConsentText();
		}

		return CampaignLanguageUtil.get(locale, "consent-default-text");
	}

	public static String getURL(FormVersion formVersion) {
		return Validator.isNull(formVersion.getConsentURL()) ? null :
			formVersion.getConsentURL();
	}

	public static String getVersion(FormVersion formVersion) {
		return ConsentVersionUtil.getVersion(formVersion);
	}

	private ConsentTerms() {
	}

}
