package io.github.valtairnascimento.campaignforms.internal.export;

import java.io.StringWriter;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ExportFormatTest {
	@Test
	public void testCsvEscape() {
		Assert.assertEquals("", CsvUtil.escape(null, ';', true));
		Assert.assertEquals("Maria", CsvUtil.escape("Maria", ';', true));
		Assert.assertEquals(
			"\"Maria \"\"Excel\"\"\"",
			CsvUtil.escape("Maria \"Excel\"", ';', true));
		Assert.assertEquals("\"a;b\"", CsvUtil.escape("a;b", ';', true));
		Assert.assertEquals("a,b", CsvUtil.escape("a,b", ';', true));
		Assert.assertEquals("\"a,b\"", CsvUtil.escape("a,b", ',', true));
		Assert.assertEquals(
			"\"linha 1\nlinha 2\"", CsvUtil.escape("linha 1\nlinha 2", ';', true));
	}

	@Test
	public void testCsvFormulaInjection() {
		for (String formula :
				Arrays.asList(
					"=HYPERLINK(\"x\")", "+1", "-1+2", "@SUM(A1)", "\tx")) {
			Assert.assertTrue(
				formula,
				CsvUtil.escape(
					formula, ';', true
				).replace(
					"\"", ""
				).startsWith(
					"'"
				));
		}

		Assert.assertEquals("-12.5", CsvUtil.escape("-12.5", ';', false));
	}

	@Test
	public void testCsvWriteRow() throws Exception {
		StringWriter stringWriter = new StringWriter();

		CsvUtil.writeRow(
			stringWriter, Arrays.asList("=1", "-3", "a;b"),
			new boolean[] {true, false, true}, ';');

		Assert.assertEquals("'=1;-3;\"a;b\"\r\n", stringWriter.toString());
		Assert.assertEquals(0xFEFF, CsvUtil.BOM);
	}

	@Test
	public void testJsonKeepsNullsAndTypes() {
		Map<String, Object> row = new LinkedHashMap<>();

		row.put("id", 7L);
		row.put("nome", "Maria \"Excel\"\n");
		row.put("valor", new BigDecimal("1E+3"));
		row.put("aceite", true);
		row.put("vazio", null);
		row.put("interesses", Arrays.asList("inovacao", "gestao"));
		row.put("nenhum", Collections.emptyList());

		StringBuilder sb = new StringBuilder();

		JsonWriterUtil.write(sb, row);

		Assert.assertEquals(
			"{\"id\":7,\"nome\":\"Maria \\\"Excel\\\"\\n\",\"valor\":1000," +
				"\"aceite\":true,\"vazio\":null," +
					"\"interesses\":[\"inovacao\",\"gestao\"],\"nenhum\":[]}",
			sb.toString());
	}

	@Test
	public void testJsonEscapesHtmlAndLineSeparators() {
		StringBuilder sb = new StringBuilder();

		JsonWriterUtil.write(
			sb,
			"</script>" + (char)0x2028 + (char)0x2029 + (char)0x01 + "\\");

		Assert.assertEquals(
			"\"\\u003c/script>\\u2028\\u2029\\u0001\\\\\"", sb.toString());
	}

}
