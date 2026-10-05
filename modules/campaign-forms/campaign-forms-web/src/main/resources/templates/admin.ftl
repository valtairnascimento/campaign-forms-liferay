<#ftl output_format="HTML">

<div class="campaign-forms-admin" data-campaign-forms-admin-config="${config}" id="${rootId}">
	<div class="campaign-forms-admin__loading" role="status">
		<span aria-hidden="true" class="loading-animation"></span>
		<span class="sr-only">${loadingLabel}</span>
	</div>
</div>

<noscript>
	<div class="alert alert-warning">${noscript}</div>
</noscript>

<script src="${scriptURL}"></script>
<script>
	window.CampaignFormsAdmin && window.CampaignFormsAdmin.mountAll();
</script>
