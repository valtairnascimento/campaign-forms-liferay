package io.github.valtairnascimento.campaignforms.domain;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PublicCampaignSnapshot implements Serializable {
	public PublicCampaignSnapshot(
		long campaignId, long companyId, long groupId, String slug,
		String name, String description, String mode, String status,
		String defaultLanguageId, Date startDate, Date endDate,
		int submissionLimit, String closedMessage, String limitReachedMessage,
		Form form, Page page, boolean preview) {
		_campaignId = campaignId;
		_companyId = companyId;
		_groupId = groupId;
		_slug = slug;
		_name = name;
		_description = description;
		_mode = mode;
		_status = status;
		_defaultLanguageId = defaultLanguageId;
		_startDate = startDate;
		_endDate = endDate;
		_submissionLimit = submissionLimit;
		_closedMessage = closedMessage;
		_limitReachedMessage = limitReachedMessage;
		_form = form;
		_page = page;
		_preview = preview;
	}

	public long getCampaignId() {
		return _campaignId;
	}

	public String getClosedMessage() {
		return _closedMessage;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public String getDescription() {
		return _description;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public Form getForm() {
		return _form;
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getLimitReachedMessage() {
		return _limitReachedMessage;
	}

	public String getMode() {
		return _mode;
	}

	public String getName() {
		return _name;
	}

	public Page getPage() {
		return _page;
	}

	public String getSlug() {
		return _slug;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public String getStatus() {
		return _status;
	}

	public int getSubmissionLimit() {
		return _submissionLimit;
	}

	public boolean isPreview() {
		return _preview;
	}

	public static class Block implements Serializable {
		public Block(
			String blockKey, String blockType, int schemaVersion,
			Map<String, Object> configuration) {
			_blockKey = blockKey;
			_blockType = blockType;
			_schemaVersion = schemaVersion;
			_configuration = Collections.unmodifiableMap(configuration);
		}

		public String getBlockKey() {
			return _blockKey;
		}

		public String getBlockType() {
			return _blockType;
		}

		public Map<String, Object> getConfiguration() {
			return _configuration;
		}

		public int getSchemaVersion() {
			return _schemaVersion;
		}

		private static final long serialVersionUID = 1L;

		private final String _blockKey;
		private final String _blockType;
		private final Map<String, Object> _configuration;
		private final int _schemaVersion;
	}

	public static class Field implements Serializable {
		public Field(
			String fieldKey, String type, String label, String placeholder,
			String helpText, boolean required, String defaultValue,
			String width, boolean multiValued, Map<String, Object> validation,
			Map<String, Object> properties, List<Option> options) {
			_fieldKey = fieldKey;
			_type = type;
			_label = label;
			_placeholder = placeholder;
			_helpText = helpText;
			_required = required;
			_defaultValue = defaultValue;
			_width = width;
			_multiValued = multiValued;
			_validation = Collections.unmodifiableMap(validation);
			_properties = Collections.unmodifiableMap(properties);
			_options = Collections.unmodifiableList(options);
		}

		public String getDefaultValue() {
			return _defaultValue;
		}

		public String getFieldKey() {
			return _fieldKey;
		}

		public String getHelpText() {
			return _helpText;
		}

		public String getLabel() {
			return _label;
		}

		public List<Option> getOptions() {
			return _options;
		}

		public String getPlaceholder() {
			return _placeholder;
		}

		public Map<String, Object> getProperties() {
			return _properties;
		}

		public String getType() {
			return _type;
		}

		public Map<String, Object> getValidation() {
			return _validation;
		}

		public String getWidth() {
			return _width;
		}

		public boolean isMultiValued() {
			return _multiValued;
		}

		public boolean isRequired() {
			return _required;
		}

		private static final long serialVersionUID = 1L;

		private final String _defaultValue;
		private final String _fieldKey;
		private final String _helpText;
		private final String _label;
		private final boolean _multiValued;
		private final List<Option> _options;
		private final String _placeholder;
		private final Map<String, Object> _properties;
		private final boolean _required;
		private final String _type;
		private final Map<String, Object> _validation;
		private final String _width;
	}

	public static class Form implements Serializable {
		public Form(
			long formId, long formVersionId, int version, String submitLabel,
			String successTitle, String successMessage, String redirectUrl,
			boolean captchaEnabled, boolean requireAuthentication,
			boolean consentRequired, String consentText, String consentURL,
			String consentVersion, List<Field> fields) {
			_formId = formId;
			_formVersionId = formVersionId;
			_version = version;
			_submitLabel = submitLabel;
			_successTitle = successTitle;
			_successMessage = successMessage;
			_redirectUrl = redirectUrl;
			_captchaEnabled = captchaEnabled;
			_requireAuthentication = requireAuthentication;
			_consentRequired = consentRequired;
			_consentText = consentText;
			_consentURL = consentURL;
			_consentVersion = consentVersion;
			_fields = Collections.unmodifiableList(fields);
		}

		public String getConsentText() {
			return _consentText;
		}

		public String getConsentURL() {
			return _consentURL;
		}

		public String getConsentVersion() {
			return _consentVersion;
		}

		public List<Field> getFields() {
			return _fields;
		}

		public long getFormId() {
			return _formId;
		}

		public long getFormVersionId() {
			return _formVersionId;
		}

		public String getRedirectUrl() {
			return _redirectUrl;
		}

		public String getSubmitLabel() {
			return _submitLabel;
		}

		public String getSuccessMessage() {
			return _successMessage;
		}

		public String getSuccessTitle() {
			return _successTitle;
		}

		public int getVersion() {
			return _version;
		}

		public boolean isCaptchaEnabled() {
			return _captchaEnabled;
		}

		public boolean isConsentRequired() {
			return _consentRequired;
		}

		public boolean isRequireAuthentication() {
			return _requireAuthentication;
		}

		private static final long serialVersionUID = 1L;

		private final boolean _captchaEnabled;
		private final boolean _consentRequired;
		private final String _consentText;
		private final String _consentURL;
		private final String _consentVersion;
		private final List<Field> _fields;
		private final long _formId;
		private final long _formVersionId;
		private final String _redirectUrl;
		private final boolean _requireAuthentication;
		private final String _submitLabel;
		private final String _successMessage;
		private final String _successTitle;
		private final int _version;
	}

	public static class Option implements Serializable {
		public Option(String label, String value) {
			_label = label;
			_value = value;
		}

		public String getLabel() {
			return _label;
		}

		public String getValue() {
			return _value;
		}

		private static final long serialVersionUID = 1L;

		private final String _label;
		private final String _value;
	}

	public static class Page implements Serializable {
		public Page(
			String templateKey, String themeKey, String cssClass,
			String seoTitle, String seoDescription, String canonicalUrl,
			boolean noIndex, String ogTitle, String ogDescription,
			long ogImageFileEntryId, List<Block> blocks) {
			_templateKey = templateKey;
			_themeKey = themeKey;
			_cssClass = cssClass;
			_seoTitle = seoTitle;
			_seoDescription = seoDescription;
			_canonicalUrl = canonicalUrl;
			_noIndex = noIndex;
			_ogTitle = ogTitle;
			_ogDescription = ogDescription;
			_ogImageFileEntryId = ogImageFileEntryId;
			_blocks = Collections.unmodifiableList(blocks);
		}

		public List<Block> getBlocks() {
			return _blocks;
		}

		public String getCanonicalUrl() {
			return _canonicalUrl;
		}

		public String getCssClass() {
			return _cssClass;
		}

		public String getOgDescription() {
			return _ogDescription;
		}

		public long getOgImageFileEntryId() {
			return _ogImageFileEntryId;
		}

		public String getOgTitle() {
			return _ogTitle;
		}

		public String getSeoDescription() {
			return _seoDescription;
		}

		public String getSeoTitle() {
			return _seoTitle;
		}

		public String getTemplateKey() {
			return _templateKey;
		}

		public String getThemeKey() {
			return _themeKey;
		}

		public boolean isNoIndex() {
			return _noIndex;
		}

		private static final long serialVersionUID = 1L;

		private final List<Block> _blocks;
		private final String _canonicalUrl;
		private final String _cssClass;
		private final boolean _noIndex;
		private final String _ogDescription;
		private final long _ogImageFileEntryId;
		private final String _ogTitle;
		private final String _seoDescription;
		private final String _seoTitle;
		private final String _templateKey;
		private final String _themeKey;
	}

	private static final long serialVersionUID = 1L;

	private final long _campaignId;
	private final String _closedMessage;
	private final long _companyId;
	private final String _defaultLanguageId;
	private final String _description;
	private final Date _endDate;
	private final Form _form;
	private final long _groupId;
	private final String _limitReachedMessage;
	private final String _mode;
	private final String _name;
	private final Page _page;
	private final boolean _preview;
	private final String _slug;
	private final Date _startDate;
	private final String _status;
	private final int _submissionLimit;
}
