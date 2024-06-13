// Author : Anirudh Suryawanshi

package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.UserCountByRoleDto;
import com.dto.UserReservationHistoryDto;
import com.dto.UserTotalReservationsByStatusDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.User;

public interface UserDao {
	int save(User user) throws SQLException,DatabaseConnectionException;
	void deleteById(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	void softDeleteById(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	int update(User user) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<User> findALL() throws SQLException,DatabaseConnectionException;
	boolean findOne(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<UserCountByRoleDto> getUserCountByRole() throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<UserTotalReservationsByStatusDto> getUserTotalReservationsByStatus() throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
	List<UserReservationHistoryDto> getUserReservationHistory() throws SQLException,ResourceNotFoundException,DatabaseConnectionException;
}
