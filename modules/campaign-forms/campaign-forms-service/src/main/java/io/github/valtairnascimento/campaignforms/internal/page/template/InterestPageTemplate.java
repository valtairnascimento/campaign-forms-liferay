package io.github.valtairnascimento.campaignforms.internal.page.template;

import io.github.valtairnascimento.campaignforms.page.PageTemplateDefinition;

import org.osgi.service.component.annotations.Component;

@Component(
	property = PageTemplateDefinition.PROPERTY_KEY + "=" + InterestPageTemplate.KEY,
	service = PageTemplateDefinition.class
)
public class InterestPageTemplate extends BuiltInPageTemplate {
	public static final String KEY = "interest";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	protected String[] getBlockTypes() {
		return new String[] {"HERO", "TEXT", "FORM"};
	}

}
