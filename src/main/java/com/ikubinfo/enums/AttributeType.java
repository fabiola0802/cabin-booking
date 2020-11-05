package com.ikubinfo.enums;

public enum AttributeType {

	SITE,

	CABIN;

	public static boolean contains(String type) {
		for (AttributeType attributeType : values()) {
			if (attributeType.name().equals(type)) {
				return true;
			}
		}
		return false;
	}
	public static AttributeType findByName(String type) {
		if (type != null) {
			return AttributeType.valueOf(type);
		} else {
			return null;
		}
	}
}
