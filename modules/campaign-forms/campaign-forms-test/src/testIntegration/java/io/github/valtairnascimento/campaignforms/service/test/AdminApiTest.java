package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.ApiTestUtil;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
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
public class AdminApiTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		User user = TestPropsValues.getUser();

		_adminLogin = user.getEmailAddress();
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testCampaignLifecycle() throws Exception {
		JSONObject campaignJSONObject = _addCampaign(
			CampaignTestUtil.randomSlug());

		long campaignId = campaignJSONObject.getLong("id");

		Assert.assertEquals("DRAFT", campaignJSONObject.getString("status"));
		Assert.assertEquals("QUICK", campaignJSONObject.getString("mode"));

		ApiTestUtil.Response response = _post(
			"/campaigns/" + campaignId + "/publish", null);

		Assert.assertEquals(response.toString(), 409, response.getStatus());

		JSONObject errorJSONObject = response.getJSONObject();

		Assert.assertEquals(
			"PUBLICATION_BLOCKED", errorJSONObject.getString("code"));
		Assert.assertEquals(
			"form-has-no-fields",
			errorJSONObject.getJSONArray(
				"errors"
			).getJSONObject(
				0
			).getString(
				"code"
			));

		long formFieldId = _addField(campaignId, "nome", "text");

		_addField(campaignId, "email", "email");

		JSONArray issuesJSONArray = _get(
			"/campaigns/" + campaignId + "/publication-issues"
		).getJSONObject(
		).getJSONArray(
			"items"
		);

		Assert.assertEquals(0, issuesJSONArray.length());

		response = _post("/campaigns/" + campaignId + "/publish", null);

		Assert.assertEquals(response.toString(), 200, response.getStatus());
		Assert.assertEquals(
			"PUBLISHED",
			response.getJSONObject(
			).getString(
				"status"
			));

		String fieldBody =
			"{\"fieldKey\": \"nome\", \"label\": \"Nome completo\", " +
				"\"type\": \"text\"}";

		response = _put("/form-fields/" + formFieldId, fieldBody);

		Assert.assertEquals(response.toString(), 409, response.getStatus());
		Assert.assertEquals(
			"VERSION_LOCKED",
			response.getJSONObject(
			).getString(
				"code"
			));

		JSONObject draftJSONObject = _post(
			"/campaigns/" + campaignId + "/form/draft", null
		).getJSONObject();

		Assert.assertEquals("DRAFT", draftJSONObject.getString("status"));
		Assert.assertEquals(2, draftJSONObject.getInt("version"));

		long draftFormFieldId = draftJSONObject.getJSONArray(
			"formFields"
		).getJSONObject(
			0
		).getLong(
			"id"
		);

		response = _put("/form-fields/" + draftFormFieldId, fieldBody);

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		Assert.assertTrue(
			_get(
				"/campaigns/" + campaignId
			).getJSONObject(
			).getBoolean(
				"hasUnpublishedChanges"
			));

		String duplicateSlug = CampaignTestUtil.randomSlug();

		response = _post(
			"/campaigns/" + campaignId + "/duplicate",
			"{\"slug\": \"" + duplicateSlug + "\", \"instanceKey\": " +
				"\"londrina\"}");

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		JSONObject duplicateJSONObject = response.getJSONObject();

		Assert.assertEquals(
			campaignId, duplicateJSONObject.getLong("sourceCampaignId"));
		Assert.assertEquals(
			duplicateSlug, duplicateJSONObject.getString("slug"));

		response = _post(
			"/campaigns/" + duplicateJSONObject.getLong("id") + "/publish",
			null);

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		JSONObject pageJSONObject = _get(
			"/sites/" + _group.getGroupId() + "/campaigns"
		).getJSONObject();

		Assert.assertEquals(2, pageJSONObject.getInt("totalCount"));
	}

	@Test
	public void testPermissionsReflectTheCurrentUser() throws Exception {
		long campaignId = _addCampaign(
			CampaignTestUtil.randomSlug()
		).getLong(
			"id"
		);

		JSONObject permissionsJSONObject = _get(
			"/campaigns/" + campaignId
		).getJSONObject(
		).getJSONObject(
			"permissions"
		);

		for (String actionId :
				new String[] {
					"DELETE", "DELETE_SUBMISSIONS", "EXPORT_SUBMISSIONS",
					"MANAGE_INTEGRATIONS", "MANAGE_NOTIFICATIONS", "PUBLISH",
					"UPDATE", "VIEW_SUBMISSIONS"
				}) {
			Assert.assertTrue(
				actionId, permissionsJSONObject.getBoolean(actionId));
		}

		Role siteMemberRole = _roleLocalService.getRole(
			_group.getCompanyId(), RoleConstants.SITE_MEMBER);

		_resourcePermissionLocalService.setResourcePermissions(
			_group.getCompanyId(), Campaign.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(campaignId),
			siteMemberRole.getRoleId(), new String[] {"VIEW"});

		User user = UserTestUtil.addUser(_group.getGroupId());

		_users.add(user);

		_userLocalService.updatePassword(
			user.getUserId(), _PASSWORD, _PASSWORD, false, true);

		ApiTestUtil.Response response = ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + campaignId,
			user.getEmailAddress(), _PASSWORD);

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		permissionsJSONObject = response.getJSONObject(
		).getJSONObject(
			"permissions"
		);

		Assert.assertFalse(permissionsJSONObject.getBoolean("UPDATE"));
		Assert.assertFalse(permissionsJSONObject.getBoolean("PUBLISH"));
		Assert.assertFalse(
			permissionsJSONObject.getBoolean("VIEW_SUBMISSIONS"));

		response = ApiTestUtil.post(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + campaignId + "/archive",
			null, user.getEmailAddress(), _PASSWORD);

		Assert.assertEquals(response.toString(), 403, response.getStatus());
	}

	@Test
	public void testSiteMemberWithoutPermissionIsForbidden() throws Exception {
		long campaignId = _addCampaign(
			CampaignTestUtil.randomSlug()
		).getLong(
			"id"
		);

		User user = UserTestUtil.addUser(_group.getGroupId());

		_users.add(user);

		_userLocalService.updatePassword(
			user.getUserId(), _PASSWORD, _PASSWORD, false, true);

		ApiTestUtil.Response response = ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + campaignId,
			user.getEmailAddress(), _PASSWORD);

		Assert.assertEquals(response.toString(), 403, response.getStatus());
		Assert.assertEquals(
			"FORBIDDEN",
			response.getJSONObject(
			).getString(
				"code"
			));

		response = ApiTestUtil.post(
			ApiTestUtil.ADMIN_PATH + "/sites/" + _group.getGroupId() +
				"/campaigns",
			"{\"name\": \"X\", \"slug\": \"" + CampaignTestUtil.randomSlug() +
				"\"}",
			user.getEmailAddress(), _PASSWORD);

		Assert.assertEquals(response.toString(), 403, response.getStatus());
	}

	@Test
	public void testValidationAndConflictErrors() throws Exception {
		ApiTestUtil.Response response = _post(
			"/sites/" + _group.getGroupId() + "/campaigns",
			"{\"name\": \"Campanha\", \"slug\": \"Slug Inválido\"}");

		Assert.assertEquals(response.toString(), 400, response.getStatus());

		JSONObject jsonObject = response.getJSONObject();

		Assert.assertEquals("VALIDATION_ERROR", jsonObject.getString("code"));
		Assert.assertTrue(
			jsonObject.getString("message"),
			jsonObject.getString(
				"message"
			).contains(
				"endereço"
			));

		String slug = CampaignTestUtil.randomSlug();

		_addCampaign(slug);

		response = _post(
			"/sites/" + _group.getGroupId() + "/campaigns",
			"{\"name\": \"Outra\", \"slug\": \"" + slug + "\"}");

		Assert.assertEquals(response.toString(), 409, response.getStatus());
		Assert.assertEquals(
			"DUPLICATE_SLUG",
			response.getJSONObject(
			).getString(
				"code"
			));

		response = _get("/campaigns/999999999");

		Assert.assertEquals(response.toString(), 404, response.getStatus());
		Assert.assertEquals(
			"CAMPAIGN_NOT_FOUND",
			response.getJSONObject(
			).getString(
				"code"
			));
	}

	private JSONObject _addCampaign(String slug) throws Exception {
		ApiTestUtil.Response response = _post(
			"/sites/" + _group.getGroupId() + "/campaigns",
			"{\"name\": \"Encontro RH\", \"slug\": \"" + slug +
				"\", \"seriesKey\": \"encontro-rh\"}");

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		return response.getJSONObject();
	}

	private long _addField(long campaignId, String fieldKey, String type)
		throws Exception {
		ApiTestUtil.Response response = _post(
			"/campaigns/" + campaignId + "/form/fields",
			"{\"fieldKey\": \"" + fieldKey + "\", \"label\": \"" + fieldKey +
				"\", \"type\": \"" + type + "\", \"required\": true}");

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		return response.getJSONObject(
		).getLong(
			"id"
		);
	}

	private ApiTestUtil.Response _get(String path) throws Exception {
		return ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + path, _adminLogin, _ADMIN_PASSWORD);
	}

	private ApiTestUtil.Response _post(String path, String body)
		throws Exception {
		return ApiTestUtil.post(
			ApiTestUtil.ADMIN_PATH + path, body, _adminLogin, _ADMIN_PASSWORD);
	}

	private ApiTestUtil.Response _put(String path, String body)
		throws Exception {
		return ApiTestUtil.put(
			ApiTestUtil.ADMIN_PATH + path, body, _adminLogin, _ADMIN_PASSWORD);
	}

	private static final String _ADMIN_PASSWORD = "test";

	private static final String _PASSWORD = "Campaign@Test123";

	private String _adminLogin;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Inject
	private RoleLocalService _roleLocalService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

	@Inject
	private UserLocalService _userLocalService;

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();
}
