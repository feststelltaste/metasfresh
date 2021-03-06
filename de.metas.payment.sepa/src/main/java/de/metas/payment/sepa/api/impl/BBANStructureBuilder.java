/**
 * 
 */
package de.metas.payment.sepa.api.impl;

/*
 * #%L
 * de.metas.payment.sepa
 * %%
 * Copyright (C) 2015 metas GmbH
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


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.metas.builder.BuilderSupport;
import de.metas.payment.sepa.api.IBBANStructureBuilder;
import de.metas.payment.sepa.api.IBBANStructureEntryBuilder;
import de.metas.payment.sepa.wrapper.BBANStructure;
import de.metas.payment.sepa.wrapper.BBANStructureEntry;
import de.metas.util.Check;

/**
 * @author cg
 *
 */
public class BBANStructureBuilder implements IBBANStructureBuilder
{
	private BBANStructure _BBANStructure;

	public BBANStructureBuilder()
	{
		_BBANStructure = new BBANStructure();
	}

	@Override
	public BBANStructure create()
	{
		for (final BBANStructureEntryBuilder line : s.getLines())
		{
			line.create(_BBANStructure);
		}
		
		// sort entries
		sortEntries();
		
		return _BBANStructure;
	}

	private final BuilderSupport<BBANStructureEntryBuilder> s = new BuilderSupport<BBANStructureEntryBuilder>(this);

	@Override
	public IBBANStructureEntryBuilder addBBANStructureEntry()
	{
		return addBBANStructureEntry(BBANStructureEntryBuilder.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T extends BBANStructureEntryBuilder> T addBBANStructureEntry(final Class<T> implClazz)
	{
		return (T)s.addLine(implClazz);
	}

	private void sortEntries()
	{
		List<BBANStructureEntry> listEntries =_BBANStructure.getEntries();

		// if there is no entry , than we have BBAN structure 
		if (listEntries.isEmpty())
		{
			_BBANStructure = null;
			return;
		}

		// order list by seqNo
		Collections.sort(listEntries, new Comparator<BBANStructureEntry>()
		{
			@Override
			public int compare(BBANStructureEntry entry1, BBANStructureEntry entry2)
			{
				String seqNo1 = entry1.getSeqNo();
				if (Check.isEmpty(seqNo1, true))
				{
					seqNo1 = "10";
				}

				String seqNo2 = entry2.getSeqNo();
				if (Check.isEmpty(seqNo2, true))
				{
					seqNo2 = "20";
				}

				final int no1 = Integer.valueOf(seqNo1);
				final int no2 = Integer.valueOf(seqNo2);

				// order
				if (no1 > no2)
				{
					return 1;
				}
				else if (no1 < no2)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		});
	}	
}
