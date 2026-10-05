package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Notification;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.ConfigDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.NotificationResource;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/notification.properties",
	scope = ServiceScope.PROTOTYPE, service = NotificationResource.class
)
public class NotificationResourceImpl extends BaseNotificationResourceImpl {
	@Override
	public Response deleteNotification(Long notificationId) throws Exception {
		_notificationConfigService.deleteNotificationConfig(notificationId);

		return Response.noContent(
		).build();
	}

	@Override
	public Page<Notification> getCampaignNotificationsPage(Long campaignId)
		throws Exception {
		return Page.of(
			TransformUtil.transform(
				_notificationConfigService.getCampaignNotificationConfigs(
					campaignId),
				_configDTOConverter::toDTO));
	}

	@Override
	public Notification getNotification(Long notificationId)
		throws Exception {
		return _configDTOConverter.toDTO(
			_notificationConfigService.getNotificationConfig(notificationId));
	}

	@Override
	public Notification postCampaignNotification(
			Long campaignId, Notification notification)
		throws Exception {
		return _configDTOConverter.toDTO(
			_notificationConfigService.addNotificationConfig(
				campaignId, _configDTOConverter.toInput(notification)));
	}

	@Override
	public Notification putNotification(
			Long notificationId, Notification notification)
		throws Exception {
		return _configDTOConverter.toDTO(
			_notificationConfigService.updateNotificationConfig(
				notificationId, _configDTOConverter.toInput(notification)));
	}

	@Reference
	private ConfigDTOConverter _configDTOConverter;

	@Reference
	private NotificationConfigService _notificationConfigService;
}
