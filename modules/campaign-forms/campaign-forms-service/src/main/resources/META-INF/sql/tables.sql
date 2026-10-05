create table CF_AuditEntry (
	mvccVersion LONG default 0 not null,
	auditEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	campaignId LONG,
	action VARCHAR(75) null,
	className VARCHAR(255) null,
	classPK LONG,
	details TEXT null
);

create table CF_Campaign (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	campaignId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(150) null,
	description STRING null,
	slug VARCHAR(150) null,
	mode_ VARCHAR(20) null,
	status VARCHAR(20) null,
	startDate DATE null,
	endDate DATE null,
	submissionLimit INTEGER,
	seriesKey VARCHAR(75) null,
	instanceKey VARCHAR(75) null,
	sourceCampaignId LONG,
	retentionDays INTEGER,
	defaultLanguageId VARCHAR(10) null,
	publishedDate DATE null,
	publishedByUserId LONG,
	settings_ TEXT null
);

create table CF_CampaignQuota (
	mvccVersion LONG default 0 not null,
	campaignId LONG not null primary key,
	companyId LONG,
	used INTEGER
);

create table CF_DataFeed (
	mvccVersion LONG default 0 not null,
	dataFeedId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	feedKey VARCHAR(64) null,
	tokenHash VARCHAR(64) null,
	tokenHint VARCHAR(8) null,
	name VARCHAR(150) null,
	campaignId LONG,
	seriesKey VARCHAR(75) null,
	format VARCHAR(10) null,
	includeMetadata BOOLEAN,
	expirationDate DATE null,
	lastAccessDate DATE null,
	active_ BOOLEAN
);

create table CF_EventDelivery (
	mvccVersion LONG default 0 not null,
	eventDeliveryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	submissionId LONG,
	eventType VARCHAR(50) null,
	targetType VARCHAR(30) null,
	targetId LONG,
	status VARCHAR(20) null,
	attempts INTEGER,
	nextAttemptDate DATE null,
	lastErrorCode VARCHAR(75) null,
	lastErrorMessage VARCHAR(1000) null
);

create table CF_Form (
	mvccVersion LONG default 0 not null,
	formId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	formKey VARCHAR(75) null,
	name VARCHAR(150) null,
	draftVersionId LONG,
	publishedVersionId LONG
);

create table CF_FormField (
	mvccVersion LONG default 0 not null,
	formFieldId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	formVersionId LONG,
	fieldKey VARCHAR(50) null,
	type_ VARCHAR(30) null,
	label VARCHAR(500) null,
	placeholder VARCHAR(255) null,
	helpText VARCHAR(1000) null,
	required BOOLEAN,
	position INTEGER,
	defaultValue VARCHAR(1000) null,
	width VARCHAR(10) null,
	validation TEXT null,
	properties TEXT null,
	conditionalRule TEXT null,
	presetKey VARCHAR(50) null,
	personalData BOOLEAN
);

create table CF_FormFieldOption (
	mvccVersion LONG default 0 not null,
	formFieldOptionId LONG not null primary key,
	companyId LONG,
	formFieldId LONG,
	label VARCHAR(500) null,
	value VARCHAR(255) null,
	position INTEGER
);

create table CF_FormVersion (
	mvccVersion LONG default 0 not null,
	formVersionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	formId LONG,
	version INTEGER,
	status VARCHAR(20) null,
	submitLabel VARCHAR(150) null,
	successTitle VARCHAR(255) null,
	successMessage TEXT null,
	redirectUrl VARCHAR(2048) null,
	captchaEnabled BOOLEAN,
	requireAuthentication BOOLEAN,
	consentRequired BOOLEAN,
	consentText TEXT null,
	consentVersion VARCHAR(75) null,
	consentURL VARCHAR(2048) null,
	saveIncompleteSubmission BOOLEAN,
	settings_ TEXT null,
	publishedDate DATE null,
	publishedByUserId LONG
);

create table CF_IntegrationConfig (
	mvccVersion LONG default 0 not null,
	integrationConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	providerKey VARCHAR(50) null,
	name VARCHAR(150) null,
	enabled BOOLEAN,
	eventTypes VARCHAR(500) null,
	settings_ TEXT null,
	encryptedSecrets TEXT null
);

create table CF_NotificationConfig (
	mvccVersion LONG default 0 not null,
	notificationConfigId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	name VARCHAR(150) null,
	eventType VARCHAR(50) null,
	channel VARCHAR(30) null,
	enabled BOOLEAN,
	recipients VARCHAR(2000) null,
	cc VARCHAR(2000) null,
	bcc VARCHAR(2000) null,
	fromName VARCHAR(150) null,
	replyTo VARCHAR(255) null,
	subjectTemplate VARCHAR(500) null,
	bodyTemplate TEXT null,
	settings_ TEXT null
);

create table CF_PageBlock (
	mvccVersion LONG default 0 not null,
	pageBlockId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	pageConfigurationId LONG,
	blockType VARCHAR(30) null,
	blockKey VARCHAR(50) null,
	position INTEGER,
	enabled BOOLEAN,
	schemaVersion INTEGER,
	configuration TEXT null
);

create table CF_PageConfiguration (
	mvccVersion LONG default 0 not null,
	pageConfigurationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	stage VARCHAR(20) null,
	templateKey VARCHAR(75) null,
	themeKey VARCHAR(75) null,
	cssClass VARCHAR(75) null,
	seoTitle VARCHAR(255) null,
	seoDescription VARCHAR(1000) null,
	canonicalUrl VARCHAR(2048) null,
	noIndex BOOLEAN,
	ogTitle VARCHAR(255) null,
	ogDescription VARCHAR(1000) null,
	ogImageFileEntryId LONG,
	settings_ TEXT null
);

create table CF_PageTemplate (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	pageTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	templateKey VARCHAR(75) null,
	name VARCHAR(150) null,
	description VARCHAR(1000) null,
	systemTemplate BOOLEAN,
	blocks TEXT null
);

create table CF_Submission (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	submissionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	campaignId LONG,
	formId LONG,
	formVersionId LONG,
	status VARCHAR(20) null,
	sourceUrl VARCHAR(2048) null,
	referrer VARCHAR(2048) null,
	userAgent VARCHAR(500) null,
	languageId VARCHAR(10) null,
	ipHash VARCHAR(64) null,
	utmSource VARCHAR(255) null,
	utmMedium VARCHAR(255) null,
	utmCampaign VARCHAR(255) null,
	utmContent VARCHAR(255) null,
	utmTerm VARCHAR(255) null,
	consentVersion VARCHAR(75) null,
	consentDate DATE null,
	idempotencyKey VARCHAR(64) null,
	metadata TEXT null,
	payloadSnapshot TEXT null
);

create table CF_SubmissionValue (
	mvccVersion LONG default 0 not null,
	submissionValueId LONG not null primary key,
	companyId LONG,
	campaignId LONG,
	submissionId LONG,
	formFieldId LONG,
	fieldKey VARCHAR(50) null,
	valueType VARCHAR(20) null,
	valueIndex INTEGER,
	stringValue VARCHAR(500) null,
	textValue TEXT null,
	numberValue DECIMAL(30, 16) null,
	dateValue DATE null,
	booleanValue BOOLEAN
);