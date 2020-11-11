package com.ikubinfo.enums;

public enum ExceptionMessage {

	ATTRIBUTE_TYPE_NOT_FOUND("Attribute type not found"), SITE_NOT_FOUND("Site not found"),
	ATTRIBUTE_NOT_FOUND("Attribute  not found"), CABIN_NOT_FOUND("Cabin not found"), USER_NOT_FOUND("User not found");

	private String message;

	ExceptionMessage(String errorMessage) {
		message = errorMessage;

	}

	public String getMessage() {
		return message;
	}

}
