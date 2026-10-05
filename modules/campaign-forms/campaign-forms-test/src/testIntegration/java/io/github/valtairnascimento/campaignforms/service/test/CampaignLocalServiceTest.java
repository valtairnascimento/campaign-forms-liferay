package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.CampaignStatus;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.exception.CampaignHasSubmissionsException;
import io.github.valtairnascimento.campaignforms.exception.CampaignSlugException;
import io.github.valtairnascimento.campaignforms.exception.DuplicateSlugException;
import io.github.valtairnascimento.campaignforms.model.AuditEntry;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CampaignLocalServiceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_otherGroup = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		for (long groupId :
				new long[] {_group.getGroupId(), _otherGroup.getGroupId()}) {
			for (Campaign campaign :
					_campaignLocalService.getGroupCampaigns(
						groupId, -1, -1, null)) {
				for (Submission submission :
						_submissionLocalService.getCampaignSubmissions(
							campaign.getCampaignId(), -1, -1)) {
					_submissionLocalService.deleteSubmission(
						submission.getSubmissionId());
				}

				_campaignLocalService.deleteCampaign(campaign.getCampaignId());
			}
		}
	}

	@Test
	public void testAddCustomPageCampaignHasNoDefaultTemplate()
		throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(),
			CampaignMode.CUSTOM_PAGE);

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		Assert.assertEquals(
			StringPool.BLANK, pageConfiguration.getTemplateKey());
	}

	@Test
	public void testAddQuickCampaignCreatesDraftFormAndPage()
		throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		Assert.assertEquals(CampaignStatus.DRAFT.name(), campaign.getStatus());
		Assert.assertEquals(campaign.getUuid(), campaign.getExternalReferenceCode());
		Assert.assertEquals(
			_group.getDefaultLanguageId(), campaign.getDefaultLanguageId());

		Form form = _formLocalService.getCampaignForm(campaign.getCampaignId());

		Assert.assertEquals(CampaignConstants.DEFAULT_FORM_KEY, form.getFormKey());
		Assert.assertEquals(0, form.getPublishedVersionId());

		FormVersion draftFormVersion = _formVersionLocalService.getFormVersion(
			form.getDraftVersionId());

		Assert.assertEquals(1, draftFormVersion.getVersion());
		Assert.assertEquals(
			FormVersionStatus.DRAFT.name(), draftFormVersion.getStatus());

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		Assert.assertEquals(
			CampaignConstants.TEMPLATE_KEY_QUICK_DEFAULT,
			pageConfiguration.getTemplateKey());
		Assert.assertNull(
			_pageConfigurationLocalService.fetchPageConfiguration(
				campaign.getCampaignId(), PageStage.PUBLISHED));

		List<AuditEntry> auditEntries =
			_auditEntryLocalService.getCampaignAuditEntries(
				campaign.getCampaignId(), -1, -1);

		Assert.assertEquals(auditEntries.toString(), 1, auditEntries.size());

		AuditEntry auditEntry = auditEntries.get(0);

		Assert.assertEquals(
			AuditAction.CAMPAIGN_ADDED.name(), auditEntry.getAction());
	}

	@Test
	public void testDeleteCampaignCascades() throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		long campaignId = campaign.getCampaignId();

		_campaignLocalService.deleteCampaign(campaignId);

		Assert.assertNull(_campaignLocalService.fetchCampaign(campaignId));
		Assert.assertTrue(
			_formLocalService.getCampaignForms(
				campaignId
			).isEmpty());
		Assert.assertTrue(
			_pageConfigurationLocalService.getCampaignPageConfigurations(
				campaignId
			).isEmpty());

		List<AuditEntry> auditEntries =
			_auditEntryLocalService.getCampaignAuditEntries(campaignId, -1, -1);

		AuditEntry auditEntry = auditEntries.get(0);

		Assert.assertEquals(
			AuditAction.CAMPAIGN_DELETED.name(), auditEntry.getAction());
	}

	@Test(expected = CampaignHasSubmissionsException.class)
	public void testDeleteCampaignWithSubmissionsFails() throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		CampaignTestUtil.addSubmission(_submissionLocalService, campaign);

		_campaignLocalService.deleteCampaign(campaign.getCampaignId());
	}

	@Test(expected = DuplicateSlugException.class)
	public void testDuplicateSlugInSameSiteFails() throws Exception {
		CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK,
			"encontro-rh-londrina");
		CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK,
			"encontro-rh-londrina");
	}

	@Test(expected = CampaignSlugException.class)
	public void testInvalidSlugFails() throws Exception {
		CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK,
			"Encontro RH");
	}

	@Test
	public void testSameSlugInAnotherSiteIsAllowed() throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK,
			"encontro-rh-londrina");
		Campaign otherCampaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _otherGroup.getGroupId(), CampaignMode.QUICK,
			"encontro-rh-londrina");

		Assert.assertNotEquals(
			campaign.getCampaignId(), otherCampaign.getCampaignId());
		Assert.assertEquals(
			otherCampaign,
			_campaignLocalService.fetchCampaignBySlug(
				_otherGroup.getGroupId(), "encontro-rh-londrina"));
	}

	@Test
	public void testUpdateCampaignKeepsIdAndAuditsChangedFields()
		throws Exception {
		Campaign campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		Campaign updatedCampaign = _campaignLocalService.updateCampaign(
			TestPropsValues.getUserId(), campaign.getCampaignId(),
			CampaignTestUtil.campaignInput(
				CampaignMode.QUICK, "novo-endereco"
			).setName(
				campaign.getName()
			),
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));

		Assert.assertEquals(
			campaign.getCampaignId(), updatedCampaign.getCampaignId());
		Assert.assertEquals("novo-endereco", updatedCampaign.getSlug());

		AuditEntry auditEntry = _auditEntryLocalService.getCampaignAuditEntries(
			campaign.getCampaignId(), 0, 1
		).get(
			0
		);

		Assert.assertEquals(
			AuditAction.CAMPAIGN_UPDATED.name(), auditEntry.getAction());
		Assert.assertTrue(
			auditEntry.getDetails(), auditEntry.getDetails().contains("slug"));
		Assert.assertFalse(
			auditEntry.getDetails(), auditEntry.getDetails().contains("\"name\""));
	}

	@Inject
	private AuditEntryLocalService _auditEntryLocalService;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@Inject
	private FormVersionLocalService _formVersionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private Group _otherGroup;

	@Inject
	private PageConfigurationLocalService _pageConfigurationLocalService;

	@Inject
	private SubmissionLocalService _submissionLocalService;
}
