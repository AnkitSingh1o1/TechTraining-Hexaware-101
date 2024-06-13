package com.utility;

import java.util.Comparator;

import com.dto.AdminDto;

public class VehicleRevenueAsc implements Comparator<AdminDto> {
	@Override
	public int compare(AdminDto a1, AdminDto a2) {
		if(a1.getVehicleRevenue() < a2.getVehicleRevenue()) {
			return -1;
		}
		if(a1.getVehicleRevenue() > a2.getVehicleRevenue()) {  
			return 1; 
		}
		if(a1.getVehicleRevenue() == a2.getVehicleRevenue()) { 
			return 0;
		}
		return 0;
	}
	
}
