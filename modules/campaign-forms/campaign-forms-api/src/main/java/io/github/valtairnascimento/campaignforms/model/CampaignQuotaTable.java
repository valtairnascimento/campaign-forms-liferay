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
 * The table class for the &quot;CF_CampaignQuota&quot; database table.
 *
 * @author Valtair Nascimento
 * @see CampaignQuota
 * @generated
 */
public class CampaignQuotaTable extends BaseTable<CampaignQuotaTable> {

	public static final CampaignQuotaTable INSTANCE = new CampaignQuotaTable();

	public final Column<CampaignQuotaTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<CampaignQuotaTable, Long> campaignId = createColumn(
		"campaignId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<CampaignQuotaTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CampaignQuotaTable, Integer> used = createColumn(
		"used", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private CampaignQuotaTable() {
		super("CF_CampaignQuota", CampaignQuotaTable::new);
	}

}