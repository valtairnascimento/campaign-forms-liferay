package io.github.valtairnascimento.campaignforms.internal.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderValidator {
	public PlaceholderValidator(
		Set<String> fieldKeys, Set<String> campaignAttributeKeys) {
		_fieldKeys = Collections.unmodifiableSet(fieldKeys);
		_campaignAttributeKeys = Collections.unmodifiableSet(
			campaignAttributeKeys);
	}

	public static List<String> findPlaceholders(String text) {
		if (text == null) {
			return Collections.emptyList();
		}

		Set<String> placeholders = new LinkedHashSet<>();

		Matcher matcher = _placeholderPattern.matcher(text);

		while (matcher.find()) {
			placeholders.add(matcher.group());
		}

		return new ArrayList<>(placeholders);
	}

	public static String getSubmissionFieldKey(String placeholder) {
		String expression = _getExpression(placeholder);

		if ((expression == null) || !expression.startsWith(_SUBMISSION)) {
			return null;
		}

		String key = expression.substring(_SUBMISSION.length());

		if (_SUBMISSION_KEYS.contains(key)) {
			return null;
		}

		return key;
	}

	public List<String> getUnknownPlaceholders(String text) {
		List<String> unknownPlaceholders = new ArrayList<>();

		for (String placeholder : findPlaceholders(text)) {
			if (!isKnown(placeholder)) {
				unknownPlaceholders.add(placeholder);
			}
		}

		return unknownPlaceholders;
	}

	public boolean isKnown(String placeholder) {
		String expression = _getExpression(placeholder);

		if (expression == null) {
			return false;
		}

		if (expression.startsWith(_CAMPAIGN)) {
			String key = expression.substring(_CAMPAIGN.length());

			if (_CAMPAIGN_KEYS.contains(key) ||
				_campaignAttributeKeys.contains(key)) {
				return true;
			}

			return key.startsWith(_ATTRIBUTES) &&
				   _campaignAttributeKeys.contains(
					   key.substring(_ATTRIBUTES.length()));
		}

		if (expression.startsWith(_SUBMISSION)) {
			String key = expression.substring(_SUBMISSION.length());

			return _SUBMISSION_KEYS.contains(key) || _fieldKeys.contains(key);
		}

		if (expression.startsWith(_FORM)) {
			return _FORM_KEYS.contains(expression.substring(_FORM.length()));
		}

		if (expression.startsWith(_SITE)) {
			return _SITE_KEYS.contains(expression.substring(_SITE.length()));
		}

		return false;
	}

	private static String _getExpression(String placeholder) {
		if ((placeholder == null) || !placeholder.startsWith("${") ||
			!placeholder.endsWith("}")) {
			return null;
		}

		String expression = placeholder.substring(2, placeholder.length() - 1);

		if (!_expressionPattern.matcher(
				expression
			).matches()) {
			return null;
		}

		return expression;
	}

	private static final String _ATTRIBUTES = "attributes.";

	private static final String _CAMPAIGN = "campaign.";

	private static final Set<String> _CAMPAIGN_KEYS = Set.of(
		"description", "endDate", "name", "slug", "startDate");

	private static final String _FORM = "form.";

	private static final Set<String> _FORM_KEYS = Set.of("name", "version");

	private static final String _SITE = "site.";

	private static final Set<String> _SITE_KEYS = Set.of("name", "url");

	private static final String _SUBMISSION = "submission.";

	private static final Set<String> _SUBMISSION_KEYS = Set.of(
		"date", "id", "table");

	private static final Pattern _expressionPattern = Pattern.compile(
		"^[a-z]+\\.[A-Za-z0-9_]+(\\.[A-Za-z0-9_]+)?$");
	private static final Pattern _placeholderPattern = Pattern.compile(
		"\\$\\{[^}]{0,100}\\}");

	private final Set<String> _campaignAttributeKeys;
	private final Set<String> _fieldKeys;
}
