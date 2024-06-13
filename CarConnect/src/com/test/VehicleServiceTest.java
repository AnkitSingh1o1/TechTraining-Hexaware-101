/*Author :AKSHAY PAWAR*/

package com.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;
import com.service.VehicleService;

public class VehicleServiceTest {
	VehicleService vehicleService = new VehicleService();

	@Test

	public void sortVehicleByDailyRate() {

		/* Use Case 1 */
		Vehicle v1 = new Vehicle(1, "Punch", "Tata", 2020, "Black", "MH01AB1234", true, 800, 1);
		Vehicle v2 = new Vehicle(2, "Bolero", "Mahindra", 2019, "White", "MH02CD5678", true, 1200, 2);
		Vehicle v3 = new Vehicle(3, "Swift", "Maruti", 2021, "Red", "KA03EF9101", false, 900, 3);
		Vehicle v4 = new Vehicle(4, "Baleno", "Maruti", 2018, "Silver", "DL04GH1122", true, 1100, 4);

		List<Vehicle> list = Arrays.asList(v1, v2, v3, v4);
		String sortDirection = "ASC";

		List<Vehicle> expectedList = Arrays.asList(v1, v3, v4, v2);
		List<Vehicle> actualList = vehicleService.sortVehicleByDailyRate(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		/* UseCase 2 */

		sortDirection = "Desc";
		expectedList = Arrays.asList(v2, v4, v3, v1);
		actualList = vehicleService.sortVehicleByDailyRate(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		/* UseCase 3 */

		sortDirection = "ASC";
		list = Arrays.asList(v1, v3, v4);
		expectedList = Arrays.asList(v1, v3, v4);
		actualList = vehicleService.sortVehicleByDailyRate(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		/* UseCase 4 */

		sortDirection = "DESC";
		list = Arrays.asList(v1, v2, v4);
		expectedList = Arrays.asList(v2, v4, v1);
		actualList = vehicleService.sortVehicleByDailyRate(list, sortDirection);
		Assert.assertEquals(expectedList, actualList);

		/* UseCase 5 */

		sortDirection = "Wrong Direction";
		list = Arrays.asList(v1, v2, v4);
		expectedList = Arrays.asList(v2, v4, v1);
		actualList = vehicleService.sortVehicleByDailyRate(list, sortDirection);
		try {
			Assert.assertEquals(expectedList, actualList);
		} catch (Error e) {
		}
	}

	public void getVehicleAge() {
		int vehicle_id = 5;
		int expectedResult = 4;
		int age;
		try {
			age = vehicleService.getVehicleAge(vehicle_id);
			Assert.assertEquals(expectedResult, age);
		} catch (SQLException | InvalidInputException | DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}

		vehicle_id = 50;
		String expectedRes = "Vehicle Id is Invalid, Try Again!";

		try {
			age = vehicleService.getVehicleAge(vehicle_id);
			Assert.assertEquals(expectedRes, age);
		} catch (SQLException | InvalidInputException | DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}
	}

}
