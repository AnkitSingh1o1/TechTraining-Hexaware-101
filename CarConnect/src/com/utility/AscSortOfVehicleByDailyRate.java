/*Author: AKSHAY PAWAR*/

package com.utility;

import java.util.Comparator;

import com.model.Vehicle;




public class AscSortOfVehicleByDailyRate implements Comparator<Vehicle>{

	@Override
	public int compare(Vehicle v1, Vehicle v2) {
		
		return (int) (v1.getVehicle_daily_rate()-v2.getVehicle_daily_rate());	
		}
	
}


