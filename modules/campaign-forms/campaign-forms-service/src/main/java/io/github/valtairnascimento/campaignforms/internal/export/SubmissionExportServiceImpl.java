package io.github.valtairnascimento.campaignforms.internal.export;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.domain.SubmissionExportService;
import io.github.valtairnascimento.campaignforms.domain.SubmissionQuery;
import io.github.valtairnascimento.campaignforms.internal.field.type.DateFieldType;
import io.github.valtairnascimento.campaignforms.internal.field.type.HiddenFieldType;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignTransactionUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.SubmissionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionValueLocalService;
import io.github.valtairnascimento.campaignforms.service.persistence.CampaignPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldOptionPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormVersionPersistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = SubmissionExportService.class)
public class SubmissionExportServiceImpl implements SubmissionExportService {
	@Override
	public int writeCSV(SubmissionQuery submissionQuery, Writer writer)
		throws IOException, PortalException {
		return _readOnly(() -> _writeCSV(submissionQuery, writer));
	}

	@Override
	public int writeJSON(SubmissionQuery submissionQuery, Writer writer)
		throws IOException, PortalException {
		return _readOnly(() -> _writeJSON(submissionQuery, writer));
	}

	@Override
	public int writeXLSX(
			SubmissionQuery submissionQuery, OutputStream outputStream,
			Locale locale, TimeZone timeZone)
		throws IOException, PortalException {
		return _readOnly(
			() -> _writeXLSX(submissionQuery, outputStream, locale, timeZone));
	}

	private int _writeCSV(SubmissionQuery submissionQuery, Writer writer)
		throws IOException {
		ExportContext exportContext = _createExportContext(submissionQuery);

		char separator = submissionQuery.getSeparator();

		writer.write(CsvUtil.BOM);

		List<String> header = new ArrayList<>();

		for (Column column : exportContext.columns) {
			header.add(
				submissionQuery.isUseLabels() ? column.label : column.key);
		}

		CsvUtil.writeRow(writer, header, null, separator);

		int[] count = {0};

		_forEachSubmission(
			submissionQuery, exportContext,
			(submission, values) -> {
				List<String> cells = new ArrayList<>();
				boolean[] text = new boolean[exportContext.columns.size()];

				for (int i = 0; i < exportContext.columns.size(); i++) {
					Column column = exportContext.columns.get(i);

					Object value = _getValue(
						column, submission, values, exportContext,
						submissionQuery.isUseLabels());

					cells.add(_toCSVText(value));
					text[i] = _isText(value);
				}

				CsvUtil.writeRow(writer, cells, text, separator);

				count[0]++;
			});

		writer.flush();

		return count[0];
	}

	private int _writeJSON(SubmissionQuery submissionQuery, Writer writer)
		throws IOException {
		ExportContext exportContext = _createExportContext(submissionQuery);

		writer.write("{\"items\":[");

		long[] last = {0, 0};

		_forEachSubmission(
			submissionQuery, exportContext,
			(submission, values) -> {
				Map<String, Object> item = new LinkedHashMap<>();
				Map<String, Object> itemValues = new LinkedHashMap<>();

				for (Column column : exportContext.columns) {
					Object value = _toJSONValue(
						_getValue(
							column, submission, values, exportContext,
							submissionQuery.isUseLabels()));

					if (column.metadata) {
						item.put(column.key, value);
					}
					else {
						itemValues.put(column.key, value);
					}
				}

				item.put("values", itemValues);

				if (last[1] > 0) {
					writer.write(',');
				}

				StringBuilder sb = new StringBuilder(256);

				JsonWriterUtil.write(sb, item);

				writer.write(sb.toString());

				last[0] = submission.getSubmissionId();
				last[1]++;
			});

		writer.write("]");

		if ((last[1] > 0) && _hasMore(submissionQuery, last[0])) {
			writer.write(",\"nextAfterId\":");
			writer.write(String.valueOf(last[0]));
		}

		writer.write("}");
		writer.flush();

		return (int)last[1];
	}

	private int _writeXLSX(
			SubmissionQuery submissionQuery, OutputStream outputStream,
			Locale locale, TimeZone timeZone)
		throws IOException {
		ExportContext exportContext = _createExportContext(
			submissionQuery.setIncludeMetadata(false));

		List<Column> columns = new ArrayList<>();

		for (Column column : exportContext.columns) {
			if (!column.metadata && !HiddenFieldType.KEY.equals(column.type)) {
				columns.add(column);
			}
		}

		XlsxWriter xlsxWriter = new XlsxWriter(
			outputStream,
			CampaignLanguageUtil.get(locale, "export-sheet-name"));

		List<String> header = new ArrayList<>();

		for (Column column : columns) {
			header.add(column.label);
		}

		xlsxWriter.writeHeader(header);

		String yes = CampaignLanguageUtil.get(locale, "notification-yes");
		String no = CampaignLanguageUtil.get(locale, "notification-no");

		ZoneId zoneId = ((timeZone == null) ? TimeZone.getDefault() :
			timeZone).toZoneId();

		int[] count = {0};

		_forEachSubmission(
			submissionQuery, exportContext,
			(submission, values) -> {
				List<Object> cells = new ArrayList<>();

				for (Column column : columns) {
					cells.add(
						_toXLSXValue(
							column,
							_getValue(
								column, submission, values, exportContext,
								true),
							yes, no, zoneId));
				}

				xlsxWriter.writeRow(cells);

				count[0]++;
			});

		xlsxWriter.finish();

		return count[0];
	}

	private void _addMetadataColumns(
		List<Column> columns, boolean includeMetadata) {
		for (String key : _METADATA_KEYS) {
			if (includeMetadata || _ALWAYS_KEYS.contains(key)) {
				columns.add(new Column(key, key, true, null, false));
			}
		}
	}

	private ExportContext _createExportContext(
		SubmissionQuery submissionQuery) {
		ExportContext exportContext = new ExportContext();

		_addMetadataColumns(
			exportContext.columns, submissionQuery.isIncludeMetadata());

		List<Campaign> campaigns = new ArrayList<>();

		for (long campaignId : submissionQuery.getCampaignIds()) {
			Campaign campaign = _campaignPersistence.fetchByPrimaryKey(
				campaignId);

			if (campaign != null) {
				campaigns.add(campaign);
			}
		}

		campaigns.sort(
			(campaign1, campaign2) -> campaign2.getCreateDate(
			).compareTo(
				campaign1.getCreateDate()
			));

		Map<String, Column> fieldColumns = new LinkedHashMap<>();

		for (Campaign campaign : campaigns) {
			exportContext.campaigns.put(campaign.getCampaignId(), campaign);

			Group group = _groupLocalService.fetchGroup(campaign.getGroupId());

			exportContext.sites.put(
				campaign.getCampaignId(),
				(group == null) ? "" :
					group.getFriendlyURL(
					).replaceFirst(
						"^/", ""
					));

			for (Form form :
					_formPersistence.findByCampaignId(
						campaign.getCampaignId())) {
				for (FormVersion formVersion :
						_formVersionPersistence.findByFormId(
							form.getFormId())) {
					if (FormVersionStatus.DRAFT.name(
						).equals(
							formVersion.getStatus()
						)) {
						continue;
					}

					exportContext.versions.put(
						formVersion.getFormVersionId(),
						formVersion.getVersion());

					for (FormField formField :
							_formFieldPersistence.findByFormVersionId(
								formVersion.getFormVersionId())) {
						fieldColumns.putIfAbsent(
							formField.getFieldKey(),
							new Column(
								formField.getFieldKey(), formField.getLabel(),
								false, formField.getType(),
								_MULTI_VALUED_TYPES.contains(
									formField.getType())));

						_addOptionLabels(exportContext, formField);
					}
				}
			}
		}

		exportContext.columns.addAll(fieldColumns.values());

		return exportContext;
	}

	private void _addOptionLabels(
		ExportContext exportContext, FormField formField) {
		List<FormFieldOption> formFieldOptions =
			_formFieldOptionPersistence.findByFormFieldId(
				formField.getFormFieldId());

		if (formFieldOptions.isEmpty()) {
			return;
		}

		Map<String, String> labels = new HashMap<>();

		for (FormFieldOption formFieldOption : formFieldOptions) {
			labels.put(formFieldOption.getValue(), formFieldOption.getLabel());
		}

		exportContext.optionLabels.put(formField.getFormFieldId(), labels);
	}

	private DynamicQuery _createSubmissionQuery(
		SubmissionQuery submissionQuery, long afterId) {
		DynamicQuery dynamicQuery = _submissionLocalService.dynamicQuery();

		Long[] campaignIds = new Long[submissionQuery.getCampaignIds().length];

		for (int i = 0; i < campaignIds.length; i++) {
			campaignIds[i] = submissionQuery.getCampaignIds()[i];
		}

		dynamicQuery.add(RestrictionsFactoryUtil.in("campaignId", campaignIds));
		dynamicQuery.add(RestrictionsFactoryUtil.gt("submissionId", afterId));

		if (submissionQuery.getModifiedSince() != null) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.ge(
					"modifiedDate", submissionQuery.getModifiedSince()));
		}

		return dynamicQuery;
	}

	private void _forEachSubmission(
			SubmissionQuery submissionQuery, ExportContext exportContext,
			RowConsumer rowConsumer)
		throws IOException {
		if (submissionQuery.getCampaignIds().length == 0) {
			return;
		}

		long afterId = submissionQuery.getAfterId();

		int limit = submissionQuery.getLimit();

		int written = 0;

		while (true) {
			int batchSize = _BATCH_SIZE;

			if (limit > 0) {
				batchSize = Math.min(_BATCH_SIZE, limit - written);
			}

			if (batchSize <= 0) {
				return;
			}

			DynamicQuery dynamicQuery = _createSubmissionQuery(
				submissionQuery, afterId);

			dynamicQuery.addOrder(OrderFactoryUtil.asc("submissionId"));

			List<Submission> submissions = _submissionLocalService.dynamicQuery(
				dynamicQuery, 0, batchSize);

			if (submissions.isEmpty()) {
				return;
			}

			Map<Long, Map<String, List<SubmissionValue>>> values = _getValues(
				submissions);

			for (Submission submission : submissions) {
				rowConsumer.accept(
					submission,
					values.getOrDefault(
						submission.getSubmissionId(), Collections.emptyMap()));
			}

			Submission lastSubmission = submissions.get(submissions.size() - 1);

			afterId = lastSubmission.getSubmissionId();

			written += submissions.size();

			if (submissions.size() < batchSize) {
				return;
			}
		}
	}

	private Object _getMetadataValue(
		String key, Submission submission, ExportContext exportContext) {
		Campaign campaign = exportContext.campaigns.get(
			submission.getCampaignId());

		switch (key) {
			case "campaign":
				return (campaign == null) ? null : campaign.getName();
			case "campaignId":
				return submission.getCampaignId();
			case "consentDate":
				return submission.getConsentDate();
			case "consentVersion":
				return submission.getConsentVersion();
			case "createdAt":
				return submission.getCreateDate();
			case "formVersion":
				return exportContext.versions.get(
					submission.getFormVersionId());
			case "instance":
				return (campaign == null) ? null : campaign.getInstanceKey();
			case "languageId":
				return submission.getLanguageId();
			case "modifiedAt":
				return submission.getModifiedDate();
			case "referrer":
				return submission.getReferrer();
			case "series":
				if (campaign == null) {
					return null;
				}

				return Validator.isNotNull(campaign.getSeriesKey()) ?
					campaign.getSeriesKey() : campaign.getSlug();
			case "site":
				return exportContext.sites.get(submission.getCampaignId());
			case "sourceUrl":
				return submission.getSourceUrl();
			case "status":
				return submission.getStatus();
			case "submissionId":
				return submission.getSubmissionId();
			case "utmCampaign":
				return submission.getUtmCampaign();
			case "utmContent":
				return submission.getUtmContent();
			case "utmMedium":
				return submission.getUtmMedium();
			case "utmSource":
				return submission.getUtmSource();
			case "utmTerm":
				return submission.getUtmTerm();
			default:
				return null;
		}
	}

	private Object _getValue(
		Column column, Submission submission,
		Map<String, List<SubmissionValue>> values, ExportContext exportContext,
		boolean useLabels) {
		if (column.metadata) {
			return _getMetadataValue(column.key, submission, exportContext);
		}

		List<SubmissionValue> submissionValues = values.get(column.key);

		if ((submissionValues == null) || submissionValues.isEmpty()) {
			return column.multiValued ? Collections.emptyList() : null;
		}

		List<Object> typedValues = new ArrayList<>();

		for (SubmissionValue submissionValue : submissionValues) {
			typedValues.add(
				_toTypedValue(submissionValue, exportContext, useLabels));
		}

		if (column.multiValued) {
			return typedValues;
		}

		return typedValues.get(0);
	}

	private Map<Long, Map<String, List<SubmissionValue>>> _getValues(
		List<Submission> submissions) {
		Long[] submissionIds = new Long[submissions.size()];

		for (int i = 0; i < submissions.size(); i++) {
			Submission submission = submissions.get(i);

			submissionIds[i] = submission.getSubmissionId();
		}

		DynamicQuery dynamicQuery =
			_submissionValueLocalService.dynamicQuery();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("submissionId", submissionIds));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("submissionId"));
		dynamicQuery.addOrder(OrderFactoryUtil.asc("valueIndex"));

		List<SubmissionValue> submissionValues =
			_submissionValueLocalService.dynamicQuery(dynamicQuery);

		Map<Long, Map<String, List<SubmissionValue>>> values = new HashMap<>();

		for (SubmissionValue submissionValue : submissionValues) {
			values.computeIfAbsent(
				submissionValue.getSubmissionId(), key -> new HashMap<>()
			).computeIfAbsent(
				submissionValue.getFieldKey(), key -> new ArrayList<>()
			).add(
				submissionValue
			);
		}

		return values;
	}

	private boolean _hasMore(SubmissionQuery submissionQuery, long lastId) {
		if (submissionQuery.getLimit() <= 0) {
			return false;
		}

		return _submissionLocalService.dynamicQueryCount(
			_createSubmissionQuery(submissionQuery, lastId)) > 0;
	}

	private boolean _isText(Object value) {
		if (value instanceof List) {
			return true;
		}

		return value instanceof String;
	}

	private String _toCSVText(Object value) {
		if (value == null) {
			return "";
		}

		if (value instanceof List) {
			List<String> items = new ArrayList<>();

			for (Object item : (List<?>)value) {
				items.add(_toCSVText(item));
			}

			return String.join("; ", items);
		}

		if (value instanceof Date) {
			return ((Date)value).toInstant(
			).toString();
		}

		if (value instanceof BigDecimal) {
			return ((BigDecimal)value).toPlainString();
		}

		return String.valueOf(value);
	}

	private Object _toJSONValue(Object value) {
		if (value instanceof List) {
			List<Object> items = new ArrayList<>();

			for (Object item : (List<?>)value) {
				items.add(_toJSONValue(item));
			}

			return items;
		}

		if (value instanceof Date) {
			return ((Date)value).toInstant(
			).toString();
		}

		return value;
	}

	private Object _toTypedValue(
		SubmissionValue submissionValue, ExportContext exportContext,
		boolean useLabels) {
		String valueType = submissionValue.getValueType();

		if (FieldValueType.NUMBER.name(
			).equals(
				valueType
			)) {
			return submissionValue.getNumberValue();
		}

		if (FieldValueType.BOOLEAN.name(
			).equals(
				valueType
			)) {
			return submissionValue.isBooleanValue();
		}

		if (FieldValueType.DATE.name(
			).equals(
				valueType
			)) {
			Date dateValue = submissionValue.getDateValue();

			return (dateValue == null) ? null :
				dateValue.toInstant(
				).atZone(
					ZoneOffset.UTC
				).toLocalDate(
				).toString();
		}

		if (FieldValueType.DATETIME.name(
			).equals(
				valueType
			)) {
			return submissionValue.getDateValue();
		}

		if (FieldValueType.TEXT.name(
			).equals(
				valueType
			)) {
			return submissionValue.getTextValue();
		}

		String value = submissionValue.getStringValue();

		if (useLabels) {
			Map<String, String> labels = exportContext.optionLabels.get(
				submissionValue.getFormFieldId());

			if ((labels != null) && labels.containsKey(value)) {
				return labels.get(value);
			}
		}

		return value;
	}

	private Object _toXLSXValue(
		Column column, Object value, String yes, String no, ZoneId zoneId) {
		if (value == null) {
			return null;
		}

		if (value instanceof List) {
			List<String> items = new ArrayList<>();

			for (Object item : (List<?>)value) {
				Object itemValue = _toXLSXValue(column, item, yes, no, zoneId);

				if (itemValue != null) {
					items.add(String.valueOf(itemValue));
				}
			}

			return String.join(", ", items);
		}

		if (value instanceof Boolean) {
			return (Boolean)value ? yes : no;
		}

		if (value instanceof Date) {
			return LocalDateTime.ofInstant(((Date)value).toInstant(), zoneId);
		}

		if (DateFieldType.KEY.equals(column.type) && (value instanceof String)) {
			try {
				return LocalDate.parse((String)value);
			}
			catch (DateTimeParseException dateTimeParseException) {
				return value;
			}
		}

		return value;
	}

	private int _readOnly(Callable<Integer> callable)
		throws IOException, PortalException {
		try {
			return CampaignTransactionUtil.invokeReadOnly(callable);
		}
		catch (IOException | PortalException | RuntimeException exception) {
			throw exception;
		}
		catch (Throwable throwable) {
			throw new SystemException(throwable);
		}
	}

	private static final Set<String> _ALWAYS_KEYS = Set.of(
		"createdAt", "submissionId");

	private static final int _BATCH_SIZE = 500;

	private static final List<String> _METADATA_KEYS = Arrays.asList(
		"submissionId", "createdAt", "modifiedAt", "campaignId", "campaign",
		"series", "instance", "site", "formVersion", "status", "languageId",
		"sourceUrl", "referrer", "utmSource", "utmMedium", "utmCampaign",
		"utmContent", "utmTerm", "consentVersion", "consentDate");

	private static final Set<String> _MULTI_VALUED_TYPES = Set.of(
		"checkbox", "multiSelect");

	@Reference
	private CampaignPersistence _campaignPersistence;

	@Reference
	private FormFieldOptionPersistence _formFieldOptionPersistence;

	@Reference
	private FormFieldPersistence _formFieldPersistence;

	@Reference
	private FormPersistence _formPersistence;

	@Reference
	private FormVersionPersistence _formVersionPersistence;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private SubmissionLocalService _submissionLocalService;

	@Reference
	private SubmissionValueLocalService _submissionValueLocalService;

	private static class Column {
		public Column(
			String key, String label, boolean metadata, String type,
			boolean multiValued) {
			this.key = key;
			this.label = label;
			this.metadata = metadata;
			this.type = type;
			this.multiValued = multiValued;
		}

		public final String key;
		public final String label;
		public final boolean metadata;
		public final boolean multiValued;
		public final String type;
	}

	private static class ExportContext {
		public final Map<Long, Campaign> campaigns = new HashMap<>();
		public final List<Column> columns = new ArrayList<>();
		public final Map<Long, Map<String, String>> optionLabels =
			new HashMap<>();
		public final Map<Long, String> sites = new HashMap<>();
		public final Map<Long, Integer> versions = new HashMap<>();
	}

	@FunctionalInterface
	private interface RowConsumer {
		public void accept(
				Submission submission,
				Map<String, List<SubmissionValue>> values)
			throws IOException;
	}

}
