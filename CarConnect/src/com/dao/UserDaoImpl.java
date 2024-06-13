// Author : Anirudh Suryawanshi

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dto.UserCountByRoleDto;
import com.dto.UserReservationHistoryDto;
import com.dto.UserTotalReservationsByStatusDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.User;
import com.utility.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public int save(User user) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "insert into user (user_id, user_username, user_password, user_role) " + "values (?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, user.getUserId());
		pstmt.setString(2, user.getUserName());
		pstmt.setString(3, user.getUserPassword());
		pstmt.setString(4, user.getUserRole());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public void deleteById(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "delete from user where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
	}

	@Override
	public void softDeleteById(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "update user set isActive='no' where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
	}

	@Override
	public int update(User user) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "update user set user_username=?, user_password=?, user_role=? " + "where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		pstmt.setString(3, user.getUserRole());
		pstmt.setInt(4, user.getUserId());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<User> findALL() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from user where isActive='Yes'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<User> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("user_id");
			String username = rs.getString("user_username");
			String password = rs.getString("user_password");
			String role = rs.getString("user_role");
			User user = new User(id, username, password, role);
			list.add(user);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public boolean findOne(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select user_id from user where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		boolean status = rs.next();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<UserCountByRoleDto> getUserCountByRole() throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select user_role,count(user_id) as number_of_users from user group by user_role";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<UserCountByRoleDto> list = new ArrayList<>();
		while (rs.next()) {
			String userRole = rs.getString("user_role");
			int numberOfUsers = rs.getInt("number_of_users");
			UserCountByRoleDto record = new UserCountByRoleDto(userRole, numberOfUsers);
			list.add(record);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<UserTotalReservationsByStatusDto> getUserTotalReservationsByStatus()
			throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT u.user_id, r.reservation_status, COUNT(*) AS reservation_status_count "
				+ "FROM Reservation r JOIN Customer c ON r.customer_id = c.customer_id "
				+ "JOIN User u ON c.user_id = u.user_id GROUP BY u.user_id,r.reservation_status;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<UserTotalReservationsByStatusDto> list = new ArrayList<>();
		while (rs.next()) {
			int userId = rs.getInt("user_id");
			String reservationStatus = rs.getString("reservation_status");
			int reservationStatusCount = rs.getInt("reservation_status_count");
			UserTotalReservationsByStatusDto record = new UserTotalReservationsByStatusDto(userId, reservationStatus,
					reservationStatusCount);
			list.add(record);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<UserReservationHistoryDto> getUserReservationHistory() throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT u.user_id, r.reservation_id, r.reservation_start_date, "
				+ "r.reservation_end_date, v.vehicle_make, v.vehicle_model "
				+ "FROM Reservation r JOIN Customer c ON r.customer_id = c.customer_id"
				+ " JOIN User u ON c.user_id = u.user_id JOIN Vehicle v ON r.vehicle_id = v.vehicle_id;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<UserReservationHistoryDto> list = new ArrayList<>();
		while (rs.next()) {
			int userId = rs.getInt("user_id");
			int reservationId = rs.getInt("reservation_id");
			String startDate = rs.getString("reservation_start_date");
			String endDate = rs.getString("reservation_end_date");
			String vehicleMake = rs.getString("vehicle_make");
			String vehicleModel = rs.getString("vehicle_model");
			UserReservationHistoryDto record = new UserReservationHistoryDto(userId, reservationId, startDate, endDate,
					vehicleMake, vehicleModel);
			list.add(record);
		}
		DBConnection.dbClose();
		return list;
	}
}
