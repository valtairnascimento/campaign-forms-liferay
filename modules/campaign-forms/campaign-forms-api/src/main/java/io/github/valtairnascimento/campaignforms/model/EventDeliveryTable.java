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
 * The table class for the &quot;CF_EventDelivery&quot; database table.
 *
 * @author Valtair Nascimento
 * @see EventDelivery
 * @generated
 */
public class EventDeliveryTable extends BaseTable<EventDeliveryTable> {

	public static final EventDeliveryTable INSTANCE = new EventDeliveryTable();

	public final Column<EventDeliveryTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<EventDeliveryTable, Long> eventDeliveryId =
		createColumn(
			"eventDeliveryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EventDeliveryTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Long> submissionId = createColumn(
		"submissionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, String> eventType = createColumn(
		"eventType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, String> targetType = createColumn(
		"targetType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Long> targetId = createColumn(
		"targetId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Integer> attempts = createColumn(
		"attempts", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, Date> nextAttemptDate =
		createColumn(
			"nextAttemptDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, String> lastErrorCode =
		createColumn(
			"lastErrorCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EventDeliveryTable, String> lastErrorMessage =
		createColumn(
			"lastErrorMessage", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);

	private EventDeliveryTable() {
		super("CF_EventDelivery", EventDeliveryTable::new);
	}

}