// Author : Anirudh Suryawanshi

package com.utility;

import java.util.Comparator;

import com.dto.CustomersWithTotalSpentDto;

public class TotalSpentSortUtilityAsc implements Comparator<CustomersWithTotalSpentDto> {
	@Override
	public int compare(CustomersWithTotalSpentDto c1, CustomersWithTotalSpentDto c2) {
		if (c1.getTotalSpent() < c2.getTotalSpent())
			return -1;
		else if (c1.getTotalSpent() > c2.getTotalSpent())
			return 1;
		return 0;
	}
}
