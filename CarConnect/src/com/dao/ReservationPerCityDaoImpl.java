package com.dao;
import java.sql.*;
import java.util.*;
import com.dto.ReservationPerCity;
import com.exception.*;
import com.utility.DBConnection;
public class ReservationPerCityDaoImpl implements ReservationPerCityDao{
@Override
public List<ReservationPerCity>getReservationCountPerCity() throws SQLException,DatabaseConnectionException{
Connection con=DBConnection.dbConnect();
String sql="select a.address_city, count(r.reservation_id) as reservation_count from reservation r join customer c on r.customer_id = c.customer_id join address a on c.address_id = a.address_id group by upper(a.address_city) order by reservation_count desc";
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rst=pstmt.executeQuery();

List<ReservationPerCity> reservationPerCityList=new ArrayList<>();

while (rst.next()) {
	String city=rst.getString("address_city");
	int reservationCount=rst.getInt("reservation_count");
	ReservationPerCity reservationPerCity=new ReservationPerCity(city,reservationCount);
	reservationPerCityList.add(reservationPerCity);
}DBConnection.dbClose();
return reservationPerCityList;
}
}