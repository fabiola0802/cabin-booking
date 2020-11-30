package com.ikubinfo.dto;

import java.util.List;

public class CabinFilter {

	private Integer numberOfFloors;
	private Integer numberOfKitchens;
	private Integer numberOfBathrooms;
	private Integer numberOfBedrooms;
	private Integer maxCapacity;
	private Double price;
	private Integer siteId;
	private List<Integer> attributeIds;

	public CabinFilter() {
		super();
	}

	public CabinFilter(Integer numberOfFloors, Integer numberOfKitchens, Integer numberOfBathrooms,
			Integer numberOfBedrooms, Integer maxCapacity, Double price, Integer siteId, List<Integer> attributeIds) {
		super();
		this.numberOfFloors = numberOfFloors;
		this.numberOfKitchens = numberOfKitchens;
		this.numberOfBathrooms = numberOfBathrooms;
		this.numberOfBedrooms = numberOfBedrooms;
		this.maxCapacity = maxCapacity;
		this.price = price;
		this.siteId = siteId;
		this.attributeIds = attributeIds;
	}

	public Integer getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(Integer numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public Integer getNumberOfKitchens() {
		return numberOfKitchens;
	}

	public void setNumberOfKitchens(Integer numberOfKitchens) {
		this.numberOfKitchens = numberOfKitchens;
	}

	public Integer getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(Integer numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public Integer getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(Integer numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public List<Integer> getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(List<Integer> attributeIds) {
		this.attributeIds = attributeIds;
	}
}
