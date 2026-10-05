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
	Pagination,
	StatusLabel,
} from '../components/ui.jsx';
import {navigate} from '../router.js';
import DuplicateDialog from './DuplicateDialog.jsx';

const PAGE_SIZE = 20;

export default function CampaignList() {
	const {api, config, formatDate, notify, t} = useApp();

	const [page, setPage] = useState(1);
	const [confirm, setConfirm] = useState(null);
	const [duplicating, setDuplicating] = useState(null);
	const [busy, setBusy] = useState(false);

	const {data, error, loading, reload} = useAsync(
		() => api.getCampaigns({page, pageSize: PAGE_SIZE}),
		[page]
	);

	const canAdd = Boolean(config.permissions.ADD_CAMPAIGN);

	const runAction = async (action, successMessage) => {
		setBusy(true);

		try {
			await action();

			notify(successMessage);

			setConfirm(null);

			reload();
		}
		catch (actionError) {
			setConfirm(null);

			notify(getErrorMessage(t, actionError), 'danger');
		}
		finally {
			setBusy(false);
		}
	};

	const getActions = (campaign) => {
		const permissions = campaign.permissions || {};

		const publicURL =
			config.publicURL && campaign.status === 'PUBLISHED'
				? config.publicURL + campaign.slug
				: null;

		return [
			{
				hidden: !permissions.UPDATE,
				label: t('edit'),
				onClick: () => navigate(`/campaigns/${campaign.id}/info`),
			},
			{
				hidden: !publicURL,
				label: t('view'),
				onClick: () => window.open(publicURL, '_blank', 'noopener'),
			},
			{
				hidden: !permissions.UPDATE || campaign.mode !== 'QUICK',
				label: t('convert-to-custom-page'),
				onClick: () =>
					setConfirm({
						action: () =>
							api
								.convertToCustomPage(campaign.id)
								.then(() => navigate(`/campaigns/${campaign.id}/page`)),
						confirmLabel: t('convert'),
						displayType: 'primary',
						message: t('convert-confirmation', campaign.name),
						success: t('campaign-converted'),
						title: t('convert-to-custom-page'),
					}),
			},
			{
				hidden: !canAdd,
				label: t('duplicate'),
				onClick: () => setDuplicating(campaign),
			},
			{
				hidden: !permissions.VIEW_SUBMISSIONS,
				label: t('results'),
				onClick: () => navigate(`/campaigns/${campaign.id}/results`),
			},
			{
				hidden: !permissions.PUBLISH || campaign.status !== 'PUBLISHED',
				label: t('archive'),
				onClick: () =>
					setConfirm({
						action: () => api.archiveCampaign(campaign.id),
						confirmLabel: t('archive'),
						displayType: 'primary',
						message: t('archive-confirmation', campaign.name),
						success: t('campaign-archived'),
						title: t('archive-campaign'),
					}),
			},
			{
				hidden: !permissions.PUBLISH || campaign.status !== 'ARCHIVED',
				label: t('reopen'),
				onClick: () =>
					runAction(
						() => api.reopenCampaign(campaign.id),
						t('campaign-reopened')
					),
			},
			{
				danger: true,
				hidden: !permissions.DELETE || campaign.submissionsCount > 0,
				label: t('delete'),
				onClick: () =>
					setConfirm({
						action: () => api.deleteCampaign(campaign.id),
						confirmLabel: t('delete'),
						message: t('delete-campaign-confirmation', campaign.name),
						success: t('campaign-deleted'),
						title: t('delete-campaign'),
					}),
			},
		];
	};

	const campaigns = (data && data.items) || [];

	return (
		<>
			<div className="campaign-forms-admin__toolbar">
				<h2 className="campaign-forms-admin__title">{t('campaigns')}</h2>

				{canAdd && (
					<Button displayType="primary" onClick={() => navigate('/new')}>
						<Icon className="inline-item inline-item-before" symbol="plus" />
						{t('new-campaign')}
					</Button>
				)}
			</div>

			{loading && !data && <Loading />}

			{error && (
				<Alert displayType="danger">
					{getErrorMessage(t, error)}{' '}
					<button className="btn btn-link btn-sm" onClick={reload} type="button">
						{t('try-again')}
					</button>
				</Alert>
			)}

			{data && !campaigns.length && (
				<EmptyState
					action={
						canAdd && (
							<Button displayType="primary" onClick={() => navigate('/new')}>
								{t('new-campaign')}
							</Button>
						)
					}
					description={t('no-campaigns-description')}
					title={t('no-campaigns')}
				/>
			)}

			{campaigns.length > 0 && (
				<div className="table-responsive">
					<table className="table table-autofit table-hover table-list">
						<thead>
							<tr>
								<th className="table-cell-expand">{t('name')}</th>
								<th>{t('type')}</th>
								<th>{t('status')}</th>
								<th className="text-right">{t('submissions')}</th>
								<th>{t('modified-date')}</th>
								<th>
									<span className="sr-only">{t('actions')}</span>
								</th>
							</tr>
						</thead>

						<tbody>
							{campaigns.map((campaign) => (
								<tr key={campaign.id}>
									<td className="table-cell-expand">
										{campaign.permissions && campaign.permissions.UPDATE ? (
											<a
												className="table-title"
												href={`#/campaigns/${campaign.id}/info`}
											>
												{campaign.name}
											</a>
										) : (
											<span className="table-title">{campaign.name}</span>
										)}

										<div className="text-secondary small">/{campaign.slug}</div>
									</td>
									<td>{t(`mode-${String(campaign.mode).toLowerCase()}`)}</td>
									<td>
										<StatusLabel campaign={campaign} />
									</td>
									<td className="text-right">{campaign.submissionsCount}</td>
									<td>{formatDate(campaign.dateModified)}</td>
									<td>
										<ActionsMenu
											items={getActions(campaign)}
											label={t('actions-for-x', campaign.name)}
										/>
									</td>
								</tr>
							))}
						</tbody>
					</table>
				</div>
			)}

			{data && (
				<Pagination
					onChange={setPage}
					page={page}
					pageSize={PAGE_SIZE}
					totalCount={data.totalCount}
				/>
			)}

			{confirm && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={confirm.confirmLabel}
					displayType={confirm.displayType}
					message={confirm.message}
					onCancel={() => setConfirm(null)}
					onConfirm={() => runAction(confirm.action, confirm.success)}
					title={confirm.title}
				/>
			)}

			{duplicating && (
				<DuplicateDialog
					onClose={() => setDuplicating(null)}
					source={duplicating}
				/>
			)}
		</>
	);
}
