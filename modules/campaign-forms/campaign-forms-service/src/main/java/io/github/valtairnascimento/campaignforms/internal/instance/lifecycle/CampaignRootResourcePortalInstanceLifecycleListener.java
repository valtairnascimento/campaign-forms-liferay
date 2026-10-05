package io.github.valtairnascimento.campaignforms.internal.instance.lifecycle;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;

import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PortalInstanceLifecycleListener.class)
public class CampaignRootResourcePortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {
	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		int count = _resourcePermissionLocalService.getResourcePermissionsCount(
			company.getCompanyId(), CampaignConstants.RESOURCE_NAME,
			ResourceConstants.SCOPE_INDIVIDUAL,
			CampaignConstants.RESOURCE_NAME);

		if (count > 0) {
			return;
		}

		_resourceLocalService.addResources(
			company.getCompanyId(), 0, 0, CampaignConstants.RESOURCE_NAME,
			CampaignConstants.RESOURCE_NAME, false, false, false);
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=" +
			"io.github.valtairnascimento.campaignforms.service)" +
				"(release.schema.version>=1.0.0))"
	)
	private Release _release;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;
}
