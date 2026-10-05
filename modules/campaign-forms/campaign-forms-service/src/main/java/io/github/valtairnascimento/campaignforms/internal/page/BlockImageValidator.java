package io.github.valtairnascimento.campaignforms.internal.page;

import io.github.valtairnascimento.campaignforms.exception.PageBlockConfigurationException;

import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = BlockImageValidator.class)
public class BlockImageValidator {
	public String getProblem(long fileEntryId, long groupId) {
		if (fileEntryId <= 0) {
			return _INVALID;
		}

		try {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			if ((fileEntry.getGroupId() != groupId) ||
				!_MIME_TYPES.contains(
					StringUtil.toLowerCase(fileEntry.getMimeType()))) {
				return _INVALID;
			}

			PermissionChecker guestPermissionChecker =
				_permissionCheckerFactory.create(
					_userLocalService.getGuestUser(fileEntry.getCompanyId()));

			if (!_fileEntryModelResourcePermission.contains(
					guestPermissionChecker, fileEntry, ActionKeys.VIEW)) {
				return _NOT_PUBLIC;
			}

			return null;
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return _INVALID;
		}
	}

	public boolean isValidImage(long fileEntryId, long groupId) {
		if (getProblem(fileEntryId, groupId) == null) {
			return true;
		}

		return false;
	}

	public long readImage(
			JSONObject configuration, JSONObject sanitized, String key,
			long groupId, boolean required)
		throws PageBlockConfigurationException {
		long fileEntryId = 0;

		if ((configuration != null) && configuration.has(key) &&
			!configuration.isNull(key)) {
			fileEntryId = GetterUtil.getLong(
				String.valueOf(configuration.get(key)));
		}

		if (fileEntryId <= 0) {
			if (required) {
				throw new PageBlockConfigurationException(
					"block-field-is-required", key);
			}

			return 0;
		}

		String problem = getProblem(fileEntryId, groupId);

		if (problem != null) {
			throw new PageBlockConfigurationException(problem, key);
		}

		sanitized.put(key, fileEntryId);

		return fileEntryId;
	}

	private static final String _INVALID = "block-image-is-invalid";

	private static final Set<String> _MIME_TYPES = Set.of(
		"image/gif", "image/jpeg", "image/jpg", "image/png", "image/webp");

	private static final String _NOT_PUBLIC = "block-image-is-not-public";

	private static final Log _log = LogFactoryUtil.getLog(
		BlockImageValidator.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileEntry)"
	)
	private ModelResourcePermission<FileEntry>
		_fileEntryModelResourcePermission;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;
}
