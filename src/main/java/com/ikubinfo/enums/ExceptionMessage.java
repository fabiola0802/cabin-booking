package com.ikubinfo.enums;

public enum ExceptionMessage {

	ATTRIBUTE_TYPE_NOT_FOUND("Attribute type not found"),
	SITE_NOT_FOUND("Site not found"),
	ATTRIBUTE_NOT_FOUND("Attribute  not found");
	
	

	private String message;

	ExceptionMessage(String errorMessage) {
		message = errorMessage;

	}

	public String getMessage() {
		return message;
	}

}
