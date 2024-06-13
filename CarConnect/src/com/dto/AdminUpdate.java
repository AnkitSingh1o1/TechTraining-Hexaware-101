package com.dto;

public class AdminUpdate {
	
	private String adminFirstName;
	private String adminLastName;
	private String adminEmail;
	private String adminPhoneNumber;
	private String adminRole;
	public AdminUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAdminFirstName() {
		return adminFirstName;
	}
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}
	public String getAdminLastName() {
		return adminLastName;
	}
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}
	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public AdminUpdate(String adminFirstName, String adminLastName, String adminEmail, String adminPhoneNumber,
			String adminRole) {
		super();
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.adminEmail = adminEmail;
		this.adminPhoneNumber = adminPhoneNumber;
		this.adminRole = adminRole;
	}
	
}
