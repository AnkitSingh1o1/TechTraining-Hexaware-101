package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dao.VehicleDaoImpl;
import com.dto.VehicleDto;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;
import com.service.VehicleService;

public class VehicleController {

	public static void main(String[] args) {
		VehicleService vehicleService=new VehicleService();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("--------Vehicle Operations-------");
			System.out.println("Press 1. Insert Vehicle");
			System.out.println("Press 2. Display All Vehicle");
			System.out.println("Press 3. Delete Vehicle");
			System.out.println("Press 4. Deactivate Delete Vehicle[Soft delete]");
			System.out.println("Press 5. Display Vehicle Stats");
			System.out.println("Press 6. Display All Available Vehicle");
			System.out.println("Press 7. Display Vendor's Vehicle");
			System.out.println("Press 8. Get Vehicle's Daily Rate");
			System.out.println("Press 9. Get Vehicle's Age");
			System.out.println("Press 10. Get Vehicles In Sorted Order Of Daily Rate");
			System.out.println("Press 11. Update Vehicle's Availability");
			System.out.println("Press 12. Update Vehicle's Daily Rate");
			System.out.println("Press 0. To Exit");
			int input=sc.nextInt();
			if(input==0)
			{System.out.println("Exiting Vehicle Module...");
			break;	
			}
			switch(input) {
			case 1:
				Random random=new Random();
				int randomNumber = random.nextInt(); 
				int vehicle_id =randomNumber<0?randomNumber*-1:randomNumber; 
				System.out.println("Name of Model");
				sc.nextLine();
				String vehicle_model = sc.nextLine();   
				System.out.println("Enter Model's Manufacturer");
				String vehicle_make =sc.nextLine(); 
				System.out.println("Year of Manufacture");
				int vehicle_year=sc.nextInt();
				System.out.println("color of vehicle");
				sc.nextLine();
				String vehicle_color=sc.nextLine(); 
				
				System.out.println("Registration number of vehicle");
				String vehicle_registration_no=sc.nextLine();
				System.out.println("Availability of vehicle");
				Boolean vehicle_availability=sc.nextBoolean(); 
				System.out.println("Daily Rate of vehicle");
				Double vehicle_daily_rate=sc.nextDouble();
				List<Vehicle> list;
				try {
					list = vehicleService.DisplayAll();
					for(Vehicle v : list) {
						System.out.println(v);
					}
				} catch (SQLException | DatabaseConnectionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("Enter Vendor ID");
				int vendor_id=sc.nextInt(); 
				Vehicle vehicle =new Vehicle(vehicle_id, vehicle_model, vehicle_make, vehicle_year, vehicle_color,
						 vehicle_registration_no, vehicle_availability, vehicle_daily_rate, vendor_id);
				
				
				int status;
				try {
					status = vehicleService.add(vehicle);
					if(status == 1)
						System.out.println("Vehicle record added to DB..");
					else
						System.out.println("Insert operation failed");
				} catch (SQLException|DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				
				List<Vehicle> list1;
				try {
					list1 = vehicleService.DisplayAll();
				
					for(Vehicle v:list1) {
						System.out.println(v);
					}
				} catch (SQLException|DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter vehicle ID");
				try {
					vehicleService.deleteByid(sc.nextInt());
					System.out.println("Artist record deleted..");
				} catch (SQLException e) {
					 System.out.println(e.getMessage());
				} catch (VehicleNotFoundException|DatabaseConnectionException e) {
					 System.out.println(e.getMessage());
				}				
				break; 
			case 4: 
				System.out.println("Enter vehicle ID");
				try {
					vehicleService.softDeleteByid(sc.nextInt());
					System.out.println("Vehicle de-activated..");
				} catch (VehicleNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException|DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}				
				break; 
			case 5:
				try {
					List<VehicleDto> list11 = vehicleService.getVehicleStats();
					System.out.println("------------------------------------------------------------\n");
					System.out.format("%15s%18s%20s","Vendor Id", "Name of Vendor", "Number of Vehicle");
					System.out.println("\n-----------------------------------------------------------");
					for(VehicleDto a : list11) {
						System.out.format("%15d%18s%10d", a.getVendor_id(), a.getVendorName(),a.getNumberOfVehicles());
						System.out.print("\n");
				}
					System.out.println("-------------------------------------------------------------");
				} catch (SQLException|DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}	
				break; 
			case 6:
				List<Vehicle> list11;
				try {
					list11 = vehicleService.findAllAvailable();
					for(Vehicle v1:list11) {
						System.out.println(v1);
					}
				} catch (SQLException|DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			break;
			case 7:
				try {
					//display all artists 
					List<Vehicle> list111 = vehicleService.DisplayAll();
					for(Vehicle v : list111) {
						System.out.println(v);
					}
					//read artist id
					System.out.println("Enter Vendor ID");
					int vendorId = sc.nextInt();
					
					//fetch artworks of this artist
					List<Vehicle> listVehicle = vehicleService.findMyVehicles(vendorId);
					for(Vehicle a : listVehicle) {
						System.out.println(a);
					}
				}  catch (SQLException|DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}				
				break; 
			case 8:
				try {
				List<Vehicle> list111 = vehicleService.DisplayAll();
				for(Vehicle v : list111) {
					System.out.println(v);
				}
				System.out.println("Enter Vehicle ID");
				int vehicleId = sc.nextInt();
				double vehicleDailyRate=vehicleService.getDailyRate(vehicleId);
				System.out.println(vehicleDailyRate);}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}	
				catch (InvalidInputException|DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}	
				break;
			case 9:
				System.out.println("Enter vehicle ID");
				int vehicle_id1=sc.nextInt();
				try {
					int age;
					
						age = vehicleService.getVehicleAge(vehicle_id1);
						System.out.println(age);
					} catch (SQLException|InvalidInputException|DatabaseConnectionException e) {
						System.out.println(e.getMessage());
					}
				break;
			case 10:
			
				
				try {
					List<Vehicle>list2 =new VehicleDaoImpl().findAll();
					System.out.println("Enter the Sorting Order");
					sc.nextLine();
					String sortOrder=sc.nextLine();
					list2=vehicleService.sortVehicleByDailyRate(list2, sortOrder);
					for(Vehicle v:list2) {
						System.out.println(v);
					}
				} catch (SQLException|DatabaseConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}break;
			case 11:
				System.out.println("Enter the Vehicle id of the vehicle which you want to Update");
				int vehicle_id2=sc.nextInt();
				System.out.println("Update the availability of Vehicle");
				boolean availability=sc.nextBoolean();
				Vehicle vehicle1=new Vehicle(vehicle_id2,availability);
				
				int status1;
				try {
				
					status1 = vehicleService.updateVehicleAvailability(vehicle1 );
					if(status1==1)
						System.out.println("Vehicle's Availability Updated");
					else
						System.out.println("Updation Failed");
					
				} catch (SQLException|VehicleNotFoundException|DatabaseConnectionException e) {
				System.out.println(e.getMessage());
				} 
				break;
			case 12:
				System.out.println("Enter the Vehicle ID of the vehicle whose Daily Rate you want to Update");
				int vehicle_Id=sc.nextInt();
				System.out.println("New Daily Rate For vehicle");
				double newRate1=sc.nextDouble();
				Vehicle vehicle12=new Vehicle(vehicle_Id,newRate1);

				int statuss;
				try {
				
					statuss = vehicleService.updateVehicleDailyRate(vehicle12 );
					if(statuss==1)
						System.out.println("Vehicle's Daily Rate Updated");
					else
						System.out.println("Updation Failed");
					
				} catch (SQLException|VehicleNotFoundException|DatabaseConnectionException e) {
				System.out.println(e.getMessage());
				} 
				break;
				
				
				
				 
			}
		}
sc.close();
	}

}
