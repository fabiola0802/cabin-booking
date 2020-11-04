package com.ikubinfo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ikubinfo.enums.AttributeType;

public class AttributeDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 3)
	private String name;

	@NotNull
	private AttributeType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

}
