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

import java.math.BigDecimal;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;CF_SubmissionValue&quot; database table.
 *
 * @author Valtair Nascimento
 * @see SubmissionValue
 * @generated
 */
public class SubmissionValueTable extends BaseTable<SubmissionValueTable> {

	public static final SubmissionValueTable INSTANCE =
		new SubmissionValueTable();

	public final Column<SubmissionValueTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<SubmissionValueTable, Long> submissionValueId =
		createColumn(
			"submissionValueId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SubmissionValueTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Long> submissionId = createColumn(
		"submissionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Long> formFieldId = createColumn(
		"formFieldId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, String> fieldKey = createColumn(
		"fieldKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, String> valueType = createColumn(
		"valueType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Integer> valueIndex =
		createColumn(
			"valueIndex", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, String> stringValue =
		createColumn(
			"stringValue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Clob> textValue = createColumn(
		"textValue", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, BigDecimal> numberValue =
		createColumn(
			"numberValue", BigDecimal.class, Types.DECIMAL,
			Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Date> dateValue = createColumn(
		"dateValue", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<SubmissionValueTable, Boolean> booleanValue =
		createColumn(
			"booleanValue", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private SubmissionValueTable() {
		super("CF_SubmissionValue", SubmissionValueTable::new);
	}

}