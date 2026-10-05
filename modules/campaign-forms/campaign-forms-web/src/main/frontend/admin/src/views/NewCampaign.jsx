import {useState} from 'react';

import {useApp} from '../AppContext.js';
import {Button, Icon} from '../components/ui.jsx';
import {navigate} from '../router.js';
import DuplicateDialog from './DuplicateDialog.jsx';

export default function NewCampaign() {
	const {t} = useApp();

	const [duplicating, setDuplicating] = useState(false);

	const options = [
		{
			description: t('mode-quick-description'),
			icon: 'forms',
			onClick: () => navigate('/new/quick'),
			title: t('mode-quick'),
		},
		{
			description: t('mode-custom_page-description'),
			icon: 'page',
			onClick: () => navigate('/new/custom'),
			title: t('mode-custom_page'),
		},
		{
			description: t('duplicate-existing-description'),
			icon: 'copy',
			onClick: () => setDuplicating(true),
			title: t('duplicate-existing'),
		},
	];

	return (
		<>
			<div className="campaign-forms-admin__toolbar">
				<Button
					aria-label={t('back')}
					className="btn-monospaced"
					displayType="unstyled"
					onClick={() => navigate('/')}
					title={t('back')}
				>
					<Icon symbol="angle-left" />
				</Button>

				<h2 className="campaign-forms-admin__title">{t('new-campaign')}</h2>
			</div>

			<p className="text-secondary">{t('new-campaign-description')}</p>

			<ul className="campaign-forms-admin__options">
				{options.map((option) => (
					<li key={option.title}>
						<button
							className="card card-interactive card-interactive-primary campaign-forms-admin__option"
							disabled={option.disabled}
							onClick={option.onClick}
							type="button"
						>
							<span className="card-body">
								<Icon className="campaign-forms-admin__option-icon" symbol={option.icon} />
								<span className="card-title">{option.title}</span>
								<span className="card-text text-secondary">
									{option.description}
								</span>
							</span>
						</button>
					</li>
				))}
			</ul>

			{duplicating && <DuplicateDialog onClose={() => setDuplicating(false)} />}
		</>
	);
}
