package com.ikubinfo.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikubinfo.utils.messages.ValidationMessage;

@JsonInclude(Include.NON_NULL)
public class CabinDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	@NotNull(message = ValidationMessage.CABIN_NOT_NULL)
	@Min(value = 100, message = ValidationMessage.CABIN_NUMBER_SIZE)
	@Max(value = 999, message = ValidationMessage.CABIN_NUMBER_SIZE)
	private Integer cabinNumber;
	@NotNull(message = ValidationMessage.NUMBER_OF_FLOORS_NOT_NULL)
	@Min(value = 1, message = ValidationMessage.FLOOR_NUMBER_MIN)
	@Max(value = 2, message = ValidationMessage.FLOOR_NUMBER_MAX)
	private Integer numberOfFloors;
	private Integer numberOfKitchens;
	@NotNull(message = ValidationMessage.NUMBER_OF_BATHROOMS_NOT_NULL)
	private Integer numberOfBathrooms;
	@NotNull(message = ValidationMessage.NUMBER_OF_BEDROOMS_NOT_NULL)
	private Integer numberOfBedrooms;
	@NotNull(message = ValidationMessage.MAX_CAPACITY_NOT_NULL)
	private Integer maxCapacity;
	@NotNull(message = ValidationMessage.PRICE_NOT_NULL)
	private Double price;
	private SiteDto site;
	private List<AttributeDto> attributes;

	public Integer getCabinNumber() {
		return cabinNumber;
	}

	public void setCabinNumber(Integer cabinNumber) {
		this.cabinNumber = cabinNumber;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
