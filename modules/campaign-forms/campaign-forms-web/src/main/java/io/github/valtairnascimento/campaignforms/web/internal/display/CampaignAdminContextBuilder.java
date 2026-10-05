package io.github.valtairnascimento.campaignforms.web.internal.display;

import io.github.valtairnascimento.campaignforms.constants.CampaignActionKeys;
import io.github.valtairnascimento.campaignforms.constants.CampaignConstants;
import io.github.valtairnascimento.campaignforms.web.constants.CampaignPortletKeys;
import io.github.valtairnascimento.campaignforms.web.internal.portlet.route.CampaignRendererFriendlyURLMapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CampaignAdminContextBuilder.class)
public class CampaignAdminContextBuilder {
	public Map<String, Object> build(
		RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, CampaignAdminContextBuilder.class);

		Group group = themeDisplay.getScopeGroup();

		JSONObject configJSONObject = JSONUtil.put(
			"apiURL", _portal.getPathContext() + "/o/campaign-forms-admin/v1.0"
		).put(
			"deliveryAPIURL",
			_portal.getPathContext() + "/o/headless-delivery/v1.0"
		).put(
			"languageId", themeDisplay.getLanguageId()
		).put(
			"locale", LocaleUtil.toW3cLanguageId(locale)
		).put(
			"messages", _getMessages(resourceBundle)
		).put(
			"permissions",
			JSONUtil.put(
				CampaignActionKeys.ADD_CAMPAIGN,
				_contains(
					themeDisplay.getPermissionChecker(), group.getGroupId(),
					CampaignActionKeys.ADD_CAMPAIGN))
		).put(
			"publicURL", _getPublicURL(themeDisplay)
		).put(
			"siteId", group.getGroupId()
		).put(
			"spritemap", themeDisplay.getPathThemeSpritemap()
		).put(
			"timeZone",
			themeDisplay.getTimeZone(
			).getID()
		);

		return HashMapBuilder.<String, Object>put(
			"config", configJSONObject.toString()
		).put(
			"loadingLabel",
			_language.get(resourceBundle, "campaign-admin.loading")
		).put(
			"noscript",
			_language.get(resourceBundle, "campaign-admin.noscript")
		).put(
			"rootId", renderResponse.getNamespace() + "campaignAdmin"
		).put(
			"scriptURL",
			_portal.getPathContext() + "/o/campaign-forms-web/admin" +
				"/campaign-admin.js?v=" + _bundleLastModified
		).build();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleLastModified = bundleContext.getBundle(
		).getLastModified();
	}

	private boolean _contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {
		try {
			return _portletResourcePermission.contains(
				permissionChecker, groupId, actionId);
		}
		catch (Exception exception) {
			_log.error(exception);

			return false;
		}
	}

	private JSONObject _getMessages(ResourceBundle resourceBundle) {
		JSONObject messagesJSONObject = _jsonFactory.createJSONObject();

		Enumeration<String> keys = resourceBundle.getKeys();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();

			if (key.startsWith(_MESSAGES_PREFIX)) {
				messagesJSONObject.put(
					key.substring(_MESSAGES_PREFIX.length()),
					resourceBundle.getString(key));
			}
		}

		return messagesJSONObject;
	}

	private String _getPublicURL(ThemeDisplay themeDisplay) {
		try {
			long plid = _portal.getPlidFromPortletId(
				themeDisplay.getScopeGroupId(), false,
				CampaignPortletKeys.CAMPAIGN_RENDERER);

			if (plid == LayoutConstants.DEFAULT_PLID) {
				return null;
			}

			Layout layout = _layoutLocalService.fetchLayout(plid);

			if (layout == null) {
				return null;
			}

			return _portal.getLayoutFullURL(layout, themeDisplay) + "/-/" +
				CampaignRendererFriendlyURLMapper.MAPPING + "/";
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return null;
		}
	}

	private static final String _MESSAGES_PREFIX = "campaign-admin.";

	private static final Log _log = LogFactoryUtil.getLog(
		CampaignAdminContextBuilder.class);

	private volatile long _bundleLastModified;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + CampaignConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;
}
