package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.test.util.ApiTestUtil;

import com.liferay.application.list.PanelApp;
import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CampaignAdminPortletTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAppBundleIsServed() throws Exception {
		ApiTestUtil.Response response = ApiTestUtil.get(
			"/o/campaign-forms-web/admin/campaign-admin.js", null, null);

		Assert.assertEquals(200, response.getStatus());
		Assert.assertTrue(response.getBody().contains("CampaignFormsAdmin"));

		response = ApiTestUtil.get(
			"/o/campaign-forms-web/admin/campaign-admin.css", null, null);

		Assert.assertEquals(200, response.getStatus());
		Assert.assertTrue(
			response.getBody().contains("campaign-forms-admin__stepper"));
	}

	@Test
	public void testPanelAppInSiteContentMenu() throws Exception {
		List<PanelApp> panelApps = _panelAppRegistry.getPanelApps(
			PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT);

		Assert.assertTrue(
			panelApps.stream(
			).anyMatch(
				panelApp -> _PORTLET_ID.equals(panelApp.getPortletId())
			));

		Portlet portlet = _portletLocalService.getPortletById(
			TestPropsValues.getCompanyId(), _PORTLET_ID);

		Assert.assertNotNull(portlet);
		Assert.assertTrue(portlet.isActive());
		Assert.assertFalse(portlet.isInstanceable());
	}

	@Test
	public void testPortletResourceActions() throws Exception {
		List<String> actionIds = ResourceActionsUtil.getPortletResourceActions(
			_PORTLET_ID);

		Assert.assertTrue(
			actionIds.toString(),
			actionIds.contains(ActionKeys.ACCESS_IN_CONTROL_PANEL));
		Assert.assertTrue(actionIds.contains(ActionKeys.VIEW));

		Assert.assertTrue(
			ResourceActionsUtil.getPortletResourceGuestUnsupportedActions(
				_PORTLET_ID
			).contains(
				ActionKeys.ACCESS_IN_CONTROL_PANEL
			));
	}

	private static final String _PORTLET_ID =
		"io_github_valtairnascimento_campaignforms_web_portlet_CampaignAdminPortlet";

	@Inject
	private PanelAppRegistry _panelAppRegistry;

	@Inject
	private PortletLocalService _portletLocalService;
}
