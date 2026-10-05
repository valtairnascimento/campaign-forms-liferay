package io.github.valtairnascimento.campaignforms.web.internal.portlet.route;

import io.github.valtairnascimento.campaignforms.web.constants.CampaignPortletKeys;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
		"javax.portlet.name=" + CampaignPortletKeys.CAMPAIGN_RENDERER
	},
	service = FriendlyURLMapper.class
)
public class CampaignRendererFriendlyURLMapper
	extends DefaultFriendlyURLMapper {
	public static final String MAPPING = "campaign";

	@Override
	public String getMapping() {
		return MAPPING;
	}

}
