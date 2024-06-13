package com.model;


public class Admin {

	private int adminId;
	private String adminFirstName;
	private String adminLastName;
	private String adminEmail;
	private String adminPhoneNumber;
	private String adminRole;
	private String adminJoinDate;
	private int adminUserId;
	
	//generate parameterized constructor
	
	public Admin(int adminId, String adminFirstName, String adminLastName, String adminEmail, String adminPhoneNumber,
			String adminRole, String adminJoinDate, int adminUserId) {
		super();
		this.adminId = adminId;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.adminEmail = adminEmail;
		this.adminPhoneNumber = adminPhoneNumber;
		this.adminRole = adminRole;
		this.adminJoinDate = adminJoinDate;
		this.adminUserId = adminUserId;
	}
	
	//Generating default constructor
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getters and setters


	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
	public String getAdminJoinDate() {
		return adminJoinDate;
	}
	public void setAdminJoinDate(String adminJoinDate) {
		this.adminJoinDate = adminJoinDate;
	}
	public int getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminFirstName=" + adminFirstName + ", adminLastName=" + adminLastName
				+ ", adminEmail=" + adminEmail + ", adminPhoneNumber=" + adminPhoneNumber + ", adminRole=" + adminRole
				+ ", adminJoinDate=" + adminJoinDate + ", adminUserId=" + adminUserId + "]";
	}
	
}
