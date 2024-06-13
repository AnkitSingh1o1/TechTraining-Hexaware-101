
//Author: Ashwin Soni

package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.AdminDto;
import com.dto.AdminUpdate;
import com.exception.DatabaseConnectionException;
import com.model.Admin;
import com.model.Customer;


public interface AdminDao {

//Returns total revenue by summing up all the total_cost of the reservation table with 'completed' status 
	double getRevenue() throws SQLException, DatabaseConnectionException;

//Returns total revenue earned by each vehicle
	List<AdminDto> vehicleRevenue() throws SQLException, DatabaseConnectionException;

//Add admin data to database
	int registerAdmin(Admin admin) throws SQLException, DatabaseConnectionException;

//Lists all Admins
	List<Admin> findAll() throws SQLException, DatabaseConnectionException;

//Finds admin by adminId
	boolean findOne(int adminId) throws SQLException, DatabaseConnectionException;

//Deactivates admin with given adminId
	void deactivateAdmin(int adminId) throws SQLException, DatabaseConnectionException;
	
//Updates Admin Details
	void updateAdmin(int adminId, AdminUpdate admin) throws SQLException, DatabaseConnectionException;
	
//Lists customers wtih no reservations
	List<Customer> getCustomerWithNoReservation() throws SQLException, DatabaseConnectionException;
	
//Fetch adminId by username
	int getAdminIdByUsername(String username, String password) throws SQLException, DatabaseConnectionException;



	

}
