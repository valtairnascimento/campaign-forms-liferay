package io.github.valtairnascimento.campaignforms.headless.admin.internal.resource.v1_0;

import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.PageTemplate;
import io.github.valtairnascimento.campaignforms.headless.admin.resource.v1_0.PageTemplateResource;
import io.github.valtairnascimento.campaignforms.page.BlockContext;
import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;
import io.github.valtairnascimento.campaignforms.page.PageTemplateRegistry;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/page-template.properties",
	scope = ServiceScope.PROTOTYPE, service = PageTemplateResource.class
)
public class PageTemplateResourceImpl extends BasePageTemplateResourceImpl {
	@Override
	public Page<PageTemplate> getPageTemplatesPage() throws Exception {
		Locale locale = contextAcceptLanguage.getPreferredLocale();

		return Page.of(
			TransformUtil.transform(
				_pageTemplateRegistry.getPageTemplateDefinitions(),
				pageTemplateDefinition -> {
					PageTemplate dto = new PageTemplate();

					dto.setBlockTypes(
						TransformUtil.transformToArray(
							pageTemplateDefinition.getBlocks(
								new BlockContext(
									contextCompany.getCompanyId(), 0,
									LocaleUtil.getSiteDefault(), "")),
							PageTemplateDefinition.TemplateBlock::getBlockType,
							String.class));
					dto.setDescription(
						pageTemplateDefinition.getDescription(locale));
					dto.setKey(pageTemplateDefinition.getKey());
					dto.setLabel(pageTemplateDefinition.getLabel(locale));

					return dto;
				}));
	}

	@Reference
	private PageTemplateRegistry _pageTemplateRegistry;
}
