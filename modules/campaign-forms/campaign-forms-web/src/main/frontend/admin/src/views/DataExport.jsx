import {useRef, useState} from 'react';

import {getErrorMessage} from '../api/client.js';
import {useApp, useAsync} from '../AppContext.js';
import {
	Alert,
	Button,
	Checkbox,
	ConfirmDialog,
	Icon,
	Loading,
	Modal,
	Select,
	TextInput,
} from '../components/ui.jsx';

export function saveFile(blob, fileName) {
	const url = URL.createObjectURL(blob);

	const link = document.createElement('a');

	link.download = fileName || 'submissions.csv';
	link.href = url;

	document.body.appendChild(link);

	link.click();
	link.remove();

	setTimeout(() => URL.revokeObjectURL(url), 1000);
}

export function toExpirationDate(value) {
	if (!value) {
		return null;
	}

	return new Date(`${value}T23:59:59`).toISOString();
}

export function ExportDialog({campaign, onClose}) {
	const {api, notify, t} = useApp();

	const [values, setValues] = useState({
		includeMetadata: true,
		labels: true,
		separator: 'semicolon',
	});

	const [busy, setBusy] = useState(false);
	const [error, setError] = useState(null);

	const set = (key) => (value) =>
		setValues((previous) => ({...previous, [key]: value}));

	const submit = async (event) => {
		event.preventDefault();

		setBusy(true);
		setError(null);

		try {
			const {blob, fileName} = await api.exportSubmissions(campaign.id, values);

			saveFile(blob, fileName);

			notify(t('export-ready'));

			onClose();
		}
		catch (exportError) {
			setError(exportError);
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
						<Button busy={busy} displayType="primary" form="cfExportForm" type="submit">
							<Icon className="inline-item inline-item-before" symbol="download" />
							{t('download-csv')}
						</Button>
					</div>
				</>
			}
			onClose={onClose}
			size="md"
			title={t('export-submissions')}
		>
			<form id="cfExportForm" noValidate onSubmit={submit}>
				{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

				<p className="text-secondary">{t('export-description')}</p>

				<Select
					help={t('separator-help')}
					label={t('separator')}
					onChange={set('separator')}
					options={['semicolon', 'comma', 'tab'].map((separator) => ({
						label: t(`separator-${separator}`),
						value: separator,
					}))}
					value={values.separator}
				/>

				<Checkbox
					checked={values.labels}
					help={t('export-labels-help')}
					label={t('export-labels')}
					onChange={set('labels')}
				/>

				<Checkbox
					checked={values.includeMetadata}
					help={t('export-metadata-help')}
					label={t('export-metadata')}
					onChange={set('includeMetadata')}
				/>
			</form>
		</Modal>
	);
}

function SecretURL({dataFeed, onDone}) {
	const {t} = useApp();

	const [copied, setCopied] = useState(false);

	const inputRef = useRef(null);

	const copy = async () => {
		try {
			await navigator.clipboard.writeText(dataFeed.url);
		}
		catch (error) {
			inputRef.current.select();

			return;
		}

		setCopied(true);
	};

	return (
		<div className="campaign-forms-admin__secret">
			<Alert displayType="warning" title={t('data-feed-url-once')}>
				{t('data-feed-url-once-description')}
			</Alert>

			<div className="form-group">
				<label htmlFor="cfDataFeedURL">{t('data-feed-url-x', dataFeed.name)}</label>

				<div className="input-group">
					<div className="input-group-item input-group-prepend">
						<input
							className="form-control"
							id="cfDataFeedURL"
							onFocus={(event) => event.target.select()}
							readOnly
							ref={inputRef}
							value={dataFeed.url}
						/>
					</div>
					<div className="input-group-append input-group-item input-group-item-shrink">
						<Button onClick={copy}>
							<Icon className="inline-item inline-item-before" symbol="copy" />
							{copied ? t('copied') : t('copy')}
						</Button>
					</div>
				</div>

				<div aria-live="polite" className="sr-only">
					{copied ? t('copied') : ''}
				</div>
			</div>

			<p className="text-secondary small">{t('data-feed-power-bi-help')}</p>

			<Button displayType="primary" onClick={onDone}>
				{t('done')}
			</Button>
		</div>
	);
}

function DataFeedForm({campaign, onCancel, onCreated}) {
	const {api, t} = useApp();

	const hasSeries = Boolean(campaign.seriesKey);

	const [values, setValues] = useState({
		expirationDate: '',
		format: 'CSV',
		includeMetadata: true,
		name: '',
		scope: hasSeries ? 'SERIES' : 'CAMPAIGN',
	});

	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) =>
		setValues((previous) => ({...previous, [key]: value}));

	const submit = async (event) => {
		event.preventDefault();

		if (!values.name.trim()) {
			setErrors({name: t('field-required')});

			return;
		}

		setBusy(true);
		setError(null);
		setErrors({});

		try {
			onCreated(
				await api.addDataFeed(campaign.id, {
					expirationDate: toExpirationDate(values.expirationDate),
					format: values.format,
					includeMetadata: values.includeMetadata,
					name: values.name.trim(),
					scope: values.scope,
				})
			);
		}
		catch (addError) {
			setErrors(addError.fieldErrors || {});
			setError(addError);
			setBusy(false);
		}
	};

	return (
		<form noValidate onSubmit={submit}>
			<h3 className="h5">{t('new-data-feed')}</h3>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			<TextInput
				error={errors.name}
				help={t('data-feed-name-help')}
				label={t('data-feed-name')}
				maxLength={75}
				onChange={set('name')}
				required
				value={values.name}
			/>

			{hasSeries && (
				<Select
					error={errors.scope}
					help={t('data-feed-scope-help')}
					label={t('data-feed-scope')}
					onChange={set('scope')}
					options={[
						{label: t('data-feed-scope-series-x', campaign.seriesKey), value: 'SERIES'},
						{label: t('data-feed-scope-campaign'), value: 'CAMPAIGN'},
					]}
					value={values.scope}
				/>
			)}

			<Select
				help={t('data-feed-format-help')}
				label={t('data-feed-format')}
				onChange={set('format')}
				options={[
					{label: 'CSV', value: 'CSV'},
					{label: 'JSON', value: 'JSON'},
				]}
				value={values.format}
			/>

			<Checkbox
				checked={values.includeMetadata}
				help={t('export-metadata-help')}
				label={t('export-metadata')}
				onChange={set('includeMetadata')}
			/>

			<TextInput
				error={errors.expirationDate}
				help={t('data-feed-expiration-help')}
				label={t('data-feed-expiration')}
				onChange={set('expirationDate')}
				type="date"
				value={values.expirationDate}
			/>

			<div className="btn-group">
				<div className="btn-group-item">
					<Button onClick={onCancel}>{t('cancel')}</Button>
				</div>
				<div className="btn-group-item">
					<Button busy={busy} displayType="primary" type="submit">
						{t('create-url')}
					</Button>
				</div>
			</div>
		</form>
	);
}

export function DataFeedsDialog({campaign, onClose}) {
	const {api, formatDate, notify, t} = useApp();

	const dataFeeds = useAsync(() => api.getDataFeeds(campaign.id), [campaign.id]);

	const [adding, setAdding] = useState(false);
	const [secret, setSecret] = useState(null);
	const [confirming, setConfirming] = useState(null);
	const [busy, setBusy] = useState(false);
	const [error, setError] = useState(null);

	const confirm = async () => {
		const {action, dataFeed} = confirming;

		setBusy(true);
		setError(null);

		try {
			if (action === 'revoke') {
				await api.deleteDataFeed(dataFeed.id);

				notify(t('data-feed-revoked'));
			}
			else {
				setSecret(await api.regenerateDataFeedToken(dataFeed.id));
			}

			dataFeeds.reload();
		}
		catch (confirmError) {
			setError(confirmError);
		}
		finally {
			setBusy(false);
			setConfirming(null);
		}
	};

	const items = (dataFeeds.data || []).filter((dataFeed) => dataFeed.active);

	let content;

	if (secret) {
		content = <SecretURL dataFeed={secret} onDone={() => setSecret(null)} />;
	}
	else if (adding) {
		content = (
			<DataFeedForm
				campaign={campaign}
				onCancel={() => setAdding(false)}
				onCreated={(dataFeed) => {
					setAdding(false);
					setSecret(dataFeed);

					dataFeeds.reload();
				}}
			/>
		);
	}
	else {
		content = (
			<>
				<p className="text-secondary">{t('data-feeds-description')}</p>

				{dataFeeds.error && (
					<Alert displayType="danger">{getErrorMessage(t, dataFeeds.error)}</Alert>
				)}

				{dataFeeds.loading && !dataFeeds.data && <Loading />}

				{dataFeeds.data && !items.length && (
					<p className="text-secondary">{t('no-data-feeds')}</p>
				)}

				{items.length > 0 && (
					<ul className="list-group">
						{items.map((dataFeed) => (
							<li className="list-group-item list-group-item-flex" key={dataFeed.id}>
								<div className="autofit-col autofit-col-expand">
									<p className="list-group-title">{dataFeed.name}</p>
									<p className="list-group-subtext">
										{dataFeed.scope === 'SERIES'
											? t('data-feed-scope-series-x', dataFeed.seriesKey)
											: t('data-feed-scope-campaign')}
										{` · ${dataFeed.format} · ${t('token-ending-x', dataFeed.tokenHint)}`}
									</p>
									<p className="list-group-subtext">
										{dataFeed.lastAccessDate
											? t('last-access-x', formatDate(dataFeed.lastAccessDate))
											: t('never-accessed')}
										{dataFeed.expirationDate &&
											` · ${t('expires-x', formatDate(dataFeed.expirationDate))}`}
									</p>
								</div>

								<div className="autofit-col">
									<div className="btn-group">
										<div className="btn-group-item">
											<Button
												onClick={() => setConfirming({action: 'regenerate', dataFeed})}
												small
											>
												{t('regenerate-token')}
											</Button>
										</div>
										<div className="btn-group-item">
											<Button
												displayType="danger"
												onClick={() => setConfirming({action: 'revoke', dataFeed})}
												small
											>
												{t('revoke')}
											</Button>
										</div>
									</div>
								</div>
							</li>
						))}
					</ul>
				)}

				<Button className="mt-3" onClick={() => setAdding(true)}>
					<Icon className="inline-item inline-item-before" symbol="plus" />
					{t('new-data-feed')}
				</Button>
			</>
		);
	}

	return (
		<>
			<Modal
				footer={
					<div className="btn-group-item">
						<Button onClick={onClose}>{t('close')}</Button>
					</div>
				}
				onClose={onClose}
				title={t('power-bi')}
			>
				{error && (
					<Alert displayType="danger" onClose={() => setError(null)}>
						{getErrorMessage(t, error)}
					</Alert>
				)}

				{content}
			</Modal>

			{confirming && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={
						confirming.action === 'revoke' ? t('revoke') : t('regenerate-token')
					}
					message={t(
						confirming.action === 'revoke'
							? 'revoke-data-feed-confirmation'
							: 'regenerate-token-confirmation',
						confirming.dataFeed.name
					)}
					onCancel={() => setConfirming(null)}
					onConfirm={confirm}
					title={
						confirming.action === 'revoke'
							? t('revoke-data-feed')
							: t('regenerate-token')
					}
				/>
			)}
		</>
	);
}
