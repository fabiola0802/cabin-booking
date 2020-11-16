package com.ikubinfo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikubinfo.enums.Role;
import com.ikubinfo.utils.messages.ValidationMessage;

@JsonInclude(Include.NON_NULL)
public class UserDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	@NotNull(message = ValidationMessage.NAME_NOT_NULL)
	@Pattern(regexp = "^[a-zA-Z]{3,15}", message = ValidationMessage.NAME_FORMAT)
	private String name;
	@NotNull(message = ValidationMessage.SURNAME_NOT_NULL)
	@Pattern(regexp = "^[a-zA-Z]{3,15}", message = ValidationMessage.SURNAME_FORMAT)
	private String surname;
	@NotNull(message = ValidationMessage.EMAIL_NOT_NULL)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}", message = ValidationMessage.EMAIL_FORMAT)
	private String email;
	@NotNull(message = ValidationMessage.USERNAME_NOT_NULL)
	@Pattern(regexp = "^[a-zA-Z0-9]{3,15}", message = ValidationMessage.USERNAME_FORMAT)
	private String username;
	@NotNull(message = ValidationMessage.PASSWORD_NOT_NULL)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$", message = ValidationMessage.PASSWORD_FORMAT)
	private String password;

	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
