package io.github.valtairnascimento.campaignforms.web.internal.display;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.page.PageThemes;
import io.github.valtairnascimento.campaignforms.web.internal.display.block.BlockRenderContext;
import io.github.valtairnascimento.campaignforms.web.internal.display.block.BlockRenderer;
import io.github.valtairnascimento.campaignforms.web.internal.display.block.BlockRendererRegistry;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignTemplateContextBuilder.class)
public class CampaignTemplateContextBuilder {
	public Map<String, Object> build(
		PublicCampaignSnapshot publicCampaignSnapshot, String namespace,
		String submissionURL, Locale locale,
		BlockRenderContext blockRenderContext) {
		Map<String, Object> context = new HashMap<>();

		Map<String, String> messages = _getMessages(locale);

		context.put("messages", messages);

		if (publicCampaignSnapshot == null) {
			context.put("notFound", true);

			return context;
		}

		context.put("notFound", false);

		Map<String, Object> campaign = new HashMap<>();

		campaign.put(
			"campaignId",
			String.valueOf(publicCampaignSnapshot.getCampaignId()));
		campaign.put(
			"custom",
			CampaignMode.CUSTOM_PAGE.matches(publicCampaignSnapshot.getMode()));
		campaign.put(
			"description",
			GetterUtil.getString(publicCampaignSnapshot.getDescription()));
		campaign.put("name", publicCampaignSnapshot.getName());
		campaign.put("slug", publicCampaignSnapshot.getSlug());

		context.put("campaign", campaign);

		boolean preview = publicCampaignSnapshot.isPreview();

		context.put("preview", preview);

		PublicCampaignSnapshot.Page page = publicCampaignSnapshot.getPage();

		context.put(
			"themeKey",
			PageThemes.isValid(page.getThemeKey()) ?
				GetterUtil.getString(page.getThemeKey(), PageThemes.DEFAULT) :
					PageThemes.DEFAULT);

		String closedMessage = preview ? null :
			_publicCampaignService.getClosedMessage(
				publicCampaignSnapshot, locale);

		context.put("closedMessage", GetterUtil.getString(closedMessage));

		if (closedMessage == null) {
			context.put(
				"form",
				_buildForm(
					publicCampaignSnapshot, namespace, submissionURL,
					messages, locale));
		}

		List<Map<String, Object>> blocks = _buildBlocks(
			publicCampaignSnapshot, blockRenderContext);

		context.put("blocks", blocks);

		context.put(
			"pageHeading",
			blocks.isEmpty() || !"HERO".equals(blocks.get(0).get("type")));

		return context;
	}

	private List<Map<String, Object>> _buildBlocks(
		PublicCampaignSnapshot publicCampaignSnapshot,
		BlockRenderContext blockRenderContext) {
		List<Map<String, Object>> blocks = new ArrayList<>();

		for (PublicCampaignSnapshot.Block block :
				publicCampaignSnapshot.getPage(
				).getBlocks()) {
			BlockRenderer blockRenderer =
				_blockRendererRegistry.getBlockRenderer(block.getBlockType());

			if (blockRenderer == null) {
				continue;
			}

			Map<String, Object> viewModel = blockRenderer.getViewModel(
				block, blockRenderContext);

			if (viewModel == null) {
				continue;
			}

			Map<String, Object> blockMap = new HashMap<>(viewModel);

			blockMap.put("blockKey", block.getBlockKey());
			blockMap.put(
				"form",
				CampaignConstants.BLOCK_TYPE_FORM.equals(block.getBlockType()));
			blockMap.put("type", block.getBlockType());

			blocks.add(blockMap);
		}

		return blocks;
	}

	private Map<String, Object> _buildField(
		PublicCampaignSnapshot.Field field, String formId) {
		Map<String, Object> fieldMap = new HashMap<>();

		String inputId = formId + "_" + field.getFieldKey();

		String[] control = _CONTROLS.getOrDefault(
			field.getType(), _DEFAULT_CONTROL);

		fieldMap.put("control", control[0]);
		fieldMap.put("defaultValue", GetterUtil.getString(field.getDefaultValue()));
		fieldMap.put("errorId", inputId + "_error");
		fieldMap.put("fieldKey", field.getFieldKey());
		fieldMap.put("helpId", inputId + "_help");
		fieldMap.put("helpText", GetterUtil.getString(field.getHelpText()));
		fieldMap.put("inputId", inputId);
		fieldMap.put("inputType", control[1]);
		fieldMap.put("label", field.getLabel());
		fieldMap.put("placeholder", GetterUtil.getString(field.getPlaceholder()));
		fieldMap.put("required", field.isRequired());
		fieldMap.put("type", field.getType());
		fieldMap.put(
			"widthClass", "campaign-forms-form__field--" + _getWidth(field));

		Map<String, Object> properties = field.getProperties();

		fieldMap.put(
			"autocomplete",
			GetterUtil.getString(properties.get("autocomplete")));

		Map<String, Object> validation = field.getValidation();

		fieldMap.put("maxLength", _toString(validation.get("maxLength")));
		fieldMap.put("minLength", _toString(validation.get("minLength")));
		fieldMap.put("max", _toString(validation.get("max")));
		fieldMap.put("min", _toString(validation.get("min")));
		fieldMap.put(
			"step",
			GetterUtil.getBoolean(validation.get("integer")) ? "1" : "any");

		List<Map<String, Object>> options = new ArrayList<>();

		int index = 0;

		for (PublicCampaignSnapshot.Option option : field.getOptions()) {
			Map<String, Object> optionMap = new HashMap<>();

			optionMap.put("id", inputId + "_" + index++);
			optionMap.put("label", option.getLabel());
			optionMap.put("value", option.getValue());

			options.add(optionMap);
		}

		fieldMap.put("options", options);

		return fieldMap;
	}

	private Map<String, Object> _buildForm(
		PublicCampaignSnapshot publicCampaignSnapshot, String namespace,
		String submissionURL, Map<String, String> messages, Locale locale) {
		PublicCampaignSnapshot.Form form = publicCampaignSnapshot.getForm();

		String formId = namespace + "campaignForm";

		Map<String, Object> formMap = new HashMap<>();

		List<Map<String, Object>> fields = new ArrayList<>();

		for (PublicCampaignSnapshot.Field field : form.getFields()) {
			fields.add(_buildField(field, formId));
		}

		formMap.put("consentId", formId + "_consent");
		formMap.put("consentRequired", form.isConsentRequired());
		formMap.put("consentText", GetterUtil.getString(form.getConsentText()));

		formMap.put("consentURL", GetterUtil.getString(form.getConsentURL()));
		formMap.put("fields", fields);
		formMap.put(
			"honeypotName",
			_formTokenManager.getHoneypotFieldName(
				publicCampaignSnapshot.getCompanyId(),
				form.getFormVersionId()));
		formMap.put("id", formId);

		String submitLabel = form.getSubmitLabel();

		if (Validator.isNull(submitLabel)) {
			submitLabel = messages.get("submit");
		}

		formMap.put("submitLabel", submitLabel);

		formMap.put(
			"config",
			_buildFormConfig(
				publicCampaignSnapshot, submissionURL, locale
			).toString());

		return formMap;
	}

	private JSONObject _buildFormConfig(
		PublicCampaignSnapshot publicCampaignSnapshot, String submissionURL,
		Locale locale) {
		PublicCampaignSnapshot.Form form = publicCampaignSnapshot.getForm();

		JSONArray fieldsJSONArray = _jsonFactory.createJSONArray();

		for (PublicCampaignSnapshot.Field field : form.getFields()) {
			JSONArray optionsJSONArray = _jsonFactory.createJSONArray();

			for (PublicCampaignSnapshot.Option option : field.getOptions()) {
				optionsJSONArray.put(option.getValue());
			}

			fieldsJSONArray.put(
				_jsonFactory.createJSONObject(
				).put(
					"fieldKey", field.getFieldKey()
				).put(
					"multiValued", field.isMultiValued()
				).put(
					"options", optionsJSONArray
				).put(
					"required", field.isRequired()
				).put(
					"type", field.getType()
				).put(
					"validation",
					_jsonFactory.createJSONObject(field.getValidation())
				));
		}

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, CampaignConstants.class);

		JSONObject errorsJSONObject = _jsonFactory.createJSONObject();

		for (Map.Entry<String, String> entry : _getErrorMessageKeys(
				).entrySet()) {
			errorsJSONObject.put(
				entry.getKey(),
				LanguageUtil.get(resourceBundle, entry.getValue()));
		}

		Map<String, String> messages = _getMessages(locale);

		return _jsonFactory.createJSONObject(
		).put(
			"campaignId", publicCampaignSnapshot.getCampaignId()
		).put(
			"campaignSlug", publicCampaignSnapshot.getSlug()
		).put(
			"consentVersion", GetterUtil.getString(form.getConsentVersion())
		).put(
			"fields", fieldsJSONArray
		).put(
			"formId", form.getFormId()
		).put(
			"formToken",
			_formTokenManager.createFormToken(
				publicCampaignSnapshot.getCompanyId(), form.getFormVersionId())
		).put(
			"formVersion", form.getVersion()
		).put(
			"messages",
			_jsonFactory.createJSONObject(
			).put(
				"errors", errorsJSONObject
			).put(
				"genericError", messages.get("generic-error")
			).put(
				"invalidFormToken", messages.get("invalid-form-token")
			).put(
				"sending", messages.get("sending")
			).put(
				"success", messages.get("success")
			).put(
				"summary", messages.get("error-summary")
			)
		).put(
			"preview", publicCampaignSnapshot.isPreview()
		).put(
			"redirectUrl", GetterUtil.getString(form.getRedirectUrl())
		).put(
			"submissionUrl", submissionURL
		).put(
			"successMessage", GetterUtil.getString(form.getSuccessMessage())
		).put(
			"successTitle", GetterUtil.getString(form.getSuccessTitle())
		);
	}

	private Map<String, String> _getErrorMessageKeys() {
		Map<String, String> errorMessageKeys = new LinkedHashMap<>();

		for (Field field : FieldError.class.getFields()) {
			if (field.getType() != String.class) {
				continue;
			}

			try {
				String code = (String)field.get(null);

				errorMessageKeys.put(
					code,
					"field-error-" +
						StringUtil.replace(
							StringUtil.toLowerCase(code), '_', '-'));
			}
			catch (IllegalAccessException illegalAccessException) {
				throw new IllegalStateException(illegalAccessException);
			}
		}

		errorMessageKeys.put("DATE_TOO_EARLY", "field-error-date-too-early");
		errorMessageKeys.put("DATE_TOO_LATE", "field-error-date-too-late");
		errorMessageKeys.put("INVALID_INTEGER", "field-error-invalid-integer");

		return errorMessageKeys;
	}

	private Map<String, String> _getMessages(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		Map<String, String> messages = new HashMap<>();

		for (String key : _MESSAGE_KEYS) {
			messages.put(key, LanguageUtil.get(resourceBundle, key));
		}

		return messages;
	}

	private String _getWidth(PublicCampaignSnapshot.Field field) {
		String width = field.getWidth();

		if (Validator.isNull(width)) {
			return "full";
		}

		return StringUtil.toLowerCase(width);
	}

	private String _toString(Object value) {
		if (value == null) {
			return "";
		}

		return String.valueOf(value);
	}

	private static final Map<String, String[]> _CONTROLS;

	private static final String[] _DEFAULT_CONTROL = {"input", "text"};

	private static final String[] _MESSAGE_KEYS = {
		"campaign-not-found", "consent", "consent-privacy-link",
		"error-summary", "generic-error", "honeypot-label",
		"invalid-form-token", "noscript", "opens-in-new-tab", "preview-banner",
		"preview-submit", "required", "select-an-option", "sending", "submit",
		"success"
	};

	static {
		Map<String, String[]> controls = new HashMap<>();

		controls.put("boolean", new String[] {"boolean", ""});
		controls.put("checkbox", new String[] {"checkbox", ""});
		controls.put("date", new String[] {"input", "date"});
		controls.put("datetime", new String[] {"input", "datetime-local"});
		controls.put("email", new String[] {"input", "email"});
		controls.put("hidden", new String[] {"input", "hidden"});
		controls.put("multiSelect", new String[] {"multiSelect", ""});
		controls.put("number", new String[] {"input", "number"});
		controls.put("radio", new String[] {"radio", ""});
		controls.put("select", new String[] {"select", ""});
		controls.put("tel", new String[] {"input", "tel"});
		controls.put("text", new String[] {"input", "text"});
		controls.put("textarea", new String[] {"textarea", ""});

		_CONTROLS = Collections.unmodifiableMap(controls);
	}

	@Reference
	private BlockRendererRegistry _blockRendererRegistry;

	@Reference
	private FormTokenManager _formTokenManager;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private PublicCampaignService _publicCampaignService;
}
