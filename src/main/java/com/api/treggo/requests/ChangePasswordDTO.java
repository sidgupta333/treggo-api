package com.api.treggo.requests;

public class ChangePasswordDTO {

	private String username;
	private String oldPassword;
	private String newPassword;

	public ChangePasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChangePasswordDTO(String username, String oldPassword, String newPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
