package com.ikubinfo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PasswordDto {
	@NotEmpty
	private String oldPassword;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$", 
	message = "Password should contain at least 6 characters, at least one lowercase letter at least one uppercase letter, at least a number and a special character, and no spaces allowed")
	private String newPassword;

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
