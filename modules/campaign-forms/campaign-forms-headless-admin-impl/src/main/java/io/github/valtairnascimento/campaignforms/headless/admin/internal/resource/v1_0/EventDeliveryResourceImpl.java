package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.constants.DeliveryTargetType;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.EventDelivery;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.EventDeliveryResource;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.service.IntegrationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/event-delivery.properties",
	scope = ServiceScope.PROTOTYPE, service = EventDeliveryResource.class
)
public class EventDeliveryResourceImpl extends BaseEventDeliveryResourceImpl {
	@Override
	public Page<EventDelivery> getSubmissionEventDeliveriesPage(
			Long submissionId)
		throws Exception {
		return Page.of(
			TransformUtil.transform(
				_submissionService.getSubmissionEventDeliveries(submissionId),
				this::_toDTO));
	}

	private String _getTargetName(
		io.github.valtairnascimento.campaignforms.model.EventDelivery eventDelivery) {
		if (DeliveryTargetType.NOTIFICATION.name(
			).equals(
				eventDelivery.getTargetType()
			)) {
			NotificationConfig notificationConfig =
				_notificationConfigLocalService.fetchNotificationConfig(
					eventDelivery.getTargetId());

			return (notificationConfig == null) ? null :
				notificationConfig.getName();
		}

		IntegrationConfig integrationConfig =
			_integrationConfigLocalService.fetchIntegrationConfig(
				eventDelivery.getTargetId());

		return (integrationConfig == null) ? null :
			integrationConfig.getName();
	}

	private EventDelivery _toDTO(
		io.github.valtairnascimento.campaignforms.model.EventDelivery eventDelivery) {
		EventDelivery dto = new EventDelivery();

		dto.setAttempts(eventDelivery.getAttempts());
		dto.setDateCreated(eventDelivery.getCreateDate());
		dto.setDateModified(eventDelivery.getModifiedDate());
		dto.setEventType(eventDelivery.getEventType());
		dto.setId(eventDelivery.getEventDeliveryId());
		dto.setLastErrorCode(eventDelivery.getLastErrorCode());
		dto.setLastErrorMessage(eventDelivery.getLastErrorMessage());
		dto.setStatus(eventDelivery.getStatus());
		dto.setTargetName(_getTargetName(eventDelivery));
		dto.setTargetType(eventDelivery.getTargetType());

		return dto;
	}

	@Reference
	private IntegrationConfigLocalService _integrationConfigLocalService;

	@Reference
	private NotificationConfigLocalService _notificationConfigLocalService;

	@Reference
	private SubmissionService _submissionService;
}
