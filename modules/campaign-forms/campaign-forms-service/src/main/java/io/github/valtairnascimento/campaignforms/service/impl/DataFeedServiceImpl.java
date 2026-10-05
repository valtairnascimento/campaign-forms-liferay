package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.DataFeed;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.base.DataFeedServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=DataFeed"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class DataFeedServiceImpl extends DataFeedServiceBaseImpl {
	@Override
	public DataFeedCredentials addDataFeed(
			long campaignId, String name, boolean seriesScope, String format,
			boolean includeMetadata, Date expirationDate)
		throws PortalException {
		_check(campaignId);

		DataFeedCredentials dataFeedCredentials =
			dataFeedLocalService.addDataFeed(
				getUserId(), campaignId, name, seriesScope, format,
				includeMetadata, expirationDate);

		_audit(dataFeedCredentials.getDataFeed(), AuditAction.DATA_FEED_ADDED);

		return dataFeedCredentials;
	}

	@Override
	public List<DataFeed> getCampaignDataFeeds(long campaignId)
		throws PortalException {
		_check(campaignId);

		return dataFeedLocalService.getCampaignDataFeeds(campaignId);
	}

	@Override
	public DataFeedCredentials regenerateDataFeedToken(long dataFeedId)
		throws PortalException {
		_check(
			dataFeedLocalService.getDataFeed(
				dataFeedId
			).getCampaignId());

		DataFeedCredentials dataFeedCredentials =
			dataFeedLocalService.regenerateDataFeedToken(dataFeedId);

		_audit(
			dataFeedCredentials.getDataFeed(),
			AuditAction.DATA_FEED_TOKEN_ROTATED);

		return dataFeedCredentials;
	}

	@Override
	public DataFeed revokeDataFeed(long dataFeedId) throws PortalException {
		_check(
			dataFeedLocalService.getDataFeed(
				dataFeedId
			).getCampaignId());

		DataFeed dataFeed = dataFeedLocalService.revokeDataFeed(dataFeedId);

		_audit(dataFeed, AuditAction.DATA_FEED_REVOKED);

		return dataFeed;
	}

	private void _audit(DataFeed dataFeed, AuditAction auditAction)
		throws PortalException {
		_auditEntryLocalService.addAuditEntry(
			dataFeed.getCompanyId(), dataFeed.getGroupId(), getUserId(),
			dataFeed.getCampaignId(), auditAction, DataFeed.class.getName(),
			dataFeed.getDataFeedId(),
			_jsonFactory.createJSONObject(
			).put(
				"feedKey", dataFeed.getFeedKey()
			).put(
				"format", dataFeed.getFormat()
			).put(
				"name", dataFeed.getName()
			).put(
				"seriesKey", dataFeed.getSeriesKey()
			));
	}

	private void _check(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId,
			CampaignActionKeys.EXPORT_SUBMISSIONS);
	}

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private JSONFactory _jsonFactory;
}
