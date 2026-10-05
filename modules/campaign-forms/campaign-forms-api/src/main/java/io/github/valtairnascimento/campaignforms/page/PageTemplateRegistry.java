package io.github.valtairnascimento.campaignforms.page;

import java.util.List;

public interface PageTemplateRegistry {
	public PageTemplateDefinition getPageTemplateDefinition(String key);

	public List<PageTemplateDefinition> getPageTemplateDefinitions();
}
