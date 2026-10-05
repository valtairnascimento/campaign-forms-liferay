package io.github.valtairnascimento.campaignforms.web.internal.display;

import io.github.valtairnascimento.campaignforms.domain.PublicCampaignService;
import io.github.valtairnascimento.campaignforms.domain.PublicCampaignSnapshot;
import io.github.valtairnascimento.campaignforms.exception.NoSuchCampaignException;
import io.github.valtairnascimento.campaignforms.web.internal.display.block.BlockImageResolver;
import io.github.valtairnascimento.campaignforms.web.internal.display.block.BlockRenderContext;
import io.github.valtairnascimento.campaignforms.web.internal.template.FreeMarkerTemplateProcessor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Map;

import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.w3c.dom.Element;

@Component(service = CampaignRenderer.class)
public class CampaignRenderer {
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String slug, boolean preview)
		throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PublicCampaignSnapshot publicCampaignSnapshot = _getSnapshot(
			renderRequest, themeDisplay, slug, preview);

		if (publicCampaignSnapshot != null) {
			_setSEO(publicCampaignSnapshot, renderRequest);
		}

		Map<String, Object> context = _campaignTemplateContextBuilder.build(
			publicCampaignSnapshot, renderResponse.getNamespace(),
			_getSubmissionURL(themeDisplay, slug), themeDisplay.getLocale(),
			new BlockRenderContext(
				themeDisplay.getScopeGroupId(), renderResponse.getNamespace(),
				themeDisplay));

		renderResponse.setContentType("text/html");

		_freeMarkerTemplateProcessor.process(
			_TEMPLATE_PATH, context, renderResponse.getWriter());
	}

	public void renderHeaders(
			HeaderRequest headerRequest, HeaderResponse headerResponse,
			String slug, boolean preview)
		throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)headerRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PublicCampaignSnapshot publicCampaignSnapshot = _getSnapshot(
			headerRequest, themeDisplay, slug, preview);

		if (publicCampaignSnapshot == null) {
			return;
		}

		PublicCampaignSnapshot.Page page = publicCampaignSnapshot.getPage();

		if (page.isNoIndex() || publicCampaignSnapshot.isPreview()) {
			_addMeta(headerResponse, "name", "robots", "noindex, nofollow");
		}

		String description = page.getOgDescription();

		if (Validator.isNull(description)) {
			description = page.getSeoDescription();
		}

		if (Validator.isNull(description)) {
			description = publicCampaignSnapshot.getDescription();
		}

		if (Validator.isNotNull(description)) {
			_addMeta(headerResponse, "property", "og:description", description);
		}

		String imageURL = _blockImageResolver.getAbsoluteURL(
			page.getOgImageFileEntryId(), publicCampaignSnapshot.getGroupId(),
			themeDisplay);

		if (Validator.isNotNull(imageURL)) {
			_addMeta(headerResponse, "property", "og:image", imageURL);
			_addMeta(
				headerResponse, "name", "twitter:card", "summary_large_image");
		}
	}

	private void _addMeta(
		HeaderResponse headerResponse, String attributeName,
		String attributeValue, String content) {
		Element element = headerResponse.createElement("meta");

		element.setAttribute(attributeName, attributeValue);
		element.setAttribute("content", _toMetaText(content));

		headerResponse.addProperty(MimeResponse.MARKUP_HEAD_ELEMENT, element);
	}

	private PublicCampaignSnapshot _getSnapshot(
			PortletRequest portletRequest, ThemeDisplay themeDisplay,
			String slug, boolean preview)
		throws PortletException {
		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			portletRequest);

		String attributeName = _SNAPSHOT_ATTRIBUTE + slug + "_" + preview;

		Object[] holder = (Object[])httpServletRequest.getAttribute(
			attributeName);

		if (holder == null) {
			holder = new Object[] {
				_resolveSnapshot(themeDisplay, slug, preview)
			};

			httpServletRequest.setAttribute(attributeName, holder);
		}

		return (PublicCampaignSnapshot)holder[0];
	}

	private static String _toMetaText(String text) {
		StringBuilder sb = new StringBuilder(text.length());

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c == '"') {
				sb.append(_CLOSING_QUOTE);
			}
			else if ((c == '<') || (c == '>')) {
				continue;
			}
			else if (Character.isWhitespace(c)) {
				sb.append(' ');
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString(
		).trim();
	}

	private String _getSubmissionURL(ThemeDisplay themeDisplay, String slug) {
		return _portal.getPathContext() + "/o/campaign-forms/v1.0/sites/" +
			themeDisplay.getScopeGroupId() + "/campaigns/by-slug/" + slug +
				"/submissions";
	}

	private PublicCampaignSnapshot _resolveSnapshot(
			ThemeDisplay themeDisplay, String slug, boolean preview)
		throws PortletException {
		if (Validator.isNull(slug)) {
			return null;
		}

		try {
			if (preview) {
				return _publicCampaignService.getPreviewSnapshot(
					themeDisplay.getScopeGroupId(), slug,
					themeDisplay.getPermissionChecker());
			}

			return _publicCampaignService.getPublishedSnapshot(
				themeDisplay.getScopeGroupId(), slug);
		}
		catch (NoSuchCampaignException | PrincipalException exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
		catch (PortalException portalException) {
			throw new PortletException(portalException);
		}
	}

	private void _setSEO(
		PublicCampaignSnapshot publicCampaignSnapshot,
		RenderRequest renderRequest) {
		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		PublicCampaignSnapshot.Page page = publicCampaignSnapshot.getPage();

		String title = page.getSeoTitle();

		if (Validator.isNull(title)) {
			title = publicCampaignSnapshot.getName();
		}

		_portal.setPageTitle(title, httpServletRequest);

		String description = page.getSeoDescription();

		if (Validator.isNull(description)) {
			description = publicCampaignSnapshot.getDescription();
		}

		if (Validator.isNotNull(description)) {
			_portal.setPageDescription(description, httpServletRequest);
		}
	}

	private static final char _CLOSING_QUOTE = (char)0x201D;

	private static final String _SNAPSHOT_ATTRIBUTE =
		CampaignRenderer.class.getName() + "_SNAPSHOT_";

	private static final String _TEMPLATE_PATH = "/templates/campaign.ftl";

	private static final Log _log = LogFactoryUtil.getLog(
		CampaignRenderer.class);

	@Reference
	private BlockImageResolver _blockImageResolver;

	@Reference
	private CampaignTemplateContextBuilder _campaignTemplateContextBuilder;

	@Reference
	private FreeMarkerTemplateProcessor _freeMarkerTemplateProcessor;

	@Reference
	private Portal _portal;

	@Reference
	private PublicCampaignService _publicCampaignService;
}
