package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.DeliveryStatus;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.EventDeliveryLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

@RunWith(Arquillian.class)
public class NotificationDeliveryTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_originalFromAddress = PrefsPropsUtil.getString(
			TestPropsValues.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		_setFromAddress("campanhas@example.com");

		Bundle bundle = FrameworkUtil.getBundle(NotificationDeliveryTest.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("destination.name", DestinationNames.MAIL);

		_serviceRegistration = bundle.getBundleContext(
		).registerService(
			MessageListener.class,
			message -> {
				Object payload = message.getPayload();

				if (payload instanceof MailMessage) {
					_mailMessages.add((MailMessage)payload);
				}
			},
			properties
		);

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
			formVersionId,
			CampaignTestUtil.field(
				"interesses", "checkbox", false
			).setOptions(
				Arrays.asList(
					new FieldOptionInput("Inovação", "inovacao"),
					new FieldOptionInput("Gestão", "gestao"))
			));

		_notificationConfig =
			_notificationConfigLocalService.addNotificationConfig(
				TestPropsValues.getUserId(), _campaign.getCampaignId(),
				new NotificationConfigInput(
				).setName(
					"Aviso"
				).setEventType(
					EventType.SUBMISSION_CREATED
				).setChannel(
					NotificationChannelType.EMAIL
				).setEnabled(
					true
				).setRecipients(
					"eventos@example.org, ${submission.email}"
				).setSubjectTemplate(
					"Nova inscrição: ${submission.nome}"
				).setBodyTemplate(
					"Olá!\n${submission.table}"
				));

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());
	}

	@After
	public void tearDown() throws Exception {
		_serviceRegistration.unregister();

		_setFromAddress(_originalFromAddress);

		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testBlacklistedSenderIsRetried() throws Exception {
		_setFromAddress("noreply@liferay.com");

		EventDelivery eventDelivery =
			_eventDeliveryLocalService.getSubmissionEventDeliveries(
				_submit().getSubmissionId()
			).get(
				0
			);

		Assert.assertEquals(
			DeliveryStatus.FAILED.name(), eventDelivery.getStatus());
		Assert.assertEquals(
			"notification-sender-is-not-configured",
			eventDelivery.getLastErrorCode());
		Assert.assertTrue(
			eventDelivery.getNextAttemptDate(
			).after(
				new Date()
			));

		_setFromAddress("campanhas@example.com");

		eventDelivery = _eventDeliveryLocalService.processEventDelivery(
			eventDelivery.getEventDeliveryId());

		Assert.assertEquals(
			DeliveryStatus.SUCCESS.name(), eventDelivery.getStatus());
		Assert.assertEquals(2, eventDelivery.getAttempts());
	}

	@Test
	public void testDeliveredAfterCommit() throws Exception {
		SubmissionResult submissionResult = _submit();

		List<EventDelivery> eventDeliveries =
			_eventDeliveryLocalService.getSubmissionEventDeliveries(
				submissionResult.getSubmissionId());

		Assert.assertEquals(
			eventDeliveries.toString(), 1, eventDeliveries.size());

		EventDelivery eventDelivery = eventDeliveries.get(0);

		Assert.assertEquals(
			DeliveryStatus.SUCCESS.name(), eventDelivery.getStatus());
		Assert.assertEquals(1, eventDelivery.getAttempts());

		MailMessage mailMessage = _waitForMail("Nova inscrição: Maria Silva");

		Assert.assertEquals(
			Arrays.asList("eventos@example.org", "maria@example.org"),
			_getAddresses(mailMessage.getTo()));
		Assert.assertTrue(mailMessage.isHTMLFormat());

		String body = mailMessage.getBody();

		Assert.assertTrue(body, body.contains("Olá!<br><table"));
		Assert.assertTrue(body, body.contains(">Maria Silva</td>"));
		Assert.assertTrue(body, body.contains(">Inovação, Gestão</td>"));
	}

	@Test
	public void testDueDeliveriesAreRetried() throws Exception {
		Submission submission = _submissionLocalService.getSubmission(
			_submit().getSubmissionId());

		EventDelivery eventDelivery =
			_eventDeliveryLocalService.addEventDeliveries(
				submission, EventType.SUBMISSION_CREATED
			).get(
				0
			);

		Assert.assertEquals(
			DeliveryStatus.PENDING.name(), eventDelivery.getStatus());

		_eventDeliveryLocalService.processDueEventDeliveries();

		eventDelivery = _eventDeliveryLocalService.getEventDelivery(
			eventDelivery.getEventDeliveryId());

		Assert.assertEquals(
			DeliveryStatus.PENDING.name(), eventDelivery.getStatus());

		eventDelivery.setNextAttemptDate(
			new Date(System.currentTimeMillis() - (3 * 60 * 1000)));

		_eventDeliveryLocalService.updateEventDelivery(eventDelivery);

		_eventDeliveryLocalService.processDueEventDeliveries();

		eventDelivery = _eventDeliveryLocalService.getEventDelivery(
			eventDelivery.getEventDeliveryId());

		Assert.assertEquals(
			DeliveryStatus.SUCCESS.name(), eventDelivery.getStatus());
	}

	@Test
	public void testRemovedNotificationIsDead() throws Exception {
		Submission submission = _submissionLocalService.getSubmission(
			_submit().getSubmissionId());

		EventDelivery eventDelivery =
			_eventDeliveryLocalService.addEventDeliveries(
				submission, EventType.SUBMISSION_CREATED
			).get(
				0
			);

		_notificationConfigLocalService.deleteNotificationConfig(
			_notificationConfig.getNotificationConfigId());

		eventDelivery = _eventDeliveryLocalService.processEventDelivery(
			eventDelivery.getEventDeliveryId());

		Assert.assertEquals(
			DeliveryStatus.DEAD.name(), eventDelivery.getStatus());
		Assert.assertEquals(
			"notification-is-not-available",
			eventDelivery.getLastErrorCode());
		Assert.assertEquals(1, eventDelivery.getAttempts());

		eventDelivery = _eventDeliveryLocalService.processEventDelivery(
			eventDelivery.getEventDeliveryId());

		Assert.assertEquals(1, eventDelivery.getAttempts());
	}

	@Test
	public void testSendTestNotification() throws Exception {
		User user = TestPropsValues.getUser();

		Assert.assertEquals(
			user.getEmailAddress(),
			_notificationConfigLocalService.sendTestNotification(
				_notificationConfig.getNotificationConfigId(),
				user.getUserId()));

		MailMessage mailMessage = _waitForMail("Nova inscrição: ");

		Assert.assertEquals(
			Arrays.asList(user.getEmailAddress()),
			_getAddresses(mailMessage.getTo()));
		Assert.assertTrue(
			mailMessage.getSubject(),
			mailMessage.getSubject(
			).startsWith(
				"["
			));
		Assert.assertEquals(0, mailMessage.getCC().length);
	}

	private List<String> _getAddresses(Object[] internetAddresses) {
		List<String> addresses = new ArrayList<>();

		for (Object internetAddress : internetAddresses) {
			addresses.add(((InternetAddress)internetAddress).getAddress());
		}

		return addresses;
	}

	private void _setFromAddress(String fromAddress) throws Exception {
		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			TestPropsValues.getCompanyId());

		portletPreferences.setValue(
			PropsKeys.ADMIN_EMAIL_FROM_ADDRESS, fromAddress);

		portletPreferences.store();
	}

	private SubmissionResult _submit() throws Exception {
		Form form = _formLocalService.getCampaignForm(
			_campaign.getCampaignId());

		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		return _formSubmissionService.submit(
			_group.getGroupId(), _campaign.getSlug(),
			CampaignTestUtil.submissionCommand(
				_formTokenManager, formVersion
			).putValue(
				"nome", "Maria Silva"
			).putValue(
				"email", "maria@example.org"
			).putValues(
				"interesses", Arrays.asList("inovacao", "gestao")
			));
	}

	private MailMessage _waitForMail(String subject) throws Exception {
		for (int i = 0; i < 50; i++) {
			for (MailMessage mailMessage : _mailMessages) {
				if (mailMessage.getSubject(
					).contains(
						subject
					)) {
					return mailMessage;
				}
			}

			Thread.sleep(200);
		}

		Assert.fail("No email with subject " + subject);

		return null;
	}

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

	private final Queue<MailMessage> _mailMessages =
		new ConcurrentLinkedQueue<>();
	private NotificationConfig _notificationConfig;
	private String _originalFromAddress;

	@Inject
	private NotificationConfigLocalService _notificationConfigLocalService;

	private ServiceRegistration<MessageListener> _serviceRegistration;

	@Inject
	private SubmissionLocalService _submissionLocalService;
}
