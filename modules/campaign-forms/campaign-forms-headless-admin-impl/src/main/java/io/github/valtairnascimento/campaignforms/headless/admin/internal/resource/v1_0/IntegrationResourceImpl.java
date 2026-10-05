package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Integration;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.ConfigDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.IntegrationResource;
import io.github.valtairnascimento.campaignforms.service.IntegrationConfigService;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/integration.properties",
	scope = ServiceScope.PROTOTYPE, service = IntegrationResource.class
)
public class IntegrationResourceImpl extends BaseIntegrationResourceImpl {
	@Override
	public Response deleteIntegration(Long integrationId) throws Exception {
		_integrationConfigService.deleteIntegrationConfig(integrationId);

		return Response.noContent(
		).build();
	}

	@Override
	public Page<Integration> getCampaignIntegrationsPage(Long campaignId)
		throws Exception {
		return Page.of(
			TransformUtil.transform(
				_integrationConfigService.getCampaignIntegrationConfigs(
					campaignId),
				_configDTOConverter::toDTO));
	}

	@Override
	public Integration getIntegration(Long integrationId) throws Exception {
		return _configDTOConverter.toDTO(
			_integrationConfigService.getIntegrationConfig(integrationId));
	}

	@Override
	public Integration postCampaignIntegration(
			Long campaignId, Integration integration)
		throws Exception {
		return _configDTOConverter.toDTO(
			_integrationConfigService.addIntegrationConfig(
				campaignId, _configDTOConverter.toInput(integration)));
	}

	@Override
	public Integration putIntegration(
			Long integrationId, Integration integration)
		throws Exception {
		return _configDTOConverter.toDTO(
			_integrationConfigService.updateIntegrationConfig(
				integrationId, _configDTOConverter.toInput(integration)));
	}

	@Reference
	private ConfigDTOConverter _configDTOConverter;

	@Reference
	private IntegrationConfigService _integrationConfigService;
}
