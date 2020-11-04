package com.ikubinfo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SiteDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Min(value = 1000, message = "code should be with 4 digits")
	@Max(value = 9999, message = "code should be with 4 digits")
	private Integer code;
	private String description;
	private String location;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
