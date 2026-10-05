package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.input.NotificationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.NotificationConfig;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.base.NotificationConfigServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=NotificationConfig"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class NotificationConfigServiceImpl
	extends NotificationConfigServiceBaseImpl {
	@Override
	public NotificationConfig addNotificationConfig(
			long campaignId, NotificationConfigInput notificationConfigInput)
		throws PortalException {
		_check(campaignId);

		NotificationConfig notificationConfig =
			notificationConfigLocalService.addNotificationConfig(
				getUserId(), campaignId, notificationConfigInput);

		_audit(notificationConfig, AuditAction.NOTIFICATION_ADDED);

		return notificationConfig;
	}

	@Override
	public NotificationConfig deleteNotificationConfig(
			long notificationConfigId)
		throws PortalException {
		NotificationConfig notificationConfig = _getNotificationConfig(
			notificationConfigId);

		notificationConfig =
			notificationConfigLocalService.deleteNotificationConfig(
				notificationConfigId);

		_audit(notificationConfig, AuditAction.NOTIFICATION_DELETED);

		return notificationConfig;
	}

	@Override
	public List<NotificationConfig> getCampaignNotificationConfigs(
			long campaignId)
		throws PortalException {
		_check(campaignId);

		return notificationConfigLocalService.getCampaignNotificationConfigs(
			campaignId);
	}

	@Override
	public NotificationConfig getNotificationConfig(long notificationConfigId)
		throws PortalException {
		return _getNotificationConfig(notificationConfigId);
	}

	@Override
	public NotificationConfig updateNotificationConfig(
			long notificationConfigId,
			NotificationConfigInput notificationConfigInput)
		throws PortalException {
		_getNotificationConfig(notificationConfigId);

		NotificationConfig notificationConfig =
			notificationConfigLocalService.updateNotificationConfig(
				notificationConfigId, notificationConfigInput);

		_audit(notificationConfig, AuditAction.NOTIFICATION_UPDATED);

		return notificationConfig;
	}

	private void _audit(
			NotificationConfig notificationConfig, AuditAction auditAction)
		throws PortalException {
		_auditEntryLocalService.addAuditEntry(
			notificationConfig.getCompanyId(), notificationConfig.getGroupId(),
			getUserId(), notificationConfig.getCampaignId(), auditAction,
			NotificationConfig.class.getName(),
			notificationConfig.getNotificationConfigId(),
			_jsonFactory.createJSONObject(
			).put(
				"enabled", notificationConfig.isEnabled()
			).put(
				"eventType", notificationConfig.getEventType()
			).put(
				"name", notificationConfig.getName()
			));
	}

	@Override
	public String sendTestNotification(long notificationConfigId)
		throws PortalException {
		_getNotificationConfig(notificationConfigId);

		return notificationConfigLocalService.sendTestNotification(
			notificationConfigId, getUserId());
	}

	private void _check(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId,
			CampaignActionKeys.MANAGE_NOTIFICATIONS);
	}

	private NotificationConfig _getNotificationConfig(
			long notificationConfigId)
		throws PortalException {
		NotificationConfig notificationConfig =
			notificationConfigLocalService.getNotificationConfig(
				notificationConfigId);

		_check(notificationConfig.getCampaignId());

		return notificationConfig;
	}

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference(
		target = "(model.class.name=io.github.valtairnascimento.campaignforms.model.Campaign)"
	)
	private ModelResourcePermission<Campaign> _campaignModelResourcePermission;

	@Reference
	private JSONFactory _jsonFactory;
}
