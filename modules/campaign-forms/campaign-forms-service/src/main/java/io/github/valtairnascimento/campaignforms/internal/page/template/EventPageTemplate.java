package io.github.valtairnascimento.campaignforms.internal.page.template;

import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;

import org.osgi.service.component.annotations.Component;

@Component(
	property = PageTemplateDefinition.PROPERTY_KEY + "=" + EventPageTemplate.KEY,
	service = PageTemplateDefinition.class
)
public class EventPageTemplate extends BuiltInPageTemplate {
	public static final String KEY = "event";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	protected String[] getBlockTypes() {
		return new String[] {"HERO", "TEXT", "FORM", "FAQ"};
	}

}
