// Author : Anirudh Suryawanshi

package com.dto;

public class UserReservationHistoryDto {
	private int userId;
	private int reservationId;
	private String startDate;
	private String endDate;
	private String vehicleMake;
	private String vehicleModel;
	
	public UserReservationHistoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserReservationHistoryDto(int userId, int reservationId, String startDate, String endDate,
			String vehicleMake, String vehicleModel) {
		super();
		this.userId = userId;
		this.reservationId = reservationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	@Override
	public String toString() {
		return "UserReservationHistoryDto [userId=" + userId + ", reservationId=" + reservationId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", vehicleMake=" + vehicleMake + ", vehicleModel=" + vehicleModel
				+ "]";
	}
}
