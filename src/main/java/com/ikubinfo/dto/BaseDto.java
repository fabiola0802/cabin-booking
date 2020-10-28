package com.ikubinfo.dto;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;

	public abstract int getId();

	public abstract void setId(int id);

}
