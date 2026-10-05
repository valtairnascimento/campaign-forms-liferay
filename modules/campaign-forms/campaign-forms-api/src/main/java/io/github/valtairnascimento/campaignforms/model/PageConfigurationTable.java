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
 * The table class for the &quot;CF_PageConfiguration&quot; database table.
 *
 * @author Valtair Nascimento
 * @see PageConfiguration
 * @generated
 */
public class PageConfigurationTable extends BaseTable<PageConfigurationTable> {

	public static final PageConfigurationTable INSTANCE =
		new PageConfigurationTable();

	public final Column<PageConfigurationTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<PageConfigurationTable, Long> pageConfigurationId =
		createColumn(
			"pageConfigurationId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<PageConfigurationTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> stage = createColumn(
		"stage", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> templateKey =
		createColumn(
			"templateKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> themeKey = createColumn(
		"themeKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> cssClass = createColumn(
		"cssClass", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> seoTitle = createColumn(
		"seoTitle", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> seoDescription =
		createColumn(
			"seoDescription", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> canonicalUrl =
		createColumn(
			"canonicalUrl", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Boolean> noIndex = createColumn(
		"noIndex", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> ogTitle = createColumn(
		"ogTitle", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, String> ogDescription =
		createColumn(
			"ogDescription", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Long> ogImageFileEntryId =
		createColumn(
			"ogImageFileEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<PageConfigurationTable, Clob> settings = createColumn(
		"settings_", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private PageConfigurationTable() {
		super("CF_PageConfiguration", PageConfigurationTable::new);
	}

}