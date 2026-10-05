package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.DuplicationRequest;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.CampaignService;
import io.github.valtairnascimento.campaignforms.test.util.CampaignTestUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
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
public class CampaignPermissionTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_otherGroup = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(), CampaignMode.QUICK);

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
				_campaignLocalService.deleteCampaign(campaign.getCampaignId());
			}
		}
	}

	@Test
	public void testAdminOfAnotherSiteCannotAccessCampaign() throws Exception {
		User otherSiteAdmin = _addUser(
			UserTestUtil.addGroupAdminUser(_otherGroup));

		_runAs(otherSiteAdmin);

		_assertForbidden(() -> _campaignService.getCampaign(_campaign.getCampaignId()));
		_assertForbidden(
			() -> _campaignService.getCampaignBySlug(
				_group.getGroupId(), _campaign.getSlug()));
		_assertForbidden(
			() -> _campaignService.updateCampaign(
				_campaign.getCampaignId(),
				CampaignTestUtil.campaignInput(
					CampaignMode.QUICK, _campaign.getSlug()),
				ServiceContextTestUtil.getServiceContext(
					_group.getGroupId(), otherSiteAdmin.getUserId())));
		_assertForbidden(
			() -> _campaignService.deleteCampaign(_campaign.getCampaignId()));

		Assert.assertEquals(
			0, _campaignService.getGroupCampaignsCount(_group.getGroupId()));

		Campaign ownCampaign = _campaignService.addCampaign(
			_otherGroup.getGroupId(),
			CampaignTestUtil.campaignInput(
				CampaignMode.QUICK, CampaignTestUtil.randomSlug()),
			ServiceContextTestUtil.getServiceContext(
				_otherGroup.getGroupId(), otherSiteAdmin.getUserId()));

		Assert.assertEquals(
			ownCampaign,
			_campaignService.getCampaign(ownCampaign.getCampaignId()));
	}

	@Test
	public void testJSONWebServicesAreNotExposed() {
		Assert.assertTrue(
			JSONWebServiceActionsManagerUtil.getJSONWebServiceActionMappings(
				"campaignforms"
			).isEmpty());
	}

	@Test
	public void testResourceActionsAreRegistered() {
		List<String> actionIds = ResourceActionsUtil.getModelResourceActions(
			Campaign.class.getName());

		Assert.assertTrue(
			actionIds.toString(), actionIds.contains(CampaignActionKeys.PUBLISH));
		Assert.assertTrue(
			actionIds.toString(),
			actionIds.contains(CampaignActionKeys.EXPORT_SUBMISSIONS));
		Assert.assertTrue(
			ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
				Campaign.class.getName()
			).contains(
				CampaignActionKeys.VIEW_SUBMISSIONS
			));
	}

	@Test
	public void testSiteMemberWithoutRoleCannotAddOrView() throws Exception {
		User siteMember = _addUser(
			UserTestUtil.addUser(_group.getGroupId()));

		_runAs(siteMember);

		_assertForbidden(
			() -> _campaignService.addCampaign(
				_group.getGroupId(),
				CampaignTestUtil.campaignInput(
					CampaignMode.QUICK, CampaignTestUtil.randomSlug()),
				ServiceContextTestUtil.getServiceContext(
					_group.getGroupId(), siteMember.getUserId())));
		_assertForbidden(
			() -> _campaignService.getCampaign(_campaign.getCampaignId()));

		Assert.assertEquals(
			0, _campaignService.getGroupCampaignsCount(_group.getGroupId()));
	}

	@Test
	public void testSiteAdminSeesCampaigns() throws Exception {
		_runAs(_addUser(UserTestUtil.addGroupAdminUser(_group)));

		Assert.assertEquals(
			_campaign, _campaignService.getCampaign(_campaign.getCampaignId()));
		Assert.assertEquals(
			1, _campaignService.getGroupCampaignsCount(_group.getGroupId()));
	}

	@Test
	public void testDuplicateRequiresAddCampaignOnTargetSite()
		throws Exception {
		User siteAdmin = _addUser(UserTestUtil.addGroupAdminUser(_group));

		_runAs(siteAdmin);

		Campaign campaign = _campaignService.duplicateCampaign(
			_campaign.getCampaignId(),
			new DuplicationRequest(
			).setSlug(
				CampaignTestUtil.randomSlug()
			),
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), siteAdmin.getUserId()));

		Assert.assertEquals(_group.getGroupId(), campaign.getGroupId());

		_assertForbidden(
			() -> _campaignService.duplicateCampaign(
				_campaign.getCampaignId(),
				new DuplicationRequest(
				).setTargetGroupId(
					_otherGroup.getGroupId()
				).setSlug(
					CampaignTestUtil.randomSlug()
				),
				ServiceContextTestUtil.getServiceContext(
					_otherGroup.getGroupId(), siteAdmin.getUserId())));
	}

	@Test
	public void testLifecycleActionsRequirePermission() throws Exception {
		User siteMember = _addUser(
			UserTestUtil.addUser(_group.getGroupId()));

		_runAs(siteMember);

		long campaignId = _campaign.getCampaignId();

		_assertForbidden(() -> _campaignService.publishCampaign(campaignId));
		_assertForbidden(() -> _campaignService.archiveCampaign(campaignId));
		_assertForbidden(() -> _campaignService.reopenCampaign(campaignId));
		_assertForbidden(
			() -> _campaignService.convertToCustomPage(campaignId));
		_assertForbidden(
			() -> _campaignService.getPublicationIssues(campaignId));
		_assertForbidden(
			() -> _campaignService.duplicateCampaign(
				campaignId,
				new DuplicationRequest(
				).setSlug(
					CampaignTestUtil.randomSlug()
				),
				ServiceContextTestUtil.getServiceContext(
					_group.getGroupId(), siteMember.getUserId())));
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

	private void _runAs(User user) throws Exception {
		PrincipalThreadLocal.setName(user.getUserId());
		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(user));
	}

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	@Inject
	private CampaignService _campaignService;

	@DeleteAfterTestRun
	private Group _group;

	private String _originalName;
	private PermissionChecker _originalPermissionChecker;

	@DeleteAfterTestRun
	private Group _otherGroup;

	@DeleteAfterTestRun
	private final List<User> _users = new ArrayList<>();

	@FunctionalInterface
	private interface UnsafeRunnable {
		public void run() throws Exception;
	}

}
