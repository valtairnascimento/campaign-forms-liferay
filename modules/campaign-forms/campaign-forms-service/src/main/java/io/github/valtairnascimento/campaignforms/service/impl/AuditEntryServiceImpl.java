package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.service.base.AuditEntryServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"json.web.service.context.name=campaignforms",
		"json.web.service.context.path=AuditEntry"
	},
	service = AopService.class
)
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AuditEntryServiceImpl extends AuditEntryServiceBaseImpl {
}
