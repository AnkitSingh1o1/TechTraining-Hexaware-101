package com.dao;

import java.sql.SQLException;

import com.exception.DatabaseConnectionException;
//import com.model.Login;
import com.model.User;


public interface LoginDao {

	User login(String username, String password) throws SQLException, DatabaseConnectionException;
	int resetPassword(String user, String newPassword) throws SQLException, DatabaseConnectionException;

}
