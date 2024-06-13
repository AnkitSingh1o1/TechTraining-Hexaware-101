// Author: Ankit Singh

package com.dao;

import java.sql.SQLException;
import java.util.List;
import com.dto.ReservationPerCustomer;
import com.exception.DatabaseConnectionException;
import com.exception.ReservationException;
import com.model.Reservation;

public interface ReservationDao {

	//CRUD
	int save(Reservation reservation) throws SQLException, ReservationException, DatabaseConnectionException; // 1 for successful insertion / 0 for failure
	int deleteById(int id)throws SQLException, ReservationException, DatabaseConnectionException;
	void softDeleteById(int id) throws SQLException, ReservationException, DatabaseConnectionException;
	int update(int id, Reservation updatedReservation) throws SQLException, ReservationException, DatabaseConnectionException;
	List<Reservation> findAll() throws SQLException, DatabaseConnectionException;
	Boolean findOne(int id) throws SQLException, ReservationException, DatabaseConnectionException;
	
	//Extra
	boolean ifVehicleIsAvailableForReservation(int id) throws SQLException, ReservationException, DatabaseConnectionException;
	List<Reservation> customerFindAllReservationsByStatus(int id, String status) throws SQLException, ReservationException, DatabaseConnectionException;
	List<Reservation> vendorFindAllReservationsByStatus(int id, String status) throws SQLException, ReservationException, DatabaseConnectionException;

	List<Reservation> findAllReservationsById(int id) throws SQLException, ReservationException, DatabaseConnectionException;
	//GroupBy
	List<ReservationPerCustomer> getReservationCountPerCustomer() throws SQLException, DatabaseConnectionException;
	Reservation getReservatonById(int reservationId) throws SQLException, ReservationException, DatabaseConnectionException;
}

