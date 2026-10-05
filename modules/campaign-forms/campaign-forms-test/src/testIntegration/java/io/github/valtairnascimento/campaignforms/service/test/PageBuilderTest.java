package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.exception.PageSettingsException;
import io.github.valtairnascimento.campaignforms.input.PageSettingsInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.test.util.ApiTestUtil;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.List;
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
public class PageBuilderTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_otherGroup = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(),
			CampaignMode.CUSTOM_PAGE);

		_pageConfigurationId = _pageConfigurationLocalService.getPageConfiguration(
			_campaign.getCampaignId(), PageStage.DRAFT
		).getPageConfigurationId();
	}

	@After
	public void tearDown() throws Exception {
		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId());
	}

	@Test
	public void testAdminAPIBlockTypesAndTemplates() throws Exception {
		String login = TestPropsValues.getUser(
		).getEmailAddress();

		ApiTestUtil.Response response = ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + _campaign.getCampaignId() +
				"/block-types",
			login, _ADMIN_PASSWORD);

		Assert.assertEquals(response.toString(), 200, response.getStatus());

		JSONArray itemsJSONArray = response.getJSONObject(
		).getJSONArray(
			"items"
		);

		List<String> keys = new ArrayList<>();

		for (int i = 0; i < itemsJSONArray.length(); i++) {
			keys.add(
				itemsJSONArray.getJSONObject(
					i
				).getString(
					"key"
				));
		}

		Assert.assertEquals(
			"[HERO, TEXT, IMAGE, CTA, FAQ, FORM]", keys.toString());

		JSONObject heroJSONObject = itemsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			_campaign.getName(),
			heroJSONObject.getJSONObject(
				"defaultConfiguration"
			).getString(
				"title"
			));
		Assert.assertTrue(
			itemsJSONArray.getJSONObject(
				5
			).getBoolean(
				"singleton"
			));

		response = ApiTestUtil.get(
			ApiTestUtil.ADMIN_PATH + "/page-templates", login, _ADMIN_PASSWORD);

		Assert.assertEquals(
			"event",
			response.getJSONObject(
			).getJSONArray(
				"items"
			).getJSONObject(
				0
			).getString(
				"key"
			));

		response = ApiTestUtil.post(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + _campaign.getCampaignId() +
				"/page/apply-template",
			"{\"templateKey\": \"interest\"}", login, _ADMIN_PASSWORD);

		Assert.assertEquals(response.toString(), 200, response.getStatus());
		Assert.assertEquals(
			3,
			response.getJSONObject(
			).getJSONArray(
				"pageBlocks"
			).length());

		response = ApiTestUtil.post(
			ApiTestUtil.ADMIN_PATH + "/campaigns/" + _campaign.getCampaignId() +
				"/page/blocks",
			"{\"blockType\": \"CTA\", \"configuration\": {\"title\": \"Vem\", " +
				"\"buttonLabel\": \"Ir\", \"buttonUrl\": \"javascript:x\"}}",
			login, _ADMIN_PASSWORD);

		Assert.assertEquals(response.toString(), 400, response.getStatus());
		Assert.assertEquals(
			"buttonUrl",
			response.getJSONObject(
			).getJSONArray(
				"errors"
			).getJSONObject(
				0
			).getString(
				"field"
			));
	}

	@Test
	public void testApplyTemplate() throws Exception {
		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "TEXT", "{\"body\": \"antigo\"}", true);

		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.applyPageTemplate(
				_pageConfigurationId, "event");

		Assert.assertEquals("event", pageConfiguration.getTemplateKey());

		List<PageBlock> pageBlocks = _pageBlockLocalService.getPageBlocks(
			_pageConfigurationId);

		Assert.assertEquals("[HERO, TEXT, FORM, FAQ]", _types(pageBlocks));
		Assert.assertEquals(
			_campaign.getName(),
			_configuration(
				pageBlocks.get(0)
			).getString(
				"title"
			));
		Assert.assertEquals(
			"About the event",
			_configuration(
				pageBlocks.get(1)
			).getString(
				"title"
			));
		Assert.assertEquals(
			"Register",
			_configuration(
				pageBlocks.get(2)
			).getString(
				"title"
			));

		try {
			_pageConfigurationLocalService.applyPageTemplate(
				_pageConfigurationId, "nao-existe");

			Assert.fail();
		}
		catch (PageSettingsException pageSettingsException) {
			Assert.assertEquals(
				"page-template-is-invalid", pageSettingsException.getMessage());
		}
	}

	@Test
	public void testBlockValidationAndSanitization() throws Exception {
		_assertInvalid("HERO", "{\"subtitle\": \"sem título\"}", "title");
		_assertInvalid(
			"TEXT", "{\"body\": \"[x](javascript:alert(1))\"}", "body",
			"block-link-is-invalid");
		_assertInvalid(
			"FAQ",
			"{\"items\": [{\"question\": \"a\", \"answer\": \"b\"}, " +
				"{\"answer\": \"sem pergunta\"}]}",
			"items[1].question");
		_assertInvalid("FAQ", "{\"items\": []}", "items");
		_assertInvalid(
			"HERO", "{\"title\": \"t\", \"style\": \"neon\"}", "style",
			"block-option-is-invalid");
		_assertInvalid(
			"VIDEO", "{}", "blockType", "block-type-is-invalid");

		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "FORM", null, true);

		_assertInvalid("FORM", "{}", "blockType", "block-type-is-singleton");

		PageBlock pageBlock = _pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "CTA",
			"{\"title\": \"  Garanta sua vaga \", \"buttonLabel\": \"Ir\", " +
				"\"buttonUrl\": \"#form\", \"text\": \"\", \"onclick\": " +
					"\"alert(1)\"}",
			true);

		JSONObject configurationJSONObject = _configuration(pageBlock);

		Assert.assertEquals(
			"Garanta sua vaga", configurationJSONObject.getString("title"));
		Assert.assertEquals("brand", configurationJSONObject.getString("style"));
		Assert.assertFalse(configurationJSONObject.has("onclick"));
		Assert.assertFalse(configurationJSONObject.has("text"));

		FileEntry image = _addFile(_group, "banner.png", "image/png");

		pageBlock = _pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "IMAGE",
			"{\"imageFileEntryId\": \"" + image.getFileEntryId() +
				"\", \"alt\": \"Auditório\"}",
			true);

		Assert.assertEquals(
			image.getFileEntryId(),
			_configuration(
				pageBlock
			).getLong(
				"imageFileEntryId"
			));

		_assertInvalid(
			"IMAGE", "{\"imageFileEntryId\": " + image.getFileEntryId() + "}",
			"alt", "block-field-is-required");

		FileEntry otherSiteImage = _addFile(
			_otherGroup, "outro.png", "image/png");

		_assertInvalid(
			"IMAGE",
			"{\"imageFileEntryId\": " + otherSiteImage.getFileEntryId() +
				", \"alt\": \"x\"}",
			"imageFileEntryId", "block-image-is-invalid");

		FileEntry svg = _addFile(_group, "logo.svg", "image/svg+xml");

		_assertInvalid(
			"IMAGE",
			"{\"imageFileEntryId\": " + svg.getFileEntryId() +
				", \"decorative\": true}",
			"imageFileEntryId", "block-image-is-invalid");

		FileEntry privateImage = _addFile(
			_group, "privada.png", "image/png", false);

		_assertInvalid(
			"IMAGE",
			"{\"imageFileEntryId\": " + privateImage.getFileEntryId() +
				", \"decorative\": true}",
			"imageFileEntryId", "block-image-is-not-public");
	}

	@Test
	public void testPreviewAndBrokenImage() throws Exception {
		FileEntry image = _addFile(_group, "foto.png", "image/png");

		_pageConfigurationLocalService.applyPageTemplate(
			_pageConfigurationId, "blank");

		PageBlock imageBlock = _pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "IMAGE",
			"{\"imageFileEntryId\": " + image.getFileEntryId() +
				", \"decorative\": true}",
			true);

		_addFields();

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		PageBlock hero = _pageBlockLocalService.getPageBlocks(
			_pageConfigurationId
		).get(
			0
		);

		_pageBlockLocalService.updatePageBlock(
			hero.getPageBlockId(), "{\"title\": \"Título novo\"}", true);

		PublicCampaignSnapshot preview =
			_publicCampaignService.getPreviewSnapshot(
				_group.getGroupId(), _campaign.getSlug(),
				PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

		Assert.assertTrue(preview.isPreview());
		Assert.assertEquals(
			"Título novo",
			preview.getPage(
			).getBlocks(
			).get(
				0
			).getConfiguration(
			).get(
				"title"
			));

		PublicCampaignSnapshot published =
			_publicCampaignService.getPublishedSnapshot(
				_group.getGroupId(), _campaign.getSlug());

		Assert.assertFalse(published.isPreview());
		Assert.assertEquals(
			_campaign.getName(),
			published.getPage(
			).getBlocks(
			).get(
				0
			).getConfiguration(
			).get(
				"title"
			));

		User user = UserTestUtil.addUser(_group.getGroupId());

		_users.add(user);

		try {
			_publicCampaignService.getPreviewSnapshot(
				_group.getGroupId(), _campaign.getSlug(),
				PermissionCheckerFactoryUtil.create(user));

			Assert.fail();
		}
		catch (PrincipalException principalException) {
			Assert.assertNotNull(principalException);
		}

		_dlAppLocalService.deleteFileEntry(image.getFileEntryId());

		boolean flagged = false;

		for (PublicationIssue publicationIssue :
				_campaignPublicationService.getPublicationIssues(
					_campaign.getCampaignId())) {
			if ("block-needs-review".equals(publicationIssue.getCode()) &&
				imageBlock.getBlockKey(
				).equals(
					publicationIssue.getTarget()
				)) {
				flagged = true;
			}
		}

		Assert.assertTrue(flagged);
	}

	@Test
	public void testRenderedPageBlocksAndSeo() throws Exception {
		_addRendererPage();

		FileEntry image = _addFile(_group, "capa.png", "image/png");

		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "HERO",
			"{\"title\": \"Encontro <b>RH</b>\", \"imageFileEntryId\": " +
				image.getFileEntryId() + "}",
			true);
		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "TEXT",
			"{\"title\": \"Sobre\", \"body\": \"Texto **forte** <script>x" +
				"</script>\"}",
			true);
		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "FAQ",
			"{\"items\": [{\"question\": \"Custa?\", \"answer\": \"Não\"}]}",
			true);

		PageBlock hidden = _pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "CTA",
			"{\"title\": \"Bloco oculto\", \"buttonLabel\": \"Ir\", " +
				"\"buttonUrl\": \"#form\"}",
			false);

		_pageBlockLocalService.addPageBlock(
			_pageConfigurationId, "FORM", "{\"title\": \"Inscreva-se\"}", true);

		_pageConfigurationLocalService.updatePageSettings(
			_pageConfigurationId,
			new PageSettingsInput(
			).setNoIndex(
				true
			).setOgDescription(
				"Compartilhe \"o\" encontro\"><script>alert(1)</script>"
			).setOgImageFileEntryId(
				image.getFileEntryId()
			).setSeoTitle(
				"Encontro de RH 2026"
			).setThemeKey(
				"dark"
			));

		_addFields();

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		String html = _getPage(_campaign.getSlug(), "");

		Assert.assertTrue(
			html,
			html.contains(
				"<h1 class=\"campaign-forms-hero__title\" id=\"hero_title\">" +
					"Encontro &lt;b&gt;RH&lt;/b&gt;</h1>"));
		Assert.assertTrue(html, html.contains("campaign-forms--theme-dark"));
		Assert.assertTrue(html, html.contains("Texto <strong>forte</strong>"));
		Assert.assertTrue(html, html.contains("&lt;script&gt;x&lt;/script&gt;"));
		Assert.assertFalse(html, html.contains(hidden.getBlockKey() + "_title"));

		int heroIndex = html.indexOf("id=\"hero\"");
		int textIndex = html.indexOf("id=\"text\"");
		int faqIndex = html.indexOf("id=\"faq\"");
		int formIndex = html.indexOf("id=\"form\"");

		Assert.assertTrue(
			html,
			(heroIndex > 0) && (heroIndex < textIndex) &&
			(textIndex < faqIndex) && (faqIndex < formIndex));

		Assert.assertTrue(
			html, html.contains("<summary class=\"campaign-forms-faq__question\">"));
		Assert.assertTrue(html, html.contains("class=\"campaign-forms-hero__image\""));

		Assert.assertEquals(1, _count(html, "rel=\"canonical\""));
		Assert.assertEquals(1, _count(html, "property=\"og:title\""));
		Assert.assertEquals(1, _count(html, "property=\"og:description\""));
		Assert.assertEquals(1, _count(html, "name=\"robots\""));
		Assert.assertTrue(html, html.contains("<title>Encontro de RH 2026"));

		Assert.assertFalse(html, html.contains("\"><script>alert(1)"));
		Assert.assertTrue(
			html,
			html.contains(
				"content=\"Compartilhe ”o” encontro”"));

		Matcher matcher = Pattern.compile(
			"content=\"([^\"]+)\" property=\"og:image\""
		).matcher(
			html
		);

		Assert.assertTrue(html, matcher.find());
		Assert.assertTrue(
			matcher.group(1),
			matcher.group(
				1
			).startsWith(
				"http"
			));

		String previewHtml = _getPage(_campaign.getSlug(), "/preview");

		Assert.assertTrue(
			previewHtml, previewHtml.contains("campaign-forms__not-found"));
	}

	private FileEntry _addFile(Group group, String name, String mimeType)
		throws Exception {
		return _addFile(group, name, mimeType, true);
	}

	private FileEntry _addFile(
			Group group, String name, String mimeType, boolean guestView)
		throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(guestView);

		byte[] bytes = mimeType.equals("image/svg+xml") ?
			"<svg xmlns=\"http://www.w3.org/2000/svg\"/>".getBytes() :
				Base64.decode(_PNG);

		return _dlAppLocalService.addFileEntry(
			null, TestPropsValues.getUserId(), group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString() + "-" + name, mimeType,
			RandomTestUtil.randomString(), null, "", "", bytes, null, null,
			serviceContext);
	}

	private void _addFields() throws Exception {
		FormVersion formVersion = CampaignTestUtil.getDraftFormVersion(
			_formLocalService, _campaign);

		_formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), CampaignTestUtil.nameField());
		_formFieldLocalService.addFormField(
			formVersion.getFormVersionId(), CampaignTestUtil.emailField());
	}

	private void _addRendererPage() throws Exception {
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
			TestPropsValues.getUserId(), _RENDERER_PORTLET_ID, false);

		_layoutLocalService.updateLayout(
			_group.getGroupId(), false, layout.getLayoutId(),
			layout.getTypeSettings());
	}

	private void _assertInvalid(
			String blockType, String configuration, String field)
		throws Exception {
		_assertInvalid(blockType, configuration, field, null);
	}

	private void _assertInvalid(
			String blockType, String configuration, String field,
			String messageKey)
		throws Exception {
		try {
			_pageBlockLocalService.addPageBlock(
				_pageConfigurationId, blockType, configuration, true);

			Assert.fail(blockType + " " + configuration);
		}
		catch (PageBlockConfigurationException
					pageBlockConfigurationException) {
			Assert.assertEquals(
				configuration, field,
				pageBlockConfigurationException.getField());

			if (messageKey != null) {
				Assert.assertEquals(
					messageKey, pageBlockConfigurationException.getMessage());
			}
		}
	}

	private JSONObject _configuration(PageBlock pageBlock) throws Exception {
		return JSONFactoryUtil.createJSONObject(
			pageBlock.getConfiguration());
	}

	private int _count(String html, String fragment) {
		return StringUtil.count(html, fragment);
	}

	private String _getPage(String slug, String suffix) throws Exception {
		ApiTestUtil.Response response = ApiTestUtil.get(
			"/web" + _group.getFriendlyURL() + "/campaign/-/campaign/" + slug +
				suffix,
			null, null);

		Assert.assertEquals(200, response.getStatus());

		return response.getBody();
	}

	private String _types(List<PageBlock> pageBlocks) {
		List<String> types = new ArrayList<>();

		for (PageBlock pageBlock : pageBlocks) {
			types.add(pageBlock.getBlockType());
		}

		return types.toString();
	}

	private static final String _ADMIN_PASSWORD = "test";

	private static final String _PNG =
		"iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+M9QDwAD" +
			"hgGAWjR9awAAAABJRU5ErkJggg==";

	private static final String _RENDERER_PORTLET_ID =
		"io_github_valtairnascimento_campaignforms_web_portlet_CampaignRendererPortlet";

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private DLAppLocalService _dlAppLocalService;

	@Inject
	private FormFieldLocalService _formFieldLocalService;

	@Inject
	private FormLocalService _formLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@DeleteAfterTestRun
	private Group _otherGroup;

	@Inject
	private PageBlockLocalService _pageBlockLocalService;

	private long _pageConfigurationId;

	@Inject
	private PageConfigurationLocalService _pageConfigurationLocalService;

	@Inject
	private PublicCampaignService _publicCampaignService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();
}
