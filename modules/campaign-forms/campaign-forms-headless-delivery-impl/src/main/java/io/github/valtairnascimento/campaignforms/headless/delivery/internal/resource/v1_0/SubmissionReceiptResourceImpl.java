package io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.domain.FormSubmissionService;
import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionConsent;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionReceipt;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionRequest;
import io.github.valtairnascimento.campaignforms.headless.delivery.dto.v1_0.SubmissionSource;
import io.github.valtairnascimento.campaignforms.headless.delivery.resource.v1_0.SubmissionReceiptResource;
import io.github.valtairnascimento.campaignforms.submission.SubmissionCommand;
import io.github.valtairnascimento.campaignforms.submission.SubmissionResult;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.SecureRandom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/submission-receipt.properties",
	scope = ServiceScope.PROTOTYPE, service = SubmissionReceiptResource.class
)
public class SubmissionReceiptResourceImpl
	extends BaseSubmissionReceiptResourceImpl {
	@Override
	public SubmissionReceipt postSiteCampaignBySlugSubmission(
			Long siteId, String slug, String idempotencyKey,
			SubmissionRequest submissionRequest)
		throws Exception {
		SubmissionResult submissionResult = _formSubmissionService.submit(
			siteId, slug,
			_toSubmissionCommand(idempotencyKey, submissionRequest));

		SubmissionReceipt submissionReceipt = new SubmissionReceipt();

		submissionReceipt.setMessage(
			_nullIfEmpty(submissionResult.getSuccessMessage()));
		submissionReceipt.setRedirectUrl(
			_nullIfEmpty(submissionResult.getRedirectUrl()));
		submissionReceipt.setSubmissionId(
			_getSubmissionId(submissionResult));
		submissionReceipt.setSuccess(true);
		submissionReceipt.setTitle(
			_nullIfEmpty(submissionResult.getSuccessTitle()));

		return submissionReceipt;
	}

	private long _getSubmissionId(SubmissionResult submissionResult) {
		if (submissionResult.getOutcome() !=
				SubmissionResult.Outcome.DISCARDED) {
			return submissionResult.getSubmissionId();
		}

		return 100000 + _secureRandom.nextInt(Integer.MAX_VALUE);
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	private SubmissionCommand _toSubmissionCommand(
			String idempotencyKey, SubmissionRequest submissionRequest)
		throws InvalidFieldException {
		SubmissionCommand submissionCommand = new SubmissionCommand();

		if (submissionRequest == null) {
			submissionRequest = new SubmissionRequest();
		}

		Map<String, Object> values = submissionRequest.getValues();

		if (values != null) {
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				submissionCommand.putValues(
					entry.getKey(), _toStrings(entry.getValue()));
			}
		}

		SubmissionConsent submissionConsent = submissionRequest.getConsent();

		if (submissionConsent != null) {
			submissionCommand.setConsent(
				GetterUtil.getBoolean(submissionConsent.getAccepted()),
				submissionConsent.getVersion());
		}

		SubmissionSource submissionSource = submissionRequest.getSource();

		if (submissionSource != null) {
			submissionCommand.setReferrer(
				submissionSource.getReferrer()
			).setSourceUrl(
				submissionSource.getSourceUrl()
			).setUtm(
				submissionSource.getUtmSource(),
				submissionSource.getUtmMedium(),
				submissionSource.getUtmCampaign(),
				submissionSource.getUtmContent(),
				submissionSource.getUtmTerm()
			);
		}

		User user = contextUser;

		if ((user != null) && !user.isGuestUser()) {
			submissionCommand.setUserId(user.getUserId());
		}

		return submissionCommand.setCaptchaToken(
			submissionRequest.getCaptchaToken()
		).setFormToken(
			submissionRequest.getFormToken()
		).setHoneypotValue(
			submissionRequest.getHoneypot()
		).setIdempotencyKey(
			idempotencyKey
		).setIpAddress(
			contextHttpServletRequest.getRemoteAddr()
		).setLanguageId(
			contextAcceptLanguage.getPreferredLanguageId()
		).setUserAgent(
			contextHttpServletRequest.getHeader("User-Agent")
		);
	}

	private List<String> _toStrings(Object value)
		throws InvalidFieldException {
		if (value == null) {
			return Collections.emptyList();
		}

		if (value instanceof Collection) {
			List<String> strings = new ArrayList<>();

			for (Object item : (Collection<?>)value) {
				if ((item instanceof Collection) || (item instanceof Map)) {
					throw new InvalidFieldException("request-is-invalid");
				}

				if (item != null) {
					strings.add(String.valueOf(item));
				}
			}

			return strings;
		}

		if (value instanceof Map) {
			throw new InvalidFieldException("request-is-invalid");
		}

		return Collections.singletonList(String.valueOf(value));
	}

	private static final SecureRandom _secureRandom = new SecureRandom();

	@Reference
	private FormSubmissionService _formSubmissionService;
}
