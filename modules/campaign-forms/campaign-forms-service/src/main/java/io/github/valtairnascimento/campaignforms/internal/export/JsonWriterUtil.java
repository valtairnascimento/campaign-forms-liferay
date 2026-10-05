package io.github.valtairnascimento.campaignforms.internal.export;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

public class JsonWriterUtil {
	public static void write(StringBuilder sb, Object value) {
		if (value == null) {
			sb.append("null");
		}
		else if (value instanceof Boolean) {
			sb.append(value);
		}
		else if (value instanceof BigDecimal) {
			sb.append(((BigDecimal)value).toPlainString());
		}
		else if (value instanceof Number) {
			sb.append(value);
		}
		else if (value instanceof Map) {
			sb.append('{');

			boolean first = true;

			for (Map.Entry<?, ?> entry : ((Map<?, ?>)value).entrySet()) {
				if (!first) {
					sb.append(',');
				}

				first = false;

				_writeString(sb, String.valueOf(entry.getKey()));
				sb.append(':');
				write(sb, entry.getValue());
			}

			sb.append('}');
		}
		else if (value instanceof List) {
			sb.append('[');

			boolean first = true;

			for (Object item : (List<?>)value) {
				if (!first) {
					sb.append(',');
				}

				first = false;

				write(sb, item);
			}

			sb.append(']');
		}
		else {
			_writeString(sb, String.valueOf(value));
		}
	}

	private static void _writeString(StringBuilder sb, String value) {
		sb.append('"');

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);

			switch (c) {
				case '"':
					sb.append("\\\"");

					break;
				case '\\':
					sb.append("\\\\");

					break;
				case '\n':
					sb.append("\\n");

					break;
				case '\r':
					sb.append("\\r");

					break;
				case '\t':
					sb.append("\\t");

					break;
				default:

					if ((c < 0x20) || (c == (char)0x2028) || (c == (char)0x2029) ||
						(c == '<')) {
						sb.append(String.format("\\u%04x", (int)c));
					}
					else {
						sb.append(c);
					}
			}
		}

		sb.append('"');
	}

	private JsonWriterUtil() {
	}

}
