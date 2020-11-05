package com.ikubinfo.enums;

public enum ValidationMessage {

	DATA_NOT_VALID("Data is not valid");

	private String message;

	ValidationMessage(String validationMessage) {
		message = validationMessage;

	}

	public String getMessage() {
		return message;
	}
}
