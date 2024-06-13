// Author : Anirudh Suryawanshi

package com.options;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.controller.AdminController;
import com.dto.CustomerReservationDetailsDto;
import com.dto.CustomersWithReservationsDto;
import com.dto.CustomersWithTotalSpentDto;
import com.exception.AddressInvalidException;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Address;
import com.model.Customer;
import com.service.AddressService;
import com.service.CustomerService;

public class CustomerOptions {
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		AddressService addressService = new AddressService();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("----------Customer Options-------------");
			System.out.println();
//			System.out.println("Press 1. Insert Customer ");
			System.out.println("Press 1. Update Customer ");
			System.out.println("Press 2. Delete Customer ");
			System.out.println("Press 3. Deactivate Customer ");
			System.out.println("Press 4. Show All Customers ");
			System.out.println("Press 5. Show Customers with Number of Reservations");
			System.out.println("Press 6. Show Customers with Total Spent");
			System.out.println("Press 7. Show Customer Reservation Details");
			System.out.println("Press 0. To Exit");
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from Customer Module...");
				AdminController.adminMenu("admin", "admin123");
				break;
			}
			switch (input) {
//			case 1:
//				Random random = new Random();
//				int randomNumber = random.nextInt();
//				int id = randomNumber < 0 ? randomNumber * -1 : randomNumber;
//				sc.nextLine();
//				System.out.print("Enter first name : ");
//				String firstName = sc.nextLine();
//				System.out.print("Enter last name : ");
//				String lastName = sc.nextLine();
//				System.out.print("Enter email : ");
//				String email = sc.nextLine();
//				System.out.print("Enter phone no. : ");
//				String phoneNo = sc.nextLine();
//				System.out.print("Enter phone Registration Date : ");
//				String registrationDate = sc.nextLine();
//
//				try {
//					List<User> list = userService.findAll();
//					for (User a : list)
//						System.out.println(a);
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				}
//				System.out.print("Choose user id from above : ");
//				int userId = sc.nextInt();
//
////				System.out.print("Enter user id : ");
////				int userId = sc.nextInt();
//
//				System.out.print("Enter address id : ");
//				int addressId = sc.nextInt();
//
//				Customer customer = new Customer(id, firstName, lastName, email, phoneNo, registrationDate, userId,
//						addressId);
//				try {
//					int status = customerService.insert(customer);
//					if (status == 1)
//						System.out.println("Record added successfully");
//					else
//						System.out.println("Record not added");
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				}
//				break;

			case 1:
				List<Customer> list;
				try {
					list = customerService.findAll();
					for (Customer a : list)
						System.out.println(a);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				try {
					System.out.print("Choose customer id from above: ");
					int customerid = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter first name : ");
					String firstName = sc.nextLine();
					System.out.print("Enter last name : ");
					String lastName = sc.nextLine();
					System.out.print("Enter email : ");
					String email = sc.nextLine();
					System.out.print("Enter phone no. : ");
					String phoneNo = sc.nextLine();
					System.out.print("Enter phone Registration Date : ");
					String registrationDate = sc.nextLine();

					int userId = customerService.getUserIdByCustomerId(customerid);
					int addressId = customerService.getAddressIdByCustomerId(customerid);

					System.out.print("Enter your State : ");
					String state = sc.nextLine();
					System.out.print("Enter your city : ");
					String city = sc.nextLine();
					System.out.print("Enter your Pincode : ");
					String pincode = sc.nextLine();

					Address address = new Address(addressId, state, city, pincode);
					Customer customer = new Customer(customerid, firstName, lastName, email, phoneNo, registrationDate,
							userId, addressId);

					int status = customerService.update(customer);
					int addressStatus = addressService.updateById(address);
					if (status == 1 && addressStatus == 1)
						System.out.println("Record updated successfully");
					else
						System.out.println("Record not updated");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (AddressInvalidException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				System.out.print("Enter customer id : ");
				try {
					customerService.deleteById(sc.nextInt());
					System.out.println("Record deleted successfully");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				}
				break;

			case 3:
				System.out.print("Enter customer id : ");
				try {
					customerService.softDeleteById(sc.nextInt());
					System.out.println("Record de-activated successfully");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				}
				break;

			case 4:
				try {
					list = customerService.findAll();
					for (Customer a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("-------------Customers with Reservations-------------");
				try {
					List<CustomersWithReservationsDto> list1 = customerService.getCustomerWithNumberOfReservations();
					for (CustomersWithReservationsDto a : list1)
						System.out.println(a.getName() + " : " + a.getNumberOfReservations());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				System.out.println("-------------Customers with Total Spent-------------");
				try {
					List<CustomersWithTotalSpentDto> list1 = customerService.getTotalSpentByCustomer();
					for (CustomersWithTotalSpentDto a : list1)
						System.out.println(a.getName() + " : Rs. " + a.getTotalSpent());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 7:
				try {
					list = customerService.findAll();
					for (Customer a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println("Error in DB" + e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				System.out.print("Choose customer id from above : ");
				int customerId = sc.nextInt();
				try {
					List<CustomerReservationDetailsDto> details = customerService
							.getCustomerReservationDetails(customerId);
					if (details.size() != 0)
						for (CustomerReservationDetailsDto a : details)
							System.out.println(a);
					else
						System.out.println("Details not available");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}
		sc.close();
	}

	public static void customerOptions() {
		String[] sarr = { "" };
		main(sarr);
	}
}
