// Author : Anirudh Suryawanshi

package com.dto;

import java.util.Objects;

public class UserCountByRoleDto {
	private String role;
	private int userCount;

	public UserCountByRoleDto() {
		super();

	}

	public UserCountByRoleDto(String role, int userCount) {
		super();
		this.role = role;
		this.userCount = userCount;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, userCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCountByRoleDto other = (UserCountByRoleDto) obj;
		return Objects.equals(role, other.role) && userCount == other.userCount;
	}

	@Override
	public String toString() {
		return "UserCountByRole [role=" + role + ", userCount=" + userCount + "]";
	}
}
