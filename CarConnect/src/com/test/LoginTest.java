package com.test;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.User;
import com.service.LoginService;

public class LoginTest {
	LoginService loginService = new LoginService();

	@Test
	public void login() {
		String username = "admin";
		String password = "admin123";
		try {
			User actualUserLogin = loginService.login(username, password);
			User expectedUser = new User(31, "admin", "admin123", "admin");

			Assert.assertEquals(expectedUser, actualUserLogin);

		} catch (InvalidInputException e) {
		} catch (SQLException e) {

		}
// Test - 2
		catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}

		username = "anjali_customer";
		password = "passwordvwx";
		try {
			User actualUserLogin = loginService.login(username, password);
			User expectedUser = new User(11, "anjali_customer", "passwordvwx", "customer");

			Assert.assertEquals(expectedUser, actualUserLogin);

		} catch (InvalidInputException e) {
		} catch (SQLException e) {

		}
// Test - 3
		catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}

		username = "arjun_vendor";
		password = "passwordjkl";
		try {
			User actualUserLogin = loginService.login(username, password);
			User expectedUser = new User(22, "arjun_vendor", "passwordjkl", "vendor");

			Assert.assertEquals(expectedUser, actualUserLogin);

		} catch (InvalidInputException e) {
		} catch (SQLException e) {

		}
// Test - 4
		catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}

		username = "vendor";
		password = "passwordjkl";
		try {
			User actualUserLogin = loginService.login(username, password);
			User expectedUser = new User(22, "arjun_vendor", "passwordjkl", "vendor");
			Assert.assertEquals(expectedUser, actualUserLogin);
		} catch (InvalidInputException e) {
			Assert.assertEquals("Invalid Credentials", e.getMessage());
		} catch (SQLException e) {

		} catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}
	}
}
