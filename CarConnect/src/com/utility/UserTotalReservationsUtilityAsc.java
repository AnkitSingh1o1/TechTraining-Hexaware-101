// Author : Anirudh Suryawanshi

package com.utility;

import java.util.Comparator;
import com.dto.UserTotalReservationsByStatusDto;

public class UserTotalReservationsUtilityAsc implements Comparator<UserTotalReservationsByStatusDto> {

	@Override
	public int compare(UserTotalReservationsByStatusDto c1, UserTotalReservationsByStatusDto c2) {
		if (c1.getReservationStatusCount() < c2.getReservationStatusCount())
			return -1;
		else if (c1.getReservationStatusCount() > c2.getReservationStatusCount())
			return 1;
		return 0;
	}

}
