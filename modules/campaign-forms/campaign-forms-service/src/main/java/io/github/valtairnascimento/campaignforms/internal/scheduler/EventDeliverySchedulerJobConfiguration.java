package io.github.valtairnascimento.campaignforms.internal.scheduler;

import io.github.valtairnascimento.campaignforms.service.EventDeliveryLocalService;

import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = SchedulerJobConfiguration.class)
public class EventDeliverySchedulerJobConfiguration
	implements SchedulerJobConfiguration {
	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () -> {
			int count = _eventDeliveryLocalService.processDueEventDeliveries();

			if ((count > 0) && _log.isInfoEnabled()) {
				_log.info("Processed " + count + " campaign event deliveries");
			}
		};
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return TriggerConfiguration.createTriggerConfiguration(
			5, TimeUnit.MINUTE);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EventDeliverySchedulerJobConfiguration.class);

	@Reference
	private EventDeliveryLocalService _eventDeliveryLocalService;
}
