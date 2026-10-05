import {useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp, useAsync} from '../../AppContext.js';
import StepFooter from '../../components/StepFooter.jsx';
import {Alert, Checkbox, Loading, TextArea, TextInput} from '../../components/ui.jsx';
import {ImageField} from './page/fields.jsx';
import {toPagePayload} from './page/pageModel.js';

export const SEO_TITLE_RECOMMENDED = 60;

export const SEO_DESCRIPTION_RECOMMENDED = 160;

function Counter({max, value}) {
	const {t} = useApp();

	const length = (value || '').length;

	return (
		<span className={length > max ? 'text-warning' : 'text-secondary'}>
			{t('characters-x-of-y', length, max)}
		</span>
	);
}

function SeoForm({campaign, onBack, onNext, onReloadCampaign, page}) {
	const {api, config, t} = useApp();

	const initial = {
		noIndex: Boolean(page.noIndex),
		ogDescription: page.ogDescription || '',
		ogImageFileEntryId: page.ogImageFileEntryId || null,
		seoDescription: page.seoDescription || '',
		seoTitle: page.seoTitle || '',
	};

	const [values, setValues] = useState(initial);
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const canUpdate = Boolean(campaign.permissions && campaign.permissions.UPDATE);

	const title = values.seoTitle.trim() || campaign.name;
	const description = values.seoDescription.trim() || campaign.description || '';

	const submit = async (event) => {
		event.preventDefault();

		if (!canUpdate || JSON.stringify(values) === JSON.stringify(initial)) {
			onNext();

			return;
		}

		setBusy(true);
		setError(null);

		try {
			await api.updatePage(
				campaign.id,
				toPagePayload(page, {
					noIndex: values.noIndex,
					ogDescription: values.ogDescription.trim() || null,
					ogImageFileEntryId: values.ogImageFileEntryId || 0,
					seoDescription: values.seoDescription.trim() || null,
					seoTitle: values.seoTitle.trim() || null,
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
			<h3 className="sheet-title">{t('step-seo')}</h3>

			<p className="text-secondary">{t('seo-step-description')}</p>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			<div aria-label={t('search-result-preview')} className="campaign-forms-admin__serp" role="figure">
				<span className="campaign-forms-admin__serp-url">
					{config.publicURL ? config.publicURL + campaign.slug : campaign.slug}
				</span>
				<span className="campaign-forms-admin__serp-title">{title}</span>
				<span className="campaign-forms-admin__serp-description">{description}</span>
			</div>

			<fieldset disabled={!canUpdate || busy}>
				<TextInput
					error={errors.seoTitle}
					help={
						<>
							{t('seo-title-help')} <Counter max={SEO_TITLE_RECOMMENDED} value={values.seoTitle} />
						</>
					}
					label={t('seo-title')}
					maxLength={255}
					onChange={set('seoTitle')}
					placeholder={campaign.name}
					value={values.seoTitle}
				/>

				<TextArea
					error={errors.seoDescription}
					help={
						<>
							{t('seo-description-help')}{' '}
							<Counter max={SEO_DESCRIPTION_RECOMMENDED} value={values.seoDescription} />
						</>
					}
					label={t('seo-description')}
					maxLength={1000}
					onChange={set('seoDescription')}
					rows={3}
					value={values.seoDescription}
				/>

				<h4 className="sheet-subtitle">{t('social-sharing')}</h4>

				<TextArea
					error={errors.ogDescription}
					help={t('og-description-help')}
					label={t('og-description')}
					maxLength={1000}
					onChange={set('ogDescription')}
					rows={2}
					value={values.ogDescription}
				/>

				<ImageField
					error={errors.ogImageFileEntryId}
					help={t('og-image-help')}
					label={t('og-image')}
					onChange={set('ogImageFileEntryId')}
					value={values.ogImageFileEntryId}
				/>

				<h4 className="sheet-subtitle">{t('search-engines')}</h4>

				<Checkbox
					checked={values.noIndex}
					help={t('no-index-help')}
					label={t('no-index')}
					onChange={set('noIndex')}
				/>
			</fieldset>

			<StepFooter busy={busy} onBack={onBack} submit />
		</form>
	);
}

export default function SeoStep(props) {
	const {api, t} = useApp();

	const {data, error, loading} = useAsync(
		() => api.getPage(props.campaign.id),
		[props.campaign.id]
	);

	if (loading && !data) {
		return <Loading />;
	}

	if (error) {
		return <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>;
	}

	return <SeoForm {...props} page={data} />;
}
