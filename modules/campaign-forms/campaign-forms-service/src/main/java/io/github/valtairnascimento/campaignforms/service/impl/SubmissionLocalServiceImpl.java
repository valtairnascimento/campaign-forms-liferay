package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.constants.SubmissionStatus;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.CampaignQuotaLocalService;
import io.github.valtairnascimento.campaignforms.service.EventDeliveryLocalService;
import io.github.valtairnascimento.campaignforms.service.base.SubmissionLocalServiceBaseImpl;
import io.github.valtairnascimento.campaignforms.submission.SubmissionData;
import io.github.valtairnascimento.campaignforms.submission.SubmittedField;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.Submission",
	service = AopService.class
)
public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {
	@Override
	public Submission addSubmission(
			long campaignId, long formVersionId, SubmissionData submissionData)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);
		FormVersion formVersion = formVersionPersistence.findByPrimaryKey(
			formVersionId);

		Form form = formPersistence.findByPrimaryKey(formVersion.getFormId());

		boolean limitReached = false;

		if (campaign.getSubmissionLimit() > 0) {
			int usedSlots = _campaignQuotaLocalService.reserveSlot(campaign);

			limitReached = usedSlots >= campaign.getSubmissionLimit();
		}

		Submission submission = submissionPersistence.create(
			counterLocalService.increment());

		submission.setGroupId(campaign.getGroupId());
		submission.setCompanyId(campaign.getCompanyId());
		submission.setUserId(submissionData.getUserId());
		submission.setUserName(submissionData.getUserName());
		submission.setCreateDate(submissionData.getCreateDate());
		submission.setModifiedDate(submissionData.getCreateDate());
		submission.setCampaignId(campaignId);
		submission.setFormId(form.getFormId());
		submission.setFormVersionId(formVersionId);
		submission.setStatus(SubmissionStatus.RECEIVED.name());
		submission.setSourceUrl(submissionData.getSourceUrl());
		submission.setReferrer(submissionData.getReferrer());
		submission.setUserAgent(submissionData.getUserAgent());
		submission.setLanguageId(submissionData.getLanguageId());
		submission.setIpHash(submissionData.getIpHash());
		submission.setUtmSource(submissionData.getUtmSource());
		submission.setUtmMedium(submissionData.getUtmMedium());
		submission.setUtmCampaign(submissionData.getUtmCampaign());
		submission.setUtmContent(submissionData.getUtmContent());
		submission.setUtmTerm(submissionData.getUtmTerm());
		submission.setConsentVersion(submissionData.getConsentVersion());
		submission.setConsentDate(submissionData.getConsentDate());
		submission.setIdempotencyKey(submissionData.getIdempotencyKey());
		submission.setMetadata(submissionData.getMetadata());
		submission.setPayloadSnapshot(submissionData.getPayloadSnapshot());

		submission = submissionPersistence.update(submission);

		for (SubmittedField submittedField :
				submissionData.getSubmittedFields()) {
			List<FieldValue> fieldValues = submittedField.getFieldValues();

			for (int i = 0; i < fieldValues.size(); i++) {
				_addSubmissionValue(
					submission, submittedField, i, fieldValues.get(i));
			}
		}

		List<EventDelivery> eventDeliveries = new ArrayList<>(
			_eventDeliveryLocalService.addEventDeliveries(
				submission, EventType.SUBMISSION_CREATED));

		if (limitReached) {
			eventDeliveries.addAll(
				_eventDeliveryLocalService.addEventDeliveries(
					submission, EventType.CAMPAIGN_LIMIT_REACHED));
		}

		if (!eventDeliveries.isEmpty()) {
			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					for (EventDelivery eventDelivery : eventDeliveries) {
						_eventDeliveryLocalService.processEventDelivery(
							eventDelivery.getEventDeliveryId());
					}

					return null;
				});
		}

		return submission;
	}

	@Override
	public Submission deleteSubmission(long submissionId)
		throws PortalException {
		Submission submission = submissionPersistence.findByPrimaryKey(
			submissionId);

		submissionValuePersistence.removeBySubmissionId(submissionId);
		eventDeliveryPersistence.removeBySubmissionId(submissionId);

		Campaign campaign = campaignPersistence.fetchByPrimaryKey(
			submission.getCampaignId());

		if ((campaign != null) && (campaign.getSubmissionLimit() > 0)) {
			_campaignQuotaLocalService.releaseSlot(campaign.getCampaignId());
		}

		return submissionPersistence.remove(submission);
	}

	@Override
	public Submission fetchSubmissionByIdempotencyKey(
		long campaignId, String idempotencyKey) {
		return submissionPersistence.fetchByC_IK(campaignId, idempotencyKey);
	}

	@Override
	public List<Submission> getCampaignSubmissions(
		long campaignId, int start, int end) {
		return submissionPersistence.findByCampaignId(campaignId, start, end);
	}

	@Override
	public int getCampaignSubmissionsCount(long campaignId) {
		return submissionPersistence.countByCampaignId(campaignId);
	}

	@Override
	public Map<Long, Long> getSubmissionsCounts(long[] campaignIds) {
		Map<Long, Long> submissionsCounts = new HashMap<>();

		if (ArrayUtil.isEmpty(campaignIds)) {
			return submissionsCounts;
		}

		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"campaignId", Arrays.asList(ArrayUtil.toArray(campaignIds))));

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(ProjectionFactoryUtil.groupProperty("campaignId"));
		projectionList.add(ProjectionFactoryUtil.rowCount());

		dynamicQuery.setProjection(projectionList);

		List<Object[]> rows = dynamicQuery(dynamicQuery);

		for (Object[] row : rows) {
			Number count = (Number)row[1];

			submissionsCounts.put((Long)row[0], count.longValue());
		}

		for (long campaignId : campaignIds) {
			submissionsCounts.putIfAbsent(campaignId, 0L);
		}

		return submissionsCounts;
	}

	private void _addSubmissionValue(
		Submission submission, SubmittedField submittedField, int valueIndex,
		FieldValue fieldValue) {
		SubmissionValue submissionValue = submissionValuePersistence.create(
			counterLocalService.increment());

		submissionValue.setCompanyId(submission.getCompanyId());
		submissionValue.setCampaignId(submission.getCampaignId());
		submissionValue.setSubmissionId(submission.getSubmissionId());
		submissionValue.setFormFieldId(submittedField.getFormFieldId());
		submissionValue.setFieldKey(submittedField.getFieldKey());

		FieldValueType fieldValueType = fieldValue.getValueType();

		submissionValue.setValueType(fieldValueType.name());

		submissionValue.setValueIndex(valueIndex);
		submissionValue.setStringValue(fieldValue.getStringValue());
		submissionValue.setTextValue(fieldValue.getTextValue());
		submissionValue.setNumberValue(fieldValue.getNumberValue());
		submissionValue.setDateValue(fieldValue.getDateValue());
		submissionValue.setBooleanValue(fieldValue.isBooleanValue());

		submissionValuePersistence.update(submissionValue);
	}

	@Reference
	private CampaignQuotaLocalService _campaignQuotaLocalService;

	@Reference
	private EventDeliveryLocalService _eventDeliveryLocalService;
}
