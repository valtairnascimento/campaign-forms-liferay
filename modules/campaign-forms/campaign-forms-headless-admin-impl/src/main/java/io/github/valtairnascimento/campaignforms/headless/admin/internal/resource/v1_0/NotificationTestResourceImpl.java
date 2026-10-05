package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.NotificationTest;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.NotificationTestResource;
import io.github.valtairnascimento.campaignforms.service.NotificationConfigService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/notification-test.properties",
	scope = ServiceScope.PROTOTYPE, service = NotificationTestResource.class
)
public class NotificationTestResourceImpl
	extends BaseNotificationTestResourceImpl {
	@Override
	public NotificationTest postNotificationTest(Long notificationId)
		throws Exception {
		NotificationTest notificationTest = new NotificationTest();

		notificationTest.setRecipient(
			_notificationConfigService.sendTestNotification(notificationId));

		return notificationTest;
	}

	@Reference
	private NotificationConfigService _notificationConfigService;
}
