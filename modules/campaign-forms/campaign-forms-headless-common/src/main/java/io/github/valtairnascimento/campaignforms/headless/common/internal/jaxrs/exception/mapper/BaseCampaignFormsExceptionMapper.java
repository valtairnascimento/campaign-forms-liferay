package io.github.valtairnascimento.campaignforms.headless.common.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Reference;

public abstract class BaseCampaignFormsExceptionMapper<T extends Throwable>
	implements ExceptionMapper<T> {
	public static final String APPLICATION_SELECT =
		"osgi.jaxrs.application.select=(|" +
			"(osgi.jaxrs.name=CampaignForms.Admin)" +
				"(osgi.jaxrs.name=CampaignForms.Delivery))";

	public static final String EXTENSION = "osgi.jaxrs.extension=true";

	public static final int PRIORITY = 1;

	@Override
	public Response toResponse(T throwable) {
		return errorResponseFactory.toResponse(throwable, _getLocale());
	}

	@Reference
	protected ErrorResponseFactory errorResponseFactory;

	@Context
	protected HttpHeaders httpHeaders;

	private Locale _getLocale() {
		if (httpHeaders != null) {
			List<Locale> locales = httpHeaders.getAcceptableLanguages();

			if (!locales.isEmpty()) {
				Locale locale = locales.get(0);

				if (!locale.getLanguage(
					).equals(
						"*"
					)) {
					return locale;
				}
			}
		}

		return LocaleUtil.getSiteDefault();
	}

}
