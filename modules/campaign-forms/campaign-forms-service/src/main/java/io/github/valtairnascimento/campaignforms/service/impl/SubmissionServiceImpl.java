package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.base.SubmissionServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=Submission"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SubmissionServiceImpl extends SubmissionServiceBaseImpl {
	@Override
	public Submission deleteSubmission(long submissionId)
		throws PortalException {
		Submission submission = submissionLocalService.getSubmission(
			submissionId);

		_check(
			submission.getCampaignId(), CampaignActionKeys.DELETE_SUBMISSIONS);

		submission = submissionLocalService.deleteSubmission(submissionId);

		_auditEntryLocalService.addAuditEntry(
			submission.getCompanyId(), submission.getGroupId(), getUserId(),
			submission.getCampaignId(), AuditAction.SUBMISSION_DELETED,
			Submission.class.getName(), submissionId,
			_jsonFactory.createJSONObject(
			).put(
				"formVersionId", submission.getFormVersionId()
			));

		return submission;
	}

	@Override
	public List<Submission> getCampaignSubmissions(
			long campaignId, int start, int end)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW_SUBMISSIONS);

		return submissionLocalService.getCampaignSubmissions(
			campaignId, start, end);
	}

	@Override
	public int getCampaignSubmissionsCount(long campaignId)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.VIEW_SUBMISSIONS);

		return submissionLocalService.getCampaignSubmissionsCount(campaignId);
	}

	@Override
	public List<EventDelivery> getSubmissionEventDeliveries(long submissionId)
		throws PortalException {
		Submission submission = submissionLocalService.getSubmission(
			submissionId);

		_check(submission.getCampaignId(), CampaignActionKeys.VIEW_SUBMISSIONS);

		return eventDeliveryPersistence.findBySubmissionId(submissionId);
	}

	@Override
	public Submission getSubmission(long submissionId) throws PortalException {
		Submission submission = submissionLocalService.getSubmission(
			submissionId);

		_check(
			submission.getCampaignId(), CampaignActionKeys.VIEW_SUBMISSIONS);

		return submission;
	}

	@Override
	public void checkSubmissionsExport(long campaignId, String format)
		throws PortalException {
		_check(campaignId, CampaignActionKeys.EXPORT_SUBMISSIONS);

		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		_auditEntryLocalService.addAuditEntry(
			campaign.getCompanyId(), campaign.getGroupId(), getUserId(),
			campaignId, AuditAction.SUBMISSIONS_EXPORTED,
			Campaign.class.getName(), campaignId,
			_jsonFactory.createJSONObject(
			).put(
				"format", format
			));
	}

	private void _check(long campaignId, String actionId)
		throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId, actionId);
	}

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private JSONFactory _jsonFactory;
}
