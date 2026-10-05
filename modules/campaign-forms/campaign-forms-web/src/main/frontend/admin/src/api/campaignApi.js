export function createCampaignApi(client, siteId, deliveryClient = null) {
	const items = (page) => (page && page.items) || [];

	return {
		addBlock: (campaignId, block) =>
			client.post(`/campaigns/${campaignId}/page/blocks`, block),
		addCampaign: (campaign) =>
			client.post(`/sites/${siteId}/campaigns`, campaign),
		addDataFeed: (campaignId, dataFeed) =>
			client.post(`/campaigns/${campaignId}/data-feeds`, dataFeed),
		addField: (campaignId, field) =>
			client.post(`/campaigns/${campaignId}/form/fields`, field),
		addNotification: (campaignId, notification) =>
			client.post(`/campaigns/${campaignId}/notifications`, notification),
		applyPageTemplate: (campaignId, templateKey) =>
			client.post(`/campaigns/${campaignId}/page/apply-template`, {
				templateKey,
			}),
		archiveCampaign: (campaignId) =>
			client.post(`/campaigns/${campaignId}/archive`),
		convertToCustomPage: (campaignId) =>
			client.post(`/campaigns/${campaignId}/convert-to-custom-page`),
		createFormDraft: (campaignId) =>
			client.post(`/campaigns/${campaignId}/form/draft`),
		deleteBlock: (pageBlockId) => client.delete(`/page-blocks/${pageBlockId}`),
		deleteCampaign: (campaignId) => client.delete(`/campaigns/${campaignId}`),
		deleteDataFeed: (dataFeedId) => client.delete(`/data-feeds/${dataFeedId}`),
		deleteField: (formFieldId) => client.delete(`/form-fields/${formFieldId}`),
		deleteNotification: (notificationId) =>
			client.delete(`/notifications/${notificationId}`),
		deleteSubmission: (submissionId) =>
			client.delete(`/submissions/${submissionId}`),
		duplicateBlock: (pageBlockId) =>
			client.post(`/page-blocks/${pageBlockId}/duplicate`),
		duplicateCampaign: (campaignId, duplicationRequest) =>
			client.post(`/campaigns/${campaignId}/duplicate`, duplicationRequest),

		exportSubmissions: (
			campaignId,
			{format = 'csv', includeMetadata, labels, separator} = {}
		) =>
			client.download(`/campaigns/${campaignId}/submissions/export`, {
				format,
				includeMetadata,
				labels,
				separator,
			}),
		getBlockTypes: (campaignId) =>
			client.get(`/campaigns/${campaignId}/block-types`).then(items),
		getCampaign: (campaignId) => client.get(`/campaigns/${campaignId}`),
		getCampaigns: ({page, pageSize}) =>
			client.get(`/sites/${siteId}/campaigns`, {page, pageSize}),
		getDataFeeds: (campaignId) =>
			client.get(`/campaigns/${campaignId}/data-feeds`).then(items),
		getFieldPresets: () => client.get('/field-presets').then(items),
		getFieldTypes: () => client.get('/field-types').then(items),
		getForm: (campaignId) => client.get(`/campaigns/${campaignId}/form`),

		getImage: (documentId) =>
			deliveryClient.get(`/documents/${documentId}`, {
				fields: 'contentUrl,encodingFormat,id,title',
			}),

		getImages: ({page, pageSize, search}) =>
			deliveryClient.get(`/sites/${siteId}/documents`, {
				fields: 'contentUrl,encodingFormat,id,title',
				filter: "startswith(encodingFormat, 'image/')",
				flatten: true,
				page,
				pageSize,
				search,
				sort: 'dateModified:desc',
			}),
		getNotifications: (campaignId) =>
			client.get(`/campaigns/${campaignId}/notifications`).then(items),
		getPage: (campaignId) => client.get(`/campaigns/${campaignId}/page`),
		getPageTemplates: () => client.get('/page-templates').then(items),
		getPublicationIssues: (campaignId) =>
			client
				.get(`/campaigns/${campaignId}/publication-issues`)
				.then(items),
		getSubmissionDeliveries: (submissionId) =>
			client
				.get(`/submissions/${submissionId}/event-deliveries`)
				.then(items),
		getSubmissions: (campaignId, {page, pageSize}) =>
			client.get(`/campaigns/${campaignId}/submissions`, {
				page,
				pageSize,
			}),
		publishCampaign: (campaignId) =>
			client.post(`/campaigns/${campaignId}/publish`),
		regenerateDataFeedToken: (dataFeedId) =>
			client.post(`/data-feeds/${dataFeedId}/regenerate-token`),
		reopenCampaign: (campaignId) =>
			client.post(`/campaigns/${campaignId}/reopen`),
		reorderBlocks: (campaignId, ids) =>
			client.put(`/campaigns/${campaignId}/page/blocks/order`, {ids}),
		reorderFields: (campaignId, ids) =>
			client.put(`/campaigns/${campaignId}/form/fields/order`, {ids}),
		sendTestNotification: (notificationId) =>
			client.post(`/notifications/${notificationId}/test`),
		updateBlock: (pageBlockId, block) =>
			client.put(`/page-blocks/${pageBlockId}`, block),
		updateCampaign: (campaignId, campaign) =>
			client.put(`/campaigns/${campaignId}`, campaign),
		updateField: (formFieldId, field) =>
			client.put(`/form-fields/${formFieldId}`, field),
		updateForm: (campaignId, formVersion) =>
			client.put(`/campaigns/${campaignId}/form`, formVersion),
		updateNotification: (notificationId, notification) =>
			client.put(`/notifications/${notificationId}`, notification),
		updatePage: (campaignId, page) =>
			client.put(`/campaigns/${campaignId}/page`, page),

		uploadImage: (file) => {
			const formData = new FormData();

			formData.append(
				'document',
				JSON.stringify({title: file.name, viewableBy: 'Anyone'})
			);
			formData.append('file', file);

			return deliveryClient.upload(`/sites/${siteId}/documents`, formData);
		},
	};
}
