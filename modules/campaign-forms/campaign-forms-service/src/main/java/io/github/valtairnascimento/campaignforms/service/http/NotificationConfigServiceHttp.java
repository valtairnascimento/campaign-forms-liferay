/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package io.github.valtairnascimento.campaignforms.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import io.github.valtairnascimento.campaignforms.service.NotificationConfigServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>NotificationConfigServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Valtair Nascimento
 * @generated
 */
public class NotificationConfigServiceHttp {

	public static
		io.github.valtairnascimento.campaignforms.model.NotificationConfig
				addNotificationConfig(
					HttpPrincipal httpPrincipal, long campaignId,
					io.github.valtairnascimento.campaignforms.input.
						NotificationConfigInput notificationConfigInput)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class, "addNotificationConfig",
				_addNotificationConfigParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, notificationConfigInput);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (io.github.valtairnascimento.campaignforms.model.
				NotificationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.NotificationConfig
				deleteNotificationConfig(
					HttpPrincipal httpPrincipal, long notificationConfigId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class, "deleteNotificationConfig",
				_deleteNotificationConfigParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, notificationConfigId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (io.github.valtairnascimento.campaignforms.model.
				NotificationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.NotificationConfig>
				getCampaignNotificationConfigs(
					HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class,
				"getCampaignNotificationConfigs",
				_getCampaignNotificationConfigsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<io.github.valtairnascimento.campaignforms.model.
					NotificationConfig>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.NotificationConfig
				getNotificationConfig(
					HttpPrincipal httpPrincipal, long notificationConfigId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class, "getNotificationConfig",
				_getNotificationConfigParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, notificationConfigId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (io.github.valtairnascimento.campaignforms.model.
				NotificationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.NotificationConfig
				updateNotificationConfig(
					HttpPrincipal httpPrincipal, long notificationConfigId,
					io.github.valtairnascimento.campaignforms.input.
						NotificationConfigInput notificationConfigInput)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class, "updateNotificationConfig",
				_updateNotificationConfigParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, notificationConfigId, notificationConfigInput);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (io.github.valtairnascimento.campaignforms.model.
				NotificationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String sendTestNotification(
			HttpPrincipal httpPrincipal, long notificationConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				NotificationConfigServiceUtil.class, "sendTestNotification",
				_sendTestNotificationParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, notificationConfigId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		NotificationConfigServiceHttp.class);

	private static final Class<?>[] _addNotificationConfigParameterTypes0 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput.class
		};
	private static final Class<?>[] _deleteNotificationConfigParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getCampaignNotificationConfigsParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getNotificationConfigParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _updateNotificationConfigParameterTypes4 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.
				NotificationConfigInput.class
		};
	private static final Class<?>[] _sendTestNotificationParameterTypes6 =
		new Class[] {long.class};

}