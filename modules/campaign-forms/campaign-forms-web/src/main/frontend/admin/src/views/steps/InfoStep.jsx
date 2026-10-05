import {useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp} from '../../AppContext.js';
import {Alert, TextArea, TextInput} from '../../components/ui.jsx';
import {fromDateTimeInputValue, toDateTimeInputValue} from '../../util/dates.js';
import {SLUG_PATTERN, slugify} from '../../util/text.js';
import StepFooter from '../../components/StepFooter.jsx';

const WRITABLE_KEYS = [
	'defaultLanguageId',
	'description',
	'endDate',
	'externalReferenceCode',
	'instanceKey',
	'name',
	'retentionDays',
	'seriesKey',
	'settings',
	'slug',
	'startDate',
	'submissionLimit',
];

export function toCampaignPayload(campaign, changes) {
	const payload = {};

	WRITABLE_KEYS.forEach((key) => {
		if (campaign && campaign[key] !== undefined && campaign[key] !== null) {
			payload[key] = campaign[key];
		}
	});

	return {...payload, ...changes};
}

function initialValues(campaign) {
	const settings = (campaign && campaign.settings) || {};

	return {
		closedMessage: settings.closedMessage || '',
		description: (campaign && campaign.description) || '',
		endDate: toDateTimeInputValue(campaign && campaign.endDate),
		limitReachedMessage: settings.limitReachedMessage || '',
		name: (campaign && campaign.name) || '',
		slug: (campaign && campaign.slug) || '',
		startDate: toDateTimeInputValue(campaign && campaign.startDate),
		submissionLimit:
			campaign && campaign.submissionLimit ? String(campaign.submissionLimit) : '',
	};
}

export function validateInfo(values, t) {
	const errors = {};

	if (!values.name.trim()) {
		errors.name = t('field-required');
	}

	if (!values.slug) {
		errors.slug = t('field-required');
	}
	else if (!SLUG_PATTERN.test(values.slug)) {
		errors.slug = t('slug-invalid');
	}

	if (values.startDate && values.endDate && values.endDate <= values.startDate) {
		errors.endDate = t('end-date-before-start-date');
	}

	if (values.submissionLimit && !/^\d+$/.test(values.submissionLimit)) {
		errors.submissionLimit = t('submission-limit-invalid');
	}

	return errors;
}

export default function InfoStep({campaign, mode, onNext, onSaved}) {
	const {api, config, t} = useApp();

	const [values, setValues] = useState(() => initialValues(campaign));
	const [slugEdited, setSlugEdited] = useState(Boolean(campaign));
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const readOnly = Boolean(campaign && campaign.permissions && !campaign.permissions.UPDATE);

	const handleNameChange = (name) => {
		setValues((previous) => ({
			...previous,
			name,
			slug: slugEdited ? previous.slug : slugify(name),
		}));
	};

	const submit = async (event) => {
		event.preventDefault();

		const validationErrors = validateInfo(values, t);

		setErrors(validationErrors);

		if (Object.keys(validationErrors).length) {
			return;
		}

		const settings = {...((campaign && campaign.settings) || {})};

		['closedMessage', 'limitReachedMessage'].forEach((key) => {
			if (values[key].trim()) {
				settings[key] = values[key].trim();
			}
			else {
				delete settings[key];
			}
		});

		const changes = {
			description: values.description.trim() || null,
			endDate: fromDateTimeInputValue(values.endDate),
			name: values.name.trim(),
			settings,
			slug: values.slug,
			startDate: fromDateTimeInputValue(values.startDate),
			submissionLimit: values.submissionLimit ? Number(values.submissionLimit) : 0,
		};

		setBusy(true);
		setError(null);

		try {
			const savedCampaign = campaign
				? await api.updateCampaign(campaign.id, toCampaignPayload(campaign, changes))
				: await api.addCampaign({
						...changes,
						defaultLanguageId: config.languageId,
						mode,
					});

			onSaved(savedCampaign);
		}
		catch (submitError) {
			const fieldErrors = {...submitError.fieldErrors};

			if (submitError.code === 'DUPLICATE_SLUG') {
				fieldErrors.slug = getErrorMessage(t, submitError);
			}

			setErrors(fieldErrors);

			if (!Object.keys(fieldErrors).length) {
				setError(submitError);
			}
		}
		finally {
			setBusy(false);
		}
	};

	return (
		<form className="sheet sheet-lg" noValidate onSubmit={submit}>
			<h3 className="sheet-title">{t('step-info')}</h3>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			{readOnly && <Alert displayType="info">{t('read-only-campaign')}</Alert>}

			<fieldset disabled={readOnly || busy}>
				<TextInput
					error={errors.name}
					help={t('name-help')}
					label={t('name')}
					maxLength={150}
					onChange={handleNameChange}
					required
					value={values.name}
				/>

				<TextInput
					error={errors.slug}
					help={
						config.publicURL
							? t('slug-help-url', config.publicURL + (values.slug || '…'))
							: t('slug-help')
					}
					label={t('slug')}
					maxLength={100}
					onChange={(slug) => {
						setSlugEdited(true);
						set('slug')(slug.toLowerCase());
					}}
					required
					value={values.slug}
				/>

				<TextArea
					label={t('description')}
					maxLength={4000}
					onChange={set('description')}
					value={values.description}
				/>

				<h4 className="sheet-subtitle">{t('period-and-limit')}</h4>

				<div className="row">
					<div className="col-md-6">
						<TextInput
							error={errors.startDate}
							help={t('start-date-help')}
							label={t('start-date')}
							onChange={set('startDate')}
							type="datetime-local"
							value={values.startDate}
						/>
					</div>
					<div className="col-md-6">
						<TextInput
							error={errors.endDate}
							help={t('end-date-help')}
							label={t('end-date')}
							onChange={set('endDate')}
							type="datetime-local"
							value={values.endDate}
						/>
					</div>
				</div>

				<TextInput
					error={errors.submissionLimit}
					help={t('submission-limit-help')}
					inputMode="numeric"
					label={t('submission-limit')}
					min={0}
					onChange={set('submissionLimit')}
					type="number"
					value={values.submissionLimit}
				/>

				<TextArea
					help={t('closed-message-help')}
					label={t('closed-message')}
					onChange={set('closedMessage')}
					rows={2}
					value={values.closedMessage}
				/>

				{values.submissionLimit && values.submissionLimit !== '0' && (
					<TextArea
						help={t('limit-reached-message-help')}
						label={t('limit-reached-message')}
						onChange={set('limitReachedMessage')}
						rows={2}
						value={values.limitReachedMessage}
					/>
				)}
			</fieldset>

			<StepFooter
				busy={busy}
				nextLabel={campaign ? t('save-and-continue') : t('create-and-continue')}
				onNext={readOnly ? onNext : undefined}
				submit={!readOnly}
			/>
		</form>
	);
}
