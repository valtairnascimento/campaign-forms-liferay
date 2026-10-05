package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.internal.submission.ConsentTerms;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "service.ranking:Integer=400", service = SubmissionStep.class
)
public class ConsentSubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext) {
		FormVersion formVersion = submissionContext.getFormVersion();

		if (!formVersion.isConsentRequired()) {
			return;
		}

		String consentVersion = ConsentTerms.getVersion(formVersion);

		SubmissionCommand submissionCommand = submissionContext.getCommand();

		if (!submissionCommand.isConsentAccepted() ||
			!Objects.equals(
				submissionCommand.getConsentVersion(), consentVersion)) {
			submissionContext.addError(
				new FieldError(
					"consent", FieldError.CONSENT_REQUIRED,
					"field-error-consent-required"));

			return;
		}

		submissionContext.setConsent(
			consentVersion, submissionContext.getNow());
	}

}
