package io.github.valtairnascimento.campaignforms.headless.delivery.internal.dto;

import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.Block;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.Field;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.Option;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicCampaign;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicForm;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.PublicPage;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PublicCampaignDTOFactory.class)
public class PublicCampaignDTOFactory {
	public PublicCampaignSnapshot getPublishedSnapshot(
			long groupId, String slug)
		throws PortalException {
		return _publicCampaignService.getPublishedSnapshot(groupId, slug);
	}

	public PublicCampaign toPublicCampaign(
		PublicCampaignSnapshot publicCampaignSnapshot, Locale locale) {
		PublicCampaign publicCampaign = new PublicCampaign();

		String closedMessage = _publicCampaignService.getClosedMessage(
			publicCampaignSnapshot, locale);

		publicCampaign.setAcceptingSubmissions(closedMessage == null);
		publicCampaign.setCampaignId(publicCampaignSnapshot.getCampaignId());
		publicCampaign.setClosedMessage(closedMessage);
		publicCampaign.setDefaultLanguageId(
			publicCampaignSnapshot.getDefaultLanguageId());
		publicCampaign.setDescription(publicCampaignSnapshot.getDescription());
		publicCampaign.setForm(toPublicForm(publicCampaignSnapshot));
		publicCampaign.setMode(
			PublicCampaign.Mode.create(publicCampaignSnapshot.getMode()));
		publicCampaign.setName(publicCampaignSnapshot.getName());
		publicCampaign.setPage(toPublicPage(publicCampaignSnapshot));
		publicCampaign.setSlug(publicCampaignSnapshot.getSlug());

		return publicCampaign;
	}

	public PublicForm toPublicForm(
		PublicCampaignSnapshot publicCampaignSnapshot) {
		PublicCampaignSnapshot.Form form = publicCampaignSnapshot.getForm();

		PublicForm publicForm = new PublicForm();

		publicForm.setCaptchaEnabled(form.isCaptchaEnabled());
		publicForm.setConsentRequired(form.isConsentRequired());
		publicForm.setConsentText(form.getConsentText());
		publicForm.setConsentURL(form.getConsentURL());
		publicForm.setConsentVersion(form.getConsentVersion());
		publicForm.setFields(
			TransformUtil.transformToArray(
				form.getFields(), this::_toField, Field.class));
		publicForm.setFormId(form.getFormId());
		publicForm.setFormToken(
			_formTokenManager.createFormToken(
				publicCampaignSnapshot.getCompanyId(),
				form.getFormVersionId()));
		publicForm.setHoneypotFieldName(
			_formTokenManager.getHoneypotFieldName(
				publicCampaignSnapshot.getCompanyId(),
				form.getFormVersionId()));
		publicForm.setRedirectUrl(form.getRedirectUrl());
		publicForm.setRequireAuthentication(form.isRequireAuthentication());
		publicForm.setSubmitLabel(form.getSubmitLabel());
		publicForm.setSuccessMessage(form.getSuccessMessage());
		publicForm.setSuccessTitle(form.getSuccessTitle());
		publicForm.setVersion(form.getVersion());

		return publicForm;
	}

	public PublicPage toPublicPage(
		PublicCampaignSnapshot publicCampaignSnapshot) {
		PublicCampaignSnapshot.Page page = publicCampaignSnapshot.getPage();

		PublicPage publicPage = new PublicPage();

		publicPage.setBlocks(
			TransformUtil.transformToArray(
				page.getBlocks(),
				pageBlock -> {
					Block block = new Block();

					block.setBlockKey(pageBlock.getBlockKey());
					block.setBlockType(pageBlock.getBlockType());
					block.setConfiguration(pageBlock.getConfiguration());
					block.setSchemaVersion(pageBlock.getSchemaVersion());

					return block;
				},
				Block.class));
		publicPage.setCanonicalUrl(page.getCanonicalUrl());
		publicPage.setCssClass(page.getCssClass());
		publicPage.setNoIndex(page.isNoIndex());
		publicPage.setOgDescription(page.getOgDescription());
		publicPage.setOgTitle(page.getOgTitle());
		publicPage.setSeoDescription(page.getSeoDescription());
		publicPage.setSeoTitle(page.getSeoTitle());
		publicPage.setTemplateKey(page.getTemplateKey());
		publicPage.setThemeKey(page.getThemeKey());

		return publicPage;
	}

	private Field _toField(PublicCampaignSnapshot.Field snapshotField) {
		Field field = new Field();

		field.setDefaultValue(snapshotField.getDefaultValue());
		field.setFieldKey(snapshotField.getFieldKey());
		field.setHelpText(snapshotField.getHelpText());
		field.setLabel(snapshotField.getLabel());
		field.setMultiValued(snapshotField.isMultiValued());
		field.setOptions(
			TransformUtil.transformToArray(
				snapshotField.getOptions(),
				snapshotOption -> {
					Option option = new Option();

					option.setLabel(snapshotOption.getLabel());
					option.setValue(snapshotOption.getValue());

					return option;
				},
				Option.class));
		field.setPlaceholder(snapshotField.getPlaceholder());
		field.setProperties(snapshotField.getProperties());
		field.setRequired(snapshotField.isRequired());
		field.setType(snapshotField.getType());
		field.setValidation(snapshotField.getValidation());
		field.setWidth(Field.Width.create(snapshotField.getWidth()));

		return field;
	}

	@Reference
	private FormTokenManager _formTokenManager;

	@Reference
	private PublicCampaignService _publicCampaignService;
}
