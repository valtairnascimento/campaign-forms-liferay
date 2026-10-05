package io.github.valtairnascimento.campaignforms.web.internal.portlet;

import io.github.valtairnascimento.campaignforms.web.constants.CampaignPortletKeys;
import io.github.valtairnascimento.campaignforms.web.internal.display.CampaignAdminContextBuilder;
import io.github.valtairnascimento.campaignforms.web.internal.template.FreeMarkerTemplateProcessor;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=campaign-forms-admin-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/admin/campaign-admin.css",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Captações",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.name=" + CampaignPortletKeys.CAMPAIGN_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class CampaignAdminPortlet extends MVCPortlet {
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		renderResponse.setContentType("text/html");

		_freeMarkerTemplateProcessor.process(
			_TEMPLATE_PATH,
			_campaignAdminContextBuilder.build(renderRequest, renderResponse),
			renderResponse.getWriter());
	}

	private static final String _TEMPLATE_PATH = "/templates/admin.ftl";

	@Reference
	private CampaignAdminContextBuilder _campaignAdminContextBuilder;

	@Reference
	private FreeMarkerTemplateProcessor _freeMarkerTemplateProcessor;
}
