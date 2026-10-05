package io.github.valtairnascimento.campaignforms.internal.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

@Component(service = UpgradeStepRegistrator.class)
public class CampaignFormsServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {
	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			UpgradeProcessFactory.addColumns(
				"CF_FormVersion", "consentURL VARCHAR(2048) null"));
	}

}
