
//Author: Ashwin Soni

package com.controller;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.exception.AddressInvalidException;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Address;
import com.model.Customer;
import com.model.User;
import com.service.AddressService;
import com.service.CustomerService;
import com.service.LoginService;
import com.service.UserService;

public class LoginController {
	public static void main(String[] args) {
		LoginService loginService = new LoginService();
		UserService userService = new UserService();
		CustomerService customerService = new CustomerService();
		AddressService addressService = new AddressService();
		// AddressService addressService = new AddressService();
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		while (!loggedIn) {

			System.out.println();
			System.out.println("----------Welcome to Car Connect------------");
			System.out.println();
			System.out.println("Press 1 to Login");
			System.out.println("Press 2 to Reset Password");
			System.out.println("Press 3 to SignUp");
			System.out.println("Press 0 to Exit");
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			sc.nextLine(); // Consume newline character

			switch (input) {
			case 0:
				System.out.println("Exiting from Car Connect...");
				System.out.println("Thank you for using this application");
				return;
			case 1:
				try {
					System.out.print("Enter Username : ");
					String username = sc.nextLine().trim(); // takes username as input(trimmed)
					System.out.print("Enter Password : ");
					String password = sc.nextLine();// takes password as input(trimmed)

					User userLogin = loginService.login(username, password);
					loggedIn = true; // On logging in loggedIn is set to true

					if (userLogin.getUserRole().equalsIgnoreCase("customer")) {
						CustomerController.customerMenu(username, password);
					}

					else if (userLogin.getUserRole().equalsIgnoreCase("vendor")) {
						VendorController.vendorMenu(username, password);
					}

					else {
						AdminController.adminMenu(username, password);
						
					}
					break;
				} catch (InvalidInputException e) {
					System.out.println("Invalid username or password. Please try again.");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				try {
					System.out.print("Enter Username : ");
					String user = sc.nextLine();
					String newPassword = null;
					boolean isValid = false;
					while (!isValid) {
						System.out.println("Enter Password (Must contain atleast 8 characters-"
								+ "1 special caharacter(@, #, $, *), 1 upper case(A-Z) character and 1 digit(0-9))");
						newPassword = sc.nextLine();
						isValid = loginService.validatePassword(newPassword);
						if (!isValid)
							System.out.println("Weak password, Try Again");

					}
					loginService.resetPassword(user, newPassword);
					System.out.println("Password Reset!!....Try logging in with new password");
				}

				catch (SQLException | DatabaseConnectionException e) {
					System.out.println("An error occurred while processing your request. Please try again later.");
				}
				break;

			case 3:
				try {

					Random random = new Random();
					int randomNumber = random.nextInt();
					int customerId = randomNumber < 0 ? randomNumber * -1 : randomNumber;
					System.out.print("Enter first name : ");
					String firstName = sc.nextLine();
					System.out.print("Enter last name : ");
					String lastName = sc.nextLine();
					System.out.print("Enter email : ");
					String email = sc.nextLine();
					System.out.print("Enter phone no. : ");
					String phoneNo = sc.nextLine();
					System.out.print("Enter Registration Date : ");
					String registrationDate = sc.nextLine();

					random = new Random();
					randomNumber = random.nextInt();
					int addressId = randomNumber < 0 ? randomNumber * -1 : randomNumber;
					System.out.print("Enter your State : ");
					String state = sc.nextLine();
					System.out.print("Enter your city : ");
					String city = sc.nextLine();
					System.out.print("Enter your Pincode : ");
					String pincode = sc.nextLine();

					Address address = new Address(addressId, state, city, pincode);
					int addressStatus = addressService.save(address);

					random = new Random();
					randomNumber = random.nextInt();
					int userId = randomNumber < 0 ? randomNumber * -1 : randomNumber;

					String password = null;
					System.out.print("Enter Username : ");
					String username = sc.nextLine();
					boolean isValid = false;
					while (!isValid) {
						System.out.println("Enter Password (Must contain atleast 8 characters-"
								+ "1 special caharacter(@, #, $, *), 1 upper case(A-Z) character and 1 digit(0-9))");
						password = sc.nextLine();
						isValid = loginService.validatePassword(password);
						if (!isValid)
							System.out.println("Weak password, Try Again");

					}

					User user = new User(userId, username, password, "customer");// only customer can sign up
					int userStatus = userService.insert(user);

					Customer customer = new Customer(customerId, firstName, lastName, email, phoneNo, registrationDate,
							userId, addressId);
					int customerStatus = customerService.insert(customer);

					if (userStatus == 1 && customerStatus == 1 && addressStatus == 1) {
						System.out.println("Sign Up Successfull !!! Try Logging In");
					} else {
						System.out.println("Sign Up failed...");
					}
				} catch (SQLException | DatabaseConnectionException | AddressInvalidException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				System.out.println("Invalid option. Please choose again.");
			}
		}
		sc.close(); // Close Scanner object after loop
	}

	public static void loginController() {
		String[] sarr = { "" };
		main(sarr);
	}
}