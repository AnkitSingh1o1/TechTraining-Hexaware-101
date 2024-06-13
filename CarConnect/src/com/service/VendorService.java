// Author: Ankit Singh

package com.service;


import java.sql.SQLException;
import java.util.List;

import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.dto.VendorAndCount;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Vendor;

public class VendorService {
	
	VendorDao dao = new VendorDaoImpl();
	
	public int save(Vendor v) throws SQLException, DatabaseConnectionException{
		return dao.save(v);
	}
	
	public int deleteById(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException{
		if(dao.findOne(id))
			return dao.deleteById(id);
		else
			throw new ResourceNotFoundException("Id not found");
	}
	
	public void softDeleteById(int id) throws SQLException,ResourceNotFoundException, DatabaseConnectionException{
		if(dao.findOne(id))
			dao.softDeleteById(id);
		else
			throw new ResourceNotFoundException("Id not found");
	}
	
	public int update(int id, Vendor updatedVendor) throws SQLException, ResourceNotFoundException, DatabaseConnectionException{
		if(dao.findOne(id))
			return dao.update(id, updatedVendor);
		else
			throw new ResourceNotFoundException("Id not found");
		
	}
	
	public List<Vendor> findAll() throws SQLException, DatabaseConnectionException{
		return dao.findAll();
	}
	
	public Boolean findOne(int id) throws SQLException, DatabaseConnectionException, ResourceNotFoundException{
		if(dao.findOne(id))
			return dao.findOne(id);
		else
			throw new ResourceNotFoundException("Id not found");
	}
	
	
	
	
	
	public List<Vendor> findAllActiveVendor() throws SQLException, DatabaseConnectionException{
		return dao.findAll();
	}
	
	public List<VendorAndCount> countVendorVehicle() throws SQLException, DatabaseConnectionException {
		return dao.countVendorVehicle();
	}
	public List<VendorAndCount> vendorWithGoodReviewCount() throws SQLException, DatabaseConnectionException{
		return dao.vendorWithGoodReviewCount();
	}
	
	public List<VendorAndCount> vendorWithBadReviewCount() throws SQLException, DatabaseConnectionException {
		return dao.vendorWithBadReviewCount();
	}
	
	public int getVendorIdByUsernamePassword(String username, String password) 
			throws SQLException, ResourceNotFoundException, DatabaseConnectionException{
		return dao.getVendorIdByUsernamePassword(username, password);
	}
	
}
