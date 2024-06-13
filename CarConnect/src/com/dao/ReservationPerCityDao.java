package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.ReservationPerCity;
import com.exception.DatabaseConnectionException;

public interface ReservationPerCityDao {

	List<ReservationPerCity> getReservationCountPerCity() throws SQLException, DatabaseConnectionException;

}
