package com.ikubinfo.enums;

public enum BadRequestMessage {

	
	INCORRECT_TYPE("The attribute that you chose is not the right type "),
	INPUT_INVALID("Some of the attributes you chose do not exist or their type is wrong"),
	CAN_NOT_DELETE_SITE("This site cannot be deleted because it has cabins");
	
	
	

	private String message;

	BadRequestMessage(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}
}