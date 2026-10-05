package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.domain.SubmissionExportService;
import io.github.valtairnascimento.campaignforms.domain.SubmissionQuery;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Submission;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.SubmissionDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.SubmissionResource;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.service.CampaignService;
import io.github.valtairnascimento.campaignforms.service.SubmissionService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/submission.properties",
	scope = ServiceScope.PROTOTYPE, service = SubmissionResource.class
)
public class SubmissionResourceImpl extends BaseSubmissionResourceImpl {
	@Override
	public Response getCampaignSubmissionsExport(
			Long campaignId, String format, Boolean labels,
			Boolean includeMetadata, String separator)
		throws Exception {
		String exportFormat = _getFormat(format);

		_submissionService.checkSubmissionsExport(campaignId, exportFormat);

		SubmissionQuery submissionQuery = new SubmissionQuery(
			campaignId
		).setIncludeMetadata(
			GetterUtil.getBoolean(includeMetadata, true)
		).setUseLabels(
			GetterUtil.getBoolean(labels)
		).setSeparator(
			_getSeparator(separator)
		);

		Locale locale = contextAcceptLanguage.getPreferredLocale();
		TimeZone timeZone = contextUser.getTimeZone();

		StreamingOutput streamingOutput = outputStream -> {
			try {
				if (_FORMAT_XLSX.equals(exportFormat)) {
					OutputStream bufferedOutputStream =
						new BufferedOutputStream(outputStream);

					_submissionExportService.writeXLSX(
						submissionQuery, bufferedOutputStream, locale,
						timeZone);

					bufferedOutputStream.flush();

					return;
				}

				Writer writer = new BufferedWriter(
					new OutputStreamWriter(
						outputStream, StandardCharsets.UTF_8));

				if (_FORMAT_JSON.equals(exportFormat)) {
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

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyyMMdd-HHmm");

		simpleDateFormat.setTimeZone(timeZone);

		String date = simpleDateFormat.format(new Date());

		String fileName = "submissions-" + campaignId + "-" + date + "." +
			exportFormat;

		if (_FORMAT_XLSX.equals(exportFormat)) {
			Campaign campaign = _campaignService.getCampaign(campaignId);

			fileName = "submissions-" + campaign.getSlug() + "-" + date + ".xlsx";
		}

		return Response.ok(
			streamingOutput, _CONTENT_TYPES.get(exportFormat)
		).header(
			"Content-Disposition", "attachment; filename=\"" + fileName + "\""
		).header(
			"Cache-Control", "no-store"
		).build();
	}

	@Override
	public Response deleteSubmission(Long submissionId) throws Exception {
		_submissionService.deleteSubmission(submissionId);

		return Response.noContent(
		).build();
	}

	@Override
	public Page<Submission> getCampaignSubmissionsPage(
			Long campaignId, Pagination pagination)
		throws Exception {
		return Page.of(
			_submissionDTOConverter.toDTOs(
				_submissionService.getCampaignSubmissions(
					campaignId, pagination.getStartPosition(),
					pagination.getEndPosition())),
			pagination,
			_submissionService.getCampaignSubmissionsCount(campaignId));
	}

	@Override
	public Submission getSubmission(Long submissionId) throws Exception {
		return _submissionDTOConverter.toDTO(
			_submissionService.getSubmission(submissionId));
	}

	private char _getSeparator(String separator) {
		if ("comma".equals(separator) || ",".equals(separator)) {
			return ',';
		}

		if ("tab".equals(separator)) {
			return '\t';
		}

		return ';';
	}

	private String _getFormat(String format) {
		if (_FORMAT_JSON.equalsIgnoreCase(format)) {
			return _FORMAT_JSON;
		}

		if (_FORMAT_XLSX.equalsIgnoreCase(format)) {
			return _FORMAT_XLSX;
		}

		return _FORMAT_CSV;
	}

	private static final Map<String, String> _CONTENT_TYPES = HashMapBuilder.put(
		"csv", "text/csv; charset=UTF-8"
	).put(
		"json", "application/json; charset=UTF-8"
	).put(
		"xlsx",
		"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	).build();

	private static final String _FORMAT_CSV = "csv";

	private static final String _FORMAT_JSON = "json";

	private static final String _FORMAT_XLSX = "xlsx";

	@Reference
	private CampaignService _campaignService;

	@Reference
	private SubmissionDTOConverter _submissionDTOConverter;

	@Reference
	private SubmissionExportService _submissionExportService;

	@Reference
	private SubmissionService _submissionService;
}
