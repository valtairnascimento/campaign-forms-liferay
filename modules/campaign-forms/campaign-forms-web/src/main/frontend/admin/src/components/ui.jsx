import {forwardRef, useEffect, useId, useRef, useState} from 'react';
import {createPortal} from 'react-dom';

import {useApp} from '../AppContext.js';

export function Icon({className = '', symbol}) {
	const {config} = useApp();

	return (
		<svg
			aria-hidden="true"
			className={`lexicon-icon lexicon-icon-${symbol} ${className}`}
			focusable="false"
			role="presentation"
		>
			<use href={`${config.spritemap}#${symbol}`} />
		</svg>
	);
}

export function Button({
	busy = false,
	children,
	className = '',
	disabled = false,
	displayType = 'secondary',
	small = false,
	type = 'button',
	...otherProps
}) {
	return (
		<button
			aria-busy={busy || undefined}
			className={`btn btn-${displayType} ${small ? 'btn-sm' : ''} ${className}`}
			disabled={disabled || busy}
			type={type}
			{...otherProps}
		>
			{busy && (
				<span
					aria-hidden="true"
					className="campaign-forms-admin__spinner loading-animation loading-animation-sm"
				/>
			)}

			{children}
		</button>
	);
}

export function Alert({children, displayType = 'info', onClose, title}) {
	const {t} = useApp();

	return (
		<div
			className={`alert alert-${displayType} ${onClose ? 'alert-dismissible' : ''}`}
			role={displayType === 'danger' ? 'alert' : 'status'}
		>
			{title && <strong className="lead">{title} </strong>}

			{children}

			{onClose && (
				<button
					aria-label={t('close')}
					className="close"
					onClick={onClose}
					type="button"
				>
					<Icon symbol="times" />
				</button>
			)}
		</div>
	);
}

export function Loading() {
	const {t} = useApp();

	return (
		<div className="campaign-forms-admin__loading" role="status">
			<span aria-hidden="true" className="loading-animation" />
			<span className="sr-only">{t('loading')}</span>
		</div>
	);
}

export function StatusLabel({campaign}) {
	const {t} = useApp();

	const displayTypes = {
		ARCHIVED: 'info',
		DRAFT: 'secondary',
		PUBLISHED: 'success',
	};

	return (
		<>
			<span
				className={`label label-${displayTypes[campaign.status] || 'secondary'}`}
			>
				<span className="label-item label-item-expand">
					{t(`status-${String(campaign.status).toLowerCase()}`)}
				</span>
			</span>

			{campaign.hasUnpublishedChanges && (
				<span className="label label-warning">
					<span className="label-item label-item-expand">
						{t('unpublished-changes')}
					</span>
				</span>
			)}
		</>
	);
}

export function FormGroup({
	children,
	error,
	help,
	label,
	required = false,
}) {
	const id = useId();

	const describedBy = [help && `${id}-help`, error && `${id}-error`]
		.filter(Boolean)
		.join(' ');

	return (
		<div className={`form-group ${error ? 'has-error' : ''}`}>
			<label htmlFor={id}>
				{label}

				{required && (
					<>
						<span aria-hidden="true" className="reference-mark">
							<Icon symbol="asterisk" />
						</span>
						<RequiredText />
					</>
				)}
			</label>

			{children({
				'aria-describedby': describedBy || undefined,
				'aria-invalid': error ? true : undefined,
				'id': id,
				'required': required || undefined,
			})}

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
		</div>
	);
}

function RequiredText() {
	const {t} = useApp();

	return <span className="sr-only">{t('required')}</span>;
}

export function TextInput({
	error,
	help,
	label,
	onChange,
	required,
	value,
	...inputProps
}) {
	return (
		<FormGroup error={error} help={help} label={label} required={required}>
			{(fieldProps) => (
				<input
					className="form-control"
					onChange={(event) => onChange(event.target.value)}
					type="text"
					value={value === null || value === undefined ? '' : value}
					{...fieldProps}
					{...inputProps}
				/>
			)}
		</FormGroup>
	);
}

export const TextArea = forwardRef(function TextArea(
	{error, help, label, onChange, required, rows = 4, value, ...inputProps},
	ref
) {
	return (
		<FormGroup error={error} help={help} label={label} required={required}>
			{(fieldProps) => (
				<textarea
					className="form-control"
					onChange={(event) => onChange(event.target.value)}
					ref={ref}
					rows={rows}
					value={value || ''}
					{...fieldProps}
					{...inputProps}
				/>
			)}
		</FormGroup>
	);
});

export function Select({
	error,
	help,
	label,
	onChange,
	options,
	required,
	value,
	...selectProps
}) {
	return (
		<FormGroup error={error} help={help} label={label} required={required}>
			{(fieldProps) => (
				<select
					className="form-control"
					onChange={(event) => onChange(event.target.value)}
					value={value === null || value === undefined ? '' : value}
					{...fieldProps}
					{...selectProps}
				>
					{options.map((option) => (
						<option key={option.value} value={option.value}>
							{option.label}
						</option>
					))}
				</select>
			)}
		</FormGroup>
	);
}

export function Checkbox({checked, help, label, onChange, ...inputProps}) {
	const id = useId();

	return (
		<div className="form-group">
			<div className="custom-control custom-checkbox">
				<label htmlFor={id}>
					<input
						aria-describedby={help ? `${id}-help` : undefined}
						checked={Boolean(checked)}
						className="custom-control-input"
						id={id}
						onChange={(event) => onChange(event.target.checked)}
						type="checkbox"
						{...inputProps}
					/>

					<span className="custom-control-label">
						<span className="custom-control-label-text">{label}</span>
					</span>
				</label>
			</div>

			{help && (
				<div className="form-text" id={`${id}-help`}>
					{help}
				</div>
			)}
		</div>
	);
}

export function Modal({
	children,
	footer,
	onClose,
	size = 'lg',
	title,
}) {
	const {t} = useApp();

	const titleId = useId();

	const dialogRef = useRef(null);

	useEffect(() => {
		const previousActiveElement = document.activeElement;

		const dialog = dialogRef.current;

		const focusables = () =>
			Array.from(
				dialog.querySelectorAll(
					'a[href], button:not([disabled]), input:not([disabled]), select:not([disabled]), textarea:not([disabled]), [tabindex]:not([tabindex="-1"])'
				)
			);

		const firstField = dialog.querySelector(
			'.modal-body input:not([disabled]), .modal-body select, .modal-body textarea'
		);

		(firstField || focusables()[0] || dialog).focus();

		const handleKeyDown = (event) => {
			if (event.key === 'Escape') {
				event.stopPropagation();

				onClose();
			}
			else if (event.key === 'Tab') {
				const elements = focusables();

				if (!elements.length) {
					return;
				}

				const first = elements[0];
				const last = elements[elements.length - 1];

				if (event.shiftKey && document.activeElement === first) {
					event.preventDefault();

					last.focus();
				}
				else if (!event.shiftKey && document.activeElement === last) {
					event.preventDefault();

					first.focus();
				}
			}
		};

		dialog.addEventListener('keydown', handleKeyDown);

		return () => {
			dialog.removeEventListener('keydown', handleKeyDown);

			if (previousActiveElement && previousActiveElement.focus) {
				previousActiveElement.focus();
			}
		};
	}, []);

	return createPortal(
		<div
			className="campaign-forms-admin campaign-forms-admin__modal-root"
			onSubmit={(event) => event.stopPropagation()}
		>
			<div className="campaign-forms-admin__modal modal show" role="presentation">
				<div
					aria-labelledby={titleId}
					aria-modal="true"
					className={`modal-dialog modal-${size}`}
					ref={dialogRef}
					role="dialog"
					tabIndex={-1}
				>
					<div className="modal-content">
						<div className="modal-header">
							<div className="modal-title" id={titleId}>
								{title}
							</div>

							<button
								aria-label={t('close')}
								className="close"
								onClick={onClose}
								type="button"
							>
								<Icon symbol="times" />
							</button>
						</div>

						<div className="modal-body">{children}</div>

						{footer && (
							<div className="modal-footer">
								<div className="modal-item-last">
									<div className="btn-group">{footer}</div>
								</div>
							</div>
						)}
					</div>
				</div>
			</div>

			<div className="campaign-forms-admin__backdrop modal-backdrop show" />
		</div>,
		document.body
	);
}

export function ConfirmDialog({
	busy,
	confirmLabel,
	displayType = 'danger',
	message,
	onCancel,
	onConfirm,
	title,
}) {
	const {t} = useApp();

	return (
		<Modal
			footer={
				<>
					<div className="btn-group-item">
						<Button onClick={onCancel}>{t('cancel')}</Button>
					</div>
					<div className="btn-group-item">
						<Button busy={busy} displayType={displayType} onClick={onConfirm}>
							{confirmLabel}
						</Button>
					</div>
				</>
			}
			onClose={onCancel}
			size="md"
			title={title}
		>
			<p>{message}</p>
		</Modal>
	);
}

export function ActionsMenu({items, label}) {
	const [open, setOpen] = useState(false);

	const menuId = useId();

	const ref = useRef(null);

	const visibleItems = items.filter((item) => !item.hidden);

	useEffect(() => {
		if (!open) {
			return;
		}

		const handleClick = (event) => {
			if (ref.current && !ref.current.contains(event.target)) {
				setOpen(false);
			}
		};

		const handleKeyDown = (event) => {
			if (event.key === 'Escape') {
				setOpen(false);

				ref.current.querySelector('button').focus();
			}
		};

		document.addEventListener('mousedown', handleClick);
		document.addEventListener('keydown', handleKeyDown);

		const firstItem = ref.current.querySelector('.dropdown-item');

		if (firstItem) {
			firstItem.focus();
		}

		return () => {
			document.removeEventListener('mousedown', handleClick);
			document.removeEventListener('keydown', handleKeyDown);
		};
	}, [open]);

	if (!visibleItems.length) {
		return null;
	}

	return (
		<div className="dropdown campaign-forms-admin__actions" ref={ref}>
			<button
				aria-controls={menuId}
				aria-expanded={open}
				aria-haspopup="true"
				aria-label={label}
				className="btn btn-monospaced btn-sm btn-unstyled component-action"
				onClick={() => setOpen(!open)}
				title={label}
				type="button"
			>
				<Icon symbol="ellipsis-v" />
			</button>

			{open && (
				<ul className="dropdown-menu dropdown-menu-right show" id={menuId} role="menu">
					{visibleItems.map((item) => (
						<li key={item.label} role="none">
							<button
								className={`dropdown-item ${item.danger ? 'text-danger' : ''}`}
								onClick={() => {
									setOpen(false);

									item.onClick();
								}}
								role="menuitem"
								type="button"
							>
								{item.label}
							</button>
						</li>
					))}
				</ul>
			)}
		</div>
	);
}

export function Pagination({onChange, page, pageSize, totalCount}) {
	const {t} = useApp();

	const lastPage = Math.max(1, Math.ceil(totalCount / pageSize));

	if (lastPage <= 1) {
		return null;
	}

	return (
		<nav
			aria-label={t('pagination')}
			className="campaign-forms-admin__pagination pagination-bar"
		>
			<div className="pagination-results">
				{t('pagination-results', (page - 1) * pageSize + 1, Math.min(page * pageSize, totalCount), totalCount)}
			</div>

			<ul className="pagination">
				<li className={`page-item ${page <= 1 ? 'disabled' : ''}`}>
					<button
						aria-label={t('previous-page')}
						className="page-link"
						disabled={page <= 1}
						onClick={() => onChange(page - 1)}
						type="button"
					>
						<Icon symbol="angle-left" />
					</button>
				</li>

				<li className="page-item active">
					<span aria-current="page" className="page-link">
						{t('page-x-of-y', page, lastPage)}
					</span>
				</li>

				<li className={`page-item ${page >= lastPage ? 'disabled' : ''}`}>
					<button
						aria-label={t('next-page')}
						className="page-link"
						disabled={page >= lastPage}
						onClick={() => onChange(page + 1)}
						type="button"
					>
						<Icon symbol="angle-right" />
					</button>
				</li>
			</ul>
		</nav>
	);
}

export function EmptyState({action, description, title}) {
	return (
		<div className="c-empty-state campaign-forms-admin__empty">
			<div className="c-empty-state-text">
				<p className="c-empty-state-title">{title}</p>
				<p>{description}</p>
			</div>

			{action && <div className="c-empty-state-footer">{action}</div>}
		</div>
	);
}
