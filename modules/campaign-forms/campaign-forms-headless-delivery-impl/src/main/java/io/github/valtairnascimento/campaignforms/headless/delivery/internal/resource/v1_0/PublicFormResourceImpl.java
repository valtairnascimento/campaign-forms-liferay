package io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicForm;
import io.github.valtairnascimento.campaignforms.headless.delivery.internal.dto.PublicCampaignDTOFactory;
import io.github.valtairnascimento.campaignforms.headless.delivery.resource.v1_0.PublicFormResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/public-form.properties",
	scope = ServiceScope.PROTOTYPE, service = PublicFormResource.class
)
public class PublicFormResourceImpl extends BasePublicFormResourceImpl {
	@Override
	public PublicForm getSiteCampaignBySlugForm(Long siteId, String slug)
		throws Exception {
		return _publicCampaignDTOFactory.toPublicForm(
			_publicCampaignDTOFactory.getPublishedSnapshot(siteId, slug));
	}

	@Reference
	private PublicCampaignDTOFactory _publicCampaignDTOFactory;
}
