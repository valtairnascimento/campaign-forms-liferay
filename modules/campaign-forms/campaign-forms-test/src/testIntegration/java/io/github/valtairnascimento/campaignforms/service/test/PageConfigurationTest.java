package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.exception.PageSettingsException;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.service.CampaignLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;
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
public class PageConfigurationTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_campaign = CampaignTestUtil.addCampaign(
			_campaignLocalService, _group.getGroupId(),
			CampaignMode.CUSTOM_PAGE);

		_draftPageConfiguration =
			_pageConfigurationLocalService.getPageConfiguration(
				_campaign.getCampaignId(), PageStage.DRAFT);
	}

	@After
	public void tearDown() throws Exception {
		_campaignLocalService.deleteCampaign(_campaign.getCampaignId());
	}

	@Test
	public void testBlockKeysAreStableAndUnique() throws Exception {
		long pageConfigurationId =
			_draftPageConfiguration.getPageConfigurationId();

		PageBlock hero = _pageBlockLocalService.addPageBlock(
			pageConfigurationId, "HERO", "{\"title\": \"Encontro RH\"}", true);
		PageBlock form = _pageBlockLocalService.addPageBlock(
			pageConfigurationId, "FORM", null, true);
		PageBlock secondHero = _pageBlockLocalService.duplicatePageBlock(
			hero.getPageBlockId());

		Assert.assertEquals("hero", hero.getBlockKey());
		Assert.assertEquals("form", form.getBlockKey());
		Assert.assertEquals("hero-2", secondHero.getBlockKey());
		Assert.assertEquals("{}", form.getConfiguration());

		List<PageBlock> pageBlocks = _pageBlockLocalService.getPageBlocks(
			pageConfigurationId);

		Assert.assertEquals("hero", pageBlocks.get(0).getBlockKey());
		Assert.assertEquals("hero-2", pageBlocks.get(1).getBlockKey());
		Assert.assertEquals("form", pageBlocks.get(2).getBlockKey());
	}

	@Test(expected = PageBlockConfigurationException.class)
	public void testInvalidBlockConfigurationFails() throws Exception {
		_pageBlockLocalService.addPageBlock(
			_draftPageConfiguration.getPageConfigurationId(), "HERO",
			"{not json", true);
	}

	@Test
	public void testPublishCopyKeepsDraftAndLiveSeparate() throws Exception {
		long userId = TestPropsValues.getUserId();

		_pageBlockLocalService.addPageBlock(
			_draftPageConfiguration.getPageConfigurationId(), "HERO", null,
			true);
		_pageBlockLocalService.addPageBlock(
			_draftPageConfiguration.getPageConfigurationId(), "FORM", null,
			true);

		PageConfiguration publishedPageConfiguration =
			_pageConfigurationLocalService.copyPageConfiguration(
				userId, _draftPageConfiguration.getPageConfigurationId(),
				_campaign.getCampaignId(), PageStage.PUBLISHED);

		Assert.assertEquals(
			PageStage.PUBLISHED.name(), publishedPageConfiguration.getStage());
		Assert.assertEquals(
			2,
			_pageBlockLocalService.getPageBlocks(
				publishedPageConfiguration.getPageConfigurationId()
			).size());

		_pageBlockLocalService.addPageBlock(
			_draftPageConfiguration.getPageConfigurationId(), "FAQ", null,
			true);

		Assert.assertEquals(
			2,
			_pageBlockLocalService.getPageBlocks(
				publishedPageConfiguration.getPageConfigurationId()
			).size());

		PageConfiguration republishedPageConfiguration =
			_pageConfigurationLocalService.copyPageConfiguration(
				userId, _draftPageConfiguration.getPageConfigurationId(),
				_campaign.getCampaignId(), PageStage.PUBLISHED);

		Assert.assertEquals(
			publishedPageConfiguration.getPageConfigurationId(),
			republishedPageConfiguration.getPageConfigurationId());
		Assert.assertEquals(
			3,
			_pageBlockLocalService.getPageBlocks(
				republishedPageConfiguration.getPageConfigurationId()
			).size());

		PageBlock publishedPageBlock = _pageBlockLocalService.getPageBlocks(
			republishedPageConfiguration.getPageConfigurationId()
		).get(
			0
		);

		try {
			_pageBlockLocalService.updatePageBlock(
				publishedPageBlock.getPageBlockId(), "{}", false);

			Assert.fail();
		}
		catch (PageSettingsException pageSettingsException) {
		}
	}

	private Campaign _campaign;

	@Inject
	private CampaignLocalService _campaignLocalService;

	private PageConfiguration _draftPageConfiguration;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private PageBlockLocalService _pageBlockLocalService;

	@Inject
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
