// Author : Anirudh Suryawanshi

package com.dto;

import java.util.Objects;

public class CustomersWithTotalSpentDto {
	private int customer_id;
	private String name;
	private double totalSpent;

	public CustomersWithTotalSpentDto() {
		super();
	}

	public CustomersWithTotalSpentDto(int customer_id, String name, double totalSpent) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.totalSpent = totalSpent;
	}

	public int getCustomer_id() {
		return customer_id;
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

	public double getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer_id, name, totalSpent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomersWithTotalSpentDto other = (CustomersWithTotalSpentDto) obj;
		return customer_id == other.customer_id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(totalSpent) == Double.doubleToLongBits(other.totalSpent);
	}

	@Override
	public String toString() {
		return "CustomersWithTotalSpentDto [customer_id=" + customer_id + ", name=" + name + ", totalSpent="
				+ totalSpent + "]";
	}
}
