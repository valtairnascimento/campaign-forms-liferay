package io.github.valtairnascimento.campaignforms.web.internal.template;

import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.URLTemplateResource;

import java.io.Writer;

import java.net.URL;

import java.util.Map;

import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(service = FreeMarkerTemplateProcessor.class)
public class FreeMarkerTemplateProcessor {
	public void process(
			String templatePath, Map<String, Object> context, Writer writer)
		throws PortletException {
		URL url = FreeMarkerTemplateProcessor.class.getResource(templatePath);

		if (url == null) {
			throw new PortletException("Template not found: " + templatePath);
		}

		try {
			Template template = TemplateManagerUtil.getTemplate(
				TemplateConstants.LANG_TYPE_FTL,
				new URLTemplateResource(templatePath, url), true);

			template.putAll(context);

			template.processTemplate(writer);
		}
		catch (TemplateException templateException) {
			throw new PortletException(templateException);
		}
	}

}
