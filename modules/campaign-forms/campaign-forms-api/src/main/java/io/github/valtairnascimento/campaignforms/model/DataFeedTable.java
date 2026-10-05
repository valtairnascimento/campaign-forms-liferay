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

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CF_DataFeed&quot; database table.
 *
 * @author Valtair Nascimento
 * @see DataFeed
 * @generated
 */
public class DataFeedTable extends BaseTable<DataFeedTable> {

	public static final DataFeedTable INSTANCE = new DataFeedTable();

	public final Column<DataFeedTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<DataFeedTable, Long> dataFeedId = createColumn(
		"dataFeedId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<DataFeedTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> feedKey = createColumn(
		"feedKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> tokenHash = createColumn(
		"tokenHash", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> tokenHint = createColumn(
		"tokenHint", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> seriesKey = createColumn(
		"seriesKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, String> format = createColumn(
		"format", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Boolean> includeMetadata = createColumn(
		"includeMetadata", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Date> expirationDate = createColumn(
		"expirationDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Date> lastAccessDate = createColumn(
		"lastAccessDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DataFeedTable, Boolean> active = createColumn(
		"active_", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private DataFeedTable() {
		super("CF_DataFeed", DataFeedTable::new);
	}

}