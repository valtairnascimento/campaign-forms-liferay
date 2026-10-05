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
 * The table class for the &quot;CF_PageTemplate&quot; database table.
 *
 * @author Valtair Nascimento
 * @see PageTemplate
 * @generated
 */
public class PageTemplateTable extends BaseTable<PageTemplateTable> {

	public static final PageTemplateTable INSTANCE = new PageTemplateTable();

	public final Column<PageTemplateTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<PageTemplateTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Long> pageTemplateId = createColumn(
		"pageTemplateId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<PageTemplateTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, String> templateKey = createColumn(
		"templateKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Boolean> systemTemplate =
		createColumn(
			"systemTemplate", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<PageTemplateTable, Clob> blocks = createColumn(
		"blocks", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private PageTemplateTable() {
		super("CF_PageTemplate", PageTemplateTable::new);
	}

}