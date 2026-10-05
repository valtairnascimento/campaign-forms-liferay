package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FormField;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Order;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Position;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.FormDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.FormFieldResource;
import io.github.valtairnascimento.campaignforms.service.FormService;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/form-field.properties",
	scope = ServiceScope.PROTOTYPE, service = FormFieldResource.class
)
public class FormFieldResourceImpl extends BaseFormFieldResourceImpl {
	@Override
	public Response deleteFormField(Long formFieldId) throws Exception {
		_formService.deleteFormField(formFieldId);

		return Response.noContent(
		).build();
	}

	@Override
	public FormField getFormField(Long formFieldId) throws Exception {
		return _formDTOConverter.toDTO(_formService.getFormField(formFieldId));
	}

	@Override
	public FormField postCampaignFormField(Long campaignId, FormField formField)
		throws Exception {
		return _formDTOConverter.toDTO(
			_formService.addFormField(
				campaignId, _formDTOConverter.toFormFieldInput(formField)));
	}

	@Override
	public Page<FormField> putCampaignFormFieldsOrder(
			Long campaignId, Order order)
		throws Exception {
		return Page.of(
			_formDTOConverter.toDTOs(
				_formService.reorderFormFields(
					campaignId, ArrayUtil.toArray(order.getIds()))));
	}

	@Override
	public FormField putFormField(Long formFieldId, FormField formField)
		throws Exception {
		return _formDTOConverter.toDTO(
			_formService.updateFormField(
				formFieldId, _formDTOConverter.toFormFieldInput(formField)));
	}

	@Override
	public Page<FormField> putFormFieldPosition(
			Long formFieldId, Position position)
		throws Exception {
		return Page.of(
			_formDTOConverter.toDTOs(
				_formService.moveFormField(
					formFieldId,
					GetterUtil.getInteger(position.getPosition()))));
	}

	@Reference
	private FormDTOConverter _formDTOConverter;

	@Reference
	private FormService _formService;
}
