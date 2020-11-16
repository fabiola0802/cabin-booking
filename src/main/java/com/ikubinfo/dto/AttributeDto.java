package com.ikubinfo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikubinfo.enums.AttributeType;
import com.ikubinfo.utils.messages.ValidationMessage;

@JsonInclude(Include.NON_NULL)
public class AttributeDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ValidationMessage.ATTRIBUTE_NOT_EMPTY)
	@Size(min = 3, message = ValidationMessage.ATTRIBUTE_NAME_SIZE)
	private String name;

	@NotNull(message = ValidationMessage.TYPE_NOT_NULL)
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
