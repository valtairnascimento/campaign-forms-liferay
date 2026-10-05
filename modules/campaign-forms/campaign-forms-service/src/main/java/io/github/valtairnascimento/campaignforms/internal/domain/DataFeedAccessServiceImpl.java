package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.domain.DataFeedAccessService;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignTransactionUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.DataFeed;
import io.github.valtairnascimento.campaignforms.service.DataFeedLocalService;
import io.github.valtairnascimento.campaignforms.service.persistence.CampaignPersistence;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DataFeedAccessService.class)
public class DataFeedAccessServiceImpl implements DataFeedAccessService {
	@Override
	public DataFeed authenticate(String feedKey, String token)
		throws PortalException {
		return _dataFeedLocalService.authenticateDataFeed(feedKey, token);
	}

	@Override
	public long[] getCampaignIds(DataFeed dataFeed) throws PortalException {
		try {
			return CampaignTransactionUtil.invokeReadOnly(
				() -> _getCampaignIds(dataFeed));
		}
		catch (PortalException | RuntimeException exception) {
			throw exception;
		}
		catch (Throwable throwable) {
			throw new SystemException(throwable);
		}
	}

	private long[] _getCampaignIds(DataFeed dataFeed) throws PortalException {
		List<Campaign> campaigns = new ArrayList<>();

		if (Validator.isNotNull(dataFeed.getSeriesKey())) {
			for (Campaign campaign :
					_campaignPersistence.findByC_SK(
						dataFeed.getCompanyId(), dataFeed.getSeriesKey())) {
				if (campaign.getGroupId() == dataFeed.getGroupId()) {
					campaigns.add(campaign);
				}
			}
		}
		else {
			Campaign campaign = _campaignPersistence.fetchByPrimaryKey(
				dataFeed.getCampaignId());

			if (campaign != null) {
				campaigns.add(campaign);
			}
		}

		User user = _userLocalService.fetchUser(dataFeed.getUserId());

		if ((user == null) || !user.isActive()) {
			return new long[0];
		}

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			user);

		List<Long> campaignIds = new ArrayList<>();

		for (Campaign campaign : campaigns) {
			if (_campaignModelResourcePermission.contains(
					permissionChecker, campaign,
					CampaignActionKeys.EXPORT_SUBMISSIONS)) {
				campaignIds.add(campaign.getCampaignId());
			}
		}

		long[] result = new long[campaignIds.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = campaignIds.get(i);
		}

		return result;
	}

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private CampaignPersistence _campaignPersistence;

	@Reference
	private DataFeedLocalService _dataFeedLocalService;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;
}
