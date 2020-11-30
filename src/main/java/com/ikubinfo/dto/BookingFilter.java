package com.ikubinfo.dto;

import java.time.LocalDate;

public class BookingFilter {

	private LocalDate bookingDate;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Integer numberOfPeople;
	private Integer userId;

	public BookingFilter() {
		super();
	}

	public BookingFilter(LocalDate bookingDate, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfPeople,
			Integer userId) {
		super();
		this.bookingDate = bookingDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfPeople = numberOfPeople;
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
}
