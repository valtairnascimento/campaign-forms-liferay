package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PublicationIssue;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.PublicationIssueResource;
import io.github.valtairnascimento.campaignforms.service.CampaignService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/publication-issue.properties",
	scope = ServiceScope.PROTOTYPE, service = PublicationIssueResource.class
)
public class PublicationIssueResourceImpl
	extends BasePublicationIssueResourceImpl {
	@Override
	public Page<PublicationIssue> getCampaignPublicationIssuesPage(
			Long campaignId)
		throws Exception {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", contextAcceptLanguage.getPreferredLocale(),
			CampaignConstants.class);

		return Page.of(
			TransformUtil.transform(
				_campaignService.getPublicationIssues(campaignId),
				publicationIssue -> {
					PublicationIssue dto = new PublicationIssue();

					dto.setCode(publicationIssue.getCode());
					dto.setMessage(
						LanguageUtil.get(
							resourceBundle, publicationIssue.getCode()));

					if (Validator.isNotNull(publicationIssue.getTarget())) {
						dto.setTarget(publicationIssue.getTarget());
					}

					return dto;
				}));
	}

	@Reference
	private CampaignService _campaignService;
}
