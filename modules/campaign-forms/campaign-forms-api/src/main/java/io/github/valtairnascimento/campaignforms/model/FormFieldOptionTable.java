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

/**
 * The table class for the &quot;CF_FormFieldOption&quot; database table.
 *
 * @author Valtair Nascimento
 * @see FormFieldOption
 * @generated
 */
public class FormFieldOptionTable extends BaseTable<FormFieldOptionTable> {

	public static final FormFieldOptionTable INSTANCE =
		new FormFieldOptionTable();

	public final Column<FormFieldOptionTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<FormFieldOptionTable, Long> formFieldOptionId =
		createColumn(
			"formFieldOptionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FormFieldOptionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormFieldOptionTable, Long> formFieldId = createColumn(
		"formFieldId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FormFieldOptionTable, String> label = createColumn(
		"label", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldOptionTable, String> value = createColumn(
		"value", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FormFieldOptionTable, Integer> position = createColumn(
		"position", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private FormFieldOptionTable() {
		super("CF_FormFieldOption", FormFieldOptionTable::new);
	}

}