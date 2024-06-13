// Author : Anirudh Suryawanshi

package com.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.exception.DatabaseConnectionException;
import com.exception.ReservationException;
import com.exception.ResourceNotFoundException;
import com.exception.VehicleNotFoundException;
import com.model.Reservation;
import com.model.Review;
import com.model.Vehicle;
import com.service.CustomerService;
import com.service.ReservationService;
import com.service.ReviewService;
import com.service.VehicleService;

public class CustomerController {
	public static void main(String[] args) {
		VehicleService vehicleService = new VehicleService();
		ReservationService reservationService = new ReservationService();
		ReviewService reviewService = new ReviewService();
		Scanner sc = new Scanner(System.in);

		while (true) {
			int customerId = Integer.parseInt(args[0]);
			System.out.println();
			System.out.println("-------------Welcome Customer-------------");
			System.out.println();
			System.out.println("Press 1. Display All Vehicles");
			System.out.println("Press 2. Make Reservation");
			System.out.println("Press 3. My Booking Status");
			System.out.println("Press 4. My All Reservations");
			System.out.println("Press 5. Return Vehicle");
			System.out.println("Press 0. To Exit");
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from Customer Module...");
				LoginController.loginController();
				break;
			}
			switch (input) {
			case 1:
				// Getting all available vehicles
				System.out.println("-------------Available Vehicles-------------");
				try {
					List<Vehicle> list = vehicleService.findAllAvailable();
					for (Vehicle a : list)
						System.out.println(a);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				try {
					// Choosing vehicle id for getting its reviews
					System.out.print("Choose vehicle id from above to see reviews : ");
					int vehicleId = sc.nextInt();

					// Getting reviews of a particular vehicle
					System.out.println("-------------Reviews-------------");

					List<Review> list = reviewService.getReviewsByVehicleId(vehicleId);
					if (list.size() == 0)
						System.out.println("No reviews available");
					else
						for (Review a : list)
							System.out.println(
									"Comments: " + a.getReview_comment() + ", Rating: " + a.getReview_rating());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (VehicleNotFoundException e) {
					System.out.println("No reviews available");
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				// Making reservation
				System.out.println("-------------Make Reservations-------------");
				Random random = new Random();
				int randomNumber = random.nextInt();
				int id = randomNumber < 0 ? randomNumber * -1 : randomNumber;

				try {
					// Taking input from customer
					sc.nextLine();
					System.out.print("Enter start date : ");
					String startDate = sc.nextLine();
					System.out.print("Enter end date : ");
					String endDate = sc.nextLine();
					System.out.print("Enter total cost : ");
					Double totalCost = sc.nextDouble();
					String reservationStatus = "Pending";
					int adminId = 1;

					// Showing available vehicles for booking
					List<Vehicle> list = vehicleService.findAllAvailable();
					for (Vehicle a : list)
						System.out.println(a);

					System.out.print("Choose vehicle id from above to book : ");
					int vehicleId = sc.nextInt();

					Reservation reservation = new Reservation(customerId, vehicleId, id, startDate, endDate, totalCost,
							reservationStatus, adminId);

					int status = reservationService.save(reservation);
					if (status == 1)
						System.out.println("Reservation done successfully!!!");
					else
						System.out.println("Reservation failed");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					System.out.println("Please enter a valid input");
				} catch (ReservationException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				// Showing booking that have pending status
				System.out.println("-------------My Booking Status-------------");
				try {
					List<Reservation> list = reservationService.customerFindAllReservationsByStatus(customerId,
							"pending");
					for (Reservation a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ReservationException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				} 
				break;

			case 4:
				// Showing all reservations of customer
				System.out.println("-------------My All Reservations-------------");
				try {
					List<Reservation> list = reservationService.findAllReservationsById(customerId);
					for (Reservation a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ReservationException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("-------------Return Vehicle-------------");
				try {
					List<Reservation> list = reservationService.customerFindAllReservationsByStatus(customerId,
							"confirmed");
					if (list.isEmpty()) {
						System.out.print("You have no vehicle to return");
						System.out.println();
						break;
					} else {
						for (Reservation a : list)
							System.out.println(a);
						System.out.print("Choose reservation id from above to return : ");
					}
					int reservationId = sc.nextInt();
					Reservation updatedReservation = reservationService.getReservatonById(reservationId);
					updatedReservation.setReservation_status("due");
					int status = reservationService.update(reservationId, updatedReservation);
					if (status == 1)
						System.out.println("Vehicle returned successfully!!!");
					else
						System.out.println("Vehicle not returned");

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ReservationException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}
		sc.close();
	}

	public static void customerMenu(String username, String password) {
		CustomerService customerService = new CustomerService();
		String customerId = null;
		try {
			customerId = customerService.getCustomerIdByUsernamePassword(username, password) + "";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}
		String[] sarr = { customerId };
		main(sarr);
	}
}
