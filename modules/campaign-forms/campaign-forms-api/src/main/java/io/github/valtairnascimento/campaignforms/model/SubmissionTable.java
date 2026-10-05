/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package io.github.valtairnascimento.campaignforms.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CF_Submission&quot; database table.
 *
 * @author Valtair Nascimento
 * @see Submission
 * @generated
 */
public class SubmissionTable extends BaseTable<SubmissionTable> {

	public static final SubmissionTable INSTANCE = new SubmissionTable();

	public final Column<SubmissionTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<SubmissionTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> submissionId = createColumn(
		"submissionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SubmissionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> formId = createColumn(
		"formId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Long> formVersionId = createColumn(
		"formVersionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> sourceUrl = createColumn(
		"sourceUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> referrer = createColumn(
		"referrer", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> userAgent = createColumn(
		"userAgent", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> languageId = createColumn(
		"languageId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> ipHash = createColumn(
		"ipHash", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> utmSource = createColumn(
		"utmSource", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> utmMedium = createColumn(
		"utmMedium", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> utmCampaign = createColumn(
		"utmCampaign", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> utmContent = createColumn(
		"utmContent", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> utmTerm = createColumn(
		"utmTerm", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> consentVersion = createColumn(
		"consentVersion", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Date> consentDate = createColumn(
		"consentDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, String> idempotencyKey = createColumn(
		"idempotencyKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Clob> metadata = createColumn(
		"metadata", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<SubmissionTable, Clob> payloadSnapshot = createColumn(
		"payloadSnapshot", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private SubmissionTable() {
		super("CF_Submission", SubmissionTable::new);
	}

}