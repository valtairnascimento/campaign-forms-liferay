package io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicPage;
import io.github.valtairnascimento.campaignforms.headless.delivery.internal.dto.PublicCampaignDTOFactory;
import io.github.valtairnascimento.campaignforms.headless.delivery.resource.v1_0.PublicPageResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/public-page.properties",
	scope = ServiceScope.PROTOTYPE, service = PublicPageResource.class
)
public class PublicPageResourceImpl extends BasePublicPageResourceImpl {
	@Override
	public PublicPage getSiteCampaignBySlugPage(Long siteId, String slug)
		throws Exception {
		return _publicCampaignDTOFactory.toPublicPage(
			_publicCampaignDTOFactory.getPublishedSnapshot(siteId, slug));
	}

	@Reference
	private PublicCampaignDTOFactory _publicCampaignDTOFactory;
}
