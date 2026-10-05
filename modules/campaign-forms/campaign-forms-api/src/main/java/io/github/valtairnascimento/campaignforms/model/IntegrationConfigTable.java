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
 * The table class for the &quot;CF_IntegrationConfig&quot; database table.
 *
 * @author Valtair Nascimento
 * @see IntegrationConfig
 * @generated
 */
public class IntegrationConfigTable extends BaseTable<IntegrationConfigTable> {

	public static final IntegrationConfigTable INSTANCE =
		new IntegrationConfigTable();

	public final Column<IntegrationConfigTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<IntegrationConfigTable, Long> integrationConfigId =
		createColumn(
			"integrationConfigId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<IntegrationConfigTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, String> providerKey =
		createColumn(
			"providerKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Boolean> enabled = createColumn(
		"enabled", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, String> eventTypes =
		createColumn(
			"eventTypes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Clob> settings = createColumn(
		"settings_", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<IntegrationConfigTable, Clob> encryptedSecrets =
		createColumn(
			"encryptedSecrets", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private IntegrationConfigTable() {
		super("CF_IntegrationConfig", IntegrationConfigTable::new);
	}

}