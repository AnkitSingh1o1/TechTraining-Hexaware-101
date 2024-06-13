package com.model;

public class Address {
private int address_id;
private String address_state;
private String address_city;
private String address_pincode;
public Address(int address_id, String address_state, String address_city, String address_pincode) {
	super();
	this.address_id = address_id;
	this.address_state = address_state;
	this.address_city = address_city;
	this.address_pincode = address_pincode;
}
public Address() {
	super();
	// TODO Auto-generated constructor stub
}
public int getAddress_id() {
	return address_id;
}
public void setAddress_id(int address_id) {
	this.address_id = address_id;
}
public String getAddress_state() {
	return address_state;
}
public void setAddress_state(String address_state) {
	this.address_state = address_state;
}
public String getAddress_city() {
	return address_city;
}
public void setAddress_city(String address_city) {
	this.address_city = address_city;
}
public String getAddress_pincode() {
	return address_pincode;
}
public void setAddress_pincode(String address_pincode) {
	this.address_pincode = address_pincode;
}
public String toString() {
	return "Address [address_id=" + address_id + ", address_state=" + address_state + ", address_city="
			+ address_city + ", address_pincode=" + address_pincode + "]";
}
}
