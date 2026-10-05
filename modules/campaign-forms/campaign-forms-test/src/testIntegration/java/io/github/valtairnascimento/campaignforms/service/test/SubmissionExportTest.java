package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.CampaignPublicationService;
import io.github.valtairnascimento.campaignforms.domain.DataFeedAccessService;
import io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials;
import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.domain.SubmissionExportService;
import io.github.valtairnascimento.campaignforms.domain.SubmissionQuery;
import io.github.valtairnascimento.campaignforms.exception.DataFeedConfigurationException;
import io.github.valtairnascimento.campaignforms.input.FieldOptionInput;
import io.github.valtairnascimento.campaignforms.model.AuditEntry;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.DataFeed;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.DataFeedLocalService;
import io.github.valtairnascimento.campaignforms.service.DataFeedService;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionService;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SubmissionExportTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_otherGroup = GroupTestUtil.addGroup();

		_seriesKey = "feira-" + System.nanoTime();

		_campaign = _addCampaign(_group, _seriesKey);

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
		_formFieldLocalService.addFormField(
			formVersionId, CampaignTestUtil.field("empresa", "text", false));

		_campaignPublicationService.publish(
			TestPropsValues.getUserId(), _campaign.getCampaignId());

		_firstSubmissionId = _submit(
			"Maria \"Excel\"", "maria@example.org",
			Arrays.asList("inovacao", "gestao"));
		_secondSubmissionId = _submit(
			"=HYPERLINK(\"x\")", "joao@example.org", new ArrayList<>());

		_originalName = PrincipalThreadLocal.getName();
		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();
	}

	@After
	public void tearDown() throws Exception {
		PrincipalThreadLocal.setName(_originalName);
		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		for (Group group : new Group[] {_group, _otherGroup}) {
			for (Campaign campaign :
					_campaignLocalService.getGroupCampaigns(
						group.getGroupId(), -1, -1, null)) {
				for (DataFeed dataFeed :
						_dataFeedLocalService.getCampaignDataFeeds(
							campaign.getCampaignId())) {
					_dataFeedLocalService.deleteDataFeed(dataFeed);
				}
			}
		}

		CampaignTestUtil.deleteCampaigns(
			_campaignLocalService, _submissionLocalService,
			_group.getGroupId(), _otherGroup.getGroupId());
	}

	@Test
	public void testDataFeedAuthentication() throws Exception {
		DataFeedCredentials dataFeedCredentials = _addDataFeed(
			TestPropsValues.getUserId(), false, null);

		DataFeed dataFeed = dataFeedCredentials.getDataFeed();
		String token = dataFeedCredentials.getToken();

		Assert.assertTrue(token, token.length() >= 40);
		Assert.assertNotEquals(token, dataFeed.getTokenHash());
		Assert.assertFalse(dataFeed.getTokenHash().contains(token));
		Assert.assertTrue(token.endsWith(dataFeed.getTokenHint()));
		Assert.assertNull(dataFeed.getLastAccessDate());

		DataFeed authenticatedDataFeed = _dataFeedAccessService.authenticate(
			dataFeed.getFeedKey(), token);

		Assert.assertEquals(
			dataFeed.getDataFeedId(), authenticatedDataFeed.getDataFeedId());
		Assert.assertNotNull(authenticatedDataFeed.getLastAccessDate());

		_assertUnauthenticated(dataFeed.getFeedKey(), token + "x");
		_assertUnauthenticated(dataFeed.getFeedKey(), null);
		_assertUnauthenticated("nao-existe", token);

		String newToken = _dataFeedLocalService.regenerateDataFeedToken(
			dataFeed.getDataFeedId()
		).getToken();

		_assertUnauthenticated(dataFeed.getFeedKey(), token);

		_dataFeedAccessService.authenticate(dataFeed.getFeedKey(), newToken);

		_dataFeedLocalService.revokeDataFeed(dataFeed.getDataFeedId());

		_assertUnauthenticated(dataFeed.getFeedKey(), newToken);

		DataFeedCredentials expiredDataFeedCredentials = _addDataFeed(
			TestPropsValues.getUserId(), false,
			new Date(System.currentTimeMillis() - 1000));

		_assertUnauthenticated(
			expiredDataFeedCredentials.getDataFeed(
			).getFeedKey(),
			expiredDataFeedCredentials.getToken());
	}

	@Test
	public void testDataFeedScope() throws Exception {
		Campaign nextEdition = _addCampaign(_group, _seriesKey);
		Campaign otherSiteCampaign = _addCampaign(_otherGroup, _seriesKey);

		DataFeed seriesDataFeed = _addDataFeed(
			TestPropsValues.getUserId(), true, null
		).getDataFeed();

		long[] campaignIds = _dataFeedAccessService.getCampaignIds(
			seriesDataFeed);

		Arrays.sort(campaignIds);

		long[] expectedCampaignIds = {
			_campaign.getCampaignId(), nextEdition.getCampaignId()
		};

		Arrays.sort(expectedCampaignIds);

		Assert.assertArrayEquals(expectedCampaignIds, campaignIds);
		Assert.assertFalse(
			Arrays.toString(campaignIds),
			Arrays.stream(
				campaignIds
			).anyMatch(
				campaignId -> campaignId == otherSiteCampaign.getCampaignId()
			));

		DataFeed campaignDataFeed = _addDataFeed(
			TestPropsValues.getUserId(), false, null
		).getDataFeed();

		Assert.assertArrayEquals(
			new long[] {_campaign.getCampaignId()},
			_dataFeedAccessService.getCampaignIds(campaignDataFeed));

		Campaign withoutSeries = _addCampaign(_group, null);

		try {
			_dataFeedLocalService.addDataFeed(
				TestPropsValues.getUserId(), withoutSeries.getCampaignId(),
				"Painel", true, "CSV", true, null);

			Assert.fail();
		}
		catch (DataFeedConfigurationException
					dataFeedConfigurationException) {
			Assert.assertEquals(
				"data-feed-series-is-required",
				dataFeedConfigurationException.getMessage());
		}
	}

	@Test
	public void testDataFeedUsesOwnerPermissions() throws Exception {
		User siteMember = _addUser(UserTestUtil.addUser(_group.getGroupId()));

		Assert.assertArrayEquals(
			new long[0],
			_dataFeedAccessService.getCampaignIds(
				_addDataFeed(
					siteMember.getUserId(), false, null
				).getDataFeed()));

		User siteAdmin = _addUser(UserTestUtil.addGroupAdminUser(_group));

		DataFeed dataFeed = _addDataFeed(
			siteAdmin.getUserId(), false, null
		).getDataFeed();

		Assert.assertArrayEquals(
			new long[] {_campaign.getCampaignId()},
			_dataFeedAccessService.getCampaignIds(dataFeed));

		_userLocalService.updateStatus(
			siteAdmin.getUserId(), WorkflowConstants.STATUS_INACTIVE,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		Assert.assertArrayEquals(
			new long[0], _dataFeedAccessService.getCampaignIds(dataFeed));
	}

	@Test
	public void testExportCSV() throws Exception {
		StringWriter stringWriter = new StringWriter();

		Assert.assertEquals(
			2,
			_submissionExportService.writeCSV(
				new SubmissionQuery(
					_campaign.getCampaignId()
				).setIncludeMetadata(
					false
				).setSeparator(
					';'
				).setUseLabels(
					true
				),
				stringWriter));

		String csv = stringWriter.toString();

		Assert.assertEquals(0xFEFF, csv.charAt(0));

		String[] lines = csv.substring(
			1
		).split(
			"\r\n"
		);

		Assert.assertEquals(csv, 3, lines.length);
		Assert.assertEquals(
			"submissionId;createdAt;Nome;E-mail;Interesses;Empresa", lines[0]);
		Assert.assertTrue(
			lines[1],
			lines[1].startsWith(_firstSubmissionId + ";"));
		Assert.assertTrue(
			lines[1],
			lines[1].endsWith(
				";\"Maria \"\"Excel\"\"\";maria@example.org;" +
					"\"Inovação; Gestão\";"));

		Assert.assertTrue(
			lines[2],
			lines[2].endsWith(
				";\"'=HYPERLINK(\"\"x\"\")\";joao@example.org;;"));

		stringWriter = new StringWriter();

		_submissionExportService.writeCSV(
			new SubmissionQuery(
				_campaign.getCampaignId()
			).setSeparator(
				','
			),
			stringWriter);

		lines = stringWriter.toString(
		).substring(
			1
		).split(
			"\r\n"
		);

		Assert.assertTrue(
			lines[0],
			lines[0].startsWith(
				"submissionId,createdAt,modifiedAt,campaignId,campaign," +
					"series,instance,site,"));
		Assert.assertTrue(
			lines[0], lines[0].endsWith(",nome,email,interesses,empresa"));
		Assert.assertTrue(
			lines[1],
			lines[1].contains(
				"," + _campaign.getName() + "," + _seriesKey + ","));
		Assert.assertTrue(
			lines[1], lines[1].contains(",maria@example.org,inovacao; gestao,"));
	}

	@Test
	public void testExportXLSX() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		Assert.assertEquals(
			2,
			_submissionExportService.writeXLSX(
				new SubmissionQuery(_campaign.getCampaignId()),
				byteArrayOutputStream, LocaleUtil.BRAZIL,
				TimeZone.getTimeZone("America/Sao_Paulo")));

		String sheet = null;

		try (ZipInputStream zipInputStream = new ZipInputStream(
				new ByteArrayInputStream(
					byteArrayOutputStream.toByteArray()))) {
			ZipEntry zipEntry = null;

			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				if ("xl/worksheets/sheet1.xml".equals(zipEntry.getName())) {
					sheet = new String(
						zipInputStream.readAllBytes(), StandardCharsets.UTF_8);
				}
			}
		}

		Assert.assertNotNull(sheet);

		List<String> texts = new ArrayList<>();

		Matcher matcher = Pattern.compile(
			"<t xml:space=\"preserve\">([^<]*)</t>"
		).matcher(
			sheet
		);

		while (matcher.find()) {
			texts.add(matcher.group(1));
		}

		Assert.assertEquals(
			Arrays.asList(
				"Nome", "E-mail", "Interesses", "Empresa",
				"Maria &quot;Excel&quot;", "maria@example.org",
				"Inovação, Gestão", "=HYPERLINK(&quot;x&quot;)",
				"joao@example.org"),
			texts);
		Assert.assertFalse(sheet, sheet.contains("<f>"));
		Assert.assertFalse(
			sheet, sheet.contains(String.valueOf(_firstSubmissionId)));
	}

	@Test
	public void testExportJSONPages() throws Exception {
		JSONObject page = _writeJSON(
			new SubmissionQuery(
				_campaign.getCampaignId()
			).setLimit(
				1
			));

		JSONArray items = page.getJSONArray("items");

		Assert.assertEquals(1, items.length());
		Assert.assertEquals(_firstSubmissionId, page.getLong("nextAfterId"));

		JSONObject item = items.getJSONObject(0);

		Assert.assertEquals(_firstSubmissionId, item.getLong("submissionId"));
		Assert.assertEquals(_seriesKey, item.getString("series"));

		JSONObject values = item.getJSONObject("values");

		Assert.assertEquals("Maria \"Excel\"", values.getString("nome"));
		Assert.assertEquals(
			"[\"inovacao\",\"gestao\"]",
			values.getJSONArray(
				"interesses"
			).toString());

		Assert.assertTrue(values.toString(), values.has("empresa"));
		Assert.assertTrue(values.toString(), values.isNull("empresa"));

		page = _writeJSON(
			new SubmissionQuery(
				_campaign.getCampaignId()
			).setAfterId(
				_firstSubmissionId
			).setLimit(
				1
			));

		items = page.getJSONArray("items");

		Assert.assertEquals(1, items.length());
		Assert.assertEquals(
			_secondSubmissionId,
			items.getJSONObject(
				0
			).getLong(
				"submissionId"
			));
		Assert.assertFalse(page.toString(), page.has("nextAfterId"));
		Assert.assertEquals(
			"[]",
			items.getJSONObject(
				0
			).getJSONObject(
				"values"
			).getJSONArray(
				"interesses"
			).toString());
	}

	@Test
	public void testExportRequiresPermissionAndIsAudited() throws Exception {
		long campaignId = _campaign.getCampaignId();

		_runAs(_addUser(UserTestUtil.addUser(_group.getGroupId())));

		_assertForbidden(
			() -> _submissionService.checkSubmissionsExport(campaignId, "csv"));
		_assertForbidden(
			() -> _dataFeedService.addDataFeed(
				campaignId, "Painel", false, "CSV", true, null));
		_assertForbidden(() -> _dataFeedService.getCampaignDataFeeds(campaignId));

		_runAs(_addUser(UserTestUtil.addGroupAdminUser(_group)));

		_submissionService.checkSubmissionsExport(campaignId, "csv");

		DataFeedCredentials dataFeedCredentials = _dataFeedService.addDataFeed(
			campaignId, "Painel", false, "CSV", true, null);

		_dataFeedService.revokeDataFeed(
			dataFeedCredentials.getDataFeed(
			).getDataFeedId());

		List<String> actions = new ArrayList<>();

		for (AuditEntry auditEntry :
				_auditEntryLocalService.getCampaignAuditEntries(
					campaignId, -1, -1)) {
			actions.add(auditEntry.getAction());

			Assert.assertFalse(
				auditEntry.getDetails(),
				String.valueOf(
					auditEntry.getDetails()
				).contains(
					dataFeedCredentials.getToken()
				));
		}

		Assert.assertTrue(
			actions.toString(),
			actions.containsAll(
				Arrays.asList(
					AuditAction.SUBMISSIONS_EXPORTED.name(),
					AuditAction.DATA_FEED_ADDED.name(),
					AuditAction.DATA_FEED_REVOKED.name())));
	}

	private Campaign _addCampaign(Group group, String seriesKey)
		throws Exception {
		Campaign campaign = _campaignLocalService.addCampaign(
			TestPropsValues.getUserId(), group.getGroupId(),
			CampaignTestUtil.campaignInput(
				CampaignMode.QUICK, CampaignTestUtil.randomSlug()
			).setSeriesKey(
				seriesKey
			),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));

		return CampaignTestUtil.withConsent(campaign);
	}

	private DataFeedCredentials _addDataFeed(
			long userId, boolean seriesScope, Date expirationDate)
		throws Exception {
		return _dataFeedLocalService.addDataFeed(
			userId, _campaign.getCampaignId(), "Painel", seriesScope, "CSV",
			true, expirationDate);
	}

	private User _addUser(User user) {
		_users.add(user);

		return user;
	}

	private void _assertForbidden(UnsafeRunnable unsafeRunnable)
		throws Exception {
		try {
			unsafeRunnable.run();

			Assert.fail("Expected PrincipalException");
		}
		catch (PrincipalException principalException) {
		}
	}

	private void _assertUnauthenticated(String feedKey, String token)
		throws Exception {
		try {
			_dataFeedAccessService.authenticate(feedKey, token);

			Assert.fail("Expected PrincipalException");
		}
		catch (PrincipalException.MustBeAuthenticated mustBeAuthenticated) {
		}
	}

	private void _runAs(User user) throws Exception {
		PrincipalThreadLocal.setName(user.getUserId());
		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(user));
	}

	private long _submit(String nome, String email, List<String> interesses)
		throws Exception {
		Form form = _formLocalService.getCampaignForm(
			_campaign.getCampaignId());

		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			form.getPublishedVersionId());

		return _formSubmissionService.submit(
			_group.getGroupId(), _campaign.getSlug(),
			CampaignTestUtil.submissionCommand(
				_formTokenManager, formVersion
			).putValue(
				"nome", nome
			).putValue(
				"email", email
			).putValues(
				"interesses", interesses
			)
		).getSubmissionId();
	}

	private JSONObject _writeJSON(SubmissionQuery submissionQuery)
		throws Exception {
		StringWriter stringWriter = new StringWriter();

		_submissionExportService.writeJSON(submissionQuery, stringWriter);

		return JSONFactoryUtil.createJSONObject(stringWriter.toString());
	}

	@Inject
	private AuditEntryLocalService _auditEntryLocalService;

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignPublicationService _campaignPublicationService;

	@Inject
	private DataFeedAccessService _dataFeedAccessService;

	@Inject
	private DataFeedLocalService _dataFeedLocalService;

	@Inject
	private DataFeedService _dataFeedService;

	private long _firstSubmissionId;

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

	private String _originalName;
	private PermissionChecker _originalPermissionChecker;

	@DeleteAfterTestRun
	private Group _otherGroup;

	private long _secondSubmissionId;
	private String _seriesKey;

	@Inject
	private SubmissionExportService _submissionExportService;

	@Inject
	private SubmissionLocalService _submissionLocalService;

	@Inject
	private SubmissionService _submissionService;

	@Inject
	private UserLocalService _userLocalService;

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();

	@FunctionalInterface
	private interface UnsafeRunnable {
		public void run() throws Exception;
	}

}
