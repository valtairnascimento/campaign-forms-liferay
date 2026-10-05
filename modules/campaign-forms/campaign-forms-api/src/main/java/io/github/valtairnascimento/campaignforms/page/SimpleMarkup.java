package io.github.valtairnascimento.campaignforms.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMarkup {
	public static String escape(String text) {
		if (text == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder(text.length() + 16);

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c == '&') {
				sb.append("&amp;");
			}
			else if (c == '<') {
				sb.append("&lt;");
			}
			else if (c == '>') {
				sb.append("&gt;");
			}
			else if (c == '"') {
				sb.append("&quot;");
			}
			else if (c == '\'') {
				sb.append("&#39;");
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static List<String> getInvalidLinks(String markup) {
		List<String> invalidLinks = new ArrayList<>();

		if (markup == null) {
			return invalidLinks;
		}

		Matcher matcher = _linkPattern.matcher(markup);

		while (matcher.find()) {
			String url = matcher.group(2);

			if (!isAllowedURL(url)) {
				invalidLinks.add(url);
			}
		}

		return invalidLinks;
	}

	public static boolean isAllowedURL(String url) {
		if ((url == null) || url.isEmpty() || (url.length() > 2000)) {
			return false;
		}

		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);

			if (Character.isWhitespace(c) || Character.isISOControl(c) ||
				(c == '"') || (c == '\'') || (c == '<') || (c == '>') ||
				(c == '\\') || (c == '`')) {
				return false;
			}
		}

		String lowerCaseURL = url.toLowerCase(Locale.ROOT);

		if (lowerCaseURL.startsWith("https://")) {
			return _hostPattern.matcher(
				url.substring(8)
			).lookingAt();
		}

		if (lowerCaseURL.startsWith("mailto:")) {
			return url.length() > 7;
		}

		if (lowerCaseURL.startsWith("tel:")) {
			return _telPattern.matcher(
				url.substring(4)
			).matches();
		}

		if (url.startsWith("#")) {
			return _anchorPattern.matcher(
				url.substring(1)
			).matches();
		}

		return url.startsWith("/") && !url.startsWith("//");
	}

	public static String toHtml(String markup) {
		if ((markup == null) || markup.trim(
			).isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(markup.length() * 2);

		List<String> paragraphLines = new ArrayList<>();
		List<String> listItems = new ArrayList<>();

		String listTag = null;

		String normalized = markup.replace(
			"\r\n", "\n"
		).replace(
			'\r', '\n'
		);

		for (String line : normalized.split("\n", -1)) {
			String trimmed = line.trim();

			if (trimmed.isEmpty()) {
				_flushParagraph(sb, paragraphLines);
				listTag = _flushList(sb, listTag, listItems);

				continue;
			}

			Matcher bulletMatcher = _bulletPattern.matcher(trimmed);
			Matcher numberMatcher = _numberPattern.matcher(trimmed);

			if (trimmed.startsWith("### ") || trimmed.startsWith("## ")) {
				_flushParagraph(sb, paragraphLines);
				listTag = _flushList(sb, listTag, listItems);

				String tag = trimmed.startsWith("### ") ? "h4" : "h3";

				sb.append('<');
				sb.append(tag);
				sb.append('>');
				sb.append(
					_inline(
						trimmed.substring(
							trimmed.indexOf(' ') + 1
						).trim()));
				sb.append("</");
				sb.append(tag);
				sb.append('>');
			}
			else if (bulletMatcher.matches() || numberMatcher.matches()) {
				_flushParagraph(sb, paragraphLines);

				String tag = bulletMatcher.matches() ? "ul" : "ol";

				if (!tag.equals(listTag)) {
					listTag = _flushList(sb, listTag, listItems);
				}

				listTag = tag;

				listItems.add(
					bulletMatcher.matches() ? bulletMatcher.group(1) :
						numberMatcher.group(1));
			}
			else {
				listTag = _flushList(sb, listTag, listItems);

				paragraphLines.add(trimmed);
			}
		}

		_flushParagraph(sb, paragraphLines);
		_flushList(sb, listTag, listItems);

		return sb.toString();
	}

	private static String _emphasis(String escapedText) {
		String html = _boldPattern.matcher(
			escapedText
		).replaceAll(
			"<strong>$1</strong>"
		);

		return _italicPattern.matcher(
			html
		).replaceAll(
			"<em>$1</em>"
		);
	}

	private static String _flushList(
		StringBuilder sb, String listTag, List<String> listItems) {
		if ((listTag == null) || listItems.isEmpty()) {
			listItems.clear();

			return null;
		}

		sb.append('<');
		sb.append(listTag);
		sb.append('>');

		for (String listItem : listItems) {
			sb.append("<li>");
			sb.append(_inline(listItem));
			sb.append("</li>");
		}

		sb.append("</");
		sb.append(listTag);
		sb.append('>');

		listItems.clear();

		return null;
	}

	private static void _flushParagraph(
		StringBuilder sb, List<String> paragraphLines) {
		if (paragraphLines.isEmpty()) {
			return;
		}

		sb.append("<p>");

		for (int i = 0; i < paragraphLines.size(); i++) {
			if (i > 0) {
				sb.append("<br>");
			}

			sb.append(_inline(paragraphLines.get(i)));
		}

		sb.append("</p>");

		paragraphLines.clear();
	}

	private static String _inline(String text) {
		StringBuilder sb = new StringBuilder(text.length() + 32);

		Matcher matcher = _linkPattern.matcher(text);

		int start = 0;

		while (matcher.find()) {
			String before = text.substring(start, matcher.start());

			sb.append(_emphasis(escape(before)));

			String url = matcher.group(2);

			if (isAllowedURL(url)) {
				sb.append("<a href=\"");
				sb.append(escape(url));
				sb.append('"');

				if (url.toLowerCase(
						Locale.ROOT
					).startsWith(
						"https://"
					)) {
					sb.append(" rel=\"noopener noreferrer\"");
				}

				sb.append('>');
				sb.append(_emphasis(escape(matcher.group(1))));
				sb.append("</a>");
			}
			else {
				sb.append(_emphasis(escape(matcher.group())));
			}

			start = matcher.end();
		}

		sb.append(_emphasis(escape(text.substring(start))));

		return sb.toString();
	}

	private static final Pattern _anchorPattern = Pattern.compile(
		"^[a-z0-9]+(-[a-z0-9]+)*$");
	private static final Pattern _boldPattern = Pattern.compile(
		"\\*\\*([^*]+?)\\*\\*");
	private static final Pattern _bulletPattern = Pattern.compile(
		"^[-*]\\s+(.+)$");
	private static final Pattern _hostPattern = Pattern.compile(
		"^[A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?(\\.[A-Za-z0-9]([A-Za-z0-9-]*" +
			"[A-Za-z0-9])?)+(:\\d{1,5})?(/|\\?|#|$)");
	private static final Pattern _italicPattern = Pattern.compile(
		"\\*([^*\\s][^*]*?)\\*");
	private static final Pattern _linkPattern = Pattern.compile(
		"\\[([^\\]\\n]{1,300})\\]\\(([^)\\s]{1,2000})\\)");
	private static final Pattern _numberPattern = Pattern.compile(
		"^\\d{1,3}[.)]\\s+(.+)$");
	private static final Pattern _telPattern = Pattern.compile(
		"^\\+?[0-9()\\-.]{3,30}$");
}
