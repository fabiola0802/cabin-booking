package com.ikubinfo.enums;

public enum BadRequestMessage {

	
	INCORRECT_TYPE("The attribute that you chose is not the right type ");
	
	
	

	private String message;

	BadRequestMessage(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}
}
