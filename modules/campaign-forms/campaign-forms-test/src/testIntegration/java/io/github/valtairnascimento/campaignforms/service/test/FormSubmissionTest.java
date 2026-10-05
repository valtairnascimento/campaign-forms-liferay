package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.exception.CampaignInactiveException;
import io.github.valtairnascimento.campaignforms.exception.FormTokenException;
import io.github.valtairnascimento.campaignforms.exception.FormValidationException;
import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.exception.SubmissionLimitReachedException;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.FormVersionInput;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.EventDeliveryLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionValueLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FormSubmissionTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		long formVersionId = draftFormVersion.getFormVersionId();

		_formFieldLocalService.addFormField(
			formVersionId, CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			formVersionId, CampaignTestUtil.emailField());
		_formFieldLocalService.addFormField(
			formVersionId, CampaignTestUtil.selectField());
		_formFieldLocalService.addFormField(
			formVersionId,
			CampaignTestUtil.field(
				"interesses", "checkbox", false
			).setOptions(
				Arrays.asList(
					new FieldOptionInput("Inovação", "inovacao"),
					new FieldOptionInput("Gestão", "gestao"))
			));
		_formFieldLocalService.addFormField(
			formVersionId,
			CampaignTestUtil.field(
				"origem", "hidden", false
			).setDefaultValue(
				"site"
			));

		_formVersionLocalService.updateFormVersion(
			formVersionId,
			new FormVersionInput(
			).setConsentURL(
				CampaignTestUtil.CONSENT_URL
			).setSuccessTitle(
				"Obrigado!"
			).setSuccessMessage(
				"Inscrição recebida."
			));

		_addNotification(EventType.SUBMISSION_CREATED);

		_publish();
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testArchivedOrOutOfPeriodCampaignIsClosed() throws Exception {
		_campaignPublicationService.archive(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		_assertRejected(_validCommand(), CampaignInactiveException.class);

		_campaignPublicationService.reopen(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		_updateCampaign(
			_campaignInput(
			).setStartDate(
				new Date(System.currentTimeMillis() + _ONE_DAY)
			));

		_assertRejected(_validCommand(), CampaignInactiveException.class);
	}

	@Test
	public void testConsentIsRequiredWhenConfigured() throws Exception {
		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		_formVersionLocalService.updateFormVersion(
			draftFormVersion.getFormVersionId(),
			new FormVersionInput(
			).setConsentRequired(
				true
			).setConsentText(
				"Autorizo o uso dos meus dados."
			).setConsentVersion(
				"2026-10"
			).setConsentURL(
				CampaignTestUtil.CONSENT_URL
			));

		_publish();

		FormValidationException formValidationException = _assertRejected(
			_validCommand(
			).setConsent(
				false, "2026-10"
			),
			FormValidationException.class);

		Assert.assertTrue(
			formValidationException.getFieldErrors(
			).contains(
				new FieldError("consent", FieldError.CONSENT_REQUIRED, null)
			));

		_assertRejected(
			_validCommand(
			).setConsent(
				true, "2025-01"
			),
			FormValidationException.class);

		SubmissionResult submissionResult = _submit(
			_validCommand(
			).setConsent(
				true, "2026-10"
			));

		Submission submission = _submissionLocalService.getSubmission(
			submissionResult.getSubmissionId());

		Assert.assertEquals("2026-10", submission.getConsentVersion());
		Assert.assertNotNull(submission.getConsentDate());
	}

	@Test
	public void testDraftCampaignIsNotFound() throws Exception {
		Campaign draftCampaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		try {
			_formSubmissionService.submit(
				_group.getGroupId(), draftCampaign.getSlug(),
				new SubmissionCommand());

			Assert.fail();
		}
		catch (NoSuchCampaignException noSuchCampaignException) {
		}

		try {
			_formSubmissionService.submit(
				_group.getGroupId(), "nao-existe", new SubmissionCommand());

			Assert.fail();
		}
		catch (NoSuchCampaignException noSuchCampaignException) {
		}
	}

	@Test
	public void testFormTokenIsVerified() throws Exception {
		_assertRejected(
			_validCommand(
			).setFormToken(
				null
			),
			FormTokenException.class);

		String formToken = _formTokenManager.createFormToken(
			_getPublishedFormVersion(),
			new Date(System.currentTimeMillis() - 60_000));

		_assertRejected(
			_validCommand(
			).setFormToken(
				formToken.substring(0, formToken.length() - 2) + "xx"
			),
			FormTokenException.class);

		FormTokenException formTokenException = _assertRejected(
			_validCommand(
			).setFormToken(
				_formTokenManager.createFormToken(
					_getPublishedFormVersion(),
					new Date(System.currentTimeMillis() - (25 * _ONE_HOUR)))
			),
			FormTokenException.class);

		Assert.assertEquals(
			"form-token-has-expired", formTokenException.getMessage());
	}

	@Test
	public void testHoneypotAndTooFastSubmissionsAreDiscarded()
		throws Exception {
		SubmissionResult submissionResult = _submit(
			_validCommand(
			).setHoneypotValue(
				"http://spam.example"
			));

		Assert.assertEquals(
			SubmissionResult.Outcome.DISCARDED, submissionResult.getOutcome());

		submissionResult = _submit(
			_validCommand(
			).setFormToken(
				_formTokenManager.createFormToken(_getPublishedFormVersion())
			));

		Assert.assertEquals(
			SubmissionResult.Outcome.DISCARDED, submissionResult.getOutcome());
		Assert.assertEquals(0, submissionResult.getSubmissionId());

		Assert.assertEquals(
			0,
			_submissionLocalService.getCampaignSubmissionsCount(
				_campaign.getCampaignId()));
	}

	@Test
	public void testIdempotencyKeyReturnsOriginalSubmission() throws Exception {
		String idempotencyKey = UUID.randomUUID(
		).toString();

		SubmissionResult submissionResult = _submit(
			_validCommand(
			).setIdempotencyKey(
				idempotencyKey
			));

		SubmissionResult duplicateSubmissionResult = _submit(
			_validCommand(
			).setIdempotencyKey(
				idempotencyKey
			));

		Assert.assertEquals(
			SubmissionResult.Outcome.DUPLICATE,
			duplicateSubmissionResult.getOutcome());
		Assert.assertEquals(
			submissionResult.getSubmissionId(),
			duplicateSubmissionResult.getSubmissionId());
		Assert.assertEquals(
			1,
			_submissionLocalService.getCampaignSubmissionsCount(
				_campaign.getCampaignId()));

		_assertRejected(
			_validCommand(
			).setIdempotencyKey(
				"x"
			),
			InvalidFieldException.class);
	}

	@Test
	public void testInvalidFieldsReturnAllErrors() throws Exception {
		FormValidationException formValidationException = _assertRejected(
			_command(
			).putValue(
				"email", "maria@"
			).putValue(
				"cidade", "maringa"
			),
			FormValidationException.class);

		List<FieldError> fieldErrors = formValidationException.getFieldErrors();

		Assert.assertEquals(fieldErrors.toString(), 3, fieldErrors.size());
		Assert.assertTrue(
			fieldErrors.contains(
				new FieldError("nome", FieldError.REQUIRED, null)));
		Assert.assertTrue(
			fieldErrors.contains(
				new FieldError("email", FieldError.INVALID_EMAIL, null)));
		Assert.assertTrue(
			fieldErrors.contains(
				new FieldError("cidade", FieldError.INVALID_OPTION, null)));

		Assert.assertEquals(
			0,
			_submissionLocalService.getCampaignSubmissionsCount(
				_campaign.getCampaignId()));
	}

	@Test
	public void testRequireAuthentication() throws Exception {
		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		_formVersionLocalService.updateFormVersion(
			draftFormVersion.getFormVersionId(),
			new FormVersionInput(
			).setConsentURL(
				CampaignTestUtil.CONSENT_URL
			).setRequireAuthentication(
				true
			));

		_publish();

		_assertRejected(
			_validCommand(), PrincipalException.MustBeAuthenticated.class);

		SubmissionResult submissionResult = _submit(
			_validCommand(
			).setUserId(
				TestPropsValues.getUserId()
			));

		Submission submission = _submissionLocalService.getSubmission(
			submissionResult.getSubmissionId());

		Assert.assertEquals(
			TestPropsValues.getUserId(), submission.getUserId());
	}

	@Test
	public void testSubmissionLimit() throws Exception {
		_updateCampaign(
			_campaignInput(
			).setSubmissionLimit(
				1
			));

		_addNotification(EventType.CAMPAIGN_LIMIT_REACHED);

		SubmissionResult submissionResult = _submit(_validCommand());

		List<String> eventTypes = new ArrayList<>();

		for (EventDelivery eventDelivery :
				_eventDeliveryLocalService.getSubmissionEventDeliveries(
					submissionResult.getSubmissionId())) {
			eventTypes.add(eventDelivery.getEventType());
		}

		Assert.assertTrue(
			eventTypes.toString(),
			eventTypes.contains(EventType.CAMPAIGN_LIMIT_REACHED.name()));

		_assertRejected(_validCommand(), SubmissionLimitReachedException.class);

		_submissionLocalService.deleteSubmission(
			submissionResult.getSubmissionId());

		Assert.assertEquals(
			SubmissionResult.Outcome.CREATED,
			_submit(
				_validCommand()
			).getOutcome());
	}

	@Test
	public void testSubmitStoresNormalizedValues() throws Exception {
		SubmissionResult submissionResult = _submit(
			_validCommand(
			).putValues(
				"interesses", Arrays.asList("inovacao", "gestao")
			).setIpAddress(
				"203.0.113.7"
			).setSourceUrl(
				"https://www.example.org/campaign/encontro?email=a@b.c#form"
			).setUtm(
				"newsletter", "email", "encontro-rh", null, null
			).setUserAgent(
				"Mozilla/5.0\n"
			));

		Assert.assertEquals(
			SubmissionResult.Outcome.CREATED, submissionResult.getOutcome());
		Assert.assertEquals("Obrigado!", submissionResult.getSuccessTitle());
		Assert.assertEquals(
			"Inscrição recebida.", submissionResult.getSuccessMessage());

		Submission submission = _submissionLocalService.getSubmission(
			submissionResult.getSubmissionId());

		Assert.assertEquals(
			_getPublishedFormVersion().getFormVersionId(),
			submission.getFormVersionId());
		Assert.assertEquals(
			"https://www.example.org/campaign/encontro",
			submission.getSourceUrl());
		Assert.assertEquals("newsletter", submission.getUtmSource());
		Assert.assertEquals("Mozilla/5.0", submission.getUserAgent());
		Assert.assertEquals(64, submission.getIpHash().length());
		Assert.assertFalse(submission.getIpHash().contains("203.0.113.7"));
		Assert.assertEquals(0, submission.getUserId());

		Map<String, List<String>> values = new HashMap<>();

		for (SubmissionValue submissionValue :
				_submissionValueLocalService.getSubmissionValues(
					submission.getSubmissionId())) {
			values.computeIfAbsent(
				submissionValue.getFieldKey(), key -> new ArrayList<>()
			).add(
				submissionValue.getStringValue()
			);

			Assert.assertEquals(
				FieldValueType.STRING.name(), submissionValue.getValueType());
		}

		Assert.assertEquals(Arrays.asList("Maria Silva"), values.get("nome"));
		Assert.assertEquals(
			Arrays.asList("maria@example.org"), values.get("email"));
		Assert.assertEquals(Arrays.asList("londrina"), values.get("cidade"));
		Assert.assertEquals(
			Arrays.asList("inovacao", "gestao"), values.get("interesses"));
		Assert.assertEquals(Arrays.asList("site"), values.get("origem"));

		Assert.assertTrue(
			submission.getPayloadSnapshot(),
			submission.getPayloadSnapshot(
			).contains(
				"\"interesses\":[\"inovacao\",\"gestao\"]"
			));

		List<EventDelivery> eventDeliveries =
			_eventDeliveryLocalService.getSubmissionEventDeliveries(
				submission.getSubmissionId());

		Assert.assertEquals(
			eventDeliveries.toString(), 1, eventDeliveries.size());
	}

	@Test
	public void testUnknownFieldIsRejected() throws Exception {
		InvalidFieldException invalidFieldException = _assertRejected(
			_validCommand(
			).putValue(
				"status", "PUBLISHED"
			),
			InvalidFieldException.class);

		Assert.assertEquals("status", invalidFieldException.getFieldKey());
	}

	private void _addNotification(EventType eventType) throws Exception {
		_notificationConfigLocalService.addNotificationConfig(
			TestPropsValues.getUserId(), _campaign.getCampaignId(),
			new NotificationConfigInput(
			).setName(
				eventType.name()
			).setEventType(
				eventType
			).setChannel(
				NotificationChannelType.EMAIL
			).setEnabled(
				true
			).setRecipients(
				"eventos@example.org"
			).setSubjectTemplate(
				"Nova inscrição em ${campaign.name}"
			).setBodyTemplate(
				"${submission.table}"
			));
	}

	private <T extends Exception> T _assertRejected(
			SubmissionCommand submissionCommand, Class<T> exceptionClass)
		throws Exception {
		try {
			_submit(submissionCommand);
		}
		catch (Exception exception) {
			if (exceptionClass.isInstance(exception)) {
				return exceptionClass.cast(exception);
			}

			throw exception;
		}

		Assert.fail("Expected " + exceptionClass.getName());

		return null;
	}

	private CampaignInput _campaignInput() throws Exception {
		Campaign campaign = _campaignLocalService.getCampaign(
			_campaign.getCampaignId());

		return new CampaignInput(
		).setMode(
			CampaignMode.parse(campaign.getMode())
		).setName(
			campaign.getName()
		).setSlug(
			campaign.getSlug()
		).setSeriesKey(
			campaign.getSeriesKey()
		).setInstanceKey(
			campaign.getInstanceKey()
		);
	}

	private SubmissionCommand _command() throws Exception {
		return CampaignTestUtil.submissionCommand(
			_formTokenManager, _getPublishedFormVersion());
	}

	private FormVersion _getPublishedFormVersion() throws Exception {
		Form form = _formLocalService.getCampaignForm(
			_campaign.getCampaignId());

		return _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());
	}

	private void _publish() throws Exception {
		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());
	}

	private SubmissionResult _submit(SubmissionCommand submissionCommand)
		throws Exception {
		return _formSubmissionService.submit(
			_group.getGroupId(), _campaign.getSlug(), submissionCommand);
	}

	private void _updateCampaign(CampaignInput campaignInput)
		throws Exception {
		_campaignLocalService.updateCampaign(
			TestPropsValues.getUserId(), _campaign.getCampaignId(),
			campaignInput,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));
	}

	private SubmissionCommand _validCommand() throws Exception {
		return _command(
		).putValue(
			"nome", " Maria Silva "
		).putValue(
			"email", "Maria@EXAMPLE.org"
		).putValue(
			"cidade", "londrina"
		);
	}

	private static final long _ONE_DAY = 24 * 60 * 60 * 1000L;

	private static final long _ONE_HOUR = 60 * 60 * 1000L;

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private EventDeliveryLocalService _eventDeliveryLocalService;

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
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

	@Inject
	private SubmissionValueLocalService _submissionValueLocalService;
}
