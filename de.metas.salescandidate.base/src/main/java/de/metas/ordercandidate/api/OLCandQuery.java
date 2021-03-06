package de.metas.ordercandidate.api;

import de.metas.impex.model.I_AD_InputDataSource;
import de.metas.util.Check;
import lombok.Builder;
import lombok.Value;

/*
 * #%L
 * de.metas.swat.base
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Value
@Builder
public class OLCandQuery
{
	/** ID (e.g. document number), of a source document in a remote system; multiple OLCands can have the same ID */
	private String externalReference;

	/** {@link I_AD_InputDataSource#COLUMNNAME_InternalName} of the data source the candidates in question were added with. */
	private String inputDataSourceName;

	public OLCandQuery(
			String externalReference,
			String inputDataSourceName)
	{
		if (externalReference != null)
		{
			Check.assumeNotEmpty(externalReference, "If externalReference is specified, then it may not be empty");
			Check.assumeNotEmpty(inputDataSourceName, "If externalReference is specified, then inputDataSourceName may not be empty; externalReference={}", externalReference);
		}

		this.externalReference = externalReference;
		this.inputDataSourceName = inputDataSourceName;
	}
}
