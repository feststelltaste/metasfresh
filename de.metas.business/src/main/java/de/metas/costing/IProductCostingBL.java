package de.metas.costing;

import org.adempiere.acct.api.AcctSchema;
import org.adempiere.acct.api.AcctSchemaId;
import org.compiere.model.I_M_Product;

import de.metas.product.ProductId;
import de.metas.util.ISingletonService;

/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public interface IProductCostingBL extends ISingletonService
{
	CostingLevel getCostingLevel(ProductId productId, AcctSchemaId acctSchemaId);

	CostingLevel getCostingLevel(ProductId productId, AcctSchema acctSchema);

	CostingLevel getCostingLevel(I_M_Product product, AcctSchema acctSchema);

	/**
	 * Get Product Costing Method
	 *
	 * @param C_AcctSchema_ID accounting schema ID
	 * @return product costing method
	 */
	CostingMethod getCostingMethod(I_M_Product product, AcctSchema acctSchema);

	CostingMethod getCostingMethod(ProductId productId, AcctSchema acctSchema);

	CostingMethod getCostingMethod(ProductId productId, AcctSchemaId acctSchemaId);
}
