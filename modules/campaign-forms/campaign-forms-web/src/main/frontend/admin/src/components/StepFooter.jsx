import {useApp} from '../AppContext.js';
import {Button} from './ui.jsx';

export default function StepFooter({busy, nextLabel, onBack, onNext, submit = false}) {
	const {t} = useApp();

	return (
		<div className="campaign-forms-admin__step-footer">
			{onBack && (
				<Button disabled={busy} onClick={onBack}>
					{t('back')}
				</Button>
			)}

			{(onNext || submit) && (
				<Button
					busy={busy}
					displayType="primary"
					onClick={submit ? undefined : onNext}
					type={submit ? 'submit' : 'button'}
				>
					{nextLabel || t('save-and-continue')}
				</Button>
			)}
		</div>
	);
}
