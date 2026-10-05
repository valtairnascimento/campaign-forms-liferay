package io.github.valtairnascimento.campaignforms.headless.admin.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/campaign-forms-admin",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=CampaignForms.Admin"
	},
	service = Application.class
)
@Generated("")
public class CampaignFormsAdminApplication extends Application {
}