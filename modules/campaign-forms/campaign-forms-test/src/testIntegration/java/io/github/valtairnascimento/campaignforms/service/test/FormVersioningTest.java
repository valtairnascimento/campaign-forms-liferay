package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.exception.DuplicateFieldKeyException;
import io.github.valtairnascimento.campaignforms.exception.FieldKeyException;
import io.github.valtairnascimento.campaignforms.exception.FormVersionLockedException;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldOptionLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
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
public class FormVersioningTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		_form = _formLocalService.getCampaignForm(_campaign.getCampaignId());
	}

	@After
	public void tearDown() throws Exception {
		_campaignLocalService.deleteCampaign(_campaign.getCampaignId());
	}

	@Test
	public void testAddFieldsAndOptionsInDraft() throws Exception {
		long draftVersionId = _form.getDraftVersionId();

		_formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.emailField());

		FormField selectFormField = _formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.selectField());

		List<FormField> formFields = _formFieldLocalService.getFormFields(
			draftVersionId);

		Assert.assertEquals(3, formFields.size());
		Assert.assertEquals(2, selectFormField.getPosition());

		List<FormFieldOption> formFieldOptions =
			_formFieldOptionLocalService.getFormFieldOptions(
				selectFormField.getFormFieldId());

		Assert.assertEquals(2, formFieldOptions.size());

		FormFieldOption formFieldOption = formFieldOptions.get(1);

		Assert.assertEquals("londrina", formFieldOption.getValue());
	}

	@Test(expected = DuplicateFieldKeyException.class)
	public void testDuplicateFieldKeyFails() throws Exception {
		_formFieldLocalService.addFormField(
			_form.getDraftVersionId(), CampaignTestUtil.emailField());
		_formFieldLocalService.addFormField(
			_form.getDraftVersionId(), CampaignTestUtil.emailField());
	}

	@Test
	public void testNewDraftCopiesFieldsAndPublishSupersedesPrevious()
		throws Exception {
		long userId = TestPropsValues.getUserId();

		long version1Id = _form.getDraftVersionId();

		_formFieldLocalService.addFormField(
			version1Id, CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			version1Id, CampaignTestUtil.selectField());

		_formLocalService.publishDraftFormVersion(userId, _form.getFormId());

		FormVersion version2 = _formLocalService.getOrCreateDraftFormVersion(
			userId, _form.getFormId());

		Assert.assertEquals(2, version2.getVersion());
		Assert.assertNotEquals(version1Id, version2.getFormVersionId());

		List<FormField> copiedFormFields = _formFieldLocalService.getFormFields(
			version2.getFormVersionId());

		Assert.assertEquals(2, copiedFormFields.size());

		FormField copiedSelectFormField = copiedFormFields.get(1);

		Assert.assertEquals("cidade", copiedSelectFormField.getFieldKey());
		Assert.assertEquals(
			2,
			_formFieldOptionLocalService.getFormFieldOptions(
				copiedSelectFormField.getFormFieldId()
			).size());

		Assert.assertEquals(
			version2.getFormVersionId(),
			_formLocalService.getOrCreateDraftFormVersion(
				userId, _form.getFormId()
			).getFormVersionId());

		_formFieldLocalService.addFormField(
			version2.getFormVersionId(), CampaignTestUtil.emailField());

		Form form = _formLocalService.publishDraftFormVersion(
			userId, _form.getFormId());

		Assert.assertEquals(
			version2.getFormVersionId(), form.getPublishedVersionId());
		Assert.assertEquals(0, form.getDraftVersionId());

		FormVersion version1 = _formVersionLocalService.getFormVersion(
			version1Id);

		Assert.assertEquals(
			FormVersionStatus.SUPERSEDED.name(), version1.getStatus());

		Assert.assertEquals(
			2, _formFieldLocalService.getFormFields(version1Id).size());
		Assert.assertEquals(
			3,
			_formFieldLocalService.getFormFields(
				version2.getFormVersionId()
			).size());
	}

	@Test
	public void testPublishedVersionIsImmutable() throws Exception {
		FormField formField = _formFieldLocalService.addFormField(
			_form.getDraftVersionId(), CampaignTestUtil.nameField());

		_formLocalService.publishDraftFormVersion(
			TestPropsValues.getUserId(), _form.getFormId());

		try {
			_formFieldLocalService.updateFormField(
				formField.getFormFieldId(),
				CampaignTestUtil.nameField(
				).setLabel(
					"Nome completo"
				));

			Assert.fail();
		}
		catch (FormVersionLockedException formVersionLockedException) {
		}

		try {
			_formFieldLocalService.deleteFormField(formField.getFormFieldId());

			Assert.fail();
		}
		catch (FormVersionLockedException formVersionLockedException) {
		}

		try {
			_formFieldLocalService.addFormField(
				formField.getFormVersionId(), CampaignTestUtil.emailField());

			Assert.fail();
		}
		catch (FormVersionLockedException formVersionLockedException) {
		}
	}

	@Test(expected = FieldKeyException.class)
	public void testReservedFieldKeyFails() throws Exception {
		_formFieldLocalService.addFormField(
			_form.getDraftVersionId(),
			CampaignTestUtil.nameField(
			).setFieldKey(
				"submissionId"
			));
	}

	@Test
	public void testReorderAndDeleteCompactPositions() throws Exception {
		long draftVersionId = _form.getDraftVersionId();

		FormField nameFormField = _formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.nameField());
		FormField emailFormField = _formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.emailField());
		FormField selectFormField = _formFieldLocalService.addFormField(
			draftVersionId, CampaignTestUtil.selectField());

		List<FormField> formFields = _formFieldLocalService.reorderFormFields(
			draftVersionId,
			new long[] {
				selectFormField.getFormFieldId(), nameFormField.getFormFieldId(),
				emailFormField.getFormFieldId()
			});

		Assert.assertEquals("cidade", formFields.get(0).getFieldKey());

		_formFieldLocalService.deleteFormField(nameFormField.getFormFieldId());

		formFields = _formFieldLocalService.getFormFields(draftVersionId);

		Assert.assertEquals(2, formFields.size());
		Assert.assertEquals(0, formFields.get(0).getPosition());
		Assert.assertEquals(1, formFields.get(1).getPosition());
		Assert.assertEquals("email", formFields.get(1).getFieldKey());
	}

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	private Form _form;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormFieldOptionLocalService _formFieldOptionLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@Inject
	private FormVersionLocalService _formVersionLocalService;

	@DeleteAfterTestRun
	private Group _group;
}
