import {useState} from 'react';

import {getErrorMessage} from '../../../api/client.js';
import {useApp, useAsync} from '../../../AppContext.js';
import StepFooter from '../../../components/StepFooter.jsx';
import {
	ActionsMenu,
	Alert,
	Button,
	ConfirmDialog,
	Icon,
	Loading,
	Select,
} from '../../../components/ui.jsx';
import BlockDialog from './BlockDialog.jsx';
import {
	THEMES,
	blockSummary,
	getAnchors,
	getPreviewURL,
	moveBlock,
	toPagePayload,
} from './pageModel.js';

const BLOCK_ICONS = {
	CTA: 'bolt',
	FAQ: 'question-circle',
	FORM: 'forms',
	HERO: 'picture',
	IMAGE: 'document-image',
	TEXT: 'paragraph',
};

function AddBlockMenu({blockTypes, blocks, disabled, onAdd}) {
	const {t} = useApp();

	const [open, setOpen] = useState(false);

	return (
		<div className="campaign-forms-admin__add-block">
			<Button
				aria-expanded={open}
				disabled={disabled}
				displayType="primary"
				onClick={() => setOpen(!open)}
			>
				<Icon className="inline-item inline-item-before" symbol="plus" />
				{t('add-block')}
			</Button>

			{open && (
				<ul aria-label={t('block-types')} className="list-group campaign-forms-admin__palette">
					{blockTypes.map((blockType) => {
						const taken =
							blockType.singleton &&
							blocks.some((block) => block.blockType === blockType.key);

						return (
							<li className="list-group-item" key={blockType.key}>
								<button
									className="campaign-forms-admin__palette-option"
									disabled={taken}
									onClick={() => {
										setOpen(false);
										onAdd(blockType);
									}}
									type="button"
								>
									<Icon symbol={BLOCK_ICONS[blockType.key] || 'square'} />
									<span>
										<strong>{blockType.label}</strong>
										<span className="d-block text-secondary small">
											{taken ? t('block-already-on-page') : blockType.description}
										</span>
									</span>
								</button>
							</li>
						);
					})}
				</ul>
			)}
		</div>
	);
}

export default function PageStep({campaign, onBack, onNext, onReloadCampaign}) {
	const {api, config, t} = useApp();

	const {data, error, loading, setData} = useAsync(
		() =>
			Promise.all([
				api.getPage(campaign.id),
				api.getBlockTypes(campaign.id),
			]).then(([page, blockTypes]) => ({blockTypes, page})),
		[campaign.id]
	);

	const [dialog, setDialog] = useState(null);
	const [deleting, setDeleting] = useState(null);
	const [actionError, setActionError] = useState(null);
	const [busy, setBusy] = useState(false);
	const [previewVersion, setPreviewVersion] = useState(0);
	const [dragIndex, setDragIndex] = useState(null);
	const [announcement, setAnnouncement] = useState('');

	if (loading && !data) {
		return <Loading />;
	}

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	const {blockTypes, page} = data;

	const blocks = (page.pageBlocks || [])
		.slice()
		.sort((a, b) => a.position - b.position);

	const canUpdate = Boolean(campaign.permissions && campaign.permissions.UPDATE);

	const previewURL = getPreviewURL(config.publicURL, campaign.slug);

	const anchors = getAnchors(blocks, t);

	const refresh = async () => {
		const freshPage = await api.getPage(campaign.id);

		setData({...data, page: freshPage});
		setPreviewVersion((version) => version + 1);

		onReloadCampaign();
	};

	const run = async (operation, message) => {
		setBusy(true);
		setActionError(null);

		try {
			await operation();
			await refresh();

			if (message) {
				setAnnouncement(message);
			}
		}
		catch (operationError) {
			setActionError(operationError);
		}
		finally {
			setBusy(false);
		}
	};

	const reorder = (fromIndex, toIndex) => {
		const ordered = moveBlock(blocks, fromIndex, toIndex);

		if (ordered === blocks) {
			return;
		}

		run(
			() => api.reorderBlocks(campaign.id, ordered.map((block) => block.id)),
			t('block-moved-x', blockSummary(blocks[fromIndex], t), toIndex + 1)
		);
	};

	const saveBlock = async (configuration) => {
		const block = dialog.block;

		if (block.id) {
			await api.updateBlock(block.id, {configuration, enabled: block.enabled});
		}
		else {
			await api.addBlock(campaign.id, {
				blockType: block.blockType,
				configuration,
				enabled: true,
			});
		}

		await refresh();
	};

	const changeTheme = (themeKey) =>
		run(() => api.updatePage(campaign.id, toPagePayload(page, {themeKey})));

	return (
		<div className="campaign-forms-admin__builder">
			<div className="sheet campaign-forms-admin__builder-blocks">
				<h3 className="sheet-title">{t('step-page')}</h3>

				<p className="text-secondary">{t('page-step-description')}</p>

				<div aria-live="polite" className="sr-only">
					{announcement}
				</div>

				{actionError && (
					<Alert displayType="danger" onClose={() => setActionError(null)}>
						{getErrorMessage(t, actionError)}
					</Alert>
				)}

				{!blocks.length && <p className="text-secondary">{t('no-blocks')}</p>}

				<ol aria-busy={busy} className="list-group campaign-forms-admin__blocks">
					{blocks.map((block, index) => (
						<li
							className={`list-group-item list-group-item-flex campaign-forms-admin__block ${
								block.enabled ? '' : 'campaign-forms-admin__block--disabled'
							} ${dragIndex === index ? 'campaign-forms-admin__block--dragging' : ''}`}
							draggable={canUpdate && !busy}
							key={block.id}
							onDragEnd={() => setDragIndex(null)}
							onDragOver={(event) => {
								if (dragIndex !== null) {
									event.preventDefault();
								}
							}}
							onDragStart={(event) => {
								event.dataTransfer.effectAllowed = 'move';
								event.dataTransfer.setData('text/plain', String(block.id));
								setDragIndex(index);
							}}
							onDrop={(event) => {
								event.preventDefault();

								if (dragIndex !== null) {
									reorder(dragIndex, index);
								}

								setDragIndex(null);
							}}
						>
							{canUpdate && (
								<div aria-hidden="true" className="autofit-col campaign-forms-admin__drag-handle">
									<Icon symbol="drag" />
								</div>
							)}

							<div className="autofit-col autofit-col-expand">
								<p className="list-group-title">
									<Icon
										className="inline-item inline-item-before"
										symbol={BLOCK_ICONS[block.blockType] || 'square'}
									/>
									{t(`block-type-${String(block.blockType).toLowerCase()}`)}

									{!block.enabled && (
										<span className="label label-secondary">
											<span className="label-item label-item-expand">
												{t('hidden')}
											</span>
										</span>
									)}
								</p>
								<p className="list-group-subtext">
									{blockSummary(block, t)} · #{block.blockKey}
								</p>
							</div>

							{canUpdate && (
								<div className="autofit-col campaign-forms-admin__field-actions">
									<Button
										aria-label={t('move-up-x', blockSummary(block, t))}
										className="btn-monospaced"
										disabled={busy || index === 0}
										displayType="unstyled"
										onClick={() => reorder(index, index - 1)}
										small
										title={t('move-up-x', blockSummary(block, t))}
									>
										<Icon symbol="angle-up" />
									</Button>
									<Button
										aria-label={t('move-down-x', blockSummary(block, t))}
										className="btn-monospaced"
										disabled={busy || index === blocks.length - 1}
										displayType="unstyled"
										onClick={() => reorder(index, index + 1)}
										small
										title={t('move-down-x', blockSummary(block, t))}
									>
										<Icon symbol="angle-down" />
									</Button>

									<ActionsMenu
										items={[
											{
												label: t('edit'),
												onClick: () => setDialog({block}),
											},
											{
												label: block.enabled ? t('hide-block') : t('show-block'),
												onClick: () =>
													run(() =>
														api.updateBlock(block.id, {
															configuration: block.configuration,
															enabled: !block.enabled,
														})
													),
											},
											{
												hidden: block.blockType === 'FORM',
												label: t('duplicate'),
												onClick: () => run(() => api.duplicateBlock(block.id)),
											},
											{
												danger: true,
												label: t('delete'),
												onClick: () => setDeleting(block),
											},
										]}
										label={t('actions-for-x', blockSummary(block, t))}
									/>
								</div>
							)}
						</li>
					))}
				</ol>

				{canUpdate && (
					<AddBlockMenu
						blockTypes={blockTypes}
						blocks={blocks}
						disabled={busy}
						onAdd={(blockType) =>
							setDialog({
								block: {
									blockType: blockType.key,
									configuration: blockType.defaultConfiguration || {},
									enabled: true,
								},
							})
						}
					/>
				)}

				<h4 className="sheet-subtitle">{t('appearance')}</h4>

				<Select
					disabled={!canUpdate || busy}
					help={t('page-theme-help')}
					label={t('page-theme')}
					onChange={changeTheme}
					options={THEMES.map((theme) => ({
						label: t(`theme-${theme}`),
						value: theme,
					}))}
					value={page.themeKey || 'default'}
				/>

				<StepFooter busy={busy} nextLabel={t('continue')} onBack={onBack} onNext={onNext} />
			</div>

			<div className="sheet campaign-forms-admin__builder-preview">
				<div className="campaign-forms-admin__preview-header">
					<h3 className="sheet-title">{t('live-preview')}</h3>

					{previewURL && (
						<a
							className="btn btn-secondary btn-sm"
							href={previewURL}
							rel="noopener noreferrer"
							target="_blank"
						>
							<Icon className="inline-item inline-item-before" symbol="shortcut" />
							{t('open-in-new-tab')}
						</a>
					)}
				</div>

				{previewURL ? (
					<iframe
						className="campaign-forms-admin__preview-frame"
						key={previewVersion}
						src={`${previewURL}&v=${previewVersion}`}
						title={t('preview-of-x', campaign.name)}
					/>
				) : (
					<Alert displayType="warning">{t('no-public-page')}</Alert>
				)}
			</div>

			{dialog && (
				<BlockDialog
					anchors={anchors}
					block={dialog.block}
					onClose={() => setDialog(null)}
					onSave={saveBlock}
				/>
			)}

			{deleting && (
				<ConfirmDialog
					busy={busy}
					confirmLabel={t('delete')}
					message={t('delete-block-confirmation', blockSummary(deleting, t))}
					onCancel={() => setDeleting(null)}
					onConfirm={async () => {
						await run(() => api.deleteBlock(deleting.id));

						setDeleting(null);
					}}
					title={t('delete-block')}
				/>
			)}
		</div>
	);
}
