package io.github.valtairnascimento.campaignforms.internal.security.permission.resource;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "resource.name=" + CampaignConstants.RESOURCE_NAME,
	service = PortletResourcePermission.class
)
public class CampaignPortletResourcePermission
	implements PortletResourcePermission {
	@Override
	public void check(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PrincipalException {
		check(permissionChecker, group.getGroupId(), actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PrincipalException {
		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CampaignConstants.RESOURCE_NAME, groupId,
				actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, Group group, String actionId) {
		return contains(permissionChecker, group.getGroupId(), actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {
		return permissionChecker.hasPermission(
			groupId, CampaignConstants.RESOURCE_NAME, groupId, actionId);
	}

	@Override
	public String getResourceName() {
		return CampaignConstants.RESOURCE_NAME;
	}

}
