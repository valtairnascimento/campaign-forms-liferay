package io.github.valtairnascimento.campaignforms.internal.domain;

import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.constants.CampaignMode;
import io.github.valtairnascimento.campaignforms.constants.FormVersionStatus;
import io.github.valtairnascimento.campaignforms.constants.PageStage;
import io.github.valtairnascimento.campaignforms.domain.PublicationIssue;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.internal.field.FieldDefinitionLoader;
import io.github.valtairnascimento.campaignforms.internal.field.type.EmailFieldType;
import io.github.valtairnascimento.campaignforms.internal.page.BlockContextUtil;
import io.github.valtairnascimento.campaignforms.internal.template.PlaceholderValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.PageBlock;
import io.github.valtairnascimento.campaignforms.model.PageConfiguration;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.BlockType;
import io.github.valtairnascimento.campaignforms.page.BlockTypeRegistry;
import io.github.valtairnascimento.campaignforms.service.FormLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.PageBlockLocalService;
import io.github.valtairnascimento.campaignforms.service.PageConfigurationLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PublicationValidator.class)
public class PublicationValidator {
	public List<PublicationIssue> validate(Campaign campaign)
		throws PortalException {
		List<PublicationIssue> publicationIssues = new ArrayList<>();

		if ((campaign.getEndDate() != null) &&
			!campaign.getEndDate(
			).after(
				new Date()
			)) {
			publicationIssues.add(
				new PublicationIssue("campaign-end-date-is-in-the-past", null));
		}

		Form form = _formLocalService.fetchCampaignForm(
			campaign.getCampaignId());

		if (form == null) {
			publicationIssues.add(
				new PublicationIssue("campaign-has-no-form", null));

			return publicationIssues;
		}

		long formVersionId = form.getDraftVersionId();

		if (formVersionId <= 0) {
			formVersionId = form.getPublishedVersionId();
		}

		List<FieldDefinition> fieldDefinitions =
			_fieldDefinitionLoader.getFieldDefinitions(formVersionId);

		_validateFields(
			form, formVersionId, fieldDefinitions, publicationIssues);

		if (CampaignMode.CUSTOM_PAGE.matches(campaign.getMode())) {
			_validateCustomPage(campaign, publicationIssues);
		}

		_validateNotifications(campaign, fieldDefinitions, publicationIssues);

		return publicationIssues;
	}

	private Set<String> _getCampaignAttributeKeys(Campaign campaign) {
		try {
			JSONObject settingsJSONObject = _jsonFactory.createJSONObject(
				campaign.getSettings());

			JSONObject attributesJSONObject =
				settingsJSONObject.getJSONObject("attributes");

			if (attributesJSONObject != null) {
				return attributesJSONObject.keySet();
			}
		}
		catch (JSONException jsonException) {
		}

		return Collections.emptySet();
	}

	private Map<String, String> _getPublishedValueTypes(
		Form form, long formVersionId) {
		Map<String, String> valueTypes = new HashMap<>();

		for (FormVersion formVersion :
				_formVersionLocalService.getFormVersions(form.getFormId())) {
			if ((formVersion.getFormVersionId() == formVersionId) ||
				FormVersionStatus.DRAFT.matches(formVersion.getStatus())) {
				continue;
			}

			for (FieldDefinition fieldDefinition :
					_fieldDefinitionLoader.getFieldDefinitions(
						formVersion.getFormVersionId())) {
				FieldType fieldType = _fieldTypeRegistry.getFieldType(
					fieldDefinition.getType());

				String valueType = fieldDefinition.getType();

				if (fieldType != null) {
					valueType = fieldType.getValueType(
					).name();
				}

				valueTypes.putIfAbsent(fieldDefinition.getFieldKey(), valueType);
			}
		}

		return valueTypes;
	}

	private void _validateCustomPage(
		Campaign campaign, List<PublicationIssue> publicationIssues) {
		PageConfiguration pageConfiguration =
			_pageConfigurationLocalService.fetchPageConfiguration(
				campaign.getCampaignId(), PageStage.DRAFT);

		int formBlocks = 0;

		if (pageConfiguration != null) {
			BlockContext blockContext = BlockContextUtil.create(campaign);

			for (PageBlock pageBlock :
					_pageBlockLocalService.getPageBlocks(
						pageConfiguration.getPageConfigurationId())) {
				if (!pageBlock.isEnabled()) {
					continue;
				}

				if (CampaignConstants.BLOCK_TYPE_FORM.equals(
						pageBlock.getBlockType())) {
					formBlocks++;
				}

				_validateBlock(pageBlock, blockContext, publicationIssues);
			}
		}

		if (formBlocks != 1) {
			publicationIssues.add(
				new PublicationIssue(
					"custom-page-requires-one-form-block", null));
		}
	}

	private void _validateBlock(
		PageBlock pageBlock, BlockContext blockContext,
		List<PublicationIssue> publicationIssues) {
		BlockType blockType = _blockTypeRegistry.getBlockType(
			pageBlock.getBlockType());

		if (blockType == null) {
			publicationIssues.add(
				new PublicationIssue(
					"block-type-is-invalid", pageBlock.getBlockKey()));

			return;
		}

		try {
			blockType.sanitize(
				_jsonFactory.createJSONObject(pageBlock.getConfiguration()),
				blockContext);
		}
		catch (JSONException | PageBlockConfigurationException exception) {
			publicationIssues.add(
				new PublicationIssue(
					"block-needs-review", pageBlock.getBlockKey()));
		}
	}

	private void _validateFields(
		Form form, long formVersionId, List<FieldDefinition> fieldDefinitions,
		List<PublicationIssue> publicationIssues) {
		if (fieldDefinitions.isEmpty()) {
			publicationIssues.add(
				new PublicationIssue("form-has-no-fields", null));

			return;
		}

		if (fieldDefinitions.size() > CampaignConstants.MAX_FIELDS_PER_FORM) {
			publicationIssues.add(
				new PublicationIssue("form-has-too-many-fields", null));
		}

		Map<String, String> publishedValueTypes = _getPublishedValueTypes(
			form, formVersionId);

		for (FieldDefinition fieldDefinition : fieldDefinitions) {
			String fieldKey = fieldDefinition.getFieldKey();

			FieldType fieldType = _fieldTypeRegistry.getFieldType(
				fieldDefinition.getType());

			if (fieldType == null) {
				publicationIssues.add(
					new PublicationIssue(
						"field-type-is-not-available", fieldKey));

				continue;
			}

			try {
				fieldType.validateDefinition(fieldDefinition);
			}
			catch (FormFieldConfigurationException
						formFieldConfigurationException) {
				publicationIssues.add(
					new PublicationIssue(
						formFieldConfigurationException.getMessage(),
						fieldKey));
			}

			String publishedValueType = publishedValueTypes.get(fieldKey);

			if ((publishedValueType != null) &&
				!publishedValueType.equals(
					fieldType.getValueType(
					).name())) {
				publicationIssues.add(
					new PublicationIssue(
						"field-value-type-cannot-change", fieldKey));
			}
		}
	}

	private void _validateNotifications(
		Campaign campaign, List<FieldDefinition> fieldDefinitions,
		List<PublicationIssue> publicationIssues) {
		Map<String, String> fieldTypes = new HashMap<>();

		for (FieldDefinition fieldDefinition : fieldDefinitions) {
			fieldTypes.put(
				fieldDefinition.getFieldKey(), fieldDefinition.getType());
		}

		PlaceholderValidator placeholderValidator = new PlaceholderValidator(
			fieldTypes.keySet(), _getCampaignAttributeKeys(campaign));

		Set<PublicationIssue> notificationIssues = new LinkedHashSet<>();

		for (NotificationConfig notificationConfig :
				_notificationConfigLocalService.getCampaignNotificationConfigs(
					campaign.getCampaignId())) {
			if (!notificationConfig.isEnabled()) {
				continue;
			}

			for (String text :
					new String[] {
						notificationConfig.getRecipients(),
						notificationConfig.getReplyTo(),
						notificationConfig.getSubjectTemplate(),
						notificationConfig.getBodyTemplate()
					}) {
				for (String placeholder :
						placeholderValidator.getUnknownPlaceholders(text)) {
					notificationIssues.add(
						new PublicationIssue(
							"notification-placeholder-is-unknown",
							placeholder));
				}
			}

			for (String text :
					new String[] {
						notificationConfig.getRecipients(),
						notificationConfig.getReplyTo()
					}) {
				for (String placeholder :
						PlaceholderValidator.findPlaceholders(text)) {
					String fieldKey =
						PlaceholderValidator.getSubmissionFieldKey(placeholder);

					if ((fieldKey != null) && fieldTypes.containsKey(fieldKey) &&
						!EmailFieldType.KEY.equals(fieldTypes.get(fieldKey))) {
						notificationIssues.add(
							new PublicationIssue(
								"notification-recipient-field-must-be-email",
								placeholder));
					}
				}
			}
		}

		publicationIssues.addAll(notificationIssues);
	}

	@Reference
	private BlockTypeRegistry _blockTypeRegistry;

	@Reference
	private FieldDefinitionLoader _fieldDefinitionLoader;

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormLocalService _formLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Reference
	private PageBlockLocalService _pageBlockLocalService;

	@Reference
	private PageConfigurationLocalService _pageConfigurationLocalService;
}
