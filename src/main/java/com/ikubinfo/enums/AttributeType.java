package com.ikubinfo.enums;

public enum AttributeType {

	site,

	cabin;

	public static boolean contains(AttributeType type) {
		for (AttributeType attributeType : values()) {
			if (attributeType.name().equals(type.name())) {
				return true;
			}
		}
		return false;
	}
}
