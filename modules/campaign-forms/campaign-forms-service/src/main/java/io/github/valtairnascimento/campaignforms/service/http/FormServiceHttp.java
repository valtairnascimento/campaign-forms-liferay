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

import io.github.valtairnascimento.campaignforms.service.FormServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>FormServiceUtil</code> service
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
public class FormServiceHttp {

	public static io.github.valtairnascimento.campaignforms.model.FormField
			addFormField(
				HttpPrincipal httpPrincipal, long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "addFormField",
				_addFormFieldParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, formFieldInput);

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

			return (io.github.valtairnascimento.campaignforms.model.FormField)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			deleteFormField(HttpPrincipal httpPrincipal, long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "deleteFormField",
				_deleteFormFieldParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formFieldId);

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

			return (io.github.valtairnascimento.campaignforms.model.FormField)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getCampaignFormVersion(HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getCampaignFormVersion",
				_getCampaignFormVersionParameterTypes2);

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

			return (io.github.valtairnascimento.campaignforms.model.FormVersion)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormVersion>
				getCampaignFormVersions(
					HttpPrincipal httpPrincipal, long campaignId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getCampaignFormVersions",
				_getCampaignFormVersionsParameterTypes3);

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
				<io.github.valtairnascimento.campaignforms.model.FormVersion>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			getFormField(HttpPrincipal httpPrincipal, long formFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getFormField",
				_getFormFieldParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formFieldId);

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

			return (io.github.valtairnascimento.campaignforms.model.FormField)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				getFormFields(HttpPrincipal httpPrincipal, long formVersionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getFormFields",
				_getFormFieldsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formVersionId);

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
				<io.github.valtairnascimento.campaignforms.model.FormField>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getFormVersion(HttpPrincipal httpPrincipal, long formVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getFormVersion",
				_getFormVersionParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formVersionId);

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

			return (io.github.valtairnascimento.campaignforms.model.FormVersion)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			getOrCreateDraftFormVersion(
				HttpPrincipal httpPrincipal, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "getOrCreateDraftFormVersion",
				_getOrCreateDraftFormVersionParameterTypes7);

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

			return (io.github.valtairnascimento.campaignforms.model.FormVersion)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				moveFormField(
					HttpPrincipal httpPrincipal, long formFieldId, int position)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "moveFormField",
				_moveFormFieldParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formFieldId, position);

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
				<io.github.valtairnascimento.campaignforms.model.FormField>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<io.github.valtairnascimento.campaignforms.model.FormField>
				reorderFormFields(
					HttpPrincipal httpPrincipal, long campaignId,
					long[] formFieldIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "reorderFormFields",
				_reorderFormFieldsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, formFieldIds);

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
				<io.github.valtairnascimento.campaignforms.model.FormField>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormVersion
			updateDraftFormVersion(
				HttpPrincipal httpPrincipal, long campaignId,
				io.github.valtairnascimento.campaignforms.input.FormVersionInput
					formVersionInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "updateDraftFormVersion",
				_updateDraftFormVersionParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, campaignId, formVersionInput);

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

			return (io.github.valtairnascimento.campaignforms.model.FormVersion)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static io.github.valtairnascimento.campaignforms.model.FormField
			updateFormField(
				HttpPrincipal httpPrincipal, long formFieldId,
				io.github.valtairnascimento.campaignforms.input.FormFieldInput
					formFieldInput)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FormServiceUtil.class, "updateFormField",
				_updateFormFieldParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, formFieldId, formFieldInput);

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

			return (io.github.valtairnascimento.campaignforms.model.FormField)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FormServiceHttp.class);

	private static final Class<?>[] _addFormFieldParameterTypes0 = new Class[] {
		long.class,
		io.github.valtairnascimento.campaignforms.input.FormFieldInput.class
	};
	private static final Class<?>[] _deleteFormFieldParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getCampaignFormVersionParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getCampaignFormVersionsParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getFormFieldParameterTypes4 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getFormFieldsParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[] _getFormVersionParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getOrCreateDraftFormVersionParameterTypes7 = new Class[] {long.class};
	private static final Class<?>[] _moveFormFieldParameterTypes8 =
		new Class[] {long.class, int.class};
	private static final Class<?>[] _reorderFormFieldsParameterTypes9 =
		new Class[] {long.class, long[].class};
	private static final Class<?>[] _updateDraftFormVersionParameterTypes10 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.FormVersionInput.
				class
		};
	private static final Class<?>[] _updateFormFieldParameterTypes11 =
		new Class[] {
			long.class,
			io.github.valtairnascimento.campaignforms.input.FormFieldInput.class
		};

}