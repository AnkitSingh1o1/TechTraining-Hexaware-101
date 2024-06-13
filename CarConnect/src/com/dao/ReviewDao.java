/*Author :AKSHAY PAWAR*/

package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.ReviewDto;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Review;


public interface ReviewDao {
	 int add(Review review) throws SQLException,DatabaseConnectionException;
      List<Review> findAll() throws SQLException, DatabaseConnectionException;
      void deleteById(int id) throws SQLException,InvalidInputException,DatabaseConnectionException;
	boolean findOne(int customerId) throws SQLException,DatabaseConnectionException;
	List<Review> getReviewsByVendorId(int vendor_id) throws SQLException,InvalidInputException,DatabaseConnectionException;
	List<Review> getReviewsByVehicleId(int vehicle_id) throws SQLException,VehicleNotFoundException,DatabaseConnectionException;
	List<ReviewDto> getReviewStats() throws SQLException,DatabaseConnectionException;
	boolean findVehicle(int vehicleId) throws SQLException,DatabaseConnectionException;
	boolean findVendor(int vendorId) throws SQLException,DatabaseConnectionException;
}
