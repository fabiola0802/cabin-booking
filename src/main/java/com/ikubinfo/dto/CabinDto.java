package com.ikubinfo.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CabinDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Min(value = 100, message = "cabin number should be with 3 digits")
	@Max(value = 999, message = "cabin number should be with 3 digits")
	private int cabinNumber;
	@NotNull
	@Min(value = 1, message = "Cabin should have at least 1 floor")
	@Max(value = 2, message = "Cabin cannot have more than 2 floors")
	private int numberOfFloors;
	private int numberOfKitchens;
	private int numberOfBathrooms;
	private int numberOfBedrooms;
	private int maxCapacity;
	private double price;
	private SiteDto site;
	private List<AttributeDto> attributes;

	public int getCabinNumber() {
		return cabinNumber;
	}

	public void setCabinNumber(int cabinNumber) {
		this.cabinNumber = cabinNumber;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getNumberOfKitchens() {
		return numberOfKitchens;
	}

	public void setNumberOfKitchens(int numberOfKitchens) {
		this.numberOfKitchens = numberOfKitchens;
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public SiteDto getSite() {
		return site;
	}

	public void setSite(SiteDto site) {
		this.site = site;
	}

	public List<AttributeDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDto> attributes) {
		this.attributes = attributes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
