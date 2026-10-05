package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.field.FieldPresetRegistry;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.FieldPreset;
import io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter.FormDTOConverter;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.FieldPresetResource;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/field-preset.properties",
	scope = ServiceScope.PROTOTYPE, service = FieldPresetResource.class
)
public class FieldPresetResourceImpl extends BaseFieldPresetResourceImpl {
	@Override
	public Page<FieldPreset> getFieldPresetsPage() throws Exception {
		return Page.of(
			TransformUtil.transform(
				_fieldPresetRegistry.getFieldPresets(),
				fieldPreset -> _formDTOConverter.toDTO(
					fieldPreset, contextAcceptLanguage.getPreferredLocale())));
	}

	@Reference
	private FieldPresetRegistry _fieldPresetRegistry;

	@Reference
	private FormDTOConverter _formDTOConverter;
}
