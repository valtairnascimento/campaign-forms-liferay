package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.ConsentVersionUtil;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.ApiTestUtil;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DeliveryApiTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		FormVersion formVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		_formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), CampaignTestUtil.emailField());
		_formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), CampaignTestUtil.selectField());

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testArchivedCampaignIsClosed() throws Exception {
		String formToken = _getFormToken();

		_campaignPublicationService.archive(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		JSONObject jsonObject = _getCampaign();

		Assert.assertFalse(jsonObject.getBoolean("acceptingSubmissions"));
		Assert.assertNotNull(jsonObject.getString("closedMessage"));

		ApiTestUtil.Response response = _submit(_validBody(formToken));

		Assert.assertEquals(response.toString(), 409, response.getStatus());
		Assert.assertEquals(
			"CAMPAIGN_CLOSED",
			response.getJSONObject(
			).getString(
				"code"
			));
	}

	@Test
	public void testDraftCampaignIsNotFound() throws Exception {
		Campaign draftCampaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

		ApiTestUtil.Response response = ApiTestUtil.get(
			_campaignPath(draftCampaign.getSlug()), null, null);

		Assert.assertEquals(response.toString(), 404, response.getStatus());

		JSONObject jsonObject = response.getJSONObject();

		Assert.assertEquals("CAMPAIGN_NOT_FOUND", jsonObject.getString("code"));
		Assert.assertEquals(
			"Captação não encontrada.", jsonObject.getString("message"));
	}

	@Test
	public void testGuestCanReadPublishedCampaign() throws Exception {
		JSONObject jsonObject = _getCampaign();

		Assert.assertTrue(jsonObject.getBoolean("acceptingSubmissions"));
		Assert.assertEquals(_campaign.getSlug(), jsonObject.getString("slug"));

		JSONObject formJSONObject = jsonObject.getJSONObject("form");

		Assert.assertNotNull(formJSONObject.getString("formToken"));

		Assert.assertTrue(formJSONObject.getBoolean("consentRequired"));
		Assert.assertEquals(
			CampaignTestUtil.CONSENT_URL, formJSONObject.getString("consentURL"));
		Assert.assertTrue(
			formJSONObject.getString(
				"consentVersion"
			).startsWith(
				"auto-"
			));
		Assert.assertTrue(
			formJSONObject.getString(
				"honeypotFieldName"
			).startsWith(
				"website_"
			));

		JSONArray fieldsJSONArray = formJSONObject.getJSONArray("fields");

		Assert.assertEquals(3, fieldsJSONArray.length());

		JSONObject cityJSONObject = fieldsJSONArray.getJSONObject(2);

		Assert.assertEquals("cidade", cityJSONObject.getString("fieldKey"));
		Assert.assertEquals(
			2,
			cityJSONObject.getJSONArray(
				"options"
			).length());

		Assert.assertFalse(jsonObject.has("status"));
		Assert.assertFalse(jsonObject.has("settings"));
		Assert.assertFalse(jsonObject.has("siteId"));
	}

	@Test
	public void testGuestCannotUseAdminApi() throws Exception {
		ApiTestUtil.Response response = ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + _campaign.getCampaignId(),
			null, null);

		Assert.assertTrue(
			response.toString(),
			(response.getStatus() == 401) || (response.getStatus() == 403));
	}

	@Test
	public void testInvalidSubmissionReturnsFieldErrors() throws Exception {
		ApiTestUtil.Response response = _submit(
			"{\"formToken\": \"" + _getFormToken() +
				"\", \"values\": {\"email\": \"maria@\"}}");

		Assert.assertEquals(response.toString(), 422, response.getStatus());

		JSONObject jsonObject = response.getJSONObject();

		Assert.assertEquals(
			"FORM_VALIDATION_ERROR", jsonObject.getString("code"));

		JSONArray errorsJSONArray = jsonObject.getJSONArray("errors");

		Assert.assertEquals(
			errorsJSONArray.toString(), 3, errorsJSONArray.length());
		Assert.assertTrue(
			errorsJSONArray.toString(),
			errorsJSONArray.toString(
			).contains(
				"CONSENT_REQUIRED"
			));

		JSONObject errorJSONObject = errorsJSONArray.getJSONObject(0);

		Assert.assertEquals("nome", errorJSONObject.getString("field"));
		Assert.assertEquals("REQUIRED", errorJSONObject.getString("code"));
		Assert.assertEquals(
			"Preencha este campo.", errorJSONObject.getString("message"));
	}

	@Test
	public void testSubmissionIsCreatedAndIdempotent() throws Exception {
		String formToken = _getFormToken();
		String idempotencyKey = UUID.randomUUID(
		).toString();

		ApiTestUtil.Response response = _submit(
			_validBody(formToken), "Idempotency-Key", idempotencyKey);

		Assert.assertEquals(response.toString(), 201, response.getStatus());

		JSONObject jsonObject = response.getJSONObject();

		Assert.assertTrue(jsonObject.getBoolean("success"));

		long submissionId = jsonObject.getLong("submissionId");

		Assert.assertEquals(
			_campaign.getCampaignId(),
			_submissionLocalService.getSubmission(
				submissionId
			).getCampaignId());

		ApiTestUtil.Response duplicateResponse = _submit(
			_validBody(formToken), "Idempotency-Key", idempotencyKey);

		Assert.assertEquals(
			submissionId,
			duplicateResponse.getJSONObject(
			).getLong(
				"submissionId"
			));
		Assert.assertEquals(
			1,
			_submissionLocalService.getCampaignSubmissionsCount(
				_campaign.getCampaignId()));
	}

	@Test
	public void testUnknownFieldIsRejected() throws Exception {
		ApiTestUtil.Response response = _submit(
			"{\"formToken\": \"" + _getFormToken() +
				"\", \"values\": {\"nome\": \"Maria\", \"status\": \"X\"}}");

		Assert.assertEquals(response.toString(), 400, response.getStatus());
		Assert.assertEquals(
			"UNKNOWN_FIELD",
			response.getJSONObject(
			).getString(
				"code"
			));

		response = _submit("{\"values\": {}, \"userId\": 1}");

		Assert.assertEquals(response.toString(), 400, response.getStatus());
		Assert.assertEquals(
			"INVALID_REQUEST",
			response.getJSONObject(
			).getString(
				"code"
			));
	}

	private String _campaignPath(String slug) {
		return ApiTestUtil.DELIVERY_PATH + "/sites/" + _group.getGroupId() +
			"/campaigns/by-slug/" + slug;
	}

	private JSONObject _getCampaign() throws Exception {
		ApiTestUtil.Response response = ApiTestUtil.get(
			_campaignPath(_campaign.getSlug()), null, null);

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		return response.getJSONObject();
	}

	private String _getFormToken() throws Exception {
		return CampaignTestUtil.submissionCommand(
			_formTokenManager,
			_formVersionLocalService.getFormVersion(
				_formLocalService.getCampaignForm(
					_campaign.getCampaignId()
				).getPublishedVersionId())
		).getFormToken();
	}

	private ApiTestUtil.Response _submit(String body, String... headers)
		throws Exception {
		return ApiTestUtil.post(
			_campaignPath(_campaign.getSlug()) + "/submissions", body, null,
			null, headers);
	}

	private String _validBody(String formToken) throws Exception {
		return "{\"formToken\": \"" + formToken +
			"\", \"values\": {\"nome\": \"Maria\", \"email\": " +
				"\"maria@example.org\", \"cidade\": \"londrina\"}, " +
					"\"consent\": {\"accepted\": true, \"version\": \"" +
						ConsentVersionUtil.getVersion(
							_formVersionLocalService.getFormVersion(
								_formLocalService.getCampaignForm(
									_campaign.getCampaignId()
								).getPublishedVersionId())) + "\"}}";
	}

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@Inject
	private FormTokenManager _formTokenManager;

	@Inject
	private FormVersionLocalService _formVersionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private SubmissionLocalService _submissionLocalService;
}
