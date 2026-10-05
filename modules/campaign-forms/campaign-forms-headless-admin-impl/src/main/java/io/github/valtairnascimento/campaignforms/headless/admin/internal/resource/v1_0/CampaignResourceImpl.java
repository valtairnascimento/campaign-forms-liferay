package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Campaign;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.CampaignDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.CampaignResource;
import io.github.valtairnascimento.campaignforms.service.CampaignService;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/campaign.properties",
	scope = ServiceScope.PROTOTYPE, service = CampaignResource.class
)
public class CampaignResourceImpl extends BaseCampaignResourceImpl {
	@Override
	public Response deleteCampaign(Long campaignId) throws Exception {
		_campaignService.deleteCampaign(campaignId);

		return Response.noContent(
		).build();
	}

	@Override
	public Campaign getCampaign(Long campaignId) throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.getCampaign(campaignId));
	}

	@Override
	public Campaign getSiteCampaignByExternalReferenceCode(
			Long siteId, String externalReferenceCode)
		throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.getCampaignByExternalReferenceCode(
				siteId, externalReferenceCode));
	}

	@Override
	public Page<Campaign> getSiteCampaignsPage(
			Long siteId, Pagination pagination)
		throws Exception {
		return Page.of(
			_campaignDTOConverter.toDTOs(
				_campaignService.getGroupCampaigns(
					siteId, pagination.getStartPosition(),
					pagination.getEndPosition(), null)),
			pagination, _campaignService.getGroupCampaignsCount(siteId));
	}

	@Override
	public Campaign postCampaignArchive(Long campaignId) throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.archiveCampaign(campaignId));
	}

	@Override
	public Campaign postCampaignConvertToCustomPage(Long campaignId)
		throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.convertToCustomPage(campaignId));
	}

	@Override
	public Campaign postCampaignDuplicate(
			Long campaignId, DuplicationRequest duplicationRequest)
		throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.duplicateCampaign(
				campaignId,
				_campaignDTOConverter.toDuplicationRequest(duplicationRequest),
				_getServiceContext()));
	}

	@Override
	public Campaign postCampaignPublish(Long campaignId) throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.publishCampaign(campaignId));
	}

	@Override
	public Campaign postCampaignReopen(Long campaignId) throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.reopenCampaign(campaignId));
	}

	@Override
	public Campaign postSiteCampaign(Long siteId, Campaign campaign)
		throws Exception {
		return _campaignDTOConverter.toDTO(
			_campaignService.addCampaign(
				siteId,
				_campaignDTOConverter.toCampaignInput(
					campaign,
					(campaign.getMode() == null) ? CampaignMode.QUICK : null),
				_getServiceContext()));
	}

	@Override
	public Campaign putCampaign(Long campaignId, Campaign campaign)
		throws Exception {
		io.github.valtairnascimento.campaignforms.model.Campaign existingCampaign =
			_campaignService.getCampaign(campaignId);

		return _campaignDTOConverter.toDTO(
			_campaignService.updateCampaign(
				campaignId,
				_campaignDTOConverter.toCampaignInput(
					campaign, CampaignMode.parse(existingCampaign.getMode())),
				_getServiceContext()));
	}

	private ServiceContext _getServiceContext() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(contextCompany.getCompanyId());
		serviceContext.setUserId(contextUser.getUserId());

		return serviceContext;
	}

	@Reference
	private CampaignDTOConverter _campaignDTOConverter;

	@Reference
	private CampaignService _campaignService;
}
