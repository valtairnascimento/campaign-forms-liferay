package io.github.valtairnascimento.campaignforms.web.internal.application.list;

import io.github.valtairnascimento.campaignforms.web.constants.CampaignPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"panel.app.order:Integer=550",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
	},
	service = PanelApp.class
)
public class CampaignAdminPanelApp extends BasePanelApp {
	@Override
	public String getPortletId() {
		return CampaignPortletKeys.CAMPAIGN_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CampaignPortletKeys.CAMPAIGN_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}
