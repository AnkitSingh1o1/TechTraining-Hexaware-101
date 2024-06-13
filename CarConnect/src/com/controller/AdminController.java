
//Author: Ashwin Soni

package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dto.AdminDto;
import com.dto.AdminUpdate;
import com.dto.ReservationPerCustomer;
import com.dto.ReviewDto;
import com.dto.VehicleDto;
import com.dto.VendorAndCount;
import com.exception.AdminNotFoundException;
import com.exception.AuthenticationException;
import com.exception.DatabaseConnectionException;
import com.model.Admin;
import com.model.Customer;
import com.model.Reservation;
import com.model.Review;
import com.model.User;
import com.model.Vehicle;
import com.model.Vendor;
import com.options.CustomerOptions;
import com.options.VendorOptions;
import com.service.AdminService;
import com.service.LoginService;
import com.service.ReservationService;
import com.service.ReviewService;
import com.service.VehicleService;
import com.service.VendorService;

public class AdminController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AdminService adminService = new AdminService();
		VendorService vendorService = new VendorService();
		VehicleService vehicleService = new VehicleService();
		ReviewService reviewService = new ReviewService();
		LoginService loginService = new LoginService();
		ReservationService reservationService = new ReservationService();

		while (true) {

			System.out.println("----------Welcome Admin------------");
			System.out.println("Press 1 User Options");
			System.out.println("Press 2 Customer Options");
			System.out.println("Press 3 Vendor Options");
			System.out.println("Press 4 List reservations");
			System.out.println("Press 5 List Vehicles");
			System.out.println("Press 6 List Reviews");
			System.out.println("Press 7 Get Reports");
			System.out.println("Press 8 Add Admin");
			System.out.println("Press 9 Deactivate Admin");
			System.out.println("Press 10 Update Admin");
			System.out.println("Press 0 to Exit");

			// Take User input
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting Admin...");
				System.out.println("Exiting from Customer Module...");
				LoginController.loginController();
				break;
			}
			switch (input) {

			case 1:

				System.out.println("Choose from USER options");
				UserController.userOptions();
				break;

			case 2:

				CustomerOptions.customerOptions();
				break;

			case 3:

				VendorOptions.vendorOptions();
				break;

			case 4:
				// Display all Reservations
				try {
					List<Reservation> list = reservationService.findAll();
					for (Reservation r : list) {
						System.out.println(r);
					}
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				// Display all Vehicles
				try {
					List<Vehicle> list = vehicleService.DisplayAll();
					for (Vehicle v : list) {
						System.out.println(v);
					}
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				// Display all Reviews
				try {
					List<Review> list = reviewService.DisplayAll();
					for (Review r : list) {
						System.out.println(r);
					}
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 7:

				while (true) {
					System.out.println("----------Get Reports------------");
					System.out.println("Press 1 Vendor With Review Rating >= 4");// vendorWithBadReview
					System.out.println("Press 2 Vendor With Review Rating < 3 ");// vendorWithGoodReview
					System.out.println("Press 3 Get Reservation Count Per Customer");// getReservationCountPerCustomer(Service)
					System.out.println("Press 4 Get Vehicle Stats");// getVehicleStats
					System.out.println("Press 5 Get Review Stats");
					System.out.println("Press 6 Total Revenue Report");
					System.out.println("Press 7 Vehicle Revenue");
					System.out.println("Press 8 List Customer with NO Reservation History");
					System.out.println("Press 0 To Exit");

					int option = sc.nextInt();

					if (option == 0) {
						break;
					}

					switch (option) {

					case 1:
						try {
							List<VendorAndCount> list = vendorService.vendorWithGoodReviewCount();
							for (VendorAndCount v : list) {
								System.out.println(v);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2:
						try {
							List<VendorAndCount> list = vendorService.vendorWithBadReviewCount();
							for (VendorAndCount v : list) {
								System.out.println(v);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 3:
						try {
							List<ReservationPerCustomer> list = reservationService.getReservationCountPerCustomer();
							for (ReservationPerCustomer r : list) {
								System.out.println(r);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 4:

						try {
							List<VehicleDto> list = vehicleService.getVehicleStats();
							for (VehicleDto v : list) {
								System.out.println(v);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 5:

						try {
							List<ReviewDto> list = reviewService.getReviewStats();
							for (ReviewDto r : list) {
								System.out.println(r);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 6:
						// fetch total revenue for the reservations with completed status
						try {
							double revenue = adminService.getRevenue();
							System.out.println("The Total Revenue is : " + revenue);

						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 7:
						// fetch the revenue report for each vehicle and displays vehicle_id and total
						// revenue earned

						try {
							System.out.println("Enter sort direction(ASC for Ascending | DSC for Descending)");
							sc.nextLine();
							String direction = sc.nextLine();
							List<AdminDto> list = adminService.vehicleRevenue();
							list = adminService.sortVehicleRevenue(list, direction);
							for (AdminDto a : list) {
								System.out.println(a);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}

						break;
					case 8:
						try {
							List<Customer> list = adminService.getCustomerWithNoReservation();
							for (Customer a : list) {
								System.out.println(a);
							}
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}
						break;
					}

				}
				break;

			case 8:
				try {
					Random random = new Random();
					int randomNumber = random.nextInt();
					int adminId = randomNumber < 0 ? randomNumber * (-1) : randomNumber;
					sc.nextLine();
					System.out.println("Enter Admin First Name");
					String adminFName = sc.nextLine();
					System.out.println("Enter Admin Last Name");
					String adminLName = sc.nextLine();
					System.out.println("Enter Admin Email");
					String adminEmail = sc.nextLine();
					System.out.println("Enter Admin Phone Number");
					String adminPhoneNumber = sc.nextLine();
					System.out.println("Enter Admin Role");
					String adminRole = sc.nextLine();
					System.out.println("Enter Admin Join Date");
					String adminJoinDate = sc.nextLine();

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

					Admin admin = new Admin(adminId, adminFName, adminLName, adminEmail, adminPhoneNumber, adminRole,
							adminJoinDate, userId);
					User user = new User(userId, username, password, "admin");
					int status = adminService.addAdmin(admin);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 9:
				try {
					List<Admin> list = adminService.findAll();
					System.out.println("Enter the Admin Id from the list you want to delete");

					int adminId = sc.nextInt();
					adminService.deactivateAdmin(adminId);

					System.out.println("Admin with : " + adminId + " has been Deactivated");

				} catch (AdminNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 10:
				try {
					List<Admin> list = adminService.findAll();
					System.out.println("Enter the Admin Id from the list you want to Update");
					int adminId = 0;
					while (true) {
						try {
							System.out.println("Press 0 to go BACK");
							adminId = sc.nextInt();
							if (adminId == 0) {
								break;
							}
							adminService.getAdminById(adminId);
							break;
						} catch (AdminNotFoundException e) {
							System.out.println(e.getMessage());
						} catch (SQLException | DatabaseConnectionException e) {
							System.out.println(e.getMessage());
						}

					}
					sc.nextLine();
					System.out.println("Enter Admin First Name");
					String adminFName = sc.nextLine();
					System.out.println("Enter Admin Last Name");
					String adminLName = sc.nextLine();
					System.out.println("Enter Admin Email");
					String adminEmail = sc.nextLine();
					System.out.println("Enter Admin Phone Number");
					String adminPhoneNumber = sc.nextLine();
					System.out.println("Enter Admin Role");
					String adminRole = sc.nextLine();

					AdminUpdate admin = new AdminUpdate(adminFName, adminLName, adminEmail, adminPhoneNumber,
							adminRole);

					adminService.updateAdmin(adminId, admin);
					System.out.println("Admin with : " + adminId + " has been Updated");

				} catch (AdminNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}

				break;
			}

		}

	}

	public static void adminMenu(String username, String password) {
		AdminService adminService = new AdminService();
		String adminId = null;
		try {
			adminId = adminService.getAdminIdByUsername(username, password) + " ";

		} catch (AuthenticationException | SQLException | DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}
		String[] charary = { adminId };
		main(charary);
	}

}
