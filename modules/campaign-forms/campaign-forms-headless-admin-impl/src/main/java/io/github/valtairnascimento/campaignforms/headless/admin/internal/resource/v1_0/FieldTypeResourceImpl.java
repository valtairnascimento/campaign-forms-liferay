package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldType;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.FormDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.FieldTypeResource;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/field-type.properties",
	scope = ServiceScope.PROTOTYPE, service = FieldTypeResource.class
)
public class FieldTypeResourceImpl extends BaseFieldTypeResourceImpl {
	@Override
	public Page<FieldType> getFieldTypesPage() throws Exception {
		return Page.of(
			TransformUtil.transform(
				_fieldTypeRegistry.getFieldTypes(), _formDTOConverter::toDTO));
	}

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormDTOConverter _formDTOConverter;
}
