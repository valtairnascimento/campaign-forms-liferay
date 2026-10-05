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
 * The table class for the &quot;CF_FormField&quot; database table.
 *
 * @author Valtair Nascimento
 * @see FormField
 * @generated
 */
public class FormFieldTable extends BaseTable<FormFieldTable> {

	public static final FormFieldTable INSTANCE = new FormFieldTable();

	public final Column<FormFieldTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<FormFieldTable, Long> formFieldId = createColumn(
		"formFieldId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FormFieldTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Long> formVersionId = createColumn(
		"formVersionId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> fieldKey = createColumn(
		"fieldKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> label = createColumn(
		"label", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> placeholder = createColumn(
		"placeholder", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> helpText = createColumn(
		"helpText", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Boolean> required = createColumn(
		"required", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Integer> position = createColumn(
		"position", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> defaultValue = createColumn(
		"defaultValue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> width = createColumn(
		"width", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Clob> validation = createColumn(
		"validation", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Clob> properties = createColumn(
		"properties", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Clob> conditionalRule = createColumn(
		"conditionalRule", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, String> presetKey = createColumn(
		"presetKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldTable, Boolean> personalData = createColumn(
		"personalData", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);

	private FormFieldTable() {
		super("CF_FormField", FormFieldTable::new);
	}

}