package io.github.valtairnascimento.campaignforms.internal.submission;

import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.Form;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class SubmissionContextImpl implements SubmissionContext {
	public SubmissionContextImpl(
		Campaign campaign, Form form, FormVersion formVersion,
		List<FieldDefinition> fieldDefinitions,
		SubmissionCommand submissionCommand, Date now, TimeZone timeZone) {
		_campaign = campaign;
		_form = form;
		_formVersion = formVersion;
		_fieldDefinitions = Collections.unmodifiableList(fieldDefinitions);
		_submissionCommand = submissionCommand;
		_now = now;
		_timeZone = timeZone;
	}

	@Override
	public void addError(FieldError fieldError) {
		_errors.add(fieldError);
	}

	@Override
	public void discard() {
		_discarded = true;
	}

	@Override
	public Campaign getCampaign() {
		return _campaign;
	}

	@Override
	public SubmissionCommand getCommand() {
		return _submissionCommand;
	}

	@Override
	public Date getConsentDate() {
		return _consentDate;
	}

	@Override
	public String getConsentVersion() {
		return _consentVersion;
	}

	@Override
	public List<FieldError> getErrors() {
		return Collections.unmodifiableList(_errors);
	}

	@Override
	public List<FieldDefinition> getFieldDefinitions() {
		return _fieldDefinitions;
	}

	@Override
	public Form getForm() {
		return _form;
	}

	@Override
	public FormVersion getFormVersion() {
		return _formVersion;
	}

	@Override
	public Date getNow() {
		return _now;
	}

	@Override
	public TimeZone getTimeZone() {
		return _timeZone;
	}

	@Override
	public Map<String, List<FieldValue>> getValues() {
		return Collections.unmodifiableMap(_values);
	}

	@Override
	public boolean isDiscarded() {
		return _discarded;
	}

	@Override
	public void setConsent(String consentVersion, Date consentDate) {
		_consentVersion = consentVersion;
		_consentDate = consentDate;
	}

	@Override
	public void setValues(String fieldKey, List<FieldValue> fieldValues) {
		_values.put(fieldKey, Collections.unmodifiableList(fieldValues));
	}

	private final Campaign _campaign;
	private Date _consentDate;
	private String _consentVersion;
	private boolean _discarded;
	private final List<FieldError> _errors = new ArrayList<>();
	private final List<FieldDefinition> _fieldDefinitions;
	private final Form _form;
	private final FormVersion _formVersion;
	private final Date _now;
	private final SubmissionCommand _submissionCommand;
	private final TimeZone _timeZone;
	private final Map<String, List<FieldValue>> _values =
		new LinkedHashMap<>();
}
