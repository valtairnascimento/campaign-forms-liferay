import {useState} from 'react';

import {getErrorMessage} from '../../../api/client.js';
import {useApp} from '../../../AppContext.js';
import {
	Alert,
	Button,
	Checkbox,
	Icon,
	Modal,
	Select,
	TextArea,
	TextInput,
} from '../../../components/ui.jsx';
import {ImageField, LinkTargetField, MarkupEditor} from './fields.jsx';
import {STYLES, cleanConfiguration, moveBlock, validateBlock} from './pageModel.js';

const MAX_FAQ_ITEMS = 30;

function StyleSelect({onChange, value}) {
	const {t} = useApp();

	return (
		<Select
			help={t('block-style-help')}
			label={t('block-style')}
			onChange={onChange}
			options={STYLES.map((style) => ({
				label: t(`style-${style}`),
				value: style,
			}))}
			value={value || 'brand'}
		/>
	);
}

function HeroEditor({anchors, configuration, errors, set}) {
	const {t} = useApp();

	return (
		<>
			<TextInput
				error={errors.title}
				help={t('hero-title-help')}
				label={t('title')}
				maxLength={150}
				onChange={set('title')}
				required
				value={configuration.title}
			/>
			<TextArea
				error={errors.subtitle}
				label={t('subtitle')}
				maxLength={500}
				onChange={set('subtitle')}
				rows={2}
				value={configuration.subtitle}
			/>
			<ImageField
				error={errors.imageFileEntryId}
				help={t('hero-image-help')}
				label={t('background-image')}
				onChange={set('imageFileEntryId')}
				value={configuration.imageFileEntryId}
			/>
			<div className="row">
				<div className="col-md-6">
					<TextInput
						error={errors.buttonLabel}
						label={t('button-label')}
						maxLength={60}
						onChange={set('buttonLabel')}
						value={configuration.buttonLabel}
					/>
				</div>
				<div className="col-md-6">
					<LinkTargetField
						anchors={anchors}
						error={errors.buttonUrl}
						label={t('button-target')}
						onChange={set('buttonUrl')}
						value={configuration.buttonUrl}
					/>
				</div>
			</div>
			<div className="row">
				<div className="col-md-6">
					<StyleSelect onChange={set('style')} value={configuration.style} />
				</div>
				<div className="col-md-6">
					<Select
						label={t('alignment')}
						onChange={set('align')}
						options={['left', 'center'].map((align) => ({
							label: t(`align-${align}`),
							value: align,
						}))}
						value={configuration.align || 'left'}
					/>
				</div>
			</div>
		</>
	);
}

function TextEditor({configuration, errors, set}) {
	const {t} = useApp();

	return (
		<>
			<TextInput
				error={errors.title}
				label={t('title')}
				maxLength={150}
				onChange={set('title')}
				value={configuration.title}
			/>
			<MarkupEditor
				error={errors.body}
				label={t('text')}
				maxLength={10000}
				onChange={set('body')}
				required
				rows={10}
				value={configuration.body}
			/>
		</>
	);
}

function ImageEditor({configuration, errors, set}) {
	const {t} = useApp();

	return (
		<>
			<ImageField
				error={errors.imageFileEntryId}
				label={t('image')}
				onChange={set('imageFileEntryId')}
				required
				value={configuration.imageFileEntryId}
			/>
			<Checkbox
				checked={configuration.decorative}
				help={t('decorative-help')}
				label={t('decorative')}
				onChange={set('decorative')}
			/>
			{!configuration.decorative && (
				<TextInput
					error={errors.alt}
					help={t('alt-help')}
					label={t('alt')}
					maxLength={250}
					onChange={set('alt')}
					required
					value={configuration.alt}
				/>
			)}
			<TextInput
				error={errors.caption}
				label={t('caption')}
				maxLength={300}
				onChange={set('caption')}
				value={configuration.caption}
			/>
			<Select
				label={t('image-size')}
				onChange={set('size')}
				options={['content', 'wide'].map((size) => ({
					label: t(`image-size-${size}`),
					value: size,
				}))}
				value={configuration.size || 'content'}
			/>
		</>
	);
}

function FormEditor({configuration, errors, set}) {
	const {t} = useApp();

	return (
		<>
			<p className="text-secondary">{t('form-block-help')}</p>
			<TextInput
				error={errors.title}
				label={t('title')}
				maxLength={150}
				onChange={set('title')}
				value={configuration.title}
			/>
			<MarkupEditor
				error={errors.description}
				label={t('introduction')}
				maxLength={2000}
				onChange={set('description')}
				rows={3}
				value={configuration.description}
			/>
		</>
	);
}

function CtaEditor({anchors, configuration, errors, set}) {
	const {t} = useApp();

	return (
		<>
			<TextInput
				error={errors.title}
				label={t('title')}
				maxLength={150}
				onChange={set('title')}
				required
				value={configuration.title}
			/>
			<MarkupEditor
				error={errors.text}
				label={t('text')}
				maxLength={2000}
				onChange={set('text')}
				rows={3}
				value={configuration.text}
			/>
			<div className="row">
				<div className="col-md-6">
					<TextInput
						error={errors.buttonLabel}
						label={t('button-label')}
						maxLength={60}
						onChange={set('buttonLabel')}
						required
						value={configuration.buttonLabel}
					/>
				</div>
				<div className="col-md-6">
					<LinkTargetField
						anchors={anchors}
						error={errors.buttonUrl}
						label={t('button-target')}
						onChange={set('buttonUrl')}
						required
						value={configuration.buttonUrl}
					/>
				</div>
			</div>
			<StyleSelect onChange={set('style')} value={configuration.style} />
		</>
	);
}

function FaqEditor({configuration, errors, set}) {
	const {t} = useApp();

	const items = configuration.items || [];

	const setItems = (nextItems) => set('items')(nextItems);

	const setItem = (index, key) => (value) =>
		setItems(
			items.map((item, itemIndex) =>
				itemIndex === index ? {...item, [key]: value} : item
			)
		);

	return (
		<>
			<TextInput
				error={errors.title}
				label={t('title')}
				maxLength={150}
				onChange={set('title')}
				value={configuration.title}
			/>

			<fieldset>
				<legend className="h5">{t('questions')}</legend>

				{errors.items && (
					<div className="form-feedback-item text-danger" role="alert">
						{errors.items}
					</div>
				)}

				<ol className="list-unstyled campaign-forms-admin__faq-items">
					{items.map((item, index) => (
						<li className="campaign-forms-admin__faq-item" key={index}>
							<div className="campaign-forms-admin__faq-item-header">
								<strong>{t('question-x', index + 1)}</strong>

								<div>
									<Button
										aria-label={t('move-up-x', t('question-x', index + 1))}
										className="btn-monospaced"
										disabled={index === 0}
										displayType="unstyled"
										onClick={() => setItems(moveBlock(items, index, index - 1))}
										small
									>
										<Icon symbol="angle-up" />
									</Button>
									<Button
										aria-label={t('move-down-x', t('question-x', index + 1))}
										className="btn-monospaced"
										disabled={index === items.length - 1}
										displayType="unstyled"
										onClick={() => setItems(moveBlock(items, index, index + 1))}
										small
									>
										<Icon symbol="angle-down" />
									</Button>
									<Button
										aria-label={t('remove-x', t('question-x', index + 1))}
										className="btn-monospaced"
										displayType="unstyled"
										onClick={() =>
											setItems(items.filter((_, itemIndex) => itemIndex !== index))
										}
										small
									>
										<Icon symbol="trash" />
									</Button>
								</div>
							</div>

							<TextInput
								error={errors[`items[${index}].question`]}
								label={t('question')}
								maxLength={300}
								onChange={setItem(index, 'question')}
								required
								value={item.question}
							/>
							<MarkupEditor
								error={errors[`items[${index}].answer`]}
								label={t('answer')}
								maxLength={4000}
								onChange={setItem(index, 'answer')}
								required
								rows={3}
								value={item.answer}
							/>
						</li>
					))}
				</ol>

				<Button
					disabled={items.length >= MAX_FAQ_ITEMS}
					onClick={() => setItems([...items, {answer: '', question: ''}])}
					small
				>
					<Icon className="inline-item inline-item-before" symbol="plus" />
					{t('add-question')}
				</Button>
			</fieldset>
		</>
	);
}

const EDITORS = {
	CTA: CtaEditor,
	FAQ: FaqEditor,
	FORM: FormEditor,
	HERO: HeroEditor,
	IMAGE: ImageEditor,
	TEXT: TextEditor,
};

export default function BlockDialog({anchors, block, onClose, onSave}) {
	const {t} = useApp();

	const [configuration, setConfiguration] = useState(() => ({
		...(block.configuration || {}),
	}));

	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const set = (key) => (value) =>
		setConfiguration((previous) => ({...previous, [key]: value}));

	const Editor = EDITORS[block.blockType];

	const typeLabel = t(`block-type-${String(block.blockType).toLowerCase()}`);

	const submit = async (event) => {
		event.preventDefault();

		const validationErrors = validateBlock(block.blockType, configuration, t);

		setErrors(validationErrors);

		if (Object.keys(validationErrors).length) {
			return;
		}

		setBusy(true);
		setError(null);

		try {
			await onSave(cleanConfiguration(configuration));

			onClose();
		}
		catch (saveError) {
			const fieldErrors = saveError.fieldErrors || {};

			setErrors(fieldErrors);

			if (!Object.keys(fieldErrors).length) {
				setError(saveError);
			}

			setBusy(false);
		}
	};

	return (
		<Modal
			footer={
				<>
					<div className="btn-group-item">
						<Button onClick={onClose}>{t('cancel')}</Button>
					</div>
					<div className="btn-group-item">
						<Button busy={busy} displayType="primary" form="cfBlockForm" type="submit">
							{block.id ? t('save') : t('add-block')}
						</Button>
					</div>
				</>
			}
			onClose={onClose}
			title={block.id ? t('edit-block-x', typeLabel) : t('new-block-x', typeLabel)}
		>
			<form id="cfBlockForm" noValidate onSubmit={submit}>
				{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

				{Editor ? (
					<Editor
						anchors={anchors}
						configuration={configuration}
						errors={errors}
						set={set}
					/>
				) : (
					<Alert displayType="warning">{t('block-type-unavailable')}</Alert>
				)}
			</form>
		</Modal>
	);
}
