package com.ikubinfo.dto;

public class CabinDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private int cabinNumber;
	private int numberOfFloors;
	private int numberOfKitchens;
	private int numberOfBathrooms;
	private int numberOfBedrooms;
	private int maxCapacity;
	private SiteDto site;

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

}
