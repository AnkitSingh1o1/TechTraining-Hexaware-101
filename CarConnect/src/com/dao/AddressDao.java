package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Address;

public interface AddressDao {
	int save(Address address) throws SQLException,DatabaseConnectionException;
	boolean findOne(int address_id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	//int DeleteById(int address_id) throws SQLException;
	List<Address> findAll() throws SQLException,DatabaseConnectionException;
	int softDeleteById(int address_id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	int updateById(Address address) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	//int getAddressIdByCustomerID(int id) throws SQLException;
	int getAddressIdByUserId(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
}
