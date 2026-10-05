package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import com.liferay.portal.vulcan.resource.OpenAPIResource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Valtair Nascimento
 * @generated
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/openapi.properties",
	service = OpenAPIResourceImpl.class
)
@Generated("")
@OpenAPIDefinition(
	info = @Info(description = "Campaign Forms administrative API. Requires authentication (session + CSRF, OAuth2 or Basic) and the campaign permissions (VIEW, UPDATE, PUBLISH, MANAGE_NOTIFICATIONS, MANAGE_INTEGRATIONS, VIEW_SUBMISSIONS, DELETE_SUBMISSIONS, EXPORT_SUBMISSIONS). Errors use the format `{code, message, errorId, errors: [{field, code, message}]}`.", license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"), title = "Campaign Forms Admin", version = "v1.0")
)
@Path("/v1.0")
public class OpenAPIResourceImpl {

	@GET
	@Path("/openapi.{type:json|yaml}")
	@Produces({MediaType.APPLICATION_JSON, "application/yaml"})
	public Response getOpenAPI(
			@Context HttpServletRequest httpServletRequest,
			@PathParam("type") String type, @Context UriInfo uriInfo)
		throws Exception {

		Class<? extends OpenAPIResource> clazz = _openAPIResource.getClass();

		try {
			Method method = clazz.getMethod(
				"getOpenAPI", HttpServletRequest.class, Set.class, String.class,
				UriInfo.class);

			return (Response)method.invoke(
				_openAPIResource, httpServletRequest, _resourceClasses, type,
				uriInfo);
		}
		catch (NoSuchMethodException noSuchMethodException1) {
			try {
				Method method = clazz.getMethod(
					"getOpenAPI", Set.class, String.class, UriInfo.class);

				return (Response)method.invoke(
					_openAPIResource, _resourceClasses, type, uriInfo);
			}
			catch (NoSuchMethodException noSuchMethodException2) {
				return _openAPIResource.getOpenAPI(_resourceClasses, type);
			}
		}
	}

	@Reference
	private OpenAPIResource _openAPIResource;

	private final Set<Class<?>> _resourceClasses = new HashSet<Class<?>>() {
		{
			add(BlockTypeResourceImpl.class);

			add(CampaignResourceImpl.class);

			add(DataFeedResourceImpl.class);

			add(EventDeliveryResourceImpl.class);

			add(FieldPresetResourceImpl.class);

			add(FieldTypeResourceImpl.class);

			add(FormFieldResourceImpl.class);

			add(FormVersionResourceImpl.class);

			add(IntegrationResourceImpl.class);

			add(NotificationResourceImpl.class);

			add(NotificationTestResourceImpl.class);

			add(PageBlockResourceImpl.class);

			add(PageConfigurationResourceImpl.class);

			add(PageTemplateResourceImpl.class);

			add(PublicationIssueResourceImpl.class);

			add(SubmissionResourceImpl.class);

			add(OpenAPIResourceImpl.class);
		}
	};

}