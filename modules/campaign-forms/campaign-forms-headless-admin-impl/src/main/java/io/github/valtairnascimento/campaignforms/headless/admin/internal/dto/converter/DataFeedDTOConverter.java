package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.DataFeed;

import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DataFeedDTOConverter.class)
public class DataFeedDTOConverter {
	public DataFeed toDTO(
		io.github.valtairnascimento.campaignforms.model.DataFeed dataFeed, String token,
		HttpServletRequest httpServletRequest) {
		DataFeed dto = new DataFeed();

		dto.setActive(dataFeed.isActive());
		dto.setDateCreated(dataFeed.getCreateDate());
		dto.setExpirationDate(dataFeed.getExpirationDate());
		dto.setFormat(DataFeed.Format.create(dataFeed.getFormat()));
		dto.setId(dataFeed.getDataFeedId());
		dto.setIncludeMetadata(dataFeed.isIncludeMetadata());
		dto.setLastAccessDate(dataFeed.getLastAccessDate());
		dto.setName(dataFeed.getName());

		if (Validator.isNotNull(dataFeed.getSeriesKey())) {
			dto.setScope(DataFeed.Scope.SERIES);
			dto.setSeriesKey(dataFeed.getSeriesKey());
		}
		else {
			dto.setScope(DataFeed.Scope.CAMPAIGN);
		}

		dto.setTokenHint(dataFeed.getTokenHint());

		String url =
			_portal.getPortalURL(httpServletRequest) +
				_portal.getPathContext() + "/o/campaign-forms/v1.0/data-feeds/" +
					dataFeed.getFeedKey() + "/submissions?format=" +
						dataFeed.getFormat(
						).toLowerCase();

		if (token != null) {
			dto.setToken(token);

			url = url + "&token=" + URLCodec.encodeURL(token);
		}

		dto.setUrl(url);

		return dto;
	}

	@Reference
	private Portal _portal;
}
