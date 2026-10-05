package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignModeService;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.exception.CampaignPublicationException;
import io.github.valtairnascimento.campaignforms.exception.CampaignStatusException;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CampaignPublicationTest {
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
	public void testArchiveAndReopen() throws Exception {
		long campaignId = _addPublishedCampaign(
			CampaignMode.QUICK
		).getCampaignId();

		Campaign campaign = _campaignPublicationService.archive(
			TestPropsValues.getUserId(), campaignId);

		Assert.assertEquals(
			CampaignStatus.ARCHIVED.name(), campaign.getStatus());

		_assertStatusException(
			() -> _campaignPublicationService.publish(
				TestPropsValues.getUserId(), campaignId));
		_assertStatusException(
			() -> _campaignPublicationService.archive(
				TestPropsValues.getUserId(), campaignId));

		Campaign reopenedCampaign = _campaignPublicationService.reopen(
			TestPropsValues.getUserId(), campaignId);

		Assert.assertEquals(
			CampaignStatus.PUBLISHED.name(), reopenedCampaign.getStatus());

		_assertStatusException(
			() -> _campaignPublicationService.reopen(
				TestPropsValues.getUserId(), campaignId));
	}

	@Test
	public void testCustomPageRequiresExactlyOneFormBlock() throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(),
			CampaignMode.CUSTOM_PAGE);

		_addFields(campaign);

		_assertIssue(campaign, "custom-page-requires-one-form-block");

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		PageBlock formBlock = _pageBlockLocalService.addPageBlock(
			pageConfiguration.getPageConfigurationId(),
			CampaignConstants.BLOCK_TYPE_FORM, "{}", true);

		Assert.assertEquals(
			"[]",
			_campaignPublicationService.getPublicationIssues(
				campaign.getCampaignId()
			).toString());

		try {
			_pageBlockLocalService.addPageBlock(
				pageConfiguration.getPageConfigurationId(),
				CampaignConstants.BLOCK_TYPE_FORM, "{}", true);

			Assert.fail();
		}
		catch (PageBlockConfigurationException
					pageBlockConfigurationException) {
			Assert.assertEquals(
				"block-type-is-singleton",
				pageBlockConfigurationException.getMessage());
		}

		_pageBlockLocalService.updatePageBlock(
			formBlock.getPageBlockId(), "{}", false);

		_assertIssue(campaign, "custom-page-requires-one-form-block");
	}

	@Test
	public void testEmptyFormCannotBePublished() throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		try {
			_campaignPublicationService.publish(
				TestPropsValues.getUserId(), campaign.getCampaignId());

			Assert.fail();
		}
		catch (CampaignPublicationException campaignPublicationException) {
			Assert.assertTrue(
				campaignPublicationException.getPublicationIssues(
				).contains(
					new PublicationIssue("form-has-no-fields", null)
				));
		}

		Assert.assertEquals(
			CampaignStatus.DRAFT.name(),
			_campaignLocalService.getCampaign(
				campaign.getCampaignId()
			).getStatus());
	}

	@Test
	public void testFieldValueTypeCannotChangeBetweenVersions()
		throws Exception {
		Campaign campaign = _addPublishedCampaign(CampaignMode.QUICK);

		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, campaign);

		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(),
			CampaignTestUtil.field("participantes", "number", false));

		_publish(campaign);

		draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, campaign);

		long formFieldId = _formFieldLocalService.getFormFields(
			draftFormVersion.getFormVersionId()
		).stream(
		).filter(
			formField -> "participantes".equals(formField.getFieldKey())
		).findFirst(
		).get(
		).getFormFieldId();

		_formFieldLocalService.updateFormField(
			formFieldId,
			CampaignTestUtil.field("participantes", "text", false));

		_assertIssue(campaign, "field-value-type-cannot-change");

		_formFieldLocalService.updateFormField(
			formFieldId,
			CampaignTestUtil.field("participantes", "number", true));

		Assert.assertEquals(
			"[]",
			_campaignPublicationService.getPublicationIssues(
				campaign.getCampaignId()
			).toString());
	}

	@Test
	public void testPublishCopiesDraftPageAndConvertedBlocks()
		throws Exception {
		Campaign campaign = _addPublishedCampaign(CampaignMode.QUICK);

		long formId = _formLocalService.getCampaignForm(
			campaign.getCampaignId()
		).getFormId();

		Campaign convertedCampaign = _campaignModeService.convertToCustomPage(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		Assert.assertEquals(
			CampaignMode.CUSTOM_PAGE.name(), convertedCampaign.getMode());
		Assert.assertEquals(campaign.getSlug(), convertedCampaign.getSlug());
		Assert.assertEquals(
			formId,
			_formLocalService.getCampaignForm(
				campaign.getCampaignId()
			).getFormId());

		Assert.assertEquals(
			"[HERO, FORM]",
			_getBlockTypes(campaign, PageStage.DRAFT).toString());
		Assert.assertEquals(
			"[]", _getBlockTypes(campaign, PageStage.PUBLISHED).toString());

		_publish(campaign);

		Assert.assertEquals(
			"[HERO, FORM]",
			_getBlockTypes(campaign, PageStage.PUBLISHED).toString());

		_campaignModeService.convertToCustomPage(
			TestPropsValues.getUserId(), campaign.getCampaignId());

		Assert.assertEquals(
			"[HERO, FORM]",
			_getBlockTypes(campaign, PageStage.DRAFT).toString());
	}

	@Test
	public void testPublishPromotesDraftAndRepublishCreatesNewVersion()
		throws Exception {
		Campaign campaign = _addPublishedCampaign(CampaignMode.QUICK);

		Assert.assertEquals(
			CampaignStatus.PUBLISHED.name(), campaign.getStatus());
		Assert.assertNotNull(campaign.getPublishedDate());

		Form form = _formLocalService.getCampaignForm(
			campaign.getCampaignId());

		Assert.assertEquals(0, form.getDraftVersionId());

		FormVersion firstFormVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		Assert.assertEquals(1, firstFormVersion.getVersion());
		Assert.assertEquals(
			FormVersionStatus.PUBLISHED.name(), firstFormVersion.getStatus());

		Assert.assertNotNull(
			_pageConfigurationLocalService.fetchPageConfiguration(
				campaign.getCampaignId(), PageStage.PUBLISHED));

		_publish(campaign);

		Assert.assertEquals(
			firstFormVersion.getFormVersionId(),
			_formLocalService.getCampaignForm(
				campaign.getCampaignId()
			).getPublishedVersionId());

		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, campaign);

		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(),
			CampaignTestUtil.field("empresa", "text", false));

		_publish(campaign);

		form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		FormVersion secondFormVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		Assert.assertEquals(2, secondFormVersion.getVersion());
		Assert.assertEquals(
			FormVersionStatus.SUPERSEDED.name(),
			_formVersionLocalService.getFormVersion(
				firstFormVersion.getFormVersionId()
			).getStatus());
		Assert.assertEquals(
			3,
			_formFieldLocalService.getFormFields(
				secondFormVersion.getFormVersionId()
			).size());
	}

	@Test
	public void testUnknownNotificationPlaceholderBlocksPublication()
		throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		_addFields(campaign);

		_notificationConfigLocalService.addNotificationConfig(
			TestPropsValues.getUserId(), campaign.getCampaignId(),
			new NotificationConfigInput(
			).setName(
				"Confirmação"
			).setEventType(
				EventType.SUBMISSION_CREATED
			).setChannel(
				NotificationChannelType.EMAIL
			).setEnabled(
				true
			).setRecipients(
				"${submission.nome}"
			).setSubjectTemplate(
				"Inscrição em ${campaign.name} - ${campaign.cidade}"
			).setBodyTemplate(
				"Olá ${submission.nome}, ${submission.inexistente}"
			));

		List<PublicationIssue> publicationIssues =
			_campaignPublicationService.getPublicationIssues(
				campaign.getCampaignId());

		Assert.assertTrue(
			publicationIssues.toString(),
			publicationIssues.contains(
				new PublicationIssue(
					"notification-placeholder-is-unknown",
					"${submission.inexistente}")));
		Assert.assertTrue(
			publicationIssues.toString(),
			publicationIssues.contains(
				new PublicationIssue(
					"notification-placeholder-is-unknown",
					"${campaign.cidade}")));
		Assert.assertTrue(
			publicationIssues.toString(),
			publicationIssues.contains(
				new PublicationIssue(
					"notification-recipient-field-must-be-email",
					"${submission.nome}")));
		Assert.assertEquals(
			publicationIssues.toString(), 3, publicationIssues.size());
	}

	private void _addFields(Campaign campaign) throws Exception {
		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, campaign);

		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(), CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(),
			CampaignTestUtil.emailField());
	}

	private Campaign _addPublishedCampaign(CampaignMode mode)
		throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), mode);

		_addFields(campaign);

		return _publish(campaign);
	}

	private void _assertIssue(Campaign campaign, String code)
		throws Exception {
		List<PublicationIssue> publicationIssues =
			_campaignPublicationService.getPublicationIssues(
				campaign.getCampaignId());

		for (PublicationIssue publicationIssue : publicationIssues) {
			if (code.equals(publicationIssue.getCode())) {
				return;
			}
		}

		Assert.fail("Expected " + code + " in " + publicationIssues);
	}

	private void _assertStatusException(UnsafeRunnable unsafeRunnable)
		throws Exception {
		try {
			unsafeRunnable.run();

			Assert.fail("Expected CampaignStatusException");
		}
		catch (CampaignStatusException campaignStatusException) {
		}
	}

	private List<String> _getBlockTypes(Campaign campaign, PageStage stage)
		throws Exception {
		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), stage);

		List<String> blockTypes = new ArrayList<>();

		for (PageBlock pageBlock :
				_pageBlockLocalService.getPageBlocks(
					pageConfiguration.getPageConfigurationId())) {
			blockTypes.add(pageBlock.getBlockType());
		}

		return blockTypes;
	}

	private Campaign _publish(Campaign campaign) throws Exception {
		return _campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());
	}

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignModeService _campaignModeService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@Inject
	private FormVersionLocalService _formVersionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Inject
	private PageBlockLocalService _pageBlockLocalService;

	@Inject
	private PageConfigurationLocalService _pageConfigurationLocalService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

	@FunctionalInterface
	private interface UnsafeRunnable {
		public void run() throws Exception;
	}

}
