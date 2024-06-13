// Author : Anirudh Suryawanshi

package com.utility;

import java.util.Comparator;

import com.dto.CustomersWithReservationsDto;

public class ReservationNumSortUtilityAsc implements Comparator<CustomersWithReservationsDto> {
	@Override
	public int compare(CustomersWithReservationsDto c1, CustomersWithReservationsDto c2) {
		if (c1.getNumberOfReservations() < c2.getNumberOfReservations())
			return -1;
		else if (c1.getNumberOfReservations() > c2.getNumberOfReservations())
			return 1;
		return 0;
	}
}