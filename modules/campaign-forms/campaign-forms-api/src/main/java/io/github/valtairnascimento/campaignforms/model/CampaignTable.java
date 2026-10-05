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
 * The table class for the &quot;CF_Campaign&quot; database table.
 *
 * @author Valtair Nascimento
 * @see Campaign
 * @generated
 */
public class CampaignTable extends BaseTable<CampaignTable> {

	public static final CampaignTable INSTANCE = new CampaignTable();

	public final Column<CampaignTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CampaignTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> externalReferenceCode =
		createColumn(
			"externalReferenceCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CampaignTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> slug = createColumn(
		"slug", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> mode = createColumn(
		"mode_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Integer> submissionLimit = createColumn(
		"submissionLimit", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> seriesKey = createColumn(
		"seriesKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> instanceKey = createColumn(
		"instanceKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Long> sourceCampaignId = createColumn(
		"sourceCampaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Integer> retentionDays = createColumn(
		"retentionDays", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, String> defaultLanguageId = createColumn(
		"defaultLanguageId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Date> publishedDate = createColumn(
		"publishedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Long> publishedByUserId = createColumn(
		"publishedByUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignTable, Clob> settings = createColumn(
		"settings_", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private CampaignTable() {
		super("CF_Campaign", CampaignTable::new);
	}

}