package io.github.valtairnascimento.campaignforms.domain;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DuplicationRequest {
	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(_attributes);
	}

	public Date getEndDate() {
		return _endDate;
	}

	public String getInstanceKey() {
		return _instanceKey;
	}

	public String getName() {
		return _name;
	}

	public String getRecipients() {
		return _recipients;
	}

	public String getSlug() {
		return _slug;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public String getSubjectTemplate() {
		return _subjectTemplate;
	}

	public long getTargetGroupId() {
		return _targetGroupId;
	}

	public boolean isCopyFields() {
		return _copyFields;
	}

	public boolean isCopyIntegrations() {
		return _copyIntegrations;
	}

	public boolean isCopyIntegrationSecrets() {
		return _copyIntegrationSecrets;
	}

	public boolean isCopyNotifications() {
		return _copyNotifications;
	}

	public boolean isCopyPage() {
		return _copyPage;
	}

	public boolean isCopySeo() {
		return _copySeo;
	}

	public boolean isCopySettings() {
		return _copySettings;
	}

	public DuplicationRequest putAttribute(String key, String value) {
		_attributes.put(key, value);

		return this;
	}

	public DuplicationRequest setCopyFields(boolean copyFields) {
		_copyFields = copyFields;

		return this;
	}

	public DuplicationRequest setCopyIntegrations(boolean copyIntegrations) {
		_copyIntegrations = copyIntegrations;

		return this;
	}

	public DuplicationRequest setCopyIntegrationSecrets(
		boolean copyIntegrationSecrets) {
		_copyIntegrationSecrets = copyIntegrationSecrets;

		return this;
	}

	public DuplicationRequest setCopyNotifications(boolean copyNotifications) {
		_copyNotifications = copyNotifications;

		return this;
	}

	public DuplicationRequest setCopyPage(boolean copyPage) {
		_copyPage = copyPage;

		return this;
	}

	public DuplicationRequest setCopySeo(boolean copySeo) {
		_copySeo = copySeo;

		return this;
	}

	public DuplicationRequest setCopySettings(boolean copySettings) {
		_copySettings = copySettings;

		return this;
	}

	public DuplicationRequest setDates(Date startDate, Date endDate) {
		_startDate = startDate;
		_endDate = endDate;

		return this;
	}

	public DuplicationRequest setInstanceKey(String instanceKey) {
		_instanceKey = instanceKey;

		return this;
	}

	public DuplicationRequest setName(String name) {
		_name = name;

		return this;
	}

	public DuplicationRequest setNotificationOverrides(
		String recipients, String subjectTemplate) {
		_recipients = recipients;
		_subjectTemplate = subjectTemplate;

		return this;
	}

	public DuplicationRequest setSlug(String slug) {
		_slug = slug;

		return this;
	}

	public DuplicationRequest setTargetGroupId(long targetGroupId) {
		_targetGroupId = targetGroupId;

		return this;
	}

	private final Map<String, String> _attributes = new LinkedHashMap<>();
	private boolean _copyFields = true;
	private boolean _copyIntegrations;
	private boolean _copyIntegrationSecrets;
	private boolean _copyNotifications = true;
	private boolean _copyPage = true;
	private boolean _copySeo = true;
	private boolean _copySettings = true;
	private Date _endDate;
	private String _instanceKey;
	private String _name;
	private String _recipients;
	private String _slug;
	private Date _startDate;
	private String _subjectTemplate;
	private long _targetGroupId;
}
