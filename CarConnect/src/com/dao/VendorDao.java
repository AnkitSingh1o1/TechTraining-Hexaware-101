// Author: Ankit Singh

package com.dao;

import java.sql.SQLException;
import java.util.*;

import com.dto.VendorAndCount;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Vendor;

public interface VendorDao {
	
	//CRUD
	int save(Vendor vendor) throws SQLException, DatabaseConnectionException;
	int deleteById(int id)throws SQLException, ResourceNotFoundException, DatabaseConnectionException;
	void softDeleteById(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException;
	int update(int id, Vendor updatedVendor) throws SQLException, ResourceNotFoundException, DatabaseConnectionException;
	List<Vendor> findAll() throws SQLException, DatabaseConnectionException;
	Boolean findOne(int id) throws SQLException, DatabaseConnectionException;
	
	List<Vendor> findAllActiveVendor() throws SQLException, DatabaseConnectionException;
	List<VendorAndCount> countVendorVehicle() throws SQLException, DatabaseConnectionException; 
	List<VendorAndCount> vendorWithGoodReviewCount() throws SQLException, DatabaseConnectionException; // Review rating >= 4
	List<VendorAndCount> vendorWithBadReviewCount() throws SQLException, DatabaseConnectionException; // Review rating <= 3
	int getVendorIdByUsernamePassword(String username, String password) throws SQLException, ResourceNotFoundException, DatabaseConnectionException;
}
 