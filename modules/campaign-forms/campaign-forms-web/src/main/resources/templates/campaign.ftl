<#ftl output_format="HTML">

<#macro requiredMarker>
	<span aria-hidden="true" class="campaign-forms-form__required">*</span>
	<span class="sr-only">${messages["required"]}</span>
</#macro>

<#macro describedBy field>
	<#local ids = field.errorId>
	<#if field.helpText?has_content>
		<#local ids = field.helpId + " " + ids>
	</#if>
	aria-describedby="${ids}"
</#macro>

<#macro fieldFeedback field>
	<#if field.helpText?has_content>
		<p class="campaign-forms-form__help form-text" id="${field.helpId}">${field.helpText}</p>
	</#if>
	<p class="campaign-forms-form__error form-feedback-item" data-cf-error-for="${field.fieldKey}" hidden id="${field.errorId}"></p>
</#macro>

<#macro inputField field>
	<#if field.inputType == "hidden">
		<input name="${field.fieldKey}" type="hidden" value="${field.defaultValue}" />
		<#return>
	</#if>

	<div class="campaign-forms-form__field form-group ${field.widthClass}" data-cf-field="${field.fieldKey}">
		<label class="campaign-forms-form__label control-label" for="${field.inputId}">
			${field.label}<#if field.required> <@requiredMarker /></#if>
		</label>

		<input
			<#if field.autocomplete?has_content>autocomplete="${field.autocomplete}"</#if>
			<@describedBy field=field />
			class="campaign-forms-form__input form-control"
			id="${field.inputId}"
			<#if field.inputType == "number" && field.max?has_content>max="${field.max}"</#if>
			<#if field.maxLength?has_content>maxlength="${field.maxLength}"</#if>
			<#if field.inputType == "number" && field.min?has_content>min="${field.min}"</#if>
			<#if field.inputType == "date" && field.max?has_content>max="${field.max}"</#if>
			<#if field.inputType == "date" && field.min?has_content>min="${field.min}"</#if>
			name="${field.fieldKey}"
			<#if field.placeholder?has_content>placeholder="${field.placeholder}"</#if>
			<#if field.required>required</#if>
			<#if field.inputType == "number">step="${field.step}"</#if>
			type="${field.inputType}"
			value="${field.defaultValue}"
		/>

		<@fieldFeedback field=field />
	</div>
</#macro>

<#macro textareaField field>
	<div class="campaign-forms-form__field form-group ${field.widthClass}" data-cf-field="${field.fieldKey}">
		<label class="campaign-forms-form__label control-label" for="${field.inputId}">
			${field.label}<#if field.required> <@requiredMarker /></#if>
		</label>

		<textarea
			<@describedBy field=field />
			class="campaign-forms-form__input form-control"
			id="${field.inputId}"
			<#if field.maxLength?has_content>maxlength="${field.maxLength}"</#if>
			name="${field.fieldKey}"
			<#if field.placeholder?has_content>placeholder="${field.placeholder}"</#if>
			<#if field.required>required</#if>
			rows="5"
		>${field.defaultValue}</textarea>

		<@fieldFeedback field=field />
	</div>
</#macro>

<#macro selectField field multiple>
	<div class="campaign-forms-form__field form-group ${field.widthClass}" data-cf-field="${field.fieldKey}">
		<label class="campaign-forms-form__label control-label" for="${field.inputId}">
			${field.label}<#if field.required> <@requiredMarker /></#if>
		</label>

		<select
			<#if field.autocomplete?has_content>autocomplete="${field.autocomplete}"</#if>
			<@describedBy field=field />
			class="campaign-forms-form__input form-control"
			id="${field.inputId}"
			<#if multiple>multiple</#if>
			name="${field.fieldKey}"
			<#if field.required>required</#if>
		>
			<#if !multiple>
				<option value="">${messages["select-an-option"]}</option>
			</#if>

			<#list field.options as option>
				<option <#if option.value == field.defaultValue>selected</#if> value="${option.value}">${option.label}</option>
			</#list>
		</select>

		<@fieldFeedback field=field />
	</div>
</#macro>

<#macro choiceField field inputType>
	<fieldset
		<@describedBy field=field />
		class="campaign-forms-form__field campaign-forms-form__fieldset form-group ${field.widthClass}"
		data-cf-field="${field.fieldKey}"
		id="${field.inputId}"
	>
		<legend class="campaign-forms-form__label control-label">
			${field.label}<#if field.required> <@requiredMarker /></#if>
		</legend>

		<#list field.options as option>
			<div class="campaign-forms-form__option custom-control custom-${inputType}">
				<input
					class="campaign-forms-form__choice custom-control-input"
					<#if option.value == field.defaultValue>checked</#if>
					id="${option.id}"
					name="${field.fieldKey}"
					<#if field.required && inputType == "radio">required</#if>
					type="${inputType}"
					value="${option.value}"
				/>

				<label class="custom-control-label" for="${option.id}">${option.label}</label>
			</div>
		</#list>

		<@fieldFeedback field=field />
	</fieldset>
</#macro>

<#macro booleanField field>
	<div class="campaign-forms-form__field form-group ${field.widthClass}" data-cf-field="${field.fieldKey}">
		<div class="custom-control custom-checkbox">
			<input
				<@describedBy field=field />
				class="campaign-forms-form__choice custom-control-input"
				<#if field.defaultValue == "true">checked</#if>
				id="${field.inputId}"
				name="${field.fieldKey}"
				<#if field.required>required</#if>
				type="checkbox"
				value="true"
			/>

			<label class="custom-control-label" for="${field.inputId}">
				${field.label}<#if field.required> <@requiredMarker /></#if>
			</label>
		</div>

		<@fieldFeedback field=field />
	</div>
</#macro>

<#macro campaignForm>
	<#if closedMessage?has_content>
		<div class="campaign-forms__closed alert alert-info" role="status">${closedMessage}</div>
		<#return>
	</#if>

	<noscript>
		<div class="alert alert-warning">${messages["noscript"]}</div>
	</noscript>

	<form
		class="campaign-forms-form"
		data-campaign-forms-config="${form.config}"
		id="${form.id}"
		novalidate
	>
		<div class="campaign-forms-form__summary alert alert-danger" hidden role="alert" tabindex="-1"></div>

		<div class="campaign-forms-form__fields">
			<#list form.fields as field>
				<#if field.control == "textarea">
					<@textareaField field=field />
				<#elseif field.control == "select">
					<@selectField field=field multiple=false />
				<#elseif field.control == "multiSelect">
					<@selectField field=field multiple=true />
				<#elseif field.control == "radio">
					<@choiceField field=field inputType="radio" />
				<#elseif field.control == "checkbox">
					<@choiceField field=field inputType="checkbox" />
				<#elseif field.control == "boolean">
					<@booleanField field=field />
				<#else>
					<@inputField field=field />
				</#if>
			</#list>
		</div>

		<#if form.consentRequired>
			<div class="campaign-forms-form__field campaign-forms-form__consent form-group" data-cf-field="consent">
				<div class="custom-control custom-checkbox">
					<input
						aria-describedby="<#if form.consentURL?has_content>${form.consentId}_link </#if>${form.consentId}_error"
						class="campaign-forms-form__choice custom-control-input"
						id="${form.consentId}"
						name="consent"
						required
						type="checkbox"
						value="true"
					/>

					<label class="custom-control-label" for="${form.consentId}">
						${form.consentText} <@requiredMarker />
					</label>
				</div>

				<#if form.consentURL?has_content>
					<p class="campaign-forms-form__consent-link form-text" id="${form.consentId}_link">
						<a href="${form.consentURL}" rel="noopener" target="_blank">${messages["consent-privacy-link"]}<span class="sr-only"> ${messages["opens-in-new-tab"]}</span></a>
					</p>
				</#if>

				<p class="campaign-forms-form__error form-feedback-item" data-cf-error-for="consent" hidden id="${form.consentId}_error"></p>
			</div>
		</#if>

		<div aria-hidden="true" class="campaign-forms-form__honeypot">
			<label for="${form.id}_hp">${messages["honeypot-label"]}</label>
			<input autocomplete="off" data-cf-honeypot id="${form.id}_hp" name="${form.honeypotName}" tabindex="-1" type="text" value="" />
		</div>

		<div class="campaign-forms-form__actions">
			<button class="campaign-forms-form__submit btn btn-primary" type="submit">${form.submitLabel}</button>
		</div>
	</form>

	<div class="campaign-forms-form__status" hidden role="status" tabindex="-1">
		<h2 class="campaign-forms-form__status-title"></h2>
		<p class="campaign-forms-form__status-message"></p>
	</div>
</#macro>

<#macro blockButton label url>
	<#if label?has_content && url?has_content>
		<a class="btn btn-primary campaign-forms__button" href="${url}">${label}</a>
	</#if>
</#macro>

<#macro heroBlock block level>
	<section
		aria-labelledby="${block.blockKey}_title"
		class="campaign-forms__block campaign-forms-hero campaign-forms-hero--${block.style} campaign-forms-hero--${block.align}<#if block.imageUrl?has_content> campaign-forms-hero--image</#if>"
		id="${block.blockKey}"
	>
		<#if block.imageUrl?has_content>
			<img alt="" class="campaign-forms-hero__image" src="${block.imageUrl}" />
		</#if>

		<div class="campaign-forms-hero__content">
			<${level} class="campaign-forms-hero__title" id="${block.blockKey}_title">${block.title}</${level}>

			<#if block.subtitle?has_content>
				<p class="campaign-forms-hero__subtitle">${block.subtitle}</p>
			</#if>

			<@blockButton label=block.buttonLabel url=block.buttonUrl />
		</div>
	</section>
</#macro>

<#macro sectionOpen block extraClass>
	<section
		<#if block.title?has_content>aria-labelledby="${block.blockKey}_title"</#if>
		class="campaign-forms__block ${extraClass}"
		id="${block.blockKey}"
	>
		<#if block.title?has_content>
			<h2 class="campaign-forms__block-title" id="${block.blockKey}_title">${block.title}</h2>
		</#if>
</#macro>

<#macro textBlock block>
	<@sectionOpen block=block extraClass="campaign-forms-text" />
		<div class="campaign-forms-text__body">${block.bodyHtml?no_esc}</div>
	</section>
</#macro>

<#macro imageBlock block>
	<figure class="campaign-forms__block campaign-forms-image campaign-forms-image--${block.size}" id="${block.blockKey}">
		<img alt="${block.alt}" class="campaign-forms-image__img" loading="lazy" src="${block.imageUrl}" />

		<#if block.caption?has_content>
			<figcaption class="campaign-forms-image__caption">${block.caption}</figcaption>
		</#if>
	</figure>
</#macro>

<#macro ctaBlock block>
	<@sectionOpen block=block extraClass="campaign-forms-cta campaign-forms-cta--${block.style}" />
		<#if block.textHtml?has_content>
			<div class="campaign-forms-cta__text">${block.textHtml?no_esc}</div>
		</#if>

		<@blockButton label=block.buttonLabel url=block.buttonUrl />
	</section>
</#macro>

<#macro faqBlock block>
	<@sectionOpen block=block extraClass="campaign-forms-faq" />
		<div class="campaign-forms-faq__items">
			<#list block.items as item>
				<details class="campaign-forms-faq__item">
					<summary class="campaign-forms-faq__question">${item.question}</summary>

					<div class="campaign-forms-faq__answer">${item.answerHtml?no_esc}</div>
				</details>
			</#list>
		</div>
	</section>
</#macro>

<#macro formBlock block>
	<@sectionOpen block=block extraClass="campaign-forms__block--form" />
		<#if block.descriptionHtml?has_content>
			<div class="campaign-forms__block-description">${block.descriptionHtml?no_esc}</div>
		</#if>

		<@campaignForm />
	</section>
</#macro>

<div class="campaign-forms campaign-forms--theme-${themeKey!"default"}">
	<#if preview!false>
		<div class="campaign-forms__preview alert alert-warning" role="status">${messages["preview-banner"]}</div>
	</#if>

	<#if notFound>
		<div class="campaign-forms__not-found alert alert-info">${messages["campaign-not-found"]}</div>
	<#elseif campaign.custom>
		<#if pageHeading>
			<h1 class="campaign-forms__title">${campaign.name}</h1>
		</#if>

		<#list blocks as block>
			<#if block.type == "HERO">
				<@heroBlock block=block level=(block?is_first && !pageHeading)?then("h1", "h2") />
			<#elseif block.type == "TEXT">
				<@textBlock block=block />
			<#elseif block.type == "IMAGE">
				<@imageBlock block=block />
			<#elseif block.type == "CTA">
				<@ctaBlock block=block />
			<#elseif block.type == "FAQ">
				<@faqBlock block=block />
			<#elseif block.form>
				<@formBlock block=block />
			</#if>
		</#list>
	<#else>
		<header class="campaign-forms__header">
			<h1 class="campaign-forms__title">${campaign.name}</h1>

			<#if campaign.description?has_content>
				<p class="campaign-forms__description">${campaign.description}</p>
			</#if>
		</header>

		<section class="campaign-forms__block campaign-forms__block--form" id="form">
			<@campaignForm />
		</section>
	</#if>
</div>
