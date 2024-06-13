// Author : Anirudh Suryawanshi

package com.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.dto.UserCountByRoleDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.service.UserService;

public class UserServiceTest {
	UserService userService = new UserService();

	@Test
	public void getUserTotalReservationsByStatus() {
		// Preparing input
		UserCountByRoleDto u1 = new UserCountByRoleDto("admin", 1);
		UserCountByRoleDto u2 = new UserCountByRoleDto("vendor", 15);
		UserCountByRoleDto u3 = new UserCountByRoleDto("customer", 16);

		// -------------Use Case 1-------------
		List<UserCountByRoleDto> expectedList = Arrays.asList(u1, u2, u3);
		List<UserCountByRoleDto> actualList = null;
		try {
			actualList = userService.getUserCountByRole();
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
		}

		// -------------Use Case 2-------------
		expectedList = Arrays.asList(u2, u3);// removing first element
		actualList = null;
		try {
			actualList = userService.getUserCountByRole();
			actualList.remove(0);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
		}

		// -------------Use Case 3-------------
		expectedList = Arrays.asList(u1, u2);// removing last element
		actualList = null;
		try {
			actualList = userService.getUserCountByRole();
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
		}

		// -------------Use Case 4-------------
		expectedList = Arrays.asList(u2);// removing first and last element
		actualList = null;
		try {
			actualList = userService.getUserCountByRole();
			actualList.remove(0);
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
		}
	}
}
