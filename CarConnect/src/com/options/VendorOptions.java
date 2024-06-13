// Author: Ankit Singh

package com.options;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.controller.AdminController;
import com.exception.AddressInvalidException;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Address;
import com.model.User;
import com.model.Vendor;
import com.service.UserService;
import com.service.VendorService;
import com.utility.RandomId;
import com.service.AddressService;

public class VendorOptions {
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		VendorService vendorService = new VendorService();
		UserService userService = new UserService();
		AddressService addressService = new AddressService();
		
		while (true) {
			System.out.println();
			System.out.println("----------Vendor Options-------------");
			System.out.println();
			System.out.println("Press 1. Insert Vendor ");
			System.out.println("Press 2. Update Vendor ");
			System.out.println("Press 3. Delete Vendor ");
			System.out.println("Press 4. Deactivate Vendor ");
			System.out.println("Press 5. Show All Vendors ");
			System.out.println("Press 0. Exit");
			
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from Vendor Module...");
				AdminController.adminMenu("admin", "admin123");
				break;
			}
			
			
			switch (input) {
			case 1:
			try {
				sc.nextLine();
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
				System.out.print("Enter state: ");
				String state = sc.nextLine();
				System.out.print("Enter city: ");
				String city = sc.nextLine();
				System.out.print("Enter pincode: ");
				int pincode = sc.nextInt();

				sc.nextLine();
				System.out.print("Create username: ");
				String username = sc.nextLine();
				System.out.print("Create password: ");
				String password = sc.nextLine();
				
				int vendorId = RandomId.getRandomId();
				int userId = RandomId.getRandomId();
				int addressId = RandomId.getRandomId();
				

				//Add into user -> address -> vendor
				User newUser = new User(userId, username, password, "Vendor");
				
				int uStatus = userService.insert(newUser);
				
				Address newAdd = new Address(addressId, state, city, ""+pincode);
				
				int aStatus = addressService.save(newAdd);
				
				Vendor vendor = new Vendor(vendorId, firstName, lastName, email, phoneNo, registrationDate, userId,
						addressId);
				int vStatus = vendorService.save(vendor);
				
				if (vStatus == 1 && uStatus == 1 && aStatus == 1)
					System.out.println("Vendor added successfully. Try loging in. Thank you!!!");
				else
					System.out.println("Record not added");
			} catch (SQLException | DatabaseConnectionException | AddressInvalidException e) {
				System.out.println(e.getMessage());
			} 
			
			break;

			case 2:
				List<Vendor> list = null;
				try {
					list = vendorService.findAll();
					for (Vendor a : list)
						System.out.println(a);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.print("Choose vendor id from above: ");
				int id = sc.nextInt();
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

				try {
					List<User> list1 = userService.findAll();
					for (User a : list1)
						System.out.println(a);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.print("Choose user id from above : ");
				int userId = sc.nextInt();

				System.out.print("Enter address id : ");
				int addressId = sc.nextInt();
				
				Vendor vendor = new Vendor(id, firstName, lastName, email, phoneNo, registrationDate, userId,
						addressId);
				try {
					int status = vendorService.update(id, vendor);
					if (status == 1)
						System.out.println("Record updated successfully");
					else
						System.out.println("Record not updated");
				} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				
			case 3:
				System.out.print("Enter vendor id : ");
				try {
					vendorService.deleteById(sc.nextInt());
					System.out.println("Record deleted successfully");
				} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:
				System.out.print("Enter customer id : ");
				try {
					vendorService.softDeleteById(sc.nextInt());
					System.out.println("Record de-activated successfully");
				} catch (SQLException | ResourceNotFoundException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 5:
				try {
					list = vendorService.findAll();
					for (Vendor a : list)
						System.out.println(a);
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;

			}
		}

}

public static void vendorOptions() {
	String[] args = { "" };
	main(args);
}

}
