package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.DataFeedFormat;
import io.github.valtairnascimento.campaignforms.domain.DataFeedCredentials;
import io.github.valtairnascimento.campaignforms.exception.DataFeedConfigurationException;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.DataFeed;
import io.github.valtairnascimento.campaignforms.service.base.DataFeedLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.Validator;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.DataFeed",
	service = AopService.class
)
public class DataFeedLocalServiceImpl extends DataFeedLocalServiceBaseImpl {
	@Override
	public DataFeedCredentials addDataFeed(
			long userId, long campaignId, String name, boolean seriesScope,
			String format, boolean includeMetadata, Date expirationDate)
		throws PortalException {
		Campaign campaign = campaignPersistence.findByPrimaryKey(campaignId);

		if (seriesScope && Validator.isNull(campaign.getSeriesKey())) {
			throw new DataFeedConfigurationException(
				"data-feed-series-is-required");
		}

		User user = userLocalService.getUser(userId);

		DataFeed dataFeed = dataFeedPersistence.create(
			counterLocalService.increment());

		dataFeed.setGroupId(campaign.getGroupId());
		dataFeed.setCompanyId(campaign.getCompanyId());
		dataFeed.setUserId(userId);
		dataFeed.setUserName(user.getFullName());

		Date now = new Date();

		dataFeed.setCreateDate(now);
		dataFeed.setModifiedDate(now);

		dataFeed.setFeedKey(_generateFeedKey());
		dataFeed.setName(
			Validator.isNull(name) ? campaign.getName() :
				name.substring(0, Math.min(name.length(), 150)));
		dataFeed.setCampaignId(campaignId);

		if (seriesScope) {
			dataFeed.setSeriesKey(campaign.getSeriesKey());
		}

		dataFeed.setFormat(_getFormat(format));
		dataFeed.setIncludeMetadata(includeMetadata);
		dataFeed.setExpirationDate(expirationDate);
		dataFeed.setActive(true);

		String token = _generateToken();

		_setToken(dataFeed, token);

		return new DataFeedCredentials(
			dataFeedPersistence.update(dataFeed), token);
	}

	@Override
	public DataFeed authenticateDataFeed(String feedKey, String token)
		throws PortalException {
		DataFeed dataFeed = (feedKey == null) ? null :
			dataFeedPersistence.fetchByFeedKey(feedKey);

		if ((dataFeed == null) || Validator.isNull(token) ||
			!MessageDigest.isEqual(
				_hash(token).getBytes(StandardCharsets.US_ASCII),
				dataFeed.getTokenHash(
				).getBytes(
					StandardCharsets.US_ASCII
				)) ||
			!dataFeed.isActive() ||
			((dataFeed.getExpirationDate() != null) &&
			 !new Date().before(dataFeed.getExpirationDate()))) {
			throw new PrincipalException.MustBeAuthenticated(0);
		}

		dataFeed.setLastAccessDate(new Date());

		return dataFeedPersistence.update(dataFeed);
	}

	@Override
	public List<DataFeed> getCampaignDataFeeds(long campaignId) {
		return dataFeedPersistence.findByCampaignId(campaignId);
	}

	@Override
	public DataFeedCredentials regenerateDataFeedToken(long dataFeedId)
		throws PortalException {
		DataFeed dataFeed = dataFeedPersistence.findByPrimaryKey(dataFeedId);

		String token = _generateToken();

		_setToken(dataFeed, token);

		dataFeed.setActive(true);
		dataFeed.setModifiedDate(new Date());

		return new DataFeedCredentials(
			dataFeedPersistence.update(dataFeed), token);
	}

	@Override
	public DataFeed revokeDataFeed(long dataFeedId) throws PortalException {
		DataFeed dataFeed = dataFeedPersistence.findByPrimaryKey(dataFeedId);

		dataFeed.setActive(false);
		dataFeed.setModifiedDate(new Date());

		return dataFeedPersistence.update(dataFeed);
	}

	private String _generateFeedKey() {
		StringBuilder sb = new StringBuilder(_FEED_KEY_LENGTH);

		for (int i = 0; i < _FEED_KEY_LENGTH; i++) {
			sb.append(
				_FEED_KEY_ALPHABET.charAt(
					_secureRandom.nextInt(_FEED_KEY_ALPHABET.length())));
		}

		String feedKey = sb.toString();

		if (dataFeedPersistence.fetchByFeedKey(feedKey) != null) {
			return _generateFeedKey();
		}

		return feedKey;
	}

	private String _generateToken() {
		byte[] bytes = new byte[32];

		_secureRandom.nextBytes(bytes);

		return Base64.getUrlEncoder(
		).withoutPadding(
		).encodeToString(
			bytes
		);
	}

	private String _getFormat(String format) {
		for (DataFeedFormat dataFeedFormat : DataFeedFormat.values()) {
			if (dataFeedFormat.name(
				).equalsIgnoreCase(
					format
				)) {
				return dataFeedFormat.name();
			}
		}

		return DataFeedFormat.CSV.name();
	}

	private String _hash(String token) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

			byte[] digest = messageDigest.digest(
				token.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder(digest.length * 2);

			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		}
		catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			throw new IllegalStateException(noSuchAlgorithmException);
		}
	}

	private void _setToken(DataFeed dataFeed, String token) {
		dataFeed.setTokenHash(_hash(token));
		dataFeed.setTokenHint(token.substring(token.length() - 4));
	}

	private static final String _FEED_KEY_ALPHABET =
		"abcdefghijkmnpqrstuvwxyz23456789";

	private static final int _FEED_KEY_LENGTH = 16;

	private static final SecureRandom _secureRandom = new SecureRandom();
}
