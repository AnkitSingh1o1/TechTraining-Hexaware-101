// Author : Anirudh Suryawanshi

package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.dto.CustomerReservationDetailsDto;
import com.dto.CustomersWithReservationsDto;
import com.dto.CustomersWithTotalSpentDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Customer;

public interface CustomerDao {
	int save(Customer customer) throws SQLException,DatabaseConnectionException;
	void deleteById(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	void softDeleteById(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	int update(Customer customer) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<Customer> findALL() throws SQLException,DatabaseConnectionException;
	boolean findOne(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<CustomersWithReservationsDto> getCustomerWithNumberOfReservations() throws SQLException,DatabaseConnectionException;
	List<CustomersWithTotalSpentDto> getTotalSpentByCustomer() throws SQLException,DatabaseConnectionException;
	List<CustomerReservationDetailsDto> getCustomerReservationDetails(int customerId) throws SQLException,DatabaseConnectionException;
	int getCustomerIdByUsernamePassword(String username, String password)throws SQLException,DatabaseConnectionException;
	int getUserIdByCustomerId(int customerid) throws SQLException,DatabaseConnectionException;	
	int getAddressIdByCustomerId(int customerid) throws SQLException,DatabaseConnectionException;	
}
