package io.github.valtairnascimento.campaignforms.internal.security.permission.resource;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign",
	service = ModelResourcePermission.class
)
public class CampaignModelResourcePermission
	implements ModelResourcePermission<Campaign> {
	@Override
	public void check(
			PermissionChecker permissionChecker, Campaign campaign,
			String actionId)
		throws PortalException {
		if (!contains(permissionChecker, campaign, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Campaign.class.getName(),
				campaign.getCampaignId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long campaignId,
			String actionId)
		throws PortalException {
		check(
			permissionChecker, _campaignLocalService.getCampaign(campaignId),
			actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, Campaign campaign,
		String actionId) {
		if (permissionChecker.hasOwnerPermission(
				campaign.getCompanyId(), Campaign.class.getName(),
				campaign.getCampaignId(), campaign.getUserId(), actionId)) {
			return true;
		}

		return permissionChecker.hasPermission(
			campaign.getGroupId(), Campaign.class.getName(),
			campaign.getCampaignId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long campaignId,
			String actionId)
		throws PortalException {
		return contains(
			permissionChecker, _campaignLocalService.getCampaign(campaignId),
			actionId);
	}

	@Override
	public String getModelName() {
		return Campaign.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference(
		target = "(resource.name=" + CampaignConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;
}
