package com.ikubinfo.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^[a-zA-Z]{3,15}", message = "Name should  contain only letters and al least 3 characters long")
	private String name;
	@Pattern(regexp = "^[a-zA-Z]{3,15}", message = "Surname should  contain only letters and al least 3 characters long")
	private String surname;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}", message = "Email is not in the correct format")
	private String email;
	@Pattern(regexp = "^[a-zA-Z0-9]{3,15}", message = "Username is not in the correct format")
	private String username;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$", 
	message = "Password should contain at least 6 characters, at least one lowercase letter at least one uppercase letter, at least a number and a special character, and no spaces allowed")
	private String password;

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

}
