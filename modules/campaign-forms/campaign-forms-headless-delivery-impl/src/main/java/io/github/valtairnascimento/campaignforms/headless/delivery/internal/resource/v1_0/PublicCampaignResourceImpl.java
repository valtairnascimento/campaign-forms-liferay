package io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicCampaign;
import io.github.valtairnascimento.campaignforms.headless.delivery.internal.dto.PublicCampaignDTOFactory;
import io.github.valtairnascimento.campaignforms.headless.delivery.resource.v1_0.PublicCampaignResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/public-campaign.properties",
	scope = ServiceScope.PROTOTYPE, service = PublicCampaignResource.class
)
public class PublicCampaignResourceImpl extends BasePublicCampaignResourceImpl {
	@Override
	public PublicCampaign getSiteCampaignBySlug(Long siteId, String slug)
		throws Exception {
		return _publicCampaignDTOFactory.toPublicCampaign(
			_publicCampaignDTOFactory.getPublishedSnapshot(siteId, slug),
			contextAcceptLanguage.getPreferredLocale());
	}

	@Reference
	private PublicCampaignDTOFactory _publicCampaignDTOFactory;
}
