package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = BlockImageResolver.class)
public class BlockImageResolver {
	public String getURL(
		Object fileEntryIdObject, long groupId, ThemeDisplay themeDisplay) {
		long fileEntryId = GetterUtil.getLong(
			(fileEntryIdObject == null) ? null :
				String.valueOf(fileEntryIdObject));

		if (fileEntryId <= 0) {
			return null;
		}

		try {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			if (fileEntry.getGroupId() != groupId) {
				return null;
			}

			String url = _dlURLHelper.getPreviewURL(
				fileEntry, fileEntry.getFileVersion(), themeDisplay, "", false,
				false);

			return HttpComponentsUtil.removeParameter(url, "p_auth");
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return null;
		}
	}

	public String getAbsoluteURL(
		long fileEntryId, long groupId, ThemeDisplay themeDisplay) {
		String url = getURL(fileEntryId, groupId, themeDisplay);

		if (Validator.isNull(url) || url.startsWith("http")) {
			return url;
		}

		return themeDisplay.getPortalURL() + url;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlockImageResolver.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLURLHelper _dlURLHelper;
}
