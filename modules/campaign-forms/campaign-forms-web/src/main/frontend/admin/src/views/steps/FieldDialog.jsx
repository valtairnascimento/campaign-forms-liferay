import {useState} from 'react';

import {getErrorMessage} from '../../api/client.js';
import {useApp} from '../../AppContext.js';
import {
	Alert,
	Button,
	Checkbox,
	Icon,
	Modal,
	Select,
	TextArea,
	TextInput,
} from '../../components/ui.jsx';
import {FIELD_KEY_PATTERN, toFieldKey, toOptionValue} from '../../util/text.js';
import {
	FIELD_TYPE_ORDER,
	PLACEHOLDER_TYPES,
	SUPPORTED_RULES,
	TEXT_PATTERNS,
	WIDTHS,
	moveItem,
	toFieldPayload,
	toValidation,
} from './fieldModel.js';

function toRuleValues(validation) {
	const rules = {};

	Object.keys(validation || {}).forEach((rule) => {
		const value = validation[rule];

		rules[rule] = typeof value === 'boolean' ? value : String(value);
	});

	return rules;
}

let optionSequence = 0;

function newOption(label = '', value = '') {
	optionSequence += 1;

	return {id: `option-${optionSequence}`, label, persisted: Boolean(value), value};
}

export function validateField(values, {existingKeys, optionBased, t}) {
	const errors = {};

	if (!values.label.trim()) {
		errors.label = t('field-required');
	}

	if (!FIELD_KEY_PATTERN.test(values.fieldKey) || values.fieldKey.length > 50) {
		errors.fieldKey = t('field-key-invalid');
	}
	else if (existingKeys.includes(values.fieldKey)) {
		errors.fieldKey = t('field-key-in-use');
	}

	if (optionBased) {
		const filled = values.options.filter((option) => option.label.trim());

		const optionValues = filled.map(
			(option) => option.value || toOptionValue(option.label)
		);

		if (!filled.length) {
			errors.options = t('options-required');
		}
		else if (new Set(optionValues).size !== optionValues.length) {
			errors.options = t('options-duplicated');
		}
		else if (optionValues.some((value) => !value)) {
			errors.options = t('options-invalid');
		}
	}

	if (values.type === 'hidden' && !values.defaultValue.trim()) {
		errors.defaultValue = t('field-required');
	}

	return errors;
}

export default function FieldDialog({
	existingKeys,
	field,
	fieldTypes,
	onClose,
	onSave,
}) {
	const {t} = useApp();

	const editing = Boolean(field);

	const [values, setValues] = useState(() => ({
		defaultValue: (field && field.defaultValue) || '',
		fieldKey: (field && field.fieldKey) || '',
		helpText: (field && field.helpText) || '',
		label: (field && field.label) || '',
		options: ((field && field.fieldOptions) || []).map((option) =>
			newOption(option.label, option.value)
		),
		personalData: field ? Boolean(field.personalData) : false,
		placeholder: (field && field.placeholder) || '',
		required: field ? Boolean(field.required) : false,
		rules: toRuleValues(field && field.validation),
		type: (field && field.type) || 'text',
		width: (field && field.width) || 'FULL',
	}));

	const [keyEdited, setKeyEdited] = useState(editing);
	const [errors, setErrors] = useState({});
	const [error, setError] = useState(null);
	const [busy, setBusy] = useState(false);

	const fieldType = fieldTypes.find((type) => type.key === values.type) || {};

	const optionBased = Boolean(fieldType.optionBased);

	const set = (key) => (value) => setValues((previous) => ({...previous, [key]: value}));

	const setRule = (rule) => (value) =>
		setValues((previous) => ({
			...previous,
			rules: {...previous.rules, [rule]: value},
		}));

	const setOptions = (options) => setValues((previous) => ({...previous, options}));

	const updateOption = (index, label) =>
		setOptions(
			values.options.map((option, optionIndex) =>
				optionIndex === index ? {...option, label} : option
			)
		);

	const supportedRules = SUPPORTED_RULES[values.type] || [];

	const typeOptions = FIELD_TYPE_ORDER.filter((key) =>
		fieldTypes.some((type) => type.key === key)
	).map((key) => ({label: t(`field-type-${key}`), value: key}));

	const submit = async (event) => {
		event.preventDefault();

		const validationErrors = validateField(values, {
			existingKeys: editing ? [] : existingKeys,
			optionBased,
			t,
		});

		setErrors(validationErrors);

		if (Object.keys(validationErrors).length) {
			return;
		}

		const payload = toFieldPayload(field || {}, {
			defaultValue: values.type === 'hidden' ? values.defaultValue.trim() : field && field.defaultValue,
			fieldKey: values.fieldKey,
			fieldOptions: optionBased
				? values.options
						.filter((option) => option.label.trim())
						.map((option) => ({
							label: option.label.trim(),
							value: option.value || toOptionValue(option.label),
						}))
				: [],
			helpText: values.helpText.trim(),
			label: values.label.trim(),
			personalData: values.personalData,
			placeholder: PLACEHOLDER_TYPES.includes(values.type) ? values.placeholder.trim() : '',
			required: values.type === 'hidden' ? false : values.required,
			type: values.type,
			validation: toValidation(values.type, values.rules),
			width: values.width,
		});

		setBusy(true);
		setError(null);

		try {
			await onSave(payload);

			onClose();
		}
		catch (saveError) {
			setErrors(saveError.fieldErrors || {});
			setError(saveError);
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
						<Button busy={busy} displayType="primary" form="cfFieldForm" type="submit">
							{editing ? t('save') : t('add-field')}
						</Button>
					</div>
				</>
			}
			onClose={onClose}
			title={editing ? t('edit-field-x', field.label) : t('new-field')}
		>
			<form id="cfFieldForm" noValidate onSubmit={submit}>
				{error && <Alert displayType="danger">{getErrorMessage(t, error)}</Alert>}

				<TextInput
					error={errors.label}
					label={t('field-label')}
					maxLength={255}
					onChange={(label) =>
						setValues((previous) => ({
							...previous,
							fieldKey: keyEdited ? previous.fieldKey : toFieldKey(label),
							label,
						}))
					}
					required
					value={values.label}
				/>

				<div className="row">
					<div className="col-md-6">
						<Select
							disabled={editing}
							help={editing ? t('field-type-fixed') : null}
							label={t('field-type')}
							onChange={set('type')}
							options={typeOptions}
							required
							value={values.type}
						/>
					</div>
					<div className="col-md-6">
						<TextInput
							disabled={editing}
							error={errors.fieldKey}
							help={editing ? t('field-key-fixed') : t('field-key-help')}
							label={t('field-key')}
							maxLength={50}
							onChange={(fieldKey) => {
								setKeyEdited(true);
								set('fieldKey')(fieldKey);
							}}
							required
							value={values.fieldKey}
						/>
					</div>
				</div>

				{values.type === 'hidden' ? (
					<TextInput
						error={errors.defaultValue}
						help={t('hidden-value-help')}
						label={t('hidden-value')}
						onChange={set('defaultValue')}
						required
						value={values.defaultValue}
					/>
				) : (
					<>
						<TextArea
							label={t('field-help-text')}
							maxLength={1000}
							onChange={set('helpText')}
							rows={2}
							value={values.helpText}
						/>

						{PLACEHOLDER_TYPES.includes(values.type) && (
							<TextInput
								help={t('field-placeholder-help')}
								label={t('field-placeholder')}
								onChange={set('placeholder')}
								value={values.placeholder}
							/>
						)}

						<Select
							label={t('field-width')}
							onChange={set('width')}
							options={WIDTHS.map((width) => ({
								label: t(`width-${width.toLowerCase()}`),
								value: width,
							}))}
							value={values.width}
						/>

						<Checkbox
							checked={values.required}
							label={t('field-required-checkbox')}
							onChange={set('required')}
						/>
					</>
				)}

				<Checkbox
					checked={values.personalData}
					help={t('personal-data-help')}
					label={t('personal-data')}
					onChange={set('personalData')}
				/>

				{optionBased && (
					<fieldset className="campaign-forms-admin__options-editor">
						<legend className="h5">{t('options')}</legend>

						{errors.options && (
							<div className="form-feedback-item text-danger" role="alert">
								{errors.options}
							</div>
						)}

						<ol className="list-unstyled">
							{values.options.map((option, index) => (
								<li className="campaign-forms-admin__option-row" key={option.id}>
									<div className="campaign-forms-admin__option-label">
										<label className="sr-only" htmlFor={`cfOption_${option.id}`}>
											{t('option-x', index + 1)}
										</label>
										<input
											className="form-control"
											id={`cfOption_${option.id}`}
											onChange={(event) => updateOption(index, event.target.value)}
											placeholder={t('option-x', index + 1)}
											type="text"
											value={option.label}
										/>
										<span className="text-secondary small">
											{option.value || toOptionValue(option.label)}
										</span>
									</div>

									<Button
										aria-label={t('move-up-x', option.label || index + 1)}
										className="btn-monospaced"
										disabled={index === 0}
										displayType="unstyled"
										onClick={() => setOptions(moveItem(values.options, index, -1))}
										small
									>
										<Icon symbol="angle-up" />
									</Button>
									<Button
										aria-label={t('move-down-x', option.label || index + 1)}
										className="btn-monospaced"
										disabled={index === values.options.length - 1}
										displayType="unstyled"
										onClick={() => setOptions(moveItem(values.options, index, 1))}
										small
									>
										<Icon symbol="angle-down" />
									</Button>
									<Button
										aria-label={t('remove-x', option.label || index + 1)}
										className="btn-monospaced"
										displayType="unstyled"
										onClick={() =>
											setOptions(values.options.filter((_, optionIndex) => optionIndex !== index))
										}
										small
									>
										<Icon symbol="trash" />
									</Button>
								</li>
							))}
						</ol>

						<Button onClick={() => setOptions([...values.options, newOption()])} small>
							<Icon className="inline-item inline-item-before" symbol="plus" />
							{t('add-option')}
						</Button>
					</fieldset>
				)}

				{supportedRules.length > 0 && (
					<fieldset>
						<legend className="h5">{t('validation')}</legend>

						<div className="row">
							{supportedRules.includes('minLength') && (
								<div className="col-md-6">
									<TextInput
										label={t('rule-min-length')}
										min={0}
										onChange={setRule('minLength')}
										type="number"
										value={values.rules.minLength}
									/>
								</div>
							)}
							{supportedRules.includes('maxLength') && (
								<div className="col-md-6">
									<TextInput
										label={t('rule-max-length')}
										min={1}
										onChange={setRule('maxLength')}
										type="number"
										value={values.rules.maxLength}
									/>
								</div>
							)}
							{supportedRules.includes('min') && (
								<div className="col-md-6">
									<TextInput
										label={values.type === 'date' ? t('rule-min-date') : t('rule-min')}
										onChange={setRule('min')}
										type={values.type === 'date' ? 'date' : 'number'}
										value={values.rules.min}
									/>
								</div>
							)}
							{supportedRules.includes('max') && (
								<div className="col-md-6">
									<TextInput
										label={values.type === 'date' ? t('rule-max-date') : t('rule-max')}
										onChange={setRule('max')}
										type={values.type === 'date' ? 'date' : 'number'}
										value={values.rules.max}
									/>
								</div>
							)}
							{supportedRules.includes('minSelected') && (
								<div className="col-md-6">
									<TextInput
										label={t('rule-min-selected')}
										min={0}
										onChange={setRule('minSelected')}
										type="number"
										value={values.rules.minSelected}
									/>
								</div>
							)}
							{supportedRules.includes('maxSelected') && (
								<div className="col-md-6">
									<TextInput
										label={t('rule-max-selected')}
										min={1}
										onChange={setRule('maxSelected')}
										type="number"
										value={values.rules.maxSelected}
									/>
								</div>
							)}
						</div>

						{supportedRules.includes('pattern') && (
							<Select
								label={t('rule-pattern')}
								onChange={setRule('pattern')}
								options={[
									{label: t('pattern-none'), value: ''},
									...TEXT_PATTERNS.map((pattern) => ({
										label: t(`pattern-${pattern}`),
										value: pattern,
									})),
								]}
								value={values.rules.pattern || ''}
							/>
						)}

						{supportedRules.includes('integer') && (
							<Checkbox
								checked={values.rules.integer}
								label={t('rule-integer')}
								onChange={setRule('integer')}
							/>
						)}
					</fieldset>
				)}
			</form>
		</Modal>
	);
}
