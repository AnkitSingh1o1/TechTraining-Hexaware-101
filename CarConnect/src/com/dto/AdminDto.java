package com.dto;

public class AdminDto {
	
	public int vehicleId;
	public String vehicleModel;
	public int vehicleRevenue;
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public int getVehicleRevenue() {
		return vehicleRevenue;
	}
	public void setVehicleRevenue(int vehicleRevenue) {
		this.vehicleRevenue = vehicleRevenue;
	}
	@Override
	public String toString() {
		return "AdminDto [vehicleId=" + vehicleId + ", vehicleModel=" + vehicleModel + ", vehicleRevenue="
				+ vehicleRevenue + "]";
	}
	public AdminDto(int vehicleId, String vehicleModel, int vehicleRevenue) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleModel = vehicleModel;
		this.vehicleRevenue = vehicleRevenue;
	}
	public AdminDto() {
		super();
		
	}
	
}
