package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;
import io.github.valtairnascimento.campaignforms.exception.IntegrationConfigurationException;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Integration;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Notification;
import io.github.valtairnascimento.campaignforms.input.IntegrationConfigInput;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ConfigDTOConverter.class)
public class ConfigDTOConverter {
	public Integration toDTO(IntegrationConfig integrationConfig) {
		Integration dto = new Integration();

		dto.setEnabled(integrationConfig.isEnabled());

		dto.setEventTypes(StringUtil.split(integrationConfig.getEventTypes()));
		dto.setId(integrationConfig.getIntegrationConfigId());
		dto.setName(integrationConfig.getName());
		dto.setProviderKey(integrationConfig.getProviderKey());
		dto.setSettings(_jsonMapHelper.toMap(integrationConfig.getSettings()));

		return dto;
	}

	public Notification toDTO(NotificationConfig notificationConfig) {
		Notification dto = new Notification();

		dto.setBcc(_nullIfEmpty(notificationConfig.getBcc()));
		dto.setBodyTemplate(_nullIfEmpty(notificationConfig.getBodyTemplate()));
		dto.setCc(_nullIfEmpty(notificationConfig.getCc()));
		dto.setChannel(
			Notification.Channel.create(notificationConfig.getChannel()));
		dto.setEnabled(notificationConfig.isEnabled());
		dto.setEventType(
			Notification.EventType.create(notificationConfig.getEventType()));
		dto.setFromName(_nullIfEmpty(notificationConfig.getFromName()));
		dto.setId(notificationConfig.getNotificationConfigId());
		dto.setName(notificationConfig.getName());
		dto.setRecipients(_nullIfEmpty(notificationConfig.getRecipients()));
		dto.setReplyTo(_nullIfEmpty(notificationConfig.getReplyTo()));
		dto.setSettings(_jsonMapHelper.toMap(notificationConfig.getSettings()));
		dto.setSubjectTemplate(
			_nullIfEmpty(notificationConfig.getSubjectTemplate()));

		return dto;
	}

	public IntegrationConfigInput toInput(Integration integration)
		throws IntegrationConfigurationException {
		List<EventType> eventTypes = new ArrayList<>();

		if (integration.getEventTypes() != null) {
			for (String eventType : integration.getEventTypes()) {
				try {
					eventTypes.add(EventType.parse(eventType));
				}
				catch (IllegalArgumentException illegalArgumentException) {
					throw new IntegrationConfigurationException(
						"integration-event-is-invalid",
						illegalArgumentException);
				}
			}
		}

		return new IntegrationConfigInput(
		).setEnabled(
			GetterUtil.getBoolean(integration.getEnabled())
		).setEventTypes(
			eventTypes
		).setName(
			integration.getName()
		).setProviderKey(
			integration.getProviderKey()
		).setSettings(
			_jsonMapHelper.toJSON(integration.getSettings())
		);
	}

	public NotificationConfigInput toInput(Notification notification) {
		NotificationConfigInput notificationConfigInput =
			new NotificationConfigInput(
			).setBcc(
				notification.getBcc()
			).setBodyTemplate(
				notification.getBodyTemplate()
			).setCc(
				notification.getCc()
			).setEnabled(
				GetterUtil.getBoolean(notification.getEnabled())
			).setFromName(
				notification.getFromName()
			).setName(
				notification.getName()
			).setRecipients(
				notification.getRecipients()
			).setReplyTo(
				notification.getReplyTo()
			).setSettings(
				_jsonMapHelper.toJSON(notification.getSettings())
			).setSubjectTemplate(
				notification.getSubjectTemplate()
			);

		if (notification.getChannel() != null) {
			notificationConfigInput.setChannel(
				NotificationChannelType.parse(
					notification.getChannel(
					).getValue()));
		}

		if (notification.getEventType() != null) {
			notificationConfigInput.setEventType(
				EventType.parse(
					notification.getEventType(
					).getValue()));
		}

		return notificationConfigInput;
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	@Reference
	private JSONMapHelper _jsonMapHelper;
}
