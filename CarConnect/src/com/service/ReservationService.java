// Author: Ankit Singh

package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.dao.ReservationDao;
import com.dao.ReservationDaoImpl;
import com.dao.VehicleDao;
import com.dao.VehicleDaoImpl;
import com.dto.ReservationPerCustomer;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.ReservationException;
import com.exception.VehicleNotFoundException;
import com.model.Reservation;

public class ReservationService {

	ReservationDao dao = new ReservationDaoImpl();
	VehicleDao vehicleDao  = new VehicleDaoImpl();
	VehicleService vehicleService = new VehicleService();
	
	public int save(Reservation reservation) throws SQLException, ReservationException, DatabaseConnectionException{
		int vehicleId = reservation.getVehicle_id();
		if(dao.ifVehicleIsAvailableForReservation(vehicleId)) {
			return dao.save(reservation);
		}
		else
			throw new ReservationException("Selected vehicle not available for reservation...Sorry");
	}
	
	
	public int deleteById(int id) throws SQLException, ReservationException, DatabaseConnectionException{
		if(dao.findOne(id)) 
			return dao.deleteById(id);
		else 
			throw new ReservationException("Id not found");	
	}
	
	
	public void softDeleteById(int id) throws SQLException, ReservationException, DatabaseConnectionException{
		if(dao.findOne(id))
			dao.softDeleteById(id);
		else 
			throw new ReservationException("Id not found");
	}
	
	
	public int update(int id, Reservation updatedReservation) throws SQLException, ReservationException, DatabaseConnectionException{
		if(dao.findOne(id))
			return dao.update(id, updatedReservation);
		else 
			throw new ReservationException("Id not found");
		
	}
	
	
	public List<Reservation> findAll() throws SQLException, DatabaseConnectionException{
		return dao.findAll();
	}
	
	
	public Boolean findOne(int id) throws SQLException, DatabaseConnectionException, ReservationException{
		if(dao.findOne(id))
			return true;
		else 
			throw new ReservationException("Id not found");
	}

	
	public List<Reservation> findAllReservationsById(int id) throws SQLException, ReservationException, DatabaseConnectionException {
		if(dao.findOne(id))
			return dao.findAllReservationsById(id);
		else 
			throw new ReservationException("Id not found");
	}
	
	
	public List<ReservationPerCustomer> getReservationCountPerCustomer() throws SQLException, DatabaseConnectionException{
		return dao.getReservationCountPerCustomer();
	}
	
	
	public double getTotalCost(int vehicleId, String startDate, String endDate) throws SQLException, NullPointerException,
	InvalidInputException, DateTimeParseException, VehicleNotFoundException, DatabaseConnectionException {
		
		if(vehicleDao.findOne(vehicleId)) {
			
			//Get Daily rate of a vehicle by vehicleId
			double costPerDay = vehicleService.getDailyRate(vehicleId);
						
			// Parse the date strings to LocalDate objects
			LocalDate date1 = null;
			LocalDate date2 = null;
		
			date1 = LocalDate.parse(startDate);
			date2 = LocalDate.parse(endDate);
		

			// Calculate the difference in days between the two dates
			int daysDifference = (int) ChronoUnit.DAYS.between(date1, date2);
				        
			return costPerDay*daysDifference;
		}
		else
			throw new VehicleNotFoundException("Id not found");
	}
	
	
	public List<Reservation> customerFindAllReservationsByStatus(int id, String status) throws SQLException, ReservationException, DatabaseConnectionException {
		
		return dao.customerFindAllReservationsByStatus(id, status);
	}
	
	
	public List<Reservation> vendorFindAllReservationsByStatus(int id, String status) throws SQLException, ReservationException, DatabaseConnectionException {
		if(dao.findOne(id))
			return dao.vendorFindAllReservationsByStatus(id, status);
		else 
			throw new ReservationException("Id not found");
	}
	

	public Reservation getReservatonById(int reservationId) throws SQLException, ReservationException, DatabaseConnectionException {
		if(dao.findOne(reservationId))
			return dao.getReservatonById(reservationId);
		else 
			throw new ReservationException("Id not found");
	}
}
