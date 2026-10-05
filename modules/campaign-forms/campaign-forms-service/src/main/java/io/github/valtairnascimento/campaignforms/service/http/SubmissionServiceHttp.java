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

import io.github.valtairnascimento.campaignforms.service.SubmissionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SubmissionServiceUtil</code> service
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
public class SubmissionServiceHttp {

	public static io.github.valtairnascimento.campaignforms.model.Submission
			deleteSubmission(HttpPrincipal httpPrincipal, long submissionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "deleteSubmission",
				_deleteSubmissionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, submissionId);

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

			return (io.github.valtairnascimento.campaignforms.model.Submission)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.Submission>
				getCampaignSubmissions(
					HttpPrincipal httpPrincipal, long campaignId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "getCampaignSubmissions",
				_getCampaignSubmissionsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, start, end);

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
				<io.github.valtairnascimento.campaignforms.model.Submission>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCampaignSubmissionsCount(
			HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "getCampaignSubmissionsCount",
				_getCampaignSubmissionsCountParameterTypes2);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.EventDelivery>
				getSubmissionEventDeliveries(
					HttpPrincipal httpPrincipal, long submissionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "getSubmissionEventDeliveries",
				_getSubmissionEventDeliveriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, submissionId);

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
				<io.github.valtairnascimento.campaignforms.model.EventDelivery>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.Submission
			getSubmission(HttpPrincipal httpPrincipal, long submissionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "getSubmission",
				_getSubmissionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, submissionId);

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

			return (io.github.valtairnascimento.campaignforms.model.Submission)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void checkSubmissionsExport(
			HttpPrincipal httpPrincipal, long campaignId, String format)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SubmissionServiceUtil.class, "checkSubmissionsExport",
				_checkSubmissionsExportParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, format);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SubmissionServiceHttp.class);

	private static final Class<?>[] _deleteSubmissionParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _getCampaignSubmissionsParameterTypes1 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[]
		_getCampaignSubmissionsCountParameterTypes2 = new Class[] {long.class};
	private static final Class<?>[]
		_getSubmissionEventDeliveriesParameterTypes3 = new Class[] {long.class};
	private static final Class<?>[] _getSubmissionParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _checkSubmissionsExportParameterTypes5 =
		new Class[] {long.class, String.class};

}