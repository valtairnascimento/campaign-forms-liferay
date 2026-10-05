import {useState} from 'react';

import {getErrorMessage} from '../api/client.js';
import {useApp, useAsync} from '../AppContext.js';
import {
	ActionsMenu,
	Alert,
	Button,
	ConfirmDialog,
	EmptyState,
	Icon,
	Loading,
	Modal,
	Pagination,
} from '../components/ui.jsx';
import {navigate} from '../router.js';
import {DataFeedsDialog, ExportDialog, saveFile} from './DataExport.jsx';

const PAGE_SIZE = 20;

const VISIBLE_COLUMNS = 4;

export function formatValue(value, field, t) {
	if (value === null || value === undefined || value === '') {
		return '';
	}

	const labels = {};

	((field && field.fieldOptions) || []).forEach((option) => {
		labels[option.value] = option.label;
	});

	const format = (item) => {
		if (typeof item === 'boolean') {
			return item ? t('yes') : t('no');
		}

		return labels[item] === undefined ? String(item) : labels[item];
	};

	return Array.isArray(value) ? value.map(format).join(', ') : format(value);
}

const DELIVERY_LABELS = {
	DEAD: 'danger',
	FAILED: 'warning',
	PENDING: 'secondary',
	SUCCESS: 'success',
};

function Deliveries({submissionId}) {
	const {api, formatDate, t} = useApp();

	const {data, error} = useAsync(
		() => api.getSubmissionDeliveries(submissionId),
		[submissionId]
	);

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	if (!data) {
		return <Loading />;
	}

	if (!data.length) {
		return <p className="text-secondary">{t('no-deliveries')}</p>;
	}

	return (
		<ul className="list-unstyled campaign-forms-admin__deliveries">
			{data.map((delivery) => (
				<li key={delivery.id}>
					<span className={`label label-${DELIVERY_LABELS[delivery.status] || 'secondary'}`}>
						<span className="label-item label-item-expand">
							{t(`delivery-${String(delivery.status).toLowerCase()}`)}
						</span>
					</span>{' '}
					{delivery.targetName || t(`target-${String(delivery.targetType).toLowerCase()}`)}
					<span className="text-secondary small">
						{' · '}
						{formatDate(delivery.dateModified)}
						{delivery.attempts > 1 && ` · ${t('attempts-x', delivery.attempts)}`}
					</span>

					{delivery.lastErrorMessage && delivery.status !== 'SUCCESS' && (
						<div className="text-secondary small">{delivery.lastErrorMessage}</div>
					)}
				</li>
			))}
		</ul>
	);
}

function SubmissionDialog({fields, onClose, submission}) {
	const {formatDate, t} = useApp();

	const fieldKeys = fields.map((field) => field.fieldKey);

	const extraKeys = Object.keys(submission.values || {}).filter(
		(key) => !fieldKeys.includes(key)
	);

	const metadata = [
		['submission-date', formatDate(submission.dateCreated)],
		['form-version', submission.formVersion],
		['language', submission.languageId],
		['source-url', submission.sourceUrl],
		['referrer', submission.referrer],
		['utm-source', submission.utmSource],
		['utm-medium', submission.utmMedium],
		['utm-campaign', submission.utmCampaign],
		['utm-term', submission.utmTerm],
		['utm-content', submission.utmContent],
		['consent-date', formatDate(submission.consentDate)],
		['consent-version', submission.consentVersion],
	].filter(([, value]) => value !== null && value !== undefined && value !== '');

	return (
		<Modal
			footer={
				<div className="btn-group-item">
					<Button onClick={onClose}>{t('close')}</Button>
				</div>
			}
			onClose={onClose}
			title={t('submission-x', submission.id)}
		>
			<dl className="campaign-forms-admin__values">
				{fields.map((field) => (
					<div key={field.fieldKey}>
						<dt>{field.label}</dt>
						<dd>{formatValue(submission.values[field.fieldKey], field, t) || '—'}</dd>
					</div>
				))}

				{extraKeys.map((key) => (
					<div key={key}>
						<dt>{key}</dt>
						<dd>{formatValue(submission.values[key], null, t)}</dd>
					</div>
				))}
			</dl>

			<h3 className="h5">{t('details')}</h3>

			<dl className="campaign-forms-admin__values">
				{metadata.map(([key, value]) => (
					<div key={key}>
						<dt>{t(key)}</dt>
						<dd>{String(value)}</dd>
					</div>
				))}
			</dl>

			<h3 className="h5">{t('deliveries')}</h3>

			<Deliveries submissionId={submission.id} />
		</Modal>
	);
}

export default function Results({campaignId}) {
	const {api, formatDate, notify, t} = useApp();

	const [page, setPage] = useState(1);
	const [viewing, setViewing] = useState(null);
	const [deleting, setDeleting] = useState(null);
	const [busy, setBusy] = useState(false);
	const [exporting, setExporting] = useState(false);
	const [downloadingExcel, setDownloadingExcel] = useState(false);
	const [feeds, setFeeds] = useState(false);

	const context = useAsync(
		() =>
			Promise.all([api.getCampaign(campaignId), api.getForm(campaignId)]).then(
				([campaign, formVersion]) => ({
					campaign,
					fields: (formVersion.formFields || []).filter(
						(field) => field.type !== 'hidden'
					),
				})
			),
		[campaignId]
	);

	const submissions = useAsync(
		() => api.getSubmissions(campaignId, {page, pageSize: PAGE_SIZE}),
		[campaignId, page]
	);

	const error = context.error || submissions.error;

	const downloadExcel = async () => {
		setDownloadingExcel(true);

		try {
			const {blob, fileName} = await api.exportSubmissions(campaignId, {
				format: 'xlsx',
			});

			saveFile(blob, fileName || 'submissions.xlsx');

			notify(t('excel-ready'));
		}
		catch (downloadError) {
			notify(getErrorMessage(t, downloadError), 'danger');
		}
		finally {
			setDownloadingExcel(false);
		}
	};

	const remove = async () => {
		setBusy(true);

		try {
			await api.deleteSubmission(deleting.id);

			notify(t('submission-deleted'));

			submissions.reload();
		}
		catch (deleteError) {
			notify(getErrorMessage(t, deleteError), 'danger');
		}
		finally {
			setBusy(false);
			setDeleting(null);
		}
	};

	const campaign = context.data && context.data.campaign;
	const fields = (context.data && context.data.fields) || [];
	const columns = fields.slice(0, VISIBLE_COLUMNS);
	const items = (submissions.data && submissions.data.items) || [];

	const canDelete = Boolean(
		campaign && campaign.permissions && campaign.permissions.DELETE_SUBMISSIONS
	);

	const canExport = Boolean(
		campaign && campaign.permissions && campaign.permissions.EXPORT_SUBMISSIONS
	);

	return (
		<>
			<div className="campaign-forms-admin__toolbar">
				<Button
					aria-label={t('back-to-campaigns')}
					className="btn-monospaced"
					displayType="unstyled"
					onClick={() => navigate('/')}
					title={t('back-to-campaigns')}
				>
					<Icon symbol="angle-left" />
				</Button>

				<div className="campaign-forms-admin__heading">
					<h2 className="campaign-forms-admin__title">{t('results')}</h2>

					{campaign && (
						<div className="campaign-forms-admin__subtitle">{campaign.name}</div>
					)}
				</div>

				{canExport && (
					<div className="campaign-forms-admin__toolbar-actions">
						<Button onClick={() => setFeeds(true)}>
							<Icon className="inline-item inline-item-before" symbol="link" />
							{t('power-bi')}
						</Button>
						<Button disabled={!items.length} onClick={() => setExporting(true)}>
							{t('export-csv')}
						</Button>
						<Button
							busy={downloadingExcel}
							disabled={!items.length}
							displayType="primary"
							onClick={downloadExcel}
							title={t('download-excel-description')}
						>
							<Icon className="inline-item inline-item-before" symbol="download" />
							{t('download-excel')}
						</Button>
					</div>
				)}
			</div>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			{(context.loading || submissions.loading) && !submissions.data && <Loading />}

			{submissions.data && context.data && !items.length && (
				<EmptyState
					description={t('no-submissions-description')}
					title={t('no-submissions')}
				/>
			)}

			{items.length > 0 && context.data && (
				<div className="table-responsive">
					<table className="table table-autofit table-hover table-list">
						<thead>
							<tr>
								<th>{t('submission-date')}</th>

								{columns.map((field) => (
									<th className="table-cell-expand-smaller" key={field.fieldKey}>
										{field.label}
									</th>
								))}

								<th>{t('utm-source')}</th>
								<th>
									<span className="sr-only">{t('actions')}</span>
								</th>
							</tr>
						</thead>

						<tbody>
							{items.map((submission) => (
								<tr key={submission.id}>
									<td>
										<button
											className="btn btn-link btn-sm p-0"
											onClick={() => setViewing(submission)}
											type="button"
										>
											{formatDate(submission.dateCreated)}
										</button>
									</td>

									{columns.map((field) => (
										<td className="table-cell-expand-smaller text-truncate" key={field.fieldKey}>
											{formatValue((submission.values || {})[field.fieldKey], field, t)}
										</td>
									))}

									<td>{submission.utmSource || ''}</td>
									<td>
										<ActionsMenu
											items={[
												{label: t('view'), onClick: () => setViewing(submission)},
												{
													danger: true,
													hidden: !canDelete,
													label: t('delete'),
													onClick: () => setDeleting(submission),
												},
											]}
											label={t('actions-for-x', t('submission-x', submission.id))}
										/>
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			)}

			{submissions.data && (
				<Pagination
					onChange={setPage}
					page={page}
					pageSize={PAGE_SIZE}
					totalCount={submissions.data.totalCount}
				/>
			)}

			{viewing && (
				<SubmissionDialog
					fields={fields}
					onClose={() => setViewing(null)}
					submission={viewing}
				/>
			)}

			{exporting && (
				<ExportDialog campaign={campaign} onClose={() => setExporting(false)} />
			)}

			{feeds && <DataFeedsDialog campaign={campaign} onClose={() => setFeeds(false)} />}

			{deleting && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('delete')}
					message={t('delete-submission-confirmation')}
					onCancel={() => setDeleting(null)}
					onConfirm={remove}
					title={t('delete-submission')}
				/>
			)}
		</>
	);
}
