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

import io.github.valtairnascimento.campaignforms.service.PageConfigurationServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>PageConfigurationServiceUtil</code> service
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
public class PageConfigurationServiceHttp {

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			addPageBlock(
				HttpPrincipal httpPrincipal, long campaignId, String blockType,
				String configuration, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "addPageBlock",
				_addPageBlockParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, blockType, configuration, enabled);

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

			return (io.github.valtairnascimento.campaignforms.model.PageBlock)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.PageConfiguration
				applyPageTemplate(
					HttpPrincipal httpPrincipal, long campaignId,
					String templateKey)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "applyPageTemplate",
				_applyPageTemplateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, templateKey);

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
				PageConfiguration)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			deletePageBlock(HttpPrincipal httpPrincipal, long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "deletePageBlock",
				_deletePageBlockParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, pageBlockId);

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

			return (io.github.valtairnascimento.campaignforms.model.PageBlock)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			duplicatePageBlock(HttpPrincipal httpPrincipal, long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "duplicatePageBlock",
				_duplicatePageBlockParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, pageBlockId);

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

			return (io.github.valtairnascimento.campaignforms.model.PageBlock)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.PageConfiguration
				getDraftPageConfiguration(
					HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "getDraftPageConfiguration",
				_getDraftPageConfigurationParameterTypes4);

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

			return (io.github.valtairnascimento.campaignforms.model.
				PageConfiguration)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			getPageBlock(HttpPrincipal httpPrincipal, long pageBlockId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "getPageBlock",
				_getPageBlockParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, pageBlockId);

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

			return (io.github.valtairnascimento.campaignforms.model.PageBlock)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				getPageBlocks(HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "getPageBlocks",
				_getPageBlocksParameterTypes6);

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
				<io.github.valtairnascimento.campaignforms.model.PageBlock>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				movePageBlock(
					HttpPrincipal httpPrincipal, long pageBlockId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "movePageBlock",
				_movePageBlockParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, pageBlockId, position);

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
				<io.github.valtairnascimento.campaignforms.model.PageBlock>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.PageBlock>
				reorderPageBlocks(
					HttpPrincipal httpPrincipal, long campaignId,
					long[] pageBlockIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "reorderPageBlocks",
				_reorderPageBlocksParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, pageBlockIds);

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
				<io.github.valtairnascimento.campaignforms.model.PageBlock>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.PageBlock
			updatePageBlock(
				HttpPrincipal httpPrincipal, long pageBlockId,
				String configuration, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "updatePageBlock",
				_updatePageBlockParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, pageBlockId, configuration, enabled);

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

			return (io.github.valtairnascimento.campaignforms.model.PageBlock)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		io.github.valtairnascimento.campaignforms.model.PageConfiguration
				updatePageSettings(
					HttpPrincipal httpPrincipal, long campaignId,
					io.github.valtairnascimento.campaignforms.input.
						PageSettingsInput pageSettingsInput)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PageConfigurationServiceUtil.class, "updatePageSettings",
				_updatePageSettingsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, pageSettingsInput);

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
				PageConfiguration)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PageConfigurationServiceHttp.class);

	private static final Class<?>[] _addPageBlockParameterTypes0 = new Class[] {
		long.class, String.class, String.class, boolean.class
	};
	private static final Class<?>[] _applyPageTemplateParameterTypes1 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _deletePageBlockParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _duplicatePageBlockParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getDraftPageConfigurationParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getPageBlockParameterTypes5 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getPageBlocksParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _movePageBlockParameterTypes7 =
		new Class[] {long.class, int.class};
	private static final Class<?>[] _reorderPageBlocksParameterTypes8 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _updatePageBlockParameterTypes9 =
		new Class[] {long.class, String.class, boolean.class};
	private static final Class<?>[] _updatePageSettingsParameterTypes10 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.PageSettingsInput.
				class
		};

}