package io.github.valtairnascimento.campaignforms.web.internal.portlet;

import io.github.valtairnascimento.campaignforms.web.constants.CampaignPortletKeys;
import io.github.valtairnascimento.campaignforms.web.internal.display.CampaignRenderer;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=campaign-forms-renderer",
		"com.liferay.portlet.display-category=category.campaign-forms",
		"com.liferay.portlet.footer-portlet-javascript=/js/campaign-form.js",
		"com.liferay.portlet.header-portlet-css=/css/campaign-form.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Captação",
		"javax.portlet.name=" + CampaignPortletKeys.CAMPAIGN_RENDERER,
		"javax.portlet.preferences=classpath:/META-INF/portlet-preferences/renderer-default-portlet-preferences.xml",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class CampaignRendererPortlet extends MVCPortlet {
	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		_campaignRenderer.render(
			renderRequest, renderResponse, _getSlug(renderRequest),
			ParamUtil.getBoolean(renderRequest, "preview"));
	}

	@Override
	public void renderHeaders(
			HeaderRequest headerRequest, HeaderResponse headerResponse)
		throws IOException, PortletException {
		_campaignRenderer.renderHeaders(
			headerRequest, headerResponse, _getSlug(headerRequest),
			ParamUtil.getBoolean(headerRequest, "preview"));
	}

	private String _getSlug(PortletRequest portletRequest) {
		String slug = ParamUtil.getString(portletRequest, "slug");

		if (slug.isEmpty()) {
			PortletPreferences portletPreferences =
				portletRequest.getPreferences();

			slug = portletPreferences.getValue("slug", "");
		}

		return slug;
	}

	@Reference
	private CampaignRenderer _campaignRenderer;
}
