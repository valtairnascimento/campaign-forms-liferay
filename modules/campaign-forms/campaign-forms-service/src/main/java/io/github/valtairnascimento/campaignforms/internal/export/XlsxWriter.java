package io.github.valtairnascimento.campaignforms.internal.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class XlsxWriter {
	public XlsxWriter(OutputStream outputStream, String sheetName) {
		_zipOutputStream = new ZipOutputStream(outputStream);
		_sheetName = _toSheetName(sheetName);
	}

	public void finish() throws IOException {
		_writer.write("</sheetData>");

		if ((_columnCount > 0) && (_rowCount > 0)) {
			_writer.write("<autoFilter ref=\"A1:");
			_writer.write(getColumnName(_columnCount - 1));
			_writer.write(String.valueOf(_rowCount));
			_writer.write("\"/>");
		}

		_writer.write("</worksheet>");
		_writer.flush();

		_zipOutputStream.closeEntry();
		_zipOutputStream.finish();
		_zipOutputStream.flush();
	}

	public void writeHeader(List<String> labels) throws IOException {
		_columnCount = labels.size();

		_writeStaticParts();

		_zipOutputStream.putNextEntry(new ZipEntry("xl/worksheets/sheet1.xml"));

		_writer = new OutputStreamWriter(
			new NonClosingOutputStream(_zipOutputStream),
			StandardCharsets.UTF_8);

		_writer.write(_XML_HEADER);
		_writer.write("<worksheet xmlns=\"");
		_writer.write(_NAMESPACE);
		_writer.write("\"><sheetViews><sheetView workbookViewId=\"0\">");
		_writer.write("<pane ySplit=\"1\" topLeftCell=\"A2\" ");
		_writer.write("activePane=\"bottomLeft\" state=\"frozen\"/>");
		_writer.write("</sheetView></sheetViews>");

		if (_columnCount > 0) {
			_writer.write("<cols>");

			for (int i = 0; i < _columnCount; i++) {
				String label = labels.get(i);

				int width = Math.max(
					14, Math.min(48, ((label == null) ? 0 : label.length()) + 4));

				_writer.write("<col min=\"");
				_writer.write(String.valueOf(i + 1));
				_writer.write("\" max=\"");
				_writer.write(String.valueOf(i + 1));
				_writer.write("\" width=\"");
				_writer.write(String.valueOf(width));
				_writer.write("\" customWidth=\"1\"/>");
			}

			_writer.write("</cols>");
		}

		_writer.write("<sheetData>");

		_writeRow(labels.toArray(), _STYLE_HEADER);
	}

	public void writeRow(List<?> values) throws IOException {
		_writeRow(values.toArray(), _STYLE_DEFAULT);
	}

	protected static String getColumnName(int index) {
		StringBuilder sb = new StringBuilder();

		int value = index + 1;

		while (value > 0) {
			int remainder = (value - 1) % 26;

			sb.insert(0, (char)('A' + remainder));

			value = (value - 1) / 26;
		}

		return sb.toString();
	}

	protected static String toSerial(LocalDateTime localDateTime) {
		BigDecimal days = BigDecimal.valueOf(
			localDateTime.toLocalDate(
			).toEpochDay() + _EPOCH_OFFSET);

		BigDecimal fraction = BigDecimal.valueOf(
			localDateTime.toLocalTime(
			).toSecondOfDay()
		).divide(
			BigDecimal.valueOf(86400), 10, RoundingMode.HALF_UP
		);

		return days.add(
			fraction
		).stripTrailingZeros(
		).toPlainString();
	}

	protected static String toXMLText(String text) {
		StringBuilder sb = new StringBuilder(text.length());

		int length = Math.min(text.length(), _MAX_CELL_LENGTH);

		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);

			if (Character.isHighSurrogate(c) && ((i + 1) < length) &&
				Character.isLowSurrogate(text.charAt(i + 1))) {
				sb.append(c);
				sb.append(text.charAt(++i));

				continue;
			}

			if (Character.isSurrogate(c) || (c == 0xFFFE) || (c == 0xFFFF) ||
				((c < 0x20) && (c != '\t') && (c != '\n') && (c != '\r'))) {
				continue;
			}

			switch (c) {
				case '&':
					sb.append("&amp;");

					break;
				case '<':
					sb.append("&lt;");

					break;
				case '>':
					sb.append("&gt;");

					break;
				case '"':
					sb.append("&quot;");

					break;
				case '\r':
					sb.append("&#13;");

					break;
				default:
					sb.append(c);
			}
		}

		return sb.toString();
	}

	private String _toSheetName(String sheetName) {
		String name = (sheetName == null) ? "" :
			sheetName.replaceAll(
				"[\\[\\]:*?/\\\\]", " "
			).trim();

		if (name.isEmpty()) {
			name = "Sheet1";
		}

		return (name.length() > 31) ? name.substring(0, 31) : name;
	}

	private void _writeCell(String reference, Object value, int rowStyle)
		throws IOException {
		if (value == null) {
			return;
		}

		if ((value instanceof Number) && !(value instanceof Double &&
			 (((Double)value).isNaN() || ((Double)value).isInfinite()))) {
			String number = (value instanceof BigDecimal) ?
				((BigDecimal)value).toPlainString() : String.valueOf(value);

			_writer.write("<c r=\"" + reference + "\"><v>");
			_writer.write(number);
			_writer.write("</v></c>");

			return;
		}

		if (value instanceof LocalDate) {
			_writer.write(
				"<c r=\"" + reference + "\" s=\"" + _STYLE_DATE + "\"><v>");
			_writer.write(toSerial(((LocalDate)value).atStartOfDay()));
			_writer.write("</v></c>");

			return;
		}

		if (value instanceof LocalDateTime) {
			_writer.write(
				"<c r=\"" + reference + "\" s=\"" + _STYLE_DATE_TIME +
					"\"><v>");
			_writer.write(toSerial((LocalDateTime)value));
			_writer.write("</v></c>");

			return;
		}

		String text = String.valueOf(value);

		if (text.isEmpty()) {
			return;
		}

		_writer.write("<c r=\"" + reference + "\" t=\"inlineStr\"");

		if (rowStyle != _STYLE_DEFAULT) {
			_writer.write(" s=\"" + rowStyle + "\"");
		}

		_writer.write("><is><t xml:space=\"preserve\">");
		_writer.write(toXMLText(text));
		_writer.write("</t></is></c>");
	}

	private void _writeEntry(String name, String content) throws IOException {
		_zipOutputStream.putNextEntry(new ZipEntry(name));
		_zipOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
		_zipOutputStream.closeEntry();
	}

	private void _writeRow(Object[] values, int style) throws IOException {
		_rowCount++;

		_writer.write("<row r=\"" + _rowCount + "\">");

		for (int i = 0; i < values.length; i++) {
			_writeCell(getColumnName(i) + _rowCount, values[i], style);
		}

		_writer.write("</row>");
	}

	private void _writeStaticParts() throws IOException {
		_writeEntry(
			"[Content_Types].xml",
			String.join(
				"", _XML_HEADER,
				"<Types xmlns=\"http://schemas.openxmlformats.org/package/",
				"2006/content-types\">",
				"<Default Extension=\"rels\" ContentType=\"",
				"application/vnd.openxmlformats-package.relationships+xml\"/>",
				"<Default Extension=\"xml\" ContentType=\"application/xml\"/>",
				"<Override PartName=\"/xl/workbook.xml\" ContentType=\"",
				_SPREADSHEET_ML, ".sheet.main+xml\"/>",
				"<Override PartName=\"/xl/worksheets/sheet1.xml\" ",
				"ContentType=\"", _SPREADSHEET_ML, ".worksheet+xml\"/>",
				"<Override PartName=\"/xl/styles.xml\" ContentType=\"",
				_SPREADSHEET_ML, ".styles+xml\"/>", "</Types>"));

		_writeEntry(
			"_rels/.rels",
			String.join(
				"", _XML_HEADER, "<Relationships xmlns=\"", _RELATIONSHIPS,
				"\"><Relationship Id=\"rId1\" Type=\"", _OFFICE_DOCUMENT,
				"/officeDocument\" Target=\"xl/workbook.xml\"/>",
				"</Relationships>"));

		_writeEntry(
			"xl/workbook.xml",
			String.join(
				"", _XML_HEADER, "<workbook xmlns=\"", _NAMESPACE,
				"\" xmlns:r=\"", _OFFICE_DOCUMENT, "\"><sheets><sheet name=\"",
				toXMLText(_sheetName),
				"\" sheetId=\"1\" r:id=\"rId1\"/></sheets></workbook>"));

		_writeEntry(
			"xl/_rels/workbook.xml.rels",
			String.join(
				"", _XML_HEADER, "<Relationships xmlns=\"", _RELATIONSHIPS,
				"\"><Relationship Id=\"rId1\" Type=\"", _OFFICE_DOCUMENT,
				"/worksheet\" Target=\"worksheets/sheet1.xml\"/>",
				"<Relationship Id=\"rId2\" Type=\"", _OFFICE_DOCUMENT,
				"/styles\" Target=\"styles.xml\"/></Relationships>"));

		_writeEntry(
			"xl/styles.xml",
			String.join(
				"", _XML_HEADER, "<styleSheet xmlns=\"", _NAMESPACE, "\">",
				"<numFmts count=\"2\">",
				"<numFmt numFmtId=\"164\" formatCode=\"dd/mm/yyyy\"/>",
				"<numFmt numFmtId=\"165\" formatCode=\"dd/mm/yyyy hh:mm\"/>",
				"</numFmts>", "<fonts count=\"2\">",
				"<font><sz val=\"11\"/><name val=\"Calibri\"/></font>",
				"<font><b/><sz val=\"11\"/><name val=\"Calibri\"/></font>",
				"</fonts>", "<fills count=\"2\">",
				"<fill><patternFill patternType=\"none\"/></fill>",
				"<fill><patternFill patternType=\"gray125\"/></fill>",
				"</fills>", "<borders count=\"1\">",
				"<border><left/><right/><top/><bottom/><diagonal/></border>",
				"</borders>", "<cellStyleXfs count=\"1\">",
				"<xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\"/>",
				"</cellStyleXfs>", "<cellXfs count=\"4\">",
				"<xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\" ",
				"xfId=\"0\"/>",
				"<xf numFmtId=\"0\" fontId=\"1\" fillId=\"0\" borderId=\"0\" ",
				"xfId=\"0\" applyFont=\"1\"/>",
				"<xf numFmtId=\"164\" fontId=\"0\" fillId=\"0\" borderId=\"0\" ",
				"xfId=\"0\" applyNumberFormat=\"1\"/>",
				"<xf numFmtId=\"165\" fontId=\"0\" fillId=\"0\" borderId=\"0\" ",
				"xfId=\"0\" applyNumberFormat=\"1\"/>", "</cellXfs>",
				"<cellStyles count=\"1\">",
				"<cellStyle name=\"Normal\" xfId=\"0\" builtinId=\"0\"/>",
				"</cellStyles></styleSheet>"));
	}

	private static final long _EPOCH_OFFSET = 25569;

	private static final int _MAX_CELL_LENGTH = 32767;

	private static final String _NAMESPACE =
		"http://schemas.openxmlformats.org/spreadsheetml/2006/main";

	private static final String _OFFICE_DOCUMENT =
		"http://schemas.openxmlformats.org/officeDocument/2006/relationships";

	private static final String _RELATIONSHIPS =
		"http://schemas.openxmlformats.org/package/2006/relationships";

	private static final String _SPREADSHEET_ML =
		"application/vnd.openxmlformats-officedocument.spreadsheetml";

	private static final int _STYLE_DATE = 2;

	private static final int _STYLE_DATE_TIME = 3;

	private static final int _STYLE_DEFAULT = 0;

	private static final int _STYLE_HEADER = 1;

	private static final String _XML_HEADER =
		"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";

	private int _columnCount;
	private int _rowCount;
	private final String _sheetName;
	private Writer _writer;
	private final ZipOutputStream _zipOutputStream;

	private static class NonClosingOutputStream extends OutputStream {
		public NonClosingOutputStream(OutputStream outputStream) {
			_outputStream = outputStream;
		}

		@Override
		public void close() throws IOException {
			_outputStream.flush();
		}

		@Override
		public void flush() throws IOException {
			_outputStream.flush();
		}

		@Override
		public void write(byte[] bytes, int offset, int length)
			throws IOException {
			_outputStream.write(bytes, offset, length);
		}

		@Override
		public void write(int b) throws IOException {
			_outputStream.write(b);
		}

		private final OutputStream _outputStream;
	}

}
