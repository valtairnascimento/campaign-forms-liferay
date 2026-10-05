package io.github.valtairnascimento.campaignforms.internal.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;

import java.nio.charset.StandardCharsets;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Assert;
import org.junit.Test;

public class XlsxWriterTest {
	@Test
	public void testColumnNames() {
		Assert.assertEquals("A", XlsxWriter.getColumnName(0));
		Assert.assertEquals("Z", XlsxWriter.getColumnName(25));
		Assert.assertEquals("AA", XlsxWriter.getColumnName(26));
		Assert.assertEquals("AZ", XlsxWriter.getColumnName(51));
		Assert.assertEquals("BA", XlsxWriter.getColumnName(52));
	}

	@Test
	public void testSerialDates() {
		Assert.assertEquals(
			"1", XlsxWriter.toSerial(LocalDateTime.of(1899, 12, 31, 0, 0)));
		Assert.assertEquals(
			"46297", XlsxWriter.toSerial(LocalDateTime.of(2026, 10, 2, 0, 0)));
		Assert.assertEquals(
			"46297.5",
			XlsxWriter.toSerial(LocalDateTime.of(2026, 10, 2, 12, 0)));
	}

	@Test
	public void testWorkbook() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		XlsxWriter xlsxWriter = new XlsxWriter(
			byteArrayOutputStream, "Inscritos: [2026]");

		xlsxWriter.writeHeader(Arrays.asList("Nome", "Idade", "Nascimento"));
		xlsxWriter.writeRow(
			Arrays.asList(
				"=HYPERLINK(\"x\") & <b>", new BigDecimal("42"),
				LocalDate.of(2000, 1, 1)));
		xlsxWriter.writeRow(Arrays.asList("Ana\u0001", null, null));
		xlsxWriter.finish();

		Map<String, String> entries = _unzip(
			byteArrayOutputStream.toByteArray());

		Assert.assertEquals(
			Arrays.asList(
				"[Content_Types].xml", "_rels/.rels", "xl/workbook.xml",
				"xl/_rels/workbook.xml.rels", "xl/styles.xml",
				"xl/worksheets/sheet1.xml"),
			Arrays.asList(entries.keySet().toArray()));

		String workbook = entries.get("xl/workbook.xml");

		Assert.assertTrue(
			workbook, workbook.contains("<sheet name=\"Inscritos   2026\""));

		String sheet = entries.get("xl/worksheets/sheet1.xml");

		Assert.assertTrue(
			sheet,
			sheet.contains(
				"<c r=\"A1\" t=\"inlineStr\" s=\"1\"><is><t " +
					"xml:space=\"preserve\">Nome</t></is></c>"));
		Assert.assertTrue(sheet, sheet.contains("state=\"frozen\""));
		Assert.assertTrue(
			sheet,
			sheet.contains(
				"<c r=\"A2\" t=\"inlineStr\"><is><t xml:space=\"preserve\">" +
					"=HYPERLINK(&quot;x&quot;) &amp; &lt;b&gt;</t></is></c>"));
		Assert.assertFalse(sheet, sheet.contains("<f>"));
		Assert.assertTrue(sheet, sheet.contains("<c r=\"B2\"><v>42</v></c>"));
		Assert.assertTrue(
			sheet, sheet.contains("<c r=\"C2\" s=\"2\"><v>36526</v></c>"));

		Assert.assertTrue(sheet, sheet.contains(">Ana</t>"));
		Assert.assertFalse(sheet, sheet.contains("r=\"B3\""));
		Assert.assertTrue(sheet, sheet.contains("<autoFilter ref=\"A1:C3\"/>"));
	}

	@Test
	public void testXMLText() {
		Assert.assertEquals(
			"a &amp; b &lt;c&gt; &quot;d&quot;",
			XlsxWriter.toXMLText("a & b <c> \"d\""));
		Assert.assertEquals("ab", XlsxWriter.toXMLText("a\u0000￾b"));
		Assert.assertEquals(
			"😀", XlsxWriter.toXMLText("😀"));
		Assert.assertEquals("x", XlsxWriter.toXMLText("x\uD83D"));
	}

	private Map<String, String> _unzip(byte[] bytes) throws Exception {
		Map<String, String> entries = new LinkedHashMap<>();

		try (ZipInputStream zipInputStream = new ZipInputStream(
				new ByteArrayInputStream(bytes))) {
			ZipEntry zipEntry = null;

			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				entries.put(
					zipEntry.getName(),
					new String(
						zipInputStream.readAllBytes(), StandardCharsets.UTF_8));
			}
		}

		return entries;
	}

}
