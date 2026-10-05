package io.github.valtairnascimento.campaignforms.web.internal.display.block;

import com.liferay.portal.kernel.theme.ThemeDisplay;

public class BlockRenderContext {
	public BlockRenderContext(
		long groupId, String namespace, ThemeDisplay themeDisplay) {
		_groupId = groupId;
		_namespace = namespace;
		_themeDisplay = themeDisplay;
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getNamespace() {
		return _namespace;
	}

	public ThemeDisplay getThemeDisplay() {
		return _themeDisplay;
	}

	private final long _groupId;
	private final String _namespace;
	private final ThemeDisplay _themeDisplay;
}
