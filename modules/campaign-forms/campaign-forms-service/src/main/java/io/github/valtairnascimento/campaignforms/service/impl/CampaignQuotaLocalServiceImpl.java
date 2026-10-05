package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.exception.SubmissionLimitReachedException;
import io.github.valtairnascimento.campaignforms.model.Campaign;
import io.github.valtairnascimento.campaignforms.model.CampaignQuota;
import io.github.valtairnascimento.campaignforms.service.base.CampaignQuotaLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.CampaignQuota",
	service = AopService.class
)
public class CampaignQuotaLocalServiceImpl
	extends CampaignQuotaLocalServiceBaseImpl {
	@Override
	public CampaignQuota addCampaignQuotaIfAbsent(Campaign campaign) {
		CampaignQuota campaignQuota = campaignQuotaPersistence.fetchByPrimaryKey(
			campaign.getCampaignId());

		if (campaignQuota != null) {
			return campaignQuota;
		}

		campaignQuota = campaignQuotaPersistence.create(
			campaign.getCampaignId());

		campaignQuota.setCompanyId(campaign.getCompanyId());
		campaignQuota.setUsed(0);

		return campaignQuotaPersistence.update(campaignQuota);
	}

	@Override
	public int getUsedSlots(long campaignId) {
		CampaignQuota campaignQuota = campaignQuotaPersistence.fetchByPrimaryKey(
			campaignId);

		if (campaignQuota == null) {
			return 0;
		}

		return campaignQuota.getUsed();
	}

	@Override
	public void releaseSlot(long campaignId) {
		if (campaignQuotaPersistence.fetchByPrimaryKey(campaignId) == null) {
			return;
		}

		_executeUpdate(_SQL_RELEASE, campaignId, -1);
	}

	@Override
	public int reserveSlot(Campaign campaign) throws PortalException {
		addCampaignQuotaIfAbsent(campaign);

		int count = _executeUpdate(
			_SQL_RESERVE, campaign.getCampaignId(),
			campaign.getSubmissionLimit());

		if (count == 0) {
			throw new SubmissionLimitReachedException(
				"submission-limit-reached");
		}

		Session session = null;

		try {
			session = campaignQuotaPersistence.openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(
				_SQL_SELECT_USED);

			sqlQuery.addScalar("used", Type.INTEGER);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(campaign.getCampaignId());

			Integer used = (Integer)sqlQuery.uniqueResult();

			return used;
		}
		finally {
			campaignQuotaPersistence.closeSession(session);
		}
	}

	private int _executeUpdate(String sql, long campaignId, int limit) {
		Session session = null;

		try {
			session = campaignQuotaPersistence.openSession();

			session.flush();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(campaignId);

			if (limit >= 0) {
				queryPos.add(limit);
			}

			int count = sqlQuery.executeUpdate();

			campaignQuotaPersistence.clearCache();

			return count;
		}
		finally {
			campaignQuotaPersistence.closeSession(session);
		}
	}

	private static final String _SQL_RELEASE =
		"UPDATE CF_CampaignQuota SET used = used - 1 WHERE campaignId = ? " +
			"AND used > 0";

	private static final String _SQL_RESERVE =
		"UPDATE CF_CampaignQuota SET used = used + 1 WHERE campaignId = ? " +
			"AND used < ?";

	private static final String _SQL_SELECT_USED =
		"SELECT used FROM CF_CampaignQuota WHERE campaignId = ?";
}
