package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.exception.FormValidationException;
import io.github.valtairnascimento.campaignforms.exception.FormVersionSettingsException;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ConsentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testConsentIsOptional() throws Exception {
		Campaign campaign = _addCampaign();

		Assert.assertFalse(_getDraft(campaign).isConsentRequired());

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		PublicCampaignSnapshot.Form form = _getPublishedForm(campaign);

		Assert.assertFalse(form.isConsentRequired());
		Assert.assertNull(form.getConsentURL());
		Assert.assertNull(form.getConsentVersion());

		SubmissionResult submissionResult = _submit(
			campaign, _command(campaign));

		Assert.assertEquals(
			SubmissionResult.Outcome.CREATED, submissionResult.getOutcome());
	}

	@Test
	public void testInvalidConsentURLIsRejected() throws Exception {
		Campaign campaign = _addCampaign();

		for (String consentURL :
				new String[] {
					"javascript:alert(1)", "//evil.example/privacy",
					"www.example.com"
				}) {

			try {
				_updateDraft(campaign, true, consentURL, null);

				Assert.fail(consentURL);
			}
			catch (FormVersionSettingsException formVersionSettingsException) {
				Assert.assertEquals(
					"consent-url-is-invalid",
					formVersionSettingsException.getMessage());
			}
		}

		_updateDraft(campaign, true, "/web/guest/privacy", null);

		Assert.assertEquals(
			"/web/guest/privacy", _getDraft(campaign).getConsentURL());
	}

	@Test
	public void testNewCampaignReusesTheSiteConsentURL() throws Exception {
		Campaign campaign = _addCampaign();

		Assert.assertTrue(Validator.isNull(_getDraft(campaign).getConsentURL()));

		_updateDraft(campaign, true, CampaignTestUtil.CONSENT_URL, null);

		Assert.assertEquals(
			CampaignTestUtil.CONSENT_URL,
			_getDraft(
				_addCampaign()
			).getConsentURL());
	}

	@Test
	public void testRequiredConsentWithoutPrivacyLink() throws Exception {
		Campaign campaign = _addCampaign();

		_updateDraft(campaign, true, null, null);

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		PublicCampaignSnapshot.Form form = _getPublishedForm(campaign);

		Assert.assertTrue(form.isConsentRequired());
		Assert.assertNull(form.getConsentURL());
		Assert.assertTrue(
			form.getConsentText(),
			form.getConsentText(
			).contains(
				"Privacy Policy"
			) ||
			form.getConsentText(
			).contains(
				"Política de Privacidade"
			));

		try {
			_submit(campaign, _command(campaign).setConsent(false, null));

			Assert.fail();
		}
		catch (FormValidationException formValidationException) {
		}

		Assert.assertEquals(
			SubmissionResult.Outcome.CREATED,
			_submit(
				campaign,
				_command(
					campaign
				).setConsent(
					true, form.getConsentVersion()
				)
			).getOutcome());
	}

	@Test
	public void testVersionChangesWithTheTerms() throws Exception {
		Campaign campaign = _addCampaign();

		_updateDraft(campaign, true, CampaignTestUtil.CONSENT_URL, null);

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		PublicCampaignSnapshot.Form form = _getPublishedForm(campaign);

		Assert.assertEquals(CampaignTestUtil.CONSENT_URL, form.getConsentURL());

		String version = form.getConsentVersion();

		Assert.assertTrue(version, version.startsWith("auto-"));

		_updateDraft(
			campaign, true, "https://www.example.com/terms",
			"I agree to be contacted about this event.");

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		form = _getPublishedForm(campaign);

		Assert.assertEquals(
			"I agree to be contacted about this event.", form.getConsentText());
		Assert.assertNotEquals(version, form.getConsentVersion());
	}

	private Campaign _addCampaign() throws Exception {
		Campaign campaign = _campaignLocalService.addCampaign(
			TestPropsValues.getUserId(), _group.getGroupId(),
			CampaignTestUtil.campaignInput(
				CampaignMode.QUICK, CampaignTestUtil.randomSlug()),
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));

		_formFieldLocalService.addFormField(
			_getDraft(
				campaign
			).getFormVersionId(),
			CampaignTestUtil.nameField());

		return campaign;
	}

	private SubmissionCommand _command(Campaign campaign) throws Exception {
		Form form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		return new SubmissionCommand(
		).setFormToken(
			_formTokenManager.createFormToken(
				formVersion, new Date(System.currentTimeMillis() - 60_000))
		).putValue(
			"nome", "Maria"
		);
	}

	private FormVersion _getDraft(Campaign campaign) throws Exception {
		return CampaignTestUtil.getDraftFormVersion(_formLocalService, campaign);
	}

	private PublicCampaignSnapshot.Form _getPublishedForm(Campaign campaign)
		throws Exception {

		PublicCampaignSnapshot publicCampaignSnapshot =
			_publicCampaignService.getPublishedSnapshot(
				_group.getGroupId(), campaign.getSlug());

		return publicCampaignSnapshot.getForm();
	}

	private SubmissionResult _submit(
			Campaign campaign, SubmissionCommand submissionCommand)
		throws Exception {

		return _formSubmissionService.submit(
			_group.getGroupId(), campaign.getSlug(), submissionCommand);
	}

	private void _updateDraft(
			Campaign campaign, boolean consentRequired, String consentURL,
			String consentText)
		throws Exception {

		_formVersionLocalService.updateFormVersion(
			_getDraft(
				campaign
			).getFormVersionId(),
			new FormVersionInput(
			).setConsentRequired(
				consentRequired
			).setConsentText(
				consentText
			).setConsentURL(
				consentURL
			));
	}

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@Inject
	private FormSubmissionService _formSubmissionService;

	@Inject
	private FormTokenManager _formTokenManager;

	@Inject
	private FormVersionLocalService _formVersionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private PublicCampaignService _publicCampaignService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

}
