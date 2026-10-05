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

import io.github.valtairnascimento.campaignforms.service.CampaignServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>CampaignServiceUtil</code> service
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
public class CampaignServiceHttp {

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			addCampaign(
				HttpPrincipal httpPrincipal, long groupId,
				io.github.valtairnascimento.campaignforms.input.CampaignInput
					campaignInput,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "addCampaign",
				_addCampaignParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, campaignInput, serviceContext);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			archiveCampaign(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "archiveCampaign",
				_archiveCampaignParameterTypes1);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			convertToCustomPage(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "convertToCustomPage",
				_convertToCustomPageParameterTypes2);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			deleteCampaign(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "deleteCampaign",
				_deleteCampaignParameterTypes3);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			duplicateCampaign(
				HttpPrincipal httpPrincipal, long sourceCampaignId,
				io.github.valtairnascimento.campaignforms.domain.
					DuplicationRequest duplicationRequest,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "duplicateCampaign",
				_duplicateCampaignParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sourceCampaignId, duplicationRequest,
				serviceContext);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaign(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getCampaign",
				_getCampaignParameterTypes5);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long groupId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getCampaignByExternalReferenceCode",
				_getCampaignByExternalReferenceCodeParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, externalReferenceCode);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			getCampaignBySlug(
				HttpPrincipal httpPrincipal, long groupId, String slug)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getCampaignBySlug",
				_getCampaignBySlugParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, slug);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.Campaign>
			getGroupCampaigns(
				HttpPrincipal httpPrincipal, long groupId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<io.github.valtairnascimento.campaignforms.model.Campaign>
						orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getGroupCampaigns",
				_getGroupCampaignsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<io.github.valtairnascimento.campaignforms.model.Campaign>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getGroupCampaignsCount(
		HttpPrincipal httpPrincipal, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getGroupCampaignsCount",
				_getGroupCampaignsCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.domain.PublicationIssue>
				getPublicationIssues(
					HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "getPublicationIssues",
				_getPublicationIssuesParameterTypes10);

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
				<io.github.valtairnascimento.campaignforms.domain.
					PublicationIssue>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			publishCampaign(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "publishCampaign",
				_publishCampaignParameterTypes11);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			reopenCampaign(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "reopenCampaign",
				_reopenCampaignParameterTypes12);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Campaign
			updateCampaign(
				HttpPrincipal httpPrincipal, long campaignId,
				io.github.valtairnascimento.campaignforms.input.CampaignInput
					campaignInput,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CampaignServiceUtil.class, "updateCampaign",
				_updateCampaignParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, campaignInput, serviceContext);

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

			return (io.github.valtairnascimento.campaignforms.model.Campaign)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CampaignServiceHttp.class);

	private static final Class<?>[] _addCampaignParameterTypes0 = new Class[] {
		long.class,
		io.github.valtairnascimento.campaignforms.input.CampaignInput.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _archiveCampaignParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _convertToCustomPageParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteCampaignParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _duplicateCampaignParameterTypes4 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.domain.DuplicationRequest.
				class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getCampaignParameterTypes5 = new Class[] {
		long.class
	};
	private static final Class<?>[]
		_getCampaignByExternalReferenceCodeParameterTypes6 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getCampaignBySlugParameterTypes7 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getGroupCampaignsParameterTypes8 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupCampaignsCountParameterTypes9 =
		new Class[] {long.class};
	private static final Class<?>[] _getPublicationIssuesParameterTypes10 =
		new Class[] {long.class};
	private static final Class<?>[] _publishCampaignParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[] _reopenCampaignParameterTypes12 =
		new Class[] {long.class};
	private static final Class<?>[] _updateCampaignParameterTypes13 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.CampaignInput.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}