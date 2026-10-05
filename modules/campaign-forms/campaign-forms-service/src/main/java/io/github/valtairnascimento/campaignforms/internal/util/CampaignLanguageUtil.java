package io.github.valtairnascimento.campaignforms.internal.util;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public class CampaignLanguageUtil {
	public static String get(Locale locale, String key) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, CampaignConstants.class);

		return LanguageUtil.get(resourceBundle, key);
	}

	public static String fetch(Locale locale, String key) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, CampaignConstants.class);

		if (!resourceBundle.containsKey(key)) {
			return null;
		}

		return resourceBundle.getString(key);
	}

	private CampaignLanguageUtil() {
	}

}
