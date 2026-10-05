import {useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp, useAsync} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {Alert, Loading, TextArea, TextInput} from '../../components/ui.jsx';
import {toFormVersionPayload} from './fieldModel.js';

export function isValidRedirectUrl(url) {
	if (!url) {
		return true;
	}

	if (url.startsWith('//') || url.startsWith('/\\')) {
		return false;
	}

	if (url.startsWith('/')) {
		return true;
	}

	return /^https?:\/\/[^/\s]+/i.test(url);
}

function ConfirmationForm({campaign, formVersion, onBack, onNext, onReloadCampaign}) {
	const {api, t} = useApp();

	const canUpdate = Boolean(campaign.permissions && campaign.permissions.UPDATE);

	const initial = {
		redirectUrl: formVersion.redirectUrl || '',
		successMessage: formVersion.successMessage || '',
		successTitle: formVersion.successTitle || '',
	};

	const [values, setValues] = useState(initial);
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const submit = async (event) => {
		event.preventDefault();

		const redirectUrl = values.redirectUrl.trim();

		if (!isValidRedirectUrl(redirectUrl)) {
			setErrors({redirectUrl: t('redirect-url-invalid')});

			return;
		}

		setErrors({});

		if (!canUpdate || JSON.stringify(values) === JSON.stringify(initial)) {
			onNext();

			return;
		}

		setBusy(true);
		setError(null);

		try {
			await api.updateForm(
				campaign.id,
				toFormVersionPayload(formVersion, {
					redirectUrl: redirectUrl || null,
					successMessage: values.successMessage.trim() || null,
					successTitle: values.successTitle.trim() || null,
				})
			);

			onReloadCampaign();

			onNext();
		}
		catch (submitError) {
			setErrors(submitError.fieldErrors || {});
			setError(submitError);
			setBusy(false);
		}
	};

	return (
		<form className="sheet sheet-lg" noValidate onSubmit={submit}>
			<h3 className="sheet-title">{t('step-confirmation')}</h3>

			<p className="text-secondary">{t('confirmation-description')}</p>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			<fieldset disabled={!canUpdate || busy}>
				<TextInput
					help={t('success-title-help')}
					label={t('success-title')}
					maxLength={255}
					onChange={set('successTitle')}
					value={values.successTitle}
				/>

				<TextArea
					help={t('success-message-help')}
					label={t('success-message')}
					maxLength={4000}
					onChange={set('successMessage')}
					value={values.successMessage}
				/>

				<TextInput
					error={errors.redirectUrl}
					help={t('redirect-url-help')}
					label={t('redirect-url')}
					onChange={set('redirectUrl')}
					type="url"
					value={values.redirectUrl}
				/>
			</fieldset>

			<StepFooter busy={busy} onBack={onBack} submit />
		</form>
	);
}

export default function ConfirmationStep(props) {
	const {api, t} = useApp();

	const {data, error, loading} = useAsync(
		() => api.getForm(props.campaign.id),
		[props.campaign.id]
	);

	if (loading && !data) {
		return <Loading />;
	}

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	return <ConfirmationForm {...props} formVersion={data} />;
}
