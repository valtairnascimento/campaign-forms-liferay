package io.github.valtairnascimento.campaignforms.headless.common.internal.jaxrs.exception.mapper;

import com.fasterxml.jackson.core.JsonParseException;

import javax.annotation.Priority;

import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		BaseCampaignFormsExceptionMapper.APPLICATION_SELECT,
		BaseCampaignFormsExceptionMapper.EXTENSION,
		"osgi.jaxrs.name=CampaignForms.CampaignFormsJsonParseExceptionMapper",
		"service.ranking:Integer=1000"
	},
	service = ExceptionMapper.class
)
@Priority(BaseCampaignFormsExceptionMapper.PRIORITY)
public class CampaignFormsJsonParseExceptionMapper
	extends BaseCampaignFormsExceptionMapper<JsonParseException> {
}
