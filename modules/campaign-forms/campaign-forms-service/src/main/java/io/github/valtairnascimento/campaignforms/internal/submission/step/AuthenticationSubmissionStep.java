package io.github.valtairnascimento.campaignforms.internal.submission.step;

import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.submission.SubmissionContext;
import io.github.valtairnascimento.campaignforms.submission.SubmissionStep;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "service.ranking:Integer=900", service = SubmissionStep.class
)
public class AuthenticationSubmissionStep implements SubmissionStep {
	@Override
	public void process(SubmissionContext submissionContext)
		throws PortalException {
		FormVersion formVersion = submissionContext.getFormVersion();

		if (!formVersion.isRequireAuthentication()) {
			return;
		}

		long userId = submissionContext.getCommand(
		).getUserId();

		User user = null;

		if (userId > 0) {
			user = _userLocalService.fetchUser(userId);
		}

		if ((user == null) || user.isGuestUser() || !user.isActive()) {
			throw new PrincipalException.MustBeAuthenticated(userId);
		}
	}

	@Reference
	private UserLocalService _userLocalService;
}
