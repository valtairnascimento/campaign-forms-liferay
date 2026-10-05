package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.submission.CaptchaVerifier;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.Iterator;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(
	property = "service.ranking:Integer=700", service = SubmissionStep.class
)
public class CaptchaSubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext) {
		if (!submissionContext.getFormVersion(
			).isCaptchaEnabled()) {
			return;
		}

		Iterator<CaptchaVerifier> iterator = _captchaVerifiers.iterator();

		if (!iterator.hasNext()) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"CAPTCHA is enabled for form version " +
						submissionContext.getFormVersion(
						).getFormVersionId() +
							", but no CaptchaVerifier is registered");
			}

			return;
		}

		CaptchaVerifier captchaVerifier = iterator.next();

		SubmissionCommand submissionCommand = submissionContext.getCommand();

		if (!captchaVerifier.verify(
				submissionContext.getCampaign(
				).getCompanyId(),
				submissionCommand.getCaptchaToken(),
				submissionCommand.getIpAddress())) {
			submissionContext.addError(
				new FieldError(
					"captcha", FieldError.CAPTCHA_INVALID,
					"field-error-captcha-invalid"));
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_captchaVerifiers = ServiceTrackerListFactory.open(
			bundleContext, CaptchaVerifier.class, Collections.reverseOrder());
	}

	@Deactivate
	protected void deactivate() {
		_captchaVerifiers.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CaptchaSubmissionStep.class);

	private ServiceTrackerList<CaptchaVerifier> _captchaVerifiers;
}
