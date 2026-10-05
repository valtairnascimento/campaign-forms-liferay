package io.github.valtairnascimento.campaignforms.internal.notification;

import io.github.valtairnascimento.campaignforms.page.SimpleMarkup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationTemplateRenderer {
	public static final String TABLE_KEY = "submission.table";

	public static List<String> resolveAddresses(
		String spec, NotificationData notificationData) {
		Map<String, String> addresses = new LinkedHashMap<>();

		if (spec == null) {
			return new ArrayList<>();
		}

		for (String token : spec.trim().split("[,;\\s]+")) {
			if (token.isEmpty()) {
				continue;
			}

			List<String> candidates = new ArrayList<>();

			Matcher matcher = _placeholderPattern.matcher(token);

			if (matcher.matches()) {
				String key = matcher.group(1);

				if (key.startsWith(_SUBMISSION)) {
					candidates.addAll(
						notificationData.getFieldValues(
							key.substring(_SUBMISSION.length())));
				}
			}
			else {
				candidates.add(token);
			}

			for (String candidate : candidates) {
				String address = (candidate == null) ? "" : candidate.trim();

				if (isEmailAddress(address)) {
					addresses.putIfAbsent(
						address.toLowerCase(Locale.ROOT), address);
				}
			}
		}

		return new ArrayList<>(addresses.values());
	}

	public static boolean isEmailAddress(String address) {
		return (address != null) && (address.length() <= 254) &&
			   _emailPattern.matcher(
				   address
			   ).matches();
	}

	public static String renderHTML(
		String template, NotificationData notificationData) {
		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html><html><body style=\"font-family: Arial, ");
		sb.append("Helvetica, sans-serif; font-size: 14px; line-height: 1.5; ");
		sb.append("color: #272833;\">");

		Matcher matcher = _placeholderPattern.matcher(
			(template == null) ? "" : template);

		int start = 0;

		String text = (template == null) ? "" : template;

		while (matcher.find()) {
			sb.append(_toHTML(text.substring(start, matcher.start())));

			String key = matcher.group(1);

			if (TABLE_KEY.equals(key)) {
				sb.append(_renderTable(notificationData));
			}
			else {
				sb.append(_toHTML(_getValue(notificationData, key)));
			}

			start = matcher.end();
		}

		sb.append(_toHTML(text.substring(start)));
		sb.append("</body></html>");

		return sb.toString();
	}

	public static String renderText(
		String template, NotificationData notificationData) {
		if (template == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		Matcher matcher = _placeholderPattern.matcher(template);

		int start = 0;

		while (matcher.find()) {
			sb.append(template, start, matcher.start());

			String key = matcher.group(1);

			if (!TABLE_KEY.equals(key)) {
				sb.append(_getValue(notificationData, key));
			}

			start = matcher.end();
		}

		sb.append(template.substring(start));

		return sb.toString(
		).replaceAll(
			"[\\r\\n\\t]+", " "
		).replaceAll(
			" {2,}", " "
		).trim();
	}

	private static String _getValue(
		NotificationData notificationData, String key) {
		String value = notificationData.getValue(key);

		if ((value == null) && key.startsWith("campaign.attributes.")) {
			value = notificationData.getValue(
				"campaign." + key.substring("campaign.attributes.".length()));
		}

		return (value == null) ? "" : value;
	}

	private static String _renderTable(NotificationData notificationData) {
		StringBuilder sb = new StringBuilder();

		sb.append("<table cellpadding=\"6\" cellspacing=\"0\" style=\"");
		sb.append("border-collapse: collapse; border: 1px solid #e7e7ed;\">");
		sb.append("<tbody>");

		for (String[] row : notificationData.getTableRows()) {
			sb.append("<tr><th align=\"left\" style=\"border: 1px solid ");
			sb.append("#e7e7ed; background: #f7f8f9; vertical-align: top;\">");
			sb.append(_toHTML(row[0]));
			sb.append("</th><td style=\"border: 1px solid #e7e7ed;\">");
			sb.append(_toHTML(row[1]));
			sb.append("</td></tr>");
		}

		sb.append("</tbody></table>");

		return sb.toString();
	}

	private static String _toHTML(String text) {
		return SimpleMarkup.escape(
			text
		).replace(
			"\r\n", "\n"
		).replace(
			"\n", "<br>"
		);
	}

	private static final String _SUBMISSION = "submission.";

	private static final Pattern _emailPattern = Pattern.compile(
		"^[^@\\s<>\"',;:()\\[\\]\\\\]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\." +
			"[A-Za-z]{2,}$");
	private static final Pattern _placeholderPattern = Pattern.compile(
		"\\$\\{([a-z]+\\.[A-Za-z0-9_]+(?:\\.[A-Za-z0-9_]+)?)\\}");
}
