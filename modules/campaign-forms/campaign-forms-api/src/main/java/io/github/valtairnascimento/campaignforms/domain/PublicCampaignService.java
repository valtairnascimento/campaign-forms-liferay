package io.github.valtairnascimento.campaignforms.domain;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Locale;

public interface PublicCampaignService {
	public String getClosedMessage(
		PublicCampaignSnapshot publicCampaignSnapshot, Locale locale);

	public PublicCampaignSnapshot getPreviewSnapshot(
			long groupId, String slug, PermissionChecker permissionChecker)
		throws PortalException;

	public PublicCampaignSnapshot getPublishedSnapshot(
			long groupId, String slug)
		throws PortalException;
}
