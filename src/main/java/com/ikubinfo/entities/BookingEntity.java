package com.ikubinfo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "booking_date")
	private LocalDate bookingDate;

	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	@Column(name = "number_of_people")
	private int numberOfPeople;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "cabin_id")
	private CabinEntity cabin;

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CabinEntity getCabin() {
		return cabin;
	}

	public void setCabin(CabinEntity cabin) {
		this.cabin = cabin;
	}

	@Override
	public String toString() {
		return "BookingEntity [bookingDate=" + bookingDate + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", numberOfPeople=" + numberOfPeople + "]";
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
