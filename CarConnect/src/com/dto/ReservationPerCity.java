package com.dto;

public class ReservationPerCity {
private String city;
private int reservationCount;
public ReservationPerCity(String city, int reservationCount) {
	super();
	this.city = city;
	this.reservationCount = reservationCount;
}
public String getCity() {
	return city;
}
public int getReservationCount() {
	return reservationCount;
}
public void setCity(String city) {
	this.city = city;
}
public void setReservationCount(int reservationCount) {
	this.reservationCount = reservationCount;
}
@Override
public String toString() {
	return "ReservationPerCity [city=" + city + ", reservationCount=" + reservationCount + "]";
}


}
