package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.base.SubmissionValueLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.SubmissionValue",
	service = AopService.class
)
public class SubmissionValueLocalServiceImpl
	extends SubmissionValueLocalServiceBaseImpl {
	@Override
	public List<SubmissionValue> getCampaignSubmissionValues(
		long campaignId, String fieldKey, String stringValue) {
		return submissionValuePersistence.findByC_FK_SV(
			campaignId, fieldKey, stringValue);
	}

	@Override
	public List<SubmissionValue> getSubmissionValues(long submissionId) {
		return submissionValuePersistence.findBySubmissionId(submissionId);
	}

}
