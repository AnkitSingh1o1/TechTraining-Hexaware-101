
//Author: Ashwin Soni

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.AdminDto;
import com.dto.AdminUpdate;
import com.exception.DatabaseConnectionException;
import com.model.Admin;
import com.model.Customer;
import com.utility.DBConnection;

public class AdminDaoImpl implements AdminDao {

	@Override
	public double getRevenue() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT SUM(reservation_total_cost) as 'totalRevenue' FROM reservation WHERE reservation_status = 'completed'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		double totalRevenue = 0;
		if (rst.next())
			totalRevenue = rst.getDouble("totalRevenue");
		DBConnection.dbClose();

		return totalRevenue;

	}

	@Override
	public List<AdminDto> vehicleRevenue() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();

		String sql = "SELECT v.vehicle_id,v.vehicle_model, sum(r.reservation_total_cost) as "
				+ "'vehicleRevenue' FROM reservation r JOIN vehicle v ON v.vehicle_id = "
				+ "r.vehicle_id GROUP BY vehicle_id";

		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		List<AdminDto> list = new ArrayList<>();

		while (rst.next()) {
			int vehicleId = rst.getInt("vehicle_id");
			int totalRevenue = rst.getInt("vehicleRevenue");
			String vehicleModel = rst.getString("vehicle_model");

			AdminDto admin = new AdminDto(vehicleId, vehicleModel, totalRevenue);
			list.add(admin);

		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int registerAdmin(Admin admin) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "INSERT INTO admin(admin_id,admin_first_name,admin_last_name,"
				+ "admin_email,admin_phone_number,admin_role,admin_join_date) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, admin.getAdminId());
		pstmt.setString(2, admin.getAdminFirstName());
		pstmt.setString(3, admin.getAdminLastName());
		pstmt.setString(4, admin.getAdminEmail());
		pstmt.setString(5, admin.getAdminPhoneNumber());
		pstmt.setString(6, admin.getAdminRole());
		pstmt.setString(7, admin.getAdminJoinDate());

		int status = pstmt.executeUpdate();

		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<Admin> findAll() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * FROM admin";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Admin> list = new ArrayList<Admin>();
		while (rst.next()) {
			int adminId = rst.getInt("admin_id");
			String firstName = rst.getString("admin_first_name");
			String lastName = rst.getString("admin_last_name");
			String email = rst.getString("admin_email");
			String phoneNumber = rst.getString("admin_phone_number");
			String role = rst.getString("admin_role");
			String joinDate = rst.getString("admin_join_date");
			int userId = rst.getInt("user_id");
			Admin admin = new Admin(adminId, firstName, lastName, email, phoneNumber, role, joinDate, userId);
			list.add(admin);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public boolean findOne(int adminId) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * FROM admin WHERE admin_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, adminId);
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			return true;
		}
		DBConnection.dbClose();
		return false;
	}

	@Override
	public void deactivateAdmin(int adminId) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE admin SET isActive = 0 WHERE admin_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, adminId);
		pstmt.executeUpdate();

		DBConnection.dbClose();

	}

	@Override
	public void updateAdmin(int adminId, AdminUpdate admin) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE admin SET admin_first_name = ? , admin_last_name = ?, admin_email = ?, admin_phone_number = ?, admin_role = ? WHERE admin_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, admin.getAdminFirstName());
		pstmt.setString(2, admin.getAdminLastName());
		pstmt.setString(3, admin.getAdminEmail());
		pstmt.setString(4, admin.getAdminPhoneNumber());
		pstmt.setString(5, admin.getAdminRole());
		pstmt.setInt(6, adminId);

		pstmt.executeUpdate();

		DBConnection.dbClose();

	}

	@Override
	public List<Customer> getCustomerWithNoReservation() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = " SELECT * FROM customer WHERE customer_id NOT IN (SELECT customer_id FROM reservation) ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		List<Customer> list = new ArrayList<>();
		Customer customer = new Customer();
		while (rst.next()) {

			list.add(customer);

		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int getAdminIdByUsername(String username, String password) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT a.admin_id FROM admin a JOIN user u ON a.user_id = u.user_id WHERE u.user_username = ? AND u.user_password = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rst = pstmt.executeQuery();
		int adminId = 0;
		if (rst.next()) {
			adminId = rst.getInt("admin_id");
		}
		DBConnection.dbClose();
		return adminId;

	}

}
