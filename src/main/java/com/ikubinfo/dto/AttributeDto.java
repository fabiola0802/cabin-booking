package com.ikubinfo.dto;

import com.ikubinfo.enums.AttributeType;

public class AttributeDto extends BaseDto{

	private static final long serialVersionUID = 1L;

	private String name;
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

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
