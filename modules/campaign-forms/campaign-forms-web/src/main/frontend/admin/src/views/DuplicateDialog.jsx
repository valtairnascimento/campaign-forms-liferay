import {useState} from 'react';

import {getErrorMessage} from '../api/client.js';
import {useApp, useAsync} from '../AppContext.js';
import {
	Alert,
	Button,
	Checkbox,
	Loading,
	Modal,
	Select,
	TextInput,
} from '../components/ui.jsx';
import {navigate} from '../router.js';
import {fromDateTimeInputValue} from '../util/dates.js';
import {SLUG_PATTERN, slugify} from '../util/text.js';

function getAttributes(campaign) {
	const settings = (campaign && campaign.settings) || {};

	return settings.attributes && typeof settings.attributes === 'object'
		? settings.attributes
		: {};
}

export default function DuplicateDialog({onClose, source: initialSource}) {
	const {api, t} = useApp();

	const sources = useAsync(
		() =>
			initialSource
				? Promise.resolve([initialSource])
				: api.getCampaigns({page: 1, pageSize: 100}).then((page) => page.items || []),
		[]
	);

	const [sourceId, setSourceId] = useState(initialSource ? initialSource.id : '');

	const source =
		(sources.data || []).find((campaign) => String(campaign.id) === String(sourceId)) ||
		null;

	const [values, setValues] = useState({
		copyFields: true,
		copyIntegrations: false,
		copyNotifications: true,
		copyPage: true,
		copySettings: true,
		endDate: '',
		name: '',
		recipients: '',
		slug: '',
		startDate: '',
	});

	const [attributes, setAttributes] = useState({});
	const [slugEdited, setSlugEdited] = useState(false);
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const selectSource = (id) => {
		setSourceId(id);
		setAttributes({});
	};

	const sourceAttributes = getAttributes(source);

	const handleNameChange = (name) => {
		setValues((previous) => ({
			...previous,
			name,
			slug: slugEdited ? previous.slug : slugify(name),
		}));
	};

	const submit = async (event) => {
		event.preventDefault();

		const validationErrors = {};

		if (!source) {
			validationErrors.source = t('field-required');
		}

		if (!values.name.trim()) {
			validationErrors.name = t('field-required');
		}

		if (!SLUG_PATTERN.test(values.slug)) {
			validationErrors.slug = t('slug-invalid');
		}

		setErrors(validationErrors);

		if (Object.keys(validationErrors).length) {
			return;
		}

		setBusy(true);
		setError(null);

		try {
			const campaign = await api.duplicateCampaign(source.id, {
				attributes: {...sourceAttributes, ...attributes},
				copyFields: values.copyFields,
				copyIntegrations: values.copyIntegrations,
				copyNotifications: values.copyNotifications,
				copyPage: values.copyPage,
				copySeo: values.copyPage,
				copySettings: values.copySettings,
				endDate: fromDateTimeInputValue(values.endDate),
				name: values.name.trim(),
				recipients: values.recipients.trim() || null,
				slug: values.slug,
				startDate: fromDateTimeInputValue(values.startDate),
			});

			onClose();

			navigate(`/campaigns/${campaign.id}/info`);
		}
		catch (submitError) {
			setErrors(submitError.fieldErrors || {});

			if (submitError.code === 'DUPLICATE_SLUG') {
				setErrors({slug: getErrorMessage(t, submitError)});
			}
			else {
				setError(submitError);
			}
		}
		finally {
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
						<Button busy={busy} displayType="primary" form="cfDuplicateForm" type="submit">
							{t('duplicate')}
						</Button>
					</div>
				</>
			}
			onClose={onClose}
			title={source ? t('duplicate-x', source.name) : t('duplicate-existing')}
		>
			{sources.loading && <Loading />}

			{sources.error && <Alert displayType="danger">{getErrorMessage(t, sources.error)}</Alert>}

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			{sources.data && (
				<form id="cfDuplicateForm" noValidate onSubmit={submit}>
					{!initialSource && (
						<Select
							error={errors.source}
							label={t('source-campaign')}
							onChange={selectSource}
							options={[
								{label: t('select'), value: ''},
								...sources.data.map((campaign) => ({
									label: campaign.name,
									value: String(campaign.id),
								})),
							]}
							required
							value={String(sourceId)}
						/>
					)}

					<TextInput
						error={errors.name}
						label={t('name')}
						maxLength={150}
						onChange={handleNameChange}
						required
						value={values.name}
					/>

					<TextInput
						error={errors.slug}
						help={t('slug-help')}
						label={t('slug')}
						onChange={(slug) => {
							setSlugEdited(true);
							set('slug')(slug);
						}}
						required
						value={values.slug}
					/>

					<div className="row">
						<div className="col-md-6">
							<TextInput
								label={t('start-date')}
								onChange={set('startDate')}
								type="datetime-local"
								value={values.startDate}
							/>
						</div>
						<div className="col-md-6">
							<TextInput
								label={t('end-date')}
								onChange={set('endDate')}
								type="datetime-local"
								value={values.endDate}
							/>
						</div>
					</div>

					{Object.keys(sourceAttributes).map((key) => (
						<TextInput
							help={t('attribute-help', `\${campaign.${key}}`)}
							key={key}
							label={t('attribute-x', key)}
							onChange={(value) =>
								setAttributes((previous) => ({...previous, [key]: value}))
							}
							value={
								attributes[key] === undefined
									? String(sourceAttributes[key])
									: attributes[key]
							}
						/>
					))}

					<TextInput
						help={t('new-recipients-help')}
						label={t('new-recipients')}
						onChange={set('recipients')}
						value={values.recipients}
					/>

					<fieldset>
						<legend className="h5">{t('what-to-copy')}</legend>

						<Checkbox
							checked={values.copyFields}
							label={t('copy-fields')}
							onChange={set('copyFields')}
						/>
						<Checkbox
							checked={values.copyPage}
							label={t('copy-page')}
							onChange={set('copyPage')}
						/>
						<Checkbox
							checked={values.copyNotifications}
							label={t('copy-notifications')}
							onChange={set('copyNotifications')}
						/>
						<Checkbox
							checked={values.copySettings}
							label={t('copy-settings')}
							onChange={set('copySettings')}
						/>

						{source && source.permissions && source.permissions.MANAGE_INTEGRATIONS && (
							<Checkbox
								checked={values.copyIntegrations}
								help={t('copy-integrations-help')}
								label={t('copy-integrations')}
								onChange={set('copyIntegrations')}
							/>
						)}
					</fieldset>

					<p className="text-secondary small">{t('duplicate-never-copies')}</p>
				</form>
			)}
		</Modal>
	);
}
