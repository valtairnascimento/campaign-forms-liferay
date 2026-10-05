import {useEffect, useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp, useAsync} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {getPreviewURL} from './page/pageModel.js';
import {
	Alert,
	Button,
	ConfirmDialog,
	Icon,
	Loading,
	StatusLabel,
} from '../../components/ui.jsx';

export function getIssueStep(issue) {
	const code = issue.code || '';

	if (code.startsWith('notification-')) {
		return 'notification';
	}

	if (code.startsWith('block-') || code.startsWith('custom-page-')) {
		return 'page';
	}

	if (code.startsWith('campaign-')) {
		return 'info';
	}

	return 'fields';
}

export default function PublishStep({
	campaign,
	onBack,
	onCampaignChange,
	onReloadCampaign,
}) {
	const {api, config, notify, t} = useApp();

	const issues = useAsync(
		() => api.getPublicationIssues(campaign.id),
		[campaign.id]
	);

	useEffect(() => {
		onReloadCampaign();
	}, []);

	const [busy, setBusy] = useState(false);
	const [error, setError] = useState(null);
	const [confirmArchive, setConfirmArchive] = useState(false);
	const [copied, setCopied] = useState(false);

	const permissions = campaign.permissions || {};

	const publicURL = config.publicURL ? config.publicURL + campaign.slug : null;

	const run = async (action, successMessage) => {
		setBusy(true);
		setError(null);

		try {
			const updatedCampaign = await action();

			onCampaignChange(updatedCampaign);

			notify(successMessage);

			issues.reload();
		}
		catch (actionError) {
			setError(actionError);

			issues.reload();
		}
		finally {
			setBusy(false);
			setConfirmArchive(false);
		}
	};

	const copy = async () => {
		try {
			await navigator.clipboard.writeText(publicURL);

			setCopied(true);

			setTimeout(() => setCopied(false), 3000);
		}
		catch (copyError) {
			setCopied(false);
		}
	};

	const issueList = issues.data || [];

	const canPublish =
		permissions.PUBLISH &&
		!issueList.length &&
		(campaign.status === 'DRAFT' || campaign.hasUnpublishedChanges);

	const published = campaign.status === 'PUBLISHED';

	return (
		<div className="sheet sheet-lg">
			<h3 className="sheet-title">{t('step-publish')}</h3>

			<p>
				{t('current-status')} <StatusLabel campaign={campaign} />
			</p>

			{error && (
				<Alert displayType="danger" onClose={() => setError(null)}>
					{getErrorMessage(t, error)}
				</Alert>
			)}

			{issues.loading && !issues.data && <Loading />}

			{issueList.length > 0 && (
				<div className="alert alert-warning" role="status">
					<strong className="lead">{t('publication-issues-title')}</strong>

					<ul className="campaign-forms-admin__issues">
						{issueList.map((issue, index) => (
							<li key={`${issue.code}-${issue.target || index}`}>
								{issue.message}{' '}
								<a href={`#/campaigns/${campaign.id}/${getIssueStep(issue)}`}>
									{t('fix')}
								</a>
							</li>
						))}
					</ul>
				</div>
			)}

			{issues.data && !issueList.length && campaign.status === 'DRAFT' && (
				<Alert displayType="success">{t('ready-to-publish')}</Alert>
			)}

			{campaign.hasUnpublishedChanges && (
				<Alert displayType="info">{t('unpublished-changes-info')}</Alert>
			)}

			<h4 className="sheet-subtitle">{t('public-address')}</h4>

			{publicURL ? (
				<div className="campaign-forms-admin__public-url">
					<input
						aria-label={t('public-address')}
						className="form-control"
						readOnly
						type="text"
						value={publicURL}
					/>

					<Button onClick={copy}>
						<Icon className="inline-item inline-item-before" symbol="copy" />
						{copied ? t('copied') : t('copy')}
					</Button>

					<a
						className="btn btn-secondary"
						href={getPreviewURL(config.publicURL, campaign.slug)}
						rel="noopener noreferrer"
						target="_blank"
					>
						<Icon className="inline-item inline-item-before" symbol="view" />
						{t('preview')}
					</a>

					{published && (
						<a className="btn btn-secondary" href={publicURL} rel="noopener noreferrer" target="_blank">
							<Icon className="inline-item inline-item-before" symbol="shortcut" />
							{t('open')}
						</a>
					)}
				</div>
			) : (
				<Alert displayType="warning">{t('no-public-page')}</Alert>
			)}

			{published && publicURL && <p className="text-secondary small">{t('published-info')}</p>}

			<div className="campaign-forms-admin__publish-actions">
				{permissions.PUBLISH &&
					(campaign.status === 'DRAFT' || campaign.hasUnpublishedChanges) && (
					<Button
						busy={busy}
						disabled={!canPublish}
						displayType="primary"
						onClick={() =>
							run(
								() => api.publishCampaign(campaign.id),
								published ? t('changes-published') : t('campaign-published')
							)
						}
					>
						{published ? t('publish-changes') : t('publish')}
					</Button>
				)}

				{permissions.PUBLISH && published && (
					<Button disabled={busy} onClick={() => setConfirmArchive(true)}>
						{t('archive')}
					</Button>
				)}

				{permissions.PUBLISH && campaign.status === 'ARCHIVED' && (
					<Button
						busy={busy}
						displayType="primary"
						onClick={() => run(() => api.reopenCampaign(campaign.id), t('campaign-reopened'))}
					>
						{t('reopen')}
					</Button>
				)}

				{!permissions.PUBLISH && <p className="text-secondary">{t('publish-no-permission')}</p>}
			</div>

			<StepFooter onBack={onBack} />

			{confirmArchive && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('archive')}
					displayType="primary"
					message={t('archive-confirmation', campaign.name)}
					onCancel={() => setConfirmArchive(false)}
					onConfirm={() => run(() => api.archiveCampaign(campaign.id), t('campaign-archived'))}
					title={t('archive-campaign')}
				/>
			)}
		</div>
	);
}
