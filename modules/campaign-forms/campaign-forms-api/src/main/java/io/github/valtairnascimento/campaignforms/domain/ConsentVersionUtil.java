package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.FormVersion;

import com.liferay.portal.kernel.util.Validator;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConsentVersionUtil {
	public static String getVersion(FormVersion formVersion) {
		if (Validator.isNotNull(formVersion.getConsentVersion())) {
			return formVersion.getConsentVersion();
		}

		String terms =
			_nullToEmpty(formVersion.getConsentText()) + "\n" +
				_nullToEmpty(formVersion.getConsentURL());

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

			byte[] digest = messageDigest.digest(
				terms.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder("auto-");

			for (int i = 0; i < 5; i++) {
				sb.append(String.format("%02x", digest[i]));
			}

			return sb.toString();
		}
		catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			throw new IllegalStateException(noSuchAlgorithmException);
		}
	}

	private static String _nullToEmpty(String value) {
		return (value == null) ? "" : value;
	}

	private ConsentVersionUtil() {
	}

}
