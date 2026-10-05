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
 * The table class for the &quot;CF_FormVersion&quot; database table.
 *
 * @author Valtair Nascimento
 * @see FormVersion
 * @generated
 */
public class FormVersionTable extends BaseTable<FormVersionTable> {

	public static final FormVersionTable INSTANCE = new FormVersionTable();

	public final Column<FormVersionTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<FormVersionTable, Long> formVersionId = createColumn(
		"formVersionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FormVersionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Long> formId = createColumn(
		"formId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Integer> version = createColumn(
		"version", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> submitLabel = createColumn(
		"submitLabel", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> successTitle = createColumn(
		"successTitle", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Clob> successMessage = createColumn(
		"successMessage", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> redirectUrl = createColumn(
		"redirectUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Boolean> captchaEnabled =
		createColumn(
			"captchaEnabled", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Boolean> requireAuthentication =
		createColumn(
			"requireAuthentication", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Boolean> consentRequired =
		createColumn(
			"consentRequired", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Clob> consentText = createColumn(
		"consentText", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> consentVersion = createColumn(
		"consentVersion", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, String> consentURL = createColumn(
		"consentURL", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Boolean> saveIncompleteSubmission =
		createColumn(
			"saveIncompleteSubmission", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Clob> settings = createColumn(
		"settings_", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Date> publishedDate = createColumn(
		"publishedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FormVersionTable, Long> publishedByUserId =
		createColumn(
			"publishedByUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private FormVersionTable() {
		super("CF_FormVersion", FormVersionTable::new);
	}

}