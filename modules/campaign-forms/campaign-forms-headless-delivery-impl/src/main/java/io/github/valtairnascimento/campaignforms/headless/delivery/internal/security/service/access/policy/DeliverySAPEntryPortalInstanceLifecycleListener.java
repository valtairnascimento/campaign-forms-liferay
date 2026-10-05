package io.github.valtairnascimento.campaignforms.headless.delivery.internal.security.service.access.policy;

import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PortalInstanceLifecycleListener.class)
public class DeliverySAPEntryPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {
	public static final String ALLOWED_SERVICE_SIGNATURES =
		"io.github.valtairnascimento.campaignforms.headless.delivery.internal.resource." +
			"v1_0.*";

	public static final String SAP_ENTRY_NAME = "CAMPAIGN_FORMS_DELIVERY";

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		SAPEntry sapEntry = _sapEntryLocalService.fetchSAPEntry(
			company.getCompanyId(), SAP_ENTRY_NAME);

		if (sapEntry != null) {
			return;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(company.getCompanyId());

		_sapEntryLocalService.addSAPEntry(
			company.getGuestUser(
			).getUserId(),
			ALLOWED_SERVICE_SIGNATURES, true, true, SAP_ENTRY_NAME,
			Collections.singletonMap(
				LocaleUtil.getDefault(), "Campaign Forms - public API"),
			serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Added service access policy " + SAP_ENTRY_NAME +
					" for company " + company.getCompanyId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeliverySAPEntryPortalInstanceLifecycleListener.class);

	@Reference
	private SAPEntryLocalService _sapEntryLocalService;
}
