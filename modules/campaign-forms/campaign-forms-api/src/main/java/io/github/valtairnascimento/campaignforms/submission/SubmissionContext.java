package io.github.valtairnascimento.campaignforms.submission;

import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public interface SubmissionContext {
	public void addError(FieldError fieldError);

	public void discard();

	public Campaign getCampaign();

	public SubmissionCommand getCommand();

	public Date getConsentDate();

	public String getConsentVersion();

	public List<FieldError> getErrors();

	public List<FieldDefinition> getFieldDefinitions();

	public Form getForm();

	public FormVersion getFormVersion();

	public Date getNow();

	public TimeZone getTimeZone();

	public Map<String, List<FieldValue>> getValues();

	public boolean isDiscarded();

	public void setConsent(String consentVersion, Date consentDate);

	public void setValues(String fieldKey, List<FieldValue> fieldValues);
}
