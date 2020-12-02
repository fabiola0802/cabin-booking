package com.ikubinfo.dto;

import java.time.LocalDate;

public class BookingFilter {

	private LocalDate bookingDate;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer numberOfPeople;
	private Integer userId;
	private Integer cabinId;

	private boolean checkInStartingFrom = false;
	private boolean checkOutUntil = false;

	public BookingFilter() {
		super();
	}

	public BookingFilter(LocalDate bookingDate, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfPeople) {
		super();
		this.bookingDate = bookingDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfPeople = numberOfPeople;
	}
	
	public BookingFilter(LocalDate bookingDate, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfPeople,
			Integer userId) {
		this(bookingDate, checkInDate, checkOutDate, numberOfPeople);
		this.userId = userId;
	}

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

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCabinId() {
		return cabinId;
	}

	public void setCabinId(Integer cabinId) {
		this.cabinId = cabinId;
	}

	public boolean isCheckInStartingFrom() {
		return checkInStartingFrom;
	}

	public void setCheckInStartingFrom(Boolean checkInStartingFrom) {
		this.checkInStartingFrom = Boolean.TRUE.equals(checkInStartingFrom);
	}

	public boolean isCheckOutUntil() {
		return checkOutUntil;
	}

	public void setCheckOutUntil(Boolean checkOutUntil) {
		this.checkOutUntil = Boolean.TRUE.equals(checkOutUntil);
	}

	
}
