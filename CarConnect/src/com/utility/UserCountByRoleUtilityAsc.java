// Author : Anirudh Suryawanshi

package com.utility;

import java.util.Comparator;

import com.dto.UserCountByRoleDto;

public class UserCountByRoleUtilityAsc implements Comparator<UserCountByRoleDto> {

	@Override
	public int compare(UserCountByRoleDto c1, UserCountByRoleDto c2) {
		if (c1.getUserCount() < c2.getUserCount())
			return -1;
		else if (c1.getUserCount() > c2.getUserCount())
			return 1;
		return 0;
	}

}
