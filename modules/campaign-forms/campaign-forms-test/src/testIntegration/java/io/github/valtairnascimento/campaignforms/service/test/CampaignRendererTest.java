package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.input.CampaignInput;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.ApiTestUtil;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CampaignRendererTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Layout layout = _layoutLocalService.addLayout(
			TestPropsValues.getUserId(), _group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, "Captacao", "Captação",
			"", LayoutConstants.TYPE_PORTLET, true, "/campaign",
			serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.addPortletId(
			TestPropsValues.getUserId(), _PORTLET_ID, false);

		_layoutLocalService.updateLayout(
			_group.getGroupId(), false, layout.getLayoutId(),
			layout.getTypeSettings());

		_campaign = _campaignLocalService.addCampaign(
			TestPropsValues.getUserId(), _group.getGroupId(),
			new CampaignInput(
			).setMode(
				CampaignMode.QUICK
			).setName(
				"RH <b>& Cia</b>"
			).setDescription(
				"Encontro regional <script>alert(1)</script>"
			).setSlug(
				CampaignTestUtil.randomSlug()
			),
			serviceContext);

		CampaignTestUtil.withConsent(_campaign);

		FormVersion formVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		long formVersionId = formVersion.getFormVersionId();

		_formFieldLocalService.addFormField(
			formVersionId,
			CampaignTestUtil.nameField(
			).setHelpText(
				"Como no documento"
			));
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
	public void testArchivedCampaignShowsClosedMessage() throws Exception {
		_campaignPublicationService.archive(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		String html = _getPage(_campaign.getSlug());

		Assert.assertTrue(
			html, html.contains("This campaign is not accepting responses."));
		Assert.assertFalse(html, html.contains("class=\"campaign-forms-form\""));
	}

	@Test
	public void testFormIsRenderedAccessiblyAndEscaped() throws Exception {
		String html = _getPage(_campaign.getSlug());

		Assert.assertTrue(html, html.contains("class=\"campaign-forms-form\""));

		Assert.assertTrue(html, html.contains("RH &lt;b&gt;&amp; Cia&lt;/b&gt;"));

		Assert.assertTrue(html, html.contains("name=\"consent\""));
		Assert.assertTrue(
			html,
			html.contains(
				"<a href=\"" + CampaignTestUtil.CONSENT_URL +
					"\" rel=\"noopener\" target=\"_blank\">"));
		Assert.assertFalse(html, html.contains("<script>alert(1)</script>"));

		String inputId = _getInputId(html, "email");

		Assert.assertTrue(html, html.contains("for=\"" + inputId + "\""));
		Assert.assertTrue(html, html.contains("type=\"email\""));
		Assert.assertTrue(html, html.contains("Como no documento"));
		Assert.assertTrue(html, html.contains("aria-describedby="));
		Assert.assertTrue(html, html.contains("(required)"));

		Assert.assertTrue(html, html.contains("<fieldset"));
		Assert.assertTrue(html, html.contains("<legend"));
		Assert.assertTrue(html, html.contains("value=\"inovacao\""));

		Assert.assertTrue(html, html.contains("name=\"website_"));

		Assert.assertTrue(
			html,
			html.contains("%2Fo%2Fcampaign-forms-web%2Fjs%2Fcampaign-form.js"));

		ApiTestUtil.Response response = ApiTestUtil.get(
			"/o/campaign-forms-web/js/campaign-form.js", null, null);

		Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void testRenderedFormTokenAcceptsSubmission() throws Exception {
		JSONObject configJSONObject = _getConfig(
			_getPage(_campaign.getSlug()));

		Assert.assertEquals(
			"/o/campaign-forms/v1.0/sites/" + _group.getGroupId() +
				"/campaigns/by-slug/" + _campaign.getSlug() + "/submissions",
			configJSONObject.getString("submissionUrl"));
		Assert.assertEquals(
			4,
			configJSONObject.getJSONArray(
				"fields"
			).length());
		Assert.assertEquals(
			"This field is required.",
			configJSONObject.getJSONObject(
				"messages"
			).getJSONObject(
				"errors"
			).getString(
				"REQUIRED"
			));

		Thread.sleep(2100);

		ApiTestUtil.Response response = ApiTestUtil.post(
			configJSONObject.getString("submissionUrl"),
			"{\"formToken\": \"" + configJSONObject.getString("formToken") +
				"\", \"values\": {\"nome\": \"Maria\", \"email\": " +
					"\"maria@example.org\", \"interesses\": [\"gestao\"]}, " +
						"\"consent\": {\"accepted\": true, \"version\": \"" +
							configJSONObject.getString("consentVersion") + "\"}}",
			null, null);

		Assert.assertEquals(response.toString(), 201, response.getStatus());
		Assert.assertEquals(
			1,
			_submissionLocalService.getCampaignSubmissionsCount(
				_campaign.getCampaignId()));
	}

	@Test
	public void testUnknownSlugShowsNotFound() throws Exception {
		String html = _getPage("nao-existe");

		Assert.assertTrue(html, html.contains("campaign-forms__not-found"));
		Assert.assertTrue(
			html, html.contains("This campaign is not available."));
	}

	private JSONObject _getConfig(String html) throws Exception {
		Matcher matcher = _configPattern.matcher(html);

		Assert.assertTrue(html, matcher.find());

		String json = StringUtil.replace(
			matcher.group(1),
			new String[] {"&quot;", "&#39;", "&lt;", "&gt;", "&amp;"},
			new String[] {"\"", "'", "<", ">", "&"});

		return JSONFactoryUtil.createJSONObject(json);
	}

	private String _getInputId(String html, String fieldKey) {
		Matcher matcher = Pattern.compile(
			"id=\"(\\w+_" + fieldKey + ")\""
		).matcher(
			html
		);

		Assert.assertTrue(html, matcher.find());

		return matcher.group(1);
	}

	private String _getPage(String slug) throws Exception {
		ApiTestUtil.Response response = ApiTestUtil.get(
			"/web" + _group.getFriendlyURL() + "/campaign/-/campaign/" + slug,
			null, null);

		Assert.assertEquals(
			response.getBody(
			).substring(
				0, Math.min(500, response.getBody().length())
			),
			200, response.getStatus());

		return response.getBody();
	}

	private static final String _PORTLET_ID =
		"io_github_valtairnascimento_campaignforms_web_portlet_CampaignRendererPortlet";

	private static final Pattern _configPattern = Pattern.compile(
		"data-campaign-forms-config=\"([^\"]*)\"");

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private SubmissionLocalService _submissionLocalService;
}
