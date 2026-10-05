package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.input.IntegrationConfigInput;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.service.AuditEntryLocalService;
import io.github.valtairnascimento.campaignforms.service.base.IntegrationConfigServiceBaseImpl;

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
		"json.web.service.context.path=IntegrationConfig"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class IntegrationConfigServiceImpl
	extends IntegrationConfigServiceBaseImpl {
	@Override
	public IntegrationConfig addIntegrationConfig(
			long campaignId, IntegrationConfigInput integrationConfigInput)
		throws PortalException {
		_check(campaignId);

		IntegrationConfig integrationConfig =
			integrationConfigLocalService.addIntegrationConfig(
				getUserId(), campaignId, integrationConfigInput);

		_audit(integrationConfig, AuditAction.INTEGRATION_ADDED);

		return integrationConfig;
	}

	@Override
	public IntegrationConfig deleteIntegrationConfig(long integrationConfigId)
		throws PortalException {
		_getIntegrationConfig(integrationConfigId);

		IntegrationConfig integrationConfig =
			integrationConfigLocalService.deleteIntegrationConfig(
				integrationConfigId);

		_audit(integrationConfig, AuditAction.INTEGRATION_DELETED);

		return integrationConfig;
	}

	@Override
	public List<IntegrationConfig> getCampaignIntegrationConfigs(
			long campaignId)
		throws PortalException {
		_check(campaignId);

		return integrationConfigLocalService.getCampaignIntegrationConfigs(
			campaignId);
	}

	@Override
	public IntegrationConfig getIntegrationConfig(long integrationConfigId)
		throws PortalException {
		return _getIntegrationConfig(integrationConfigId);
	}

	@Override
	public IntegrationConfig updateIntegrationConfig(
			long integrationConfigId,
			IntegrationConfigInput integrationConfigInput)
		throws PortalException {
		_getIntegrationConfig(integrationConfigId);

		IntegrationConfig integrationConfig =
			integrationConfigLocalService.updateIntegrationConfig(
				integrationConfigId, integrationConfigInput);

		_audit(integrationConfig, AuditAction.INTEGRATION_UPDATED);

		return integrationConfig;
	}

	private void _audit(
			IntegrationConfig integrationConfig, AuditAction auditAction)
		throws PortalException {
		_auditEntryLocalService.addAuditEntry(
			integrationConfig.getCompanyId(), integrationConfig.getGroupId(),
			getUserId(), integrationConfig.getCampaignId(), auditAction,
			IntegrationConfig.class.getName(),
			integrationConfig.getIntegrationConfigId(),
			_jsonFactory.createJSONObject(
			).put(
				"enabled", integrationConfig.isEnabled()
			).put(
				"eventTypes", integrationConfig.getEventTypes()
			).put(
				"name", integrationConfig.getName()
			).put(
				"providerKey", integrationConfig.getProviderKey()
			));
	}

	private void _check(long campaignId) throws PortalException {
		_campaignModelResourcePermission.check(
			getPermissionChecker(), campaignId,
			CampaignActionKeys.MANAGE_INTEGRATIONS);
	}

	private IntegrationConfig _getIntegrationConfig(long integrationConfigId)
		throws PortalException {
		IntegrationConfig integrationConfig =
			integrationConfigLocalService.getIntegrationConfig(
				integrationConfigId);

		_check(integrationConfig.getCampaignId());

		return integrationConfig;
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
