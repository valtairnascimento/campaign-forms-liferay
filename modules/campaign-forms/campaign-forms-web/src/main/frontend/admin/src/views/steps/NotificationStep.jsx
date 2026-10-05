import {useRef, useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp, useAsync} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {
	ActionsMenu,
	Alert,
	Button,
	Checkbox,
	ConfirmDialog,
	Icon,
	Loading,
	Modal,
	Select,
	TextArea,
	TextInput,
} from '../../components/ui.jsx';

const NOTIFICATION_KEYS = [
	'bcc',
	'bodyTemplate',
	'cc',
	'channel',
	'enabled',
	'eventType',
	'fromName',
	'name',
	'recipients',
	'replyTo',
	'settings',
	'subjectTemplate',
];

export function toNotificationPayload(notification, changes = {}) {
	const merged = {channel: 'EMAIL', ...notification, ...changes};

	const payload = {};

	NOTIFICATION_KEYS.forEach((key) => {
		if (merged[key] !== undefined && merged[key] !== null) {
			payload[key] = merged[key];
		}
	});

	return payload;
}

export function getPlaceholders(fields) {
	return [
		'${campaign.name}',
		'${submission.table}',
		'${submission.date}',
		'${submission.id}',
		'${site.name}',
		'${site.url}',
		...fields
			.filter((field) => field.type !== 'hidden')
			.map((field) => `\${submission.${field.fieldKey}}`),
	];
}

function getTemplates(t, fields) {
	const emailField = fields.find((field) => field.type === 'email');

	const templates = [
		{
			bodyTemplate: t('template-team-body'),
			enabled: true,
			eventType: 'SUBMISSION_CREATED',
			label: t('template-team'),
			name: t('template-team'),
			recipients: '',
			subjectTemplate: t('template-team-subject'),
		},
	];

	if (emailField) {
		templates.push({
			bodyTemplate: t('template-respondent-body'),
			enabled: true,
			eventType: 'SUBMISSION_CREATED',
			label: t('template-respondent'),
			name: t('template-respondent'),
			recipients: `\${submission.${emailField.fieldKey}}`,
			subjectTemplate: t('template-respondent-subject'),
		});
	}

	return templates;
}

function NotificationDialog({fields, notification, onClose, onSave}) {
	const {t} = useApp();

	const [values, setValues] = useState(() => ({
		bcc: notification.bcc || '',
		bodyTemplate: notification.bodyTemplate || '',
		cc: notification.cc || '',
		enabled: notification.enabled !== false,
		eventType: notification.eventType || 'SUBMISSION_CREATED',
		fromName: notification.fromName || '',
		name: notification.name || '',
		recipients: notification.recipients || '',
		replyTo: notification.replyTo || '',
		subjectTemplate: notification.subjectTemplate || '',
	}));

	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const bodyRef = useRef(null);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const insertPlaceholder = (placeholder) => {
		const textarea = bodyRef.current;

		const body = values.bodyTemplate;

		const start = textarea ? textarea.selectionStart : body.length;
		const end = textarea ? textarea.selectionEnd : body.length;

		set('bodyTemplate')(body.slice(0, start) + placeholder + body.slice(end));

		if (textarea) {
			requestAnimationFrame(() => {
				textarea.focus();
				textarea.setSelectionRange(
					start + placeholder.length,
					start + placeholder.length
				);
			});
		}
	};

	const submit = async (event) => {
		event.preventDefault();

		const validationErrors = {};

		if (!values.name.trim()) {
			validationErrors.name = t('field-required');
		}

		if (values.enabled && !values.recipients.trim()) {
			validationErrors.recipients = t('field-required');
		}

		if (values.enabled && !values.subjectTemplate.trim()) {
			validationErrors.subjectTemplate = t('field-required');
		}

		setErrors(validationErrors);

		if (Object.keys(validationErrors).length) {
			return;
		}

		const changes = {};

		Object.keys(values).forEach((key) => {
			const value = values[key];

			changes[key] = typeof value === 'string' ? value.trim() || null : value;
		});

		setBusy(true);
		setError(null);

		try {
			await onSave(toNotificationPayload(notification, changes));

			onClose();
		}
		catch (saveError) {
			setErrors(saveError.fieldErrors || {});
			setError(saveError);
			setBusy(false);
		}
	};

	return (
		<Modal
			footer={
				<>
					<div className="btn-group-item">
						<Button onClick={onClose}>{t('cancel')}</Button>
					</div>
					<div className="btn-group-item">
						<Button busy={busy} displayType="primary" form="cfNotificationForm" type="submit">
							{t('save')}
						</Button>
					</div>
				</>
			}
			onClose={onClose}
			title={notification.id ? t('edit-notification') : t('new-notification')}
		>
			<form id="cfNotificationForm" noValidate onSubmit={submit}>
				{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

				<TextInput
					error={errors.name}
					help={t('notification-name-help')}
					label={t('notification-name')}
					onChange={set('name')}
					required
					value={values.name}
				/>

				<Select
					label={t('notification-event')}
					onChange={set('eventType')}
					options={['SUBMISSION_CREATED', 'CAMPAIGN_LIMIT_REACHED'].map((eventType) => ({
						label: t(`event-${eventType.toLowerCase()}`),
						value: eventType,
					}))}
					value={values.eventType}
				/>

				<Checkbox
					checked={values.enabled}
					label={t('notification-enabled')}
					onChange={set('enabled')}
				/>

				<TextInput
					error={errors.recipients}
					help={t('recipients-help')}
					label={t('recipients')}
					onChange={set('recipients')}
					required={values.enabled}
					value={values.recipients}
				/>

				<TextInput
					error={errors.subjectTemplate}
					label={t('subject')}
					maxLength={255}
					onChange={set('subjectTemplate')}
					required={values.enabled}
					value={values.subjectTemplate}
				/>

				<TextArea
					error={errors.bodyTemplate}
					help={t('body-help')}
					label={t('body')}
					onChange={set('bodyTemplate')}
					ref={bodyRef}
					rows={8}
					value={values.bodyTemplate}
				/>

				<div className="campaign-forms-admin__placeholders">
					<span className="text-secondary small">{t('insert-placeholder')}</span>

					{getPlaceholders(fields).map((placeholder) => (
						<Button
							className="campaign-forms-admin__placeholder"
							key={placeholder}
							onClick={() => insertPlaceholder(placeholder)}
							small
						>
							{placeholder}
						</Button>
					))}
				</div>

				<details className="campaign-forms-admin__more">
					<summary>{t('more-options')}</summary>

					<TextInput
						help={t('from-name-help')}
						label={t('from-name')}
						onChange={set('fromName')}
						value={values.fromName}
					/>
					<TextInput
						label={t('reply-to')}
						onChange={set('replyTo')}
						type="email"
						value={values.replyTo}
					/>
					<TextInput label={t('cc')} onChange={set('cc')} value={values.cc} />
					<TextInput label={t('bcc')} onChange={set('bcc')} value={values.bcc} />
				</details>
			</form>
		</Modal>
	);
}

export default function NotificationStep({campaign, onBack, onNext}) {
	const {api, notify, t} = useApp();

	const canManage = Boolean(
		campaign.permissions && campaign.permissions.MANAGE_NOTIFICATIONS
	);

	const {data, error, loading, setData} = useAsync(
		() =>
			Promise.all([
				canManage ? api.getNotifications(campaign.id) : Promise.resolve([]),
				api.getForm(campaign.id),
			]).then(([notifications, formVersion]) => ({
				fields: formVersion.formFields || [],
				notifications,
			})),
		[campaign.id]
	);

	const [editing, setEditing] = useState(null);
	const [deleting, setDeleting] = useState(null);
	const [actionError, setActionError] = useState(null);
	const [busy, setBusy] = useState(false);

	if (loading && !data) {
		return <Loading />;
	}

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	const reloadNotifications = async () => {
		const notifications = await api.getNotifications(campaign.id);

		setData({...data, notifications});
	};

	const save = async (payload) => {
		if (editing.id) {
			await api.updateNotification(editing.id, payload);
		}
		else {
			await api.addNotification(campaign.id, payload);
		}

		await reloadNotifications();
	};

	const toggle = async (notification) => {
		setBusy(true);
		setActionError(null);

		try {
			await api.updateNotification(
				notification.id,
				toNotificationPayload(notification, {enabled: !notification.enabled})
			);

			await reloadNotifications();
		}
		catch (toggleError) {
			setActionError(toggleError);
		}
		finally {
			setBusy(false);
		}
	};

	const sendTest = async (notification) => {
		setBusy(true);
		setActionError(null);

		try {
			const {recipient} = await api.sendTestNotification(notification.id);

			notify(t('test-email-sent-x', recipient));
		}
		catch (testError) {
			setActionError(testError);
		}
		finally {
			setBusy(false);
		}
	};

	const remove = async () => {
		setBusy(true);
		setActionError(null);

		try {
			await api.deleteNotification(deleting.id);

			await reloadNotifications();
		}
		catch (deleteError) {
			setActionError(deleteError);
		}
		finally {
			setBusy(false);
			setDeleting(null);
		}
	};

	const {fields, notifications} = data;

	return (
		<div className="sheet sheet-lg">
			<h3 className="sheet-title">{t('step-notification')}</h3>

			<p className="text-secondary">{t('notification-description')}</p>

			{!canManage && <Alert displayType="info">{t('notifications-no-permission')}</Alert>}

			{actionError && (
				<Alert displayType="danger" onClose={() => setActionError(null)}>
					{getErrorMessage(t, actionError)}
				</Alert>
			)}

			{canManage && (
				<>
					{notifications.length === 0 && (
						<p className="text-secondary">{t('no-notifications')}</p>
					)}

					<ul aria-busy={busy} className="list-group">
						{notifications.map((notification) => (
							<li className="list-group-item list-group-item-flex" key={notification.id}>
								<div className="autofit-col autofit-col-expand">
									<p className="list-group-title">
										{notification.name}{' '}
										<span
											className={`label ${notification.enabled ? 'label-success' : 'label-secondary'}`}
										>
											<span className="label-item label-item-expand">
												{notification.enabled ? t('enabled') : t('disabled')}
											</span>
										</span>
									</p>
									<p className="list-group-subtext">
										{t(`event-${String(notification.eventType).toLowerCase()}`)}
										{notification.recipients && ` · ${notification.recipients}`}
									</p>
									{notification.subjectTemplate && (
										<p className="list-group-subtext">{notification.subjectTemplate}</p>
									)}
								</div>

								<div className="autofit-col">
									<ActionsMenu
										items={[
											{
												label: t('edit'),
												onClick: () => setEditing(notification),
											},
											{
												label: notification.enabled ? t('disable') : t('enable'),
												onClick: () => toggle(notification),
											},
											{
												label: t('send-test-email'),
												onClick: () => sendTest(notification),
											},
											{
												danger: true,
												label: t('delete'),
												onClick: () => setDeleting(notification),
											},
										]}
										label={t('actions-for-x', notification.name)}
									/>
								</div>
							</li>
						))}
					</ul>

					<div className="campaign-forms-admin__presets">
						<span className="text-secondary">{t('add-email')}</span>

						{getTemplates(t, fields).map((template) => (
							<Button key={template.label} onClick={() => setEditing(template)} small>
								<Icon className="inline-item inline-item-before" symbol="plus" />
								{template.label}
							</Button>
						))}

						<Button onClick={() => setEditing({enabled: true})} small>
							<Icon className="inline-item inline-item-before" symbol="plus" />
							{t('blank-email')}
						</Button>
					</div>
				</>
			)}

			<StepFooter onBack={onBack} onNext={onNext} nextLabel={t('continue')} />

			{editing && (
				<NotificationDialog
					fields={fields}
					notification={editing}
					onClose={() => setEditing(null)}
					onSave={save}
				/>
			)}

			{deleting && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('delete')}
					message={t('delete-notification-confirmation', deleting.name)}
					onCancel={() => setDeleting(null)}
					onConfirm={remove}
					title={t('delete-notification')}
				/>
			)}
		</div>
	);
}
