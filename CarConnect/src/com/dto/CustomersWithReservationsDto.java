// Author : Anirudh Suryawanshi

package com.dto;

import java.util.Objects;

public class CustomersWithReservationsDto {
	private int customer_id;
	private String name;
	private int numberOfReservations;

	public int getCustomer_id() {
		return customer_id;
	}

	public CustomersWithReservationsDto(int customer_id, String name, int numberOfReservations) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.numberOfReservations = numberOfReservations;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public CustomersWithReservationsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer_id, name, numberOfReservations);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomersWithReservationsDto other = (CustomersWithReservationsDto) obj;
		return customer_id == other.customer_id && Objects.equals(name, other.name)
				&& numberOfReservations == other.numberOfReservations;
	}

	@Override
	public String toString() {
		return "CustomersWithReservationsDto [customer_id=" + customer_id + ", name=" + name + ", numberOfReservations="
				+ numberOfReservations + "]";
	}

}
