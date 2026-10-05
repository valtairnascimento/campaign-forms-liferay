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
 * The table class for the &quot;CF_NotificationConfig&quot; database table.
 *
 * @author Valtair Nascimento
 * @see NotificationConfig
 * @generated
 */
public class NotificationConfigTable
	extends BaseTable<NotificationConfigTable> {

	public static final NotificationConfigTable INSTANCE =
		new NotificationConfigTable();

	public final Column<NotificationConfigTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<NotificationConfigTable, Long> notificationConfigId =
		createColumn(
			"notificationConfigId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<NotificationConfigTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> userName =
		createColumn(
			"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Long> campaignId =
		createColumn(
			"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> eventType =
		createColumn(
			"eventType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> channel = createColumn(
		"channel", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Boolean> enabled =
		createColumn(
			"enabled", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> recipients =
		createColumn(
			"recipients", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> cc = createColumn(
		"cc", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> bcc = createColumn(
		"bcc", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> fromName =
		createColumn(
			"fromName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> replyTo = createColumn(
		"replyTo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, String> subjectTemplate =
		createColumn(
			"subjectTemplate", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Clob> bodyTemplate =
		createColumn(
			"bodyTemplate", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<NotificationConfigTable, Clob> settings = createColumn(
		"settings_", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);

	private NotificationConfigTable() {
		super("CF_NotificationConfig", NotificationConfigTable::new);
	}

}