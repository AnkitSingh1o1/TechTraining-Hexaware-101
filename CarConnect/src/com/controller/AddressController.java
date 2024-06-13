package com.controller;

import java.util.*;
import java.sql.*;
import com.exception.ResourceNotFoundException;
import com.exception.AddressInvalidException;
import com.exception.DatabaseConnectionException; 
import com.model.Address;
import com.dto.*;
import com.service.AddressService;

public class AddressController {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Press 1: Enter Address");
            System.out.println("Press 2: Delete Address");
            System.out.println("Press 3: View all Addresses");
            System.out.println("Press 4: Update Address");
            System.out.println("Press 5: Find Address ID using User ID");
            System.out.println("Press 6: List Cities by Reservation count");

            System.out.println("Press 0: Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting");
                break;
            }
            switch (input) {
                case 1:
                    Address address = new Address();
                    Random random = new Random();
                    int randomNumber = random.nextInt();
                    int id = randomNumber < 0 ? randomNumber * -1 : randomNumber;
                    address.setAddress_id(id);

                    System.out.println("Enter your state: ");
                    sc.nextLine();
                    String state = sc.nextLine();
                    address.setAddress_state(state);

                    System.out.println("Enter your city: ");
                    address.setAddress_city(sc.nextLine());

                    System.out.println("Enter your pincode: ");
                    address.setAddress_pincode(sc.nextLine());
                    try {
                        int status = addressService.save(address);
                        if (status == 1) {
                            System.out.println("Address entry is successful");
                        } else {
                            System.out.println("Address entry failed");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }catch (DatabaseConnectionException e) { 
                        System.out.println(e.getMessage());
                    } catch (AddressInvalidException e) {
						e.printStackTrace();
					}
                    break;

                case 2:
                    System.out.println("Enter address id");
                    try {
                        int status = addressService.softDeleteAddress(sc.nextInt());
                        if (status == 1) {
                            System.out.println("Address deleted successfully");
                        } else {
                            System.out.println("Address deletion failed");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }catch (DatabaseConnectionException e) { 
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Viewing all addresses");
                    try {
                        List<Address> addressList = addressService.findAll();
                        for (Address a : addressList) {
                            System.out.println(a);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }catch (DatabaseConnectionException e) { 
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                
                case 4:
                    Address address1 = new Address();
                    System.out.println("Enter Address ID: ");
                    int id1 = sc.nextInt();
                    address1.setAddress_id(id1);

                    System.out.println("Enter new state: ");
                    sc.nextLine();
                    address1.setAddress_state(sc.nextLine());

                    System.out.println("Enter new city: ");
                    address1.setAddress_city(sc.nextLine());

                    System.out.println("Enter new pincode: ");
                    address1.setAddress_pincode(sc.nextLine());

                    try {
                        int status = addressService.updateById(address1);
                        if (status == 1) {
                            System.out.println("Address updated successfully");
                        } else {
                            System.out.println("Address update failed");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }catch (DatabaseConnectionException e) {
                        System.out.println(e.getMessage());
                    }catch (AddressInvalidException e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case 5:
                	System.out.println("Enter UserId");
                	try {
                		System.out.println("Address ID: " + addressService.getAddressIdByUserId(sc.nextInt()));
                	} catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (DatabaseConnectionException e) {
                        System.out.println(e.getMessage());
                    } catch (AddressInvalidException e) {
                    	System.out.println(e.getMessage());
					} 
                	break;

                case 6:
               	 System.out.println("Viewing Cities by Reservation Count");
                    try {
                        List<ReservationPerCity> reservationPerCity = addressService.getReservationPerCity();
                        for (ReservationPerCity r : reservationPerCity) {
                            System.out.println(r.getCity()+ ": "+ r.getReservationCount());
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }catch (DatabaseConnectionException e) { 
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                           
        }}
        sc.close();
    }
}
