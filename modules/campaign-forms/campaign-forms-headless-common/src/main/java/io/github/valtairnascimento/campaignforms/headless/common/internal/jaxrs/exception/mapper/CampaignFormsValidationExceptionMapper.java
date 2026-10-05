package io.github.valtairnascimento.campaignforms.headless.common.internal.jaxrs.exception.mapper;

import javax.validation.ValidationException;

import javax.annotation.Priority;

import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		BaseCampaignFormsExceptionMapper.APPLICATION_SELECT,
		BaseCampaignFormsExceptionMapper.EXTENSION,
		"osgi.jaxrs.name=CampaignForms.CampaignFormsValidationExceptionMapper",
		"service.ranking:Integer=1000"
	},
	service = ExceptionMapper.class
)
@Priority(BaseCampaignFormsExceptionMapper.PRIORITY)
public class CampaignFormsValidationExceptionMapper
	extends BaseCampaignFormsExceptionMapper<ValidationException> {
}
