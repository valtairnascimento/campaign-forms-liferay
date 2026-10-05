package io.github.valtairnascimento.campaignforms.internal.util;

import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.exception.FormVersionLockedException;
import io.github.valtairnascimento.campaignforms.exception.PageSettingsException;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;

public class DraftLockUtil {
	public static void checkDraft(FormVersion formVersion)
		throws FormVersionLockedException {
		if (!FormVersionStatus.DRAFT.matches(formVersion.getStatus())) {
			throw new FormVersionLockedException(
				"published-form-version-cannot-be-changed");
		}
	}

	public static void checkDraft(PageConfiguration pageConfiguration)
		throws PageSettingsException {
		if (!PageStage.DRAFT.matches(pageConfiguration.getStage())) {
			throw new PageSettingsException(
				"published-page-cannot-be-changed");
		}
	}

	private DraftLockUtil() {
	}

}
