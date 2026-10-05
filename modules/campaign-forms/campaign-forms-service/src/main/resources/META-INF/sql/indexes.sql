create index IX_9A4BA58 on CF_AuditEntry (campaignId);
create index IX_C6D092F1 on CF_AuditEntry (groupId);

create index IX_DAEBB5C2 on CF_Campaign (companyId, seriesKey[$COLUMN_LENGTH:75$]);
create unique index IX_8B1CED34 on CF_Campaign (externalReferenceCode[$COLUMN_LENGTH:75$], groupId);
create unique index IX_F56D0502 on CF_Campaign (groupId, slug[$COLUMN_LENGTH:150$]);
create index IX_220797E on CF_Campaign (groupId, status[$COLUMN_LENGTH:20$]);
create index IX_DFBD6FC6 on CF_Campaign (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_62F8F49D on CF_Campaign (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A3792EA9 on CF_DataFeed (campaignId);
create unique index IX_87C9F332 on CF_DataFeed (feedKey[$COLUMN_LENGTH:64$]);
create index IX_F6E3E840 on CF_DataFeed (groupId);

create index IX_DD3ED6F7 on CF_EventDelivery (campaignId);
create index IX_38A3FE3A on CF_EventDelivery (status[$COLUMN_LENGTH:20$], nextAttemptDate);
create index IX_BA28ECB3 on CF_EventDelivery (submissionId);

create unique index IX_A5E976D1 on CF_Form (campaignId, formKey[$COLUMN_LENGTH:75$]);

create unique index IX_4AC9DA4D on CF_FormField (formVersionId, fieldKey[$COLUMN_LENGTH:50$]);

create index IX_952509AC on CF_FormFieldOption (formFieldId);

create index IX_3CF18FD7 on CF_FormVersion (formId, status[$COLUMN_LENGTH:20$]);
create unique index IX_63AC958C on CF_FormVersion (formId, version);

create index IX_E013647F on CF_IntegrationConfig (campaignId);

create index IX_D5299856 on CF_NotificationConfig (campaignId, eventType[$COLUMN_LENGTH:50$]);

create unique index IX_8DA1B2A5 on CF_PageBlock (pageConfigurationId, blockKey[$COLUMN_LENGTH:50$]);

create unique index IX_3B591253 on CF_PageConfiguration (campaignId, stage[$COLUMN_LENGTH:20$]);

create unique index IX_51C51F8B on CF_PageTemplate (groupId, templateKey[$COLUMN_LENGTH:75$]);
create index IX_DC6DE5F on CF_PageTemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_14C6C2F6 on CF_PageTemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_E8A0CDE4 on CF_Submission (campaignId, idempotencyKey[$COLUMN_LENGTH:64$]);
create index IX_BFF49973 on CF_Submission (campaignId, status[$COLUMN_LENGTH:20$]);
create index IX_6F801051 on CF_Submission (formVersionId);
create index IX_1E0429DC on CF_Submission (groupId);
create index IX_360F8502 on CF_Submission (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8FA94799 on CF_Submission (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_48C149A9 on CF_SubmissionValue (campaignId, fieldKey[$COLUMN_LENGTH:50$], stringValue[$COLUMN_LENGTH:500$]);
create index IX_D71A0A8A on CF_SubmissionValue (submissionId);