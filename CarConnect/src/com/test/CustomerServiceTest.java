// Author : Anirudh Suryawanshi

package com.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.CustomersWithReservationsDto;
import com.dto.CustomersWithTotalSpentDto;
import com.exception.DatabaseConnectionException;
import com.service.CustomerService;

public class CustomerServiceTest {
	CustomerService customerService = new CustomerService();

	@Test
	public void getCustomerWithNumberOfReservations() {
		// Preparing input
		CustomersWithReservationsDto c1 = new CustomersWithReservationsDto(2, "Priya Gupta", 1);
		CustomersWithReservationsDto c2 = new CustomersWithReservationsDto(3, "Neha Sharma", 1);
		CustomersWithReservationsDto c3 = new CustomersWithReservationsDto(6, "Anuj Jain", 1);
		CustomersWithReservationsDto c4 = new CustomersWithReservationsDto(13, "Sakshi Malhotra", 1);
		CustomersWithReservationsDto c5 = new CustomersWithReservationsDto(1, "Rahul Kumar", 2);
		CustomersWithReservationsDto c6 = new CustomersWithReservationsDto(4, "Raj Singh", 2);
		CustomersWithReservationsDto c7 = new CustomersWithReservationsDto(9, "Deepak Yadav", 2);
		CustomersWithReservationsDto c8 = new CustomersWithReservationsDto(11, "Anjali Gandhi", 2);
		CustomersWithReservationsDto c9 = new CustomersWithReservationsDto(12, "Isha Kapoor", 2);
		CustomersWithReservationsDto c10 = new CustomersWithReservationsDto(15, "Shreya Tiwari", 2);

		// -------------Use Case 1-------------
		List<CustomersWithReservationsDto> expectedList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
		List<CustomersWithReservationsDto> actualList = null;
		try {
			actualList = customerService.getCustomerWithNumberOfReservations();
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 2-------------
		expectedList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);// removing last element
		actualList = null;
		try {
			actualList = customerService.getCustomerWithNumberOfReservations();
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 3-------------
		expectedList = Arrays.asList(c2, c3, c4, c5, c6, c7, c8, c9, c10);// // removing first element
		actualList = null;
		try {
			actualList = customerService.getCustomerWithNumberOfReservations();
			actualList.remove(0);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 4-------------
		expectedList = Arrays.asList(c2, c3, c4, c5, c6, c7, c8, c9);// removing first & last elements
		actualList = null;
		try {
			actualList = customerService.getCustomerWithNumberOfReservations();
			actualList.remove(0);
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}
	}

	@Test
	public void getTotalSpentByCustomer() {
		CustomersWithTotalSpentDto c1 = new CustomersWithTotalSpentDto(6, "Anuj Jain", 3000.0);
		CustomersWithTotalSpentDto c2 = new CustomersWithTotalSpentDto(13, "Sakshi Malhotra", 3200.0);
		CustomersWithTotalSpentDto c3 = new CustomersWithTotalSpentDto(11, "Anjali Gandhi", 4400.0);
		CustomersWithTotalSpentDto c4 = new CustomersWithTotalSpentDto(3, "Neha Sharma", 4800.0);
		CustomersWithTotalSpentDto c5 = new CustomersWithTotalSpentDto(15, "Shreya Tiwari", 5000.0);
		CustomersWithTotalSpentDto c6 = new CustomersWithTotalSpentDto(12, "Isha Kapoor", 6300.0);
		CustomersWithTotalSpentDto c7 = new CustomersWithTotalSpentDto(4, "Raj Singh", 9400.0);
		CustomersWithTotalSpentDto c8 = new CustomersWithTotalSpentDto(1, "Rahul Kumar", 11300.0);
		CustomersWithTotalSpentDto c9 = new CustomersWithTotalSpentDto(2, "Priya Gupta", 13500.0);
		CustomersWithTotalSpentDto c10 = new CustomersWithTotalSpentDto(9, "Deepak Yadav", 17600.0);

		// -------------Use Case 1-------------
		List<CustomersWithTotalSpentDto> expectedList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
		List<CustomersWithTotalSpentDto> actualList = null;
		try {
			actualList = customerService.getTotalSpentByCustomer();
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 2-------------
		expectedList = Arrays.asList(c2, c3, c4, c5, c6, c7, c8, c9, c10);// removing first element
		actualList = null;
		try {
			actualList = customerService.getTotalSpentByCustomer();
			actualList.remove(0);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 3-------------
		expectedList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);// removing last element
		actualList = null;
		try {
			actualList = customerService.getTotalSpentByCustomer();
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}

		// -------------Use Case 4-------------
		expectedList = Arrays.asList(c2, c3, c4, c5, c6, c7, c8, c9);// removing first & last elements
		actualList = null;
		try {
			actualList = customerService.getTotalSpentByCustomer();
			actualList.remove(0);
			actualList.remove(actualList.size() - 1);
			Assert.assertEquals(expectedList, actualList);
		} catch (SQLException | DatabaseConnectionException e) {
		}
	}
}
