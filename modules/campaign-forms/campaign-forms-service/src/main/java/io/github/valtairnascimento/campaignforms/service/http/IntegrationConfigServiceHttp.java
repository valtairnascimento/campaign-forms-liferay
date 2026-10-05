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

import io.github.valtairnascimento.campaignforms.service.IntegrationConfigServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>IntegrationConfigServiceUtil</code> service
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
public class IntegrationConfigServiceHttp {

	public static
		io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				addIntegrationConfig(
					HttpPrincipal httpPrincipal, long campaignId,
					io.github.valtairnascimento.campaignforms.input.
						IntegrationConfigInput integrationConfigInput)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				IntegrationConfigServiceUtil.class, "addIntegrationConfig",
				_addIntegrationConfigParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, integrationConfigInput);

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
				IntegrationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				deleteIntegrationConfig(
					HttpPrincipal httpPrincipal, long integrationConfigId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				IntegrationConfigServiceUtil.class, "deleteIntegrationConfig",
				_deleteIntegrationConfigParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, integrationConfigId);

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
				IntegrationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.IntegrationConfig>
				getCampaignIntegrationConfigs(
					HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				IntegrationConfigServiceUtil.class,
				"getCampaignIntegrationConfigs",
				_getCampaignIntegrationConfigsParameterTypes2);

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
					IntegrationConfig>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				getIntegrationConfig(
					HttpPrincipal httpPrincipal, long integrationConfigId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				IntegrationConfigServiceUtil.class, "getIntegrationConfig",
				_getIntegrationConfigParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, integrationConfigId);

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
				IntegrationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.IntegrationConfig
				updateIntegrationConfig(
					HttpPrincipal httpPrincipal, long integrationConfigId,
					io.github.valtairnascimento.campaignforms.input.
						IntegrationConfigInput integrationConfigInput)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				IntegrationConfigServiceUtil.class, "updateIntegrationConfig",
				_updateIntegrationConfigParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, integrationConfigId, integrationConfigInput);

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
				IntegrationConfig)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		IntegrationConfigServiceHttp.class);

	private static final Class<?>[] _addIntegrationConfigParameterTypes0 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.
				IntegrationConfigInput.class
		};
	private static final Class<?>[] _deleteIntegrationConfigParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getCampaignIntegrationConfigsParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getIntegrationConfigParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _updateIntegrationConfigParameterTypes4 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.
				IntegrationConfigInput.class
		};

}