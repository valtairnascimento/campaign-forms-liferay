package io.github.valtairnascimento.campaignforms.internal.notification;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.internal.util.CampaignLanguageUtil;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormFieldOption;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.persistence.CampaignPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldOptionPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormFieldPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.FormVersionPersistence;
import io.github.valtairnascimento.campaignforms.service.persistence.SubmissionValuePersistence;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = NotificationMailer.class)
public class NotificationMailer {
	public MailMessage buildMessage(
			NotificationConfig notificationConfig, Submission submission)
		throws NotificationException {
		Campaign campaign = _campaignPersistence.fetchByPrimaryKey(
			submission.getCampaignId());

		if (campaign == null) {
			throw new NotificationException("campaign-not-found", true);
		}

		Locale locale = _getLocale(campaign);
		TimeZone timeZone = _getTimeZone(campaign);

		NotificationData notificationData = _createNotificationData(
			campaign, submission.getFormVersionId(), locale, timeZone);

		notificationData.put(
			"submission.date",
			_formatDateTime(submission.getCreateDate(), locale, timeZone)
		).put(
			"submission.id", String.valueOf(submission.getSubmissionId())
		);

		_addSubmissionValues(
			notificationData, submission, locale, timeZone);

		return _toMailMessage(
			notificationConfig, notificationData,
			NotificationTemplateRenderer.resolveAddresses(
				notificationConfig.getRecipients(), notificationData),
			campaign.getCompanyId());
	}

	public MailMessage buildTestMessage(
			NotificationConfig notificationConfig, User user)
		throws NotificationException {
		Campaign campaign = _campaignPersistence.fetchByPrimaryKey(
			notificationConfig.getCampaignId());

		if (campaign == null) {
			throw new NotificationException("campaign-not-found", true);
		}

		Locale locale = _getLocale(campaign);
		TimeZone timeZone = _getTimeZone(campaign);

		Form form = _formPersistence.fetchByCampaignId_First(
			campaign.getCampaignId(), null);

		long formVersionId = 0;

		if (form != null) {
			formVersionId = (form.getDraftVersionId() > 0) ?
				form.getDraftVersionId() : form.getPublishedVersionId();
		}

		NotificationData notificationData = _createNotificationData(
			campaign, formVersionId, locale, timeZone);

		notificationData.put(
			"submission.date", _formatDateTime(new Date(), locale, timeZone)
		).put(
			"submission.id", "0"
		);

		for (FormField formField :
				_formFieldPersistence.findByFormVersionId(formVersionId)) {
			String sample = _getSampleValue(formField, user, locale);

			notificationData.addFieldValue(formField.getFieldKey(), sample);
			notificationData.put("submission." + formField.getFieldKey(), sample);
			notificationData.addTableRow(formField.getLabel(), sample);
		}

		List<String> recipients = new ArrayList<>();

		recipients.add(user.getEmailAddress());

		MailMessage mailMessage = _toMailMessage(
			notificationConfig, notificationData, recipients,
			campaign.getCompanyId());

		mailMessage.setSubject(
			CampaignLanguageUtil.get(locale, "notification-test-subject-prefix") +
				" " + mailMessage.getSubject());

		mailMessage.setCC(new InternetAddress[0]);
		mailMessage.setBCC(new InternetAddress[0]);

		return mailMessage;
	}

	public void send(MailMessage mailMessage) {
		_mailService.sendEmail(mailMessage);
	}

	private void _addSubmissionValues(
		NotificationData notificationData, Submission submission,
		Locale locale, TimeZone timeZone) {
		Map<String, List<String>> displayValues = new LinkedHashMap<>();

		Map<Long, Map<String, String>> optionLabels = _getOptionLabels(
			submission.getFormVersionId());

		for (SubmissionValue submissionValue :
				_submissionValuePersistence.findBySubmissionId(
					submission.getSubmissionId())) {
			String rawValue = _getRawValue(submissionValue, timeZone);

			if (rawValue == null) {
				continue;
			}

			notificationData.addFieldValue(
				submissionValue.getFieldKey(), rawValue);

			String displayValue = _formatValue(
				submissionValue, optionLabels, locale, timeZone);

			displayValues.computeIfAbsent(
				submissionValue.getFieldKey(), key -> new ArrayList<>()
			).add(
				displayValue
			);
		}

		Map<String, String> labels = new LinkedHashMap<>();

		for (FormField formField :
				_formFieldPersistence.findByFormVersionId(
					submission.getFormVersionId())) {
			labels.put(formField.getFieldKey(), formField.getLabel());
		}

		for (Map.Entry<String, String> entry : labels.entrySet()) {
			List<String> values = displayValues.get(entry.getKey());

			if ((values == null) || values.isEmpty()) {
				continue;
			}

			String value = String.join(", ", values);

			notificationData.put("submission." + entry.getKey(), value);
			notificationData.addTableRow(entry.getValue(), value);
		}
	}

	private NotificationData _createNotificationData(
		Campaign campaign, long formVersionId, Locale locale,
		TimeZone timeZone) {
		NotificationData notificationData = new NotificationData();

		notificationData.put(
			"campaign.description", campaign.getDescription()
		).put(
			"campaign.endDate",
			_formatDateTime(campaign.getEndDate(), locale, timeZone)
		).put(
			"campaign.name", campaign.getName()
		).put(
			"campaign.slug", campaign.getSlug()
		).put(
			"campaign.startDate",
			_formatDateTime(campaign.getStartDate(), locale, timeZone)
		);

		JSONObject settingsJSONObject = _toJSONObject(campaign.getSettings());

		JSONObject attributesJSONObject = settingsJSONObject.getJSONObject(
			"attributes");

		if (attributesJSONObject != null) {
			Iterator<String> keys = attributesJSONObject.keys();

			while (keys.hasNext()) {
				String key = keys.next();

				notificationData.put(
					"campaign." + key, attributesJSONObject.getString(key));
			}
		}

		FormVersion formVersion = _formVersionPersistence.fetchByPrimaryKey(
			formVersionId);

		if (formVersion != null) {
			notificationData.put(
				"form.version", String.valueOf(formVersion.getVersion()));
		}

		notificationData.put("form.name", campaign.getName());

		Group group = _groupLocalService.fetchGroup(campaign.getGroupId());

		if (group != null) {
			notificationData.put(
				"site.name", group.getName(locale)
			).put(
				"site.url", _getSiteURL(campaign, group)
			);
		}

		return notificationData;
	}

	private String _formatDateTime(
		Date date, Locale locale, TimeZone timeZone) {
		if (date == null) {
			return "";
		}

		DateFormat dateFormat = DateFormat.getDateTimeInstance(
			DateFormat.SHORT, DateFormat.SHORT, locale);

		dateFormat.setTimeZone(timeZone);

		return dateFormat.format(date);
	}

	private String _formatValue(
		SubmissionValue submissionValue,
		Map<Long, Map<String, String>> optionLabels, Locale locale,
		TimeZone timeZone) {
		String valueType = submissionValue.getValueType();

		if (FieldValueType.BOOLEAN.name(
			).equals(
				valueType
			)) {
			return CampaignLanguageUtil.get(
				locale,
				submissionValue.isBooleanValue() ? "notification-yes" :
					"notification-no");
		}

		if (FieldValueType.NUMBER.name(
			).equals(
				valueType
			) && (submissionValue.getNumberValue() != null)) {
			NumberFormat numberFormat = NumberFormat.getInstance(locale);

			numberFormat.setMaximumFractionDigits(10);

			return numberFormat.format(submissionValue.getNumberValue());
		}

		if (FieldValueType.DATE.name(
			).equals(
				valueType
			) && (submissionValue.getDateValue() != null)) {
			DateFormat dateFormat = DateFormat.getDateInstance(
				DateFormat.SHORT, locale);

			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			return dateFormat.format(submissionValue.getDateValue());
		}

		if (FieldValueType.DATETIME.name(
			).equals(
				valueType
			)) {
			return _formatDateTime(
				submissionValue.getDateValue(), locale, timeZone);
		}

		String value = _getRawValue(submissionValue, timeZone);

		Map<String, String> labels = optionLabels.get(
			submissionValue.getFormFieldId());

		if ((labels != null) && labels.containsKey(value)) {
			return labels.get(value);
		}

		return value;
	}

	private Locale _getLocale(Campaign campaign) {
		if (Validator.isNull(campaign.getDefaultLanguageId())) {
			return LocaleUtil.getSiteDefault();
		}

		return LocaleUtil.fromLanguageId(campaign.getDefaultLanguageId());
	}

	private Map<Long, Map<String, String>> _getOptionLabels(
		long formVersionId) {
		Map<Long, Map<String, String>> optionLabels = new HashMap<>();

		for (FormField formField :
				_formFieldPersistence.findByFormVersionId(formVersionId)) {
			List<FormFieldOption> formFieldOptions =
				_formFieldOptionPersistence.findByFormFieldId(
					formField.getFormFieldId());

			if (formFieldOptions.isEmpty()) {
				continue;
			}

			Map<String, String> labels = new HashMap<>();

			for (FormFieldOption formFieldOption : formFieldOptions) {
				labels.put(formFieldOption.getValue(), formFieldOption.getLabel());
			}

			optionLabels.put(formField.getFormFieldId(), labels);
		}

		return optionLabels;
	}

	private String _getRawValue(
		SubmissionValue submissionValue, TimeZone timeZone) {
		String valueType = submissionValue.getValueType();

		if (FieldValueType.TEXT.name(
			).equals(
				valueType
			)) {
			return submissionValue.getTextValue();
		}

		if (FieldValueType.NUMBER.name(
			).equals(
				valueType
			)) {
			BigDecimal numberValue = submissionValue.getNumberValue();

			return (numberValue == null) ? null : numberValue.toPlainString();
		}

		if (FieldValueType.BOOLEAN.name(
			).equals(
				valueType
			)) {
			return String.valueOf(submissionValue.isBooleanValue());
		}

		if (FieldValueType.DATE.name(
			).equals(
				valueType
			) ||
			FieldValueType.DATETIME.name(
			).equals(
				valueType
			)) {
			Date dateValue = submissionValue.getDateValue();

			return (dateValue == null) ? null : dateValue.toInstant(
			).toString();
		}

		return submissionValue.getStringValue();
	}

	private String _getSampleValue(
		FormField formField, User user, Locale locale) {
		String type = formField.getType();

		if ("email".equals(type)) {
			return user.getEmailAddress();
		}

		if ("number".equals(type)) {
			return "10";
		}

		if ("boolean".equals(type)) {
			return CampaignLanguageUtil.get(locale, "notification-yes");
		}

		if ("date".equals(type) || "datetime".equals(type)) {
			return DateFormat.getDateInstance(
				DateFormat.SHORT, locale
			).format(
				new Date()
			);
		}

		List<FormFieldOption> formFieldOptions =
			_formFieldOptionPersistence.findByFormFieldId(
				formField.getFormFieldId());

		if (!formFieldOptions.isEmpty()) {
			return formFieldOptions.get(
				0
			).getLabel();
		}

		return CampaignLanguageUtil.get(locale, "notification-sample-answer");
	}

	private String _getSiteURL(Campaign campaign, Group group) {
		Company company = _companyLocalService.fetchCompany(
			campaign.getCompanyId());

		if (company == null) {
			return "";
		}

		return "https://" + company.getVirtualHostname() + "/web" +
			group.getFriendlyURL();
	}

	private TimeZone _getTimeZone(Campaign campaign) {
		Company company = _companyLocalService.fetchCompany(
			campaign.getCompanyId());

		if (company == null) {
			return TimeZone.getDefault();
		}

		try {
			return company.getTimeZone();
		}
		catch (Exception exception) {
			return TimeZone.getDefault();
		}
	}

	private boolean _isBlacklisted(String address) {
		for (String blacklistedAddress :
				PropsUtil.getArray(PropsKeys.MAIL_SEND_BLACKLIST)) {
			if (address.equalsIgnoreCase(blacklistedAddress.trim())) {
				return true;
			}
		}

		return false;
	}

	private JSONObject _toJSONObject(String json) {
		try {
			if (Validator.isNotNull(json)) {
				return _jsonFactory.createJSONObject(json);
			}
		}
		catch (Exception exception) {
		}

		return _jsonFactory.createJSONObject();
	}

	private InternetAddress[] _toInternetAddresses(List<String> addresses)
		throws NotificationException {
		InternetAddress[] internetAddresses =
			new InternetAddress[addresses.size()];

		try {
			for (int i = 0; i < addresses.size(); i++) {
				internetAddresses[i] = new InternetAddress(
					addresses.get(i), true);
			}
		}
		catch (Exception exception) {
			throw new NotificationException(
				"notification-address-is-invalid", true);
		}

		return internetAddresses;
	}

	private MailMessage _toMailMessage(
			NotificationConfig notificationConfig,
			NotificationData notificationData, List<String> recipients,
			long companyId)
		throws NotificationException {
		if (recipients.isEmpty()) {
			throw new NotificationException(
				"notification-has-no-recipients", true);
		}

		String fromAddress = PrefsPropsUtil.getString(
			companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		if (!NotificationTemplateRenderer.isEmailAddress(fromAddress) ||
			_isBlacklisted(fromAddress)) {
			throw new NotificationException(
				"notification-sender-is-not-configured", false);
		}

		String fromName = NotificationTemplateRenderer.renderText(
			notificationConfig.getFromName(), notificationData);

		if (Validator.isNull(fromName)) {
			fromName = PrefsPropsUtil.getString(
				companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		}

		MailMessage mailMessage = new MailMessage();

		try {
			mailMessage.setFrom(new InternetAddress(fromAddress, fromName));
		}
		catch (Exception exception) {
			throw new NotificationException(
				"notification-sender-is-not-configured", false);
		}

		mailMessage.setTo(_toInternetAddresses(recipients));
		mailMessage.setCC(
			_toInternetAddresses(
				NotificationTemplateRenderer.resolveAddresses(
					notificationConfig.getCc(), notificationData)));
		mailMessage.setBCC(
			_toInternetAddresses(
				NotificationTemplateRenderer.resolveAddresses(
					notificationConfig.getBcc(), notificationData)));

		List<String> replyTo = NotificationTemplateRenderer.resolveAddresses(
			notificationConfig.getReplyTo(), notificationData);

		if (!replyTo.isEmpty()) {
			mailMessage.setReplyTo(
				_toInternetAddresses(replyTo.subList(0, 1)));
		}

		mailMessage.setSubject(
			NotificationTemplateRenderer.renderText(
				notificationConfig.getSubjectTemplate(), notificationData));
		mailMessage.setBody(
			NotificationTemplateRenderer.renderHTML(
				notificationConfig.getBodyTemplate(), notificationData));
		mailMessage.setHTMLFormat(true);

		return mailMessage;
	}

	@Reference
	private CampaignPersistence _campaignPersistence;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private FormFieldOptionPersistence _formFieldOptionPersistence;

	@Reference
	private FormFieldPersistence _formFieldPersistence;

	@Reference
	private FormPersistence _formPersistence;

	@Reference
	private FormVersionPersistence _formVersionPersistence;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MailService _mailService;

	@Reference
	private SubmissionValuePersistence _submissionValuePersistence;
}
