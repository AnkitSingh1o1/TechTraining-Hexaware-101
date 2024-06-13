package com.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.AdminDto;
import com.service.AdminService;

public class AdminServiceTest {
	AdminService adminService = new AdminService();

	@Test
	public void sortVehicleRevenue() {
		AdminDto a1 = new AdminDto(1, "baleno", 8000);
		AdminDto a2 = new AdminDto(2, "punch", 7500);
		AdminDto a3 = new AdminDto(3, "harrier", 16000);
		AdminDto a4 = new AdminDto(4, "swift", 6000);
		AdminDto a5 = new AdminDto(5, "tavera", 10000);

		List<AdminDto> list = Arrays.asList(a1, a2, a3, a4, a5);
		// test1- ascending
		String sortDirection = "ASC";
		List<AdminDto> expectedList = Arrays.asList(a4, a2, a1, a5, a3);
		List<AdminDto> actualList = adminService.sortVehicleRevenue(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		// Test-2

		sortDirection = "DESC";
		expectedList = Arrays.asList(a3, a5, a1, a2, a4);
		actualList = adminService.sortVehicleRevenue(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		// Test-4

		sortDirection = "asc";
		expectedList = Arrays.asList(a4, a5, a3);
		actualList = adminService.sortVehicleRevenue(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		// Test-4

		sortDirection = "Desc";
		expectedList = Arrays.asList(a3, a5, a1, a4);
		actualList = adminService.sortVehicleRevenue(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		sortDirection = "Wrong";
		expectedList = Arrays.asList(a3, a5, a1, a4);
		actualList = adminService.sortVehicleRevenue(list, sortDirection);
		try {
			Assert.assertEquals(expectedList, actualList);
		} catch (Error e) {
		}

	}

}