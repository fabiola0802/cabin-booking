package com.ikubinfo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cabin", uniqueConstraints = @UniqueConstraint(columnNames = { "cabin_number", "site_id" }))
public class CabinEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "cabin_number", nullable = false)
	private Integer cabinNumber;

	@Column(name = "number_of_floors", nullable = false)
	private Integer numberOfFloors;

	@Column(name = "number_of_kitchens", nullable = false)
	private Integer numberOfKitchens;

	@Column(name = "number_of_bathrooms", nullable = false)
	private Integer numberOfBathrooms;

	@Column(name = "number_of_bedrooms", nullable = false)
	private Integer numberOfBedrooms;

	@Column(name = "max_capacity", nullable = false)
	private Integer maxCapacity;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "site_id")
	private SiteEntity site;

	@ManyToMany
	@JoinTable(name = "cabin_attribute", joinColumns = @JoinColumn(name = "cabin_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private List<AttributeEntity> cabinAttributes;

	@OneToMany(mappedBy = "cabin")
	private List<BookingEntity> bookings;

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

	public List<BookingEntity> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingEntity> bookings) {
		this.bookings = bookings;
	}

	public SiteEntity getSite() {
		return site;
	}

	public void setSite(SiteEntity site) {
		this.site = site;
	}

	public List<AttributeEntity> getCabinAttributes() {
		return cabinAttributes;
	}

	public void setCabinAttributes(List<AttributeEntity> cabinAttributes) {
		this.cabinAttributes = cabinAttributes;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CabinEntity [id=" + id + ", cabinNumber=" + cabinNumber + ", numberOfFloors=" + numberOfFloors
				+ ", numberOfKitchens=" + numberOfKitchens + ", numberOfBathrooms=" + numberOfBathrooms
				+ ", numberOfBedrooms=" + numberOfBedrooms + ", maxCapacity=" + maxCapacity + ", site=" + site + "]";
	}

}
