package com.ikubinfo.dto;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
