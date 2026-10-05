import {getErrorMessage} from '../api/client.js';
import {useApp, useAsync} from '../AppContext.js';
import {Alert, Button, Icon, Loading, StatusLabel} from '../components/ui.jsx';
import {getSteps, navigate} from '../router.js';
import ConfirmationStep from './steps/ConfirmationStep.jsx';
import FieldsStep from './steps/FieldsStep.jsx';
import InfoStep from './steps/InfoStep.jsx';
import NotificationStep from './steps/NotificationStep.jsx';
import PageStep from './steps/page/PageStep.jsx';
import PreviewStep from './steps/PreviewStep.jsx';
import PublishStep from './steps/PublishStep.jsx';
import SeoStep from './steps/SeoStep.jsx';
import TemplateStep from './steps/TemplateStep.jsx';

const STEP_COMPONENTS = {
	confirmation: ConfirmationStep,
	fields: FieldsStep,
	info: InfoStep,
	notification: NotificationStep,
	page: PageStep,
	preview: PreviewStep,
	publish: PublishStep,
	seo: SeoStep,
	template: TemplateStep,
};

function Stepper({campaignId, currentStep, steps}) {
	const {t} = useApp();

	const currentIndex = steps.indexOf(currentStep);

	return (
		<nav aria-label={t('steps')} className="campaign-forms-admin__stepper">
			<ol>
				{steps.map((step, index) => {
					const current = step === currentStep;

					const className = [
						'campaign-forms-admin__step',
						current && 'campaign-forms-admin__step--current',
						index < currentIndex && 'campaign-forms-admin__step--done',
					]
						.filter(Boolean)
						.join(' ');

					const content = (
						<>
							<span aria-hidden="true" className="campaign-forms-admin__step-number">
								{index + 1}
							</span>
							<span className="campaign-forms-admin__step-label">
								{t(`step-${step}`)}
							</span>
						</>
					);

					return (
						<li className={className} key={step}>
							{campaignId ? (
								<a
									aria-current={current ? 'step' : undefined}
									href={`#/campaigns/${campaignId}/${step}`}
								>
									{content}
								</a>
							) : (
								<span aria-current={current ? 'step' : undefined}>
									{content}
								</span>
							)}
						</li>
					);
				})}
			</ol>
		</nav>
	);
}

function Header({campaign, mode}) {
	const {t} = useApp();

	return (
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
				<h2 className="campaign-forms-admin__title">
					{campaign ? campaign.name : t('new-campaign')}
				</h2>

				<div className="campaign-forms-admin__subtitle">
					{t(`mode-${String(campaign ? campaign.mode : mode).toLowerCase()}`)}

					{campaign && (
						<>
							{' '}
							<StatusLabel campaign={campaign} />
						</>
					)}
				</div>
			</div>
		</div>
	);
}

export function CampaignCreator({mode}) {
	return (
		<>
			<Header mode={mode} />

			<Stepper currentStep="info" steps={getSteps(mode)} />

			<InfoStep
				campaign={null}
				mode={mode}
				onSaved={(campaign) =>
					navigate(`/campaigns/${campaign.id}/${getSteps(mode)[1]}`)
				}
			/>
		</>
	);
}

export default function CampaignEditor({campaignId, step}) {
	const {api, t} = useApp();

	const {data: campaign, error, loading, reload, setData} = useAsync(
		() => api.getCampaign(campaignId),
		[campaignId]
	);

	if (loading && !campaign) {
		return <Loading />;
	}

	if (error) {
		return (
			<>
				<Header mode="QUICK" />

				<Alert displayType="danger">{getErrorMessage(t, error)}</Alert>
			</>
		);
	}

	const steps = getSteps(campaign.mode);

	const currentStep = steps.includes(step) ? step : steps[0];

	const StepComponent = STEP_COMPONENTS[currentStep];

	const stepIndex = steps.indexOf(currentStep);

	const goTo = (index) => {
		if (index >= 0 && index < steps.length) {
			navigate(`/campaigns/${campaignId}/${steps[index]}`);
		}
	};

	return (
		<>
			<Header campaign={campaign} />

			<Stepper
				campaignId={campaignId}
				currentStep={currentStep}
				steps={steps}
			/>

			<StepComponent
				campaign={campaign}
				key={currentStep}
				onGoToStep={(target) => navigate(`/campaigns/${campaignId}/${target}`)}
				onBack={() => goTo(stepIndex - 1)}
				onCampaignChange={setData}
				onNext={() => goTo(stepIndex + 1)}
				onReloadCampaign={reload}
				onSaved={(savedCampaign) => {
					setData(savedCampaign);
					goTo(stepIndex + 1);
				}}
			/>
		</>
	);
}
