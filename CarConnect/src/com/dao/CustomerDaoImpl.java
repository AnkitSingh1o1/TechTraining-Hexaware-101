// Author : Anirudh Suryawanshi

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.CustomerReservationDetailsDto;
import com.dto.CustomersWithReservationsDto;
import com.dto.CustomersWithTotalSpentDto;
import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int save(Customer cutomer) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "insert into customer (customer_id, customer_first_name, customer_last_name,"
				+ " customer_email,customer_phone_number, customer_registration_date, user_id, "
				+ "address_id) values (?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cutomer.getCustomerId());
		pstmt.setString(2, cutomer.getCustomerFirstName());
		pstmt.setString(3, cutomer.getCustomerLastName());
		pstmt.setString(4, cutomer.getCustomerEmail());
		pstmt.setString(5, cutomer.getCustomerPhoneNumber());
		pstmt.setString(6, cutomer.getCustomerRegistrationDate());
		pstmt.setInt(7, cutomer.getUserId());
		pstmt.setInt(8, cutomer.getAddressId());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public void deleteById(int id) throws ResourceNotFoundException, SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "delete from customer where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
	}

	@Override
	public void softDeleteById(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "update customer set isActive='no' where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
	}

	@Override
	public int update(Customer customer) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "update customer set customer_first_name=?, customer_last_name=?, "
				+ "customer_email=?,customer_phone_number=?, customer_registration_date=?, "
				+ "user_id=?, address_id=? where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerFirstName());
		pstmt.setString(2, customer.getCustomerLastName());
		pstmt.setString(3, customer.getCustomerEmail());
		pstmt.setString(4, customer.getCustomerPhoneNumber());
		pstmt.setString(5, customer.getCustomerRegistrationDate());
		pstmt.setInt(6, customer.getUserId());
		pstmt.setInt(7, customer.getAddressId());
		pstmt.setInt(8, customer.getCustomerId());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<Customer> findALL() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from customer where isActive='Yes'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Customer> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("customer_id");
			String firstName = rs.getString("customer_first_name");
			String lastName = rs.getString("customer_last_name");
			String email = rs.getString("customer_email");
			String phoneNumer = rs.getString("customer_phone_number");
			String registrationDate = rs.getString("customer_registration_date");
			int userId = rs.getInt("user_id");
			int addressId = rs.getInt("address_id");
			Customer customer = new Customer(id, firstName, lastName, email, phoneNumer, registrationDate, userId,
					addressId);
			list.add(customer);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public boolean findOne(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select customer_id from customer where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		boolean status = rs.next();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<CustomersWithReservationsDto> getCustomerWithNumberOfReservations() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select c.customer_id, c.customer_first_name,c.customer_last_name , count(r.reservation_id) "
				+ "as number_of_reservation from customer c JOIN reservation r ON "
				+ "c.customer_id=r.customer_id group by c.customer_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<CustomersWithReservationsDto> list = new ArrayList<>();
		while (rs.next()) {
			int custmerId = rs.getInt("customer_id");
			String firstName = rs.getString("customer_first_name");
			String lastName = rs.getString("customer_last_name");
			int numberOfReservations = rs.getInt("number_of_reservation");
			String name = firstName + " " + lastName;
			CustomersWithReservationsDto record = new CustomersWithReservationsDto(custmerId, name,
					numberOfReservations);
			list.add(record);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<CustomersWithTotalSpentDto> getTotalSpentByCustomer() throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT c.customer_id, c.customer_first_name, c.customer_last_name,"
				+ " SUM(r.reservation_total_cost) AS total_spent FROM Customer c JOIN Reservation r "
				+ "ON c.customer_id = r.customer_id GROUP BY c.customer_id;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<CustomersWithTotalSpentDto> list = new ArrayList<>();
		while (rs.next()) {
			int custmerId = rs.getInt("customer_id");
			String firstName = rs.getString("customer_first_name");
			String lastName = rs.getString("customer_last_name");
			int totalSpent = rs.getInt("total_spent");
			String name = firstName + " " + lastName;
			CustomersWithTotalSpentDto record = new CustomersWithTotalSpentDto(custmerId, name, totalSpent);
			list.add(record);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<CustomerReservationDetailsDto> getCustomerReservationDetails(int customerId) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT r.reservation_id, r.reservation_start_date,r.reservation_end_date,"
				+ "r.reservation_total_cost,r.reservation_status,r.customer_id, v.vehicle_model,"
				+ " v.vehicle_make, v.vehicle_year, v.vehicle_registration_no FROM Reservation r"
				+ " JOIN Vehicle v ON r.vehicle_id = v.vehicle_id WHERE r.customer_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		ResultSet rs = pstmt.executeQuery();
		CustomerReservationDetailsDto details = null;
		List<CustomerReservationDetailsDto> list = new ArrayList<>();
		while (rs.next()) {
			int reservationId = rs.getInt("reservation_id");
			String startDate = rs.getString("reservation_start_date");
			String endDate = rs.getString("reservation_end_date");
			Double totalCost = rs.getDouble("reservation_total_cost");
			String status = rs.getString("reservation_status");
			customerId = rs.getInt("customer_id");
			String vehicleModel = rs.getString("vehicle_model");
			String vehicleMake = rs.getString("vehicle_make");
			String vehicleYear = rs.getString("vehicle_year");
			String vehicleRegistrationNo = rs.getString("vehicle_registration_no");
			details = new CustomerReservationDetailsDto(reservationId, startDate, endDate, totalCost, status,
					customerId, vehicleModel, vehicleMake, vehicleYear, vehicleRegistrationNo);
			list.add(details);
		}
		DBConnection.dbClose();
		return list;

	}

	@Override
	public int getCustomerIdByUsernamePassword(String username, String password) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select c.customer_id from customer c JOIN user u ON c.user_id=u.user_id "
				+ "where u.user_username=? AND u.user_password=?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		int customerId = 0;
		if (rs.next())
			customerId = rs.getInt("customer_id");
		DBConnection.dbClose();
		return customerId;
	}

	@Override
	public int getUserIdByCustomerId(int customerid) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select user_id from customer where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerid);
		ResultSet rs = pstmt.executeQuery();
		int userId = 0;
		if (rs.next())
			userId = rs.getInt("user_id");
		DBConnection.dbClose();
		return userId;
	}

	@Override
	public int getAddressIdByCustomerId(int customerid) throws SQLException, DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql = "select address_id from customer where customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerid);
		ResultSet rs = pstmt.executeQuery();
		int addressId = 0;
		if (rs.next())
			addressId = rs.getInt("address_id");
		DBConnection.dbClose();
		return addressId;
	}
}
