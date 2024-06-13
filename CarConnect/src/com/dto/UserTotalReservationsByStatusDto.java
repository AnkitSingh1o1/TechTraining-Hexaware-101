// Author : Anirudh Suryawanshi

package com.dto;

public class UserTotalReservationsByStatusDto {
	private int userId;
	private String reservationStatus;
	private int reservationStatusCount;
	
	public UserTotalReservationsByStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTotalReservationsByStatusDto(int userId, String reservationStatus, int reservationStatusCount) {
		super();
		this.userId = userId;
		this.reservationStatus = reservationStatus;
		this.reservationStatusCount = reservationStatusCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public int getReservationStatusCount() {
		return reservationStatusCount;
	}

	public void setReservationStatusCount(int reservationStatusCount) {
		this.reservationStatusCount = reservationStatusCount;
	}

	@Override
	public String toString() {
		return "UserTotalReservationsByStatusDto [userId=" + userId + ", reservationStatus=" + reservationStatus
				+ ", reservationStatusCount=" + reservationStatusCount + "]";
	}
}
