import {useState} from 'react';

import {useApp} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {Alert, Button, Icon} from '../../components/ui.jsx';
import {getPreviewURL} from './page/pageModel.js';

const DEVICES = [
	{key: 'desktop', width: '100%'},
	{key: 'tablet', width: '768px'},
	{key: 'mobile', width: '375px'},
];

export default function PreviewStep({campaign, onBack, onNext}) {
	const {config, t} = useApp();

	const [device, setDevice] = useState('desktop');
	const [version, setVersion] = useState(0);

	const previewURL = getPreviewURL(config.publicURL, campaign.slug);

	const width = DEVICES.find((item) => item.key === device).width;

	return (
		<div className="sheet">
			<div className="campaign-forms-admin__preview-header">
				<h3 className="sheet-title">{t('step-preview')}</h3>

				<div className="btn-group" role="group" aria-label={t('device')}>
					{DEVICES.map((item) => (
						<Button
							aria-pressed={device === item.key}
							displayType={device === item.key ? 'primary' : 'secondary'}
							key={item.key}
							onClick={() => setDevice(item.key)}
							small
						>
							{t(`device-${item.key}`)}
						</Button>
					))}
				</div>

				<div className="btn-group">
					<Button onClick={() => setVersion(version + 1)} small>
						<Icon className="inline-item inline-item-before" symbol="reload" />
						{t('reload')}
					</Button>

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
			</div>

			<p className="text-secondary">{t('preview-step-description')}</p>

			{previewURL ? (
				<div className="campaign-forms-admin__device-frame">
					<iframe
						className="campaign-forms-admin__preview-frame campaign-forms-admin__preview-frame--tall"
						key={version}
						src={`${previewURL}&v=${version}`}
						style={{width}}
						title={t('preview-of-x', campaign.name)}
					/>
				</div>
			) : (
				<Alert displayType="warning">{t('no-public-page')}</Alert>
			)}

			<StepFooter nextLabel={t('continue')} onBack={onBack} onNext={onNext} />
		</div>
	);
}
