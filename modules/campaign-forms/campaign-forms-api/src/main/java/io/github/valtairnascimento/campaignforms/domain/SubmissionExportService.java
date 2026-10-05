package io.github.valtairnascimento.campaignforms.domain;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import java.util.Locale;
import java.util.TimeZone;

public interface SubmissionExportService {
	public int writeCSV(SubmissionQuery submissionQuery, Writer writer)
		throws IOException, PortalException;

	public int writeJSON(SubmissionQuery submissionQuery, Writer writer)
		throws IOException, PortalException;

	public int writeXLSX(
			SubmissionQuery submissionQuery, OutputStream outputStream,
			Locale locale, TimeZone timeZone)
		throws IOException, PortalException;
}
