package io.github.valtairnascimento.campaignforms.test.util;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.SubmissionStatus;
import io.github.valtairnascimento.campaignforms.domain.ConsentVersionUtil;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalServiceUtil;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalServiceUtil;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.Date;

public class CampaignTestUtil {
	public static Campaign addCampaign(
			CampaignLocalService campaignLocalService, long groupId,
			CampaignMode mode)
		throws Exception {
		return addCampaign(
			campaignLocalService, groupId, mode, randomSlug());
	}

	public static Campaign addCampaign(
			CampaignLocalService campaignLocalService, long groupId,
			CampaignMode mode, String slug)
		throws Exception {
		return withConsent(
			campaignLocalService.addCampaign(
				TestPropsValues.getUserId(), groupId,
				campaignInput(mode, slug),
				ServiceContextTestUtil.getServiceContext(
					groupId, TestPropsValues.getUserId())));
	}

	public static Submission addSubmission(
		SubmissionLocalService submissionLocalService, Campaign campaign) {
		Submission submission = submissionLocalService.createSubmission(
			CounterLocalServiceUtil.increment());

		submission.setGroupId(campaign.getGroupId());
		submission.setCompanyId(campaign.getCompanyId());
		submission.setCreateDate(new Date());
		submission.setModifiedDate(new Date());
		submission.setCampaignId(campaign.getCampaignId());
		submission.setStatus(SubmissionStatus.RECEIVED.name());

		return submissionLocalService.addSubmission(submission);
	}

	public static void deleteCampaigns(
			CampaignLocalService campaignLocalService,
			SubmissionLocalService submissionLocalService, long... groupIds)
		throws Exception {
		for (long groupId : groupIds) {
			for (Campaign campaign :
					campaignLocalService.getGroupCampaigns(
						groupId, -1, -1, null)) {
				for (Submission submission :
						submissionLocalService.getCampaignSubmissions(
							campaign.getCampaignId(), -1, -1)) {
					submissionLocalService.deleteSubmission(
						submission.getSubmissionId());
				}

				campaignLocalService.deleteCampaign(campaign.getCampaignId());
			}
		}
	}

	public static FormFieldInput field(
		String fieldKey, String type, boolean required) {
		return new FormFieldInput(
		).setFieldKey(
			fieldKey
		).setLabel(
			StringUtil.upperCaseFirstLetter(fieldKey)
		).setType(
			type
		).setRequired(
			required
		);
	}

	public static FormVersion getDraftFormVersion(
			FormLocalService formLocalService, Campaign campaign)
		throws Exception {
		Form form = formLocalService.getCampaignForm(campaign.getCampaignId());

		return formLocalService.getOrCreateDraftFormVersion(
			TestPropsValues.getUserId(), form.getFormId());
	}

	public static SubmissionCommand submissionCommand(
		FormTokenManager formTokenManager, FormVersion formVersion) {
		return new SubmissionCommand(
		).setConsent(
			true, ConsentVersionUtil.getVersion(formVersion)
		).setFormToken(
			formTokenManager.createFormToken(
				formVersion, new Date(System.currentTimeMillis() - 60_000))
		);
	}

	public static Campaign withConsent(Campaign campaign) throws Exception {
		Form form = FormLocalServiceUtil.getCampaignForm(
			campaign.getCampaignId());

		FormVersion formVersion = FormVersionLocalServiceUtil.getFormVersion(
			form.getDraftVersionId());

		FormVersionLocalServiceUtil.updateFormVersion(
			formVersion.getFormVersionId(),
			new FormVersionInput(
			).setCaptchaEnabled(
				formVersion.isCaptchaEnabled()
			).setConsentRequired(
				true
			).setConsentText(
				formVersion.getConsentText()
			).setConsentURL(
				CONSENT_URL
			).setConsentVersion(
				formVersion.getConsentVersion()
			).setRedirectUrl(
				formVersion.getRedirectUrl()
			).setRequireAuthentication(
				formVersion.isRequireAuthentication()
			).setSaveIncompleteSubmission(
				formVersion.isSaveIncompleteSubmission()
			).setSettings(
				formVersion.getSettings()
			).setSubmitLabel(
				formVersion.getSubmitLabel()
			).setSuccessMessage(
				formVersion.getSuccessMessage()
			).setSuccessTitle(
				formVersion.getSuccessTitle()
			));

		return campaign;
	}

	public static final String CONSENT_URL =
		"https://www.example.com/privacy";

	public static CampaignInput campaignInput(CampaignMode mode, String slug) {
		return new CampaignInput(
		).setMode(
			mode
		).setName(
			"Encontro RH " + RandomTestUtil.randomString()
		).setSlug(
			slug
		).setSeriesKey(
			"encontro-rh"
		).setInstanceKey(
			"curitiba"
		);
	}

	public static FormFieldInput emailField() {
		return new FormFieldInput(
		).setFieldKey(
			"email"
		).setLabel(
			"E-mail"
		).setType(
			"email"
		).setRequired(
			true
		).setPersonalData(
			true
		);
	}

	public static FormFieldInput nameField() {
		return new FormFieldInput(
		).setFieldKey(
			"nome"
		).setLabel(
			"Nome"
		).setType(
			"text"
		).setRequired(
			true
		).setPersonalData(
			true
		);
	}

	public static FormFieldInput selectField() {
		return new FormFieldInput(
		).setFieldKey(
			"cidade"
		).setLabel(
			"Cidade"
		).setType(
			"select"
		).setOptions(
			Arrays.asList(
				new FieldOptionInput("Curitiba", "curitiba"),
				new FieldOptionInput("Londrina", "londrina"))
		);
	}

	public static String randomSlug() {
		return "campanha-" +
			StringUtil.toLowerCase(RandomTestUtil.randomString(8));
	}

}
