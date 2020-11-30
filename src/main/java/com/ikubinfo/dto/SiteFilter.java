package com.ikubinfo.dto;

public class SiteFilter {

	private String description;
	private String location;

	public SiteFilter() {
		super();
	}

	public SiteFilter(String description, String location) {
		super();
		this.description = description;
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
