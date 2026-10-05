package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.exception.NotificationConfigException;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.notification.NotificationException;
import io.github.valtairnascimento.campaignforms.internal.notification.NotificationMailer;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.service.base.NotificationConfigLocalServiceBaseImpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.NotificationConfig",
	service = AopService.class
)
public class NotificationConfigLocalServiceImpl
	extends NotificationConfigLocalServiceBaseImpl {
	@Override
	public NotificationConfig addNotificationConfig(
			long userId, long campaignId,
			NotificationConfigInput notificationConfigInput)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		User user = userLocalService.getUser(userId);

		NotificationConfig notificationConfig =
			notificationConfigPersistence.create(
				counterLocalService.increment());

		notificationConfig.setGroupId(campaign.getGroupId());
		notificationConfig.setCompanyId(campaign.getCompanyId());
		notificationConfig.setUserId(userId);
		notificationConfig.setUserName(user.getFullName());

		Date now = new Date();

		notificationConfig.setCreateDate(now);
		notificationConfig.setModifiedDate(now);

		notificationConfig.setCampaignId(campaignId);

		_setFields(notificationConfig, notificationConfigInput);

		return notificationConfigPersistence.update(notificationConfig);
	}

	@Override
	public void deleteCampaignNotificationConfigs(long campaignId) {
		notificationConfigPersistence.removeByCampaignId(campaignId);
	}

	@Override
	public List<NotificationConfig> getCampaignNotificationConfigs(
		long campaignId) {
		return notificationConfigPersistence.findByCampaignId(campaignId);
	}

	@Override
	public List<NotificationConfig> getEnabledNotificationConfigs(
		long campaignId, EventType eventType) {
		List<NotificationConfig> notificationConfigs = new ArrayList<>();

		for (NotificationConfig notificationConfig :
				notificationConfigPersistence.findByC_ET(
					campaignId, eventType.name())) {
			if (notificationConfig.isEnabled()) {
				notificationConfigs.add(notificationConfig);
			}
		}

		return notificationConfigs;
	}

	@Override
	public String sendTestNotification(long notificationConfigId, long userId)
		throws PortalException {
		NotificationConfig notificationConfig =
			notificationConfigPersistence.findByPrimaryKey(notificationConfigId);

		User user = userLocalService.getUser(userId);

		try {
			_notificationMailer.send(
				_notificationMailer.buildTestMessage(notificationConfig, user));
		}
		catch (NotificationException notificationException) {
			throw new NotificationConfigException(
				notificationException.getCode(), notificationException);
		}

		return user.getEmailAddress();
	}

	@Override
	public NotificationConfig updateNotificationConfig(
			long notificationConfigId,
			NotificationConfigInput notificationConfigInput)
		throws PortalException {
		NotificationConfig notificationConfig =
			notificationConfigPersistence.findByPrimaryKey(
				notificationConfigId);

		notificationConfig.setModifiedDate(new Date());

		_setFields(notificationConfig, notificationConfigInput);

		return notificationConfigPersistence.update(notificationConfig);
	}

	private String _normalizeAddresses(
			String addresses, int maxLength, Predicate<String> predicate,
			String messageKey)
		throws NotificationConfigException {
		if (CampaignValidator.isBlank(addresses)) {
			return StringPool.BLANK;
		}

		List<String> normalizedAddresses = new ArrayList<>();

		for (String address : _addressSeparatorPattern.split(addresses.trim())) {
			if (!predicate.test(address)) {
				throw new NotificationConfigException(messageKey);
			}

			normalizedAddresses.add(address);
		}

		String normalized = StringUtil.merge(
			normalizedAddresses, StringPool.COMMA_AND_SPACE);

		if (normalized.length() > maxLength) {
			throw new NotificationConfigException(
				"notification-recipients-are-too-long");
		}

		return normalized;
	}

	private void _setFields(
			NotificationConfig notificationConfig,
			NotificationConfigInput notificationConfigInput)
		throws PortalException {
		String name = notificationConfigInput.getName();

		if (CampaignValidator.isBlank(name)) {
			throw new NotificationConfigException(
				"notification-name-is-required");
		}

		if (name.length() > CampaignValidator.MAX_NAME_LENGTH) {
			throw new NotificationConfigException(
				"notification-name-is-too-long");
		}

		if ((notificationConfigInput.getEventType() == null) ||
			(notificationConfigInput.getChannel() == null)) {
			throw new NotificationConfigException(
				"notification-event-and-channel-are-required");
		}

		String recipients = _normalizeAddresses(
			notificationConfigInput.getRecipients(), _MAX_ADDRESSES_LENGTH,
			CampaignValidator::isEmailOrSubmissionPlaceholder,
			"notification-recipient-is-invalid");
		String cc = _normalizeAddresses(
			notificationConfigInput.getCc(), _MAX_ADDRESSES_LENGTH,
			Validator::isEmailAddress, "notification-cc-is-invalid");
		String bcc = _normalizeAddresses(
			notificationConfigInput.getBcc(), _MAX_ADDRESSES_LENGTH,
			Validator::isEmailAddress, "notification-bcc-is-invalid");

		String replyTo = notificationConfigInput.getReplyTo();

		if (Validator.isNotNull(replyTo) &&
			!CampaignValidator.isEmailOrSubmissionPlaceholder(replyTo)) {
			throw new NotificationConfigException(
				"notification-reply-to-is-invalid");
		}

		String subjectTemplate = notificationConfigInput.getSubjectTemplate();

		if ((subjectTemplate != null) &&
			((subjectTemplate.length() > 500) ||
			 (subjectTemplate.indexOf('\n') >= 0) ||
			 (subjectTemplate.indexOf('\r') >= 0))) {
			throw new NotificationConfigException(
				"notification-subject-is-invalid");
		}

		String fromName = notificationConfigInput.getFromName();

		if ((fromName != null) &&
			((fromName.length() > CampaignValidator.MAX_NAME_LENGTH) ||
			 (fromName.indexOf('\n') >= 0) || (fromName.indexOf('\r') >= 0))) {
			throw new NotificationConfigException(
				"notification-from-name-is-invalid");
		}

		if (notificationConfigInput.isEnabled() &&
			(CampaignValidator.isBlank(recipients) ||
			 CampaignValidator.isBlank(subjectTemplate) ||
			 CampaignValidator.isBlank(notificationConfigInput.getBodyTemplate()))) {
			throw new NotificationConfigException(
				"enabled-notification-requires-recipients-subject-and-body");
		}

		notificationConfig.setName(name);
		notificationConfig.setEventType(
			notificationConfigInput.getEventType(
			).name());
		notificationConfig.setChannel(
			notificationConfigInput.getChannel(
			).name());
		notificationConfig.setEnabled(notificationConfigInput.isEnabled());
		notificationConfig.setRecipients(recipients);
		notificationConfig.setCc(cc);
		notificationConfig.setBcc(bcc);
		notificationConfig.setFromName(fromName);
		notificationConfig.setReplyTo(replyTo);
		notificationConfig.setSubjectTemplate(subjectTemplate);
		notificationConfig.setBodyTemplate(
			notificationConfigInput.getBodyTemplate());

		try {
			notificationConfig.setSettings(
				_jsonSettingsHelper.normalizeObject(
					notificationConfigInput.getSettings()));
		}
		catch (JSONException jsonException) {
			throw new NotificationConfigException(
				"notification-settings-are-invalid", jsonException);
		}
	}

	private static final int _MAX_ADDRESSES_LENGTH = 2000;

	private static final Pattern _addressSeparatorPattern = Pattern.compile(
		"[,;\\s]+");

	@Reference
	private NotificationMailer _notificationMailer;

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
