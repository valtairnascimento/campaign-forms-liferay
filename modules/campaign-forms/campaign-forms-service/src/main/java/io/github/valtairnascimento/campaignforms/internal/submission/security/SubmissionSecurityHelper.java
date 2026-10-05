package io.github.valtairnascimento.campaignforms.internal.submission.security;

import io.github.valtairnascimento.campaignforms.domain.FormTokenManager;
import io.github.valtairnascimento.campaignforms.model.FormVersion;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.nio.charset.StandardCharsets;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;

import java.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = {FormTokenManager.class, SubmissionSecurityHelper.class})
public class SubmissionSecurityHelper implements FormTokenManager {
	@Override
	public String createFormToken(FormVersion formVersion) {
		return createFormToken(formVersion, new Date());
	}

	@Override
	public String createFormToken(FormVersion formVersion, Date issueDate) {
		return _createFormToken(
			formVersion.getCompanyId(), formVersion.getFormVersionId(),
			issueDate);
	}

	@Override
	public String createFormToken(long companyId, long formVersionId) {
		return _createFormToken(companyId, formVersionId, new Date());
	}

	@Override
	public String getHoneypotFieldName(FormVersion formVersion) {
		return getHoneypotFieldName(
			formVersion.getCompanyId(), formVersion.getFormVersionId());
	}

	@Override
	public String getHoneypotFieldName(long companyId, long formVersionId) {
		byte[] hmac = _hmac(
			companyId, _PURPOSE_HONEYPOT, String.valueOf(formVersionId));

		return "website_" + _toHex(hmac).substring(0, 8);
	}

	public String hashIpAddress(long companyId, String ipAddress) {
		if ((ipAddress == null) || ipAddress.isEmpty()) {
			return "";
		}

		return _toHex(_hmac(companyId, _PURPOSE_IP_HASH, ipAddress));
	}

	public FormTokenStatus verifyFormToken(
		FormVersion formVersion, String formToken, Date now) {
		if ((formToken == null) || (formToken.length() > _MAX_TOKEN_LENGTH)) {
			return FormTokenStatus.INVALID;
		}

		String[] parts = formToken.split("\\.");

		if (parts.length != 3) {
			return FormTokenStatus.INVALID;
		}

		long issueTime;

		try {
			if (Long.parseLong(parts[0]) != formVersion.getFormVersionId()) {
				return FormTokenStatus.INVALID;
			}

			issueTime = Long.parseLong(parts[1]);
		}
		catch (NumberFormatException numberFormatException) {
			return FormTokenStatus.INVALID;
		}

		byte[] expectedSignature = _hmac(
			formVersion.getCompanyId(), _PURPOSE_FORM_TOKEN,
			parts[0] + "." + parts[1]);

		byte[] signature;

		try {
			signature = Base64.getUrlDecoder(
			).decode(
				parts[2]
			);
		}
		catch (IllegalArgumentException illegalArgumentException) {
			return FormTokenStatus.INVALID;
		}

		if (!MessageDigest.isEqual(expectedSignature, signature)) {
			return FormTokenStatus.INVALID;
		}

		long age = now.getTime() - issueTime;

		if (age < MIN_FILL_TIME_MILLIS) {
			return FormTokenStatus.TOO_FAST;
		}

		if (age > MAX_AGE_MILLIS) {
			return FormTokenStatus.EXPIRED;
		}

		return FormTokenStatus.VALID;
	}

	public enum FormTokenStatus {
		EXPIRED, INVALID, TOO_FAST, VALID
	}

	private String _createFormToken(
		long companyId, long formVersionId, Date issueDate) {
		String payload = formVersionId + "." + issueDate.getTime();

		return payload + "." +
			_encoder.encodeToString(
				_hmac(companyId, _PURPOSE_FORM_TOKEN, payload));
	}

	private byte[] _getCompanyKey(long companyId) {
		try {
			Company company = _companyLocalService.getCompany(companyId);

			Key key = company.getKeyObj();

			return key.getEncoded();
		}
		catch (PortalException portalException) {
			throw new IllegalStateException(
				"Unable to get the key of company " + companyId,
				portalException);
		}
	}

	private byte[] _hmac(long companyId, String purpose, String value) {
		try {
			Mac mac = Mac.getInstance(_ALGORITHM);

			mac.init(new SecretKeySpec(_getCompanyKey(companyId), _ALGORITHM));

			mac.update(purpose.getBytes(StandardCharsets.UTF_8));
			mac.update((byte)0);

			return mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
		}
		catch (GeneralSecurityException generalSecurityException) {
			throw new IllegalStateException(generalSecurityException);
		}
	}

	private String _toHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (byte b : bytes) {
			sb.append(Character.forDigit((b >> 4) & 0xF, 16));
			sb.append(Character.forDigit(b & 0xF, 16));
		}

		return sb.toString();
	}

	private static final String _ALGORITHM = "HmacSHA256";

	private static final int _MAX_TOKEN_LENGTH = 200;

	private static final String _PURPOSE_FORM_TOKEN = "campaign-forms-form-token";

	private static final String _PURPOSE_HONEYPOT = "campaign-forms-honeypot";

	private static final String _PURPOSE_IP_HASH = "campaign-forms-ip-hash";

	private static final Base64.Encoder _encoder = Base64.getUrlEncoder(
	).withoutPadding();

	@Reference
	private CompanyLocalService _companyLocalService;
}
