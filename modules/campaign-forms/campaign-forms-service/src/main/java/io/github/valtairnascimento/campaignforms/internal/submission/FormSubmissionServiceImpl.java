package io.github.valtairnascimento.campaignforms.internal.submission;

import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.exception.FormValidationException;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.internal.field.FieldDefinitionLoader;
import io.github.valtairnascimento.campaignforms.internal.submission.security.SubmissionSecurityHelper;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionData;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;
import io.github.valtairnascimento.campaignforms.submission.SubmittedField;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(service = FormSubmissionService.class)
public class FormSubmissionServiceImpl implements FormSubmissionService {
	@Override
	public SubmissionResult submit(
			long groupId, String slug, SubmissionCommand submissionCommand)
		throws PortalException {
		Campaign campaign = _campaignLocalService.fetchCampaignBySlug(
			groupId, slug);

		if ((campaign == null) ||
			CampaignStatus.DRAFT.matches(campaign.getStatus())) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		Form form = _formLocalService.fetchCampaignForm(
			campaign.getCampaignId());

		if ((form == null) || (form.getPublishedVersionId() <= 0)) {
			throw new NoSuchCampaignException("campaign-not-found");
		}

		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		String idempotencyKey =
			SubmissionDataSanitizer.normalizeIdempotencyKey(
				submissionCommand.getIdempotencyKey());

		if (idempotencyKey != null) {
			Submission submission =
				_submissionLocalService.fetchSubmissionByIdempotencyKey(
					campaign.getCampaignId(), idempotencyKey);

			if (submission != null) {
				return _toSubmissionResult(
					SubmissionResult.Outcome.DUPLICATE,
					submission.getSubmissionId(), formVersion);
			}
		}

		List<FieldDefinition> fieldDefinitions =
			_fieldDefinitionLoader.getFieldDefinitions(
				formVersion.getFormVersionId());

		SubmissionContextImpl submissionContextImpl = new SubmissionContextImpl(
			campaign, form, formVersion, fieldDefinitions, submissionCommand,
			new Date(), _getTimeZone(campaign));

		for (SubmissionStep submissionStep : _submissionSteps) {
			submissionStep.process(submissionContextImpl);

			if (submissionContextImpl.isDiscarded()) {
				return _toSubmissionResult(
					SubmissionResult.Outcome.DISCARDED, 0, formVersion);
			}
		}

		if (!submissionContextImpl.getErrors(
			).isEmpty()) {
			throw new FormValidationException(
				submissionContextImpl.getErrors());
		}

		Submission submission;

		try {
			submission = _submissionLocalService.addSubmission(
				campaign.getCampaignId(), formVersion.getFormVersionId(),
				_toSubmissionData(submissionContextImpl, idempotencyKey));
		}
		catch (SystemException systemException) {
			if (idempotencyKey != null) {
				Submission existingSubmission =
					_submissionLocalService.fetchSubmissionByIdempotencyKey(
						campaign.getCampaignId(), idempotencyKey);

				if (existingSubmission != null) {
					return _toSubmissionResult(
						SubmissionResult.Outcome.DUPLICATE,
						existingSubmission.getSubmissionId(), formVersion);
				}
			}

			throw systemException;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Submission " + submission.getSubmissionId() +
					" added to campaign " + campaign.getCampaignId());
		}

		return _toSubmissionResult(
			SubmissionResult.Outcome.CREATED, submission.getSubmissionId(),
			formVersion);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_submissionSteps = ServiceTrackerListFactory.open(
			bundleContext, SubmissionStep.class, Collections.reverseOrder());
	}

	@Deactivate
	protected void deactivate() {
		_submissionSteps.close();
	}

	private TimeZone _getTimeZone(Campaign campaign) throws PortalException {
		return _companyLocalService.getCompany(
			campaign.getCompanyId()
		).getTimeZone();
	}

	private boolean _isMultiValued(FieldDefinition fieldDefinition) {
		FieldType fieldType = _fieldTypeRegistry.getFieldType(
			fieldDefinition.getType());

		if (fieldType == null) {
			return false;
		}

		return fieldType.isMultiValued();
	}

	private SubmissionData _toSubmissionData(
		SubmissionContextImpl submissionContextImpl, String idempotencyKey) {
		Campaign campaign = submissionContextImpl.getCampaign();
		SubmissionCommand submissionCommand =
			submissionContextImpl.getCommand();

		SubmissionData submissionData = new SubmissionData();

		long userId = 0;
		String userName = "";

		if (submissionCommand.getUserId() > 0) {
			User user = _userLocalService.fetchUser(
				submissionCommand.getUserId());

			if ((user != null) && !user.isGuestUser()) {
				userId = user.getUserId();
				userName = user.getFullName();
			}
		}

		submissionData.setUser(
			userId, userName
		).setCreateDate(
			submissionContextImpl.getNow()
		).setIdempotencyKey(
			idempotencyKey
		).setIpHash(
			_submissionSecurityHelper.hashIpAddress(
				campaign.getCompanyId(), submissionCommand.getIpAddress())
		).setSourceUrl(
			SubmissionDataSanitizer.sanitizeUrl(
				submissionCommand.getSourceUrl())
		).setReferrer(
			SubmissionDataSanitizer.sanitizeUrl(submissionCommand.getReferrer())
		).setUserAgent(
			SubmissionDataSanitizer.sanitizeText(
				submissionCommand.getUserAgent(),
				SubmissionDataSanitizer.MAX_USER_AGENT_LENGTH)
		).setLanguageId(
			SubmissionDataSanitizer.normalizeLanguageId(
				submissionCommand.getLanguageId(),
				campaign.getDefaultLanguageId())
		).setUtm(
			_sanitizeUtm(submissionCommand.getUtmSource()),
			_sanitizeUtm(submissionCommand.getUtmMedium()),
			_sanitizeUtm(submissionCommand.getUtmCampaign()),
			_sanitizeUtm(submissionCommand.getUtmContent()),
			_sanitizeUtm(submissionCommand.getUtmTerm())
		).setConsent(
			submissionContextImpl.getConsentVersion(),
			submissionContextImpl.getConsentDate()
		).setMetadata(
			_jsonFactory.createJSONObject(
			).put(
				"formVersion",
				submissionContextImpl.getFormVersion(
				).getVersion()
			).toString()
		);

		JSONObject payloadJSONObject = _jsonFactory.createJSONObject();

		Map<String, List<FieldValue>> values =
			submissionContextImpl.getValues();

		for (FieldDefinition fieldDefinition :
				submissionContextImpl.getFieldDefinitions()) {
			List<FieldValue> fieldValues = values.get(
				fieldDefinition.getFieldKey());

			if (fieldValues == null) {
				continue;
			}

			submissionData.addSubmittedField(
				new SubmittedField(
					fieldDefinition.getFormFieldId(),
					fieldDefinition.getFieldKey(), fieldValues));

			if (_isMultiValued(fieldDefinition)) {
				JSONArray jsonArray = _jsonFactory.createJSONArray();

				for (FieldValue fieldValue : fieldValues) {
					jsonArray.put(fieldValue.getFullStringValue());
				}

				payloadJSONObject.put(fieldDefinition.getFieldKey(), jsonArray);
			}
			else {
				FieldValue fieldValue = fieldValues.get(0);

				payloadJSONObject.put(
					fieldDefinition.getFieldKey(),
					fieldValue.getFullStringValue());
			}
		}

		submissionData.setPayloadSnapshot(payloadJSONObject.toString());

		return submissionData;
	}

	private String _sanitizeUtm(String value) {
		return SubmissionDataSanitizer.sanitizeText(
			value, SubmissionDataSanitizer.MAX_UTM_LENGTH);
	}

	private SubmissionResult _toSubmissionResult(
		SubmissionResult.Outcome outcome, long submissionId,
		FormVersion formVersion) {
		return new SubmissionResult(
			outcome, submissionId, formVersion.getSuccessTitle(),
			formVersion.getSuccessMessage(), formVersion.getRedirectUrl());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FormSubmissionServiceImpl.class);

	@Reference
	private CampaignLocalService _campaignLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private FieldDefinitionLoader _fieldDefinitionLoader;

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private SubmissionLocalService _submissionLocalService;

	@Reference
	private SubmissionSecurityHelper _submissionSecurityHelper;

	private ServiceTrackerList<SubmissionStep> _submissionSteps;

	@Reference
	private UserLocalService _userLocalService;
}
