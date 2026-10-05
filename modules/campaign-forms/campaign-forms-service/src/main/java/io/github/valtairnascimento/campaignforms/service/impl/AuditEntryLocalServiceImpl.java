package io.github.valtairnascimento.campaignforms.service.impl;

import io.github.valtairnascimento.campaignforms.constants.AuditAction;
import io.github.valtairnascimento.campaignforms.model.AuditEntry;
import io.github.valtairnascimento.campaignforms.service.base.AuditEntryLocalServiceBaseImpl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=io.github.valtairnascimento.campaignforms.model.AuditEntry",
	service = AopService.class
)
public class AuditEntryLocalServiceImpl extends AuditEntryLocalServiceBaseImpl {
	@Override
	public AuditEntry addAuditEntry(
		long companyId, long groupId, long userId, long campaignId,
		AuditAction action, String className, long classPK,
		JSONObject details) {
		AuditEntry auditEntry = auditEntryPersistence.create(
			counterLocalService.increment());

		auditEntry.setGroupId(groupId);
		auditEntry.setCompanyId(companyId);
		auditEntry.setUserId(userId);

		User user = userLocalService.fetchUser(userId);

		auditEntry.setUserName(
			(user == null) ? StringPool.BLANK : user.getFullName());

		auditEntry.setCreateDate(new Date());
		auditEntry.setCampaignId(campaignId);
		auditEntry.setAction(action.name());
		auditEntry.setClassName(className);
		auditEntry.setClassPK(classPK);
		auditEntry.setDetails(
			(details == null) ? "{}" : details.toString());

		return auditEntryPersistence.update(auditEntry);
	}

	@Override
	public List<AuditEntry> getCampaignAuditEntries(
		long campaignId, int start, int end) {
		return auditEntryPersistence.findByCampaignId(campaignId, start, end);
	}

	@Override
	public int getCampaignAuditEntriesCount(long campaignId) {
		return auditEntryPersistence.countByCampaignId(campaignId);
	}

}
