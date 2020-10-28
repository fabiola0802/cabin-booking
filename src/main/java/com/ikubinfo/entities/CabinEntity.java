package com.ikubinfo.entities;

import java.io.Serializable;
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
@Table(name = "cabin", uniqueConstraints = @UniqueConstraint(columnNames = { "cabin_number" }))
public class CabinEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cabin_number", nullable = false)
	private int cabinNumber;

	@Column(name = "number_of_floors", nullable = false)
	private int numberOfFloors;

	@Column(name = "number_of_kitchens", nullable = false)
	private int numberOfKitchens;

	@Column(name = "number_of_bathrooms", nullable = false)
	private int numberOfBathrooms;

	@Column(name = "number_of_bedrooms", nullable = false)
	private int numberOfBedrooms;

	@Column(name = "max_capacity", nullable = false)
	private int maxCapacity;

	@ManyToOne
	@JoinColumn(name = "site_id")
	private SiteEntity site;

	@ManyToMany
	@JoinTable(name = "cabin_attribute", joinColumns = @JoinColumn(name = "cabin_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private List<AttributeEntity> cabinAttributes;

	@OneToMany(mappedBy = "cabin")
	private List<BookingEntity> bookings;

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

	public List<BookingEntity> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingEntity> bookings) {
		this.bookings = bookings;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
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

	@Override
	public String toString() {
		return "CabinEntity [id=" + id + ", cabinNumber=" + cabinNumber + ", numberOfFloors=" + numberOfFloors
				+ ", numberOfKitchens=" + numberOfKitchens + ", numberOfBathrooms=" + numberOfBathrooms
				+ ", numberOfBedrooms=" + numberOfBedrooms + ", maxCapacity=" + maxCapacity + ", site=" + site + "]";
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

}
