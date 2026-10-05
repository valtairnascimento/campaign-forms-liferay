package io.github.valtairnascimento.campaignforms.input;

import io.github.valtairnascimento.campaignforms.constants.EventType;
import io.github.valtairnascimento.campaignforms.constants.NotificationChannelType;

public class NotificationConfigInput {
	public String getBcc() {
		return _bcc;
	}

	public String getBodyTemplate() {
		return _bodyTemplate;
	}

	public String getCc() {
		return _cc;
	}

	public NotificationChannelType getChannel() {
		return _channel;
	}

	public EventType getEventType() {
		return _eventType;
	}

	public String getFromName() {
		return _fromName;
	}

	public String getName() {
		return _name;
	}

	public String getRecipients() {
		return _recipients;
	}

	public String getReplyTo() {
		return _replyTo;
	}

	public String getSettings() {
		return _settings;
	}

	public String getSubjectTemplate() {
		return _subjectTemplate;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public NotificationConfigInput setBcc(String bcc) {
		_bcc = bcc;

		return this;
	}

	public NotificationConfigInput setBodyTemplate(String bodyTemplate) {
		_bodyTemplate = bodyTemplate;

		return this;
	}

	public NotificationConfigInput setCc(String cc) {
		_cc = cc;

		return this;
	}

	public NotificationConfigInput setChannel(NotificationChannelType channel) {
		_channel = channel;

		return this;
	}

	public NotificationConfigInput setEnabled(boolean enabled) {
		_enabled = enabled;

		return this;
	}

	public NotificationConfigInput setEventType(EventType eventType) {
		_eventType = eventType;

		return this;
	}

	public NotificationConfigInput setFromName(String fromName) {
		_fromName = fromName;

		return this;
	}

	public NotificationConfigInput setName(String name) {
		_name = name;

		return this;
	}

	public NotificationConfigInput setRecipients(String recipients) {
		_recipients = recipients;

		return this;
	}

	public NotificationConfigInput setReplyTo(String replyTo) {
		_replyTo = replyTo;

		return this;
	}

	public NotificationConfigInput setSettings(String settings) {
		_settings = settings;

		return this;
	}

	public NotificationConfigInput setSubjectTemplate(String subjectTemplate) {
		_subjectTemplate = subjectTemplate;

		return this;
	}

	private String _bcc;
	private String _bodyTemplate;
	private String _cc;
	private NotificationChannelType _channel = NotificationChannelType.EMAIL;
	private boolean _enabled;
	private EventType _eventType = EventType.SUBMISSION_CREATED;
	private String _fromName;
	private String _name;
	private String _recipients;
	private String _replyTo;
	private String _settings;
	private String _subjectTemplate;
}
