package io.github.valtairnascimento.campaignforms.headless.delivery.internal.jaxrs.filter;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"osgi.jaxrs.application.select=" +
			"(osgi.jaxrs.name=CampaignForms.Delivery)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=CampaignForms.SubmissionCreatedStatusFilter"
	},
	service = ContainerResponseFilter.class
)
public class SubmissionCreatedStatusContainerResponseFilter
	implements ContainerResponseFilter {
	@Override
	public void filter(
		ContainerRequestContext containerRequestContext,
		ContainerResponseContext containerResponseContext) {
		if (!HttpMethod.POST.equals(containerRequestContext.getMethod()) ||
			(containerResponseContext.getStatus() !=
				Response.Status.OK.getStatusCode())) {
			return;
		}

		String path = containerRequestContext.getUriInfo(
		).getPath();

		if (path.endsWith("/submissions")) {
			containerResponseContext.setStatus(
				Response.Status.CREATED.getStatusCode());
		}
	}

}
