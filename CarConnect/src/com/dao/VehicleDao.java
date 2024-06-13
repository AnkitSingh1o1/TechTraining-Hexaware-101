package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.VehicleDto;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;

public interface VehicleDao {
     int addVehicle(Vehicle vehicle) throws SQLException,DatabaseConnectionException;
     List<Vehicle> findAll() throws SQLException,DatabaseConnectionException;
     void deleteById(int vehicleId) throws SQLException,VehicleNotFoundException,DatabaseConnectionException;
     Boolean findOne(int vehicleId) throws SQLException,DatabaseConnectionException; 
     void softDeleteById(int id) throws SQLException,VehicleNotFoundException,DatabaseConnectionException;
     List<VehicleDto> getVehicleStats() throws SQLException,DatabaseConnectionException;
     List<Vehicle> findAllAvailable() throws SQLException,DatabaseConnectionException;
     List<Vehicle> findMyVehicles(int vendorId) throws SQLException,DatabaseConnectionException;
     double getDailyRate(int vehicleId)throws SQLException,InvalidInputException,DatabaseConnectionException;
     int getVehicleYear(int vehicleId)throws SQLException,InvalidInputException,DatabaseConnectionException;
     int updateVehicleAvailability( Vehicle vehicle1) throws SQLException,VehicleNotFoundException,DatabaseConnectionException;
     int updateVehicleDailyRate( Vehicle vehicle12) throws SQLException,VehicleNotFoundException, DatabaseConnectionException,DatabaseConnectionException;
}
