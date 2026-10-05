import {useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp, useAsync} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {Alert, Button, ConfirmDialog, Loading} from '../../components/ui.jsx';

export default function TemplateStep({campaign, onBack, onGoToStep, onNext, onReloadCampaign}) {
	const {api, t} = useApp();

	const {data, error, loading} = useAsync(
		() =>
			Promise.all([api.getPageTemplates(), api.getPage(campaign.id)]).then(
				([templates, page]) => ({page, templates})
			),
		[campaign.id]
	);

	const [confirm, setConfirm] = useState(null);
	const [busy, setBusy] = useState(false);
	const [applyError, setApplyError] = useState(null);

	if (loading && !data) {
		return <Loading />;
	}

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	const {page, templates} = data;

	const hasBlocks = Boolean(page.pageBlocks && page.pageBlocks.length);

	const canUpdate = Boolean(campaign.permissions && campaign.permissions.UPDATE);

	const apply = async (template) => {
		setBusy(true);
		setApplyError(null);

		try {
			await api.applyPageTemplate(campaign.id, template.key);

			onReloadCampaign();
			onGoToStep('page');
		}
		catch (applyException) {
			setApplyError(applyException);
			setBusy(false);
			setConfirm(null);
		}
	};

	return (
		<div className="sheet sheet-lg">
			<h3 className="sheet-title">{t('step-template')}</h3>

			<p className="text-secondary">
				{hasBlocks ? t('template-step-replace-info') : t('template-step-description')}
			</p>

			{applyError && (
				<Alert displayType="danger">{getErrorMessage(t, applyError)}</Alert>
			)}

			<ul className="campaign-forms-admin__options">
				{templates.map((template) => {
					const current = page.templateKey === template.key;

					return (
						<li key={template.key}>
							<div className="card campaign-forms-admin__template">
								<div className="card-body">
									<p className="card-title">
										{template.label}

										{current && (
											<span className="label label-info">
												<span className="label-item label-item-expand">
													{t('template-in-use')}
												</span>
											</span>
										)}
									</p>
									<p className="card-text text-secondary">{template.description}</p>

									<ol className="campaign-forms-admin__template-blocks">
										{template.blockTypes.map((blockType, index) => (
											<li key={`${blockType}-${index}`}>
												{t(`block-type-${blockType.toLowerCase()}`)}
											</li>
										))}
									</ol>

									{canUpdate && (
										<Button
											aria-label={t('use-template-x', template.label)}
											busy={busy && confirm === template}
											disabled={busy}
											displayType={hasBlocks ? 'secondary' : 'primary'}
											onClick={() => (hasBlocks ? setConfirm(template) : apply(template))}
											small
										>
											{t('use-template')}
										</Button>
									)}
								</div>
							</div>
						</li>
					);
				})}
			</ul>

			<StepFooter
				nextLabel={hasBlocks ? t('continue') : t('skip')}
				onBack={onBack}
				onNext={onNext}
			/>

			{confirm && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('use-template')}
					displayType="primary"
					message={t('apply-template-confirmation', confirm.label)}
					onCancel={() => setConfirm(null)}
					onConfirm={() => apply(confirm)}
					title={t('apply-template')}
				/>
			)}
		</div>
	);
}
