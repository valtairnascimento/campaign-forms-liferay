package io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.constants.DataFeedFormat;
import io.github.valtairnascimento.campaignforms.domain.DataFeedAccessService;
import io.github.valtairnascimento.campaignforms.domain.SubmissionExportService;
import io.github.valtairnascimento.campaignforms.domain.SubmissionQuery;
import io.github.valtairnascimento.campaignforms.headless.delivery.resource.v1_0.DataFeedSubmissionResource;
import io.github.valtairnascimento.campaignforms.model.DataFeed;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.nio.charset.StandardCharsets;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/data-feed-submission.properties",
	scope = ServiceScope.PROTOTYPE,
	service = DataFeedSubmissionResource.class
)
public class DataFeedSubmissionResourceImpl
	extends BaseDataFeedSubmissionResourceImpl {
	@Override
	public Response getDataFeedSubmissions(
			String feedKey, String format, String token, Date modifiedSince,
			Long afterId, Integer pageSize)
		throws Exception {
		String headerToken = contextHttpServletRequest.getHeader(
			"X-Feed-Token");

		DataFeed dataFeed = _dataFeedAccessService.authenticate(
			feedKey, Validator.isNotNull(headerToken) ? headerToken : token);

		boolean json = Validator.isNull(format) ?
			DataFeedFormat.JSON.name(
			).equals(
				dataFeed.getFormat()
			) :
				"json".equalsIgnoreCase(format);

		SubmissionQuery submissionQuery = new SubmissionQuery(
			_dataFeedAccessService.getCampaignIds(dataFeed)
		).setAfterId(
			GetterUtil.getLong(afterId)
		).setIncludeMetadata(
			dataFeed.isIncludeMetadata()
		).setModifiedSince(
			modifiedSince
		).setSeparator(
			','
		);

		if (json) {
			int limit = GetterUtil.getInteger(pageSize, _MAX_PAGE_SIZE);

			submissionQuery.setLimit(
				Math.max(1, Math.min(limit, _MAX_PAGE_SIZE)));
		}

		StreamingOutput streamingOutput = outputStream -> {
			Writer writer = new BufferedWriter(
				new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

			try {
				if (json) {
					_submissionExportService.writeJSON(submissionQuery, writer);
				}
				else {
					_submissionExportService.writeCSV(submissionQuery, writer);
				}
			}
			catch (PortalException portalException) {
				throw new IOException(portalException);
			}
		};

		return Response.ok(
			streamingOutput,
			json ? "application/json; charset=UTF-8" :
				"text/csv; charset=UTF-8"
		).header(
			"Cache-Control", "no-store"
		).header(
			"X-Robots-Tag", "noindex"
		).build();
	}

	private static final int _MAX_PAGE_SIZE = 1000;

	@Reference
	private DataFeedAccessService _dataFeedAccessService;

	@Reference
	private SubmissionExportService _submissionExportService;
}
