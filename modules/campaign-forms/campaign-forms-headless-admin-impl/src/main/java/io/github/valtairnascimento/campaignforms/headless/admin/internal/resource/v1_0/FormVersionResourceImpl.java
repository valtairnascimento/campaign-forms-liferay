package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FormVersion;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.FormDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.FormVersionResource;
import io.github.valtairnascimento.campaignforms.service.FormService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/form-version.properties",
	scope = ServiceScope.PROTOTYPE, service = FormVersionResource.class
)
public class FormVersionResourceImpl extends BaseFormVersionResourceImpl {
	@Override
	public FormVersion getCampaignForm(Long campaignId) throws Exception {
		return _formDTOConverter.toDTO(
			_formService.getCampaignFormVersion(campaignId));
	}

	@Override
	public Page<FormVersion> getCampaignFormVersionsPage(Long campaignId)
		throws Exception {
		return Page.of(
			TransformUtil.transform(
				_formService.getCampaignFormVersions(campaignId),
				_formDTOConverter::toDTO));
	}

	@Override
	public FormVersion getFormVersion(Long formVersionId) throws Exception {
		return _formDTOConverter.toDTO(
			_formService.getFormVersion(formVersionId));
	}

	@Override
	public FormVersion postCampaignFormDraft(Long campaignId)
		throws Exception {
		return _formDTOConverter.toDTO(
			_formService.getOrCreateDraftFormVersion(campaignId));
	}

	@Override
	public FormVersion putCampaignForm(Long campaignId, FormVersion formVersion)
		throws Exception {
		return _formDTOConverter.toDTO(
			_formService.updateDraftFormVersion(
				campaignId, _formDTOConverter.toFormVersionInput(formVersion)));
	}

	@Reference
	private FormDTOConverter _formDTOConverter;

	@Reference
	private FormService _formService;
}
