package com.ikubinfo.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikubinfo.utils.messages.ValidationMessage;
@JsonInclude(Include.NON_NULL)
public class SiteDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	@NotNull(message = ValidationMessage.SITE_NOT_NULL)
	@Min(value = 1000, message = ValidationMessage.SITE_CODE_SIZE)
	@Max(value = 9999, message = ValidationMessage.SITE_CODE_SIZE)
	private Integer code;
	private String description;
	private String location;
	private List<AttributeDto> attributes;
	private List<CabinDto> cabins;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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

	public List<AttributeDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDto> attributes) {
		this.attributes = attributes;
	}

	public List<CabinDto> getCabins() {
		return cabins;
	}

	public void setCabins(List<CabinDto> cabins) {
		this.cabins = cabins;
	}

}
