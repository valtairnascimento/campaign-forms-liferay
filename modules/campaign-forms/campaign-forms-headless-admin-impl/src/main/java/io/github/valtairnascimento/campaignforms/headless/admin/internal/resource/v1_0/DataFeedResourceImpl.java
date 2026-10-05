package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DataFeed;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.DataFeedDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.DataFeedResource;
import io.github.valtairnascimento.campaignforms.service.DataFeedService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/data-feed.properties",
	scope = ServiceScope.PROTOTYPE, service = DataFeedResource.class
)
public class DataFeedResourceImpl extends BaseDataFeedResourceImpl {
	@Override
	public Response deleteDataFeed(Long dataFeedId) throws Exception {
		_dataFeedService.revokeDataFeed(dataFeedId);

		return Response.noContent(
		).build();
	}

	@Override
	public Page<DataFeed> getCampaignDataFeedsPage(Long campaignId)
		throws Exception {
		return Page.of(
			TransformUtil.transform(
				_dataFeedService.getCampaignDataFeeds(campaignId),
				dataFeed -> _dataFeedDTOConverter.toDTO(
					dataFeed, null, contextHttpServletRequest)));
	}

	@Override
	public DataFeed postCampaignDataFeed(Long campaignId, DataFeed dataFeed)
		throws Exception {
		DataFeedCredentials dataFeedCredentials =
			_dataFeedService.addDataFeed(
				campaignId, dataFeed.getName(),
				dataFeed.getScope() == DataFeed.Scope.SERIES,
				(dataFeed.getFormat() == null) ? null :
					dataFeed.getFormat(
					).getValue(),
				GetterUtil.getBoolean(dataFeed.getIncludeMetadata(), true),
				dataFeed.getExpirationDate());

		return _dataFeedDTOConverter.toDTO(
			dataFeedCredentials.getDataFeed(), dataFeedCredentials.getToken(),
			contextHttpServletRequest);
	}

	@Override
	public DataFeed postDataFeedRegenerateToken(Long dataFeedId)
		throws Exception {
		DataFeedCredentials dataFeedCredentials =
			_dataFeedService.regenerateDataFeedToken(dataFeedId);

		return _dataFeedDTOConverter.toDTO(
			dataFeedCredentials.getDataFeed(), dataFeedCredentials.getToken(),
			contextHttpServletRequest);
	}

	@Reference
	private DataFeedDTOConverter _dataFeedDTOConverter;

	@Reference
	private DataFeedService _dataFeedService;
}
