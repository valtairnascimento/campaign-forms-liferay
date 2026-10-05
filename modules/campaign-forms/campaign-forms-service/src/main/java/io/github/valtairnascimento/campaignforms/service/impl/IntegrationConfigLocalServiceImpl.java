package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.exception.IntegrationConfigurationException;
import io.github.valtairnascimento.campaignforms.input.IntegrationConfigInput;
import io.github.valtairnascimento.campaignforms.internal.json.JSONSettingsHelper;
import io.github.valtairnascimento.campaignforms.internal.validation.CampaignValidator;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.IntegrationConfig;
import io.github.valtairnascimento.campaignforms.service.base.IntegrationConfigLocalServiceBaseImpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.IntegrationConfig",
	service = AopService.class
)
public class IntegrationConfigLocalServiceImpl
	extends IntegrationConfigLocalServiceBaseImpl {
	@Override
	public IntegrationConfig addIntegrationConfig(
			long userId, long campaignId,
			IntegrationConfigInput integrationConfigInput)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		User user = userLocalService.getUser(userId);

		IntegrationConfig integrationConfig =
			integrationConfigPersistence.create(
				counterLocalService.increment());

		integrationConfig.setGroupId(campaign.getGroupId());
		integrationConfig.setCompanyId(campaign.getCompanyId());
		integrationConfig.setUserId(userId);
		integrationConfig.setUserName(user.getFullName());

		Date now = new Date();

		integrationConfig.setCreateDate(now);
		integrationConfig.setModifiedDate(now);

		integrationConfig.setCampaignId(campaignId);

		_setFields(integrationConfig, integrationConfigInput);

		return integrationConfigPersistence.update(integrationConfig);
	}

	@Override
	public void deleteCampaignIntegrationConfigs(long campaignId) {
		integrationConfigPersistence.removeByCampaignId(campaignId);
	}

	@Override
	public List<IntegrationConfig> getCampaignIntegrationConfigs(
		long campaignId) {
		return integrationConfigPersistence.findByCampaignId(campaignId);
	}

	@Override
	public List<IntegrationConfig> getEnabledIntegrationConfigs(
		long campaignId, EventType eventType) {
		List<IntegrationConfig> integrationConfigs = new ArrayList<>();

		for (IntegrationConfig integrationConfig :
				integrationConfigPersistence.findByCampaignId(campaignId)) {
			if (integrationConfig.isEnabled() &&
				StringUtil.contains(
					integrationConfig.getEventTypes(), eventType.name())) {
				integrationConfigs.add(integrationConfig);
			}
		}

		return integrationConfigs;
	}

	@Override
	public IntegrationConfig updateIntegrationConfig(
			long integrationConfigId,
			IntegrationConfigInput integrationConfigInput)
		throws PortalException {
		IntegrationConfig integrationConfig =
			integrationConfigPersistence.findByPrimaryKey(integrationConfigId);

		integrationConfig.setModifiedDate(new Date());

		_setFields(integrationConfig, integrationConfigInput);

		return integrationConfigPersistence.update(integrationConfig);
	}

	private void _setFields(
			IntegrationConfig integrationConfig,
			IntegrationConfigInput integrationConfigInput)
		throws PortalException {
		if (!CampaignValidator.isValidKey(
				integrationConfigInput.getProviderKey())) {
			throw new IntegrationConfigurationException(
				"integration-provider-is-invalid");
		}

		String name = integrationConfigInput.getName();

		if (CampaignValidator.isBlank(name)) {
			throw new IntegrationConfigurationException(
				"integration-name-is-required");
		}

		if (name.length() > CampaignValidator.MAX_NAME_LENGTH) {
			throw new IntegrationConfigurationException(
				"integration-name-is-too-long");
		}

		Set<String> eventTypeNames = new LinkedHashSet<>();

		for (EventType eventType : integrationConfigInput.getEventTypes()) {
			if (eventType != null) {
				eventTypeNames.add(eventType.name());
			}
		}

		if (integrationConfigInput.isEnabled() && eventTypeNames.isEmpty()) {
			throw new IntegrationConfigurationException(
				"integration-event-is-required");
		}

		integrationConfig.setProviderKey(
			integrationConfigInput.getProviderKey());
		integrationConfig.setName(name);
		integrationConfig.setEnabled(integrationConfigInput.isEnabled());
		integrationConfig.setEventTypes(
			StringUtil.merge(eventTypeNames, StringPool.COMMA));

		try {
			integrationConfig.setSettings(
				_jsonSettingsHelper.normalizeObject(
					integrationConfigInput.getSettings()));
		}
		catch (JSONException jsonException) {
			throw new IntegrationConfigurationException(
				"integration-settings-are-invalid", jsonException);
		}
	}

	@Reference
	private JSONSettingsHelper _jsonSettingsHelper;
}
