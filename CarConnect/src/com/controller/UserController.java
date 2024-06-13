// Author : Anirudh Suryawanshi

package com.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.dto.UserCountByRoleDto;
import com.dto.UserReservationHistoryDto;
import com.dto.UserTotalReservationsByStatusDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.User;
import com.service.UserService;

public class UserController {
	public static void main(String[] args) {
		UserService userService = new UserService();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("----------User Options-------------");
			System.out.println();
//			System.out.println("Press 1. Insert User");
			System.out.println("Press 1. Update User");
			System.out.println("Press 2. Delete User");
			System.out.println("Press 3. Deactivate User");
			System.out.println("Press 4. Show All Users");
			System.out.println("Press 5. Show Users Count By Role");
			System.out.println("Press 6. Show User Total Reservations By Status");
			System.out.println("Press 7. Show User Reservations History");
			System.out.println("Press 0. To Exit");
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from User Module...");
				AdminController.adminMenu("admin", "admin123");
				break;
			}
			switch (input) {
//			case 1:
//				Random random = new Random();
//				int randomNumber = random.nextInt();
//				int id = randomNumber < 0 ? randomNumber * -1 : randomNumber;
//				sc.nextLine();
//				System.out.print("Enter username : ");
//				String username = sc.nextLine();
//				System.out.print("Enter password : ");
//				String password = sc.nextLine();
//				System.out.print("Enter role : ");
//				String role = sc.nextLine();
//				User user = new User(id, username, password, role);
//				try {
//					int status = userService.insert(user);
//					if (status == 1)
//						System.out.println("Record added successfully");
//					else
//						System.out.println("Record not added");
//				} catch (SQLException e) {
//					System.out.println(e.getMessage());
//				}
//				break;

			case 1:
				List<User> list;
				try {
					list = userService.findAll();
					for (User a : list)
						System.out.println(a);

					System.out.print("Choose user id from above: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter username : ");
					String username = sc.nextLine();
					System.out.print("Enter password : ");
					String password = sc.nextLine();
					System.out.print("Enter role : ");
					String role = sc.nextLine();
					User user = new User(id, username, password, role);

					int status = userService.update(user);
					if (status == 1)
						System.out.println("Record updated successfully");
					else
						System.out.println("Record not updated");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					list = userService.findAll();
					for (User a : list)
						System.out.println(a);
					System.out.print("Choose user id from above: ");
					userService.deleteById(sc.nextInt());
					System.out.println("Record deleted successfully");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					list = userService.findAll();
					for (User a : list)
						System.out.println(a);
					System.out.print("Choose user id from above: ");
					userService.softDeleteById(sc.nextInt());
					System.out.println("Record de-activated successfully");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid input");
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 4:
				System.out.println("-------------All Users-------------");
				try {
					list = userService.findAll();
					for (User a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				System.out.println("-------------Users Count By Role-------------");
				try {
					List<UserCountByRoleDto> list1 = userService.getUserCountByRole();
					for (UserCountByRoleDto a : list1)
						System.out.println(a.getRole() + " : " + a.getUserCount());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 6:
				System.out.println("-------------User Total Reservations By Status-------------");
				try {
					List<UserTotalReservationsByStatusDto> list1 = userService.getUserTotalReservationsByStatus();
					for (UserTotalReservationsByStatusDto a : list1)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 7:
				System.out.println("-------------Users Reservation History-------------");
				try {
					List<UserReservationHistoryDto> list1 = userService.getUserReservationHistory();
					for (UserReservationHistoryDto a : list1)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

		}
		sc.close();
	}

	public static void userOptions() {
		String[] sarr = { "" };
		main(sarr);
	}
}
