package io.github.valtairnascimento.campaignforms.internal.export;

import java.io.IOException;
import java.io.Writer;

import java.util.List;

public class CsvUtil {
	public static final char BOM = (char)0xFEFF;

	public static String escape(String value, char separator, boolean text) {
		if (value == null) {
			return "";
		}

		String cell = value;

		if (text && !cell.isEmpty()) {
			char first = cell.charAt(0);

			if ((first == '=') || (first == '+') || (first == '-') ||
				(first == '@') || (first == '\t') || (first == '\r')) {
				cell = "'" + cell;
			}
		}

		boolean quote =
			(cell.indexOf(separator) >= 0) || (cell.indexOf('"') >= 0) ||
			(cell.indexOf('\n') >= 0) || (cell.indexOf('\r') >= 0);

		if (!quote) {
			return cell;
		}

		return "\"" + cell.replace("\"", "\"\"") + "\"";
	}

	public static void writeRow(
			Writer writer, List<String> cells, boolean[] text, char separator)
		throws IOException {
		for (int i = 0; i < cells.size(); i++) {
			if (i > 0) {
				writer.write(separator);
			}

			writer.write(
				escape(cells.get(i), separator, (text == null) || text[i]));
		}

		writer.write("\r\n");
	}

	private CsvUtil() {
	}

}
