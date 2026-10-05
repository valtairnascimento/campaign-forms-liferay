import {useEffect, useId, useRef, useState} from 'react';

import {getErrorMessage} from '../../../api/client.js';
import {useApp, useAsync} from '../../../AppContext.js';
import {
	Alert,
	Button,
	Icon,
	Loading,
	Modal,
	Select,
	TextArea,
	TextInput,
} from '../../../components/ui.jsx';
import {insertLink, prefixLines, wrapSelection} from './markup.js';

export function MarkupEditor({
	error,
	help,
	label,
	maxLength,
	onChange,
	required,
	rows = 6,
	value,
}) {
	const {t} = useApp();

	const ref = useRef(null);

	const apply = (transform) => {
		const textarea = ref.current;

		const current = value || '';

		const start = textarea ? textarea.selectionStart : current.length;
		const end = textarea ? textarea.selectionEnd : current.length;

		const result = transform(current, start, end);

		onChange(result.text);

		if (textarea) {
			requestAnimationFrame(() => {
				textarea.focus();
				textarea.setSelectionRange(
					result.selectionStart,
					result.selectionEnd
				);
			});
		}
	};

	const tools = [
		{
			icon: 'bold',
			label: t('markup-bold'),
			transform: (text, start, end) =>
				wrapSelection(text, start, end, '**', t('markup-bold-placeholder')),
		},
		{
			icon: 'italic',
			label: t('markup-italic'),
			transform: (text, start, end) =>
				wrapSelection(text, start, end, '*', t('markup-italic-placeholder')),
		},
		{
			icon: 'link',
			label: t('markup-link'),
			transform: (text, start, end) =>
				insertLink(text, start, end, t('markup-link-placeholder')),
		},
		{
			icon: 'list-ul',
			label: t('markup-list'),
			transform: (text, start, end) => prefixLines(text, start, end, '- '),
		},
		{
			icon: 'list-ol',
			label: t('markup-numbered-list'),
			transform: (text, start, end) => prefixLines(text, start, end, '1. '),
		},
		{
			icon: 'text',
			label: t('markup-subtitle'),
			transform: (text, start, end) => prefixLines(text, start, end, '## '),
		},
	];

	return (
		<div className="campaign-forms-admin__markup">
			<div
				aria-label={t('markup-toolbar-x', label)}
				className="campaign-forms-admin__markup-toolbar"
				role="toolbar"
			>
				{tools.map((tool) => (
					<Button
						aria-label={tool.label}
						className="btn-monospaced"
						displayType="secondary"
						key={tool.icon}
						onClick={() => apply(tool.transform)}
						onMouseDown={(event) => event.preventDefault()}
						small
						title={tool.label}
					>
						<Icon symbol={tool.icon} />
					</Button>
				))}
			</div>

			<TextArea
				error={error}
				help={help || t('markup-help')}
				label={label}
				maxLength={maxLength}
				onChange={onChange}
				ref={ref}
				required={required}
				rows={rows}
				value={value}
			/>
		</div>
	);
}

export function LinkTargetField({
	anchors,
	error,
	label,
	onChange,
	required,
	value,
}) {
	const {t} = useApp();

	const isAnchor = anchors.some((anchor) => anchor.value === value);

	const [custom, setCustom] = useState(Boolean(value) && !isAnchor);

	const selectValue = custom ? '__custom' : value || '';

	return (
		<>
			<Select
				error={custom ? null : error}
				label={label}
				onChange={(selected) => {
					if (selected === '__custom') {
						setCustom(true);
						onChange('');
					}
					else {
						setCustom(false);
						onChange(selected);
					}
				}}
				options={[
					...(required ? [] : [{label: t('no-button'), value: ''}]),
					...anchors,
					{label: t('other-address'), value: '__custom'},
				]}
				required={required}
				value={selectValue}
			/>

			{custom && (
				<TextInput
					error={error}
					help={t('link-url-help')}
					label={t('address')}
					onChange={onChange}
					placeholder="https://"
					required={required}
					value={value}
				/>
			)}
		</>
	);
}

export function ImagePickerDialog({onClose, onSelect}) {
	const {api, t} = useApp();

	const [search, setSearch] = useState('');
	const [query, setQuery] = useState('');
	const [page, setPage] = useState(1);
	const [images, setImages] = useState([]);
	const [lastPage, setLastPage] = useState(1);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [uploading, setUploading] = useState(false);

	const fileInputId = useId();

	useEffect(() => {
		let active = true;

		setLoading(true);
		setError(null);

		api.getImages({page, pageSize: _PAGE_SIZE, search: query}).then(
			(result) => {
				if (!active) {
					return;
				}

				setImages((previous) =>
					page === 1 ? result.items || [] : [...previous, ...(result.items || [])]
				);
				setLastPage(result.lastPage || 1);
				setLoading(false);
			},
			(loadError) => {
				if (active) {
					setError(loadError);
					setLoading(false);
				}
			}
		);

		return () => {
			active = false;
		};
	}, [api, page, query]);

	const upload = async (event) => {
		const file = event.target.files && event.target.files[0];

		event.target.value = '';

		if (!file) {
			return;
		}

		setUploading(true);
		setError(null);

		try {
			const image = await api.uploadImage(file);

			onSelect(image);
			onClose();
		}
		catch (uploadError) {
			setError(uploadError);
			setUploading(false);
		}
	};

	return (
		<Modal
			footer={
				<div className="btn-group-item">
					<Button onClick={onClose}>{t('cancel')}</Button>
				</div>
			}
			onClose={onClose}
			title={t('choose-image')}
		>
			<form
				className="campaign-forms-admin__image-search"
				onSubmit={(event) => {
					event.preventDefault();
					setPage(1);
					setQuery(search.trim());
				}}
				role="search"
			>
				<TextInput
					label={t('search-images')}
					onChange={setSearch}
					type="search"
					value={search}
				/>

				<Button type="submit">{t('search')}</Button>

				<label
					className={`btn btn-primary ${uploading ? 'disabled' : ''}`}
					htmlFor={fileInputId}
				>
					<Icon className="inline-item inline-item-before" symbol="upload" />
					{uploading ? t('uploading') : t('upload-image')}
				</label>

				<input
					accept="image/png,image/jpeg,image/gif,image/webp"
					className="sr-only"
					disabled={uploading}
					id={fileInputId}
					onChange={upload}
					type="file"
				/>
			</form>

			<p className="text-secondary small">{t('image-library-help')}</p>

			{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

			{!loading && !images.length && !error && (
				<p className="text-secondary">{t('no-images')}</p>
			)}

			<ul className="campaign-forms-admin__image-grid">
				{images.map((image) => (
					<li key={image.id}>
						<button
							className="campaign-forms-admin__image-option"
							onClick={() => {
								onSelect(image);
								onClose();
							}}
							type="button"
						>
							<img alt="" loading="lazy" src={image.contentUrl} />
							<span>{image.title}</span>
						</button>
					</li>
				))}
			</ul>

			{loading && <Loading />}

			{!loading && page < lastPage && (
				<Button onClick={() => setPage(page + 1)}>{t('load-more')}</Button>
			)}
		</Modal>
	);
}

const _PAGE_SIZE = 24;

export function ImageField({error, help, label, onChange, required, value}) {
	const {api, t} = useApp();

	const [picking, setPicking] = useState(false);
	const [selected, setSelected] = useState(null);

	const current = useAsync(
		() =>
			value && (!selected || selected.id !== Number(value)) ?
				api.getImage(value).catch(() => null) :
				Promise.resolve(selected),
		[value]
	);

	const image = selected && selected.id === Number(value) ? selected : current.data;

	const id = useId();

	const describedBy = [help && `${id}-help`, error && `${id}-error`]
		.filter(Boolean)
		.join(' ');

	return (
		<div
			aria-describedby={describedBy || undefined}
			aria-labelledby={`${id}-label`}
			className={`form-group campaign-forms-admin__image-field ${error ? 'has-error' : ''}`}
			role="group"
		>
			<span className="campaign-forms-admin__group-label" id={`${id}-label`}>
				{label}

				{required && (
					<>
						<span aria-hidden="true" className="reference-mark">
							<Icon symbol="asterisk" />
						</span>
						<span className="sr-only">{t('required')}</span>
					</>
				)}
			</span>

			{value ? (
				<div className="campaign-forms-admin__image-preview">
					{image ? <img alt="" src={image.contentUrl} /> : current.loading && <Loading />}

					<span className="text-secondary small">
						{image ? image.title : t('image-not-found')}
					</span>
				</div>
			) : null}

			<div className="btn-group">
				<Button onClick={() => setPicking(true)} small>
					{value ? t('change-image') : t('choose-image')}
				</Button>

				{value && !required && (
					<Button onClick={() => onChange(null)} small>
						{t('remove-image')}
					</Button>
				)}
			</div>

			{help && (
				<div className="form-text" id={`${id}-help`}>
					{help}
				</div>
			)}

			{error && (
				<div className="form-feedback-group">
					<div className="form-feedback-item" id={`${id}-error`}>
						{error}
					</div>
				</div>
			)}

			{picking && (
				<ImagePickerDialog
					onClose={() => setPicking(false)}
					onSelect={(selectedImage) => {
						setSelected(selectedImage);
						onChange(selectedImage.id);
					}}
				/>
			)}
		</div>
	);
}
