package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.DatabaseConnectionException;
import com.exception.ResourceNotFoundException;
import com.model.Address;
import com.utility.DBConnection;

public class AddressDaoImpl implements AddressDao{

	@Override
	public int save(Address address) throws SQLException,DatabaseConnectionException {
		Connection con=DBConnection.dbConnect();
		String sql="INSERT INTO address (address_id,address_state , address_city, address_pincode) VALUES (?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,address.getAddress_id());
		pstmt.setString(2, address.getAddress_state());
		pstmt.setString(3, address.getAddress_city());
		pstmt.setString(4, address.getAddress_pincode());
		int status=pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public boolean findOne(int address_id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql="select address_id from address where address_id=? and isActive='yes'";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, address_id);
		ResultSet rst  = pstmt.executeQuery();
		boolean status = rst.next(); //true / false
		DBConnection.dbClose();
		return status;
	}

	
	/*public void DeleteById(int address_id) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Connection con = DBConnection.dbConnect();
				String sql="delete from address where address_id =?";
				//prepare the statement 
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, address_id);
				pstmt.executeUpdate();
				DBConnection.dbClose();
	}*/
@Override
	public int softDeleteById(int id) throws SQLException,ResourceNotFoundException,DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql="update address set isActive='no' where address_id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
	int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;}

	@Override
	public List<Address> findAll() throws SQLException,DatabaseConnectionException {
		Connection con = DBConnection.dbConnect();
		String sql="select * from address where isActive='yes'";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst  = pstmt.executeQuery();
		List<Address> list=new ArrayList<>();
		while(rst.next() == true) {
			int id  = rst.getInt("address_id");
			String  state= rst.getString("address_state");
			String city = rst.getString("address_city");
			String pincode = rst.getString("address_pincode");
			 Address address = new Address(id,state,city,pincode); //100X 200X 300X
			list.add(address);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int updateById(Address address) throws SQLException,ResourceNotFoundException,DatabaseConnectionException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "update address set address_state=?, address_city=?, address_pincode=? WHERE address_id=?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, address.getAddress_state());
	    pstmt.setString(2, address.getAddress_city());
	    pstmt.setString(3, address.getAddress_pincode());
	    pstmt.setInt(4, address.getAddress_id());
	    int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;}

	@Override
	public int getAddressIdByUserId(int id) throws SQLException, ResourceNotFoundException, DatabaseConnectionException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "SELECT address_id FROM address WHERE address_id = (SELECT address_id FROM customer WHERE user_id = ? AND isActive = 'yes') UNION SELECT address_id FROM address WHERE address_id = (SELECT address_id FROM vendor WHERE user_id = ? AND isActive = 'yes')";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, id);
	    pstmt.setInt(2, id);
	    ResultSet rst = pstmt.executeQuery();
	    int address_id = 0;
	    if (rst.next()) {
	        address_id = rst.getInt("address_id");
	    }
	    DBConnection.dbClose();
	    return address_id;
	}
}
