package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.DeliveryStatus;
import io.github.valtairnascimento.campaignforms.constants.DeliveryTargetType;
import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.internal.notification.NotificationException;
import io.github.valtairnascimento.campaignforms.internal.notification.NotificationMailer;
import io.github.valtairnascimento.campaignforms.model.EventDelivery;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.model.Submission;
import io.github.valtairnascimento.campaignforms.service.IntegrationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.base.EventDeliveryLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.EventDelivery",
	service = AopService.class
)
public class EventDeliveryLocalServiceImpl
	extends EventDeliveryLocalServiceBaseImpl {
	@Override
	public List<EventDelivery> addEventDeliveries(
		Submission submission, EventType eventType) {
		List<EventDelivery> eventDeliveries = new ArrayList<>();

		Date now = new Date();

		for (NotificationConfig notificationConfig :
				_notificationConfigLocalService.getEnabledNotificationConfigs(
					submission.getCampaignId(), eventType)) {
			eventDeliveries.add(
				_addEventDelivery(
					submission, eventType, DeliveryTargetType.NOTIFICATION,
					notificationConfig.getNotificationConfigId(), now));
		}

		for (IntegrationConfig integrationConfig :
				_integrationConfigLocalService.getEnabledIntegrationConfigs(
					submission.getCampaignId(), eventType)) {
			eventDeliveries.add(
				_addEventDelivery(
					submission, eventType, DeliveryTargetType.INTEGRATION,
					integrationConfig.getIntegrationConfigId(), now));
		}

		return eventDeliveries;
	}

	@Override
	public List<EventDelivery> getSubmissionEventDeliveries(long submissionId) {
		return eventDeliveryPersistence.findBySubmissionId(submissionId);
	}

	@Override
	public int processDueEventDeliveries() {
		Date now = new Date();

		List<EventDelivery> eventDeliveries = new ArrayList<>(
			eventDeliveryPersistence.findByS_LtNAD(
				DeliveryStatus.PENDING.name(),
				new Date(now.getTime() - _PENDING_GRACE_MILLIS), 0,
				_BATCH_SIZE));

		eventDeliveries.addAll(
			eventDeliveryPersistence.findByS_LtNAD(
				DeliveryStatus.FAILED.name(), now, 0, _BATCH_SIZE));

		for (EventDelivery eventDelivery : eventDeliveries) {
			processEventDelivery(eventDelivery.getEventDeliveryId());
		}

		return eventDeliveries.size();
	}

	@Override
	public EventDelivery processEventDelivery(long eventDeliveryId) {
		EventDelivery eventDelivery = eventDeliveryPersistence.fetchByPrimaryKey(
			eventDeliveryId);

		if ((eventDelivery == null) ||
			DeliveryStatus.SUCCESS.name(
			).equals(
				eventDelivery.getStatus()
			) ||
			DeliveryStatus.DEAD.name(
			).equals(
				eventDelivery.getStatus()
			)) {
			return eventDelivery;
		}

		try {
			_deliver(eventDelivery);

			eventDelivery.setLastErrorCode(null);
			eventDelivery.setLastErrorMessage(null);
			eventDelivery.setStatus(DeliveryStatus.SUCCESS.name());
		}
		catch (NotificationException notificationException) {
			_fail(
				eventDelivery, notificationException.getCode(),
				notificationException.getMessage(),
				notificationException.isPermanent());
		}
		catch (Exception exception) {
			_log.error(
				"Unable to deliver event " + eventDeliveryId, exception);

			_fail(
				eventDelivery, "delivery-failed",
				String.valueOf(exception.getMessage()), false);
		}

		eventDelivery.setAttempts(eventDelivery.getAttempts() + 1);
		eventDelivery.setModifiedDate(new Date());

		return eventDeliveryPersistence.update(eventDelivery);
	}

	private void _deliver(EventDelivery eventDelivery) throws Exception {
		if (!DeliveryTargetType.NOTIFICATION.name(
			).equals(
				eventDelivery.getTargetType()
			)) {
			throw new NotificationException(
				"integration-provider-is-not-available", true);
		}

		NotificationConfig notificationConfig =
			notificationConfigPersistence.fetchByPrimaryKey(
				eventDelivery.getTargetId());

		if ((notificationConfig == null) || !notificationConfig.isEnabled()) {
			throw new NotificationException(
				"notification-is-not-available", true);
		}

		Submission submission = submissionPersistence.fetchByPrimaryKey(
			eventDelivery.getSubmissionId());

		if (submission == null) {
			throw new NotificationException("submission-not-found", true);
		}

		_notificationMailer.send(
			_notificationMailer.buildMessage(notificationConfig, submission));
	}

	private void _fail(
		EventDelivery eventDelivery, String code, String message,
		boolean permanent) {
		eventDelivery.setLastErrorCode(code);
		eventDelivery.setLastErrorMessage(
			(message == null) ? null :
				message.substring(0, Math.min(message.length(), 500)));

		int attempts = eventDelivery.getAttempts() + 1;

		if (permanent || (attempts >= _MAX_ATTEMPTS)) {
			eventDelivery.setStatus(DeliveryStatus.DEAD.name());

			return;
		}

		eventDelivery.setStatus(DeliveryStatus.FAILED.name());
		eventDelivery.setNextAttemptDate(
			new Date(
				System.currentTimeMillis() +
					(_RETRY_BASE_MILLIS << (attempts - 1))));
	}

	private EventDelivery _addEventDelivery(
		Submission submission, EventType eventType,
		DeliveryTargetType deliveryTargetType, long targetId, Date now) {
		EventDelivery eventDelivery = eventDeliveryPersistence.create(
			counterLocalService.increment());

		eventDelivery.setGroupId(submission.getGroupId());
		eventDelivery.setCompanyId(submission.getCompanyId());
		eventDelivery.setCreateDate(now);
		eventDelivery.setModifiedDate(now);
		eventDelivery.setCampaignId(submission.getCampaignId());
		eventDelivery.setSubmissionId(submission.getSubmissionId());
		eventDelivery.setEventType(eventType.name());
		eventDelivery.setTargetType(deliveryTargetType.name());
		eventDelivery.setTargetId(targetId);
		eventDelivery.setStatus(DeliveryStatus.PENDING.name());
		eventDelivery.setAttempts(0);
		eventDelivery.setNextAttemptDate(now);

		return eventDeliveryPersistence.update(eventDelivery);
	}

	private static final int _BATCH_SIZE = 100;

	private static final int _MAX_ATTEMPTS = 5;

	private static final long _PENDING_GRACE_MILLIS = 2 * 60 * 1000;

	private static final long _RETRY_BASE_MILLIS = 5 * 60 * 1000;

	private static final Log _log = LogFactoryUtil.getLog(
		EventDeliveryLocalServiceImpl.class);

	@Reference
	private IntegrationConfigLocalService _integrationConfigLocalService;

	@Reference
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Reference
	private NotificationMailer _notificationMailer;
}
