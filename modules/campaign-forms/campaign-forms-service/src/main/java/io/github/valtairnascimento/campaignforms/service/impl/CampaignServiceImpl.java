package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.domain.CampaignDuplicationService;
import io.github.valtairnascimento.campaignforms.domain.CampaignModeService;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.base.CampaignServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=Campaign"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class CampaignServiceImpl extends CampaignServiceBaseImpl {
	@Override
	public Campaign addCampaign(
			long groupId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException {
		_portletResourcePermission.check(
			getPermissionChecker(), groupId, CampaignActionKeys.ADD_CAMPAIGN);

		return campaignLocalService.addCampaign(
			getUserId(), groupId, campaignInput, serviceContext);
	}

	@Override
	public Campaign archiveCampaign(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.PUBLISH);

		return _campaignPublicationService.archive(getUserId(), campaignId);
	}

	@Override
	public Campaign convertToCustomPage(long campaignId)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.UPDATE);

		return _campaignModeService.convertToCustomPage(
			getUserId(), campaignId);
	}

	@Override
	public Campaign deleteCampaign(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.DELETE);

		return campaignLocalService.deleteCampaign(campaignId);
	}

	@Override
	public Campaign duplicateCampaign(
			long sourceCampaignId, DuplicationRequest duplicationRequest,
			ServiceContext serviceContext)
		throws PortalException {
		Campaign sourceCampaign = campaignLocalService.getCampaign(
			sourceCampaignId);

		_campaignModelResourcePermission.check(
			getPermissionChecker(), sourceCampaign, CampaignActionKeys.VIEW);

		long targetGroupId = duplicationRequest.getTargetGroupId();

		if (targetGroupId <= 0) {
			targetGroupId = sourceCampaign.getGroupId();
		}

		_portletResourcePermission.check(
			getPermissionChecker(), targetGroupId,
			CampaignActionKeys.ADD_CAMPAIGN);

		return _campaignDuplicationService.duplicate(
			getUserId(), sourceCampaignId, duplicationRequest, serviceContext);
	}

	@Override
	public Campaign getCampaign(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.VIEW);

		return campaignLocalService.getCampaign(campaignId);
	}

	@Override
	public Campaign getCampaignByExternalReferenceCode(
			long groupId, String externalReferenceCode)
		throws PortalException {
		Campaign campaign =
			campaignLocalService.getCampaignByExternalReferenceCode(
				externalReferenceCode, groupId);

		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaign, CampaignActionKeys.VIEW);

		return campaign;
	}

	@Override
	public Campaign getCampaignBySlug(long groupId, String slug)
		throws PortalException {
		Campaign campaign = campaignLocalService.getCampaignBySlug(
			groupId, slug);

		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaign, CampaignActionKeys.VIEW);

		return campaign;
	}

	@Override
	public List<Campaign> getGroupCampaigns(
		long groupId, int start, int end,
		OrderByComparator<Campaign> orderByComparator) {
		return campaignPersistence.filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupCampaignsCount(long groupId) {
		return campaignPersistence.filterCountByGroupId(groupId);
	}

	@Override
	public List<PublicationIssue> getPublicationIssues(long campaignId)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.VIEW);

		return _campaignPublicationService.getPublicationIssues(campaignId);
	}

	@Override
	public Campaign publishCampaign(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.PUBLISH);

		return _campaignPublicationService.publish(getUserId(), campaignId);
	}

	@Override
	public Campaign reopenCampaign(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.PUBLISH);

		return _campaignPublicationService.reopen(getUserId(), campaignId);
	}

	@Override
	public Campaign updateCampaign(
			long campaignId, CampaignInput campaignInput,
			ServiceContext serviceContext)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, CampaignActionKeys.UPDATE);

		return campaignLocalService.updateCampaign(
			getUserId(), campaignId, campaignInput, serviceContext);
	}

	@Reference
	private CampaignDuplicationService _campaignDuplicationService;

	@Reference
	private CampaignModeService _campaignModeService;

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private CampaignPublicationService _campaignPublicationService;

	@Reference(
		target = "(resource.name=" + CampaignConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;
}
