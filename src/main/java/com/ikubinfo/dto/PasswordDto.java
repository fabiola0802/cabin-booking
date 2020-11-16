package com.ikubinfo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ikubinfo.utils.messages.ValidationMessage;

public class PasswordDto {
	@NotNull(message = ValidationMessage.PASSWORD_NOT_NULL)
	private String oldPassword;

	@NotNull(message = ValidationMessage.PASSWORD_NOT_NULL)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$", message = ValidationMessage.PASSWORD_FORMAT)
	private String newPassword;
	
	
	private String confirmPassword;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
