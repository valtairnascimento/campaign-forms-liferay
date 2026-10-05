import {useEffect, useState} from 'react';

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
	TextArea,
	TextInput,
} from '../../components/ui.jsx';
import FieldDialog from './FieldDialog.jsx';
import {
	findField,
	isValidConsentURL,
	moveItem,
	toFieldPayload,
	toFormVersionPayload,
} from './fieldModel.js';

function formSettings(formVersion) {
	return {
		consentRequired: Boolean(formVersion.consentRequired),
		consentText: formVersion.consentText || '',
		consentURL: formVersion.consentURL || '',
		consentVersion: formVersion.consentVersion || '',
		submitLabel: formVersion.submitLabel || '',
	};
}

export default function FieldsStep({campaign, onBack, onNext, onReloadCampaign}) {
	const {api, t} = useApp();

	const form = useAsync(
		() =>
			Promise.all([
				api.getForm(campaign.id),
				api.getFieldTypes(),
				api.getFieldPresets(),
			]).then(([formVersion, fieldTypes, fieldPresets]) => ({
				fieldPresets,
				fieldTypes,
				formVersion,
			})),
		[campaign.id]
	);

	const [dialog, setDialog] = useState(null);
	const [deleting, setDeleting] = useState(null);
	const [settings, setSettings] = useState(null);
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const data = form.data;

	useEffect(() => {
		if (data && !settings) {
			setSettings(formSettings(data.formVersion));
		}
	}, [data, settings]);

	if (form.loading && !data) {
		return <Loading />;
	}

	if (form.error) {
		return <Alert displayType="danger">{getErrorMessage(t, form.error)}</Alert>;
	}

	if (!settings) {
		return <Loading />;
	}

	const {fieldPresets, fieldTypes, formVersion} = data;

	const fields = formVersion.formFields || [];

	const canUpdate = Boolean(campaign.permissions && campaign.permissions.UPDATE);

	const usedKeys = fields.map((field) => field.fieldKey);

	const availablePresets = fieldPresets.filter(
		(preset) =>
			!usedKeys.includes(preset.formField && preset.formField.fieldKey) &&
			!fields.some((field) => field.presetKey === preset.key)
	);

	const mutate = async (operation) => {
		let draft = formVersion;

		if (draft.status !== 'DRAFT') {
			draft = await api.createFormDraft(campaign.id);
		}

		await operation(draft);

		const freshFormVersion = await api.getForm(campaign.id);

		form.setData({...data, formVersion: freshFormVersion});

		onReloadCampaign();
	};

	const run = async (operation) => {
		setBusy(true);
		setError(null);

		try {
			await mutate(operation);
		}
		catch (operationError) {
			setError(operationError);
		}
		finally {
			setBusy(false);
		}
	};

	const move = (index, offset) => {
		const fieldKeys = moveItem(usedKeys, index, offset);

		return run((draft) =>
			api.reorderFields(
				campaign.id,
				fieldKeys.map((fieldKey) => findField(draft, fieldKey).id)
			)
		);
	};

	const toggleRequired = (field) =>
		run((draft) => {
			const draftField = findField(draft, field.fieldKey);

			return api.updateField(
				draftField.id,
				toFieldPayload(draftField, {required: !draftField.required})
			);
		});

	const addPreset = (preset) =>
		run(() =>
			api.addField(
				campaign.id,
				toFieldPayload(preset.formField, {presetKey: preset.key})
			)
		);

	const saveField = (payload) =>
		mutate((draft) => {
			if (dialog.field) {
				const draftField = findField(draft, dialog.field.fieldKey);

				return api.updateField(draftField.id, payload);
			}

			return api.addField(campaign.id, payload);
		});

	const deleteField = async () => {
		await run((draft) => api.deleteField(findField(draft, deleting.fieldKey).id));

		setDeleting(null);
	};

	const setSetting = (key) => (value) =>
		setSettings((previous) => ({...previous, [key]: value}));

	const settingsChanged =
		JSON.stringify(settings) !== JSON.stringify(formSettings(formVersion));

	const submit = async (event) => {
		event.preventDefault();

		const consentURL = settings.consentURL.trim();

		if (canUpdate && settings.consentRequired && consentURL && !isValidConsentURL(consentURL)) {
			setErrors({consentURL: t('consent-url-invalid')});

			return;
		}

		setErrors({});

		if (!canUpdate || !settingsChanged) {
			onNext();

			return;
		}

		setBusy(true);
		setError(null);

		try {
			await mutate((draft) =>
				api.updateForm(
					campaign.id,
					toFormVersionPayload(draft, {
						consentRequired: settings.consentRequired,
						consentText: settings.consentText.trim() || null,
						consentURL: consentURL || null,
						consentVersion: settings.consentVersion.trim() || null,
						submitLabel: settings.submitLabel.trim() || null,
					})
				)
			);

			onNext();
		}
		catch (submitError) {
			setErrors(submitError.fieldErrors || {});
			setError(submitError);
			setBusy(false);
		}
	};

	return (
		<form className="sheet sheet-lg" noValidate onSubmit={submit}>
			<h3 className="sheet-title">{t('step-fields')}</h3>

			{formVersion.status !== 'DRAFT' && canUpdate && (
				<Alert displayType="info">{t('published-form-edit-info')}</Alert>
			)}

			{error && (
				<Alert displayType="danger" onClose={() => setError(null)}>
					{getErrorMessage(t, error)}
				</Alert>
			)}

			{fields.length === 0 && (
				<p className="text-secondary">{t('no-fields')}</p>
			)}

			<ol aria-busy={busy} className="list-group campaign-forms-admin__fields">
				{fields.map((field, index) => (
					<li className="list-group-item list-group-item-flex" key={field.fieldKey}>
						<div className="autofit-col autofit-col-expand">
							<p className="list-group-title">
								{field.label}

								{field.required && (
									<span className="label label-secondary campaign-forms-admin__required">
										<span className="label-item label-item-expand">
											{t('required-label')}
										</span>
									</span>
								)}
							</p>
							<p className="list-group-subtext">
								{t(`field-type-${field.type}`)} · {field.fieldKey}
							</p>
						</div>

						{canUpdate && (
							<div className="autofit-col campaign-forms-admin__field-actions">
								<Button
									aria-label={t('move-up-x', field.label)}
									className="btn-monospaced"
									disabled={busy || index === 0}
									displayType="unstyled"
									onClick={() => move(index, -1)}
									small
									title={t('move-up-x', field.label)}
								>
									<Icon symbol="angle-up" />
								</Button>
								<Button
									aria-label={t('move-down-x', field.label)}
									className="btn-monospaced"
									disabled={busy || index === fields.length - 1}
									displayType="unstyled"
									onClick={() => move(index, 1)}
									small
									title={t('move-down-x', field.label)}
								>
									<Icon symbol="angle-down" />
								</Button>

								<ActionsMenu
									items={[
										{
											label: t('edit'),
											onClick: () => setDialog({field}),
										},
										{
											hidden: field.type === 'hidden',
											label: field.required ? t('make-optional') : t('make-required'),
											onClick: () => toggleRequired(field),
										},
										{
											danger: true,
											label: t('delete'),
											onClick: () => setDeleting(field),
										},
									]}
									label={t('actions-for-x', field.label)}
								/>
							</div>
						)}
					</li>
				))}
			</ol>

			{canUpdate && (
				<div className="campaign-forms-admin__add-field">
					{availablePresets.length > 0 && (
						<div className="campaign-forms-admin__presets">
							<span className="text-secondary">{t('ready-fields')}</span>

							{availablePresets.map((preset) => (
								<Button
									disabled={busy}
									key={preset.key}
									onClick={() => addPreset(preset)}
									small
								>
									<Icon className="inline-item inline-item-before" symbol="plus" />
									{preset.label}
								</Button>
							))}
						</div>
					)}

					<Button disabled={busy} displayType="secondary" onClick={() => setDialog({field: null})}>
						<Icon className="inline-item inline-item-before" symbol="plus" />
						{t('custom-field')}
					</Button>
				</div>
			)}

			<h4 className="sheet-subtitle">{t('form-settings')}</h4>

			<fieldset disabled={!canUpdate || busy}>
				<TextInput
					help={t('submit-label-help')}
					label={t('submit-label')}
					maxLength={100}
					onChange={setSetting('submitLabel')}
					value={settings.submitLabel}
				/>

			</fieldset>

			<h4 className="sheet-subtitle">{t('consent-lgpd')}</h4>

			<p className="text-secondary">{t('consent-lgpd-description')}</p>

			<fieldset disabled={!canUpdate || busy}>
				<Checkbox
					checked={settings.consentRequired}
					help={t('consent-required-help')}
					label={t('consent-required')}
					onChange={setSetting('consentRequired')}
				/>

				{settings.consentRequired && (
					<>
						<TextInput
							error={errors.consentURL}
							help={t('consent-url-help')}
							label={t('consent-url')}
							maxLength={2048}
							onChange={setSetting('consentURL')}
							placeholder="https://"
							type="url"
							value={settings.consentURL}
						/>

						<TextArea
							error={errors.consentText}
							help={t('consent-text-help', t('consent-default-text'))}
							label={t('consent-text')}
							onChange={setSetting('consentText')}
							placeholder={t('consent-default-text')}
							rows={3}
							value={settings.consentText}
						/>

						<details className="campaign-forms-admin__more">
							<summary>{t('more-options')}</summary>

							<TextInput
								error={errors.consentVersion}
								help={t('consent-version-help')}
								label={t('consent-version')}
								maxLength={75}
								onChange={setSetting('consentVersion')}
								value={settings.consentVersion}
							/>
						</details>
					</>
				)}
			</fieldset>

			<StepFooter busy={busy} onBack={onBack} submit />

			{dialog && (
				<FieldDialog
					existingKeys={usedKeys}
					field={dialog.field}
					fieldTypes={fieldTypes}
					onClose={() => setDialog(null)}
					onSave={saveField}
				/>
			)}

			{deleting && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('delete')}
					message={t('delete-field-confirmation', deleting.label)}
					onCancel={() => setDeleting(null)}
					onConfirm={deleteField}
					title={t('delete-field')}
				/>
			)}
		</form>
	);
}
