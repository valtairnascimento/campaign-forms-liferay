package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignDuplicationService;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CampaignDuplicationTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_otherGroup = GroupTestUtil.addGroup();

		_sourceCampaign = _campaignLocalService.addCampaign(
			TestPropsValues.getUserId(), _group.getGroupId(),
			CampaignTestUtil.campaignInput(
				CampaignMode.CUSTOM_PAGE, CampaignTestUtil.randomSlug()
			).setSettings(
				"{\"attributes\":{\"city\":\"Curitiba\"}}"
			).setSubmissionLimit(
				100
			),
			_getServiceContext(_group));

		CampaignTestUtil.withConsent(_sourceCampaign);

		FormVersion draftFormVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _sourceCampaign);

		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(), CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			draftFormVersion.getFormVersionId(),
			CampaignTestUtil.emailField());

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				_sourceCampaign.getCampaignId(), PageStage.DRAFT);

		pageConfiguration.setSeoTitle("Encontro RH Curitiba");

		_pageConfigurationLocalService.updatePageConfiguration(
			pageConfiguration);

		_pageBlockLocalService.addPageBlock(
			pageConfiguration.getPageConfigurationId(),
			CampaignConstants.BLOCK_TYPE_HERO, "{\"title\":\"Encontro RH\"}",
			true);
		_pageBlockLocalService.addPageBlock(
			pageConfiguration.getPageConfigurationId(),
			CampaignConstants.BLOCK_TYPE_FORM, "{}", true);

		_addNotification("Equipe", "rh.curitiba@example.org");
		_addNotification("Confirmação", "${submission.email}");

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _sourceCampaign.getCampaignId());

		_formSubmissionService.submit(
			_group.getGroupId(), _sourceCampaign.getSlug(),
			CampaignTestUtil.submissionCommand(
				_formTokenManager, _getPublishedFormVersion(_sourceCampaign)
			).putValue(
				"nome", "Maria"
			).putValue(
				"email", "maria@example.org"
			));
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId(), _otherGroup.getGroupId());
	}

	@Test
	public void testDuplicateCopiesStructureButNotSubmissions()
		throws Exception {
		String slug = CampaignTestUtil.randomSlug();

		Campaign campaign = _duplicate(
			new DuplicationRequest(
			).setName(
				"Encontro RH Londrina"
			).setSlug(
				slug
			).setInstanceKey(
				"londrina"
			).putAttribute(
				"city", "Londrina"
			).setNotificationOverrides(
				"rh.londrina@example.org", "Nova inscrição - Londrina"
			));

		Assert.assertNotEquals(
			_sourceCampaign.getCampaignId(), campaign.getCampaignId());
		Assert.assertNotEquals(_sourceCampaign.getUuid(), campaign.getUuid());
		Assert.assertEquals(
			_sourceCampaign.getCampaignId(), campaign.getSourceCampaignId());
		Assert.assertEquals(CampaignStatus.DRAFT.name(), campaign.getStatus());
		Assert.assertEquals(CampaignMode.CUSTOM_PAGE.name(), campaign.getMode());
		Assert.assertEquals(slug, campaign.getSlug());
		Assert.assertEquals(
			_sourceCampaign.getSeriesKey(), campaign.getSeriesKey());
		Assert.assertEquals("londrina", campaign.getInstanceKey());
		Assert.assertEquals(100, campaign.getSubmissionLimit());
		Assert.assertNull(campaign.getPublishedDate());

		JSONObject settingsJSONObject = JSONFactoryUtil.createJSONObject(
			campaign.getSettings());

		Assert.assertEquals(
			"Londrina",
			settingsJSONObject.getJSONObject(
				"attributes"
			).getString(
				"city"
			));

		Form form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		Assert.assertEquals(0, form.getPublishedVersionId());

		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			form.getDraftVersionId());

		Assert.assertEquals(1, formVersion.getVersion());
		Assert.assertEquals(
			"[nome, email]", _getFieldKeys(formVersion).toString());

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		Assert.assertEquals(
			"Encontro RH Curitiba", pageConfiguration.getSeoTitle());

		List<String> blockTypes = new ArrayList<>();

		for (PageBlock pageBlock :
				_pageBlockLocalService.getPageBlocks(
					pageConfiguration.getPageConfigurationId())) {
			blockTypes.add(pageBlock.getBlockType());
		}

		Assert.assertEquals("[HERO, FORM]", blockTypes.toString());

		Map<String, NotificationConfig> notificationConfigs = new HashMap<>();

		for (NotificationConfig notificationConfig :
				_notificationConfigLocalService.getCampaignNotificationConfigs(
					campaign.getCampaignId())) {
			notificationConfigs.put(
				notificationConfig.getName(), notificationConfig);
		}

		Assert.assertEquals(
			notificationConfigs.keySet(
			).toString(),
			3, notificationConfigs.size());

		NotificationConfig teamNotificationConfig = notificationConfigs.get(
			"Equipe");

		Assert.assertEquals(
			"rh.londrina@example.org", teamNotificationConfig.getRecipients());
		Assert.assertEquals(
			"Nova inscrição - Londrina",
			teamNotificationConfig.getSubjectTemplate());

		NotificationConfig confirmationNotificationConfig =
			notificationConfigs.get("Confirmação");

		Assert.assertEquals(
			"${submission.email}",
			confirmationNotificationConfig.getRecipients());
		Assert.assertEquals(
			"Inscrição em ${campaign.name}",
			confirmationNotificationConfig.getSubjectTemplate());

		Assert.assertEquals(
			0,
			_submissionLocalService.getCampaignSubmissionsCount(
				campaign.getCampaignId()));
		Assert.assertEquals(
			1,
			_submissionLocalService.getCampaignSubmissionsCount(
				_sourceCampaign.getCampaignId()));

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), campaign.getCampaignId());
	}

	@Test
	public void testDuplicateToAnotherSiteWithoutFieldsAndSeo()
		throws Exception {
		Campaign campaign = _duplicate(
			new DuplicationRequest(
			).setTargetGroupId(
				_otherGroup.getGroupId()
			).setSlug(
				_sourceCampaign.getSlug()
			).setCopyFields(
				false
			).setCopySeo(
				false
			).setCopySettings(
				false
			));

		Assert.assertEquals(_otherGroup.getGroupId(), campaign.getGroupId());
		Assert.assertEquals(_sourceCampaign.getSlug(), campaign.getSlug());
		Assert.assertEquals(_sourceCampaign.getName(), campaign.getName());
		Assert.assertEquals(0, campaign.getSubmissionLimit());

		Form form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		Assert.assertTrue(
			_getFieldKeys(
				_formVersionLocalService.getFormVersion(
					form.getDraftVersionId())
			).isEmpty());

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		Assert.assertEquals("", pageConfiguration.getSeoTitle());
		Assert.assertEquals(
			2,
			_pageBlockLocalService.getPageBlocks(
				pageConfiguration.getPageConfigurationId()
			).size());
	}

	private void _addNotification(String name, String recipients)
		throws Exception {
		_notificationConfigLocalService.addNotificationConfig(
			TestPropsValues.getUserId(), _sourceCampaign.getCampaignId(),
			new NotificationConfigInput(
			).setName(
				name
			).setEventType(
				EventType.SUBMISSION_CREATED
			).setChannel(
				NotificationChannelType.EMAIL
			).setEnabled(
				true
			).setRecipients(
				recipients
			).setSubjectTemplate(
				"Inscrição em ${campaign.name}"
			).setBodyTemplate(
				"${submission.table}"
			));
	}

	private Campaign _duplicate(DuplicationRequest duplicationRequest)
		throws Exception {
		long groupId = duplicationRequest.getTargetGroupId();

		if (groupId <= 0) {
			groupId = _group.getGroupId();
		}

		return _campaignDuplicationService.duplicate(
			TestPropsValues.getUserId(), _sourceCampaign.getCampaignId(),
			duplicationRequest,
			_getServiceContext(_getGroup(groupId)));
	}

	private List<String> _getFieldKeys(FormVersion formVersion) {
		List<String> fieldKeys = new ArrayList<>();

		for (FormField formField :
				_formFieldLocalService.getFormFields(
					formVersion.getFormVersionId())) {
			fieldKeys.add(formField.getFieldKey());
		}

		return fieldKeys;
	}

	private Group _getGroup(long groupId) {
		if (groupId == _otherGroup.getGroupId()) {
			return _otherGroup;
		}

		return _group;
	}

	private FormVersion _getPublishedFormVersion(Campaign campaign)
		throws Exception {
		Form form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		return _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());
	}

	private ServiceContext _getServiceContext(Group group) throws Exception {
		return ServiceContextTestUtil.getServiceContext(
			group.getGroupId(), TestPropsValues.getUserId());
	}

	@Inject
	private CampaignDuplicationService _campaignDuplicationService;

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
	private NotificationConfigLocalService _notificationConfigLocalService;

	@DeleteAfterTestRun
	private Group _otherGroup;

	@Inject
	private PageBlockLocalService _pageBlockLocalService;

	@Inject
	private PageConfigurationLocalService _pageConfigurationLocalService;

	private Campaign _sourceCampaign;

	@Inject
	private SubmissionLocalService _submissionLocalService;
}
