// Author : Anirudh Suryawanshi

package com.dto;

public class CustomerReservationDetailsDto {
	private int reservationId;
	private String startDate;
	private String endDate;
	private double totalCost;
	private String status;
	private int customerId;
	private String vehicleModel;
	private String vehicleMake;
	private String vehicleYear;
	private String registrationNo;
	
	public CustomerReservationDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomerReservationDetailsDto(int reservationId, String startDate, String endDate, double totalCost,
			String status, int customerId, String vehicleModel, String vehicleMake, String vehicleYear,
			String registrationNo) {
		super();
		this.reservationId = reservationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
		this.customerId = customerId;
		this.vehicleModel = vehicleModel;
		this.vehicleMake = vehicleMake;
		this.vehicleYear = vehicleYear;
		this.registrationNo = registrationNo;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleYear() {
		return vehicleYear;
	}

	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	@Override
	public String toString() {
		return "CustomerReservationDetailsDto [reservationId=" + reservationId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", totalCost=" + totalCost + ", status=" + status + ", customerId="
				+ customerId + ", vehicleModel=" + vehicleModel + ", vehicleMake=" + vehicleMake + ", vehicleYear="
				+ vehicleYear + ", registrationNo=" + registrationNo + "]";
	}	
}
