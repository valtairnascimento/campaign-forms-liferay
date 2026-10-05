package io.github.valtairnascimento.campaignforms.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;

import java.util.concurrent.Callable;

public class CampaignTransactionUtil {
	public static <T> T invoke(Callable<T> callable) throws PortalException {
		try {
			return TransactionInvokerUtil.invoke(_transactionConfig, callable);
		}
		catch (PortalException | RuntimeException exception) {
			throw exception;
		}
		catch (Throwable throwable) {
			throw new SystemException(throwable);
		}
	}

	public static <T> T invokeReadOnly(Callable<T> callable)
		throws Throwable {
		return TransactionInvokerUtil.invoke(_readOnlyTransactionConfig, callable);
	}

	private CampaignTransactionUtil() {
	}

	private static final TransactionConfig _readOnlyTransactionConfig =
		TransactionConfig.Factory.create(
			Isolation.PORTAL, Propagation.REQUIRED, true, -1,
			new Class<?>[] {Exception.class}, new String[0], new Class<?>[0],
			new String[0]);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});
}
